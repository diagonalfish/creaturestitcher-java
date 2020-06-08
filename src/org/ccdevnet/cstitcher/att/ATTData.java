/*
 * ATTData.java
 * Creature Stitcher
 *
 * Copyright (C) 2008-2020 Eric Goodwin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.ccdevnet.cstitcher.att;

import java.awt.*;

/**
 * A representation of an ATT file that can be modified on a point-by-point basis and
 * can generate string output for its contents.
 */
public class ATTData {

    private final int rows;
    private final int pointsPerRow;

    private final Point[][] data;

    private boolean modified;

    /**
     * Create a new ATTData object with the given number
     * of rows and points per row
     *
     * @param rows
     * @param pointsPerRow
     */
    public ATTData(int rows, int pointsPerRow) {
        this.rows = rows;
        this.pointsPerRow = pointsPerRow;
        data = new Point[rows][pointsPerRow];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < pointsPerRow; j++) {
                data[i][j] = new Point();
            }
        }
        modified = false;
    }

    public Point getPoint(int row, int point) {
        return data[row][point];
    }

    /**
     * Sets a point in the ATT file to a new value
     *
     * @param row      Row of the ATT data
     * @param point    Which point of the ATT data
     * @param newPoint
     */
    public void setPoint(int row, int point, Point newPoint) {
        data[row][point] = newPoint;
        modified = true;
    }

    /**
     * Sets the x coordinate of a point in the ATT file to a new value
     *
     * @param row
     * @param point
     * @param newX
     */
    public void setPointX(int row, int point, int newX) {
        data[row][point].x = newX;
        modified = true;
    }

    /**
     * Sets the y coordinate of a point in the ATT file to a new value
     *
     * @param row
     * @param point
     * @param newY
     */
    public void setPointY(int row, int point, int newY) {
        data[row][point].y = newY;
        modified = true;
    }

    /**
     * Gives the string representation of this ATT data; i.e., the data that should
     * be written to a file when it is saved.
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < pointsPerRow; j++) {
                output = output.concat(data[i][j].x + " " + data[i][j].y);
                if (j < (pointsPerRow - 1)) output = output.concat(" ");
            }
            if (i < (rows - 1)) output = output.concat("\n");
        }
        return output;
    }

    /**
     * Has this ATT data been changed?
     *
     * @return true or false
     */
    public boolean isModified() {
        return modified;
    }

    /**
     * Reset the modification value.  Called by ATTFileReader after data is added
     * from a file.
     */
    public void resetModified() {
        modified = false;
    }

}
