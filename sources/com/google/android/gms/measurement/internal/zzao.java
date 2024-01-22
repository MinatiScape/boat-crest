package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
/* loaded from: classes10.dex */
public final class zzao {

    /* renamed from: a  reason: collision with root package name */
    public final String f10142a;
    public final String b;
    public final String c;
    public final long d;
    public final long e;
    public final zzar f;

    public zzao(zzfs zzfsVar, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzar zzarVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.f10142a = str2;
        this.b = str3;
        this.c = true == TextUtils.isEmpty(str) ? null : str;
        this.d = j;
        this.e = j2;
        if (j2 != 0 && j2 > j) {
            zzfsVar.zzay().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzei.zzn(str2));
        }
        if (bundle != null && !bundle.isEmpty()) {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzfsVar.zzay().zzd().zza("Param name can't be null");
                    it.remove();
                } else {
                    Object b = zzfsVar.zzv().b(next, bundle2.get(next));
                    if (b == null) {
                        zzfsVar.zzay().zzk().zzb("Param value can't be null", zzfsVar.zzj().zzd(next));
                        it.remove();
                    } else {
                        zzfsVar.zzv().k(bundle2, next, b);
                    }
                }
            }
            zzarVar = new zzar(bundle2);
        } else {
            zzarVar = new zzar(new Bundle());
        }
        this.f = zzarVar;
    }

    public final zzao a(zzfs zzfsVar, long j) {
        return new zzao(zzfsVar, this.c, this.f10142a, this.b, this.d, j, this.f);
    }

    public final String toString() {
        String str = this.f10142a;
        String str2 = this.b;
        String valueOf = String.valueOf(this.f);
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 33 + String.valueOf(str2).length() + valueOf.length());
        sb.append("Event{appId='");
        sb.append(str);
        sb.append("', name='");
        sb.append(str2);
        sb.append("', params=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    public zzao(zzfs zzfsVar, String str, String str2, String str3, long j, long j2, zzar zzarVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzarVar);
        this.f10142a = str2;
        this.b = str3;
        this.c = true == TextUtils.isEmpty(str) ? null : str;
        this.d = j;
        this.e = j2;
        if (j2 != 0 && j2 > j) {
            zzfsVar.zzay().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzei.zzn(str2), zzei.zzn(str3));
        }
        this.f = zzarVar;
    }
}
