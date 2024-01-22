package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.gtm.zzav;
import com.google.android.gms.internal.gtm.zzbi;
import com.google.android.gms.internal.gtm.zzbq;
import com.google.android.gms.internal.gtm.zzbt;
import com.google.android.gms.internal.gtm.zzbx;
import com.google.android.gms.internal.gtm.zzcf;
import com.google.android.gms.internal.gtm.zzcx;
import com.google.android.gms.internal.gtm.zzex;
import com.google.android.gms.internal.gtm.zzez;
import com.google.android.gms.internal.gtm.zzfb;
import com.google.android.gms.internal.gtm.zzfs;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public final class i implements Runnable {
    public final /* synthetic */ Map h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ String j;
    public final /* synthetic */ long k;
    public final /* synthetic */ boolean l;
    public final /* synthetic */ boolean m;
    public final /* synthetic */ String n;
    public final /* synthetic */ Tracker o;

    public i(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.o = tracker;
        this.h = map;
        this.i = z;
        this.j = str;
        this.k = j;
        this.l = z2;
        this.m = z3;
        this.n = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        j jVar;
        double d;
        zzbi zzr;
        zzcf zzu;
        zzcx zzx;
        zzcx zzx2;
        zzbq zzs;
        zzbq zzs2;
        zzfb zzz;
        zzez zzezVar;
        zzfb zzz2;
        jVar = this.o.e;
        if (jVar.zzf()) {
            this.h.put(Constants.INAPP_NOTIF_SHOW_CLOSE, "start");
        }
        Map map = this.h;
        GoogleAnalytics zzp = this.o.zzp();
        Preconditions.checkNotMainThread("getClientId can not be called from the main thread");
        String zzb = zzp.a().zzi().zzb();
        if (zzb != null && TextUtils.isEmpty((CharSequence) map.get("cid"))) {
            map.put("cid", zzb);
        }
        String str = (String) this.h.get("sf");
        if (str != null) {
            try {
                d = Double.parseDouble(str);
            } catch (NumberFormatException unused) {
                d = 100.0d;
            }
            if (zzfs.zzj(d, (String) this.h.get("cid"))) {
                this.o.zzG("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(d));
                return;
            }
        }
        zzr = this.o.zzr();
        if (this.i) {
            Map map2 = this.h;
            boolean zzb2 = zzr.zzb();
            if (!map2.containsKey("ate")) {
                map2.put("ate", true != zzb2 ? BleConst.GetDeviceTime : "1");
            }
            zzfs.zzg(this.h, "adid", zzr.zza());
        } else {
            this.h.remove("ate");
            this.h.remove("adid");
        }
        zzu = this.o.zzu();
        zzav zza = zzu.zza();
        zzfs.zzg(this.h, "an", zza.zzf());
        zzfs.zzg(this.h, "av", zza.zzg());
        zzfs.zzg(this.h, "aid", zza.zzd());
        zzfs.zzg(this.h, "aiid", zza.zze());
        this.h.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, "1");
        this.h.put("_v", zzbt.zzb);
        Map map3 = this.h;
        zzx = this.o.zzx();
        zzfs.zzg(map3, "ul", zzx.zza().zzd());
        Map map4 = this.h;
        zzx2 = this.o.zzx();
        zzfs.zzg(map4, "sr", zzx2.zzb());
        if (!this.j.equals("transaction") && !this.j.equals(com.clevertap.android.sdk.leanplum.Constants.IAP_ITEM_PARAM)) {
            zzezVar = this.o.d;
            if (!zzezVar.zza()) {
                zzz2 = this.o.zzz();
                zzz2.zzc(this.h, "Too many hits sent too quickly, rate limiting invoked");
                return;
            }
        }
        long zza2 = zzfs.zza((String) this.h.get("ht"));
        if (zza2 == 0) {
            zza2 = this.k;
        }
        long j = zza2;
        if (this.l) {
            zzex zzexVar = new zzex(this.o, this.h, j, this.m);
            zzz = this.o.zzz();
            zzz.zzN("Dry run enabled. Would have sent hit", zzexVar);
            return;
        }
        String str2 = (String) this.h.get("cid");
        HashMap hashMap = new HashMap();
        zzfs.zzh(hashMap, "uid", this.h);
        zzfs.zzh(hashMap, "an", this.h);
        zzfs.zzh(hashMap, "aid", this.h);
        zzfs.zzh(hashMap, "av", this.h);
        zzfs.zzh(hashMap, "aiid", this.h);
        Preconditions.checkNotNull(str2);
        zzbx zzbxVar = new zzbx(0L, str2, this.n, !TextUtils.isEmpty((CharSequence) this.h.get("adid")), 0L, hashMap);
        zzs = this.o.zzs();
        this.h.put("_s", String.valueOf(zzs.zza(zzbxVar)));
        zzex zzexVar2 = new zzex(this.o, this.h, j, this.m);
        zzs2 = this.o.zzs();
        zzs2.zzh(zzexVar2);
    }
}
