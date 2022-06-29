/*package io.javaspringBoot.courseAPIdata.Controller;

import io.javaspringBoot.courseAPIdata.Model.Course;
import io.javaspringBoot.courseAPIdata.Service.impl.CourseServiceImpl;
import io.javaspringBoot.courseAPIdata.Model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    @Autowired //dependency injection
    public CourseServiceImpl courseServiceImpl;

    @RequestMapping("/topics/{id}/courses") //will be converted to json by spring mvc
    public List<Course> getAllCourses(@PathVariable int id){
        return courseServiceImpl.getAllCourses(id);
    }

    @GetMapping("/topics/{topicId}/courses/{id}")
    public Optional<Course> getCourses(@PathVariable int id){
        return courseServiceImpl.getCourses(id);
    }

    @PostMapping("/add/topics/{topicId}/courses")
    public void addCourses(@RequestBody Course course,@PathVariable int topicId)//pick this instance from request payload
    {
        course.setTopic(new Topic());
        courseServiceImpl.addCourses(course);
    }

    @PutMapping("/update/topics/{topicId}/courses/{id}")
    public void updateCourses(@RequestBody Course course, @PathVariable int topicId, @PathVariable String id)//pick this instance from request payload
    {
        course.setTopic(new Topic());

        courseServiceImpl.updateCourses(course);
    }

    @DeleteMapping("delete/topics/{topicId}/courses/{id}")
    public void deleteTopic(@PathVariable int id){
         courseServiceImpl.deleteCourses(id);
    }
}
*/