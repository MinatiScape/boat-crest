package androidx.asynclayoutinflater.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.core.util.Pools;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
/* loaded from: classes.dex */
public final class AsyncLayoutInflater {

    /* renamed from: a  reason: collision with root package name */
    public LayoutInflater f486a;
    public Handler.Callback d = new a();
    public Handler b = new Handler(this.d);
    public d c = d.b();

    /* loaded from: classes.dex */
    public interface OnInflateFinishedListener {
        void onInflateFinished(@NonNull View view, @LayoutRes int i, @Nullable ViewGroup viewGroup);
    }

    /* loaded from: classes.dex */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            c cVar = (c) message.obj;
            if (cVar.d == null) {
                cVar.d = AsyncLayoutInflater.this.f486a.inflate(cVar.c, cVar.b, false);
            }
            cVar.e.onInflateFinished(cVar.d, cVar.c, cVar.b);
            AsyncLayoutInflater.this.c.d(cVar);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class b extends LayoutInflater {

        /* renamed from: a  reason: collision with root package name */
        public static final String[] f487a = {"android.widget.", "android.webkit.", "android.app."};

        public b(Context context) {
            super(context);
        }

        @Override // android.view.LayoutInflater
        public LayoutInflater cloneInContext(Context context) {
            return new b(context);
        }

        @Override // android.view.LayoutInflater
        public View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
            View createView;
            for (String str2 : f487a) {
                try {
                    createView = createView(str, str2, attributeSet);
                } catch (ClassNotFoundException unused) {
                }
                if (createView != null) {
                    return createView;
                }
            }
            return super.onCreateView(str, attributeSet);
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public AsyncLayoutInflater f488a;
        public ViewGroup b;
        public int c;
        public View d;
        public OnInflateFinishedListener e;
    }

    /* loaded from: classes.dex */
    public static class d extends Thread {
        public static final d j;
        public ArrayBlockingQueue<c> h = new ArrayBlockingQueue<>(10);
        public Pools.SynchronizedPool<c> i = new Pools.SynchronizedPool<>(10);

        static {
            d dVar = new d();
            j = dVar;
            dVar.start();
        }

        public static d b() {
            return j;
        }

        public void a(c cVar) {
            try {
                this.h.put(cVar);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to enqueue async inflate request", e);
            }
        }

        public c c() {
            c acquire = this.i.acquire();
            return acquire == null ? new c() : acquire;
        }

        public void d(c cVar) {
            cVar.e = null;
            cVar.f488a = null;
            cVar.b = null;
            cVar.c = 0;
            cVar.d = null;
            this.i.release(cVar);
        }

        public void e() {
            try {
                c take = this.h.take();
                try {
                    take.d = take.f488a.f486a.inflate(take.c, take.b, false);
                } catch (RuntimeException e) {
                    Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", e);
                }
                Message.obtain(take.f488a.b, 0, take).sendToTarget();
            } catch (InterruptedException e2) {
                Log.w("AsyncLayoutInflater", e2);
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                e();
            }
        }
    }

    public AsyncLayoutInflater(@NonNull Context context) {
        this.f486a = new b(context);
    }

    @UiThread
    public void inflate(@LayoutRes int i, @Nullable ViewGroup viewGroup, @NonNull OnInflateFinishedListener onInflateFinishedListener) {
        Objects.requireNonNull(onInflateFinishedListener, "callback argument may not be null!");
        c c2 = this.c.c();
        c2.f488a = this;
        c2.c = i;
        c2.b = viewGroup;
        c2.e = onInflateFinishedListener;
        this.c.a(c2);
    }
}
