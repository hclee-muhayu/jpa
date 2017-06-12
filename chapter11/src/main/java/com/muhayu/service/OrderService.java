package com.muhayu.service;

import com.muhayu.domain.Delivery;
import com.muhayu.domain.Member;
import com.muhayu.domain.Order;
import com.muhayu.domain.OrderItem;
import com.muhayu.domain.item.Item;
import com.muhayu.repository.MemberRepository;
import com.muhayu.repository.OrderRepository;
import com.muhayu.domain.OrderSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemService itemService;

    public Long order(Long memberId, Long itemId, int count) {
        final Member member = memberRepository.findOne(memberId);
        final Item item = itemService.findOne(itemId);

        final Delivery delivery = new Delivery(member.getAddress());
        final OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        final Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        final Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
