package com.example.gameguesstheanimal2.entity;

import jakarta.persistence.*;

@Entity
@Table(name="node")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_node")
    private Long id;
    @Column(name = "question")
    private String question;
    @Column(name = "animal")
    private String animal;
    @ManyToOne
    @JoinColumn(name="left_node")
    private Node leftNode;
    @ManyToOne
    @JoinColumn(name="right_node")
    private Node rightNode;
    @Column(name = "isLeaf")
    private boolean isLeaf;

    public Node(Long id, String question, String animal, Node leftNode, Node rightNode, boolean isLeaf) {
        this.id = id;
        this.question = question;
        this.animal = animal;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.isLeaf = isLeaf;
    }


    public Node() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
