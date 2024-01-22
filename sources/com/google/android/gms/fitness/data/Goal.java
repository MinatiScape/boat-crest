package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.fitness.zzko;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
@SafeParcelable.Class(creator = "GoalCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class Goal extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Goal> CREATOR = new zzt();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    @SafeParcelable.Field(getter = "getCreateTimeNanos", id = 1)
    public final long h;
    @SafeParcelable.Field(getter = "getExpireTimeNanos", id = 2)
    public final long i;
    @SafeParcelable.Field(getter = "getActivities", id = 3, type = "java.util.List")
    public final List<Integer> j;
    @SafeParcelable.Field(getter = "getRecurrence", id = 4)
    public final Recurrence k;
    @SafeParcelable.Field(getter = "getObjectiveType", id = 5)
    public final int l;
    @SafeParcelable.Field(getter = "getMetricObjectiveWithOutChecking", id = 6)
    public final MetricObjective m;
    @SafeParcelable.Field(getter = "getDurationObjectiveWithOutChecking", id = 7)
    public final DurationObjective n;
    @SafeParcelable.Field(getter = "getFrequencyObjectiveWithOutChecking", id = 8)
    public final FrequencyObjective o;

    @SafeParcelable.Class(creator = "FrequencyObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* loaded from: classes6.dex */
    public static class FrequencyObjective extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<FrequencyObjective> CREATOR = new zzs();
        @SafeParcelable.Field(getter = "getFrequency", id = 1)
        public final int h;

        @SafeParcelable.Constructor
        public FrequencyObjective(@SafeParcelable.Param(id = 1) int i) {
            this.h = i;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof FrequencyObjective) && this.h == ((FrequencyObjective) obj).h;
        }

        public int getFrequency() {
            return this.h;
        }

        public int hashCode() {
            return this.h;
        }

        @NonNull
        public String toString() {
            return Objects.toStringHelper(this).add("frequency", Integer.valueOf(this.h)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getFrequency());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    /* loaded from: classes6.dex */
    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(@NonNull String str) {
            super(str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface ObjectiveType {
    }

    @SafeParcelable.Class(creator = "RecurrenceCreator")
    @SafeParcelable.Reserved({1000})
    /* loaded from: classes6.dex */
    public static class Recurrence extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<Recurrence> CREATOR = new zzac();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        @SafeParcelable.Field(getter = "getCount", id = 1)
        public final int h;
        @SafeParcelable.Field(getter = "getUnit", id = 2)
        public final int i;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes6.dex */
        public @interface RecurrenceUnit {
        }

        @SafeParcelable.Constructor
        public Recurrence(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2) {
            this.h = i;
            Preconditions.checkState(i2 > 0 && i2 <= 3);
            this.i = i2;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Recurrence) {
                Recurrence recurrence = (Recurrence) obj;
                return this.h == recurrence.h && this.i == recurrence.i;
            }
            return false;
        }

        public int getCount() {
            return this.h;
        }

        public int getUnit() {
            return this.i;
        }

        public int hashCode() {
            return this.i;
        }

        @NonNull
        public String toString() {
            String str;
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("count", Integer.valueOf(this.h));
            int i = this.i;
            if (i == 1) {
                str = WeatherCriteria.UNIT_TYPE_DAY;
            } else if (i == 2) {
                str = "week";
            } else if (i != 3) {
                throw new IllegalArgumentException("invalid unit value");
            } else {
                str = "month";
            }
            return add.add("unit", str).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getCount());
            SafeParcelWriter.writeInt(parcel, 2, getUnit());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    @SafeParcelable.Constructor
    public Goal(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) long j2, @SafeParcelable.Param(id = 3) List<Integer> list, @SafeParcelable.Param(id = 4) Recurrence recurrence, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) MetricObjective metricObjective, @SafeParcelable.Param(id = 7) DurationObjective durationObjective, @SafeParcelable.Param(id = 8) FrequencyObjective frequencyObjective) {
        this.h = j;
        this.i = j2;
        this.j = list;
        this.k = recurrence;
        this.l = i;
        this.m = metricObjective;
        this.n = durationObjective;
        this.o = frequencyObjective;
    }

    public static String a(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        return "frequency";
                    }
                    throw new IllegalArgumentException("invalid objective type value");
                }
                return "duration";
            }
            return DirectionsCriteria.METRIC;
        }
        return "unknown";
    }

    public final void b(int i) {
        if (i != this.l) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", a(this.l), a(i)));
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Goal) {
            Goal goal = (Goal) obj;
            return this.h == goal.h && this.i == goal.i && Objects.equal(this.j, goal.j) && Objects.equal(this.k, goal.k) && this.l == goal.l && Objects.equal(this.m, goal.m) && Objects.equal(this.n, goal.n) && Objects.equal(this.o, goal.o);
        }
        return false;
    }

    @Nullable
    public String getActivityName() {
        if (this.j.isEmpty() || this.j.size() > 1) {
            return null;
        }
        return zzko.getName(this.j.get(0).intValue());
    }

    public long getCreateTime(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.h, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public DurationObjective getDurationObjective() {
        b(2);
        return this.n;
    }

    public long getEndTime(@NonNull Calendar calendar, @NonNull TimeUnit timeUnit) {
        if (this.k != null) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());
            int i = this.k.i;
            if (i == 1) {
                calendar2.add(5, 1);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            } else if (i == 2) {
                calendar2.add(4, 1);
                calendar2.set(7, 2);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            } else if (i != 3) {
                int i2 = this.k.i;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Invalid unit ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            } else {
                calendar2.add(2, 1);
                calendar2.set(5, 1);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            }
        }
        return timeUnit.convert(this.i, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public FrequencyObjective getFrequencyObjective() {
        b(3);
        return this.o;
    }

    @NonNull
    public MetricObjective getMetricObjective() {
        b(1);
        return this.m;
    }

    public int getObjectiveType() {
        return this.l;
    }

    @Nullable
    public Recurrence getRecurrence() {
        return this.k;
    }

    public long getStartTime(@NonNull Calendar calendar, @NonNull TimeUnit timeUnit) {
        if (this.k != null) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());
            int i = this.k.i;
            if (i == 1) {
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            } else if (i == 2) {
                calendar2.set(7, 2);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            } else if (i != 3) {
                int i2 = this.k.i;
                StringBuilder sb = new StringBuilder(24);
                sb.append("Invalid unit ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            } else {
                calendar2.set(5, 1);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            }
        }
        return timeUnit.convert(this.h, TimeUnit.NANOSECONDS);
    }

    public int hashCode() {
        return this.l;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("activity", getActivityName()).add("recurrence", this.k).add("metricObjective", this.m).add("durationObjective", this.n).add("frequencyObjective", this.o).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeList(parcel, 3, this.j, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getRecurrence(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getObjectiveType());
        SafeParcelWriter.writeParcelable(parcel, 6, this.m, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.n, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.o, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Class(creator = "DurationObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* loaded from: classes6.dex */
    public static class DurationObjective extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<DurationObjective> CREATOR = new zzp();
        @SafeParcelable.Field(getter = "getDuration", id = 1)
        public final long h;

        @SafeParcelable.Constructor
        public DurationObjective(@SafeParcelable.Param(id = 1) long j) {
            this.h = j;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof DurationObjective) && this.h == ((DurationObjective) obj).h;
        }

        public long getDuration(@NonNull TimeUnit timeUnit) {
            return timeUnit.convert(this.h, TimeUnit.NANOSECONDS);
        }

        public int hashCode() {
            return (int) this.h;
        }

        @NonNull
        public String toString() {
            return Objects.toStringHelper(this).add("duration", Long.valueOf(this.h)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeLong(parcel, 1, this.h);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public DurationObjective(long j, @NonNull TimeUnit timeUnit) {
            this(timeUnit.toNanos(j));
        }
    }

    @SafeParcelable.Class(creator = "MetricObjectiveCreator")
    @SafeParcelable.Reserved({1000})
    /* loaded from: classes6.dex */
    public static class MetricObjective extends AbstractSafeParcelable {
        @NonNull
        public static final Parcelable.Creator<MetricObjective> CREATOR = new zzx();
        @SafeParcelable.Field(getter = "getDataTypeName", id = 1)
        public final String h;
        @SafeParcelable.Field(getter = "getValue", id = 2)
        public final double i;
        @SafeParcelable.Field(getter = "getInitialValue", id = 3)
        public final double j;

        @ShowFirstParty
        @SafeParcelable.Constructor
        public MetricObjective(@NonNull @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) double d, @SafeParcelable.Param(id = 3) double d2) {
            this.h = str;
            this.i = d;
            this.j = d2;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof MetricObjective) {
                MetricObjective metricObjective = (MetricObjective) obj;
                return Objects.equal(this.h, metricObjective.h) && this.i == metricObjective.i && this.j == metricObjective.j;
            }
            return false;
        }

        @NonNull
        public String getDataTypeName() {
            return this.h;
        }

        public double getValue() {
            return this.i;
        }

        public int hashCode() {
            return this.h.hashCode();
        }

        @NonNull
        public String toString() {
            return Objects.toStringHelper(this).add("dataTypeName", this.h).add("value", Double.valueOf(this.i)).add("initialValue", Double.valueOf(this.j)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getDataTypeName(), false);
            SafeParcelWriter.writeDouble(parcel, 2, getValue());
            SafeParcelWriter.writeDouble(parcel, 3, this.j);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public MetricObjective(@NonNull String str, double d) {
            this(str, d, 0.0d);
        }
    }
}
