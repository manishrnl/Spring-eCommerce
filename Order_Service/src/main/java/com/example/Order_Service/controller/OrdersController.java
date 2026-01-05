package com.example.Order_Service.controller;

import com.example.Order_Service.dto.OrderRequestDto;
import com.example.Order_Service.service.OrdersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService orderService;

    @GetMapping
    public ResponseEntity<List<OrderRequestDto>> getAllOrders(HttpServletRequest httpServletRequest) {
        log.info("Fetching all orders via controller");
        List<OrderRequestDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);  // Returns 200 OK with the list of orders
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderRequestDto> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {} via controller", id);
        OrderRequestDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);  // Returns 200 OK with the order
    }

    @PostMapping("/create-order")
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        OrderRequestDto orderRequestDto1 = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(orderRequestDto1);

    /*      Explanation Of how data is transferred from one project via other using OpenFeignClient

    1.  When You hit the URL =" http://localhost:8081/orders/core/create-order " in postman with the desired body as needed from function signature ,it goes to com.example.Order_Service.service.OrdersService.createOrder() function .

    2. Now inside service class ,the line Double totalPrice = inventoryOpenFeignClient.reduceStocks(orderRequestDto); calls the  com.example.Order_Service.client.InventoryOpenFeignClient.reduceStocks(..) along eith body .

    3. Here it gets the links from the Api_Gateway/src/main/resources/application.yaml , it generates link " http://localhost:8080/inventory/products/reduce-stocks " to call the Inventory_Service microService .

    4. with the above link it jumps to the  com.example.Inventory_Service.service .ProductService.reduceStocks(..) and do the operation here ,and returns the Double Value as is needed .

    5. Now the returned Value came to the com.example.Order_Service.service.createOrder(..) and after performing all operations , finally all the products that have been ordered is shown inside response .

     */
    }
}