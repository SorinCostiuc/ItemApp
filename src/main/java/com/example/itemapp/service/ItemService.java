package com.example.itemapp.service;

import com.example.itemapp.data.ItemRepository;
import com.example.itemapp.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    @Autowired
    private ValueCalculatorService valueCalculatorService;

    public Item retrieveHardcodedItem() {
        return new Item(1, "Toys", 20, 40);
    }

    public Item retrieveById(int id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            int calculatedValue = valueCalculatorService.calculateValue(item.getPrice(), item.getQuantity());
            item.setValue(calculatedValue);
            return item;
        }
        return null;
    }

    public Item saveItem(Item item) {
        Optional<Item> optionalItem = repository.findById(item.getId());
        if (optionalItem.isEmpty()) {
            return repository.save(item);
        }
        throw new IllegalArgumentException("Item with id: " + item.getId() + " already exists");
    }

    public Item updateItem(Item item) {
        Optional<Item> optionalItem = repository.findById(item.getId());
        if (optionalItem.isPresent()) {
            return repository.save(item);
        }
        throw new IllegalArgumentException("There is no item with id: " + item.getId());
    }

    public List<Item> retrieveAllItems() {
        List<Item> items = repository.findAll();
        for (Item item : items) {
            item.setValue(valueCalculatorService.calculateValue(item.getPrice(), item.getQuantity()));
        }
        return items;
    }

    public void deleteItem(int id) {
        Optional<Item> optionalItem = repository.findById(id);
        if (optionalItem.isPresent()) {
            repository.delete(optionalItem.get());
        } else {
            throw new IllegalArgumentException("There is no item with id: " + id);
        }
    }
}
