package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
/* loaded from: classes6.dex */
public class BooleanResult implements Result {
    public final Status h;
    public final boolean i;

    @ShowFirstParty
    @KeepForSdk
    public BooleanResult(@NonNull Status status, boolean z) {
        this.h = (Status) Preconditions.checkNotNull(status, "Status must not be null");
        this.i = z;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof BooleanResult) {
            BooleanResult booleanResult = (BooleanResult) obj;
            return this.h.equals(booleanResult.h) && this.i == booleanResult.i;
        }
        return false;
    }

    @Override // com.google.android.gms.common.api.Result
    @NonNull
    public Status getStatus() {
        return this.h;
    }

    public boolean getValue() {
        return this.i;
    }

    public final int hashCode() {
        return ((this.h.hashCode() + 527) * 31) + (this.i ? 1 : 0);
    }
}
