package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class Metaphone implements StringEncoder {

    /* renamed from: a  reason: collision with root package name */
    public int f14355a = 4;

    public final boolean a(int i, int i2) {
        return i2 + 1 == i;
    }

    public final boolean b(StringBuilder sb, int i, char c) {
        return i >= 0 && i < sb.length() - 1 && sb.charAt(i + 1) == c;
    }

    public final boolean c(StringBuilder sb, int i, char c) {
        return i > 0 && i < sb.length() && sb.charAt(i - 1) == c;
    }

    public final boolean d(StringBuilder sb, int i) {
        return "AEIOU".indexOf(sb.charAt(i)) >= 0;
    }

    public final boolean e(StringBuilder sb, int i, String str) {
        if (i < 0 || (str.length() + i) - 1 >= sb.length()) {
            return false;
        }
        return sb.substring(i, str.length() + i).equals(str);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return metaphone((String) obj);
        }
        throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
    }

    public int getMaxCodeLen() {
        return this.f14355a;
    }

    public boolean isMetaphoneEqual(String str, String str2) {
        return metaphone(str).equals(metaphone(str2));
    }

    public String metaphone(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "";
        }
        if (length == 1) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        char[] charArray = str.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder sb = new StringBuilder(40);
        StringBuilder sb2 = new StringBuilder(10);
        int i = 0;
        char c = charArray[0];
        if (c != 'A') {
            if (c == 'G' || c == 'K' || c == 'P') {
                if (charArray[1] == 'N') {
                    sb.append(charArray, 1, charArray.length - 1);
                } else {
                    sb.append(charArray);
                }
            } else if (c != 'W') {
                if (c != 'X') {
                    sb.append(charArray);
                } else {
                    charArray[0] = 'S';
                    sb.append(charArray);
                }
            } else if (charArray[1] == 'R') {
                sb.append(charArray, 1, charArray.length - 1);
            } else if (charArray[1] == 'H') {
                sb.append(charArray, 1, charArray.length - 1);
                sb.setCharAt(0, 'W');
            } else {
                sb.append(charArray);
            }
        } else if (charArray[1] == 'E') {
            sb.append(charArray, 1, charArray.length - 1);
        } else {
            sb.append(charArray);
        }
        int length2 = sb.length();
        while (sb2.length() < getMaxCodeLen() && i < length2) {
            char charAt = sb.charAt(i);
            if (charAt == 'C' || !c(sb, i, charAt)) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        if (i == 0) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'B':
                        if (!c(sb, i, 'M') || !a(length2, i)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'C':
                        if (!c(sb, i, 'S') || a(length2, i) || "EIY".indexOf(sb.charAt(i + 1)) < 0) {
                            if (e(sb, i, "CIA")) {
                                sb2.append('X');
                                break;
                            } else if (!a(length2, i) && "EIY".indexOf(sb.charAt(i + 1)) >= 0) {
                                sb2.append('S');
                                break;
                            } else if (c(sb, i, 'S') && b(sb, i, 'H')) {
                                sb2.append('K');
                                break;
                            } else if (b(sb, i, 'H')) {
                                if (i == 0 && length2 >= 3 && d(sb, 2)) {
                                    sb2.append('K');
                                    break;
                                } else {
                                    sb2.append('X');
                                    break;
                                }
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'D':
                        if (!a(length2, i + 1) && b(sb, i, 'G')) {
                            int i2 = i + 2;
                            if ("EIY".indexOf(sb.charAt(i2)) >= 0) {
                                sb2.append('J');
                                i = i2;
                                break;
                            }
                        }
                        sb2.append('T');
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'R':
                        sb2.append(charAt);
                        break;
                    case 'G':
                        int i3 = i + 1;
                        if ((!a(length2, i3) || !b(sb, i, 'H')) && ((a(length2, i3) || !b(sb, i, 'H') || d(sb, i + 2)) && (i <= 0 || (!e(sb, i, "GN") && !e(sb, i, "GNED"))))) {
                            boolean c2 = c(sb, i, 'G');
                            if (!a(length2, i) && "EIY".indexOf(sb.charAt(i3)) >= 0 && !c2) {
                                sb2.append('J');
                                break;
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'H':
                        if (!a(length2, i) && ((i <= 0 || "CSPTG".indexOf(sb.charAt(i - 1)) < 0) && d(sb, i + 1))) {
                            sb2.append('H');
                            break;
                        }
                        break;
                    case 'K':
                        if (i > 0) {
                            if (!c(sb, i, 'C')) {
                                sb2.append(charAt);
                                break;
                            }
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'P':
                        if (b(sb, i, 'H')) {
                            sb2.append('F');
                            break;
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                    case 'Q':
                        sb2.append('K');
                        break;
                    case 'S':
                        if (!e(sb, i, "SH") && !e(sb, i, "SIO") && !e(sb, i, "SIA")) {
                            sb2.append('S');
                            break;
                        } else {
                            sb2.append('X');
                            break;
                        }
                        break;
                    case 'T':
                        if (!e(sb, i, "TIA") && !e(sb, i, "TIO")) {
                            if (!e(sb, i, "TCH")) {
                                if (e(sb, i, "TH")) {
                                    sb2.append('0');
                                    break;
                                } else {
                                    sb2.append('T');
                                    break;
                                }
                            }
                        } else {
                            sb2.append('X');
                            break;
                        }
                        break;
                    case 'V':
                        sb2.append('F');
                        break;
                    case 'W':
                    case 'Y':
                        if (!a(length2, i) && d(sb, i + 1)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'X':
                        sb2.append('K');
                        sb2.append('S');
                        break;
                    case 'Z':
                        sb2.append('S');
                        break;
                }
                i++;
            } else {
                i++;
            }
            if (sb2.length() > getMaxCodeLen()) {
                sb2.setLength(getMaxCodeLen());
            }
        }
        return sb2.toString();
    }

    public void setMaxCodeLen(int i) {
        this.f14355a = i;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return metaphone(str);
    }
}
