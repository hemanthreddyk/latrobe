import drawingBoard.Window;
import drawingBoard.shapes.*;

public class EightLines {
	public static void main(String[] args) {
		Window w = new Window(20, 20, '*');
		w.setBorder();
		int row = 10, column = 10, length = 5;
		Line line = new Line(row, column, length, 0, 1, '1');
		w.addShape(line);
		line = new Line(row, column, length, 1, 1, '2');
		w.addShape(line);
		line = new Line(row, column, length, 1, 0, '3');
		w.addShape(line);
		line = new Line(row, column, length, 1, -1, '4');
		w.addShape(line);
		line = new Line(row, column, length, 0, -1, '5');
		w.addShape(line);
		line = new Line(row, column, length, -1, -1, '6');
		w.addShape(line);
		line = new Line(row, column, length, -1, 0, '7');
		w.addShape(line);
		line = new Line(row, column, length, -1, 1, '8');
		w.addShape(line);
		w.display();
	}
}
