package com.base.engine;

import org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL15.*;

public class Mesh
{
    private int vbo;
    private int size;
    
    public Mesh()
    {
        vbo = glGenBuffers();
    }
}