package com.example.rvasm;

public class Item {
    private String Name;
    private String Des;

    public Item(String name, String des) {
        Name = name;
        Des = des;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }
}
