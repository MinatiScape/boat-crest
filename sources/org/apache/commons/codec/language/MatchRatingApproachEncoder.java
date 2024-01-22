package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class MatchRatingApproachEncoder implements StringEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f14354a = {"BB", "CC", "DD", "FF", "GG", "HH", "JJ", "KK", "LL", "MM", "NN", "PP", "QQ", "RR", "SS", "TT", "VV", "WW", "XX", "YY", "ZZ"};

    public String a(String str) {
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        String[] strArr = {"\\-", "[&]", "\\'", "\\.", "[\\,]"};
        for (int i = 0; i < 5; i++) {
            upperCase = upperCase.replaceAll(strArr[i], "");
        }
        return f(upperCase).replaceAll("\\s+", "");
    }

    public String b(String str) {
        int length = str.length();
        if (length > 6) {
            String substring = str.substring(0, 3);
            String substring2 = str.substring(length - 3, length);
            return substring + substring2;
        }
        return str;
    }

    public int c(int i) {
        if (i <= 4) {
            return 5;
        }
        if (i <= 7) {
            return 4;
        }
        if (i <= 11) {
            return 3;
        }
        return i == 12 ? 2 : 1;
    }

    public boolean d(String str) {
        return str.equalsIgnoreCase(ExifInterface.LONGITUDE_EAST) || str.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS) || str.equalsIgnoreCase("O") || str.equalsIgnoreCase("I") || str.equalsIgnoreCase("U");
    }

    public int e(String str, String str2) {
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        int length = str.length() - 1;
        int length2 = str2.length() - 1;
        int i = 0;
        while (i < charArray.length && i <= length2) {
            int i2 = i + 1;
            String substring = str.substring(i, i2);
            int i3 = length - i;
            String substring2 = str.substring(i3, i3 + 1);
            String substring3 = str2.substring(i, i2);
            int i4 = length2 - i;
            String substring4 = str2.substring(i4, i4 + 1);
            if (substring.equals(substring3)) {
                charArray[i] = ' ';
                charArray2[i] = ' ';
            }
            if (substring2.equals(substring4)) {
                charArray[i3] = ' ';
                charArray2[i4] = ' ';
            }
            i = i2;
        }
        String replaceAll = new String(charArray).replaceAll("\\s+", "");
        String replaceAll2 = new String(charArray2).replaceAll("\\s+", "");
        if (replaceAll.length() > replaceAll2.length()) {
            return Math.abs(6 - replaceAll.length());
        }
        return Math.abs(6 - replaceAll2.length());
    }

    @Override // org.apache.commons.codec.Encoder
    public final Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");
    }

    public String f(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int indexOf = "ÀàÈèÌìÒòÙùÁáÉéÍíÓóÚúÝýÂâÊêÎîÔôÛûŶŷÃãÕõÑñÄäËëÏïÖöÜüŸÿÅåÇçŐőŰű".indexOf(charAt);
            if (indexOf > -1) {
                sb.append("AaEeIiOoUuAaEeIiOoUuYyAaEeIiOoUuYyAaOoNnAaEeIiOoUuYyAaCcOoUu".charAt(indexOf));
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public String g(String str) {
        String[] strArr;
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        for (String str2 : f14354a) {
            if (upperCase.contains(str2)) {
                upperCase = upperCase.replace(str2, str2.substring(0, 1));
            }
        }
        return upperCase;
    }

    public String h(String str) {
        String substring = str.substring(0, 1);
        String replaceAll = str.replaceAll(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "").replaceAll(ExifInterface.LONGITUDE_EAST, "").replaceAll("I", "").replaceAll("O", "").replaceAll("U", "").replaceAll("\\s{2,}\\b", HexStringBuilder.DEFAULT_SEPARATOR);
        if (d(substring)) {
            return substring + replaceAll;
        }
        return replaceAll;
    }

    public boolean isEncodeEquals(String str, String str2) {
        if (str == null || "".equalsIgnoreCase(str) || HexStringBuilder.DEFAULT_SEPARATOR.equalsIgnoreCase(str) || str2 == null || "".equalsIgnoreCase(str2) || HexStringBuilder.DEFAULT_SEPARATOR.equalsIgnoreCase(str2) || str.length() == 1 || str2.length() == 1) {
            return false;
        }
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        String a2 = a(str);
        String a3 = a(str2);
        String h = h(a2);
        String h2 = h(a3);
        String g = g(h);
        String g2 = g(h2);
        String b = b(g);
        String b2 = b(g2);
        if (Math.abs(b.length() - b2.length()) >= 3) {
            return false;
        }
        return e(b, b2) >= c(Math.abs(b.length() + b2.length()));
    }

    @Override // org.apache.commons.codec.StringEncoder
    public final String encode(String str) {
        return (str == null || "".equalsIgnoreCase(str) || HexStringBuilder.DEFAULT_SEPARATOR.equalsIgnoreCase(str) || str.length() == 1) ? "" : b(g(h(a(str))));
    }
}
