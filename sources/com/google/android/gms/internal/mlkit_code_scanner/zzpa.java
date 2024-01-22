package com.google.android.gms.internal.mlkit_code_scanner;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes8.dex */
public final class zzpa implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        Point[] pointArr = null;
        zzos zzosVar = null;
        zzov zzovVar = null;
        zzow zzowVar = null;
        zzoy zzoyVar = null;
        zzox zzoxVar = null;
        zzot zzotVar = null;
        zzop zzopVar = null;
        zzoq zzoqVar = null;
        zzor zzorVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    bArr = SafeParcelReader.createByteArray(parcel, readHeader);
                    break;
                case 5:
                    pointArr = (Point[]) SafeParcelReader.createTypedArray(parcel, readHeader, Point.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, readHeader);
                    break;
                case 7:
                    zzosVar = (zzos) SafeParcelReader.createParcelable(parcel, readHeader, zzos.CREATOR);
                    break;
                case 8:
                    zzovVar = (zzov) SafeParcelReader.createParcelable(parcel, readHeader, zzov.CREATOR);
                    break;
                case 9:
                    zzowVar = (zzow) SafeParcelReader.createParcelable(parcel, readHeader, zzow.CREATOR);
                    break;
                case 10:
                    zzoyVar = (zzoy) SafeParcelReader.createParcelable(parcel, readHeader, zzoy.CREATOR);
                    break;
                case 11:
                    zzoxVar = (zzox) SafeParcelReader.createParcelable(parcel, readHeader, zzox.CREATOR);
                    break;
                case 12:
                    zzotVar = (zzot) SafeParcelReader.createParcelable(parcel, readHeader, zzot.CREATOR);
                    break;
                case 13:
                    zzopVar = (zzop) SafeParcelReader.createParcelable(parcel, readHeader, zzop.CREATOR);
                    break;
                case 14:
                    zzoqVar = (zzoq) SafeParcelReader.createParcelable(parcel, readHeader, zzoq.CREATOR);
                    break;
                case 15:
                    zzorVar = (zzor) SafeParcelReader.createParcelable(parcel, readHeader, zzor.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzoz(i, str, str2, bArr, pointArr, i2, zzosVar, zzovVar, zzowVar, zzoyVar, zzoxVar, zzotVar, zzopVar, zzoqVar, zzorVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzoz[i];
    }
}
