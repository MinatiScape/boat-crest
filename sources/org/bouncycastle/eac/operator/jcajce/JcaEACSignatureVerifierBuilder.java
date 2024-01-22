package org.bouncycastle.eac.operator.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.eac.EACObjectIdentifiers;
import org.bouncycastle.eac.operator.EACSignatureVerifier;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OperatorStreamException;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes13.dex */
public class JcaEACSignatureVerifierBuilder {

    /* renamed from: a  reason: collision with root package name */
    public org.bouncycastle.eac.operator.jcajce.b f14899a = new org.bouncycastle.eac.operator.jcajce.a();

    /* loaded from: classes13.dex */
    public class a implements EACSignatureVerifier {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ASN1ObjectIdentifier f14900a;
        public final /* synthetic */ b b;

        public a(JcaEACSignatureVerifierBuilder jcaEACSignatureVerifierBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, b bVar) {
            this.f14900a = aSN1ObjectIdentifier;
            this.b = bVar;
        }

        @Override // org.bouncycastle.eac.operator.EACSignatureVerifier
        public OutputStream getOutputStream() {
            return this.b;
        }

        @Override // org.bouncycastle.eac.operator.EACSignatureVerifier
        public ASN1ObjectIdentifier getUsageIdentifier() {
            return this.f14900a;
        }

        @Override // org.bouncycastle.eac.operator.EACSignatureVerifier
        public boolean verify(byte[] bArr) {
            try {
                if (this.f14900a.on(EACObjectIdentifiers.id_TA_ECDSA)) {
                    try {
                        return this.b.a(JcaEACSignatureVerifierBuilder.b(bArr));
                    } catch (Exception unused) {
                        return false;
                    }
                }
                return this.b.a(bArr);
            } catch (SignatureException e) {
                throw new RuntimeOperatorException("exception obtaining signature: " + e.getMessage(), e);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends OutputStream {
        public Signature h;

        public b(JcaEACSignatureVerifierBuilder jcaEACSignatureVerifierBuilder, Signature signature) {
            this.h = signature;
        }

        public boolean a(byte[] bArr) throws SignatureException {
            return this.h.verify(bArr);
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

    public static byte[] b(byte[] bArr) throws IOException {
        int length = bArr.length / 2;
        byte[] bArr2 = new byte[length];
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        System.arraycopy(bArr, length, bArr3, 0, length);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(new BigInteger(1, bArr2)));
        aSN1EncodableVector.add(new ASN1Integer(new BigInteger(1, bArr3)));
        return new DERSequence(aSN1EncodableVector).getEncoded();
    }

    public EACSignatureVerifier build(ASN1ObjectIdentifier aSN1ObjectIdentifier, PublicKey publicKey) throws OperatorCreationException {
        try {
            Signature b2 = this.f14899a.b(aSN1ObjectIdentifier);
            b2.initVerify(publicKey);
            return new a(this, aSN1ObjectIdentifier, new b(this, b2));
        } catch (InvalidKeyException e) {
            throw new OperatorCreationException("invalid key: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new OperatorCreationException("unable to find algorithm: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new OperatorCreationException("unable to find provider: " + e3.getMessage(), e3);
        }
    }

    public JcaEACSignatureVerifierBuilder setProvider(String str) {
        this.f14899a = new c(str);
        return this;
    }

    public JcaEACSignatureVerifierBuilder setProvider(Provider provider) {
        this.f14899a = new d(provider);
        return this;
    }
}
