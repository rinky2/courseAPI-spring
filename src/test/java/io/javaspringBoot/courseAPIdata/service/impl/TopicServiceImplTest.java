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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.logging.SocketHandler;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
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

        List<TopicDAO> topicsDAOList = new ArrayList<>();
        when(topicRepository.findAll()).thenReturn(topicsDAOList);

        //Expected resp
        ResponseTopic<List<TopicDTO>> expectedResp = new ResponseTopic<>();
        List<TopicDTO> topicDtoList = new ArrayList<>();
        expectedResp.setBody(topicDtoList);
        expectedResp.setMessage("No topics in DB!");
        expectedResp.setStatus(200);

        ResponseTopic<List<TopicDTO>> actualResp = topicService.getAllTopics();
        System.out.println("Resp:: " + actualResp);

        assertEquals(actualResp, expectedResp);
    }

    @Test//(expected = ResponseStatusException.class)
    public void testGetAllTopics_NoResponse() {
        when(topicRepository.findAll()).thenReturn(null);
        ResponseStatusException thrown = Assertions
                .assertThrows(ResponseStatusException.class, () -> {
                    topicService.getAllTopics();
                }, "Exception error was expected");

        Assertions.assertEquals("400 BAD_REQUEST \"No Topics in Database\"", thrown.getMessage());

    }

    @Test
    public void testGetTopic() {
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        when(topicRepository.findById(1)).thenReturn(Optional.ofNullable(topicDAO));

        //Expected resp
        ResponseTopic<TopicDTO> expectedResp = new ResponseTopic<>();
        TopicDTO dto = new TopicDTO(topicDAO);
        expectedResp.setBody(dto);
        expectedResp.setMessage("Topic ID Data Found");
        expectedResp.setStatus(200);

        assertEquals(topicService.getTopic(1), expectedResp);
        Assert.assertNotNull(topicDAO);
    }

    @Test
    public void testGetTopic_NoData() {

        TopicDAO topicDAO = new TopicDAO();
        when(topicRepository.findById(200)).thenReturn(Optional.ofNullable(topicDAO));

        //Expected resp
        ResponseTopic<TopicDTO> expectedResp = new ResponseTopic<>();
        expectedResp.setBody(null);
        expectedResp.setMessage("Topic ID not Found!!");
        expectedResp.setStatus(0);

        ResponseTopic<TopicDTO> actualResp = topicService.getTopic(1);
        System.out.println("Resp:: " + actualResp);

        assertEquals(actualResp, expectedResp);
    }

    @Test
    public void testGetTopic_NoResponse() {
        when(topicRepository.findById(1)).thenReturn(null);
        ResponseStatusException thrown = Assertions
                .assertThrows(ResponseStatusException.class, () -> {
                    topicService.getTopic(1);
                }, "Exception error was expected");

        Assertions.assertEquals("500 INTERNAL_SERVER_ERROR \"No Topics in Database\"", thrown.getMessage());

    }
    @Test
    public void testAddTopic(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        List<TopicDAO> topicsDAOList = new ArrayList<>();
        topicsDAOList.add(topicDAO);
        TopicDTO dto = new TopicDTO(topicDAO);
        when(topicRepository.save(topicDAO)).thenReturn(topicDAO);

        ResponseTopic<TopicDTO> actualResp = topicService.addTopic(dto);
        ResponseTopic<TopicDTO> expectedResp = new ResponseTopic<>();
        expectedResp.setBody(dto);
        expectedResp.setMessage("Topic Added Successfully!");
        expectedResp.setStatus(200);

        System.out.println("Actual Resp::" +actualResp);
        System.out.println("Exp Resp::" +expectedResp);
        assertEquals(actualResp,expectedResp);
    }
    @Test
    public void testAddTopic_topicExists(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        List<TopicDAO> topicsDAOList = new ArrayList<>();
        topicsDAOList.add(topicDAO);
        TopicDTO dto = new TopicDTO(topicDAO);
        when(topicRepository.findAll()).thenReturn(topicsDAOList);
        ResponseStatusException thrown = Assertions
                .assertThrows(ResponseStatusException.class, () -> {
                    topicService.addTopic(dto);
                }, "Exception error was expected");
        Assertions.assertEquals("302 FOUND \"Topic ID Already present!\"", thrown.getMessage());
    }

    @Test
    public void testUpdateTopic(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        when(topicRepository.findById(1)).thenReturn(Optional.ofNullable(topicDAO));
        when(topicRepository.save(topicDAO)).thenReturn(topicDAO);
        ResponseTopic<TopicDTO> expectedResp = new ResponseTopic<>();
        TopicDTO dto = new TopicDTO(topicDAO);

        expectedResp.setBody(dto);
        expectedResp.setMessage("Updated the Data for Topic ID: 1");
        expectedResp.setStatus(200);
        System.out.println("Exp Resp::" +expectedResp);

        assertEquals(topicService.updatetopic(1,dto),expectedResp);
    }
    @Test
    public void testUpdateTopic_NoData(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        TopicDTO dto = new TopicDTO(topicDAO);
        when(topicRepository.findById(200)).thenReturn(null);
        ResponseStatusException thrown = Assertions
                .assertThrows(ResponseStatusException.class, () -> {
                    topicService.updatetopic(1,dto);
                }, "Exception error was expected");

        Assertions.assertEquals("404 NOT_FOUND \"TopicID Not Found!!!!\"", thrown.getMessage());


    }

    @Test
    public void testUpdatePartialTopic(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        when(topicRepository.findById(1)).thenReturn(Optional.ofNullable(topicDAO));

        ResponseTopic<TopicDTO> expectedResp = new ResponseTopic<>();
        TopicDTO dto = new TopicDTO(topicDAO);

        String name =topicDAO.getName();
        dto.setName(name);
        when(topicRepository.save(topicDAO)).thenReturn(topicDAO);
        expectedResp.setBody(dto);
        expectedResp.setMessage("Updated the Name for Topic ID: 1");
        expectedResp.setStatus(200);
        System.out.println("Exp Resp::" +expectedResp);

        assertEquals(topicService.updatePartialTopic(1,"Java"),expectedResp);
    }

    @Test
    public void testUpdatePartialTopic_NoData(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        TopicDTO dto = new TopicDTO(topicDAO);
        when(topicRepository.findById(2)).thenReturn(Optional.ofNullable(topicDAO));
        ResponseStatusException thrown = Assertions
                .assertThrows(ResponseStatusException.class, () -> {
                    topicService.updatePartialTopic(200,"Java");
                }, "Exception error was expected");

        Assertions.assertEquals("404 NOT_FOUND \"TopicID Not Found!!!!\"", thrown.getMessage());


    }

    @Test
    public void testDeleteTopic(){
        TopicDAO topicDAO = new TopicDAO();
        topicDAO.setId(1);
        topicDAO.setName("Java");
        topicDAO.setDescription("Java Description");
        when(topicRepository.findById(topicDAO.getId())).thenReturn(Optional.of(topicDAO));
        topicService.deleteTopic(topicDAO.getId());
        verify(topicRepository).deleteById(topicDAO.getId());


    }
    @Test
    public void testDeleteTopic_NoData(){

            when(topicRepository.findById(200)).thenReturn(null);
            Assertions
                    .assertThrows(ResponseStatusException.class, () -> {
                        topicService.deleteTopic(1);
                    }, "Exception error was expected");
        }


    }