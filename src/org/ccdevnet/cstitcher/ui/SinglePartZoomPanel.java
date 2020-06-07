package org.ccdevnet.cstitcher.ui;

import org.ccdevnet.cstitcher.libraries.BodySpriteLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class SinglePartZoomPanel extends JPanel {

    private BodySpriteLibrary spriteLib;
    private int part, pose;
    private Point attPoint;

    private Color backgroundColor;

    private boolean enabled;
    private boolean showPoint;

    public SinglePartZoomPanel(BodySpriteLibrary spriteLib, int part, int pose) {
        this.spriteLib = spriteLib;
        this.part = part;
        this.pose = pose;

        attPoint = new Point(0, 0);

        backgroundColor = Color.black;
        enabled = false;
        showPoint = false;

        setPreferredSize(new Dimension(250, 250));
        setBorder(BorderFactory.createRaisedBevelBorder());
    }

    public void paintComponent(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (!enabled) return;

        BufferedImage spr = spriteLib.getSprite(part, pose);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        int iw = spr.getWidth();
        int ih = spr.getHeight();
        double scale = ((double) w) / iw;
        double x = (w - scale * iw) / 2;
        double y = (h - scale * ih) / 2;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.scale(scale, scale);
        g2.drawRenderedImage(spr, at);

        if (showPoint) {
            int px = attPoint.x * (int) scale;
            int py = attPoint.y * (int) scale;
            g.setColor(Color.red);
            g.fillRect(px - 1, py - 1, 3, 3);
            g.drawOval(px - 6, py - 6, 12, 12);
            g.drawLine(px - 12, py, px + 12, py);
            g.drawLine(px, py - 12, px, py + 12);
        }
    }

    public void setSpriteLib(BodySpriteLibrary spriteLib) {
        this.spriteLib = spriteLib;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    public int getPose() {
        return pose;
    }

    public void setPose(int pose) {
        this.pose = pose;
    }

    public Point getAttPoint() {
        return attPoint;
    }

    public void setAttPoint(Point attPoint) {
        this.attPoint = attPoint;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setShowPoint(boolean showPoint) {
        this.showPoint = showPoint;
    }

}
