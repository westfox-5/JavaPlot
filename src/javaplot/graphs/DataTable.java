package javaplot.graphs;


import javaplot.Drawable;
import javaplot.list.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class that maintain the list of data and let create diagrams
 * based on that list.
 *
 * @param <T> Generics for the list. It must extends Drawable interface,
 *            so that each data has for sure a value, a label and a color.
 * @see Drawable Drawable
 */
public class DataTable<T extends Drawable> {

    /**
     * List of all the Diagram this class can handle.
     */
    public enum graphicTypes {
        HISTOGRAM, PIECHART, POINT_DIAGRAM, LINE_DIAGRAM
    }

    private String title, unitForData;
    private DataList<T> dati;

    /**
     * Empty constructor, nearly useless.
     */
    public DataTable() {
        this(null, null, null);
    }

    /**
     * Constructor that creates a DataTable instance with only
     * list and title.
     *
     * @param title The title of the diagram.
     * @param unit  The unit of measure of the data.
     */
    public DataTable(String title, String unit) {
        this(null, title, unit);
    }

    /**
     * Constructor that creates a DataTable instance with only
     * list and title.
     *
     * @param list  The instance of DataList containing Drawable elements.
     * @param title The title of the diagram.
     */
    public DataTable(DataList<T> list, String title) {
        this(list, title, null);
    }

    /**
     * Constructor that creates a DataTable instance with list of data, title
     * and unit of measure for the data.
     *
     * @param list  The instance of DataList containing Drawable elements.
     * @param title The title of the diagram.
     * @param unit  The unit of measure of the data.
     */
    public DataTable(DataList<T> list, String title, String unit) {
        if (list == null)
            this.setDataList(new DataList<>());
        else
            this.setDataList(list);
        this.setTitle(title);
        this.setUnit(unit);
    }

    /**
     * Setter for the unit of measure of data.
     *
     * @param unit The unit of measure of the data.
     */
    public void setUnit(String unit) {
        this.unitForData = unit;
    }

    /**
     * Setter for the title of the diagram.
     *
     * @param title The title of the diagram.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter for the list of data.
     *
     * @param list The DataList of data containing Drawable elements.
     */
    public void setDataList(DataList<T> list) {
        this.dati = list;
    }

    /**
     * Getter for the title.
     *
     * @return The title of the diagram.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for the unit of measure of data.
     *
     * @return The unit of the data.
     */
    public String getUnitForData() {
        return unitForData;
    }

    /**
     * Getter for the DataList.
     *
     * @return The DataList maintained by this class.
     * @see DataList DataList
     */
    public DataList<T> getDati() {
        return dati;
    }

    /**
     * Implementation for the String representation of the DataTable.
     *
     * @return A proper String that represents the DataTable.
     */
    @Override
    public String toString() {
        StringBuilder ris = new StringBuilder();
        ris.append(this.title);

        ris.append(" (").append(dati.size()).append("):\n");
        ris.append(dati.toString());

        return new String(ris);
    }

    /**
     * Insert an element in DataTable list.
     * It's the same of inserting the data directly into the list.
     *
     * @param data The data to insert. It must implements Drawable interface
     */
    public void insert(T data) {
        dati.insert(data);
    }

    /**
     * Private method that simply check if the list is empty.
     * It throws an exception, terminating the caller method.
     */
    private void check() {
        if (dati.size() == 0) {
            throw new RuntimeException("Empty list!");
        }
    }


    /**
     * Plot the selected type of diagram in a JScrollPane element and return it, so that it can be inserted in
     * the main frame.
     *
     * @param panelDimension The dimension of the panel in which the diagram will be plotted.
     * @param type           The type of diagram to plot.
     * @return The ScrollPanel containing the diagram.
     */
    public JScrollPane createDiagram(graphicTypes type, Dimension panelDimension) {
        check();

        JPanel panel = new JPanel();
        panel.setSize(panelDimension);
        JScrollPane scrPanel = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrPanel.setPreferredSize(panelDimension);

        switch (type) {
            case POINT_DIAGRAM:
                PointDiagram<T> pointDiagram = new PointDiagram<>(this.dati, this.unitForData, panel.getSize());

                scrPanel.setViewportView(panel);
                panel.add(pointDiagram);

                break;
            case LINE_DIAGRAM:
                LineDiagram<T> lineDiagram = new LineDiagram<>(this.dati, this.unitForData, panel.getSize());

                scrPanel.setViewportView(panel);
                panel.add(lineDiagram);

                break;
            case HISTOGRAM:
                Histogram<T> histogram = new Histogram<>(this.dati, this.unitForData, panel.getSize());

                scrPanel.setViewportView(panel);
                panel.add(histogram);

                break;
            case PIECHART:
                PieChart<T> pieChart = new PieChart<>(this.dati, panel.getSize());

                scrPanel.setViewportView(panel);
                panel.add(pieChart);

                break;
            default:
                break;
        }
        return scrPanel;
    }

    /**
     * Create a Frame (800x600) within the selected diagram.
     *
     * @param type The type of diagram to plot.
     */
    public void createDiagram(graphicTypes type) {
        JFrame frame = new JFrame(this.title);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        createDiagram(type, frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Plot the selected type of diagram in the frame given via argument.
     *
     * @param type  The type of diagram to plot.
     * @param frame The empty frame in which the diagram will be plotted.
     */
    public void createDiagram(graphicTypes type, JFrame frame) {
        check();

        JPanel container = new JPanel();
        JScrollPane scrPanel = new JScrollPane(container,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        scrPanel.setPreferredSize(frame.getSize());

        switch (type) {
            case POINT_DIAGRAM:
                PointDiagram<T> pointDiagram = new PointDiagram<>(this.dati, this.unitForData, frame.getSize());

                frame.setContentPane(scrPanel);
                scrPanel.setViewportView(container);
                container.add(pointDiagram);

                break;
            case LINE_DIAGRAM:
                LineDiagram<T> lineDiagram = new LineDiagram<>(this.dati, this.unitForData, frame.getSize());

                frame.setContentPane(scrPanel);
                scrPanel.setViewportView(container);
                container.add(lineDiagram);

                break;
            case HISTOGRAM:
                Histogram<T> histogram = new Histogram<>(this.dati, this.unitForData, frame.getSize());

                frame.setContentPane(scrPanel);
                scrPanel.setViewportView(container);
                container.add(histogram);

                break;
            case PIECHART:
                PieChart<T> pieChart = new PieChart<>(this.dati, frame.getSize());
                frame.setContentPane(scrPanel);
                scrPanel.setViewportView(container);
                container.add(pieChart);

                break;
            default:
                break;
        }
        frame.pack();
    }

    /**
     * Simple implementation of the mean calculation.
     *
     * @return The mean calculated on the DataList, returned as a double.
     */
    public double mean() {
        check();
        double sum = 0;
        for (Node<T> dato : dati) {
            sum += (double) dato.getData().getValue();
        }
        return sum / dati.size();
    }

    /**
     * Return the node with maximum value in the DataList.
     *
     * @return The maximum value of the DataList, returned as a Node<T> object.
     */
    public Node<T> max() {
        Node<T> max = dati.getHead();
        for (Node<T> dato : dati) {
            if ((double) dato.getData().getValue() > (double) max.getData().getValue())
                max = dato;
        }
        return max;
    }

    /**
     * Return the node with minimum value in the Datalist.
     *
     * @return The minimum value of the DataList, returned as a Node<T> object.
     */
    public Node<T> min() {
        Node<T> min = dati.getHead();
        for (Node<T> dato : dati) {
            if ((double) dato.getData().getValue() < (double) min.getData().getValue())
                min = dato;
        }
        return min;
    }
}
