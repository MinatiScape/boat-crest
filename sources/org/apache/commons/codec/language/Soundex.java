package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class Soundex implements StringEncoder {
    public static final char SILENT_MARKER = '-';
    @Deprecated

    /* renamed from: a  reason: collision with root package name */
    public int f14358a;
    public final char[] b;
    public final boolean c;
    public static final String US_ENGLISH_MAPPING_STRING = "01230120022455012623010202";
    public static final char[] d = US_ENGLISH_MAPPING_STRING.toCharArray();
    public static final Soundex US_ENGLISH = new Soundex();
    public static final Soundex US_ENGLISH_SIMPLIFIED = new Soundex(US_ENGLISH_MAPPING_STRING, false);
    public static final Soundex US_ENGLISH_GENEALOGY = new Soundex("-123-12--22455-12623-1-2-2");

    public Soundex() {
        this.f14358a = 4;
        this.b = d;
        this.c = true;
    }

    public final boolean a(char[] cArr) {
        for (char c : cArr) {
            if (c == '-') {
                return true;
            }
        }
        return false;
    }

    public final char b(char c) {
        int i = c - 'A';
        if (i >= 0) {
            char[] cArr = this.b;
            if (i < cArr.length) {
                return cArr[i];
            }
        }
        throw new IllegalArgumentException("The character is not mapped: " + c + " (index=" + i + ")");
    }

    public int difference(String str, String str2) throws EncoderException {
        return a.b(this, str, str2);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return soundex((String) obj);
        }
        throw new EncoderException("Parameter supplied to Soundex encode is not of type java.lang.String");
    }

    @Deprecated
    public int getMaxLength() {
        return this.f14358a;
    }

    @Deprecated
    public void setMaxLength(int i) {
        this.f14358a = i;
    }

    public String soundex(String str) {
        char b;
        if (str == null) {
            return null;
        }
        String a2 = a.a(str);
        if (a2.length() == 0) {
            return a2;
        }
        char[] cArr = {'0', '0', '0', '0'};
        char charAt = a2.charAt(0);
        cArr[0] = charAt;
        char b2 = b(charAt);
        int i = 1;
        for (int i2 = 1; i2 < a2.length() && i < 4; i2++) {
            char charAt2 = a2.charAt(i2);
            if ((!this.c || (charAt2 != 'H' && charAt2 != 'W')) && (b = b(charAt2)) != '-') {
                if (b != '0' && b != b2) {
                    cArr[i] = b;
                    i++;
                }
                b2 = b;
            }
        }
        return new String(cArr);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return soundex(str);
    }

    public Soundex(char[] cArr) {
        this.f14358a = 4;
        char[] cArr2 = new char[cArr.length];
        this.b = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
        this.c = !a(cArr2);
    }

    public Soundex(String str) {
        this.f14358a = 4;
        char[] charArray = str.toCharArray();
        this.b = charArray;
        this.c = !a(charArray);
    }

    public Soundex(String str, boolean z) {
        this.f14358a = 4;
        this.b = str.toCharArray();
        this.c = z;
    }
}
