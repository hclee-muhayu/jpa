package com.muhayu.domain;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by hclee on 2017-06-12.
 */
@Entity
@Data
public class Seller extends BaseEntity {
    private String shopName;
}
