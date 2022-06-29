package io.javaspringBoot.courseAPIdata.Service.impl;

import io.javaspringBoot.courseAPIdata.Converter.TopicConverter;
import io.javaspringBoot.courseAPIdata.Model.Topic;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import io.javaspringBoot.courseAPIdata.Repository.TopicRepository;
import io.javaspringBoot.courseAPIdata.Service.TopicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicServiceImpl implements TopicDAO {

    @Autowired
    public TopicRepository topicRepository;

    @Autowired
    public TopicConverter topicConverter;


    public List<TopicDTO> getAllTopics() {

        List<Topic> topics = new ArrayList();
          topicRepository.findAll().forEach(topics::add);
        return topicConverter.entityToDto(topics);
    }

    public Optional<Topic> getTopic(int id) {

        //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return topicRepository.findById(id);

    }

    public void addTopic(TopicDTO topicDTO) {
        Topic topic = topicConverter.dtoToEntity(topicDTO);
        topicRepository.save(topic);
    }

    public void updatetopic(int id, TopicDTO topicDTO){
        Topic topic = topicConverter.dtoToEntity(topicDTO);
        topicRepository.save(topic);
            }



    public void deleteTopic(int id) {
        //topics.removeIf(t-> t.getId().equals(id));
        topicRepository.deleteById(id);
    }

    public void updatePartialTopic(TopicDTO topicDTO, int id, String name){
      //  Topic topic = topicConverter.dtoToEntity(topicDTO);
        Topic topic = topicRepository.findById(id).get();
        topic.setName(name);
        topicRepository.save(topic);

    }
}