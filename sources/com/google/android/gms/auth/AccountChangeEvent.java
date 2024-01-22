package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "AccountChangeEventCreator")
/* loaded from: classes6.dex */
public class AccountChangeEvent extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();
    @SafeParcelable.VersionField(id = 1)
    public final int h;
    @SafeParcelable.Field(id = 2)
    public final long i;
    @SafeParcelable.Field(id = 3)
    public final String j;
    @SafeParcelable.Field(id = 4)
    public final int k;
    @SafeParcelable.Field(id = 5)
    public final int l;
    @SafeParcelable.Field(id = 6)
    public final String m;

    @SafeParcelable.Constructor
    public AccountChangeEvent(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) String str2) {
        this.h = i;
        this.i = j;
        this.j = (String) Preconditions.checkNotNull(str);
        this.k = i2;
        this.l = i3;
        this.m = str2;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof AccountChangeEvent) {
            if (obj == this) {
                return true;
            }
            AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
            return this.h == accountChangeEvent.h && this.i == accountChangeEvent.i && Objects.equal(this.j, accountChangeEvent.j) && this.k == accountChangeEvent.k && this.l == accountChangeEvent.l && Objects.equal(this.m, accountChangeEvent.m);
        }
        return false;
    }

    @NonNull
    public String getAccountName() {
        return this.j;
    }

    @NonNull
    public String getChangeData() {
        return this.m;
    }

    public int getChangeType() {
        return this.k;
    }

    public int getEventIndex() {
        return this.l;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Long.valueOf(this.i), this.j, Integer.valueOf(this.k), Integer.valueOf(this.l), this.m);
    }

    @NonNull
    public String toString() {
        int i = this.k;
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "RENAMED_TO" : "RENAMED_FROM" : "REMOVED" : "ADDED";
        String str2 = this.j;
        String str3 = this.m;
        int i2 = this.l;
        return "AccountChangeEvent {accountName = " + str2 + ", changeType = " + str + ", changeData = " + str3 + ", eventIndex = " + i2 + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeLong(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeInt(parcel, 4, this.k);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeString(parcel, 6, this.m, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public AccountChangeEvent(long j, @NonNull String str, int i, int i2, @NonNull String str2) {
        this.h = 1;
        this.i = j;
        this.j = (String) Preconditions.checkNotNull(str);
        this.k = i;
        this.l = i2;
        this.m = str2;
    }
}
