package com.google.crypto.tink.signature;

import com.google.crypto.tink.KeysetReader;
import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaPublicKey;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPkcs1PublicKey;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.proto.RsaSsaPssPublicKey;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.subtle.Enums;
import com.google.crypto.tink.subtle.PemKeyType;
import com.google.crypto.tink.subtle.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.security.Key;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import org.jose4j.jws.RsaUsingShaAlgorithm;
/* loaded from: classes10.dex */
public final class SignaturePemKeysetReader implements KeysetReader {

    /* renamed from: a  reason: collision with root package name */
    public List<b> f11000a;

    /* loaded from: classes10.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<b> f11001a = new ArrayList();

        public Builder addPem(String str, PemKeyType pemKeyType) {
            b bVar = new b(null);
            bVar.f11003a = new BufferedReader(new StringReader(str));
            bVar.b = pemKeyType;
            this.f11001a.add(bVar);
            return this;
        }

        public KeysetReader build() {
            return new SignaturePemKeysetReader(this.f11001a);
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11002a;

        static {
            int[] iArr = new int[Enums.HashType.values().length];
            f11002a = iArr;
            try {
                iArr[Enums.HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11002a[Enums.HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11002a[Enums.HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public BufferedReader f11003a;
        public PemKeyType b;

        public b() {
        }

        public /* synthetic */ b(a aVar) {
            this();
        }
    }

    public SignaturePemKeysetReader(List<b> list) {
        this.f11000a = list;
    }

    public static KeyData a(PemKeyType pemKeyType, ECPublicKey eCPublicKey) throws IOException {
        if (pemKeyType.algorithm.equals("ECDSA")) {
            return KeyData.newBuilder().setTypeUrl(new com.google.crypto.tink.signature.a().getKeyType()).setValue(EcdsaPublicKey.newBuilder().setVersion(new com.google.crypto.tink.signature.a().getVersion()).setParams(EcdsaParams.newBuilder().setHashType(e(pemKeyType)).setCurve(c(pemKeyType)).setEncoding(EcdsaSignatureEncoding.DER).build()).setX(ByteString.copyFrom(eCPublicKey.getW().getAffineX().toByteArray())).setY(ByteString.copyFrom(eCPublicKey.getW().getAffineY().toByteArray())).build().toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        }
        throw new IOException("unsupported EC signature algorithm: " + pemKeyType.algorithm);
    }

    public static KeyData b(PemKeyType pemKeyType, RSAPublicKey rSAPublicKey) throws IOException {
        if (pemKeyType.algorithm.equals("RSASSA-PKCS1-v1_5")) {
            return KeyData.newBuilder().setTypeUrl(new d().getKeyType()).setValue(RsaSsaPkcs1PublicKey.newBuilder().setVersion(new d().getVersion()).setParams(RsaSsaPkcs1Params.newBuilder().setHashType(e(pemKeyType)).build()).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build().toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        } else if (pemKeyType.algorithm.equals(RsaUsingShaAlgorithm.RSASSA_PSS)) {
            return KeyData.newBuilder().setTypeUrl(new e().getKeyType()).setValue(RsaSsaPssPublicKey.newBuilder().setVersion(new e().getVersion()).setParams(RsaSsaPssParams.newBuilder().setSigHash(e(pemKeyType)).setMgf1Hash(e(pemKeyType)).setSaltLength(d(pemKeyType)).build()).setE(ByteString.copyFrom(rSAPublicKey.getPublicExponent().toByteArray())).setN(ByteString.copyFrom(rSAPublicKey.getModulus().toByteArray())).build().toByteString()).setKeyMaterialType(KeyData.KeyMaterialType.ASYMMETRIC_PUBLIC).build();
        } else {
            throw new IOException("unsupported RSA signature algorithm: " + pemKeyType.algorithm);
        }
    }

    public static EllipticCurveType c(PemKeyType pemKeyType) {
        int i = pemKeyType.keySizeInBits;
        if (i != 256) {
            if (i != 384) {
                if (i == 521) {
                    return EllipticCurveType.NIST_P521;
                }
                throw new IllegalArgumentException("unsupported curve for key size: " + pemKeyType.keySizeInBits);
            }
            return EllipticCurveType.NIST_P384;
        }
        return EllipticCurveType.NIST_P256;
    }

    public static int d(PemKeyType pemKeyType) {
        int i = a.f11002a[pemKeyType.hash.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return 64;
                }
                throw new IllegalArgumentException("unsupported hash type: " + pemKeyType.hash.name());
            }
            return 48;
        }
        return 32;
    }

    public static HashType e(PemKeyType pemKeyType) {
        int i = a.f11002a[pemKeyType.hash.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return HashType.SHA512;
                }
                throw new IllegalArgumentException("unsupported hash type: " + pemKeyType.hash.name());
            }
            return HashType.SHA384;
        }
        return HashType.SHA256;
    }

    public static Keyset.Key f(BufferedReader bufferedReader, PemKeyType pemKeyType) throws IOException {
        KeyData a2;
        Key readKey = pemKeyType.readKey(bufferedReader);
        if (readKey == null) {
            return null;
        }
        if (readKey instanceof RSAPublicKey) {
            a2 = b(pemKeyType, (RSAPublicKey) readKey);
        } else if (!(readKey instanceof ECPublicKey)) {
            return null;
        } else {
            a2 = a(pemKeyType, (ECPublicKey) readKey);
        }
        return Keyset.Key.newBuilder().setKeyData(a2).setStatus(KeyStatusType.ENABLED).setOutputPrefixType(OutputPrefixType.RAW).setKeyId(Random.randInt()).build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() throws IOException {
        Keyset.Builder newBuilder = Keyset.newBuilder();
        for (b bVar : this.f11000a) {
            for (Keyset.Key f = f(bVar.f11003a, bVar.b); f != null; f = f(bVar.f11003a, bVar.b)) {
                newBuilder.addKey(f);
            }
        }
        if (newBuilder.getKeyCount() != 0) {
            newBuilder.setPrimaryKeyId(newBuilder.getKey(0).getKeyId());
            return newBuilder.build();
        }
        throw new IOException("cannot find any key");
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() throws IOException {
        throw new UnsupportedOperationException();
    }
}
