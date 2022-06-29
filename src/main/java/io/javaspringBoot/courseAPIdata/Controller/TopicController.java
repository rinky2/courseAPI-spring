package io.javaspringBoot.courseAPIdata.Controller;

import io.javaspringBoot.courseAPIdata.Model.Topic;
import io.javaspringBoot.courseAPIdata.Service.impl.TopicServiceImpl;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TopicController {

    @Autowired //dependency injection
    private TopicServiceImpl topicServiceImpl;



    @RequestMapping("/topics") //will be converted to json by spring mvc
    @ResponseStatus(HttpStatus.OK)
    public List<TopicDTO> getAllTopics(){
        return topicServiceImpl.getAllTopics();
    }

    @RequestMapping("/topics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Topic> getTopic(@PathVariable int id){
        return topicServiceImpl.getTopic(id);
    }

    @PostMapping ("/topics")
    public void addTopic(@RequestBody Topic topic)//pick this instance from request payload
    {
        topicServiceImpl.addTopic(topic);
    }

    @PutMapping( "/topics/update/{id}")
    public void addTopic(@RequestBody Topic topic, @PathVariable int id)//pick this instance from request payload
    {
        topicServiceImpl.updatetopic(id,topic);
    }

    @DeleteMapping("/topics/delete/{id}")
    public void deleteTopic(@PathVariable int id){
         topicServiceImpl.deleteTopic(id);
    }

    @PatchMapping("/topics/patch/{id}/{name}")
    public ResponseEntity<Topic> updatePartialTopic(@RequestBody Topic topic, @PathVariable int id , @PathVariable String name){
        topicServiceImpl.updatePartialTopic(topic,id,name);
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(topic, responseHeaders, HttpStatus.OK);
    }
}
