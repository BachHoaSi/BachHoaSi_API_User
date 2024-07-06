package com.swd391.bachhoasi_user.controller;

import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.dto.request.FeedbackRequest;
import com.swd391.bachhoasi_user.model.dto.request.OrderRequest;
import com.swd391.bachhoasi_user.model.dto.response.ResponseObject;
import com.swd391.bachhoasi_user.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static com.swd391.bachhoasi_user.util.BaseUtils.getResponseObjectResponseEntity;


@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Get orders", description = "Get orders by store id and order status: PENDING, ACCEPTED, PICKED_UP, IN_TRANSIT, DELIVERED, CANCELLED")
    @GetMapping
    public ResponseEntity<ResponseObject> getOrders(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pagination,
            @RequestParam(required = true, name = "storeId") BigDecimal storeId,
            @RequestParam(required = true, name = "orderStatus") String orderStatus) {
        var data = orderService.getAllOrders(storeId, OrderStatus.valueOf(orderStatus), pagination);
        var responseObject = ResponseObject.builder()
                .code("ORDER_GET_SUCCESS")
                .message("Get order successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }
    @Operation(summary = "Get order details", description = "Get order details by order id")
    @GetMapping("/details")
    public ResponseEntity<ResponseObject> getOrderDetails(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pagination,
            @RequestParam(required = true, name = "orderId") BigDecimal orderId
    ) {
        var data = orderService.getOrderDetails(orderId, pagination);
        var responseObject = ResponseObject.builder()
                .code("ORDER_DETAILS_GET_SUCCESS")
                .message("Get order successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @Operation(summary = "Add order", description = "Add order by store id, cart id and payment method: COD, BANKING; Date time format: yyyy-MM-ddTHH:mm:ss")
    @PostMapping
    public ResponseEntity<ResponseObject> addOrder(@RequestBody @Valid OrderRequest orderRequest, BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = orderService.placeOrder(orderRequest);
        var responseObject = ResponseObject.builder()
                .code("ORDER_ADD_SUCCESS")
                .message("Add order successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }

    @PostMapping("/feedback")
    public ResponseEntity<ResponseObject> addFeedback(@RequestBody @Valid FeedbackRequest feedbackRequest, BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = orderService.addFeedback(feedbackRequest);
        var responseObject = ResponseObject.builder()
                .code("ORDER_FEEDBACK_SUCCESS")
                .message("Add feedback successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }
    @Operation(summary = "re-order", description = "Reorder by cart id and store id")
    @PostMapping("/re-order")
    public ResponseEntity<ResponseObject> reOrder(@RequestBody @Valid OrderRequest orderRequest, BindingResult bindingResult) {
        var response = getResponseObjectResponseEntity(bindingResult);
        if (response != null){ return response;}
        var data = orderService.placeOrder(orderRequest);
        var responseObject = ResponseObject.builder()
                .code("ORDER_REORDER_SUCCESS")
                .message("Reorder successfully")
                .status(HttpStatus.OK)
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok().body(responseObject);
    }


}