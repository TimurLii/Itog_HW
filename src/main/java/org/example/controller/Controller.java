package org.example.controller;

import javax.swing.*;
import java.awt.*;


public class Controller extends JFrame {


    private static final int WIDTH = 500;
    private static final int HEIGHT = 400;


    private AddMainMenu addMainMenu;

    public Controller() {
        this.setSettings();
        initWindowMainMenu();
        this.setVisible(true);
        addMainMenu.allListener();

    }

    private void initWindowMainMenu() {
        addMainMenu = new AddMainMenu(this );
        this.add(addMainMenu.createBtnAnimalWindow() ,BorderLayout.NORTH);
        addMainMenu.setWorkAddMainMenu(true);
    }

    private void setSettings() {
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setTitle("Animal registry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    protected void setWorkMainMenu(boolean workMainMenu){
        addMainMenu.setWorkAddMainMenu(workMainMenu);
    }


}
