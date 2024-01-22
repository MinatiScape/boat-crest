package com.google.android.gms.common;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
/* loaded from: classes6.dex */
public final class q {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f8366a = null;
    public long b = -1;
    public zzag c = zzag.zzl();
    public zzag d = zzag.zzl();

    @CanIgnoreReturnValue
    public final q a(long j) {
        this.b = j;
        return this;
    }

    @CanIgnoreReturnValue
    public final q b(List list) {
        Preconditions.checkNotNull(list);
        this.d = zzag.zzk(list);
        return this;
    }

    @CanIgnoreReturnValue
    public final q c(List list) {
        Preconditions.checkNotNull(list);
        this.c = zzag.zzk(list);
        return this;
    }

    @CanIgnoreReturnValue
    public final q d(String str) {
        this.f8366a = str;
        return this;
    }

    public final b e() {
        if (this.f8366a != null) {
            if (this.b >= 0) {
                if (this.c.isEmpty() && this.d.isEmpty()) {
                    throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
                }
                return new b(this.f8366a, this.b, this.c, this.d, null);
            }
            throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
        }
        throw new IllegalStateException("packageName must be defined");
    }
}
