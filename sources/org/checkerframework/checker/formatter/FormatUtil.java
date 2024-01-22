package org.checkerframework.checker.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.MissingFormatArgumentException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.checkerframework.checker.formatter.qual.ConversionCategory;
import org.checkerframework.checker.formatter.qual.ReturnsFormat;
/* loaded from: classes13.dex */
public class FormatUtil {

    /* renamed from: a  reason: collision with root package name */
    public static Pattern f15422a = Pattern.compile("%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])");

    /* loaded from: classes13.dex */
    public static class ExcessiveOrMissingFormatArgumentException extends MissingFormatArgumentException {
        private static final long serialVersionUID = 17000126;
        private final int expected;
        private final int found;

        public ExcessiveOrMissingFormatArgumentException(int i, int i2) {
            super("-");
            this.expected = i;
            this.found = i2;
        }

        public int getExpected() {
            return this.expected;
        }

        public int getFound() {
            return this.found;
        }

        @Override // java.util.MissingFormatArgumentException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected %d arguments but found %d.", Integer.valueOf(this.expected), Integer.valueOf(this.found));
        }
    }

    /* loaded from: classes13.dex */
    public static class IllegalFormatConversionCategoryException extends IllegalFormatConversionException {
        private static final long serialVersionUID = 17000126;
        private final ConversionCategory expected;
        private final ConversionCategory found;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public IllegalFormatConversionCategoryException(org.checkerframework.checker.formatter.qual.ConversionCategory r4, org.checkerframework.checker.formatter.qual.ConversionCategory r5) {
            /*
                r3 = this;
                java.lang.String r0 = r4.chars
                int r0 = r0.length()
                r1 = 0
                if (r0 != 0) goto Lc
                r0 = 45
                goto L12
            Lc:
                java.lang.String r0 = r4.chars
                char r0 = r0.charAt(r1)
            L12:
                java.lang.Class<? extends java.lang.Object>[] r2 = r5.types
                if (r2 != 0) goto L19
                java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
                goto L1b
            L19:
                r1 = r2[r1]
            L1b:
                r3.<init>(r0, r1)
                r3.expected = r4
                r3.found = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.checkerframework.checker.formatter.FormatUtil.IllegalFormatConversionCategoryException.<init>(org.checkerframework.checker.formatter.qual.ConversionCategory, org.checkerframework.checker.formatter.qual.ConversionCategory):void");
        }

        public ConversionCategory getExpected() {
            return this.expected;
        }

        public ConversionCategory getFound() {
            return this.found;
        }

        @Override // java.util.IllegalFormatConversionException, java.lang.Throwable
        public String getMessage() {
            return String.format("Expected category %s but found %s.", this.expected, this.found);
        }
    }

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f15423a;
        public final ConversionCategory b;

        public a(char c, int i) {
            this.f15423a = i;
            this.b = ConversionCategory.fromConversionChar(c);
        }

        public ConversionCategory a() {
            return this.b;
        }

        public int b() {
            return this.f15423a;
        }
    }

    public static char a(Matcher matcher) {
        String group = matcher.group(5);
        if (group == null) {
            return matcher.group(6).charAt(0);
        }
        return group.charAt(0);
    }

    @ReturnsFormat
    public static String asFormat(String str, ConversionCategory... conversionCategoryArr) throws IllegalFormatException {
        ConversionCategory[] formatParameterCategories = formatParameterCategories(str);
        if (formatParameterCategories.length == conversionCategoryArr.length) {
            for (int i = 0; i < conversionCategoryArr.length; i++) {
                if (conversionCategoryArr[i] != formatParameterCategories[i]) {
                    throw new IllegalFormatConversionCategoryException(conversionCategoryArr[i], formatParameterCategories[i]);
                }
            }
            return str;
        }
        throw new ExcessiveOrMissingFormatArgumentException(conversionCategoryArr.length, formatParameterCategories.length);
    }

    public static int b(Matcher matcher) {
        String group = matcher.group(1);
        if (group != null) {
            return Integer.parseInt(group.substring(0, group.length() - 1));
        }
        return (matcher.group(2) == null || !matcher.group(2).contains(String.valueOf((char) Typography.less))) ? 0 : -1;
    }

    public static a[] c(String str) {
        ArrayList arrayList = new ArrayList();
        Matcher matcher = f15422a.matcher(str);
        while (matcher.find()) {
            char a2 = a(matcher);
            if (a2 != '%' && a2 != 'n') {
                arrayList.add(new a(a2, b(matcher)));
            }
        }
        return (a[]) arrayList.toArray(new a[arrayList.size()]);
    }

    public static ConversionCategory[] formatParameterCategories(String str) throws IllegalFormatException {
        tryFormatSatisfiability(str);
        a[] c = c(str);
        HashMap hashMap = new HashMap();
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        for (a aVar : c) {
            int b = aVar.b();
            if (b != -1) {
                if (b != 0) {
                    i3 = b - 1;
                } else {
                    i2++;
                    i3 = i2;
                }
            }
            i = Math.max(i, i3);
            hashMap.put(Integer.valueOf(i3), ConversionCategory.intersect(hashMap.containsKey(Integer.valueOf(i3)) ? (ConversionCategory) hashMap.get(Integer.valueOf(i3)) : ConversionCategory.UNUSED, aVar.a()));
        }
        ConversionCategory[] conversionCategoryArr = new ConversionCategory[i + 1];
        for (int i4 = 0; i4 <= i; i4++) {
            conversionCategoryArr[i4] = hashMap.containsKey(Integer.valueOf(i4)) ? (ConversionCategory) hashMap.get(Integer.valueOf(i4)) : ConversionCategory.UNUSED;
        }
        return conversionCategoryArr;
    }

    public static void tryFormatSatisfiability(String str) throws IllegalFormatException {
        String.format(str, null);
    }
}
