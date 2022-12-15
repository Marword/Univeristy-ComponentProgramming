package model;

import java.util.Random;

public enum Level {

    EASY(30),
    MEDIUM(40),
    HARD(50),
    MASTER(60);

    private int gaps;

    Level(int gaps) {
        this.gaps = gaps;
    }

    public int getGaps() {
        return gaps;
    }

    public void removeValues(final SudokuBoard board) {
        Random random = new Random();
        for (int i = 0; i < gaps; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (board.get(x, y) != 0) {
                board.set(x, y, 0);
            } else {
                i--;
            }
        }
    }

    public SudokuBoard chooseLevel(SudokuBoard sudokuBoard, String level) {

        switch (level) {

            case "Latwy":
            case "Easy": {
                this.gaps = 30;
                removeValues(sudokuBoard);
                break;
            }
            case "Sredni":
            case "Medium": {
                this.gaps = 40;
                removeValues(sudokuBoard);
                break;
            }
            case "Trudny":
            case "Hard": {
                this.gaps = 50;
                removeValues(sudokuBoard);
                break;
            }
            case "Mistrz":
            case "Master": {
                this.gaps = 60;
                removeValues(sudokuBoard);
                break;
            }
            default: {
                this.gaps = 30;
                removeValues(sudokuBoard);
            }
        }

        return sudokuBoard;
    }
}


