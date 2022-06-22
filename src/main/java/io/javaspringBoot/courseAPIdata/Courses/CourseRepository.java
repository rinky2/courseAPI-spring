package io.javaspringBoot.courseAPIdata.Courses;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {

    //takes a name does an arbitory search in db for all the courses that have the name property as passed here
    public List<Course> findByTopicId(String topicId);

}
