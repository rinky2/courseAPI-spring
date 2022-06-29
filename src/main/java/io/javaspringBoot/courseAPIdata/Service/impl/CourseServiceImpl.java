/*package io.javaspringBoot.courseAPIdata.Service.impl;

import io.javaspringBoot.courseAPIdata.Model.Course;
import io.javaspringBoot.courseAPIdata.Repository.CourseRepository;
import io.javaspringBoot.courseAPIdata.Service.CourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public abstract class CourseServiceImpl implements CourseDAO {

    @Autowired
    public CourseRepository courseRepository;


    public List<Course> getAllCourses(int topicId) {

        List<Course> courses = new ArrayList();
        courseRepository.findByTopicId(topicId).forEach(courses::add);
        return courses;
    }

    public Optional<Course> getCourses(int id) {

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



    public void deleteCourses(int id) {
        //topics.removeIf(t-> t.getId().equals(id));
        courseRepository.deleteById(id);
    }
}
*/