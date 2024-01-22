package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new a();
    @NonNull
    public final Month h;
    @NonNull
    public final Month i;
    @NonNull
    public final DateValidator j;
    @Nullable
    public Month k;
    public final int l;
    public final int m;

    /* loaded from: classes10.dex */
    public interface DateValidator extends Parcelable {
        boolean isValid(long j);
    }

    /* loaded from: classes10.dex */
    public class a implements Parcelable.Creator<CalendarConstraints> {
        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: a */
        public CalendarConstraints createFromParcel(@NonNull Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        @NonNull
        /* renamed from: b */
        public CalendarConstraints[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    }

    public /* synthetic */ CalendarConstraints(Month month, Month month2, DateValidator dateValidator, Month month3, a aVar) {
        this(month, month2, dateValidator, month3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Month e(Month month) {
        if (month.compareTo(this.h) < 0) {
            return this.h;
        }
        return month.compareTo(this.i) > 0 ? this.i : month;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CalendarConstraints) {
            CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
            return this.h.equals(calendarConstraints.h) && this.i.equals(calendarConstraints.i) && ObjectsCompat.equals(this.k, calendarConstraints.k) && this.j.equals(calendarConstraints.j);
        }
        return false;
    }

    @NonNull
    public Month f() {
        return this.i;
    }

    public int g() {
        return this.m;
    }

    public DateValidator getDateValidator() {
        return this.j;
    }

    @Nullable
    public Month h() {
        return this.k;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.h, this.i, this.k, this.j});
    }

    @NonNull
    public Month i() {
        return this.h;
    }

    public int j() {
        return this.l;
    }

    public boolean k(long j) {
        if (this.h.f(1) <= j) {
            Month month = this.i;
            if (j <= month.f(month.l)) {
                return true;
            }
        }
        return false;
    }

    public void l(@Nullable Month month) {
        this.k = month;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.h, 0);
        parcel.writeParcelable(this.i, 0);
        parcel.writeParcelable(this.k, 0);
        parcel.writeParcelable(this.j, 0);
    }

    public CalendarConstraints(@NonNull Month month, @NonNull Month month2, @NonNull DateValidator dateValidator, @Nullable Month month3) {
        this.h = month;
        this.i = month2;
        this.k = month3;
        this.j = dateValidator;
        if (month3 != null && month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.m = month.k(month2) + 1;
        this.l = (month2.j - month.j) + 1;
    }

    /* loaded from: classes10.dex */
    public static final class Builder {
        public static final long e = j.a(Month.b(1900, 0).m);
        public static final long f = j.a(Month.b(2100, 11).m);

        /* renamed from: a  reason: collision with root package name */
        public long f10275a;
        public long b;
        public Long c;
        public DateValidator d;

        public Builder() {
            this.f10275a = e;
            this.b = f;
            this.d = DateValidatorPointForward.from(Long.MIN_VALUE);
        }

        @NonNull
        public CalendarConstraints build() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.d);
            Month c = Month.c(this.f10275a);
            Month c2 = Month.c(this.b);
            DateValidator dateValidator = (DateValidator) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l = this.c;
            return new CalendarConstraints(c, c2, dateValidator, l == null ? null : Month.c(l.longValue()), null);
        }

        @NonNull
        public Builder setEnd(long j) {
            this.b = j;
            return this;
        }

        @NonNull
        public Builder setOpenAt(long j) {
            this.c = Long.valueOf(j);
            return this;
        }

        @NonNull
        public Builder setStart(long j) {
            this.f10275a = j;
            return this;
        }

        @NonNull
        public Builder setValidator(@NonNull DateValidator dateValidator) {
            this.d = dateValidator;
            return this;
        }

        public Builder(@NonNull CalendarConstraints calendarConstraints) {
            this.f10275a = e;
            this.b = f;
            this.d = DateValidatorPointForward.from(Long.MIN_VALUE);
            this.f10275a = calendarConstraints.h.m;
            this.b = calendarConstraints.i.m;
            this.c = Long.valueOf(calendarConstraints.k.m);
            this.d = calendarConstraints.j;
        }
    }
}
