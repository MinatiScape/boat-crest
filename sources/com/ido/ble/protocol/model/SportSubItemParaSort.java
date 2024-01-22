package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class SportSubItemParaSort {
    public static final int AEROBIC_TRAINING_EFFECT = 17;
    public static final int AVERAGE_PACE = 9;
    public static final int CADENCE = 14;
    public static final int CALORIE = 3;
    public static final int DISTANCE = 4;
    public static final int HR_REAL_TIME_AND_SECTION = 11;
    public static final int INTELLIGENT_ACCOMPANY_RUNNING = 18;
    public static final int INVALID = 0;
    public static final int LAST_TRIP_TO_SWOLF = 7;
    public static final int MAIN_STROKE = 6;
    public static final int MUSIC = 1;
    public static final int NUMBER_OF_TRIPS = 5;
    public static final int OPERATE_QUERY = 1;
    public static final int OPERATE_SET = 2;
    public static final int PACE_REAL_TIME = 8;
    public static final int PLASMA_FREQUENCY = 16;
    public static final int PULP_TIMES = 15;
    public static final int ROLLING_SPEED = 10;
    public static final int SPORT_TIME = 2;
    public static final int STEP = 13;
    public static final int STEP_FREQUENCY = 12;
    public int all_num;
    public int err_code;
    public List<Integer> items;
    public int now_user_location;
    public int operate;
    public int sport_type;

    public String toString() {
        return "SportSubItemParaSort{operate=" + this.operate + ", sport_type=" + this.sport_type + ", now_user_location=" + this.now_user_location + ", all_num=" + this.all_num + ", items=" + this.items + ", err_code=" + this.err_code + '}';
    }
}
