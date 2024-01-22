package com.google.zxing.datamatrix.encoder;
/* loaded from: classes11.dex */
public final class e implements f {
    public static void b(char c, StringBuilder sb) {
        if (c >= ' ' && c <= '?') {
            sb.append(c);
        } else if (c >= '@' && c <= '^') {
            sb.append((char) (c - '@'));
        } else {
            HighLevelEncoder.c(c);
        }
    }

    public static String c(CharSequence charSequence) {
        int length = charSequence.length();
        if (length != 0) {
            int charAt = (charSequence.charAt(0) << 18) + ((length >= 2 ? charSequence.charAt(1) : (char) 0) << '\f') + ((length >= 3 ? charSequence.charAt(2) : (char) 0) << 6) + (length >= 4 ? charSequence.charAt(3) : (char) 0);
            char c = (char) ((charAt >> 8) & 255);
            char c2 = (char) (charAt & 255);
            StringBuilder sb = new StringBuilder(3);
            sb.append((char) ((charAt >> 16) & 255));
            if (length >= 2) {
                sb.append(c);
            }
            if (length >= 3) {
                sb.append(c2);
            }
            return sb.toString();
        }
        throw new IllegalStateException("StringBuilder must not be empty");
    }

    public static void e(g gVar, CharSequence charSequence) {
        try {
            int length = charSequence.length();
            if (length == 0) {
                return;
            }
            boolean z = true;
            if (length == 1) {
                gVar.p();
                int dataCapacity = gVar.g().getDataCapacity() - gVar.a();
                int f = gVar.f();
                if (f > dataCapacity) {
                    gVar.q(gVar.a() + 1);
                    dataCapacity = gVar.g().getDataCapacity() - gVar.a();
                }
                if (f <= dataCapacity && dataCapacity <= 2) {
                    return;
                }
            }
            if (length <= 4) {
                int i = length - 1;
                String c = c(charSequence);
                if (!(!gVar.i()) || i > 2) {
                    z = false;
                }
                if (i <= 2) {
                    gVar.q(gVar.a() + i);
                    if (gVar.g().getDataCapacity() - gVar.a() >= 3) {
                        gVar.q(gVar.a() + c.length());
                        z = false;
                    }
                }
                if (z) {
                    gVar.k();
                    gVar.f -= i;
                } else {
                    gVar.s(c);
                }
                return;
            }
            throw new IllegalStateException("Count must not exceed 4");
        } finally {
            gVar.o(0);
        }
    }

    @Override // com.google.zxing.datamatrix.encoder.f
    public void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!gVar.i()) {
                break;
            }
            b(gVar.c(), sb);
            gVar.f++;
            if (sb.length() >= 4) {
                gVar.s(c(sb));
                sb.delete(0, 4);
                if (HighLevelEncoder.l(gVar.d(), gVar.f, d()) != d()) {
                    gVar.o(0);
                    break;
                }
            }
        }
        sb.append((char) 31);
        e(gVar, sb);
    }

    public int d() {
        return 4;
    }
}
