package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.regex.Pattern;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes11.dex */
public final class VINResultParser extends ResultParser {
    public static final Pattern f = Pattern.compile("[IOQ]");
    public static final Pattern g = Pattern.compile("[A-Z0-9]{17}");

    public static char g(int i) {
        if (i < 10) {
            return (char) (i + 48);
        }
        if (i == 10) {
            return 'X';
        }
        throw new IllegalArgumentException();
    }

    public static boolean h(CharSequence charSequence) {
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            int i3 = i + 1;
            i2 += l(i3) * k(charSequence.charAt(i));
            i = i3;
        }
        return charSequence.charAt(8) == g(i2 % 11);
    }

    public static String i(CharSequence charSequence) {
        char charAt = charSequence.charAt(0);
        char charAt2 = charSequence.charAt(1);
        if (charAt == '9') {
            if (charAt2 < 'A' || charAt2 > 'E') {
                if (charAt2 < '3' || charAt2 > '9') {
                    return null;
                }
                return "BR";
            }
            return "BR";
        } else if (charAt == 'S') {
            if (charAt2 < 'A' || charAt2 > 'M') {
                if (charAt2 < 'N' || charAt2 > 'T') {
                    return null;
                }
                return "DE";
            }
            return "UK";
        } else if (charAt == 'Z') {
            if (charAt2 < 'A' || charAt2 > 'R') {
                return null;
            }
            return "IT";
        } else {
            switch (charAt) {
                case '1':
                case '4':
                case '5':
                    return "US";
                case '2':
                    return "CA";
                case '3':
                    if (charAt2 < 'A' || charAt2 > 'W') {
                        return null;
                    }
                    return "MX";
                default:
                    switch (charAt) {
                        case 'J':
                            if (charAt2 < 'A' || charAt2 > 'T') {
                                return null;
                            }
                            return "JP";
                        case 'K':
                            if (charAt2 < 'L' || charAt2 > 'R') {
                                return null;
                            }
                            return "KO";
                        case 'L':
                            return "CN";
                        case 'M':
                            if (charAt2 < 'A' || charAt2 > 'E') {
                                return null;
                            }
                            return "IN";
                        default:
                            switch (charAt) {
                                case 'V':
                                    if (charAt2 < 'F' || charAt2 > 'R') {
                                        if (charAt2 < 'S' || charAt2 > 'W') {
                                            return null;
                                        }
                                        return "ES";
                                    }
                                    return "FR";
                                case 'W':
                                    return "DE";
                                case 'X':
                                    if (charAt2 != '0') {
                                        if (charAt2 < '3' || charAt2 > '9') {
                                            return null;
                                        }
                                        return "RU";
                                    }
                                    return "RU";
                                default:
                                    return null;
                            }
                    }
            }
        }
    }

    public static int j(char c) {
        if (c < 'E' || c > 'H') {
            if (c < 'J' || c > 'N') {
                if (c == 'P') {
                    return 1993;
                }
                if (c < 'R' || c > 'T') {
                    if (c < 'V' || c > 'Y') {
                        if (c < '1' || c > '9') {
                            if (c < 'A' || c > 'D') {
                                throw new IllegalArgumentException();
                            }
                            return (c - 'A') + 2010;
                        }
                        return (c - '1') + 2001;
                    }
                    return (c - 'V') + 1997;
                }
                return (c - Matrix.MATRIX_TYPE_RANDOM_REGULAR) + 1994;
            }
            return (c - 'J') + 1988;
        }
        return (c - 'E') + 1984;
    }

    public static int k(char c) {
        if (c < 'A' || c > 'I') {
            if (c < 'J' || c > 'R') {
                if (c < 'S' || c > 'Z') {
                    if (c < '0' || c > '9') {
                        throw new IllegalArgumentException();
                    }
                    return c - '0';
                }
                return (c - 'S') + 2;
            }
            return (c - 'J') + 1;
        }
        return (c - 'A') + 1;
    }

    public static int l(int i) {
        if (i <= 0 || i > 7) {
            if (i == 8) {
                return 10;
            }
            if (i == 9) {
                return 0;
            }
            if (i < 10 || i > 17) {
                throw new IllegalArgumentException();
            }
            return 19 - i;
        }
        return 9 - i;
    }

    @Override // com.google.zxing.client.result.ResultParser
    public VINParsedResult parse(Result result) {
        if (result.getBarcodeFormat() != BarcodeFormat.CODE_39) {
            return null;
        }
        String trim = f.matcher(result.getText()).replaceAll("").trim();
        if (g.matcher(trim).matches()) {
            try {
                if (h(trim)) {
                    String substring = trim.substring(0, 3);
                    return new VINParsedResult(trim, substring, trim.substring(3, 9), trim.substring(9, 17), i(substring), trim.substring(3, 8), j(trim.charAt(9)), trim.charAt(10), trim.substring(11));
                }
                return null;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        return null;
    }
}
