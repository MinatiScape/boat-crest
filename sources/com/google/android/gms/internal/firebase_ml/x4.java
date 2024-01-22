package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import java.io.IOException;
/* loaded from: classes7.dex */
public final class x4 extends zzkj {
    public final /* synthetic */ FirebaseApp b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x4(zzrt zzrtVar, String str, FirebaseApp firebaseApp) {
        super(str);
        this.b = firebaseApp;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzkj
    public final void zza(zzkg<?> zzkgVar) throws IOException {
        String d;
        super.zza(zzkgVar);
        Context applicationContext = this.b.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        zzkgVar.zzfl().put("X-Android-Package", packageName);
        d = zzrt.d(applicationContext, packageName);
        zzkgVar.zzfl().put("X-Android-Cert", d);
    }
}
