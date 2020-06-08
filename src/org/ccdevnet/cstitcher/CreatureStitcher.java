/*
 * CreatureStitcher.java
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

package org.ccdevnet.cstitcher;

import org.ccdevnet.cstitcher.ui.CreatureStitcherPanel;

import javax.swing.*;

/**
 * Startup class for Creature Stitcher.
 */
public class CreatureStitcher {

    public static final String VERSION = "0.1 alpha";

    public static void main(String[] args) {
        //Set the UIManager to use the system UI class
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) { /* We don't really care if it fails. */ }

        JFrame frame = new JFrame("Creature Stitcher v" + VERSION);

        frame.getContentPane().add(new CreatureStitcherPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
