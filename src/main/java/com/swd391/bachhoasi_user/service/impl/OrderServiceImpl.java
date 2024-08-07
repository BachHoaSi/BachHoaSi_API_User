package com.swd391.bachhoasi_user.service.impl;

import com.swd391.bachhoasi_user.model.constant.CartStatus;
import com.swd391.bachhoasi_user.model.constant.OrderStatus;
import com.swd391.bachhoasi_user.model.dto.request.CallBackRequest;
import com.swd391.bachhoasi_user.model.dto.request.FeedbackRequest;
import com.swd391.bachhoasi_user.model.dto.request.OrderRequest;
import com.swd391.bachhoasi_user.model.dto.response.*;
import com.swd391.bachhoasi_user.model.entity.*;
import com.swd391.bachhoasi_user.model.exception.ActionFailedException;
import com.swd391.bachhoasi_user.model.exception.AllNullException;
import com.swd391.bachhoasi_user.model.exception.NotFoundException;
import com.swd391.bachhoasi_user.model.exception.ValidationFailedException;
import com.swd391.bachhoasi_user.repository.*;
import com.swd391.bachhoasi_user.service.MenuService;
import com.swd391.bachhoasi_user.service.OrderService;
import com.swd391.bachhoasi_user.util.AuthUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final CartProductMenuRepository cartProductMenuRepository;
    private final CartRepository cartRepository;
    private final OrderProductMenuRepository orderProductMenuRepository;
    private final OrderContactRepository orderContactRepository;
    private final MenuService menuService;
    private final AuthUtils authUtils;

    @Override
    public OrderResponse placeOrder(OrderRequest order) {


        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(order.getStoreId())){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));

        Cart currentCart = cartRepository.findById(order.getCartId()).orElseThrow(() -> new NotFoundException("Cart is not found!"));

        List<CartProductMenu> listProductInCart = cartProductMenuRepository.findByCartIdAndCartStoreId(order.getCartId(), order.getStoreId());
        if(listProductInCart.isEmpty()){
            throw new NotFoundException("Cart is empty");
        }
        // init new order and order contact
        Order newOrder = Order.builder()
                .store(store)
                .orderStatus(OrderStatus.PENDING)
                .payingMethod(order.getPayingMethod())
                .orderDate(order.getDeliveryTime())
                .createdDate(new Date(System.currentTimeMillis()))
                .updatedDate(new Date(System.currentTimeMillis()))
                .build();
        OrderContact orderContact = OrderContact.builder()
                .buildingNumber(store.getLocation())
                .phoneNumber(store.getPhoneNumber())
                .customerName(store.getName())
                .createdDate(new Date(System.currentTimeMillis()))
                .updatedDate(new Date(System.currentTimeMillis()))
                .build();


        // init order products and set price
        List<OrderProductMenu> orderProducts = new ArrayList<>();
        int totalPrice = 0;
        int subTotal = 0;
        for(CartProductMenu cartProductMenu : listProductInCart){
            OrderProductMenu orderProductMenu = OrderProductMenu.builder()
                    .order(newOrder)
                    .product(cartProductMenu.getProduct())
                    .quantity(cartProductMenu.getQuantity())
                    .build();
            orderProducts.add(orderProductMenu);
            subTotal += cartProductMenu.getProduct().getComposeId().getProduct().getBasePrice().intValue() * cartProductMenu.getQuantity();
            totalPrice += cartProductMenu.getProduct().getPrice().intValue() * cartProductMenu.getQuantity();
        }
        newOrder.setGrandTotal(BigDecimal.valueOf(totalPrice));
        newOrder.setSubTotal(BigDecimal.valueOf(subTotal));

        int point = (int) (totalPrice * 0.1); // 10% of total price
        newOrder.setPoint(point);

        currentCart.setCartStatus(CartStatus.ORDERED);

        try{
            orderContact = orderContactRepository.save(orderContact);
            newOrder.setOrderContact(orderContact);
            newOrder = orderRepository.save(newOrder);
            orderProductMenuRepository.saveAll(orderProducts);
            cartRepository.save(currentCart);
        }catch (Exception e){
            throw new NotFoundException("Cannot place order");
        }
        return convertOrderToOrderResponse(newOrder);
    }

    @Override
    public PaginationResponse<OrderProductMenuResponse> getOrderDetails(BigDecimal orderId, Pageable pageable) {
        Page<OrderProductMenu> products = orderProductMenuRepository.findByOrderId(orderId, pageable);
        Page<OrderProductMenuResponse> productMenuResponses = products.map(this::convertOrderProductMenuToProductMenuResponse);
        return new PaginationResponse<>(productMenuResponses);
    }

    @Override
    public PaginationResponse<OrderResponse> getAllOrders(BigDecimal storeId,OrderStatus orderStatus, Pageable pageable) {

        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(storeId)){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));
        Page<Order> orders = orderRepository.findByOrderContactPhoneNumberAndOrderStatus(store.getPhoneNumber(), orderStatus, pageable);
        if(orders.isEmpty()){
            throw new NotFoundException("No order found");
        }
        Page<OrderResponse> orderResponses = orders.map(this::convertOrderToOrderResponse);
        return new PaginationResponse<>(orderResponses);
    }

    @Override
    public OrderResponse addFeedback(FeedbackRequest feedbackRequest) {

        if(feedbackRequest.getDeliveryFeedback().isEmpty()&&feedbackRequest.getOrderFeedback().isEmpty()){
            throw new AllNullException("Feedback is empty");
        }
        var currentUser = authUtils.getUserFromAuthentication();
        if(!currentUser.getId().equals(feedbackRequest.getStoreId())){
            throw new ValidationFailedException("You are not authorized to access this store");
        }
        Store store = storeRepository.findById(currentUser.getId()).orElseThrow(() -> new NotFoundException("Store is not found!"));
        Order order = orderRepository.findByIdAndOrderStatusAndOrderContactPhoneNumber(feedbackRequest.getOrderId(),OrderStatus.DELIVERED, store.getPhoneNumber())
                .orElseThrow(()-> new NotFoundException("Order not found or the order is not delivered!"));
        order.setOrderFeedback(feedbackRequest.getOrderFeedback());
        order.setDeliveryFeedback(feedbackRequest.getDeliveryFeedback());
        try{
            order = orderRepository.save(order);
        }catch (Exception e){
            throw new ActionFailedException("Cannot add feedback!");
        }
        return convertOrderToOrderResponse(order);
    }

    public OrderResponse convertOrderToOrderResponse(Order order){
        return OrderResponse.builder()
                .orderId(order.getId())
                .storeName(order.getOrderContact().getCustomerName())
                .orderStatus(order.getOrderStatus())
                .deliveryTime(order.getOrderDate())
                .totalPrice(order.getGrandTotal())
                .storeAddress(order.getOrderContact().getBuildingNumber())
                .orderFeedback(order.getOrderFeedback())
                .deliveryFeedback(order.getDeliveryFeedback())
                .payingMethod(order.getPayingMethod())
                .point(order.getPoint())
                .createdDate(order.getCreatedDate())
                .build();
    }
    private OrderProductMenuResponse convertOrderProductMenuToProductMenuResponse(OrderProductMenu orderProductMenu) {

        ProductMenuResponse productMenuResponse = menuService.convertToProductMenuResponse(orderProductMenu.getProduct());
        OrderProductMenuResponse orderProductMenuResponse = new OrderProductMenuResponse();
        orderProductMenuResponse.setId(orderProductMenu.getId());
        orderProductMenuResponse.setProduct(productMenuResponse);
        orderProductMenuResponse.setQuantity(orderProductMenu.getQuantity());
        return orderProductMenuResponse;

    }


}