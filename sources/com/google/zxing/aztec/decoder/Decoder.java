package com.google.zxing.aztec.decoder;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.material.color.c;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Arrays;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.jose4j.jwk.EllipticCurveJsonWebKey;
import org.jose4j.jwk.OctetSequenceJsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.slf4j.Marker;
/* loaded from: classes11.dex */
public final class Decoder {
    public static final String[] b = {"CTRL_PS", HexStringBuilder.DEFAULT_SEPARATOR, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", WeatherCriteria.UNIT_CELSIUS, "D", ExifInterface.LONGITUDE_EAST, WeatherCriteria.UNIT_FARENHEIT, "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    public static final String[] c = {"CTRL_PS", HexStringBuilder.DEFAULT_SEPARATOR, "a", "b", c.f10260a, "d", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "f", "g", "h", "i", "j", OctetSequenceJsonWebKey.KEY_VALUE_MEMBER_NAME, "l", "m", "n", "o", RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME, RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, RsaJsonWebKey.PRIME_FACTOR_OTHER_MEMBER_NAME, "s", RsaJsonWebKey.FACTOR_CRT_COEFFICIENT, "u", CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, Constants.INAPP_WINDOW, "x", EllipticCurveJsonWebKey.Y_MEMBER_NAME, "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    public static final String[] d = {"CTRL_PS", HexStringBuilder.DEFAULT_SEPARATOR, "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    public static final String[] e = {"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", MqttTopic.MULTI_LEVEL_WILDCARD, "$", "%", "&", "'", "(", ")", Marker.ANY_MARKER, "+", Constants.SEPARATOR_COMMA, "-", ".", MqttTopic.TOPIC_LEVEL_SEPARATOR, ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    public static final String[] f = {"CTRL_PS", HexStringBuilder.DEFAULT_SEPARATOR, BleConst.GetDeviceTime, "1", "2", "3", BleConst.GetDeviceInfo, BleConst.SetDeviceInfo, BleConst.CMD_Set_Mac, BleConst.GetStepGoal, BleConst.SetStepGoal, BleConst.GetDeviceBatteryLevel, Constants.SEPARATOR_COMMA, ".", "CTRL_UL", "CTRL_US"};

    /* renamed from: a  reason: collision with root package name */
    public AztecDetectorResult f11769a;

    /* loaded from: classes11.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11770a;

        static {
            int[] iArr = new int[b.values().length];
            f11770a = iArr;
            try {
                iArr[b.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f11770a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f11770a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f11770a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f11770a[b.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public enum b {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public static byte[] a(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = g(zArr, i << 3);
        }
        return bArr;
    }

    public static String d(b bVar, int i) {
        int i2 = a.f11770a[bVar.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return f[i];
                        }
                        throw new IllegalStateException("Bad table");
                    }
                    return e[i];
                }
                return d[i];
            }
            return c[i];
        }
        return b[i];
    }

    public static String e(boolean[] zArr) {
        int length = zArr.length;
        b bVar = b.UPPER;
        StringBuilder sb = new StringBuilder(20);
        b bVar2 = bVar;
        int i = 0;
        while (i < length) {
            if (bVar != b.BINARY) {
                int i2 = bVar == b.DIGIT ? 4 : 5;
                if (length - i < i2) {
                    break;
                }
                int h = h(zArr, i, i2);
                i += i2;
                String d2 = d(bVar, h);
                if (d2.startsWith("CTRL_")) {
                    bVar2 = f(d2.charAt(5));
                    if (d2.charAt(6) != 'L') {
                        bVar2 = bVar;
                        bVar = bVar2;
                    }
                } else {
                    sb.append(d2);
                }
                bVar = bVar2;
            } else if (length - i < 5) {
                break;
            } else {
                int h2 = h(zArr, i, 5);
                i += 5;
                if (h2 == 0) {
                    if (length - i < 11) {
                        break;
                    }
                    h2 = h(zArr, i, 11) + 31;
                    i += 11;
                }
                int i3 = 0;
                while (true) {
                    if (i3 >= h2) {
                        break;
                    } else if (length - i < 8) {
                        i = length;
                        break;
                    } else {
                        sb.append((char) h(zArr, i, 8));
                        i += 8;
                        i3++;
                    }
                }
                bVar = bVar2;
            }
        }
        return sb.toString();
    }

    public static b f(char c2) {
        if (c2 != 'B') {
            if (c2 != 'D') {
                if (c2 != 'P') {
                    if (c2 != 'L') {
                        if (c2 != 'M') {
                            return b.UPPER;
                        }
                        return b.MIXED;
                    }
                    return b.LOWER;
                }
                return b.PUNCT;
            }
            return b.DIGIT;
        }
        return b.BINARY;
    }

    public static byte g(boolean[] zArr, int i) {
        int h;
        int length = zArr.length - i;
        if (length >= 8) {
            h = h(zArr, i, 8);
        } else {
            h = h(zArr, i, length) << (8 - length);
        }
        return (byte) h;
    }

    public static int h(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return e(zArr);
    }

    public static int i(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public final boolean[] b(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int i = 8;
        if (this.f11769a.getNbLayers() <= 2) {
            i = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.f11769a.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.f11769a.getNbLayers() <= 22) {
            i = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            i = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.f11769a.getNbDatablocks();
        int length = zArr.length / i;
        if (length >= nbDatablocks) {
            int length2 = zArr.length % i;
            int[] iArr = new int[length];
            int i2 = 0;
            while (i2 < length) {
                iArr[i2] = h(zArr, length2, i);
                i2++;
                length2 += i;
            }
            try {
                new ReedSolomonDecoder(genericGF).decode(iArr, length - nbDatablocks);
                int i3 = (1 << i) - 1;
                int i4 = 0;
                for (int i5 = 0; i5 < nbDatablocks; i5++) {
                    int i6 = iArr[i5];
                    if (i6 == 0 || i6 == i3) {
                        throw FormatException.getFormatInstance();
                    }
                    if (i6 == 1 || i6 == i3 - 1) {
                        i4++;
                    }
                }
                boolean[] zArr2 = new boolean[(nbDatablocks * i) - i4];
                int i7 = 0;
                for (int i8 = 0; i8 < nbDatablocks; i8++) {
                    int i9 = iArr[i8];
                    if (i9 == 1 || i9 == i3 - 1) {
                        Arrays.fill(zArr2, i7, (i7 + i) - 1, i9 > 1);
                        i7 += i - 1;
                    } else {
                        int i10 = i - 1;
                        while (i10 >= 0) {
                            int i11 = i7 + 1;
                            zArr2[i7] = ((1 << i10) & i9) != 0;
                            i10--;
                            i7 = i11;
                        }
                    }
                }
                return zArr2;
            } catch (ReedSolomonException e2) {
                throw FormatException.getFormatInstance(e2);
            }
        }
        throw FormatException.getFormatInstance();
    }

    public final boolean[] c(BitMatrix bitMatrix) {
        boolean isCompact = this.f11769a.isCompact();
        int nbLayers = this.f11769a.getNbLayers();
        int i = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[i(nbLayers, isCompact)];
        int i2 = 2;
        if (isCompact) {
            for (int i3 = 0; i3 < i; i3++) {
                iArr[i3] = i3;
            }
        } else {
            int i4 = i / 2;
            int i5 = ((i + 1) + (((i4 - 1) / 15) * 2)) / 2;
            for (int i6 = 0; i6 < i4; i6++) {
                int i7 = (i6 / 15) + i6;
                iArr[(i4 - i6) - 1] = (i5 - i7) - 1;
                iArr[i4 + i6] = i7 + i5 + 1;
            }
        }
        int i8 = 0;
        int i9 = 0;
        while (i8 < nbLayers) {
            int i10 = ((nbLayers - i8) << i2) + (isCompact ? 9 : 12);
            int i11 = i8 << 1;
            int i12 = (i - 1) - i11;
            int i13 = 0;
            while (i13 < i10) {
                int i14 = i13 << 1;
                int i15 = 0;
                while (i15 < i2) {
                    int i16 = i11 + i15;
                    int i17 = i11 + i13;
                    zArr[i9 + i14 + i15] = bitMatrix.get(iArr[i16], iArr[i17]);
                    int i18 = iArr[i17];
                    int i19 = i12 - i15;
                    boolean z = isCompact;
                    zArr[(i10 * 2) + i9 + i14 + i15] = bitMatrix.get(i18, iArr[i19]);
                    int i20 = i12 - i13;
                    zArr[(i10 * 4) + i9 + i14 + i15] = bitMatrix.get(iArr[i19], iArr[i20]);
                    zArr[(i10 * 6) + i9 + i14 + i15] = bitMatrix.get(iArr[i20], iArr[i16]);
                    i15++;
                    nbLayers = nbLayers;
                    isCompact = z;
                    i2 = 2;
                }
                i13++;
                i2 = 2;
            }
            i9 += i10 << 3;
            i8++;
            i2 = 2;
        }
        return zArr;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.f11769a = aztecDetectorResult;
        boolean[] b2 = b(c(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(a(b2), e(b2), null, null);
        decoderResult.setNumBits(b2.length);
        return decoderResult;
    }
}
