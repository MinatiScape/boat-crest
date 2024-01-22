package org.jose4j.keys;

import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Set;
import org.jose4j.base64url.SimplePEMEncoder;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public abstract class a {
    public String provider;
    public SecureRandom secureRandom;

    public a(String str, SecureRandom secureRandom) {
        this.provider = str;
        this.secureRandom = secureRandom;
    }

    public static String pemEncode(PublicKey publicKey) {
        byte[] encoded = publicKey.getEncoded();
        return "-----BEGIN PUBLIC KEY-----\r\n" + SimplePEMEncoder.encode(encoded) + "-----END PUBLIC KEY-----";
    }

    public abstract String a();

    public PublicKey fromPemEncoded(String str) throws JoseException, InvalidKeySpecException {
        return getKeyFactory().generatePublic(new X509EncodedKeySpec(SimplePEMEncoder.decode(str.substring(str.indexOf("-----BEGIN PUBLIC KEY-----") + 26, str.indexOf("-----END PUBLIC KEY-----")).trim())));
    }

    public KeyFactory getKeyFactory() throws JoseException {
        String a2 = a();
        try {
            String str = this.provider;
            return str == null ? KeyFactory.getInstance(a2) : KeyFactory.getInstance(a2, str);
        } catch (NoSuchAlgorithmException e) {
            throw new JoseException("Couldn't find " + a2 + " KeyFactory! " + e, e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get KeyFactory instance with provider " + this.provider, e2);
        }
    }

    public KeyPairGenerator getKeyPairGenerator() throws JoseException {
        String a2 = a();
        try {
            String str = this.provider;
            return str == null ? KeyPairGenerator.getInstance(a2) : KeyPairGenerator.getInstance(a2, str);
        } catch (NoSuchAlgorithmException e) {
            throw new JoseException("Couldn't find " + a2 + " KeyPairGenerator! " + e, e);
        } catch (NoSuchProviderException e2) {
            throw new JoseException("Cannot get KeyPairGenerator instance with provider " + this.provider, e2);
        }
    }

    public boolean isAvailable() {
        Set<String> algorithms = Security.getAlgorithms("KeyFactory");
        Set<String> algorithms2 = Security.getAlgorithms("KeyPairGenerator");
        String a2 = a();
        return algorithms2.contains(a2) && algorithms.contains(a2);
    }
}
