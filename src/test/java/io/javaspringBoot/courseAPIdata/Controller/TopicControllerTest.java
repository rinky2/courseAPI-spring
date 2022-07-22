package io.javaspringBoot.courseAPIdata.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.javaspringBoot.courseAPIdata.CourseApiDataApplicationTests;
import io.javaspringBoot.courseAPIdata.Model.ResponseTopic;
import io.javaspringBoot.courseAPIdata.Model.TopicDAO;
import io.javaspringBoot.courseAPIdata.Model.TopicDTO;
import io.javaspringBoot.courseAPIdata.Service.TopicService;
import io.javaspringBoot.courseAPIdata.Service.impl.TopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class TopicControllerTest extends CourseApiDataApplicationTests {

    @Mock
    TopicServiceImpl topicService;

    @InjectMocks
    private TopicController topicController;

    TopicDTO topicDTO = new TopicDTO();
    List<TopicDTO> topics = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(topicController)
                .build();
    }
    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        topicDTO= new TopicDTO();
        topicDTO.setId(1);
        topicDTO.setName("Java");
        topicDTO.setDescription("Java Description");
        topics.add(topicDTO);
    }

    @Test
    void getAllTopics() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/topics"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(topics);
    }
    @Test
    public void  getTopic() throws Exception {
        ResponseTopic<TopicDTO> resp = new ResponseTopic<>();
        resp.setBody(topicDTO);
        resp.setMessage("Topic ID Data Found");
        resp.setStatus(200);
        when(topicService.getTopic(1)).thenReturn(resp);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/topics/1"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(topicDTO);

    }

    @Test
    void addTopic() throws Exception {
        ResponseTopic<TopicDTO> topicDTO = new ResponseTopic<>();
        when(topicService.addTopic(any(TopicDTO.class))).thenReturn(topicDTO);
        mockMvc.perform(MockMvcRequestBuilders.post("/topics")
                .content(new ObjectMapper().writeValueAsString(topicDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertNotNull(topicDTO);
    }

    @Test
    void deleteTopic() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/topics/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePartialTopic() throws Exception {
        ResponseTopic<TopicDTO> resp = new ResponseTopic<>();
        topicDTO.setName("Java EE");
        resp.setBody(topicDTO);
        resp.setMessage("Topic ID Data Found");
        resp.setStatus(200);
        when(topicService.updatePartialTopic(1, "Java EE")).thenReturn(resp);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/topics/patch/1/Java EE"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(topicDTO);
    }

    @Test
    void updateTopic() throws Exception {
        ResponseTopic<TopicDTO> topicsDTO = new ResponseTopic<>();
        topicDTO.setId(1);
        topicDTO.setName("Java");
        topicDTO.setDescription("Java Description");
        when(topicService.updatetopic(1,topicDTO)).thenReturn(topicsDTO);
        mockMvc.perform(MockMvcRequestBuilders.put("/topics/update/1")
                .content(new ObjectMapper().writeValueAsString(topicDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertNotNull(topicDTO);



    }
}