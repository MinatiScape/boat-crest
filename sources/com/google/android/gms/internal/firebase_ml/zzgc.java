package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class zzgc {
    public static final String VERSION;
    public static final Integer zzaaj = 1;
    public static final Integer zzaak = 26;

    /* renamed from: a  reason: collision with root package name */
    public static final Integer f8759a = 0;

    static {
        String valueOf = String.valueOf((Object) 1);
        String valueOf2 = String.valueOf((Object) 26);
        String valueOf3 = String.valueOf((Object) 0);
        StringBuilder sb = new StringBuilder(valueOf.length() + 11 + valueOf2.length() + valueOf3.length());
        sb.append(valueOf);
        sb.append(".");
        sb.append(valueOf2);
        sb.append(".");
        sb.append(valueOf3);
        sb.append("-SNAPSHOT");
        VERSION = sb.toString().toString();
    }
}
