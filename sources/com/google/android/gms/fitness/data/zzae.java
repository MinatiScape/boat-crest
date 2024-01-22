package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SessionDataSetCreator")
@SafeParcelable.Reserved({1000})
/* loaded from: classes6.dex */
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR = new zzag();
    @SafeParcelable.Field(getter = "getSession", id = 1)
    public final Session h;
    @SafeParcelable.Field(getter = "getDataSet", id = 2)
    public final DataSet i;

    @SafeParcelable.Constructor
    public zzae(@SafeParcelable.Param(id = 1) Session session, @SafeParcelable.Param(id = 2) DataSet dataSet) {
        this.h = session;
        this.i = dataSet;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzae) {
            zzae zzaeVar = (zzae) obj;
            return Objects.equal(this.h, zzaeVar.h) && Objects.equal(this.i, zzaeVar.i);
        }
        return false;
    }

    public final DataSet getDataSet() {
        return this.i;
    }

    public final Session getSession() {
        return this.h;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("session", this.h).add("dataSet", this.i).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.h, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.i, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
