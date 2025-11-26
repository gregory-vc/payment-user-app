package org.example.dto.integrations;

import java.util.List;

public record UserDto(Long id, String username, List<ProductDto> products) {
}
