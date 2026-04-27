package org.example.project.staticAnalyser;

import org.example.project.FunctionDeclarationTable;
import org.example.project.UserVariableDeclaration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocalScope {

    public final Map<String, UserVariableDeclaration> declaredVariables = new HashMap<>();
    public final FunctionDeclarationTable localFunctionDeclarations = new FunctionDeclarationTable();

    public boolean containsVariable(String name){
        return declaredVariables.containsKey(name);
    }
    public boolean containsFunction(String name){
        return localFunctionDeclarations.contains(name);
    }
    public LocalScope copyScope(){
        LocalScope localScope = new LocalScope();
        localScope.declaredVariables.putAll(declaredVariables);
        localScope.localFunctionDeclarations.declarationsMap.putAll(localFunctionDeclarations.declarationsMap);
        return localScope;
    }

    @Override
    public String toString() {
        return "LocalScope{" +
                "declaredVariables=" + declaredVariables +
                ", localFunctionDeclarations=" + localFunctionDeclarations.declarationsMap +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LocalScope that = (LocalScope) o;
        return Objects.equals(declaredVariables, that.declaredVariables) && Objects.equals(localFunctionDeclarations, that.localFunctionDeclarations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declaredVariables, localFunctionDeclarations);
    }
}
