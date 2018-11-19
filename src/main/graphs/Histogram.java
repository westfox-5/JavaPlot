package javaplot.graphs;

import javaplot.Drawable;
import javaplot.lista.DataList;
import javaplot.lista.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

class Histogram<T extends Drawable> extends JComponent {

    private DataList<T> dati;
    private String unit;
    private int
            colWidth = 40,
            maxValueY,
            offsetX = 30,
            yBase;
    private Font labelFont;


    Histogram(DataList<T> data, String unit, Dimension size) {
        this.dati = data;
        this.unit = unit;
        this.yBase = size.height - 50;
        this.labelFont = new Font(null, Font.PLAIN, 13);
    }


    @Override
    public void paintComponent(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;
        T maxValue;
        try {
            maxValue = dati.getMaxNodeFromValue().getData();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Iterator<Node<T>> iter = dati.iterator();
        String text;

        int x = 80 + offsetX;
        Node<T> index;

        while (iter.hasNext()) {
            index = iter.next();

            int h = ((yBase - 30) * index.getData().getValue().intValue()) / (maxValue.getValue().intValue()); //hmax : maxvalue = h : dati[i]
            int yAlta = yBase - h;

            if (maxValueY < h) maxValueY = h;

            g.setColor(Color.GRAY);
            Rectangle2D.Double barra = new Rectangle2D.Double(x, yAlta, colWidth, h);

            g.fill(barra);

            text = String.valueOf(index.getData().getValue().intValue());
            g.drawString(text, x - text.length() * 3 + colWidth / 2, yAlta - 2);

            x += colWidth + offsetX;

        }
        g.setFont(labelFont);

        BasicGraphics.drawAxes(g, x,yBase);

        g.setColor(Color.BLACK);

        BasicGraphics.drawUnit(g, unit,yBase);
        BasicGraphics.drawLabelY(g, maxValueY, maxValue,yBase);

        iter = dati.iterator();
        BasicGraphics.drawLabelX(g, iter, offsetX, colWidth, labelFont,yBase);

    }

    @Override
    public Dimension getPreferredSize() {
        try {
            String max = dati.getMaxNodeFromStringLength().getLabel();
            return new Dimension(
                    (dati.size() + 1) * (colWidth + offsetX) + colWidth / 2 - 3,
                    yBase + 10 + max.length() * 7
            );
        } catch (Exception e) {
            return null;
        }
    }
}