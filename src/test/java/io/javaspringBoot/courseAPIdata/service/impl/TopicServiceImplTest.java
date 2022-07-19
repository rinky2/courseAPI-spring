package io.javaspringBoot.courseAPIdata.service.impl;

import io.javaspringBoot.courseAPIdata.Converter.TopicConverter;
import io.javaspringBoot.courseAPIdata.Model.ResponseTopic;
import io.javaspringBoot.courseAPIdata.Model.TopicDAO;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import io.javaspringBoot.courseAPIdata.Repository.TopicRepository;
import io.javaspringBoot.courseAPIdata.Service.impl.TopicServiceImpl;
import org.apache.commons.logging.Log;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;

import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTest {


    @Mock
    TopicRepository topicRepository;

    @InjectMocks
    TopicServiceImpl topicService;


    @Test
    public void getAllTopicsTest() {
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");

        List<TopicDAO> topicsDAOList = new ArrayList<>();
        topicsDAOList.add(topicDAO);
        when(topicRepository.findAll()).thenReturn(topicsDAOList);

        //Expected resp
        ResponseTopic<List<TopicDTO>> expectedResp = new ResponseTopic<>();
        TopicDTO dto = new TopicDTO(topicDAO);
        List<TopicDTO> topicDtoList = new ArrayList<>();
        topicDtoList.add(dto);
        expectedResp.setBody(topicDtoList);
        expectedResp.setMessage("Topics Displayed from the Database.");
        expectedResp.setStatus(200);


        ResponseTopic<List<TopicDTO>> actualResp = topicService.getAllTopics();
        System.out.println("Resp:: " + actualResp);

        assertEquals(actualResp, expectedResp);
    }

    @Test
    public void testGetAllTopics_NoData() {
        TopicDAO topicDao = new TopicDAO();
        List<TopicDAO> topicsDAOList = new ArrayList<>();
        when(topicRepository.findAll()).thenReturn(topicsDAOList);

        //Expected resp
        ResponseTopic<List<TopicDTO>> expectedResp = new ResponseTopic<>();
        TopicDTO dto = new TopicDTO(topicDao);
        List<TopicDTO> topicDtoList = new ArrayList<>();
        expectedResp.setBody(topicDtoList);
        expectedResp.setMessage("No topics in DB!");
        expectedResp.setStatus(200);

        ResponseTopic<List<TopicDTO>> actualResp = topicService.getAllTopics();
        System.out.println("Resp:: " + actualResp);

        assertEquals(actualResp, expectedResp);
    }


}