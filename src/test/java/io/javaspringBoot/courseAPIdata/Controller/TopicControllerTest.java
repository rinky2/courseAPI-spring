package io.javaspringBoot.courseAPIdata.Controller;


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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
}