package com.maistruk.galery.dao.impl.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.Style;

@Component
public class PaintingRowMapper implements RowMapper<Painting> {

    @Override
    public Painting mapRow(ResultSet resultSet, int row) throws SQLException {
        Integer id = resultSet.getInt("id");
        Integer year = resultSet.getInt("year");
        String name = resultSet.getString("name");
        Style style = Style.valueOf(resultSet.getString("style"));
        byte[] file = resultSet.getBytes("image");
        return new Painting(id, year, name, style, file);
    }

}
