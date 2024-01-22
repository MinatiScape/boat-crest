package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.jieli.jl_rcsp.constant.Command;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes11.dex */
public final class Code128Writer extends OneDimensionalCodeWriter {

    /* loaded from: classes11.dex */
    public enum a {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    public static int b(CharSequence charSequence, int i, int i2) {
        a c;
        a c2;
        char charAt;
        a c3 = c(charSequence, i);
        a aVar = a.ONE_DIGIT;
        if (c3 == aVar) {
            return i2 == 101 ? 101 : 100;
        }
        a aVar2 = a.UNCODABLE;
        if (c3 == aVar2) {
            return (i >= charSequence.length() || ((charAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || (charAt >= '`' && (charAt < 241 || charAt > 244))))) ? 100 : 101;
        } else if (i2 == 101 && c3 == a.FNC_1) {
            return 101;
        } else {
            if (i2 == 99) {
                return 99;
            }
            if (i2 == 100) {
                a aVar3 = a.FNC_1;
                if (c3 == aVar3 || (c = c(charSequence, i + 2)) == aVar2 || c == aVar) {
                    return 100;
                }
                if (c == aVar3) {
                    return c(charSequence, i + 3) == a.TWO_DIGITS ? 99 : 100;
                }
                int i3 = i + 4;
                while (true) {
                    c2 = c(charSequence, i3);
                    if (c2 != a.TWO_DIGITS) {
                        break;
                    }
                    i3 += 2;
                }
                return c2 == a.ONE_DIGIT ? 100 : 99;
            }
            if (c3 == a.FNC_1) {
                c3 = c(charSequence, i + 1);
            }
            return c3 == a.TWO_DIGITS ? 99 : 100;
        }
    }

    public static a c(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return a.UNCODABLE;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == 241) {
            return a.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return a.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return a.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i2);
        if (charAt2 >= '0' && charAt2 <= '9') {
            return a.TWO_DIGITS;
        }
        return a.ONE_DIGIT;
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public boolean[] encode(String str) {
        int length = str.length();
        if (length > 0 && length <= 80) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                switch (charAt) {
                    case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                    case 242:
                    case 243:
                    case 244:
                        break;
                    default:
                        if (charAt <= 127) {
                            break;
                        } else {
                            throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(charAt)));
                        }
                }
            }
            ArrayList<int[]> arrayList = new ArrayList();
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 1;
            while (true) {
                int i7 = 103;
                if (i3 < length) {
                    int b = b(str, i3, i5);
                    int i8 = 100;
                    if (b == i5) {
                        switch (str.charAt(i3)) {
                            case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                                i8 = 102;
                                break;
                            case 242:
                                i8 = 97;
                                break;
                            case 243:
                                i8 = 96;
                                break;
                            case 244:
                                if (i5 == 101) {
                                    i8 = 101;
                                    break;
                                }
                                break;
                            default:
                                if (i5 != 100) {
                                    if (i5 == 101) {
                                        i8 = str.charAt(i3) - ' ';
                                        if (i8 < 0) {
                                            i8 += 96;
                                            break;
                                        }
                                    } else {
                                        i8 = Integer.parseInt(str.substring(i3, i3 + 2));
                                        i3++;
                                        break;
                                    }
                                } else {
                                    i8 = str.charAt(i3) - ' ';
                                    break;
                                }
                                break;
                        }
                        i3++;
                    } else {
                        if (i5 != 0) {
                            i7 = b;
                        } else if (b == 100) {
                            i7 = 104;
                        } else if (b != 101) {
                            i7 = 105;
                        }
                        i8 = i7;
                        i5 = b;
                    }
                    arrayList.add(Code128Reader.f11819a[i8]);
                    i4 += i8 * i6;
                    if (i3 != 0) {
                        i6++;
                    }
                } else {
                    int[][] iArr = Code128Reader.f11819a;
                    arrayList.add(iArr[i4 % 103]);
                    arrayList.add(iArr[106]);
                    int i9 = 0;
                    for (int[] iArr2 : arrayList) {
                        for (int i10 : iArr2) {
                            i9 += i10;
                        }
                    }
                    boolean[] zArr = new boolean[i9];
                    for (int[] iArr3 : arrayList) {
                        i += OneDimensionalCodeWriter.appendPattern(zArr, i, iArr3, true);
                    }
                    return zArr;
                }
            }
        } else {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
    }

    @Override // com.google.zxing.oned.OneDimensionalCodeWriter
    public Collection<BarcodeFormat> getSupportedWriteFormats() {
        return Collections.singleton(BarcodeFormat.CODE_128);
    }
}
