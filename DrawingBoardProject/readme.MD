                                       
                                       
                                       Project Folder Structure
|------------------------------------------------------------------------------------------------|
|                                                                                                |
|                                                                                                |
|                                                                     |--> Circle.java           |
|                                                                     |--> Line.java             |
|                                                                     |--> Oval.java             |
|                                                       |-->shapes--> |--> Rectangle.java        |
|                                                       |             |--> Shape.java            |
|                                   |-->drawingBoard--> |             |--> Text.java             |
|                                   |                   |             |--> Triangle.java         |
|                                   |                   |                                        |
|                                   |                   |--> Window.java                         |
|                        |-->src--> |                                                            |
|                        |          |                                                            |
|                        |          |                |--> DrawingBoard.java                      |
|                        |          |                |--> EightLines.java                        |
|                        |          |-->testFiles--> |--> HouseForSale.java                      |
| DrawingBoardProject--> |                           |--> T3Main.java                            |
|                        |                           |--> T4Main.java                            |
|                        |                           |--> T7Tester.java                          |
|                        |                                                                       |
|                        |-->dataFiles--> SpecIn.txt                                             |
|                        |                                                                       |
|                        |-->readMe.MD                                                           |
|                                                                                                |
|------------------------------------------------------------------------------------------------|

Steps for compilation:
1. Create the exact same folder structure with exact folderNames.
2. From 'DrawingBoardProject' folder execute following commands
3. javac -d bin -sourcepath src src/drawingBoard/Window.java src/testFiles/*


Steps for execution:
1. From 'DrawingBoardProject' folder execute following command in the following format
   java -cp bin [className]
    
  Eg: java -cp bin EightLines


For the files generated during execution check the dataFiles folder


Commands
  1. java -cp bin EightLines
  2. java -cp bin HouseForSale
  3. java -cp bin T3Main
  4. java -cp bin T4Main
  5. java -cp bin DrawingBoard
  6. java -cp bin T7Tester
