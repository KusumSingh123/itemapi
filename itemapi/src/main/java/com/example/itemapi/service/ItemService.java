package com.example.itemapi.service;

import com.example.itemapi.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();
    private Long idCounter = 1L;

    // Add new item
    public Item addItem(Item item) {
        item.setId(idCounter++);
        items.add(item);
        return item;
    }

    // Get item by ID
    public Item getItemById(Long id) {
        return items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
