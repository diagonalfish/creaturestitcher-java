/*
 * BodySpriteLibrary.java - Created Jul 26, 2008 8:57:48 PM
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

package org.ccdevnet.cstitcher.libraries;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.ccdevnet.cstitcher.exceptions.*;
import org.ccdevnet.cstitcher.util.NameSets;
import rebound.jagent.lib.c16.*;

/**
 * This object loads and provides access to all sprites for
 * a given set (gender/species, life stage, breed slot) as 
 * BufferedImage objects.
 */
public class BodySpriteLibrary {

	private BufferedImage[][] sprites;

	private int gender, lstage, slot;
	
	private String spriteDir;
	
	private boolean eyesClosed;
	private int expression;
	
	/**
	 * Creates a new BodySpriteLibrary that will initially look
	 * for sprites with the given gender, lifestage, and slot.
	 * @param gender Integer 0-7
	 * @param lstage Integer 0-6
	 * @param slot Integer 0-13
	 * @param spriteDir Directory where we will look for sprites (include trailing slash)
	 */
	public BodySpriteLibrary(int gender, int lstage, int slot,
			String spriteDir) {
		this.gender = gender;
		this.lstage = lstage;
		this.slot = slot;
		this.spriteDir = spriteDir;
		eyesClosed = false;
		expression = 0;
		
	}
	
	/**
	 * Creates a BodySpriteLibrary with gender, lifestage and slot
	 * initialized to zero.
	 * @param spriteDir Directory where we will look for sprites (include trailing slash)
	 */
	public BodySpriteLibrary(String spriteDir) {
		expression = gender = lstage = slot = 0;
		eyesClosed = false;
		this.spriteDir = spriteDir;
	}
	
	/**
	 * Attempts to load all relevant sprites from the current sprite directory,
	 * based on the current settings for gender, lifestage, and slot.
	 * @throws SpriteLoadException
	 */
	public void reloadSprites() throws SpriteLoadException {
		BufferedImage[][] newSprites = new BufferedImage[14][];
		
		for (int i = 0; i < 14; i++) {
			String filename = spriteDir + NameSets.intToLetter(i) +
				gender + lstage + NameSets.intToLetter(slot) + ".c16";
			System.out.println("  Loading " + NameSets.bodyPartName(i, false));
			FromC16Converter c16 = new FromC16Converter(filename);
			try {
				c16.read();
				newSprites[i] = c16.convertFrames(BufferedImage.TYPE_INT_ARGB);
			}
			catch (IOException ioe) {
				throw new SpriteLoadException("Could not load " +
						NameSets.bodyPartName(i, false) + " sprites.  Make " +
						"sure that " + filename + " exists and try again");
			}
			if (i == 0 && newSprites[0].length != 192) {
				throw new SpriteLoadException("Head sprite file (" + filename +
						") contains " + newSprites[0].length + " sprites instead " +
						"of the expected 192");
			}
			else if (i == 1 && newSprites[1].length != 64) {
				throw new SpriteLoadException("Body sprite file (" + filename +
						") contains " + newSprites[1].length + " sprites instead " +
						"of the expected 64");
			}
			else if (i > 1 && newSprites[i].length != 16) {
				throw new SpriteLoadException(NameSets.bodyPartName(i, true) +
						" sprite file (" + filename +
						") contains " + newSprites[i].length + " sprites instead " +
						"of the expected 16");
			}
		}
		sprites = newSprites;
	}
	
	/**
	 * Returns head sprite for the given pose.  Takes the settings for
	 * eyesClosed and expression into account.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getHead(int pose) {
		if (pose > 15) pose = 15;
		int index = pose;
		index += expression * 32;
		if (eyesClosed) index += 16;
		return sprites[0][index];
	}
	
	/**
	 * Returns body sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getBody(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[1][pose];
	}
	
	/**
	 * Returns left upper leg sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getLeftUpperLeg(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[2][pose];
	}
	
	/**
	 * Returns left lower leg sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getLeftLowerLeg(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[3][pose];
	}
	
	/**
	 * Returns left foot sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getLeftFoot(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[4][pose];
	}
	
	/**
	 * Returns right upper leg sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getRightUpperLeg(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[5][pose];
	}
	
	/**
	 * Returns right lower leg sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getRightLowerLeg(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[6][pose];
	}
	
	/**
	 * Returns right foot sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getRightFoot(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[7][pose];
	}
	
	/**
	 * Returns left upper arm sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getLeftUpperArm(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[8][pose];
	}
	
	/**
	 * Returns left lower arm sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getLeftLowerArm(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[9][pose];
	}
	
	/**
	 * Returns right upper arm sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getRightUpperArm(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[10][pose];
	}
	
	/**
	 * Returns right lower arm sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getRightLowerArm(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[11][pose];
	}
	
	/**
	 * Returns tail base sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getTailBase(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[12][pose];
	}
	
	/**
	 * Returns tail tip sprite for the given pose.
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getTailTip(int pose) {
		if (pose > 15) pose = 15;
		//Not dealing with the pregnancy sprites for now...
		return sprites[13][pose];
	}
	
	/**
	 * Direct accessor to the part library.  Returns the sprite
	 * for the given part and pose.  For the head, takes settings
	 * for eyesClosed and expression into account.
	 * @param part
	 * @param pose
	 * @return Sprite as BufferedImage
	 */
	public BufferedImage getSprite(int part, int pose) {
		if (pose > 15) pose = 15;
		if (part > 15) part = 13;
		
		int index = pose;
		
		if (part == 0) {
			index += expression * 32;
			if (eyesClosed) index += 16;
		}
		return sprites[part][index];
		
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setLifeStage(int lstage) {
		this.lstage = lstage;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

	/**
	 * Determines which expression will be used when returning
	 * a head sprite.
	 * @param expression
	 */
	public void setExpression(int expression) {
		this.expression = expression;
	}

	/**
	 * Sets whether the eyes-closed version of the sprite will be used when
	 * returning a head sprite.
	 * @param eyesClosed
	 */
	public void setEyesClosed(boolean eyesClosed) {
		this.eyesClosed = eyesClosed;
	}

	/**
	 * Set new sprite directory to look for sprites.  Include a trailing slash!
	 * @param spriteDir
	 */
	public void setSpriteDir(String spriteDir) {
		this.spriteDir = spriteDir;
	}

	public int getExpression() {
		return expression;
	}

	public boolean isEyesClosed() {
		return eyesClosed;
	}
}
