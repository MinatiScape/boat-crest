package com.apex.bluetooth.model;

import com.apex.bluetooth.enumeration.BatInfoStatus;
/* loaded from: classes.dex */
public class EABleBatInfo {
    public BatInfoStatus e_status;
    public int level;

    public BatInfoStatus getE_status() {
        return this.e_status;
    }

    public int getLevel() {
        return this.level;
    }

    public void setE_status(BatInfoStatus batInfoStatus) {
        this.e_status = batInfoStatus;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String toString() {
        return "EABleBatInfo{e_status=" + this.e_status + ", level=" + this.level + '}';
    }
}
