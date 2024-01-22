package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class SportPlan {
    public static final int OPERATE_END = 3;
    public static final int OPERATE_QUERY = 4;
    public static final int OPERATE_SEND = 2;
    public static final int OPERATE_START = 1;
    public int day;
    public int day_num;
    public int err_code;
    public int hour;
    public List<PlanContent> items;
    public int min;
    public int month;
    public int operate;
    public int sec;
    public int type;
    public int version;
    public int year;

    /* loaded from: classes11.dex */
    public static class ActionContent {
        public int height_heart;
        public int low_heart;
        public int time;
        public int type;

        public String toString() {
            return "ActionContent{type=" + this.type + ", time=" + this.time + ", low_heart=" + this.low_heart + ", height_heart=" + this.height_heart + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class PlanContent {
        public List<ActionContent> item;
        public int num;
        public int type;

        public String toString() {
            return "PlanContent{type=" + this.type + ", num=" + this.num + ", item=" + this.item + '}';
        }
    }

    public String toString() {
        return "SportPlan{err_code=" + this.err_code + ", operate=" + this.operate + ", version=" + this.version + ", type=" + this.type + ", year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", day_num=" + this.day_num + ", items=" + this.items + ", hour=" + this.hour + ", min=" + this.min + ", sec=" + this.sec + '}';
    }
}
