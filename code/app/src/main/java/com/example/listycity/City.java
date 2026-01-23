package com.example.listycity;

import java.io.Serializable;

public class City implements Serializable {
    String name;
    String province;

    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }
}

