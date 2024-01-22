package com.crrepa.f0;

import com.clevertap.android.sdk.Constants;
import java.util.List;
/* loaded from: classes9.dex */
public class d {
    public static String a(List<Integer> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size() - 1;
        for (int i = 0; i <= size; i++) {
            sb.append(list.get(i));
            if (i < size) {
                sb.append(Constants.SEPARATOR_COMMA);
            }
        }
        return sb.toString();
    }
}
