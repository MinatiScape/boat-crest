package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.Goal;
import java.util.List;
@SafeParcelable.Class(creator = "GoalsResultCreator")
@SafeParcelable.Reserved({1000})
@Deprecated
/* loaded from: classes6.dex */
public class GoalsResult extends AbstractSafeParcelable implements Result {
    @NonNull
    public static final Parcelable.Creator<GoalsResult> CREATOR = new zzf();
    @SafeParcelable.Field(getter = "getStatus", id = 1)
    public final Status h;
    @SafeParcelable.Field(getter = "getGoals", id = 2)
    public final List<Goal> i;

    @ShowFirstParty
    @SafeParcelable.Constructor
    public GoalsResult(@NonNull @SafeParcelable.Param(id = 1) Status status, @NonNull @SafeParcelable.Param(id = 2) List<Goal> list) {
        this.h = status;
        this.i = list;
    }

    @NonNull
    public List<Goal> getGoals() {
        return this.i;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getGoals(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
