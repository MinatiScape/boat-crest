package org.bouncycastle.pqc.jcajce.provider.newhope;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.ShortBufferException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.bouncycastle.pqc.crypto.ExchangePair;
import org.bouncycastle.pqc.crypto.newhope.NHAgreement;
import org.bouncycastle.pqc.crypto.newhope.NHExchangePairGenerator;
import org.bouncycastle.pqc.crypto.newhope.NHPublicKeyParameters;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class KeyAgreementSpi extends BaseAgreementSpi {
    public NHAgreement h;
    public BCNHPublicKey i;
    public NHExchangePairGenerator j;
    public byte[] k;

    public KeyAgreementSpi() {
        super("NH", null);
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return engineGenerateSecret();
    }

    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean z) throws InvalidKeyException, IllegalStateException {
        if (z) {
            BCNHPublicKey bCNHPublicKey = (BCNHPublicKey) key;
            this.i = bCNHPublicKey;
            NHExchangePairGenerator nHExchangePairGenerator = this.j;
            if (nHExchangePairGenerator == null) {
                this.k = this.h.calculateAgreement(bCNHPublicKey.getKeyParams());
                return null;
            }
            ExchangePair generateExchange = nHExchangePairGenerator.generateExchange((AsymmetricKeyParameter) bCNHPublicKey.getKeyParams());
            this.k = generateExchange.getSharedValue();
            return new BCNHPublicKey((NHPublicKeyParameters) generateExchange.getPublicKey());
        }
        throw new IllegalStateException("NewHope can only be between two parties.");
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i) throws IllegalStateException, ShortBufferException {
        byte[] bArr2 = this.k;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        Arrays.fill(this.k, (byte) 0);
        return this.k.length;
    }

    @Override // org.bouncycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi, javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() throws IllegalStateException {
        byte[] clone = Arrays.clone(this.k);
        Arrays.fill(this.k, (byte) 0);
        return clone;
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom secureRandom) throws InvalidKeyException {
        if (key == null) {
            this.j = new NHExchangePairGenerator(secureRandom);
            return;
        }
        NHAgreement nHAgreement = new NHAgreement();
        this.h = nHAgreement;
        nHAgreement.init(((BCNHPrivateKey) key).getKeyParams());
    }

    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidKeyException, InvalidAlgorithmParameterException {
        throw new InvalidAlgorithmParameterException("NewHope does not require parameters");
    }
}
