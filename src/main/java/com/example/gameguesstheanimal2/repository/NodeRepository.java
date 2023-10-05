package com.example.gameguesstheanimal2.repository;

import com.example.gameguesstheanimal2.entity.Node;
import com.example.gameguesstheanimal2.mapper.NodeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NodeRepository {
    private final JdbcTemplate jdbcTemplate;


    public NodeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Node> getAll(){
        String query="SELECT id_node, question, animal, left_node, right_node, is_leaf FROM node";
        return jdbcTemplate.query(query, new NodeMapper());
    }
    public void create(String animal){
        String query="INSERT INTO node(question, animal, left_node, right_node, is_leaf)"+
                "VALUES (null, ?, null, null, true)";
        jdbcTemplate.update(query, animal);
    }
    public void update(String question, String animal){
        String query="UPDATE node SET question=?, animal=null, left_node=null, right_node=null, is_leaf=false" +
                " WHERE animal=?";
        jdbcTemplate.update(query, question, animal );
    }
    public List<Node> getNode(Long id){
        String query="SELECT id_node, question, animal, left_node, right_node, is_leaf FROM node WHERE id_node=?";
        return jdbcTemplate.query(query,new NodeMapper(),id);
    }
}
