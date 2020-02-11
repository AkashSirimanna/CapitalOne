# Capital One
# Akash Sirimanna


A proposed solution to the Capital One Summer 2020 SWE Internship

Requirements:
	- Java 8

How to run (The program was built in a linux environment)
1. Make sure you meet the requirements
2. Make sure the input file is ready to go (hopefully with one of the supported extensions)
3. Enter the following code

```
javac CodeProcessor.java
java CodeProcessor
```

4. Enjoy Your Information About Your Code!


# How it works

The project leverages a hashmap of filename to comment/block symbols.
It then parses the file of code, line by line, character by character and looks for block comments, comments, TODOS etc...
It uses the hashmap's ability to give us the escape characters for different file extensions in O(1) time to be able to complete this project in O(N). 

# Edge Cases

There are many edge cases that I had to take into consideration. Below are some of the corner cases I considered
	- Block comment starting and ending in the same line
	- TODOs within a Block
	- Comments, Block Comments and TODOS within single quotes
	- Comments, Block Comments and TODOS within double quotes

