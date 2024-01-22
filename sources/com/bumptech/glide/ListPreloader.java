package com.bumptech.glide;

import android.graphics.drawable.Drawable;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;
/* loaded from: classes.dex */
public class ListPreloader<T> implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    public final int f2308a;
    public final b b;
    public final RequestManager c;
    public final PreloadModelProvider<T> d;
    public final PreloadSizeProvider<T> e;
    public int f;
    public int g;
    public int i;
    public int h = -1;
    public boolean j = true;

    /* loaded from: classes.dex */
    public interface PreloadModelProvider<U> {
        @NonNull
        List<U> getPreloadItems(int i);

        @Nullable
        RequestBuilder<?> getPreloadRequestBuilder(@NonNull U u);
    }

    /* loaded from: classes.dex */
    public interface PreloadSizeProvider<T> {
        @Nullable
        int[] getPreloadSize(@NonNull T t, int i, int i2);
    }

    /* loaded from: classes.dex */
    public static final class a implements Target<Object> {
        public int h;
        public int i;
        @Nullable
        public Request j;

        @Override // com.bumptech.glide.request.target.Target
        @Nullable
        public Request getRequest() {
            return this.j;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.onSizeReady(this.i, this.h);
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onDestroy() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadStarted(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStart() {
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStop() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void setRequest(@Nullable Request request) {
            this.j = request;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final Queue<a> f2309a;

        public b(int i) {
            this.f2309a = Util.createQueue(i);
            for (int i2 = 0; i2 < i; i2++) {
                this.f2309a.offer(new a());
            }
        }

        public a a(int i, int i2) {
            a poll = this.f2309a.poll();
            this.f2309a.offer(poll);
            poll.i = i;
            poll.h = i2;
            return poll;
        }
    }

    public ListPreloader(@NonNull RequestManager requestManager, @NonNull PreloadModelProvider<T> preloadModelProvider, @NonNull PreloadSizeProvider<T> preloadSizeProvider, int i) {
        this.c = requestManager;
        this.d = preloadModelProvider;
        this.e = preloadSizeProvider;
        this.f2308a = i;
        this.b = new b(i + 1);
    }

    public final void a() {
        for (int i = 0; i < this.b.f2309a.size(); i++) {
            this.c.clear(this.b.a(0, 0));
        }
    }

    public final void b(int i, int i2) {
        int min;
        int i3;
        if (i < i2) {
            i3 = Math.max(this.f, i);
            min = i2;
        } else {
            min = Math.min(this.g, i);
            i3 = i2;
        }
        int min2 = Math.min(this.i, min);
        int min3 = Math.min(this.i, Math.max(0, i3));
        if (i < i2) {
            for (int i4 = min3; i4 < min2; i4++) {
                d(this.d.getPreloadItems(i4), i4, true);
            }
        } else {
            for (int i5 = min2 - 1; i5 >= min3; i5--) {
                d(this.d.getPreloadItems(i5), i5, false);
            }
        }
        this.g = min3;
        this.f = min2;
    }

    public final void c(int i, boolean z) {
        if (this.j != z) {
            this.j = z;
            a();
        }
        b(i, (z ? this.f2308a : -this.f2308a) + i);
    }

    public final void d(List<T> list, int i, boolean z) {
        int size = list.size();
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                e(list.get(i2), i, i2);
            }
            return;
        }
        for (int i3 = size - 1; i3 >= 0; i3--) {
            e(list.get(i3), i, i3);
        }
    }

    public final void e(@Nullable T t, int i, int i2) {
        int[] preloadSize;
        RequestBuilder<?> preloadRequestBuilder;
        if (t == null || (preloadSize = this.e.getPreloadSize(t, i, i2)) == null || (preloadRequestBuilder = this.d.getPreloadRequestBuilder(t)) == null) {
            return;
        }
        preloadRequestBuilder.into((RequestBuilder<?>) this.b.a(preloadSize[0], preloadSize[1]));
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.i = i3;
        int i4 = this.h;
        if (i > i4) {
            c(i2 + i, true);
        } else if (i < i4) {
            c(i, false);
        }
        this.h = i;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }
}
