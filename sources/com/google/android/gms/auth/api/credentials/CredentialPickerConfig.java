package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@SafeParcelable.Class(creator = "CredentialPickerConfigCreator")
@Deprecated
/* loaded from: classes6.dex */
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<CredentialPickerConfig> CREATOR = new zbb();
    @SafeParcelable.Field(id = 1000)
    public final int h;
    @SafeParcelable.Field(getter = "shouldShowAddAccountButton", id = 1)
    public final boolean i;
    @SafeParcelable.Field(getter = "shouldShowCancelButton", id = 2)
    public final boolean j;
    @SafeParcelable.Field(getter = "getPromptInternalId", id = 4)
    public final int k;

    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f8195a = false;
        public boolean b = true;
        public int c = 1;

        @NonNull
        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(2, this.f8195a, this.b, false, this.c);
        }

        @NonNull
        @Deprecated
        public Builder setForNewAccount(boolean z) {
            this.c = true == z ? 3 : 1;
            return this;
        }

        @NonNull
        public Builder setPrompt(int i) {
            this.c = i;
            return this;
        }

        @NonNull
        public Builder setShowAddAccountButton(boolean z) {
            this.f8195a = z;
            return this;
        }

        @NonNull
        public Builder setShowCancelButton(boolean z) {
            this.b = z;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }

    @SafeParcelable.Constructor
    public CredentialPickerConfig(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) boolean z2, @SafeParcelable.Param(id = 3) boolean z3, @SafeParcelable.Param(id = 4) int i2) {
        this.h = i;
        this.i = z;
        this.j = z2;
        if (i < 2) {
            this.k = true == z3 ? 3 : 1;
        } else {
            this.k = i2;
        }
    }

    @Deprecated
    public boolean isForNewAccount() {
        return this.k == 3;
    }

    public boolean shouldShowAddAccountButton() {
        return this.i;
    }

    public boolean shouldShowCancelButton() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 1000, this.h);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
