package com.google.i18n.phonenumbers;

import com.google.i18n.phonenumbers.Phonemetadata;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes10.dex */
public class AsYouTypeFormatter {
    public String k;
    public Phonemetadata.PhoneMetadata l;
    public Phonemetadata.PhoneMetadata m;
    public static final Phonemetadata.PhoneMetadata w = new Phonemetadata.PhoneMetadata().setInternationalPrefix("NA");
    public static final Pattern x = Pattern.compile("\\[([^\\[\\]])*\\]");
    public static final Pattern y = Pattern.compile("\\d(?=[^,}][^,}])");
    public static final Pattern z = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*(\\$\\d[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]*)+");
    public static final Pattern A = Pattern.compile("[- ]");
    public static final Pattern B = Pattern.compile("\u2008");

    /* renamed from: a  reason: collision with root package name */
    public String f11512a = "";
    public StringBuilder b = new StringBuilder();
    public String c = "";
    public StringBuilder d = new StringBuilder();
    public StringBuilder e = new StringBuilder();
    public boolean f = true;
    public boolean g = false;
    public boolean h = false;
    public boolean i = false;
    public final PhoneNumberUtil j = PhoneNumberUtil.getInstance();
    public int n = 0;
    public int o = 0;
    public int p = 0;
    public StringBuilder q = new StringBuilder();
    public boolean r = false;
    public String s = "";
    public StringBuilder t = new StringBuilder();
    public List<Phonemetadata.NumberFormat> u = new ArrayList();
    public RegexCache v = new RegexCache(64);

    public AsYouTypeFormatter(String str) {
        this.k = str;
        Phonemetadata.PhoneMetadata k = k(str);
        this.m = k;
        this.l = k;
    }

    public final boolean a() {
        if (this.s.length() > 0) {
            this.t.insert(0, this.s);
            this.q.setLength(this.q.lastIndexOf(this.s));
        }
        return !this.s.equals(u());
    }

    public final String b(String str) {
        int length = this.q.length();
        if (this.r && length > 0 && this.q.charAt(length - 1) != ' ') {
            String str2 = new String(this.q);
            String valueOf = String.valueOf(str);
            StringBuilder sb = new StringBuilder(str2.length() + 1 + valueOf.length());
            sb.append(str2);
            sb.append(' ');
            sb.append(valueOf);
            return sb.toString();
        }
        String valueOf2 = String.valueOf(this.q);
        String valueOf3 = String.valueOf(str);
        StringBuilder sb2 = new StringBuilder(valueOf2.length() + 0 + valueOf3.length());
        sb2.append(valueOf2);
        sb2.append(valueOf3);
        return sb2.toString();
    }

    public final String c() {
        if (this.t.length() >= 3) {
            i(this.t.toString());
            String g = g();
            return g.length() > 0 ? g : r() ? l() : this.d.toString();
        }
        return b(this.t.toString());
    }

    public void clear() {
        this.f11512a = "";
        this.d.setLength(0);
        this.e.setLength(0);
        this.b.setLength(0);
        this.n = 0;
        this.c = "";
        this.q.setLength(0);
        this.s = "";
        this.t.setLength(0);
        this.f = true;
        this.g = false;
        this.p = 0;
        this.o = 0;
        this.h = false;
        this.i = false;
        this.u.clear();
        this.r = false;
        if (this.m.equals(this.l)) {
            return;
        }
        this.m = k(this.k);
    }

    public final String d() {
        this.f = true;
        this.i = false;
        this.u.clear();
        this.n = 0;
        this.b.setLength(0);
        this.c = "";
        return c();
    }

    public final boolean e() {
        StringBuilder sb;
        int f;
        if (this.t.length() == 0 || (f = this.j.f(this.t, (sb = new StringBuilder()))) == 0) {
            return false;
        }
        this.t.setLength(0);
        this.t.append((CharSequence) sb);
        String regionCodeForCountryCode = this.j.getRegionCodeForCountryCode(f);
        if (PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(regionCodeForCountryCode)) {
            this.m = this.j.n(f);
        } else if (!regionCodeForCountryCode.equals(this.k)) {
            this.m = k(regionCodeForCountryCode);
        }
        String num = Integer.toString(f);
        StringBuilder sb2 = this.q;
        sb2.append(num);
        sb2.append(' ');
        this.s = "";
        return true;
    }

    public final boolean f() {
        RegexCache regexCache = this.v;
        String valueOf = String.valueOf(this.m.getInternationalPrefix());
        Matcher matcher = regexCache.getPatternForRegex(valueOf.length() != 0 ? "\\+|".concat(valueOf) : new String("\\+|")).matcher(this.e);
        if (matcher.lookingAt()) {
            this.h = true;
            int end = matcher.end();
            this.t.setLength(0);
            this.t.append(this.e.substring(end));
            this.q.setLength(0);
            this.q.append(this.e.substring(0, end));
            if (this.e.charAt(0) != '+') {
                this.q.append(' ');
            }
            return true;
        }
        return false;
    }

    public String g() {
        for (Phonemetadata.NumberFormat numberFormat : this.u) {
            Matcher matcher = this.v.getPatternForRegex(numberFormat.getPattern()).matcher(this.t);
            if (matcher.matches()) {
                this.r = A.matcher(numberFormat.getNationalPrefixFormattingRule()).find();
                return b(matcher.replaceAll(numberFormat.getFormat()));
            }
        }
        return "";
    }

    public int getRememberedPosition() {
        if (!this.f) {
            return this.o;
        }
        int i = 0;
        int i2 = 0;
        while (i < this.p && i2 < this.f11512a.length()) {
            if (this.e.charAt(i) == this.f11512a.charAt(i2)) {
                i++;
            }
            i2++;
        }
        return i2;
    }

    public final boolean h(Phonemetadata.NumberFormat numberFormat) {
        String pattern = numberFormat.getPattern();
        if (pattern.indexOf(124) != -1) {
            return false;
        }
        String replaceAll = y.matcher(x.matcher(pattern).replaceAll("\\\\d")).replaceAll("\\\\d");
        this.b.setLength(0);
        String j = j(replaceAll, numberFormat.getFormat());
        if (j.length() > 0) {
            this.b.append(j);
            return true;
        }
        return false;
    }

    public final void i(String str) {
        List<Phonemetadata.NumberFormat> numberFormats = (!this.h || this.m.intlNumberFormatSize() <= 0) ? this.m.numberFormats() : this.m.intlNumberFormats();
        boolean hasNationalPrefix = this.m.hasNationalPrefix();
        for (Phonemetadata.NumberFormat numberFormat : numberFormats) {
            if (!hasNationalPrefix || this.h || numberFormat.isNationalPrefixOptionalWhenFormatting() || PhoneNumberUtil.l(numberFormat.getNationalPrefixFormattingRule())) {
                if (p(numberFormat.getFormat())) {
                    this.u.add(numberFormat);
                }
            }
        }
        s(str);
    }

    public String inputDigit(char c) {
        String n = n(c, false);
        this.f11512a = n;
        return n;
    }

    public String inputDigitAndRememberPosition(char c) {
        String n = n(c, true);
        this.f11512a = n;
        return n;
    }

    public final String j(String str, String str2) {
        Matcher matcher = this.v.getPatternForRegex(str).matcher("999999999999999");
        matcher.find();
        String group = matcher.group();
        return group.length() < this.t.length() ? "" : group.replaceAll(str, str2).replaceAll(BleConst.GetDeviceBatteryLevel, "\u2008");
    }

    public final Phonemetadata.PhoneMetadata k(String str) {
        Phonemetadata.PhoneMetadata o = this.j.o(this.j.getRegionCodeForCountryCode(this.j.getCountryCodeForRegion(str)));
        return o != null ? o : w;
    }

    public final String l() {
        int length = this.t.length();
        if (length > 0) {
            String str = "";
            for (int i = 0; i < length; i++) {
                str = m(this.t.charAt(i));
            }
            return this.f ? b(str) : this.d.toString();
        }
        return this.q.toString();
    }

    public final String m(char c) {
        Matcher matcher = B.matcher(this.b);
        if (matcher.find(this.n)) {
            String replaceFirst = matcher.replaceFirst(Character.toString(c));
            this.b.replace(0, replaceFirst.length(), replaceFirst);
            int start = matcher.start();
            this.n = start;
            return this.b.substring(0, start + 1);
        }
        if (this.u.size() == 1) {
            this.f = false;
        }
        this.c = "";
        return this.d.toString();
    }

    public final String n(char c, boolean z2) {
        this.d.append(c);
        if (z2) {
            this.o = this.d.length();
        }
        if (!o(c)) {
            this.f = false;
            this.g = true;
        } else {
            c = t(c, z2);
        }
        if (!this.f) {
            if (this.g) {
                return this.d.toString();
            }
            if (f()) {
                if (e()) {
                    return d();
                }
            } else if (a()) {
                this.q.append(' ');
                return d();
            }
            return this.d.toString();
        }
        int length = this.e.length();
        if (length != 0 && length != 1 && length != 2) {
            if (length == 3) {
                if (f()) {
                    this.i = true;
                } else {
                    this.s = u();
                    return c();
                }
            }
            if (this.i) {
                if (e()) {
                    this.i = false;
                }
                String valueOf = String.valueOf(this.q);
                String valueOf2 = String.valueOf(this.t.toString());
                StringBuilder sb = new StringBuilder(valueOf.length() + 0 + valueOf2.length());
                sb.append(valueOf);
                sb.append(valueOf2);
                return sb.toString();
            } else if (this.u.size() > 0) {
                String m = m(c);
                String g = g();
                if (g.length() > 0) {
                    return g;
                }
                s(this.t.toString());
                if (r()) {
                    return l();
                }
                return this.f ? b(m) : this.d.toString();
            } else {
                return c();
            }
        }
        return this.d.toString();
    }

    public final boolean o(char c) {
        if (Character.isDigit(c)) {
            return true;
        }
        return this.d.length() == 1 && PhoneNumberUtil.s.matcher(Character.toString(c)).matches();
    }

    public final boolean p(String str) {
        return z.matcher(str).matches();
    }

    public final boolean q() {
        return this.m.getCountryCode() == 1 && this.t.charAt(0) == '1' && this.t.charAt(1) != '0' && this.t.charAt(1) != '1';
    }

    public final boolean r() {
        Iterator<Phonemetadata.NumberFormat> it = this.u.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            String pattern = next.getPattern();
            if (this.c.equals(pattern)) {
                return false;
            }
            if (h(next)) {
                this.c = pattern;
                this.r = A.matcher(next.getNationalPrefixFormattingRule()).find();
                this.n = 0;
                return true;
            }
            it.remove();
        }
        this.f = false;
        return false;
    }

    public final void s(String str) {
        int length = str.length() - 3;
        Iterator<Phonemetadata.NumberFormat> it = this.u.iterator();
        while (it.hasNext()) {
            Phonemetadata.NumberFormat next = it.next();
            if (next.leadingDigitsPatternSize() != 0) {
                if (!this.v.getPatternForRegex(next.getLeadingDigitsPattern(Math.min(length, next.leadingDigitsPatternSize() - 1))).matcher(str).lookingAt()) {
                    it.remove();
                }
            }
        }
    }

    public final char t(char c, boolean z2) {
        if (c == '+') {
            this.e.append(c);
        } else {
            c = Character.forDigit(Character.digit(c, 10), 10);
            this.e.append(c);
            this.t.append(c);
        }
        if (z2) {
            this.p = this.e.length();
        }
        return c;
    }

    public final String u() {
        int i = 1;
        if (q()) {
            StringBuilder sb = this.q;
            sb.append('1');
            sb.append(' ');
            this.h = true;
        } else {
            if (this.m.hasNationalPrefixForParsing()) {
                Matcher matcher = this.v.getPatternForRegex(this.m.getNationalPrefixForParsing()).matcher(this.t);
                if (matcher.lookingAt() && matcher.end() > 0) {
                    this.h = true;
                    i = matcher.end();
                    this.q.append(this.t.substring(0, i));
                }
            }
            i = 0;
        }
        String substring = this.t.substring(0, i);
        this.t.delete(0, i);
        return substring;
    }
}
