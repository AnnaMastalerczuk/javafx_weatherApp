package com.amastalerczuk.controller;

import com.amastalerczuk.view.ViewFactory;

public abstract class BaseController {
    private String fxmlName;
    protected ViewFactory viewFactory = new ViewFactory();
    public BaseController(){}
    public BaseController(String fxmlName) {
        this.fxmlName = fxmlName;
    }
    public String getFxmlName() {
        return fxmlName;
    }
}
