package com.realsil.sdk.core.utility;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class ParcelUtils {
    public static <T extends Parcelable> T fromBytes(@NonNull byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T t = (T) obtain.readParcelable(ParcelUtils.class.getClassLoader());
        obtain.recycle();
        return t;
    }

    public static byte[] toBytes(@NonNull Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        obtain.writeParcelable(parcelable, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
