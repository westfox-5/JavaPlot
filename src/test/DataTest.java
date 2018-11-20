package test;

import com.javaplot.graphs.DataTable;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DataTest {

    public static void main(String[] args) {

//      ################### DATAS ###################

        DataTable<ClassTest> dt = new DataTable<>("Prova grafico", "Metri");

        //inserts 1000 random values with alternated red|blue colors
        for (int i = 0; i < 10; i++) {
            dt.insert(new ClassTest(
                (new Random()).nextInt(10000),
                (i % 2 == 0) ? Color.blue : Color.red)
            );
        }

        //print dataset
        System.out.println(dt);


//      ################### CALLS ###################


        /* ------ Create a new frame ------ */

        dt.createDiagram(DataTable.graphicTypes.POINT_DIAGRAM);




        /* ------ The frame is passed as a parameter ------ */

        JFrame ciccio = new JFrame("Ciccio");
        // you can set your preferred size
        ciccio.setSize(new Dimension(800, 600));

        dt.createDiagram(DataTable.graphicTypes.LINE_DIAGRAM, ciccio);

        ciccio.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ciccio.setVisible(true);


        /* ------ Plot created in the JPanel passed as an argumenti ------ */

        JFrame pippo = new JFrame("Pippo");
        pippo.setSize(new Dimension(400, 400));

        pippo.setContentPane(
                dt.createDiagram(DataTable.graphicTypes.LINE_DIAGRAM, pippo.getSize())
        );

        // IMPORTANT
        pippo.pack();

        pippo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pippo.setVisible(true);

    }

}
