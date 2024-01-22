package androidx.camera.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import android.view.Display;
import androidx.annotation.DoNotInline;
import androidx.annotation.FloatRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.OptIn;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.util.Function;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalUseCaseGroup;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.ViewPort;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.RotationProvider;
import androidx.camera.view.video.ExperimentalVideo;
import androidx.camera.view.video.OnVideoSavedCallback;
import androidx.camera.view.video.OutputFileOptions;
import androidx.camera.view.video.OutputFileResults;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class CameraController {
    public static final int IMAGE_ANALYSIS = 2;
    public static final int IMAGE_CAPTURE = 1;
    public static final int TAP_TO_FOCUS_FAILED = 4;
    public static final int TAP_TO_FOCUS_FOCUSED = 2;
    public static final int TAP_TO_FOCUS_NOT_FOCUSED = 3;
    public static final int TAP_TO_FOCUS_NOT_STARTED = 0;
    public static final int TAP_TO_FOCUS_STARTED = 1;
    @ExperimentalVideo
    public static final int VIDEO_CAPTURE = 4;
    public final Context C;
    @NonNull
    public final ListenableFuture<Void> D;
    @NonNull
    public Preview c;
    @Nullable
    public OutputSize d;
    @NonNull
    public ImageCapture e;
    @Nullable
    public OutputSize f;
    @Nullable
    public Executor g;
    @Nullable
    public Executor h;
    @Nullable
    public Executor i;
    @Nullable
    public ImageAnalysis.Analyzer j;
    @NonNull
    public ImageAnalysis k;
    @Nullable
    public OutputSize l;
    @NonNull
    public VideoCapture m;
    @Nullable
    public OutputSize o;
    @Nullable
    public Camera p;
    @Nullable
    public ProcessCameraProvider q;
    @Nullable
    public ViewPort r;
    @Nullable
    public Preview.SurfaceProvider s;
    @Nullable
    public Display t;
    public final RotationProvider u;
    @NonNull
    @VisibleForTesting
    public final RotationProvider.Listener v;
    @Nullable
    public final d w;

    /* renamed from: a  reason: collision with root package name */
    public CameraSelector f798a = CameraSelector.DEFAULT_BACK_CAMERA;
    public int b = 3;
    @NonNull
    public final AtomicBoolean n = new AtomicBoolean(false);
    public boolean x = true;
    public boolean y = true;
    public final f<ZoomState> z = new f<>();
    public final f<Integer> A = new f<>();
    public final MutableLiveData<Integer> B = new MutableLiveData<>(0);

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface TapToFocusStates {
    }

    @OptIn(markerClass = {ExperimentalVideo.class})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface UseCases {
    }

    /* loaded from: classes.dex */
    public class a implements VideoCapture.OnVideoSavedCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnVideoSavedCallback f800a;

        public a(OnVideoSavedCallback onVideoSavedCallback) {
            this.f800a = onVideoSavedCallback;
        }

        @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
        public void onError(int i, @NonNull String str, @Nullable Throwable th) {
            CameraController.this.n.set(false);
            this.f800a.onError(i, str, th);
        }

        @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
        public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
            CameraController.this.n.set(false);
            this.f800a.onVideoSaved(OutputFileResults.create(outputFileResults.getSavedUri()));
        }
    }

    /* loaded from: classes.dex */
    public class b implements FutureCallback<FocusMeteringResult> {
        public b() {
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable FocusMeteringResult focusMeteringResult) {
            if (focusMeteringResult == null) {
                return;
            }
            Logger.d("CameraController", "Tap to focus onSuccess: " + focusMeteringResult.isFocusSuccessful());
            CameraController.this.B.postValue(Integer.valueOf(focusMeteringResult.isFocusSuccessful() ? 2 : 3));
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof CameraControl.OperationCanceledException) {
                Logger.d("CameraController", "Tap-to-focus is canceled by new action.");
                return;
            }
            Logger.d("CameraController", "Tap to focus failed.", th);
            CameraController.this.B.postValue(4);
        }
    }

    @RequiresApi(30)
    /* loaded from: classes.dex */
    public static class c {
        @NonNull
        @DoNotInline
        public static Context a(@NonNull Context context, @Nullable String str) {
            return context.createAttributionContext(str);
        }

        @Nullable
        @DoNotInline
        public static String b(@NonNull Context context) {
            return context.getAttributionTag();
        }
    }

    /* loaded from: classes.dex */
    public class d implements DisplayManager.DisplayListener {
        public d() {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        @OptIn(markerClass = {ExperimentalUseCaseGroup.class})
        @SuppressLint({"WrongConstant"})
        public void onDisplayChanged(int i) {
            Display display = CameraController.this.t;
            if (display == null || display.getDisplayId() != i) {
                return;
            }
            CameraController cameraController = CameraController.this;
            cameraController.c.setTargetRotation(cameraController.t.getRotation());
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    }

    public CameraController(@NonNull Context context) {
        Context g = g(context);
        this.C = g;
        this.c = new Preview.Builder().build();
        this.e = new ImageCapture.Builder().build();
        this.k = new ImageAnalysis.Builder().build();
        this.m = new VideoCapture.Builder().build();
        this.D = Futures.transform(ProcessCameraProvider.getInstance(g), new Function() { // from class: androidx.camera.view.a
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void n;
                n = CameraController.this.n((ProcessCameraProvider) obj);
                return n;
            }
        }, CameraXExecutors.mainThreadExecutor());
        this.w = new d();
        this.u = new RotationProvider(g);
        this.v = new RotationProvider.Listener() { // from class: androidx.camera.view.b
            @Override // androidx.camera.view.RotationProvider.Listener
            public final void onRotationChanged(int i) {
                CameraController.this.o(i);
            }
        };
    }

    public static Context g(@NonNull Context context) {
        String b2;
        Context applicationContext = context.getApplicationContext();
        return (Build.VERSION.SDK_INT < 30 || (b2 = c.b(context)) == null) ? applicationContext : c.a(applicationContext, b2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void n(ProcessCameraProvider processCameraProvider) {
        this.q = processCameraProvider;
        w();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void o(int i) {
        this.k.setTargetRotation(i);
        this.e.setTargetRotation(i);
        this.m.setTargetRotation(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(CameraSelector cameraSelector) {
        this.f798a = cameraSelector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(int i) {
        this.b = i;
    }

    public final void A(int i, int i2) {
        ImageAnalysis.Analyzer analyzer;
        if (j()) {
            this.q.unbind(this.k);
        }
        ImageAnalysis.Builder imageQueueDepth = new ImageAnalysis.Builder().setBackpressureStrategy(i).setImageQueueDepth(i2);
        t(imageQueueDepth, this.l);
        Executor executor = this.i;
        if (executor != null) {
            imageQueueDepth.setBackgroundExecutor(executor);
        }
        ImageAnalysis build = imageQueueDepth.build();
        this.k = build;
        Executor executor2 = this.h;
        if (executor2 == null || (analyzer = this.j) == null) {
            return;
        }
        build.setAnalyzer(executor2, analyzer);
    }

    public final void B(int i) {
        if (j()) {
            this.q.unbind(this.e);
        }
        ImageCapture.Builder captureMode = new ImageCapture.Builder().setCaptureMode(i);
        t(captureMode, this.f);
        Executor executor = this.g;
        if (executor != null) {
            captureMode.setIoExecutor(executor);
        }
        this.e = captureMode.build();
    }

    public final void C() {
        if (j()) {
            this.q.unbind(this.c);
        }
        Preview.Builder builder = new Preview.Builder();
        t(builder, this.d);
        this.c = builder.build();
    }

    public final void D() {
        if (j()) {
            this.q.unbind(this.m);
        }
        VideoCapture.Builder builder = new VideoCapture.Builder();
        t(builder, this.o);
        this.m = builder.build();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void E(@NonNull ImageCapture.OutputFileOptions outputFileOptions) {
        if (this.f798a.getLensFacing() == null || outputFileOptions.getMetadata().isReversedHorizontalSet()) {
            return;
        }
        outputFileOptions.getMetadata().setReversedHorizontal(this.f798a.getLensFacing().intValue() == 0);
    }

    @MainThread
    public void clearImageAnalysisAnalyzer() {
        Threads.checkMainThread();
        this.h = null;
        this.j = null;
        this.k.clearAnalyzer();
    }

    @Nullable
    @OptIn(markerClass = {ExperimentalUseCaseGroup.class, ExperimentalVideo.class})
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseGroup createUseCaseGroup() {
        if (!j()) {
            Logger.d("CameraController", "Camera not initialized.");
            return null;
        } else if (!l()) {
            Logger.d("CameraController", "PreviewView not attached.");
            return null;
        } else {
            UseCaseGroup.Builder addUseCase = new UseCaseGroup.Builder().addUseCase(this.c);
            if (isImageCaptureEnabled()) {
                addUseCase.addUseCase(this.e);
            } else {
                this.q.unbind(this.e);
            }
            if (isImageAnalysisEnabled()) {
                addUseCase.addUseCase(this.k);
            } else {
                this.q.unbind(this.k);
            }
            if (isVideoCaptureEnabled()) {
                addUseCase.addUseCase(this.m);
            } else {
                this.q.unbind(this.m);
            }
            addUseCase.setViewPort(this.r);
            return addUseCase.build();
        }
    }

    @SuppressLint({"MissingPermission", "WrongConstant"})
    @MainThread
    public void e(@NonNull Preview.SurfaceProvider surfaceProvider, @NonNull ViewPort viewPort, @NonNull Display display) {
        Threads.checkMainThread();
        if (this.s != surfaceProvider) {
            this.s = surfaceProvider;
            this.c.setSurfaceProvider(surfaceProvider);
        }
        this.r = viewPort;
        this.t = display;
        y();
        w();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> enableTorch(boolean z) {
        Threads.checkMainThread();
        if (!i()) {
            Logger.w("CameraController", "Use cases not attached to camera.");
            return Futures.immediateFuture(null);
        }
        return this.p.getCameraControl().enableTorch(z);
    }

    @MainThread
    public void f() {
        Threads.checkMainThread();
        ProcessCameraProvider processCameraProvider = this.q;
        if (processCameraProvider != null) {
            processCameraProvider.unbindAll();
        }
        this.c.setSurfaceProvider(null);
        this.p = null;
        this.s = null;
        this.r = null;
        this.t = null;
        z();
    }

    @Nullable
    @MainThread
    public CameraControl getCameraControl() {
        Threads.checkMainThread();
        Camera camera = this.p;
        if (camera == null) {
            return null;
        }
        return camera.getCameraControl();
    }

    @Nullable
    @MainThread
    public CameraInfo getCameraInfo() {
        Threads.checkMainThread();
        Camera camera = this.p;
        if (camera == null) {
            return null;
        }
        return camera.getCameraInfo();
    }

    @NonNull
    @MainThread
    public CameraSelector getCameraSelector() {
        Threads.checkMainThread();
        return this.f798a;
    }

    @Nullable
    @MainThread
    public Executor getImageAnalysisBackgroundExecutor() {
        Threads.checkMainThread();
        return this.i;
    }

    @MainThread
    public int getImageAnalysisBackpressureStrategy() {
        Threads.checkMainThread();
        return this.k.getBackpressureStrategy();
    }

    @MainThread
    public int getImageAnalysisImageQueueDepth() {
        Threads.checkMainThread();
        return this.k.getImageQueueDepth();
    }

    @Nullable
    @MainThread
    public OutputSize getImageAnalysisTargetSize() {
        Threads.checkMainThread();
        return this.l;
    }

    @MainThread
    public int getImageCaptureFlashMode() {
        Threads.checkMainThread();
        return this.e.getFlashMode();
    }

    @Nullable
    @MainThread
    public Executor getImageCaptureIoExecutor() {
        Threads.checkMainThread();
        return this.g;
    }

    @MainThread
    public int getImageCaptureMode() {
        Threads.checkMainThread();
        return this.e.getCaptureMode();
    }

    @Nullable
    @MainThread
    public OutputSize getImageCaptureTargetSize() {
        Threads.checkMainThread();
        return this.f;
    }

    @NonNull
    public ListenableFuture<Void> getInitializationFuture() {
        return this.D;
    }

    @Nullable
    @MainThread
    public OutputSize getPreviewTargetSize() {
        Threads.checkMainThread();
        return this.d;
    }

    @NonNull
    @MainThread
    public LiveData<Integer> getTapToFocusState() {
        Threads.checkMainThread();
        return this.B;
    }

    @NonNull
    @MainThread
    public LiveData<Integer> getTorchState() {
        Threads.checkMainThread();
        return this.A;
    }

    @Nullable
    @ExperimentalVideo
    @MainThread
    public OutputSize getVideoCaptureTargetSize() {
        Threads.checkMainThread();
        return this.o;
    }

    @NonNull
    @MainThread
    public LiveData<ZoomState> getZoomState() {
        Threads.checkMainThread();
        return this.z;
    }

    public final DisplayManager h() {
        return (DisplayManager) this.C.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
    }

    @MainThread
    public boolean hasCamera(@NonNull CameraSelector cameraSelector) {
        Threads.checkMainThread();
        Preconditions.checkNotNull(cameraSelector);
        ProcessCameraProvider processCameraProvider = this.q;
        if (processCameraProvider != null) {
            try {
                return processCameraProvider.hasCamera(cameraSelector);
            } catch (CameraInfoUnavailableException e) {
                Logger.w("CameraController", "Failed to check camera availability", e);
                return false;
            }
        }
        throw new IllegalStateException("Camera not initialized. Please wait for the initialization future to finish. See #getInitializationFuture().");
    }

    public final boolean i() {
        return this.p != null;
    }

    @MainThread
    public boolean isImageAnalysisEnabled() {
        Threads.checkMainThread();
        return m(2);
    }

    @MainThread
    public boolean isImageCaptureEnabled() {
        Threads.checkMainThread();
        return m(1);
    }

    @MainThread
    public boolean isPinchToZoomEnabled() {
        Threads.checkMainThread();
        return this.x;
    }

    @ExperimentalVideo
    @MainThread
    public boolean isRecording() {
        Threads.checkMainThread();
        return this.n.get();
    }

    @MainThread
    public boolean isTapToFocusEnabled() {
        Threads.checkMainThread();
        return this.y;
    }

    @ExperimentalVideo
    @MainThread
    public boolean isVideoCaptureEnabled() {
        Threads.checkMainThread();
        return m(4);
    }

    public final boolean j() {
        return this.q != null;
    }

    public final boolean k(@Nullable OutputSize outputSize, @Nullable OutputSize outputSize2) {
        if (outputSize == outputSize2) {
            return true;
        }
        return outputSize != null && outputSize.equals(outputSize2);
    }

    public final boolean l() {
        return (this.s == null || this.r == null || this.t == null) ? false : true;
    }

    public final boolean m(int i) {
        return (i & this.b) != 0;
    }

    public void r(float f) {
        if (!i()) {
            Logger.w("CameraController", "Use cases not attached to camera.");
        } else if (!this.x) {
            Logger.d("CameraController", "Pinch to zoom disabled.");
        } else {
            Logger.d("CameraController", "Pinch to zoom with scale: " + f);
            ZoomState value = getZoomState().getValue();
            if (value == null) {
                return;
            }
            setZoomRatio(Math.min(Math.max(value.getZoomRatio() * u(f), value.getMinZoomRatio()), value.getMaxZoomRatio()));
        }
    }

    public void s(MeteringPointFactory meteringPointFactory, float f, float f2) {
        if (!i()) {
            Logger.w("CameraController", "Use cases not attached to camera.");
        } else if (!this.y) {
            Logger.d("CameraController", "Tap to focus disabled. ");
        } else {
            Logger.d("CameraController", "Tap to focus started: " + f + ", " + f2);
            this.B.postValue(1);
            MeteringPoint createPoint = meteringPointFactory.createPoint(f, f2, 0.16666667f);
            Futures.addCallback(this.p.getCameraControl().startFocusAndMetering(new FocusMeteringAction.Builder(createPoint, 1).addPoint(meteringPointFactory.createPoint(f, f2, 0.25f), 2).build()), new b(), CameraXExecutors.directExecutor());
        }
    }

    @MainThread
    public void setCameraSelector(@NonNull CameraSelector cameraSelector) {
        Threads.checkMainThread();
        final CameraSelector cameraSelector2 = this.f798a;
        if (cameraSelector2 == cameraSelector) {
            return;
        }
        this.f798a = cameraSelector;
        ProcessCameraProvider processCameraProvider = this.q;
        if (processCameraProvider == null) {
            return;
        }
        processCameraProvider.unbindAll();
        x(new Runnable() { // from class: androidx.camera.view.d
            @Override // java.lang.Runnable
            public final void run() {
                CameraController.this.p(cameraSelector2);
            }
        });
    }

    @OptIn(markerClass = {ExperimentalVideo.class})
    @MainThread
    public void setEnabledUseCases(int i) {
        Threads.checkMainThread();
        final int i2 = this.b;
        if (i == i2) {
            return;
        }
        this.b = i;
        if (!isVideoCaptureEnabled()) {
            stopRecording();
        }
        x(new Runnable() { // from class: androidx.camera.view.c
            @Override // java.lang.Runnable
            public final void run() {
                CameraController.this.q(i2);
            }
        });
    }

    @MainThread
    public void setImageAnalysisAnalyzer(@NonNull Executor executor, @NonNull ImageAnalysis.Analyzer analyzer) {
        Threads.checkMainThread();
        if (this.j == analyzer && this.h == executor) {
            return;
        }
        this.h = executor;
        this.j = analyzer;
        this.k.setAnalyzer(executor, analyzer);
    }

    @MainThread
    public void setImageAnalysisBackgroundExecutor(@Nullable Executor executor) {
        Threads.checkMainThread();
        if (this.i == executor) {
            return;
        }
        this.i = executor;
        A(this.k.getBackpressureStrategy(), this.k.getImageQueueDepth());
        w();
    }

    @MainThread
    public void setImageAnalysisBackpressureStrategy(int i) {
        Threads.checkMainThread();
        if (this.k.getBackpressureStrategy() == i) {
            return;
        }
        A(i, this.k.getImageQueueDepth());
        w();
    }

    @MainThread
    public void setImageAnalysisImageQueueDepth(int i) {
        Threads.checkMainThread();
        if (this.k.getImageQueueDepth() == i) {
            return;
        }
        A(this.k.getBackpressureStrategy(), i);
        w();
    }

    @MainThread
    public void setImageAnalysisTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (k(this.l, outputSize)) {
            return;
        }
        this.l = outputSize;
        A(this.k.getBackpressureStrategy(), this.k.getImageQueueDepth());
        w();
    }

    @MainThread
    public void setImageCaptureFlashMode(int i) {
        Threads.checkMainThread();
        this.e.setFlashMode(i);
    }

    @MainThread
    public void setImageCaptureIoExecutor(@Nullable Executor executor) {
        Threads.checkMainThread();
        if (this.g == executor) {
            return;
        }
        this.g = executor;
        B(this.e.getCaptureMode());
        w();
    }

    @MainThread
    public void setImageCaptureMode(int i) {
        Threads.checkMainThread();
        if (this.e.getCaptureMode() == i) {
            return;
        }
        B(i);
        w();
    }

    @MainThread
    public void setImageCaptureTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (k(this.f, outputSize)) {
            return;
        }
        this.f = outputSize;
        B(getImageCaptureMode());
        w();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> setLinearZoom(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        Threads.checkMainThread();
        if (!i()) {
            Logger.w("CameraController", "Use cases not attached to camera.");
            return Futures.immediateFuture(null);
        }
        return this.p.getCameraControl().setLinearZoom(f);
    }

    @MainThread
    public void setPinchToZoomEnabled(boolean z) {
        Threads.checkMainThread();
        this.x = z;
    }

    @MainThread
    public void setPreviewTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (k(this.d, outputSize)) {
            return;
        }
        this.d = outputSize;
        C();
        w();
    }

    @MainThread
    public void setTapToFocusEnabled(boolean z) {
        Threads.checkMainThread();
        this.y = z;
    }

    @ExperimentalVideo
    @MainThread
    public void setVideoCaptureTargetSize(@Nullable OutputSize outputSize) {
        Threads.checkMainThread();
        if (k(this.o, outputSize)) {
            return;
        }
        this.o = outputSize;
        D();
        w();
    }

    @NonNull
    @MainThread
    public ListenableFuture<Void> setZoomRatio(float f) {
        Threads.checkMainThread();
        if (!i()) {
            Logger.w("CameraController", "Use cases not attached to camera.");
            return Futures.immediateFuture(null);
        }
        return this.p.getCameraControl().setZoomRatio(f);
    }

    @ExperimentalVideo
    @MainThread
    public void startRecording(@NonNull OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull OnVideoSavedCallback onVideoSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(j(), "Camera not initialized.");
        Preconditions.checkState(isVideoCaptureEnabled(), "VideoCapture disabled.");
        this.m.u(outputFileOptions.toVideoCaptureOutputFileOptions(), executor, new a(onVideoSavedCallback));
        this.n.set(true);
    }

    @ExperimentalVideo
    @MainThread
    public void stopRecording() {
        Threads.checkMainThread();
        if (this.n.get()) {
            this.m.z();
        }
    }

    public final void t(@NonNull ImageOutputConfig.Builder<?> builder, @Nullable OutputSize outputSize) {
        if (outputSize == null) {
            return;
        }
        if (outputSize.getResolution() != null) {
            builder.setTargetResolution(outputSize.getResolution());
        } else if (outputSize.getAspectRatio() != -1) {
            builder.setTargetAspectRatio(outputSize.getAspectRatio());
        } else {
            Logger.e("CameraController", "Invalid target surface size. " + outputSize);
        }
    }

    @MainThread
    public void takePicture(@NonNull ImageCapture.OutputFileOptions outputFileOptions, @NonNull Executor executor, @NonNull ImageCapture.OnImageSavedCallback onImageSavedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(j(), "Camera not initialized.");
        Preconditions.checkState(isImageCaptureEnabled(), "ImageCapture disabled.");
        E(outputFileOptions);
        this.e.a0(outputFileOptions, executor, onImageSavedCallback);
    }

    public final float u(float f) {
        return f > 1.0f ? ((f - 1.0f) * 2.0f) + 1.0f : 1.0f - ((1.0f - f) * 2.0f);
    }

    @Nullable
    public abstract Camera v();

    public void w() {
        x(null);
    }

    public void x(@Nullable Runnable runnable) {
        try {
            this.p = v();
            if (!i()) {
                Logger.d("CameraController", "Use cases not attached to camera.");
                return;
            }
            this.z.f(this.p.getCameraInfo().getZoomState());
            this.A.f(this.p.getCameraInfo().getTorchState());
        } catch (IllegalArgumentException e) {
            if (runnable != null) {
                runnable.run();
            }
            throw new IllegalStateException("The selected camera does not support the enabled use cases. Please disable use case and/or select a different camera. e.g. #setVideoCaptureEnabled(false)", e);
        }
    }

    public final void y() {
        h().registerDisplayListener(this.w, new Handler(Looper.getMainLooper()));
        this.u.setListener(this.v);
    }

    public final void z() {
        h().unregisterDisplayListener(this.w);
        this.u.clearListener();
    }

    /* loaded from: classes.dex */
    public static final class OutputSize {
        public static final int UNASSIGNED_ASPECT_RATIO = -1;

        /* renamed from: a  reason: collision with root package name */
        public final int f799a;
        @Nullable
        public final Size b;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY})
        /* loaded from: classes.dex */
        public @interface OutputAspectRatio {
        }

        public OutputSize(int i) {
            Preconditions.checkArgument(i != -1);
            this.f799a = i;
            this.b = null;
        }

        public int getAspectRatio() {
            return this.f799a;
        }

        @Nullable
        public Size getResolution() {
            return this.b;
        }

        @NonNull
        public String toString() {
            return "aspect ratio: " + this.f799a + " resolution: " + this.b;
        }

        public OutputSize(@NonNull Size size) {
            Preconditions.checkNotNull(size);
            this.f799a = -1;
            this.b = size;
        }
    }

    @MainThread
    public void takePicture(@NonNull Executor executor, @NonNull ImageCapture.OnImageCapturedCallback onImageCapturedCallback) {
        Threads.checkMainThread();
        Preconditions.checkState(j(), "Camera not initialized.");
        Preconditions.checkState(isImageCaptureEnabled(), "ImageCapture disabled.");
        this.e.Z(executor, onImageCapturedCallback);
    }
}
