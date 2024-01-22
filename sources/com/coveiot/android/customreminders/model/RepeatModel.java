package com.coveiot.android.customreminders.model;

import java.io.Serializable;
/* loaded from: classes3.dex */
public class RepeatModel implements Serializable {
    public boolean friday;
    public boolean monday;
    public boolean saturday;
    public boolean sunday;
    public boolean thursday;
    public boolean tuesday;
    public boolean wednesday;

    public RepeatModel(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.sunday = false;
        this.monday = false;
        this.tuesday = false;
        this.wednesday = false;
        this.thursday = false;
        this.friday = false;
        this.saturday = false;
        this.sunday = z;
        this.monday = z2;
        this.tuesday = z3;
        this.wednesday = z4;
        this.thursday = z5;
        this.friday = z6;
        this.saturday = z7;
    }

    public boolean isAllSelected() {
        return this.sunday && this.monday && this.tuesday && this.wednesday && this.thursday && this.friday && this.saturday;
    }

    public boolean isRepeatEnabled() {
        return this.sunday || this.monday || this.tuesday || this.wednesday || this.thursday || this.friday || this.saturday;
    }

    public RepeatModel() {
        this.sunday = false;
        this.monday = false;
        this.tuesday = false;
        this.wednesday = false;
        this.thursday = false;
        this.friday = false;
        this.saturday = false;
    }

    public RepeatModel(boolean z) {
        this.sunday = false;
        this.monday = false;
        this.tuesday = false;
        this.wednesday = false;
        this.thursday = false;
        this.friday = false;
        this.saturday = false;
        this.sunday = z;
        this.monday = z;
        this.tuesday = z;
        this.wednesday = z;
        this.thursday = z;
        this.friday = z;
        this.saturday = z;
    }
}
