package org.jose4j.keys;

import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.NamedParameterSpec;
import org.jose4j.lang.ExceptionHelp;
import org.jose4j.lang.JoseException;
/* loaded from: classes13.dex */
public abstract class OctetKeyPairUtil extends a {
    public OctetKeyPairUtil(String str, SecureRandom secureRandom) {
        super(str, secureRandom);
    }

    public static OctetKeyPairUtil getOctetKeyPairUtil(String str, String str2, SecureRandom secureRandom) {
        if (!str.equals("Ed25519") && !str.equals("Ed448")) {
            if (str.equals("X25519") || str.equals("X448")) {
                return new XDHKeyUtil(str2, secureRandom);
            }
            return null;
        }
        return new EdDsaKeyUtil(str2, secureRandom);
    }

    public NamedParameterSpec b(String str) throws JoseException {
        try {
            return new NamedParameterSpec(str);
        } catch (NoClassDefFoundError e) {
            throw new JoseException(str + " NamedParameterSpec not available. " + ExceptionHelp.toStringWithCauses(e));
        }
    }

    @Override // org.jose4j.keys.a
    public /* bridge */ /* synthetic */ PublicKey fromPemEncoded(String str) throws JoseException, InvalidKeySpecException {
        return super.fromPemEncoded(str);
    }

    public KeyPair generateKeyPair(String str) throws JoseException {
        KeyPairGenerator keyPairGenerator = getKeyPairGenerator();
        NamedParameterSpec b = b(str);
        try {
            SecureRandom secureRandom = this.secureRandom;
            if (secureRandom == null) {
                keyPairGenerator.initialize((AlgorithmParameterSpec) b);
            } else {
                keyPairGenerator.initialize((AlgorithmParameterSpec) b, secureRandom);
            }
            return keyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            throw new JoseException("Unable to create EdDSA key pair: " + e, e);
        }
    }

    @Override // org.jose4j.keys.a
    public /* bridge */ /* synthetic */ boolean isAvailable() {
        return super.isAvailable();
    }

    public abstract PrivateKey privateKey(byte[] bArr, String str) throws JoseException;

    public abstract PublicKey publicKey(byte[] bArr, String str) throws JoseException;

    public abstract byte[] rawPrivateKey(PrivateKey privateKey);

    public abstract byte[] rawPublicKey(Key key);
}
