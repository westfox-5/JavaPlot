rm -r ./classes
mkdir classes

javac -classpath ./src -d ./classes src/test/Test.java 
javac -classpath ./src -d ./classes src/test/ClassTest.java 
javac -classpath ./src -d ./classes src/javaplot/Drawable.java
javac -classpath ./src -d ./classes src/javaplot/graphs/BasicGraphics.java
javac -classpath ./src -d ./classes src/javaplot/graphs/DataTable.java
javac -classpath ./src -d ./classes src/javaplot/graphs/Histogram.java
javac -classpath ./src -d ./classes src/javaplot/graphs/LineDiagram.java
javac -classpath ./src -d ./classes src/javaplot/graphs/PieChart.java
javac -classpath ./src -d ./classes src/javaplot/graphs/PointDiagram.java
javac -classpath ./src -d ./classes src/javaplot/list/Node.java
javac -classpath ./src -d ./classes src/javaplot/list/DataList.java
javac -classpath ./src -d ./classes src/javaplot/list/ListException.java


java classes/test/Test