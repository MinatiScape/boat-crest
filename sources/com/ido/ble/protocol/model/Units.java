package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class Units implements Serializable {
    public static final int CALORIE_KILO = 1;
    public static final int CALORIE_KJ = 3;
    public static final int CALORIE_LARGE = 2;
    public static final int DIST_UNIT_KM = 1;
    public static final int DIST_UNIT_MI = 2;
    public static final int LANG_BURMESE = 27;
    public static final int LANG_CROATIAN = 19;
    public static final int LANG_CZ = 9;
    public static final int LANG_DANISH = 18;
    public static final int LANG_DE = 4;
    public static final int LANG_DUTCH = 12;
    public static final int LANG_EN = 2;
    public static final int LANG_ES = 6;
    public static final int LANG_FILIPINO = 28;
    public static final int LANG_FINLAND = 33;
    public static final int LANG_FR = 3;
    public static final int LANG_GREEK = 30;
    public static final int LANG_HINDI = 22;
    public static final int LANG_HUNGARIAN = 14;
    public static final int LANG_INDONESIAN = 20;
    public static final int LANG_IT = 5;
    public static final int LANG_JA = 7;
    public static final int LANG_KOREAN = 21;
    public static final int LANG_LITHUANIAN = 11;
    public static final int LANG_PERSIA = 34;
    public static final int LANG_PO = 8;
    public static final int LANG_PORTUGUESE = 23;
    public static final int LANG_ROMANIAN = 10;
    public static final int LANG_RUSSIAN = 15;
    public static final int LANG_SLOVAK = 17;
    public static final int LANG_SLOVENIAN = 13;
    public static final int LANG_SWEDEN = 32;
    public static final int LANG_THAI = 25;
    public static final int LANG_TRADITIONAL_CHINESE = 29;
    public static final int LANG_TURKISH = 24;
    public static final int LANG_UKRAINIAN = 16;
    public static final int LANG_VIETNAMESE = 26;
    public static final int LANG_ZH = 1;
    public static final int STRIDE_GPS_CAL_SWITCH_OFF = 2;
    public static final int STRIDE_GPS_CAL_SWITCH_ON = 1;
    public static final int SWIM_POOL_METER = 1;
    public static final int SWIM_POOL_YARD = 2;
    public static final int TEMP_UNIT_C = 1;
    public static final int TEMP_UNIT_F = 2;
    public static final int TIME_MODE_12 = 2;
    public static final int TIME_MODE_24 = 1;
    public static final int UNIT_DEFAULT = 0;
    public static final int WEEK_START_MONDAY = 0;
    public static final int WEEK_START_SATURDAY = 3;
    public static final int WEEK_START_SUNDAY = 1;
    public static final int WEIGHT_UNIT_KG = 1;
    public static final int WEIGHT_UNIT_LB = 2;
    public static final int WEIGHT_UNIT_ST = 3;
    public static final int lANG_ARABIC = 31;
    public static final int lANG_NORWEGIAN = 35;
    private static final long serialVersionUID = 1;
    public int cyclingUnit;
    public int dist;
    public int language;
    public int stride;
    public int strideGPSCal;
    public int strideRun;
    public int swimPoolUnit;
    public int temp;
    public int timeMode;
    public int walkingRunningUnit;
    public int weight;
    public int weekStartDate = 1;
    public int calorieUnit = 1;

    public String toString() {
        return "Units{dist=" + this.dist + ", weight=" + this.weight + ", temp=" + this.temp + ", stride=" + this.stride + ", language=" + this.language + ", timeMode=" + this.timeMode + ", strideRun=" + this.strideRun + ", strideGPSCal=" + this.strideGPSCal + ", weekStartDate=" + this.weekStartDate + ", calorieUnit=" + this.calorieUnit + ", swimPoolUnit=" + this.swimPoolUnit + ", cyclingUnit=" + this.cyclingUnit + ", walkingRunningUnit=" + this.walkingRunningUnit + '}';
    }
}
