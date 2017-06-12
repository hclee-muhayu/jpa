package com.muhayu.domain;

import com.muhayu.domain.item.Item;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by hclee on 2017-06-12.
 */
@Data
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    private int orderPrice;
    private int count;
}
