package com.google.i18n.phonenumbers;

import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonemetadata;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.b;
import com.jstyle.blesdk1860.constant.BleConst;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes10.dex */
public class PhoneNumberUtil {
    public static final String A;
    public static final String B;
    public static final Pattern C;
    public static final Pattern D;
    public static final Pattern E;
    public static final Pattern F;
    public static final Pattern G;
    public static final Pattern H;
    public static final Pattern I;
    public static final Pattern J;
    public static PhoneNumberUtil K = null;
    public static final String REGION_CODE_FOR_NON_GEO_ENTITY = "001";
    public static final MetadataLoader j = new a();
    public static final Logger k = Logger.getLogger(PhoneNumberUtil.class.getName());
    public static final Map<Integer, String> l;
    public static final Map<Character, Character> m;
    public static final Map<Character, Character> n;
    public static final Map<Character, Character> o;
    public static final Map<Character, Character> p;
    public static final Pattern q;
    public static final String r;
    public static final Pattern s;
    public static final Pattern t;
    public static final Pattern u;
    public static final Pattern v;
    public static final Pattern w;
    public static final Pattern x;
    public static final Pattern y;
    public static final String z;

    /* renamed from: a  reason: collision with root package name */
    public final Map<Integer, List<String>> f11514a;
    public final Set<String> b = new HashSet(35);
    public final Map<String, Phonemetadata.PhoneMetadata> c = Collections.synchronizedMap(new HashMap());
    public final Map<Integer, Phonemetadata.PhoneMetadata> d = Collections.synchronizedMap(new HashMap());
    public final RegexCache e = new RegexCache(100);
    public final Set<String> f = new HashSet(320);
    public final Set<Integer> g = new HashSet();
    public final String h;
    public final MetadataLoader i;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static abstract class Leniency {
        private static final /* synthetic */ Leniency[] $VALUES;
        public static final Leniency EXACT_GROUPING;
        public static final Leniency POSSIBLE;
        public static final Leniency STRICT_GROUPING;
        public static final Leniency VALID;

        /* loaded from: classes10.dex */
        public enum a extends Leniency {
            public a(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                return phoneNumberUtil.isPossibleNumber(phoneNumber);
            }
        }

        /* loaded from: classes10.dex */
        public enum b extends Leniency {
            public b(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && com.google.i18n.phonenumbers.b.e(phoneNumber, str, phoneNumberUtil)) {
                    return com.google.i18n.phonenumbers.b.l(phoneNumber, phoneNumberUtil);
                }
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum c extends Leniency {

            /* loaded from: classes10.dex */
            public class a implements b.a {
                public a(c cVar) {
                }

                @Override // com.google.i18n.phonenumbers.b.a
                public boolean a(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
                    return com.google.i18n.phonenumbers.b.b(phoneNumberUtil, phoneNumber, sb, strArr);
                }
            }

            public c(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && com.google.i18n.phonenumbers.b.e(phoneNumber, str, phoneNumberUtil) && !com.google.i18n.phonenumbers.b.d(phoneNumber, str) && com.google.i18n.phonenumbers.b.l(phoneNumber, phoneNumberUtil)) {
                    return com.google.i18n.phonenumbers.b.c(phoneNumber, str, phoneNumberUtil, new a(this));
                }
                return false;
            }
        }

        /* loaded from: classes10.dex */
        public enum d extends Leniency {

            /* loaded from: classes10.dex */
            public class a implements b.a {
                public a(d dVar) {
                }

                @Override // com.google.i18n.phonenumbers.b.a
                public boolean a(PhoneNumberUtil phoneNumberUtil, Phonenumber.PhoneNumber phoneNumber, StringBuilder sb, String[] strArr) {
                    return com.google.i18n.phonenumbers.b.a(phoneNumberUtil, phoneNumber, sb, strArr);
                }
            }

            public d(String str, int i) {
                super(str, i, null);
            }

            @Override // com.google.i18n.phonenumbers.PhoneNumberUtil.Leniency
            public boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil) {
                if (phoneNumberUtil.isValidNumber(phoneNumber) && com.google.i18n.phonenumbers.b.e(phoneNumber, str, phoneNumberUtil) && !com.google.i18n.phonenumbers.b.d(phoneNumber, str) && com.google.i18n.phonenumbers.b.l(phoneNumber, phoneNumberUtil)) {
                    return com.google.i18n.phonenumbers.b.c(phoneNumber, str, phoneNumberUtil, new a(this));
                }
                return false;
            }
        }

        static {
            a aVar = new a("POSSIBLE", 0);
            POSSIBLE = aVar;
            b bVar = new b("VALID", 1);
            VALID = bVar;
            c cVar = new c("STRICT_GROUPING", 2);
            STRICT_GROUPING = cVar;
            d dVar = new d("EXACT_GROUPING", 3);
            EXACT_GROUPING = dVar;
            $VALUES = new Leniency[]{aVar, bVar, cVar, dVar};
        }

        private Leniency(String str, int i) {
        }

        public static Leniency valueOf(String str) {
            return (Leniency) Enum.valueOf(Leniency.class, str);
        }

        public static Leniency[] values() {
            return (Leniency[]) $VALUES.clone();
        }

        public abstract boolean verify(Phonenumber.PhoneNumber phoneNumber, String str, PhoneNumberUtil phoneNumberUtil);

        public /* synthetic */ Leniency(String str, int i, a aVar) {
            this(str, i);
        }
    }

    /* loaded from: classes10.dex */
    public enum MatchType {
        NOT_A_NUMBER,
        NO_MATCH,
        SHORT_NSN_MATCH,
        NSN_MATCH,
        EXACT_MATCH
    }

    /* loaded from: classes10.dex */
    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    /* loaded from: classes10.dex */
    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    /* loaded from: classes10.dex */
    public enum ValidationResult {
        IS_POSSIBLE,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        TOO_LONG
    }

    /* loaded from: classes10.dex */
    public static class a implements MetadataLoader {
        @Override // com.google.i18n.phonenumbers.MetadataLoader
        public InputStream loadMetadata(String str) {
            return PhoneNumberUtil.class.getResourceAsStream(str);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Iterable<PhoneNumberMatch> {
        public final /* synthetic */ CharSequence h;
        public final /* synthetic */ String i;
        public final /* synthetic */ Leniency j;
        public final /* synthetic */ long k;

        public b(CharSequence charSequence, String str, Leniency leniency, long j) {
            this.h = charSequence;
            this.i = str;
            this.j = leniency;
            this.k = j;
        }

        @Override // java.lang.Iterable
        public Iterator<PhoneNumberMatch> iterator() {
            return new com.google.i18n.phonenumbers.b(PhoneNumberUtil.this, this.h, this.i, this.j, this.k);
        }
    }

    /* loaded from: classes10.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f11515a;
        public static final /* synthetic */ int[] b;
        public static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[PhoneNumberType.values().length];
            c = iArr;
            try {
                iArr[PhoneNumberType.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[PhoneNumberType.TOLL_FREE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[PhoneNumberType.MOBILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[PhoneNumberType.FIXED_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                c[PhoneNumberType.FIXED_LINE_OR_MOBILE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                c[PhoneNumberType.SHARED_COST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                c[PhoneNumberType.VOIP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                c[PhoneNumberType.PERSONAL_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                c[PhoneNumberType.PAGER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                c[PhoneNumberType.UAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                c[PhoneNumberType.VOICEMAIL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr2 = new int[PhoneNumberFormat.values().length];
            b = iArr2;
            try {
                iArr2[PhoneNumberFormat.E164.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                b[PhoneNumberFormat.INTERNATIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                b[PhoneNumberFormat.RFC3966.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                b[PhoneNumberFormat.NATIONAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused15) {
            }
            int[] iArr3 = new int[Phonenumber.PhoneNumber.CountryCodeSource.values().length];
            f11515a = iArr3;
            try {
                iArr3[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f11515a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD.ordinal()] = 2;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f11515a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f11515a[Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused19) {
            }
        }
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(52, "1");
        hashMap.put(54, BleConst.GetDeviceBatteryLevel);
        l = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put('0', '0');
        hashMap2.put('1', '1');
        hashMap2.put('2', '2');
        hashMap2.put('3', '3');
        hashMap2.put('4', '4');
        hashMap2.put('5', '5');
        hashMap2.put('6', '6');
        hashMap2.put('7', '7');
        hashMap2.put('8', '8');
        hashMap2.put('9', '9');
        HashMap hashMap3 = new HashMap(40);
        hashMap3.put('A', '2');
        hashMap3.put('B', '2');
        hashMap3.put('C', '2');
        hashMap3.put('D', '3');
        hashMap3.put('E', '3');
        hashMap3.put('F', '3');
        hashMap3.put('G', '4');
        hashMap3.put('H', '4');
        hashMap3.put('I', '4');
        hashMap3.put('J', '5');
        hashMap3.put('K', '5');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), '5');
        hashMap3.put('M', '6');
        hashMap3.put('N', '6');
        hashMap3.put('O', '6');
        hashMap3.put('P', '7');
        hashMap3.put('Q', '7');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR), '7');
        hashMap3.put('S', '7');
        hashMap3.put('T', '8');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), '8');
        hashMap3.put('V', '8');
        hashMap3.put('W', '9');
        hashMap3.put('X', '9');
        hashMap3.put('Y', '9');
        hashMap3.put(Character.valueOf(Matrix.MATRIX_TYPE_ZERO), '9');
        Map<Character, Character> unmodifiableMap = Collections.unmodifiableMap(hashMap3);
        n = unmodifiableMap;
        HashMap hashMap4 = new HashMap(100);
        hashMap4.putAll(unmodifiableMap);
        hashMap4.putAll(hashMap2);
        o = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.putAll(hashMap2);
        hashMap5.put('+', '+');
        hashMap5.put('*', '*');
        m = Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        for (Character ch : unmodifiableMap.keySet()) {
            char charValue = ch.charValue();
            hashMap6.put(Character.valueOf(Character.toLowerCase(charValue)), Character.valueOf(charValue));
            hashMap6.put(Character.valueOf(charValue), Character.valueOf(charValue));
        }
        hashMap6.putAll(hashMap2);
        hashMap6.put(Character.valueOf(Soundex.SILENT_MARKER), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 65293, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8208, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8209, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8210, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put(Character.valueOf(Typography.ndash), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put(Character.valueOf(Typography.mdash), Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8213, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put((char) 8722, Character.valueOf(Soundex.SILENT_MARKER));
        hashMap6.put('/', '/');
        hashMap6.put((char) 65295, '/');
        hashMap6.put(' ', ' ');
        hashMap6.put((char) 12288, ' ');
        hashMap6.put((char) 8288, ' ');
        hashMap6.put('.', '.');
        hashMap6.put((char) 65294, '.');
        p = Collections.unmodifiableMap(hashMap6);
        q = Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
        Map<Character, Character> map = n;
        String valueOf = String.valueOf(Arrays.toString(map.keySet().toArray()).replaceAll("[, \\[\\]]", ""));
        String valueOf2 = String.valueOf(Arrays.toString(map.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
        String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        r = concat;
        s = Pattern.compile("[+＋]+");
        t = Pattern.compile("[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～]+");
        u = Pattern.compile("(\\p{Nd})");
        v = Pattern.compile("[+＋\\p{Nd}]");
        w = Pattern.compile("[\\\\/] *x");
        x = Pattern.compile("[[\\P{N}&&\\P{L}]&&[^#]]+$");
        y = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
        String valueOf3 = String.valueOf(concat);
        StringBuilder sb = new StringBuilder("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*".length() + 2 + valueOf3.length() + "\\p{Nd}".length());
        sb.append("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  \u00ad\u200b\u2060\u3000()（）［］.\\[\\]/~⁓∼～*");
        sb.append(valueOf3);
        sb.append("\\p{Nd}");
        sb.append("]*");
        String sb2 = sb.toString();
        z = sb2;
        String e = e("xｘ#＃~～".length() != 0 ? Constants.SEPARATOR_COMMA.concat("xｘ#＃~～") : new String(Constants.SEPARATOR_COMMA));
        A = e;
        B = e("xｘ#＃~～");
        String valueOf4 = String.valueOf(e);
        StringBuilder sb3 = new StringBuilder(valueOf4.length() + 5);
        sb3.append("(?:");
        sb3.append(valueOf4);
        sb3.append(")$");
        C = Pattern.compile(sb3.toString(), 66);
        String valueOf5 = String.valueOf(sb2);
        String valueOf6 = String.valueOf(e);
        StringBuilder sb4 = new StringBuilder(valueOf5.length() + 5 + valueOf6.length());
        sb4.append(valueOf5);
        sb4.append("(?:");
        sb4.append(valueOf6);
        sb4.append(")?");
        D = Pattern.compile(sb4.toString(), 66);
        E = Pattern.compile("(\\D+)");
        F = Pattern.compile("(\\$\\d)");
        G = Pattern.compile("\\$NP");
        H = Pattern.compile("\\$FG");
        I = Pattern.compile("\\$CC");
        J = Pattern.compile("\\(?\\$1\\)?");
        K = null;
    }

    public PhoneNumberUtil(String str, MetadataLoader metadataLoader, Map<Integer, List<String>> map) {
        this.h = str;
        this.i = metadataLoader;
        this.f11514a = map;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() == 1 && REGION_CODE_FOR_NON_GEO_ENTITY.equals(value.get(0))) {
                this.g.add(entry.getKey());
            } else {
                this.f.addAll(value);
            }
        }
        if (this.f.remove(REGION_CODE_FOR_NON_GEO_ENTITY)) {
            k.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.b.addAll(map.get(1));
    }

    public static boolean D(String str) {
        if (str.length() < 2) {
            return false;
        }
        return D.matcher(str).matches();
    }

    public static Phonemetadata.PhoneMetadataCollection E(ObjectInputStream objectInputStream) {
        Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
        try {
            try {
                try {
                    phoneMetadataCollection.readExternal(objectInputStream);
                    objectInputStream.close();
                } catch (IOException e) {
                    k.log(Level.WARNING, "error reading input (ignored)", (Throwable) e);
                    objectInputStream.close();
                }
            } catch (Throwable th) {
                try {
                    objectInputStream.close();
                } catch (IOException e2) {
                    k.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e2);
                }
                throw th;
            }
        } catch (IOException e3) {
            k.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e3);
        }
        return phoneMetadataCollection;
    }

    public static String L(String str) {
        if (y.matcher(str).matches()) {
            return P(str, o, true);
        }
        return normalizeDigitsOnly(str);
    }

    public static void M(StringBuilder sb) {
        sb.replace(0, sb.length(), L(sb.toString()));
    }

    public static String N(String str) {
        return P(str, m, true);
    }

    public static StringBuilder O(String str, boolean z2) {
        char[] charArray;
        StringBuilder sb = new StringBuilder(str.length());
        for (char c2 : str.toCharArray()) {
            int digit = Character.digit(c2, 10);
            if (digit != -1) {
                sb.append(digit);
            } else if (z2) {
                sb.append(c2);
            }
        }
        return sb;
    }

    public static String P(String str, Map<Character, Character> map, boolean z2) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            Character ch = map.get(Character.valueOf(Character.toUpperCase(charAt)));
            if (ch != null) {
                sb.append(ch);
            } else if (!z2) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static synchronized void U(PhoneNumberUtil phoneNumberUtil) {
        synchronized (PhoneNumberUtil.class) {
            K = phoneNumberUtil;
        }
    }

    public static void V(String str, Phonenumber.PhoneNumber phoneNumber) {
        if (str.length() <= 1 || str.charAt(0) != '0') {
            return;
        }
        phoneNumber.setItalianLeadingZero(true);
        int i = 1;
        while (i < str.length() - 1 && str.charAt(i) == '0') {
            i++;
        }
        if (i != 1) {
            phoneNumber.setNumberOfLeadingZeros(i);
        }
    }

    public static String convertAlphaCharactersInNumber(String str) {
        return P(str, o, false);
    }

    public static PhoneNumberUtil createInstance(MetadataLoader metadataLoader) {
        if (metadataLoader != null) {
            return new PhoneNumberUtil("/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto", metadataLoader, CountryCodeToRegionCodeMap.a());
        }
        throw new IllegalArgumentException("metadataLoader could not be null.");
    }

    public static String e(String str) {
        String valueOf = String.valueOf(str);
        StringBuilder sb = new StringBuilder(";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[".length() + 48 + valueOf.length() + "(\\p{Nd}{1,7})".length() + "\\p{Nd}".length());
        sb.append(";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[");
        sb.append(valueOf);
        sb.append("]|int|anexo|ｉｎｔ)");
        sb.append("[:\\.．]?[  \\t,-]*");
        sb.append("(\\p{Nd}{1,7})");
        sb.append("#?|");
        sb.append("[- ]+(");
        sb.append("\\p{Nd}");
        sb.append("{1,5})#");
        return sb.toString();
    }

    public static String g(String str) {
        Matcher matcher = v.matcher(str);
        if (matcher.find()) {
            String substring = str.substring(matcher.start());
            Matcher matcher2 = x.matcher(substring);
            if (matcher2.find()) {
                substring = substring.substring(0, matcher2.start());
                Logger logger = k;
                Level level = Level.FINER;
                String valueOf = String.valueOf(substring);
                logger.log(level, valueOf.length() != 0 ? "Stripped trailing characters: ".concat(valueOf) : new String("Stripped trailing characters: "));
            }
            Matcher matcher3 = w.matcher(substring);
            return matcher3.find() ? substring.substring(0, matcher3.start()) : substring;
        }
        return "";
    }

    public static String getCountryMobileToken(int i) {
        Map<Integer, String> map = l;
        return map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)) : "";
    }

    public static synchronized PhoneNumberUtil getInstance() {
        PhoneNumberUtil phoneNumberUtil;
        synchronized (PhoneNumberUtil.class) {
            if (K == null) {
                U(createInstance(j));
            }
            phoneNumberUtil = K;
        }
        return phoneNumberUtil;
    }

    public static boolean l(String str) {
        return str.length() == 0 || J.matcher(str).matches();
    }

    public static String normalizeDigitsOnly(String str) {
        return O(str, false).toString();
    }

    public boolean A(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return this.e.getPatternForRegex(phoneNumberDesc.getPossibleNumberPattern()).matcher(str).matches();
    }

    public final boolean B(Phonemetadata.PhoneMetadata phoneMetadata, String str) {
        return W(this.e.getPatternForRegex(phoneMetadata.getGeneralDesc().getPossibleNumberPattern()), str) == ValidationResult.TOO_SHORT;
    }

    public final boolean C(String str) {
        return str != null && this.f.contains(str);
    }

    public void F(String str, String str2, int i, MetadataLoader metadataLoader) {
        boolean equals = REGION_CODE_FOR_NON_GEO_ENTITY.equals(str2);
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(equals ? String.valueOf(i) : str2);
        StringBuilder sb = new StringBuilder(valueOf.length() + 1 + valueOf2.length());
        sb.append(valueOf);
        sb.append("_");
        sb.append(valueOf2);
        String sb2 = sb.toString();
        InputStream loadMetadata = metadataLoader.loadMetadata(sb2);
        if (loadMetadata == null) {
            Logger logger = k;
            Level level = Level.SEVERE;
            String valueOf3 = String.valueOf(sb2);
            logger.log(level, valueOf3.length() != 0 ? "missing metadata: ".concat(valueOf3) : new String("missing metadata: "));
            String valueOf4 = String.valueOf(sb2);
            throw new IllegalStateException(valueOf4.length() != 0 ? "missing metadata: ".concat(valueOf4) : new String("missing metadata: "));
        }
        try {
            List<Phonemetadata.PhoneMetadata> metadataList = E(new ObjectInputStream(loadMetadata)).getMetadataList();
            if (metadataList.isEmpty()) {
                Logger logger2 = k;
                Level level2 = Level.SEVERE;
                String valueOf5 = String.valueOf(sb2);
                logger2.log(level2, valueOf5.length() != 0 ? "empty metadata: ".concat(valueOf5) : new String("empty metadata: "));
                String valueOf6 = String.valueOf(sb2);
                throw new IllegalStateException(valueOf6.length() != 0 ? "empty metadata: ".concat(valueOf6) : new String("empty metadata: "));
            }
            if (metadataList.size() > 1) {
                Logger logger3 = k;
                Level level3 = Level.WARNING;
                String valueOf7 = String.valueOf(sb2);
                logger3.log(level3, valueOf7.length() != 0 ? "invalid metadata (too many entries): ".concat(valueOf7) : new String("invalid metadata (too many entries): "));
            }
            Phonemetadata.PhoneMetadata phoneMetadata = metadataList.get(0);
            if (equals) {
                this.d.put(Integer.valueOf(i), phoneMetadata);
            } else {
                this.c.put(str2, phoneMetadata);
            }
        } catch (IOException e) {
            Logger logger4 = k;
            Level level4 = Level.SEVERE;
            String valueOf8 = String.valueOf(sb2);
            logger4.log(level4, valueOf8.length() != 0 ? "cannot load/parse metadata: ".concat(valueOf8) : new String("cannot load/parse metadata: "), (Throwable) e);
            String valueOf9 = String.valueOf(sb2);
            throw new RuntimeException(valueOf9.length() != 0 ? "cannot load/parse metadata: ".concat(valueOf9) : new String("cannot load/parse metadata: "), e);
        }
    }

    public final void G(Phonenumber.PhoneNumber phoneNumber, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        if (!phoneNumber.hasExtension() || phoneNumber.getExtension().length() <= 0) {
            return;
        }
        if (phoneNumberFormat == PhoneNumberFormat.RFC3966) {
            sb.append(";ext=");
            sb.append(phoneNumber.getExtension());
        } else if (phoneMetadata.hasPreferredExtnPrefix()) {
            sb.append(phoneMetadata.getPreferredExtnPrefix());
            sb.append(phoneNumber.getExtension());
        } else {
            sb.append(" ext. ");
            sb.append(phoneNumber.getExtension());
        }
    }

    public int H(String str, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb, boolean z2, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException {
        if (str.length() == 0) {
            return 0;
        }
        StringBuilder sb2 = new StringBuilder(str);
        Phonenumber.PhoneNumber.CountryCodeSource J2 = J(sb2, phoneMetadata != null ? phoneMetadata.getInternationalPrefix() : "NonMatch");
        if (z2) {
            phoneNumber.setCountryCodeSource(J2);
        }
        if (J2 != Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            if (sb2.length() > 2) {
                int f = f(sb2, sb);
                if (f != 0) {
                    phoneNumber.setCountryCode(f);
                    return f;
                }
                throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Country calling code supplied was not recognised.");
            }
            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_AFTER_IDD, "Phone number had an IDD, but after this was not long enough to be a viable phone number.");
        }
        if (phoneMetadata != null) {
            int countryCode = phoneMetadata.getCountryCode();
            String valueOf = String.valueOf(countryCode);
            String sb3 = sb2.toString();
            if (sb3.startsWith(valueOf)) {
                StringBuilder sb4 = new StringBuilder(sb3.substring(valueOf.length()));
                Phonemetadata.PhoneNumberDesc generalDesc = phoneMetadata.getGeneralDesc();
                Pattern patternForRegex = this.e.getPatternForRegex(generalDesc.getNationalNumberPattern());
                K(sb4, phoneMetadata, null);
                Pattern patternForRegex2 = this.e.getPatternForRegex(generalDesc.getPossibleNumberPattern());
                if ((!patternForRegex.matcher(sb2).matches() && patternForRegex.matcher(sb4).matches()) || W(patternForRegex2, sb2.toString()) == ValidationResult.TOO_LONG) {
                    sb.append((CharSequence) sb4);
                    if (z2) {
                        phoneNumber.setCountryCodeSource(Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN);
                    }
                    phoneNumber.setCountryCode(countryCode);
                    return countryCode;
                }
            }
        }
        phoneNumber.setCountryCode(0);
        return 0;
    }

    public String I(StringBuilder sb) {
        Matcher matcher = C.matcher(sb);
        if (matcher.find() && D(sb.substring(0, matcher.start()))) {
            int groupCount = matcher.groupCount();
            for (int i = 1; i <= groupCount; i++) {
                if (matcher.group(i) != null) {
                    String group = matcher.group(i);
                    sb.delete(matcher.start(), sb.length());
                    return group;
                }
            }
            return "";
        }
        return "";
    }

    public Phonenumber.PhoneNumber.CountryCodeSource J(StringBuilder sb, String str) {
        if (sb.length() == 0) {
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
        }
        Matcher matcher = s.matcher(sb);
        if (matcher.lookingAt()) {
            sb.delete(0, matcher.end());
            M(sb);
            return Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
        }
        Pattern patternForRegex = this.e.getPatternForRegex(str);
        M(sb);
        return R(patternForRegex, sb) ? Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_IDD : Phonenumber.PhoneNumber.CountryCodeSource.FROM_DEFAULT_COUNTRY;
    }

    public boolean K(StringBuilder sb, Phonemetadata.PhoneMetadata phoneMetadata, StringBuilder sb2) {
        int length = sb.length();
        String nationalPrefixForParsing = phoneMetadata.getNationalPrefixForParsing();
        if (length != 0 && nationalPrefixForParsing.length() != 0) {
            Matcher matcher = this.e.getPatternForRegex(nationalPrefixForParsing).matcher(sb);
            if (matcher.lookingAt()) {
                Pattern patternForRegex = this.e.getPatternForRegex(phoneMetadata.getGeneralDesc().getNationalNumberPattern());
                boolean matches = patternForRegex.matcher(sb).matches();
                int groupCount = matcher.groupCount();
                String nationalPrefixTransformRule = phoneMetadata.getNationalPrefixTransformRule();
                if (nationalPrefixTransformRule != null && nationalPrefixTransformRule.length() != 0 && matcher.group(groupCount) != null) {
                    StringBuilder sb3 = new StringBuilder(sb);
                    sb3.replace(0, length, matcher.replaceFirst(nationalPrefixTransformRule));
                    if (!matches || patternForRegex.matcher(sb3.toString()).matches()) {
                        if (sb2 != null && groupCount > 1) {
                            sb2.append(matcher.group(1));
                        }
                        sb.replace(0, sb.length(), sb3.toString());
                        return true;
                    }
                    return false;
                } else if (!matches || patternForRegex.matcher(sb.substring(matcher.end())).matches()) {
                    if (sb2 != null && groupCount > 0 && matcher.group(groupCount) != null) {
                        sb2.append(matcher.group(1));
                    }
                    sb.delete(0, matcher.end());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public final void Q(String str, String str2, boolean z2, boolean z3, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException {
        int H2;
        if (str != null) {
            if (str.length() <= 250) {
                StringBuilder sb = new StringBuilder();
                a(str, sb);
                if (D(sb.toString())) {
                    if (z3 && !c(sb.toString(), str2)) {
                        throw new NumberParseException(NumberParseException.ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
                    }
                    if (z2) {
                        phoneNumber.setRawInput(str);
                    }
                    String I2 = I(sb);
                    if (I2.length() > 0) {
                        phoneNumber.setExtension(I2);
                    }
                    Phonemetadata.PhoneMetadata o2 = o(str2);
                    StringBuilder sb2 = new StringBuilder();
                    try {
                        H2 = H(sb.toString(), o2, sb2, z2, phoneNumber);
                    } catch (NumberParseException e) {
                        Matcher matcher = s.matcher(sb.toString());
                        NumberParseException.ErrorType errorType = e.getErrorType();
                        NumberParseException.ErrorType errorType2 = NumberParseException.ErrorType.INVALID_COUNTRY_CODE;
                        if (errorType == errorType2 && matcher.lookingAt()) {
                            H2 = H(sb.substring(matcher.end()), o2, sb2, z2, phoneNumber);
                            if (H2 == 0) {
                                throw new NumberParseException(errorType2, "Could not interpret numbers after plus-sign.");
                            }
                        } else {
                            throw new NumberParseException(e.getErrorType(), e.getMessage());
                        }
                    }
                    if (H2 != 0) {
                        String regionCodeForCountryCode = getRegionCodeForCountryCode(H2);
                        if (!regionCodeForCountryCode.equals(str2)) {
                            o2 = p(H2, regionCodeForCountryCode);
                        }
                    } else {
                        M(sb);
                        sb2.append((CharSequence) sb);
                        if (str2 != null) {
                            phoneNumber.setCountryCode(o2.getCountryCode());
                        } else if (z2) {
                            phoneNumber.clearCountryCodeSource();
                        }
                    }
                    if (sb2.length() >= 2) {
                        if (o2 != null) {
                            StringBuilder sb3 = new StringBuilder();
                            StringBuilder sb4 = new StringBuilder(sb2);
                            K(sb4, o2, sb3);
                            if (!B(o2, sb4.toString())) {
                                if (z2) {
                                    phoneNumber.setPreferredDomesticCarrierCode(sb3.toString());
                                }
                                sb2 = sb4;
                            }
                        }
                        int length = sb2.length();
                        if (length < 2) {
                            throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
                        }
                        if (length <= 17) {
                            V(sb2.toString(), phoneNumber);
                            phoneNumber.setNationalNumber(Long.parseLong(sb2.toString()));
                            return;
                        }
                        throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
                    }
                    throw new NumberParseException(NumberParseException.ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
                }
                throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
            }
            throw new NumberParseException(NumberParseException.ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        }
        throw new NumberParseException(NumberParseException.ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
    }

    public final boolean R(Pattern pattern, StringBuilder sb) {
        Matcher matcher = pattern.matcher(sb);
        if (matcher.lookingAt()) {
            int end = matcher.end();
            Matcher matcher2 = u.matcher(sb.substring(end));
            if (matcher2.find() && normalizeDigitsOnly(matcher2.group(1)).equals(BleConst.GetDeviceTime)) {
                return false;
            }
            sb.delete(0, end);
            return true;
        }
        return false;
    }

    public final void S(int i, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        int i2 = c.b[phoneNumberFormat.ordinal()];
        if (i2 == 1) {
            sb.insert(0, i).insert(0, '+');
        } else if (i2 == 2) {
            sb.insert(0, HexStringBuilder.DEFAULT_SEPARATOR).insert(0, i).insert(0, '+');
        } else if (i2 != 3) {
        } else {
            sb.insert(0, "-").insert(0, i).insert(0, '+').insert(0, "tel:");
        }
    }

    public final boolean T(String str, String str2, String str3) {
        String normalizeDigitsOnly = normalizeDigitsOnly(str);
        if (normalizeDigitsOnly.startsWith(str2)) {
            try {
                return isValidNumber(parse(normalizeDigitsOnly.substring(str2.length()), str3));
            } catch (NumberParseException unused) {
            }
        }
        return false;
    }

    public final ValidationResult W(Pattern pattern, String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return ValidationResult.IS_POSSIBLE;
        }
        if (matcher.lookingAt()) {
            return ValidationResult.TOO_LONG;
        }
        return ValidationResult.TOO_SHORT;
    }

    public final void a(String str, StringBuilder sb) {
        int indexOf = str.indexOf(";phone-context=");
        if (indexOf > 0) {
            int i = indexOf + 15;
            if (str.charAt(i) == '+') {
                int indexOf2 = str.indexOf(59, i);
                if (indexOf2 > 0) {
                    sb.append(str.substring(i, indexOf2));
                } else {
                    sb.append(str.substring(i));
                }
            }
            int indexOf3 = str.indexOf("tel:");
            sb.append(str.substring(indexOf3 >= 0 ? indexOf3 + 4 : 0, indexOf));
        } else {
            sb.append(g(str));
        }
        int indexOf4 = sb.indexOf(";isub=");
        if (indexOf4 > 0) {
            sb.delete(indexOf4, sb.length());
        }
    }

    public boolean b(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata o2 = o(getRegionCodeForNumber(phoneNumber));
        if (o2 == null) {
            return true;
        }
        return !z(getNationalSignificantNumber(phoneNumber), o2.getNoInternationalDialling());
    }

    public final boolean c(String str, String str2) {
        if (C(str2)) {
            return true;
        }
        return (str == null || str.length() == 0 || !s.matcher(str).lookingAt()) ? false : true;
    }

    public Phonemetadata.NumberFormat d(List<Phonemetadata.NumberFormat> list, String str) {
        for (Phonemetadata.NumberFormat numberFormat : list) {
            int leadingDigitsPatternSize = numberFormat.leadingDigitsPatternSize();
            if (leadingDigitsPatternSize == 0 || this.e.getPatternForRegex(numberFormat.getLeadingDigitsPattern(leadingDigitsPatternSize - 1)).matcher(str).lookingAt()) {
                if (this.e.getPatternForRegex(numberFormat.getPattern()).matcher(str).matches()) {
                    return numberFormat;
                }
            }
        }
        return null;
    }

    public int f(StringBuilder sb, StringBuilder sb2) {
        if (sb.length() != 0 && sb.charAt(0) != '0') {
            int length = sb.length();
            for (int i = 1; i <= 3 && i <= length; i++) {
                int parseInt = Integer.parseInt(sb.substring(0, i));
                if (this.f11514a.containsKey(Integer.valueOf(parseInt))) {
                    sb2.append(sb.substring(i));
                    return parseInt;
                }
            }
        }
        return 0;
    }

    public Iterable<PhoneNumberMatch> findNumbers(CharSequence charSequence, String str) {
        return findNumbers(charSequence, str, Leniency.VALID, Long.MAX_VALUE);
    }

    public String format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat) {
        if (phoneNumber.getNationalNumber() == 0 && phoneNumber.hasRawInput()) {
            String rawInput = phoneNumber.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder sb = new StringBuilder(20);
        format(phoneNumber, phoneNumberFormat, sb);
        return sb.toString();
    }

    public String formatByPattern(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, List<Phonemetadata.NumberFormat> list) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (v(countryCode)) {
            Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
            StringBuilder sb = new StringBuilder(20);
            Phonemetadata.NumberFormat d = d(list, nationalSignificantNumber);
            if (d == null) {
                sb.append(nationalSignificantNumber);
            } else {
                Phonemetadata.NumberFormat numberFormat = new Phonemetadata.NumberFormat();
                numberFormat.mergeFrom(d);
                String nationalPrefixFormattingRule = d.getNationalPrefixFormattingRule();
                if (nationalPrefixFormattingRule.length() > 0) {
                    String nationalPrefix = p2.getNationalPrefix();
                    if (nationalPrefix.length() > 0) {
                        numberFormat.setNationalPrefixFormattingRule(H.matcher(G.matcher(nationalPrefixFormattingRule).replaceFirst(nationalPrefix)).replaceFirst("\\$1"));
                    } else {
                        numberFormat.clearNationalPrefixFormattingRule();
                    }
                }
                sb.append(j(nationalSignificantNumber, numberFormat, phoneNumberFormat));
            }
            G(phoneNumber, p2, phoneNumberFormat, sb);
            S(countryCode, phoneNumberFormat, sb);
            return sb.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatInOriginalFormat(Phonenumber.PhoneNumber phoneNumber, String str) {
        String format;
        String nationalPrefixFormattingRule;
        int indexOf;
        if (phoneNumber.hasRawInput() && (u(phoneNumber) || !t(phoneNumber))) {
            return phoneNumber.getRawInput();
        }
        if (!phoneNumber.hasCountryCodeSource()) {
            return format(phoneNumber, PhoneNumberFormat.NATIONAL);
        }
        int i = c.f11515a[phoneNumber.getCountryCodeSource().ordinal()];
        if (i == 1) {
            format = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
        } else if (i == 2) {
            format = formatOutOfCountryCallingNumber(phoneNumber, str);
        } else if (i != 3) {
            String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
            String nddPrefixForRegion = getNddPrefixForRegion(regionCodeForCountryCode, true);
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
            format = format(phoneNumber, phoneNumberFormat);
            if (nddPrefixForRegion != null && nddPrefixForRegion.length() != 0 && !T(phoneNumber.getRawInput(), nddPrefixForRegion, regionCodeForCountryCode)) {
                Phonemetadata.NumberFormat d = d(o(regionCodeForCountryCode).numberFormats(), getNationalSignificantNumber(phoneNumber));
                if (d != null && (indexOf = (nationalPrefixFormattingRule = d.getNationalPrefixFormattingRule()).indexOf("$1")) > 0 && normalizeDigitsOnly(nationalPrefixFormattingRule.substring(0, indexOf)).length() != 0) {
                    Phonemetadata.NumberFormat numberFormat = new Phonemetadata.NumberFormat();
                    numberFormat.mergeFrom(d);
                    numberFormat.clearNationalPrefixFormattingRule();
                    ArrayList arrayList = new ArrayList(1);
                    arrayList.add(numberFormat);
                    format = formatByPattern(phoneNumber, phoneNumberFormat, arrayList);
                }
            }
        } else {
            format = format(phoneNumber, PhoneNumberFormat.INTERNATIONAL).substring(1);
        }
        String rawInput = phoneNumber.getRawInput();
        return (format == null || rawInput.length() <= 0 || N(format).equals(N(rawInput))) ? format : rawInput;
    }

    public String formatNationalNumberWithCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (v(countryCode)) {
            Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
            StringBuilder sb = new StringBuilder(20);
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.NATIONAL;
            sb.append(i(nationalSignificantNumber, p2, phoneNumberFormat, str));
            G(phoneNumber, p2, phoneNumberFormat, sb);
            S(countryCode, phoneNumberFormat, sb);
            return sb.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatNationalNumberWithPreferredCarrierCode(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (phoneNumber.hasPreferredDomesticCarrierCode()) {
            str = phoneNumber.getPreferredDomesticCarrierCode();
        }
        return formatNationalNumberWithCarrierCode(phoneNumber, str);
    }

    public String formatNumberForMobileDialing(Phonenumber.PhoneNumber phoneNumber, String str, boolean z2) {
        String format;
        int countryCode = phoneNumber.getCountryCode();
        String str2 = "";
        if (!v(countryCode)) {
            return phoneNumber.hasRawInput() ? phoneNumber.getRawInput() : "";
        }
        Phonenumber.PhoneNumber clearExtension = new Phonenumber.PhoneNumber().mergeFrom(phoneNumber).clearExtension();
        String regionCodeForCountryCode = getRegionCodeForCountryCode(countryCode);
        PhoneNumberType numberType = getNumberType(clearExtension);
        boolean z3 = false;
        boolean z4 = numberType != PhoneNumberType.UNKNOWN;
        if (str.equals(regionCodeForCountryCode)) {
            PhoneNumberType phoneNumberType = PhoneNumberType.FIXED_LINE;
            if (numberType == phoneNumberType || numberType == PhoneNumberType.MOBILE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE) {
                z3 = true;
            }
            if (regionCodeForCountryCode.equals("CO") && numberType == phoneNumberType) {
                format = formatNationalNumberWithCarrierCode(clearExtension, "3");
            } else if (regionCodeForCountryCode.equals("BR") && z3) {
                if (clearExtension.hasPreferredDomesticCarrierCode()) {
                    str2 = formatNationalNumberWithPreferredCarrierCode(clearExtension, "");
                }
            } else if (z4 && regionCodeForCountryCode.equals("HU")) {
                String valueOf = String.valueOf(getNddPrefixForRegion(regionCodeForCountryCode, true));
                String valueOf2 = String.valueOf(format(clearExtension, PhoneNumberFormat.NATIONAL));
                StringBuilder sb = new StringBuilder(valueOf.length() + 1 + valueOf2.length());
                sb.append(valueOf);
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(valueOf2);
                format = sb.toString();
            } else if (countryCode == 1) {
                Phonemetadata.PhoneMetadata o2 = o(str);
                if (b(clearExtension) && !B(o2, getNationalSignificantNumber(clearExtension))) {
                    format = format(clearExtension, PhoneNumberFormat.INTERNATIONAL);
                } else {
                    format = format(clearExtension, PhoneNumberFormat.NATIONAL);
                }
            } else if ((regionCodeForCountryCode.equals(REGION_CODE_FOR_NON_GEO_ENTITY) || ((regionCodeForCountryCode.equals("MX") || regionCodeForCountryCode.equals("CL")) && z3)) && b(clearExtension)) {
                format = format(clearExtension, PhoneNumberFormat.INTERNATIONAL);
            } else {
                format = format(clearExtension, PhoneNumberFormat.NATIONAL);
            }
            str2 = format;
        } else if (z4 && b(clearExtension)) {
            return format(clearExtension, z2 ? PhoneNumberFormat.INTERNATIONAL : PhoneNumberFormat.E164);
        }
        return z2 ? str2 : N(str2);
    }

    public String formatOutOfCountryCallingNumber(Phonenumber.PhoneNumber phoneNumber, String str) {
        if (!C(str)) {
            Logger logger = k;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(str);
            StringBuilder sb = new StringBuilder(valueOf.length() + 79);
            sb.append("Trying to format number from invalid region ");
            sb.append(valueOf);
            sb.append(". International formatting applied.");
            logger.log(level, sb.toString());
            return format(phoneNumber, PhoneNumberFormat.INTERNATIONAL);
        }
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        if (v(countryCode)) {
            if (countryCode == 1) {
                if (isNANPACountry(str)) {
                    String valueOf2 = String.valueOf(format(phoneNumber, PhoneNumberFormat.NATIONAL));
                    StringBuilder sb2 = new StringBuilder(valueOf2.length() + 12);
                    sb2.append(countryCode);
                    sb2.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    sb2.append(valueOf2);
                    return sb2.toString();
                }
            } else if (countryCode == m(str)) {
                return format(phoneNumber, PhoneNumberFormat.NATIONAL);
            }
            Phonemetadata.PhoneMetadata o2 = o(str);
            String internationalPrefix = o2.getInternationalPrefix();
            if (!q.matcher(internationalPrefix).matches()) {
                internationalPrefix = o2.hasPreferredInternationalPrefix() ? o2.getPreferredInternationalPrefix() : "";
            }
            Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
            StringBuilder sb3 = new StringBuilder(h(nationalSignificantNumber, p2, phoneNumberFormat));
            G(phoneNumber, p2, phoneNumberFormat, sb3);
            if (internationalPrefix.length() > 0) {
                sb3.insert(0, HexStringBuilder.DEFAULT_SEPARATOR).insert(0, countryCode).insert(0, HexStringBuilder.DEFAULT_SEPARATOR).insert(0, internationalPrefix);
            } else {
                S(countryCode, phoneNumberFormat, sb3);
            }
            return sb3.toString();
        }
        return nationalSignificantNumber;
    }

    public String formatOutOfCountryKeepingAlphaChars(Phonenumber.PhoneNumber phoneNumber, String str) {
        String str2;
        int indexOf;
        String rawInput = phoneNumber.getRawInput();
        if (rawInput.length() == 0) {
            return formatOutOfCountryCallingNumber(phoneNumber, str);
        }
        int countryCode = phoneNumber.getCountryCode();
        if (v(countryCode)) {
            String P = P(rawInput, p, true);
            String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
            if (nationalSignificantNumber.length() > 3 && (indexOf = P.indexOf(nationalSignificantNumber.substring(0, 3))) != -1) {
                P = P.substring(indexOf);
            }
            Phonemetadata.PhoneMetadata o2 = o(str);
            if (countryCode == 1) {
                if (isNANPACountry(str)) {
                    String valueOf = String.valueOf(P);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 12);
                    sb.append(countryCode);
                    sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                    sb.append(valueOf);
                    return sb.toString();
                }
            } else if (o2 != null && countryCode == m(str)) {
                Phonemetadata.NumberFormat d = d(o2.numberFormats(), nationalSignificantNumber);
                if (d == null) {
                    return P;
                }
                Phonemetadata.NumberFormat numberFormat = new Phonemetadata.NumberFormat();
                numberFormat.mergeFrom(d);
                numberFormat.setPattern("(\\d+)(.*)");
                numberFormat.setFormat("$1$2");
                return j(P, numberFormat, PhoneNumberFormat.NATIONAL);
            }
            if (o2 != null) {
                str2 = o2.getInternationalPrefix();
                if (!q.matcher(str2).matches()) {
                    str2 = o2.getPreferredInternationalPrefix();
                }
            } else {
                str2 = "";
            }
            StringBuilder sb2 = new StringBuilder(P);
            Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
            PhoneNumberFormat phoneNumberFormat = PhoneNumberFormat.INTERNATIONAL;
            G(phoneNumber, p2, phoneNumberFormat, sb2);
            if (str2.length() > 0) {
                sb2.insert(0, HexStringBuilder.DEFAULT_SEPARATOR).insert(0, countryCode).insert(0, HexStringBuilder.DEFAULT_SEPARATOR).insert(0, str2);
            } else {
                Logger logger = k;
                Level level = Level.WARNING;
                String valueOf2 = String.valueOf(str);
                StringBuilder sb3 = new StringBuilder(valueOf2.length() + 79);
                sb3.append("Trying to format number from invalid region ");
                sb3.append(valueOf2);
                sb3.append(". International formatting applied.");
                logger.log(level, sb3.toString());
                S(countryCode, phoneNumberFormat, sb2);
            }
            return sb2.toString();
        }
        return rawInput;
    }

    public AsYouTypeFormatter getAsYouTypeFormatter(String str) {
        return new AsYouTypeFormatter(str);
    }

    public int getCountryCodeForRegion(String str) {
        if (!C(str)) {
            Logger logger = k;
            Level level = Level.WARNING;
            if (str == null) {
                str = "null";
            }
            StringBuilder sb = new StringBuilder(str.length() + 43);
            sb.append("Invalid or missing region code (");
            sb.append(str);
            sb.append(") provided.");
            logger.log(level, sb.toString());
            return 0;
        }
        return m(str);
    }

    public Phonenumber.PhoneNumber getExampleNumber(String str) {
        return getExampleNumberForType(str, PhoneNumberType.FIXED_LINE);
    }

    public Phonenumber.PhoneNumber getExampleNumberForNonGeoEntity(int i) {
        Phonemetadata.PhoneMetadata n2 = n(i);
        if (n2 != null) {
            Phonemetadata.PhoneNumberDesc generalDesc = n2.getGeneralDesc();
            try {
                if (generalDesc.hasExampleNumber()) {
                    String valueOf = String.valueOf(generalDesc.getExampleNumber());
                    StringBuilder sb = new StringBuilder(valueOf.length() + 12);
                    sb.append("+");
                    sb.append(i);
                    sb.append(valueOf);
                    return parse(sb.toString(), "ZZ");
                }
                return null;
            } catch (NumberParseException e) {
                k.log(Level.SEVERE, e.toString());
                return null;
            }
        }
        Logger logger = k;
        Level level = Level.WARNING;
        StringBuilder sb2 = new StringBuilder(61);
        sb2.append("Invalid or unknown country calling code provided: ");
        sb2.append(i);
        logger.log(level, sb2.toString());
        return null;
    }

    public Phonenumber.PhoneNumber getExampleNumberForType(String str, PhoneNumberType phoneNumberType) {
        if (!C(str)) {
            Logger logger = k;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(str);
            logger.log(level, valueOf.length() != 0 ? "Invalid or unknown region code provided: ".concat(valueOf) : new String("Invalid or unknown region code provided: "));
            return null;
        }
        Phonemetadata.PhoneNumberDesc q2 = q(o(str), phoneNumberType);
        try {
            if (q2.hasExampleNumber()) {
                return parse(q2.getExampleNumber(), str);
            }
        } catch (NumberParseException e) {
            k.log(Level.SEVERE, e.toString());
        }
        return null;
    }

    public int getLengthOfGeographicalAreaCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata o2 = o(getRegionCodeForNumber(phoneNumber));
        if (o2 == null) {
            return 0;
        }
        if ((o2.hasNationalPrefix() || phoneNumber.isItalianLeadingZero()) && y(phoneNumber)) {
            return getLengthOfNationalDestinationCode(phoneNumber);
        }
        return 0;
    }

    public int getLengthOfNationalDestinationCode(Phonenumber.PhoneNumber phoneNumber) {
        Phonenumber.PhoneNumber phoneNumber2;
        if (phoneNumber.hasExtension()) {
            phoneNumber2 = new Phonenumber.PhoneNumber();
            phoneNumber2.mergeFrom(phoneNumber);
            phoneNumber2.clearExtension();
        } else {
            phoneNumber2 = phoneNumber;
        }
        String[] split = E.split(format(phoneNumber2, PhoneNumberFormat.INTERNATIONAL));
        if (split.length <= 3) {
            return 0;
        }
        if (getNumberType(phoneNumber) == PhoneNumberType.MOBILE && !getCountryMobileToken(phoneNumber.getCountryCode()).equals("")) {
            return split[2].length() + split[3].length();
        }
        return split[2].length();
    }

    public String getNationalSignificantNumber(Phonenumber.PhoneNumber phoneNumber) {
        StringBuilder sb = new StringBuilder();
        if (phoneNumber.isItalianLeadingZero()) {
            char[] cArr = new char[phoneNumber.getNumberOfLeadingZeros()];
            Arrays.fill(cArr, '0');
            sb.append(new String(cArr));
        }
        sb.append(phoneNumber.getNationalNumber());
        return sb.toString();
    }

    public String getNddPrefixForRegion(String str, boolean z2) {
        Phonemetadata.PhoneMetadata o2 = o(str);
        if (o2 == null) {
            Logger logger = k;
            Level level = Level.WARNING;
            if (str == null) {
                str = "null";
            }
            StringBuilder sb = new StringBuilder(str.length() + 43);
            sb.append("Invalid or missing region code (");
            sb.append(str);
            sb.append(") provided.");
            logger.log(level, sb.toString());
            return null;
        }
        String nationalPrefix = o2.getNationalPrefix();
        if (nationalPrefix.length() == 0) {
            return null;
        }
        return z2 ? nationalPrefix.replace("~", "") : nationalPrefix;
    }

    public PhoneNumberType getNumberType(Phonenumber.PhoneNumber phoneNumber) {
        Phonemetadata.PhoneMetadata p2 = p(phoneNumber.getCountryCode(), getRegionCodeForNumber(phoneNumber));
        if (p2 == null) {
            return PhoneNumberType.UNKNOWN;
        }
        return r(getNationalSignificantNumber(phoneNumber), p2);
    }

    public String getRegionCodeForCountryCode(int i) {
        List<String> list = this.f11514a.get(Integer.valueOf(i));
        return list == null ? "ZZ" : list.get(0);
    }

    public String getRegionCodeForNumber(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        List<String> list = this.f11514a.get(Integer.valueOf(countryCode));
        if (list == null) {
            String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
            Logger logger = k;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(nationalSignificantNumber);
            StringBuilder sb = new StringBuilder(valueOf.length() + 54);
            sb.append("Missing/invalid country_code (");
            sb.append(countryCode);
            sb.append(") for number ");
            sb.append(valueOf);
            logger.log(level, sb.toString());
            return null;
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            return s(phoneNumber, list);
        }
    }

    public List<String> getRegionCodesForCountryCode(int i) {
        List<String> list = this.f11514a.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>(0);
        }
        return Collections.unmodifiableList(list);
    }

    public Set<Integer> getSupportedGlobalNetworkCallingCodes() {
        return Collections.unmodifiableSet(this.g);
    }

    public Set<String> getSupportedRegions() {
        return Collections.unmodifiableSet(this.f);
    }

    public final String h(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat) {
        return i(str, phoneMetadata, phoneNumberFormat, null);
    }

    public final String i(String str, Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberFormat phoneNumberFormat, String str2) {
        Phonemetadata.NumberFormat d = d((phoneMetadata.intlNumberFormats().size() == 0 || phoneNumberFormat == PhoneNumberFormat.NATIONAL) ? phoneMetadata.numberFormats() : phoneMetadata.intlNumberFormats(), str);
        return d == null ? str : k(str, d, phoneNumberFormat, str2);
    }

    public boolean isAlphaNumber(String str) {
        if (D(str)) {
            StringBuilder sb = new StringBuilder(str);
            I(sb);
            return y.matcher(sb).matches();
        }
        return false;
    }

    public boolean isMobileNumberPortableRegion(String str) {
        Phonemetadata.PhoneMetadata o2 = o(str);
        if (o2 == null) {
            Logger logger = k;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(str);
            logger.log(level, valueOf.length() != 0 ? "Invalid or unknown region code provided: ".concat(valueOf) : new String("Invalid or unknown region code provided: "));
            return false;
        }
        return o2.isMobileNumberPortableRegion();
    }

    public boolean isNANPACountry(String str) {
        return this.b.contains(str);
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        Phonenumber.PhoneNumber phoneNumber3 = new Phonenumber.PhoneNumber();
        phoneNumber3.mergeFrom(phoneNumber);
        Phonenumber.PhoneNumber phoneNumber4 = new Phonenumber.PhoneNumber();
        phoneNumber4.mergeFrom(phoneNumber2);
        phoneNumber3.clearRawInput();
        phoneNumber3.clearCountryCodeSource();
        phoneNumber3.clearPreferredDomesticCarrierCode();
        phoneNumber4.clearRawInput();
        phoneNumber4.clearCountryCodeSource();
        phoneNumber4.clearPreferredDomesticCarrierCode();
        if (phoneNumber3.hasExtension() && phoneNumber3.getExtension().length() == 0) {
            phoneNumber3.clearExtension();
        }
        if (phoneNumber4.hasExtension() && phoneNumber4.getExtension().length() == 0) {
            phoneNumber4.clearExtension();
        }
        if (phoneNumber3.hasExtension() && phoneNumber4.hasExtension() && !phoneNumber3.getExtension().equals(phoneNumber4.getExtension())) {
            return MatchType.NO_MATCH;
        }
        int countryCode = phoneNumber3.getCountryCode();
        int countryCode2 = phoneNumber4.getCountryCode();
        if (countryCode != 0 && countryCode2 != 0) {
            if (phoneNumber3.exactlySameAs(phoneNumber4)) {
                return MatchType.EXACT_MATCH;
            }
            if (countryCode == countryCode2 && x(phoneNumber3, phoneNumber4)) {
                return MatchType.SHORT_NSN_MATCH;
            }
            return MatchType.NO_MATCH;
        }
        phoneNumber3.setCountryCode(countryCode2);
        if (phoneNumber3.exactlySameAs(phoneNumber4)) {
            return MatchType.NSN_MATCH;
        }
        if (x(phoneNumber3, phoneNumber4)) {
            return MatchType.SHORT_NSN_MATCH;
        }
        return MatchType.NO_MATCH;
    }

    public boolean isPossibleNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isPossibleNumberWithReason(phoneNumber) == ValidationResult.IS_POSSIBLE;
    }

    public ValidationResult isPossibleNumberWithReason(Phonenumber.PhoneNumber phoneNumber) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        int countryCode = phoneNumber.getCountryCode();
        if (!v(countryCode)) {
            return ValidationResult.INVALID_COUNTRY_CODE;
        }
        return W(this.e.getPatternForRegex(p(countryCode, getRegionCodeForCountryCode(countryCode)).getGeneralDesc().getPossibleNumberPattern()), nationalSignificantNumber);
    }

    public boolean isValidNumber(Phonenumber.PhoneNumber phoneNumber) {
        return isValidNumberForRegion(phoneNumber, getRegionCodeForNumber(phoneNumber));
    }

    public boolean isValidNumberForRegion(Phonenumber.PhoneNumber phoneNumber, String str) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata p2 = p(countryCode, str);
        if (p2 != null) {
            return (REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) || countryCode == m(str)) && r(getNationalSignificantNumber(phoneNumber), p2) != PhoneNumberType.UNKNOWN;
        }
        return false;
    }

    public String j(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat) {
        return k(str, numberFormat, phoneNumberFormat, null);
    }

    public final String k(String str, Phonemetadata.NumberFormat numberFormat, PhoneNumberFormat phoneNumberFormat, String str2) {
        String replaceAll;
        String format = numberFormat.getFormat();
        Matcher matcher = this.e.getPatternForRegex(numberFormat.getPattern()).matcher(str);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.NATIONAL;
        if (phoneNumberFormat == phoneNumberFormat2 && str2 != null && str2.length() > 0 && numberFormat.getDomesticCarrierCodeFormattingRule().length() > 0) {
            replaceAll = matcher.replaceAll(F.matcher(format).replaceFirst(I.matcher(numberFormat.getDomesticCarrierCodeFormattingRule()).replaceFirst(str2)));
        } else {
            String nationalPrefixFormattingRule = numberFormat.getNationalPrefixFormattingRule();
            if (phoneNumberFormat == phoneNumberFormat2 && nationalPrefixFormattingRule != null && nationalPrefixFormattingRule.length() > 0) {
                replaceAll = matcher.replaceAll(F.matcher(format).replaceFirst(nationalPrefixFormattingRule));
            } else {
                replaceAll = matcher.replaceAll(format);
            }
        }
        if (phoneNumberFormat == PhoneNumberFormat.RFC3966) {
            Matcher matcher2 = t.matcher(replaceAll);
            if (matcher2.lookingAt()) {
                replaceAll = matcher2.replaceFirst("");
            }
            return matcher2.reset(replaceAll).replaceAll("-");
        }
        return replaceAll;
    }

    public final int m(String str) {
        Phonemetadata.PhoneMetadata o2 = o(str);
        if (o2 == null) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Invalid region code: ".concat(valueOf) : new String("Invalid region code: "));
        }
        return o2.getCountryCode();
    }

    public Phonemetadata.PhoneMetadata n(int i) {
        synchronized (this.d) {
            if (this.f11514a.containsKey(Integer.valueOf(i))) {
                if (!this.d.containsKey(Integer.valueOf(i))) {
                    F(this.h, REGION_CODE_FOR_NON_GEO_ENTITY, i, this.i);
                }
                return this.d.get(Integer.valueOf(i));
            }
            return null;
        }
    }

    public Phonemetadata.PhoneMetadata o(String str) {
        if (C(str)) {
            synchronized (this.c) {
                if (!this.c.containsKey(str)) {
                    F(this.h, str, 0, this.i);
                }
            }
            return this.c.get(str);
        }
        return null;
    }

    public final Phonemetadata.PhoneMetadata p(int i, String str) {
        return REGION_CODE_FOR_NON_GEO_ENTITY.equals(str) ? n(i) : o(str);
    }

    public Phonenumber.PhoneNumber parse(String str, String str2) throws NumberParseException {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parse(str, str2, phoneNumber);
        return phoneNumber;
    }

    public Phonenumber.PhoneNumber parseAndKeepRawInput(String str, String str2) throws NumberParseException {
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        parseAndKeepRawInput(str, str2, phoneNumber);
        return phoneNumber;
    }

    public Phonemetadata.PhoneNumberDesc q(Phonemetadata.PhoneMetadata phoneMetadata, PhoneNumberType phoneNumberType) {
        switch (c.c[phoneNumberType.ordinal()]) {
            case 1:
                return phoneMetadata.getPremiumRate();
            case 2:
                return phoneMetadata.getTollFree();
            case 3:
                return phoneMetadata.getMobile();
            case 4:
            case 5:
                return phoneMetadata.getFixedLine();
            case 6:
                return phoneMetadata.getSharedCost();
            case 7:
                return phoneMetadata.getVoip();
            case 8:
                return phoneMetadata.getPersonalNumber();
            case 9:
                return phoneMetadata.getPager();
            case 10:
                return phoneMetadata.getUan();
            case 11:
                return phoneMetadata.getVoicemail();
            default:
                return phoneMetadata.getGeneralDesc();
        }
    }

    public final PhoneNumberType r(String str, Phonemetadata.PhoneMetadata phoneMetadata) {
        if (!z(str, phoneMetadata.getGeneralDesc())) {
            return PhoneNumberType.UNKNOWN;
        }
        if (z(str, phoneMetadata.getPremiumRate())) {
            return PhoneNumberType.PREMIUM_RATE;
        }
        if (z(str, phoneMetadata.getTollFree())) {
            return PhoneNumberType.TOLL_FREE;
        }
        if (z(str, phoneMetadata.getSharedCost())) {
            return PhoneNumberType.SHARED_COST;
        }
        if (z(str, phoneMetadata.getVoip())) {
            return PhoneNumberType.VOIP;
        }
        if (z(str, phoneMetadata.getPersonalNumber())) {
            return PhoneNumberType.PERSONAL_NUMBER;
        }
        if (z(str, phoneMetadata.getPager())) {
            return PhoneNumberType.PAGER;
        }
        if (z(str, phoneMetadata.getUan())) {
            return PhoneNumberType.UAN;
        }
        if (z(str, phoneMetadata.getVoicemail())) {
            return PhoneNumberType.VOICEMAIL;
        }
        if (z(str, phoneMetadata.getFixedLine())) {
            if (phoneMetadata.isSameMobileAndFixedLinePattern()) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            if (z(str, phoneMetadata.getMobile())) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            return PhoneNumberType.FIXED_LINE;
        } else if (!phoneMetadata.isSameMobileAndFixedLinePattern() && z(str, phoneMetadata.getMobile())) {
            return PhoneNumberType.MOBILE;
        } else {
            return PhoneNumberType.UNKNOWN;
        }
    }

    public final String s(Phonenumber.PhoneNumber phoneNumber, List<String> list) {
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        for (String str : list) {
            Phonemetadata.PhoneMetadata o2 = o(str);
            if (o2.hasLeadingDigits()) {
                if (this.e.getPatternForRegex(o2.getLeadingDigits()).matcher(nationalSignificantNumber).lookingAt()) {
                    return str;
                }
            } else if (r(nationalSignificantNumber, o2) != PhoneNumberType.UNKNOWN) {
                return str;
            }
        }
        return null;
    }

    public final boolean t(Phonenumber.PhoneNumber phoneNumber) {
        int countryCode = phoneNumber.getCountryCode();
        Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
        if (p2 == null) {
            return false;
        }
        return d(p2.numberFormats(), getNationalSignificantNumber(phoneNumber)) != null;
    }

    public boolean truncateTooLongNumber(Phonenumber.PhoneNumber phoneNumber) {
        if (isValidNumber(phoneNumber)) {
            return true;
        }
        Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
        phoneNumber2.mergeFrom(phoneNumber);
        long nationalNumber = phoneNumber.getNationalNumber();
        do {
            nationalNumber /= 10;
            phoneNumber2.setNationalNumber(nationalNumber);
            if (isPossibleNumberWithReason(phoneNumber2) == ValidationResult.TOO_SHORT || nationalNumber == 0) {
                return false;
            }
        } while (!isValidNumber(phoneNumber2));
        phoneNumber.setNationalNumber(nationalNumber);
        return true;
    }

    public final boolean u(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumber.isItalianLeadingZero() && !w(phoneNumber.getCountryCode());
    }

    public final boolean v(int i) {
        return this.f11514a.containsKey(Integer.valueOf(i));
    }

    public boolean w(int i) {
        Phonemetadata.PhoneMetadata p2 = p(i, getRegionCodeForCountryCode(i));
        if (p2 == null) {
            return false;
        }
        return p2.isLeadingZeroPossible();
    }

    public final boolean x(Phonenumber.PhoneNumber phoneNumber, Phonenumber.PhoneNumber phoneNumber2) {
        String valueOf = String.valueOf(phoneNumber.getNationalNumber());
        String valueOf2 = String.valueOf(phoneNumber2.getNationalNumber());
        return valueOf.endsWith(valueOf2) || valueOf2.endsWith(valueOf);
    }

    public boolean y(Phonenumber.PhoneNumber phoneNumber) {
        PhoneNumberType numberType = getNumberType(phoneNumber);
        return numberType == PhoneNumberType.FIXED_LINE || numberType == PhoneNumberType.FIXED_LINE_OR_MOBILE;
    }

    public boolean z(String str, Phonemetadata.PhoneNumberDesc phoneNumberDesc) {
        return A(str, phoneNumberDesc) && this.e.getPatternForRegex(phoneNumberDesc.getNationalNumberPattern()).matcher(str).matches();
    }

    public Iterable<PhoneNumberMatch> findNumbers(CharSequence charSequence, String str, Leniency leniency, long j2) {
        return new b(charSequence, str, leniency, j2);
    }

    public boolean isPossibleNumber(String str, String str2) {
        try {
            return isPossibleNumber(parse(str, str2));
        } catch (NumberParseException unused) {
            return false;
        }
    }

    public void parse(String str, String str2, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException {
        Q(str, str2, false, true, phoneNumber);
    }

    public void parseAndKeepRawInput(String str, String str2, Phonenumber.PhoneNumber phoneNumber) throws NumberParseException {
        Q(str, str2, true, true, phoneNumber);
    }

    public void format(Phonenumber.PhoneNumber phoneNumber, PhoneNumberFormat phoneNumberFormat, StringBuilder sb) {
        sb.setLength(0);
        int countryCode = phoneNumber.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(phoneNumber);
        PhoneNumberFormat phoneNumberFormat2 = PhoneNumberFormat.E164;
        if (phoneNumberFormat == phoneNumberFormat2) {
            sb.append(nationalSignificantNumber);
            S(countryCode, phoneNumberFormat2, sb);
        } else if (!v(countryCode)) {
            sb.append(nationalSignificantNumber);
        } else {
            Phonemetadata.PhoneMetadata p2 = p(countryCode, getRegionCodeForCountryCode(countryCode));
            sb.append(h(nationalSignificantNumber, p2, phoneNumberFormat));
            G(phoneNumber, p2, phoneNumberFormat, sb);
            S(countryCode, phoneNumberFormat, sb);
        }
    }

    public MatchType isNumberMatch(String str, String str2) {
        try {
            return isNumberMatch(parse(str, "ZZ"), str2);
        } catch (NumberParseException e) {
            if (e.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                try {
                    return isNumberMatch(parse(str2, "ZZ"), str);
                } catch (NumberParseException e2) {
                    if (e2.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                        try {
                            Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
                            Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                            Q(str, null, false, false, phoneNumber);
                            Q(str2, null, false, false, phoneNumber2);
                            return isNumberMatch(phoneNumber, phoneNumber2);
                        } catch (NumberParseException unused) {
                            return MatchType.NOT_A_NUMBER;
                        }
                    }
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }

    public MatchType isNumberMatch(Phonenumber.PhoneNumber phoneNumber, String str) {
        try {
            return isNumberMatch(phoneNumber, parse(str, "ZZ"));
        } catch (NumberParseException e) {
            if (e.getErrorType() == NumberParseException.ErrorType.INVALID_COUNTRY_CODE) {
                String regionCodeForCountryCode = getRegionCodeForCountryCode(phoneNumber.getCountryCode());
                try {
                    if (!regionCodeForCountryCode.equals("ZZ")) {
                        MatchType isNumberMatch = isNumberMatch(phoneNumber, parse(str, regionCodeForCountryCode));
                        return isNumberMatch == MatchType.EXACT_MATCH ? MatchType.NSN_MATCH : isNumberMatch;
                    }
                    Phonenumber.PhoneNumber phoneNumber2 = new Phonenumber.PhoneNumber();
                    Q(str, null, false, false, phoneNumber2);
                    return isNumberMatch(phoneNumber, phoneNumber2);
                } catch (NumberParseException unused) {
                    return MatchType.NOT_A_NUMBER;
                }
            }
            return MatchType.NOT_A_NUMBER;
        }
    }
}
