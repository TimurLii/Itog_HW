package org.example.controller;


import org.example.repo.AnimalRepository;
import org.example.repo.IAnimalRepository;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddMainMenu {
    Controller controller;
    WindowAddAnimal windowAddAnimal;
    WindowShowAllAnimal windowShowAllAnimal;
    WindowShowAllCommand windowShowAllCommand;
    WindowAddNewCommand windowAddNewCommand;

    private JPanel panelBtn;
    private JPanel panelAddWindow;
    private JButton btnShowAllAnimal;
    private JButton btnAddAnimal;
    private JButton btnAddCommand;
    private JButton btnShowAllCommandAnimal;
    protected JTextArea messageFromUser;

    IAnimalRepository iAnimalRepository;



    public AddMainMenu(Controller controller) {
        this.controller = controller;
        initWindow();
        initInterface();

    }

    private void initWindow() {
        windowAddAnimal = new WindowAddAnimal(this);
        windowShowAllAnimal = new WindowShowAllAnimal(this);
        windowShowAllCommand = new WindowShowAllCommand(this);
        windowAddNewCommand = new WindowAddNewCommand(this);

    }

    private void initInterface() {
        this.iAnimalRepository = new AnimalRepository();
    }

    protected Component createBtnAnimalWindow() {
        this.panelAddWindow = new JPanel(new BorderLayout());
        this.panelBtn = new JPanel(new GridLayout(2, 1));
        this.btnAddAnimal = new JButton("Add animal.");
        this.btnAddCommand = new JButton("Add command.");
        this.btnShowAllAnimal = new JButton("Show all animal.");
        this.btnShowAllCommandAnimal = new JButton("Show all command");


        this.panelBtn.add(btnAddAnimal);
        this.panelBtn.add(btnAddCommand);
        this.panelBtn.add(btnShowAllAnimal);
        this.panelBtn.add(btnShowAllCommandAnimal);

        this.panelAddWindow.add(panelBtn, BorderLayout.NORTH);
        this.panelAddWindow.add(createTextArea(), BorderLayout.CENTER);


        return this.panelAddWindow;
    }

    protected Component createTextArea() {
        messageFromUser = new JTextArea();
        return messageFromUser;
    }


    public void allListener() {
        addAnimaListener();
        addCommandListener();
        addNewCommandByAnimal();
        showAllAnimal();
    }

    public void addAnimaListener() {
        btnAddAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.add(windowAddAnimal.panelCreateAnimal());
                controller.setWorkMainMenu(false);
                windowAddAnimal.listenerForAddAnimalWindow();

            }
        });
    }


    public void addCommandListener() {
        btnAddCommand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkAddMainMenu(false);
                controller.add(windowAddNewCommand.createWindowAddNewCommand());
                windowAddNewCommand.addNewCommandListener();
            }
        });
    }

    public void showAllAnimal() {
        btnShowAllAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkAddMainMenu(false);
                controller.add(windowShowAllAnimal.createPanelTextArea());
                windowShowAllAnimal.showAllAnimal();
                windowShowAllAnimal.btnBackListener();

            }
        });
    }

    public void addNewCommandByAnimal() {
        btnShowAllCommandAnimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setWorkAddMainMenu(false);
                controller.add(windowShowAllCommand.createPanelTextArea());
                windowShowAllCommand.showAllCommandListener();
            }

        });
    }

    protected void setWorkAddMainMenu(boolean workAddMainMenu) {
        panelBtn.setVisible(workAddMainMenu);
    }

}
