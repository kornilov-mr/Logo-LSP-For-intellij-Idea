package org.example.project;

import org.example.project.ast.ConvertToAST;

import java.util.concurrent.ConcurrentHashMap;

public class ProjectContext {
    public final static ConcurrentHashMap<String, FileNode> openFiles = new ConcurrentHashMap<>();

    public static void didOpenFile(String url, String textContent) {
        FileNode fileNode = ConvertToAST.convert(textContent);
        openFiles.put(url, fileNode);
    }

    public static void didCloseFile(String url) {
        openFiles.remove(url);
    }

    public static FileNode getFileNode(String url){
        return openFiles.get(url);
    }
}
