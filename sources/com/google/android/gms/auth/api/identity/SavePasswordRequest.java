package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SavePasswordRequestCreator")
/* loaded from: classes6.dex */
public class SavePasswordRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SavePasswordRequest> CREATOR = new zbm();
    @SafeParcelable.Field(getter = "getSignInPassword", id = 1)
    public final SignInPassword h;
    @Nullable
    @SafeParcelable.Field(getter = "getSessionId", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getTheme", id = 3)
    public final int j;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public SignInPassword f8205a;
        @Nullable
        public String b;
        public int c;

        @NonNull
        public SavePasswordRequest build() {
            return new SavePasswordRequest(this.f8205a, this.b, this.c);
        }

        @NonNull
        public Builder setSignInPassword(@NonNull SignInPassword signInPassword) {
            this.f8205a = signInPassword;
            return this;
        }

        @NonNull
        public final Builder zba(@NonNull String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public final Builder zbb(int i) {
            this.c = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public SavePasswordRequest(@SafeParcelable.Param(id = 1) SignInPassword signInPassword, @Nullable @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i) {
        this.h = (SignInPassword) Preconditions.checkNotNull(signInPassword);
        this.i = str;
        this.j = i;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder zba(@NonNull SavePasswordRequest savePasswordRequest) {
        Preconditions.checkNotNull(savePasswordRequest);
        Builder builder = builder();
        builder.setSignInPassword(savePasswordRequest.getSignInPassword());
        builder.zbb(savePasswordRequest.j);
        String str = savePasswordRequest.i;
        if (str != null) {
            builder.zba(str);
        }
        return builder;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SavePasswordRequest) {
            SavePasswordRequest savePasswordRequest = (SavePasswordRequest) obj;
            return Objects.equal(this.h, savePasswordRequest.h) && Objects.equal(this.i, savePasswordRequest.i) && this.j == savePasswordRequest.j;
        }
        return false;
    }

    @NonNull
    public SignInPassword getSignInPassword() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSignInPassword(), i, false);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
