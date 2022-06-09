package com.example.itemapp;

import com.example.itemapp.model.Item;
import com.example.itemapp.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void retrieveByIdTest() throws Exception {
        //given
        Item expectedItem = new Item(10, "toys", 40, 20);
        expectedItem.setValue(30);
        //when + then
        when(itemService.retrieveById(10)).thenReturn(expectedItem);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/items/{id}", 10)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{id:10,name:toys,price:40,quantity:20}"));
    }
}
