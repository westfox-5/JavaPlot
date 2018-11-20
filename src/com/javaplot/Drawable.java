package com.javaplot;


import java.awt.*;

/**
 * Interface that must be implemented by objects that are being used by DataTable
 *
 * @see javaplot.graphs.DataTable DataTable
 */
public interface Drawable {
    /**
     * Return the Number representation of a data.
     *
     * @return The Number value associated with the data.
     * @see Number Number
     */
    Number getValue();

    /**
     * Return the label associated with the data.
     *
     * @return The String representation of the data.
     * @see String String
     */
    String getLabel();

    /**
     * Return the Color associated with the data.
     *
     * @return The Color
     * @see Color Color
     */
    Color getColor();

}
