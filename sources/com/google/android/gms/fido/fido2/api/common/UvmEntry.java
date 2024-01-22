package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "UvmEntryCreator")
/* loaded from: classes6.dex */
public class UvmEntry extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<UvmEntry> CREATOR = new zzba();
    @SafeParcelable.Field(getter = "getUserVerificationMethod", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getKeyProtectionType", id = 2)
    public final short i;
    @SafeParcelable.Field(getter = "getMatcherProtectionType", id = 3)
    public final short j;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f8404a;
        public short b;
        public short c;

        @NonNull
        public UvmEntry build() {
            return new UvmEntry(this.f8404a, this.b, this.c);
        }

        @NonNull
        public Builder setKeyProtectionType(short s) {
            this.b = s;
            return this;
        }

        @NonNull
        public Builder setMatcherProtectionType(short s) {
            this.c = s;
            return this;
        }

        @NonNull
        public Builder setUserVerificationMethod(int i) {
            this.f8404a = i;
            return this;
        }
    }

    @SafeParcelable.Constructor
    public UvmEntry(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) short s, @SafeParcelable.Param(id = 3) short s2) {
        this.h = i;
        this.i = s;
        this.j = s2;
    }

    public boolean equals(@NonNull Object obj) {
        if (obj instanceof UvmEntry) {
            UvmEntry uvmEntry = (UvmEntry) obj;
            return this.h == uvmEntry.h && this.i == uvmEntry.i && this.j == uvmEntry.j;
        }
        return false;
    }

    public short getKeyProtectionType() {
        return this.i;
    }

    public short getMatcherProtectionType() {
        return this.j;
    }

    public int getUserVerificationMethod() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.h), Short.valueOf(this.i), Short.valueOf(this.j));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getUserVerificationMethod());
        SafeParcelWriter.writeShort(parcel, 2, getKeyProtectionType());
        SafeParcelWriter.writeShort(parcel, 3, getMatcherProtectionType());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
