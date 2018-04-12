/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yourorghere;



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
/**
 * This program demonstrates a single modeling transformation, glScalef() and a
 * single viewing transformation, gluLookAt(). A wireframe cube is rendered.
 * 
 * @author Kiet Le (Java conversion)
 */
public class Cube implements GLEventListener{

   public static DisplayMode dm, dm_old;
   private GLU glu = new GLU();
   private float rquad=0.0f;
   @Override
   public void display( GLAutoDrawable drawable ) {

      final GL gl = drawable.getGL();
      gl.glClear( GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );     
      gl.glLoadIdentity();
      gl.glTranslatef( 0f, 0f, -5.0f ); 
      gl.glRotatef( rquad, 1.0f, 1.0f, 1.0f ); // Rotate The Cube On X, Y & Z
      //giving different colors to different sides
      gl.glBegin( GL.GL_QUADS ); // Start Drawing The Cube
      gl.glColor3f( 1f,0f,0f );   //red color
      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Top)
      gl.glVertex3f( -1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Bottom Left Of The Quad (Top)
      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Bottom Right Of The Quad (Top)
      gl.glColor3f( 0f,1f,0f ); //green color
      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Top Right Of The Quad 
      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Top Left Of The Quad 
      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 
      gl.glColor3f( 0f,0f,1f ); //blue color
      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Front)
      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Left Of The Quad (Front)
      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad 
      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 
      gl.glColor3f( 1f,1f,0f ); //yellow (red + green)
      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad
      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Back)
      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Back)
      gl.glColor3f( 1f,0f,1f ); //purple (red + green)
      gl.glVertex3f( -1.0f, 1.0f, 1.0f ); // Top Right Of The Quad (Left)
      gl.glVertex3f( -1.0f, 1.0f, -1.0f ); // Top Left Of The Quad (Left)
      gl.glVertex3f( -1.0f, -1.0f, -1.0f ); // Bottom Left Of The Quad 
      gl.glVertex3f( -1.0f, -1.0f, 1.0f ); // Bottom Right Of The Quad 
      gl.glColor3f( 0f,1f, 1f ); //sky blue (blue +green)
      gl.glVertex3f( 1.0f, 1.0f, -1.0f ); // Top Right Of The Quad (Right)
      gl.glVertex3f( 1.0f, 1.0f, 1.0f ); // Top Left Of The Quad 
      gl.glVertex3f( 1.0f, -1.0f, 1.0f ); // Bottom Left Of The Quad 
      gl.glVertex3f( 1.0f, -1.0f, -1.0f ); // Bottom Right Of The Quad 
      gl.glEnd(); // Done Drawing The Quad
      gl.glFlush();
      rquad -=0.15f;
   }
  
   public void dispose( GLAutoDrawable drawable ) {
   // TODO Auto-generated method stub
   }

   public void init( GLAutoDrawable drawable ) {
      final GL gl = drawable.getGL();
      gl.glShadeModel( GL.GL_SMOOTH );
      gl.glClearColor( 0f, 0f, 0f, 0f );
      gl.glClearDepth( 1.0f );
      gl.glEnable( GL.GL_DEPTH_TEST );
      gl.glDepthFunc( GL.GL_LEQUAL );
      gl.glHint( GL.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST );
   }

   public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height ) {
      final GL gl = drawable.getGL();
      if( height <=0 )
         height =1;
      final float h = ( float ) width / ( float ) height;
      gl.glViewport( 0, 0, width, height );
      gl.glMatrixMode( GL.GL_PROJECTION );
      gl.glLoadIdentity();
      glu.gluPerspective( 45.0f, h, 1.0, 20.0 );
      gl.glMatrixMode( GL.GL_MODELVIEW );
      gl.glLoadIdentity();
   }
   public static void main( String[] args ) {
       
      GLCapabilities capabilities = new GLCapabilities( );
      // The canvas 
      final GLCanvas glcanvas = new GLCanvas( capabilities );
      Cube cube = new Cube();
      glcanvas.addGLEventListener( cube );
      glcanvas.setSize( 400, 400 );
      final JFrame frame = new JFrame ( " Omar cube" );
      frame.getContentPane().add( glcanvas );
      frame.setSize( frame.getContentPane().getPreferredSize() );
      frame.setVisible( true );
      final FPSAnimator animator = new FPSAnimator( glcanvas, 300,true );
      animator.start();
   }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}