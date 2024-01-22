package org.jose4j.jwe;

import java.security.Key;
import java.util.Objects;
import org.jose4j.base64url.Base64Url;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmFactoryFactory;
import org.jose4j.jwa.CryptoPrimitive;
import org.jose4j.jwx.CompactSerializer;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.jwx.Headers;
import org.jose4j.jwx.JsonWebStructure;
import org.jose4j.lang.ByteUtil;
import org.jose4j.lang.InvalidAlgorithmException;
import org.jose4j.lang.InvalidKeyException;
import org.jose4j.lang.JoseException;
import org.jose4j.lang.StringUtil;
import org.jose4j.zip.CompressionAlgorithmIdentifiers;
/* loaded from: classes13.dex */
public class JsonWebEncryption extends JsonWebStructure {
    public static final short COMPACT_SERIALIZATION_PARTS = 5;
    public byte[] i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public byte[] m;
    public CryptoPrimitive o;
    public Base64Url g = new Base64Url();
    public String h = "UTF-8";
    public AlgorithmConstraints n = AlgorithmConstraints.NO_CONSTRAINTS;

    public final void a(ContentEncryptionAlgorithm contentEncryptionAlgorithm, ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor, byte[] bArr) throws InvalidKeyException {
        int contentEncryptionKeyByteLength = contentEncryptionKeyDescriptor.getContentEncryptionKeyByteLength();
        if (bArr.length == contentEncryptionKeyByteLength) {
            return;
        }
        throw new InvalidKeyException(ByteUtil.bitLength(bArr) + " bit content encryption key is not the correct size for the " + contentEncryptionAlgorithm.getAlgorithmIdentifier() + " content encryption algorithm (" + ByteUtil.bitLength(contentEncryptionKeyByteLength) + ").");
    }

    public byte[] b(Headers headers, byte[] bArr) throws InvalidAlgorithmException {
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.ZIP);
        return stringHeaderValue != null ? AlgorithmFactoryFactory.getInstance().getCompressionAlgorithmFactory().getAlgorithm(stringHeaderValue).compress(bArr) : bArr;
    }

    public final CryptoPrimitive c() throws JoseException {
        KeyManagementAlgorithm keyManagementModeAlgorithm = getKeyManagementModeAlgorithm();
        Key key = getKey();
        if (isDoKeyValidation()) {
            keyManagementModeAlgorithm.validateDecryptionKey(key, getContentEncryptionAlgorithm());
        }
        return keyManagementModeAlgorithm.prepareForDecrypt(key, this.headers, getProviderCtx());
    }

    public byte[] d(Headers headers, byte[] bArr) throws JoseException {
        String stringHeaderValue = headers.getStringHeaderValue(HeaderParameterNames.ZIP);
        return stringHeaderValue != null ? AlgorithmFactoryFactory.getInstance().getCompressionAlgorithmFactory().getAlgorithm(stringHeaderValue).decompress(bArr) : bArr;
    }

    public final void e() throws JoseException {
        KeyManagementAlgorithm keyManagementModeAlgorithm = getKeyManagementModeAlgorithm();
        ContentEncryptionAlgorithm contentEncryptionAlgorithm = getContentEncryptionAlgorithm();
        ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor = contentEncryptionAlgorithm.getContentEncryptionKeyDescriptor();
        checkCrit();
        CryptoPrimitive cryptoPrimitive = this.o;
        if (cryptoPrimitive == null) {
            cryptoPrimitive = c();
        }
        Key manageForDecrypt = keyManagementModeAlgorithm.manageForDecrypt(cryptoPrimitive, getEncryptedKey(), contentEncryptionKeyDescriptor, getHeaders(), getProviderCtx());
        ContentEncryptionParts contentEncryptionParts = new ContentEncryptionParts(this.k, this.l, getIntegrity());
        byte[] f = f();
        byte[] encoded = manageForDecrypt.getEncoded();
        a(contentEncryptionAlgorithm, contentEncryptionKeyDescriptor, encoded);
        setPlaintext(d(getHeaders(), contentEncryptionAlgorithm.decrypt(contentEncryptionParts, f, encoded, getHeaders(), getProviderCtx())));
    }

    public void enableDefaultCompression() {
        setCompressionAlgorithmHeaderParameter(CompressionAlgorithmIdentifiers.DEFLATE);
    }

    public byte[] f() {
        return StringUtil.getBytesAscii(getEncodedHeader());
    }

    public KeyManagementAlgorithm g(boolean z) throws InvalidAlgorithmException {
        String algorithmHeaderValue = getAlgorithmHeaderValue();
        if (algorithmHeaderValue != null) {
            if (z) {
                getAlgorithmConstraints().checkConstraint(algorithmHeaderValue);
            }
            return AlgorithmFactoryFactory.getInstance().getJweKeyManagementAlgorithmFactory().getAlgorithm(algorithmHeaderValue);
        }
        throw new InvalidAlgorithmException("Encryption key management algorithm header (alg) not set.");
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public String getCompactSerialization() throws JoseException {
        KeyManagementAlgorithm keyManagementModeAlgorithm = getKeyManagementModeAlgorithm();
        ContentEncryptionAlgorithm contentEncryptionAlgorithm = getContentEncryptionAlgorithm();
        ContentEncryptionKeyDescriptor contentEncryptionKeyDescriptor = contentEncryptionAlgorithm.getContentEncryptionKeyDescriptor();
        Key key = getKey();
        if (isDoKeyValidation()) {
            keyManagementModeAlgorithm.validateEncryptionKey(getKey(), contentEncryptionAlgorithm);
        }
        ContentEncryptionKeys manageForEncrypt = keyManagementModeAlgorithm.manageForEncrypt(key, contentEncryptionKeyDescriptor, getHeaders(), this.m, getProviderCtx());
        setContentEncryptionKey(manageForEncrypt.getContentEncryptionKey());
        this.j = manageForEncrypt.getEncryptedKey();
        byte[] f = f();
        byte[] contentEncryptionKey = manageForEncrypt.getContentEncryptionKey();
        byte[] bArr = this.i;
        Objects.requireNonNull(bArr, "The plaintext payload for the JWE has not been set.");
        byte[] b = b(getHeaders(), bArr);
        a(contentEncryptionAlgorithm, contentEncryptionKeyDescriptor, contentEncryptionKey);
        ContentEncryptionParts encrypt = contentEncryptionAlgorithm.encrypt(b, f, contentEncryptionKey, getHeaders(), getIv(), getProviderCtx());
        setIv(encrypt.getIv());
        this.l = encrypt.getCiphertext();
        return CompactSerializer.serialize(getEncodedHeader(), this.g.base64UrlEncode(manageForEncrypt.getEncryptedKey()), this.g.base64UrlEncode(encrypt.getIv()), this.g.base64UrlEncode(encrypt.getCiphertext()), this.g.base64UrlEncode(encrypt.getAuthenticationTag()));
    }

    public String getCompressionAlgorithmHeaderParameter() {
        return getHeader(HeaderParameterNames.ZIP);
    }

    public ContentEncryptionAlgorithm getContentEncryptionAlgorithm() throws InvalidAlgorithmException {
        String encryptionMethodHeaderParameter = getEncryptionMethodHeaderParameter();
        if (encryptionMethodHeaderParameter != null) {
            this.n.checkConstraint(encryptionMethodHeaderParameter);
            return AlgorithmFactoryFactory.getInstance().getJweContentEncryptionAlgorithmFactory().getAlgorithm(encryptionMethodHeaderParameter);
        }
        throw new InvalidAlgorithmException("Content encryption header (enc) not set.");
    }

    public byte[] getContentEncryptionKey() {
        return this.m;
    }

    public byte[] getEncryptedKey() {
        return this.j;
    }

    public String getEncryptionMethodHeaderParameter() {
        return getHeader("enc");
    }

    public byte[] getIv() {
        return this.k;
    }

    public KeyManagementAlgorithm getKeyManagementModeAlgorithm() throws InvalidAlgorithmException {
        return g(true);
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public String getPayload() throws JoseException {
        return getPlaintextString();
    }

    public byte[] getPlaintextBytes() throws JoseException {
        if (this.i == null) {
            e();
        }
        return this.i;
    }

    public String getPlaintextString() throws JoseException {
        return StringUtil.newString(getPlaintextBytes(), this.h);
    }

    public CryptoPrimitive prepareDecryptingPrimitive() throws JoseException {
        CryptoPrimitive c = c();
        this.o = c;
        return c;
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public void setCompactSerializationParts(String[] strArr) throws JoseException {
        if (strArr.length == 5) {
            setEncodedHeader(strArr[0]);
            this.j = this.g.base64UrlDecode(strArr[1]);
            setEncodedIv(strArr[2]);
            String str = strArr[3];
            checkNotEmptyPart(str, "Encoded JWE Ciphertext");
            this.l = this.g.base64UrlDecode(str);
            String str2 = strArr[4];
            checkNotEmptyPart(str2, "Encoded JWE Authentication Tag");
            setIntegrity(this.g.base64UrlDecode(str2));
            return;
        }
        throw new JoseException("A JWE Compact Serialization must have exactly 5 parts separated by period ('.') characters");
    }

    public void setCompressionAlgorithmHeaderParameter(String str) {
        setHeader(HeaderParameterNames.ZIP, str);
    }

    public void setContentEncryptionAlgorithmConstraints(AlgorithmConstraints algorithmConstraints) {
        this.n = algorithmConstraints;
    }

    public void setContentEncryptionKey(byte[] bArr) {
        this.m = bArr;
    }

    public void setEncodedContentEncryptionKey(String str) {
        setContentEncryptionKey(Base64Url.decode(str));
    }

    public void setEncodedIv(String str) {
        setIv(this.g.base64UrlDecode(str));
    }

    public void setEncryptionMethodHeaderParameter(String str) {
        setHeader("enc", str);
    }

    public void setIv(byte[] bArr) {
        this.k = bArr;
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public void setPayload(String str) {
        setPlaintext(str);
    }

    public void setPlainTextCharEncoding(String str) {
        this.h = str;
    }

    public void setPlaintext(byte[] bArr) {
        this.i = bArr;
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public KeyManagementAlgorithm getAlgorithm() throws InvalidAlgorithmException {
        return getKeyManagementModeAlgorithm();
    }

    @Override // org.jose4j.jwx.JsonWebStructure
    public KeyManagementAlgorithm getAlgorithmNoConstraintCheck() throws InvalidAlgorithmException {
        return g(false);
    }

    public void setPlaintext(String str) {
        this.i = StringUtil.getBytesUnchecked(str, this.h);
    }
}
