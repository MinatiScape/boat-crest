package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.firebase.messaging.Constants;
/* loaded from: classes10.dex */
public final class w implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzbr h;
    public final /* synthetic */ ServiceConnection i;
    public final /* synthetic */ zzez j;

    public w(zzez zzezVar, com.google.android.gms.internal.measurement.zzbr zzbrVar, ServiceConnection serviceConnection) {
        this.j = zzezVar;
        this.h = zzbrVar;
        this.i = serviceConnection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        zzez zzezVar = this.j;
        zzfa zzfaVar = zzezVar.i;
        str = zzezVar.h;
        com.google.android.gms.internal.measurement.zzbr zzbrVar = this.h;
        ServiceConnection serviceConnection = this.i;
        zzfaVar.f10152a.zzaz().zzg();
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        Bundle bundle2 = null;
        try {
            Bundle zzd = zzbrVar.zzd(bundle);
            if (zzd == null) {
                zzfaVar.f10152a.zzay().zzd().zza("Install Referrer Service returned a null response");
            } else {
                bundle2 = zzd;
            }
        } catch (Exception e) {
            zzfaVar.f10152a.zzay().zzd().zzb("Exception occurred while retrieving the Install Referrer", e.getMessage());
        }
        zzfaVar.f10152a.zzaz().zzg();
        zzfs.e();
        if (bundle2 != null) {
            long j = bundle2.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j == 0) {
                zzfaVar.f10152a.zzay().zzk().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = bundle2.getString("install_referrer");
                if (string != null && !string.isEmpty()) {
                    zzfaVar.f10152a.zzay().zzj().zzb("InstallReferrer API result", string);
                    Bundle P = zzfaVar.f10152a.zzv().P(Uri.parse(string.length() != 0 ? "?".concat(string) : new String("?")));
                    if (P == null) {
                        zzfaVar.f10152a.zzay().zzd().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = P.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = bundle2.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                            if (j2 == 0) {
                                zzfaVar.f10152a.zzay().zzd().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                P.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzfaVar.f10152a.zzm().e.zza()) {
                            zzfaVar.f10152a.zzay().zzj().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzfaVar.f10152a.zzJ()) {
                            zzfaVar.f10152a.zzm().e.zzb(j);
                            zzfaVar.f10152a.zzay().zzj().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            P.putString("_cis", "referrer API v2");
                            zzfaVar.f10152a.zzq().zzE("auto", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, P, str);
                        }
                    }
                } else {
                    zzfaVar.f10152a.zzay().zzd().zza("No referrer defined in Install Referrer response");
                }
            }
        }
        ConnectionTracker.getInstance().unbindService(zzfaVar.f10152a.zzau(), serviceConnection);
    }
}
