package com.github.siyamed.shapeimageview.path.parser;
/* loaded from: classes9.dex */
public class e {
    public static final double[] e = new double[128];

    /* renamed from: a  reason: collision with root package name */
    public char f7974a;
    public final CharSequence b;
    public int c = 0;
    public final int d;

    static {
        int i = 0;
        while (true) {
            double[] dArr = e;
            if (i >= dArr.length) {
                return;
            }
            dArr[i] = Math.pow(10.0d, i);
            i++;
        }
    }

    public e(CharSequence charSequence) {
        this.b = charSequence;
        this.d = charSequence.length();
        this.f7974a = charSequence.charAt(this.c);
    }

    public static float b(int i, int i2) {
        if (i2 < -125 || i == 0) {
            return 0.0f;
        }
        if (i2 >= 128) {
            return i > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        } else if (i2 == 0) {
            return i;
        } else {
            if (i >= 67108864) {
                i++;
            }
            double d = i;
            double[] dArr = e;
            return (float) (i2 > 0 ? d * dArr[i2] : d / dArr[-i2]);
        }
    }

    public void a() {
        this.f7974a = e();
    }

    public float c() {
        h();
        float d = d();
        g();
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0028 A[LOOP:0: B:13:0x0028->B:17:0x0034, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x009a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c2 A[LOOP:3: B:62:0x00c2->B:63:0x00c8, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public float d() {
        /*
            Method dump skipped, instructions count: 508
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.siyamed.shapeimageview.path.parser.e.d():float");
    }

    public final char e() {
        int i = this.c;
        int i2 = this.d;
        if (i < i2) {
            this.c = i + 1;
        }
        int i3 = this.c;
        if (i3 == i2) {
            return (char) 0;
        }
        return this.b.charAt(i3);
    }

    public final void f(char c) {
        throw new RuntimeException("Unexpected char '" + c + "'.");
    }

    public void g() {
        while (true) {
            int i = this.c;
            if (i >= this.d) {
                return;
            }
            char charAt = this.b.charAt(i);
            if (charAt != '\t' && charAt != '\n' && charAt != ' ' && charAt != ',') {
                return;
            }
            a();
        }
    }

    public void h() {
        while (true) {
            int i = this.c;
            if (i >= this.d || !Character.isWhitespace(this.b.charAt(i))) {
                return;
            }
            a();
        }
    }
}
