package org.example.dto.integrations;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String accountNumber,
        BigDecimal balance,
        ProductType productType
) {
}
