package com.google.zxing.datamatrix.encoder;

import com.goodix.ble.libcomx.util.HexStringBuilder;
/* loaded from: classes11.dex */
public class c implements f {
    public static String d(CharSequence charSequence) {
        int charAt = (charSequence.charAt(0) * 1600) + (charSequence.charAt(1) * HexStringBuilder.COMMENT_BEGIN_CHAR) + charSequence.charAt(2) + 1;
        return new String(new char[]{(char) (charAt / 256), (char) (charAt % 256)});
    }

    public static void g(g gVar, StringBuilder sb) {
        gVar.s(d(sb));
        sb.delete(0, 3);
    }

    @Override // com.google.zxing.datamatrix.encoder.f
    public void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!gVar.i()) {
                break;
            }
            char c = gVar.c();
            gVar.f++;
            int c2 = c(c, sb);
            int a2 = gVar.a() + ((sb.length() / 3) << 1);
            gVar.q(a2);
            int dataCapacity = gVar.g().getDataCapacity() - a2;
            if (!gVar.i()) {
                StringBuilder sb2 = new StringBuilder();
                if (sb.length() % 3 == 2 && dataCapacity != 2) {
                    c2 = b(gVar, sb, sb2, c2);
                }
                while (sb.length() % 3 == 1 && (c2 > 3 || dataCapacity != 1)) {
                    c2 = b(gVar, sb, sb2, c2);
                }
            } else if (sb.length() % 3 == 0 && HighLevelEncoder.l(gVar.d(), gVar.f, e()) != e()) {
                gVar.o(0);
                break;
            }
        }
        f(gVar, sb);
    }

    public final int b(g gVar, StringBuilder sb, StringBuilder sb2, int i) {
        int length = sb.length();
        sb.delete(length - i, length);
        gVar.f--;
        int c = c(gVar.c(), sb2);
        gVar.k();
        return c;
    }

    public int c(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
            return 1;
        } else if (c < ' ') {
            sb.append((char) 0);
            sb.append(c);
            return 2;
        } else if (c <= '/') {
            sb.append((char) 1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c <= '@') {
            sb.append((char) 1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        } else if (c <= '_') {
            sb.append((char) 1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        } else if (c <= 127) {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        } else {
            sb.append("\u0001\u001e");
            return c((char) (c - 128), sb) + 2;
        }
    }

    public int e() {
        return 1;
    }

    public void f(g gVar, StringBuilder sb) {
        int length = sb.length() % 3;
        int a2 = gVar.a() + ((sb.length() / 3) << 1);
        gVar.q(a2);
        int dataCapacity = gVar.g().getDataCapacity() - a2;
        if (length == 2) {
            sb.append((char) 0);
            while (sb.length() >= 3) {
                g(gVar, sb);
            }
            if (gVar.i()) {
                gVar.r((char) 254);
            }
        } else if (dataCapacity == 1 && length == 1) {
            while (sb.length() >= 3) {
                g(gVar, sb);
            }
            if (gVar.i()) {
                gVar.r((char) 254);
            }
            gVar.f--;
        } else if (length == 0) {
            while (sb.length() >= 3) {
                g(gVar, sb);
            }
            if (dataCapacity > 0 || gVar.i()) {
                gVar.r((char) 254);
            }
        } else {
            throw new IllegalStateException("Unexpected case. Please report!");
        }
        gVar.o(0);
    }
}
