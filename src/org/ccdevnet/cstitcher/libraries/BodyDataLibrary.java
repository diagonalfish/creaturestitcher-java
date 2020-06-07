/*
 * BodyDataLibrary.java - Created Jul 26, 2008 9:37:32 PM
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

import org.ccdevnet.cstitcher.att.ATTData;
import org.ccdevnet.cstitcher.att.ATTFileReader;
import org.ccdevnet.cstitcher.att.ATTFileWriter;
import org.ccdevnet.cstitcher.exceptions.ATTFileReadException;
import org.ccdevnet.cstitcher.exceptions.ATTFileWriteException;
import org.ccdevnet.cstitcher.util.NameSets;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * This object loads and provides the means to access
 * and modify ATT (body data) files for a given set
 * (gender/species, life stage, breed slot).
 *
 * @author Eric
 */
public class BodyDataLibrary {

    private ATTData[] atts;

    private int gender, lstage, slot;

    /* keep track of the loaded gender, lstage, slot, so we know
       where to save later on */
    private int Lgender, Llstage, Lslot;

    private String attDir;

    /**
     * Creates a new BodyDataLibrary that will initially look
     * for body data files with the given gender, lifestage, and slot.
     *
     * @param gender Integer 0-7
     * @param lstage Integer 0-6
     * @param slot   Integer 0-13
     * @param attDir Directory where we will look for body data files (include trailing slash)
     */
    public BodyDataLibrary(int gender, int lstage, int slot,
                           String attDir) {
        this.gender = gender;
        this.lstage = lstage;
        this.slot = slot;
        this.attDir = attDir;

        Lgender = Llstage = Lslot = -1;
    }

    /**
     * Creates a BodyDataLibrary with gender, lifestage and slot
     * initialized to zero.
     *
     * @param attDir Directory where we will look for body data files (include trailing slash)
     */
    public BodyDataLibrary(String attDir) {
        gender = lstage = slot = 0;
        Lgender = Llstage = Lslot = -1;
        this.attDir = attDir;
    }

    /**
     * Attempts to load all relevant body data from the current directory,
     * based on the current settings for gender, lifestage, and slot.
     *
     * @throws ATTFileReadException
     * @throws FileNotFoundException
     */
    public void reloadBodyData(boolean createIfMissing) throws ATTFileReadException,
            FileNotFoundException {
        ATTData[] newAtts = new ATTData[14];

        for (int i = 0; i < 14; i++) {
            String filename = attDir + NameSets.intToLetter(i) +
                    gender + lstage + NameSets.intToLetter(slot) + ".att";

            int points;
            if (i == 0) points = 5; //head has 5 pts
            else if (i == 1) points = 6; //body has 6 pts
            else points = 2; //other body parts

            ATTFileReader afr = new ATTFileReader(16, points, filename);

            try {
                newAtts[i] = afr.readData();
            } catch (FileNotFoundException fe) {
                if (createIfMissing) {
                    /* Fill in the hole with blank ATTData */
                    newAtts[i] = new ATTData(16, points);
                } else throw new FileNotFoundException("Could not find " +
                        NameSets.bodyPartName(i, false) +
                        " body data file (should be located at " + filename + ")");
            }
        }

        atts = newAtts;
        Lgender = gender;
        Llstage = lstage;
        Lslot = slot;
    }


    public void saveBodyData() throws ATTFileWriteException {
        ATTFileWriter afr = new ATTFileWriter();

        for (int i = 0; i < 14; i++) {
            String filename = attDir + NameSets.intToLetter(i) +
                    gender + lstage + NameSets.intToLetter(slot) + ".att";

            afr.setAttFile(filename);
            afr.writeData(atts[i]);
        }
    }

    /**
     * Get the att data coordinates for a given part and pose, at the
     * given index.
     *
     * @param part
     * @param pose
     * @param pointIndex
     * @return A Point containing an x and y coordinate
     */
    public Point getPoint(int part, int pose, int pointIndex) {
        return atts[part].getPoint(pose, pointIndex);
    }

    /**
     * Save a point back into the library.  This will result in it being set
     * to 'modified' and will be saved next time the save function is called.
     *
     * @param part
     * @param pose
     * @param pointIndex
     * @param newPoint
     */
    public void setPoint(int part, int pose, int pointIndex, Point newPoint) {
        atts[part].setPoint(pose, pointIndex, newPoint);
    }


    /**
     * Determines the number of att files that have been modified
     *
     * @return Number of files modified, or 0 if none
     */
    public int modifiedAtts() {
        int count = 0;
        for (int i = 0; i < 14; i++) {
            if (atts[i].isModified()) count++;
        }
        return count;
    }

    public String getAttDir() {
        return attDir;
    }

    public void setAttDir(String attDir) {
        this.attDir = attDir;
    }
}
