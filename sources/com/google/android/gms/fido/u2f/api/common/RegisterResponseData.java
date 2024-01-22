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
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import com.google.android.gms.internal.fido.zzbf;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "RegisterResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class RegisterResponseData extends ResponseData {
    @NonNull
    public static final Parcelable.Creator<RegisterResponseData> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getRegisterData", id = 2)
    public final byte[] h;
    @SafeParcelable.Field(getter = "getProtocolVersionAsString", id = 3, type = "java.lang.String")
    public final ProtocolVersion i;
    @Nullable
    @SafeParcelable.Field(getter = "getClientDataString", id = 4)
    public final String j;

    public RegisterResponseData(@NonNull byte[] bArr) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = ProtocolVersion.V1;
        this.j = null;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RegisterResponseData) {
            RegisterResponseData registerResponseData = (RegisterResponseData) obj;
            return Objects.equal(this.i, registerResponseData.i) && Arrays.equals(this.h, registerResponseData.h) && Objects.equal(this.j, registerResponseData.j);
        }
        return false;
    }

    @NonNull
    public String getClientDataString() {
        return this.j;
    }

    @NonNull
    public ProtocolVersion getProtocolVersion() {
        return this.i;
    }

    @NonNull
    public byte[] getRegisterData() {
        return this.h;
    }

    public int getVersionCode() {
        ProtocolVersion protocolVersion = ProtocolVersion.UNKNOWN;
        int ordinal = this.i.ordinal();
        int i = 1;
        if (ordinal != 1) {
            i = 2;
            if (ordinal != 2) {
                return -1;
            }
        }
        return i;
    }

    public int hashCode() {
        return Objects.hashCode(this.i, Integer.valueOf(Arrays.hashCode(this.h)), this.j);
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    @NonNull
    public JSONObject toJsonObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("registrationData", Base64.encodeToString(this.h, 11));
            jSONObject.put("version", this.i.toString());
            String str = this.j;
            if (str != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_CLIENT_DATA, Base64.encodeToString(str.getBytes(), 11));
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String toString() {
        zzaj zza = zzak.zza(this);
        zza.zzb("protocolVersion", this.i);
        zzbf zzd = zzbf.zzd();
        byte[] bArr = this.h;
        zza.zzb("registerData", zzd.zze(bArr, 0, bArr.length));
        String str = this.j;
        if (str != null) {
            zza.zzb("clientDataString", str);
        }
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 2, getRegisterData(), false);
        SafeParcelWriter.writeString(parcel, 3, this.i.toString(), false);
        SafeParcelWriter.writeString(parcel, 4, getClientDataString(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RegisterResponseData(@NonNull byte[] bArr, @NonNull ProtocolVersion protocolVersion, @Nullable String str) {
        this.h = (byte[]) Preconditions.checkNotNull(bArr);
        this.i = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        Preconditions.checkArgument(protocolVersion != ProtocolVersion.UNKNOWN);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(str == null);
            this.j = null;
            return;
        }
        this.j = (String) Preconditions.checkNotNull(str);
    }

    @SafeParcelable.Constructor
    public RegisterResponseData(@SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2) {
        this.h = bArr;
        try {
            this.i = ProtocolVersion.fromString(str);
            this.j = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
