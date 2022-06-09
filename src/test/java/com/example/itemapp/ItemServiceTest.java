package com.example.itemapp;

import com.example.itemapp.data.ItemRepository;
import com.example.itemapp.model.Item;
import com.example.itemapp.service.ItemService;
import com.example.itemapp.service.ValueCalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository repository;

    @Mock
    private ValueCalculatorService calculatorService;

    @Test
    void returnHardcodedItemTest(){
        //given
        Item expectedItem = new Item(1, "Toys", 20, 40);
        //when
        Item actualItem = itemService.retrieveHardcodedItem();
        //then
        assertEquals(expectedItem, actualItem);
    }

    @Test
    void retrieveByIdTest() {
        //given
        int givenId = 40;
        Item expectedItem = new Item(99,"fruits", 40,10);
        //when
        when(repository.findById(40)).thenReturn(Optional.of(expectedItem));
        when(calculatorService.calculateValue(40, 10)).thenReturn(400);
        Item actualItem = itemService.retrieveById(givenId);
        //then
        assertEquals(expectedItem, actualItem);
        assertEquals(400, actualItem.getValue());
    }

    @Test
    void retrieveAllItemsTest() {
        //given
        Item expectedFirstItem = new Item(99,"fruits", 40,10);
        Item expectedSecondItem = new Item(99,"fruits", 40,10);
        List<Item> expectedItemList = List.of(expectedFirstItem, expectedSecondItem);
        //when
        when(repository.findAll()).thenReturn(expectedItemList);
        when(calculatorService.calculateValue(anyInt(), anyInt())).thenReturn(100);
        List<Item> actualItems = itemService.retrieveAllItems();
        //then
        assertThat(actualItems).isNotEmpty().isNotNull().hasSize(2).isEqualTo(expectedItemList);
    }

    @Test
    void saveItemTest() {
        //given
        Item givenItem = new Item(99,"fruits", 40,10);
        //when
        when(repository.findById(99)).thenReturn(Optional.empty());
        //then
        verify(repository).save(givenItem);
    }

    @Test
    void saveItemThrowsExceptionTest() {
        //given
        Item givenItem = new Item(99,"fruits", 40,10);
        //when + then
        when(repository.findById(99)).thenReturn(Optional.of(givenItem));
        assertThrows(IllegalArgumentException.class, () -> itemService.saveItem(givenItem));
    }

}
