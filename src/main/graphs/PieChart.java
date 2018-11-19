package javaplot.graphs;

import javaplot.Drawable;
import javaplot.lista.DataList;
import javaplot.lista.Node;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;


class PieChart<T extends Drawable> extends JComponent {

    private DataList<T> dati;
    private int yBase,
            offsetY = 18,
            raggio = 250,
            offsetX = 10,
            xLabel = (offsetX + raggio) * 2,
            dimRettangolo = 10,
            xBase;

    PieChart(DataList<T> data, Dimension size) {
        this.yBase = size.height - 50;
        this.xBase = size.width - 50;
        this.dati = data;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int xC = offsetX;
        int yC = offsetX;
        double angolo = 0, angoloPrima, tot = 0;

        Iterator<Node<T>> iter = dati.iterator();
        Node<T> index;

        g2.drawOval(xC, yC, raggio * 2, raggio * 2);


        while (iter.hasNext()) {
            index = iter.next();
            tot += index.getData().getValue().doubleValue();
        }

        iter = dati.iterator();
        xLabel = (offsetX + raggio) * 2;
        int yLabel = 17;

        while (iter.hasNext()) {
            index = iter.next();
            g2.setColor(index.getData().getColor());
            angoloPrima = angolo;
            angolo += ((360 * index.getData().getValue().doubleValue()) / tot);  //360 : tot = x : dati[i]


            g2.fillArc(xC, yC, raggio * 2, raggio * 2, (int) angoloPrima, (int) (360 - angoloPrima));

            g2.fillRect(xLabel + 5, yLabel - (offsetY) / 2, dimRettangolo, dimRettangolo);
            g2.setColor(Color.BLACK);
            g2.drawString(index.getLabel(), xLabel + 5 + dimRettangolo + 5, yLabel);
            yLabel += offsetY;
        }

        g2.setColor(Color.BLACK);
        g2.drawRect(xLabel - 5, 5, offsetX + maxLabelLength() * 9 + dimRettangolo, dati.size() * offsetY);


    }


    @Override
    public Dimension getPreferredSize() {
        try {
            int altezzaLegenda = dati.size() * offsetY;
            return new Dimension(
                    Math.max(xBase, xLabel + (offsetX + maxLabelLength() * 7 + dimRettangolo)),
                    Math.max(yBase, 10 + altezzaLegenda)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int maxLabelLength() {
        int maggiore = 0;
        Node<T> index;
        Iterator<Node<T>> iter = dati.iterator();
        while (iter.hasNext()) {
            index = iter.next();
            if (maggiore < index.getLabel().length()) {
                maggiore = index.getLabel().length();
            }
        }
        return maggiore;
    }

}