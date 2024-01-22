package androidx.camera.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Rational;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.Logger;
import androidx.camera.core.MeteringPointFactory;
import androidx.camera.core.Preview;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.view.PreviewView;
import androidx.camera.view.internal.compat.quirk.DeviceQuirks;
import androidx.camera.view.internal.compat.quirk.SurfaceViewStretchedQuirk;
import androidx.camera.view.p;
import androidx.camera.view.transform.OutputTransform;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class PreviewView extends FrameLayout {
    public static final ImplementationMode s = ImplementationMode.PERFORMANCE;
    @NonNull
    public ImplementationMode h;
    @Nullable
    @VisibleForTesting
    public p i;
    @NonNull
    public final k j;
    @NonNull
    public final MutableLiveData<StreamState> k;
    @Nullable
    public final AtomicReference<j> l;
    public CameraController m;
    @NonNull
    public q n;
    @NonNull
    public final ScaleGestureDetector o;
    @Nullable
    public MotionEvent p;
    public final View.OnLayoutChangeListener q;
    public final Preview.SurfaceProvider r;

    /* loaded from: classes.dex */
    public enum ImplementationMode {
        PERFORMANCE(0),
        COMPATIBLE(1);
        
        private final int mId;

        ImplementationMode(int i) {
            this.mId = i;
        }

        public static ImplementationMode fromId(int i) {
            ImplementationMode[] values;
            for (ImplementationMode implementationMode : values()) {
                if (implementationMode.mId == i) {
                    return implementationMode;
                }
            }
            throw new IllegalArgumentException("Unknown implementation mode id " + i);
        }

        public int getId() {
            return this.mId;
        }
    }

    /* loaded from: classes.dex */
    public enum ScaleType {
        FILL_START(0),
        FILL_CENTER(1),
        FILL_END(2),
        FIT_START(3),
        FIT_CENTER(4),
        FIT_END(5);
        
        private final int mId;

        ScaleType(int i) {
            this.mId = i;
        }

        public static ScaleType fromId(int i) {
            ScaleType[] values;
            for (ScaleType scaleType : values()) {
                if (scaleType.mId == i) {
                    return scaleType;
                }
            }
            throw new IllegalArgumentException("Unknown scale type id " + i);
        }

        public int getId() {
            return this.mId;
        }
    }

    /* loaded from: classes.dex */
    public enum StreamState {
        IDLE,
        STREAMING
    }

    /* loaded from: classes.dex */
    public class a implements Preview.SurfaceProvider {
        public a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(SurfaceRequest surfaceRequest) {
            PreviewView.this.r.onSurfaceRequested(surfaceRequest);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(CameraInternal cameraInternal, SurfaceRequest surfaceRequest, SurfaceRequest.TransformationInfo transformationInfo) {
            Logger.d("PreviewView", "Preview transformation info updated. " + transformationInfo);
            PreviewView.this.j.p(transformationInfo, surfaceRequest.getResolution(), cameraInternal.getCameraInfoInternal().getLensFacing().intValue() == 0);
            PreviewView.this.d();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(j jVar, CameraInternal cameraInternal) {
            if (PreviewView.this.l.compareAndSet(jVar, null)) {
                jVar.k(StreamState.IDLE);
            }
            jVar.e();
            cameraInternal.getCameraState().removeObserver(jVar);
        }

        @Override // androidx.camera.core.Preview.SurfaceProvider
        @AnyThread
        @SuppressLint({"UnsafeOptInUsageError"})
        public void onSurfaceRequested(@NonNull final SurfaceRequest surfaceRequest) {
            p wVar;
            if (!Threads.isMainThread()) {
                ContextCompat.getMainExecutor(PreviewView.this.getContext()).execute(new Runnable() { // from class: androidx.camera.view.o
                    @Override // java.lang.Runnable
                    public final void run() {
                        PreviewView.a.this.d(surfaceRequest);
                    }
                });
                return;
            }
            Logger.d("PreviewView", "Surface requested by Preview.");
            final CameraInternal camera = surfaceRequest.getCamera();
            surfaceRequest.setTransformationInfoListener(ContextCompat.getMainExecutor(PreviewView.this.getContext()), new SurfaceRequest.TransformationInfoListener() { // from class: androidx.camera.view.m
                @Override // androidx.camera.core.SurfaceRequest.TransformationInfoListener
                public final void onTransformationInfoUpdate(SurfaceRequest.TransformationInfo transformationInfo) {
                    PreviewView.a.this.e(camera, surfaceRequest, transformationInfo);
                }
            });
            PreviewView previewView = PreviewView.this;
            if (PreviewView.e(surfaceRequest, previewView.h)) {
                PreviewView previewView2 = PreviewView.this;
                wVar = new d0(previewView2, previewView2.j);
            } else {
                PreviewView previewView3 = PreviewView.this;
                wVar = new w(previewView3, previewView3.j);
            }
            previewView.i = wVar;
            CameraInfoInternal cameraInfoInternal = camera.getCameraInfoInternal();
            PreviewView previewView4 = PreviewView.this;
            final j jVar = new j(cameraInfoInternal, previewView4.k, previewView4.i);
            PreviewView.this.l.set(jVar);
            camera.getCameraState().addObserver(ContextCompat.getMainExecutor(PreviewView.this.getContext()), jVar);
            PreviewView.this.i.g(surfaceRequest, new p.a() { // from class: androidx.camera.view.n
                @Override // androidx.camera.view.p.a
                public final void a() {
                    PreviewView.a.this.f(jVar, camera);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f804a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ImplementationMode.values().length];
            b = iArr;
            try {
                iArr[ImplementationMode.COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ImplementationMode.PERFORMANCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[ScaleType.values().length];
            f804a = iArr2;
            try {
                iArr2[ScaleType.FILL_END.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f804a[ScaleType.FILL_CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f804a[ScaleType.FILL_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f804a[ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f804a[ScaleType.FIT_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f804a[ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        public c() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            CameraController cameraController = PreviewView.this.m;
            if (cameraController != null) {
                cameraController.r(scaleGestureDetector.getScaleFactor());
                return true;
            }
            return true;
        }
    }

    @UiThread
    public PreviewView(@NonNull Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if ((i3 - i == i7 - i5 && i4 - i2 == i8 - i6) ? false : true) {
            d();
            b(true);
        }
    }

    public static boolean e(@NonNull SurfaceRequest surfaceRequest, @NonNull ImplementationMode implementationMode) {
        int i;
        boolean equals = surfaceRequest.getCamera().getCameraInfoInternal().getImplementationType().equals(CameraInfo.IMPLEMENTATION_TYPE_CAMERA2_LEGACY);
        boolean z = DeviceQuirks.get(SurfaceViewStretchedQuirk.class) != null;
        if (surfaceRequest.isRGBA8888Required() || Build.VERSION.SDK_INT <= 24 || equals || z || (i = b.b[implementationMode.ordinal()]) == 1) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        throw new IllegalArgumentException("Invalid implementation mode: " + implementationMode);
    }

    private int getViewPortScaleType() {
        switch (b.f804a[getScaleType().ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 0;
            case 4:
            case 5:
            case 6:
                return 3;
            default:
                throw new IllegalStateException("Unexpected scale type: " + getScaleType());
        }
    }

    public final void b(boolean z) {
        Display display = getDisplay();
        ViewPort viewPort = getViewPort();
        if (this.m == null || viewPort == null || !isAttachedToWindow() || display == null) {
            return;
        }
        try {
            this.m.e(getSurfaceProvider(), viewPort, display);
        } catch (IllegalStateException e) {
            if (z) {
                Logger.e("PreviewView", e.getMessage(), e);
                return;
            }
            throw e;
        }
    }

    public void d() {
        p pVar = this.i;
        if (pVar != null) {
            pVar.h();
        }
        this.n.a(new Size(getWidth(), getHeight()), getLayoutDirection());
    }

    @Nullable
    @UiThread
    public Bitmap getBitmap() {
        Threads.checkMainThread();
        p pVar = this.i;
        if (pVar == null) {
            return null;
        }
        return pVar.a();
    }

    @Nullable
    @UiThread
    public CameraController getController() {
        Threads.checkMainThread();
        return this.m;
    }

    @NonNull
    @UiThread
    public ImplementationMode getImplementationMode() {
        Threads.checkMainThread();
        return this.h;
    }

    @NonNull
    @UiThread
    public MeteringPointFactory getMeteringPointFactory() {
        Threads.checkMainThread();
        return this.n;
    }

    @Nullable
    @TransformExperimental
    public OutputTransform getOutputTransform() {
        Matrix matrix;
        Threads.checkMainThread();
        try {
            matrix = this.j.i(new Size(getWidth(), getHeight()), getLayoutDirection());
        } catch (IllegalStateException unused) {
            matrix = null;
        }
        Rect h = this.j.h();
        if (matrix != null && h != null) {
            matrix.preConcat(TransformUtils.getNormalizedToBuffer(h));
            if (this.i instanceof d0) {
                matrix.postConcat(getMatrix());
            } else {
                Logger.w("PreviewView", "PreviewView needs to be in COMPATIBLE mode for the transform to work correctly.");
            }
            return new OutputTransform(matrix, new Size(h.width(), h.height()));
        }
        Logger.d("PreviewView", "Transform info is not ready");
        return null;
    }

    @NonNull
    public LiveData<StreamState> getPreviewStreamState() {
        return this.k;
    }

    @NonNull
    @UiThread
    public ScaleType getScaleType() {
        Threads.checkMainThread();
        return this.j.g();
    }

    @NonNull
    @UiThread
    public Preview.SurfaceProvider getSurfaceProvider() {
        Threads.checkMainThread();
        return this.r;
    }

    @Nullable
    @UiThread
    public ViewPort getViewPort() {
        Threads.checkMainThread();
        if (getDisplay() == null) {
            return null;
        }
        return getViewPort(getDisplay().getRotation());
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        addOnLayoutChangeListener(this.q);
        p pVar = this.i;
        if (pVar != null) {
            pVar.d();
        }
        b(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeOnLayoutChangeListener(this.q);
        p pVar = this.i;
        if (pVar != null) {
            pVar.e();
        }
        CameraController cameraController = this.m;
        if (cameraController != null) {
            cameraController.f();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (this.m == null) {
            return super.onTouchEvent(motionEvent);
        }
        boolean z = motionEvent.getPointerCount() == 1;
        boolean z2 = motionEvent.getAction() == 1;
        boolean z3 = motionEvent.getEventTime() - motionEvent.getDownTime() < ((long) ViewConfiguration.getLongPressTimeout());
        if (!z || !z2 || !z3) {
            return this.o.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
        }
        this.p = motionEvent;
        performClick();
        return true;
    }

    @Override // android.view.View
    public boolean performClick() {
        if (this.m != null) {
            MotionEvent motionEvent = this.p;
            float x = motionEvent != null ? motionEvent.getX() : getWidth() / 2.0f;
            MotionEvent motionEvent2 = this.p;
            this.m.s(this.n, x, motionEvent2 != null ? motionEvent2.getY() : getHeight() / 2.0f);
        }
        this.p = null;
        return super.performClick();
    }

    @UiThread
    public void setController(@Nullable CameraController cameraController) {
        Threads.checkMainThread();
        CameraController cameraController2 = this.m;
        if (cameraController2 != null && cameraController2 != cameraController) {
            cameraController2.f();
        }
        this.m = cameraController;
        b(false);
    }

    @UiThread
    public void setImplementationMode(@NonNull ImplementationMode implementationMode) {
        Threads.checkMainThread();
        this.h = implementationMode;
    }

    @UiThread
    public void setScaleType(@NonNull ScaleType scaleType) {
        Threads.checkMainThread();
        this.j.o(scaleType);
        d();
        b(false);
    }

    @UiThread
    public PreviewView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @UiThread
    public PreviewView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    @UiThread
    public PreviewView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ImplementationMode implementationMode = s;
        this.h = implementationMode;
        k kVar = new k();
        this.j = kVar;
        this.k = new MutableLiveData<>(StreamState.IDLE);
        this.l = new AtomicReference<>();
        this.n = new q(kVar);
        this.q = new View.OnLayoutChangeListener() { // from class: androidx.camera.view.l
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                PreviewView.this.c(view, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        };
        this.r = new a();
        Threads.checkMainThread();
        Resources.Theme theme = context.getTheme();
        int[] iArr = R.styleable.PreviewView;
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, i, i2);
        ViewCompat.saveAttributeDataForStyleable(this, context, iArr, attributeSet, obtainStyledAttributes, i, i2);
        try {
            setScaleType(ScaleType.fromId(obtainStyledAttributes.getInteger(R.styleable.PreviewView_scaleType, kVar.g().getId())));
            setImplementationMode(ImplementationMode.fromId(obtainStyledAttributes.getInteger(R.styleable.PreviewView_implementationMode, implementationMode.getId())));
            obtainStyledAttributes.recycle();
            this.o = new ScaleGestureDetector(context, new c());
            if (getBackground() == null) {
                setBackgroundColor(ContextCompat.getColor(getContext(), 17170444));
            }
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Nullable
    @SuppressLint({"WrongConstant", "UnsafeOptInUsageError"})
    @UiThread
    public ViewPort getViewPort(int i) {
        Threads.checkMainThread();
        if (getWidth() == 0 || getHeight() == 0) {
            return null;
        }
        return new ViewPort.Builder(new Rational(getWidth(), getHeight()), i).setScaleType(getViewPortScaleType()).setLayoutDirection(getLayoutDirection()).build();
    }
}
