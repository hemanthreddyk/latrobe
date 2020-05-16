package drawingBoard.shapes;

import drawingBoard.Window;

public class Circle extends Shape {
	private int rad;

	public Circle(int rb, int cb, int rad, char character) {
		super(rb, cb, character);
		this.rad = rad;
	}

	/**
	 * This method draws the circle to the specified window
	 * 
	 * @param window
	 * @return void
	 */

	public void draw(Window w) {
		for (int i = 0; i < 20; i++) {
			double angle = i * Math.PI / 10;
			int row = rb + (int) Math.round((rad * Math.cos(angle)));
			int col = cb + (int) Math.round((rad * Math.sin(angle)));
			w.setCell(row, col, character);
		}
	}

	/**
	 * This method generates the specification of the circle, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the circle
	 */

	public String generateSpec() {
		String spec = "\ncircle\n" + rb + " " + cb + " " + rad + "\n" + character + "\n.";
		return spec;
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * circle in a readable format
	 * 
	 * @return String
	 */

	public String toString() {
		return "circle" + " " + "(" + rb + "," + cb + ") " + "(" + rad + ") " + "(" + character + ")";
	}

	/**
	 * This is a static method which takes the specification of circle as string
	 * input and returns the circle object.
	 * 
	 * @param spec
	 * @return Circle
	 */

	public static Circle createCircle(String spec) {
		String[] properties = spec.split("\\n");
		char character = properties[2].charAt(0);
		String[] attributes = properties[1].split("\\s");
		int rb = Integer.parseInt(attributes[0]);
		int cb = Integer.parseInt(attributes[1]);
		int rad = Integer.parseInt(attributes[2]);
		return new Circle(rb, cb, rad, character);
	}
}
