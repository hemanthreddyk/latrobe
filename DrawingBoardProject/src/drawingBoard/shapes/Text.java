package drawingBoard.shapes;

import drawingBoard.Window;

public class Text extends Shape {
	private int rInc, cInc;
	private String text;

	public Text(int rb, int cb, String text, int rInc, int cInc) {
		super(rb, cb, ' ');
		this.rInc = rInc;
		this.cInc = cInc;
		this.text = text;
	}

	/**
	 * This method draws the Text to the specified window
	 * 
	 * @param window
	 * @return void
	 */
	
	public void draw(Window w) {
		for (int i = 0; i < text.length(); i++) {
			int row = rb + (rInc * i);
			int col = cb + (cInc * i);
			w.setCell(row, col, text.charAt(i));
		}
	}

	/**
	 * This method generates the specification of the text, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the text
	 */
	
	public String generateSpec() {
		String spec = "\ntext\n" + rb + " " + cb + "\n" + text + "\n" + rInc + " " + cInc + "\n.";
		return spec;
	}

	/**
	 * This is override to the toString method which returns the specification of
	 * Text in a readable format
	 * 
	 * @return String
	 */
	
	public String toString() {
		return "text" + " " + "(" + rb + "," + cb + ") " + "(" + text + ") " + "(" + rInc + "," + cInc + ") ";
	}
	
	/**
	 * This is a static method which takes the specification of Text as string
	 * input and returns the Text object.
	 * 
	 * @param spec
	 * @return Text
	 */

	public static Text createText(String spec) {
		String[] properties = spec.split("\\n");
		String text = properties[2];
		String[] attributes = properties[1].split("\\s");
		int rb = Integer.parseInt(attributes[0]);
		int cb = Integer.parseInt(attributes[1]);
		attributes = properties[3].split("\\s");
		int rInc = Integer.parseInt(attributes[0]);
		int cInc = Integer.parseInt(attributes[1]);
		return new Text(rb, cb, text, rInc, cInc);
	}
}
