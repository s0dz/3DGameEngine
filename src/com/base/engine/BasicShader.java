package com.base.engine;

public class BasicShader extends Shader
{
    public BasicShader()
    {
        super();
        
        addVertexShader( ResourceLoader.loadShader( "basicVertex.vs" ) );
        addFragmentShader( ResourceLoader.loadShader( "basicFragment.vs" ) );
        compileShader();
        
        addUniform( "transform" );
    }
    
    public void updateUniforms( Matrix4f worldMatrix, Matrix4f projectedMatrix )
    {
        setUniform( "transofrm", projectedMatrix );
    }
}