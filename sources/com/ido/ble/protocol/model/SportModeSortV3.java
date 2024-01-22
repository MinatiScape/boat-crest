package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class SportModeSortV3 {
    public List<SportModeSortItemV3> item;
    public int num;

    /* loaded from: classes11.dex */
    public static class SportModeSortItemV3 {
        public int index;
        public int type;

        public String toString() {
            return "SportModeSortItem{index=" + this.index + ", type=" + this.type + '}';
        }
    }

    public String toString() {
        return "SportModeSortV3{num=" + this.num + ", item=" + this.item + '}';
    }
}
