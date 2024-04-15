/*
    TCSS 360
    Nate Montgomery, Chase Dolan, Trae Claar
    Group Assignment 1 - Official Solution
 */

import java.util.Scanner;

/**
 * Class which reads Minesweeper fields as input from the user and outputs hints
 * for those fields.
 *
 * @author Chase Dolan
 * @author Nate Montgomery
 * @author Trae Claar
 * @version Spring 2023
 */
public final class HintWriter {

    /** The size of the buffer (both sides total) of a field array. */
    private static final int BUFFER_SIZE = 2;

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private HintWriter() { }

    /**
     * Main method which reads input, constructs the hints, and outputs the results.
     *
     * @param theArgs command line input
     */
    public static void main(final String[] theArgs) {
        final Scanner in = new Scanner(System.in);
        final StringBuilder sb = new StringBuilder();

        char[][] nextField = writeHintsForNext(in);
        int count = 0;
        while (nextField.length - BUFFER_SIZE > 0) {
            count++;
            sb.append(buildFieldString(count, nextField)).append('\n');
            nextField = writeHintsForNext(in);
        }

        System.out.print(sb.toString());

        in.close();
    }

    /**
     * Writes an array of hints for the next field in theIn. It is assumed that the
     * first row of the next field is the next line in theIn.
     *
     * @param theIn the input stream containing the next field
     * @return an array of hints based on the next field
     */
    public static char[][] writeHintsForNext(final Scanner theIn) {
        final char[][] field = createFieldArray(theIn);

        for (int i = 1; i < field.length - BUFFER_SIZE / 2 ; i++) {

            final String row = theIn.nextLine();
            for (int j = 1; j < field[0].length - BUFFER_SIZE / 2; j++) {
                if (row.charAt(j - 1) == '*') {
                    placeMine(field, i, j);
                } else if (field[i][j] == '\0') {
                    field[i][j] = '0';
                }
            }
        }

        return field;
    }

    /**
     * Creates an array to represent a field based on the dimensions provided as the
     * next line in the scanner.
     *
     * @param theIn the scanner containing the dimensions of the array
     * @return the field array
     */
    public static char[][] createFieldArray(final Scanner theIn) {
        final Scanner dim = new Scanner(theIn.nextLine());
        final char[][] arr = new char[dim.nextInt() + BUFFER_SIZE][dim.nextInt() + BUFFER_SIZE];
        dim.close();
        return arr;
    }

    /**
     * Places a mine in the specified position and increments surrounding squares.
     *
     * @param theField the field in which to place the mine
     * @param theRow the row in which to place the mine
     * @param theCol the column in which to place the mine
     */
    public static void placeMine(final char[][] theField, final int theRow,
                                  final int theCol) {

        incrementSquare(theField, theRow, theCol - 1);
        incrementSquare(theField, theRow - 1, theCol);
        incrementSquare(theField, theRow - 1, theCol - 1);
        incrementSquare(theField, theRow, theCol + 1);
        incrementSquare(theField, theRow + 1, theCol);
        incrementSquare(theField, theRow + 1, theCol + 1);
        incrementSquare(theField, theRow - 1, theCol + 1);
        incrementSquare(theField, theRow + 1, theCol - 1);

        theField[theRow][theCol] = '*';
    }

    /**
     * Increments the value of the specified square (or not) based on its contents.
     *
     * @param theField the field containing the square to increment
     * @param theRow the row containing the square
     * @param theCol the column containing the square
     */
    public static void incrementSquare(final char[][] theField, final int theRow,
                                        final int theCol) {

        final char square = theField[theRow][theCol];
        if (square == '\0') {
            theField[theRow][theCol] = '1';
        } else if (Character.isDigit(square)) {
            theField[theRow][theCol]++;
        }
    }

    /**
     * Creates a String representation of a field array.
     *
     * @param theN the number of the field
     * @param theField the char array representing the field
     * @return a String representation of the field
     */
    public static String buildFieldString(final int theN, final char[][] theField) {
        final StringBuilder sb = new StringBuilder();

        sb.append("Field #").append(theN).append(":\n");
        for (int i = 1; i < theField.length - BUFFER_SIZE / 2; i++) {
            for (int j = 1; j < theField[0].length - BUFFER_SIZE / 2; j++) {
                sb.append(theField[i][j]);
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}