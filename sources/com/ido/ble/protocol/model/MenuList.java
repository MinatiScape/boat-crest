package com.ido.ble.protocol.model;

import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class MenuList {
    public static final int MENU_ALARM = 5;
    public static final int MENU_ALEXA = 20;
    public static final int MENU_BLOOD_OXYGEN = 18;
    public static final int MENU_BREATH = 11;
    public static final int MENU_DATA_THREE_CIRCLE = 14;
    public static final int MENU_FIND_PHONE = 12;
    public static final int MENU_HEART_RATE = 2;
    public static final int MENU_INVALID = 0;
    public static final int MENU_MUSIC = 6;
    public static final int MENU_PRESSURE = 13;
    public static final int MENU_RECENT_ACTIVITY = 16;
    public static final int MENU_SECOND = 7;
    public static final int MENU_SETTINGS = 19;
    public static final int MENU_SLEEP = 3;
    public static final int MENU_SPORT_MODE = 9;
    public static final int MENU_STEP = 1;
    public static final int MENU_TAKE_PHOTO = 4;
    public static final int MENU_TIME = 15;
    public static final int MENU_TIMER = 8;
    public static final int MENU_TODAY_HEALTH_DATA = 17;
    public static final int MENU_WEATHER = 10;
    public static final int MENU_X_SCREEN = 21;
    public List<Integer> items = new ArrayList();

    /* loaded from: classes11.dex */
    public static final class DeviceReturnInfo {
        public int currentShowNum;
        public List<Item> items;
        public int maxNum;
        public int maxShowNum;
        public int minShowNum;

        /* loaded from: classes11.dex */
        public static class Item {
            public int index;
            public int value;

            public String toString() {
                return "Item{index=" + this.index + ", value=" + this.value + '}';
            }
        }

        public String toString() {
            return "DeviceReturnInfo{minShowNum=" + this.minShowNum + ", maxShowNum=" + this.maxShowNum + ", maxNum=" + this.maxNum + ", currentShowNum=" + this.currentShowNum + ", items=" + this.items + '}';
        }
    }

    public String toString() {
        return "MenuList{items=" + this.items + '}';
    }
}
