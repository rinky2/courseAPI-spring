package io.javaspringBoot.courseAPIdata.Service;

import io.javaspringBoot.courseAPIdata.Model.Topic;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;

import java.util.List;
import java.util.Optional;

public interface TopicDAO {
    List<TopicDTO> getAllTopics();

    Optional<Topic> getTopic(int id);

    void addTopic(TopicDTO topicDTO);

    void updatetopic(int id, TopicDTO topicDTO);

    void deleteTopic(int id);

    void updatePartialTopic(TopicDTO topicDTO, int id, String name);
}
