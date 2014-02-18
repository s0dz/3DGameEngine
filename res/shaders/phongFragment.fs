#version 330

in vec2 texCoord0;

out vec4 fragColor;

uniform vec3 baseColor;
uniform vec3 ambientLight;
uniform sampler2D sampler;

struct BaseLight
{
    vec3 color;
    float intensity;
};

struct DirectionLight
{
    BaseLight base;
    vec3 direction;
};

vec4 calcLight( BaseLight base, vec3 direction, vec3 normal )
{
    float diffuseFactor = dot( -direction, normal );

    vec4 diffuseColor = vec4( 0, 0, 0 , 0 );

    if( diffuseFactor > 0 )
    {
        diffuseColor = vec4( base.color, 1.0 ) * base.intensity * diffuseFactor;
    }

    return diffuseColor;
}

vec4 calcDirectionalLight( Light directonalLight, vec3 normal )
{
    return calcLight( directionalLight.base, directionalLight.direction, normal );
}

void main()
{
    vec4 totalLight = vec4( ambientLight, 1 );
    vec4 color = vec4( baseColor, 1 );
    vec4 textureColor = texture( sampler, texCoord0.xy );

    if( textureColor != vec4(0,0,0,0) )
        color *= textureColor;
    
    fragColor = color * totalLight;
}