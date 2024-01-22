package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.fido.common.Transport;
import com.google.android.gms.fido.u2f.api.common.ProtocolVersion;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "KeyHandleCreator")
@Deprecated
/* loaded from: classes6.dex */
public class KeyHandle extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<KeyHandle> CREATOR = new zze();
    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getBytes", id = 2)
    public final byte[] i;
    @SafeParcelable.Field(getter = "getProtocolVersionAsString", id = 3, type = "java.lang.String")
    public final ProtocolVersion j;
    @Nullable
    @SafeParcelable.Field(getter = "getTransports", id = 4)
    public final List k;

    @SafeParcelable.Constructor
    public KeyHandle(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) List list) {
        this.h = i;
        this.i = bArr;
        try {
            this.j = ProtocolVersion.fromString(str);
            this.k = list;
        } catch (ProtocolVersion.UnsupportedProtocolException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @NonNull
    public static KeyHandle parseFromJson(@NonNull JSONObject jSONObject) throws JSONException {
        try {
            try {
                return new KeyHandle(Base64.decode(jSONObject.getString(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE), 8), ProtocolVersion.fromString(jSONObject.has("version") ? jSONObject.getString("version") : null), jSONObject.has("transports") ? Transport.parseTransports(jSONObject.getJSONArray("transports")) : null);
            } catch (IllegalArgumentException e) {
                throw new JSONException(e.toString());
            }
        } catch (ProtocolVersion.UnsupportedProtocolException e2) {
            throw new JSONException(e2.toString());
        }
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        if (this == obj) {
            return true;
        }
        if (obj instanceof KeyHandle) {
            KeyHandle keyHandle = (KeyHandle) obj;
            if (Arrays.equals(this.i, keyHandle.i) && this.j.equals(keyHandle.j)) {
                List list2 = this.k;
                if (list2 == null && keyHandle.k == null) {
                    return true;
                }
                return list2 != null && (list = keyHandle.k) != null && list2.containsAll(list) && keyHandle.k.containsAll(this.k);
            }
            return false;
        }
        return false;
    }

    @NonNull
    public byte[] getBytes() {
        return this.i;
    }

    @NonNull
    public ProtocolVersion getProtocolVersion() {
        return this.j;
    }

    @NonNull
    public List<Transport> getTransports() {
        return this.k;
    }

    public int getVersionCode() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.i)), this.j, this.k);
    }

    @NonNull
    public JSONObject toJson() {
        return zza();
    }

    @NonNull
    public String toString() {
        List list = this.k;
        return String.format("{keyHandle: %s, version: %s, transports: %s}", Base64Utils.encode(this.i), this.j, list == null ? "null" : list.toString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeByteArray(parcel, 2, getBytes(), false);
        SafeParcelWriter.writeString(parcel, 3, this.j.toString(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getTransports(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final JSONObject zza() {
        JSONObject jSONObject = new JSONObject();
        try {
            byte[] bArr = this.i;
            if (bArr != null) {
                jSONObject.put(SignResponseData.JSON_RESPONSE_DATA_KEY_HANDLE, Base64.encodeToString(bArr, 11));
            }
            ProtocolVersion protocolVersion = this.j;
            if (protocolVersion != null) {
                jSONObject.put("version", protocolVersion.toString());
            }
            if (this.k != null) {
                JSONArray jSONArray = new JSONArray();
                for (Transport transport : this.k) {
                    jSONArray.put(transport.toString());
                }
                jSONObject.put("transports", jSONArray);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public KeyHandle(@NonNull byte[] bArr, @NonNull ProtocolVersion protocolVersion, @Nullable List<Transport> list) {
        this.h = 1;
        this.i = bArr;
        this.j = protocolVersion;
        this.k = list;
    }
}
