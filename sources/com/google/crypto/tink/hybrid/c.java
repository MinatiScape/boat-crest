package com.google.crypto.tink.hybrid;

import com.google.crypto.tink.Registry;
import com.google.crypto.tink.proto.EcPointFormat;
import com.google.crypto.tink.proto.EciesAeadHkdfParams;
import com.google.crypto.tink.proto.EllipticCurveType;
import com.google.crypto.tink.proto.HashType;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes10.dex */
public class c {

    /* loaded from: classes10.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f10846a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[EcPointFormat.values().length];
            c = iArr;
            try {
                iArr[EcPointFormat.UNCOMPRESSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[EcPointFormat.DO_NOT_USE_CRUNCHY_UNCOMPRESSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[EcPointFormat.COMPRESSED.ordinal()] = 3;
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
            int[] iArr3 = new int[HashType.values().length];
            f10846a = iArr3;
            try {
                iArr3[HashType.SHA1.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f10846a[HashType.SHA256.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f10846a[HashType.SHA512.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
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
                throw new GeneralSecurityException("unknown curve type: " + ellipticCurveType);
            }
            return EllipticCurves.CurveType.NIST_P384;
        }
        return EllipticCurves.CurveType.NIST_P256;
    }

    public static String b(HashType hashType) throws NoSuchAlgorithmException {
        int i = a.f10846a[hashType.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return "HmacSha512";
                }
                throw new NoSuchAlgorithmException("hash unsupported for HMAC: " + hashType);
            }
            return "HmacSha256";
        }
        return "HmacSha1";
    }

    public static EllipticCurves.PointFormatType c(EcPointFormat ecPointFormat) throws GeneralSecurityException {
        int i = a.c[ecPointFormat.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return EllipticCurves.PointFormatType.COMPRESSED;
                }
                throw new GeneralSecurityException("unknown point format: " + ecPointFormat);
            }
            return EllipticCurves.PointFormatType.DO_NOT_USE_CRUNCHY_UNCOMPRESSED;
        }
        return EllipticCurves.PointFormatType.UNCOMPRESSED;
    }

    public static void d(EciesAeadHkdfParams eciesAeadHkdfParams) throws GeneralSecurityException {
        EllipticCurves.getCurveSpec(a(eciesAeadHkdfParams.getKemParams().getCurveType()));
        b(eciesAeadHkdfParams.getKemParams().getHkdfHashType());
        if (eciesAeadHkdfParams.getEcPointFormat() != EcPointFormat.UNKNOWN_FORMAT) {
            Registry.newKeyData(eciesAeadHkdfParams.getDemParams().getAeadDem());
            return;
        }
        throw new GeneralSecurityException("unknown EC point format");
    }
}
