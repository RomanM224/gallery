package com.maistruk.galery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruk.galery.dao.PainterDao;
import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;

@Service
public class PainterService {
    
    @Autowired
    private PainterDao painterDao;
    
    @Autowired
    private PaintingService paintingService;
    
    public void create(Painter painter) {
        painterDao.create(painter);
    }

    public void update(Painter painter) {
        painterDao.update(painter);
    }

    public List<Painter> getAll() {
        return painterDao.getAll();
    }

    public Painter getById(Integer id) {
        return painterDao.getById(id);
    }
    
    public Painter getByPaintingId(Integer paintingId) {
        return painterDao.getByPaintingId(paintingId);
    }

    public void delete(Integer id) {
        Painter painter = getById(id);
        List<Painting> paintings = paintingService.getByPainter(painter);
        painterDao.delete(id);
        for(Painting painting : paintings) {
            paintingService.delete(painting.getId());
        }
    }

}
