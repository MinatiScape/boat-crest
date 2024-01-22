package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
@ShowFirstParty
@SafeParcelable.Class(creator = "TokenDataCreator")
/* loaded from: classes6.dex */
public class TokenData extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<TokenData> CREATOR = new zzm();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getToken", id = 2)
    public final String i;
    @Nullable
    @SafeParcelable.Field(getter = "getExpirationTimeSecs", id = 3)
    public final Long j;
    @SafeParcelable.Field(getter = "isCached", id = 4)
    public final boolean k;
    @SafeParcelable.Field(getter = "isSnowballed", id = 5)
    public final boolean l;
    @Nullable
    @SafeParcelable.Field(getter = "getGrantedScopes", id = 6)
    public final List m;
    @Nullable
    @SafeParcelable.Field(getter = "getScopeData", id = 7)
    public final String n;

    @SafeParcelable.Constructor
    public TokenData(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @Nullable @SafeParcelable.Param(id = 3) Long l, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2, @Nullable @SafeParcelable.Param(id = 6) List list, @Nullable @SafeParcelable.Param(id = 7) String str2) {
        this.h = i;
        this.i = Preconditions.checkNotEmpty(str);
        this.j = l;
        this.k = z;
        this.l = z2;
        this.m = list;
        this.n = str2;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof TokenData) {
            TokenData tokenData = (TokenData) obj;
            return TextUtils.equals(this.i, tokenData.i) && Objects.equal(this.j, tokenData.j) && this.k == tokenData.k && this.l == tokenData.l && Objects.equal(this.m, tokenData.m) && Objects.equal(this.n, tokenData.n);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.i, this.j, Boolean.valueOf(this.k), Boolean.valueOf(this.l), this.m, this.n);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeString(parcel, 2, this.i, false);
        SafeParcelWriter.writeLongObject(parcel, 3, this.j, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.k);
        SafeParcelWriter.writeBoolean(parcel, 5, this.l);
        SafeParcelWriter.writeStringList(parcel, 6, this.m, false);
        SafeParcelWriter.writeString(parcel, 7, this.n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public final String zza() {
        return this.i;
    }
}
