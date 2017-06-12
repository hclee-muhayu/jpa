package com.muhayu.domain;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by hclee on 2017-06-12.
 */
@Embeddable
@Data
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
