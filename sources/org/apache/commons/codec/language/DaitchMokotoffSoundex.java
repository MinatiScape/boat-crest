package org.apache.commons.codec.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes12.dex */
public class DaitchMokotoffSoundex implements StringEncoder {
    public static final Map<Character, List<c>> b;
    public static final Map<Character, Character> c;

    /* renamed from: a  reason: collision with root package name */
    public final boolean f14349a;

    /* loaded from: classes12.dex */
    public static class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            return cVar2.b() - cVar.b();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final StringBuilder f14350a;
        public String b;
        public String c;

        public /* synthetic */ b(a aVar) {
            this();
        }

        public b a() {
            b bVar = new b();
            bVar.f14350a.append(toString());
            bVar.c = this.c;
            return bVar;
        }

        public void b() {
            while (this.f14350a.length() < 6) {
                this.f14350a.append('0');
                this.b = null;
            }
        }

        public void c(String str, boolean z) {
            String str2 = this.c;
            if ((str2 == null || !str2.endsWith(str) || z) && this.f14350a.length() < 6) {
                this.f14350a.append(str);
                if (this.f14350a.length() > 6) {
                    StringBuilder sb = this.f14350a;
                    sb.delete(6, sb.length());
                }
                this.b = null;
            }
            this.c = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof b) {
                return toString().equals(((b) obj).toString());
            }
            return false;
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public String toString() {
            if (this.b == null) {
                this.b = this.f14350a.toString();
            }
            return this.b;
        }

        public b() {
            this.f14350a = new StringBuilder();
            this.c = null;
            this.b = null;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final String f14351a;
        public final String[] b;
        public final String[] c;
        public final String[] d;

        public c(String str, String str2, String str3, String str4) {
            this.f14351a = str;
            this.b = str2.split("\\|");
            this.c = str3.split("\\|");
            this.d = str4.split("\\|");
        }

        public int b() {
            return this.f14351a.length();
        }

        public String[] c(String str, boolean z) {
            if (z) {
                return this.b;
            }
            int b = b();
            if (b < str.length() ? d(str.charAt(b)) : false) {
                return this.c;
            }
            return this.d;
        }

        public final boolean d(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }

        public boolean e(String str) {
            return str.startsWith(this.f14351a);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.f14351a, Arrays.asList(this.b), Arrays.asList(this.c), Arrays.asList(this.d));
        }
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        HashMap hashMap2 = new HashMap();
        c = hashMap2;
        Scanner scanner = new Scanner(Resources.getInputStream("org/apache/commons/codec/language/dmrules.txt"), "UTF-8");
        try {
            b(scanner, "org/apache/commons/codec/language/dmrules.txt", hashMap, hashMap2);
            scanner.close();
            for (Map.Entry entry : hashMap.entrySet()) {
                Collections.sort((List) entry.getValue(), new a());
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    scanner.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public static void b(Scanner scanner, String str, Map<Character, List<c>> map, Map<Character, Character> map2) {
        int i = 0;
        boolean z = false;
        while (scanner.hasNextLine()) {
            i++;
            String nextLine = scanner.nextLine();
            if (z) {
                if (nextLine.endsWith("*/")) {
                    z = false;
                }
            } else if (nextLine.startsWith("/*")) {
                z = true;
            } else {
                int indexOf = nextLine.indexOf("//");
                String trim = (indexOf >= 0 ? nextLine.substring(0, indexOf) : nextLine).trim();
                if (trim.length() == 0) {
                    continue;
                } else if (trim.contains("=")) {
                    String[] split = trim.split("=");
                    if (split.length == 2) {
                        String str2 = split[0];
                        String str3 = split[1];
                        if (str2.length() == 1 && str3.length() == 1) {
                            map2.put(Character.valueOf(str2.charAt(0)), Character.valueOf(str3.charAt(0)));
                        } else {
                            throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + nextLine + " in " + str);
                        }
                    } else {
                        throw new IllegalArgumentException("Malformed folding statement split into " + split.length + " parts: " + nextLine + " in " + str);
                    }
                } else {
                    String[] split2 = trim.split("\\s+");
                    if (split2.length == 4) {
                        try {
                            c cVar = new c(d(split2[0]), d(split2[1]), d(split2[2]), d(split2[3]));
                            char charAt = cVar.f14351a.charAt(0);
                            List<c> list = map.get(Character.valueOf(charAt));
                            if (list == null) {
                                list = new ArrayList<>();
                                map.put(Character.valueOf(charAt), list);
                            }
                            list.add(cVar);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalStateException("Problem parsing line '" + i + "' in " + str, e);
                        }
                    } else {
                        throw new IllegalArgumentException("Malformed rule statement split into " + split2.length + " parts: " + nextLine + " in " + str);
                    }
                }
            }
        }
    }

    public static String d(String str) {
        if (str.startsWith("\"")) {
            str = str.substring(1);
        }
        return str.endsWith("\"") ? str.substring(0, str.length() - 1) : str;
    }

    public final String a(String str) {
        char[] charArray;
        StringBuilder sb = new StringBuilder();
        for (char c2 : str.toCharArray()) {
            if (!Character.isWhitespace(c2)) {
                char lowerCase = Character.toLowerCase(c2);
                if (this.f14349a) {
                    Map<Character, Character> map = c;
                    if (map.containsKey(Character.valueOf(lowerCase))) {
                        lowerCase = map.get(Character.valueOf(lowerCase)).charValue();
                    }
                }
                sb.append(lowerCase);
            }
        }
        return sb.toString();
    }

    public final String[] c(String str, boolean z) {
        String str2;
        int i;
        String str3;
        if (str == null) {
            return null;
        }
        String a2 = a(str);
        LinkedHashSet<b> linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new b(null));
        int i2 = 0;
        char c2 = 0;
        while (i2 < a2.length()) {
            char charAt = a2.charAt(i2);
            if (!Character.isWhitespace(charAt)) {
                String substring = a2.substring(i2);
                List<c> list = b.get(Character.valueOf(charAt));
                if (list != null) {
                    List arrayList = z ? new ArrayList() : Collections.emptyList();
                    Iterator<c> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            str2 = a2;
                            i = 1;
                            break;
                        }
                        c next = it.next();
                        if (next.e(substring)) {
                            if (z) {
                                arrayList.clear();
                            }
                            String[] c3 = next.c(substring, c2 == 0);
                            boolean z2 = c3.length > 1 && z;
                            for (b bVar : linkedHashSet) {
                                int length = c3.length;
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= length) {
                                        str3 = a2;
                                        break;
                                    }
                                    String str4 = c3[i3];
                                    b a3 = z2 ? bVar.a() : bVar;
                                    str3 = a2;
                                    a3.c(str4, (c2 == 'm' && charAt == 'n') || (c2 == 'n' && charAt == 'm'));
                                    if (z) {
                                        arrayList.add(a3);
                                        i3++;
                                        a2 = str3;
                                    }
                                }
                                a2 = str3;
                            }
                            str2 = a2;
                            if (z) {
                                linkedHashSet.clear();
                                linkedHashSet.addAll(arrayList);
                            }
                            i = 1;
                            i2 += next.b() - 1;
                        }
                    }
                    c2 = charAt;
                    i2 += i;
                    a2 = str2;
                }
            }
            str2 = a2;
            i = 1;
            i2 += i;
            a2 = str2;
        }
        String[] strArr = new String[linkedHashSet.size()];
        int i4 = 0;
        for (b bVar2 : linkedHashSet) {
            bVar2.b();
            strArr[i4] = bVar2.toString();
            i4++;
        }
        return strArr;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
    }

    public String soundex(String str) {
        String[] c2 = c(str, true);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String str2 : c2) {
            sb.append(str2);
            i++;
            if (i < c2.length) {
                sb.append('|');
            }
        }
        return sb.toString();
    }

    public DaitchMokotoffSoundex(boolean z) {
        this.f14349a = z;
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null) {
            return null;
        }
        return c(str, false)[0];
    }
}
