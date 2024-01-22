package com.htsmart.wristband2.dfu;

import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes11.dex */
public interface j {

    /* renamed from: a  reason: collision with root package name */
    public static final int f12003a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface a {
    }

    /* loaded from: classes11.dex */
    public interface b {
        void a(int i);

        void a(@Nullable String str, @Nullable String str2, int i);
    }

    void a();

    void a(b bVar);

    void a(boolean z);

    void cancel();
}
