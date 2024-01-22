package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.IntRange;
import java.util.Arrays;
/* loaded from: classes10.dex */
public class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new a();
    public final b h;
    public final b i;
    public final int j;
    public int k;
    public int l;
    public int m;
    public int n;

    /* loaded from: classes10.dex */
    public class a implements Parcelable.Creator<TimeModel> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public TimeModel createFromParcel(Parcel parcel) {
            return new TimeModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public TimeModel[] newArray(int i) {
            return new TimeModel[i];
        }
    }

    public TimeModel() {
        this(0);
    }

    public static String a(Resources resources, CharSequence charSequence) {
        return b(resources, charSequence, "%02d");
    }

    public static String b(Resources resources, CharSequence charSequence, String str) {
        return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
    }

    public static int f(int i) {
        return i >= 12 ? 1 : 0;
    }

    public int c() {
        if (this.j == 1) {
            return this.k % 24;
        }
        int i = this.k;
        if (i % 12 == 0) {
            return 12;
        }
        return this.n == 1 ? i - 12 : i;
    }

    public b d() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public b e() {
        return this.h;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TimeModel) {
            TimeModel timeModel = (TimeModel) obj;
            return this.k == timeModel.k && this.l == timeModel.l && this.j == timeModel.j && this.m == timeModel.m;
        }
        return false;
    }

    public void g(int i) {
        if (this.j == 1) {
            this.k = i;
        } else {
            this.k = (i % 12) + (this.n != 1 ? 0 : 12);
        }
    }

    public void h(int i) {
        this.n = f(i);
        this.k = i;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.j), Integer.valueOf(this.k), Integer.valueOf(this.l), Integer.valueOf(this.m)});
    }

    public void i(@IntRange(from = 0, to = 59) int i) {
        this.l = i % 60;
    }

    public void j(int i) {
        if (i != this.n) {
            this.n = i;
            int i2 = this.k;
            if (i2 < 12 && i == 1) {
                this.k = i2 + 12;
            } else if (i2 < 12 || i != 0) {
            } else {
                this.k = i2 - 12;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        parcel.writeInt(this.j);
    }

    public TimeModel(int i) {
        this(0, 0, 10, i);
    }

    public TimeModel(int i, int i2, int i3, int i4) {
        this.k = i;
        this.l = i2;
        this.m = i3;
        this.j = i4;
        this.n = f(i);
        this.h = new b(59);
        this.i = new b(i4 == 1 ? 24 : 12);
    }

    public TimeModel(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }
}
