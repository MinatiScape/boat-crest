package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzny;
import com.google.android.gms.internal.measurement.zzoh;
import com.google.android.gms.internal.measurement.zzpx;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class zzhv extends x {
    public zzgs b;
    public final Set<zzgt> c;
    public boolean d;
    public final AtomicReference<String> e;
    public final Object f;
    @GuardedBy("consentLock")
    public zzag g;
    @GuardedBy("consentLock")
    public int h;
    public final AtomicLong i;
    public long j;
    public int k;
    public final zzr l;
    public final a4 m;
    @VisibleForTesting
    public w1 zza;
    @VisibleForTesting
    public boolean zzc;

    public zzhv(zzfs zzfsVar) {
        super(zzfsVar);
        this.c = new CopyOnWriteArraySet();
        this.f = new Object();
        this.zzc = true;
        this.m = new m1(this);
        this.e = new AtomicReference<>();
        this.g = new zzag(null, null);
        this.h = 100;
        this.j = -1L;
        this.k = 100;
        this.i = new AtomicLong(0L);
        this.l = new zzr(zzfsVar);
    }

    public static /* bridge */ /* synthetic */ void k(zzhv zzhvVar, zzag zzagVar, int i, long j, boolean z, boolean z2) {
        zzhvVar.zzg();
        zzhvVar.zza();
        if (j <= zzhvVar.j && zzag.zzl(zzhvVar.k, i)) {
            zzhvVar.zzs.zzay().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzagVar);
            return;
        }
        v zzm = zzhvVar.zzs.zzm();
        zzfs zzfsVar = zzm.zzs;
        zzm.zzg();
        if (zzm.j(i)) {
            SharedPreferences.Editor edit = zzm.b().edit();
            edit.putString("consent_settings", zzagVar.zzi());
            edit.putInt("consent_source", i);
            edit.apply();
            zzhvVar.j = j;
            zzhvVar.k = i;
            zzhvVar.zzs.zzt().zzF(z);
            if (z2) {
                zzhvVar.zzs.zzt().zzu(new AtomicReference<>());
                return;
            }
            return;
        }
        zzhvVar.zzs.zzay().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(i));
    }

    @WorkerThread
    public final void b(String str, String str2, Bundle bundle) {
        zzg();
        c(str, str2, this.zzs.zzav().currentTimeMillis(), bundle);
    }

    @WorkerThread
    public final void c(String str, String str2, long j, Bundle bundle) {
        zzg();
        zzH(str, str2, j, bundle, true, this.b == null || zzku.x(str2), true, null);
    }

    public final void d(long j, boolean z) {
        zzg();
        zza();
        this.zzs.zzay().zzc().zza("Resetting analytics data (FE)");
        zzjy zzu = this.zzs.zzu();
        zzu.zzg();
        zzu.zzb.a();
        boolean zzJ = this.zzs.zzJ();
        v zzm = this.zzs.zzm();
        zzm.d.zzb(j);
        if (!TextUtils.isEmpty(zzm.zzs.zzm().s.zza())) {
            zzm.s.zzb(null);
        }
        zzoh.zzc();
        zzaf zzf = zzm.zzs.zzf();
        zzdv<Boolean> zzdvVar = zzdw.zzaj;
        if (zzf.zzs(null, zzdvVar)) {
            zzm.n.zzb(0L);
        }
        if (!zzm.zzs.zzf().zzv()) {
            zzm.g(!zzJ);
        }
        zzm.t.zzb(null);
        zzm.u.zzb(0L);
        zzm.v.zzb(null);
        if (z) {
            this.zzs.zzt().zzC();
        }
        zzoh.zzc();
        if (this.zzs.zzf().zzs(null, zzdvVar)) {
            this.zzs.zzu().zza.a();
        }
        this.zzc = !zzJ;
    }

    public final void e(String str, String str2, long j, Object obj) {
        this.zzs.zzaz().zzp(new e1(this, str, str2, obj, j));
    }

    public final void f(String str) {
        this.e.set(str);
    }

    @WorkerThread
    public final void g(zzag zzagVar) {
        zzg();
        boolean z = (zzagVar.zzk() && zzagVar.zzj()) || this.zzs.zzt().c();
        if (z != this.zzs.zzK()) {
            this.zzs.zzG(z);
            v zzm = this.zzs.zzm();
            zzfs zzfsVar = zzm.zzs;
            zzm.zzg();
            Boolean valueOf = zzm.b().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzm.b().getBoolean("measurement_enabled_from_api", true)) : null;
            if (!z || valueOf == null || valueOf.booleanValue()) {
                i(Boolean.valueOf(z), false);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007e  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h(java.lang.String r9, java.lang.String r10, java.lang.Object r11, long r12) {
        /*
            r8 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r10)
            r8.zzg()
            r8.zza()
            java.lang.String r0 = "allow_personalized_ads"
            boolean r0 = r0.equals(r10)
            java.lang.String r1 = "_npa"
            if (r0 == 0) goto L64
            boolean r0 = r11 instanceof java.lang.String
            if (r0 == 0) goto L52
            r0 = r11
            java.lang.String r0 = (java.lang.String) r0
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 != 0) goto L52
            r10 = 1
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r11 = r0.toLowerCase(r11)
            java.lang.String r0 = "false"
            boolean r11 = r0.equals(r11)
            r2 = 1
            if (r10 == r11) goto L37
            r10 = 0
            goto L38
        L37:
            r10 = r2
        L38:
            java.lang.Long r11 = java.lang.Long.valueOf(r10)
            com.google.android.gms.measurement.internal.zzfs r10 = r8.zzs
            com.google.android.gms.measurement.internal.v r10 = r10.zzm()
            com.google.android.gms.measurement.internal.zzew r10 = r10.l
            long r4 = r11.longValue()
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L4e
            java.lang.String r0 = "true"
        L4e:
            r10.zzb(r0)
            goto L61
        L52:
            if (r11 != 0) goto L64
            com.google.android.gms.measurement.internal.zzfs r10 = r8.zzs
            com.google.android.gms.measurement.internal.v r10 = r10.zzm()
            com.google.android.gms.measurement.internal.zzew r10 = r10.l
            java.lang.String r0 = "unset"
            r10.zzb(r0)
        L61:
            r6 = r11
            r3 = r1
            goto L66
        L64:
            r3 = r10
            r6 = r11
        L66:
            com.google.android.gms.measurement.internal.zzfs r10 = r8.zzs
            boolean r10 = r10.zzJ()
            if (r10 != 0) goto L7e
            com.google.android.gms.measurement.internal.zzfs r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzei r9 = r9.zzay()
            com.google.android.gms.measurement.internal.zzeg r9 = r9.zzj()
            java.lang.String r10 = "User property not set since app measurement is disabled"
            r9.zza(r10)
            return
        L7e:
            com.google.android.gms.measurement.internal.zzfs r10 = r8.zzs
            boolean r10 = r10.zzM()
            if (r10 != 0) goto L87
            return
        L87:
            com.google.android.gms.measurement.internal.zzkq r10 = new com.google.android.gms.measurement.internal.zzkq
            r2 = r10
            r4 = r12
            r7 = r9
            r2.<init>(r3, r4, r6, r7)
            com.google.android.gms.measurement.internal.zzfs r9 = r8.zzs
            com.google.android.gms.measurement.internal.zzjj r9 = r9.zzt()
            r9.zzK(r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhv.h(java.lang.String, java.lang.String, java.lang.Object, long):void");
    }

    @WorkerThread
    public final void i(Boolean bool, boolean z) {
        zzg();
        zza();
        this.zzs.zzay().zzc().zzb("Setting app measurement enabled (FE)", bool);
        this.zzs.zzm().f(bool);
        if (z) {
            v zzm = this.zzs.zzm();
            zzfs zzfsVar = zzm.zzs;
            zzm.zzg();
            SharedPreferences.Editor edit = zzm.b().edit();
            if (bool != null) {
                edit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
            } else {
                edit.remove("measurement_enabled_from_api");
            }
            edit.apply();
        }
        if (this.zzs.zzK() || !(bool == null || bool.booleanValue())) {
            j();
        }
    }

    @WorkerThread
    public final void j() {
        zzg();
        String zza = this.zzs.zzm().l.zza();
        if (zza != null) {
            if ("unset".equals(zza)) {
                h("app", "_npa", null, this.zzs.zzav().currentTimeMillis());
            } else {
                h("app", "_npa", Long.valueOf(true != "true".equals(zza) ? 0L : 1L), this.zzs.zzav().currentTimeMillis());
            }
        }
        if (this.zzs.zzJ() && this.zzc) {
            this.zzs.zzay().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
            zzy();
            zzoh.zzc();
            if (this.zzs.zzf().zzs(null, zzdw.zzaj)) {
                this.zzs.zzu().zza.a();
            }
            this.zzs.zzaz().zzp(new a1(this));
            return;
        }
        this.zzs.zzay().zzc().zza("Updating Scion state (FE)");
        this.zzs.zzt().zzI();
    }

    public final void zzA() {
        if (!(this.zzs.zzau().getApplicationContext() instanceof Application) || this.zza == null) {
            return;
        }
        ((Application) this.zzs.zzau().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
    }

    public final /* synthetic */ void zzB(Bundle bundle) {
        if (bundle == null) {
            this.zzs.zzm().v.zzb(new Bundle());
            return;
        }
        Bundle zza = this.zzs.zzm().v.zza();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                if (this.zzs.zzv().v(obj)) {
                    this.zzs.zzv().j(this.m, null, 27, null, null, 0);
                }
                this.zzs.zzay().zzl().zzc("Invalid default event parameter type. Name, value", str, obj);
            } else if (zzku.x(str)) {
                this.zzs.zzay().zzl().zzb("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                zza.remove(str);
            } else {
                zzku zzv = this.zzs.zzv();
                this.zzs.zzf();
                if (zzv.p("param", str, 100, obj)) {
                    this.zzs.zzv().k(zza, str, obj);
                }
            }
        }
        this.zzs.zzv();
        int zzc = this.zzs.zzf().zzc();
        if (zza.size() > zzc) {
            int i = 0;
            for (String str2 : new TreeSet(zza.keySet())) {
                i++;
                if (i > zzc) {
                    zza.remove(str2);
                }
            }
            this.zzs.zzv().j(this.m, null, 26, null, null, 0);
            this.zzs.zzay().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        this.zzs.zzm().v.zzb(zza);
        this.zzs.zzt().zzH(zza);
    }

    public final void zzC(String str, String str2, Bundle bundle) {
        zzD(str, str2, bundle, true, true, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzD(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        String str3 = str == null ? "app" : str;
        Bundle bundle2 = bundle == null ? new Bundle() : bundle;
        if (zzku.B(str2, FirebaseAnalytics.Event.SCREEN_VIEW)) {
            this.zzs.zzs().zzx(bundle2, j);
            return;
        }
        boolean z3 = true;
        if (z2 && this.b != null && !zzku.x(str2)) {
            z3 = false;
        }
        zzL(str3, str2, j, bundle2, z2, z3, z, null);
    }

    public final void zzE(String str, String str2, Bundle bundle, String str3) {
        zzfs.e();
        zzL("auto", str2, this.zzs.zzav().currentTimeMillis(), bundle, false, true, true, str3);
    }

    @WorkerThread
    public final void zzH(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        boolean z4;
        String str4;
        long j2;
        String str5;
        String str6;
        Bundle[] bundleArr;
        Class<?> cls;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(bundle);
        zzg();
        zza();
        if (this.zzs.zzJ()) {
            List<String> g = this.zzs.zzh().g();
            if (g != null && !g.contains(str2)) {
                this.zzs.zzay().zzc().zzc("Dropping non-safelisted event. event name, origin", str2, str);
                return;
            }
            if (!this.d) {
                this.d = true;
                try {
                    if (!this.zzs.zzN()) {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.zzs.zzau().getClassLoader());
                    } else {
                        cls = Class.forName("com.google.android.gms.tagmanager.TagManagerService");
                    }
                    try {
                        cls.getDeclaredMethod("initialize", Context.class).invoke(null, this.zzs.zzau());
                    } catch (Exception e) {
                        this.zzs.zzay().zzk().zzb("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException unused) {
                    this.zzs.zzay().zzi().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if (Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(str2) && bundle.containsKey("gclid")) {
                this.zzs.zzaw();
                h("auto", "_lgclid", bundle.getString("gclid"), this.zzs.zzav().currentTimeMillis());
            }
            this.zzs.zzaw();
            if (z && zzku.zzal(str2)) {
                this.zzs.zzv().h(bundle, this.zzs.zzm().v.zza());
            }
            if (!z3) {
                this.zzs.zzaw();
                if (!"_iap".equals(str2)) {
                    zzku zzv = this.zzs.zzv();
                    int i = 2;
                    if (zzv.s("event", str2)) {
                        if (zzv.o("event", zzgp.zza, zzgp.zzb, str2)) {
                            zzv.zzs.zzf();
                            if (zzv.n("event", 40, str2)) {
                                i = 0;
                            }
                        } else {
                            i = 13;
                        }
                    }
                    if (i != 0) {
                        this.zzs.zzay().zze().zzb("Invalid public event name. Event will not be logged (FE)", this.zzs.zzj().zzc(str2));
                        zzku zzv2 = this.zzs.zzv();
                        this.zzs.zzf();
                        this.zzs.zzv().j(this.m, null, i, "_ev", zzv2.zzC(str2, 40, true), str2 != null ? str2.length() : 0);
                        return;
                    }
                }
            }
            zzpx.zzc();
            if (this.zzs.zzf().zzs(null, zzdw.zzaA)) {
                this.zzs.zzaw();
                zzic zzj = this.zzs.zzs().zzj(false);
                if (zzj != null && !bundle.containsKey("_sc")) {
                    zzj.f10157a = true;
                }
                zzku.zzJ(zzj, bundle, z && !z3);
            } else {
                this.zzs.zzaw();
                zzic zzj2 = this.zzs.zzs().zzj(false);
                if (zzj2 != null && !bundle.containsKey("_sc")) {
                    zzj2.f10157a = true;
                }
                zzku.zzJ(zzj2, bundle, z && !z3);
            }
            boolean equals = "am".equals(str);
            boolean x = zzku.x(str2);
            if (!z || this.b == null || x) {
                z4 = equals;
            } else if (!equals) {
                this.zzs.zzay().zzc().zzc("Passing event to registered event handler (FE)", this.zzs.zzj().zzc(str2), this.zzs.zzj().zzb(bundle));
                Preconditions.checkNotNull(this.b);
                this.b.interceptEvent(str, str2, bundle, j);
                return;
            } else {
                z4 = true;
            }
            if (this.zzs.zzM()) {
                int zzh = this.zzs.zzv().zzh(str2);
                if (zzh != 0) {
                    this.zzs.zzay().zze().zzb("Invalid event name. Event will not be logged (FE)", this.zzs.zzj().zzc(str2));
                    zzku zzv3 = this.zzs.zzv();
                    this.zzs.zzf();
                    this.zzs.zzv().j(this.m, str3, zzh, "_ev", zzv3.zzC(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
                Bundle R = this.zzs.zzv().R(str3, str2, bundle, CollectionUtils.listOf((Object[]) new String[]{"_o", "_sn", "_sc", "_si"}), z3);
                Preconditions.checkNotNull(R);
                this.zzs.zzaw();
                if (this.zzs.zzs().zzj(false) != null && "_ae".equals(str2)) {
                    l3 l3Var = this.zzs.zzu().zzb;
                    long elapsedRealtime = l3Var.d.zzs.zzav().elapsedRealtime();
                    long j3 = elapsedRealtime - l3Var.b;
                    l3Var.b = elapsedRealtime;
                    if (j3 > 0) {
                        this.zzs.zzv().f(R, j3);
                    }
                }
                zzny.zzc();
                if (this.zzs.zzf().zzs(null, zzdw.zzai)) {
                    if (!"auto".equals(str) && "_ssr".equals(str2)) {
                        zzku zzv4 = this.zzs.zzv();
                        String string = R.getString("_ffr");
                        if (Strings.isEmptyOrWhitespace(string)) {
                            string = null;
                        } else if (string != null) {
                            string = string.trim();
                        }
                        if (!zzku.B(string, zzv4.zzs.zzm().s.zza())) {
                            zzv4.zzs.zzm().s.zzb(string);
                        } else {
                            zzv4.zzs.zzay().zzc().zza("Not logging duplicate session_start_with_rollout event");
                            return;
                        }
                    } else if ("_ae".equals(str2)) {
                        String zza = this.zzs.zzv().zzs.zzm().s.zza();
                        if (!TextUtils.isEmpty(zza)) {
                            R.putString("_ffr", zza);
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(R);
                if (this.zzs.zzm().n.zza() > 0 && this.zzs.zzm().i(j) && this.zzs.zzm().p.zzb()) {
                    this.zzs.zzay().zzj().zza("Current session is expired, remove the session number, ID, and engagement time");
                    str4 = "_ae";
                    j2 = 0;
                    h("auto", "_sid", null, this.zzs.zzav().currentTimeMillis());
                    h("auto", "_sno", null, this.zzs.zzav().currentTimeMillis());
                    h("auto", "_se", null, this.zzs.zzav().currentTimeMillis());
                } else {
                    str4 = "_ae";
                    j2 = 0;
                }
                if (R.getLong(FirebaseAnalytics.Param.EXTEND_SESSION, j2) == 1) {
                    this.zzs.zzay().zzj().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzs.zzu().zza.b(j, true);
                }
                ArrayList arrayList2 = new ArrayList(R.keySet());
                Collections.sort(arrayList2);
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    String str7 = (String) arrayList2.get(i2);
                    if (str7 != null) {
                        this.zzs.zzv();
                        Object obj = R.get(str7);
                        if (obj instanceof Bundle) {
                            bundleArr = new Bundle[]{(Bundle) obj};
                        } else if (obj instanceof Parcelable[]) {
                            Parcelable[] parcelableArr = (Parcelable[]) obj;
                            bundleArr = (Bundle[]) Arrays.copyOf(parcelableArr, parcelableArr.length, Bundle[].class);
                        } else if (obj instanceof ArrayList) {
                            ArrayList arrayList3 = (ArrayList) obj;
                            bundleArr = (Bundle[]) arrayList3.toArray(new Bundle[arrayList3.size()]);
                        } else {
                            bundleArr = null;
                        }
                        if (bundleArr != null) {
                            R.putParcelableArray(str7, bundleArr);
                        }
                    }
                }
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    Bundle bundle2 = (Bundle) arrayList.get(i3);
                    if (i3 != 0) {
                        str6 = "_ep";
                        str5 = str;
                    } else {
                        str5 = str;
                        str6 = str2;
                    }
                    bundle2.putString("_o", str5);
                    if (z2) {
                        bundle2 = this.zzs.zzv().Q(bundle2);
                    }
                    Bundle bundle3 = bundle2;
                    this.zzs.zzt().zzA(new zzat(str6, new zzar(bundle3), str, j), str3);
                    if (!z4) {
                        for (zzgt zzgtVar : this.c) {
                            zzgtVar.onEvent(str, str2, new Bundle(bundle3), j);
                        }
                    }
                }
                this.zzs.zzaw();
                if (this.zzs.zzs().zzj(false) == null || !str4.equals(str2)) {
                    return;
                }
                this.zzs.zzu().zzb.d(true, true, this.zzs.zzav().elapsedRealtime());
                return;
            }
            return;
        }
        this.zzs.zzay().zzc().zza("Event not sent since app measurement is disabled");
    }

    public final void zzI(zzgt zzgtVar) {
        zza();
        Preconditions.checkNotNull(zzgtVar);
        if (this.c.add(zzgtVar)) {
            return;
        }
        this.zzs.zzay().zzk().zza("OnEventListener already registered");
    }

    public final void zzJ(long j) {
        this.e.set(null);
        this.zzs.zzaz().zzp(new g1(this, j));
    }

    public final void zzL(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2 = new Bundle(bundle);
        for (String str4 : bundle2.keySet()) {
            Object obj = bundle2.get(str4);
            if (obj instanceof Bundle) {
                bundle2.putBundle(str4, new Bundle((Bundle) obj));
            } else {
                int i = 0;
                if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    while (i < parcelableArr.length) {
                        Parcelable parcelable = parcelableArr[i];
                        if (parcelable instanceof Bundle) {
                            parcelableArr[i] = new Bundle((Bundle) parcelable);
                        }
                        i++;
                    }
                } else if (obj instanceof List) {
                    List list = (List) obj;
                    while (i < list.size()) {
                        Object obj2 = list.get(i);
                        if (obj2 instanceof Bundle) {
                            list.set(i, new Bundle((Bundle) obj2));
                        }
                        i++;
                    }
                }
            }
        }
        this.zzs.zzaz().zzp(new d1(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    public final void zzO(Bundle bundle) {
        zzP(bundle, this.zzs.zzav().currentTimeMillis());
    }

    public final void zzP(Bundle bundle, long j) {
        Preconditions.checkNotNull(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            this.zzs.zzay().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        Preconditions.checkNotNull(bundle2);
        zzgo.zza(bundle2, "app_id", String.class, null);
        zzgo.zza(bundle2, "origin", String.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.NAME, String.class, null);
        zzgo.zza(bundle2, "value", Object.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, String.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.class, 0L);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_NAME, String.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIMED_OUT_EVENT_PARAMS, Bundle.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_NAME, String.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_EVENT_PARAMS, Bundle.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.class, 0L);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, String.class, null);
        zzgo.zza(bundle2, AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, Bundle.class, null);
        Preconditions.checkNotEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME));
        Preconditions.checkNotEmpty(bundle2.getString("origin"));
        Preconditions.checkNotNull(bundle2.get("value"));
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, j);
        String string = bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME);
        Object obj = bundle2.get("value");
        if (this.zzs.zzv().N(string) == 0) {
            if (this.zzs.zzv().K(string, obj) == 0) {
                Object c = this.zzs.zzv().c(string, obj);
                if (c == null) {
                    this.zzs.zzay().zzd().zzc("Unable to normalize conditional user property value", this.zzs.zzj().zze(string), obj);
                    return;
                }
                zzgo.zzb(bundle2, c);
                long j2 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT);
                if (!TextUtils.isEmpty(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME))) {
                    this.zzs.zzf();
                    if (j2 > 15552000000L || j2 < 1) {
                        this.zzs.zzay().zzd().zzc("Invalid conditional user property timeout", this.zzs.zzj().zze(string), Long.valueOf(j2));
                        return;
                    }
                }
                long j3 = bundle2.getLong(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE);
                this.zzs.zzf();
                if (j3 <= 15552000000L && j3 >= 1) {
                    this.zzs.zzaz().zzp(new h1(this, bundle2));
                    return;
                } else {
                    this.zzs.zzay().zzd().zzc("Invalid conditional user property time to live", this.zzs.zzj().zze(string), Long.valueOf(j3));
                    return;
                }
            }
            this.zzs.zzay().zzd().zzc("Invalid conditional user property value", this.zzs.zzj().zze(string), obj);
            return;
        }
        this.zzs.zzay().zzd().zzb("Invalid conditional user property name", this.zzs.zzj().zze(string));
    }

    public final void zzQ(Bundle bundle, int i, long j) {
        zza();
        String zzh = zzag.zzh(bundle);
        if (zzh != null) {
            this.zzs.zzay().zzl().zzb("Ignoring invalid consent setting", zzh);
            this.zzs.zzay().zzl().zza("Valid consent values are 'granted', 'denied'");
        }
        zzR(zzag.zza(bundle), i, j);
    }

    public final void zzR(zzag zzagVar, int i, long j) {
        boolean z;
        boolean z2;
        zzag zzagVar2;
        boolean z3;
        zza();
        if (i != -10 && zzagVar.zze() == null && zzagVar.zzf() == null) {
            this.zzs.zzay().zzl().zza("Discarding empty consent settings");
            return;
        }
        synchronized (this.f) {
            z = true;
            z2 = false;
            if (zzag.zzl(i, this.h)) {
                boolean zzm = zzagVar.zzm(this.g);
                if (zzagVar.zzk() && !this.g.zzk()) {
                    z2 = true;
                }
                zzag zzd = zzagVar.zzd(this.g);
                this.g = zzd;
                this.h = i;
                zzagVar2 = zzd;
                z3 = z2;
                z2 = zzm;
            } else {
                zzagVar2 = zzagVar;
                z3 = false;
                z = false;
            }
        }
        if (!z) {
            this.zzs.zzay().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzagVar2);
            return;
        }
        long andIncrement = this.i.getAndIncrement();
        if (z2) {
            this.e.set(null);
            this.zzs.zzaz().zzq(new s1(this, zzagVar2, j, i, andIncrement, z3));
        } else if (i != 30 && i != -10) {
            this.zzs.zzaz().zzp(new u1(this, zzagVar2, i, andIncrement, z3));
        } else {
            this.zzs.zzaz().zzq(new t1(this, zzagVar2, i, andIncrement, z3));
        }
    }

    @WorkerThread
    public final void zzS(zzgs zzgsVar) {
        zzgs zzgsVar2;
        zzg();
        zza();
        if (zzgsVar != null && zzgsVar != (zzgsVar2 = this.b)) {
            Preconditions.checkState(zzgsVar2 == null, "EventInterceptor already set.");
        }
        this.b = zzgsVar;
    }

    public final void zzT(Boolean bool) {
        zza();
        this.zzs.zzaz().zzp(new r1(this, bool));
    }

    public final void zzV(String str, String str2, Object obj, boolean z) {
        zzW("auto", "_ldl", obj, true, this.zzs.zzav().currentTimeMillis());
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzW(java.lang.String r16, java.lang.String r17, java.lang.Object r18, boolean r19, long r20) {
        /*
            r15 = this;
            r6 = r15
            r2 = r17
            r0 = r18
            if (r16 != 0) goto La
            java.lang.String r1 = "app"
            goto Lc
        La:
            r1 = r16
        Lc:
            r3 = 6
            r4 = 0
            r5 = 24
            if (r19 == 0) goto L1e
            com.google.android.gms.measurement.internal.zzfs r3 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r3 = r3.zzv()
            int r3 = r3.N(r2)
        L1c:
            r11 = r3
            goto L46
        L1e:
            com.google.android.gms.measurement.internal.zzfs r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r7 = r7.zzv()
            java.lang.String r8 = "user property"
            boolean r9 = r7.s(r8, r2)
            if (r9 != 0) goto L2d
        L2c:
            goto L1c
        L2d:
            java.lang.String[] r9 = com.google.android.gms.measurement.internal.zzgr.zza
            r10 = 0
            boolean r9 = r7.o(r8, r9, r10, r2)
            if (r9 != 0) goto L39
            r3 = 15
            goto L1c
        L39:
            com.google.android.gms.measurement.internal.zzfs r9 = r7.zzs
            r9.zzf()
            boolean r7 = r7.n(r8, r5, r2)
            if (r7 != 0) goto L45
            goto L2c
        L45:
            r11 = r4
        L46:
            r3 = 1
            if (r11 == 0) goto L6e
            com.google.android.gms.measurement.internal.zzfs r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r0 = r0.zzv()
            com.google.android.gms.measurement.internal.zzfs r1 = r6.zzs
            r1.zzf()
            java.lang.String r13 = r0.zzC(r2, r5, r3)
            if (r2 == 0) goto L5e
            int r4 = r17.length()
        L5e:
            r14 = r4
            com.google.android.gms.measurement.internal.zzfs r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r8 = r0.zzv()
            com.google.android.gms.measurement.internal.a4 r9 = r6.m
            r10 = 0
            java.lang.String r12 = "_ev"
            r8.j(r9, r10, r11, r12, r13, r14)
            return
        L6e:
            if (r0 == 0) goto Lc0
            com.google.android.gms.measurement.internal.zzfs r7 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r7 = r7.zzv()
            int r11 = r7.K(r2, r0)
            if (r11 == 0) goto Lab
            com.google.android.gms.measurement.internal.zzfs r1 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzfs r7 = r6.zzs
            r7.zzf()
            java.lang.String r13 = r1.zzC(r2, r5, r3)
            boolean r1 = r0 instanceof java.lang.String
            if (r1 != 0) goto L93
            boolean r1 = r0 instanceof java.lang.CharSequence
            if (r1 == 0) goto L9b
        L93:
            java.lang.String r0 = java.lang.String.valueOf(r18)
            int r4 = r0.length()
        L9b:
            r14 = r4
            com.google.android.gms.measurement.internal.zzfs r0 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r8 = r0.zzv()
            com.google.android.gms.measurement.internal.a4 r9 = r6.m
            r10 = 0
            java.lang.String r12 = "_ev"
            r8.j(r9, r10, r11, r12, r13, r14)
            return
        Lab:
            com.google.android.gms.measurement.internal.zzfs r3 = r6.zzs
            com.google.android.gms.measurement.internal.zzku r3 = r3.zzv()
            java.lang.Object r5 = r3.c(r2, r0)
            if (r5 == 0) goto Lbf
            r0 = r15
            r2 = r17
            r3 = r20
            r0.e(r1, r2, r3, r5)
        Lbf:
            return
        Lc0:
            r5 = 0
            r0 = r15
            r2 = r17
            r3 = r20
            r0.e(r1, r2, r3, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhv.zzW(java.lang.String, java.lang.String, java.lang.Object, boolean, long):void");
    }

    public final void zzY(zzgt zzgtVar) {
        zza();
        Preconditions.checkNotNull(zzgtVar);
        if (this.c.remove(zzgtVar)) {
            return;
        }
        this.zzs.zzay().zzk().zza("OnEventListener had not been registered");
    }

    @Override // com.google.android.gms.measurement.internal.x
    public final boolean zzf() {
        return false;
    }

    public final int zzh(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzs.zzf();
        return 25;
    }

    public final Boolean zzi() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) this.zzs.zzaz().e(atomicReference, 15000L, "boolean test flag value", new j1(this, atomicReference));
    }

    public final Double zzj() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) this.zzs.zzaz().e(atomicReference, 15000L, "double test flag value", new q1(this, atomicReference));
    }

    public final Integer zzl() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) this.zzs.zzaz().e(atomicReference, 15000L, "int test flag value", new p1(this, atomicReference));
    }

    public final Long zzm() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) this.zzs.zzaz().e(atomicReference, 15000L, "long test flag value", new o1(this, atomicReference));
    }

    public final String zzo() {
        return this.e.get();
    }

    public final String zzp() {
        zzic zzi = this.zzs.zzs().zzi();
        if (zzi != null) {
            return zzi.zzb;
        }
        return null;
    }

    public final String zzq() {
        zzic zzi = this.zzs.zzs().zzi();
        if (zzi != null) {
            return zzi.zza;
        }
        return null;
    }

    public final String zzr() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) this.zzs.zzaz().e(atomicReference, 15000L, "String test flag value", new n1(this, atomicReference));
    }

    public final ArrayList<Bundle> zzs(String str, String str2) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        }
        this.zzs.zzaw();
        if (zzaa.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().e(atomicReference, 5000L, "get conditional user properties", new k1(this, atomicReference, null, str, str2));
        List list = (List) atomicReference.get();
        if (list == null) {
            this.zzs.zzay().zzd().zzb("Timed out waiting for get conditional user properties", null);
            return new ArrayList<>();
        }
        return zzku.zzG(list);
    }

    public final List<zzkq> zzt(boolean z) {
        zza();
        this.zzs.zzay().zzj().zza("Getting user properties (FE)");
        if (!this.zzs.zzaz().zzs()) {
            this.zzs.zzaw();
            if (zzaa.zza()) {
                this.zzs.zzay().zzd().zza("Cannot get all user properties from main thread");
                return Collections.emptyList();
            }
            AtomicReference atomicReference = new AtomicReference();
            this.zzs.zzaz().e(atomicReference, 5000L, "get user properties", new f1(this, atomicReference, z));
            List<zzkq> list = (List) atomicReference.get();
            if (list == null) {
                this.zzs.zzay().zzd().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.emptyList();
            }
            return list;
        }
        this.zzs.zzay().zzd().zza("Cannot get all user properties from analytics worker thread");
        return Collections.emptyList();
    }

    public final Map<String, Object> zzu(String str, String str2, boolean z) {
        if (this.zzs.zzaz().zzs()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        this.zzs.zzaw();
        if (zzaa.zza()) {
            this.zzs.zzay().zzd().zza("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        this.zzs.zzaz().e(atomicReference, 5000L, "get user properties", new l1(this, atomicReference, null, str, str2, z));
        List<zzkq> list = (List) atomicReference.get();
        if (list == null) {
            this.zzs.zzay().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.emptyMap();
        }
        ArrayMap arrayMap = new ArrayMap(list.size());
        for (zzkq zzkqVar : list) {
            Object zza = zzkqVar.zza();
            if (zza != null) {
                arrayMap.put(zzkqVar.zzb, zza);
            }
        }
        return arrayMap;
    }

    @WorkerThread
    public final void zzy() {
        zzg();
        zza();
        if (this.zzs.zzM()) {
            if (this.zzs.zzf().zzs(null, zzdw.zzZ)) {
                zzaf zzf = this.zzs.zzf();
                zzf.zzs.zzaw();
                Boolean d = zzf.d("google_analytics_deferred_deep_link_enabled");
                if (d != null && d.booleanValue()) {
                    this.zzs.zzay().zzc().zza("Deferred Deep Link feature enabled.");
                    this.zzs.zzaz().zzp(new Runnable() { // from class: com.google.android.gms.measurement.internal.zzgv
                        @Override // java.lang.Runnable
                        public final void run() {
                            zzhv zzhvVar = zzhv.this;
                            zzhvVar.zzg();
                            if (!zzhvVar.zzs.zzm().q.zzb()) {
                                long zza = zzhvVar.zzs.zzm().r.zza();
                                zzhvVar.zzs.zzm().r.zzb(1 + zza);
                                zzhvVar.zzs.zzf();
                                if (zza >= 5) {
                                    zzhvVar.zzs.zzay().zzk().zza("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
                                    zzhvVar.zzs.zzm().q.zza(true);
                                    return;
                                }
                                zzhvVar.zzs.zzE();
                                return;
                            }
                            zzhvVar.zzs.zzay().zzc().zza("Deferred Deep Link already retrieved. Not fetching again.");
                        }
                    });
                }
            }
            this.zzs.zzt().zzq();
            this.zzc = false;
            v zzm = this.zzs.zzm();
            zzm.zzg();
            String string = zzm.b().getString("previous_os_version", null);
            zzm.zzs.zzg().zzu();
            String str = Build.VERSION.RELEASE;
            if (!TextUtils.isEmpty(str) && !str.equals(string)) {
                SharedPreferences.Editor edit = zzm.b().edit();
                edit.putString("previous_os_version", str);
                edit.apply();
            }
            if (TextUtils.isEmpty(string)) {
                return;
            }
            this.zzs.zzg().zzu();
            if (string.equals(str)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", string);
            b("auto", "_ou", bundle);
        }
    }

    public final void zzz(String str, String str2, Bundle bundle) {
        long currentTimeMillis = this.zzs.zzav().currentTimeMillis();
        Preconditions.checkNotEmpty(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, str);
        bundle2.putLong(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, currentTimeMillis);
        if (str2 != null) {
            bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_NAME, str2);
            bundle2.putBundle(AppMeasurementSdk.ConditionalUserProperty.EXPIRED_EVENT_PARAMS, bundle);
        }
        this.zzs.zzaz().zzp(new i1(this, bundle2));
    }
}
