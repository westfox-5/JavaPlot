package javaplot.graphs;

import javaplot.Drawable;
import javaplot.lista.DataList;
import javaplot.lista.Node;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

class PointDiagram<T extends Drawable> extends JComponent {

    private DataList<T> dati;
    private String unit;
    private int
            maxValueY,
            offsetX = 50,
            yBase,
            xBase = 80;
    private Font labelFont;


    PointDiagram(DataList<T> data, String unitForData, Dimension size) {
        this.dati = data;
        this.yBase = size.height - 50;
        this.unit = unitForData;

        this.labelFont = new Font(null, Font.PLAIN, 13);

    }

    @Override
    public void paintComponent(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;

        T maxValue;
        try {
            maxValue = dati.getMaxNodeFromValue().getData();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String text;
        int raggio = 5; 
        Iterator<Node<T>> iter = dati.iterator();
        Node<T> index;

        //disegna i punti
        g.setColor(Color.GRAY);
        int x = xBase + offsetX;

        while (iter.hasNext()) {
            index = iter.next();

            int h = ((yBase - 30) * index.getData().getValue().intValue()) / (maxValue.getValue().intValue());// hmax : maxvalue = h : dati[i]
            int yAlta = yBase - h;

            if (maxValueY < h) maxValueY = h;

            g.fillOval(x - raggio, yAlta - raggio, raggio * 2, raggio * 2);

            text = String.valueOf(index.getData().getValue().intValue());
            g.drawString(text, x - text.length() * 3, yAlta - raggio);

            x += offsetX;
        }

        iter = dati.iterator();

        g.setFont(labelFont);
        g.setColor(Color.BLACK);

        BasicGraphics.drawUnit(g, unit, yBase);

        BasicGraphics.drawLabelY(g, maxValueY, maxValue, yBase);

        BasicGraphics.drawAxes(g, x, yBase);
        g.setColor(Color.BLACK);

        BasicGraphics.drawLabelX(g, iter, offsetX, labelFont, yBase);

    }

    @Override
    public Dimension getPreferredSize() {
        try {
            String max = dati.getMaxNodeFromStringLength().getLabel();
            return new Dimension(
                    ((dati.size() + 1) * offsetX) + 50,
                    yBase + 10 + max.length() * 7
            );
        } catch (Exception e) {
            return null;
        }
    }
}