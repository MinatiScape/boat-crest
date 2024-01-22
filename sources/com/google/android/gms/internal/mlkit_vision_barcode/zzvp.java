package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes9.dex */
public final class zzvp implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzve zzveVar = null;
        String str = null;
        String str2 = null;
        zzvf[] zzvfVarArr = null;
        zzvc[] zzvcVarArr = null;
        String[] strArr = null;
        zzux[] zzuxVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzveVar = (zzve) SafeParcelReader.createParcelable(parcel, readHeader, zzve.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzvfVarArr = (zzvf[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzvf.CREATOR);
                    break;
                case 5:
                    zzvcVarArr = (zzvc[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzvc.CREATOR);
                    break;
                case 6:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 7:
                    zzuxVarArr = (zzux[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzux.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzva(zzveVar, str, str2, zzvfVarArr, zzvcVarArr, strArr, zzuxVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzva[i];
    }
}
