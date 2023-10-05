package com.example.gameguesstheanimal2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "binary_tree")
public class BinaryTreeStructure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parent")
    private Long id;
    @Column(name = "id_left")
    private Long idLeft;
    @Column(name = "id_right")
    private Long idRight;

    public BinaryTreeStructure(Long id, Long idLeft, Long idRight) {
        this.id = id;
        this.idLeft = idLeft;
        this.idRight = idRight;
    }

    public BinaryTreeStructure() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getIdLeft() {
        return idLeft;
    }

    public void setIdLeft(Long idLeft) {
        this.idLeft = idLeft;
    }

    public Long getIdRight() {
        return idRight;
    }

    public void setIdRight(Long idRight) {
        this.idRight = idRight;
    }
}
