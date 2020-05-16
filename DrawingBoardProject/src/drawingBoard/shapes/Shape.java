package drawingBoard.shapes;

import drawingBoard.Window;

public abstract class Shape {
	public int rb, cb;
	public char character;

	protected Shape(int rb, int cb, char character) {
		this.rb = rb;
		this.cb = cb;
		this.character = character;
	}

	/**
	 * This is a abstract method which draws the shape to the specified window
	 * 
	 * @param window
	 * @return void
	 */
	
	abstract public void draw(Window window);

	/**
	 * This is a abstract method which generates the specification of the shape, this is used while
	 * writing the specification to a file.
	 * 
	 * @return String This returns the specification of the shape
	 */
	
	abstract public String generateSpec();

	/**
	 * This is a abstract method which overrides the toString method which returns the specification of
	 * shape in a readable format
	 * 
	 * @return String
	 */
	
	abstract public String toString();
}
