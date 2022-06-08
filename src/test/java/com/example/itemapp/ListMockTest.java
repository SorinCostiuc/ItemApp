package com.example.itemapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListMockTest {

    @Mock
    List<String> mockedList;

    @Test
    void shouldReturnSizeGreaterThanZero() {
        when(mockedList.size()).thenReturn(5);
        when(mockedList.get(1)).thenReturn("elemnt at index 1");
        assertEquals(5, mockedList.size());
        assertEquals("elemnt at index 1", mockedList.get(1));
    }
}
