package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
//    private SudokuBoard sudo_board;
//    private SudokuBoard sudo_board1;
//
//
//    @BeforeEach
//    public void startup() {
//        sudo_board = new SudokuBoard();
//        sudo_board.fillBoard();
//    }
//    @Test
//    public void differentArrangmentsTest() {
//        sudo_board1 = new SudokuBoard();
//        sudo_board1.fillBoard();
//
//        int[][] board1 = sudo_board.copyBoard();
//        int[][] board2 = sudo_board1.copyBoard();
//        assertFalse(Arrays.deepEquals(board1, board2));
//    }
//
//    @Test
//    public void fillBoardTest()
//    {
//        int[][] board = sudo_board.copyBoard();
//        int rep;
//        //sprawdzanie wierszy
//        for (int i = 0; i < 9; i++) {
//            rep = 0;
//            for (int j = 0; j < 9; j++) {
//                for (int num = 1; num < 10; num++) {
//                    if (board[i][j] == num) {
//                        rep++;
//                    }
//                }
//            }
//            if (rep != 9)  fail("Row " + i + " is incorrect!");
//        }
//
//        //sprawdzanie kolumn
//        for (int i = 0; i < 9; i++) {
//            rep = 0;
//            for (int j = 0; j < 9; j++) {
//                for (int num = 1; num < 10; num++) {
//                    if (board[j][i] == num) {
//                        rep++;
//                    }
//                }
//            }
//            if (rep != 9)  fail("Column " + i + " is incorrect!");
//        }
//
//        //sprawdzanie małych kwadratów
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                rep = 0;
//                for (int rowCheck = 0; rowCheck<3; rowCheck++)
//                {
//                    for (int colCheck = 0; colCheck<3; colCheck++)
//                    {
//                        for (int num = 1; num<10; num++)
//                        {
//                            if (board[i*3 + rowCheck][j*3 + colCheck] == num)
//                            {
//                                rep++;
//                            }
//                        }
//
//                    }
//                }
//                if (rep!=9)
//                {
//                    fail("Small board (" + i + ", " + j + ") is incorrect!");
//                }
//            }
//        }
//
//
//
//    }


}