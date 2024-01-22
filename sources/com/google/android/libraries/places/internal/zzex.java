package com.google.android.libraries.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
final class zzex implements Parcelable.Creator<zzey> {
    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ zzey createFromParcel(Parcel parcel) {
        return new zzey(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    @NonNull
    public final /* synthetic */ zzey[] newArray(int i) {
        return new zzey[i];
    }
}
