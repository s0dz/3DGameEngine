package com.base.engine;

import java.util.HashMap;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VALIDATE_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glGetProgram;
import static org.lwjgl.opengl.GL20.glGetShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class Shader
{
    private int program;
    private HashMap<String, Integer> uniforms;
    
    public Shader()
    {
        program = glCreateProgram();
        uniforms = new HashMap<String, Integer>();
        
        if( program == 0 )
        {
            System.err.println( "Shader creation failed: Could not find valid memory location in constructor" );
            System.exit( 1 );
        }
    }
    
    public void bind()
    {
        glUseProgram( program );
    }
    
    public void updateUniforms( Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material )
    {
        
    }
    
    public void addUniform( String uniform )
    {
        int uniformLocation = glGetUniformLocation( program, uniform );
        
        if( uniformLocation == 0xFFFFFFFF )
        {
            System.err.println( "Error: Could not find uniform: " + uniform );
            new Exception().printStackTrace();
            System.exit( 1 );
        }
        
        uniforms.put( uniform, uniformLocation );
    }
    
    public void addVertexShader( String text )
    {
        addProgram( text, GL_VERTEX_SHADER );
    }
    
    public void addGeometryShader( String text )
    {
        addProgram( text, GL_GEOMETRY_SHADER );
    }
    
    public void addFragmentShader( String text )
    {
        addProgram( text, GL_FRAGMENT_SHADER );
    }
    
    public void compileShader()
    {
        glLinkProgram( program );
        
        if( glGetProgram( program, GL_LINK_STATUS ) == 0 )
        {
            System.err.println( glGetShaderInfoLog( program, 1024 ) );
            System.exit( 1 );
        }
        
        glValidateProgram( program );
        
        if( glGetProgram( program, GL_VALIDATE_STATUS ) == 0 )
        {
            System.err.println( glGetShaderInfoLog( program, 1024 ) );
            System.exit( 1 );
        }
    }
    
    private void addProgram( String text, int type )
    {
        int shader = glCreateShader( type );
        
        if( shader == 0 )
        {
            System.err.println( "Shader creation failed: Could not find valid memory location when adding shader" );
            System.exit( 1 );
        }
        
        glShaderSource( shader, text );
        glCompileShader( shader );
        
        if( glGetShader( shader, GL_COMPILE_STATUS ) == 0 )
        {
            System.err.println( glGetShaderInfoLog( shader, 1024 ) );
            System.exit( 1 );
        }
        
        glAttachShader( program, shader );
    }
    
    public void setUniformi( String uniformName, int value )
    {
        glUniform1i( uniforms.get( uniformName ), value );
    }
    
    public void setUniformf( String uniformName, float value )
    {
        glUniform1f( uniforms.get( uniformName ), value );
    }
    
    public void setUniform( String uniformName, Vector3f value )
    {
        glUniform3f( uniforms.get( uniformName ), value.getX(), value.getY(), value.getZ() );
    }
    
    public void setUniform( String uniformName, Matrix4f value )
    {
        glUniformMatrix4( uniforms.get( uniformName ), true, Util.createFlippedBuffer( value ) );
    }
}