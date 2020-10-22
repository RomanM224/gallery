package com.maistruk.galery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.galery.model.Painter;
import com.maistruk.galery.service.PainterService;

@Controller
@RequestMapping("/painter")
public class PainterController {

    @Autowired
    private PainterService painterService;
    
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("galery/painter/create");
    }
    
    @PostMapping("/create")
    public ModelAndView create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("painterInfo") String info) {
        Painter painter = new Painter(1, firstName, lastName, info);
        painterService.create(painter);
        ModelAndView modelAndView = new ModelAndView("galery/info");
        return modelAndView.addObject("info", "Painter created");
    }
    
    @GetMapping("/update")
    public ModelAndView update() {
        List<Painter> painters = painterService.getAll();
        ModelAndView modelAndView = new ModelAndView("galery/painter/update");
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }
    
    @PostMapping("/update")
    public ModelAndView update(@RequestParam("id") Integer id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("painterInfo") String info) {
        Painter painter = new Painter(id, firstName, lastName, info);
        painterService.update(painter);
        ModelAndView modelAndView = new ModelAndView("galery/info");
        return modelAndView.addObject("info", "Painter created");
    }
    
    @GetMapping("/showAll")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/showAll");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
      //  modelAndView.addObject("students", Collections.emptyList());
        return modelAndView;
    }
    
    @GetMapping("/getById")
    public ModelAndView getById() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/showById");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }
    
    @PostMapping("/getById")
    public ModelAndView getById(@RequestParam("id") Integer id) {
        List<Painter> painters = new ArrayList<>();
        Painter painter = painterService.getById(id);
        painters.add(painter);
        ModelAndView modelAndView = new ModelAndView("galery/painter/showAll");
        return modelAndView.addObject("painters", painters);
    }
    
    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView modelAndView = new ModelAndView("galery/painter/delete");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }
    
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        painterService.delete(id);
        ModelAndView modelAndView = new ModelAndView("galery/info");
        return modelAndView.addObject("info", "Painter deleted");    }
}
