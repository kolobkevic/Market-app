package ru.kolobkevic.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDto {
    private String address;
    private String phone;
}
