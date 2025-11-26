package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.ItemRequestDto;
import org.example.dto.ItemResponseDto;
import org.example.dto.ListItemsResponseDto;
import org.example.entity.Item;
import org.example.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemResponseDto findByTitle(String title) {
        return itemRepository.findByTitle(title)
                .map(item -> new ItemResponseDto(
                        item.getId(),
                        item.getTitle()
                ))
                .orElseThrow(() -> new EntityNotFoundException("item not found"));
    }

    public List<ItemResponseDto> findAll() {
        return itemRepository.findAll().stream().map(
                item -> new ItemResponseDto(
                        item.getId(),
                        item.getTitle()
                )
        ).toList();
    }

    public ListItemsResponseDto getListWithAllItems() {
        List<ItemResponseDto> items = itemRepository.findAll().stream().map(
                item -> new ItemResponseDto(
                        item.getId(),
                        item.getTitle()
                )
        ).toList();

        return new ListItemsResponseDto(
                items,
                items.size()
        );
    }

    public void createAndSave(Integer count, ItemRequestDto itemRequestDto) {
        if (count == null || count <= 0) {
            throw new IllegalArgumentException("Count must be grater than 0");
        }

        for (int i = 0; i < count; i++) {
            Item item = new Item();
            item.setTitle(itemRequestDto.title());
            itemRepository.save(item);
        }
    }
}
