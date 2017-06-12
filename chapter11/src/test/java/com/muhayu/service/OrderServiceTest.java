package com.muhayu.service;

import com.muhayu.domain.Address;
import com.muhayu.domain.Member;
import com.muhayu.domain.Order;
import com.muhayu.domain.OrderStatus;
import com.muhayu.domain.item.Book;
import com.muhayu.domain.item.Item;
import com.muhayu.exception.NotEnoughStockException;
import com.muhayu.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void order() throws Exception {
        final Member member = createMember();
        final Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;
        final Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        final Order getOrder = orderRepository.findOne(orderId);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품종류 수가 정확해야 한다", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량 이다.", 10000 * 2, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void notEnoughStockException() throws Exception {
        final Member member = createMember();
        final Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 11;
        orderService.order(member.getId(), item.getId(), orderCount);
        fail("재고 수량 부족 예외가 발생해야 한다.");
    }

    @Test
    public void cancel() throws Exception {
        final Member member = createMember();
        final Item item = createBook("시골 JPA", 10000, 10);
        int orderCount = 2;
        final Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        orderService.cancelOrder(orderId);
        final Order getOrder = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL 이다", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
    }

    private Book createBook(final String name, final int price, final int stockQuantity) {
        final Book book = new Book();
        book.setName(name);
        book.setStockQuantity(stockQuantity);
        book.setPrice(price);
        entityManager.persist(book);
        return book;
    }

    private Member createMember() {
        final Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        entityManager.persist(member);
        return member;
    }
}