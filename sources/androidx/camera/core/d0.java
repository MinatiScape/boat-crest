package androidx.camera.core;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.os.OperationCanceledException;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class d0 implements ImageReaderProxy.OnImageAvailableListener {
    @GuardedBy("mAnalyzerLock")

    /* renamed from: a  reason: collision with root package name */
    public ImageAnalysis.Analyzer f678a;
    public volatile int b;
    @GuardedBy("mAnalyzerLock")
    public Executor c;
    public final Object d = new Object();
    public boolean e = true;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(ImageProxy imageProxy, ImageAnalysis.Analyzer analyzer, CallbackToFutureAdapter.Completer completer) {
        if (this.e) {
            analyzer.analyze(new a2(imageProxy, k1.a(imageProxy.getImageInfo().getTagBundle(), imageProxy.getImageInfo().getTimestamp(), this.b)));
            completer.set(null);
            return;
        }
        completer.setException(new OperationCanceledException("ImageAnalysis is detached"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object i(Executor executor, final ImageProxy imageProxy, final ImageAnalysis.Analyzer analyzer, final CallbackToFutureAdapter.Completer completer) throws Exception {
        executor.execute(new Runnable() { // from class: androidx.camera.core.c0
            @Override // java.lang.Runnable
            public final void run() {
                d0.this.h(imageProxy, analyzer, completer);
            }
        });
        return "analyzeImage";
    }

    @Nullable
    public abstract ImageProxy c(@NonNull ImageReaderProxy imageReaderProxy);

    public ListenableFuture<Void> d(final ImageProxy imageProxy) {
        final Executor executor;
        final ImageAnalysis.Analyzer analyzer;
        synchronized (this.d) {
            executor = this.c;
            analyzer = this.f678a;
        }
        if (analyzer != null && executor != null) {
            return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.b0
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object i;
                    i = d0.this.i(executor, imageProxy, analyzer, completer);
                    return i;
                }
            });
        }
        return Futures.immediateFailedFuture(new OperationCanceledException("No analyzer or executor currently set."));
    }

    public void e() {
        this.e = true;
    }

    public abstract void f();

    public void g() {
        this.e = false;
        f();
    }

    public abstract void j(@NonNull ImageProxy imageProxy);

    public void k(@Nullable Executor executor, @Nullable ImageAnalysis.Analyzer analyzer) {
        synchronized (this.d) {
            if (analyzer == null) {
                f();
            }
            this.f678a = analyzer;
            this.c = executor;
        }
    }

    public void l(int i) {
        this.b = i;
    }

    @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
    public void onImageAvailable(@NonNull ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy c = c(imageReaderProxy);
            if (c != null) {
                j(c);
            }
        } catch (IllegalStateException e) {
            Logger.e("ImageAnalysisAnalyzer", "Failed to acquire image.", e);
        }
    }
}
