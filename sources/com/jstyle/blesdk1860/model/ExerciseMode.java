package com.jstyle.blesdk1860.model;
/* loaded from: classes11.dex */
public class ExerciseMode extends SendData {
    public static final int Mode_BADMINTON = 2;
    public static final int Mode_BASKETBALL = 8;
    public static final int Mode_BREATH = 6;
    public static final int Mode_CLIMB = 9;
    public static final int Mode_CYCLING = 1;
    public static final int Mode_DANCE = 7;
    public static final int Mode_FOOTBALL = 3;
    public static final int Mode_RUN = 0;
    public static final int Mode_TENNIS = 4;
    public static final int Mode_YOGA = 5;
    public static final int Mode_workout = 10;
    public static final int Status_CONTUINE = 3;
    public static final int Status_FINISH = 4;
    public static final int Status_PAUSE = 2;
    public static final int Status_START = 1;
    public static int[] modes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    /* renamed from: a  reason: collision with root package name */
    public int f12523a;
    public int b;

    public int getEnableStatus() {
        return this.b;
    }

    public int getExerciseMode(int i) {
        return modes[i];
    }

    public void setEnableStatus(int i) {
        this.b = i;
    }

    public void setExerciseMode(int i) {
        this.f12523a = i;
    }

    public int getExerciseMode() {
        return this.f12523a;
    }
}
