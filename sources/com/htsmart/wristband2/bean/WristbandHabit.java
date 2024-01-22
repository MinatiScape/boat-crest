package com.htsmart.wristband2.bean;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class WristbandHabit implements Cloneable, Parcelable {
    public static final Parcelable.Creator<WristbandHabit> CREATOR = new a();
    public static final int HABIT_ID_MAX = 9;
    public static final int HABIT_ID_MIN = 0;
    public static final int HABIT_STATE_CLOSED = 4;
    public static final int HABIT_STATE_COMPLETED = 2;
    public static final int HABIT_STATE_DELETED = 5;
    public static final int HABIT_STATE_INIT = 0;
    public static final int HABIT_STATE_ONGOING = 1;
    public static final int HABIT_STATE_OVERDUE = 3;
    public static final int HABIT_TYPE_CUSTOM = 0;
    public static final int HABIT_TYPE_SPORT = 1;
    public int h;
    public int i;
    public String j;
    public Date k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;

    /* loaded from: classes11.dex */
    public static class a implements Parcelable.Creator<WristbandHabit> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public WristbandHabit createFromParcel(Parcel parcel) {
            return new WristbandHabit(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public WristbandHabit[] newArray(int i) {
            return new WristbandHabit[i];
        }
    }

    public WristbandHabit() {
    }

    public WristbandHabit(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readString();
        String readString = parcel.readString();
        if (readString != null) {
            try {
                this.k = new Date(Long.parseLong(readString));
            } catch (Exception unused) {
            }
        }
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = parcel.readInt();
        this.s = parcel.readInt();
        this.t = parcel.readInt();
    }

    public static int findNewHabitId(List<WristbandHabit> list) {
        boolean z;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        for (int i = 0; i <= 9; i++) {
            Iterator<WristbandHabit> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i == it.next().getHabitId()) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                return i;
            }
        }
        return -1;
    }

    @NonNull
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAssociatedFunction() {
        return this.r;
    }

    public int getDuration() {
        return this.l;
    }

    public int getHabitId() {
        return this.h;
    }

    public String getHabitName() {
        return this.j;
    }

    public int getHabitType() {
        return this.i;
    }

    public int getMaxReachGoalDays() {
        return this.p;
    }

    public int getReachGoalDays() {
        return this.o;
    }

    public int getRemindAdvance() {
        return this.t;
    }

    public int getRemindDuration() {
        return this.s;
    }

    public int getRepeat() {
        return this.m;
    }

    public Date getStartTime() {
        return this.k;
    }

    public int getState() {
        return this.n;
    }

    public int getTaskDays() {
        return this.q;
    }

    public void setAssociatedFunction(int i) {
        this.r = i;
    }

    public void setDuration(int i) {
        this.l = i;
    }

    public void setHabitId(int i) {
        this.h = i;
    }

    public void setHabitName(String str) {
        this.j = str;
    }

    public void setHabitType(int i) {
        this.i = i;
    }

    public void setMaxReachGoalDays(int i) {
        this.p = i;
    }

    public void setReachGoalDays(int i) {
        this.o = i;
    }

    public void setRemindAdvance(int i) {
        this.t = i;
    }

    public void setRemindDuration(int i) {
        this.s = i;
    }

    public void setRepeat(int i) {
        this.m = i;
    }

    public void setStartTime(Date date) {
        this.k = date;
    }

    public void setState(int i) {
        this.n = i;
    }

    public void setTaskDays(int i) {
        this.q = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeString(this.j);
        Date date = this.k;
        parcel.writeString(date != null ? String.valueOf(date.getTime()) : null);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeInt(this.o);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        parcel.writeInt(this.r);
        parcel.writeInt(this.s);
        parcel.writeInt(this.t);
    }
}
