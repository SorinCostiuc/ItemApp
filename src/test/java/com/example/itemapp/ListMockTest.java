package com.example.itemapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListMockTest {

    @Mock
    List<String> mockedList = new ArrayList<>();

    @Test
    void shouldReturnSizeGreaterThanZero() {
        when(mockedList.size()).thenReturn(5);

        when(mockedList.get(1)).thenReturn("element at index 1");

        assertEquals(5, mockedList.size());
        assertEquals("element at index 1", mockedList.get(1));
    }

    @Test
    void shouldReturnSizeAtSecondCall() {
        when(mockedList.size()).thenReturn(10).thenReturn(400);

        assertEquals(10, mockedList.size());
        assertEquals(400, mockedList.size());
    }

    @Test
    void shouldReturnValueForAnyCallToGet() {
        when(mockedList.get(anyInt())).thenReturn("java");

        assertEquals("java", mockedList.get(50));
        assertEquals("java", mockedList.get(30));
        assertEquals("java", mockedList.get(4315));
        assertEquals("java", mockedList.get(Integer.MAX_VALUE));
    }

    @Test
    void shouldThrowException() {
        when(mockedList.get(anyInt())).thenThrow(IllegalArgumentException.class);

        assertEquals("java", mockedList.get(50));
        assertEquals("java", mockedList.get(30));
        assertEquals("java", mockedList.get(4315));
        assertEquals("java", mockedList.get(Integer.MAX_VALUE));
    }

    @Test
    void verifyTest() {
        mockedList.add("test");
        mockedList.add("test");
        mockedList.add("java");

        verify(mockedList, times(2)).add("test");
        verify(mockedList).add("java");
    }

    @Test
    void verifyNoInteractionsTest() {
        verifyNoInteractions(mockedList);
    }
}
