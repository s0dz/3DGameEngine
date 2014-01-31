package com.base.engine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import org.lwjgl.BufferUtils;

public class Util
{
    public static FloatBuffer createFloatBuffer( int size )
    {
        return BufferUtils.createFloatBuffer( size );
    }
    
    public static IntBuffer createIntBuffer( int size )
    {
        return BufferUtils.createIntBuffer( size );
    }
    
    public static IntBuffer createFlippedBuffer( int... values )
    {
        IntBuffer buffer = createIntBuffer( values.length );
        buffer.put( values );
        buffer.flip();
        
        return buffer;
    }
    
    public static FloatBuffer createFlippedBuffer( Vertex[] vertices )
    {
        FloatBuffer buffer = createFloatBuffer( vertices.length * Vertex.SIZE );
        
        for( Vertex vertice : vertices )
        {
            buffer.put( vertice.getPos().getX() );
            buffer.put( vertice.getPos().getY() );
            buffer.put( vertice.getPos().getZ() );
        }
        
        buffer.flip();
        
        return buffer;
    }
    
    public static FloatBuffer createFlippedBuffer( Matrix4f value )
    {
        FloatBuffer buffer = createFloatBuffer( 4 * 4 );
        
        for( int i = 0; i < 4; i++ )
            for( int j = 0; j < 4; j++ )
                buffer.put( value.get( i, j ) );
        
        buffer.flip();
        
        return buffer;
    }
    
    public static String[] removeEmptyStrings( String[] data )
    {
        ArrayList<String> result = new ArrayList<String>();
        
        for( int i = 0; i < data.length; i++ )
        {
            if( !data[i].equals( "" ) )
            {
                result.add( data[i] );
            }
        }
        
        String[] res = new String[ result.size() ];
        result.toArray( res );
        
        return res;
    }
}