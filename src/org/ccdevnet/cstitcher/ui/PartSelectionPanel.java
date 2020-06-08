/*
 * PartSelectionPanel.java
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

package org.ccdevnet.cstitcher.ui;

import org.ccdevnet.cstitcher.libraries.BodySpriteLibrary;
import org.ccdevnet.cstitcher.util.NameSets;

import javax.swing.*;
import java.awt.*;

/**
 * This panel combines SinglePartViewPanels with dropdown
 * boxes than allow the user to select a part for moving around
 * and position the body parts according to direction and angle.
 */
public class PartSelectionPanel extends JPanel {

    private BodySpriteLibrary spriteLib;

    private SinglePartViewPanel[] partViews;
    private AngleComboBox[] angleBoxes;

    private JComboBox selDir;
    private JComboBox selHeadDir;
    private JComboBox selExpression;

    public PartSelectionPanel(BodySpriteLibrary spriteLib) {
        this.spriteLib = spriteLib;

        setBorder(BorderFactory.createTitledBorder("Parts"));

        //Generate sub-components
        partViews = new SinglePartViewPanel[14];
        angleBoxes = new AngleComboBox[14];
        for (int i = 0; i < 14; i++) {
            partViews[i] = new SinglePartViewPanel(spriteLib, i, 0);
            angleBoxes[i] = new AngleComboBox();
        }

        partViews[0].setSelected(true);

        String[] dirs = {"Right", "Left", "Front", "Back"};
        selDir = new JComboBox(dirs);
        selDir.setBorder(BorderFactory.createEtchedBorder());
        selDir.setSelectedItem(0);
        selHeadDir = new JComboBox(dirs);
        selHeadDir.setBorder(BorderFactory.createEtchedBorder());
        selHeadDir.setSelectedItem(0);

        String[] expressions = {"Normal", "Happy", "Sad", "Angry", "Scared", "Tired"};
        selExpression = new JComboBox(expressions);
        selExpression.setBorder(BorderFactory.createEtchedBorder());
        selExpression.setSelectedItem(spriteLib.getExpression());
        selExpression.setSelectedItem(0);

        //Place components onto the panel
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        Box sdp = new Box(BoxLayout.X_AXIS);
        sdp.add(new JLabel("Pose Direction:"));
        sdp.add(Box.createHorizontalStrut(5));
        sdp.add(selDir);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 0;
        add(sdp, c);

        Box shp = new Box(BoxLayout.X_AXIS);
        shp.add(new JLabel("Head Direction:"));
        shp.add(Box.createHorizontalStrut(5));
        shp.add(selHeadDir);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy = 1;
        add(shp, c);

        Box sxp = new Box(BoxLayout.X_AXIS);
        sxp.add(new JLabel("Expression"));
        sxp.add(Box.createHorizontalStrut(5));
        sxp.add(selExpression);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridx = 1;
        c.gridy = 1;
        add(sxp, c);

        boolean gx = false;
        int gy = 2;
        for (int i = 0; i < 14; i++) {
            c.anchor = GridBagConstraints.FIRST_LINE_START;
            if (gx == false) {
                c.gridx = 0;
                gx = true;
            } else {
                c.gridx = 1;
                gx = false;
            }
            c.gridy = gy;
            if (gx == false) gy++;

            JPanel ccl = new JPanel();
            ccl.setLayout(new GridBagLayout());

            GridBagConstraints spc = new GridBagConstraints();

            spc.gridx = 0;
            spc.gridy = 0;
            spc.gridheight = 2;
            spc.insets = new Insets(5, 0, 0, 5);
            spc.anchor = GridBagConstraints.LINE_START;
            ccl.add(partViews[i], spc);

            spc.gridx = 1;
            spc.gridy = 0;
            spc.gridheight = 1;
            spc.insets = new Insets(8, 0, 0, 0);
            ccl.add(new JLabel(NameSets.bodyPartName(i, true)), spc);

            spc.gridx = 1;
            spc.gridy = 1;
            spc.anchor = GridBagConstraints.FIRST_LINE_START;
            spc.insets = new Insets(0, 0, 0, 0);
            ccl.add(angleBoxes[i], spc);

            add(ccl, c);
        }

    }

    public void enableViews(boolean enable) {
        for (int i = 0; i < 14; i++) {
            partViews[i].setEnabled(enable);
        }
    }


    private static class AngleComboBox extends JComboBox {
        public String[] ANGLES = {"Down", "Level", "Up", "Up More"};

        public AngleComboBox() {
            super();
            setBorder(BorderFactory.createEtchedBorder());
            for (String t : ANGLES) {
                addItem(t);
            }
            setSelectedIndex(0);
        }
    }

}
