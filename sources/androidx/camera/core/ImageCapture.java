package androidx.camera.core;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.annotation.GuardedBy;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.arch.core.util.Function;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageSaver;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.CaptureProcessor;
import androidx.camera.core.impl.CaptureStage;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.Exif;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.AsyncFunction;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.IoConfig;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.UseCaseEventConfig;
import androidx.camera.core.internal.YuvToJpegProcessor;
import androidx.camera.core.internal.compat.quirk.DeviceQuirks;
import androidx.camera.core.internal.compat.quirk.ImageCaptureWashedOutImageQuirk;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.x;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.coveiot.android.camera.utils.ViewExtensionsKt;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class ImageCapture extends UseCase {
    public static final int CAPTURE_MODE_MAXIMIZE_QUALITY = 0;
    public static final int CAPTURE_MODE_MINIMIZE_LATENCY = 1;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    public static final int ERROR_CAMERA_CLOSED = 3;
    public static final int ERROR_CAPTURE_FAILED = 2;
    public static final int ERROR_FILE_IO = 1;
    public static final int ERROR_INVALID_CAMERA = 4;
    public static final int ERROR_UNKNOWN = 0;
    public static final int FLASH_MODE_AUTO = 0;
    public static final int FLASH_MODE_OFF = 2;
    public static final int FLASH_MODE_ON = 1;
    public SessionConfig.Builder A;
    public z1 B;
    public s1 C;
    public CameraCaptureCallback D;
    public DeferrableSurface E;
    public l F;
    public final Executor G;
    public final CaptureCallbackChecker l;
    public final ImageReaderProxy.OnImageAvailableListener m;
    @NonNull
    public final Executor n;
    public final int o;
    public final boolean p;
    @GuardedBy("mLockedFlashMode")
    public final AtomicReference<Integer> q;
    @GuardedBy("mLockedFlashMode")
    public int r;
    public Rational s;
    public ExecutorService t;
    public CaptureConfig u;
    public CaptureBundle v;
    public int w;
    public CaptureProcessor x;
    public boolean y;
    public final boolean z;

    /* loaded from: classes.dex */
    public static final class Builder implements UseCaseConfig.Builder<ImageCapture, ImageCaptureConfig, Builder>, ImageOutputConfig.Builder<Builder>, IoConfig.Builder<Builder> {

        /* renamed from: a  reason: collision with root package name */
        public final MutableOptionsBundle f627a;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Builder fromConfig(@NonNull Config config) {
            return new Builder(MutableOptionsBundle.from(config));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public MutableConfig getMutableConfig() {
            return this.f627a;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setBufferFormat(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCaptureBundle(@NonNull CaptureBundle captureBundle) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_BUNDLE, captureBundle);
            return this;
        }

        @NonNull
        public Builder setCaptureMode(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCaptureProcessor(@NonNull CaptureProcessor captureProcessor) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, captureProcessor);
            return this;
        }

        @NonNull
        public Builder setFlashMode(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_FLASH_MODE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setImageReaderProxyProvider(@NonNull ImageReaderProxyProvider imageReaderProxyProvider) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_IMAGE_READER_PROXY_PROVIDER, imageReaderProxyProvider);
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setMaxCaptureStages(int i) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setSoftwareJpegEncoderRequested(boolean z) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER, Boolean.valueOf(z));
            return this;
        }

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public /* bridge */ /* synthetic */ Builder setSupportedResolutions(@NonNull List list) {
            return setSupportedResolutions((List<Pair<Integer, Size[]>>) list);
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public /* bridge */ /* synthetic */ Object setTargetClass(@NonNull Class cls) {
            return setTargetClass((Class<ImageCapture>) cls);
        }

        public Builder(MutableOptionsBundle mutableOptionsBundle) {
            this.f627a = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(ImageCapture.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(ImageCapture.class);
        }

        @Override // androidx.camera.core.ExtendableBuilder
        @NonNull
        public ImageCapture build() {
            int intValue;
            if (getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, null) != null && getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, null) != null) {
                throw new IllegalArgumentException("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
            }
            Integer num = (Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num != null) {
                Preconditions.checkArgument(getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) == null, "Cannot set buffer format with CaptureProcessor defined.");
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, num);
            } else if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) != null) {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
            } else {
                getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
            }
            ImageCapture imageCapture = new ImageCapture(getUseCaseConfig());
            Size size = (Size) getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, null);
            if (size != null) {
                imageCapture.setCropAspectRatio(new Rational(size.getWidth(), size.getHeight()));
            }
            Preconditions.checkArgument(((Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, 2)).intValue() >= 1, "Maximum outstanding image count must be at least 1");
            Preconditions.checkNotNull((Executor) getMutableConfig().retrieveOption(IoConfig.OPTION_IO_EXECUTOR, CameraXExecutors.ioExecutor()), "The IO executor can't be null");
            MutableConfig mutableConfig = getMutableConfig();
            Config.Option<Integer> option = ImageCaptureConfig.OPTION_FLASH_MODE;
            if (!mutableConfig.containsOption(option) || (intValue = ((Integer) getMutableConfig().retrieveOption(option)).intValue()) == 0 || intValue == 1 || intValue == 2) {
                return imageCapture;
            }
            throw new IllegalArgumentException("The flash mode is not allowed to set: " + intValue);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ImageCaptureConfig getUseCaseConfig() {
            return new ImageCaptureConfig(OptionsBundle.from(this.f627a));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCameraSelector(@NonNull CameraSelector cameraSelector) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAMERA_SELECTOR, cameraSelector);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setCaptureOptionUnpacker(@NonNull CaptureConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setDefaultCaptureConfig(@NonNull CaptureConfig captureConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG, captureConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setDefaultResolution(@NonNull Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_DEFAULT_RESOLUTION, size);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setDefaultSessionConfig(@NonNull SessionConfig sessionConfig) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG, sessionConfig);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.IoConfig.Builder
        @NonNull
        public Builder setIoExecutor(@NonNull Executor executor) {
            getMutableConfig().insertOption(IoConfig.OPTION_IO_EXECUTOR, executor);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setMaxResolution(@NonNull Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_MAX_RESOLUTION, size);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setSessionOptionUnpacker(@NonNull SessionConfig.OptionUnpacker optionUnpacker) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, optionUnpacker);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setSupportedResolutions(@NonNull List<Pair<Integer, Size[]>> list) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, list);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setSurfaceOccupancyPriority(int i) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(i));
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        public Builder setTargetAspectRatio(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetClass(@NonNull Class<ImageCapture> cls) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, cls);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) == null) {
                setTargetName(cls.getCanonicalName() + "-" + UUID.randomUUID());
            }
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        public Builder setTargetName(@NonNull String str) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, str);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        public Builder setTargetResolution(@NonNull Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, size);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        public Builder setTargetRotation(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(i));
            return this;
        }

        @Override // androidx.camera.core.internal.UseCaseEventConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setUseCaseEventCallback(@NonNull UseCase.EventCallback eventCallback) {
            getMutableConfig().insertOption(UseCaseEventConfig.OPTION_USE_CASE_EVENT_CALLBACK, eventCallback);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class CaptureCallbackChecker extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final Set<b> f628a = new HashSet();

        /* loaded from: classes.dex */
        public interface CaptureResultChecker<T> {
            @Nullable
            T check(@NonNull CameraCaptureResult cameraCaptureResult);
        }

        /* loaded from: classes.dex */
        public class a implements b {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ CaptureResultChecker f629a;
            public final /* synthetic */ CallbackToFutureAdapter.Completer b;
            public final /* synthetic */ long c;
            public final /* synthetic */ long d;
            public final /* synthetic */ Object e;

            public a(CaptureCallbackChecker captureCallbackChecker, CaptureResultChecker captureResultChecker, CallbackToFutureAdapter.Completer completer, long j, long j2, Object obj) {
                this.f629a = captureResultChecker;
                this.b = completer;
                this.c = j;
                this.d = j2;
                this.e = obj;
            }

            @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.b
            public boolean a(@NonNull CameraCaptureResult cameraCaptureResult) {
                Object check = this.f629a.check(cameraCaptureResult);
                if (check != null) {
                    this.b.set(check);
                    return true;
                } else if (this.c <= 0 || SystemClock.elapsedRealtime() - this.c <= this.d) {
                    return false;
                } else {
                    this.b.set(this.e);
                    return true;
                }
            }
        }

        /* loaded from: classes.dex */
        public interface b {
            boolean a(@NonNull CameraCaptureResult cameraCaptureResult);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Object f(CaptureResultChecker captureResultChecker, long j, long j2, Object obj, CallbackToFutureAdapter.Completer completer) throws Exception {
            b(new a(this, captureResultChecker, completer, j, j2, obj));
            return "checkCaptureResult";
        }

        public void b(b bVar) {
            synchronized (this.f628a) {
                this.f628a.add(bVar);
            }
        }

        public <T> ListenableFuture<T> c(CaptureResultChecker<T> captureResultChecker) {
            return d(captureResultChecker, 0L, null);
        }

        public <T> ListenableFuture<T> d(final CaptureResultChecker<T> captureResultChecker, final long j, final T t) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                final long elapsedRealtime = i != 0 ? SystemClock.elapsedRealtime() : 0L;
                return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.d1
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object f;
                        f = ImageCapture.CaptureCallbackChecker.this.f(captureResultChecker, elapsedRealtime, j, t, completer);
                        return f;
                    }
                });
            }
            throw new IllegalArgumentException("Invalid timeout value: " + j);
        }

        public final void e(@NonNull CameraCaptureResult cameraCaptureResult) {
            synchronized (this.f628a) {
                HashSet hashSet = null;
                Iterator it = new HashSet(this.f628a).iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    if (bVar.a(cameraCaptureResult)) {
                        if (hashSet == null) {
                            hashSet = new HashSet();
                        }
                        hashSet.add(bVar);
                    }
                }
                if (hashSet != null) {
                    this.f628a.removeAll(hashSet);
                }
            }
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            e(cameraCaptureResult);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface CaptureMode {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static final class Defaults implements ConfigProvider<ImageCaptureConfig> {

        /* renamed from: a  reason: collision with root package name */
        public static final ImageCaptureConfig f630a = new Builder().setSurfaceOccupancyPriority(4).setTargetAspectRatio(0).getUseCaseConfig();

        @Override // androidx.camera.core.impl.ConfigProvider
        @NonNull
        public ImageCaptureConfig getConfig() {
            return f630a;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface FlashMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface ImageCaptureError {
    }

    /* loaded from: classes.dex */
    public static final class Metadata {

        /* renamed from: a  reason: collision with root package name */
        public boolean f631a;
        public boolean b = false;
        public boolean c;
        @Nullable
        public Location d;

        @Nullable
        public Location getLocation() {
            return this.d;
        }

        public boolean isReversedHorizontal() {
            return this.f631a;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public boolean isReversedHorizontalSet() {
            return this.b;
        }

        public boolean isReversedVertical() {
            return this.c;
        }

        public void setLocation(@Nullable Location location) {
            this.d = location;
        }

        public void setReversedHorizontal(boolean z) {
            this.f631a = z;
            this.b = true;
        }

        public void setReversedVertical(boolean z) {
            this.c = z;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class OnImageCapturedCallback {
        public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
        }

        public void onError(@NonNull ImageCaptureException imageCaptureException) {
        }
    }

    /* loaded from: classes.dex */
    public interface OnImageSavedCallback {
        void onError(@NonNull ImageCaptureException imageCaptureException);

        void onImageSaved(@NonNull OutputFileResults outputFileResults);
    }

    /* loaded from: classes.dex */
    public static final class OutputFileOptions {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final File f632a;
        @Nullable
        public final ContentResolver b;
        @Nullable
        public final Uri c;
        @Nullable
        public final ContentValues d;
        @Nullable
        public final OutputStream e;
        @NonNull
        public final Metadata f;

        public OutputFileOptions(@Nullable File file, @Nullable ContentResolver contentResolver, @Nullable Uri uri, @Nullable ContentValues contentValues, @Nullable OutputStream outputStream, @Nullable Metadata metadata) {
            this.f632a = file;
            this.b = contentResolver;
            this.c = uri;
            this.d = contentValues;
            this.e = outputStream;
            this.f = metadata == null ? new Metadata() : metadata;
        }

        @Nullable
        public ContentResolver a() {
            return this.b;
        }

        @Nullable
        public ContentValues b() {
            return this.d;
        }

        @Nullable
        public File c() {
            return this.f632a;
        }

        @Nullable
        public OutputStream d() {
            return this.e;
        }

        @Nullable
        public Uri e() {
            return this.c;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Metadata getMetadata() {
            return this.f;
        }

        /* loaded from: classes.dex */
        public static final class Builder {
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            public File f633a;
            @Nullable
            public ContentResolver b;
            @Nullable
            public Uri c;
            @Nullable
            public ContentValues d;
            @Nullable
            public OutputStream e;
            @Nullable
            public Metadata f;

            public Builder(@NonNull File file) {
                this.f633a = file;
            }

            @NonNull
            public OutputFileOptions build() {
                return new OutputFileOptions(this.f633a, this.b, this.c, this.d, this.e, this.f);
            }

            @NonNull
            public Builder setMetadata(@NonNull Metadata metadata) {
                this.f = metadata;
                return this;
            }

            public Builder(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull ContentValues contentValues) {
                this.b = contentResolver;
                this.c = uri;
                this.d = contentValues;
            }

            public Builder(@NonNull OutputStream outputStream) {
                this.e = outputStream;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class OutputFileResults {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Uri f634a;

        public OutputFileResults(@Nullable Uri uri) {
            this.f634a = uri;
        }

        @Nullable
        public Uri getSavedUri() {
            return this.f634a;
        }
    }

    /* loaded from: classes.dex */
    public class a extends CameraCaptureCallback {
        public a(ImageCapture imageCapture) {
        }
    }

    /* loaded from: classes.dex */
    public class b implements ImageSaver.OnImageSavedCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnImageSavedCallback f635a;

        public b(ImageCapture imageCapture, OnImageSavedCallback onImageSavedCallback) {
            this.f635a = onImageSavedCallback;
        }

        @Override // androidx.camera.core.ImageSaver.OnImageSavedCallback
        public void onError(@NonNull ImageSaver.SaveError saveError, @NonNull String str, @Nullable Throwable th) {
            this.f635a.onError(new ImageCaptureException(i.f640a[saveError.ordinal()] != 1 ? 0 : 1, str, th));
        }

        @Override // androidx.camera.core.ImageSaver.OnImageSavedCallback
        public void onImageSaved(@NonNull OutputFileResults outputFileResults) {
            this.f635a.onImageSaved(outputFileResults);
        }
    }

    /* loaded from: classes.dex */
    public class c extends OnImageCapturedCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OutputFileOptions f636a;
        public final /* synthetic */ Executor b;
        public final /* synthetic */ ImageSaver.OnImageSavedCallback c;
        public final /* synthetic */ OnImageSavedCallback d;

        public c(OutputFileOptions outputFileOptions, Executor executor, ImageSaver.OnImageSavedCallback onImageSavedCallback, OnImageSavedCallback onImageSavedCallback2) {
            this.f636a = outputFileOptions;
            this.b = executor;
            this.c = onImageSavedCallback;
            this.d = onImageSavedCallback2;
        }

        @Override // androidx.camera.core.ImageCapture.OnImageCapturedCallback
        public void onCaptureSuccess(@NonNull ImageProxy imageProxy) {
            ImageCapture.this.n.execute(new ImageSaver(imageProxy, this.f636a, imageProxy.getImageInfo().getRotationDegrees(), this.b, ImageCapture.this.G, this.c));
        }

        @Override // androidx.camera.core.ImageCapture.OnImageCapturedCallback
        public void onError(@NonNull ImageCaptureException imageCaptureException) {
            this.d.onError(imageCaptureException);
        }
    }

    /* loaded from: classes.dex */
    public class d implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ m f637a;
        public final /* synthetic */ CallbackToFutureAdapter.Completer b;

        public d(m mVar, CallbackToFutureAdapter.Completer completer) {
            this.f637a = mVar;
            this.b = completer;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(Void r2) {
            ImageCapture.this.j0(this.f637a);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            ImageCapture.this.j0(this.f637a);
            this.b.setException(th);
        }
    }

    /* loaded from: classes.dex */
    public class e implements ThreadFactory {
        public final AtomicInteger h = new AtomicInteger(0);

        public e(ImageCapture imageCapture) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "CameraX-image_capture_" + this.h.getAndIncrement());
        }
    }

    /* loaded from: classes.dex */
    public class f implements CaptureCallbackChecker.CaptureResultChecker<CameraCaptureResult> {
        public f(ImageCapture imageCapture) {
        }

        @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.CaptureResultChecker
        /* renamed from: a */
        public CameraCaptureResult check(@NonNull CameraCaptureResult cameraCaptureResult) {
            if (Logger.isDebugEnabled("ImageCapture")) {
                Logger.d("ImageCapture", "preCaptureState, AE=" + cameraCaptureResult.getAeState() + " AF =" + cameraCaptureResult.getAfState() + " AWB=" + cameraCaptureResult.getAwbState());
            }
            return cameraCaptureResult;
        }
    }

    /* loaded from: classes.dex */
    public class g implements CaptureCallbackChecker.CaptureResultChecker<Boolean> {
        public g() {
        }

        @Override // androidx.camera.core.ImageCapture.CaptureCallbackChecker.CaptureResultChecker
        /* renamed from: a */
        public Boolean check(@NonNull CameraCaptureResult cameraCaptureResult) {
            if (Logger.isDebugEnabled("ImageCapture")) {
                Logger.d("ImageCapture", "checkCaptureResult, AE=" + cameraCaptureResult.getAeState() + " AF =" + cameraCaptureResult.getAfState() + " AWB=" + cameraCaptureResult.getAwbState());
            }
            if (ImageCapture.this.J(cameraCaptureResult)) {
                return Boolean.TRUE;
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public class h extends CameraCaptureCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f639a;

        public h(ImageCapture imageCapture, CallbackToFutureAdapter.Completer completer) {
            this.f639a = completer;
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCancelled() {
            this.f639a.setException(new androidx.camera.core.h("Capture request is cancelled because camera is closed"));
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureCompleted(@NonNull CameraCaptureResult cameraCaptureResult) {
            this.f639a.set(null);
        }

        @Override // androidx.camera.core.impl.CameraCaptureCallback
        public void onCaptureFailed(@NonNull CameraCaptureFailure cameraCaptureFailure) {
            this.f639a.setException(new j("Capture request failed with reason " + cameraCaptureFailure.getReason()));
        }
    }

    /* loaded from: classes.dex */
    public static /* synthetic */ class i {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f640a;

        static {
            int[] iArr = new int[ImageSaver.SaveError.values().length];
            f640a = iArr;
            try {
                iArr[ImageSaver.SaveError.FILE_IO_FAILED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class j extends RuntimeException {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public j(String str, Throwable th) {
            super(str, th);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public j(String str) {
            super(str);
        }
    }

    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class k {

        /* renamed from: a  reason: collision with root package name */
        public final int f641a;
        @IntRange(from = 1, to = ViewExtensionsKt.ANIMATION_SLOW_MILLIS)
        public final int b;
        public final Rational c;
        @NonNull
        public final Executor d;
        @NonNull
        public final OnImageCapturedCallback e;
        public AtomicBoolean f = new AtomicBoolean(false);
        public final Rect g;

        public k(int i, @IntRange(from = 1, to = 100) int i2, Rational rational, @Nullable Rect rect, @NonNull Executor executor, @NonNull OnImageCapturedCallback onImageCapturedCallback) {
            this.f641a = i;
            this.b = i2;
            if (rational != null) {
                Preconditions.checkArgument(!rational.isZero(), "Target ratio cannot be zero");
                Preconditions.checkArgument(rational.floatValue() > 0.0f, "Target ratio must be positive");
            }
            this.c = rational;
            this.g = rect;
            this.d = executor;
            this.e = onImageCapturedCallback;
        }

        @NonNull
        public static Rect d(@NonNull Rect rect, int i, @NonNull Size size, int i2) {
            Matrix matrix = new Matrix();
            matrix.setRotate(i2 - i);
            float[] sizeToVertexes = ImageUtil.sizeToVertexes(size);
            matrix.mapPoints(sizeToVertexes);
            matrix.postTranslate(-ImageUtil.min(sizeToVertexes[0], sizeToVertexes[2], sizeToVertexes[4], sizeToVertexes[6]), -ImageUtil.min(sizeToVertexes[1], sizeToVertexes[3], sizeToVertexes[5], sizeToVertexes[7]));
            matrix.invert(matrix);
            RectF rectF = new RectF();
            matrix.mapRect(rectF, new RectF(rect));
            rectF.sort();
            Rect rect2 = new Rect();
            rectF.round(rect2);
            return rect2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(ImageProxy imageProxy) {
            this.e.onCaptureSuccess(imageProxy);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(int i, String str, Throwable th) {
            this.e.onError(new ImageCaptureException(i, str, th));
        }

        public void c(ImageProxy imageProxy) {
            Size size;
            int rotation;
            if (!this.f.compareAndSet(false, true)) {
                imageProxy.close();
                return;
            }
            if (new ExifRotationAvailability().shouldUseExifOrientation(imageProxy)) {
                try {
                    ByteBuffer buffer = imageProxy.getPlanes()[0].getBuffer();
                    buffer.rewind();
                    byte[] bArr = new byte[buffer.capacity()];
                    buffer.get(bArr);
                    Exif createFromInputStream = Exif.createFromInputStream(new ByteArrayInputStream(bArr));
                    buffer.rewind();
                    size = new Size(createFromInputStream.getWidth(), createFromInputStream.getHeight());
                    rotation = createFromInputStream.getRotation();
                } catch (IOException e) {
                    g(1, "Unable to parse JPEG exif", e);
                    imageProxy.close();
                    return;
                }
            } else {
                size = new Size(imageProxy.getWidth(), imageProxy.getHeight());
                rotation = this.f641a;
            }
            final a2 a2Var = new a2(imageProxy, size, k1.a(imageProxy.getImageInfo().getTagBundle(), imageProxy.getImageInfo().getTimestamp(), rotation));
            Rect rect = this.g;
            if (rect != null) {
                a2Var.setCropRect(d(rect, this.f641a, size, rotation));
            } else {
                Rational rational = this.c;
                if (rational != null) {
                    if (rotation % 180 != 0) {
                        rational = new Rational(this.c.getDenominator(), this.c.getNumerator());
                    }
                    Size size2 = new Size(a2Var.getWidth(), a2Var.getHeight());
                    if (ImageUtil.isAspectRatioValid(size2, rational)) {
                        a2Var.setCropRect(ImageUtil.computeCropRectFromAspectRatio(size2, rational));
                    }
                }
            }
            try {
                this.d.execute(new Runnable() { // from class: androidx.camera.core.f1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ImageCapture.k.this.e(a2Var);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.e("ImageCapture", "Unable to post to the supplied executor.");
                imageProxy.close();
            }
        }

        public void g(final int i, final String str, final Throwable th) {
            if (this.f.compareAndSet(false, true)) {
                try {
                    this.d.execute(new Runnable() { // from class: androidx.camera.core.e1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ImageCapture.k.this.f(i, str, th);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    Logger.e("ImageCapture", "Unable to post to the supplied executor.");
                }
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class l implements x.a {
        @GuardedBy("mLock")
        public final b e;
        public final int f;
        @GuardedBy("mLock")

        /* renamed from: a  reason: collision with root package name */
        public final Deque<k> f642a = new ArrayDeque();
        @GuardedBy("mLock")
        public k b = null;
        @GuardedBy("mLock")
        public ListenableFuture<ImageProxy> c = null;
        @GuardedBy("mLock")
        public int d = 0;
        public final Object g = new Object();

        /* loaded from: classes.dex */
        public class a implements FutureCallback<ImageProxy> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ k f643a;

            public a(k kVar) {
                this.f643a = kVar;
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            /* renamed from: a */
            public void onSuccess(@Nullable ImageProxy imageProxy) {
                synchronized (l.this.g) {
                    Preconditions.checkNotNull(imageProxy);
                    c2 c2Var = new c2(imageProxy);
                    c2Var.a(l.this);
                    l.this.d++;
                    this.f643a.c(c2Var);
                    l lVar = l.this;
                    lVar.b = null;
                    lVar.c = null;
                    lVar.c();
                }
            }

            @Override // androidx.camera.core.impl.utils.futures.FutureCallback
            public void onFailure(Throwable th) {
                synchronized (l.this.g) {
                    if (!(th instanceof CancellationException)) {
                        this.f643a.g(ImageCapture.G(th), th != null ? th.getMessage() : "Unknown error", th);
                    }
                    l lVar = l.this;
                    lVar.b = null;
                    lVar.c = null;
                    lVar.c();
                }
            }
        }

        /* loaded from: classes.dex */
        public interface b {
            @NonNull
            ListenableFuture<ImageProxy> a(@NonNull k kVar);
        }

        public l(int i, @NonNull b bVar) {
            this.f = i;
            this.e = bVar;
        }

        @Override // androidx.camera.core.x.a
        public void a(ImageProxy imageProxy) {
            synchronized (this.g) {
                this.d--;
                c();
            }
        }

        public void b(@NonNull Throwable th) {
            k kVar;
            ListenableFuture<ImageProxy> listenableFuture;
            ArrayList<k> arrayList;
            synchronized (this.g) {
                kVar = this.b;
                this.b = null;
                listenableFuture = this.c;
                this.c = null;
                arrayList = new ArrayList(this.f642a);
                this.f642a.clear();
            }
            if (kVar != null && listenableFuture != null) {
                kVar.g(ImageCapture.G(th), th.getMessage(), th);
                listenableFuture.cancel(true);
            }
            for (k kVar2 : arrayList) {
                kVar2.g(ImageCapture.G(th), th.getMessage(), th);
            }
        }

        public void c() {
            synchronized (this.g) {
                if (this.b != null) {
                    return;
                }
                if (this.d >= this.f) {
                    Logger.w("ImageCapture", "Too many acquire images. Close image to be able to process next.");
                    return;
                }
                k poll = this.f642a.poll();
                if (poll == null) {
                    return;
                }
                this.b = poll;
                ListenableFuture<ImageProxy> a2 = this.e.a(poll);
                this.c = a2;
                Futures.addCallback(a2, new a(poll), CameraXExecutors.directExecutor());
            }
        }

        public void d(@NonNull k kVar) {
            synchronized (this.g) {
                this.f642a.offer(kVar);
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = Integer.valueOf(this.b != null ? 1 : 0);
                objArr[1] = Integer.valueOf(this.f642a.size());
                Logger.d("ImageCapture", String.format(locale, "Send image capture request [current, pending] = [%d, %d]", objArr));
                c();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class m {

        /* renamed from: a  reason: collision with root package name */
        public CameraCaptureResult f644a = CameraCaptureResult.EmptyCameraCaptureResult.create();
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
    }

    public ImageCapture(@NonNull ImageCaptureConfig imageCaptureConfig) {
        super(imageCaptureConfig);
        this.l = new CaptureCallbackChecker();
        this.m = new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.y0
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                ImageCapture.S(imageReaderProxy);
            }
        };
        this.q = new AtomicReference<>(null);
        this.r = -1;
        this.s = null;
        this.y = false;
        ImageCaptureConfig imageCaptureConfig2 = (ImageCaptureConfig) getCurrentConfig();
        if (imageCaptureConfig2.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE)) {
            this.o = imageCaptureConfig2.getCaptureMode();
        } else {
            this.o = 1;
        }
        Executor executor = (Executor) Preconditions.checkNotNull(imageCaptureConfig2.getIoExecutor(CameraXExecutors.ioExecutor()));
        this.n = executor;
        this.G = CameraXExecutors.newSequentialExecutor(executor);
        if (this.o == 0) {
            this.p = true;
        } else {
            this.p = false;
        }
        boolean z = DeviceQuirks.get(ImageCaptureWashedOutImageQuirk.class) != null;
        this.z = z;
        if (z) {
            Logger.d("ImageCapture", "Open and close torch to replace the flash fired by flash mode.");
        }
    }

    public static boolean E(@NonNull MutableConfig mutableConfig) {
        Config.Option<Boolean> option = ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER;
        Boolean bool = Boolean.FALSE;
        boolean z = false;
        if (((Boolean) mutableConfig.retrieveOption(option, bool)).booleanValue()) {
            boolean z2 = true;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 26) {
                Logger.w("ImageCapture", "Software JPEG only supported on API 26+, but current API level is " + i2);
                z2 = false;
            }
            Integer num = (Integer) mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (num != null && num.intValue() != 256) {
                Logger.w("ImageCapture", "Software JPEG cannot be used with non-JPEG output buffer format.");
                z2 = false;
            }
            if (mutableConfig.retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) != null) {
                Logger.w("ImageCapture", "CaptureProcessor is set, unable to use software JPEG.");
            } else {
                z = z2;
            }
            if (!z) {
                Logger.w("ImageCapture", "Unable to support software JPEG. Disabling.");
                mutableConfig.insertOption(option, bool);
            }
        }
        return z;
    }

    public static int G(Throwable th) {
        if (th instanceof androidx.camera.core.h) {
            return 3;
        }
        return th instanceof j ? 2 : 0;
    }

    public static /* synthetic */ void M() {
    }

    public static /* synthetic */ void N(YuvToJpegProcessor yuvToJpegProcessor) {
        if (Build.VERSION.SDK_INT >= 26) {
            yuvToJpegProcessor.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(String str, ImageCaptureConfig imageCaptureConfig, Size size, SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
        B();
        if (isCurrentCamera(str)) {
            SessionConfig.Builder D = D(str, imageCaptureConfig, size);
            this.A = D;
            updateSessionConfig(D.build());
            notifyReset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object Q(CaptureConfig.Builder builder, List list, CaptureStage captureStage, CallbackToFutureAdapter.Completer completer) throws Exception {
        builder.addCameraCaptureCallback(new h(this, completer));
        list.add(builder.build());
        return "issueTakePicture[stage=" + captureStage.getId() + "]";
    }

    public static /* synthetic */ Void R(List list) {
        return null;
    }

    public static /* synthetic */ void S(ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireLatestImage = imageReaderProxy.acquireLatestImage();
            Log.d("ImageCapture", "Discarding ImageProxy which was inadvertently acquired: " + acquireLatestImage);
            if (acquireLatestImage != null) {
                acquireLatestImage.close();
            }
        } catch (IllegalStateException e2) {
            Log.e("ImageCapture", "Failed to acquire latest image.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object U(m mVar, final CallbackToFutureAdapter.Completer completer) throws Exception {
        CameraControlInternal cameraControl = getCameraControl();
        mVar.b = true;
        cameraControl.enableTorch(true).addListener(new Runnable() { // from class: androidx.camera.core.q0
            @Override // java.lang.Runnable
            public final void run() {
                CallbackToFutureAdapter.Completer.this.set(null);
            }
        }, CameraXExecutors.directExecutor());
        return "openTorch";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture V(m mVar, CameraCaptureResult cameraCaptureResult) throws Exception {
        mVar.f644a = cameraCaptureResult;
        p0(mVar);
        if (K(mVar)) {
            if (this.z) {
                return i0(mVar);
            }
            return n0(mVar);
        }
        return Futures.immediateFuture(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture W(m mVar, Void r2) throws Exception {
        return A(mVar);
    }

    public static /* synthetic */ Void X(Boolean bool) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y(OnImageCapturedCallback onImageCapturedCallback) {
        onImageCapturedCallback.onError(new ImageCaptureException(4, "Not bound to a valid Camera [" + this + "]", null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object b0(final k kVar, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.B.setOnImageAvailableListener(new ImageReaderProxy.OnImageAvailableListener() { // from class: androidx.camera.core.x0
            @Override // androidx.camera.core.impl.ImageReaderProxy.OnImageAvailableListener
            public final void onImageAvailable(ImageReaderProxy imageReaderProxy) {
                ImageCapture.c0(CallbackToFutureAdapter.Completer.this, imageReaderProxy);
            }
        }, CameraXExecutors.mainThreadExecutor());
        m mVar = new m();
        final FutureChain transformAsync = FutureChain.from(k0(mVar)).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.a1
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture d0;
                d0 = ImageCapture.this.d0(kVar, (Void) obj);
                return d0;
            }
        }, this.t);
        Futures.addCallback(transformAsync, new d(mVar, completer), this.t);
        completer.addCancellationListener(new Runnable() { // from class: androidx.camera.core.r0
            @Override // java.lang.Runnable
            public final void run() {
                ListenableFuture.this.cancel(true);
            }
        }, CameraXExecutors.directExecutor());
        return "takePictureInternal";
    }

    public static /* synthetic */ void c0(CallbackToFutureAdapter.Completer completer, ImageReaderProxy imageReaderProxy) {
        try {
            ImageProxy acquireLatestImage = imageReaderProxy.acquireLatestImage();
            if (acquireLatestImage != null) {
                if (!completer.set(acquireLatestImage)) {
                    acquireLatestImage.close();
                }
            } else {
                completer.setException(new IllegalStateException("Unable to acquire image"));
            }
        } catch (IllegalStateException e2) {
            completer.setException(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ ListenableFuture d0(k kVar, Void r2) throws Exception {
        return L(kVar);
    }

    public static /* synthetic */ Void f0(CameraCaptureResult cameraCaptureResult) {
        return null;
    }

    public static /* synthetic */ void g0() {
    }

    public ListenableFuture<Boolean> A(m mVar) {
        if (!this.p && !mVar.d && !mVar.b) {
            return Futures.immediateFuture(Boolean.FALSE);
        }
        return this.l.d(new g(), 1000L, Boolean.FALSE);
    }

    @UiThread
    public void B() {
        Threads.checkMainThread();
        DeferrableSurface deferrableSurface = this.E;
        this.E = null;
        this.B = null;
        this.C = null;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
    }

    public final void C(@NonNull m mVar) {
        if (mVar.b) {
            CameraControlInternal cameraControl = getCameraControl();
            mVar.b = false;
            cameraControl.enableTorch(false).addListener(new Runnable() { // from class: androidx.camera.core.s0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageCapture.M();
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    @UiThread
    public SessionConfig.Builder D(@NonNull final String str, @NonNull final ImageCaptureConfig imageCaptureConfig, @NonNull final Size size) {
        YuvToJpegProcessor yuvToJpegProcessor;
        int i2;
        Threads.checkMainThread();
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(imageCaptureConfig);
        createFrom.addRepeatingCameraCaptureCallback(this.l);
        if (imageCaptureConfig.getImageReaderProxyProvider() != null) {
            this.B = new z1(imageCaptureConfig.getImageReaderProxyProvider().newInstance(size.getWidth(), size.getHeight(), getImageFormat(), 2, 0L));
            this.D = new a(this);
        } else {
            CaptureProcessor captureProcessor = this.x;
            if (captureProcessor == null && !this.y) {
                n1 n1Var = new n1(size.getWidth(), size.getHeight(), getImageFormat(), 2);
                this.D = n1Var.g();
                this.B = new z1(n1Var);
            } else {
                final YuvToJpegProcessor yuvToJpegProcessor2 = null;
                int imageFormat = getImageFormat();
                int imageFormat2 = getImageFormat();
                if (this.y) {
                    Preconditions.checkState(this.x == null, "CaptureProcessor should not be set if software JPEG is to be used.");
                    if (Build.VERSION.SDK_INT >= 26) {
                        Logger.i("ImageCapture", "Using software JPEG encoder.");
                        yuvToJpegProcessor = new YuvToJpegProcessor(H(), this.w);
                        i2 = 256;
                        yuvToJpegProcessor2 = yuvToJpegProcessor;
                    } else {
                        throw new IllegalStateException("Software JPEG only supported on API 26+");
                    }
                } else {
                    yuvToJpegProcessor = captureProcessor;
                    i2 = imageFormat2;
                }
                s1 s1Var = new s1(size.getWidth(), size.getHeight(), imageFormat, this.w, this.t, F(w.c()), yuvToJpegProcessor, i2);
                this.C = s1Var;
                this.D = s1Var.b();
                this.B = new z1(this.C);
                if (yuvToJpegProcessor2 != null) {
                    this.C.c().addListener(new Runnable() { // from class: androidx.camera.core.p0
                        @Override // java.lang.Runnable
                        public final void run() {
                            ImageCapture.N(YuvToJpegProcessor.this);
                        }
                    }, CameraXExecutors.directExecutor());
                }
            }
        }
        this.F = new l(2, new l.b() { // from class: androidx.camera.core.w0
            @Override // androidx.camera.core.ImageCapture.l.b
            public final ListenableFuture a(ImageCapture.k kVar) {
                ListenableFuture O;
                O = ImageCapture.this.O(kVar);
                return O;
            }
        });
        this.B.setOnImageAvailableListener(this.m, CameraXExecutors.mainThreadExecutor());
        z1 z1Var = this.B;
        DeferrableSurface deferrableSurface = this.E;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        ImmediateSurface immediateSurface = new ImmediateSurface(this.B.getSurface());
        this.E = immediateSurface;
        ListenableFuture<Void> terminationFuture = immediateSurface.getTerminationFuture();
        Objects.requireNonNull(z1Var);
        terminationFuture.addListener(new a0(z1Var), CameraXExecutors.mainThreadExecutor());
        createFrom.addNonRepeatingSurface(this.E);
        createFrom.addErrorListener(new SessionConfig.ErrorListener() { // from class: androidx.camera.core.z0
            @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
            public final void onError(SessionConfig sessionConfig, SessionConfig.SessionError sessionError) {
                ImageCapture.this.P(str, imageCaptureConfig, size, sessionConfig, sessionError);
            }
        });
        return createFrom;
    }

    public final CaptureBundle F(CaptureBundle captureBundle) {
        List<CaptureStage> captureStages = this.v.getCaptureStages();
        return (captureStages == null || captureStages.isEmpty()) ? captureBundle : w.a(captureStages);
    }

    @IntRange(from = 1, to = ViewExtensionsKt.ANIMATION_SLOW_MILLIS)
    public final int H() {
        int i2 = this.o;
        if (i2 != 0) {
            if (i2 == 1) {
                return 95;
            }
            throw new IllegalStateException("CaptureMode " + this.o + " is invalid");
        }
        return 100;
    }

    public final ListenableFuture<CameraCaptureResult> I() {
        if (!this.p && getFlashMode() != 0) {
            return Futures.immediateFuture(null);
        }
        return this.l.c(new f(this));
    }

    public boolean J(CameraCaptureResult cameraCaptureResult) {
        if (cameraCaptureResult == null) {
            return false;
        }
        return (cameraCaptureResult.getAfMode() == CameraCaptureMetaData.AfMode.ON_CONTINUOUS_AUTO || cameraCaptureResult.getAfMode() == CameraCaptureMetaData.AfMode.OFF || cameraCaptureResult.getAfMode() == CameraCaptureMetaData.AfMode.UNKNOWN || cameraCaptureResult.getAfState() == CameraCaptureMetaData.AfState.FOCUSED || cameraCaptureResult.getAfState() == CameraCaptureMetaData.AfState.LOCKED_FOCUSED || cameraCaptureResult.getAfState() == CameraCaptureMetaData.AfState.LOCKED_NOT_FOCUSED) && (cameraCaptureResult.getAeState() == CameraCaptureMetaData.AeState.CONVERGED || cameraCaptureResult.getAeState() == CameraCaptureMetaData.AeState.FLASH_REQUIRED || cameraCaptureResult.getAeState() == CameraCaptureMetaData.AeState.UNKNOWN) && (cameraCaptureResult.getAwbState() == CameraCaptureMetaData.AwbState.CONVERGED || cameraCaptureResult.getAwbState() == CameraCaptureMetaData.AwbState.UNKNOWN);
    }

    public boolean K(@NonNull m mVar) {
        int flashMode = getFlashMode();
        if (flashMode == 0) {
            return mVar.f644a.getAeState() == CameraCaptureMetaData.AeState.FLASH_REQUIRED;
        } else if (flashMode != 1) {
            if (flashMode == 2) {
                return false;
            }
            throw new AssertionError(getFlashMode());
        } else {
            return true;
        }
    }

    public ListenableFuture<Void> L(@NonNull k kVar) {
        CaptureBundle F;
        Logger.d("ImageCapture", "issueTakePicture");
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        String str = null;
        if (this.C != null) {
            if (this.y) {
                F = F(w.c());
                if (F.getCaptureStages().size() > 1) {
                    return Futures.immediateFailedFuture(new IllegalArgumentException("Software JPEG not supported with CaptureBundle size > 1."));
                }
            } else {
                F = F(null);
            }
            if (F == null) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture cannot set empty CaptureBundle."));
            }
            if (F.getCaptureStages().size() > this.w) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture has CaptureStages > Max CaptureStage size"));
            }
            this.C.g(F);
            str = this.C.d();
        } else {
            F = F(w.c());
            if (F.getCaptureStages().size() > 1) {
                return Futures.immediateFailedFuture(new IllegalArgumentException("ImageCapture have no CaptureProcess set with CaptureBundle size > 1."));
            }
        }
        for (final CaptureStage captureStage : F.getCaptureStages()) {
            final CaptureConfig.Builder builder = new CaptureConfig.Builder();
            builder.setTemplateType(this.u.getTemplateType());
            builder.addImplementationOptions(this.u.getImplementationOptions());
            builder.addAllCameraCaptureCallbacks(this.A.getSingleCameraCaptureCallbacks());
            builder.addSurface(this.E);
            if (new ExifRotationAvailability().isRotationOptionSupported()) {
                builder.addImplementationOption(CaptureConfig.OPTION_ROTATION, Integer.valueOf(kVar.f641a));
            }
            builder.addImplementationOption(CaptureConfig.OPTION_JPEG_QUALITY, Integer.valueOf(kVar.b));
            builder.addImplementationOptions(captureStage.getCaptureConfig().getImplementationOptions());
            if (str != null) {
                builder.addTag(str, Integer.valueOf(captureStage.getId()));
            }
            builder.addCameraCaptureCallback(this.D);
            arrayList.add(CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.l0
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                    Object Q;
                    Q = ImageCapture.this.Q(builder, arrayList2, captureStage, completer);
                    return Q;
                }
            }));
        }
        getCameraControl().submitCaptureRequests(arrayList2);
        return Futures.transform(Futures.allAsList(arrayList), new Function() { // from class: androidx.camera.core.v0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void R;
                R = ImageCapture.R((List) obj);
                return R;
            }
        }, CameraXExecutors.directExecutor());
    }

    /* JADX WARN: Type inference failed for: r8v12, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    @Override // androidx.camera.core.UseCase
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> b(@NonNull CameraInfoInternal cameraInfoInternal, @NonNull UseCaseConfig.Builder<?, ?, ?> builder) {
        if (cameraInfoInternal.getCameraQuirks().contains(SoftwareJpegEncodingPreferredQuirk.class)) {
            MutableConfig mutableConfig = builder.getMutableConfig();
            Config.Option<Boolean> option = ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER;
            Boolean bool = Boolean.TRUE;
            if (!((Boolean) mutableConfig.retrieveOption(option, bool)).booleanValue()) {
                Logger.w("ImageCapture", "Device quirk suggests software JPEG encoder, but it has been explicitly disabled.");
            } else {
                Logger.i("ImageCapture", "Requesting software JPEG due to device quirk.");
                builder.getMutableConfig().insertOption(option, bool);
            }
        }
        boolean E = E(builder.getMutableConfig());
        Integer num = (Integer) builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (num != null) {
            Preconditions.checkArgument(builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) == null, "Cannot set buffer format with CaptureProcessor defined.");
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, Integer.valueOf(E ? 35 : num.intValue()));
        } else if (builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_CAPTURE_PROCESSOR, null) == null && !E) {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
        } else {
            builder.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
        }
        Preconditions.checkArgument(((Integer) builder.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_MAX_CAPTURE_STAGES, 2)).intValue() >= 1, "Maximum outstanding image count must be at least 1");
        return builder.getUseCaseConfig();
    }

    public int getCaptureMode() {
        return this.o;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    @Override // androidx.camera.core.UseCase
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> getDefaultConfig(boolean z, @NonNull UseCaseConfigFactory useCaseConfigFactory) {
        Config config = useCaseConfigFactory.getConfig(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
        if (z) {
            config = Config.mergeConfigs(config, DEFAULT_CONFIG.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    public int getFlashMode() {
        int i2;
        synchronized (this.q) {
            i2 = this.r;
            if (i2 == -1) {
                i2 = ((ImageCaptureConfig) getCurrentConfig()).getFlashMode(2);
            }
        }
        return i2;
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    @Override // androidx.camera.core.UseCase
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(@NonNull Config config) {
        return Builder.fromConfig(config);
    }

    public final void h0() {
        synchronized (this.q) {
            if (this.q.get() != null) {
                return;
            }
            this.q.set(Integer.valueOf(getFlashMode()));
        }
    }

    @NonNull
    public final ListenableFuture<Void> i0(@NonNull final m mVar) {
        CameraInternal camera = getCamera();
        if (camera != null && camera.getCameraInfo().getTorchState().getValue().intValue() == 1) {
            return Futures.immediateFuture(null);
        }
        Logger.d("ImageCapture", "openTorch");
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.k0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object U;
                U = ImageCapture.this.U(mVar, completer);
                return U;
            }
        });
    }

    public void j0(m mVar) {
        C(mVar);
        z(mVar);
        r0();
    }

    public final ListenableFuture<Void> k0(final m mVar) {
        h0();
        return FutureChain.from(I()).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.b1
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture V;
                V = ImageCapture.this.V(mVar, (CameraCaptureResult) obj);
                return V;
            }
        }, this.t).transformAsync(new AsyncFunction() { // from class: androidx.camera.core.c1
            @Override // androidx.camera.core.impl.utils.futures.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                ListenableFuture W;
                W = ImageCapture.this.W(mVar, (Void) obj);
                return W;
            }
        }, this.t).transform(new Function() { // from class: androidx.camera.core.t0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void X;
                X = ImageCapture.X((Boolean) obj);
                return X;
            }
        }, this.t);
    }

    @UiThread
    public final void l0(@NonNull Executor executor, @NonNull final OnImageCapturedCallback onImageCapturedCallback) {
        CameraInternal camera = getCamera();
        if (camera == null) {
            executor.execute(new Runnable() { // from class: androidx.camera.core.m0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageCapture.this.Y(onImageCapturedCallback);
                }
            });
        } else {
            this.F.d(new k(getRelativeRotation(camera), H(), this.s, getViewPortCropRect(), executor, onImageCapturedCallback));
        }
    }

    /* renamed from: m0 */
    public final ListenableFuture<ImageProxy> O(@NonNull final k kVar) {
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.j0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object b0;
                b0 = ImageCapture.this.b0(kVar, completer);
                return b0;
            }
        });
    }

    public ListenableFuture<Void> n0(m mVar) {
        Logger.d("ImageCapture", "triggerAePrecapture");
        mVar.d = true;
        return Futures.transform(getCameraControl().triggerAePrecapture(), new Function() { // from class: androidx.camera.core.i0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                Void f0;
                f0 = ImageCapture.f0((CameraCaptureResult) obj);
                return f0;
            }
        }, CameraXExecutors.directExecutor());
    }

    public final void o0(m mVar) {
        Logger.d("ImageCapture", "triggerAf");
        mVar.c = true;
        getCameraControl().triggerAf().addListener(new Runnable() { // from class: androidx.camera.core.u0
            @Override // java.lang.Runnable
            public final void run() {
                ImageCapture.g0();
            }
        }, CameraXExecutors.directExecutor());
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onAttached() {
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) getCurrentConfig();
        this.u = CaptureConfig.Builder.createFrom(imageCaptureConfig).build();
        this.x = imageCaptureConfig.getCaptureProcessor(null);
        this.w = imageCaptureConfig.getMaxCaptureStages(2);
        this.v = imageCaptureConfig.getCaptureBundle(w.c());
        this.y = imageCaptureConfig.isSoftwareJpegEncoderRequested();
        this.t = Executors.newFixedThreadPool(1, new e(this));
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onCameraControlReady() {
        q0();
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onDetached() {
        y();
        B();
        this.y = false;
        this.t.shutdown();
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onStateDetached() {
        y();
    }

    @Override // androidx.camera.core.UseCase
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Size onSuggestedResolutionUpdated(@NonNull Size size) {
        SessionConfig.Builder D = D(getCameraId(), (ImageCaptureConfig) getCurrentConfig(), size);
        this.A = D;
        updateSessionConfig(D.build());
        notifyActive();
        return size;
    }

    public void p0(m mVar) {
        if (this.p && mVar.f644a.getAfMode() == CameraCaptureMetaData.AfMode.ON_MANUAL_AUTO && mVar.f644a.getAfState() == CameraCaptureMetaData.AfState.INACTIVE) {
            o0(mVar);
        }
    }

    public final void q0() {
        synchronized (this.q) {
            if (this.q.get() != null) {
                return;
            }
            getCameraControl().setFlashMode(getFlashMode());
        }
    }

    public final void r0() {
        synchronized (this.q) {
            Integer andSet = this.q.getAndSet(null);
            if (andSet == null) {
                return;
            }
            if (andSet.intValue() != getFlashMode()) {
                q0();
            }
        }
    }

    public void setCropAspectRatio(@NonNull Rational rational) {
        this.s = rational;
    }

    public void setFlashMode(int i2) {
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("Invalid flash mode: " + i2);
        }
        synchronized (this.q) {
            this.r = i2;
            q0();
        }
    }

    public void setTargetRotation(int i2) {
        int targetRotation = getTargetRotation();
        if (!setTargetRotationInternal(i2) || this.s == null) {
            return;
        }
        this.s = ImageUtil.getRotatedAspectRatio(Math.abs(CameraOrientationUtil.surfaceRotationToDegrees(i2) - CameraOrientationUtil.surfaceRotationToDegrees(targetRotation)), this.s);
    }

    /* renamed from: takePicture */
    public void Z(@NonNull final Executor executor, @NonNull final OnImageCapturedCallback onImageCapturedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.o0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageCapture.this.Z(executor, onImageCapturedCallback);
                }
            });
        } else {
            l0(executor, onImageCapturedCallback);
        }
    }

    @NonNull
    public String toString() {
        return "ImageCapture:" + getName();
    }

    public final void y() {
        this.F.b(new androidx.camera.core.h("Camera is closed."));
    }

    public void z(m mVar) {
        if (mVar.c || mVar.d) {
            getCameraControl().cancelAfAeTrigger(mVar.c, mVar.d);
            mVar.c = false;
            mVar.d = false;
        }
    }

    /* renamed from: takePicture */
    public void a0(@NonNull final OutputFileOptions outputFileOptions, @NonNull final Executor executor, @NonNull final OnImageSavedCallback onImageSavedCallback) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.n0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageCapture.this.a0(outputFileOptions, executor, onImageSavedCallback);
                }
            });
            return;
        }
        l0(CameraXExecutors.mainThreadExecutor(), new c(outputFileOptions, executor, new b(this, onImageSavedCallback), onImageSavedCallback));
    }
}
