package com.muhayu.domain;

import lombok.Data;

/**
 * Created by hyecheon on 2017. 6. 13..
 */
@Data
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
