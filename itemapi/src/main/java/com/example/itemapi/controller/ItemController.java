package com.example.itemapi.controller;

import com.example.itemapi.model.Item;
import com.example.itemapi.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Tag(
        name = "Item APIs",
        description = "APIs for managing items (add item, get item by ID)"
)
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(
            summary = "Add a new item",
            description = "Creates a new item and stores it in memory"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.addItem(item);
        return ResponseEntity.ok(savedItem);
    }

    @Operation(
            summary = "Get item by ID",
            description = "Fetches a single item using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found"),
            @ApiResponse(responseCode = "404", description = "Item not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }
}
