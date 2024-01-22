package androidx.camera.core;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.location.Location;
import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import androidx.camera.core.UseCase;
import androidx.camera.core.VideoCapture;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConfigProvider;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImmediateSurface;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.VideoCaptureConfig;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.ThreadConfig;
import androidx.camera.core.internal.UseCaseEventConfig;
import androidx.camera.core.internal.utils.VideoUtil;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class VideoCapture extends UseCase {
    public static final int ERROR_ENCODER = 1;
    public static final int ERROR_FILE_IO = 4;
    public static final int ERROR_INVALID_CAMERA = 5;
    public static final int ERROR_MUXER = 2;
    public static final int ERROR_RECORDING_IN_PROGRESS = 3;
    public static final int ERROR_UNKNOWN = 0;
    @GuardedBy("mMuxerLock")
    public MediaMuxer A;
    public boolean B;
    public int C;
    public int D;
    public Surface E;
    @NonNull
    public AudioRecord F;
    public int G;
    public boolean H;
    public int I;
    public int J;
    public int K;
    public DeferrableSurface L;
    public Uri M;
    public ParcelFileDescriptor N;
    public final MediaCodec.BufferInfo l;
    public final Object m;
    public final AtomicBoolean n;
    public final AtomicBoolean o;
    public final AtomicBoolean p;
    public final MediaCodec.BufferInfo q;
    public final AtomicBoolean r;
    public final AtomicBoolean s;
    public HandlerThread t;
    public Handler u;
    public HandlerThread v;
    public Handler w;
    @NonNull
    public MediaCodec x;
    @NonNull
    public MediaCodec y;
    @Nullable
    public ListenableFuture<Void> z;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final Defaults DEFAULT_CONFIG = new Defaults();
    public static final int[] O = {8, 6, 5, 4};
    public static final short[] P = {2, 3, 4};

    /* loaded from: classes.dex */
    public static final class Builder implements UseCaseConfig.Builder<VideoCapture, VideoCaptureConfig, Builder>, ImageOutputConfig.Builder<Builder>, ThreadConfig.Builder<Builder> {

        /* renamed from: a  reason: collision with root package name */
        public final MutableOptionsBundle f660a;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Builder a(@NonNull Config config) {
            return new Builder(MutableOptionsBundle.from(config));
        }

        @NonNull
        public static Builder fromConfig(@NonNull VideoCaptureConfig videoCaptureConfig) {
            return new Builder(MutableOptionsBundle.from((Config) videoCaptureConfig));
        }

        @Override // androidx.camera.core.ExtendableBuilder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public MutableConfig getMutableConfig() {
            return this.f660a;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setAudioBitRate(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_AUDIO_BIT_RATE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setAudioChannelCount(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_AUDIO_CHANNEL_COUNT, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setAudioMinBufferSize(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_AUDIO_MIN_BUFFER_SIZE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setAudioRecordSource(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_AUDIO_RECORD_SOURCE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setAudioSampleRate(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_AUDIO_SAMPLE_RATE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setBitRate(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_BIT_RATE, Integer.valueOf(i));
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setIFrameInterval(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_INTRA_FRAME_INTERVAL, Integer.valueOf(i));
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
            return setTargetClass((Class<VideoCapture>) cls);
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setVideoFrameRate(int i) {
            getMutableConfig().insertOption(VideoCaptureConfig.OPTION_VIDEO_FRAME_RATE, Integer.valueOf(i));
            return this;
        }

        public Builder(@NonNull MutableOptionsBundle mutableOptionsBundle) {
            this.f660a = mutableOptionsBundle;
            Class cls = (Class) mutableOptionsBundle.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (cls != null && !cls.equals(VideoCapture.class)) {
                throw new IllegalArgumentException("Invalid target class configuration for " + this + ": " + cls);
            }
            setTargetClass(VideoCapture.class);
        }

        @Override // androidx.camera.core.ExtendableBuilder
        @NonNull
        public VideoCapture build() {
            if (getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, null) != null && getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, null) != null) {
                throw new IllegalArgumentException("Cannot use both setTargetResolution and setTargetAspectRatio on the same config.");
            }
            return new VideoCapture(getUseCaseConfig());
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public VideoCaptureConfig getUseCaseConfig() {
            return new VideoCaptureConfig(OptionsBundle.from(this.f660a));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.internal.ThreadConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setBackgroundExecutor(@NonNull Executor executor) {
            getMutableConfig().insertOption(ThreadConfig.OPTION_BACKGROUND_EXECUTOR, executor);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY})
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
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetAspectRatio(int i) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(i));
            return this;
        }

        @Override // androidx.camera.core.internal.TargetConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetClass(@NonNull Class<VideoCapture> cls) {
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
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Builder setTargetResolution(@NonNull Size size) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, size);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
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

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static final class Defaults implements ConfigProvider<VideoCaptureConfig> {

        /* renamed from: a  reason: collision with root package name */
        public static final Size f661a;
        public static final VideoCaptureConfig b;

        static {
            Size size = new Size(1920, 1080);
            f661a = size;
            b = new Builder().setVideoFrameRate(30).setBitRate(8388608).setIFrameInterval(1).setAudioBitRate(64000).setAudioSampleRate(8000).setAudioChannelCount(1).setAudioRecordSource(1).setAudioMinBufferSize(1024).setMaxResolution(size).setSurfaceOccupancyPriority(3).setTargetAspectRatio(1).getUseCaseConfig();
        }

        @Override // androidx.camera.core.impl.ConfigProvider
        @NonNull
        public VideoCaptureConfig getConfig() {
            return b;
        }
    }

    /* loaded from: classes.dex */
    public static final class Metadata {
        @Nullable
        public Location location;
    }

    /* loaded from: classes.dex */
    public interface OnVideoSavedCallback {
        void onError(int i, @NonNull String str, @Nullable Throwable th);

        void onVideoSaved(@NonNull OutputFileResults outputFileResults);
    }

    /* loaded from: classes.dex */
    public static final class OutputFileOptions {
        public static final Metadata g = new Metadata();
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public final File f662a;
        @Nullable
        public final FileDescriptor b;
        @Nullable
        public final ContentResolver c;
        @Nullable
        public final Uri d;
        @Nullable
        public final ContentValues e;
        @Nullable
        public final Metadata f;

        public OutputFileOptions(@Nullable File file, @Nullable FileDescriptor fileDescriptor, @Nullable ContentResolver contentResolver, @Nullable Uri uri, @Nullable ContentValues contentValues, @Nullable Metadata metadata) {
            this.f662a = file;
            this.b = fileDescriptor;
            this.c = contentResolver;
            this.d = uri;
            this.e = contentValues;
            this.f = metadata == null ? g : metadata;
        }

        @Nullable
        public ContentResolver a() {
            return this.c;
        }

        @Nullable
        public ContentValues b() {
            return this.e;
        }

        @Nullable
        public File c() {
            return this.f662a;
        }

        @Nullable
        public FileDescriptor d() {
            return this.b;
        }

        @Nullable
        public Metadata e() {
            return this.f;
        }

        @Nullable
        public Uri f() {
            return this.d;
        }

        public boolean g() {
            return c() != null;
        }

        public boolean h() {
            return d() != null;
        }

        public boolean i() {
            return (f() == null || a() == null || b() == null) ? false : true;
        }

        /* loaded from: classes.dex */
        public static final class Builder {
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            public File f663a;
            @Nullable
            public FileDescriptor b;
            @Nullable
            public ContentResolver c;
            @Nullable
            public Uri d;
            @Nullable
            public ContentValues e;
            @Nullable
            public Metadata f;

            public Builder(@NonNull File file) {
                this.f663a = file;
            }

            @NonNull
            public OutputFileOptions build() {
                return new OutputFileOptions(this.f663a, this.b, this.c, this.d, this.e, this.f);
            }

            @NonNull
            public Builder setMetadata(@NonNull Metadata metadata) {
                this.f = metadata;
                return this;
            }

            public Builder(@NonNull FileDescriptor fileDescriptor) {
                Preconditions.checkArgument(Build.VERSION.SDK_INT >= 26, "Using a FileDescriptor to record a video is only supported for Android 8.0 or above.");
                this.b = fileDescriptor;
            }

            public Builder(@NonNull ContentResolver contentResolver, @NonNull Uri uri, @NonNull ContentValues contentValues) {
                this.c = contentResolver;
                this.d = uri;
                this.e = contentValues;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class OutputFileResults {
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        public Uri f664a;

        public OutputFileResults(@Nullable Uri uri) {
            this.f664a = uri;
        }

        @Nullable
        public Uri getSavedUri() {
            return this.f664a;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface VideoCaptureError {
    }

    /* loaded from: classes.dex */
    public class a implements SessionConfig.ErrorListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ String f665a;
        public final /* synthetic */ Size b;

        public a(String str, Size size) {
            this.f665a = str;
            this.b = size;
        }

        @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
        public void onError(@NonNull SessionConfig sessionConfig, @NonNull SessionConfig.SessionError sessionError) {
            if (VideoCapture.this.isCurrentCamera(this.f665a)) {
                VideoCapture.this.D(this.f665a, this.b);
                VideoCapture.this.notifyReset();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class b implements OnVideoSavedCallback {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public Executor f666a;
        @NonNull
        public OnVideoSavedCallback b;

        public b(@NonNull Executor executor, @NonNull OnVideoSavedCallback onVideoSavedCallback) {
            this.f666a = executor;
            this.b = onVideoSavedCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(int i, String str, Throwable th) {
            this.b.onError(i, str, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(OutputFileResults outputFileResults) {
            this.b.onVideoSaved(outputFileResults);
        }

        @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
        public void onError(final int i, @NonNull final String str, @Nullable final Throwable th) {
            try {
                this.f666a.execute(new Runnable() { // from class: androidx.camera.core.u2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoCapture.b.this.c(i, str, th);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.e("VideoCapture", "Unable to post to the supplied executor.");
            }
        }

        @Override // androidx.camera.core.VideoCapture.OnVideoSavedCallback
        public void onVideoSaved(@NonNull final OutputFileResults outputFileResults) {
            try {
                this.f666a.execute(new Runnable() { // from class: androidx.camera.core.v2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoCapture.b.this.d(outputFileResults);
                    }
                });
            } catch (RejectedExecutionException unused) {
                Logger.e("VideoCapture", "Unable to post to the supplied executor.");
            }
        }
    }

    public VideoCapture(@NonNull VideoCaptureConfig videoCaptureConfig) {
        super(videoCaptureConfig);
        this.l = new MediaCodec.BufferInfo();
        this.m = new Object();
        this.n = new AtomicBoolean(true);
        this.o = new AtomicBoolean(true);
        this.p = new AtomicBoolean(true);
        this.q = new MediaCodec.BufferInfo();
        this.r = new AtomicBoolean(false);
        this.s = new AtomicBoolean(false);
        this.z = null;
        this.B = false;
        this.H = false;
    }

    public static MediaFormat o(VideoCaptureConfig videoCaptureConfig, Size size) {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", size.getWidth(), size.getHeight());
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", videoCaptureConfig.getBitRate());
        createVideoFormat.setInteger("frame-rate", videoCaptureConfig.getVideoFrameRate());
        createVideoFormat.setInteger("i-frame-interval", videoCaptureConfig.getIFrameInterval());
        return createVideoFormat;
    }

    public static /* synthetic */ void t(boolean z, MediaCodec mediaCodec) {
        if (!z || mediaCodec == null) {
            return;
        }
        mediaCodec.release();
    }

    public static /* synthetic */ Object v(AtomicReference atomicReference, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return "startRecording";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w() {
        this.z = null;
        if (getCamera() != null) {
            D(getCameraId(), getAttachedSurfaceResolution());
            notifyReset();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(OnVideoSavedCallback onVideoSavedCallback, String str, Size size, CallbackToFutureAdapter.Completer completer) {
        if (!E(onVideoSavedCallback, str, size)) {
            onVideoSavedCallback.onVideoSaved(new OutputFileResults(this.M));
            this.M = null;
        }
        completer.set(null);
    }

    @UiThread
    public final void A(final boolean z) {
        DeferrableSurface deferrableSurface = this.L;
        if (deferrableSurface == null) {
            return;
        }
        final MediaCodec mediaCodec = this.x;
        deferrableSurface.close();
        this.L.getTerminationFuture().addListener(new Runnable() { // from class: androidx.camera.core.t2
            @Override // java.lang.Runnable
            public final void run() {
                VideoCapture.t(z, mediaCodec);
            }
        }, CameraXExecutors.mainThreadExecutor());
        if (z) {
            this.x = null;
        }
        this.E = null;
        this.L = null;
    }

    /* renamed from: B */
    public final void s() {
        this.t.quitSafely();
        this.v.quitSafely();
        MediaCodec mediaCodec = this.y;
        if (mediaCodec != null) {
            mediaCodec.release();
            this.y = null;
        }
        AudioRecord audioRecord = this.F;
        if (audioRecord != null) {
            audioRecord.release();
            this.F = null;
        }
        if (this.E != null) {
            A(true);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        r7.I = r4.audioChannels;
        r7.J = r4.audioSampleRate;
        r7.K = r4.audioBitRate;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
        r0 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void C(android.util.Size r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            int[] r1 = androidx.camera.core.VideoCapture.O     // Catch: java.lang.NumberFormatException -> L3d
            int r2 = r1.length     // Catch: java.lang.NumberFormatException -> L3d
            r3 = r0
        L5:
            if (r3 >= r2) goto L44
            r4 = r1[r3]     // Catch: java.lang.NumberFormatException -> L3d
            int r5 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L3d
            boolean r5 = android.media.CamcorderProfile.hasProfile(r5, r4)     // Catch: java.lang.NumberFormatException -> L3d
            if (r5 == 0) goto L3a
            int r5 = java.lang.Integer.parseInt(r9)     // Catch: java.lang.NumberFormatException -> L3d
            android.media.CamcorderProfile r4 = android.media.CamcorderProfile.get(r5, r4)     // Catch: java.lang.NumberFormatException -> L3d
            int r5 = r8.getWidth()     // Catch: java.lang.NumberFormatException -> L3d
            int r6 = r4.videoFrameWidth     // Catch: java.lang.NumberFormatException -> L3d
            if (r5 != r6) goto L3a
            int r5 = r8.getHeight()     // Catch: java.lang.NumberFormatException -> L3d
            int r6 = r4.videoFrameHeight     // Catch: java.lang.NumberFormatException -> L3d
            if (r5 != r6) goto L3a
            int r8 = r4.audioChannels     // Catch: java.lang.NumberFormatException -> L3d
            r7.I = r8     // Catch: java.lang.NumberFormatException -> L3d
            int r8 = r4.audioSampleRate     // Catch: java.lang.NumberFormatException -> L3d
            r7.J = r8     // Catch: java.lang.NumberFormatException -> L3d
            int r8 = r4.audioBitRate     // Catch: java.lang.NumberFormatException -> L3d
            r7.K = r8     // Catch: java.lang.NumberFormatException -> L3d
            r8 = 1
            r0 = r8
            goto L44
        L3a:
            int r3 = r3 + 1
            goto L5
        L3d:
            java.lang.String r8 = "VideoCapture"
            java.lang.String r9 = "The camera Id is not an integer because the camera may be a removable device. Use the default values for the audio related settings."
            androidx.camera.core.Logger.i(r8, r9)
        L44:
            if (r0 != 0) goto L5e
            androidx.camera.core.impl.UseCaseConfig r8 = r7.getCurrentConfig()
            androidx.camera.core.impl.VideoCaptureConfig r8 = (androidx.camera.core.impl.VideoCaptureConfig) r8
            int r9 = r8.getAudioChannelCount()
            r7.I = r9
            int r9 = r8.getAudioSampleRate()
            r7.J = r9
            int r8 = r8.getAudioBitRate()
            r7.K = r8
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.VideoCapture.C(android.util.Size, java.lang.String):void");
    }

    @UiThread
    public void D(@NonNull String str, @NonNull Size size) {
        VideoCaptureConfig videoCaptureConfig = (VideoCaptureConfig) getCurrentConfig();
        this.x.reset();
        this.x.configure(o(videoCaptureConfig, size), (Surface) null, (MediaCrypto) null, 1);
        if (this.E != null) {
            A(false);
        }
        final Surface createInputSurface = this.x.createInputSurface();
        this.E = createInputSurface;
        SessionConfig.Builder createFrom = SessionConfig.Builder.createFrom(videoCaptureConfig);
        DeferrableSurface deferrableSurface = this.L;
        if (deferrableSurface != null) {
            deferrableSurface.close();
        }
        ImmediateSurface immediateSurface = new ImmediateSurface(this.E);
        this.L = immediateSurface;
        ListenableFuture<Void> terminationFuture = immediateSurface.getTerminationFuture();
        Objects.requireNonNull(createInputSurface);
        terminationFuture.addListener(new Runnable() { // from class: androidx.camera.core.m2
            @Override // java.lang.Runnable
            public final void run() {
                createInputSurface.release();
            }
        }, CameraXExecutors.mainThreadExecutor());
        createFrom.addSurface(this.L);
        createFrom.addErrorListener(new a(str, size));
        updateSessionConfig(createFrom.build());
        C(size, str);
        this.y.reset();
        this.y.configure(n(), (Surface) null, (MediaCrypto) null, 1);
        AudioRecord audioRecord = this.F;
        if (audioRecord != null) {
            audioRecord.release();
        }
        AudioRecord m = m(videoCaptureConfig);
        this.F = m;
        if (m == null) {
            Logger.e("VideoCapture", "AudioRecord object cannot initialized correctly!");
        }
        this.C = -1;
        this.D = -1;
        this.H = false;
    }

    public boolean E(@NonNull OnVideoSavedCallback onVideoSavedCallback, @NonNull String str, @NonNull Size size) {
        boolean z = false;
        boolean z2 = false;
        while (!z && !z2) {
            if (this.n.get()) {
                this.x.signalEndOfInputStream();
                this.n.set(false);
            }
            int dequeueOutputBuffer = this.x.dequeueOutputBuffer(this.l, 10000L);
            if (dequeueOutputBuffer == -2) {
                if (this.B) {
                    onVideoSavedCallback.onError(1, "Unexpected change in video encoding format.", null);
                    z2 = true;
                }
                synchronized (this.m) {
                    int addTrack = this.A.addTrack(this.x.getOutputFormat());
                    this.C = addTrack;
                    if (this.D >= 0 && addTrack >= 0) {
                        this.B = true;
                        Logger.i("VideoCapture", "media mMuxer start");
                        this.A.start();
                    }
                }
            } else if (dequeueOutputBuffer != -1) {
                z = G(dequeueOutputBuffer);
            }
        }
        try {
            Logger.i("VideoCapture", "videoEncoder stop");
            this.x.stop();
        } catch (IllegalStateException e) {
            onVideoSavedCallback.onError(1, "Video encoder stop failed!", e);
            z2 = true;
        }
        try {
            synchronized (this.m) {
                MediaMuxer mediaMuxer = this.A;
                if (mediaMuxer != null) {
                    if (this.B) {
                        mediaMuxer.stop();
                    }
                    this.A.release();
                    this.A = null;
                }
            }
        } catch (IllegalStateException e2) {
            onVideoSavedCallback.onError(2, "Muxer stop failed!", e2);
            z2 = true;
        }
        ParcelFileDescriptor parcelFileDescriptor = this.N;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
                this.N = null;
            } catch (IOException e3) {
                onVideoSavedCallback.onError(2, "File descriptor close failed!", e3);
                z2 = true;
            }
        }
        this.B = false;
        this.p.set(true);
        Logger.i("VideoCapture", "Video encode thread end.");
        return z2;
    }

    public final boolean F(int i) {
        ByteBuffer q = q(this.y, i);
        q.position(this.q.offset);
        if (this.D >= 0 && this.C >= 0) {
            MediaCodec.BufferInfo bufferInfo = this.q;
            if (bufferInfo.size > 0 && bufferInfo.presentationTimeUs > 0) {
                try {
                    synchronized (this.m) {
                        if (!this.s.get()) {
                            Logger.i("VideoCapture", "First audio sample written.");
                            this.s.set(true);
                        }
                        this.A.writeSampleData(this.D, q, this.q);
                    }
                } catch (Exception e) {
                    Logger.e("VideoCapture", "audio error:size=" + this.q.size + "/offset=" + this.q.offset + "/timeUs=" + this.q.presentationTimeUs);
                    e.printStackTrace();
                }
            }
        }
        this.y.releaseOutputBuffer(i, false);
        return (this.q.flags & 4) != 0;
    }

    public final boolean G(int i) {
        if (i < 0) {
            Logger.e("VideoCapture", "Output buffer should not have negative index: " + i);
            return false;
        }
        ByteBuffer outputBuffer = this.x.getOutputBuffer(i);
        if (outputBuffer == null) {
            Logger.d("VideoCapture", "OutputBuffer was null.");
            return false;
        }
        if (this.D >= 0 && this.C >= 0) {
            MediaCodec.BufferInfo bufferInfo = this.l;
            if (bufferInfo.size > 0) {
                outputBuffer.position(bufferInfo.offset);
                MediaCodec.BufferInfo bufferInfo2 = this.l;
                outputBuffer.limit(bufferInfo2.offset + bufferInfo2.size);
                this.l.presentationTimeUs = System.nanoTime() / 1000;
                synchronized (this.m) {
                    if (!this.r.get()) {
                        Logger.i("VideoCapture", "First video sample written.");
                        this.r.set(true);
                    }
                    this.A.writeSampleData(this.C, outputBuffer, this.l);
                }
            }
        }
        this.x.releaseOutputBuffer(i, false);
        return (this.l.flags & 4) != 0;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.camera.core.impl.UseCaseConfig, androidx.camera.core.impl.UseCaseConfig<?>] */
    @Override // androidx.camera.core.UseCase
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig<?> getDefaultConfig(boolean z, @NonNull UseCaseConfigFactory useCaseConfigFactory) {
        Config config = useCaseConfigFactory.getConfig(UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE);
        if (z) {
            config = Config.mergeConfigs(config, DEFAULT_CONFIG.getConfig());
        }
        if (config == null) {
            return null;
        }
        return getUseCaseConfigBuilder(config).getUseCaseConfig();
    }

    @Override // androidx.camera.core.UseCase
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(@NonNull Config config) {
        return Builder.a(config);
    }

    /* renamed from: l */
    public boolean x(OnVideoSavedCallback onVideoSavedCallback) {
        boolean z = false;
        while (!z && this.H) {
            if (this.o.get()) {
                this.o.set(false);
                this.H = false;
            }
            MediaCodec mediaCodec = this.y;
            if (mediaCodec != null && this.F != null) {
                int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(-1L);
                if (dequeueInputBuffer >= 0) {
                    ByteBuffer p = p(this.y, dequeueInputBuffer);
                    p.clear();
                    int read = this.F.read(p, this.G);
                    if (read > 0) {
                        this.y.queueInputBuffer(dequeueInputBuffer, 0, read, System.nanoTime() / 1000, this.H ? 0 : 4);
                    }
                }
                do {
                    int dequeueOutputBuffer = this.y.dequeueOutputBuffer(this.q, 0L);
                    if (dequeueOutputBuffer == -2) {
                        synchronized (this.m) {
                            int addTrack = this.A.addTrack(this.y.getOutputFormat());
                            this.D = addTrack;
                            if (addTrack >= 0 && this.C >= 0) {
                                this.B = true;
                                this.A.start();
                            }
                        }
                    } else if (dequeueOutputBuffer != -1) {
                        z = F(dequeueOutputBuffer);
                    }
                    if (dequeueOutputBuffer >= 0) {
                    }
                } while (!z);
            }
        }
        try {
            Logger.i("VideoCapture", "audioRecorder stop");
            this.F.stop();
        } catch (IllegalStateException e) {
            onVideoSavedCallback.onError(1, "Audio recorder stop failed!", e);
        }
        try {
            this.y.stop();
        } catch (IllegalStateException e2) {
            onVideoSavedCallback.onError(1, "Audio encoder stop failed!", e2);
        }
        Logger.i("VideoCapture", "Audio encode thread end");
        this.n.set(true);
        return false;
    }

    public final AudioRecord m(VideoCaptureConfig videoCaptureConfig) {
        short[] sArr;
        int i;
        AudioRecord audioRecord;
        for (short s : P) {
            int i2 = this.I == 1 ? 16 : 12;
            int audioRecordSource = videoCaptureConfig.getAudioRecordSource();
            try {
                int minBufferSize = AudioRecord.getMinBufferSize(this.J, i2, s);
                if (minBufferSize <= 0) {
                    minBufferSize = videoCaptureConfig.getAudioMinBufferSize();
                }
                i = minBufferSize;
                audioRecord = new AudioRecord(audioRecordSource, this.J, i2, s, i * 2);
            } catch (Exception e) {
                Logger.e("VideoCapture", "Exception, keep trying.", e);
            }
            if (audioRecord.getState() == 1) {
                this.G = i;
                Logger.i("VideoCapture", "source: " + audioRecordSource + " audioSampleRate: " + this.J + " channelConfig: " + i2 + " audioFormat: " + ((int) s) + " bufferSize: " + i);
                return audioRecord;
            }
            continue;
        }
        return null;
    }

    public final MediaFormat n() {
        MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", this.J, this.I);
        createAudioFormat.setInteger("aac-profile", 2);
        createAudioFormat.setInteger("bitrate", this.K);
        return createAudioFormat;
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onAttached() {
        this.t = new HandlerThread("CameraX-video encoding thread");
        this.v = new HandlerThread("CameraX-audio encoding thread");
        this.t.start();
        this.u = new Handler(this.t.getLooper());
        this.v.start();
        this.w = new Handler(this.v.getLooper());
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onDetached() {
        z();
        ListenableFuture<Void> listenableFuture = this.z;
        if (listenableFuture != null) {
            listenableFuture.addListener(new Runnable() { // from class: androidx.camera.core.p2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoCapture.this.s();
                }
            }, CameraXExecutors.mainThreadExecutor());
        } else {
            s();
        }
    }

    @Override // androidx.camera.core.UseCase
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @UiThread
    public void onStateDetached() {
        z();
    }

    @Override // androidx.camera.core.UseCase
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Size onSuggestedResolutionUpdated(@NonNull Size size) {
        if (this.E != null) {
            this.x.stop();
            this.x.release();
            this.y.stop();
            this.y.release();
            A(false);
        }
        try {
            this.x = MediaCodec.createEncoderByType("video/avc");
            this.y = MediaCodec.createEncoderByType("audio/mp4a-latm");
            D(getCameraId(), size);
            return size;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to create MediaCodec due to: " + e.getCause());
        }
    }

    public final ByteBuffer p(MediaCodec mediaCodec, int i) {
        return mediaCodec.getInputBuffer(i);
    }

    public final ByteBuffer q(MediaCodec mediaCodec, int i) {
        return mediaCodec.getOutputBuffer(i);
    }

    @NonNull
    @SuppressLint({"UnsafeNewApiCall"})
    public final MediaMuxer r(@NonNull OutputFileOptions outputFileOptions) throws IOException {
        ContentValues contentValues;
        MediaMuxer mediaMuxer;
        if (outputFileOptions.g()) {
            File c = outputFileOptions.c();
            this.M = Uri.fromFile(outputFileOptions.c());
            return new MediaMuxer(c.getAbsolutePath(), 0);
        }
        if (outputFileOptions.h()) {
            if (Build.VERSION.SDK_INT >= 26) {
                mediaMuxer = new MediaMuxer(outputFileOptions.d(), 0);
            } else {
                throw new IllegalArgumentException("Using a FileDescriptor to record a video is only supported for Android 8.0 or above.");
            }
        } else if (outputFileOptions.i()) {
            if (outputFileOptions.b() != null) {
                contentValues = new ContentValues(outputFileOptions.b());
            } else {
                contentValues = new ContentValues();
            }
            Uri insert = outputFileOptions.a().insert(outputFileOptions.f(), contentValues);
            this.M = insert;
            if (insert != null) {
                try {
                    if (Build.VERSION.SDK_INT < 26) {
                        String absolutePathFromUri = VideoUtil.getAbsolutePathFromUri(outputFileOptions.a(), this.M);
                        Logger.i("VideoCapture", "Saved Location Path: " + absolutePathFromUri);
                        mediaMuxer = new MediaMuxer(absolutePathFromUri, 0);
                    } else {
                        this.N = outputFileOptions.a().openFileDescriptor(this.M, "rw");
                        return new MediaMuxer(this.N.getFileDescriptor(), 0);
                    }
                } catch (IOException e) {
                    this.M = null;
                    throw e;
                }
            } else {
                throw new IOException("Invalid Uri!");
            }
        } else {
            throw new IllegalArgumentException("The OutputFileOptions should assign before recording");
        }
        return mediaMuxer;
    }

    public void setTargetRotation(int i) {
        setTargetRotationInternal(i);
    }

    /* renamed from: startRecording */
    public void u(@NonNull final OutputFileOptions outputFileOptions, @NonNull final Executor executor, @NonNull final OnVideoSavedCallback onVideoSavedCallback) {
        Location location;
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.s2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoCapture.this.u(outputFileOptions, executor, onVideoSavedCallback);
                }
            });
            return;
        }
        Logger.i("VideoCapture", "startRecording");
        this.r.set(false);
        this.s.set(false);
        final b bVar = new b(executor, onVideoSavedCallback);
        CameraInternal camera = getCamera();
        if (camera == null) {
            bVar.onError(5, "Not bound to a Camera [" + this + "]", null);
        } else if (!this.p.get()) {
            bVar.onError(3, "It is still in video recording!", null);
        } else {
            try {
                this.F.startRecording();
                final AtomicReference atomicReference = new AtomicReference();
                this.z = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.l2
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object v;
                        v = VideoCapture.v(atomicReference, completer);
                        return v;
                    }
                });
                final CallbackToFutureAdapter.Completer completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
                this.z.addListener(new Runnable() { // from class: androidx.camera.core.n2
                    @Override // java.lang.Runnable
                    public final void run() {
                        VideoCapture.this.w();
                    }
                }, CameraXExecutors.mainThreadExecutor());
                try {
                    Logger.i("VideoCapture", "videoEncoder start");
                    this.x.start();
                    Logger.i("VideoCapture", "audioEncoder start");
                    this.y.start();
                    try {
                        synchronized (this.m) {
                            MediaMuxer r = r(outputFileOptions);
                            this.A = r;
                            Preconditions.checkNotNull(r);
                            this.A.setOrientationHint(getRelativeRotation(camera));
                            Metadata e = outputFileOptions.e();
                            if (e != null && (location = e.location) != null) {
                                this.A.setLocation((float) location.getLatitude(), (float) e.location.getLongitude());
                            }
                        }
                        this.n.set(false);
                        this.o.set(false);
                        this.p.set(false);
                        this.H = true;
                        notifyActive();
                        this.w.post(new Runnable() { // from class: androidx.camera.core.q2
                            @Override // java.lang.Runnable
                            public final void run() {
                                VideoCapture.this.x(bVar);
                            }
                        });
                        final String cameraId = getCameraId();
                        final Size attachedSurfaceResolution = getAttachedSurfaceResolution();
                        this.u.post(new Runnable() { // from class: androidx.camera.core.r2
                            @Override // java.lang.Runnable
                            public final void run() {
                                VideoCapture.this.y(bVar, cameraId, attachedSurfaceResolution, completer);
                            }
                        });
                    } catch (IOException e2) {
                        completer.set(null);
                        bVar.onError(2, "MediaMuxer creation failed!", e2);
                    }
                } catch (IllegalStateException e3) {
                    completer.set(null);
                    bVar.onError(1, "Audio/Video encoder start fail", e3);
                }
            } catch (IllegalStateException e4) {
                bVar.onError(1, "AudioRecorder start fail", e4);
            }
        }
    }

    /* renamed from: stopRecording */
    public void z() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            CameraXExecutors.mainThreadExecutor().execute(new Runnable() { // from class: androidx.camera.core.o2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoCapture.this.z();
                }
            });
            return;
        }
        Logger.i("VideoCapture", "stopRecording");
        notifyInactive();
        if (this.p.get() || !this.H) {
            return;
        }
        this.o.set(true);
    }
}
