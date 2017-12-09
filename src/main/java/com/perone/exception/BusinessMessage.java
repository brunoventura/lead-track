package com.perone.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BusinessMessage {

    private String key;

    private List<Object> params = new ArrayList<>();

}