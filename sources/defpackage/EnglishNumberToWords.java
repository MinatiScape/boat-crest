package defpackage;

import java.text.DecimalFormat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* renamed from: EnglishNumberToWords  reason: default package */
/* loaded from: classes2.dex */
public final class EnglishNumberToWords {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f1a = {"", " Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
    @NotNull
    public static final String[] b = {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};

    /* renamed from: EnglishNumberToWords$Companion */
    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ String convert$default(Companion companion, double d, String str, int i, Object obj) {
            if ((i & 2) != 0) {
                str = null;
            }
            return companion.convert(d, str);
        }

        public final String a(int i) {
            String str = EnglishNumberToWords.b[i / 10];
            String str2 = EnglishNumberToWords.b[i % 10];
            return str + ' ' + str2;
        }

        public final String b(int i) {
            int i2;
            String str;
            int i3;
            int i4 = i % 100;
            if (i4 < 20) {
                str = EnglishNumberToWords.b[i4];
                i3 = i / 100;
            } else {
                String str2 = EnglishNumberToWords.b[i % 10];
                str = EnglishNumberToWords.f1a[i2 % 10] + str2;
                i3 = (i / 10) / 10;
            }
            if (i3 == 0) {
                return str;
            }
            return EnglishNumberToWords.b[i3] + " Hundred" + str;
        }

        @NotNull
        public final String convert(double d, @Nullable String str) {
            String str2;
            String str3;
            if (d == 0.0d) {
                return "zero " + str;
            }
            int i = (int) d;
            int i2 = (int) ((d - i) * 100);
            String.valueOf(i);
            String format = new DecimalFormat("000000000000").format(Integer.valueOf(i));
            Intrinsics.checkNotNullExpressionValue(format, "df.format(integerPart)");
            String substring = format.substring(0, 3);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            int parseInt = Integer.parseInt(substring);
            String substring2 = format.substring(3, 6);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            int parseInt2 = Integer.parseInt(substring2);
            String substring3 = format.substring(6, 9);
            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
            int parseInt3 = Integer.parseInt(substring3);
            String substring4 = format.substring(9, 12);
            Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
            int parseInt4 = Integer.parseInt(substring4);
            String str4 = "";
            String str5 = (Intrinsics.areEqual(str, "INR") || Intrinsics.areEqual(str, "NotSpecified")) ? "Rupees " : "";
            String str6 = (Intrinsics.areEqual(str, "INR") || Intrinsics.areEqual(str, "NotSpecified")) ? "only" : "";
            if (parseInt == 0) {
                str2 = "";
            } else if (parseInt != 1) {
                str2 = b(parseInt) + " billion ";
            } else {
                str2 = b(parseInt) + " billion ";
            }
            if (parseInt2 == 0) {
                str3 = "";
            } else if (parseInt2 != 1) {
                str3 = b(parseInt2) + " million ";
            } else {
                str3 = b(parseInt2) + " million ";
            }
            String str7 = str2 + str3;
            if (parseInt3 != 0) {
                if (parseInt3 != 1) {
                    str4 = b(parseInt3) + " Thousand ";
                } else {
                    str4 = "One Thousand ";
                }
            }
            String str8 = (str7 + str4) + b(parseInt4);
            if (i2 > 0) {
                if (str8.length() > 0) {
                    str8 = str8 + " and ";
                }
                str8 = str8 + a(i2);
            }
            return str5 + StringsKt__StringsKt.trim(str8).toString() + ' ' + str6;
        }
    }
}
