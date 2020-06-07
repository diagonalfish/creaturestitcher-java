/*
 * SpriteLoadException.java - Created Jul 26, 2008 11:05:25 PM
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

package org.ccdevnet.cstitcher.exceptions;

/**
 * Exception that is thrown when an error occurs during the
 * sprite loading process in a BodyDataLibrary.
 */
public class SpriteLoadException extends Exception {

    public SpriteLoadException(String message) {
        super(message);
    }

}
