package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzko;
import java.util.ArrayList;
import java.util.List;
@SafeParcelable.Class(creator = "GoalsReadRequestCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public class GoalsReadRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GoalsReadRequest> CREATOR = new zzae();
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 1, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbn h;
    @SafeParcelable.Field(getter = "getDataTypes", id = 2, type = "java.util.List")
    public final List<DataType> i;
    @SafeParcelable.Field(getter = "getObjectiveTypeList", id = 3, type = "java.util.List")
    public final List<Integer> j;
    @SafeParcelable.Field(getter = "getActivities", id = 4, type = "java.util.List")
    public final List<Integer> k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List<DataType> f8453a = new ArrayList();
        public final List<Integer> b = new ArrayList();
        public final List<Integer> c = new ArrayList();

        @NonNull
        public Builder addActivity(@NonNull String str) {
            int zzo = zzko.zzo(str);
            Preconditions.checkState(zzo != 4, "Attempting to add an unknown activity");
            com.google.android.gms.internal.fitness.zzh.zza(Integer.valueOf(zzo), this.c);
            return this;
        }

        @NonNull
        public Builder addDataType(@NonNull DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.f8453a.contains(dataType)) {
                this.f8453a.add(dataType);
            }
            return this;
        }

        @NonNull
        public Builder addObjectiveType(int i) {
            boolean z = true;
            if (i != 1 && i != 2 && i != 3) {
                z = false;
            }
            Preconditions.checkState(z, "Attempting to add an invalid objective type");
            if (!this.b.contains(Integer.valueOf(i))) {
                this.b.add(Integer.valueOf(i));
            }
            return this;
        }

        @NonNull
        public GoalsReadRequest build() {
            Preconditions.checkState(!this.f8453a.isEmpty(), "At least one data type should be specified.");
            return new GoalsReadRequest(this);
        }
    }

    @SafeParcelable.Constructor
    public GoalsReadRequest(@Nullable @SafeParcelable.Param(id = 1) IBinder iBinder, @SafeParcelable.Param(id = 2) List<DataType> list, @SafeParcelable.Param(id = 3) List<Integer> list2, @SafeParcelable.Param(id = 4) List<Integer> list3) {
        this.h = iBinder == null ? null : com.google.android.gms.internal.fitness.zzbm.zzf(iBinder);
        this.i = list;
        this.j = list2;
        this.k = list3;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoalsReadRequest) {
            GoalsReadRequest goalsReadRequest = (GoalsReadRequest) obj;
            return Objects.equal(this.i, goalsReadRequest.i) && Objects.equal(this.j, goalsReadRequest.j) && Objects.equal(this.k, goalsReadRequest.k);
        }
        return false;
    }

    @Nullable
    public List<String> getActivityNames() {
        if (this.k.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.k) {
            arrayList.add(zzko.getName(num.intValue()));
        }
        return arrayList;
    }

    @NonNull
    public List<DataType> getDataTypes() {
        return this.i;
    }

    @Nullable
    public List<Integer> getObjectiveTypes() {
        if (this.j.isEmpty()) {
            return null;
        }
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, this.j, getActivityNames());
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("dataTypes", this.i).add("objectiveTypes", this.j).add("activities", getActivityNames()).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        com.google.android.gms.internal.fitness.zzbn zzbnVar = this.h;
        SafeParcelWriter.writeIBinder(parcel, 1, zzbnVar == null ? null : zzbnVar.asBinder(), false);
        SafeParcelWriter.writeList(parcel, 2, getDataTypes(), false);
        SafeParcelWriter.writeList(parcel, 3, this.j, false);
        SafeParcelWriter.writeList(parcel, 4, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public GoalsReadRequest(Builder builder) {
        this((com.google.android.gms.internal.fitness.zzbn) null, builder.f8453a, builder.b, builder.c);
    }

    public GoalsReadRequest(GoalsReadRequest goalsReadRequest, com.google.android.gms.internal.fitness.zzbn zzbnVar) {
        this(zzbnVar, goalsReadRequest.getDataTypes(), goalsReadRequest.j, goalsReadRequest.k);
    }

    public GoalsReadRequest(@Nullable com.google.android.gms.internal.fitness.zzbn zzbnVar, List<DataType> list, List<Integer> list2, List<Integer> list3) {
        this(zzbnVar == null ? null : zzbnVar.asBinder(), list, list2, list3);
    }
}
