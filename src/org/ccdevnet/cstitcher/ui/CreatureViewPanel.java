/*
 * CreatureViewPanel.java - Created Jul 28, 2008 6:33:10 PM
 * Creature Stitcher
 * http://cstitcher.ccdevnet.org
 * 
 * Copyright (C) 2008 Eric Goodwin
 * 
 * This program is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE. See the GNU General Public License for more details.

 * You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA 02111-1307 USA
 */

package org.ccdevnet.cstitcher.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.ccdevnet.cstitcher.libraries.*;

/**
 * This panel takes the libraries of body data and sprites
 * and assembles them into a picture display
 */
public class CreatureViewPanel extends JPanel {

	private BodyDataLibrary attLib;
	private BodySpriteLibrary spriteLib;
	private int drawDir;
	private int[] poses;
	
	private boolean enabled;	
	private Color backgroundColor;
	
	private Point[] drawPoints;
	
	/* A few constants */
	
	//how far we push the drawing away from the top left corner (pixels x & y)
	private static final int ADJUST_XY = 10;
	
	//directions
	public static final int DIR_RIGHT = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_FRONT = 2;
	public static final int DIR_BACK = 3;
	
	//angles
	public static final int ANG_DOWN = 0;
	public static final int ANG_LEVEL = 1;
	public static final int ANG_UP = 2;
	public static final int ANG_UPHIGH = 3;
	
	/* Drawing orders for parts - thanks vadim! */
	public static final int[][] Z_ORDER = {
		{ 12, 13, 2, 3, 4, 8, 9, 1, 0, 10, 5, 6, 11, 7 }, //right
		{ 12, 13, 5, 6, 7, 10, 11, 1, 0, 2, 3, 4, 8, 9 }, //left
		{ 12, 13, 2, 8, 9, 10, 5, 3, 6, 11, 1, 4, 7, 0 }, //front
		{ 2, 8, 9, 0, 10, 5, 3, 6, 11, 4, 7, 1, 12, 13 }, //back
	};
	
	public CreatureViewPanel(BodyDataLibrary attLib, BodySpriteLibrary spriteLib,
			int[] poses, int drawDir) {
		this.attLib = attLib;
		this.spriteLib = spriteLib;
		this.poses = poses; //this had better have 14 elements, or doom occurs
		this.drawDir = drawDir;
		
		enabled = false;
		backgroundColor = Color.black;
		
		setPreferredSize(new Dimension(170, 200));
		
		setOpaque(false);
	}
	
	public void setAttLib(BodyDataLibrary attLib) {
		this.attLib = attLib;
	}

	/**
	 * Enables the drawing of the creature parts.
	 * DO NOT ENABLE until the libraries are loaded successfully!
	 */
	public void setEnabled(boolean enabled) {
		drawPoints = calculatePositions();
		this.enabled = enabled;
	}

	public void recalculate() {
		if (!enabled) return;
		drawPoints = calculatePositions();
	}
	
	public void setPoses(int[] poses) {
		this.poses = poses;
	}

	public void setSpriteLib(BodySpriteLibrary spriteLib) {
		this.spriteLib = spriteLib;
	}
	
	/* This is where the magic happens.  This function does all of the
	 * calculations to determine where crap goes. Returns an array of 14
	 * Points, 1 for each body part. */
	private Point[] calculatePositions() {
		Point[] points = new Point[14];
		
		//Go ahead and place the body at the origin.  We'll work the rest out from there.
		points[1] = new Point(0, 0);
		
		//Head
		points[0] = attSub(1, poses[0], 0, 0, poses[0], 0);
		
		//Left leg (2 parts) and foot
		points[2] = attSub(1, poses[2], 1, 2, poses[3], 0);
		points[3] = attSub(2, poses[2], 1, 3, poses[3], 0);
		points[3].x += points[2].x;  points[3].y += points[2].y;
		points[4] = attSub(3, poses[3], 1, 4, poses[4], 0);
		points[4].x += points[3].x;  points[4].y += points[3].y;
		
		//Right leg (2 parts) and foot
		points[5] = attSub(1, poses[1], 2, 5, poses[5], 0);
		points[6] = attSub(5, poses[5], 1, 6, poses[5], 0);
		points[6].x += points[5].x;  points[6].y += points[5].y;
		points[7] = attSub(6, poses[6], 1, 7, poses[7], 0);
		points[7].x += points[6].x;  points[7].y += points[6].y;
		
		//Left upper/lower arm
		points[8] = attSub(1, poses[1], 3, 8, poses[8], 0);
		points[9] = attSub(8, poses[8], 1, 9, poses[9], 0);
		points[9].x += points[8].x;  points[9].y += points[8].y;
		
		//Right upper/lower arm
		points[10] = attSub(1, poses[1], 4, 10, poses[10], 0);
		points[11] = attSub(10, poses[10], 1, 11, poses[11], 0);
		points[11].x += points[10].x;  points[11].y += points[10].y;
		
		//Tail base/tip
		points[12] = attSub(1, poses[1], 5, 12, poses[12], 0);
		points[13] = attSub(12, poses[12], 1, 13, poses[13], 0);
		points[13].x += points[12].x;  points[13].y += points[12].y; 
		
		/* Ok, now we need to find the minimum and maximum x/y coordinates and
		 * adjust all the points so that we are sure to draw them onscreen
		 * properly */
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (int i = 0; i < 14; i++) {
			if (points[i].x < minX) minX = points[i].x;
			if (points[i].y < minY) minY = points[i].y;
		}
		
		/* Subtracting the minimum values from all coordinates ensures that everything
		 * gets pushed onto the screen. 
		 * ie. if the minimum is -1, we subtract -1, making it 0 */
		for (int i = 0; i < 14; i++) {
			points[i].x -= minX - ADJUST_XY;
			points[i].y -= minY - ADJUST_XY;
		}
		
		return points;
	}
	
	/* The exclusive point of this function is to make the function above easier to edit.
	 * Grabs two att points and subtracts them, returning the result */
	private Point attSub(int r1, int p1, int pi1, int r2, int p2, int pi2) {
		Point point1 = attLib.getPoint(r1, p1, pi1);
		Point point2 = attLib.getPoint(r2, p2, pi2);
		int x = point1.x - point2.x;
		int y = point1.y - point2.y;
		return new Point(x, y);
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	public void paintComponent(Graphics g) {
		//draw background
		setOpaque(false);
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (!enabled) return;
		
		//Draw the parts!
		for (int i : Z_ORDER[drawDir]) {
			g.drawImage(spriteLib.getSprite(i, poses[i]), drawPoints[i].x,
					drawPoints[i].y, this);
		}
		
	}
	
	/**
	 * Handles a click on the colorButton
	 */
	/*
	public void actionPerformed(ActionEvent arg0) {
		JColorChooser cpicker = new JColorChooser();
		backgroundColor = cpicker.showDialog(this, "Choose Background Color", backgroundColor);
		canvas.repaint();
	}
	*/
}
