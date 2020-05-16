package drawingBoard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import drawingBoard.shapes.*;

public class Window {
	public int rows, cols;
	public char border;
	private char[][] cells;
	private ArrayList<Shape> shapes;
	public int numberOfShapes = 0;

	public Window(int rows, int cols, char border) {
		this.rows = rows;
		this.cols = cols;
		this.border = border;
		cells = new char[rows + 2][cols + 2];
		shapes = new ArrayList<Shape>();
	}

	/**
	 * This method adds the specified shape to the window
	 *
	 * @return void
	 */

	public void addShape(Shape shape) {
		shapes.add(shape);
		numberOfShapes++;
	}

	/**
	 * This method removes the specified shape from the window
	 * 
	 * @param void
	 */

	public void removeShape(Shape shape) {
		shapes.remove(shape);
		numberOfShapes--;
	}

	public Shape getShape(int index) {
		return shapes.get(index);
	}

	/**
	 * This method displays the window along with all the shapes drawn in it
	 *
	 * @param void
	 */

	public void display() {
		drawShapesToWindow();

		for (int i = 0; i < rows + 2; i++) {
			for (int j = 0; j < cols + 2; j++) {
				System.out.print(cells[i][j] + " ");
			}
			System.out.print("\n\n");
		}
	}

	/**
	 * This method displays the index and details of the shapes present in the
	 * window
	 * 
	 * @param void
	 */

	public void printShapes() {
		int size = shapes.size();
		if (size > 0) {
			for (int i = 0; i < shapes.size(); i++) {
				System.out.println(i + ": " + shapes.get(i).toString());
			}
		} else {
			throw new Error("There are no shapes in the window");
		}
	}

	/**
	 * This is a setter method which assigns the cell in the window with the
	 * specified character
	 * 
	 * @param row This is the row number of the window
	 * @param col This is the column number of the window
	 * @param ch  This is the character which should be assigned to the cell
	 * @return void
	 */

	public void setCell(int row, int col, char ch) {
		cells[row][col] = ch;
	}

	/**
	 * This is a private method which draws all the shapes to the window
	 * 
	 * @return void
	 */
	private void drawShapesToWindow() {
		for (Shape shape : shapes) {
			shape.draw(this);
		}
	}

	/**
	 * This method initializes the window cells and adds border to the window
	 * 
	 * @return void
	 */

	public void setBorder() {
		for (int i = 0; i < rows + 2; i++) {
			for (int j = 0; j < cols + 2; j++) {
				char ch = ' ';
				if (i == 0 || i == rows + 1 || j == 0 || j == cols + 1) {
					ch = border;
				}
				setCell(i, j, ch);
			}
		}
	}

	/**
	 * This method writes the specifications of all the shapes drawn in the window
	 * to a file.
	 * 
	 * @param fileName This is the file name to which specification has to be
	 *                 written
	 * @return void
	 */

	public void writeSpecToFile(String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		fw.write(generateSpec());
		for (Shape shape : shapes) {
			fw.write(shape.generateSpec());
		}
		fw.close();
	}

	/**
	 * This method generates the specification of the window, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the window
	 */

	private String generateSpec() {
		return rows + " " + cols + "\n" + border + "\n.";
	}

	/**
	 * This method adds numbers to the borders to indicate the row and column
	 * indexes of the cells. This function should be called before drawing any
	 * shapes to the window.
	 * 
	 * @return void
	 */

	public void addGrid() {
		for (int i = 0; i < rows + 2; i++) {
			for (int j = 0; j < cols + 2; j++) {
				char ch;
				if ((i == 0 && j == 0) || (i == 0 && j == cols + 1) || (i == rows + 1 && j == 0)
						|| (i == rows + 1 && j == cols + 1)) {
					ch = '*';
				} else if (i == 0 || i == rows + 1) {
					ch = (char) (48 + (j % 10));
				} else if (j == 0 || j == cols + 1) {
					ch = (char) (48 + (i % 10));
				} else {
					ch = ' ';
				}
				setCell(i, j, ch);
			}
		}
	}

	/**
	 * This method re-renders the whole window and displays it.
	 * 
	 * @param void
	 */

	public void refreshImage() {
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= cols; j++) {
				cells[i][j] = ' ';
			}
		}
		display();
	}

	/**
	 * This method reads the text file, constructs and returns the Window object
	 * with all the drawings specified in the spec file.
	 * 
	 * @param fileName This is file name from which the specification has to be read
	 * @return Window This returns the window object which contains all the shapes
	 *         specified in the spec document
	 */

	public static Window readSpecFromFile(String fileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		String str = "";
		while (sc.hasNextLine()) {
			String curLine = sc.nextLine();
			if (curLine.equals(".")) {
				str = str.concat(curLine);
			} else {
				str = str.concat(curLine + "\n");
			}
		}
		String[] specs = str.split("\\.");
		Window w = Window.createWindowFromSpec(specs[0]);
		w.setBorder();
		for (String spec : specs) {
			if (spec.contains("line")) {
				w.addShape(Line.createLine(spec));
			} else if (spec.contains("circle")) {
				w.addShape(Circle.createCircle(spec));
			} else if (spec.contains("rectangle")) {
				w.addShape(Rectangle.createRectangle(spec));
			} else if (spec.contains("triangle")) {
				w.addShape(Triangle.createTriangle(spec));
			} else if (spec.contains("text")) {
				w.addShape(Text.createText(spec));
			}
		}
		return w;
	}

	/**
	 * This is a static method which takes the specification of window as string
	 * input and returns the window object.
	 * 
	 * @param spec
	 * @return Window
	 */

	public static Window createWindowFromSpec(String spec) {
		String[] properties = spec.split("\\n");
		char borderCharacter = properties[1].charAt(0);
		String[] attributes = properties[0].split("\\s");
		int height = Integer.parseInt(attributes[0]);
		int width = Integer.parseInt(attributes[1]);
		return new Window(height, width, borderCharacter);
	}
}
