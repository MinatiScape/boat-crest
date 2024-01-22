package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public abstract class zzhk {

    /* renamed from: a  reason: collision with root package name */
    public long f8771a = -1;
    public String b;
    public String c;
    public zzjq d;

    public abstract void addHeader(String str, String str2) throws IOException;

    public final String getContentEncoding() {
        return this.b;
    }

    public final long getContentLength() {
        return this.f8771a;
    }

    public final String getContentType() {
        return this.c;
    }

    public final void setContentEncoding(String str) throws IOException {
        this.b = str;
    }

    public final void setContentLength(long j) throws IOException {
        this.f8771a = j;
    }

    public final void setContentType(String str) throws IOException {
        this.c = str;
    }

    public void zza(int i, int i2) throws IOException {
    }

    public final void zza(zzjq zzjqVar) throws IOException {
        this.d = zzjqVar;
    }

    public final zzjq zzgl() {
        return this.d;
    }

    public abstract zzhj zzgm() throws IOException;
}
