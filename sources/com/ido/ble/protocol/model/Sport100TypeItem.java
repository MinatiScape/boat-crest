package com.ido.ble.protocol.model;

import java.util.Objects;
/* loaded from: classes11.dex */
public class Sport100TypeItem {
    public static final int PIC_ALL_DOWNLOAD_SUCCESS = 3;
    public static final int PIC_All_SUCCESS = 15;
    public static final int PIC_BIG_DOWNLOAD_SUCCESS = 2;
    public static final int PIC_MIDDLE_BIG_DOWNLOAD_SUCCESS = 6;
    public static final int PIC_MIDDLE_DOWNLOAD_SUCCESS = 4;
    public static final int PIC_NEW_ALL_DOWNLOAD_SUCCESS = 7;
    public static final int PIC_NOT_DOWNLOAD = 0;
    public static final int PIC_SMALL_DOWNLOAD_SUCCESS = 1;
    public static final int PIC_SMALL_MIDDLE_DOWNLOAD_SUCCESS = 5;
    public int flag;
    public int type;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Sport100TypeItem) && this.type == ((Sport100TypeItem) obj).type;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.type));
    }

    public String toString() {
        return "Sport100TypeItem{type=" + this.type + ", flag=" + this.flag + '}';
    }
}
