package org.example.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.ItemErrorResponseDto;
import org.example.dto.ItemRequestDto;
import org.example.dto.ItemResponseDto;
import org.example.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/")
    public List<ItemResponseDto> getAll() {
        return itemService.findAll();
    }

    @GetMapping("/{title}")
    public ItemResponseDto getByTitle(@PathVariable String title) {
        return itemService.findByTitle(title);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(
            @RequestParam (value = "count", required = false) Integer count,
            @RequestBody ItemRequestDto itemRequestDto
            ) {
        itemService.createAndSave(count, itemRequestDto);
    }
}
