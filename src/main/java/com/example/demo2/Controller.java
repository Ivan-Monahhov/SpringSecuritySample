package com.example.demo2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(method = RequestMethod.GET, value = "/authentication/login")
    public String aName() {
        return "login.html";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/authentication/fail")
    public String bName() {
        return "fail.html";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/yay")
    public String cName() {
        return "yay.html";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/yay")
    public String c2Name() {
        return "yay.html";
    }
}
