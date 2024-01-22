package org.bouncycastle.crypto.signers;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithID;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECMultiplier;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointCombMultiplier;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class SM2Signer implements Signer, ECConstants {

    /* renamed from: a  reason: collision with root package name */
    public final DSAKCalculator f14844a = new RandomDSAKCalculator();
    public final SM3Digest b = new SM3Digest();
    public ECDomainParameters c;
    public ECPoint d;
    public ECKeyParameters e;
    public byte[] f;

    public final void a(Digest digest, ECFieldElement eCFieldElement) {
        byte[] encoded = eCFieldElement.getEncoded();
        digest.update(encoded, 0, encoded.length);
    }

    public final void b(Digest digest, byte[] bArr) {
        int length = bArr.length * 8;
        digest.update((byte) ((length >> 8) & 255));
        digest.update((byte) (length & 255));
        digest.update(bArr, 0, bArr.length);
    }

    public final byte[] c() {
        byte[] bArr = new byte[this.b.getDigestSize()];
        this.b.doFinal(bArr, 0);
        reset();
        return bArr;
    }

    public BigInteger calculateE(byte[] bArr) {
        return new BigInteger(1, bArr);
    }

    public ECMultiplier createBasePointMultiplier() {
        return new FixedPointCombMultiplier();
    }

    public final byte[] d(byte[] bArr) {
        this.b.reset();
        b(this.b, bArr);
        a(this.b, this.c.getCurve().getA());
        a(this.b, this.c.getCurve().getB());
        a(this.b, this.c.getG().getAffineXCoord());
        a(this.b, this.c.getG().getAffineYCoord());
        a(this.b, this.d.getAffineXCoord());
        a(this.b, this.d.getAffineYCoord());
        byte[] bArr2 = new byte[this.b.getDigestSize()];
        this.b.doFinal(bArr2, 0);
        return bArr2;
    }

    public BigInteger[] derDecode(byte[] bArr) throws IOException {
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(ASN1Primitive.fromByteArray(bArr));
        if (aSN1Sequence.size() != 2) {
            return null;
        }
        BigInteger value = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        BigInteger value2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
        if (Arrays.constantTimeAreEqual(derEncode(value, value2), bArr)) {
            return new BigInteger[]{value, value2};
        }
        return null;
    }

    public byte[] derEncode(BigInteger bigInteger, BigInteger bigInteger2) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(bigInteger));
        aSN1EncodableVector.add(new ASN1Integer(bigInteger2));
        return new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
    }

    public final boolean e(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger n = this.c.getN();
        BigInteger bigInteger3 = ECConstants.ONE;
        if (bigInteger.compareTo(bigInteger3) < 0 || bigInteger.compareTo(n) >= 0 || bigInteger2.compareTo(bigInteger3) < 0 || bigInteger2.compareTo(n) >= 0) {
            return false;
        }
        BigInteger calculateE = calculateE(c());
        BigInteger mod = bigInteger.add(bigInteger2).mod(n);
        if (mod.equals(ECConstants.ZERO)) {
            return false;
        }
        ECPoint normalize = ECAlgorithms.sumOfTwoMultiplies(this.c.getG(), bigInteger2, ((ECPublicKeyParameters) this.e).getQ(), mod).normalize();
        if (normalize.isInfinity()) {
            return false;
        }
        return calculateE.add(normalize.getAffineXCoord().toBigInteger()).mod(n).equals(bigInteger);
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        byte[] c = c();
        BigInteger n = this.c.getN();
        BigInteger calculateE = calculateE(c);
        BigInteger d = ((ECPrivateKeyParameters) this.e).getD();
        ECMultiplier createBasePointMultiplier = createBasePointMultiplier();
        while (true) {
            BigInteger nextK = this.f14844a.nextK();
            BigInteger mod = calculateE.add(createBasePointMultiplier.multiply(this.c.getG(), nextK).normalize().getAffineXCoord().toBigInteger()).mod(n);
            BigInteger bigInteger = ECConstants.ZERO;
            if (!mod.equals(bigInteger) && !mod.add(nextK).equals(n)) {
                BigInteger mod2 = d.add(ECConstants.ONE).modInverse(n).multiply(nextK.subtract(mod.multiply(d)).mod(n)).mod(n);
                if (!mod2.equals(bigInteger)) {
                    try {
                        return derEncode(mod, mod2);
                    } catch (IOException e) {
                        throw new CryptoException("unable to encode signature: " + e.getMessage(), e);
                    }
                }
            }
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] decode;
        ECPoint q;
        if (cipherParameters instanceof ParametersWithID) {
            ParametersWithID parametersWithID = (ParametersWithID) cipherParameters;
            CipherParameters parameters = parametersWithID.getParameters();
            decode = parametersWithID.getID();
            cipherParameters = parameters;
        } else {
            decode = Hex.decode("31323334353637383132333435363738");
        }
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                ECKeyParameters eCKeyParameters = (ECKeyParameters) parametersWithRandom.getParameters();
                this.e = eCKeyParameters;
                ECDomainParameters parameters2 = eCKeyParameters.getParameters();
                this.c = parameters2;
                this.f14844a.init(parameters2.getN(), parametersWithRandom.getRandom());
            } else {
                ECKeyParameters eCKeyParameters2 = (ECKeyParameters) cipherParameters;
                this.e = eCKeyParameters2;
                ECDomainParameters parameters3 = eCKeyParameters2.getParameters();
                this.c = parameters3;
                this.f14844a.init(parameters3.getN(), new SecureRandom());
            }
            q = createBasePointMultiplier().multiply(this.c.getG(), ((ECPrivateKeyParameters) this.e).getD()).normalize();
        } else {
            ECKeyParameters eCKeyParameters3 = (ECKeyParameters) cipherParameters;
            this.e = eCKeyParameters3;
            this.c = eCKeyParameters3.getParameters();
            q = ((ECPublicKeyParameters) this.e).getQ();
        }
        this.d = q;
        byte[] d = d(decode);
        this.f = d;
        this.b.update(d, 0, d.length);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.b.reset();
        byte[] bArr = this.f;
        if (bArr != null) {
            this.b.update(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.b.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.b.update(bArr, i, i2);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        try {
            BigInteger[] derDecode = derDecode(bArr);
            if (derDecode != null) {
                return e(derDecode[0], derDecode[1]);
            }
        } catch (IOException unused) {
        }
        return false;
    }
}
