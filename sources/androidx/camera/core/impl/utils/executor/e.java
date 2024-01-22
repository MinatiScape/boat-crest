package androidx.camera.core.impl.utils.executor;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public static volatile ScheduledExecutorService f744a;

    public static ScheduledExecutorService a() {
        if (f744a != null) {
            return f744a;
        }
        synchronized (e.class) {
            if (f744a == null) {
                f744a = new b(new Handler(Looper.getMainLooper()));
            }
        }
        return f744a;
    }
}
