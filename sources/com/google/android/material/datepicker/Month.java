package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
/* loaded from: classes10.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new a();
    @NonNull
    public final Calendar h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final long m;
    @Nullable
    public String n;

    /* loaded from: classes10.dex */
    public class a implements Parcelable.Creator<Month> {
        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a */
        public Month createFromParcel(@NonNull Parcel parcel) {
            return Month.b(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b */
        public Month[] newArray(int i) {
            return new Month[i];
        }
    }

    public Month(@NonNull Calendar calendar) {
        calendar.set(5, 1);
        Calendar f = j.f(calendar);
        this.h = f;
        this.i = f.get(2);
        this.j = f.get(1);
        this.k = f.getMaximum(7);
        this.l = f.getActualMaximum(5);
        this.m = f.getTimeInMillis();
    }

    @NonNull
    public static Month b(int i, int i2) {
        Calendar q = j.q();
        q.set(1, i);
        q.set(2, i2);
        return new Month(q);
    }

    @NonNull
    public static Month c(long j) {
        Calendar q = j.q();
        q.setTimeInMillis(j);
        return new Month(q);
    }

    @NonNull
    public static Month d() {
        return new Month(j.o());
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(@NonNull Month month) {
        return this.h.compareTo(month.h);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int e() {
        int firstDayOfWeek = this.h.get(7) - this.h.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.k : firstDayOfWeek;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Month) {
            Month month = (Month) obj;
            return this.i == month.i && this.j == month.j;
        }
        return false;
    }

    public long f(int i) {
        Calendar f = j.f(this.h);
        f.set(5, i);
        return f.getTimeInMillis();
    }

    public int g(long j) {
        Calendar f = j.f(this.h);
        f.setTimeInMillis(j);
        return f.get(5);
    }

    @NonNull
    public String h() {
        if (this.n == null) {
            this.n = d.i(this.h.getTimeInMillis());
        }
        return this.n;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.j)});
    }

    public long i() {
        return this.h.getTimeInMillis();
    }

    @NonNull
    public Month j(int i) {
        Calendar f = j.f(this.h);
        f.add(2, i);
        return new Month(f);
    }

    public int k(@NonNull Month month) {
        if (this.h instanceof GregorianCalendar) {
            return ((month.j - this.j) * 12) + (month.i - this.i);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(this.j);
        parcel.writeInt(this.i);
    }
}
