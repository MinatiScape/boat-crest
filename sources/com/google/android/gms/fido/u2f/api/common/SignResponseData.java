package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "SignResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class SignResponseData extends ResponseData {
    @NonNull
    public static final Parcelable.Creator<SignResponseData> CREATOR = new zzl();
    @NonNull
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_CLIENT_DATA = "clientData";
    @NonNull
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_KEY_HANDLE = "keyHandle";
    @NonNull
    @VisibleForTesting
    public static final String JSON_RESPONSE_DATA_SIGNATURE_DATA = "signatureData";
    @SafeParcelable.Field(getter = "getKeyHandle", id = 2)
    public final byte[] h;
    @SafeParcelable.Field(getter = "getClientDataString", id = 3)
    public final String i;
    @SafeParcelable.Field(getter = "getSignatureData", id = 4)
    public final byte[] j;
    @SafeParcelable.Field(getter = "getApplication", id = 5)
    public final byte[] k;

    @Deprecated
    public SignResponseData(@NonNull byte[] bArr, @NonNull String str, @NonNull byte[] bArr2) {
        this(bArr, str, bArr2, new byte[0]);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SignResponseData) {
            SignResponseData signResponseData = (SignResponseData) obj;
            return Arrays.equals(this.h, signResponseData.h) && Objects.equal(this.i, signResponseData.i) && Arrays.equals(this.j, signResponseData.j) && Arrays.equals(this.k, signResponseData.k);
        }
        return false;
    }

    @NonNull
    public String getClientDataString() {
        return this.i;
    }

    @NonNull
    public byte[] getKeyHandle() {
        return this.h;
    }

    @NonNull
    public byte[] getSignatureData() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.h)), this.i, Integer.valueOf(Arrays.hashCode(this.j)), Integer.valueOf(Arrays.hashCode(this.k)));
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    @NonNull
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.h, 11));
            jSONObject.put(JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(this.i.getBytes(), 11));
            jSONObject.put(JSON_RESPONSE_DATA_SIGNATURE_DATA, Base64.encodeToString(this.j, 11));
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String toString() {
        zzaj zza = zzak.zza(this);
        zzbf zzd = zzbf.zzd();
        byte[] bArr = this.h;
        zza.zzb(JSON_RESPONSE_DATA_KEY_HANDLE, zzd.zze(bArr, 0, bArr.length));
        zza.zzb("clientDataString", this.i);
        zzbf zzd2 = zzbf.zzd();
        byte[] bArr2 = this.j;
        zza.zzb(JSON_RESPONSE_DATA_SIGNATURE_DATA, zzd2.zze(bArr2, 0, bArr2.length));
        zzbf zzd3 = zzbf.zzd();
        byte[] bArr3 = this.k;
        zza.zzb("application", zzd3.zze(bArr3, 0, bArr3.length));
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getKeyHandle(), false);
        SafeParcelWriter.writeString(parcel, 3, getClientDataString(), false);
        SafeParcelWriter.writeByteArray(parcel, 4, getSignatureData(), false);
        SafeParcelWriter.writeByteArray(parcel, 5, this.k, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public SignResponseData(@NonNull @SafeParcelable.Param(id = 2) byte[] bArr, @NonNull @SafeParcelable.Param(id = 3) String str, @NonNull @SafeParcelable.Param(id = 4) byte[] bArr2, @NonNull @SafeParcelable.Param(id = 5) byte[] bArr3) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = (String) Preconditions.checkNotNull(str);
        this.j = (byte[]) Preconditions.checkNotNull(bArr2);
        this.k = (byte[]) Preconditions.checkNotNull(bArr3);
    }
}
