package com.ido.ble.protocol.model;

import java.util.Arrays;
/* loaded from: classes11.dex */
public class SportModeSort {
    public SportModeSortItem[] items;

    /* loaded from: classes11.dex */
    public static class SportModeSortItem {
        public int index;
        public int type;

        public String toString() {
            return "SportModeSortItem{index=" + this.index + ", type=" + this.type + '}';
        }
    }

    public String toString() {
        return "SportModeSort{items=" + Arrays.toString(this.items) + '}';
    }
}
