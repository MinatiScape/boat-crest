package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@SafeParcelable.Class(creator = "UvmEntriesCreator")
/* loaded from: classes6.dex */
public class UvmEntries extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<UvmEntries> CREATOR = new zzaz();
    @Nullable
    @SafeParcelable.Field(getter = "getUvmEntryList", id = 1)
    public final List h;

    /* loaded from: classes6.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final List f8403a = new ArrayList();

        @NonNull
        public Builder addAll(@NonNull List<UvmEntry> list) {
            com.google.android.gms.internal.fido.zzam.zzc(this.f8403a.size() + list.size() <= 3);
            this.f8403a.addAll(list);
            return this;
        }

        @NonNull
        public Builder addUvmEntry(@Nullable UvmEntry uvmEntry) {
            if (this.f8403a.size() < 3) {
                this.f8403a.add(uvmEntry);
                return this;
            }
            throw new IllegalStateException();
        }

        @NonNull
        public UvmEntries build() {
            return new UvmEntries(this.f8403a);
        }
    }

    @SafeParcelable.Constructor
    public UvmEntries(@Nullable @SafeParcelable.Param(id = 1) List list) {
        this.h = list;
    }

    public boolean equals(@NonNull Object obj) {
        List list;
        if (obj instanceof UvmEntries) {
            UvmEntries uvmEntries = (UvmEntries) obj;
            List list2 = this.h;
            return (list2 == null && uvmEntries.h == null) || (list2 != null && (list = uvmEntries.h) != null && list2.containsAll(list) && uvmEntries.h.containsAll(this.h));
        }
        return false;
    }

    @Nullable
    public List<UvmEntry> getUvmEntryList() {
        return this.h;
    }

    public int hashCode() {
        return Objects.hashCode(new HashSet(this.h));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getUvmEntryList(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
