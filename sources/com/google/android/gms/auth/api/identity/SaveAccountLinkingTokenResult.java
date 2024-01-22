package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SaveAccountLinkingTokenResultCreator")
/* loaded from: classes6.dex */
public class SaveAccountLinkingTokenResult extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SaveAccountLinkingTokenResult> CREATOR = new zbl();
    @Nullable
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    public final PendingIntent h;

    @SafeParcelable.Constructor
    public SaveAccountLinkingTokenResult(@Nullable @SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.h = pendingIntent;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SaveAccountLinkingTokenResult) {
            return Objects.equal(this.h, ((SaveAccountLinkingTokenResult) obj).h);
        }
        return false;
    }

    @Nullable
    public PendingIntent getPendingIntent() {
        return this.h;
    }

    public boolean hasResolution() {
        return this.h != null;
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
