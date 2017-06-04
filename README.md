# TimeManipulation

## Overview
Without using any built-in date or time functions, write a function or method that accepts two mandatory arguments: the first argument is a 12-hour time string with the format "[H]H:MM {AM|PM}", and the second argument is a (signed) integer. The second argument is the number of minutes to add to the time of day represented by the first argument. The return value should be a string of the same format as the first argument. For example, AddMinutes("9:13 AM", 200) would return "12:33 PM”.


## Installation
This program can be run in Android Studio or Compiled/Executed from an online Java Compiler.

### Run in Online Java Compiler
1. Clone this repository
2. Unzip the downloaded file
3. Copy the java code found in TimeManipulation.java
4. Using a web browser, navigate to https://www.compilejava.net/
5. Remove the default code present and paste the code from TimeManipulation.java into main window 
then click compile



### Run in Android Studio
1. Clone this repository
2. Unzip the downloaded file
3. Open the TimeManipulation-master project using Android Studio
4. Click on the drop down to the left of the run button. Click Edit Configurations...
5. In the new window, click on the plus sign at the top left of the window and select "Application"
6. A new application configuration should appear, enter the following details
	```
	Main class: com.example.TimeManipulation
	Use classpath of module: assignment
	```
7. Click "OK"
8. Click on the run button now runs the file


## Highlights
* Main method for the Android Studio program is located here
```
 TimeManipulation-master -> assignment -> src -> main -> java -> com - example -> TimeManipulation.java
 ```

 * Test files for the program are located here
```
 TimeManipulation-master -> assignment -> src -> test ->java -> com - example -> TimeManipulationTest.java
 ```
