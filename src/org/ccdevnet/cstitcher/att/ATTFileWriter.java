/*
 * ATTFileWriter.java - Created Jul 26, 2008 6:11:34 PM
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

import org.ccdevnet.cstitcher.exceptions.ATTFileWriteException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Object that can write ATTData objects back to .att files.
 */
public class ATTFileWriter {

    private String attFile;

    /**
     * Create a new ATTFileWriter that will write to the given file.
     */
    public ATTFileWriter(String attFile) {
        this.attFile = attFile;
    }

    /**
     * Create an enpty ATTFileWriter.  You must provide a path via
     * setAttFile before saving.
     */
    public ATTFileWriter() {
        this.attFile = "";
    }

    public void writeData(ATTData attData) throws ATTFileWriteException {
        try {
            FileWriter fw = new FileWriter(new File(attFile), false);
            fw.write(attData.toString());
            fw.flush();
            fw.close();
        } catch (IOException ioe) {
            throw new ATTFileWriteException("IOException: " + ioe.getMessage());
        }
    }

    public void setAttFile(String attFile) {
        this.attFile = attFile;
    }

}
