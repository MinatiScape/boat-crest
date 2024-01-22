package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SavePasswordResultCreator")
/* loaded from: classes6.dex */
public class SavePasswordResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SavePasswordResult> CREATOR = new zbn();
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    public final PendingIntent h;

    @SafeParcelable.Constructor
    public SavePasswordResult(@NonNull @SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.h = (PendingIntent) Preconditions.checkNotNull(pendingIntent);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SavePasswordResult) {
            return Objects.equal(this.h, ((SavePasswordResult) obj).h);
        }
        return false;
    }

    @NonNull
    public PendingIntent getPendingIntent() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
