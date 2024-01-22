package org.apache.commons.codec.language.bm;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.language.bm.Languages;
/* loaded from: classes12.dex */
public class Rule {
    public static final String ALL = "ALL";
    public static final RPattern ALL_STRINGS_RMATCHER = new b();
    public static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> e = new EnumMap(NameType.class);

    /* renamed from: a  reason: collision with root package name */
    public final RPattern f14368a;
    public final String b;
    public final PhonemeExpr c;
    public final RPattern d;

    /* loaded from: classes12.dex */
    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();
    }

    /* loaded from: classes12.dex */
    public static final class PhonemeList implements PhonemeExpr {

        /* renamed from: a  reason: collision with root package name */
        public final List<Phoneme> f14370a;

        public PhonemeList(List<Phoneme> list) {
            this.f14370a = list;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public List<Phoneme> getPhonemes() {
            return this.f14370a;
        }
    }

    /* loaded from: classes12.dex */
    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    /* loaded from: classes12.dex */
    public static class a implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public Pattern f14371a;
        public final /* synthetic */ String b;

        public a(String str) {
            this.b = str;
            this.f14371a = Pattern.compile(str);
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return this.f14371a.matcher(charSequence).find();
        }
    }

    /* loaded from: classes12.dex */
    public static class b implements RPattern {
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return true;
        }
    }

    /* loaded from: classes12.dex */
    public static class c extends Rule {
        public final int f;
        public final String g;
        public final /* synthetic */ int h;
        public final /* synthetic */ String i;
        public final /* synthetic */ String j;
        public final /* synthetic */ String k;
        public final /* synthetic */ String l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(String str, String str2, String str3, PhonemeExpr phonemeExpr, int i, String str4, String str5, String str6, String str7) {
            super(str, str2, str3, phonemeExpr);
            this.h = i;
            this.i = str4;
            this.j = str5;
            this.k = str6;
            this.l = str7;
            this.f = i;
            this.g = str4;
        }

        public String toString() {
            return "Rule{line=" + this.f + ", loc='" + this.g + "', pat='" + this.j + "', lcon='" + this.k + "', rcon='" + this.l + "'}";
        }
    }

    /* loaded from: classes12.dex */
    public static class d implements RPattern {
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() == 0;
        }
    }

    /* loaded from: classes12.dex */
    public static class e implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14372a;

        public e(String str) {
            this.f14372a = str;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.equals(this.f14372a);
        }
    }

    /* loaded from: classes12.dex */
    public static class f implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14373a;

        public f(String str) {
            this.f14373a = str;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return Rule.m(charSequence, this.f14373a);
        }
    }

    /* loaded from: classes12.dex */
    public static class g implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14374a;

        public g(String str) {
            this.f14374a = str;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return Rule.h(charSequence, this.f14374a);
        }
    }

    /* loaded from: classes12.dex */
    public static class h implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14375a;
        public final /* synthetic */ boolean b;

        public h(String str, boolean z) {
            this.f14375a = str;
            this.b = z;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() == 1 && Rule.d(this.f14375a, charSequence.charAt(0)) == this.b;
        }
    }

    /* loaded from: classes12.dex */
    public static class i implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14376a;
        public final /* synthetic */ boolean b;

        public i(String str, boolean z) {
            this.f14376a = str;
            this.b = z;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() > 0 && Rule.d(this.f14376a, charSequence.charAt(0)) == this.b;
        }
    }

    /* loaded from: classes12.dex */
    public static class j implements RPattern {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f14377a;
        public final /* synthetic */ boolean b;

        public j(String str, boolean z) {
            this.f14377a = str;
            this.b = z;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public boolean isMatch(CharSequence charSequence) {
            return charSequence.length() > 0 && Rule.d(this.f14377a, charSequence.charAt(charSequence.length() - 1)) == this.b;
        }
    }

    static {
        NameType[] values;
        RuleType[] values2;
        for (NameType nameType : NameType.values()) {
            EnumMap enumMap = new EnumMap(RuleType.class);
            for (RuleType ruleType : RuleType.values()) {
                HashMap hashMap = new HashMap();
                for (String str : Languages.getInstance(nameType).getLanguages()) {
                    try {
                        Scanner g2 = g(nameType, ruleType, str);
                        hashMap.put(str, k(g2, e(nameType, ruleType, str)));
                        if (g2 != null) {
                            g2.close();
                        }
                    } catch (IllegalStateException e2) {
                        throw new IllegalStateException("Problem processing " + e(nameType, ruleType, str), e2);
                    }
                }
                if (!ruleType.equals(RuleType.RULES)) {
                    Scanner g3 = g(nameType, ruleType, "common");
                    try {
                        hashMap.put("common", k(g3, e(nameType, ruleType, "common")));
                        if (g3 != null) {
                            g3.close();
                        }
                    } finally {
                    }
                }
                enumMap.put((EnumMap) ruleType, (RuleType) Collections.unmodifiableMap(hashMap));
            }
            e.put(nameType, Collections.unmodifiableMap(enumMap));
        }
    }

    public Rule(String str, String str2, String str3, PhonemeExpr phonemeExpr) {
        this.b = str;
        this.f14368a = l(str2 + "$");
        this.d = l("^" + str3);
        this.c = phonemeExpr;
    }

    public static boolean d(CharSequence charSequence, char c2) {
        for (int i2 = 0; i2 < charSequence.length(); i2++) {
            if (charSequence.charAt(i2) == c2) {
                return true;
            }
        }
        return false;
    }

    public static String e(NameType nameType, RuleType ruleType, String str) {
        return String.format("org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), ruleType.getName(), str);
    }

    public static Scanner f(String str) {
        return new Scanner(Resources.getInputStream(String.format("org/apache/commons/codec/language/bm/%s.txt", str)), "UTF-8");
    }

    public static Scanner g(NameType nameType, RuleType ruleType, String str) {
        return new Scanner(Resources.getInputStream(e(nameType, ruleType, str)), "UTF-8");
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        Map<String, List<Rule>> instanceMap = getInstanceMap(nameType, ruleType, languageSet);
        ArrayList arrayList = new ArrayList();
        for (List<Rule> list : instanceMap.values()) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, Languages.LanguageSet languageSet) {
        return languageSet.isSingleton() ? getInstanceMap(nameType, ruleType, languageSet.getAny()) : getInstanceMap(nameType, ruleType, "any");
    }

    public static boolean h(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        int length = charSequence.length() - 1;
        for (int length2 = charSequence2.length() - 1; length2 >= 0; length2--) {
            if (charSequence.charAt(length) != charSequence2.charAt(length2)) {
                return false;
            }
            length--;
        }
        return true;
    }

    public static Phoneme i(String str) {
        int indexOf = str.indexOf("[");
        if (indexOf >= 0) {
            if (str.endsWith("]")) {
                return new Phoneme(str.substring(0, indexOf), Languages.LanguageSet.from(new HashSet(Arrays.asList(str.substring(indexOf + 1, str.length() - 1).split("[+]")))));
            }
            throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
        }
        return new Phoneme(str, Languages.ANY_LANGUAGE);
    }

    public static PhonemeExpr j(String str) {
        if (str.startsWith("(")) {
            if (str.endsWith(")")) {
                ArrayList arrayList = new ArrayList();
                String substring = str.substring(1, str.length() - 1);
                for (String str2 : substring.split("[|]")) {
                    arrayList.add(i(str2));
                }
                if (substring.startsWith("|") || substring.endsWith("|")) {
                    arrayList.add(new Phoneme("", Languages.ANY_LANGUAGE));
                }
                return new PhonemeList(arrayList);
            }
            throw new IllegalArgumentException("Phoneme starts with '(' so must end with ')'");
        }
        return i(str);
    }

    public static Map<String, List<Rule>> k(Scanner scanner, String str) {
        String str2;
        int i2;
        HashMap hashMap = new HashMap();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (scanner.hasNextLine()) {
            int i6 = i4 + 1;
            String nextLine = scanner.nextLine();
            if (i5 != 0) {
                if (nextLine.endsWith("*/")) {
                    i2 = i3;
                    i5 = i2;
                    i3 = i2;
                    i4 = i6;
                }
                i2 = i3;
                i3 = i2;
                i4 = i6;
            } else {
                if (nextLine.startsWith("/*")) {
                    i2 = i3;
                    i5 = 1;
                } else {
                    int indexOf = nextLine.indexOf("//");
                    String trim = (indexOf >= 0 ? nextLine.substring(i3, indexOf) : nextLine).trim();
                    if (trim.length() == 0) {
                        continue;
                        i4 = i6;
                    } else if (trim.startsWith("#include")) {
                        String trim2 = trim.substring(8).trim();
                        if (!trim2.contains(HexStringBuilder.DEFAULT_SEPARATOR)) {
                            Scanner f2 = f(trim2);
                            try {
                                hashMap.putAll(k(f2, str + "->" + trim2));
                                if (f2 != null) {
                                    f2.close();
                                }
                                i2 = i3;
                            } finally {
                            }
                        } else {
                            throw new IllegalArgumentException("Malformed import statement '" + nextLine + "' in " + str);
                        }
                    } else {
                        String[] split = trim.split("\\s+");
                        if (split.length == 4) {
                            try {
                                String n = n(split[i3]);
                                String n2 = n(split[1]);
                                String n3 = n(split[2]);
                                str2 = "' in ";
                                try {
                                    c cVar = new c(n, n2, n3, j(n(split[3])), i6, str, n, n2, n3);
                                    i2 = 0;
                                    String substring = cVar.b.substring(0, 1);
                                    List list = (List) hashMap.get(substring);
                                    if (list == null) {
                                        list = new ArrayList();
                                        hashMap.put(substring, list);
                                    }
                                    list.add(cVar);
                                } catch (IllegalArgumentException e2) {
                                    e = e2;
                                    throw new IllegalStateException("Problem parsing line '" + i6 + str2 + str, e);
                                }
                            } catch (IllegalArgumentException e3) {
                                e = e3;
                                str2 = "' in ";
                            }
                        } else {
                            throw new IllegalArgumentException("Malformed rule statement split into " + split.length + " parts: " + nextLine + " in " + str);
                        }
                    }
                }
                i3 = i2;
                i4 = i6;
            }
        }
        return hashMap;
    }

    public static RPattern l(String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        int length = str.length();
        if (endsWith) {
            length--;
        }
        String substring = str.substring(startsWith ? 1 : 0, length);
        if (substring.contains("[")) {
            boolean startsWith2 = substring.startsWith("[");
            boolean endsWith2 = substring.endsWith("]");
            if (startsWith2 && endsWith2) {
                String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains("[")) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    boolean z = !startsWith3;
                    if (startsWith && endsWith) {
                        return new h(substring2, z);
                    }
                    if (startsWith) {
                        return new i(substring2, z);
                    }
                    if (endsWith) {
                        return new j(substring2, z);
                    }
                }
            }
        } else if (startsWith && endsWith) {
            if (substring.length() == 0) {
                return new d();
            }
            return new e(substring);
        } else if ((startsWith || endsWith) && substring.length() == 0) {
            return ALL_STRINGS_RMATCHER;
        } else {
            if (startsWith) {
                return new f(substring);
            }
            if (endsWith) {
                return new g(substring);
            }
        }
        return new a(str);
    }

    public static boolean m(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence2.length() > charSequence.length()) {
            return false;
        }
        for (int i2 = 0; i2 < charSequence2.length(); i2++) {
            if (charSequence.charAt(i2) != charSequence2.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    public static String n(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    public RPattern getLContext() {
        return this.f14368a;
    }

    public String getPattern() {
        return this.b;
    }

    public PhonemeExpr getPhoneme() {
        return this.c;
    }

    public RPattern getRContext() {
        return this.d;
    }

    public boolean patternAndContextMatches(CharSequence charSequence, int i2) {
        if (i2 >= 0) {
            int length = this.b.length() + i2;
            if (length <= charSequence.length() && charSequence.subSequence(i2, length).equals(this.b) && this.d.isMatch(charSequence.subSequence(length, charSequence.length()))) {
                return this.f14368a.isMatch(charSequence.subSequence(0, i2));
            }
            return false;
        }
        throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
    }

    /* loaded from: classes12.dex */
    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new a();

        /* renamed from: a  reason: collision with root package name */
        public final StringBuilder f14369a;
        public final Languages.LanguageSet b;

        /* loaded from: classes12.dex */
        public static class a implements Comparator<Phoneme> {
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Phoneme phoneme, Phoneme phoneme2) {
                for (int i = 0; i < phoneme.f14369a.length(); i++) {
                    if (i >= phoneme2.f14369a.length()) {
                        return 1;
                    }
                    int charAt = phoneme.f14369a.charAt(i) - phoneme2.f14369a.charAt(i);
                    if (charAt != 0) {
                        return charAt;
                    }
                }
                return phoneme.f14369a.length() < phoneme2.f14369a.length() ? -1 : 0;
            }
        }

        public Phoneme(CharSequence charSequence, Languages.LanguageSet languageSet) {
            this.f14369a = new StringBuilder(charSequence);
            this.b = languageSet;
        }

        public Phoneme append(CharSequence charSequence) {
            this.f14369a.append(charSequence);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.b;
        }

        public CharSequence getPhonemeText() {
            return this.f14369a;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        @Deprecated
        public Phoneme join(Phoneme phoneme) {
            return new Phoneme(this.f14369a.toString() + phoneme.f14369a.toString(), this.b.restrictTo(phoneme.b));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet languageSet) {
            return new Phoneme(this.f14369a.toString(), this.b.merge(languageSet));
        }

        public String toString() {
            return this.f14369a.toString() + "[" + this.b + "]";
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2) {
            this(phoneme.f14369a, phoneme.b);
            this.f14369a.append((CharSequence) phoneme2.f14369a);
        }

        public Phoneme(Phoneme phoneme, Phoneme phoneme2, Languages.LanguageSet languageSet) {
            this(phoneme.f14369a, languageSet);
            this.f14369a.append((CharSequence) phoneme2.f14369a);
        }
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType ruleType, String str) {
        Map<String, List<Rule>> map = e.get(nameType).get(ruleType).get(str);
        if (map != null) {
            return map;
        }
        throw new IllegalArgumentException(String.format("No rules found for %s, %s, %s.", nameType.getName(), ruleType.getName(), str));
    }

    public static List<Rule> getInstance(NameType nameType, RuleType ruleType, String str) {
        return getInstance(nameType, ruleType, Languages.LanguageSet.from(new HashSet(Arrays.asList(str))));
    }
}
