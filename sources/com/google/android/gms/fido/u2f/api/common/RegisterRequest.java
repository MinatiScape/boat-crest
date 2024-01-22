package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "RegisterRequestCreator")
@Deprecated
/* loaded from: classes6.dex */
public class RegisterRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RegisterRequest> CREATOR = new zzg();
    public static final int U2F_V1_CHALLENGE_BYTE_LENGTH = 65;
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getProtocolVersionAsString", id = 2, type = "java.lang.String")
    public final ProtocolVersion i;
    @SafeParcelable.Field(getter = "getChallengeValue", id = 3)
    public final byte[] j;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    public final String k;

    @SafeParcelable.Constructor
    public RegisterRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) String str2) {
        this.h = i;
        try {
            this.i = ProtocolVersion.fromString(str);
            this.j = bArr;
            this.k = str2;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public static RegisterRequest parseFromJson(@NonNull JSONObject jSONObject) throws JSONException {
        try {
            try {
                try {
                    return new RegisterRequest(ProtocolVersion.fromString(jSONObject.has("version") ? jSONObject.getString("version") : null), Base64.decode(jSONObject.getString(ClientData.KEY_CHALLENGE), 8), jSONObject.has(RemoteConfigConstants.RequestFieldKey.APP_ID) ? jSONObject.getString(RemoteConfigConstants.RequestFieldKey.APP_ID) : null);
                } catch (IllegalArgumentException e) {
                    throw new JSONException(e.getMessage());
                }
            } catch (IllegalArgumentException e2) {
                throw new JSONException(e2.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e3) {
            throw new JSONException(e3.toString());
        }
    }

    public boolean equals(@NonNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisterRequest) {
            RegisterRequest registerRequest = (RegisterRequest) obj;
            if (Arrays.equals(this.j, registerRequest.j) && this.i == registerRequest.i) {
                String str = this.k;
                if (str == null) {
                    if (registerRequest.k != null) {
                        return false;
                    }
                } else if (!str.equals(registerRequest.k)) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @NonNull
    public String getAppId() {
        return this.k;
    }

    @NonNull
    public byte[] getChallengeValue() {
        return this.j;
    }

    @NonNull
    public ProtocolVersion getProtocolVersion() {
        return this.i;
    }

    public int getVersionCode() {
        return this.h;
    }

    public int hashCode() {
        int hashCode = ((Arrays.hashCode(this.j) + 31) * 31) + this.i.hashCode();
        String str = this.k;
        return (hashCode * 31) + (str == null ? 0 : str.hashCode());
    }

    @NonNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.i.toString());
            jSONObject.put(ClientData.KEY_CHALLENGE, Base64.encodeToString(this.j, 11));
            String str = this.k;
            if (str != null) {
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_ID, str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, this.i.toString(), false);
        SafeParcelWriter.writeByteArray(parcel, 3, getChallengeValue(), false);
        SafeParcelWriter.writeString(parcel, 4, getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public RegisterRequest(@NonNull ProtocolVersion protocolVersion, @NonNull byte[] bArr, @NonNull String str) {
        this.h = 1;
        this.i = (ProtocolVersion) Preconditions.checkNotNull(protocolVersion);
        this.j = (byte[]) Preconditions.checkNotNull(bArr);
        if (protocolVersion == ProtocolVersion.V1) {
            Preconditions.checkArgument(bArr.length == 65, "invalid challengeValue length for V1");
        }
        this.k = str;
    }
}
