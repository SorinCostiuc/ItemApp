package com.example.itemapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @Mock
    List<String> myList;

    @Test
    void checkListSizeTest() {
        //given
        //when
        //then
        Mockito.when(myList.size()).thenReturn(5);
        Mockito.when(myList.get(899)).thenReturn("java");
        assertEquals(5, myList.size());
        assertEquals("java", myList.get(899));
    }

    @Test
    void verifyInteractionTest() {
        myList.add("php");
        Mockito.verify(myList).add("php");
    }
}
