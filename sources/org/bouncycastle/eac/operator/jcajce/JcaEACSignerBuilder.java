package org.bouncycastle.eac.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.eac.operator.EACSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes13.dex */
public class JcaEACSignerBuilder {
    public static final Hashtable b;

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.eac.operator.jcajce.b f14901a = new org.bouncycastle.eac.operator.jcajce.a();

    /* loaded from: classes13.dex */
    public class a implements EACSigner {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ASN1ObjectIdentifier f14902a;
        public final /* synthetic */ b b;

        public a(JcaEACSignerBuilder jcaEACSignerBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, b bVar) {
            this.f14902a = aSN1ObjectIdentifier;
            this.b = bVar;
        }

        @Override // org.bouncycastle.eac.operator.EACSigner
        public OutputStream getOutputStream() {
            return this.b;
        }

        @Override // org.bouncycastle.eac.operator.EACSigner
        public byte[] getSignature() {
            try {
                byte[] a2 = this.b.a();
                return this.f14902a.on(EACObjectIdentifiers.id_TA_ECDSA) ? JcaEACSignerBuilder.c(a2) : a2;
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.eac.operator.EACSigner
        public ASN1ObjectIdentifier getUsageIdentifier() {
            return this.f14902a;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends OutputStream {
        public Signature h;

        public b(JcaEACSignerBuilder jcaEACSignerBuilder, Signature signature) {
            this.h = signature;
        }

        public byte[] a() throws SignatureException {
            return this.h.sign();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            try {
                this.h.update((byte) i);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            try {
                this.h.update(bArr);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.h.update(bArr, i, i2);
            } catch (SignatureException e) {
                throw new OperatorStreamException("exception in content signer: " + e.getMessage(), e);
            }
        }
    }

    static {
        Hashtable hashtable = new Hashtable();
        b = hashtable;
        hashtable.put("SHA1withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1);
        hashtable.put("SHA256withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256);
        hashtable.put("SHA1withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1);
        hashtable.put("SHA256withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256);
        hashtable.put("SHA512withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512);
        hashtable.put("SHA512withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512);
        hashtable.put("SHA1withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
        hashtable.put("SHA224withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
        hashtable.put("SHA256withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
        hashtable.put("SHA384withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
        hashtable.put("SHA512withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
    }

    public static void b(byte[] bArr, byte[] bArr2, int i) {
        int length = bArr.length;
        int i2 = 0;
        if (bArr[0] == 0) {
            length--;
            i2 = 1;
        }
        System.arraycopy(bArr, i2, bArr2, i, length);
    }

    public static byte[] c(byte[] bArr) {
        ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(bArr);
        BigInteger value = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue();
        BigInteger value2 = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(1)).getValue();
        byte[] byteArray = value.toByteArray();
        byte[] byteArray2 = value2.toByteArray();
        int d = d(byteArray);
        int d2 = d(byteArray2);
        int max = max(d, d2);
        int i = max * 2;
        byte[] bArr2 = new byte[i];
        Arrays.fill(bArr2, (byte) 0);
        b(byteArray, bArr2, max - d);
        b(byteArray2, bArr2, i - d2);
        return bArr2;
    }

    public static int d(byte[] bArr) {
        int length = bArr.length;
        return bArr[0] == 0 ? length - 1 : length;
    }

    public static int max(int i, int i2) {
        return i > i2 ? i : i2;
    }

    public EACSigner build(String str, PrivateKey privateKey) throws OperatorCreationException {
        return build((ASN1ObjectIdentifier) b.get(str), privateKey);
    }

    public EACSigner build(ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey) throws OperatorCreationException {
        try {
            Signature b2 = this.f14901a.b(aSN1ObjectIdentifier);
            b2.initSign(privateKey);
            return new a(this, aSN1ObjectIdentifier, new b(this, b2));
        } catch (InvalidKeyException e) {
            throw new OperatorCreationException("invalid key: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new OperatorCreationException("unable to find algorithm: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new OperatorCreationException("unable to find provider: " + e3.getMessage(), e3);
        }
    }

    public JcaEACSignerBuilder setProvider(String str) {
        this.f14901a = new c(str);
        return this;
    }

    public JcaEACSignerBuilder setProvider(Provider provider) {
        this.f14901a = new d(provider);
        return this;
    }
}
