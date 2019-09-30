package com.epam.brest2019.courses.rest_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class VersionControllerTest {

    private static final String VERSION = "0.0.1";

    @Autowired
    private VersionController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void before(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .alwaysDo(MockMvcResultHandlers.print())
                .build();
    }


    @Test
    void version() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/version")
        ).andExpect(MockMvcResultMatchers.status().isOk())
         .andExpect(MockMvcResultMatchers.content().string("\"" + VERSION + "\""));
    }

}
