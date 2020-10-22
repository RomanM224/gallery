package com.maistruk.galery.service;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruk.galery.dao.PaintingDao;
import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.Style;

@Service
public class PaintingService {

    @Autowired
    private PaintingDao paintingDao;

    public void create(Painting painting, Integer painterId) {
        paintingDao.create(painting, painterId);
    }

    public void updateFullPaintingInfo(Painting painting, Integer painterId) {
        paintingDao.updateFullPaintingInfo(painting, painterId);
    }
    
    public void updatePaintingInfo(Painting painting) {
        paintingDao.updatePaintingInfo(painting);
    }

    public List<Painting> getAll()  {
        List<Painting> paintings = paintingDao.getAll();
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }

    public Painting getByName(String name) {
        Painting painting = paintingDao.getByName(name);
        byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
        try {
            painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return painting;
    }
    
    public List<Painting> getByPainter(Painter painter){
        List<Painting> paintings = paintingDao.getByPainter(painter);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }
    
    public List<Painting> getByStyle(Style style){
        List<Painting> paintings = paintingDao.getByStyle(style);
        for(Painting painting : paintings) {
            byte[] imageBase64Encoded = Base64.encodeBase64(painting.getImage());
            try {
                painting.setImageBase64Encoded(new String(imageBase64Encoded, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return paintings;
    }
    

    public void delete(Integer id) {
        paintingDao.delete(id);
    }

    public List<Painting> getAllSortedByNameAsc() {
        List<Painting> paintings = getAll();
        Collections.sort(paintings, Comparator.comparing(Painting::getName));
        return paintings;
    }
    
    public List<Painting> getAllSortedByNameDsc() {
        List<Painting> paintings = getAll();
        paintings.sort((Painting one, Painting two) ->two.getName().compareTo(one.getName()));
        return paintings;
    }

    public List<Painting> getAllSortedByYearAsc() {
        List<Painting> paintings = getAll();
        Collections.sort(paintings, Comparator.comparing(Painting::getYear));
        return paintings;
    }
    
    public List<Painting> getAllSortedByYearDsc() {
        List<Painting> paintings = getAll();
        paintings.sort((Painting one, Painting two) ->two.getYear().compareTo(one.getYear()));
        return paintings;
    }

}
