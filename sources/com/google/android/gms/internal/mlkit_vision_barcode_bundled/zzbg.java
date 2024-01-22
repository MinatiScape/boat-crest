package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
/* loaded from: classes8.dex */
public final class zzbg implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzav zzavVar = null;
        String str = null;
        String str2 = null;
        zzaw[] zzawVarArr = null;
        zzat[] zzatVarArr = null;
        String[] strArr = null;
        zzao[] zzaoVarArr = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    zzavVar = (zzav) SafeParcelReader.createParcelable(parcel, readHeader, zzav.CREATOR);
                    break;
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    zzawVarArr = (zzaw[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzaw.CREATOR);
                    break;
                case 5:
                    zzatVarArr = (zzat[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzat.CREATOR);
                    break;
                case 6:
                    strArr = SafeParcelReader.createStringArray(parcel, readHeader);
                    break;
                case 7:
                    zzaoVarArr = (zzao[]) SafeParcelReader.createTypedArray(parcel, readHeader, zzao.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzar(zzavVar, str, str2, zzawVarArr, zzatVarArr, strArr, zzaoVarArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzar[i];
    }
}
