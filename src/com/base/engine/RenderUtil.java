package com.base.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtil
{
    public static void clearScreen()
    {
        // TODO: Stencil Buffer
        glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
    }
    
    public static void setTextures( boolean enabled )
    {
        if( enabled )
        {
            glEnable( GL_TEXTURE_2D );
        }
        else
        {
            glDisable( GL_TEXTURE_2D );
        }
    }
    
    public static void initGraphics()
    {
        glClearColor( 0.0f, 0.0f, 0.0f, 0.0f );
        
        glFrontFace( GL_CW );
        glCullFace( GL_BACK );
        glEnable( GL_CULL_FACE );
        glEnable( GL_DEPTH_TEST );
        
        // TODO: Depth clamp for later
        
        glEnable( GL_TEXTURE_2D );
        glEnable( GL_FRAMEBUFFER_SRGB );
    }
    
    public static String getOpenGLVersion()
    {
        return glGetString( GL_VERSION );
    }
}