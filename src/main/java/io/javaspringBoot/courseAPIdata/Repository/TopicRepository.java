package io.javaspringBoot.courseAPIdata.Repository;

import io.javaspringBoot.courseAPIdata.Model.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    //void delete(String );
    //getAllCourses()
    //getTopic(String Id)
    //updateTopic(Course t)
    //deleteTopic(String Id)

}
