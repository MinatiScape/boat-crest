package com.htsmart.wristband2.dfu;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface i {

    /* loaded from: classes11.dex */
    public interface a {
        void a(@NonNull String str);

        void onError(int i, int i2);
    }

    void a();

    void a(a aVar);

    void a(String str, boolean z, byte b);

    void cancel();
}
