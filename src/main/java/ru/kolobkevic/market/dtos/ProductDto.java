package ru.kolobkevic.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.kolobkevic.market.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotNull(message = "Товар должен иметь название")
    @Min(value = 1, message = "Цена не может быть меньше 1 рубля")
    private Double price;
    @NotNull
    @Length(min = 3, max = 100, message = "Название товара должно быть от 3 до 100 символов")
    private String title;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.price = product.getPrice();
        this.title = product.getTitle();
    }
}