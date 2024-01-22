package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SafeParcelable.Class(creator = "ActivityTransitionCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes10.dex */
public class ActivityTransition extends AbstractSafeParcelable {
    public static final int ACTIVITY_TRANSITION_ENTER = 0;
    public static final int ACTIVITY_TRANSITION_EXIT = 1;
    @NonNull
    public static final Parcelable.Creator<ActivityTransition> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getActivityType", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getTransitionType", id = 2)
    public final int i;

    /* loaded from: classes10.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f10030a = -1;
        public int b = -1;

        @NonNull
        public ActivityTransition build() {
            Preconditions.checkState(this.f10030a != -1, "Activity type not set.");
            Preconditions.checkState(this.b != -1, "Activity transition type not set.");
            return new ActivityTransition(this.f10030a, this.b);
        }

        @NonNull
        public Builder setActivityTransition(int i) {
            ActivityTransition.zza(i);
            this.b = i;
            return this;
        }

        @NonNull
        public Builder setActivityType(int i) {
            this.f10030a = i;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes10.dex */
    public @interface SupportedActivityTransition {
    }

    @SafeParcelable.Constructor
    public ActivityTransition(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2) {
        this.h = i;
        this.i = i2;
    }

    public static void zza(int i) {
        boolean z = true;
        if (i < 0 || i > 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Transition type " + i + " is not valid.");
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ActivityTransition) {
            ActivityTransition activityTransition = (ActivityTransition) obj;
            return this.h == activityTransition.h && this.i == activityTransition.i;
        }
        return false;
    }

    public int getActivityType() {
        return this.h;
    }

    public int getTransitionType() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Integer.valueOf(this.i));
    }

    @NonNull
    public String toString() {
        int i = this.h;
        int i2 = this.i;
        return "ActivityTransition [mActivityType=" + i + ", mTransitionType=" + i2 + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getActivityType());
        SafeParcelWriter.writeInt(parcel, 2, getTransitionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
