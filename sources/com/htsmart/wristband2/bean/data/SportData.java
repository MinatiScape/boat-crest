package com.htsmart.wristband2.bean.data;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes11.dex */
public class SportData extends AbstractData {
    public static final int SPORT_BADMINTON = 29;
    public static final int SPORT_BASEBALL = 73;
    public static final int SPORT_BB = 21;
    public static final int SPORT_CLIMB = 17;
    public static final int SPORT_CRICKET = 81;
    public static final int SPORT_DANCE = 105;
    public static final int SPORT_DUMBBELL = 101;
    public static final int SPORT_ELLIPTICAL_TRAINER = 37;
    public static final int SPORT_EXERCISE_BIKE = 61;
    public static final int SPORT_FOOTBALL = 33;
    public static final int SPORT_FREE_MOVEMENT = 85;
    public static final int SPORT_FREE_TRAINING = 65;
    public static final int SPORT_GOLF = 113;
    public static final int SPORT_HULA_HOOP = 109;
    public static final int SPORT_ID = 9;
    public static final int SPORT_ID_RIDE = 97;
    public static final int SPORT_ID_WALKING = 93;
    public static final int SPORT_LAZY_CAR = 57;
    public static final int SPORT_LONG_JUMP = 117;
    public static final int SPORT_OD = 5;
    public static final int SPORT_PING_PONG = 45;
    public static final int SPORT_RIDE = 1;
    public static final int SPORT_ROPE_SKIPPING = 49;
    public static final int SPORT_ROWING_MACHINE = 53;
    public static final int SPORT_RUGBY = 77;
    public static final int SPORT_SIT_UPS = 121;
    public static final int SPORT_STRENGTH_TRAINING = 89;
    public static final int SPORT_SWIM = 25;
    public static final int SPORT_TENNIS = 69;
    public static final int SPORT_VOLLEYBALL = 125;
    public static final int SPORT_WALK = 13;
    public static final int SPORT_YOGA = 41;
    public int b;
    public int c;
    public float d;
    public float e;
    public int f;
    public List<SportItem> g;
    public String h;

    public float getCalories() {
        return this.e;
    }

    public float getDistance() {
        return this.d;
    }

    public int getDuration() {
        return this.c;
    }

    public List<SportItem> getItems() {
        return this.g;
    }

    @Nullable
    public String getRecordId() {
        return this.h;
    }

    public int getSportType() {
        return this.b;
    }

    public int getSteps() {
        return this.f;
    }

    public void setCalories(float f) {
        this.e = f;
    }

    public void setDistance(float f) {
        this.d = f;
    }

    public void setDuration(int i) {
        this.c = i;
    }

    public void setItems(List<SportItem> list) {
        this.g = list;
    }

    public void setRecordId(String str) {
        this.h = str;
    }

    public void setSportType(int i) {
        this.b = i;
    }

    public void setSteps(int i) {
        this.f = i;
    }
}
