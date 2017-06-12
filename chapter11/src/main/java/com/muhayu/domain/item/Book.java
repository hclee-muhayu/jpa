package com.muhayu.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by hclee on 2017-06-12.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("B")
@Data
public class Book extends Item {
    private String author;
    private String isbn;
}
