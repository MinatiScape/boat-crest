package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes11.dex */
public class WalkRealTimeReminder implements Serializable {
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    private static final long serialVersionUID = 1;
    public List<Item> items;
    public int num;
    public int on_off;
    public int version;

    /* loaded from: classes11.dex */
    public static class Item {
        public int hour;
        public int min;

        public String toString() {
            return "Item{hour=" + this.hour + ", min=" + this.min + '}';
        }
    }

    public String toString() {
        return "WalkRealTimeReminder{version=" + this.version + ", on_off=" + this.on_off + ", num=" + this.num + ", items=" + this.items + '}';
    }
}
