package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;
@SafeParcelable.Class(creator = "TokenBindingCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class TokenBinding extends AbstractSafeParcelable {
    @NonNull
    @SafeParcelable.Field(getter = "getTokenBindingStatusAsString", id = 2, type = "java.lang.String")
    public final TokenBindingStatus h;
    @Nullable
    @SafeParcelable.Field(getter = "getTokenBindingId", id = 3)
    public final String i;
    @NonNull
    public static final Parcelable.Creator<TokenBinding> CREATOR = new zzau();
    @NonNull
    public static final TokenBinding SUPPORTED = new TokenBinding(TokenBindingStatus.SUPPORTED.toString(), null);
    @NonNull
    public static final TokenBinding NOT_SUPPORTED = new TokenBinding(TokenBindingStatus.NOT_SUPPORTED.toString(), null);

    /* loaded from: classes6.dex */
    public enum TokenBindingStatus implements Parcelable {
        PRESENT("present"),
        SUPPORTED("supported"),
        NOT_SUPPORTED("not-supported");
        
        @NonNull
        public static final Parcelable.Creator<TokenBindingStatus> CREATOR = new d();
        @NonNull
        private final String zzb;

        TokenBindingStatus(@NonNull String str) {
            this.zzb = str;
        }

        @NonNull
        public static TokenBindingStatus fromString(@NonNull String str) throws UnsupportedTokenBindingStatusException {
            TokenBindingStatus[] values;
            for (TokenBindingStatus tokenBindingStatus : values()) {
                if (str.equals(tokenBindingStatus.zzb)) {
                    return tokenBindingStatus;
                }
            }
            throw new UnsupportedTokenBindingStatusException(str);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.zzb;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(this.zzb);
        }
    }

    /* loaded from: classes6.dex */
    public static class UnsupportedTokenBindingStatusException extends Exception {
        public UnsupportedTokenBindingStatusException(@NonNull String str) {
            super(String.format("TokenBindingStatus %s not supported", str));
        }
    }

    public TokenBinding(@NonNull String str) {
        this(TokenBindingStatus.PRESENT.toString(), (String) Preconditions.checkNotNull(str));
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof TokenBinding) {
            TokenBinding tokenBinding = (TokenBinding) obj;
            return com.google.android.gms.internal.fido.zzal.zza(this.h, tokenBinding.h) && com.google.android.gms.internal.fido.zzal.zza(this.i, tokenBinding.i);
        }
        return false;
    }

    @Nullable
    public String getTokenBindingId() {
        return this.i;
    }

    @NonNull
    public String getTokenBindingStatusAsString() {
        return this.h.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.h, this.i});
    }

    @NonNull
    public JSONObject toJsonObject() throws JSONException {
        try {
            return new JSONObject().put(NotificationCompat.CATEGORY_STATUS, this.h).put("id", this.i);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getTokenBindingStatusAsString(), false);
        SafeParcelWriter.writeString(parcel, 3, getTokenBindingId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public TokenBinding(@NonNull @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) String str2) {
        Preconditions.checkNotNull(str);
        try {
            this.h = TokenBindingStatus.fromString(str);
            this.i = str2;
        } catch (UnsupportedTokenBindingStatusException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
