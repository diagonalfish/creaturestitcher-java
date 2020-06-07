/*
 * ATTFileReader.java - Created Jul 26, 2008 4:30:24 PM
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

package org.ccdevnet.cstitcher.att;

import org.ccdevnet.cstitcher.exceptions.ATTFileReadException;

import java.awt.*;
import java.io.*;

/**
 * Reads ATT (body data) files and provides ATTData objects
 * containing the data that is read.
 */
public class ATTFileReader {

    private final int rows;
    private final int pointsPerRow;

    private String attFile;

    /**
     * Creates a new ATTFileReader.  The reader will expect the ATT
     * file (given as a string path) to have the given number of rows
     * (lines) and the given number of points (sets of coordinates) per row.
     *
     * @param rows         Number of lines expected
     * @param pointsPerRow Number of points per line expected
     * @param attFile      File path to be read
     */
    public ATTFileReader(int rows, int pointsPerRow, String attFile) {
        this.rows = rows;
        this.pointsPerRow = pointsPerRow;
        this.attFile = attFile;
    }

    public ATTData readData() throws ATTFileReadException, FileNotFoundException {
        File f = new File(attFile);
        FileInputStream fis;
        fis = new FileInputStream(f); //At this point we may throw a FileNotFoundException

        Reader r = new BufferedReader(new InputStreamReader(fis));
        StreamTokenizer st = new StreamTokenizer(r);
        st.eolIsSignificant(true);
        st.parseNumbers();

        ATTData att = new ATTData(rows, pointsPerRow);

        for (int i = 0; i < rows; i++) {
            //System.out.println("Parsing line " + st.lineno());
            boolean xy = true;
            int pcoord = 0;
            Point apoint = new Point();

            for (int j = 0; j < pointsPerRow * 2; j++) {
                //System.out.print(" Token " + (j + 1) + ":");
                try {
                    st.nextToken();
                } catch (IOException ioe) {
                    throw new ATTFileReadException("IOException: " + ioe.getMessage());
                }
                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    //System.out.println(st.nval);
                    if (xy) {
                        apoint.x = (int)st.nval;
                        xy = false;
                    } else {
                        apoint.y = (int)st.nval;
                        //System.out.println("  Point " + pcoord + ": " + apoint.x + "," + apoint.y);
                        att.setPoint(i, pcoord, (Point) apoint.clone());
                        xy = true;
                        pcoord++;
                    }
                } else {
                    throw new ATTFileReadException("Invalid format in " + attFile + ": " +
                            "Found non-number token on line " + st.lineno());
                }
            }
            if (i < rows - 1) {
                try {
                    st.nextToken();
                } catch (IOException ioe) {
                    throw new ATTFileReadException("IOException: " + ioe.getMessage());
                }
                if (st.ttype != StreamTokenizer.TT_EOL) {
                    throw new ATTFileReadException("Invalid format in " + attFile + ": " +
                            "Too many numbers on line " + st.lineno());
                }
            }
        }
        att.resetModified(); //Not modified until something happens *after* reading it
        return att;
    }

    public void setAttFile(String attFile) {
        this.attFile = attFile;
    }

}
