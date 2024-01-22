package androidx.core.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;
import kotlin.text.Typography;
/* loaded from: classes.dex */
public final class BidiFormatter {
    public static final TextDirectionHeuristicCompat d;
    public static final String e;
    public static final String f;
    public static final BidiFormatter g;
    public static final BidiFormatter h;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f1091a;
    public final int b;
    public final TextDirectionHeuristicCompat c;

    /* loaded from: classes.dex */
    public static class a {
        public static final byte[] f = new byte[1792];

        /* renamed from: a  reason: collision with root package name */
        public final CharSequence f1093a;
        public final boolean b;
        public final int c;
        public int d;
        public char e;

        static {
            for (int i = 0; i < 1792; i++) {
                f[i] = Character.getDirectionality(i);
            }
        }

        public a(CharSequence charSequence, boolean z) {
            this.f1093a = charSequence;
            this.b = z;
            this.c = charSequence.length();
        }

        public static byte c(char c) {
            return c < 1792 ? f[c] : Character.getDirectionality(c);
        }

        public byte a() {
            char charAt = this.f1093a.charAt(this.d - 1);
            this.e = charAt;
            if (Character.isLowSurrogate(charAt)) {
                int codePointBefore = Character.codePointBefore(this.f1093a, this.d);
                this.d -= Character.charCount(codePointBefore);
                return Character.getDirectionality(codePointBefore);
            }
            this.d--;
            byte c = c(this.e);
            if (this.b) {
                char c2 = this.e;
                if (c2 == '>') {
                    return h();
                }
                return c2 == ';' ? f() : c;
            }
            return c;
        }

        public byte b() {
            char charAt = this.f1093a.charAt(this.d);
            this.e = charAt;
            if (Character.isHighSurrogate(charAt)) {
                int codePointAt = Character.codePointAt(this.f1093a, this.d);
                this.d += Character.charCount(codePointAt);
                return Character.getDirectionality(codePointAt);
            }
            this.d++;
            byte c = c(this.e);
            if (this.b) {
                char c2 = this.e;
                if (c2 == '<') {
                    return i();
                }
                return c2 == '&' ? g() : c;
            }
            return c;
        }

        public int d() {
            this.d = 0;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (this.d < this.c && i == 0) {
                byte b = b();
                if (b != 0) {
                    if (b == 1 || b == 2) {
                        if (i3 == 0) {
                            return 1;
                        }
                    } else if (b != 9) {
                        switch (b) {
                            case 14:
                            case 15:
                                i3++;
                                i2 = -1;
                                break;
                            case 16:
                            case 17:
                                i3++;
                                i2 = 1;
                                break;
                            case 18:
                                i3--;
                                i2 = 0;
                                break;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
                i = i3;
            }
            if (i == 0) {
                return 0;
            }
            if (i2 != 0) {
                return i2;
            }
            while (this.d > 0) {
                switch (a()) {
                    case 14:
                    case 15:
                        if (i == i3) {
                            return -1;
                        }
                        break;
                    case 16:
                    case 17:
                        if (i == i3) {
                            return 1;
                        }
                        break;
                    case 18:
                        i3++;
                        continue;
                }
                i3--;
            }
            return 0;
        }

        public int e() {
            this.d = this.c;
            int i = 0;
            while (true) {
                int i2 = i;
                while (this.d > 0) {
                    byte a2 = a();
                    if (a2 != 0) {
                        if (a2 == 1 || a2 == 2) {
                            if (i == 0) {
                                return 1;
                            }
                            if (i2 == 0) {
                                break;
                            }
                        } else if (a2 != 9) {
                            switch (a2) {
                                case 14:
                                case 15:
                                    if (i2 == i) {
                                        return -1;
                                    }
                                    i--;
                                    break;
                                case 16:
                                case 17:
                                    if (i2 == i) {
                                        return 1;
                                    }
                                    i--;
                                    break;
                                case 18:
                                    i++;
                                    break;
                                default:
                                    if (i2 != 0) {
                                        break;
                                    } else {
                                        break;
                                    }
                            }
                        } else {
                            continue;
                        }
                    } else if (i == 0) {
                        return -1;
                    } else {
                        if (i2 == 0) {
                            break;
                        }
                    }
                }
                return 0;
            }
        }

        public final byte f() {
            char charAt;
            int i = this.d;
            do {
                int i2 = this.d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f1093a;
                int i3 = i2 - 1;
                this.d = i3;
                charAt = charSequence.charAt(i3);
                this.e = charAt;
                if (charAt == '&') {
                    return (byte) 12;
                }
            } while (charAt != ';');
            this.d = i;
            this.e = ';';
            return (byte) 13;
        }

        public final byte g() {
            char charAt;
            do {
                int i = this.d;
                if (i >= this.c) {
                    return (byte) 12;
                }
                CharSequence charSequence = this.f1093a;
                this.d = i + 1;
                charAt = charSequence.charAt(i);
                this.e = charAt;
            } while (charAt != ';');
            return (byte) 12;
        }

        public final byte h() {
            char charAt;
            int i = this.d;
            while (true) {
                int i2 = this.d;
                if (i2 <= 0) {
                    break;
                }
                CharSequence charSequence = this.f1093a;
                int i3 = i2 - 1;
                this.d = i3;
                char charAt2 = charSequence.charAt(i3);
                this.e = charAt2;
                if (charAt2 == '<') {
                    return (byte) 12;
                }
                if (charAt2 == '>') {
                    break;
                } else if (charAt2 == '\"' || charAt2 == '\'') {
                    do {
                        int i4 = this.d;
                        if (i4 > 0) {
                            CharSequence charSequence2 = this.f1093a;
                            int i5 = i4 - 1;
                            this.d = i5;
                            charAt = charSequence2.charAt(i5);
                            this.e = charAt;
                        }
                    } while (charAt != charAt2);
                }
            }
            this.d = i;
            this.e = Typography.greater;
            return (byte) 13;
        }

        public final byte i() {
            char charAt;
            int i = this.d;
            while (true) {
                int i2 = this.d;
                if (i2 < this.c) {
                    CharSequence charSequence = this.f1093a;
                    this.d = i2 + 1;
                    char charAt2 = charSequence.charAt(i2);
                    this.e = charAt2;
                    if (charAt2 == '>') {
                        return (byte) 12;
                    }
                    if (charAt2 == '\"' || charAt2 == '\'') {
                        do {
                            int i3 = this.d;
                            if (i3 < this.c) {
                                CharSequence charSequence2 = this.f1093a;
                                this.d = i3 + 1;
                                charAt = charSequence2.charAt(i3);
                                this.e = charAt;
                            }
                        } while (charAt != charAt2);
                    }
                } else {
                    this.d = i;
                    this.e = Typography.less;
                    return (byte) 13;
                }
            }
        }
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        d = textDirectionHeuristicCompat;
        e = Character.toString((char) 8206);
        f = Character.toString((char) 8207);
        g = new BidiFormatter(false, 2, textDirectionHeuristicCompat);
        h = new BidiFormatter(true, 2, textDirectionHeuristicCompat);
    }

    public BidiFormatter(boolean z, int i, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        this.f1091a = z;
        this.b = i;
        this.c = textDirectionHeuristicCompat;
    }

    public static int a(CharSequence charSequence) {
        return new a(charSequence, false).d();
    }

    public static int b(CharSequence charSequence) {
        return new a(charSequence, false).e();
    }

    public static boolean c(Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public final String d(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (this.f1091a || !(isRtl || b(charSequence) == 1)) {
            return this.f1091a ? (!isRtl || b(charSequence) == -1) ? f : "" : "";
        }
        return e;
    }

    public final String e(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        if (this.f1091a || !(isRtl || a(charSequence) == 1)) {
            return this.f1091a ? (!isRtl || a(charSequence) == -1) ? f : "" : "";
        }
        return e;
    }

    public boolean getStereoReset() {
        return (this.b & 2) != 0;
    }

    public boolean isRtl(String str) {
        return isRtl((CharSequence) str);
    }

    public boolean isRtlContext() {
        return this.f1091a;
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (str == null) {
            return null;
        }
        return unicodeWrap((CharSequence) str, textDirectionHeuristicCompat, z).toString();
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f1092a;
        public int b;
        public TextDirectionHeuristicCompat c;

        public Builder() {
            b(BidiFormatter.c(Locale.getDefault()));
        }

        public static BidiFormatter a(boolean z) {
            return z ? BidiFormatter.h : BidiFormatter.g;
        }

        public final void b(boolean z) {
            this.f1092a = z;
            this.c = BidiFormatter.d;
            this.b = 2;
        }

        public BidiFormatter build() {
            if (this.b == 2 && this.c == BidiFormatter.d) {
                return a(this.f1092a);
            }
            return new BidiFormatter(this.f1092a, this.b, this.c);
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
            this.c = textDirectionHeuristicCompat;
            return this;
        }

        public Builder stereoReset(boolean z) {
            if (z) {
                this.b |= 2;
            } else {
                this.b &= -3;
            }
            return this;
        }

        public Builder(boolean z) {
            b(z);
        }

        public Builder(Locale locale) {
            b(BidiFormatter.c(locale));
        }
    }

    public static BidiFormatter getInstance(boolean z) {
        return new Builder(z).build();
    }

    public boolean isRtl(CharSequence charSequence) {
        return this.c.isRtl(charSequence, 0, charSequence.length());
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat, boolean z) {
        if (charSequence == null) {
            return null;
        }
        boolean isRtl = textDirectionHeuristicCompat.isRtl(charSequence, 0, charSequence.length());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (getStereoReset() && z) {
            spannableStringBuilder.append((CharSequence) e(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        if (isRtl != this.f1091a) {
            spannableStringBuilder.append(isRtl ? (char) 8235 : (char) 8234);
            spannableStringBuilder.append(charSequence);
            spannableStringBuilder.append((char) 8236);
        } else {
            spannableStringBuilder.append(charSequence);
        }
        if (z) {
            spannableStringBuilder.append((CharSequence) d(charSequence, isRtl ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR));
        }
        return spannableStringBuilder;
    }

    public static BidiFormatter getInstance(Locale locale) {
        return new Builder(locale).build();
    }

    public String unicodeWrap(String str, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(str, textDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return unicodeWrap(charSequence, textDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String str, boolean z) {
        return unicodeWrap(str, this.c, z);
    }

    public CharSequence unicodeWrap(CharSequence charSequence, boolean z) {
        return unicodeWrap(charSequence, this.c, z);
    }

    public String unicodeWrap(String str) {
        return unicodeWrap(str, this.c, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence) {
        return unicodeWrap(charSequence, this.c, true);
    }
}
