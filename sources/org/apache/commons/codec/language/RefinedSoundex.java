package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class RefinedSoundex implements StringEncoder {

    /* renamed from: a  reason: collision with root package name */
    public final char[] f14357a;
    public static final String US_ENGLISH_MAPPING_STRING = "01360240043788015936020505";
    public static final char[] b = US_ENGLISH_MAPPING_STRING.toCharArray();
    public static final RefinedSoundex US_ENGLISH = new RefinedSoundex();

    public RefinedSoundex() {
        this.f14357a = b;
    }

    public char a(char c) {
        if (Character.isLetter(c)) {
            return this.f14357a[Character.toUpperCase(c) - 'A'];
        }
        return (char) 0;
    }

    public int difference(String str, String str2) throws EncoderException {
        return a.b(this, str, str2);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return soundex((String) obj);
        }
        throw new EncoderException("Parameter supplied to RefinedSoundex encode is not of type java.lang.String");
    }

    public String soundex(String str) {
        if (str == null) {
            return null;
        }
        String a2 = a.a(str);
        if (a2.length() == 0) {
            return a2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(a2.charAt(0));
        char c = '*';
        for (int i = 0; i < a2.length(); i++) {
            char a3 = a(a2.charAt(i));
            if (a3 != c) {
                if (a3 != 0) {
                    sb.append(a3);
                }
                c = a3;
            }
        }
        return sb.toString();
    }

    public RefinedSoundex(char[] cArr) {
        char[] cArr2 = new char[cArr.length];
        this.f14357a = cArr2;
        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return soundex(str);
    }

    public RefinedSoundex(String str) {
        this.f14357a = str.toCharArray();
    }
}
