package org.jose4j.jwa;

import java.security.Security;
import java.util.Arrays;
import org.jose4j.jwe.AesCbcHmacSha2ContentEncryptionAlgorithm;
import org.jose4j.jwe.AesGcmContentEncryptionAlgorithm;
import org.jose4j.jwe.AesGcmKeyEncryptionAlgorithm;
import org.jose4j.jwe.AesKeyWrapManagementAlgorithm;
import org.jose4j.jwe.ContentEncryptionAlgorithm;
import org.jose4j.jwe.DirectKeyManagementAlgorithm;
import org.jose4j.jwe.EcdhKeyAgreementAlgorithm;
import org.jose4j.jwe.EcdhKeyAgreementWithAesKeyWrapAlgorithm;
import org.jose4j.jwe.KeyManagementAlgorithm;
import org.jose4j.jwe.Pbes2HmacShaWithAesKeyWrapAlgorithm;
import org.jose4j.jwe.RsaKeyManagementAlgorithm;
import org.jose4j.jws.EcdsaUsingShaAlgorithm;
import org.jose4j.jws.EdDsaAlgorithm;
import org.jose4j.jws.HmacUsingShaAlgorithm;
import org.jose4j.jws.JsonWebSignatureAlgorithm;
import org.jose4j.jws.RsaUsingShaAlgorithm;
import org.jose4j.jws.UnsecuredNoneAlgorithm;
import org.jose4j.jwx.HeaderParameterNames;
import org.jose4j.zip.CompressionAlgorithm;
import org.jose4j.zip.DeflateRFC1951CompressionAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes13.dex */
public class AlgorithmFactoryFactory {
    public static final Logger e = LoggerFactory.getLogger(AlgorithmFactoryFactory.class);
    public static final AlgorithmFactoryFactory f = new AlgorithmFactoryFactory();

    /* renamed from: a  reason: collision with root package name */
    public AlgorithmFactory<JsonWebSignatureAlgorithm> f15511a;
    public AlgorithmFactory<KeyManagementAlgorithm> b;
    public AlgorithmFactory<ContentEncryptionAlgorithm> c;
    public AlgorithmFactory<CompressionAlgorithm> d;

    public AlgorithmFactoryFactory() {
        a();
    }

    public static AlgorithmFactoryFactory getInstance() {
        return f;
    }

    public final void a() {
        String property = System.getProperty("java.version");
        String property2 = System.getProperty("java.vendor");
        String property3 = System.getProperty("java.home");
        String arrays = Arrays.toString(Security.getProviders());
        Logger logger = e;
        logger.debug("Initializing jose4j (running with Java {} from {} at {} with {} security providers installed)...", property, property2, property3, arrays);
        long currentTimeMillis = System.currentTimeMillis();
        AlgorithmFactory<JsonWebSignatureAlgorithm> algorithmFactory = new AlgorithmFactory<>("alg", JsonWebSignatureAlgorithm.class);
        this.f15511a = algorithmFactory;
        algorithmFactory.registerAlgorithm(new UnsecuredNoneAlgorithm());
        this.f15511a.registerAlgorithm(new HmacUsingShaAlgorithm.HmacSha256());
        this.f15511a.registerAlgorithm(new HmacUsingShaAlgorithm.HmacSha384());
        this.f15511a.registerAlgorithm(new HmacUsingShaAlgorithm.HmacSha512());
        this.f15511a.registerAlgorithm(new EdDsaAlgorithm());
        this.f15511a.registerAlgorithm(new EcdsaUsingShaAlgorithm.EcdsaP256UsingSha256());
        this.f15511a.registerAlgorithm(new EcdsaUsingShaAlgorithm.EcdsaP384UsingSha384());
        this.f15511a.registerAlgorithm(new EcdsaUsingShaAlgorithm.EcdsaP521UsingSha512());
        this.f15511a.registerAlgorithm(new EcdsaUsingShaAlgorithm.EcdsaSECP256K1UsingSha256());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaSha256());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaSha384());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaSha512());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaPssSha256());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaPssSha384());
        this.f15511a.registerAlgorithm(new RsaUsingShaAlgorithm.RsaPssSha512());
        logger.debug("JWS signature algorithms: {}", this.f15511a.getSupportedAlgorithms());
        AlgorithmFactory<KeyManagementAlgorithm> algorithmFactory2 = new AlgorithmFactory<>("alg", KeyManagementAlgorithm.class);
        this.b = algorithmFactory2;
        algorithmFactory2.registerAlgorithm(new RsaKeyManagementAlgorithm.Rsa1_5());
        this.b.registerAlgorithm(new RsaKeyManagementAlgorithm.RsaOaep());
        this.b.registerAlgorithm(new RsaKeyManagementAlgorithm.RsaOaep256());
        this.b.registerAlgorithm(new DirectKeyManagementAlgorithm());
        this.b.registerAlgorithm(new AesKeyWrapManagementAlgorithm.Aes128());
        this.b.registerAlgorithm(new AesKeyWrapManagementAlgorithm.Aes192());
        this.b.registerAlgorithm(new AesKeyWrapManagementAlgorithm.Aes256());
        this.b.registerAlgorithm(new EcdhKeyAgreementAlgorithm());
        this.b.registerAlgorithm(new EcdhKeyAgreementWithAesKeyWrapAlgorithm.EcdhKeyAgreementWithAes128KeyWrapAlgorithm());
        this.b.registerAlgorithm(new EcdhKeyAgreementWithAesKeyWrapAlgorithm.EcdhKeyAgreementWithAes192KeyWrapAlgorithm());
        this.b.registerAlgorithm(new EcdhKeyAgreementWithAesKeyWrapAlgorithm.EcdhKeyAgreementWithAes256KeyWrapAlgorithm());
        this.b.registerAlgorithm(new Pbes2HmacShaWithAesKeyWrapAlgorithm.HmacSha256Aes128());
        this.b.registerAlgorithm(new Pbes2HmacShaWithAesKeyWrapAlgorithm.HmacSha384Aes192());
        this.b.registerAlgorithm(new Pbes2HmacShaWithAesKeyWrapAlgorithm.HmacSha512Aes256());
        this.b.registerAlgorithm(new AesGcmKeyEncryptionAlgorithm.Aes128Gcm());
        this.b.registerAlgorithm(new AesGcmKeyEncryptionAlgorithm.Aes192Gcm());
        this.b.registerAlgorithm(new AesGcmKeyEncryptionAlgorithm.Aes256Gcm());
        logger.debug("JWE key management algorithms: {}", this.b.getSupportedAlgorithms());
        AlgorithmFactory<ContentEncryptionAlgorithm> algorithmFactory3 = new AlgorithmFactory<>("enc", ContentEncryptionAlgorithm.class);
        this.c = algorithmFactory3;
        algorithmFactory3.registerAlgorithm(new AesCbcHmacSha2ContentEncryptionAlgorithm.Aes128CbcHmacSha256());
        this.c.registerAlgorithm(new AesCbcHmacSha2ContentEncryptionAlgorithm.Aes192CbcHmacSha384());
        this.c.registerAlgorithm(new AesCbcHmacSha2ContentEncryptionAlgorithm.Aes256CbcHmacSha512());
        this.c.registerAlgorithm(new AesGcmContentEncryptionAlgorithm.Aes128Gcm());
        this.c.registerAlgorithm(new AesGcmContentEncryptionAlgorithm.Aes192Gcm());
        this.c.registerAlgorithm(new AesGcmContentEncryptionAlgorithm.Aes256Gcm());
        logger.debug("JWE content encryption algorithms: {}", this.c.getSupportedAlgorithms());
        AlgorithmFactory<CompressionAlgorithm> algorithmFactory4 = new AlgorithmFactory<>(HeaderParameterNames.ZIP, CompressionAlgorithm.class);
        this.d = algorithmFactory4;
        algorithmFactory4.registerAlgorithm(new DeflateRFC1951CompressionAlgorithm());
        logger.debug("JWE compression algorithms: {}", this.d.getSupportedAlgorithms());
        logger.debug("Initialized jose4j in {}ms", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
    }

    public AlgorithmFactory<CompressionAlgorithm> getCompressionAlgorithmFactory() {
        return this.d;
    }

    public AlgorithmFactory<ContentEncryptionAlgorithm> getJweContentEncryptionAlgorithmFactory() {
        return this.c;
    }

    public AlgorithmFactory<KeyManagementAlgorithm> getJweKeyManagementAlgorithmFactory() {
        return this.b;
    }

    public AlgorithmFactory<JsonWebSignatureAlgorithm> getJwsAlgorithmFactory() {
        return this.f15511a;
    }
}
