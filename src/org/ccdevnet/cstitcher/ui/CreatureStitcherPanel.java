/*
 * CreatureStitcherPanel.java - Created Jul 27, 2008 4:31:00 PM
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
import java.awt.image.ImageObserver;

import javax.swing.*;
import javax.swing.event.*;

import org.ccdevnet.cstitcher.libraries.*;

/**
 * The primary GUI class.  Contains all the various GUI components, the
 * data libraries, and other stuff, and coordinates all their interactions.
 */
public class CreatureStitcherPanel extends JPanel implements ImageObserver {

	private BodyDataLibrary attLib;
	private BodySpriteLibrary spriteLib;
	
	public CreatureStitcherPanel() {
		
		this.setPreferredSize(new Dimension(500, 500));
		
		attLib = new BodyDataLibrary(0, 0, 3,
				"D:\\Backups\\Desktop\\testset\\Body Data\\");
		spriteLib = new BodySpriteLibrary(0, 0, 3,
				"D:\\Backups\\Desktop\\testset\\Images\\");
		
		try {
			System.out.print("Loading atts...");
			attLib.reloadBodyData(true);
			System.out.print("Done.\nLoading sprites...\n");
			spriteLib.reloadSprites();
			System.out.println("Done.");
			
		}
		catch (Exception e) {}
		
		PartSelectionPanel psp = new PartSelectionPanel(spriteLib);
		psp.enableViews(true);
		
		add(psp);
		
		
	}
	
}
