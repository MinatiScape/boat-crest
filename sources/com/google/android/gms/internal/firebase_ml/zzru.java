package com.google.android.gms.internal.firebase_ml;

import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Add missing generic type declarations: [ResultType] */
/* loaded from: classes7.dex */
public final class zzru<ResultType> implements zzpu<ResultType, zzrs>, zzqp {

    /* renamed from: a  reason: collision with root package name */
    public final zzrt f8801a;
    public final GoogleApiClient b;
    public final /* synthetic */ zzrr c;

    public zzru(zzrr zzrrVar, FirebaseApp firebaseApp, boolean z) {
        this.c = zzrrVar;
        if (z) {
            GoogleApiClient build = new GoogleApiClient.Builder(firebaseApp.getApplicationContext()).addApi(AuthProxy.API).build();
            this.b = build;
            build.connect();
        } else {
            this.b = null;
        }
        this.f8801a = zzrt.zza(firebaseApp, z, this.b);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    public final void release() {
        GoogleApiClient googleApiClient = this.b;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    public final /* synthetic */ Object zza(zzrs zzrsVar) throws FirebaseMLException {
        zzrs zzrsVar2 = zzrsVar;
        return this.c.zza(this.f8801a.zza(zzrsVar2), zzrsVar2.zzbre);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return this;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzqp
    @WorkerThread
    public final void zzol() throws FirebaseMLException {
        GoogleApiClient googleApiClient = this.b;
        if (googleApiClient != null && !zzmf.equal(googleApiClient.blockingConnect(3L, TimeUnit.SECONDS), ConnectionResult.RESULT_SUCCESS)) {
            throw new FirebaseMLException("Failed to contact Google Play services", 14);
        }
    }
}
