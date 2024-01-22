package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.fido.zzaj;
import com.google.android.gms.internal.fido.zzak;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "ErrorResponseDataCreator")
@SafeParcelable.Reserved({1})
@Deprecated
/* loaded from: classes6.dex */
public class ErrorResponseData extends ResponseData {
    @NonNull
    public static final Parcelable.Creator<ErrorResponseData> CREATOR = new zzd();
    @NonNull
    @VisibleForTesting
    public static final String JSON_ERROR_CODE = "errorCode";
    @NonNull
    @VisibleForTesting
    public static final String JSON_ERROR_MESSAGE = "errorMessage";
    @SafeParcelable.Field(getter = "getErrorCodeAsInt", id = 2, type = "int")
    public final ErrorCode h;
    @SafeParcelable.Field(getter = "getErrorMessage", id = 3)
    public final String i;

    @SafeParcelable.Constructor
    public ErrorResponseData(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) String str) {
        this.h = ErrorCode.toErrorCode(i);
        this.i = str;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ErrorResponseData) {
            ErrorResponseData errorResponseData = (ErrorResponseData) obj;
            return Objects.equal(this.h, errorResponseData.h) && Objects.equal(this.i, errorResponseData.i);
        }
        return false;
    }

    @NonNull
    public ErrorCode getErrorCode() {
        return this.h;
    }

    public int getErrorCodeAsInt() {
        return this.h.getCode();
    }

    @NonNull
    public String getErrorMessage() {
        return this.i;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @Override // com.google.android.gms.fido.u2f.api.common.ResponseData
    @NonNull
    public final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", this.h.getCode());
            String str = this.i;
            if (str != null) {
                jSONObject.put("errorMessage", str);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    public String toString() {
        zzaj zza = zzak.zza(this);
        zza.zza("errorCode", this.h.getCode());
        String str = this.i;
        if (str != null) {
            zza.zzb("errorMessage", str);
        }
        return zza.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getErrorCodeAsInt());
        SafeParcelWriter.writeString(parcel, 3, getErrorMessage(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public ErrorResponseData(@NonNull ErrorCode errorCode) {
        this.h = (ErrorCode) Preconditions.checkNotNull(errorCode);
        this.i = null;
    }

    public ErrorResponseData(@NonNull ErrorCode errorCode, @NonNull String str) {
        this.h = (ErrorCode) Preconditions.checkNotNull(errorCode);
        this.i = str;
    }
}
