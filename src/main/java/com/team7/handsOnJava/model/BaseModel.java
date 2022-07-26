package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
public abstract class BaseModel {
    private String id;
    public BaseModel(String id) {this.id = id;}
}
