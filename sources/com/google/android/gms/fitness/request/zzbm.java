package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcm;
import com.google.android.gms.internal.fitness.zzcn;
@ShowFirstParty
@SafeParcelable.Class(creator = "UnsubscribeRequestCreator")
@SafeParcelable.Reserved({4, 1000})
/* loaded from: classes6.dex */
public final class zzbm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbm> CREATOR = new zzbo();
    @Nullable
    @SafeParcelable.Field(getter = "getDataType", id = 1)
    public final DataType h;
    @Nullable
    @SafeParcelable.Field(getter = "getDataSource", id = 2)
    public final DataSource i;
    @Nullable
    @SafeParcelable.Field(getter = "getCallbackBinder", id = 3, type = "android.os.IBinder")
    public final zzcn j;

    @SafeParcelable.Constructor
    public zzbm(@SafeParcelable.Param(id = 1) DataType dataType, @SafeParcelable.Param(id = 2) DataSource dataSource, @SafeParcelable.Param(id = 3) IBinder iBinder) {
        this(dataType, dataSource, zzcm.zzj(iBinder));
    }

    public final boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbm) {
            zzbm zzbmVar = (zzbm) obj;
            return Objects.equal(this.i, zzbmVar.i) && Objects.equal(this.h, zzbmVar.h);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.i, this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        zzcn zzcnVar = this.j;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcnVar == null ? null : zzcnVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public zzbm(@Nullable DataType dataType, @Nullable DataSource dataSource, @Nullable zzcn zzcnVar) {
        Preconditions.checkArgument((dataType == null) != (dataSource == null), "Must specify exactly one of dataType and dataSource.");
        this.h = dataType;
        this.i = dataSource;
        this.j = zzcnVar;
    }
}
