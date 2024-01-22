package androidx.camera.camera2.internal;

import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.util.Rational;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.internal.Camera2CameraControlImpl;
import androidx.camera.core.CameraControl;
import androidx.camera.core.FocusMeteringAction;
import androidx.camera.core.FocusMeteringResult;
import androidx.camera.core.MeteringPoint;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class l1 {

    /* renamed from: a  reason: collision with root package name */
    public final Camera2CameraControlImpl f572a;
    public final Executor b;
    public final ScheduledExecutorService c;
    public ScheduledFuture<?> g;
    public volatile boolean d = false;
    public boolean e = false;
    @NonNull
    public Integer f = 0;
    public long h = 0;
    public boolean i = false;
    public boolean j = false;
    public Camera2CameraControlImpl.CaptureResultListener k = null;
    public Camera2CameraControlImpl.CaptureResultListener l = null;
    public MeteringRectangle[] m = new MeteringRectangle[0];
    public MeteringRectangle[] n = new MeteringRectangle[0];
    public MeteringRectangle[] o = new MeteringRectangle[0];
    public MeteringRectangle[] p = new MeteringRectangle[0];
    public MeteringRectangle[] q = new MeteringRectangle[0];
    public MeteringRectangle[] r = new MeteringRectangle[0];
    public CallbackToFutureAdapter.Completer<FocusMeteringResult> s = null;
    public CallbackToFutureAdapter.Completer<Void> t = null;

    /* loaded from: classes.dex */
    public class a extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f573a;

        public a(l1 l1Var, CallbackToFutureAdapter.Completer completer) {
            this.f573a = completer;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            CallbackToFutureAdapter.Completer completer = this.f573a;
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is closed"));
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            CallbackToFutureAdapter.Completer completer = this.f573a;
            if (completer != null) {
                completer.set(cameraCaptureResult);
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
            CallbackToFutureAdapter.Completer completer = this.f573a;
            if (completer != null) {
                completer.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f574a;

        public b(l1 l1Var, CallbackToFutureAdapter.Completer completer) {
            this.f574a = completer;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            CallbackToFutureAdapter.Completer completer = this.f574a;
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is closed"));
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            CallbackToFutureAdapter.Completer completer = this.f574a;
            if (completer != null) {
                completer.set(cameraCaptureResult);
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
            CallbackToFutureAdapter.Completer completer = this.f574a;
            if (completer != null) {
                completer.setException(new CameraControlInternal.CameraControlException(cameraCaptureFailure));
            }
        }
    }

    public l1(@NonNull Camera2CameraControlImpl camera2CameraControlImpl, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Executor executor) {
        this.f572a = camera2CameraControlImpl;
        this.b = executor;
        this.c = scheduledExecutorService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object B(final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.j1
            @Override // java.lang.Runnable
            public final void run() {
                l1.this.A(completer);
            }
        });
        return "cancelFocusAndMetering";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean C(int i, TotalCaptureResult totalCaptureResult) {
        CaptureRequest request = totalCaptureResult.getRequest();
        MeteringRectangle[] meteringRectangleArr = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS);
        MeteringRectangle[] meteringRectangleArr2 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS);
        MeteringRectangle[] meteringRectangleArr3 = (MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS);
        if (((Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_MODE)).intValue() == i && x(meteringRectangleArr, this.p) && x(meteringRectangleArr2, this.q) && x(meteringRectangleArr3, this.r)) {
            o();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean D(boolean z, MeteringRectangle[] meteringRectangleArr, MeteringRectangle[] meteringRectangleArr2, MeteringRectangle[] meteringRectangleArr3, TotalCaptureResult totalCaptureResult) {
        Integer num = (Integer) totalCaptureResult.get(CaptureResult.CONTROL_AF_STATE);
        if (L()) {
            if (z && num != null) {
                if (this.f.intValue() == 3) {
                    if (num.intValue() == 4) {
                        this.j = true;
                        this.i = true;
                    } else if (num.intValue() == 5) {
                        this.j = false;
                        this.i = true;
                    }
                }
            } else {
                this.j = true;
                this.i = true;
            }
        }
        if (this.i && totalCaptureResult.getRequest() != null) {
            if (meteringRectangleArr.length == 0) {
                meteringRectangleArr = this.p;
            }
            if (meteringRectangleArr2.length == 0) {
                meteringRectangleArr2 = this.q;
            }
            if (meteringRectangleArr3.length == 0) {
                meteringRectangleArr3 = this.r;
            }
            CaptureRequest request = totalCaptureResult.getRequest();
            if (x((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AF_REGIONS), meteringRectangleArr) && x((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AE_REGIONS), meteringRectangleArr2) && x((MeteringRectangle[]) request.get(CaptureRequest.CONTROL_AWB_REGIONS), meteringRectangleArr3)) {
                n(this.j);
                return true;
            }
        }
        if (!this.f.equals(num) && num != null) {
            this.f = num;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E(long j) {
        if (j == this.h) {
            m();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F(final long j) {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.h1
            @Override // java.lang.Runnable
            public final void run() {
                l1.this.E(j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object H(final FocusMeteringAction focusMeteringAction, final Rational rational, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.b.execute(new Runnable() { // from class: androidx.camera.camera2.internal.k1
            @Override // java.lang.Runnable
            public final void run() {
                l1.this.G(completer, focusMeteringAction, rational);
            }
        });
        return "startFocusAndMetering";
    }

    public static int I(int i, int i2, int i3) {
        return Math.min(Math.max(i, i3), i2);
    }

    public static PointF u(@NonNull MeteringPoint meteringPoint, @NonNull Rational rational, @NonNull Rational rational2) {
        if (meteringPoint.getSurfaceAspectRatio() != null) {
            rational2 = meteringPoint.getSurfaceAspectRatio();
        }
        PointF pointF = new PointF(meteringPoint.getX(), meteringPoint.getY());
        if (!rational2.equals(rational)) {
            if (rational2.compareTo(rational) > 0) {
                float doubleValue = (float) (rational2.doubleValue() / rational.doubleValue());
                pointF.y = (((float) ((doubleValue - 1.0d) / 2.0d)) + pointF.y) * (1.0f / doubleValue);
            } else {
                float doubleValue2 = (float) (rational.doubleValue() / rational2.doubleValue());
                pointF.x = (((float) ((doubleValue2 - 1.0d) / 2.0d)) + pointF.x) * (1.0f / doubleValue2);
            }
        }
        return pointF;
    }

    public static MeteringRectangle v(MeteringPoint meteringPoint, PointF pointF, Rect rect) {
        int width = (int) (rect.left + (pointF.x * rect.width()));
        int height = (int) (rect.top + (pointF.y * rect.height()));
        int size = ((int) (meteringPoint.getSize() * rect.width())) / 2;
        int size2 = ((int) (meteringPoint.getSize() * rect.height())) / 2;
        Rect rect2 = new Rect(width - size, height - size2, width + size, height + size2);
        rect2.left = I(rect2.left, rect.right, rect.left);
        rect2.right = I(rect2.right, rect.right, rect.left);
        rect2.top = I(rect2.top, rect.bottom, rect.top);
        rect2.bottom = I(rect2.bottom, rect.bottom, rect.top);
        return new MeteringRectangle(rect2, 1000);
    }

    public static int w(@Nullable MeteringRectangle[] meteringRectangleArr) {
        if (meteringRectangleArr == null) {
            return 0;
        }
        return meteringRectangleArr.length;
    }

    public static boolean x(@Nullable MeteringRectangle[] meteringRectangleArr, @Nullable MeteringRectangle[] meteringRectangleArr2) {
        if (w(meteringRectangleArr) == 0 && w(meteringRectangleArr2) == 0) {
            return true;
        }
        if (w(meteringRectangleArr) != w(meteringRectangleArr2)) {
            return false;
        }
        if (meteringRectangleArr != null && meteringRectangleArr2 != null) {
            for (int i = 0; i < meteringRectangleArr.length; i++) {
                if (!meteringRectangleArr[i].equals(meteringRectangleArr2[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean z(@NonNull MeteringPoint meteringPoint) {
        return meteringPoint.getX() >= 0.0f && meteringPoint.getX() <= 1.0f && meteringPoint.getY() >= 0.0f && meteringPoint.getY() <= 1.0f;
    }

    public void J(boolean z) {
        if (z == this.d) {
            return;
        }
        this.d = z;
        if (this.d) {
            return;
        }
        m();
    }

    public void K(@NonNull CaptureRequest.Builder builder) {
        this.p = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AF_REGIONS);
        this.q = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AE_REGIONS);
        this.r = (MeteringRectangle[]) builder.get(CaptureRequest.CONTROL_AWB_REGIONS);
    }

    public final boolean L() {
        return this.m.length > 0;
    }

    public ListenableFuture<FocusMeteringResult> M(@NonNull final FocusMeteringAction focusMeteringAction, @Nullable final Rational rational) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.g1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object H;
                H = l1.this.H(focusMeteringAction, rational, completer);
                return H;
            }
        });
    }

    /* renamed from: N */
    public void G(@NonNull CallbackToFutureAdapter.Completer<FocusMeteringResult> completer, @NonNull FocusMeteringAction focusMeteringAction, @Nullable Rational rational) {
        if (!this.d) {
            completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
        } else if (focusMeteringAction.getMeteringPointsAf().isEmpty() && focusMeteringAction.getMeteringPointsAe().isEmpty() && focusMeteringAction.getMeteringPointsAwb().isEmpty()) {
            completer.setException(new IllegalArgumentException("No AF/AE/AWB MeteringPoints are added."));
        } else {
            int min = Math.min(focusMeteringAction.getMeteringPointsAf().size(), this.f572a.s());
            int min2 = Math.min(focusMeteringAction.getMeteringPointsAe().size(), this.f572a.r());
            int min3 = Math.min(focusMeteringAction.getMeteringPointsAwb().size(), this.f572a.t());
            if (min + min2 + min3 <= 0) {
                completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints is supported on this camera."));
                return;
            }
            ArrayList<MeteringPoint> arrayList = new ArrayList();
            ArrayList<MeteringPoint> arrayList2 = new ArrayList();
            ArrayList<MeteringPoint> arrayList3 = new ArrayList();
            if (min > 0) {
                arrayList.addAll(focusMeteringAction.getMeteringPointsAf().subList(0, min));
            }
            if (min2 > 0) {
                arrayList2.addAll(focusMeteringAction.getMeteringPointsAe().subList(0, min2));
            }
            if (min3 > 0) {
                arrayList3.addAll(focusMeteringAction.getMeteringPointsAwb().subList(0, min3));
            }
            Rect p = this.f572a.p();
            Rational rational2 = new Rational(p.width(), p.height());
            if (rational == null) {
                rational = rational2;
            }
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            for (MeteringPoint meteringPoint : arrayList) {
                if (z(meteringPoint)) {
                    MeteringRectangle v = v(meteringPoint, u(meteringPoint, rational2, rational), p);
                    if (v.getWidth() != 0 && v.getHeight() != 0) {
                        arrayList4.add(v);
                    }
                }
            }
            for (MeteringPoint meteringPoint2 : arrayList2) {
                if (z(meteringPoint2)) {
                    MeteringRectangle v2 = v(meteringPoint2, u(meteringPoint2, rational2, rational), p);
                    if (v2.getWidth() != 0 && v2.getHeight() != 0) {
                        arrayList5.add(v2);
                    }
                }
            }
            for (MeteringPoint meteringPoint3 : arrayList3) {
                if (z(meteringPoint3)) {
                    MeteringRectangle v3 = v(meteringPoint3, u(meteringPoint3, rational2, rational), p);
                    if (v3.getWidth() != 0 && v3.getHeight() != 0) {
                        arrayList6.add(v3);
                    }
                }
            }
            if (arrayList4.isEmpty() && arrayList5.isEmpty() && arrayList6.isEmpty()) {
                completer.setException(new IllegalArgumentException("None of the specified AF/AE/AWB MeteringPoints are valid."));
                return;
            }
            r("Cancelled by another startFocusAndMetering()");
            s("Cancelled by another startFocusAndMetering()");
            p();
            this.s = completer;
            q((MeteringRectangle[]) arrayList4.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList5.toArray(new MeteringRectangle[0]), (MeteringRectangle[]) arrayList6.toArray(new MeteringRectangle[0]), focusMeteringAction);
        }
    }

    public void O(@Nullable CallbackToFutureAdapter.Completer<CameraCaptureResult> completer) {
        if (!this.d) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(t());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new b(this, completer));
        this.f572a.I(Collections.singletonList(builder.build()));
    }

    public void P(@Nullable CallbackToFutureAdapter.Completer<CameraCaptureResult> completer) {
        if (!this.d) {
            if (completer != null) {
                completer.setException(new CameraControl.OperationCanceledException("Camera is not active."));
                return;
            }
            return;
        }
        CaptureConfig.Builder builder = new CaptureConfig.Builder();
        builder.setTemplateType(t());
        builder.setUseRepeatingSurface(true);
        Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
        builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 1);
        builder.addImplementationOptions(builder2.build());
        builder.addCameraCaptureCallback(new a(this, completer));
        this.f572a.I(Collections.singletonList(builder.build()));
    }

    public void i(@NonNull Camera2ImplConfig.Builder builder) {
        builder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE, Integer.valueOf(this.f572a.w(this.e ? 1 : 4)));
        MeteringRectangle[] meteringRectangleArr = this.m;
        if (meteringRectangleArr.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr);
        }
        MeteringRectangle[] meteringRectangleArr2 = this.n;
        if (meteringRectangleArr2.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AE_REGIONS, meteringRectangleArr2);
        }
        MeteringRectangle[] meteringRectangleArr3 = this.o;
        if (meteringRectangleArr3.length != 0) {
            builder.setCaptureRequestOption(CaptureRequest.CONTROL_AWB_REGIONS, meteringRectangleArr3);
        }
    }

    public void j(boolean z, boolean z2) {
        if (this.d) {
            CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setUseRepeatingSurface(true);
            builder.setTemplateType(t());
            Camera2ImplConfig.Builder builder2 = new Camera2ImplConfig.Builder();
            if (z) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AF_TRIGGER, 2);
            }
            if (Build.VERSION.SDK_INT >= 23 && z2) {
                builder2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER, 2);
            }
            builder.addImplementationOptions(builder2.build());
            this.f572a.I(Collections.singletonList(builder.build()));
        }
    }

    public ListenableFuture<Void> k() {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.camera2.internal.f1
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object B;
                B = l1.this.B(completer);
                return B;
            }
        });
    }

    /* renamed from: l */
    public void A(@Nullable CallbackToFutureAdapter.Completer<Void> completer) {
        s("Cancelled by another cancelFocusAndMetering()");
        r("Cancelled by cancelFocusAndMetering()");
        this.t = completer;
        p();
        if (this.t != null) {
            final int w = this.f572a.w(4);
            Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.d1
                @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
                public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                    boolean C;
                    C = l1.this.C(w, totalCaptureResult);
                    return C;
                }
            };
            this.l = captureResultListener;
            this.f572a.l(captureResultListener);
        }
        if (L()) {
            j(true, false);
        }
        this.m = new MeteringRectangle[0];
        this.n = new MeteringRectangle[0];
        this.o = new MeteringRectangle[0];
        this.e = false;
        this.f572a.R();
    }

    public void m() {
        A(null);
    }

    public final void n(boolean z) {
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.s;
        if (completer != null) {
            completer.set(FocusMeteringResult.create(z));
            this.s = null;
        }
    }

    public final void o() {
        CallbackToFutureAdapter.Completer<Void> completer = this.t;
        if (completer != null) {
            completer.set(null);
            this.t = null;
        }
    }

    public final void p() {
        ScheduledFuture<?> scheduledFuture = this.g;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.g = null;
        }
    }

    public final void q(@NonNull final MeteringRectangle[] meteringRectangleArr, @NonNull final MeteringRectangle[] meteringRectangleArr2, @NonNull final MeteringRectangle[] meteringRectangleArr3, FocusMeteringAction focusMeteringAction) {
        this.f572a.N(this.k);
        p();
        this.m = meteringRectangleArr;
        this.n = meteringRectangleArr2;
        this.o = meteringRectangleArr3;
        if (L()) {
            this.e = true;
            this.i = false;
            this.j = false;
            this.f572a.R();
            P(null);
        } else {
            this.e = false;
            this.i = true;
            this.j = false;
            this.f572a.R();
        }
        this.f = 0;
        final boolean y = y();
        Camera2CameraControlImpl.CaptureResultListener captureResultListener = new Camera2CameraControlImpl.CaptureResultListener() { // from class: androidx.camera.camera2.internal.e1
            @Override // androidx.camera.camera2.internal.Camera2CameraControlImpl.CaptureResultListener
            public final boolean onCaptureResult(TotalCaptureResult totalCaptureResult) {
                boolean D;
                D = l1.this.D(y, meteringRectangleArr, meteringRectangleArr2, meteringRectangleArr3, totalCaptureResult);
                return D;
            }
        };
        this.k = captureResultListener;
        this.f572a.l(captureResultListener);
        if (focusMeteringAction.isAutoCancelEnabled()) {
            final long j = this.h + 1;
            this.h = j;
            this.g = this.c.schedule(new Runnable() { // from class: androidx.camera.camera2.internal.i1
                @Override // java.lang.Runnable
                public final void run() {
                    l1.this.F(j);
                }
            }, focusMeteringAction.getAutoCancelDurationInMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public final void r(String str) {
        this.f572a.N(this.k);
        CallbackToFutureAdapter.Completer<FocusMeteringResult> completer = this.s;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.s = null;
        }
    }

    public final void s(String str) {
        this.f572a.N(this.l);
        CallbackToFutureAdapter.Completer<Void> completer = this.t;
        if (completer != null) {
            completer.setException(new CameraControl.OperationCanceledException(str));
            this.t = null;
        }
    }

    public final int t() {
        return 1;
    }

    public final boolean y() {
        return this.f572a.w(1) == 1;
    }
}
