package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.firebase.messaging.Constants;
/* loaded from: classes10.dex */
public final class zzr {

    /* renamed from: a  reason: collision with root package name */
    public final zzfs f10161a;

    public zzr(zzfs zzfsVar) {
        this.f10161a = zzfsVar;
    }

    @WorkerThread
    public final void a(String str, Bundle bundle) {
        String uri;
        this.f10161a.zzaz().zzg();
        if (this.f10161a.zzJ()) {
            return;
        }
        if (bundle.isEmpty()) {
            uri = null;
        } else {
            if (true == str.isEmpty()) {
                str = "auto";
            }
            Uri.Builder builder = new Uri.Builder();
            builder.path(str);
            for (String str2 : bundle.keySet()) {
                builder.appendQueryParameter(str2, bundle.getString(str2));
            }
            uri = builder.build().toString();
        }
        if (TextUtils.isEmpty(uri)) {
            return;
        }
        this.f10161a.zzm().t.zzb(uri);
        this.f10161a.zzm().u.zzb(this.f10161a.zzav().currentTimeMillis());
    }

    @WorkerThread
    public final void b() {
        this.f10161a.zzaz().zzg();
        if (d()) {
            if (e()) {
                this.f10161a.zzm().t.zzb(null);
                Bundle bundle = new Bundle();
                bundle.putString("source", "(not set)");
                bundle.putString("medium", "(not set)");
                bundle.putString("_cis", "intent");
                bundle.putLong("_cc", 1L);
                this.f10161a.zzq().b("auto", "_cmpx", bundle);
            } else {
                String zza = this.f10161a.zzm().t.zza();
                if (TextUtils.isEmpty(zza)) {
                    this.f10161a.zzay().zzh().zza("Cache still valid but referrer not found");
                } else {
                    long zza2 = ((this.f10161a.zzm().u.zza() / 3600000) - 1) * 3600000;
                    Uri parse = Uri.parse(zza);
                    Bundle bundle2 = new Bundle();
                    Pair pair = new Pair(parse.getPath(), bundle2);
                    for (String str : parse.getQueryParameterNames()) {
                        bundle2.putString(str, parse.getQueryParameter(str));
                    }
                    ((Bundle) pair.second).putLong("_cc", zza2);
                    Object obj = pair.first;
                    this.f10161a.zzq().b(obj == null ? "app" : (String) obj, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, (Bundle) pair.second);
                }
                this.f10161a.zzm().t.zzb(null);
            }
            this.f10161a.zzm().u.zzb(0L);
        }
    }

    public final void c() {
        if (d() && e()) {
            this.f10161a.zzm().t.zzb(null);
        }
    }

    public final boolean d() {
        return this.f10161a.zzm().u.zza() > 0;
    }

    public final boolean e() {
        return d() && this.f10161a.zzav().currentTimeMillis() - this.f10161a.zzm().u.zza() > this.f10161a.zzf().zzi(null, zzdw.zzQ);
    }
}
