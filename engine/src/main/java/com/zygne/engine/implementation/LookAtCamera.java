package com.zygne.engine.implementation;

import android.opengl.GLU;

import com.zygne.engine.core.math.Vector3;

import javax.microedition.khronos.opengles.GL10;

public class LookAtCamera {
	private final Vector3 position;
	private final Vector3 up;
	private final Vector3 lookAt;
	private float fieldOfView;
	private float aspectRatio;
	private float near;
	private float far;

	public LookAtCamera(float fieldOfView, float aspectRatio, float near, float far) {
		this.fieldOfView = fieldOfView;
		this.aspectRatio = aspectRatio;
		this.near = near;
		this.far = far;
		
		position = new Vector3();
		up = new Vector3(0, 1, 0);
		lookAt = new Vector3(0,0,-1);
	}
	
	public Vector3 getPosition() {
		return position;
	}
	
	public Vector3 getUp() {
		return up;
	}
	
	public Vector3 getLookAt() {
		return lookAt;
	}
	
	public void setMatrices(GL10 gl) {
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, fieldOfView, aspectRatio, near, far);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, position.x, position.y, position.z, lookAt.x, lookAt.y, lookAt.z, up.x, up.y, up.z);
	}
}
