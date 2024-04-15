/*
    TCSS 360
    Nate Montgomery, Chase Dolan, Trae Claar
    Group Assignment 1 - Official Solution Tests
 */

//import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Unit tests for the HintWriter class.
 *
 * @author Chase Dolan
 * @author Nate Montgomery
 * @author Trae Claar
 * @version Spring 2023
 */
public class HintWriterTests {
    
    /** Private constructor to prevent instantiation. */
    public HintWriterTests() {}

    /** Test writeHintsForNext with a 100x100 field full of mines. */
    @Test
    public void testWriteHintsForNextAllMines100x100() {
        final Scanner input = new Scanner(InputGenerator.generateField(100, 100, 100));
        final char[][] expected = new char[102][102];

        expected[0][0] = '1';
        expected[0][101] = '1';
        expected[101][0] = '1';
        expected[101][101] = '1';
        expected[0][1] = '2';
        expected[1][0] = '2';
        expected[0][100] = '2';
        expected[100][0] = '2';
        expected[1][101] = '2';
        expected[101][1] = '2';
        expected[100][101] = '2';
        expected[101][100] = '2';
        for (int i = 2; i < 100; i++) {
            expected[0][i] = '3';
            expected[101][i] = '3';
            expected[i][0] = '3';
            expected[i][101] = '3';
        }

        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                expected[i][j] = '*';
            }
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with an empty 100x100 field. */
    @Test
    public void testWriteHintsForNextEmpty100x100() {
        final Scanner input = new Scanner(InputGenerator.generateField(100, 100, 0));
        final char[][] expected = new char[102][102];
        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                expected[i][j] = '0';
            }
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with a 100x1 field full of mines. */
    @Test
    public void testWriteHintsForNextAllMines100x1() {
        final Scanner input = new Scanner(InputGenerator.generateField(100, 1, 100));
        final char[][] expected = new char[102][3];
        for (int j = 0; j < 3; j++) {
            expected[0][j] = '1';
            expected[101][j] = '1';

            expected[1][j] = '2';
            expected[100][j] = '2';
        }
        for (int i = 2; i < 100; i++) {
            expected[i][0] = '3';
            expected[i][2] = '3';
        }
        for (int i = 1; i < 101; i++) {
            expected[i][1] = '*';
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with an empty 100x1 field. */
    @Test
    public void testWriteHintsForNextEmpty100x1() {
        final Scanner input = new Scanner(InputGenerator.generateField(100, 1, 0));
        final char[][] expected = new char[102][3];
        for (int i = 1; i < 101; i++) {
            expected[i][1] = '0';
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with a 1x100 field full of mines. */
    @Test
    public void testWriteHintsForNextAllMines1x100() {
        final Scanner input = new Scanner(InputGenerator.generateField(1, 100, 100));
        final char[][] expected = new char[3][102];
        for (int i = 0; i < 3; i++) {
            expected[i][0] = '1';
            expected[i][101] = '1';

            expected[i][1] = '2';
            expected[i][100] = '2';
        }
        for (int j = 2; j < 100; j++) {
            expected[0][j] = '3';
            expected[2][j] = '3';
        }
        for (int j = 1; j < 101; j++) {
            expected[1][j] = '*';
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with an empty 1x100 field. */
    @Test
    public void testWriteHintsForNextEmpty1x100() {
        final Scanner input = new Scanner(InputGenerator.generateField(1, 100, 0));
        final char[][] expected = new char[3][102];
        for (int j = 1; j < 101; j++) {
            expected[1][j] = '0';
        }

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with a 1x1 field with a mine. */
    @Test
    public void testWriteHintsForNextAllMines1x1() {
        final Scanner input = new Scanner(InputGenerator.generateField(1, 1, 100));
        final char[][] expected = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                expected[i][j] = '1';
            }
        }
        expected[1][1] = '*';

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext with an empty 1x1 field. */
    @Test
    public void testWriteHintsForNextEmpty1x1() {
        final Scanner input = new Scanner(InputGenerator.generateField(1, 1, 0));
        final char[][] expected = new char[3][3];
        expected[1][1] = '0';

        assertArrayEquals(expected, HintWriter.writeHintsForNext(input),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext where 25% of the field is filled with mines */
    @Test
    public void testWriteHintsForNext25PercentFilled4x4() {
        /**
         *   Input Field
         *   . . * .
         *   . . * .
         *   . * . *
         *   . . . .
         *
         *   Expected Hint (Without Buffer Area)
         *   0 2 * 2
         *   1 3 * 3
         *   1 * 3 *
         *   1 1 2 1
         */
        final String inputString = "4 4\n..*.\n..*.\n.*.*\n....";
        final char[][] expected = new char[6][6];
        expected[0] = new char[] {'\0', '\0', '1', '1', '1', '\0'};
        expected[1] = new char[] {'\0', '0', '2', '*', '2', '\0'};
        expected[2] = new char[] {'\0', '1', '3', '*', '3', '1'};
        expected[3] = new char[] {'\0', '1', '*', '3', '*', '1'};
        expected[4] = new char[] {'\0', '1', '1', '2', '1', '1'};
        expected[5] = new char[] {'\0', '\0', '\0', '\0', '\0', '\0'};

        char[][] result = HintWriter.writeHintsForNext(new Scanner(inputString));
        assertArrayEquals(expected, HintWriter.writeHintsForNext(new Scanner(inputString)),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext where half of the field is filled with mines */
    @Test
    public void testWriteHintsForNextHalfFilled4x4() {
        /**
         *   Input Field
         *   * . * .
         *   . . * *
         *   . . . *
         *   . * * *
         *
         *   Expected Hint
         *   * 3 * 3
         *   1 3 * *
         *   1 2 3 *
         *   1 * * *
         */
        final String inputString = "4 4\n*.*.\n..**\n...*\n.***";
        final char[][] expected = new char[6][6];
        expected[0] = new char[] {'1', '1', '2', '1', '1', '\0'};
        expected[1] = new char[] {'1', '*', '3', '*', '3', '1'};
        expected[2] = new char[] {'1', '1', '3', '*', '*', '2'};
        expected[3] = new char[] {'\0', '1', '3', '6', '*', '3'};
        expected[4] = new char[] {'\0', '1', '*', '*', '*', '2'};
        expected[5] = new char[] {'\0', '1', '2', '3', '2', '1'};

        char[][] result = HintWriter.writeHintsForNext(new Scanner(inputString));
        assertArrayEquals(expected, HintWriter.writeHintsForNext(new Scanner(inputString)),
                "Hints do not match expected result.");
    }

    /** Test writeHintsForNext where 75% of the field is filled with mines */
    @Test
    public void testWriteHintsForNext75PercentFilled4x4() {
        /**
         *   Input Field
         *   * * * *
         *   * . * *
         *   . * . *
         *   * * . *
         *
         *   Expected Hint
         *   * * * *
         *   * 6 * *
         *   4 * 6 *
         *   * * 4 *
         */
        final String inputString = "4 4\n****\n*.**\n.*.*\n**.*";
        final char[][] expected = new char[6][6];
        expected[0] = new char[] {'1', '2', '3', '3', '2', '1'};
        expected[1] = new char[] {'2', '*', '*', '*', '*', '2'};
        expected[2] = new char[] {'2', '*', '6', '*', '*', '3'};
        expected[3] = new char[] {'2', '4', '*', '6', '*', '3'};
        expected[4] = new char[] {'1', '*', '*', '4', '*', '2'};
        expected[5] = new char[] {'1', '2', '2', '2', '1', '1'};

        char[][] result = HintWriter.writeHintsForNext(new Scanner(inputString));
        assertArrayEquals(expected, HintWriter.writeHintsForNext(new Scanner(inputString)),
                "Hints do not match expected result.");
    }


    /** Test createFieldArray with a generated 2x2 field. */
    @Test
    public void testCreateFieldArray() {
        final Scanner input = new Scanner(InputGenerator.generateField(2, 2, 0));

        assertArrayEquals(new char[4][4], HintWriter.createFieldArray(input),
                "Created array does not match expected result.");
    }

    /** Test placeMine method in field with mines above the mine. */
    @Test
    public void testPlaceMineMinesAbove() {
        final String expected = "Field #1:\n***\n**1\n111\n";
        final char[][] field = new char[5][5];
        field[1][1] = '*';
        field[1][2] = '*';
        field[1][3] = '*';
        field[2][1] = '*';
        HintWriter.placeMine(field, 2, 2);

        assertEquals(expected, HintWriter.buildFieldString(1, field),
                "Placing mine did not properly update field.");
    }

    /** Test placeMine method in field with digits above the mine. */
    @Test
    public void testPlaceMineDigitsAbove() {
        final String expected = "Field #1:\n222\n2*1\n111\n";
        final char[][] field = new char[5][5];
        field[1][1] = '1';
        field[1][2] = '1';
        field[1][3] = '1';
        field[2][1] = '1';
        HintWriter.placeMine(field, 2, 2);

        assertEquals(expected, HintWriter.buildFieldString(1, field),
                "Placing mine did not properly update field.");
    }

    /** Test placeMine method in uninitialized field. */
    @Test
    public void testPlaceMineEmptyField() {
        final String expected = "Field #1:\n111\n1*1\n111\n";
        final char[][] field = new char[5][5];
        HintWriter.placeMine(field, 2, 2);

        assertEquals(expected, HintWriter.buildFieldString(1, field),
                "Placing mine did not properly update field.");
    }

    /** Test incrementSquare method on square containing '\0'. */
    @Test
    public void testIncrementSquareNullChar() {
        final char[][] field = new char[1][1];
        field[0][0] = '\0';
        HintWriter.incrementSquare(field, 0, 0);

        assertEquals('1', field[0][0], "Null square not properly incremented.");
    }

    /** Test incrementSquare method on square containing '1'. */
    @Test
    public void testIncrementSquareOf1() {
        final char[][] field = new char[1][1];
        field[0][0] = '1';
        HintWriter.incrementSquare(field, 0, 0);

        assertEquals('2', field[0][0], "Square of 1 not properly incremented.");
    }

    /** Test buildFieldString method. */
    @Test
    public void testBuildFieldString() {
        final String expected = "Field #1:\n..\n..\n";
        final char[][] field = new char[4][4];
        field[1][1] = '.';
        field[1][2] = '.';
        field[2][1] = '.';
        field[2][2] = '.';

        assertEquals(expected, HintWriter.buildFieldString(1, field),
                "buildFieldString returned incorrect String representation of empty 2x2 field.");
    }
}
