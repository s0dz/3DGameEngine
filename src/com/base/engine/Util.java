package com.base.engine;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;

public class Util
{
    public static FloatBuffer createFloatBuffer( int size )
    {
        return BufferUtils.createFloatBuffer( size );
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
}