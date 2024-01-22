package org.apache.commons.codec.language;

import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.regex.Pattern;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class Nysiis implements StringEncoder {
    public static final char[] b = {'A'};
    public static final char[] c = {'A', 'F'};
    public static final char[] d = {'C'};
    public static final char[] e = {'F', 'F'};
    public static final char[] f = {'G'};
    public static final char[] g = {'N'};
    public static final char[] h = {'N', 'N'};
    public static final char[] i = {'S'};
    public static final char[] j = {'S', 'S', 'S'};
    public static final Pattern k = Pattern.compile("^MAC");
    public static final Pattern l = Pattern.compile("^KN");
    public static final Pattern m = Pattern.compile("^K");
    public static final Pattern n = Pattern.compile("^(PH|PF)");
    public static final Pattern o = Pattern.compile("^SCH");
    public static final Pattern p = Pattern.compile("(EE|IE)$");
    public static final Pattern q = Pattern.compile("(DT|RT|RD|NT|ND)$");

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14356a;

    public Nysiis() {
        this(true);
    }

    public static boolean a(char c2) {
        return c2 == 'A' || c2 == 'E' || c2 == 'I' || c2 == 'O' || c2 == 'U';
    }

    public static char[] b(char c2, char c3, char c4, char c5) {
        if (c3 == 'E' && c4 == 'V') {
            return c;
        }
        if (a(c3)) {
            return b;
        }
        if (c3 == 'Q') {
            return f;
        }
        if (c3 == 'Z') {
            return i;
        }
        if (c3 == 'M') {
            return g;
        }
        if (c3 == 'K') {
            if (c4 == 'N') {
                return h;
            }
            return d;
        } else if (c3 == 'S' && c4 == 'C' && c5 == 'H') {
            return j;
        } else {
            if (c3 == 'P' && c4 == 'H') {
                return e;
            }
            return (c3 != 'H' || (a(c2) && a(c4))) ? (c3 == 'W' && a(c2)) ? new char[]{c2} : new char[]{c3} : new char[]{c2};
        }
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return nysiis((String) obj);
        }
        throw new EncoderException("Parameter supplied to Nysiis encode is not of type java.lang.String");
    }

    public boolean isStrict() {
        return this.f14356a;
    }

    public String nysiis(String str) {
        if (str == null) {
            return null;
        }
        String a2 = a.a(str);
        if (a2.length() == 0) {
            return a2;
        }
        String replaceFirst = q.matcher(p.matcher(o.matcher(n.matcher(m.matcher(l.matcher(k.matcher(a2).replaceFirst("MCC")).replaceFirst("NN")).replaceFirst(WeatherCriteria.UNIT_CELSIUS)).replaceFirst("FF")).replaceFirst("SSS")).replaceFirst("Y")).replaceFirst("D");
        StringBuilder sb = new StringBuilder(replaceFirst.length());
        sb.append(replaceFirst.charAt(0));
        char[] charArray = replaceFirst.toCharArray();
        int length = charArray.length;
        int i2 = 1;
        while (i2 < length) {
            int i3 = i2 - 1;
            char[] b2 = b(charArray[i3], charArray[i2], i2 < length + (-1) ? charArray[i2 + 1] : ' ', i2 < length + (-2) ? charArray[i2 + 2] : ' ');
            System.arraycopy(b2, 0, charArray, i2, b2.length);
            if (charArray[i2] != charArray[i3]) {
                sb.append(charArray[i2]);
            }
            i2++;
        }
        if (sb.length() > 1) {
            char charAt = sb.charAt(sb.length() - 1);
            if (charAt == 'S') {
                sb.deleteCharAt(sb.length() - 1);
                charAt = sb.charAt(sb.length() - 1);
            }
            if (sb.length() > 2 && sb.charAt(sb.length() - 2) == 'A' && charAt == 'Y') {
                sb.deleteCharAt(sb.length() - 2);
            }
            if (charAt == 'A') {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        String sb2 = sb.toString();
        return isStrict() ? sb2.substring(0, Math.min(6, sb2.length())) : sb2;
    }

    public Nysiis(boolean z) {
        this.f14356a = z;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return nysiis(str);
    }
}
