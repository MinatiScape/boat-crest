package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
@SafeParcelable.Class(creator = "StatusCreator")
/* loaded from: classes6.dex */
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    @SafeParcelable.VersionField(id = 1000)
    public final int h;
    @SafeParcelable.Field(getter = "getStatusCode", id = 1)
    public final int i;
    @Nullable
    @SafeParcelable.Field(getter = "getStatusMessage", id = 2)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 3)
    public final PendingIntent k;
    @Nullable
    @SafeParcelable.Field(getter = "getConnectionResult", id = 4)
    public final ConnectionResult l;
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS_CACHE = new Status(-1);
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_SUCCESS = new Status(0);
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED = new Status(14);
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR = new Status(8);
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_TIMEOUT = new Status(15);
    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public static final Status RESULT_CANCELED = new Status(16);
    @NonNull
    @ShowFirstParty
    public static final Status zza = new Status(17);
    @NonNull
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT = new Status(18);
    @NonNull
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    public Status(int i) {
        this(i, (String) null);
    }

    @SafeParcelable.Constructor
    public Status(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) int i2, @Nullable @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) PendingIntent pendingIntent, @Nullable @SafeParcelable.Param(id = 4) ConnectionResult connectionResult) {
        this.h = i;
        this.i = i2;
        this.j = str;
        this.k = pendingIntent;
        this.l = connectionResult;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Status) {
            Status status = (Status) obj;
            return this.h == status.h && this.i == status.i && Objects.equal(this.j, status.j) && Objects.equal(this.k, status.k) && Objects.equal(this.l, status.l);
        }
        return false;
    }

    @Nullable
    public ConnectionResult getConnectionResult() {
        return this.l;
    }

    @Nullable
    public PendingIntent getResolution() {
        return this.k;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    @CanIgnoreReturnValue
    public Status getStatus() {
        return this;
    }

    @ResultIgnorabilityUnspecified
    public int getStatusCode() {
        return this.i;
    }

    @Nullable
    public String getStatusMessage() {
        return this.j;
    }

    @VisibleForTesting
    public boolean hasResolution() {
        return this.k != null;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Integer.valueOf(this.i), this.j, this.k, this.l);
    }

    public boolean isCanceled() {
        return this.i == 16;
    }

    public boolean isInterrupted() {
        return this.i == 14;
    }

    @CheckReturnValue
    public boolean isSuccess() {
        return this.i <= 0;
    }

    public void startResolutionForResult(@NonNull Activity activity, int i) throws IntentSender.SendIntentException {
        if (hasResolution()) {
            PendingIntent pendingIntent = this.k;
            Preconditions.checkNotNull(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    @NonNull
    public String toString() {
        Objects.ToStringHelper stringHelper = Objects.toStringHelper(this);
        stringHelper.add("statusCode", zza());
        stringHelper.add("resolution", this.k);
        return stringHelper.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getStatusCode());
        SafeParcelWriter.writeString(parcel, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.k, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getConnectionResult(), i, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final String zza() {
        String str = this.j;
        return str != null ? str : CommonStatusCodes.getStatusCodeString(this.i);
    }

    public Status(int i, @Nullable String str) {
        this(1, i, str, null, null);
    }

    public Status(int i, @Nullable String str, @Nullable PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent, null);
    }

    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str) {
        this(connectionResult, str, 17);
    }

    @KeepForSdk
    @Deprecated
    public Status(@NonNull ConnectionResult connectionResult, @NonNull String str, int i) {
        this(1, i, str, connectionResult.getResolution(), connectionResult);
    }
}
