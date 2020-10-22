package com.maistruk.galery.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.Style;
import com.maistruk.galery.service.PainterService;
import com.maistruk.galery.service.PaintingService;

@Controller
@RequestMapping("/painting")
public class PaintingController {
    
    @Autowired
    private PaintingService paintingService;
    
    @Autowired
    private PainterService painterService;
    
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/create");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }

    
    @PostMapping("/create")
    public ModelAndView create(@RequestParam("painterId") Integer painterId, @RequestParam CommonsMultipartFile file, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/info");
        Painting painting = new Painting();
        painting.setYear(Integer.parseInt(request.getParameter("year")));
        painting.setName(request.getParameter("name"));
        painting.setStyle(Style.valueOf(request.getParameter("style")));
        byte[] image = file.getBytes();
        painting.setImage(image);
        paintingService.create(painting, painterId);   
        return modelAndView.addObject("info", "Painter created");
    }
    
    @GetMapping("/updateFullPaintingInfo")
    public ModelAndView updateFullPaintingInfo() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/updateFullPaintingInfo");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }
    
    @PostMapping("/updateFullPaintingInfo")
    public ModelAndView updateFullPaintingInfo(@RequestParam CommonsMultipartFile file, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/info");
        Integer painterId = Integer.valueOf(request.getParameter("painterId"));
        Integer paintingId = Integer.valueOf(request.getParameter("paintingId"));
        Painting painting = new Painting();
        painting.setId(paintingId);
        painting.setYear(Integer.parseInt(request.getParameter("year")));
        painting.setName(request.getParameter("name"));
        painting.setStyle(Style.valueOf(request.getParameter("style")));
        byte[] image = file.getBytes();
        painting.setImage(image);
        paintingService.updateFullPaintingInfo(painting, painterId);
        return modelAndView.addObject("info", "Painter created");
    }
    
    @GetMapping("/updatePaintingInfo")
    public ModelAndView updatePaintingInfo() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/updatePaintingInfo");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }
    
    @PostMapping("/updatePaintingInfo")
    public ModelAndView updatePaintingInfo(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("galery/info");
        Integer paintingId = Integer.valueOf(request.getParameter("paintingId"));
        Painting painting = new Painting();
        painting.setId(paintingId);
        painting.setYear(Integer.parseInt(request.getParameter("year")));
        painting.setName(request.getParameter("name"));
        painting.setStyle(Style.valueOf(request.getParameter("style")));
        paintingService.updatePaintingInfo(painting);
        return modelAndView.addObject("info", "Painter created");
    }
    
    @GetMapping("/showAll")
    public  ModelAndView showAll()  {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAll();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/showByName")
    public ModelAndView showByName() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainting");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @PostMapping("/showByName")
    public ModelAndView showByName(@RequestParam("paintingName") String name) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        Painting painting = paintingService.getByName(name);
        List<Painting> paintings = new ArrayList<>();
        paintings.add(painting);
        Map<Integer, String> painterNames = new HashMap<>();
        Painter painter = painterService.getByPaintingId(painting.getId());
        painterNames.put(painting.getId(), painter.getFullName());
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/showByPainter")
    public ModelAndView showByPainter() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByPainter");
        List<Painter> painters = painterService.getAll();
        modelAndView.addObject("painters", painters);
        return modelAndView;
    }
    
    @PostMapping("/showByPainter")
    public ModelAndView showByPainter(@RequestParam("painterId") Integer painterId) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        Painter painter = painterService.getById(painterId);
        List<Painting> paintings = paintingService.getByPainter(painter);
        Map<Integer, String> painterNames = new HashMap<>();
        for(Painting painting : paintings) {
            painterNames.put(painting.getId(), painter.getFullName());
        }
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/showByStyle")
    public ModelAndView showByStyle() {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showByStyle");
        List<Style> styles = Arrays.asList(Style.values());
        modelAndView.addObject("styles", styles);
        return modelAndView;
    }
    
    @PostMapping("/showByStyle")
    public ModelAndView showByStyle(@RequestParam("style") String myStyle) {
        ModelAndView modelAndView = new ModelAndView("galery/painting/showAll");
        Style style = Style.valueOf(myStyle);
        List<Painting> paintings = paintingService.getByStyle(style);
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/sortByNameAsc")
    public ModelAndView sortByNameAsc() {
        ModelAndView modelAndView =  new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByNameAsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/sortByNameDsc")
    public ModelAndView sortByNameDsc() {
        ModelAndView modelAndView =  new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByNameDsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/sortByYearAsc")
    public ModelAndView sortByYearAsc() {
        ModelAndView modelAndView =  new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByYearAsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/sortByYearDsc")
    public ModelAndView sortByYearDsc() {
        ModelAndView modelAndView =  new ModelAndView("galery/painting/showAll");
        List<Painting> paintings = paintingService.getAllSortedByYearDsc();
        Map<Integer, String> painterNames = getPainterNames(paintings);
        modelAndView.addObject("painterNames", painterNames);
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @GetMapping("/delete")
    public ModelAndView delete() {
        ModelAndView modelAndView =  new ModelAndView("galery/painting/delete");
        List<Painting> paintings = paintingService.getAll();
        modelAndView.addObject("paintings", paintings);
        return modelAndView;
    }
    
    @PostMapping("/delete")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        ModelAndView modelAndView =  new ModelAndView("galery/info");
        paintingService.delete(id);
        return modelAndView.addObject("info", "Painter deleted");
    }
    
    public Map<Integer, String> getPainterNames(List<Painting> paintings){
        Map<Integer, String> painterNames = new HashMap<>();
        for(Painting painting : paintings) {
            Painter painter = painterService.getByPaintingId(painting.getId());
            painterNames.put(painting.getId(), painter.getFullName());
        }
        return painterNames;
    }
}
