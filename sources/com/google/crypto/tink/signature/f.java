package com.google.crypto.tink.signature;

import com.google.crypto.tink.proto.EcdsaParams;
import com.google.crypto.tink.proto.EcdsaSignatureEncoding;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.proto.RsaSsaPkcs1Params;
import com.google.crypto.tink.proto.RsaSsaPssParams;
import com.google.crypto.tink.subtle.EllipticCurves;
import com.google.crypto.tink.subtle.Enums;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class f {

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11006a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[HashType.values().length];
            c = iArr;
            try {
                iArr[HashType.SHA256.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[HashType.SHA384.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[EllipticCurveType.values().length];
            b = iArr2;
            try {
                iArr2[EllipticCurveType.NIST_P256.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                b[EllipticCurveType.NIST_P384.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                b[EllipticCurveType.NIST_P521.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[EcdsaSignatureEncoding.values().length];
            f11006a = iArr3;
            try {
                iArr3[EcdsaSignatureEncoding.DER.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f11006a[EcdsaSignatureEncoding.IEEE_P1363.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static EllipticCurves.CurveType a(EllipticCurveType ellipticCurveType) throws GeneralSecurityException {
        int i = a.b[ellipticCurveType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return EllipticCurves.CurveType.NIST_P521;
                }
                throw new GeneralSecurityException("unknown curve type: " + ellipticCurveType.name());
            }
            return EllipticCurves.CurveType.NIST_P384;
        }
        return EllipticCurves.CurveType.NIST_P256;
    }

    public static EllipticCurves.EcdsaEncoding b(EcdsaSignatureEncoding ecdsaSignatureEncoding) throws GeneralSecurityException {
        int i = a.f11006a[ecdsaSignatureEncoding.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return EllipticCurves.EcdsaEncoding.IEEE_P1363;
            }
            throw new GeneralSecurityException("unknown ECDSA encoding: " + ecdsaSignatureEncoding.name());
        }
        return EllipticCurves.EcdsaEncoding.DER;
    }

    public static Enums.HashType c(HashType hashType) throws GeneralSecurityException {
        int i = a.c[hashType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return Enums.HashType.SHA512;
                }
                throw new GeneralSecurityException("unsupported hash type: " + hashType.name());
            }
            return Enums.HashType.SHA384;
        }
        return Enums.HashType.SHA256;
    }

    public static void d(EcdsaParams ecdsaParams) throws GeneralSecurityException {
        EcdsaSignatureEncoding encoding = ecdsaParams.getEncoding();
        HashType hashType = ecdsaParams.getHashType();
        EllipticCurveType curve = ecdsaParams.getCurve();
        int i = a.f11006a[encoding.ordinal()];
        if (i != 1 && i != 2) {
            throw new GeneralSecurityException("unsupported signature encoding");
        }
        int i2 = a.b[curve.ordinal()];
        if (i2 == 1) {
            if (hashType != HashType.SHA256) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        } else if (i2 == 2) {
            if (hashType != HashType.SHA384 && hashType != HashType.SHA512) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        } else if (i2 == 3) {
            if (hashType != HashType.SHA512) {
                throw new GeneralSecurityException("Invalid ECDSA parameters");
            }
        } else {
            throw new GeneralSecurityException("Invalid ECDSA parameters");
        }
    }

    public static void e(RsaSsaPkcs1Params rsaSsaPkcs1Params) throws GeneralSecurityException {
        c(rsaSsaPkcs1Params.getHashType());
    }

    public static void f(RsaSsaPssParams rsaSsaPssParams) throws GeneralSecurityException {
        c(rsaSsaPssParams.getSigHash());
        if (rsaSsaPssParams.getSigHash() == rsaSsaPssParams.getMgf1Hash()) {
            if (rsaSsaPssParams.getSaltLength() < 0) {
                throw new GeneralSecurityException("salt length is negative");
            }
            return;
        }
        throw new GeneralSecurityException("MGF1 hash is different from signature hash");
    }
}
