package com.google.android.gms.internal.mlkit_code_scanner;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes8.dex */
public final class zzpd implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzou zzouVar = null;
        String str = null;
        String str2 = null;
        zzov[] zzovVarArr = null;
        zzos[] zzosVarArr = null;
        String[] strArr = null;
        zzon[] zzonVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzouVar = (zzou) SafeParcelReader.createParcelable(parcel, readHeader, zzou.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzovVarArr = (zzov[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzov.CREATOR);
                    break;
                case 5:
                    zzosVarArr = (zzos[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzos.CREATOR);
                    break;
                case 6:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 7:
                    zzonVarArr = (zzon[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzon.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzoq(zzouVar, str, str2, zzovVarArr, zzosVarArr, strArr, zzonVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzoq[i];
    }
}
