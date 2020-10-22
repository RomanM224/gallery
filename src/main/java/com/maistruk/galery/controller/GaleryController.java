package com.maistruk.galery.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GaleryController {
    
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView home() {
        return new ModelAndView("galery/home");
    }
    
}
