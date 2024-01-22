package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
@SafeParcelable.Class(creator = "CredentialCreator")
@SafeParcelable.Reserved({1000, 1001, 1002})
@Deprecated
/* loaded from: classes6.dex */
public class Credential extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<Credential> CREATOR = new zba();
    @NonNull
    public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
    @Nonnull
    @SafeParcelable.Field(getter = "getId", id = 1)
    public final String h;
    @Nullable
    @SafeParcelable.Field(getter = "getName", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getProfilePictureUri", id = 3)
    public final Uri j;
    @Nonnull
    @SafeParcelable.Field(getter = "getIdTokens", id = 4)
    public final List k;
    @Nullable
    @SafeParcelable.Field(getter = "getPassword", id = 5)
    public final String l;
    @Nullable
    @SafeParcelable.Field(getter = "getAccountType", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getGivenName", id = 9)
    public final String n;
    @Nullable
    @SafeParcelable.Field(getter = "getFamilyName", id = 10)
    public final String o;

    @Deprecated
    /* loaded from: classes6.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final String f8194a;
        @Nullable
        public String b;
        @Nullable
        public Uri c;
        public List d;
        @Nullable
        public String e;
        @Nullable
        public String f;
        @Nullable
        public String g;
        @Nullable
        public String h;

        public Builder(@NonNull Credential credential) {
            this.f8194a = credential.h;
            this.b = credential.i;
            this.c = credential.j;
            this.d = credential.k;
            this.e = credential.l;
            this.f = credential.m;
            this.g = credential.n;
            this.h = credential.o;
        }

        public Builder(@NonNull String str) {
            this.f8194a = str;
        }

        @NonNull
        public Credential build() {
            return new Credential(this.f8194a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
        }

        @NonNull
        public Builder setAccountType(@NonNull String str) {
            this.f = str;
            return this;
        }

        @NonNull
        public Builder setName(@NonNull String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public Builder setPassword(@Nullable String str) {
            this.e = str;
            return this;
        }

        @NonNull
        public Builder setProfilePictureUri(@NonNull Uri uri) {
            this.c = uri;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public Credential(@SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) Uri uri, @SafeParcelable.Param(id = 4) List list, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 6) String str4, @Nullable @SafeParcelable.Param(id = 9) String str5, @Nullable @SafeParcelable.Param(id = 10) String str6) {
        List unmodifiableList;
        Boolean bool;
        String trim = ((String) Preconditions.checkNotNull(str, "credential identifier cannot be null")).trim();
        Preconditions.checkNotEmpty(trim, "credential identifier cannot be empty");
        if (str3 != null && TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Password must not be empty if set");
        }
        if (str4 != null) {
            if (TextUtils.isEmpty(str4)) {
                bool = Boolean.FALSE;
            } else {
                Uri parse = Uri.parse(str4);
                if (parse.isAbsolute() && parse.isHierarchical() && !TextUtils.isEmpty(parse.getScheme()) && !TextUtils.isEmpty(parse.getAuthority())) {
                    boolean z = true;
                    if (!"http".equalsIgnoreCase(parse.getScheme()) && !"https".equalsIgnoreCase(parse.getScheme())) {
                        z = false;
                    }
                    bool = Boolean.valueOf(z);
                } else {
                    bool = Boolean.FALSE;
                }
            }
            if (!bool.booleanValue()) {
                throw new IllegalArgumentException("Account type must be a valid Http/Https URI");
            }
        }
        if (!TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Password and AccountType are mutually exclusive");
        }
        if (str2 != null && TextUtils.isEmpty(str2.trim())) {
            str2 = null;
        }
        this.i = str2;
        this.j = uri;
        if (list == null) {
            unmodifiableList = Collections.emptyList();
        } else {
            unmodifiableList = Collections.unmodifiableList(list);
        }
        this.k = unmodifiableList;
        this.h = trim;
        this.l = str3;
        this.m = str4;
        this.n = str5;
        this.o = str6;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Credential) {
            Credential credential = (Credential) obj;
            return TextUtils.equals(this.h, credential.h) && TextUtils.equals(this.i, credential.i) && Objects.equal(this.j, credential.j) && TextUtils.equals(this.l, credential.l) && TextUtils.equals(this.m, credential.m);
        }
        return false;
    }

    @Nullable
    public String getAccountType() {
        return this.m;
    }

    @Nullable
    public String getFamilyName() {
        return this.o;
    }

    @Nullable
    public String getGivenName() {
        return this.n;
    }

    @Nonnull
    public String getId() {
        return this.h;
    }

    @Nonnull
    public List<IdToken> getIdTokens() {
        return this.k;
    }

    @Nullable
    public String getName() {
        return this.i;
    }

    @Nullable
    public String getPassword() {
        return this.l;
    }

    @Nullable
    public Uri getProfilePictureUri() {
        return this.j;
    }

    public int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, this.l, this.m);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getProfilePictureUri(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 4, getIdTokens(), false);
        SafeParcelWriter.writeString(parcel, 5, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 6, getAccountType(), false);
        SafeParcelWriter.writeString(parcel, 9, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 10, getFamilyName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
