/*
 * SingleSpriteViewPanel.java - Created Jul 29, 2008 8:05:45 PM
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

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.*;

import org.ccdevnet.cstitcher.libraries.BodySpriteLibrary;

/**
 * A small simple JPanel that displays a single part sprite. 
 */
public class SinglePartViewPanel extends JPanel {
	
	private BodySpriteLibrary spriteLib;
	private int part, pose;
	private Color backgroundColor;
	
	private boolean enabled;
	private boolean selected;

	public SinglePartViewPanel(BodySpriteLibrary spriteLib, int part, int pose) {
		this.spriteLib = spriteLib;
		this.part = part;
		this.pose = pose;
		
		backgroundColor = Color.black;
		enabled = false;
		selected = false;
		
		setPreferredSize(new Dimension(50, 50));
		setBorder(BorderFactory.createRaisedBevelBorder());
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		if (selected) {
			g.setColor(Color.yellow);
			g.fillRect(2, 2, getWidth() - 4, getHeight() - 4);
			g.setColor(backgroundColor);
			g.fillRect(4, 4, getWidth() - 8, getWidth() - 8);
		}
		
		if (!enabled) return;
		
		BufferedImage spr = spriteLib.getSprite(part, pose);
		
		Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                            RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int iw = spr.getWidth();
        int ih = spr.getHeight();
        double scale = ((double)w)/iw;
        double x = (w - scale*iw)/2;
        double y = (h - scale*ih)/2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale, scale);
        g2.drawRenderedImage(spr, at);

	}

	public void setPart(int part) {
		this.part = part;
	}

	public void setPose(int pose) {
		this.pose = pose;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getPart() {
		return part;
	}

	public int getPose() {
		return pose;
	}

	
}
