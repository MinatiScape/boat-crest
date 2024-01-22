package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes7.dex */
public final class a3 implements Cloneable {
    public zzfv<?, ?> h;
    public Object i;
    public List<Object> j = new ArrayList();

    public final byte[] a() throws IOException {
        byte[] bArr = new byte[c()];
        b(zzfs.zzg(bArr));
        return bArr;
    }

    public final void b(zzfs zzfsVar) throws IOException {
        if (this.i != null) {
            throw new NoSuchMethodError();
        }
        Iterator<Object> it = this.j.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
    }

    public final int c() {
        if (this.i == null) {
            Iterator<Object> it = this.j.iterator();
            if (it.hasNext()) {
                it.next();
                throw new NoSuchMethodError();
            }
            return 0;
        }
        throw new NoSuchMethodError();
    }

    /* renamed from: d */
    public final a3 clone() {
        Object clone;
        a3 a3Var = new a3();
        try {
            a3Var.h = this.h;
            List<Object> list = this.j;
            if (list == null) {
                a3Var.j = null;
            } else {
                a3Var.j.addAll(list);
            }
            Object obj = this.i;
            if (obj != null) {
                if (obj instanceof zzfz) {
                    clone = (zzfz) ((zzfz) obj).clone();
                } else if (obj instanceof byte[]) {
                    clone = ((byte[]) obj).clone();
                } else {
                    int i = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length];
                        a3Var.i = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (obj instanceof boolean[]) {
                        clone = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        clone = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        clone = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        clone = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        clone = ((double[]) obj).clone();
                    } else if (obj instanceof zzfz[]) {
                        zzfz[] zzfzVarArr = (zzfz[]) obj;
                        zzfz[] zzfzVarArr2 = new zzfz[zzfzVarArr.length];
                        a3Var.i = zzfzVarArr2;
                        while (i < zzfzVarArr.length) {
                            zzfzVarArr2[i] = (zzfz) zzfzVarArr[i].clone();
                            i++;
                        }
                    }
                }
                a3Var.i = clone;
            }
            return a3Var;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<Object> list;
        if (obj == this) {
            return true;
        }
        if (obj instanceof a3) {
            a3 a3Var = (a3) obj;
            if (this.i == null || a3Var.i == null) {
                List<Object> list2 = this.j;
                if (list2 == null || (list = a3Var.j) == null) {
                    try {
                        return Arrays.equals(a(), a3Var.a());
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                }
                return list2.equals(list);
            }
            zzfv<?, ?> zzfvVar = this.h;
            if (zzfvVar != a3Var.h) {
                return false;
            }
            if (zzfvVar.zzrk.isArray()) {
                Object obj2 = this.i;
                return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) a3Var.i) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) a3Var.i) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) a3Var.i) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) a3Var.i) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) a3Var.i) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) a3Var.i) : Arrays.deepEquals((Object[]) obj2, (Object[]) a3Var.i);
            }
            return this.i.equals(a3Var.i);
        }
        return false;
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(a()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
