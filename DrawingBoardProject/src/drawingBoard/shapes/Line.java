package drawingBoard.shapes;

import drawingBoard.Window;

public class Line extends Shape {
	public int length, rInc, cInc;

	public Line(int rb, int cb, int length, int rInc, int cInc, char character) {
		super(rb, cb, character);
		this.length = length;
		this.rInc = rInc;
		this.cInc = cInc;
	}

	/**
	 * This method draws the Line to the specified window
	 * 
	 * @param window
	 * @return void
	 */
	
	public void draw(Window w) {
		for (int i = 0; i <= this.length; i++) {
			int row = rb + (rInc * i);
			int col = cb + (cInc * i);
			w.setCell(row, col, character);
		}
	}

	/**
	 * This method generates the specification of the line, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the line
	 */

	public String generateSpec() {
		String spec = "\nline\n" + rb + " " + cb + " " + length + " " + rInc + " " + cInc + "\n" + character + "\n.";
		return spec;
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * line in a readable format
	 * 
	 * @return String
	 */
	
	public String toString() {
		return "line" + " " + "(" + rb + "," + cb + ") " + "(" + length + ") " + "(" + rInc + "," + cInc + ") " + "("
				+ character + ")";
	}

	public void moveLine(Window w, char direction) throws Exception {
		int x1 = rb, y1 = cb;

		switch (direction) {
		case 'u':
			x1--;
			break;
		case 'd':
			x1++;
			break;
		case 'l':
			y1--;
			break;
		case 'r':
			y1++;
			break;
		}
		if (Line.isValid(x1, y1, rInc, cInc, length, w)) {
			rb = x1;
			cb = y1;
			return;
		}

		throw new Exception("This action will move the line out of the window boundaries");
	}

	public static boolean isValid(int x1, int y1, int rInc, int cInc, int length, Window w) {
		int rows = w.rows, cols = w.cols;
		int x2 = x1 + (rInc * length);
		int y2 = y1 + (cInc * length);

		if (x1 > rows || x1 < 1 || x2 > rows || x2 < 1 || y1 > cols || y1 < 1 || y2 > cols || y2 < 1) {
			return false;
		}
		return true;
	}

	/**
	 * This is a static method which takes the specification of line as string input
	 * and returns the line object.
	 * 
	 * @param spec
	 * @return Line
	 */

	public static Line createLine(String spec) {
		String[] properties = spec.split("\\n");
		char character = properties[2].charAt(0);
		String[] attributes = properties[1].split("\\s");
		int rb = Integer.parseInt(attributes[0]);
		int cb = Integer.parseInt(attributes[1]);
		int length = Integer.parseInt(attributes[2]);
		int rInc = Integer.parseInt(attributes[3]);
		int cInc = Integer.parseInt(attributes[4]);
		return new Line(rb, cb, length, rInc, cInc, character);
	}
}
