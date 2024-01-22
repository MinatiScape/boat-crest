package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "BeginSignInResultCreator")
/* loaded from: classes6.dex */
public final class BeginSignInResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<BeginSignInResult> CREATOR = new zbb();
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    public final PendingIntent h;

    @SafeParcelable.Constructor
    public BeginSignInResult(@NonNull @SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.h = (PendingIntent) Preconditions.checkNotNull(pendingIntent);
    }

    @NonNull
    public PendingIntent getPendingIntent() {
        return this.h;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
