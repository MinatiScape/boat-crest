package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "SignInAccountCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<SignInAccount> CREATOR = new zbc();
    @SafeParcelable.Field(defaultValue = "", id = 4)
    @Deprecated
    public final String h;
    @SafeParcelable.Field(getter = "getGoogleSignInAccount", id = 7)
    public final GoogleSignInAccount i;
    @SafeParcelable.Field(defaultValue = "", id = 8)
    @Deprecated
    public final String j;

    @SafeParcelable.Constructor
    public SignInAccount(@SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 7) GoogleSignInAccount googleSignInAccount, @SafeParcelable.Param(id = 8) String str2) {
        this.i = googleSignInAccount;
        this.h = Preconditions.checkNotEmpty(str, "8.3 and 8.4 SDKs require non-null email");
        this.j = Preconditions.checkNotEmpty(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 4, this.h, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.i, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.j, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final GoogleSignInAccount zba() {
        return this.i;
    }
}
