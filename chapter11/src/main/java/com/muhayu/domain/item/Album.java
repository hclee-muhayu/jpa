package com.muhayu.domain.item;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by hyecheon on 2017. 6. 12..
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;
}
