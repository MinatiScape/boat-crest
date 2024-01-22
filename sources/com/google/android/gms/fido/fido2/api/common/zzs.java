package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.HashSet;
import java.util.List;
@SafeParcelable.Class(creator = "CableAuthenticationExtensionCreator")
/* loaded from: classes6.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    @NonNull
    @SafeParcelable.Field(getter = "getCableAuthentication", id = 1)
    public final List h;

    @SafeParcelable.Constructor
    public zzs(@NonNull @SafeParcelable.Param(id = 1) List list) {
        this.h = (List) Preconditions.checkNotNull(list);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzs) {
            zzs zzsVar = (zzs) obj;
            return this.h.containsAll(zzsVar.h) && zzsVar.h.containsAll(this.h);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(new HashSet(this.h));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.h, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
