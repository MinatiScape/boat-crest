package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
@SafeParcelable.Class(creator = "SignInCredentialCreator")
/* loaded from: classes6.dex */
public final class SignInCredential extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zbo();
    @SafeParcelable.Field(getter = "getId", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getDisplayName", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getGivenName", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getFamilyName", id = 4)
    public final String k;
    @Nullable
    @SafeParcelable.Field(getter = "getProfilePictureUri", id = 5)
    public final Uri l;
    @Nullable
    @SafeParcelable.Field(getter = "getPassword", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getGoogleIdToken", id = 7)
    public final String n;
    @Nullable
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 8)
    public final String o;
    @Nullable
    @SafeParcelable.Field(getter = "getPublicKeyCredential", id = 9)
    public final PublicKeyCredential p;

    @SafeParcelable.Constructor
    public SignInCredential(@SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 5) Uri uri, @Nullable @SafeParcelable.Param(id = 6) String str5, @Nullable @SafeParcelable.Param(id = 7) String str6, @Nullable @SafeParcelable.Param(id = 8) String str7, @Nullable @SafeParcelable.Param(id = 9) PublicKeyCredential publicKeyCredential) {
        this.h = Preconditions.checkNotEmpty(str);
        this.i = str2;
        this.j = str3;
        this.k = str4;
        this.l = uri;
        this.m = str5;
        this.n = str6;
        this.o = str7;
        this.p = publicKeyCredential;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof SignInCredential) {
            SignInCredential signInCredential = (SignInCredential) obj;
            return Objects.equal(this.h, signInCredential.h) && Objects.equal(this.i, signInCredential.i) && Objects.equal(this.j, signInCredential.j) && Objects.equal(this.k, signInCredential.k) && Objects.equal(this.l, signInCredential.l) && Objects.equal(this.m, signInCredential.m) && Objects.equal(this.n, signInCredential.n) && Objects.equal(this.o, signInCredential.o) && Objects.equal(this.p, signInCredential.p);
        }
        return false;
    }

    @Nullable
    public String getDisplayName() {
        return this.i;
    }

    @Nullable
    public String getFamilyName() {
        return this.k;
    }

    @Nullable
    public String getGivenName() {
        return this.j;
    }

    @Nullable
    public String getGoogleIdToken() {
        return this.n;
    }

    @NonNull
    public String getId() {
        return this.h;
    }

    @Nullable
    public String getPassword() {
        return this.m;
    }

    @Nullable
    @Deprecated
    public String getPhoneNumber() {
        return this.o;
    }

    @Nullable
    public Uri getProfilePictureUri() {
        return this.l;
    }

    @Nullable
    public PublicKeyCredential getPublicKeyCredential() {
        return this.p;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getProfilePictureUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 7, getGoogleIdToken(), false);
        SafeParcelWriter.writeString(parcel, 8, getPhoneNumber(), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getPublicKeyCredential(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
