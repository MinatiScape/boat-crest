package androidx.room;

import androidx.annotation.NonNull;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class f implements Executor {
    public final Executor h;
    public final ArrayDeque<Runnable> i = new ArrayDeque<>();
    public Runnable j;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ Runnable h;

        public a(Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.h.run();
            } finally {
                f.this.a();
            }
        }
    }

    public f(@NonNull Executor executor) {
        this.h = executor;
    }

    public synchronized void a() {
        Runnable poll = this.i.poll();
        this.j = poll;
        if (poll != null) {
            this.h.execute(poll);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.i.offer(new a(runnable));
        if (this.j == null) {
            a();
        }
    }
}
