package com.perone.entity.shared;

import org.mongodb.morphia.annotations.ConstructorArgs;

import java.util.ArrayList;
import java.util.List;


public enum Role {

    private int level;

    private List<String> roles = new ArrayList<>();


}
