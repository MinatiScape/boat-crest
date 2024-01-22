package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.RestrictedInheritance;
import javax.annotation.Nullable;
@CheckReturnValue
@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms/common/testing/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes6.dex */
public class GoogleSignatureVerifier {
    @Nullable
    public static GoogleSignatureVerifier c;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8237a;
    public volatile String b;

    public GoogleSignatureVerifier(@NonNull Context context) {
        this.f8237a = context.getApplicationContext();
    }

    @Nullable
    public static final i a(PackageInfo packageInfo, i... iVarArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr == null) {
            return null;
        }
        if (signatureArr.length != 1) {
            Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            return null;
        }
        j jVar = new j(packageInfo.signatures[0].toByteArray());
        for (int i = 0; i < iVarArr.length; i++) {
            if (iVarArr[i].equals(jVar)) {
                return iVarArr[i];
            }
        }
        return null;
    }

    @NonNull
    @KeepForSdk
    public static GoogleSignatureVerifier getInstance(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        synchronized (GoogleSignatureVerifier.class) {
            if (c == null) {
                m.e(context);
                c = new GoogleSignatureVerifier(context);
            }
        }
        return c;
    }

    public static final boolean zzb(@NonNull PackageInfo packageInfo, boolean z) {
        if (z && packageInfo != null && ("com.android.vending".equals(packageInfo.packageName) || "com.google.android.gms".equals(packageInfo.packageName))) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            z = (applicationInfo == null || (applicationInfo.flags & 129) == 0) ? false : true;
        }
        if (packageInfo != null && packageInfo.signatures != null) {
            if ((z ? a(packageInfo, l.f8349a) : a(packageInfo, l.f8349a[0])) != null) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public final o b(String str, boolean z, boolean z2) {
        o c2;
        ApplicationInfo applicationInfo;
        if (str == null) {
            return o.c("null pkg");
        }
        if (str.equals(this.b)) {
            return o.b();
        }
        if (m.g()) {
            c2 = m.b(str, GooglePlayServicesUtilLight.honorsDebugCertificates(this.f8237a), false, false);
        } else {
            try {
                PackageInfo packageInfo = this.f8237a.getPackageManager().getPackageInfo(str, 64);
                boolean honorsDebugCertificates = GooglePlayServicesUtilLight.honorsDebugCertificates(this.f8237a);
                if (packageInfo == null) {
                    c2 = o.c("null pkg");
                } else {
                    Signature[] signatureArr = packageInfo.signatures;
                    if (signatureArr != null && signatureArr.length == 1) {
                        j jVar = new j(packageInfo.signatures[0].toByteArray());
                        String str2 = packageInfo.packageName;
                        o a2 = m.a(str2, jVar, honorsDebugCertificates, false);
                        c2 = (!a2.f8364a || (applicationInfo = packageInfo.applicationInfo) == null || (applicationInfo.flags & 2) == 0 || !m.a(str2, jVar, false, true).f8364a) ? a2 : o.c("debuggable release cert app rejected");
                    } else {
                        c2 = o.c("single cert required");
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                return o.d("no pkg ".concat(str), e);
            }
        }
        if (c2.f8364a) {
            this.b = str;
        }
        return c2;
    }

    @KeepForSdk
    public boolean isGooglePublicSignedPackage(@NonNull PackageInfo packageInfo) {
        if (packageInfo == null) {
            return false;
        }
        if (zzb(packageInfo, false)) {
            return true;
        }
        if (zzb(packageInfo, true)) {
            if (GooglePlayServicesUtilLight.honorsDebugCertificates(this.f8237a)) {
                return true;
            }
            Log.w("GoogleSignatureVerifier", "Test-keys aren't accepted on this build.");
        }
        return false;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPackageGoogleSigned(@NonNull String str) {
        o b = b(str, false, false);
        b.e();
        return b.f8364a;
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isUidGoogleSigned(int i) {
        o c2;
        int length;
        String[] packagesForUid = this.f8237a.getPackageManager().getPackagesForUid(i);
        if (packagesForUid != null && (length = packagesForUid.length) != 0) {
            c2 = null;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    c2 = b(packagesForUid[i2], false, false);
                    if (c2.f8364a) {
                        break;
                    }
                    i2++;
                } else {
                    Preconditions.checkNotNull(c2);
                    break;
                }
            }
        } else {
            c2 = o.c("no pkgs");
        }
        c2.e();
        return c2.f8364a;
    }
}
