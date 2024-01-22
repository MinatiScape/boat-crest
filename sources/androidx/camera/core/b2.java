package androidx.camera.core;

import android.util.SparseArray;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.core.impl.ImageProxyBundle;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class b2 implements ImageProxyBundle {
    public final List<Integer> e;
    public String f;

    /* renamed from: a  reason: collision with root package name */
    public final Object f674a = new Object();
    @GuardedBy("mLock")
    public final SparseArray<CallbackToFutureAdapter.Completer<ImageProxy>> b = new SparseArray<>();
    @GuardedBy("mLock")
    public final SparseArray<ListenableFuture<ImageProxy>> c = new SparseArray<>();
    @GuardedBy("mLock")
    public final List<ImageProxy> d = new ArrayList();
    @GuardedBy("mLock")
    public boolean g = false;

    /* loaded from: classes.dex */
    public class a implements CallbackToFutureAdapter.Resolver<ImageProxy> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f675a;

        public a(int i) {
            this.f675a = i;
        }

        @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
        public Object attachCompleter(@NonNull CallbackToFutureAdapter.Completer<ImageProxy> completer) {
            synchronized (b2.this.f674a) {
                b2.this.b.put(this.f675a, completer);
            }
            return "getImageProxy(id: " + this.f675a + ")";
        }
    }

    public b2(List<Integer> list, String str) {
        this.f = null;
        this.e = list;
        this.f = str;
        d();
    }

    public void a(ImageProxy imageProxy) {
        synchronized (this.f674a) {
            if (this.g) {
                return;
            }
            Integer tag = imageProxy.getImageInfo().getTagBundle().getTag(this.f);
            if (tag != null) {
                CallbackToFutureAdapter.Completer<ImageProxy> completer = this.b.get(tag.intValue());
                if (completer != null) {
                    this.d.add(imageProxy);
                    completer.set(imageProxy);
                    return;
                }
                throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + tag);
            }
            throw new IllegalArgumentException("CaptureId is null.");
        }
    }

    public void b() {
        synchronized (this.f674a) {
            if (this.g) {
                return;
            }
            for (ImageProxy imageProxy : this.d) {
                imageProxy.close();
            }
            this.d.clear();
            this.c.clear();
            this.b.clear();
            this.g = true;
        }
    }

    public void c() {
        synchronized (this.f674a) {
            if (this.g) {
                return;
            }
            for (ImageProxy imageProxy : this.d) {
                imageProxy.close();
            }
            this.d.clear();
            this.c.clear();
            this.b.clear();
            d();
        }
    }

    public final void d() {
        synchronized (this.f674a) {
            for (Integer num : this.e) {
                int intValue = num.intValue();
                this.c.put(intValue, CallbackToFutureAdapter.getFuture(new a(intValue)));
            }
        }
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public List<Integer> getCaptureIds() {
        return Collections.unmodifiableList(this.e);
    }

    @Override // androidx.camera.core.impl.ImageProxyBundle
    @NonNull
    public ListenableFuture<ImageProxy> getImageProxy(int i) {
        ListenableFuture<ImageProxy> listenableFuture;
        synchronized (this.f674a) {
            if (!this.g) {
                listenableFuture = this.c.get(i);
                if (listenableFuture == null) {
                    throw new IllegalArgumentException("ImageProxyBundle does not contain this id: " + i);
                }
            } else {
                throw new IllegalStateException("ImageProxyBundle already closed.");
            }
        }
        return listenableFuture;
    }
}
