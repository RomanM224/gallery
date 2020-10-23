package com.maistruk.galery.mapper;

import org.springframework.stereotype.Component;

import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.dto.PainterDto;

@Component
public class PainterMapper {
    
    public Painter mapPainterDtoToPainter(PainterDto painterDto) {
        Painter painter = new Painter();
        painter.setId(painterDto.getId());
        painter.setFirstName(painterDto.getFirstName());
        painter.setLastName(painterDto.getLastName());
        painter.setInfo(painterDto.getPainterInfo());
        return painter;
    }

}
