package com.coveiot.android.sportsnotification.utils;

import com.coveiot.utils.utility.LogHelper;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public final class FontLengthUtilityOPP1 {
    @NotNull
    public static final FontLengthUtilityOPP1 INSTANCE = new FontLengthUtilityOPP1();

    public static /* synthetic */ int getStringWidth$default(FontLengthUtilityOPP1 fontLengthUtilityOPP1, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 22;
        }
        return fontLengthUtilityOPP1.getStringWidth(str, i);
    }

    @NotNull
    public final HashMap<String, Integer[]> asciAndFontWidthMap() {
        HashMap<String, Integer[]> hashMap = new HashMap<>();
        hashMap.put("0x0020", new Integer[]{10});
        hashMap.put("0x0021", new Integer[]{4});
        hashMap.put("0x0022", new Integer[]{6});
        hashMap.put("0x0023", new Integer[]{13});
        hashMap.put("0x0024", new Integer[]{11});
        hashMap.put("0x0025", new Integer[]{16});
        hashMap.put("0x0026", new Integer[]{14});
        hashMap.put("0x0027", new Integer[]{3});
        hashMap.put("0x0028", new Integer[]{5});
        hashMap.put("0x0029", new Integer[]{6});
        hashMap.put("0x002A", new Integer[]{8});
        hashMap.put("0x002B", new Integer[]{10});
        hashMap.put("0x002C", new Integer[]{4});
        hashMap.put("0x002D", new Integer[]{8});
        hashMap.put("0x002E", new Integer[]{4});
        hashMap.put("0x002F", new Integer[]{8});
        hashMap.put("0x0030", new Integer[]{10});
        hashMap.put("0x0031", new Integer[]{6});
        hashMap.put("0x0032", new Integer[]{11});
        hashMap.put("0x0033", new Integer[]{11});
        hashMap.put("0x0034", new Integer[]{11});
        hashMap.put("0x0035", new Integer[]{10});
        hashMap.put("0x0036", new Integer[]{10});
        hashMap.put("0x0037", new Integer[]{10});
        hashMap.put("0x0038", new Integer[]{10});
        hashMap.put("0x0039", new Integer[]{10});
        hashMap.put("0x003A", new Integer[]{4});
        hashMap.put("0x003B", new Integer[]{4});
        hashMap.put("0x003C", new Integer[]{11});
        hashMap.put("0x003D", new Integer[]{10});
        hashMap.put("0x003E", new Integer[]{10});
        hashMap.put("0x003F", new Integer[]{10});
        hashMap.put("0x0040", new Integer[]{16});
        hashMap.put("0x0041", new Integer[]{13});
        hashMap.put("0x0042", new Integer[]{12});
        hashMap.put("0x0043", new Integer[]{11});
        hashMap.put("0x0044", new Integer[]{12});
        hashMap.put("0x0045", new Integer[]{11});
        hashMap.put("0x0046", new Integer[]{11});
        hashMap.put("0x0047", new Integer[]{11});
        hashMap.put("0x0048", new Integer[]{12});
        hashMap.put("0x0049", new Integer[]{3});
        hashMap.put("0x004A", new Integer[]{12});
        hashMap.put("0x004B", new Integer[]{12});
        hashMap.put("0x004C", new Integer[]{11});
        hashMap.put("0x004D", new Integer[]{13});
        hashMap.put("0x004E", new Integer[]{12});
        hashMap.put("0x004F", new Integer[]{11});
        hashMap.put("0x0050", new Integer[]{12});
        hashMap.put("0x0051", new Integer[]{11});
        hashMap.put("0x0052", new Integer[]{12});
        hashMap.put("0x0053", new Integer[]{12});
        hashMap.put("0x0054", new Integer[]{12});
        hashMap.put("0x0055", new Integer[]{12});
        hashMap.put("0x0056", new Integer[]{13});
        hashMap.put("0x0057", new Integer[]{19});
        hashMap.put("0x0058", new Integer[]{13});
        hashMap.put("0x0059", new Integer[]{12});
        hashMap.put("0x005A", new Integer[]{11});
        hashMap.put("0x005B", new Integer[]{6});
        hashMap.put("0x005C", new Integer[]{8});
        hashMap.put("0x005D", new Integer[]{6});
        hashMap.put("0x005E", new Integer[]{9});
        hashMap.put("0x005F", new Integer[]{10});
        hashMap.put("0x0060", new Integer[]{4});
        hashMap.put("0x0061", new Integer[]{10});
        hashMap.put("0x0062", new Integer[]{10});
        hashMap.put("0x0063", new Integer[]{10});
        hashMap.put("0x0064", new Integer[]{10});
        hashMap.put("0x0065", new Integer[]{10});
        hashMap.put("0x0066", new Integer[]{8});
        hashMap.put("0x0067", new Integer[]{10});
        hashMap.put("0x0068", new Integer[]{10});
        hashMap.put("0x0069", new Integer[]{4});
        hashMap.put("0x006A", new Integer[]{5});
        hashMap.put("0x006B", new Integer[]{10});
        hashMap.put("0x006C", new Integer[]{3});
        hashMap.put("0x006D", new Integer[]{16});
        hashMap.put("0x006E", new Integer[]{10});
        hashMap.put("0x006F", new Integer[]{10});
        hashMap.put("0x0070", new Integer[]{10});
        hashMap.put("0x0071", new Integer[]{10});
        hashMap.put("0x0072", new Integer[]{7});
        hashMap.put("0x0073", new Integer[]{10});
        hashMap.put("0x0074", new Integer[]{7});
        hashMap.put("0x0075", new Integer[]{10});
        hashMap.put("0x0076", new Integer[]{11});
        hashMap.put("0x0077", new Integer[]{16});
        hashMap.put("0x0078", new Integer[]{11});
        hashMap.put("0x0079", new Integer[]{10});
        hashMap.put("0x007A", new Integer[]{10});
        hashMap.put("0x007B", new Integer[]{7});
        hashMap.put("0x007C", new Integer[]{3});
        hashMap.put("0x007D", new Integer[]{7});
        hashMap.put("0x007E", new Integer[]{1});
        return hashMap;
    }

    public final int getStringWidth(@NotNull String text, int i) {
        Intrinsics.checkNotNullParameter(text, "text");
        int length = text.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            HashMap<String, Integer[]> asciAndFontWidthMap = asciAndFontWidthMap();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String hexString = Integer.toHexString(text.charAt(i3));
            Intrinsics.checkNotNullExpressionValue(hexString, "toHexString(text[i].toInt())");
            String upperCase = hexString.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            String format = String.format("0x00%s", Arrays.copyOf(new Object[]{upperCase}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            Integer[] numArr = asciAndFontWidthMap.get(format);
            if (numArr != null) {
                i2 += numArr[0].intValue();
            } else {
                LogHelper.d(INSTANCE.getClass().getSimpleName(), "Not fount length for " + text.charAt(i3) + " hex string " + Integer.toHexString(text.charAt(i3)));
            }
        }
        return i2;
    }
}
