package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SafeParcelable.Class(creator = "ModuleAvailabilityResponseCreator")
/* loaded from: classes6.dex */
public class ModuleAvailabilityResponse extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<ModuleAvailabilityResponse> CREATOR = new zaa();
    @SafeParcelable.Field(getter = "areModulesAvailable", id = 1)
    public final boolean h;
    @SafeParcelable.Field(getter = "getAvailabilityStatus", id = 2)
    public final int i;

    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes6.dex */
    public @interface AvailabilityStatus {
        public static final int STATUS_ALREADY_AVAILABLE = 0;
        public static final int STATUS_READY_TO_DOWNLOAD = 1;
        public static final int STATUS_UNKNOWN_MODULE = 2;
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleAvailabilityResponse(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) int i) {
        this.h = z;
        this.i = i;
    }

    public boolean areModulesAvailable() {
        return this.h;
    }

    @AvailabilityStatus
    public int getAvailabilityStatus() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, areModulesAvailable());
        SafeParcelWriter.writeInt(parcel, 2, getAvailabilityStatus());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
