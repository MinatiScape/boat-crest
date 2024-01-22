package com.coveiot.utils.utility;

import android.content.Context;
/* loaded from: classes9.dex */
public class ScreenUtil {
    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
