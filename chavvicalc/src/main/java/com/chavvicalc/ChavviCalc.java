package com.chavvicalc;

import java.util.Scanner;

/*
 * chavvi calculator
 */
public class ChavviCalc {

  private static float valueA = 0.0f;
  private static float valueB = 0.0f;
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    Character command = '_';

    // loop until user quits
    while (command != 'q') {
      printMenu();
      System.out.print("Enter a command: ");
      command = menuGetCommand(scan);

      executeCommand(scan, command);
    }

    scan.close();
  }

  //
  // menu functions
  //
  private static void printMenuLine() {
    System.out.println(
      "----------------------------------------------------------"
    );
  }

  private static void printMenuCommand(Character command, String desc) {
    System.out.printf("%s\t%s\n", command, desc);
  }

  // prints the menu
  public static void printMenu() {
    printMenuLine();
    System.out.println("ChavviCalc");
    printMenuLine();
    System.out.printf("A = %.3f\t\tB = %.3f\n", valueA, valueB);
    printMenuLine();

    printMenuCommand('a', "Enter a value for A");
    printMenuCommand('b', "Enter a value for B");
    printMenuCommand('+', "Add");
    printMenuCommand('-', "Subtract");
    printMenuCommand('*', "Multiply");
    printMenuCommand('/', "Divide");
    printMenuCommand('c', "Clear");
    printMenuCommand('q', "Quit");

    printMenuLine();
  }

  // get first character from input
  private static Character menuGetCommand(Scanner scan) {
    Character command = '_';

    String rawInput = scan.nextLine();

    if (rawInput.length() > 0) {
      rawInput = rawInput.toLowerCase();
      command = rawInput.charAt(0);
    }

    return command;
  }

  // enter a float value
  private static void readFloat(Scanner scan, char variable) {
    System.out.printf("Enter a value for %c: ", variable);
    String input = scan.nextLine().trim();

    try {
      float val = Float.parseFloat(input);
      if (variable == 'A') {
        valueA = val;
      } else {
        valueB = val;
      }
    } catch (NumberFormatException e) {
        System.out.println("ERROR: \"" + input + "\" is not a valid number. Please enter a numeric value.");
    }
  }

  // calculator functions
  private static void executeCommand(Scanner scan, Character command) {
    switch (command) {
      case 'a':
        readFloat(scan, 'A');
        break;
      case 'b':
        readFloat(scan, 'B');
        break;
      case '+':
        valueA = valueA + valueB;
        break;
      case '-':
        valueA = valueA - valueB;
        break;
      case '*':
        valueA = valueA * valueB;
        break;
      case '/':
        if (valueB == 0.0f) {
          System.out.println("ERROR: Cannot divide by zero.");
        } else {
          valueA = valueA / valueB;
        }
        break;
      case 'c':
        valueA = 0.0f;
        valueB = 0.0f;
        break;
      case 'q':
        System.out.println("Thank you for using Chavvi Calc");
        break;
      default:
        System.out.println("ERROR: Unknown command");
    }
  }
}