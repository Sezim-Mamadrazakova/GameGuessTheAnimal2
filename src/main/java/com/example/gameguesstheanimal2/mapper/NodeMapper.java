package com.example.gameguesstheanimal2.mapper;

import com.example.gameguesstheanimal2.entity.Node;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NodeMapper implements RowMapper<Node> {


    @Override
    public Node mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Node(
                rs.getLong("id_node"),
                rs.getString("question"),
                rs.getString("animal"),
                (Node) rs.getObject("left_node"),
                (Node) rs.getObject("right_node"),
                rs.getBoolean("is_leaf")
        );
    }
}
