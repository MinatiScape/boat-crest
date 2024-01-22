package com.google.i18n.phonenumbers;

import com.clevertap.android.sdk.Constants;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import java.lang.Character;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Marker;
/* loaded from: classes10.dex */
public final class b implements Iterator<PhoneNumberMatch> {
    public static final Pattern p;
    public static final Pattern u;
    public static final Pattern w;
    public final PhoneNumberUtil h;
    public final CharSequence i;
    public final String j;
    public final PhoneNumberUtil.Leniency k;
    public long l;
    public EnumC0546b m = EnumC0546b.NOT_READY;
    public PhoneNumberMatch n = null;
    public int o = 0;
    public static final Pattern q = Pattern.compile("\\d{1,5}-+\\d{1,5}\\s{0,4}\\(\\d{1,4}");
    public static final Pattern r = Pattern.compile("(?:(?:[0-3]?\\d/[01]?\\d)|(?:[01]?\\d/[0-3]?\\d))/(?:[12]\\d)?\\d{2}");
    public static final Pattern s = Pattern.compile("[12]\\d{3}[-/]?[01]\\d[-/]?[0-3]\\d +[0-2]\\d$");
    public static final Pattern t = Pattern.compile(":[0-5]\\d");
    public static final Pattern[] v = {Pattern.compile("/+(.*)"), Pattern.compile("(\\([^(]*)"), Pattern.compile("(?:\\p{Z}-|-\\p{Z})\\p{Z}*(.+)"), Pattern.compile("[‒-―－]\\p{Z}*(.+)"), Pattern.compile("\\.+\\p{Z}*([^.]+)"), Pattern.compile("\\p{Z}+(\\P{Z}+)")};

    /* loaded from: classes10.dex */
    public interface a {
        boolean a(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr);
    }

    /* renamed from: com.google.i18n.phonenumbers.b$b  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public enum EnumC0546b {
        NOT_READY,
        READY,
        DONE
    }

    static {
        StringBuilder sb = new StringBuilder("(\\[（［".length() + 3 + ")\\]）］".length());
        sb.append("[^");
        sb.append("(\\[（［");
        sb.append(")\\]）］");
        sb.append("]");
        String sb2 = sb.toString();
        String m = m(0, 3);
        String valueOf = String.valueOf(sb2);
        String valueOf2 = String.valueOf(sb2);
        String valueOf3 = String.valueOf(sb2);
        String valueOf4 = String.valueOf(m);
        String valueOf5 = String.valueOf(sb2);
        StringBuilder sb3 = new StringBuilder("(\\[（［".length() + 26 + valueOf.length() + ")\\]）］".length() + valueOf2.length() + "(\\[（［".length() + valueOf3.length() + ")\\]）］".length() + valueOf4.length() + valueOf5.length());
        sb3.append("(?:[");
        sb3.append("(\\[（［");
        sb3.append("])?");
        sb3.append("(?:");
        sb3.append(valueOf);
        sb3.append("+");
        sb3.append("[");
        sb3.append(")\\]）］");
        sb3.append("])?");
        sb3.append(valueOf2);
        sb3.append("+");
        sb3.append("(?:[");
        sb3.append("(\\[（［");
        sb3.append("]");
        sb3.append(valueOf3);
        sb3.append("+[");
        sb3.append(")\\]）］");
        sb3.append("])");
        sb3.append(valueOf4);
        sb3.append(valueOf5);
        sb3.append(Marker.ANY_MARKER);
        u = Pattern.compile(sb3.toString());
        String m2 = m(0, 2);
        String m3 = m(0, 4);
        String m4 = m(0, 20);
        String valueOf6 = String.valueOf(m3);
        String concat = valueOf6.length() != 0 ? "[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]".concat(valueOf6) : new String("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]");
        String valueOf7 = String.valueOf(m(1, 20));
        String concat2 = valueOf7.length() != 0 ? "\\p{Nd}".concat(valueOf7) : new String("\\p{Nd}");
        String valueOf8 = String.valueOf("+＋".length() != 0 ? "(\\[（［".concat("+＋") : new String("(\\[（［"));
        StringBuilder sb4 = new StringBuilder(valueOf8.length() + 2);
        sb4.append("[");
        sb4.append(valueOf8);
        sb4.append("]");
        String sb5 = sb4.toString();
        w = Pattern.compile(sb5);
        String valueOf9 = String.valueOf(sb5);
        String valueOf10 = String.valueOf(concat);
        String valueOf11 = String.valueOf(m2);
        String valueOf12 = String.valueOf(concat2);
        String valueOf13 = String.valueOf(concat);
        String valueOf14 = String.valueOf(concat2);
        String valueOf15 = String.valueOf(m4);
        String valueOf16 = String.valueOf(PhoneNumberUtil.B);
        StringBuilder sb6 = new StringBuilder(valueOf9.length() + 13 + valueOf10.length() + valueOf11.length() + valueOf12.length() + valueOf13.length() + valueOf14.length() + valueOf15.length() + valueOf16.length());
        sb6.append("(?:");
        sb6.append(valueOf9);
        sb6.append(valueOf10);
        sb6.append(")");
        sb6.append(valueOf11);
        sb6.append(valueOf12);
        sb6.append("(?:");
        sb6.append(valueOf13);
        sb6.append(valueOf14);
        sb6.append(")");
        sb6.append(valueOf15);
        sb6.append("(?:");
        sb6.append(valueOf16);
        sb6.append(")?");
        p = Pattern.compile(sb6.toString(), 66);
    }

    public b(PhoneNumberUtil phoneNumberUtil, String str, String str2, PhoneNumberUtil.Leniency leniency, long j) {
        if (phoneNumberUtil == null || leniency == null) {
            throw null;
        }
        if (j >= 0) {
            this.h = phoneNumberUtil;
            this.i = str == null ? "" : str;
            this.j = str2;
            this.k = leniency;
            this.l = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static boolean a(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        String[] split = PhoneNumberUtil.E.split(sb.toString());
        int length = phoneNumber.hasExtension() ? split.length - 2 : split.length - 1;
        if (split.length == 1 || split[length].contains(phoneNumberUtil.getNationalSignificantNumber(phoneNumber))) {
            return true;
        }
        int length2 = strArr.length - 1;
        while (length2 > 0 && length >= 0) {
            if (!split[length].equals(strArr[length2])) {
                return false;
            }
            length2--;
            length--;
        }
        return length >= 0 && split[length].endsWith(strArr[0]);
    }

    public static boolean b(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
        int i;
        if (phoneNumber.getCountryCodeSource() != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            String num = Integer.toString(phoneNumber.getCountryCode());
            i = sb.indexOf(num) + num.length();
        } else {
            i = 0;
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int indexOf = sb.indexOf(strArr[i2], i);
            if (indexOf < 0) {
                return false;
            }
            i = indexOf + strArr[i2].length();
            if (i2 == 0 && i < sb.length() && phoneNumberUtil.getNddPrefixForRegion(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()), true) != null && Character.isDigit(sb.charAt(i))) {
                return sb.substring(i - strArr[i2].length()).startsWith(phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            }
        }
        return sb.substring(i).contains(phoneNumber.getExtension());
    }

    public static boolean c(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil, a aVar) {
        StringBuilder O = PhoneNumberUtil.O(str, true);
        if (aVar.a(phoneNumberUtil, phoneNumber, O, i(phoneNumberUtil, phoneNumber, null))) {
            return true;
        }
        Phonemetadata.PhoneMetadata b = com.google.i18n.phonenumbers.a.b(phoneNumber.getCountryCode());
        if (b != null) {
            for (Phonemetadata.NumberFormat numberFormat : b.numberFormats()) {
                if (aVar.a(phoneNumberUtil, phoneNumber, O, i(phoneNumberUtil, phoneNumber, numberFormat))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean d(Phonenumber.PhoneNumber phoneNumber, String str) {
        int indexOf;
        int indexOf2 = str.indexOf(47);
        if (indexOf2 >= 0 && (indexOf = str.indexOf(47, indexOf2 + 1)) >= 0) {
            if ((phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN || phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN) && PhoneNumberUtil.normalizeDigitsOnly(str.substring(0, indexOf2)).equals(Integer.toString(phoneNumber.getCountryCode()))) {
                return str.substring(indexOf + 1).contains(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            }
            return true;
        }
        return false;
    }

    public static boolean e(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
        int i = 0;
        while (i < str.length() - 1) {
            char charAt = str.charAt(i);
            if (charAt == 'x' || charAt == 'X') {
                int i2 = i + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 != 'x' && charAt2 != 'X') {
                    if (!PhoneNumberUtil.normalizeDigitsOnly(str.substring(i)).equals(phoneNumber.getExtension())) {
                        return false;
                    }
                } else if (phoneNumberUtil.isNumberMatch(phoneNumber, str.substring(i2)) != PhoneNumberUtil.MatchType.NSN_MATCH) {
                    return false;
                } else {
                    i = i2;
                }
            }
            i++;
        }
        return true;
    }

    public static String[] i(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, Phonemetadata.NumberFormat numberFormat) {
        if (numberFormat == null) {
            String format = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
            int indexOf = format.indexOf(59);
            if (indexOf < 0) {
                indexOf = format.length();
            }
            return format.substring(format.indexOf(45) + 1, indexOf).split("-");
        }
        return phoneNumberUtil.j(phoneNumberUtil.getNationalSignificantNumber(phoneNumber), numberFormat, PhoneNumberUtil.PhoneNumberFormat.RFC3966).split("-");
    }

    public static boolean j(char c) {
        return c == '%' || Character.getType(c) == 26;
    }

    public static boolean k(char c) {
        if (Character.isLetter(c) || Character.getType(c) == 6) {
            Character.UnicodeBlock of = Character.UnicodeBlock.of(c);
            return of.equals(Character.UnicodeBlock.BASIC_LATIN) || of.equals(Character.UnicodeBlock.LATIN_1_SUPPLEMENT) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_A) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_ADDITIONAL) || of.equals(Character.UnicodeBlock.LATIN_EXTENDED_B) || of.equals(Character.UnicodeBlock.COMBINING_DIACRITICAL_MARKS);
        }
        return false;
    }

    public static boolean l(Phonenumber.PhoneNumber phoneNumber, PhoneNumberUtil phoneNumberUtil) {
        Phonemetadata.PhoneMetadata o;
        if (phoneNumber.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY && (o = phoneNumberUtil.o(phoneNumberUtil.getRegionCodeForCountryCode(phoneNumber.getCountryCode()))) != null) {
            Phonemetadata.NumberFormat d = phoneNumberUtil.d(o.numberFormats(), phoneNumberUtil.getNationalSignificantNumber(phoneNumber));
            if (d == null || d.getNationalPrefixFormattingRule().length() <= 0 || d.isNationalPrefixOptionalWhenFormatting() || PhoneNumberUtil.l(d.getNationalPrefixFormattingRule())) {
                return true;
            }
            return phoneNumberUtil.K(new StringBuilder(PhoneNumberUtil.normalizeDigitsOnly(phoneNumber.getRawInput())), o, null);
        }
        return true;
    }

    public static String m(int i, int i2) {
        if (i >= 0 && i2 > 0 && i2 >= i) {
            StringBuilder sb = new StringBuilder(25);
            sb.append("{");
            sb.append(i);
            sb.append(Constants.SEPARATOR_COMMA);
            sb.append(i2);
            sb.append("}");
            return sb.toString();
        }
        throw new IllegalArgumentException();
    }

    public static CharSequence p(Pattern pattern, CharSequence charSequence) {
        Matcher matcher = pattern.matcher(charSequence);
        return matcher.find() ? charSequence.subSequence(0, matcher.start()) : charSequence;
    }

    public final PhoneNumberMatch f(String str, int i) {
        for (Pattern pattern : v) {
            Matcher matcher = pattern.matcher(str);
            boolean z = true;
            while (matcher.find() && this.l > 0) {
                if (z) {
                    PhoneNumberMatch o = o(p(PhoneNumberUtil.x, str.substring(0, matcher.start())).toString(), i);
                    if (o != null) {
                        return o;
                    }
                    this.l--;
                    z = false;
                }
                PhoneNumberMatch o2 = o(p(PhoneNumberUtil.x, matcher.group(1)).toString(), matcher.start(1) + i);
                if (o2 != null) {
                    return o2;
                }
                this.l--;
            }
        }
        return null;
    }

    public final PhoneNumberMatch g(CharSequence charSequence, int i) {
        if (r.matcher(charSequence).find()) {
            return null;
        }
        if (s.matcher(charSequence).find()) {
            if (t.matcher(this.i.toString().substring(charSequence.length() + i)).lookingAt()) {
                return null;
            }
        }
        String charSequence2 = charSequence.toString();
        PhoneNumberMatch o = o(charSequence2, i);
        return o != null ? o : f(charSequence2, i);
    }

    public final PhoneNumberMatch h(int i) {
        Matcher matcher = p.matcher(this.i);
        while (this.l > 0 && matcher.find(i)) {
            int start = matcher.start();
            CharSequence p2 = p(PhoneNumberUtil.w, this.i.subSequence(start, matcher.end()));
            PhoneNumberMatch g = g(p2, start);
            if (g != null) {
                return g;
            }
            i = start + p2.length();
            this.l--;
        }
        return null;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.m == EnumC0546b.NOT_READY) {
            PhoneNumberMatch h = h(this.o);
            this.n = h;
            if (h == null) {
                this.m = EnumC0546b.DONE;
            } else {
                this.o = h.end();
                this.m = EnumC0546b.READY;
            }
        }
        return this.m == EnumC0546b.READY;
    }

    @Override // java.util.Iterator
    /* renamed from: n */
    public PhoneNumberMatch next() {
        if (hasNext()) {
            PhoneNumberMatch phoneNumberMatch = this.n;
            this.n = null;
            this.m = EnumC0546b.NOT_READY;
            return phoneNumberMatch;
        }
        throw new NoSuchElementException();
    }

    public final PhoneNumberMatch o(String str, int i) {
        try {
            if (u.matcher(str).matches() && !q.matcher(str).find()) {
                if (this.k.compareTo(PhoneNumberUtil.Leniency.VALID) >= 0) {
                    if (i > 0 && !w.matcher(str).lookingAt()) {
                        char charAt = this.i.charAt(i - 1);
                        if (j(charAt) || k(charAt)) {
                            return null;
                        }
                    }
                    int length = str.length() + i;
                    if (length < this.i.length()) {
                        char charAt2 = this.i.charAt(length);
                        if (j(charAt2) || k(charAt2)) {
                            return null;
                        }
                    }
                }
                Phonenumber.PhoneNumber parseAndKeepRawInput = this.h.parseAndKeepRawInput(str, this.j);
                if ((!this.h.getRegionCodeForCountryCode(parseAndKeepRawInput.getCountryCode()).equals("IL") || this.h.getNationalSignificantNumber(parseAndKeepRawInput).length() != 4 || (i != 0 && (i <= 0 || this.i.charAt(i - 1) == '*'))) && this.k.verify(parseAndKeepRawInput, str, this.h)) {
                    parseAndKeepRawInput.clearCountryCodeSource();
                    parseAndKeepRawInput.clearRawInput();
                    parseAndKeepRawInput.clearPreferredDomesticCarrierCode();
                    return new PhoneNumberMatch(i, str, parseAndKeepRawInput);
                }
            }
        } catch (NumberParseException unused) {
        }
        return null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
