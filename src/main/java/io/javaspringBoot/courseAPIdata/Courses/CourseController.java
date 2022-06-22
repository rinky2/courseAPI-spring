package io.javaspringBoot.courseAPIdata.Courses;

import io.javaspringBoot.courseAPIdata.Topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired //dependency injection
    private CourseService courseService;

    @RequestMapping("/topics/{id}/courses") //will be converted to json by spring mvc
    public List<Course> getAllCourses(@PathVariable String id){
        return courseService.getAllCourses(id);
    }

    @RequestMapping("/topics/{topicId}/courses/{id}")
    public Optional<Course> getCourses(@PathVariable String id){
        return courseService.getCourses(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topics/{topicId}/courses")
    public void addCourses(@RequestBody Course course,@PathVariable String topicId)//pick this instance from request payload
    {
        course.setTopic(new Topic(topicId, "",""));
        courseService.addCourses(course);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{id}")
    public void updateCourses(@RequestBody Course course, @PathVariable String topicId, @PathVariable String id)//pick this instance from request payload
    {
        course.setTopic(new Topic(topicId, "",""));

        courseService.updateCourses(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{id}")
    public void deleteTopic(@PathVariable String id){
         courseService.deleteCourses(id);
    }
}
