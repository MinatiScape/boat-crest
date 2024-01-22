package org.bouncycastle.crypto.tls;
/* loaded from: classes13.dex */
public interface TlsPSKIdentityManager {
    byte[] getHint();

    byte[] getPSK(byte[] bArr);
}
