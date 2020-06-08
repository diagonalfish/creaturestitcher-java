/*
 * NudgePanel.java
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

import org.ccdevnet.cstitcher.iface.NudgeReceiver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel with 4 buttons for nudge left, right, up, down.
 */
public class NudgePanel extends JPanel implements ActionListener {

    private JButton left, right, up, down;
    private ArrayList<NudgeReceiver> nrs;

    public NudgePanel() {

        nrs = new ArrayList<>();

        setBorder(BorderFactory.createTitledBorder("Nudge"));

        int w = 40;
        int h = 25;

        left = new JButton("<");
        right = new JButton(">");
        up = new JButton("^");
        down = new JButton("v");

        left.setPreferredSize(new Dimension(w, h));
        right.setPreferredSize(new Dimension(w, h));
        up.setPreferredSize(new Dimension(w, h));
        down.setPreferredSize(new Dimension(w, h));

        left.addActionListener(this);
        right.addActionListener(this);
        up.addActionListener(this);
        down.addActionListener(this);

        setLayout(new GridLayout(3, 3));

        add(Box.createRigidArea(new Dimension(w, h)));
        add(up);
        add(Box.createRigidArea(new Dimension(w, h)));

        add(left);
        add(Box.createRigidArea(new Dimension(w, h)));
        add(right);

        add(Box.createRigidArea(new Dimension(w, h)));
        add(down);
        add(Box.createRigidArea(new Dimension(w, h)));
    }

    public void addNudgeReceiver(NudgeReceiver nr) {
        nrs.add(nr);
    }

    public void actionPerformed(ActionEvent a) {
        for (NudgeReceiver nud : nrs) {
            if (a.getSource() == left) {
                nud.nudgeLeft();
            } else if (a.getSource() == right) {
                nud.nudgeRight();
            } else if (a.getSource() == up) {
                nud.nudgeUp();
            } else if (a.getSource() == down) {
                nud.nudgeDown();
            }
        }
    }

}
