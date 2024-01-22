package com.google.android.gms.common;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;
@CheckReturnValue
@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*javatests.*/com/google/android/gms/common/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes6.dex */
public class PackageSignatureVerifier {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static d f8238a;
    @Nullable
    @VisibleForTesting
    public static volatile c b;

    public static d a() {
        d dVar;
        synchronized (d.class) {
            if (f8238a == null) {
                f8238a = new d();
            }
            dVar = f8238a;
        }
        return dVar;
    }

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerified(@NonNull Context context, @NonNull String str) {
        PackageVerificationResult packageVerificationResult;
        String str2;
        PackageVerificationResult packageVerificationResult2;
        boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(context);
        a();
        if (m.f()) {
            String concat = String.valueOf(str).concat(true != honorsDebugCertificates ? "-0" : "-1");
            if (b != null) {
                str2 = b.f8313a;
                if (str2.equals(concat)) {
                    packageVerificationResult2 = b.b;
                    return packageVerificationResult2;
                }
            }
            a();
            o c = m.c(str, honorsDebugCertificates, false, false);
            if (c.f8364a) {
                b = new c(concat, PackageVerificationResult.zzd(str, c.d));
                packageVerificationResult = b.b;
                return packageVerificationResult;
            }
            Preconditions.checkNotNull(c.b);
            return PackageVerificationResult.zza(str, c.b, c.c);
        }
        throw new zzae();
    }

    @NonNull
    @ShowFirstParty
    @KeepForSdk
    public PackageVerificationResult queryPackageSignatureVerifiedWithRetry(@NonNull Context context, @NonNull String str) {
        try {
            PackageVerificationResult queryPackageSignatureVerified = queryPackageSignatureVerified(context, str);
            queryPackageSignatureVerified.zzb();
            return queryPackageSignatureVerified;
        } catch (SecurityException e) {
            PackageVerificationResult queryPackageSignatureVerified2 = queryPackageSignatureVerified(context, str);
            if (queryPackageSignatureVerified2.zzc()) {
                Log.e("PkgSignatureVerifier", "Got flaky result during package signature verification", e);
                return queryPackageSignatureVerified2;
            }
            return queryPackageSignatureVerified2;
        }
    }
}
