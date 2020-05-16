package drawingBoard.shapes;

import drawingBoard.Window;

public class Triangle extends Shape {
	int height, rInc, cInc;

	public Triangle(int rb, int cb, int height, int rInc, int cInc, char character) {
		super(rb, cb, character);
		this.height = height;
		this.rInc = rInc;
		this.cInc = cInc;
	}

	/**
	 * This method draws the Triangle to the specified window
	 * 
	 * @param window
	 * @return void
	 */

	public void draw(Window window) {
		Line line1 = null;
		Line line2 = null;
		Line line3 = null;
		if (rInc == 0) {
			line1 = new Line(rb, cb, height, -1, cInc, character);
			line2 = new Line(rb, cb, height, 1, cInc, character);
			line3 = new Line(rb - height, cb + cInc * height, 2 * height, 1, 0, character);

		} else if (cInc == 0) {
			line1 = new Line(rb, cb, height, rInc, -1, character);
			line2 = new Line(rb, cb, height, rInc, 1, character);
			line3 = new Line(rb + rInc * height, cb - height, 2 * height, 0, 1, character);

		}

		line1.draw(window);
		line2.draw(window);
		line3.draw(window);
	}

	/**
	 * This method generates the specification of the triangle, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the triangle
	 */

	public String generateSpec() {
		String spec = "\ntriangle\n" + rb + " " + cb + " " + height + " " + rInc + " " + cInc + "\n" + character
				+ "\n.";
		return spec;
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * Triangle in a readable format
	 * 
	 * @return String
	 */

	public String toString() {
		return "triangle" + " " + "(" + rb + "," + cb + ") " + "(" + height + ") " + "(" + rInc + "," + cInc + ") "
				+ "(" + character + ")";
	}

	/**
	 * This is a static method which takes the specification of Triangle as string
	 * input and returns the Triangle object.
	 * 
	 * @param spec
	 * @return Triangle
	 */

	public static Triangle createTriangle(String spec) {
		String[] properties = spec.split("\\n");
		char character = properties[2].charAt(0);
		String[] attributes = properties[1].split("\\s");
		int rb = Integer.parseInt(attributes[0]);
		int cb = Integer.parseInt(attributes[1]);
		int height = Integer.parseInt(attributes[2]);
		int rInc = Integer.parseInt(attributes[3]);
		int cInc = Integer.parseInt(attributes[4]);
		return new Triangle(rb, cb, height, rInc, cInc, character);
	}
}
