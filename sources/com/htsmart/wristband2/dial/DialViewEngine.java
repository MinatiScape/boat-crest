package com.htsmart.wristband2.dial;

import android.content.Context;
import android.net.Uri;
/* loaded from: classes11.dex */
public interface DialViewEngine {
    void loadDialBackground(Context context, DialView dialView, Uri uri);

    @Deprecated
    void loadDialStyle(Context context, DialView dialView, Uri uri);

    void loadDialStyle(Context context, DialView dialView, Uri uri, int i);
}
