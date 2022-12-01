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
}


