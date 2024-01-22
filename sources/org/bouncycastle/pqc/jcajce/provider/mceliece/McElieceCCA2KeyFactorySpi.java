package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactorySpi;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter;
import org.bouncycastle.pqc.asn1.McElieceCCA2PrivateKey;
import org.bouncycastle.pqc.asn1.McElieceCCA2PublicKey;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
/* loaded from: classes13.dex */
public class McElieceCCA2KeyFactorySpi extends KeyFactorySpi implements AsymmetricKeyInfoConverter {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";

    @Override // java.security.KeyFactorySpi
    public PrivateKey engineGeneratePrivate(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof PKCS8EncodedKeySpec)) {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
        try {
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(((PKCS8EncodedKeySpec) keySpec).getEncoded()));
            try {
                if (PQCObjectIdentifiers.mcElieceCca2.equals(privateKeyInfo.getPrivateKeyAlgorithm().getAlgorithm())) {
                    McElieceCCA2PrivateKey mcElieceCCA2PrivateKey = McElieceCCA2PrivateKey.getInstance(privateKeyInfo.parsePrivateKey());
                    return new BCMcElieceCCA2PrivateKey(new McElieceCCA2PrivateKeyParameters(mcElieceCCA2PrivateKey.getN(), mcElieceCCA2PrivateKey.getK(), mcElieceCCA2PrivateKey.getField(), mcElieceCCA2PrivateKey.getGoppaPoly(), mcElieceCCA2PrivateKey.getP(), a.b(mcElieceCCA2PrivateKey.getDigest()).getAlgorithmName()));
                }
                throw new InvalidKeySpecException("Unable to recognise OID in McEliece public key");
            } catch (IOException unused) {
                throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec.");
            }
        } catch (IOException e) {
            throw new InvalidKeySpecException("Unable to decode PKCS8EncodedKeySpec: " + e);
        }
    }

    @Override // java.security.KeyFactorySpi
    public PublicKey engineGeneratePublic(KeySpec keySpec) throws InvalidKeySpecException {
        if (!(keySpec instanceof X509EncodedKeySpec)) {
            throw new InvalidKeySpecException("Unsupported key specification: " + keySpec.getClass() + ".");
        }
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(ASN1Primitive.fromByteArray(((X509EncodedKeySpec) keySpec).getEncoded()));
            try {
                if (PQCObjectIdentifiers.mcElieceCca2.equals(subjectPublicKeyInfo.getAlgorithm().getAlgorithm())) {
                    McElieceCCA2PublicKey mcElieceCCA2PublicKey = McElieceCCA2PublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
                    return new BCMcElieceCCA2PublicKey(new McElieceCCA2PublicKeyParameters(mcElieceCCA2PublicKey.getN(), mcElieceCCA2PublicKey.getT(), mcElieceCCA2PublicKey.getG(), a.b(mcElieceCCA2PublicKey.getDigest()).getAlgorithmName()));
                }
                throw new InvalidKeySpecException("Unable to recognise OID in McEliece private key");
            } catch (IOException e) {
                throw new InvalidKeySpecException("Unable to decode X509EncodedKeySpec: " + e.getMessage());
            }
        } catch (IOException e2) {
            throw new InvalidKeySpecException(e2.toString());
        }
    }

    @Override // java.security.KeyFactorySpi
    public KeySpec engineGetKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        return null;
    }

    @Override // java.security.KeyFactorySpi
    public Key engineTranslateKey(Key key) throws InvalidKeyException {
        return null;
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PrivateKey generatePrivate(PrivateKeyInfo privateKeyInfo) throws IOException {
        McElieceCCA2PrivateKey mcElieceCCA2PrivateKey = McElieceCCA2PrivateKey.getInstance(privateKeyInfo.parsePrivateKey().toASN1Primitive());
        return new BCMcElieceCCA2PrivateKey(new McElieceCCA2PrivateKeyParameters(mcElieceCCA2PrivateKey.getN(), mcElieceCCA2PrivateKey.getK(), mcElieceCCA2PrivateKey.getField(), mcElieceCCA2PrivateKey.getGoppaPoly(), mcElieceCCA2PrivateKey.getP(), null));
    }

    @Override // org.bouncycastle.jcajce.provider.util.AsymmetricKeyInfoConverter
    public PublicKey generatePublic(SubjectPublicKeyInfo subjectPublicKeyInfo) throws IOException {
        McElieceCCA2PublicKey mcElieceCCA2PublicKey = McElieceCCA2PublicKey.getInstance(subjectPublicKeyInfo.parsePublicKey());
        return new BCMcElieceCCA2PublicKey(new McElieceCCA2PublicKeyParameters(mcElieceCCA2PublicKey.getN(), mcElieceCCA2PublicKey.getT(), mcElieceCCA2PublicKey.getG(), a.b(mcElieceCCA2PublicKey.getDigest()).getAlgorithmName()));
    }

    public KeySpec getKeySpec(Key key, Class cls) throws InvalidKeySpecException {
        if (key instanceof BCMcElieceCCA2PrivateKey) {
            if (PKCS8EncodedKeySpec.class.isAssignableFrom(cls)) {
                return new PKCS8EncodedKeySpec(key.getEncoded());
            }
        } else if (!(key instanceof BCMcElieceCCA2PublicKey)) {
            throw new InvalidKeySpecException("Unsupported key type: " + key.getClass() + ".");
        } else if (X509EncodedKeySpec.class.isAssignableFrom(cls)) {
            return new X509EncodedKeySpec(key.getEncoded());
        }
        throw new InvalidKeySpecException("Unknown key specification: " + cls + ".");
    }

    public Key translateKey(Key key) throws InvalidKeyException {
        if ((key instanceof BCMcElieceCCA2PrivateKey) || (key instanceof BCMcElieceCCA2PublicKey)) {
            return key;
        }
        throw new InvalidKeyException("Unsupported key type.");
    }
}
