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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    ValueCalculatorService valueCalculatorService;

    @Test
    public void retrieveHardcodedItemTest() {
        //given
        Item expectedItem = new Item(1, "Toys", 20, 40);
        //when
        Item actualResult = itemService.retrieveHardcodedItem();
        //then
        assertEquals(expectedItem, actualResult);
    }

    @Test
    public void retrieveItemByIdTest() {
        //given
        Item expectedResult = new Item(1, "Toys", 50, 10);
        Mockito.when(itemRepository.findById(50)).thenReturn(Optional.of(expectedResult));
        Mockito.when(valueCalculatorService.calculateValue(50, 10)).thenReturn(500);
        //when
        Item actualResult = itemService.retrieveById(50);
        //then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteItemTest() {
        //given
        Item itemToBeDeleted = new Item(1000, "itemToBeDeleted", 50, 10);
        Mockito.when(itemRepository.findById(1000)).thenReturn(Optional.of(itemToBeDeleted));
        //when
        itemService.deleteItem(1000);
        //then
        Mockito.verify(itemRepository).delete(itemToBeDeleted);
    }
}
