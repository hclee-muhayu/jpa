package com.muhayu.domain;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by hclee on 2017-06-12.
 */
@Entity
@DiscriminatorValue("A")
@Data
public class Album extends Item {
    private String artist;
}
