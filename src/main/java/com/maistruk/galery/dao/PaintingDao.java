package com.maistruk.galery.dao;

import java.util.List;

import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.Style;

public interface PaintingDao {
    
    void create(Painting painting, Integer painterId);
    
    void updateFullPaintingInfo(Painting painting, Integer painterId);
    
    void updatePaintingInfo(Painting painting);
    
    List<Painting> getAll();
    
    List<Painting> getByPainter(Painter painter);
    
    Painting getByName(String name);
    
    List<Painting> getByStyle(Style style);
    
    void delete(Integer id);

}
