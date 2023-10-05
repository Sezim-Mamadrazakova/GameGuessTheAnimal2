package com.example.gameguesstheanimal2.service;

import com.example.gameguesstheanimal2.entity.BinaryTreeStructure;
import com.example.gameguesstheanimal2.entity.Node;
import com.example.gameguesstheanimal2.entity.Tree;
import com.example.gameguesstheanimal2.repository.BinaryTreeStructureRepository;
import com.example.gameguesstheanimal2.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class TreeService {
    private final NodeRepository nodeRepository;
    private final BinaryTreeStructureRepository binaryTreeStructureRepository;

    public TreeService(NodeRepository nodeRepository, BinaryTreeStructureRepository binaryTreeStructureRepository) {
        this.nodeRepository = nodeRepository;
        this.binaryTreeStructureRepository = binaryTreeStructureRepository;
    }
    public Tree buildTree(){
        List<Node> nodes=nodeRepository.getAll();
        List<BinaryTreeStructure> nodeChild=binaryTreeStructureRepository.getAll();

        Map<Long, Node> nodeMap = new HashMap<>();
        for (Node node : nodes) {
            nodeMap.put(node.getId(), node);
        }
        for (BinaryTreeStructure treeStructure : nodeChild) {
            Node parent = nodeMap.get(treeStructure.getId());
            Node left = nodeMap.get(treeStructure.getIdLeft());
            Node right = nodeMap.get(treeStructure.getIdRight());

            if (parent != null) {
                parent.setLeftNode(left);
                parent.setRightNode(right);
            }
        }
        Node root = nodes.get(0);
        buildSubTree(root);
        return new Tree(root);

    }
    public void buildSubTree(Node current){
        if(current==null){
            return;
        }

        Node leftNode = current.getLeftNode();
        Node rightNode = current.getRightNode();

        if (leftNode != null) {
            current.setLeftNode(leftNode);
            buildSubTree(current.getLeftNode());
        }
        if (rightNode != null) {
            current.setRightNode(rightNode);
            buildSubTree(current.getRightNode());
        }



    }
    public void task(Node root) {
        Scanner in = new Scanner(System.in);
        System.out.println("Загадай животное, а я попробую угадать...");
        String choice = "да";
        while (choice.equals("да")) {
            Node currentNode = root;
            while (currentNode != null && !currentNode.isLeaf()) {
                System.out.println("Это животное " + currentNode.getQuestion() + "? (да/нет)");
                String answer = in.next();
                if (answer.equals("да")) {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }

            }
            if (currentNode != null && currentNode.isLeaf()) {
                System.out.println("Это " + currentNode.getAnimal() + "? (да/нет)");
                String answer = in.next();
                in.nextLine();
                if (answer.equals("да")) {
                    System.out.println("Ура, я угадал!");
                } else {
                    System.out.println("Какое животное ты загадал?");
                    String newAnimal = in.nextLine();

                    System.out.println("Чем \"" + newAnimal + "\" отличается от \""
                            + currentNode.getAnimal() + "\"?");
                    String newQuestion = in.nextLine();

                    update(currentNode, newQuestion, newAnimal);
                    updateTree(currentNode,newAnimal, newQuestion);
                }
                System.out.println("Хотите сыграть снова? (да/нет)");
                answer = in.next();
            }
        }
    }

    private void update(Node current, String newQuestion, String newAnimal){
        nodeRepository.update(newQuestion, current.getAnimal());
        nodeRepository.create(current.getAnimal());
        nodeRepository.create(newAnimal);
        binaryTreeStructureRepository.create(newQuestion,newAnimal,current.getAnimal());
    }
    private void updateTree(Node current, String newAnimal, String newQuestion) {
        current.setLeaf(false);
        current.setQuestion(newQuestion);
        current.setLeftNode(new Node(
                null,
                null,
                current.getAnimal(),
                null,
                null,
                true
                )
        );
        current.setRightNode(new Node(
                null,
                null,
                current.getAnimal(),
                null,
                null,
                true)
        );
        current.setAnimal(null);
    }


}
