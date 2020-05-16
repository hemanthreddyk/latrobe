package drawingBoard.shapes;

import drawingBoard.Window;

public class Rectangle extends Shape {
	private int height, width;

	public Rectangle(int rb, int cb, int height, int width, char character) {
		super(rb, cb, character);
		this.height = height;
		this.width = width;
	}
	
	/**
	 * This method draws the Rectangle to the specified window
	 * 
	 * @param window
	 * @return void
	 */

	public void draw(Window window) {
		Line line1 = new Line(rb, cb, width, 0, 1, character);
		Line line2 = new Line(rb, cb, height, 1, 0, character);
		Line line3 = new Line(rb + height, cb + width, width, 0, -1, character);
		Line line4 = new Line(rb + height, cb + width, height, -1, 0, character);

		line1.draw(window);
		line2.draw(window);
		line3.draw(window);
		line4.draw(window);
	}
	
	/**
	 * This method generates the specification of the rectangle, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the rectangle
	 */

	public String generateSpec() {
		String spec = "\nrectangle\n" + rb + " " + cb + " " + height + " " + width + "\n" + character + "\n.";
		return spec;
	}

	/**
	 * This is a static method which takes the specification of rectangle as string
	 * input and returns the rectangle object.
	 * 
	 * @param spec
	 * @return Rectangle
	 */
	
	public static Rectangle createRectangle(String spec) {
		String[] properties = spec.split("\\n");
		char character = properties[2].charAt(0);
		String[] attributes = properties[1].split("\\s");
		int rb = Integer.parseInt(attributes[0]);
		int cb = Integer.parseInt(attributes[1]);
		int height = Integer.parseInt(attributes[2]);
		int width = Integer.parseInt(attributes[3]);
		return new Rectangle(rb, cb, height, width, character);
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * rectangle in a readable format
	 * 
	 * @return String
	 */
	
	public String toString() {
		return "rectangle" + " " + "(" + rb + "," + cb + ") " + "(" + height + "," + width + ") " + "(" + character
				+ ")";
	}
}
