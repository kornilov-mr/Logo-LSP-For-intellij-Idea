package org.example.project;

import org.example.communication.DTO.Range;

public class UserVariableDeclaration {
    public final String name;
    public final Range range;

    public UserVariableDeclaration(String name, Range range) {
        this.name = name;
        this.range = range;
    }

    @Override
    public String toString() {
        return "UserVariableDeclaration{" +
                "name='" + name + '\'' +
                ", range=" + range +
                '}';
    }
}
