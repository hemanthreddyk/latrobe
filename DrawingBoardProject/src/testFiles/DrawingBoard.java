import java.io.FileNotFoundException;
import java.util.Scanner;

import drawingBoard.Window;
import drawingBoard.shapes.*;

public class DrawingBoard {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		Window w = null;
		System.out.println("Enter the window file name (or NEW):");
		try {
			String fileName = sc.nextLine();
			if (fileName.equals("NEW")) {
				System.out.println("Enter number of rows, number of columns and character (separated by space):");
				String[] inputs = sc.nextLine().split("\\s");
				int rows = Integer.parseInt(inputs[0]);
				int cols = Integer.parseInt(inputs[1]);
				char border = inputs[2].charAt(0);
				w = new Window(rows, cols, border);
			} else {
				w = Window.readSpecFromFile("dataFiles/" + fileName);
			}

			w.addGrid();
			w.display();
			Line selectedLine = null;
			while (true) {
				try {
					System.out.println("Add Erase Select Write Quit\nUp Down Left Right + -");
					char option = sc.nextLine().charAt(0);
					switch (option) {
					case 'a':
						addLine(w);
						break;
					case 'e':
						removeLine(w);
						break;
					case 's':
						selectedLine = selectShape(w);
						break;
					case 'u':
					case 'd':
					case 'l':
					case 'r':
						moveLine(w, selectedLine, option);
						break;
					case 'w':
						writeToFile(w);
						break;
					case '+':
						increaseLineLength(w, selectedLine);
						break;
					case '-':
						decreaseLineLength(w, selectedLine);
						break;
					case 'q':
						System.out.println("Thank You!");
						System.exit(0);
					default:
						System.out.println("Invalid input");
					}
				} catch (Exception e) {
					System.out.println("Invalid input");
				} finally {
					w.refreshImage();
				}
			}

		} catch (FileNotFoundException fne) {
			System.out.println("Specified invalid file name");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static Line selectShape(Window w) {
		String errMsgPrefix = "Shape selection error: ";
		try {
			w.printShapes();
			int index = sc.nextInt();
			sc.nextLine();
			if (index > w.numberOfShapes - 1) {
				System.out.println(errMsgPrefix + "Invalid input");
				return null;
			}
			Shape s = w.getShape(index);
			if (s instanceof Line) {
				return (Line) s;
			}
			System.out.println(errMsgPrefix + "Invalid shape selected. You can only select a Line");
			return null;
		} catch (Exception e) {
			System.out.println(errMsgPrefix + e);
			return null;
		}
	}

	private static void removeLine(Window w) {
		Shape shape = selectShape(w);
		if (shape != null) {
			w.removeShape(shape);
		}
	}

	private static void addLine(Window w) {
		System.out.println("line rowBase colBase length rowIncrement colIncrement character");
		String errMsgPrefix = "Add line error: ";
		try {
			String input[] = sc.nextLine().split("\\s");
			int rb = Integer.parseInt(input[1]);
			int cb = Integer.parseInt(input[2]);
			int length = Integer.parseInt(input[3]);
			int rInc = Integer.parseInt(input[4]);
			int cInc = Integer.parseInt(input[5]);
			char character = input[6].charAt(0);
			if (rb < 1 || rb > w.rows || cb < 1 || cb > w.cols) {
				System.out.println(errMsgPrefix + "Base point is outside window boundaries");
				return;
			}
			if (!(rInc == -1 || rInc == 0 || rInc == 1) || !(cInc == -1 || cInc == 0 || cInc == 1)) {
				System.out.println(errMsgPrefix + "Invalid value for rowIncrement or colIncrement");
				return;
			}
			if (!Line.isValid(rb, cb, rInc, cInc, length, w)) {
				System.out.println(errMsgPrefix + "Line crosses the window boundaries");
				return;
			}
			Line l = new Line(rb, cb, length, rInc, cInc, character);
			w.addShape(l);
		} catch (Exception e) {
			System.out.println(errMsgPrefix + e);
		}

	}

	private static void moveLine(Window w, Line l, char direction) {
		String errMsgPrefix = "Move line error: ";
		if (l == null) {
			System.out.println(errMsgPrefix + "No shape selected");
			return;
		}
		try {
			l.moveLine(w, direction);
		} catch (Exception e) {
			System.out.println(errMsgPrefix + e);
		}
	}

	private static void writeToFile(Window w) {
		String errMsgPrefix = "Write to file error: ";
		try {
			System.out.print("File name: ");
			String fileName = sc.nextLine();
			w.writeSpecToFile("dataFiles/" + fileName);
		} catch (Exception e) {
			System.out.println(errMsgPrefix + e);
		}
	}

	private static void increaseLineLength(Window w, Line l) {
		String errMsgPrefix = "Increase size error: ";
		if (l == null) {
			System.out.println(errMsgPrefix + "No shape selected");
			return;
		}
		int newLength = l.length + 1;
		if (newLength > 0 && Line.isValid(l.rb, l.cb, l.rInc, l.cInc, newLength, w)) {
			l.length++;
		} else {
			System.out.println(errMsgPrefix + "Invalid move");
		}
	}

	private static void decreaseLineLength(Window w, Line l) {
		String errMsgPrefix = "Decrease size error: ";
		if (l == null) {
			System.out.println(errMsgPrefix + "No shape selected");
			return;
		}
		int newLength = l.length - 1;
		if (newLength == 0) {
			System.out.println(errMsgPrefix
					+ "Length of the line cannot be zero, if you wish to delete the line please use erase option instead");
			return;
		}
		if (newLength > 0 && Line.isValid(l.rb, l.cb, l.rInc, l.cInc, newLength, w)) {
			l.length--;
		} else {
			System.out.println(errMsgPrefix + "Invalid move");
		}
	}

}
