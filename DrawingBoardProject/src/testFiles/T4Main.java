import drawingBoard.Window;
import java.io.*;

public class T4Main {
	public static void main(String[] args) throws IOException {
		Window w = Window.readSpecFromFile("dataFiles/T3Drawing.txt");
		w.display();
	}
}
