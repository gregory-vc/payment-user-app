package org.example.controller;

import org.example.dto.ItemResponseDto;
import org.example.dto.ListItemsResponseDto;
import org.example.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/items")
public class ItemControllerV2 {

    private final ItemService itemService;

    public ItemControllerV2(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public ListItemsResponseDto getAll() {
        return itemService.getListWithAllItems();
    }
}
