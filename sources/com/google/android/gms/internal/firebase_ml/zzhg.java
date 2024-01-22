package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public class zzhg extends IOException {
    private final String content;
    private final int statusCode;
    private final transient zzgx zzack;
    private final String zznl;

    public zzhg(zzhd zzhdVar) {
        this(new zzhf(zzhdVar));
    }

    public static StringBuilder zzc(zzhd zzhdVar) {
        StringBuilder sb = new StringBuilder();
        int statusCode = zzhdVar.getStatusCode();
        if (statusCode != 0) {
            sb.append(statusCode);
        }
        String statusMessage = zzhdVar.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                sb.append(' ');
            }
            sb.append(statusMessage);
        }
        return sb;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public zzhg(zzhf zzhfVar) {
        super(zzhfVar.e);
        this.statusCode = zzhfVar.f8769a;
        this.zznl = zzhfVar.b;
        this.zzack = zzhfVar.c;
        this.content = zzhfVar.d;
    }
}
