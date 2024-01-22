package org.bouncycastle.cert.crmf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.EncryptedValue;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.KeyWrapper;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfo;
import org.bouncycastle.pkcs.PKCS8EncryptedPrivateKeyInfoBuilder;
import org.bouncycastle.util.Strings;
/* loaded from: classes12.dex */
public class EncryptedValueBuilder {

    /* renamed from: a  reason: collision with root package name */
    public KeyWrapper f14459a;
    public OutputEncryptor b;
    public EncryptedValuePadder c;

    public EncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor) {
        this(keyWrapper, outputEncryptor, null);
    }

    public EncryptedValueBuilder(KeyWrapper keyWrapper, OutputEncryptor outputEncryptor, EncryptedValuePadder encryptedValuePadder) {
        this.f14459a = keyWrapper;
        this.b = outputEncryptor;
        this.c = encryptedValuePadder;
    }

    public final EncryptedValue a(byte[] bArr) throws CRMFException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream = this.b.getOutputStream(byteArrayOutputStream);
        try {
            outputStream.write(bArr);
            outputStream.close();
            AlgorithmIdentifier algorithmIdentifier = this.b.getAlgorithmIdentifier();
            try {
                this.f14459a.generateWrappedKey(this.b.getKey());
                return new EncryptedValue(null, algorithmIdentifier, new DERBitString(this.f14459a.generateWrappedKey(this.b.getKey())), this.f14459a.getAlgorithmIdentifier(), null, new DERBitString(byteArrayOutputStream.toByteArray()));
            } catch (OperatorException e) {
                throw new CRMFException("cannot wrap key: " + e.getMessage(), e);
            }
        } catch (IOException e2) {
            throw new CRMFException("cannot process data: " + e2.getMessage(), e2);
        }
    }

    public final byte[] b(byte[] bArr) {
        EncryptedValuePadder encryptedValuePadder = this.c;
        return encryptedValuePadder != null ? encryptedValuePadder.getPaddedData(bArr) : bArr;
    }

    public EncryptedValue build(PrivateKeyInfo privateKeyInfo) throws CRMFException {
        PKCS8EncryptedPrivateKeyInfoBuilder pKCS8EncryptedPrivateKeyInfoBuilder = new PKCS8EncryptedPrivateKeyInfoBuilder(privateKeyInfo);
        AlgorithmIdentifier privateKeyAlgorithm = privateKeyInfo.getPrivateKeyAlgorithm();
        AlgorithmIdentifier algorithmIdentifier = this.b.getAlgorithmIdentifier();
        try {
            PKCS8EncryptedPrivateKeyInfo build = pKCS8EncryptedPrivateKeyInfoBuilder.build(this.b);
            this.f14459a.generateWrappedKey(this.b.getKey());
            return new EncryptedValue(privateKeyAlgorithm, algorithmIdentifier, new DERBitString(this.f14459a.generateWrappedKey(this.b.getKey())), this.f14459a.getAlgorithmIdentifier(), null, new DERBitString(build.getEncoded()));
        } catch (IOException e) {
            throw new CRMFException("cannot encode encrypted private key: " + e.getMessage(), e);
        } catch (IllegalStateException e2) {
            throw new CRMFException("cannot encode key: " + e2.getMessage(), e2);
        } catch (OperatorException e3) {
            throw new CRMFException("cannot wrap key: " + e3.getMessage(), e3);
        }
    }

    public EncryptedValue build(X509CertificateHolder x509CertificateHolder) throws CRMFException {
        try {
            return a(b(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new CRMFException("cannot encode certificate: " + e.getMessage(), e);
        }
    }

    public EncryptedValue build(char[] cArr) throws CRMFException {
        return a(b(Strings.toUTF8ByteArray(cArr)));
    }
}
