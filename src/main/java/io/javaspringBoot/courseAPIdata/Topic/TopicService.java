package io.javaspringBoot.courseAPIdata.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    public TopicRepository topicRepository;

//    List<Course> topics = new ArrayList<>(Arrays.asList(
//            new Course("spring", "Spring Framework", "Spring Framework Description"),
//            new Course("Java", "CoreJava", "COre Java Description"),
//            new Course("Javascript", "JavaScript Framework", "JavaScript Description")
//    ));

    public List<Topic> getAllTopics() {

        List<Topic> topics = new ArrayList();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(String id) {

        //return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
        return topicRepository.findById(id);
    }

    public void addTopic(Topic topic) {

        topicRepository.save(topic);
    }

    public void updatetopic(String id, Topic topic){
//        for (int i =0; i<topics.size();i++){
//            Course t = topics.get(i);
//            if(t.getId().equals(id)){
//                topics.set(i,topic);
//                return;
        topicRepository.save(topic);
            }



    public void deleteTopic(String id) {
        //topics.removeIf(t-> t.getId().equals(id));
        topicRepository.deleteById(id);
    }
}