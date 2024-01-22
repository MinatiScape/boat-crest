package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.pqc.crypto.sphincs.e;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class d {
    public static void a(a aVar, byte[] bArr, int i, byte[] bArr2, e.a aVar2) {
        byte[] bArr3 = new byte[40];
        for (int i2 = 0; i2 < 32; i2++) {
            bArr3[i2] = bArr2[i2];
        }
        Pack.longToLittleEndian((aVar2.c << 59) | aVar2.f15320a | (aVar2.b << 4), bArr3, 32);
        aVar.f(bArr, i, bArr3, 40);
    }

    public static void b(byte[] bArr, int i, long j, byte[] bArr2, int i2) {
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(bArr2, i2, 32), new byte[8]));
        chaChaEngine.processBytes(bArr, i, (int) j, bArr, i);
    }
}
