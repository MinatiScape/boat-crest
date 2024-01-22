package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SignInConfigurationCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zbu();
    @SafeParcelable.Field(getter = "getConsumerPkgName", id = 2)
    public final String h;
    @SafeParcelable.Field(getter = "getGoogleConfig", id = 5)
    public final GoogleSignInOptions i;

    @SafeParcelable.Constructor
    public SignInConfiguration(@NonNull @SafeParcelable.Param(id = 2) String str, @NonNull @SafeParcelable.Param(id = 5) GoogleSignInOptions googleSignInOptions) {
        this.h = Preconditions.checkNotEmpty(str);
        this.i = googleSignInOptions;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof SignInConfiguration) {
            SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
            if (this.h.equals(signInConfiguration.h)) {
                GoogleSignInOptions googleSignInOptions = this.i;
                GoogleSignInOptions googleSignInOptions2 = signInConfiguration.i;
                if (googleSignInOptions == null) {
                    if (googleSignInOptions2 == null) {
                        return true;
                    }
                } else if (googleSignInOptions.equals(googleSignInOptions2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.h).addObject(this.i).hash();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.h, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.i, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final GoogleSignInOptions zba() {
        return this.i;
    }
}
