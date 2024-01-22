package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.util.Arrays;
/* loaded from: classes7.dex */
public final class p1 {
    public int A;
    public int B;
    public Field C;
    public Object D;
    public Object E;
    public Object F;

    /* renamed from: a  reason: collision with root package name */
    public final q1 f8594a;
    public final Object[] b;
    public Class<?> c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int h;
    public final int i;
    public final int j;
    public final int k;
    public final int l;
    public final int m;
    public final int[] n;
    public int o;
    public int p;
    public int q = Integer.MAX_VALUE;
    public int r = Integer.MIN_VALUE;
    public int s = 0;
    public int t = 0;
    public int u = 0;
    public int v = 0;
    public int w = 0;
    public int x;
    public int y;
    public int z;

    public p1(Class<?> cls, String str, Object[] objArr) {
        this.c = cls;
        q1 q1Var = new q1(str);
        this.f8594a = q1Var;
        this.b = objArr;
        this.d = q1Var.b();
        int b = q1Var.b();
        this.e = b;
        if (b == 0) {
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.l = 0;
            this.k = 0;
            this.m = 0;
            this.n = null;
            return;
        }
        int b2 = q1Var.b();
        this.f = b2;
        int b3 = q1Var.b();
        this.g = b3;
        this.h = q1Var.b();
        this.i = q1Var.b();
        this.l = q1Var.b();
        this.k = q1Var.b();
        this.j = q1Var.b();
        this.m = q1Var.b();
        int b4 = q1Var.b();
        this.n = b4 != 0 ? new int[b4] : null;
        this.o = (b2 << 1) + b3;
    }

    public static Field c(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + name.length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c6, code lost:
        if (i() != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c8, code lost:
        r6.E = f();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0152, code lost:
        if (((r6.y & 2048) != 0) != false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0159, code lost:
        if (i() != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a() {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.clearcut.p1.a():boolean");
    }

    public final Object f() {
        Object[] objArr = this.b;
        int i = this.o;
        this.o = i + 1;
        return objArr[i];
    }

    public final int g() {
        return this.x;
    }

    public final int h() {
        return this.z;
    }

    public final boolean i() {
        return (this.d & 1) == 1;
    }

    public final boolean k() {
        return this.z > zzcb.zziw.id();
    }

    public final Field l() {
        int i = this.A << 1;
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field c = c(this.c, (String) obj);
        this.b[i] = c;
        return c;
    }

    public final Field m() {
        int i = (this.A << 1) + 1;
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field c = c(this.c, (String) obj);
        this.b[i] = c;
        return c;
    }

    public final Field n() {
        return this.C;
    }

    public final boolean o() {
        return i() && this.z <= zzcb.zzhp.id();
    }

    public final Field p() {
        int i = (this.f << 1) + (this.B / 32);
        Object obj = this.b[i];
        if (obj instanceof Field) {
            return (Field) obj;
        }
        Field c = c(this.c, (String) obj);
        this.b[i] = c;
        return c;
    }

    public final int q() {
        return this.B % 32;
    }

    public final boolean r() {
        return (this.y & 256) != 0;
    }

    public final boolean s() {
        return (this.y & 512) != 0;
    }

    public final Object t() {
        return this.D;
    }

    public final Object u() {
        return this.E;
    }

    public final Object v() {
        return this.F;
    }
}
