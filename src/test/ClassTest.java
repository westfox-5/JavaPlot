package test;

import javaplot.Drawable;

import java.awt.*;

public class ClassTest implements Drawable {

    private Integer i;
    private String label;
    private Color color;

    public ClassTest(Integer i, String label, Color color){
        this.i = i;
        this.label = label;
        this.color = color;
    }

    public ClassTest(Integer i, Color color){
        this(i,null, color);
    }


    @Override
    public String getLabel(){
        if(label == null) return i.toString();
        else return label;
    }

    @Override
    public Number getValue() {
        return i;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
