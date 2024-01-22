package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "AccountChangeEventsRequestCreator")
/* loaded from: classes6.dex */
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(id = 2)
    public int i;
    @SafeParcelable.Field(id = 3)
    @Deprecated
    public String j;
    @SafeParcelable.Field(id = 4)
    public Account k;

    public AccountChangeEventsRequest() {
        this.h = 1;
    }

    @NonNull
    public Account getAccount() {
        return this.k;
    }

    @NonNull
    @Deprecated
    public String getAccountName() {
        return this.j;
    }

    public int getEventIndex() {
        return this.i;
    }

    @NonNull
    public AccountChangeEventsRequest setAccount(@NonNull Account account) {
        this.k = account;
        return this;
    }

    @NonNull
    @Deprecated
    public AccountChangeEventsRequest setAccountName(@NonNull String str) {
        this.j = str;
        return this;
    }

    @NonNull
    public AccountChangeEventsRequest setEventIndex(int i) {
        this.i = i;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.k, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public AccountChangeEventsRequest(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) Account account) {
        this.h = i;
        this.i = i2;
        this.j = str;
        if (account != null || TextUtils.isEmpty(str)) {
            this.k = account;
        } else {
            this.k = new Account(str, "com.google");
        }
    }
}
