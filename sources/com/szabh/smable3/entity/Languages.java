package com.szabh.smable3.entity;

import com.bestmafen.baseble.util.BleLog;
import com.clevertap.android.sdk.Constants;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.s;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class Languages {
    public static final int DEFAULT_CODE = 1;
    public static final int INVALID_CODE = 31;
    @NotNull
    public static final Languages INSTANCE = new Languages();
    @NotNull
    public static final String DEFAULT_LANGUAGE = "en";
    @NotNull
    private static final Map<String, Integer> LANGUAGES = s.mapOf(TuplesKt.to("zh", 0), TuplesKt.to(DEFAULT_LANGUAGE, 1), TuplesKt.to("tr", 2), TuplesKt.to("ru", 4), TuplesKt.to("es", 5), TuplesKt.to("it", 6), TuplesKt.to("ko", 7), TuplesKt.to("pt", 8), TuplesKt.to("de", 9), TuplesKt.to("fr", 10), TuplesKt.to("nl", 11), TuplesKt.to("pl", 12), TuplesKt.to("cs", 13), TuplesKt.to("hu", 14), TuplesKt.to("sk", 15), TuplesKt.to("ja", 16), TuplesKt.to("da", 17), TuplesKt.to("fi", 18), TuplesKt.to("no", 19), TuplesKt.to("sv", 20), TuplesKt.to("sr", 21), TuplesKt.to("th", 22), TuplesKt.to("hi", 23), TuplesKt.to("el", 24), TuplesKt.to("Hant", 25), TuplesKt.to("lt", 26), TuplesKt.to("vi", 27), TuplesKt.to("ar", 28), TuplesKt.to("in", 29), TuplesKt.to("uk", 30), TuplesKt.to("iw", 32), TuplesKt.to("bn", 33), TuplesKt.to("et", 34), TuplesKt.to("sl", 35), TuplesKt.to("fa", 36), TuplesKt.to("ro", 37), TuplesKt.to(Constants.KEY_BG, 38), TuplesKt.to("hr", 39), TuplesKt.to("ur", 40));

    private Languages() {
    }

    public static /* synthetic */ Locale codeToLanguage$default(Languages languages, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = DEFAULT_LANGUAGE;
        }
        return languages.codeToLanguage(i, str);
    }

    public static /* synthetic */ int languageToCode$default(Languages languages, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = Locale.getDefault().getLanguage();
            Intrinsics.checkNotNullExpressionValue(str, "getDefault().language");
        }
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return languages.languageToCode(str, i);
    }

    @Nullable
    public final Locale codeToLanguage(int i, @NotNull String str) {
        String str2;
        Intrinsics.checkNotNullParameter(str, "default");
        Iterator<Map.Entry<String, Integer>> it = LANGUAGES.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                str2 = null;
                break;
            }
            Map.Entry<String, Integer> next = it.next();
            str2 = next.getKey();
            if (next.getValue().intValue() == i) {
                break;
            }
        }
        if (str2 != null) {
            str = str2;
        }
        BleLog bleLog = BleLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("Languages codeToLanguage -> code=");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%02X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(", language=");
        sb.append(str);
        sb.append('}');
        bleLog.v(sb.toString());
        if (Intrinsics.areEqual(str, "Hant")) {
            return new Locale.Builder().setLanguage("zh").setScript("Hant").build();
        }
        if (Intrinsics.areEqual(str, "zh")) {
            return new Locale.Builder().setLanguage("zh").setScript("Hans").build();
        }
        return new Locale(str);
    }

    public final int languageToCode(@NotNull String language, int i) {
        String country;
        int hashCode;
        Intrinsics.checkNotNullParameter(language, "language");
        if (Intrinsics.areEqual("zh", language) && (Intrinsics.areEqual("Hant", Locale.getDefault().getScript()) || ((country = Locale.getDefault().getCountry()) != null && ((hashCode = country.hashCode()) == 2339 ? country.equals("Hk") : !(hashCode == 2466 ? !country.equals("MO") : !(hashCode == 2691 && country.equals("TW"))))))) {
            language = "Hant";
        }
        Integer num = LANGUAGES.get(language);
        if (num != null) {
            i = num.intValue();
        }
        BleLog bleLog = BleLog.INSTANCE;
        StringBuilder sb = new StringBuilder();
        sb.append("Languages languageToCode -> language=");
        sb.append(language);
        sb.append(", code=");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%02X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        bleLog.v(sb.toString());
        return i;
    }
}
