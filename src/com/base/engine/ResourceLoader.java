package com.base.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ResourceLoader
{
    public static String loadShader( String fileName )
    {
        StringBuilder shaderSource = new StringBuilder();
        BufferedReader shaderReader = null;
        
        try
        {
            shaderReader = new BufferedReader( new FileReader( "./res/shaders/" + fileName ) );
            String line;
            while( ( line = shaderReader.readLine() ) != null )
            {
                shaderSource.append( line ).append( "\n" );
            }
            
            shaderReader.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
            System.exit(1);
        }
        
        return shaderSource.toString();
    }
    
    public static Mesh loadMesh( String fileName )
    {
        String[] splitArray = fileName.split( "//." );
        String ext = splitArray[ splitArray.length - 1 ];
        
        if( !ext.equals( "obj" ) )
        {
            System.err.println( "Error: File format not supported for mesh data: " + ext );
            new Exception().printStackTrace();
            System.exit( 1 );
        }
        
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();
        ArrayList<Vertex> indices = new ArrayList<Vertex>();
        
        BufferedReader meshReader = null;
        
        try
        {
            meshReader = new BufferedReader( new FileReader( "./res/models/" + fileName ) );
            String line;
            
            while( ( line = meshReader.readLine() ) != null )
            {
                String[] tokens = line.split( " " );
                tokens = Util.removeEmptyStrings( tokens );
            }
            
            meshReader.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();;
            System.exit( 1 );
        }
        
        return null;
    }
}