package org.bouncycastle.cert.crmf;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cmp.CMPObjectIdentifiers;
import org.bouncycastle.asn1.cmp.PBMParameter;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.MacCalculator;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.bouncycastle.util.Strings;
/* loaded from: classes12.dex */
public class PKMACBuilder {

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmIdentifier f14463a;
    public int b;
    public AlgorithmIdentifier c;
    public int d;
    public SecureRandom e;
    public PKMACValuesCalculator f;
    public PBMParameter g;
    public int h;

    /* loaded from: classes12.dex */
    public class a implements MacCalculator {

        /* renamed from: a  reason: collision with root package name */
        public ByteArrayOutputStream f14464a = new ByteArrayOutputStream();
        public final /* synthetic */ PBMParameter b;
        public final /* synthetic */ byte[] c;

        public a(PBMParameter pBMParameter, byte[] bArr) {
            this.b = pBMParameter;
            this.c = bArr;
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return new AlgorithmIdentifier(CMPObjectIdentifiers.passwordBasedMac, this.b);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public GenericKey getKey() {
            return new GenericKey(getAlgorithmIdentifier(), this.c);
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public byte[] getMac() {
            try {
                return PKMACBuilder.this.f.calculateMac(this.c, this.f14464a.toByteArray());
            } catch (CRMFException e) {
                throw new RuntimeOperatorException("exception calculating mac: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.operator.MacCalculator
        public OutputStream getOutputStream() {
            return this.f14464a;
        }
    }

    public PKMACBuilder(AlgorithmIdentifier algorithmIdentifier, int i, AlgorithmIdentifier algorithmIdentifier2, PKMACValuesCalculator pKMACValuesCalculator) {
        this.d = 20;
        this.f14463a = algorithmIdentifier;
        this.b = i;
        this.c = algorithmIdentifier2;
        this.f = pKMACValuesCalculator;
    }

    public PKMACBuilder(PKMACValuesCalculator pKMACValuesCalculator) {
        this(new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1), 1000, new AlgorithmIdentifier(IANAObjectIdentifiers.hmacSHA1, DERNull.INSTANCE), pKMACValuesCalculator);
    }

    public PKMACBuilder(PKMACValuesCalculator pKMACValuesCalculator, int i) {
        this.d = 20;
        this.h = i;
        this.f = pKMACValuesCalculator;
    }

    public final void b(int i) {
        int i2 = this.h;
        if (i2 <= 0 || i <= i2) {
            return;
        }
        throw new IllegalArgumentException("iteration count exceeds limit (" + i + " > " + this.h + ")");
    }

    public MacCalculator build(char[] cArr) throws CRMFException {
        PBMParameter pBMParameter = this.g;
        if (pBMParameter != null) {
            return c(pBMParameter, cArr);
        }
        byte[] bArr = new byte[this.d];
        if (this.e == null) {
            this.e = new SecureRandom();
        }
        this.e.nextBytes(bArr);
        return c(new PBMParameter(bArr, this.f14463a, this.b, this.c), cArr);
    }

    public final MacCalculator c(PBMParameter pBMParameter, char[] cArr) throws CRMFException {
        byte[] uTF8ByteArray = Strings.toUTF8ByteArray(cArr);
        byte[] octets = pBMParameter.getSalt().getOctets();
        byte[] bArr = new byte[uTF8ByteArray.length + octets.length];
        System.arraycopy(uTF8ByteArray, 0, bArr, 0, uTF8ByteArray.length);
        System.arraycopy(octets, 0, bArr, uTF8ByteArray.length, octets.length);
        this.f.setup(pBMParameter.getOwf(), pBMParameter.getMac());
        int intValue = pBMParameter.getIterationCount().getValue().intValue();
        do {
            bArr = this.f.calculateDigest(bArr);
            intValue--;
        } while (intValue > 0);
        return new a(pBMParameter, bArr);
    }

    public PKMACBuilder setIterationCount(int i) {
        if (i >= 100) {
            b(i);
            this.b = i;
            return this;
        }
        throw new IllegalArgumentException("iteration count must be at least 100");
    }

    public PKMACBuilder setParameters(PBMParameter pBMParameter) {
        b(pBMParameter.getIterationCount().getValue().intValue());
        this.g = pBMParameter;
        return this;
    }

    public PKMACBuilder setSaltLength(int i) {
        if (i >= 8) {
            this.d = i;
            return this;
        }
        throw new IllegalArgumentException("salt length must be at least 8 bytes");
    }

    public PKMACBuilder setSecureRandom(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }
}
