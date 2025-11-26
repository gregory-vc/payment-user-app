package org.example.dto;

import java.util.List;

public record ListItemsResponseDto(List<ItemResponseDto> items, Integer count) {
}
