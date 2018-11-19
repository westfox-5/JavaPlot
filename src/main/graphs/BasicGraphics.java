package javaplot.graphs;

import javaplot.Drawable;
import javaplot.lista.Node;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Iterator;

class BasicGraphics {

    private static int xBase = 80;

    static void drawAxes(Graphics2D g, int xFinal, int yBase) {
        Line2D.Double asseY = new Line2D.Double(xBase, 0, xBase, yBase + 30);
        Line2D.Double asseX = new Line2D.Double(0, yBase, xFinal, yBase);
        g.draw(asseX);
        g.draw(asseY);
    }

    static <T extends Drawable> void drawLabelY(Graphics2D g, int maxHeight, T maxValue, int yBase) {
        int nValuesYLabel = 10;
        int offsetY = maxHeight / nValuesYLabel;
        int y = yBase;
        String text;

        for (int i = 0; i <= nValuesYLabel; i++, y -= offsetY) {
            text = String.format("%.2f", (i * (maxValue.getValue().doubleValue()) / 10));
            if (i != 0) {
                g.drawLine(xBase - 3, y, xBase + 3, y);
                g.drawString(text, xBase - (text.length() * 8), y + 5);
            }
        }
    }

    static <T extends Drawable> void drawLabelX(Graphics2D g, Iterator<Node<T>> iter, int offsetX, Font font, int yBase) {
        drawLabelX(g, iter, offsetX, 0, font, yBase);
    }

    static <T extends Drawable> void drawLabelX(Graphics2D g, Iterator<Node<T>> iter, int offsetX, int colWidth, Font font, int yBase) {
        AffineTransform af = new AffineTransform();
        af.rotate(Math.toRadians(90));
        Font rotatedFont = font.deriveFont(af);
        g.setFont(rotatedFont);

        int x = xBase + offsetX + colWidth / 2;
        Node<T> index;
        String text;

        while (iter.hasNext()) {
            index = iter.next();
            text = index.getLabel();
            g.drawString(text, x, yBase + 10);

            x += offsetX + colWidth;
        }
        g.dispose();
    }

    static void drawUnit(Graphics2D g, String unit, int yBase) {
        g.drawString(unit, xBase / 2 - (unit.length() / 2) * 8, yBase + 20);
    }
}
