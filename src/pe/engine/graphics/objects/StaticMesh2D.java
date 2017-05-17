package pe.engine.graphics.objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.system.MemoryStack;

import pe.engine.main.PE;
import pe.util.math.Vec2f;
import pe.util.math.Vec3f;

public class StaticMesh2D extends Mesh{

	public StaticMesh2D(Vec2f[] vertices, int[] indices){
		super(PE.STATIC_MESH_2D, indices.length);
		
		try (MemoryStack stack = MemoryStack.stackPush()){
			vao.use();
			
			FloatBuffer vertecesBuffer = stack.mallocFloat(2 * vertices.length);
			for(Vec2f vertex:vertices){
				vertex.putInBuffer(vertecesBuffer);
			}
			vertecesBuffer.flip();
			
			vao.addVBO(2, vertecesBuffer);
			
			FloatBuffer barycentricBuffer = stack.mallocFloat(3 * 3 * indices.length);
			for(int i = 0; i < indices.length; i++){
				(new Vec3f(1, 0, 0)).putInBuffer(barycentricBuffer);
				(new Vec3f(0, 1, 0)).putInBuffer(barycentricBuffer);
				(new Vec3f(0, 0, 1)).putInBuffer(barycentricBuffer);
			}
			
			IntBuffer indecesBuffer = stack.mallocInt(indices.length);
			indecesBuffer.put(indices);
			indecesBuffer.flip();
			
			vao.addEBO(indecesBuffer);
			
			vao.unbind();
		}
	}
}
