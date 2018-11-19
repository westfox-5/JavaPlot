package javaplot.graphs;

import javaplot.Drawable;
import javaplot.lista.DataList;
import javaplot.lista.Node;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

class LineDiagram<T extends Drawable> extends JComponent {

    private DataList<T> dati;
    private String unit;
    private int
            maxValueY,
            offsetX = 30,
            yBase;
    private Font labelFont;


    LineDiagram(DataList<T> data, String unit, Dimension size) {
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

        String text;
        int raggio = 2,
                xPrima = 80,
                yPrima = yBase;

        Iterator<Node<T>> iter = dati.iterator();
        Node<T> index;

        //disegna i punti
        int x = 80 + offsetX;

        while (iter.hasNext()) {
            index = iter.next();

            int h = ((yBase - 30) * index.getData().getValue().intValue()) / (maxValue.getValue().intValue());// hmax : maxvalue = h : dati[i]
            int yAlta = yBase - h;

            if (maxValueY < h) maxValueY = h;

            g.setColor(Color.GRAY);
            g.drawLine(xPrima, yPrima, x, yAlta);

            g.setColor(Color.BLACK);
            g.fillOval(x - raggio, yAlta - raggio, raggio * 2, raggio * 2);


            xPrima = x;
            yPrima = yAlta;

            text = String.valueOf(index.getData().getValue().intValue());
            g.drawString(text, x - text.length() * 3, yAlta - raggio);

            x += offsetX;
        }

        BasicGraphics.drawAxes(g, x, yBase);

        g.setFont(labelFont);
        g.setColor(Color.BLACK);

        BasicGraphics.drawUnit(g, unit, yBase);
        BasicGraphics.drawLabelY(g, maxValueY, maxValue, yBase);

        g.setColor(Color.BLACK);
        iter = dati.iterator();

        BasicGraphics.drawLabelX(g, iter, offsetX, labelFont, yBase);
    }

    @Override
    public Dimension getPreferredSize() {
        try {
            String max = dati.getMaxNodeFromStringLength().getLabel();
            return new Dimension(
                    ((dati.size() + 1) * offsetX) + 80,
                    yBase + 10 + max.length() * 7
            );
        } catch (Exception e) {
            return null;
        }
    }

}
