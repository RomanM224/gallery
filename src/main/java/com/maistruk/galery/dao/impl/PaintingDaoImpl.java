package com.maistruk.galery.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.maistruk.galery.dao.PaintingDao;
import com.maistruk.galery.dao.impl.rowMapper.PaintingRowMapper;
import com.maistruk.galery.model.Painter;
import com.maistruk.galery.model.Painting;
import com.maistruk.galery.model.Style;

@Repository
public class PaintingDaoImpl implements PaintingDao {

    private static final String ADD_PAINTING = "INSERT INTO paintings (id, year, name, style, image) VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String ADD_PAINTING_TO_PAINTER = "INSERT INTO painters_paintings (painter_id, painting_id) VALUES (?, ?)";
    private static final String UPDATE_FULL_PAINTING_IFNO = "UPDATE paintings SET year = ?, name = ?, style = ?, image = ? WHERE id = ?";
    private static final String UPDATE_PAINTING_TO_PAINTER = "UPDATE painters_paintings SET painter_id = ? WHERE painting_id = ?";
    private static final String UPDATE_PAINTING_IFNO = "UPDATE paintings SET year = ?, name = ?, style = ? WHERE id = ?";
    private static final String GET_ALL_PAINTINGS = "SELECT * FROM paintings";
    private static final String GET_PAINTING_BY_NAME = "SELECT * FROM paintings WHERE name = ?";
    private static final String GET_PAINTING_RELATED_BY_PAINTER = "SELECT paintings.id, paintings.year, paintings.name, paintings.style, paintings.image "
            + "FROM painters_paintings INNER JOIN paintings ON painters_paintings.painting_id = paintings.id WHERE painters_paintings.painter_id = ?";
    private static final String GET_PAINTINGS_BY_STYLE = "SELECT * FROM paintings WHERE style = ?";
    private static final String DELETE_PAINTING_RELATED_TO_PAINTER = "DELETE FROM painters_paintings WHERE painting_id = ?";
    private static final String DELETE_PAINTING = "DELETE FROM paintings WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PaintingRowMapper paintingRowMapper;

    @Override
    public void create(Painting painting, Integer painterId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(ADD_PAINTING, new String[] { "id" });
            statement.setInt(1, painting.getYear());
            statement.setString(2, painting.getName());
            statement.setString(3, painting.getStyle().toString());
            statement.setBytes(4, painting.getImage());
            return statement;
        }, keyHolder);
        painting.setId(keyHolder.getKey().intValue());
        jdbcTemplate.update(ADD_PAINTING_TO_PAINTER, painterId, painting.getId());
    }

    @Override
    public void updateFullPaintingInfo(Painting painting, Integer painterId) {
        jdbcTemplate.update(UPDATE_FULL_PAINTING_IFNO, painting.getYear(), painting.getName(), painting.getStyle().toString(),
                painting.getImage(), painting.getId());
        jdbcTemplate.update(UPDATE_PAINTING_TO_PAINTER, painterId, painting.getId());
    }

    @Override
    public void updatePaintingInfo(Painting painting) {
        jdbcTemplate.update(UPDATE_PAINTING_IFNO, painting.getYear(), painting.getName(), painting.getStyle().toString(),
                painting.getId());
    }

    @Override
    public List<Painting> getAll() {
        return jdbcTemplate.query(GET_ALL_PAINTINGS, paintingRowMapper);
    }

    @Override
    public List<Painting> getByPainter(Painter painter) {
        return jdbcTemplate.query(GET_PAINTING_RELATED_BY_PAINTER, paintingRowMapper, painter.getId());
    }

    @Override
    public Painting getByName(String name) {
        List<Painting> paintings = jdbcTemplate.query(GET_PAINTING_BY_NAME, paintingRowMapper, name);
        if (paintings.isEmpty()) {
            return null;
        }
        return paintings.get(0);
    }
    
    @Override
    public List<Painting> getByStyle(Style style) {
        return jdbcTemplate.query(GET_PAINTINGS_BY_STYLE, paintingRowMapper, style.toString());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_PAINTING_RELATED_TO_PAINTER, id);
        jdbcTemplate.update(DELETE_PAINTING, id);
    }

    

}
