package org.connector.e2etestservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UIController {

    @Value("${default.edc.hostname}")
    private String preconfiguredConnectorUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndexPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("connectorUrl",preconfiguredConnectorUrl);
        return model;
    }
}
