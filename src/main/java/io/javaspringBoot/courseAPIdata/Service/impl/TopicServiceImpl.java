package io.javaspringBoot.courseAPIdata.Service.impl;

import io.javaspringBoot.courseAPIdata.Converter.TopicConverter;
import io.javaspringBoot.courseAPIdata.Model.ResponseTopic;
import io.javaspringBoot.courseAPIdata.Model.TopicDAO;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import io.javaspringBoot.courseAPIdata.Repository.TopicRepository;
import io.javaspringBoot.courseAPIdata.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicConverter topicConverter;


    public ResponseTopic<List<TopicDTO>> getAllTopics() throws ResponseStatusException{

        List<TopicDTO> topics = new ArrayList();
        ResponseTopic<List<TopicDTO>> response = new ResponseTopic<>();
        List<TopicDAO> topicsData = (List<TopicDAO>) topicRepository.findAll();
        System.out.println(topicsData);
//        response = null;
         try{
             if(topicsData.isEmpty()){
                 response.setBody(topics);
                 response.setStatus(200);
                 response.setMessage("No topics in DB!");
             }else {
                 for (int i = 0; i < topicsData.size(); i++) {
                     TopicDAO topicdb = topicsData.get(i);
                     TopicDTO dto = new TopicDTO(topicdb);
                     topics.add(dto);
                 }
                 response.setBody(topics);
                 response.setStatus(200);
                 response.setMessage("Topics Displayed from the Database.");
             }
        }catch(Exception e) {
            // System.out.println("Error occured:: "+ e.getStackTrace());
             //e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Topics in Database");
        }
             return response;

    }

    public ResponseTopic<TopicDTO> getTopic(int id) {

        ResponseTopic<TopicDTO> response = new ResponseTopic<>();
        Optional<TopicDAO> opt = topicRepository.findById(id);
        try {
            if (!opt.isPresent()) {
                response.setMessage("Topic ID not Found!!");
            }else{
                TopicDTO dto = new TopicDTO(opt.get());
                response.setBody(dto);
                response.setMessage("Topic ID Data Found");
                response.setStatus(200);
            }
        }catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No Topics in Database");
        }
        return response;

    }

    public ResponseTopic<TopicDTO> addTopic(TopicDTO topicDTO) {
//        if (topicDTO.getName() == "" || topicDTO.getName() == null) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Topic Name cannot be Empty or Null!");
//        }
        ResponseTopic<TopicDTO> response = new ResponseTopic<>();

        TopicDAO topicDAO = new TopicDAO(topicDTO);
        List<TopicDAO> topicDb = (List<TopicDAO>) topicRepository.findAll();
        for (int i = 0; i < topicDb.size(); i++) {
            TopicDAO topic = topicDb.get(i);
            if (topicDTO.getName().equals(topic.getName())) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Topic ID Already present!");
            }
        }
       topicRepository.save(topicDAO);
        response.setMessage("Topic Added Successfully!");
        response.setStatus(200);
        response.setBody(topicDTO);

        return response;
    }

    public ResponseTopic<TopicDTO> updatetopic(int id, TopicDTO topicDTO) {

        ResponseTopic<TopicDTO> response = new ResponseTopic<>();
        Optional<TopicDAO> db = topicRepository.findById(id);
        if (db.isPresent()) {
            TopicDAO t = db.get();
            if (topicDTO.getName() != null)
                t.setName(topicDTO.getName());
            topicRepository.save(t);
            response.setBody(topicDTO);
            response.setMessage("Updated the Data for Topic ID: " + id);
            response.setStatus(200);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TopicID Not Found!!!!");
        }
        return response;
    }



    public ResponseTopic<TopicDTO> deleteTopic(int id) {
        ResponseTopic<TopicDTO> response = new ResponseTopic<>();
        Optional<TopicDAO> db = topicRepository.findById(id);

        if (db.isPresent()) {
            TopicDTO t = new TopicDTO(db.get());
            response.setBody(t);
            response.setMessage("Topic Details deleted for id: " + id);
            response.setStatus(200);
            topicRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TopicID Not Found in DB.");
        }
        return response;

    }

    public ResponseTopic<TopicDTO> updatePartialTopic(int id, String name){
        ResponseTopic<TopicDTO> response = new ResponseTopic<>();

        Optional<TopicDAO> db = topicRepository.findById(id);


        if (db.isPresent()) {
            TopicDTO dto = new TopicDTO(db.get());
            TopicDAO t = db.get();
            if (name!= null)
                t.setName(name);
            topicRepository.save(t);
            response.setBody(dto);
            response.setMessage("Updated the Name for Topic ID: " + id);
            response.setStatus(200);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TopicID Not Found!!!!");
        }
//        TopicDAO topicDAO = topicRepository.findById(id).get();
//        topicDAO.setName(name);
//        topicRepository.save(topicDAO);
        return response;

    }
}