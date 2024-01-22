package com.google.android.gms.internal.firebase_ml;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.auth.api.AuthProxy;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.internal.firebase_ml.zzgd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.FirebaseMLException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes7.dex */
public final class zzrt implements zzpu<zzkl, zzrs> {
    public static final zzhh f = new zzht();
    public static final zzhx g = zzig.zzht();
    public static final GmsLogger h = new GmsLogger("ImageAnnotatorTask", "");
    public static final Map<FirebaseApp, zzrt> i = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    public String f8800a;
    public final boolean b;
    public final GoogleApiClient c;
    public final zzkj d;
    public final String e;

    public zzrt(FirebaseApp firebaseApp, boolean z, GoogleApiClient googleApiClient) {
        this.b = z;
        if (z) {
            this.c = googleApiClient;
            this.d = new y4(this);
        } else {
            this.c = null;
            this.d = new x4(this, c(firebaseApp), firebaseApp);
        }
        this.e = String.format("FirebaseML_%s", firebaseApp.getName());
    }

    public static String c(FirebaseApp firebaseApp) {
        Bundle bundle;
        String apiKey = firebaseApp.getOptions().getApiKey();
        Context applicationContext = firebaseApp.getApplicationContext();
        try {
            ApplicationInfo applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null) {
                return apiKey;
            }
            String string = bundle.getString("com.firebase.ml.cloud.ApiKeyForDebug");
            return !TextUtils.isEmpty(string) ? string : apiKey;
        } catch (PackageManager.NameNotFoundException e) {
            GmsLogger gmsLogger = h;
            String valueOf = String.valueOf(applicationContext.getPackageName());
            gmsLogger.e("ImageAnnotatorTask", valueOf.length() != 0 ? "No such package: ".concat(valueOf) : new String("No such package: "), e);
            return apiKey;
        }
    }

    public static String d(Context context, String str) {
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, str);
            if (packageCertificateHashBytes == null) {
                GmsLogger gmsLogger = h;
                String valueOf = String.valueOf(str);
                gmsLogger.e("ImageAnnotatorTask", valueOf.length() != 0 ? "Could not get fingerprint hash: ".concat(valueOf) : new String("Could not get fingerprint hash: "));
                return null;
            }
            return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException e) {
            GmsLogger gmsLogger2 = h;
            String valueOf2 = String.valueOf(str);
            gmsLogger2.e("ImageAnnotatorTask", valueOf2.length() != 0 ? "No such package: ".concat(valueOf2) : new String("No such package: "), e);
            return null;
        }
    }

    public static synchronized zzrt zza(@NonNull FirebaseApp firebaseApp, boolean z, @Nullable GoogleApiClient googleApiClient) {
        zzrt zzrtVar;
        synchronized (zzrt.class) {
            Map<FirebaseApp, zzrt> map = i;
            zzrtVar = map.get(firebaseApp);
            if (zzrtVar == null) {
                zzrtVar = new zzrt(firebaseApp, z, googleApiClient);
                map.put(firebaseApp, zzrtVar);
            }
        }
        return zzrtVar;
    }

    @WorkerThread
    public final String a() throws FirebaseMLException {
        if (!this.c.isConnected()) {
            this.c.blockingConnect(3L, TimeUnit.SECONDS);
        }
        try {
            return AuthProxy.ProxyApi.getSpatulaHeader(this.c).await(3L, TimeUnit.SECONDS).getSpatulaHeader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    public final zzqp zzoc() {
        return null;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzpu
    @WorkerThread
    public final zzkl zza(zzrs zzrsVar) throws FirebaseMLException {
        zzkk zzc = new zzkk().zzc(Collections.singletonList(new zzki().zzb(zzrsVar.features).zza(new zzkx().zze(zzrsVar.zzbrd)).zza(zzrsVar.imageContext)));
        int i2 = 14;
        try {
            zzkc zzih = ((zzkf) new zzkf(f, g, new z4(this)).zzm(this.e)).zza(this.d).zzih();
            if (this.b) {
                String a2 = a();
                this.f8800a = a2;
                if (TextUtils.isEmpty(a2)) {
                    h.e("ImageAnnotatorTask", "Failed to contact Google Play services for certificate fingerprint matching. Please ensure you have latest Google Play services installed");
                    throw new FirebaseMLException("Failed to contact Google Play services for certificate fingerprint matching. Please ensure you have latest Google Play services installed", 14);
                }
            }
            List<zzkl> zzin = new zzke(zzih).zza(zzc).zzfm().zzin();
            if (zzin != null && !zzin.isEmpty()) {
                return zzin.get(0);
            }
            throw new FirebaseMLException("Empty response from cloud vision api.", 13);
        } catch (zzgf e) {
            GmsLogger gmsLogger = h;
            String valueOf = String.valueOf(e.zzfj());
            StringBuilder sb = new StringBuilder(valueOf.length() + 44);
            sb.append("batchAnnotateImages call failed with error: ");
            sb.append(valueOf);
            gmsLogger.e("ImageAnnotatorTask", sb.toString());
            if (this.b) {
                Log.d("ImageAnnotatorTask", "If you are developing / testing on a simulator, either register your development app on Firebase console or turn off enforceCertFingerprintMatch()");
            }
            String message = e.getMessage();
            if (e.getStatusCode() != 400) {
                if (e.zzfj() != null && e.zzfj().zzfe() != null) {
                    i2 = 13;
                    for (zzgd.zza zzaVar : e.zzfj().zzfe()) {
                        String reason = zzaVar.getReason();
                        if (reason != null) {
                            if (reason.equals("rateLimitExceeded") || reason.equals("dailyLimitExceeded") || reason.equals("userRateLimitExceeded")) {
                                i2 = 8;
                                continue;
                            } else {
                                if (!reason.equals("accessNotConfigured")) {
                                    if (reason.equals("forbidden") || reason.equals("inactiveBillingState")) {
                                        message = String.format("If you haven't set up billing, please go to Firebase console to set up billing: %s. If you are specifying a debug Api Key override and turned on Api Key restrictions, make sure the restrictions are set up correctly", "https://console.firebase.google.com/u/0/project/_/overview?purchaseBillingPlan=true");
                                    }
                                }
                                i2 = 7;
                                continue;
                            }
                        }
                        if (i2 != 13) {
                            break;
                        }
                    }
                } else {
                    i2 = 13;
                }
            }
            throw new FirebaseMLException(message, i2);
        } catch (IOException e2) {
            h.e("ImageAnnotatorTask", "batchAnnotateImages call failed with exception: ", e2);
            throw new FirebaseMLException("Cloud Vision batchAnnotateImages call failure", 13, e2);
        }
    }
}
