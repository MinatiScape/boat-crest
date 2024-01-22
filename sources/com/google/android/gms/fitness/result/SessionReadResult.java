package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.zzae;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "SessionReadResultCreator")
@SafeParcelable.Reserved({4, 1000})
/* loaded from: classes6.dex */
public class SessionReadResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<SessionReadResult> CREATOR = new zzh();
    @SafeParcelable.Field(getter = "getSessions", id = 1)
    public final List<Session> h;
    @SafeParcelable.Field(getter = "getSessionDataSets", id = 2)
    public final List<zzae> i;
    @SafeParcelable.Field(getter = "getStatus", id = 3)
    public final Status j;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public SessionReadResult(@NonNull @SafeParcelable.Param(id = 1) List<Session> list, @NonNull @SafeParcelable.Param(id = 2) List<zzae> list2, @NonNull @SafeParcelable.Param(id = 3) Status status) {
        this.h = list;
        this.i = Collections.unmodifiableList(list2);
        this.j = status;
    }

    @NonNull
    @ShowFirstParty
    public static SessionReadResult zze(@NonNull Status status) {
        return new SessionReadResult(new ArrayList(), new ArrayList(), status);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionReadResult) {
            SessionReadResult sessionReadResult = (SessionReadResult) obj;
            return this.j.equals(sessionReadResult.j) && Objects.equal(this.h, sessionReadResult.h) && Objects.equal(this.i, sessionReadResult.i);
        }
        return false;
    }

    @NonNull
    public List<DataSet> getDataSet(@NonNull Session session, @NonNull DataType dataType) {
        Preconditions.checkArgument(this.h.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.i) {
            if (Objects.equal(session, zzaeVar.getSession()) && dataType.equals(zzaeVar.getDataSet().getDataType())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }

    @NonNull
    public List<Session> getSessions() {
        return this.h;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.j, this.h, this.i);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(NotificationCompat.CATEGORY_STATUS, this.j).add("sessions", this.h).add("sessionDataSets", this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getSessions(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public List<DataSet> getDataSet(@NonNull Session session) {
        Preconditions.checkArgument(this.h.contains(session), "Attempting to read data for session %s which was not returned", session);
        ArrayList arrayList = new ArrayList();
        for (zzae zzaeVar : this.i) {
            if (Objects.equal(session, zzaeVar.getSession())) {
                arrayList.add(zzaeVar.getDataSet());
            }
        }
        return arrayList;
    }
}
