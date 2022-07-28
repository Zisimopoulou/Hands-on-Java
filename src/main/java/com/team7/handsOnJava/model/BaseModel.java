package com.team7.handsOnJava.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class BaseModel {
    private String id;
    public BaseModel(String id) {this.id = id;}

    public String getModelId() { return this.id; }

    public void setModelId (String modelId) {this.id = modelId;}
}
