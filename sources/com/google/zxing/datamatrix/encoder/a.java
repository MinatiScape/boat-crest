package com.google.zxing.datamatrix.encoder;
/* loaded from: classes11.dex */
public final class a implements f {
    public static char b(char c, char c2) {
        if (HighLevelEncoder.d(c) && HighLevelEncoder.d(c2)) {
            return (char) (((c - '0') * 10) + (c2 - '0') + 130);
        }
        throw new IllegalArgumentException("not digits: " + c + c2);
    }

    @Override // com.google.zxing.datamatrix.encoder.f
    public void a(g gVar) {
        if (HighLevelEncoder.determineConsecutiveDigitCount(gVar.d(), gVar.f) >= 2) {
            gVar.r(b(gVar.d().charAt(gVar.f), gVar.d().charAt(gVar.f + 1)));
            gVar.f += 2;
            return;
        }
        char c = gVar.c();
        int l = HighLevelEncoder.l(gVar.d(), gVar.f, c());
        if (l == c()) {
            if (HighLevelEncoder.e(c)) {
                gVar.r((char) 235);
                gVar.r((char) ((c - 128) + 1));
                gVar.f++;
                return;
            }
            gVar.r((char) (c + 1));
            gVar.f++;
        } else if (l == 1) {
            gVar.r((char) 230);
            gVar.o(1);
        } else if (l == 2) {
            gVar.r((char) 239);
            gVar.o(2);
        } else if (l == 3) {
            gVar.r((char) 238);
            gVar.o(3);
        } else if (l == 4) {
            gVar.r((char) 240);
            gVar.o(4);
        } else if (l == 5) {
            gVar.r((char) 231);
            gVar.o(5);
        } else {
            throw new IllegalStateException("Illegal mode: ".concat(String.valueOf(l)));
        }
    }

    public int c() {
        return 0;
    }
}
