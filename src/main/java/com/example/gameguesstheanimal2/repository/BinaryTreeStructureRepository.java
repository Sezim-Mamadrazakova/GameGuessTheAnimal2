package com.example.gameguesstheanimal2.repository;

import com.example.gameguesstheanimal2.entity.BinaryTreeStructure;
import com.example.gameguesstheanimal2.mapper.BinaryTreeStructureMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BinaryTreeStructureRepository {
    private final JdbcTemplate jdbcTemplate;

    public BinaryTreeStructureRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<BinaryTreeStructure> getAll(){
        String query="SELECT id_parent, id_left, id_right FROM binary_tree";
        return jdbcTemplate.query(query, new BinaryTreeStructureMapper());
    }
    public void create(String newQuestion, String newAnimal, String oldAnimal) {
        String query = "INSERT INTO binary_tree (id_parent, id_left, id_right) " +
                "VALUES (" +
                "(SELECT id_node FROM node WHERE question = ?), " +
                "(SELECT id_node FROM node WHERE animal = ?), " +
                "(SELECT id_node FROM node WHERE animal = ?))";
        jdbcTemplate.update(query, newQuestion, newAnimal, oldAnimal);
    }
    public List<BinaryTreeStructure>getByParentId(Long id){
        String query="SELECT id_parent, id_left, id_right FROM binary_tree WHERE id_parent=?";
        return jdbcTemplate.query(query, new BinaryTreeStructureMapper(), id);
    }
}
