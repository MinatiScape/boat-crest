package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public final class zzc implements Parcelable.Creator<CircleOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ CircleOptions createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        LatLng latLng = null;
        ArrayList arrayList = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        double d = 0.0d;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    latLng = (LatLng) SafeParcelReader.createParcelable(parcel, readHeader, LatLng.CREATOR);
                    break;
                case 3:
                    d = SafeParcelReader.readDouble(parcel, readHeader);
                    break;
                case 4:
                    f = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 5:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    f2 = SafeParcelReader.readFloat(parcel, readHeader);
                    break;
                case 8:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 9:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 10:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, PatternItem.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new CircleOptions(latLng, d, f, i, i2, f2, z, z2, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CircleOptions[] newArray(int i) {
        return new CircleOptions[i];
    }
}
