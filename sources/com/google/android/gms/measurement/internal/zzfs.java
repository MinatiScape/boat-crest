package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzhu;
import com.google.android.gms.internal.measurement.zzny;
import com.google.android.gms.internal.measurement.zzob;
import com.google.firebase.messaging.Constants;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public final class zzfs implements y0 {
    public static volatile zzfs F;
    public volatile Boolean A;
    public volatile boolean B;
    public int C;
    @VisibleForTesting
    public final long E;

    /* renamed from: a  reason: collision with root package name */
    public final Context f10154a;
    public final String b;
    public final String c;
    public final String d;
    public final boolean e;
    public final zzaa f;
    public final zzaf g;
    public final v h;
    public final zzei i;
    public final zzfp j;
    public final zzjy k;
    public final zzku l;
    public final zzed m;
    public final Clock n;
    public final zzij o;
    public final zzhv p;
    public final zzd q;
    public final zzhz r;
    public final String s;
    public zzec t;
    public zzjj u;
    public zzan v;
    public zzea w;
    public Boolean y;
    public long z;
    @VisibleForTesting
    public Boolean zza;
    @VisibleForTesting
    public Boolean zzb;
    public boolean x = false;
    public final AtomicInteger D = new AtomicInteger(0);

    public zzfs(zzgu zzguVar) {
        long currentTimeMillis;
        Bundle bundle;
        boolean z = false;
        Preconditions.checkNotNull(zzguVar);
        zzaa zzaaVar = new zzaa(zzguVar.f10156a);
        this.f = zzaaVar;
        l.f10122a = zzaaVar;
        Context context = zzguVar.f10156a;
        this.f10154a = context;
        this.b = zzguVar.b;
        this.c = zzguVar.c;
        this.d = zzguVar.d;
        this.e = zzguVar.h;
        this.A = zzguVar.e;
        this.s = zzguVar.j;
        this.B = true;
        com.google.android.gms.internal.measurement.zzcl zzclVar = zzguVar.g;
        if (zzclVar != null && (bundle = zzclVar.zzg) != null) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zza = (Boolean) obj;
            }
            Object obj2 = zzclVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzb = (Boolean) obj2;
            }
        }
        zzhu.zzd(context);
        Clock defaultClock = DefaultClock.getInstance();
        this.n = defaultClock;
        Long l = zzguVar.i;
        if (l != null) {
            currentTimeMillis = l.longValue();
        } else {
            currentTimeMillis = defaultClock.currentTimeMillis();
        }
        this.E = currentTimeMillis;
        this.g = new zzaf(this);
        v vVar = new v(this);
        vVar.zzv();
        this.h = vVar;
        zzei zzeiVar = new zzei(this);
        zzeiVar.zzv();
        this.i = zzeiVar;
        zzku zzkuVar = new zzku(this);
        zzkuVar.zzv();
        this.l = zzkuVar;
        zzed zzedVar = new zzed(this);
        zzedVar.zzv();
        this.m = zzedVar;
        this.q = new zzd(this);
        zzij zzijVar = new zzij(this);
        zzijVar.zzb();
        this.o = zzijVar;
        zzhv zzhvVar = new zzhv(this);
        zzhvVar.zzb();
        this.p = zzhvVar;
        zzjy zzjyVar = new zzjy(this);
        zzjyVar.zzb();
        this.k = zzjyVar;
        zzhz zzhzVar = new zzhz(this);
        zzhzVar.zzv();
        this.r = zzhzVar;
        zzfp zzfpVar = new zzfp(this);
        zzfpVar.zzv();
        this.j = zzfpVar;
        com.google.android.gms.internal.measurement.zzcl zzclVar2 = zzguVar.g;
        z = (zzclVar2 == null || zzclVar2.zzb == 0) ? true : z;
        if (context.getApplicationContext() instanceof Application) {
            zzhv zzq = zzq();
            if (zzq.zzs.f10154a.getApplicationContext() instanceof Application) {
                Application application = (Application) zzq.zzs.f10154a.getApplicationContext();
                if (zzq.zza == null) {
                    zzq.zza = new w1(zzq, null);
                }
                if (z) {
                    application.unregisterActivityLifecycleCallbacks(zzq.zza);
                    application.registerActivityLifecycleCallbacks(zzq.zza);
                    zzq.zzs.zzay().zzj().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzay().zzk().zza("Application context is not an Application");
        }
        zzfpVar.zzp(new e0(this, zzguVar));
    }

    public static /* bridge */ /* synthetic */ void a(zzfs zzfsVar, zzgu zzguVar) {
        zzfsVar.zzaz().zzg();
        zzfsVar.g.e();
        zzan zzanVar = new zzan(zzfsVar);
        zzanVar.zzv();
        zzfsVar.v = zzanVar;
        zzea zzeaVar = new zzea(zzfsVar, zzguVar.f);
        zzeaVar.zzb();
        zzfsVar.w = zzeaVar;
        zzec zzecVar = new zzec(zzfsVar);
        zzecVar.zzb();
        zzfsVar.t = zzecVar;
        zzjj zzjjVar = new zzjj(zzfsVar);
        zzjjVar.zzb();
        zzfsVar.u = zzjjVar;
        zzfsVar.l.zzw();
        zzfsVar.h.zzw();
        zzfsVar.w.zzc();
        zzeg zzi = zzfsVar.zzay().zzi();
        zzfsVar.g.zzh();
        zzi.zzb("App measurement initialized, version", 42097L);
        zzfsVar.zzay().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String zzl = zzeaVar.zzl();
        if (TextUtils.isEmpty(zzfsVar.b)) {
            if (zzfsVar.zzv().u(zzl)) {
                zzfsVar.zzay().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                zzeg zzi2 = zzfsVar.zzay().zzi();
                String valueOf = String.valueOf(zzl);
                zzi2.zza(valueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(valueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app "));
            }
        }
        zzfsVar.zzay().zzc().zza("Debug-level message logging enabled");
        if (zzfsVar.C != zzfsVar.D.get()) {
            zzfsVar.zzay().zzd().zzc("Not all components initialized", Integer.valueOf(zzfsVar.C), Integer.valueOf(zzfsVar.D.get()));
        }
        zzfsVar.x = true;
    }

    public static final void e() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    public static final void f(w0 w0Var) {
        if (w0Var == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    public static final void g(x xVar) {
        if (xVar != null) {
            if (xVar.a()) {
                return;
            }
            String valueOf = String.valueOf(xVar.getClass());
            StringBuilder sb = new StringBuilder(valueOf.length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
        throw new IllegalStateException("Component not created");
    }

    public static final void h(x0 x0Var) {
        if (x0Var != null) {
            if (x0Var.a()) {
                return;
            }
            String valueOf = String.valueOf(x0Var.getClass());
            StringBuilder sb = new StringBuilder(valueOf.length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzfs zzp(Context context, com.google.android.gms.internal.measurement.zzcl zzclVar, Long l) {
        Bundle bundle;
        if (zzclVar != null && (zzclVar.zze == null || zzclVar.zzf == null)) {
            zzclVar = new com.google.android.gms.internal.measurement.zzcl(zzclVar.zza, zzclVar.zzb, zzclVar.zzc, zzclVar.zzd, null, null, zzclVar.zzg, null);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (F == null) {
            synchronized (zzfs.class) {
                if (F == null) {
                    F = new zzfs(new zzgu(context, zzclVar, l));
                }
            }
        } else if (zzclVar != null && (bundle = zzclVar.zzg) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            Preconditions.checkNotNull(F);
            F.A = Boolean.valueOf(zzclVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(F);
        return F;
    }

    public final void b() {
        this.D.incrementAndGet();
    }

    public final void c() {
        this.C++;
    }

    @WorkerThread
    public final void d(boolean z) {
        this.A = Boolean.valueOf(z);
    }

    @SideEffectFree
    public final zzfp i() {
        return this.j;
    }

    public final /* synthetic */ void zzC(String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> queryIntentActivities;
        if (i != 200 && i != 204) {
            if (i == 304) {
                i = 304;
            }
            zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
        }
        if (th == null) {
            zzm().q.zza(true);
            if (bArr != null && bArr.length != 0) {
                try {
                    JSONObject jSONObject = new JSONObject(new String(bArr));
                    String optString = jSONObject.optString("deeplink", "");
                    String optString2 = jSONObject.optString("gclid", "");
                    double optDouble = jSONObject.optDouble("timestamp", 0.0d);
                    if (TextUtils.isEmpty(optString)) {
                        zzay().zzc().zza("Deferred Deep Link is empty.");
                        return;
                    }
                    zzku zzv = zzv();
                    zzfs zzfsVar = zzv.zzs;
                    if (!TextUtils.isEmpty(optString) && (queryIntentActivities = zzv.zzs.f10154a.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(optString)), 0)) != null && !queryIntentActivities.isEmpty()) {
                        Bundle bundle = new Bundle();
                        bundle.putString("gclid", optString2);
                        bundle.putString("_cis", "ddp");
                        this.p.b("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle);
                        zzku zzv2 = zzv();
                        if (TextUtils.isEmpty(optString)) {
                            return;
                        }
                        try {
                            SharedPreferences.Editor edit = zzv2.zzs.f10154a.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                            edit.putString("deeplink", optString);
                            edit.putLong("timestamp", Double.doubleToRawLongBits(optDouble));
                            if (edit.commit()) {
                                zzv2.zzs.f10154a.sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
                                return;
                            }
                            return;
                        } catch (RuntimeException e) {
                            zzv2.zzs.zzay().zzd().zzb("Failed to persist Deferred Deep Link. exception", e);
                            return;
                        }
                    }
                    zzay().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", optString2, optString);
                    return;
                } catch (JSONException e2) {
                    zzay().zzd().zzb("Failed to parse the Deferred Deep Link response. exception", e2);
                    return;
                }
            }
            zzay().zzc().zza("Deferred Deep Link response empty.");
            return;
        }
        zzay().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
    }

    @WorkerThread
    public final void zzE() {
        zzaz().zzg();
        h(zzr());
        String zzl = zzh().zzl();
        Pair<String, Boolean> c = zzm().c(zzl);
        if (this.g.zzr() && !((Boolean) c.second).booleanValue() && !TextUtils.isEmpty((CharSequence) c.first)) {
            zzhz zzr = zzr();
            zzr.zzu();
            ConnectivityManager connectivityManager = (ConnectivityManager) zzr.zzs.f10154a.getSystemService("connectivity");
            NetworkInfo networkInfo = null;
            if (connectivityManager != null) {
                try {
                    networkInfo = connectivityManager.getActiveNetworkInfo();
                } catch (SecurityException unused) {
                }
            }
            if (networkInfo != null && networkInfo.isConnected()) {
                zzku zzv = zzv();
                zzh().zzs.g.zzh();
                URL zzD = zzv.zzD(42097L, zzl, (String) c.first, zzm().r.zza() - 1);
                if (zzD != null) {
                    zzhz zzr2 = zzr();
                    zzfq zzfqVar = new zzfq(this);
                    zzr2.zzg();
                    zzr2.zzu();
                    Preconditions.checkNotNull(zzD);
                    Preconditions.checkNotNull(zzfqVar);
                    zzr2.zzs.zzaz().zzo(new x1(zzr2, zzl, zzD, null, null, zzfqVar, null));
                    return;
                }
                return;
            }
            zzay().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        zzay().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
    }

    @WorkerThread
    public final void zzG(boolean z) {
        zzaz().zzg();
        this.B = z;
    }

    @WorkerThread
    public final void zzH(com.google.android.gms.internal.measurement.zzcl zzclVar) {
        zzag zzagVar;
        zzaz().zzg();
        zzag d = zzm().d();
        v zzm = zzm();
        zzfs zzfsVar = zzm.zzs;
        zzm.zzg();
        int i = 100;
        int i2 = zzm.b().getInt("consent_source", 100);
        zzaf zzafVar = this.g;
        zzfs zzfsVar2 = zzafVar.zzs;
        Boolean d2 = zzafVar.d("google_analytics_default_allow_ad_storage");
        zzaf zzafVar2 = this.g;
        zzfs zzfsVar3 = zzafVar2.zzs;
        Boolean d3 = zzafVar2.d("google_analytics_default_allow_analytics_storage");
        if ((d2 != null || d3 != null) && zzm().j(-10)) {
            zzagVar = new zzag(d2, d3);
            i = -10;
        } else {
            if (!TextUtils.isEmpty(zzh().f()) && (i2 == 0 || i2 == 30 || i2 == 10 || i2 == 30 || i2 == 30 || i2 == 40)) {
                zzq().zzR(zzag.zza, -10, this.E);
            } else {
                zzob.zzc();
                if ((!this.g.zzs(null, zzdw.zzau) || TextUtils.isEmpty(zzh().f())) && zzclVar != null && zzclVar.zzg != null && zzm().j(30)) {
                    zzagVar = zzag.zza(zzclVar.zzg);
                    if (!zzagVar.equals(zzag.zza)) {
                        i = 30;
                    }
                }
            }
            zzagVar = null;
        }
        if (zzagVar != null) {
            zzq().zzR(zzagVar, i, this.E);
            d = zzagVar;
        }
        zzq().g(d);
        if (zzm().d.zza() == 0) {
            zzay().zzj().zzb("Persisting first open", Long.valueOf(this.E));
            zzm().d.zzb(this.E);
        }
        zzq().l.c();
        if (!zzM()) {
            if (zzJ()) {
                if (!zzv().t("android.permission.INTERNET")) {
                    zzay().zzd().zza("App is missing INTERNET permission");
                }
                if (!zzv().t("android.permission.ACCESS_NETWORK_STATE")) {
                    zzay().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
                }
                if (!Wrappers.packageManager(this.f10154a).isCallerInstantApp() && !this.g.h()) {
                    if (!zzku.z(this.f10154a)) {
                        zzay().zzd().zza("AppMeasurementReceiver not registered/enabled");
                    }
                    if (!zzku.A(this.f10154a, false)) {
                        zzay().zzd().zza("AppMeasurementService not registered/enabled");
                    }
                }
                zzay().zzd().zza("Uploading is not possible. App measurement disabled");
            }
        } else {
            if (!TextUtils.isEmpty(zzh().f()) || !TextUtils.isEmpty(zzh().e())) {
                zzku zzv = zzv();
                String f = zzh().f();
                v zzm2 = zzm();
                zzm2.zzg();
                String string = zzm2.b().getString("gmp_app_id", null);
                String e = zzh().e();
                v zzm3 = zzm();
                zzm3.zzg();
                if (zzv.C(f, string, e, zzm3.b().getString("admob_app_id", null))) {
                    zzay().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    v zzm4 = zzm();
                    zzm4.zzg();
                    Boolean e2 = zzm4.e();
                    SharedPreferences.Editor edit = zzm4.b().edit();
                    edit.clear();
                    edit.apply();
                    if (e2 != null) {
                        zzm4.f(e2);
                    }
                    zzi().zzj();
                    this.u.zzs();
                    this.u.q();
                    zzm().d.zzb(this.E);
                    zzm().f.zzb(null);
                }
                v zzm5 = zzm();
                String f2 = zzh().f();
                zzm5.zzg();
                SharedPreferences.Editor edit2 = zzm5.b().edit();
                edit2.putString("gmp_app_id", f2);
                edit2.apply();
                v zzm6 = zzm();
                String e3 = zzh().e();
                zzm6.zzg();
                SharedPreferences.Editor edit3 = zzm6.b().edit();
                edit3.putString("admob_app_id", e3);
                edit3.apply();
            }
            if (!zzm().d().zzk()) {
                zzm().f.zzb(null);
            }
            zzq().f(zzm().f.zza());
            zzny.zzc();
            if (this.g.zzs(null, zzdw.zzai)) {
                try {
                    zzv().zzs.f10154a.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                } catch (ClassNotFoundException unused) {
                    if (!TextUtils.isEmpty(zzm().s.zza())) {
                        zzay().zzk().zza("Remote config removed with active feature rollouts");
                        zzm().s.zzb(null);
                    }
                }
            }
            if (!TextUtils.isEmpty(zzh().f()) || !TextUtils.isEmpty(zzh().e())) {
                boolean zzJ = zzJ();
                if (!zzm().h() && !this.g.zzv()) {
                    zzm().g(!zzJ);
                }
                if (zzJ) {
                    zzq().zzy();
                }
                zzu().zza.a();
                zzt().zzu(new AtomicReference<>());
                zzt().zzH(zzm().v.zza());
            }
        }
        zzm().m.zza(true);
    }

    @WorkerThread
    public final boolean zzI() {
        return this.A != null && this.A.booleanValue();
    }

    @WorkerThread
    public final boolean zzJ() {
        return zza() == 0;
    }

    @WorkerThread
    public final boolean zzK() {
        zzaz().zzg();
        return this.B;
    }

    @Pure
    public final boolean zzL() {
        return TextUtils.isEmpty(this.b);
    }

    @WorkerThread
    public final boolean zzM() {
        if (this.x) {
            zzaz().zzg();
            Boolean bool = this.y;
            if (bool == null || this.z == 0 || (!bool.booleanValue() && Math.abs(this.n.elapsedRealtime() - this.z) > 1000)) {
                this.z = this.n.elapsedRealtime();
                boolean z = true;
                Boolean valueOf = Boolean.valueOf(zzv().t("android.permission.INTERNET") && zzv().t("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.f10154a).isCallerInstantApp() || this.g.h() || (zzku.z(this.f10154a) && zzku.A(this.f10154a, false))));
                this.y = valueOf;
                if (valueOf.booleanValue()) {
                    if (!zzv().m(zzh().f(), zzh().e(), zzh().zzm()) && TextUtils.isEmpty(zzh().e())) {
                        z = false;
                    }
                    this.y = Boolean.valueOf(z);
                }
            }
            return this.y.booleanValue();
        }
        throw new IllegalStateException("AppMeasurement is not initialized");
    }

    @Pure
    public final boolean zzN() {
        return this.e;
    }

    @WorkerThread
    public final int zza() {
        zzaz().zzg();
        if (this.g.zzv()) {
            return 1;
        }
        Boolean bool = this.zzb;
        if (bool == null || !bool.booleanValue()) {
            zzaz().zzg();
            if (this.B) {
                Boolean e = zzm().e();
                if (e != null) {
                    return e.booleanValue() ? 0 : 3;
                }
                zzaf zzafVar = this.g;
                zzaa zzaaVar = zzafVar.zzs.f;
                Boolean d = zzafVar.d("firebase_analytics_collection_enabled");
                if (d != null) {
                    return d.booleanValue() ? 0 : 4;
                }
                Boolean bool2 = this.zza;
                return bool2 != null ? bool2.booleanValue() ? 0 : 5 : (!this.g.zzs(null, zzdw.zzS) || this.A == null || this.A.booleanValue()) ? 0 : 7;
            }
            return 8;
        }
        return 2;
    }

    @Override // com.google.android.gms.measurement.internal.y0
    @Pure
    public final Context zzau() {
        return this.f10154a;
    }

    @Override // com.google.android.gms.measurement.internal.y0
    @Pure
    public final Clock zzav() {
        return this.n;
    }

    @Override // com.google.android.gms.measurement.internal.y0
    @Pure
    public final zzaa zzaw() {
        return this.f;
    }

    @Override // com.google.android.gms.measurement.internal.y0
    @Pure
    public final zzei zzay() {
        h(this.i);
        return this.i;
    }

    @Override // com.google.android.gms.measurement.internal.y0
    @Pure
    public final zzfp zzaz() {
        h(this.j);
        return this.j;
    }

    @Pure
    public final zzd zzd() {
        zzd zzdVar = this.q;
        if (zzdVar != null) {
            return zzdVar;
        }
        throw new IllegalStateException("Component not created");
    }

    @Pure
    public final zzaf zzf() {
        return this.g;
    }

    @Pure
    public final zzan zzg() {
        h(this.v);
        return this.v;
    }

    @Pure
    public final zzea zzh() {
        g(this.w);
        return this.w;
    }

    @Pure
    public final zzec zzi() {
        g(this.t);
        return this.t;
    }

    @Pure
    public final zzed zzj() {
        f(this.m);
        return this.m;
    }

    public final zzei zzl() {
        zzei zzeiVar = this.i;
        if (zzeiVar == null || !zzeiVar.a()) {
            return null;
        }
        return this.i;
    }

    @Pure
    public final v zzm() {
        f(this.h);
        return this.h;
    }

    @Pure
    public final zzhv zzq() {
        g(this.p);
        return this.p;
    }

    @Pure
    public final zzhz zzr() {
        h(this.r);
        return this.r;
    }

    @Pure
    public final zzij zzs() {
        g(this.o);
        return this.o;
    }

    @Pure
    public final zzjj zzt() {
        g(this.u);
        return this.u;
    }

    @Pure
    public final zzjy zzu() {
        g(this.k);
        return this.k;
    }

    @Pure
    public final zzku zzv() {
        f(this.l);
        return this.l;
    }

    @Pure
    public final String zzw() {
        return this.b;
    }

    @Pure
    public final String zzx() {
        return this.c;
    }

    @Pure
    public final String zzy() {
        return this.d;
    }

    @Pure
    public final String zzz() {
        return this.s;
    }
}
