package org.connector.e2etestservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UIController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage() {
        return "index";
    }
}
