package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.mappls.sdk.services.account.Region;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes12.dex */
public class DoubleMetaphone implements StringEncoder {
    public static final String[] b = {"GN", "KN", "PN", "WR", "PS"};
    public static final String[] c = {"L", "R", "N", "M", "B", "H", WeatherCriteria.UNIT_FARENHEIT, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, HexStringBuilder.DEFAULT_SEPARATOR};
    public static final String[] d = {"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    public static final String[] e = {"L", ExifInterface.GPS_DIRECTION_TRUE, "K", ExifInterface.LATITUDE_SOUTH, "N", "M", "B", "Z"};

    /* renamed from: a  reason: collision with root package name */
    public int f14352a = 4;

    public static boolean contains(String str, int i, int i2, String... strArr) {
        int i3;
        if (i < 0 || (i3 = i2 + i) > str.length()) {
            return false;
        }
        String substring = str.substring(i, i3);
        for (String str2 : strArr) {
            if (substring.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean A(char c2) {
        return "AEIOUY".indexOf(c2) != -1;
    }

    public final String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim.toUpperCase(Locale.ENGLISH);
    }

    public final boolean b(String str, int i) {
        if (contains(str, i, 4, "CHIA")) {
            return true;
        }
        if (i <= 1) {
            return false;
        }
        int i2 = i - 2;
        if (!A(charAt(str, i2)) && contains(str, i - 1, 3, "ACH")) {
            char charAt = charAt(str, i + 2);
            return !(charAt == 'I' || charAt == 'E') || contains(str, i2, 6, "BACHER", "MACHER");
        }
        return false;
    }

    public final boolean c(String str, int i) {
        if (i != 0) {
            return false;
        }
        int i2 = i + 1;
        return (contains(str, i2, 5, "HARAC", "HARIS") || contains(str, i2, 3, "HOR", "HYM", "HIA", "HEM")) && !contains(str, 0, 5, "CHORE");
    }

    public char charAt(String str, int i) {
        if (i < 0 || i >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i);
    }

    public final boolean d(String str, int i) {
        if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, i - 2, 6, "ORCHES", "ARCHIT", "ORCHID")) {
            int i2 = i + 2;
            if (!contains(str, i2, 1, ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.LATITUDE_SOUTH)) {
                if (!contains(str, i - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O", "U", ExifInterface.LONGITUDE_EAST) && i != 0) {
                    return false;
                }
                if (!contains(str, i2, 1, c) && i + 1 != str.length() - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    public final boolean e(String str, int i) {
        if (i == str.length() - 3 && contains(str, i - 1, 4, "ILLO", "ILLA", "ALLE")) {
            return true;
        }
        return (contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O")) && contains(str, i - 1, 4, "ALLE");
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return doubleMetaphone((String) obj);
        }
        throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
    }

    public final boolean f(String str, int i) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'M') {
            return true;
        }
        return contains(str, i + (-1), 3, "UMB") && (i2 == str.length() - 1 || contains(str, i + 2, 2, "ER"));
    }

    public final int g(DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('A');
        }
        return i + 1;
    }

    public int getMaxCodeLen() {
        return this.f14352a;
    }

    public final int h(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (b(str, i)) {
            doubleMetaphoneResult.append('K');
        } else if (i == 0 && contains(str, i, 6, "CAESAR")) {
            doubleMetaphoneResult.append('S');
        } else if (contains(str, i, 2, "CH")) {
            return j(str, doubleMetaphoneResult, i);
        } else {
            if (contains(str, i, 2, "CZ") && !contains(str, i - 2, 4, "WICZ")) {
                doubleMetaphoneResult.append('S', 'X');
            } else {
                int i2 = i + 1;
                if (contains(str, i2, 3, "CIA")) {
                    doubleMetaphoneResult.append('X');
                } else if (contains(str, i, 2, "CC") && (i != 1 || charAt(str, 0) != 'M')) {
                    return i(str, doubleMetaphoneResult, i);
                } else {
                    if (contains(str, i, 2, "CK", "CG", "CQ")) {
                        doubleMetaphoneResult.append('K');
                    } else if (contains(str, i, 2, "CI", "CE", "CY")) {
                        if (contains(str, i, 3, "CIO", "CIE", "CIA")) {
                            doubleMetaphoneResult.append('S', 'X');
                        } else {
                            doubleMetaphoneResult.append('S');
                        }
                    } else {
                        doubleMetaphoneResult.append('K');
                        if (!contains(str, i2, 2, " C", " Q", " G")) {
                            if (!contains(str, i2, 1, WeatherCriteria.UNIT_CELSIUS, "K", "Q") || contains(str, i2, 2, "CE", "CI")) {
                                return i2;
                            }
                        }
                    }
                }
                return i + 3;
            }
        }
        return i + 2;
    }

    public final int i(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 2;
        if (contains(str, i2, 1, "I", ExifInterface.LONGITUDE_EAST, "H") && !contains(str, i2, 2, "HU")) {
            if ((i == 1 && charAt(str, i - 1) == 'A') || contains(str, i - 1, 5, "UCCEE", "UCCES")) {
                doubleMetaphoneResult.append("KS");
            } else {
                doubleMetaphoneResult.append('X');
            }
            return i + 3;
        }
        doubleMetaphoneResult.append('K');
        return i2;
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public final int j(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i > 0 && contains(str, i, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
        } else if (c(str, i)) {
            doubleMetaphoneResult.append('K');
        } else if (!d(str, i)) {
            if (i > 0) {
                if (contains(str, 0, 2, "MC")) {
                    doubleMetaphoneResult.append('K');
                } else {
                    doubleMetaphoneResult.append('X', 'K');
                }
            } else {
                doubleMetaphoneResult.append('X');
            }
            return i + 2;
        } else {
            doubleMetaphoneResult.append('K');
        }
        return i + 2;
    }

    public final int k(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 2, "DG")) {
            int i2 = i + 2;
            if (contains(str, i2, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
                doubleMetaphoneResult.append('J');
                return i + 3;
            }
            doubleMetaphoneResult.append("TK");
            return i2;
        } else if (contains(str, i, 2, "DT", "DD")) {
            doubleMetaphoneResult.append('T');
            return i + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return i + 1;
        }
    }

    public final int l(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'H') {
            return m(str, doubleMetaphoneResult, i);
        }
        if (charAt(str, i2) == 'N') {
            if (i == 1 && A(charAt(str, 0)) && !z) {
                doubleMetaphoneResult.append("KN", "N");
            } else if (!contains(str, i + 2, 2, "EY") && charAt(str, i2) != 'Y' && !z) {
                doubleMetaphoneResult.append("N", "KN");
            } else {
                doubleMetaphoneResult.append("KN");
            }
        } else if (contains(str, i2, 2, "LI") && !z) {
            doubleMetaphoneResult.append("KL", "L");
        } else if (i == 0 && (charAt(str, i2) == 'Y' || contains(str, i2, 2, d))) {
            doubleMetaphoneResult.append('K', 'J');
        } else {
            if ((contains(str, i2, 2, "ER") || charAt(str, i2) == 'Y') && !contains(str, 0, 6, "DANGER", "RANGER", "MANGER")) {
                int i3 = i - 1;
                if (!contains(str, i3, 1, ExifInterface.LONGITUDE_EAST, "I") && !contains(str, i3, 3, "RGY", "OGY")) {
                    doubleMetaphoneResult.append('K', 'J');
                    return i + 2;
                }
            }
            if (!contains(str, i2, 1, ExifInterface.LONGITUDE_EAST, "I", "Y") && !contains(str, i - 1, 4, "AGGI", "OGGI")) {
                if (charAt(str, i2) == 'G') {
                    int i4 = i + 2;
                    doubleMetaphoneResult.append('K');
                    return i4;
                }
                doubleMetaphoneResult.append('K');
                return i2;
            }
            if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, i2, 2, "ET")) {
                if (contains(str, i2, 3, "IER")) {
                    doubleMetaphoneResult.append('J');
                } else {
                    doubleMetaphoneResult.append('J', 'K');
                }
            } else {
                doubleMetaphoneResult.append('K');
            }
            return i + 2;
        }
        return i + 2;
    }

    public final int m(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i > 0 && !A(charAt(str, i - 1))) {
            doubleMetaphoneResult.append('K');
        } else if (i == 0) {
            int i2 = i + 2;
            if (charAt(str, i2) == 'I') {
                doubleMetaphoneResult.append('J');
                return i2;
            }
            doubleMetaphoneResult.append('K');
            return i2;
        } else if ((i <= 1 || !contains(str, i - 2, 1, "B", "H", "D")) && ((i <= 2 || !contains(str, i - 3, 1, "B", "H", "D")) && (i <= 3 || !contains(str, i - 4, 1, "B", "H")))) {
            if (i > 2 && charAt(str, i - 1) == 'U' && contains(str, i - 3, 1, WeatherCriteria.UNIT_CELSIUS, "G", "L", "R", ExifInterface.GPS_DIRECTION_TRUE)) {
                doubleMetaphoneResult.append('F');
            } else if (i > 0 && charAt(str, i - 1) != 'I') {
                doubleMetaphoneResult.append('K');
            }
        }
        return i + 2;
    }

    public final int n(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if ((i == 0 || A(charAt(str, i - 1))) && A(charAt(str, i + 1))) {
            doubleMetaphoneResult.append('H');
            return i + 2;
        }
        return i + 1;
    }

    public final int o(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (!contains(str, i, 4, "JOSE") && !contains(str, 0, 4, "SAN ")) {
            if (i == 0 && !contains(str, i, 4, "JOSE")) {
                doubleMetaphoneResult.append('J', 'A');
            } else {
                int i2 = i - 1;
                if (A(charAt(str, i2)) && !z) {
                    int i3 = i + 1;
                    if (charAt(str, i3) == 'A' || charAt(str, i3) == 'O') {
                        doubleMetaphoneResult.append('J', 'H');
                    }
                }
                if (i == str.length() - 1) {
                    doubleMetaphoneResult.append('J', ' ');
                } else if (!contains(str, i + 1, 1, e) && !contains(str, i2, 1, ExifInterface.LATITUDE_SOUTH, "K", "L")) {
                    doubleMetaphoneResult.append('J');
                }
            }
            int i4 = i + 1;
            return charAt(str, i4) == 'J' ? i + 2 : i4;
        }
        if ((i != 0 || charAt(str, i + 4) != ' ') && str.length() != 4 && !contains(str, 0, 4, "SAN ")) {
            doubleMetaphoneResult.append('J', 'H');
        } else {
            doubleMetaphoneResult.append('H');
        }
        return i + 1;
    }

    public final int p(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'L') {
            if (e(str, i)) {
                doubleMetaphoneResult.appendPrimary(Matrix.MATRIX_TYPE_RANDOM_LT);
            } else {
                doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
            }
            return i + 2;
        }
        doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
        return i2;
    }

    public final int q(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'H') {
            doubleMetaphoneResult.append('F');
            return i + 2;
        }
        doubleMetaphoneResult.append('P');
        if (contains(str, i2, 1, "P", "B")) {
            i2 = i + 2;
        }
        return i2;
    }

    public final int r(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (i == str.length() - 1 && !z && contains(str, i - 2, 2, "IE") && !contains(str, i - 4, 2, "ME", "MA")) {
            doubleMetaphoneResult.appendAlternate(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        } else {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        }
        int i2 = i + 1;
        return charAt(str, i2) == 'R' ? i + 2 : i2;
    }

    public final int s(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        if (!contains(str, i - 1, 3, Region.REGION_ICELAND, "YSL")) {
            if (i == 0 && contains(str, i, 5, "SUGAR")) {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                if (contains(str, i, 2, "SH")) {
                    if (contains(str, i + 1, 4, "HEIM", "HOEK", "HOLM", "HOLZ")) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('X');
                    }
                } else if (contains(str, i, 3, "SIO", "SIA") || contains(str, i, 4, "SIAN")) {
                    if (z) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('S', 'X');
                    }
                    return i + 3;
                } else {
                    if (i != 0 || !contains(str, i + 1, 1, "M", "N", "L", ExifInterface.LONGITUDE_WEST)) {
                        int i2 = i + 1;
                        if (!contains(str, i2, 1, "Z")) {
                            if (contains(str, i, 2, "SC")) {
                                return t(str, doubleMetaphoneResult, i);
                            }
                            if (i == str.length() - 1 && contains(str, i - 2, 2, "AI", "OI")) {
                                doubleMetaphoneResult.appendAlternate('S');
                            } else {
                                doubleMetaphoneResult.append('S');
                            }
                            if (!contains(str, i2, 1, ExifInterface.LATITUDE_SOUTH, "Z")) {
                                return i2;
                            }
                        }
                    }
                    doubleMetaphoneResult.append('S', 'X');
                    int i3 = i + 1;
                    if (!contains(str, i3, 1, "Z")) {
                        return i3;
                    }
                }
                return i + 2;
            }
        }
        return i + 1;
    }

    public void setMaxCodeLen(int i) {
        this.f14352a = i;
    }

    public final int t(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = i + 2;
        if (charAt(str, i2) == 'H') {
            int i3 = i + 3;
            if (contains(str, i3, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str, i3, 2, "ER", "EN")) {
                    doubleMetaphoneResult.append("X", "SK");
                } else {
                    doubleMetaphoneResult.append("SK");
                }
            } else if (i == 0 && !A(charAt(str, 3)) && charAt(str, 3) != 'W') {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                doubleMetaphoneResult.append('X');
            }
        } else if (contains(str, i2, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
            doubleMetaphoneResult.append('S');
        } else {
            doubleMetaphoneResult.append("SK");
        }
        return i + 3;
    }

    public final int u(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (contains(str, i, 4, "TION")) {
            doubleMetaphoneResult.append('X');
        } else if (contains(str, i, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
        } else if (!contains(str, i, 2, "TH") && !contains(str, i, 3, "TTH")) {
            doubleMetaphoneResult.append('T');
            int i2 = i + 1;
            return contains(str, i2, 1, ExifInterface.GPS_DIRECTION_TRUE, "D") ? i + 2 : i2;
        } else {
            int i3 = i + 2;
            if (!contains(str, i3, 2, "OM", "AM") && !contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('0', 'T');
                return i3;
            }
            doubleMetaphoneResult.append('T');
            return i3;
        }
        return i + 3;
    }

    public final int v(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        int i2 = 2;
        if (contains(str, i, 2, "WR")) {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        } else {
            if (i == 0) {
                int i3 = i + 1;
                if (A(charAt(str, i3)) || contains(str, i, 2, "WH")) {
                    if (A(charAt(str, i3))) {
                        doubleMetaphoneResult.append('A', 'F');
                    } else {
                        doubleMetaphoneResult.append('A');
                    }
                    return i3;
                }
            }
            if ((i != str.length() - 1 || !A(charAt(str, i - 1))) && !contains(str, i - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") && !contains(str, 0, 3, "SCH")) {
                i2 = 4;
                if (contains(str, i, 4, "WICZ", "WITZ")) {
                    doubleMetaphoneResult.append("TS", "FX");
                }
            } else {
                doubleMetaphoneResult.appendAlternate('F');
            }
            return i + 1;
        }
        return i + i2;
    }

    public final int w(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i) {
        if (i == 0) {
            doubleMetaphoneResult.append('S');
            return i + 1;
        }
        if (i != str.length() - 1 || (!contains(str, i - 3, 3, "IAU", "EAU") && !contains(str, i - 2, 2, "AU", "OU"))) {
            doubleMetaphoneResult.append("KS");
        }
        int i2 = i + 1;
        return contains(str, i2, 1, WeatherCriteria.UNIT_CELSIUS, "X") ? i + 2 : i2;
    }

    public final int x(String str, DoubleMetaphoneResult doubleMetaphoneResult, int i, boolean z) {
        int i2 = i + 1;
        if (charAt(str, i2) == 'H') {
            doubleMetaphoneResult.append('J');
            return i + 2;
        }
        if (!contains(str, i2, 2, "ZO", "ZI", "ZA") && (!z || i <= 0 || charAt(str, i - 1) == 'T')) {
            doubleMetaphoneResult.append('S');
        } else {
            doubleMetaphoneResult.append(ExifInterface.LATITUDE_SOUTH, "TS");
        }
        if (charAt(str, i2) == 'Z') {
            i2 = i + 2;
        }
        return i2;
    }

    public final boolean y(String str) {
        for (String str2 : b) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean z(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    /* loaded from: classes12.dex */
    public class DoubleMetaphoneResult {

        /* renamed from: a  reason: collision with root package name */
        public final StringBuilder f14353a;
        public final StringBuilder b;
        public final int c;

        public DoubleMetaphoneResult(int i) {
            this.f14353a = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.b = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.c = i;
        }

        public void append(char c) {
            appendPrimary(c);
            appendAlternate(c);
        }

        public void appendAlternate(char c) {
            if (this.b.length() < this.c) {
                this.b.append(c);
            }
        }

        public void appendPrimary(char c) {
            if (this.f14353a.length() < this.c) {
                this.f14353a.append(c);
            }
        }

        public String getAlternate() {
            return this.b.toString();
        }

        public String getPrimary() {
            return this.f14353a.toString();
        }

        public boolean isComplete() {
            return this.f14353a.length() >= this.c && this.b.length() >= this.c;
        }

        public void append(char c, char c2) {
            appendPrimary(c);
            appendAlternate(c2);
        }

        public void appendAlternate(String str) {
            int length = this.c - this.b.length();
            if (str.length() <= length) {
                this.b.append(str);
            } else {
                this.b.append(str.substring(0, length));
            }
        }

        public void appendPrimary(String str) {
            int length = this.c - this.f14353a.length();
            if (str.length() <= length) {
                this.f14353a.append(str);
            } else {
                this.f14353a.append(str.substring(0, length));
            }
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.apache.commons.codec.language.DoubleMetaphone] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    public String doubleMetaphone(String str, boolean z) {
        int i;
        ?? a2 = a(str);
        if (a2 == 0) {
            return null;
        }
        boolean z2 = z(a2);
        ?? y = y(a2);
        DoubleMetaphoneResult doubleMetaphoneResult = new DoubleMetaphoneResult(getMaxCodeLen());
        while (!doubleMetaphoneResult.isComplete() && y <= a2.length() - 1) {
            char charAt = a2.charAt(y);
            if (charAt == 199) {
                doubleMetaphoneResult.append('S');
            } else if (charAt != 209) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                    case 'Y':
                        y = g(doubleMetaphoneResult, y);
                        break;
                    case 'B':
                        doubleMetaphoneResult.append('P');
                        i = y + 1;
                        if (charAt(a2, i) != 'B') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'C':
                        y = h(a2, doubleMetaphoneResult, y);
                        break;
                    case 'D':
                        y = k(a2, doubleMetaphoneResult, y);
                        break;
                    case 'F':
                        doubleMetaphoneResult.append('F');
                        i = y + 1;
                        if (charAt(a2, i) != 'F') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'G':
                        y = l(a2, doubleMetaphoneResult, y, z2);
                        break;
                    case 'H':
                        y = n(a2, doubleMetaphoneResult, y);
                        break;
                    case 'J':
                        y = o(a2, doubleMetaphoneResult, y, z2);
                        break;
                    case 'K':
                        doubleMetaphoneResult.append('K');
                        i = y + 1;
                        if (charAt(a2, i) != 'K') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'L':
                        y = p(a2, doubleMetaphoneResult, y);
                        break;
                    case 'M':
                        doubleMetaphoneResult.append('M');
                        if (!f(a2, y)) {
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'N':
                        doubleMetaphoneResult.append('N');
                        i = y + 1;
                        if (charAt(a2, i) != 'N') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'P':
                        y = q(a2, doubleMetaphoneResult, y);
                        break;
                    case 'Q':
                        doubleMetaphoneResult.append('K');
                        i = y + 1;
                        if (charAt(a2, i) != 'Q') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'R':
                        y = r(a2, doubleMetaphoneResult, y, z2);
                        break;
                    case 'S':
                        y = s(a2, doubleMetaphoneResult, y, z2);
                        break;
                    case 'T':
                        y = u(a2, doubleMetaphoneResult, y);
                        break;
                    case 'V':
                        doubleMetaphoneResult.append('F');
                        i = y + 1;
                        if (charAt(a2, i) != 'V') {
                            y = i;
                            break;
                        } else {
                            y += 2;
                            break;
                        }
                    case 'W':
                        y = v(a2, doubleMetaphoneResult, y);
                        break;
                    case 'X':
                        y = w(a2, doubleMetaphoneResult, y);
                        break;
                    case 'Z':
                        y = x(a2, doubleMetaphoneResult, y, z2);
                        break;
                }
            } else {
                doubleMetaphoneResult.append('N');
            }
            y++;
        }
        return z ? doubleMetaphoneResult.getAlternate() : doubleMetaphoneResult.getPrimary();
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return StringUtils.equals(doubleMetaphone(str, z), doubleMetaphone(str2, z));
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return doubleMetaphone(str);
    }
}
