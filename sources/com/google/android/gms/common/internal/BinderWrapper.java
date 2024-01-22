package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
@KeepForSdk
@KeepName
/* loaded from: classes6.dex */
public final class BinderWrapper implements Parcelable {
    @NonNull
    public static final Parcelable.Creator<BinderWrapper> CREATOR = new m();
    public final IBinder h;

    @KeepForSdk
    public BinderWrapper(@NonNull IBinder iBinder) {
        this.h = iBinder;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeStrongBinder(this.h);
    }
}
