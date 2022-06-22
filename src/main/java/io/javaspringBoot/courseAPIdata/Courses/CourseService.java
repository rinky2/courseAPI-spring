package io.javaspringBoot.courseAPIdata.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    public CourseRepository courseRepository;

    public List<Course> getAllCourses(String topicId) {

        List<Course> courses = new ArrayList();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Optional<Course> getCourses(String id) {

        //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();

        return courseRepository.findById(id);
    }

    public void addCourses(Course course) {

        courseRepository.save(course);
    }

    public void updateCourses(Course course){
//        for (int i =0; i<topics.size();i++){
//            Course t = topics.get(i);
//            if(t.getId().equals(id)){
//                topics.set(i,course);
//                return;
        courseRepository.save(course);
            }



    public void deleteCourses(String id) {
        //topics.removeIf(t-> t.getId().equals(id));
        courseRepository.deleteById(id);
    }
}