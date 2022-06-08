package com.example.itemapp.controller;

import com.example.itemapp.model.Item;
import com.example.itemapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {

        Item item = itemService.retrieveHardcodedItem();
        return item;
    }

    @GetMapping("/items/{id}")
    public Item retrieveById(@PathVariable int id) {
        return itemService.retrieveById(id);
    }

    @GetMapping("/all-items")
    public List<Item> retrieveAllItems() {
        return itemService.retrieveAllItems();
    }

    @PostMapping("/items")
    public Item createItem(@RequestBody Item item) {
        Item createdItem = itemService.saveItem(item);
        return createdItem;
    }

    @PutMapping("/items")
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }
}
