package com.maistruk.galery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruk.galery.dao.PainterDao;
import com.maistruk.galery.exception.PainterException;
import com.maistruk.galery.exception.PaintingException;
import com.maistruk.galery.mapper.PainterMapper;
import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.dto.PainterDto;

@Service
public class PainterService {

    @Autowired
    private PainterDao painterDao;

    @Autowired
    private PaintingService paintingService;
    
    @Autowired
    private PainterMapper painterMapper;

    public void create(PainterDto painterDto) throws PainterException {
        if (painterDto.getFirstName() == null || painterDto.getFirstName().isEmpty()) {
            throw new PainterException("First Name is invalid");
        }
        if (painterDto.getLastName() == null || painterDto.getLastName().isEmpty()) {
            throw new PainterException("Last Name is invalid");
        }
        if (painterDto.getPainterInfo() == null || painterDto.getPainterInfo().isEmpty()) {
            throw new PainterException("Info is invalid");
        }
        Painter painter = painterMapper.mapPainterDtoToPainter(painterDto);
        painterDao.create(painter);
    }

    public void update(PainterDto painterDto) throws PainterException {
        if(painterDto.getId() == null) {
            throw new PainterException("Painter not exist");
        }
        if (painterDto.getFirstName() == null || painterDto.getFirstName().isEmpty()) {
            throw new PainterException("First Name is invalid");
        }
        if (painterDto.getLastName() == null || painterDto.getLastName().isEmpty()) {
            throw new PainterException("Last Name is invalid");
        }
        if (painterDto.getPainterInfo() == null || painterDto.getPainterInfo().isEmpty()) {
            throw new PainterException("Info is invalid");
        }
        Painter painter = painterMapper.mapPainterDtoToPainter(painterDto);
        painterDao.update(painter);
    }

    public List<Painter> getAll() {
        return painterDao.getAll();
    }

    public Painter getById(Integer id) throws PainterException {
        if(id == null) {
            throw new PainterException("Painter not exist");
        }
        return painterDao.getById(id);
    }

    public Painter getByPaintingId(Integer paintingId) {
        return painterDao.getByPaintingId(paintingId);
    }

    public void delete(Integer id) throws PaintingException, PainterException {
        if(id == null) {
            throw new PainterException("Painter not exist");
        }
        List<Painting> paintings = paintingService.getByPainterId(id);
        painterDao.delete(id);
        for (Painting painting : paintings) {
            paintingService.delete(painting.getId());

        }
    }

}
