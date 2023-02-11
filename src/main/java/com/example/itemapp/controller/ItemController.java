package com.example.itemapp.controller;

import com.example.itemapp.model.Item;
import com.example.itemapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/dummy-item")
    public Item dummyItem() {
        return new Item(1, "Ball", 10, 100);
    }

    @GetMapping("/item-from-business-service")
    public Item itemFromBusinessService() {
        return itemService.retrieveHardcodedItem();
    }

    @GetMapping("/{id}")
    public Item retrieveById(@PathVariable int id) {
        return itemService.retrieveById(id);
    }

    @GetMapping
    public List<Item> retrieveAllItems() {
        return itemService.retrieveAllItems();
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItem(item);
    }
}
