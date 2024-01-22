package com.google.zxing.datamatrix.encoder;
/* loaded from: classes11.dex */
public final class i extends c {
    @Override // com.google.zxing.datamatrix.encoder.c, com.google.zxing.datamatrix.encoder.f
    public void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (!gVar.i()) {
                break;
            }
            char c = gVar.c();
            gVar.f++;
            c(c, sb);
            if (sb.length() % 3 == 0) {
                c.g(gVar, sb);
                if (HighLevelEncoder.l(gVar.d(), gVar.f, e()) != e()) {
                    gVar.o(0);
                    break;
                }
            }
        }
        f(gVar, sb);
    }

    @Override // com.google.zxing.datamatrix.encoder.c
    public int c(char c, StringBuilder sb) {
        if (c == '\r') {
            sb.append((char) 0);
        } else if (c == ' ') {
            sb.append((char) 3);
        } else if (c == '*') {
            sb.append((char) 1);
        } else if (c == '>') {
            sb.append((char) 2);
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) ((c - 'A') + 14));
        } else {
            HighLevelEncoder.c(c);
        }
        return 1;
    }

    @Override // com.google.zxing.datamatrix.encoder.c
    public int e() {
        return 3;
    }

    @Override // com.google.zxing.datamatrix.encoder.c
    public void f(g gVar, StringBuilder sb) {
        gVar.p();
        int dataCapacity = gVar.g().getDataCapacity() - gVar.a();
        gVar.f -= sb.length();
        if (gVar.f() > 1 || dataCapacity > 1 || gVar.f() != dataCapacity) {
            gVar.r((char) 254);
        }
        if (gVar.e() < 0) {
            gVar.o(0);
        }
    }
}
