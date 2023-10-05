package com.example.gameguesstheanimal2.mapper;

import com.example.gameguesstheanimal2.entity.BinaryTreeStructure;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BinaryTreeStructureMapper implements RowMapper<BinaryTreeStructure> {

    @Override
    public BinaryTreeStructure mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BinaryTreeStructure(
                rs.getLong("id_parent"),
                rs.getLong("id_left"),
                rs.getLong("id_right")
        );
    }
}
