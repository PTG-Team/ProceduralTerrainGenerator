#version 330 core
#extension GL_OES_standard_derivatives : enable

in vec2 texCoord;

out vec4 color;

uniform sampler2D texture;

void main() {
	color = texture(texture, texCoord);
}