package androidx.camera.core.impl.utils;

import android.os.Handler;
import android.os.Looper;
import androidx.core.os.HandlerCompat;
/* loaded from: classes.dex */
public final class MainThreadAsyncHandler {

    /* renamed from: a  reason: collision with root package name */
    public static volatile Handler f740a;

    public static Handler getInstance() {
        if (f740a != null) {
            return f740a;
        }
        synchronized (MainThreadAsyncHandler.class) {
            if (f740a == null) {
                f740a = HandlerCompat.createAsync(Looper.getMainLooper());
            }
        }
        return f740a;
    }
}
