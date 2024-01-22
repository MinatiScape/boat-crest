package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.common.zzag;
import com.google.errorprone.annotations.RestrictedInheritance;
import java.util.HashMap;
@ShowFirstParty
@KeepForSdk
@RestrictedInheritance(allowedOnPath = ".*javatests/com/google/android/gmscore/integ/client/common/robolectric/.*", explanation = "Sub classing of GMS Core's APIs are restricted to testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes6.dex */
public class GmsSignatureVerifier {
    static {
        q qVar = new q();
        qVar.d("com.google.android.gms");
        qVar.a(204200000L);
        k kVar = m.d;
        qVar.c(zzag.zzn(kVar.t(), m.b.t()));
        k kVar2 = m.c;
        qVar.b(zzag.zzn(kVar2.t(), m.f8351a.t()));
        qVar.e();
        q qVar2 = new q();
        qVar2.d("com.android.vending");
        qVar2.a(82240000L);
        qVar2.c(zzag.zzm(kVar.t()));
        qVar2.b(zzag.zzm(kVar2.t()));
        qVar2.e();
        new HashMap();
    }
}
