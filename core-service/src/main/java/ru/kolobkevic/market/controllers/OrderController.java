package ru.kolobkevic.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kolobkevic.market.converters.OrderConverter;
import ru.kolobkevic.market.dtos.OrderDetailsDto;
import ru.kolobkevic.market.dtos.OrderDto;
import ru.kolobkevic.market.entities.User;
import ru.kolobkevic.market.exceptions.ResourceNotFoundException;
import ru.kolobkevic.market.services.OrderService;
import ru.kolobkevic.market.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("http://localhost:3188")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal, @RequestBody OrderDetailsDto orderDetailsDto){
        User user = userService.findByUsername(principal.getName()).orElseThrow(
                ()->new ResourceNotFoundException("Пользователь не найден"));
        orderService.createOrder(user, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getCurrenUserOrders(Principal principal){
        return orderService.findOrdersByUsername(principal.getName()).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}