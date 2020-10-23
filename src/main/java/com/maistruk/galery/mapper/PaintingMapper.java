package com.maistruk.galery.mapper;

import org.springframework.stereotype.Component;

import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.dto.PaintingDto;

@Component
public class PaintingMapper {
    
    public Painting mapPaintingDtoToPainting(PaintingDto paintingDto) {
        Painting painting = new Painting();
        painting.setId(paintingDto.getId());
        painting.setName(paintingDto.getName());
        painting.setStyle(paintingDto.getStyle());
        painting.setYear(paintingDto.getYear());
        painting.setImage(paintingDto.getImage());
        return painting;
    }

}
