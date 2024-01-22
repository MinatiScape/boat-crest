package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.bm.Languages;
/* loaded from: classes12.dex */
public class Lang {
    public static final Map<NameType, Lang> c = new EnumMap(NameType.class);

    /* renamed from: a  reason: collision with root package name */
    public final Languages f14360a;
    public final List<b> b;

    /* loaded from: classes12.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f14361a;
        public final Set<String> b;
        public final Pattern c;

        public boolean c(String str) {
            return this.c.matcher(str).find();
        }

        public b(Pattern pattern, Set<String> set, boolean z) {
            this.c = pattern;
            this.b = set;
            this.f14361a = z;
        }
    }

    static {
        NameType[] values;
        for (NameType nameType : NameType.values()) {
            c.put(nameType, loadFromResource(String.format("org/apache/commons/codec/language/bm/%s_lang.txt", nameType.getName()), Languages.getInstance(nameType)));
        }
    }

    public Lang(List<b> list, Languages languages) {
        this.b = Collections.unmodifiableList(list);
        this.f14360a = languages;
    }

    public static Lang instance(NameType nameType) {
        return c.get(nameType);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a2, code lost:
        throw new java.lang.IllegalArgumentException("Malformed line '" + r4 + "' in language resource '" + r9 + "'");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.commons.codec.language.bm.Lang loadFromResource(java.lang.String r9, org.apache.commons.codec.language.bm.Languages r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Scanner r1 = new java.util.Scanner
            java.io.InputStream r2 = org.apache.commons.codec.Resources.getInputStream(r9)
            java.lang.String r3 = "UTF-8"
            r1.<init>(r2, r3)
            r2 = 0
        L11:
            r3 = r2
        L12:
            boolean r4 = r1.hasNextLine()     // Catch: java.lang.Throwable -> Lac
            if (r4 == 0) goto La3
            java.lang.String r4 = r1.nextLine()     // Catch: java.lang.Throwable -> Lac
            r5 = 1
            if (r3 == 0) goto L28
        */
        //  java.lang.String r5 = "*/"
        /*
            boolean r4 = r4.endsWith(r5)     // Catch: java.lang.Throwable -> Lac
            if (r4 == 0) goto L12
            goto L11
        L28:
            java.lang.String r6 = "/*"
            boolean r6 = r4.startsWith(r6)     // Catch: java.lang.Throwable -> Lac
            if (r6 == 0) goto L32
            r3 = r5
            goto L12
        L32:
            java.lang.String r6 = "//"
            int r6 = r4.indexOf(r6)     // Catch: java.lang.Throwable -> Lac
            if (r6 < 0) goto L3f
            java.lang.String r6 = r4.substring(r2, r6)     // Catch: java.lang.Throwable -> Lac
            goto L40
        L3f:
            r6 = r4
        L40:
            java.lang.String r6 = r6.trim()     // Catch: java.lang.Throwable -> Lac
            int r7 = r6.length()     // Catch: java.lang.Throwable -> Lac
            if (r7 != 0) goto L4b
            goto L12
        L4b:
            java.lang.String r7 = "\\s+"
            java.lang.String[] r6 = r6.split(r7)     // Catch: java.lang.Throwable -> Lac
            int r7 = r6.length     // Catch: java.lang.Throwable -> Lac
            r8 = 3
            if (r7 != r8) goto L7f
            r4 = r6[r2]     // Catch: java.lang.Throwable -> Lac
            java.util.regex.Pattern r4 = java.util.regex.Pattern.compile(r4)     // Catch: java.lang.Throwable -> Lac
            r5 = r6[r5]     // Catch: java.lang.Throwable -> Lac
            java.lang.String r7 = "\\+"
            java.lang.String[] r5 = r5.split(r7)     // Catch: java.lang.Throwable -> Lac
            r7 = 2
            r6 = r6[r7]     // Catch: java.lang.Throwable -> Lac
            java.lang.String r7 = "true"
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> Lac
            org.apache.commons.codec.language.bm.Lang$b r7 = new org.apache.commons.codec.language.bm.Lang$b     // Catch: java.lang.Throwable -> Lac
            java.util.HashSet r8 = new java.util.HashSet     // Catch: java.lang.Throwable -> Lac
            java.util.List r5 = java.util.Arrays.asList(r5)     // Catch: java.lang.Throwable -> Lac
            r8.<init>(r5)     // Catch: java.lang.Throwable -> Lac
            r5 = 0
            r7.<init>(r4, r8, r6)     // Catch: java.lang.Throwable -> Lac
            r0.add(r7)     // Catch: java.lang.Throwable -> Lac
            goto L12
        L7f:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch: java.lang.Throwable -> Lac
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lac
            r0.<init>()     // Catch: java.lang.Throwable -> Lac
            java.lang.String r2 = "Malformed line '"
            r0.append(r2)     // Catch: java.lang.Throwable -> Lac
            r0.append(r4)     // Catch: java.lang.Throwable -> Lac
            java.lang.String r2 = "' in language resource '"
            r0.append(r2)     // Catch: java.lang.Throwable -> Lac
            r0.append(r9)     // Catch: java.lang.Throwable -> Lac
            java.lang.String r9 = "'"
            r0.append(r9)     // Catch: java.lang.Throwable -> Lac
            java.lang.String r9 = r0.toString()     // Catch: java.lang.Throwable -> Lac
            r10.<init>(r9)     // Catch: java.lang.Throwable -> Lac
            throw r10     // Catch: java.lang.Throwable -> Lac
        La3:
            r1.close()
            org.apache.commons.codec.language.bm.Lang r9 = new org.apache.commons.codec.language.bm.Lang
            r9.<init>(r0, r10)
            return r9
        Lac:
            r9 = move-exception
            throw r9     // Catch: java.lang.Throwable -> Lae
        Lae:
            r10 = move-exception
            r1.close()     // Catch: java.lang.Throwable -> Lb3
            goto Lb7
        Lb3:
            r0 = move-exception
            r9.addSuppressed(r0)
        Lb7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.bm.Lang.loadFromResource(java.lang.String, org.apache.commons.codec.language.bm.Languages):org.apache.commons.codec.language.bm.Lang");
    }

    public String guessLanguage(String str) {
        Languages.LanguageSet guessLanguages = guessLanguages(str);
        return guessLanguages.isSingleton() ? guessLanguages.getAny() : "any";
    }

    public Languages.LanguageSet guessLanguages(String str) {
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        HashSet hashSet = new HashSet(this.f14360a.getLanguages());
        for (b bVar : this.b) {
            if (bVar.c(lowerCase)) {
                if (bVar.f14361a) {
                    hashSet.retainAll(bVar.b);
                } else {
                    hashSet.removeAll(bVar.b);
                }
            }
        }
        Languages.LanguageSet from = Languages.LanguageSet.from(hashSet);
        return from.equals(Languages.NO_LANGUAGES) ? Languages.ANY_LANGUAGE : from;
    }
}
