package org.example.model;

import java.util.Arrays;
import java.util.List;

public class Animal {
    String name;
    String[] command;
    String birthDay;

    public Animal(String name,String[] command, String birthDay) {
        this.name = name;
        this.command = command;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCommand() {
        return command;
    }

    public void setCommand(String[] command) {
        this.command = command;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", command=" + Arrays.toString(command) +
                ", birthDay='" + birthDay + '\'' +
                '}';
    }

}
