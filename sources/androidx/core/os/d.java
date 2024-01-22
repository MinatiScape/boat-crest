package androidx.core.os;

import android.os.Build;
import androidx.annotation.DoNotInline;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.szabh.smable3.entity.Languages;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes.dex */
public final class d implements e {
    public static final Locale[] c = new Locale[0];
    public static final Locale d = new Locale(Languages.DEFAULT_LANGUAGE, "XA");
    public static final Locale e = new Locale("ar", "XB");
    public static final Locale f = LocaleListCompat.forLanguageTagCompat("en-Latn");

    /* renamed from: a  reason: collision with root package name */
    public final Locale[] f1079a;
    @NonNull
    public final String b;

    @RequiresApi(21)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static String a(Locale locale) {
            return locale.getScript();
        }
    }

    public d(@NonNull Locale... localeArr) {
        if (localeArr.length == 0) {
            this.f1079a = c;
            this.b = "";
            return;
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < localeArr.length; i++) {
            Locale locale = localeArr[i];
            if (locale != null) {
                if (!hashSet.contains(locale)) {
                    Locale locale2 = (Locale) locale.clone();
                    arrayList.add(locale2);
                    k(sb, locale2);
                    if (i < localeArr.length - 1) {
                        sb.append(',');
                    }
                    hashSet.add(locale2);
                }
            } else {
                throw new NullPointerException("list[" + i + "] is null");
            }
        }
        this.f1079a = (Locale[]) arrayList.toArray(new Locale[0]);
        this.b = sb.toString();
    }

    public static String h(Locale locale) {
        if (Build.VERSION.SDK_INT >= 21) {
            String a2 = a.a(locale);
            if (!a2.isEmpty()) {
                return a2;
            }
        }
        return "";
    }

    public static boolean i(Locale locale) {
        return d.equals(locale) || e.equals(locale);
    }

    @IntRange(from = 0, to = 1)
    public static int j(Locale locale, Locale locale2) {
        if (locale.equals(locale2)) {
            return 1;
        }
        if (!locale.getLanguage().equals(locale2.getLanguage()) || i(locale) || i(locale2)) {
            return 0;
        }
        String h = h(locale);
        if (h.isEmpty()) {
            String country = locale.getCountry();
            return (country.isEmpty() || country.equals(locale2.getCountry())) ? 1 : 0;
        }
        return h.equals(h(locale2)) ? 1 : 0;
    }

    @VisibleForTesting
    public static void k(StringBuilder sb, Locale locale) {
        sb.append(locale.getLanguage());
        String country = locale.getCountry();
        if (country == null || country.isEmpty()) {
            return;
        }
        sb.append(Soundex.SILENT_MARKER);
        sb.append(locale.getCountry());
    }

    @Override // androidx.core.os.e
    public int a(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.f1079a;
            if (i >= localeArr.length) {
                return -1;
            }
            if (localeArr[i].equals(locale)) {
                return i;
            }
            i++;
        }
    }

    @Override // androidx.core.os.e
    public String b() {
        return this.b;
    }

    @Override // androidx.core.os.e
    @Nullable
    public Object c() {
        return null;
    }

    @Override // androidx.core.os.e
    public Locale d(@NonNull String[] strArr) {
        return e(Arrays.asList(strArr), false);
    }

    public final Locale e(Collection<String> collection, boolean z) {
        int f2 = f(collection, z);
        if (f2 == -1) {
            return null;
        }
        return this.f1079a[f2];
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        Locale[] localeArr = ((d) obj).f1079a;
        if (this.f1079a.length != localeArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            Locale[] localeArr2 = this.f1079a;
            if (i >= localeArr2.length) {
                return true;
            }
            if (!localeArr2[i].equals(localeArr[i])) {
                return false;
            }
            i++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r6 < Integer.MAX_VALUE) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int f(java.util.Collection<java.lang.String> r5, boolean r6) {
        /*
            r4 = this;
            java.util.Locale[] r0 = r4.f1079a
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 != r3) goto L8
            return r2
        L8:
            int r0 = r0.length
            if (r0 != 0) goto Ld
            r5 = -1
            return r5
        Ld:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == 0) goto L1e
            java.util.Locale r6 = androidx.core.os.d.f
            int r6 = r4.g(r6)
            if (r6 != 0) goto L1b
            return r2
        L1b:
            if (r6 >= r0) goto L1e
            goto L1f
        L1e:
            r6 = r0
        L1f:
            java.util.Iterator r5 = r5.iterator()
        L23:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L3e
            java.lang.Object r1 = r5.next()
            java.lang.String r1 = (java.lang.String) r1
            java.util.Locale r1 = androidx.core.os.LocaleListCompat.forLanguageTagCompat(r1)
            int r1 = r4.g(r1)
            if (r1 != 0) goto L3a
            return r2
        L3a:
            if (r1 >= r6) goto L23
            r6 = r1
            goto L23
        L3e:
            if (r6 != r0) goto L41
            return r2
        L41:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.os.d.f(java.util.Collection, boolean):int");
    }

    public final int g(Locale locale) {
        int i = 0;
        while (true) {
            Locale[] localeArr = this.f1079a;
            if (i >= localeArr.length) {
                return Integer.MAX_VALUE;
            }
            if (j(locale, localeArr[i]) > 0) {
                return i;
            }
            i++;
        }
    }

    @Override // androidx.core.os.e
    public Locale get(int i) {
        if (i >= 0) {
            Locale[] localeArr = this.f1079a;
            if (i < localeArr.length) {
                return localeArr[i];
            }
        }
        return null;
    }

    public int hashCode() {
        int i = 1;
        for (Locale locale : this.f1079a) {
            i = (i * 31) + locale.hashCode();
        }
        return i;
    }

    @Override // androidx.core.os.e
    public boolean isEmpty() {
        return this.f1079a.length == 0;
    }

    @Override // androidx.core.os.e
    public int size() {
        return this.f1079a.length;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        while (true) {
            Locale[] localeArr = this.f1079a;
            if (i < localeArr.length) {
                sb.append(localeArr[i]);
                if (i < this.f1079a.length - 1) {
                    sb.append(',');
                }
                i++;
            } else {
                sb.append("]");
                return sb.toString();
            }
        }
    }
}
