package com.htsmart.wristband2.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class WristbandAlarm implements Cloneable, Parcelable {
    public static final int ALARM_ID_MAX = 4;
    public static final int ALARM_ID_MIN = 0;
    public static final int REPEAT_FLAG_FRI = 16;
    public static final int REPEAT_FLAG_MON = 1;
    public static final int REPEAT_FLAG_SAT = 32;
    public static final int REPEAT_FLAG_SUN = 64;
    public static final int REPEAT_FLAG_THU = 8;
    public static final int REPEAT_FLAG_TUE = 2;
    public static final int REPEAT_FLAG_WED = 4;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public String p;
    public static final int[] q = {1, 2, 4, 8, 16, 32, 64};
    public static final Parcelable.Creator<WristbandAlarm> CREATOR = new a();

    /* loaded from: classes11.dex */
    public static class a implements Parcelable.Creator<WristbandAlarm> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public WristbandAlarm createFromParcel(Parcel parcel) {
            return new WristbandAlarm(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public WristbandAlarm[] newArray(int i) {
            return new WristbandAlarm[i];
        }
    }

    public WristbandAlarm() {
    }

    public WristbandAlarm(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readByte() != 0;
        this.p = parcel.readString();
    }

    public static int a(int i, int i2, int i3) {
        return (i & (~i3)) | (i2 & i3);
    }

    public static int findNewAlarmId(List<WristbandAlarm> list) {
        boolean z;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        for (int i = 0; i <= 4; i++) {
            Iterator<WristbandAlarm> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i == it.next().getAlarmId()) {
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

    public static boolean isRepeatEnable(int i, int i2) {
        return (i & i2) > 0;
    }

    public static boolean isRepeatEnableIndex(int i, int i2) {
        return (i & q[i2]) > 0;
    }

    public static int setRepeatEnable(int i, int i2, boolean z) {
        return z ? a(i, i2, i2) : a(i, 0, i2);
    }

    public static int setRepeatEnableIndex(int i, int i2, boolean z) {
        if (z) {
            int[] iArr = q;
            return a(i, iArr[i2], iArr[i2]);
        }
        return a(i, 0, q[i2]);
    }

    public void adjustAlarm() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (this.n == 0 && this.o && (this.l * 60) + this.m <= (gregorianCalendar.get(11) * 60) + gregorianCalendar.get(12)) {
            gregorianCalendar.set(5, gregorianCalendar.get(5) + 1);
        }
        this.i = gregorianCalendar.get(1);
        this.j = gregorianCalendar.get(2);
        this.k = gregorianCalendar.get(5);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getAlarmId() {
        return this.h;
    }

    public int getDay() {
        return this.k;
    }

    public int getHour() {
        return this.l;
    }

    public String getLabel() {
        return this.p;
    }

    public int getMinute() {
        return this.m;
    }

    public int getMonth() {
        return this.j;
    }

    public int getRepeat() {
        return this.n;
    }

    public int getYear() {
        return this.i;
    }

    public boolean isEnable() {
        if (this.n == 0 && this.o) {
            if (new Date().getTime() > new Date(this.i - 1900, this.j, this.k, this.l, this.m, 0).getTime()) {
                this.o = false;
            }
        }
        return this.o;
    }

    public void setAlarmId(int i) {
        this.h = i;
    }

    public void setDay(int i) {
        this.k = i;
    }

    public void setEnable(boolean z) {
        this.o = z;
    }

    public void setHour(int i) {
        this.l = i;
    }

    public void setLabel(String str) {
        this.p = str;
    }

    public void setMinute(int i) {
        this.m = i;
    }

    public void setMonth(int i) {
        this.j = i;
    }

    public void setRepeat(int i) {
        this.n = i;
    }

    public void setYear(int i) {
        this.i = i;
    }

    public String toString() {
        return "alarmId:" + this.h + "\nyear:" + this.i + "\nmonth:" + this.j + "\nday:" + this.k + "\nhour:" + this.l + "\nminute:" + this.m + "\nrepeat:" + this.n + "\nopen:" + this.o + "\nlabel:" + this.p;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.n);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeString(this.p);
    }
}
