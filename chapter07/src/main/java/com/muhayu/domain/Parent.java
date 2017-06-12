package com.muhayu.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by hclee on 2017-06-12.
 */
@Entity
@Data
public class Parent {
    @EmbeddedId
    private ParentId id;
    private String name;
}
