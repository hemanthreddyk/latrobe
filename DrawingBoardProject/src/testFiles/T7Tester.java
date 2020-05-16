
import java.io.*;

import drawingBoard.Window;
import drawingBoard.shapes.Oval;

public class T7Tester {
	public static void main(String[] args) throws IOException {
		// Create a window
		Window w = new Window(20, 30, '*');
		// Add grid to window
		w.addGrid();
		
		Oval oval1 = new Oval(10, 7, 6, 3, '+');
		w.addShape(oval1);
		

		Oval oval2 = new Oval(10, 20, 3, 7, '+');
		w.addShape(oval2);

		w.display();
	}
}
