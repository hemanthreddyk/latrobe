package drawingBoard.shapes;

import drawingBoard.Window;

public class Oval extends Shape {
	private int height, width;

	public Oval(int rb, int cb, int height, int width, char character) {
		super(rb, cb, character);
		this.height = height;
		this.width = width;
	}

	/**
	 * This method draws the Oval to the specified window
	 * 
	 * @param window
	 * @return void
	 */
	
	public void draw(Window w) {
		for (int i = 0; i < 20; i++) {
			double angle = i * Math.PI / 10;
			int row = rb + (int) Math.round((height * Math.cos(angle)));
			int col = cb + (int) Math.round((width * Math.sin(angle)));
			w.setCell(row, col, character);
		}
	}

	/**
	 * This method generates the specification of the oval, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the oval
	 */
	
	public String generateSpec() {
		String spec = "\noval\n" + rb + " " + cb + " " + height + " " + width + "\n" + character + "\n.";
		return spec;
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * oval in a readable format
	 * 
	 * @return String
	 */
	
	public String toString() {
		return "oval" + " " + "(" + rb + "," + cb + ") " + "(" + height + "," + width + ") " + "(" + character + ")";
	}


//	public static Oval createOval(String spec) {
//		String[] properties = spec.split("\\n");
//		char character = properties[2].charAt(0);
//		String[] attributes = properties[1].split("\\s");
//		int rb = Integer.parseInt(attributes[0]);
//		int cb = Integer.parseInt(attributes[1]);
//		int rad = Integer.parseInt(attributes[2]);
//		return new Oval(rb, cb, rad, character);
//	}
}

