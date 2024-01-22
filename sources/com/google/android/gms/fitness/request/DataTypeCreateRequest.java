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
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SafeParcelable.Class(creator = "DataTypeCreateRequestCreator")
@SafeParcelable.Reserved({4, 1000})
@Deprecated
/* loaded from: classes6.dex */
public class DataTypeCreateRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzs();
    @SafeParcelable.Field(getter = "getName", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getFields", id = 2)
    public final List<Field> i;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final com.google.android.gms.internal.fitness.zzbi j;

    @SafeParcelable.Constructor
    public DataTypeCreateRequest(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) List<Field> list, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder) {
        this.h = str;
        this.i = Collections.unmodifiableList(list);
        this.j = iBinder == null ? null : com.google.android.gms.internal.fitness.zzbl.zze(iBinder);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataTypeCreateRequest) {
            DataTypeCreateRequest dataTypeCreateRequest = (DataTypeCreateRequest) obj;
            return Objects.equal(this.h, dataTypeCreateRequest.h) && Objects.equal(this.i, dataTypeCreateRequest.i);
        }
        return false;
    }

    @NonNull
    public List<Field> getFields() {
        return this.i;
    }

    @NonNull
    public String getName() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.h).add("fields", this.i).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        com.google.android.gms.internal.fitness.zzbi zzbiVar = this.j;
        SafeParcelWriter.writeIBinder(parcel, 3, zzbiVar == null ? null : zzbiVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public String f8450a;
        public final List<Field> b = new ArrayList();

        @NonNull
        public Builder addField(@NonNull Field field) {
            if (!this.b.contains(field)) {
                this.b.add(field);
            }
            return this;
        }

        @NonNull
        public DataTypeCreateRequest build() {
            Preconditions.checkState(this.f8450a != null, "Must set the name");
            Preconditions.checkState(!this.b.isEmpty(), "Must specify the data fields");
            return new DataTypeCreateRequest(this);
        }

        @NonNull
        public Builder setName(@NonNull String str) {
            this.f8450a = str;
            return this;
        }

        @NonNull
        public Builder addField(@NonNull String str, int i) {
            Preconditions.checkArgument((str == null || str.isEmpty()) ? false : true, "Invalid name specified");
            return addField(new Field(str, i));
        }
    }

    public DataTypeCreateRequest(Builder builder) {
        this(builder.f8450a, builder.b, (com.google.android.gms.internal.fitness.zzbi) null);
    }

    public DataTypeCreateRequest(DataTypeCreateRequest dataTypeCreateRequest, com.google.android.gms.internal.fitness.zzbi zzbiVar) {
        this(dataTypeCreateRequest.h, dataTypeCreateRequest.i, zzbiVar);
    }

    public DataTypeCreateRequest(String str, List<Field> list, @Nullable com.google.android.gms.internal.fitness.zzbi zzbiVar) {
        this.h = str;
        this.i = Collections.unmodifiableList(list);
        this.j = zzbiVar;
    }
}
