package org.checkerframework.checker.i18nformatter;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.variables.CTVariableUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.i18nformatter.qual.I18nChecksFormat;
import org.checkerframework.checker.i18nformatter.qual.I18nConversionCategory;
import org.checkerframework.checker.i18nformatter.qual.I18nValidFormat;
/* loaded from: classes13.dex */
public class I18nFormatUtil {

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f15424a;
        public I18nConversionCategory b;

        public a(int i, I18nConversionCategory i18nConversionCategory) {
            this.f15424a = i;
            this.b = i18nConversionCategory;
        }

        public String toString() {
            return this.b.toString() + "(index: " + this.f15424a + ")";
        }
    }

    /* loaded from: classes13.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static int f15425a;
        public static Locale b;
        public static List<I18nConversionCategory> c;
        public static List<Integer> d;
        public static int e;
        public static final String[] f = {"", CTVariableUtils.NUMBER, "date", NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP, "choice"};
        public static final String[] g = {"", FirebaseAnalytics.Param.CURRENCY, "percent", TypedValues.Custom.S_INT};
        public static final String[] h = {"", "short", "medium", "long", "full"};

        public static void a(String str) {
            StringBuilder[] sbArr = new StringBuilder[4];
            sbArr[0] = new StringBuilder();
            e = 0;
            f15425a = -1;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (i3 == 0) {
                    if (charAt == '\'') {
                        int i4 = i + 1;
                        if (i4 >= str.length() || str.charAt(i4) != '\'') {
                            z = !z;
                        } else {
                            sbArr[i3].append(charAt);
                            i = i4;
                        }
                    } else if (charAt == '{' && !z) {
                        if (sbArr[1] == null) {
                            sbArr[1] = new StringBuilder();
                        }
                        i3 = 1;
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (z) {
                    sbArr[i3].append(charAt);
                    if (charAt == '\'') {
                        z = false;
                    }
                } else if (charAt != ' ') {
                    if (charAt == '\'') {
                        sbArr[i3].append(charAt);
                        z = true;
                    } else if (charAt != ',') {
                        if (charAt == '{') {
                            i2++;
                            sbArr[i3].append(charAt);
                        } else if (charAt != '}') {
                            sbArr[i3].append(charAt);
                        } else if (i2 == 0) {
                            c(i, e, sbArr);
                            e++;
                            sbArr[1] = null;
                            sbArr[2] = null;
                            sbArr[3] = null;
                            i3 = 0;
                        } else {
                            i2--;
                            sbArr[i3].append(charAt);
                        }
                    } else if (i3 < 3) {
                        i3++;
                        if (sbArr[i3] == null) {
                            sbArr[i3] = new StringBuilder();
                        }
                    } else {
                        sbArr[i3].append(charAt);
                    }
                } else if (i3 != 2 || sbArr[2].length() > 0) {
                    sbArr[i3].append(charAt);
                }
                i++;
            }
            if (i2 != 0 || i3 == 0) {
                return;
            }
            f15425a = -1;
            throw new IllegalArgumentException("Unmatched braces in the pattern");
        }

        public static final int b(String str, String[] strArr) {
            for (int i = 0; i < strArr.length; i++) {
                if (str.equals(strArr[i])) {
                    return i;
                }
            }
            String lowerCase = str.trim().toLowerCase(Locale.ROOT);
            if (lowerCase != str) {
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    if (lowerCase.equals(strArr[i2])) {
                        return i2;
                    }
                }
                return -1;
            }
            return -1;
        }

        public static void c(int i, int i2, StringBuilder[] sbArr) {
            I18nConversionCategory i18nConversionCategory;
            String[] strArr = new String[sbArr.length];
            for (int i3 = 0; i3 < sbArr.length; i3++) {
                StringBuilder sb = sbArr[i3];
                strArr[i3] = sb != null ? sb.toString() : "";
            }
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                if (parseInt >= 0) {
                    int i4 = f15425a;
                    f15425a = i2;
                    d.add(Integer.valueOf(parseInt));
                    if (strArr[2].length() != 0) {
                        int b2 = b(strArr[2], f);
                        if (b2 == 0) {
                            i18nConversionCategory = I18nConversionCategory.GENERAL;
                        } else if (b2 == 1) {
                            int b3 = b(strArr[3], g);
                            if (b3 != 0 && b3 != 1 && b3 != 2 && b3 != 3) {
                                try {
                                    new DecimalFormat(strArr[3], DecimalFormatSymbols.getInstance(b));
                                } catch (IllegalArgumentException e2) {
                                    f15425a = i4;
                                    throw e2;
                                }
                            }
                            i18nConversionCategory = I18nConversionCategory.NUMBER;
                        } else if (b2 == 2 || b2 == 3) {
                            String str = strArr[3];
                            String[] strArr2 = h;
                            int b4 = b(str, strArr2);
                            if (b4 < 0 || b4 >= strArr2.length) {
                                try {
                                    new SimpleDateFormat(strArr[3], b);
                                } catch (IllegalArgumentException e3) {
                                    f15425a = i4;
                                    throw e3;
                                }
                            }
                            i18nConversionCategory = I18nConversionCategory.DATE;
                        } else if (b2 == 4) {
                            if (strArr[3].length() != 0) {
                                try {
                                    new ChoiceFormat(strArr[3]);
                                    i18nConversionCategory = I18nConversionCategory.NUMBER;
                                } catch (Exception e4) {
                                    f15425a = i4;
                                    throw new IllegalArgumentException("Choice Pattern incorrect: " + strArr[3], e4);
                                }
                            } else {
                                throw new IllegalArgumentException("Choice Pattern requires Subformat Pattern: " + strArr[3]);
                            }
                        } else {
                            f15425a = i4;
                            throw new IllegalArgumentException("unknown format type: " + strArr[2]);
                        }
                    } else {
                        i18nConversionCategory = I18nConversionCategory.GENERAL;
                    }
                    c.add(i18nConversionCategory);
                    return;
                }
                throw new IllegalArgumentException("negative argument number: " + parseInt);
            } catch (NumberFormatException e5) {
                throw new IllegalArgumentException("can't parse argument number: " + strArr[1], e5);
            }
        }

        public static a[] d(String str) {
            c = new ArrayList();
            d = new ArrayList();
            b = Locale.getDefault(Locale.Category.FORMAT);
            a(str);
            a[] aVarArr = new a[e];
            for (int i = 0; i < e; i++) {
                aVarArr[i] = new a(d.get(i).intValue(), c.get(i));
            }
            return aVarArr;
        }
    }

    public static I18nConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        tryFormatSatisfiability(str);
        a[] d = b.d(str);
        HashMap hashMap = new HashMap();
        int i = -1;
        for (a aVar : d) {
            int i2 = aVar.f15424a;
            hashMap.put(Integer.valueOf(i2), I18nConversionCategory.intersect(aVar.b, hashMap.containsKey(Integer.valueOf(i2)) ? (I18nConversionCategory) hashMap.get(Integer.valueOf(i2)) : I18nConversionCategory.UNUSED));
            i = Math.max(i, i2);
        }
        I18nConversionCategory[] i18nConversionCategoryArr = new I18nConversionCategory[i + 1];
        for (int i3 = 0; i3 <= i; i3++) {
            i18nConversionCategoryArr[i3] = hashMap.containsKey(Integer.valueOf(i3)) ? (I18nConversionCategory) hashMap.get(Integer.valueOf(i3)) : I18nConversionCategory.UNUSED;
        }
        return i18nConversionCategoryArr;
    }

    @I18nChecksFormat
    public static boolean hasFormat(String str, I18nConversionCategory... i18nConversionCategoryArr) {
        I18nConversionCategory[] formatParameterCategories = formatParameterCategories(str);
        if (formatParameterCategories.length != i18nConversionCategoryArr.length) {
            return false;
        }
        for (int i = 0; i < i18nConversionCategoryArr.length; i++) {
            if (!I18nConversionCategory.isSubsetOf(i18nConversionCategoryArr[i], formatParameterCategories[i])) {
                return false;
            }
        }
        return true;
    }

    @I18nValidFormat
    public static boolean isFormat(String str) {
        try {
            formatParameterCategories(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        MessageFormat.format(str, null);
    }
}
