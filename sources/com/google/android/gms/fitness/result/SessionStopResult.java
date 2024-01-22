package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Session;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "SessionStopResultCreator")
@SafeParcelable.Reserved({4, 1000})
/* loaded from: classes6.dex */
public class SessionStopResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<SessionStopResult> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getStatus", id = 2)
    public final Status h;
    @SafeParcelable.Field(getter = "getSessions", id = 3)
    public final List<Session> i;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SessionStopResult(@NonNull @SafeParcelable.Param(id = 2) Status status, @NonNull @SafeParcelable.Param(id = 3) List<Session> list) {
        this.h = status;
        this.i = Collections.unmodifiableList(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionStopResult) {
            SessionStopResult sessionStopResult = (SessionStopResult) obj;
            return this.h.equals(sessionStopResult.h) && Objects.equal(this.i, sessionStopResult.i);
        }
        return false;
    }

    @NonNull
    public List<Session> getSessions() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.h).add("sessions", this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 3, getSessions(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
