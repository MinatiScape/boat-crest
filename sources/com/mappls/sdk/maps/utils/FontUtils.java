package com.mappls.sdk.maps.utils;

import android.graphics.Typeface;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.mappls.sdk.maps.MapStrictMode;
import com.mappls.sdk.maps.constants.MapplsConstants;
import com.mappls.sdk.maps.log.Logger;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class FontUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final List<String> f12856a;

    static {
        ArrayList arrayList = new ArrayList();
        f12856a = arrayList;
        arrayList.add(MapplsConstants.DEFAULT_FONT);
        arrayList.add("serif");
        arrayList.add("monospace");
    }

    @RequiresApi(21)
    public static List<String> a() {
        ArrayList arrayList = new ArrayList();
        try {
            Typeface create = Typeface.create(Typeface.DEFAULT, 0);
            Field declaredField = Typeface.class.getDeclaredField("sSystemFontMap");
            declaredField.setAccessible(true);
            arrayList.addAll(((Map) declaredField.get(create)).keySet());
        } catch (Exception e) {
            Logger.e("Mbgl-FontUtils", "Couldn't load fonts from Typeface", e);
            MapStrictMode.strictModeViolation("Couldn't load fonts from Typeface", e);
        }
        return arrayList;
    }

    public static String extractValidFont(String... strArr) {
        List<String> list;
        if (strArr == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            list = a();
        } else {
            list = f12856a;
        }
        for (String str : strArr) {
            if (list.contains(str)) {
                return str;
            }
        }
        Logger.i("Mbgl-FontUtils", String.format("Couldn't map font family for local ideograph, using %s instead", MapplsConstants.DEFAULT_FONT));
        return MapplsConstants.DEFAULT_FONT;
    }
}
