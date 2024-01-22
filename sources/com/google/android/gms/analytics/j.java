package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.gtm.zzbs;
import com.google.android.gms.internal.gtm.zzbv;
import com.google.android.gms.internal.gtm.zzfr;
import java.util.HashMap;
/* loaded from: classes6.dex */
public final class j extends zzbs {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8183a;
    public int b;
    public long c;
    public boolean d;
    public long e;
    public final /* synthetic */ Tracker f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Tracker tracker, zzbv zzbvVar) {
        super(zzbvVar);
        this.f = tracker;
        this.c = -1L;
    }

    public final void a(Activity activity) {
        zzfr zzfrVar;
        String canonicalName;
        zzfr zzfrVar2;
        if (this.b == 0 && zzC().elapsedRealtime() >= this.e + Math.max(1000L, this.c)) {
            this.d = true;
        }
        this.b++;
        if (this.f8183a) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                this.f.setCampaignParamsOnNextHit(intent.getData());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("&t", "screenview");
            Tracker tracker = this.f;
            zzfrVar = tracker.g;
            if (zzfrVar != null) {
                zzfrVar2 = this.f.g;
                canonicalName = activity.getClass().getCanonicalName();
                String str = zzfrVar2.zzg.get(canonicalName);
                if (str != null) {
                    canonicalName = str;
                }
            } else {
                canonicalName = activity.getClass().getCanonicalName();
            }
            tracker.set("&cd", canonicalName);
            if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                Preconditions.checkNotNull(activity);
                Intent intent2 = activity.getIntent();
                String str2 = null;
                if (intent2 != null) {
                    String stringExtra = intent2.getStringExtra("android.intent.extra.REFERRER_NAME");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        str2 = stringExtra;
                    }
                }
                if (!TextUtils.isEmpty(str2)) {
                    hashMap.put("&dr", str2);
                }
            }
            this.f.send(hashMap);
        }
    }

    public final void b(Activity activity) {
        int i = this.b - 1;
        this.b = i;
        int max = Math.max(0, i);
        this.b = max;
        if (max == 0) {
            this.e = zzC().elapsedRealtime();
        }
    }

    public final void c(boolean z) {
        this.f8183a = z;
        zzg();
    }

    public final void d(long j) {
        this.c = j;
        zzg();
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
    }

    public final synchronized boolean zzf() {
        boolean z;
        z = this.d;
        this.d = false;
        return z;
    }

    public final void zzg() {
        j jVar;
        j jVar2;
        if (this.c < 0 && !this.f8183a) {
            GoogleAnalytics zzp = zzp();
            jVar2 = this.f.e;
            zzp.e(jVar2);
            return;
        }
        GoogleAnalytics zzp2 = zzp();
        jVar = this.f.e;
        zzp2.d(jVar);
    }
}
