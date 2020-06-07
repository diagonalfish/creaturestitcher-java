/*
 * NudgeReceiver.java - Created Jul 30, 2008 10:11:09 PM
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

package org.ccdevnet.cstitcher.iface;

/**
 * Receiver for events from a NudgePanel
 */
public interface NudgeReceiver {

    public void nudgeLeft();

    public void nudgeRight();

    public void nudgeUp();

    public void nudgeDown();

}
