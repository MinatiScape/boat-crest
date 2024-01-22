package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@SafeParcelable.Class(creator = "RecordConsentByConsentResultResponseCreator")
/* loaded from: classes10.dex */
public final class zag extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zag> CREATOR = new zah();
    @SafeParcelable.Field(getter = "getGrantedScopes", id = 1)
    public final List h;
    @Nullable
    @SafeParcelable.Field(getter = "getToken", id = 2)
    public final String i;

    @SafeParcelable.Constructor
    public zag(@SafeParcelable.Param(id = 1) List list, @Nullable @SafeParcelable.Param(id = 2) String str) {
        this.h = list;
        this.i = str;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.i != null ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringList(parcel, 1, this.h, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
