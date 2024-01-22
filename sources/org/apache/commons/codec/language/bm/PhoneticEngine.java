package org.apache.commons.codec.language.bm;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;
/* loaded from: classes12.dex */
public class PhoneticEngine {
    public static final Map<NameType, Set<String>> f;

    /* renamed from: a  reason: collision with root package name */
    public final Lang f14364a;
    public final NameType b;
    public final RuleType c;
    public final boolean d;
    public final int e;

    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f14365a;

        static {
            int[] iArr = new int[NameType.values().length];
            f14365a = iArr;
            try {
                iArr[NameType.SEPHARDIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14365a[NameType.ASHKENAZI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14365a[NameType.GENERIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final Set<Rule.Phoneme> f14366a;

        public /* synthetic */ b(Set set, a aVar) {
            this(set);
        }

        public static b c(Languages.LanguageSet languageSet) {
            return new b(new Rule.Phoneme("", languageSet));
        }

        public void a(CharSequence charSequence) {
            for (Rule.Phoneme phoneme : this.f14366a) {
                phoneme.append(charSequence);
            }
        }

        public void b(Rule.PhonemeExpr phonemeExpr, int i) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(i);
            loop0: for (Rule.Phoneme phoneme : this.f14366a) {
                for (Rule.Phoneme phoneme2 : phonemeExpr.getPhonemes()) {
                    Languages.LanguageSet restrictTo = phoneme.getLanguages().restrictTo(phoneme2.getLanguages());
                    if (!restrictTo.isEmpty()) {
                        Rule.Phoneme phoneme3 = new Rule.Phoneme(phoneme, phoneme2, restrictTo);
                        if (linkedHashSet.size() < i) {
                            linkedHashSet.add(phoneme3);
                            if (linkedHashSet.size() >= i) {
                                break loop0;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            this.f14366a.clear();
            this.f14366a.addAll(linkedHashSet);
        }

        public Set<Rule.Phoneme> d() {
            return this.f14366a;
        }

        public String e() {
            StringBuilder sb = new StringBuilder();
            for (Rule.Phoneme phoneme : this.f14366a) {
                if (sb.length() > 0) {
                    sb.append("|");
                }
                sb.append(phoneme.getPhonemeText());
            }
            return sb.toString();
        }

        public b(Rule.Phoneme phoneme) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            this.f14366a = linkedHashSet;
            linkedHashSet.add(phoneme);
        }

        public b(Set<Rule.Phoneme> set) {
            this.f14366a = set;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final Map<String, List<Rule>> f14367a;
        public final CharSequence b;
        public final b c;
        public int d;
        public final int e;
        public boolean f;

        public c(Map<String, List<Rule>> map, CharSequence charSequence, b bVar, int i, int i2) {
            Objects.requireNonNull(map, "finalRules");
            this.f14367a = map;
            this.c = bVar;
            this.b = charSequence;
            this.d = i;
            this.e = i2;
        }

        public int a() {
            return this.d;
        }

        public b b() {
            return this.c;
        }

        public c c() {
            int i;
            this.f = false;
            Map<String, List<Rule>> map = this.f14367a;
            CharSequence charSequence = this.b;
            int i2 = this.d;
            List<Rule> list = map.get(charSequence.subSequence(i2, i2 + 1));
            if (list != null) {
                Iterator<Rule> it = list.iterator();
                i = 1;
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Rule next = it.next();
                    int length = next.getPattern().length();
                    if (next.patternAndContextMatches(this.b, this.d)) {
                        this.c.b(next.getPhoneme(), this.e);
                        this.f = true;
                        i = length;
                        break;
                    }
                    i = length;
                }
            } else {
                i = 1;
            }
            this.d += this.f ? i : 1;
            return this;
        }

        public boolean d() {
            return this.f;
        }
    }

    static {
        EnumMap enumMap = new EnumMap(NameType.class);
        f = enumMap;
        enumMap.put((EnumMap) NameType.ASHKENAZI, (NameType) Collections.unmodifiableSet(new HashSet(Arrays.asList("bar", "ben", "da", "de", "van", "von"))));
        enumMap.put((EnumMap) NameType.SEPHARDIC, (NameType) Collections.unmodifiableSet(new HashSet(Arrays.asList("al", "el", "da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
        enumMap.put((EnumMap) NameType.GENERIC, (NameType) Collections.unmodifiableSet(new HashSet(Arrays.asList("da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean z) {
        this(nameType, ruleType, z, 20);
    }

    public static String b(Iterable<String> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = iterable.iterator();
        if (it.hasNext()) {
            sb.append(it.next());
        }
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
        }
        return sb.toString();
    }

    public final b a(b bVar, Map<String, List<Rule>> map) {
        Objects.requireNonNull(map, "finalRules");
        if (map.isEmpty()) {
            return bVar;
        }
        TreeMap treeMap = new TreeMap(Rule.Phoneme.COMPARATOR);
        for (Rule.Phoneme phoneme : bVar.d()) {
            b c2 = b.c(phoneme.getLanguages());
            String charSequence = phoneme.getPhonemeText().toString();
            b bVar2 = c2;
            int i = 0;
            while (i < charSequence.length()) {
                c c3 = new c(map, charSequence, bVar2, i, this.e).c();
                boolean d = c3.d();
                bVar2 = c3.b();
                if (!d) {
                    bVar2.a(charSequence.subSequence(i, i + 1));
                }
                i = c3.a();
            }
            for (Rule.Phoneme phoneme2 : bVar2.d()) {
                if (treeMap.containsKey(phoneme2)) {
                    Rule.Phoneme mergeWithLanguage = ((Rule.Phoneme) treeMap.remove(phoneme2)).mergeWithLanguage(phoneme2.getLanguages());
                    treeMap.put(mergeWithLanguage, mergeWithLanguage);
                } else {
                    treeMap.put(phoneme2, phoneme2);
                }
            }
        }
        return new b(treeMap.keySet(), null);
    }

    public String encode(String str) {
        return encode(str, this.f14364a.guessLanguages(str));
    }

    public Lang getLang() {
        return this.f14364a;
    }

    public int getMaxPhonemes() {
        return this.e;
    }

    public NameType getNameType() {
        return this.b;
    }

    public RuleType getRuleType() {
        return this.c;
    }

    public boolean isConcat() {
        return this.d;
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean z, int i) {
        RuleType ruleType2 = RuleType.RULES;
        if (ruleType != ruleType2) {
            this.b = nameType;
            this.c = ruleType;
            this.d = z;
            this.f14364a = Lang.instance(nameType);
            this.e = i;
            return;
        }
        throw new IllegalArgumentException("ruleType must not be " + ruleType2);
    }

    public String encode(String str, Languages.LanguageSet languageSet) {
        String str2;
        String substring;
        String substring2;
        Map<String, List<Rule>> instanceMap = Rule.getInstanceMap(this.b, RuleType.RULES, languageSet);
        Map<String, List<Rule>> instanceMap2 = Rule.getInstanceMap(this.b, this.c, "common");
        Map<String, List<Rule>> instanceMap3 = Rule.getInstanceMap(this.b, this.c, languageSet);
        String trim = str.toLowerCase(Locale.ENGLISH).replace(Soundex.SILENT_MARKER, ' ').trim();
        if (this.b == NameType.GENERIC) {
            if (trim.length() >= 2 && trim.substring(0, 2).equals("d'")) {
                String str3 = "d" + trim.substring(2);
                return "(" + encode(substring2) + ")-(" + encode(str3) + ")";
            }
            for (String str4 : f.get(this.b)) {
                if (trim.startsWith(str4 + HexStringBuilder.DEFAULT_SEPARATOR)) {
                    String str5 = str4 + trim.substring(str4.length() + 1);
                    return "(" + encode(substring) + ")-(" + encode(str5) + ")";
                }
            }
        }
        List<String> asList = Arrays.asList(trim.split("\\s+"));
        ArrayList<String> arrayList = new ArrayList();
        int i = a.f14365a[this.b.ordinal()];
        if (i == 1) {
            for (String str6 : asList) {
                String[] split = str6.split("'");
                arrayList.add(split[split.length - 1]);
            }
            arrayList.removeAll(f.get(this.b));
        } else if (i == 2) {
            arrayList.addAll(asList);
            arrayList.removeAll(f.get(this.b));
        } else if (i == 3) {
            arrayList.addAll(asList);
        } else {
            throw new IllegalStateException("Unreachable case: " + this.b);
        }
        if (this.d) {
            str2 = b(arrayList, HexStringBuilder.DEFAULT_SEPARATOR);
        } else if (arrayList.size() == 1) {
            str2 = (String) asList.iterator().next();
        } else {
            StringBuilder sb = new StringBuilder();
            for (String str7 : arrayList) {
                sb.append("-");
                sb.append(encode(str7));
            }
            return sb.substring(1);
        }
        int i2 = 0;
        b c2 = b.c(languageSet);
        while (i2 < str2.length()) {
            c c3 = new c(instanceMap, str2, c2, i2, this.e).c();
            i2 = c3.a();
            c2 = c3.b();
        }
        return a(a(c2, instanceMap2), instanceMap3).e();
    }
}
