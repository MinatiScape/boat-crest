package com.google.android.gms.internal.mlkit_vision_barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes9.dex */
public final class zzvk implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        Point[] pointArr = null;
        zzvc zzvcVar = null;
        zzvf zzvfVar = null;
        zzvg zzvgVar = null;
        zzvi zzviVar = null;
        zzvh zzvhVar = null;
        zzvd zzvdVar = null;
        zzuz zzuzVar = null;
        zzva zzvaVar = null;
        zzvb zzvbVar = null;
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
                    zzvcVar = (zzvc) SafeParcelReader.createParcelable(parcel, readHeader, zzvc.CREATOR);
                    break;
                case 8:
                    zzvfVar = (zzvf) SafeParcelReader.createParcelable(parcel, readHeader, zzvf.CREATOR);
                    break;
                case 9:
                    zzvgVar = (zzvg) SafeParcelReader.createParcelable(parcel, readHeader, zzvg.CREATOR);
                    break;
                case 10:
                    zzviVar = (zzvi) SafeParcelReader.createParcelable(parcel, readHeader, zzvi.CREATOR);
                    break;
                case 11:
                    zzvhVar = (zzvh) SafeParcelReader.createParcelable(parcel, readHeader, zzvh.CREATOR);
                    break;
                case 12:
                    zzvdVar = (zzvd) SafeParcelReader.createParcelable(parcel, readHeader, zzvd.CREATOR);
                    break;
                case 13:
                    zzuzVar = (zzuz) SafeParcelReader.createParcelable(parcel, readHeader, zzuz.CREATOR);
                    break;
                case 14:
                    zzvaVar = (zzva) SafeParcelReader.createParcelable(parcel, readHeader, zzva.CREATOR);
                    break;
                case 15:
                    zzvbVar = (zzvb) SafeParcelReader.createParcelable(parcel, readHeader, zzvb.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzvj(i, str, str2, bArr, pointArr, i2, zzvcVar, zzvfVar, zzvgVar, zzviVar, zzvhVar, zzvdVar, zzuzVar, zzvaVar, zzvbVar);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzvj[i];
    }
}
