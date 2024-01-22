package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.CamelliaEngine;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.engines.RC4Engine;
import org.bouncycastle.crypto.engines.SEEDEngine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.modes.CCMBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;
import org.bouncycastle.crypto.modes.OCBBlockCipher;
/* loaded from: classes13.dex */
public class DefaultTlsCipherFactory extends AbstractTlsCipherFactory {
    public AEADBlockCipher createAEADBlockCipher_AES_CCM() {
        return new CCMBlockCipher(createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_AES_GCM() {
        return new GCMBlockCipher(createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_AES_OCB() {
        return new OCBBlockCipher(createAESEngine(), createAESEngine());
    }

    public AEADBlockCipher createAEADBlockCipher_Camellia_GCM() {
        return new GCMBlockCipher(createCamelliaEngine());
    }

    public BlockCipher createAESBlockCipher() {
        return new CBCBlockCipher(createAESEngine());
    }

    public TlsBlockCipher createAESCipher(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsBlockCipher(tlsContext, createAESBlockCipher(), createAESBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), i);
    }

    public BlockCipher createAESEngine() {
        return new AESEngine();
    }

    public BlockCipher createCamelliaBlockCipher() {
        return new CBCBlockCipher(createCamelliaEngine());
    }

    public TlsBlockCipher createCamelliaCipher(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsBlockCipher(tlsContext, createCamelliaBlockCipher(), createCamelliaBlockCipher(), createHMACDigest(i2), createHMACDigest(i2), i);
    }

    public BlockCipher createCamelliaEngine() {
        return new CamelliaEngine();
    }

    public TlsCipher createChaCha20Poly1305(TlsContext tlsContext) throws IOException {
        return new Chacha20Poly1305(tlsContext);
    }

    @Override // org.bouncycastle.crypto.tls.AbstractTlsCipherFactory, org.bouncycastle.crypto.tls.TlsCipherFactory
    public TlsCipher createCipher(TlsContext tlsContext, int i, int i2) throws IOException {
        if (i != 0) {
            if (i != 2) {
                if (i != 103) {
                    if (i != 104) {
                        switch (i) {
                            case 7:
                                return createDESedeCipher(tlsContext, i2);
                            case 8:
                                return createAESCipher(tlsContext, 16, i2);
                            case 9:
                                return createAESCipher(tlsContext, 32, i2);
                            case 10:
                                return createCipher_AES_GCM(tlsContext, 16, 16);
                            case 11:
                                return createCipher_AES_GCM(tlsContext, 32, 16);
                            case 12:
                                return createCamelliaCipher(tlsContext, 16, i2);
                            case 13:
                                return createCamelliaCipher(tlsContext, 32, i2);
                            case 14:
                                return createSEEDCipher(tlsContext, i2);
                            case 15:
                                return createCipher_AES_CCM(tlsContext, 16, 16);
                            case 16:
                                return createCipher_AES_CCM(tlsContext, 16, 8);
                            case 17:
                                return createCipher_AES_CCM(tlsContext, 32, 16);
                            case 18:
                                return createCipher_AES_CCM(tlsContext, 32, 8);
                            case 19:
                                return createCipher_Camellia_GCM(tlsContext, 16, 16);
                            case 20:
                                return createCipher_Camellia_GCM(tlsContext, 32, 16);
                            case 21:
                                return createChaCha20Poly1305(tlsContext);
                            default:
                                throw new TlsFatalAlert((short) 80);
                        }
                    }
                    return createCipher_AES_OCB(tlsContext, 32, 12);
                }
                return createCipher_AES_OCB(tlsContext, 16, 12);
            }
            return createRC4Cipher(tlsContext, 16, i2);
        }
        return createNullCipher(tlsContext, i2);
    }

    public TlsAEADCipher createCipher_AES_CCM(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_CCM(), createAEADBlockCipher_AES_CCM(), i, i2);
    }

    public TlsAEADCipher createCipher_AES_GCM(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_GCM(), createAEADBlockCipher_AES_GCM(), i, i2);
    }

    public TlsAEADCipher createCipher_AES_OCB(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_AES_OCB(), createAEADBlockCipher_AES_OCB(), i, i2, 2);
    }

    public TlsAEADCipher createCipher_Camellia_GCM(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsAEADCipher(tlsContext, createAEADBlockCipher_Camellia_GCM(), createAEADBlockCipher_Camellia_GCM(), i, i2);
    }

    public BlockCipher createDESedeBlockCipher() {
        return new CBCBlockCipher(new DESedeEngine());
    }

    public TlsBlockCipher createDESedeCipher(TlsContext tlsContext, int i) throws IOException {
        return new TlsBlockCipher(tlsContext, createDESedeBlockCipher(), createDESedeBlockCipher(), createHMACDigest(i), createHMACDigest(i), 24);
    }

    public Digest createHMACDigest(int i) throws IOException {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                return TlsUtils.createHash((short) 6);
                            }
                            throw new TlsFatalAlert((short) 80);
                        }
                        return TlsUtils.createHash((short) 5);
                    }
                    return TlsUtils.createHash((short) 4);
                }
                return TlsUtils.createHash((short) 2);
            }
            return TlsUtils.createHash((short) 1);
        }
        return null;
    }

    public TlsNullCipher createNullCipher(TlsContext tlsContext, int i) throws IOException {
        return new TlsNullCipher(tlsContext, createHMACDigest(i), createHMACDigest(i));
    }

    public TlsStreamCipher createRC4Cipher(TlsContext tlsContext, int i, int i2) throws IOException {
        return new TlsStreamCipher(tlsContext, createRC4StreamCipher(), createRC4StreamCipher(), createHMACDigest(i2), createHMACDigest(i2), i, false);
    }

    public StreamCipher createRC4StreamCipher() {
        return new RC4Engine();
    }

    public BlockCipher createSEEDBlockCipher() {
        return new CBCBlockCipher(new SEEDEngine());
    }

    public TlsBlockCipher createSEEDCipher(TlsContext tlsContext, int i) throws IOException {
        return new TlsBlockCipher(tlsContext, createSEEDBlockCipher(), createSEEDBlockCipher(), createHMACDigest(i), createHMACDigest(i), 16);
    }
}
