package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class WatchDialOrder {
    public List<WatchDialInfo> p_sort_item;
    public int sort_item_numb;

    /* loaded from: classes11.dex */
    public static class WatchDialInfo {
        public static final int TYPE_CLOUD = 3;
        public static final int TYPE_NORMAL = 1;
        public static final int TYPE_WRAPPER = 2;
        public String name;
        public int sort_number;
        public int type;

        public String toString() {
            return "WatchDialInfo{type=" + this.type + ", sort_number=" + this.sort_number + ", name='" + this.name + "'}";
        }
    }

    public String toString() {
        return "WatchDialOrder{sort_item_numb=" + this.sort_item_numb + ", p_sort_item=" + this.p_sort_item + '}';
    }
}
