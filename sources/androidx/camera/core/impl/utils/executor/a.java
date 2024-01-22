package androidx.camera.core.impl.utils.executor;

import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class a implements Executor {
    public static volatile a h;

    public static Executor a() {
        if (h != null) {
            return h;
        }
        synchronized (a.class) {
            if (h == null) {
                h = new a();
            }
        }
        return h;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
