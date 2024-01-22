package com.google.android.gms.common;

import androidx.annotation.NonNull;
import com.google.errorprone.annotations.CheckReturnValue;
import javax.annotation.Nullable;
@CheckReturnValue
/* loaded from: classes6.dex */
public class PackageVerificationResult {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f8239a;
    @Nullable
    public final String b;
    @Nullable
    public final Throwable c;

    public PackageVerificationResult(String str, int i, boolean z, @Nullable String str2, @Nullable Throwable th) {
        this.f8239a = z;
        this.b = str2;
        this.c = th;
    }

    @NonNull
    public static PackageVerificationResult zza(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        return new PackageVerificationResult(str, 1, false, str2, th);
    }

    @NonNull
    public static PackageVerificationResult zzd(@NonNull String str, int i) {
        return new PackageVerificationResult(str, i, true, null, null);
    }

    public final void zzb() {
        if (this.f8239a) {
            return;
        }
        String valueOf = String.valueOf(this.b);
        Throwable th = this.c;
        String concat = "PackageVerificationRslt: ".concat(valueOf);
        if (th == null) {
            throw new SecurityException(concat);
        }
        throw new SecurityException(concat, th);
    }

    public final boolean zzc() {
        return this.f8239a;
    }
}
