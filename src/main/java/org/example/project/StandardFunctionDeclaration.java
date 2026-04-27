package org.example.project;

import java.util.HashMap;
import java.util.Map;

public class StandardFunctionDeclaration {
    public static StandardFunctionDeclaration declaration;

    public static StandardFunctionDeclaration getDeclaration(){
        if(declaration == null){
            declaration = new StandardFunctionDeclaration();
        }
        return declaration;
    }

    public Map<String, FunctionDeclaration> declarationsMap = new HashMap<>();

    public StandardFunctionDeclaration() {
        fillWithStandardDeclarations();
    }
    private void fillWithStandardDeclarations(){
        for (StandardLogoFunctions standardLogoFunctions : StandardLogoFunctions.values()){
            declarationsMap.put(standardLogoFunctions.declaration.name, standardLogoFunctions.declaration);
        }
    }
    public FunctionDeclaration getFunctionDeclaration(String name){
        return declarationsMap.get(name.toLowerCase());
    }
    public boolean contains(String name){
        return declarationsMap.containsKey(name.toLowerCase());
    }
}
