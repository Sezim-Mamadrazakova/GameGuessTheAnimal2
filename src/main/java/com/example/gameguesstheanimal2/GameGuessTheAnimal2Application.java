package com.example.gameguesstheanimal2;

import com.example.gameguesstheanimal2.entity.Tree;
import com.example.gameguesstheanimal2.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameGuessTheAnimal2Application implements CommandLineRunner {
    @Autowired
    private TreeService treeService;


    public static void main(String[] args) {
        SpringApplication.run(GameGuessTheAnimal2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tree tree=treeService.buildTree();
        treeService.task(tree.getRoot());
    }
}
