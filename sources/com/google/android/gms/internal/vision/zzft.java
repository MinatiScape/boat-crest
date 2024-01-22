package com.google.android.gms.internal.vision;

import java.io.IOException;
/* loaded from: classes10.dex */
public abstract class zzft {

    /* renamed from: a  reason: collision with root package name */
    public int f10018a;
    public int b;
    public x1 c;

    public zzft() {
        this.b = 100;
    }

    public static zzft a(byte[] bArr, int i, int i2, boolean z) {
        v1 v1Var = new v1(bArr, 0, i2, false);
        try {
            v1Var.zzat(i2);
            return v1Var;
        } catch (zzhc e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzav(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzr(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract long b() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract void zzar(int i) throws zzhc;

    public abstract boolean zzas(int i) throws IOException;

    public abstract int zzat(int i) throws zzhc;

    public abstract void zzau(int i);

    public abstract boolean zzdt() throws IOException;

    public abstract long zzdw() throws IOException;

    public abstract long zzdx() throws IOException;

    public abstract int zzdy() throws IOException;

    public abstract long zzdz() throws IOException;

    public abstract int zzea() throws IOException;

    public abstract boolean zzeb() throws IOException;

    public abstract String zzec() throws IOException;

    public abstract zzfh zzed() throws IOException;

    public abstract int zzee() throws IOException;

    public abstract int zzef() throws IOException;

    public abstract int zzeg() throws IOException;

    public abstract long zzeh() throws IOException;

    public abstract int zzei() throws IOException;

    public abstract long zzej() throws IOException;

    public abstract int zzex() throws IOException;

    public abstract int zzez();
}
