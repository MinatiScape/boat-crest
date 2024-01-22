package com.google.zxing.datamatrix.encoder;

import androidx.core.view.InputDeviceCompat;
/* loaded from: classes11.dex */
public final class b implements f {
    public static char c(char c, int i) {
        int i2 = c + ((i * 149) % 255) + 1;
        return i2 <= 255 ? (char) i2 : (char) (i2 + InputDeviceCompat.SOURCE_ANY);
    }

    @Override // com.google.zxing.datamatrix.encoder.f
    public void a(g gVar) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) 0);
        while (true) {
            if (!gVar.i()) {
                break;
            }
            sb.append(gVar.c());
            gVar.f++;
            if (HighLevelEncoder.l(gVar.d(), gVar.f, b()) != b()) {
                gVar.o(0);
                break;
            }
        }
        int length = sb.length() - 1;
        int a2 = gVar.a() + length + 1;
        gVar.q(a2);
        boolean z = gVar.g().getDataCapacity() - a2 > 0;
        if (gVar.i() || z) {
            if (length <= 249) {
                sb.setCharAt(0, (char) length);
            } else if (length <= 1555) {
                sb.setCharAt(0, (char) ((length / 250) + 249));
                sb.insert(1, (char) (length % 250));
            } else {
                throw new IllegalStateException("Message length not in valid ranges: ".concat(String.valueOf(length)));
            }
        }
        int length2 = sb.length();
        for (int i = 0; i < length2; i++) {
            gVar.r(c(sb.charAt(i), gVar.a() + 1));
        }
    }

    public int b() {
        return 5;
    }
}
