package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class GameData extends AbstractData {
    public static final int TYPE_2048 = 1;
    public static final int TYPE_AIRPLANE = 4;
    public static final int TYPE_ANSWER_GAME = 11;
    public static final int TYPE_ARITHMETIC = 8;
    public static final int TYPE_BASKETBALL = 7;
    public static final int TYPE_CANDY_MATCHING = 2;
    public static final int TYPE_JIGSAW_PUZZLE = 3;
    public static final int TYPE_MAZE = 6;
    public static final int TYPE_PET_DEVELOPMENT = 0;
    public static final int TYPE_RACING = 5;
    public static final int TYPE_SUDOKU = 10;
    public static final int TYPE_TETRIS = 9;
    @GameType
    public int b;
    public int c;
    public int d;
    public int e;

    /* loaded from: classes11.dex */
    public @interface GameType {
    }

    public int getDuration() {
        return this.c;
    }

    public int getLevel() {
        return this.e;
    }

    public int getScore() {
        return this.d;
    }

    public int getType() {
        return this.b;
    }

    public void setDuration(int i) {
        this.c = i;
    }

    public void setLevel(int i) {
        this.e = i;
    }

    public void setScore(int i) {
        this.d = i;
    }

    public void setType(int i) {
        this.b = i;
    }
}
