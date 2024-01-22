package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "RegisteredKeyCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class RegisteredKey extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<RegisteredKey> CREATOR = new zzj();
    @SafeParcelable.Field(getter = "getKeyHandle", id = 2)
    public final KeyHandle h;
    @SafeParcelable.Field(getter = "getAppId", id = 4)
    public final String i;
    @SafeParcelable.Field(getter = "getChallengeValue", id = 3)
    public String j;

    public RegisteredKey(@NonNull KeyHandle keyHandle) {
        this(keyHandle, null, null);
    }

    @NonNull
    public static RegisteredKey parseFromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return new RegisteredKey(KeyHandle.parseFromJson(jSONObject), jSONObject.has(ClientData.KEY_CHALLENGE) ? jSONObject.getString(ClientData.KEY_CHALLENGE) : null, jSONObject.has(RemoteConfigConstants.RequestFieldKey.APP_ID) ? jSONObject.getString(RemoteConfigConstants.RequestFieldKey.APP_ID) : null);
    }

    public boolean equals(@NonNull Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RegisteredKey) {
            RegisteredKey registeredKey = (RegisteredKey) obj;
            String str = this.j;
            if (str == null) {
                if (registeredKey.j != null) {
                    return false;
                }
            } else if (!str.equals(registeredKey.j)) {
                return false;
            }
            if (this.h.equals(registeredKey.h)) {
                String str2 = this.i;
                if (str2 == null) {
                    if (registeredKey.i != null) {
                        return false;
                    }
                } else if (!str2.equals(registeredKey.i)) {
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
        return this.i;
    }

    @NonNull
    public String getChallengeValue() {
        return this.j;
    }

    @NonNull
    public KeyHandle getKeyHandle() {
        return this.h;
    }

    public int hashCode() {
        String str = this.j;
        int hashCode = (((str == null ? 0 : str.hashCode()) + 31) * 31) + this.h.hashCode();
        String str2 = this.i;
        return (hashCode * 31) + (str2 != null ? str2.hashCode() : 0);
    }

    @NonNull
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.j;
            if (str != null) {
                jSONObject.put(ClientData.KEY_CHALLENGE, str);
            }
            JSONObject zza = this.h.zza();
            Iterator<String> keys = zza.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, zza.get(next));
            }
            String str2 = this.i;
            if (str2 != null) {
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_ID, str2);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(this.h.getBytes(), 11));
            if (this.h.getProtocolVersion() != ProtocolVersion.UNKNOWN) {
                jSONObject.put("version", this.h.getProtocolVersion().toString());
            }
            if (this.h.getTransports() != null) {
                jSONObject.put("transports", this.h.getTransports().toString());
            }
            String str = this.j;
            if (str != null) {
                jSONObject.put(ClientData.KEY_CHALLENGE, str);
            }
            String str2 = this.i;
            if (str2 != null) {
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_ID, str2);
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getKeyHandle(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getChallengeValue(), false);
        SafeParcelWriter.writeString(parcel, 4, getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public RegisteredKey(@NonNull @SafeParcelable.Param(id = 2) KeyHandle keyHandle, @NonNull @SafeParcelable.Param(id = 3) String str, @NonNull @SafeParcelable.Param(id = 4) String str2) {
        this.h = (KeyHandle) Preconditions.checkNotNull(keyHandle);
        this.j = str;
        this.i = str2;
    }
}
