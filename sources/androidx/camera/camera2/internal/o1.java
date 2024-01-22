package androidx.camera.camera2.internal;

import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.camera2.internal.compat.CameraCharacteristicsCompat;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
/* loaded from: classes.dex */
public class o1 {

    /* renamed from: a  reason: collision with root package name */
    public DeferrableSurface f577a;
    @NonNull
    public final SessionConfig b;

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Surface f578a;
        public final /* synthetic */ SurfaceTexture b;

        public a(o1 o1Var, Surface surface, SurfaceTexture surfaceTexture) {
            this.f578a = surface;
            this.b = surfaceTexture;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r1) {
            this.f578a.release();
            this.b.release();
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            throw new IllegalStateException("Future should never fail. Did it get completed by GC?", th);
        }
    }

    /* loaded from: classes.dex */
    public static class b implements UseCaseConfig<UseCase> {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Config f579a;

        public b() {
            MutableOptionsBundle create = MutableOptionsBundle.create();
            create.insertOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, new m0());
            this.f579a = create;
        }

        @Override // androidx.camera.core.impl.ReadableConfig
        @NonNull
        public Config getConfig() {
            return this.f579a;
        }
    }

    public o1(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        b bVar = new b();
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        Size c = c(cameraCharacteristicsCompat);
        Logger.d("MeteringRepeating", "MerteringSession SurfaceTexture size: " + c);
        surfaceTexture.setDefaultBufferSize(c.getWidth(), c.getHeight());
        Surface surface = new Surface(surfaceTexture);
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(bVar);
        createFrom.setTemplateType(1);
        ImmediateSurface immediateSurface = new ImmediateSurface(surface);
        this.f577a = immediateSurface;
        Futures.addCallback(immediateSurface.getTerminationFuture(), new a(this, surface, surfaceTexture), CameraXExecutors.directExecutor());
        createFrom.addSurface(this.f577a);
        this.b = createFrom.build();
    }

    public static /* synthetic */ int f(Size size, Size size2) {
        return Long.signum((size.getWidth() * size.getHeight()) - (size2.getWidth() * size2.getHeight()));
    }

    public void b() {
        Logger.d("MeteringRepeating", "MeteringRepeating clear!");
        DeferrableSurface deferrableSurface = this.f577a;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        this.f577a = null;
    }

    @NonNull
    public final Size c(@NonNull CameraCharacteristicsCompat cameraCharacteristicsCompat) {
        Size[] outputSizes;
        StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristicsCompat.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (streamConfigurationMap == null) {
            Logger.e("MeteringRepeating", "Can not retrieve SCALER_STREAM_CONFIGURATION_MAP.");
            return new Size(0, 0);
        }
        if (Build.VERSION.SDK_INT < 23) {
            outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
        } else {
            outputSizes = streamConfigurationMap.getOutputSizes(34);
        }
        if (outputSizes == null) {
            Logger.e("MeteringRepeating", "Can not get output size list.");
            return new Size(0, 0);
        }
        return (Size) Collections.min(Arrays.asList(outputSizes), new Comparator() { // from class: androidx.camera.camera2.internal.n1
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int f;
                f = o1.f((Size) obj, (Size) obj2);
                return f;
            }
        });
    }

    @NonNull
    public String d() {
        return "MeteringRepeating";
    }

    @NonNull
    public SessionConfig e() {
        return this.b;
    }
}
