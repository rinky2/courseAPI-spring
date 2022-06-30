package io.javaspringBoot.courseAPIdata.Repository;

import io.javaspringBoot.courseAPIdata.Model.TopicDAO;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<TopicDAO, Integer> {
    //void delete(String );
    //getAllCourses()
    //getTopic(String Id)
    //updateTopic(Course t)
    //deleteTopic(String Id)

}
