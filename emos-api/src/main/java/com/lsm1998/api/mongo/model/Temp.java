package com.lsm1998.api.mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Temp implements Serializable
{
    @Id
    private String id;

    private String name;

    private Integer age;
}
