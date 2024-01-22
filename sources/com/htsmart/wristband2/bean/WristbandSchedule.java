package com.htsmart.wristband2.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes11.dex */
public class WristbandSchedule implements Cloneable, Parcelable {
    public static final Parcelable.Creator<WristbandSchedule> CREATOR = new a();
    public static final int SCHEDULE_ID_MAX = 9;
    public static final int SCHEDULE_ID_MIN = 0;
    public static final int SCHEDULE_TYPE_ALARM_CLOCK = 1;
    public static final int SCHEDULE_TYPE_BREAKFAST = 4;
    public static final int SCHEDULE_TYPE_BRUSH_TEETH = 3;
    public static final int SCHEDULE_TYPE_DRINK_WATER = 0;
    public static final int SCHEDULE_TYPE_GET_UP = 8;
    public static final int SCHEDULE_TYPE_GO_HOME = 11;
    public static final int SCHEDULE_TYPE_GO_SCHOOL = 10;
    public static final int SCHEDULE_TYPE_HOMEWORK = 5;
    public static final int SCHEDULE_TYPE_LUNCH = 6;
    public static final int SCHEDULE_TYPE_SEDENTARY = 2;
    public static final int SCHEDULE_TYPE_SLEEP = 9;
    public static final int SCHEDULE_TYPE_SPORT = 7;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p;
    public String q;

    /* loaded from: classes11.dex */
    public static class a implements Parcelable.Creator<WristbandSchedule> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public WristbandSchedule createFromParcel(Parcel parcel) {
            return new WristbandSchedule(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public WristbandSchedule[] newArray(int i) {
            return new WristbandSchedule[i];
        }
    }

    public WristbandSchedule() {
    }

    public WristbandSchedule(Parcel parcel) {
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        this.n = parcel.readInt();
        this.o = parcel.readInt();
        this.p = parcel.readByte() != 0;
        this.q = parcel.readString();
    }

    public static int findNewScheduleId(List<WristbandSchedule> list) {
        boolean z;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        for (int i = 0; i <= 9; i++) {
            Iterator<WristbandSchedule> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i == it.next().getScheduleId()) {
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

    public void adjustSchedule() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        if (this.o == 0 && this.p && (this.m * 60) + this.n <= (gregorianCalendar.get(11) * 60) + gregorianCalendar.get(12)) {
            gregorianCalendar.set(5, gregorianCalendar.get(5) + 1);
        }
        this.j = gregorianCalendar.get(1);
        this.k = gregorianCalendar.get(2);
        this.l = gregorianCalendar.get(5);
        this.h = 0;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getDay() {
        return this.l;
    }

    public int getHour() {
        return this.m;
    }

    public String getLabel() {
        return this.q;
    }

    public int getMinute() {
        return this.n;
    }

    public int getMonth() {
        return this.k;
    }

    public int getRepeat() {
        return this.o;
    }

    public int getScheduleId() {
        return this.h;
    }

    public int getScheduleType() {
        return this.i;
    }

    public int getYear() {
        return this.j;
    }

    public boolean isEnable() {
        if (this.o == 0 && this.p) {
            if (new Date().getTime() > new Date(this.j - 1900, this.k, this.l, this.m, this.n, 0).getTime()) {
                this.p = false;
            }
        }
        return this.p;
    }

    public void setDay(int i) {
        this.l = i;
    }

    public void setEnable(boolean z) {
        this.p = z;
    }

    public void setHour(int i) {
        this.m = i;
    }

    public void setLabel(String str) {
        this.q = str;
    }

    public void setMinute(int i) {
        this.n = i;
    }

    public void setMonth(int i) {
        this.k = i;
    }

    public void setRepeat(int i) {
        this.o = i;
    }

    public void setScheduleId(int i) {
        this.h = i;
    }

    public void setScheduleType(int i) {
        this.i = i;
    }

    public void setYear(int i) {
        this.j = i;
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
        parcel.writeInt(this.o);
        parcel.writeByte(this.p ? (byte) 1 : (byte) 0);
        parcel.writeString(this.q);
    }
}
