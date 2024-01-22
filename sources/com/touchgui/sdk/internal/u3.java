package com.touchgui.sdk.internal;

import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public abstract class u3 {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f13828a = {"a.bin", "b.bin", "apollo_boot.bin", "st_boot.bin", "boot.bin", "res01.bin", "res02.bin", "res03.bin", "res04.bin", "res05.bin", "fonts.bin", "Ftable.bin", "Dfonts", "Sfonts.bin", "r_watch30.bin", "r_watch40.bin", "pkg.ota", "update.bin", "lle_bds.lle", "lle_glo.lle", "lle_gps.lle", "agps.bin", "LTO2.bin", "LTO7.bin", "picture.watch", "cfg_res.watch"};
    public static final String[] b = {"copy_res.*", "cfg_res\\.watch.*", ".*LTO2\\.bin", ".*LTO7\\.bin"};
    public static final HashMap c;
    public static final String[] d;

    static {
        HashMap hashMap = new HashMap();
        c = hashMap;
        hashMap.put("a.bin", 35);
        hashMap.put("b.bin", 33);
        hashMap.put("apollo_boot.bin", 142);
        hashMap.put("st_boot.bin", 143);
        hashMap.put("fonts.bin", 17);
        hashMap.put("Ftable.bin", 17);
        hashMap.put("Dfonts", 17);
        hashMap.put("lle_bds.lle", 20);
        hashMap.put("lle_glo.lle", 20);
        hashMap.put("lle_gps.lle", 20);
        hashMap.put("agps.bin", 20);
        hashMap.put("LTO2.bin", 20);
        hashMap.put("LTO7.bin", 20);
        hashMap.put("picture.watch", 18);
        hashMap.put("cfg_res.watch", 19);
        d = new String[]{"fonts.bin", "r_watch30.bin", "r_watch40.bin", "res01.bin", "res02.bin", "res03.bin", "res04.bin", "res05.bin"};
    }

    public static int a(String str) {
        String str2;
        String[] strArr = f13828a;
        int i = 0;
        while (true) {
            if (i >= 26) {
                str2 = null;
                break;
            }
            str2 = strArr[i];
            if (str.toUpperCase().contains(str2.toUpperCase())) {
                break;
            }
            i++;
        }
        Integer num = 255;
        if (str2 != null && (num = (Integer) c.get(str2)) == null) {
            num = 255;
        }
        return num.intValue();
    }

    public static boolean b(String str) {
        int lastIndexOf = str.lastIndexOf(File.separator);
        if (lastIndexOf != -1) {
            str = str.substring(lastIndexOf + 1);
        }
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = f13828a;
            for (int i = 0; i < 26; i++) {
                if (str.equalsIgnoreCase(strArr[i])) {
                    return true;
                }
            }
            String[] strArr2 = b;
            for (int i2 = 0; i2 < 4; i2++) {
                if (Pattern.compile(strArr2[i2]).matcher(str).find()) {
                    return true;
                }
            }
        }
        return false;
    }
}
