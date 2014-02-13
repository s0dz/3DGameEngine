package com.base.engine;

import org.lwjgl.input.Keyboard;

public class Game
{
    private Mesh mesh;
    private Shader shader;
    private Material material;
    private Transform transform;
    private Camera camera;
    
    public Game()
    {
        mesh = new Mesh(); // mesh = ResourceLoader.loadMesh( "cube.obj" );
        material = new Material( ResourceLoader.loadTexture( "test.png" ), new Vector3f( 0, 1, 1 ) );
        shader = new PhongShader().getInstance();
        camera = new Camera();
        
        Vertex[] vertices = new Vertex[] { new Vertex( new Vector3f( -1, -1, 0 ), new Vector2f( 0,0 ) ),
                                       new Vertex( new Vector3f( 0, 1, 0 ), new Vector2f( 0.5f, 0 ) ),
                                       new Vertex( new Vector3f( 1, -1, 0), new Vector2f( 1.0f, 0 ) ),
                                       new Vertex( new Vector3f( 0, -1, 1), new Vector2f( 0.0f, 0.5f ) ) };
        
        int[] indices = new int[] { 3, 1, 0,
                                    2, 1, 3,
                                    0, 1, 2,
                                    0, 2, 3 };
        
        mesh.addVertices( vertices, indices );
        
        transform = new Transform();
        transform.setProjection( 70f, MainComponent.WIDTH, MainComponent.HEIGHT, 0.1f, 1000 );
        transform.setCamera( camera );
        
        PhongShader.setAmbientLight( new Vector3f( 0.1f, 0.1f, 0.1f ) );
    }
    
    public void input()
    {
        camera.input();
        
//        if(Input.getKeyDown( Keyboard.KEY_UP ) )
//            System.out.println( "We've just pressed up!" );
//        if(Input.getKeyUp( Keyboard.KEY_UP ) )
//            System.out.println( "We've just released up!" );
//        
//        if(Input.getMouseDown( 1 ) )
//            System.out.println( "We've just right clicked at " + Input.getMousePosition() );
//        if(Input.getMouseUp( 1 ) )
//            System.out.println( "We've just released right click!" );
    }
    
    float temp = 0.0f;
    
    public void update()
    {
        temp += Time.getDelta();
        
        float sinTemp = (float)Math.sin( temp );
        
        transform.setTranslation( sinTemp, 0, 5 );
        transform.setRotation( 0, sinTemp * 180, 0 );
        //transform.setScale( 0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp );
    }
    
    public void render()
    {
        RenderUtil.setClearColor( Transform.getCamera().getPos().div( 2048f ).abs() );
        shader.bind();
        shader.updateUniforms( transform.getTransformation(), transform.getProjectedTransformation(), material );
        mesh.draw();
    }
}