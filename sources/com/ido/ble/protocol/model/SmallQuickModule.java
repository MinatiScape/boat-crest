package com.ido.ble.protocol.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class SmallQuickModule {
    public static final int SIZE_TYPE_LARGE = 2;
    public static final int SIZE_TYPE_SMALL = 1;
    public static final int WIDGETS_TYPE_ALARM = 13;
    public static final int WIDGETS_TYPE_ALEXA = 9;
    public static final int WIDGETS_TYPE_BATTERY = 16;
    public static final int WIDGETS_TYPE_CONTACTS = 17;
    public static final int WIDGETS_TYPE_HEART_RATE = 6;
    public static final int WIDGETS_TYPE_MUSIC = 4;
    public static final int WIDGETS_TYPE_NOISE = 15;
    public static final int WIDGETS_TYPE_PRESSURE = 7;
    public static final int WIDGETS_TYPE_RECENT_ACTIVTY = 3;
    public static final int WIDGETS_TYPE_REMINDERS = 14;
    public static final int WIDGETS_TYPE_SLEEP = 8;
    public static final int WIDGETS_TYPE_SPO2 = 11;
    public static final int WIDGETS_TYPE_STEPS = 2;
    public static final int WIDGETS_TYPE_TEMPERATURE = 10;
    public static final int WIDGETS_TYPE_THREE_CIRCLE = 1;
    public static final int WIDGETS_TYPE_TIMER = 12;
    public static final int WIDGETS_TYPE_WEATHER = 5;
    public static final int WIDGETS_TYPE_WORLD_TIME = 18;
    public int location_x;
    public int location_y;
    public int size_type;
    public int widgets_type;

    /* loaded from: classes11.dex */
    public static class QueryResponse {
        public List<SmallQuickModule> items = new ArrayList();
        public List<SupportInfo> support_items = new ArrayList();

        public String toString() {
            return "QueryResponse{items=" + this.items + ", support_items=" + this.support_items + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class SupportInfo {
        public static final int SUPOORT_SIZE_TYPE_LARGE = 1;
        public static final int SUPOORT_SIZE_TYPE_LARGE_AND_SMALL = 3;
        public static final int SUPOORT_SIZE_TYPE_SMALL = 2;
        public int support_size_type;
        public int widgets_type;

        public String toString() {
            return "SupportInfo{support_size_type=" + this.support_size_type + ", widgets_type=" + this.widgets_type + '}';
        }
    }

    public String toString() {
        return "SmallQuickModule{location_x=" + this.location_x + ", location_y=" + this.location_y + ", size_type=" + this.size_type + ", widgets_type=" + this.widgets_type + '}';
    }
}
