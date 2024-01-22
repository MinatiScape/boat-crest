package com.google.zxing.oned;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Ascii;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import com.realsil.sdk.dfu.DfuException;
import com.szabh.smable3.entity.BleNewsFeed;
import com.touchgui.sdk.TGEventListener;
import java.util.Arrays;
import java.util.Map;
/* loaded from: classes11.dex */
public final class Code93Reader extends OneDReader {
    public static final char[] c = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    public static final int[] d;
    public static final int e;

    /* renamed from: a  reason: collision with root package name */
    public final StringBuilder f11821a = new StringBuilder(20);
    public final int[] b = new int[6];

    static {
        int[] iArr = {DfuException.ERROR_REQUEST_MTU_NO_CALLBACK, 328, 324, com.veryfit.multi.nativeprotocol.b.g1, 296, TGEventListener.OTA_COMPLETED, TGEventListener.SLEEP_START, com.veryfit.multi.nativeprotocol.b.q1, 274, DfuException.ERROR_WRITE_CHARAC_NOTIFY_ERROR, TypedValues.CycleType.TYPE_WAVE_OFFSET, TypedValues.CycleType.TYPE_EASING, 418, 404, 402, 394, 360, 356, 354, 308, DfuException.ERROR_DFU_SPP_RWS_NOT_READY, 344, com.veryfit.multi.nativeprotocol.b.m1, com.veryfit.multi.nativeprotocol.b.j1, 300, 278, 436, 434, 428, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 406, com.veryfit.multi.nativeprotocol.b.C1, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, TGEventListener.APP_MENU_STYLE_UPDATED, 474, BleNewsFeed.CONTENT_MAX_LENGTH, 306, 350};
        d = iArr;
        e = iArr[47];
    }

    public static void b(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        c(charSequence, length - 2, 20);
        c(charSequence, length - 1, 15);
    }

    public static void c(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != c[i3 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static String d(CharSequence charSequence) throws FormatException {
        int i;
        char c2;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i2 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'a' || charAt > 'd') {
                sb.append(charAt);
            } else if (i2 < length - 1) {
                i2++;
                char charAt2 = charSequence.charAt(i2);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            i = charAt2 - '@';
                            c2 = (char) i;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                        break;
                    case 'b':
                        if (charAt2 >= 'A' && charAt2 <= 'E') {
                            i = charAt2 - '&';
                        } else if (charAt2 >= 'F' && charAt2 <= 'J') {
                            i = charAt2 - 11;
                        } else if (charAt2 >= 'K' && charAt2 <= 'O') {
                            i = charAt2 + 16;
                        } else if (charAt2 < 'P' || charAt2 > 'T') {
                            if (charAt2 != 'U') {
                                if (charAt2 != 'V') {
                                    if (charAt2 != 'W') {
                                        if (charAt2 >= 'X' && charAt2 <= 'Z') {
                                            c2 = Ascii.MAX;
                                            break;
                                        } else {
                                            throw FormatException.getFormatInstance();
                                        }
                                    } else {
                                        c2 = '`';
                                        break;
                                    }
                                } else {
                                    c2 = '@';
                                    break;
                                }
                            }
                            c2 = 0;
                            break;
                        } else {
                            i = charAt2 + '+';
                        }
                        c2 = (char) i;
                        break;
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            i = charAt2 - ' ';
                            c2 = (char) i;
                            break;
                        } else if (charAt2 == 'Z') {
                            c2 = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            i = charAt2 + ' ';
                            c2 = (char) i;
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        c2 = 0;
                        break;
                }
                sb.append(c2);
            } else {
                throw FormatException.getFormatInstance();
            }
            i2++;
        }
        return sb.toString();
    }

    public static char f(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = d;
            if (i2 < iArr.length) {
                if (iArr[i2] == i) {
                    return c[i2];
                }
                i2++;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public static int g(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int round = Math.round((iArr[i4] * 9.0f) / i);
            if (round <= 0 || round > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                for (int i5 = 0; i5 < round; i5++) {
                    i3 = (i3 << 1) | 1;
                }
            } else {
                i3 <<= round;
            }
        }
        return i3;
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] e2;
        int nextSet = bitArray.getNextSet(e(bitArray)[1]);
        int size = bitArray.getSize();
        int[] iArr = this.b;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f11821a;
        sb.setLength(0);
        while (true) {
            OneDReader.recordPattern(bitArray, nextSet, iArr);
            int g = g(iArr);
            if (g >= 0) {
                char f = f(g);
                sb.append(f);
                int i2 = nextSet;
                for (int i3 : iArr) {
                    i2 += i3;
                }
                int nextSet2 = bitArray.getNextSet(i2);
                if (f == '*') {
                    sb.deleteCharAt(sb.length() - 1);
                    int i4 = 0;
                    for (int i5 : iArr) {
                        i4 += i5;
                    }
                    if (nextSet2 != size && bitArray.get(nextSet2)) {
                        if (sb.length() >= 2) {
                            b(sb);
                            sb.setLength(sb.length() - 2);
                            float f2 = i;
                            return new Result(d(sb), null, new ResultPoint[]{new ResultPoint((e2[1] + e2[0]) / 2.0f, f2), new ResultPoint(nextSet + (i4 / 2.0f), f2)}, BarcodeFormat.CODE_93);
                        }
                        throw NotFoundException.getNotFoundInstance();
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
                nextSet = nextSet2;
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    public final int[] e(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Arrays.fill(this.b, 0);
        int[] iArr = this.b;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        int i2 = nextSet;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i != length - 1) {
                    i++;
                } else if (g(iArr) == e) {
                    return new int[]{i2, nextSet};
                } else {
                    i2 += iArr[0] + iArr[1];
                    int i3 = i - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i3);
                    iArr[i3] = 0;
                    iArr[i] = 0;
                    i--;
                }
                iArr[i] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
