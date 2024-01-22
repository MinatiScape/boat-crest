package com.bumptech.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.signature.AndroidResourceSignature;
import com.bumptech.glide.util.Executors;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class RequestBuilder<TranscodeType> extends BaseRequestOptions<RequestBuilder<TranscodeType>> {
    public static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    public final Context H;
    public final RequestManager I;
    public final Class<TranscodeType> J;
    public final Glide K;
    public final GlideContext L;
    @NonNull
    public TransitionOptions<?, ? super TranscodeType> M;
    @Nullable
    public Object N;
    @Nullable
    public List<RequestListener<TranscodeType>> O;
    @Nullable
    public RequestBuilder<TranscodeType> P;
    @Nullable
    public RequestBuilder<TranscodeType> Q;
    @Nullable
    public Float R;
    public boolean S;
    public boolean T;
    public boolean U;

    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f2311a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[Priority.values().length];
            b = iArr;
            try {
                iArr[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                b[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            f2311a = iArr2;
            try {
                iArr2[ImageView.ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f2311a[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f2311a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f2311a[ImageView.ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f2311a[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f2311a[ImageView.ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f2311a[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f2311a[ImageView.ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    @SuppressLint({"CheckResult"})
    public RequestBuilder(@NonNull Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        this.S = true;
        this.K = glide;
        this.I = requestManager;
        this.J = cls;
        this.H = context;
        this.M = requestManager.c(cls);
        this.L = glide.d();
        q(requestManager.a());
        apply((BaseRequestOptions<?>) requestManager.b());
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> addListener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (isAutoCloneEnabled()) {
            return mo14clone().addListener(requestListener);
        }
        if (requestListener != null) {
            if (this.O == null) {
                this.O = new ArrayList();
            }
            this.O.add(requestListener);
        }
        return selfOrThrowIfLocked();
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public /* bridge */ /* synthetic */ BaseRequestOptions apply(@NonNull BaseRequestOptions baseRequestOptions) {
        return apply((BaseRequestOptions<?>) baseRequestOptions);
    }

    @CheckResult
    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(@NonNull Y y) {
        return (Y) getDownloadOnlyRequest().into((RequestBuilder<File>) y);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public boolean equals(Object obj) {
        if (obj instanceof RequestBuilder) {
            RequestBuilder requestBuilder = (RequestBuilder) obj;
            return super.equals(requestBuilder) && Objects.equals(this.J, requestBuilder.J) && this.M.equals(requestBuilder.M) && Objects.equals(this.N, requestBuilder.N) && Objects.equals(this.O, requestBuilder.O) && Objects.equals(this.P, requestBuilder.P) && Objects.equals(this.Q, requestBuilder.Q) && Objects.equals(this.R, requestBuilder.R) && this.S == requestBuilder.S && this.T == requestBuilder.T;
        }
        return false;
    }

    @NonNull
    public RequestBuilder<TranscodeType> error(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        if (isAutoCloneEnabled()) {
            return mo14clone().error((RequestBuilder) requestBuilder);
        }
        this.Q = requestBuilder;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply((BaseRequestOptions<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    public int hashCode() {
        return Util.hashCode(this.T, Util.hashCode(this.S, Util.hashCode(this.R, Util.hashCode(this.Q, Util.hashCode(this.P, Util.hashCode(this.O, Util.hashCode(this.N, Util.hashCode(this.M, Util.hashCode(this.J, super.hashCode())))))))));
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y into(@NonNull Y y) {
        return (Y) s(y, null, Executors.mainThreadExecutor());
    }

    public final Request l(Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        return m(new Object(), target, requestListener, null, this.M, baseRequestOptions.getPriority(), baseRequestOptions.getOverrideWidth(), baseRequestOptions.getOverrideHeight(), baseRequestOptions, executor);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> listener(@Nullable RequestListener<TranscodeType> requestListener) {
        if (isAutoCloneEnabled()) {
            return mo14clone().listener(requestListener);
        }
        this.O = null;
        return addListener(requestListener);
    }

    public final Request m(Object obj, Target<TranscodeType> target, @Nullable RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        ErrorRequestCoordinator errorRequestCoordinator;
        ErrorRequestCoordinator errorRequestCoordinator2;
        if (this.Q != null) {
            errorRequestCoordinator2 = new ErrorRequestCoordinator(obj, requestCoordinator);
            errorRequestCoordinator = errorRequestCoordinator2;
        } else {
            errorRequestCoordinator = null;
            errorRequestCoordinator2 = requestCoordinator;
        }
        Request n = n(obj, target, requestListener, errorRequestCoordinator2, transitionOptions, priority, i, i2, baseRequestOptions, executor);
        if (errorRequestCoordinator == null) {
            return n;
        }
        int overrideWidth = this.Q.getOverrideWidth();
        int overrideHeight = this.Q.getOverrideHeight();
        if (Util.isValidDimensions(i, i2) && !this.Q.isValidOverride()) {
            overrideWidth = baseRequestOptions.getOverrideWidth();
            overrideHeight = baseRequestOptions.getOverrideHeight();
        }
        RequestBuilder<TranscodeType> requestBuilder = this.Q;
        ErrorRequestCoordinator errorRequestCoordinator3 = errorRequestCoordinator;
        errorRequestCoordinator3.setRequests(n, requestBuilder.m(obj, target, requestListener, errorRequestCoordinator3, requestBuilder.M, requestBuilder.getPriority(), overrideWidth, overrideHeight, this.Q, executor));
        return errorRequestCoordinator3;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.bumptech.glide.request.BaseRequestOptions] */
    public final Request n(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, @Nullable RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Priority p;
        RequestBuilder<TranscodeType> requestBuilder = this.P;
        if (requestBuilder != null) {
            if (!this.U) {
                TransitionOptions<?, ? super TranscodeType> transitionOptions2 = requestBuilder.S ? transitionOptions : requestBuilder.M;
                if (requestBuilder.isPrioritySet()) {
                    p = this.P.getPriority();
                } else {
                    p = p(priority);
                }
                Priority priority2 = p;
                int overrideWidth = this.P.getOverrideWidth();
                int overrideHeight = this.P.getOverrideHeight();
                if (Util.isValidDimensions(i, i2) && !this.P.isValidOverride()) {
                    overrideWidth = baseRequestOptions.getOverrideWidth();
                    overrideHeight = baseRequestOptions.getOverrideHeight();
                }
                ThumbnailRequestCoordinator thumbnailRequestCoordinator = new ThumbnailRequestCoordinator(obj, requestCoordinator);
                Request v = v(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator, transitionOptions, priority, i, i2, executor);
                this.U = true;
                RequestBuilder<TranscodeType> requestBuilder2 = this.P;
                Request m = requestBuilder2.m(obj, target, requestListener, thumbnailRequestCoordinator, transitionOptions2, priority2, overrideWidth, overrideHeight, requestBuilder2, executor);
                this.U = false;
                thumbnailRequestCoordinator.setRequests(v, m);
                return thumbnailRequestCoordinator;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.R != null) {
            ThumbnailRequestCoordinator thumbnailRequestCoordinator2 = new ThumbnailRequestCoordinator(obj, requestCoordinator);
            thumbnailRequestCoordinator2.setRequests(v(obj, target, requestListener, baseRequestOptions, thumbnailRequestCoordinator2, transitionOptions, priority, i, i2, executor), v(obj, target, requestListener, baseRequestOptions.mo14clone().sizeMultiplier(this.R.floatValue()), thumbnailRequestCoordinator2, transitionOptions, p(priority), i, i2, executor));
            return thumbnailRequestCoordinator2;
        } else {
            return v(obj, target, requestListener, baseRequestOptions, requestCoordinator, transitionOptions, priority, i, i2, executor);
        }
    }

    public final RequestBuilder<TranscodeType> o() {
        return mo14clone().error((RequestBuilder) null).thumbnail((RequestBuilder) null);
    }

    @NonNull
    public final Priority p(@NonNull Priority priority) {
        int i = a.b[priority.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3 && i != 4) {
                    throw new IllegalArgumentException("unknown priority: " + getPriority());
                }
                return Priority.IMMEDIATE;
            }
            return Priority.HIGH;
        }
        return Priority.NORMAL;
    }

    @NonNull
    public Target<TranscodeType> preload(int i, int i2) {
        return into((RequestBuilder<TranscodeType>) PreloadTarget.obtain(this.I, i, i2));
    }

    @SuppressLint({"CheckResult"})
    public final void q(List<RequestListener<Object>> list) {
        for (RequestListener<Object> requestListener : list) {
            addListener(requestListener);
        }
    }

    public final <Y extends Target<TranscodeType>> Y r(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, Executor executor) {
        Preconditions.checkNotNull(y);
        if (this.T) {
            Request l = l(y, requestListener, baseRequestOptions, executor);
            Request request = y.getRequest();
            if (l.isEquivalentTo(request) && !t(baseRequestOptions, request)) {
                if (!((Request) Preconditions.checkNotNull(request)).isRunning()) {
                    request.begin();
                }
                return y;
            }
            this.I.clear((Target<?>) y);
            y.setRequest(l);
            this.I.d(y, l);
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    @NonNull
    public <Y extends Target<TranscodeType>> Y s(@NonNull Y y, @Nullable RequestListener<TranscodeType> requestListener, Executor executor) {
        return (Y) r(y, requestListener, this, executor);
    }

    @NonNull
    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public final boolean t(BaseRequestOptions<?> baseRequestOptions, Request request) {
        return !baseRequestOptions.isMemoryCacheable() && request.isComplete();
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType> requestBuilder) {
        if (isAutoCloneEnabled()) {
            return mo14clone().thumbnail(requestBuilder);
        }
        this.P = requestBuilder;
        return selfOrThrowIfLocked();
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> transition(@NonNull TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        if (isAutoCloneEnabled()) {
            return mo14clone().transition(transitionOptions);
        }
        this.M = (TransitionOptions) Preconditions.checkNotNull(transitionOptions);
        this.S = false;
        return selfOrThrowIfLocked();
    }

    @NonNull
    public final RequestBuilder<TranscodeType> u(@Nullable Object obj) {
        if (isAutoCloneEnabled()) {
            return mo14clone().u(obj);
        }
        this.N = obj;
        this.T = true;
        return selfOrThrowIfLocked();
    }

    public final Request v(Object obj, Target<TranscodeType> target, RequestListener<TranscodeType> requestListener, BaseRequestOptions<?> baseRequestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int i, int i2, Executor executor) {
        Context context = this.H;
        GlideContext glideContext = this.L;
        return SingleRequest.obtain(context, glideContext, obj, this.N, this.J, baseRequestOptions, i, i2, priority, target, requestListener, this.O, requestCoordinator, glideContext.getEngine(), transitionOptions.a(), executor);
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> apply(@NonNull BaseRequestOptions<?> baseRequestOptions) {
        Preconditions.checkNotNull(baseRequestOptions);
        return (RequestBuilder) super.apply(baseRequestOptions);
    }

    @CheckResult
    @Deprecated
    public FutureTarget<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    @NonNull
    public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView imageView) {
        RequestBuilder<TranscodeType> requestBuilder;
        Util.assertMainThread();
        Preconditions.checkNotNull(imageView);
        if (!isTransformationSet() && isTransformationAllowed() && imageView.getScaleType() != null) {
            switch (a.f2311a[imageView.getScaleType().ordinal()]) {
                case 1:
                    requestBuilder = mo14clone().optionalCenterCrop();
                    break;
                case 2:
                    requestBuilder = mo14clone().optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    requestBuilder = mo14clone().optionalFitCenter();
                    break;
                case 6:
                    requestBuilder = mo14clone().optionalCenterInside();
                    break;
            }
            return (ViewTarget) r(this.L.buildImageViewTarget(imageView, this.J), null, requestBuilder, Executors.mainThreadExecutor());
        }
        requestBuilder = this;
        return (ViewTarget) r(this.L.buildImageViewTarget(imageView, this.J), null, requestBuilder, Executors.mainThreadExecutor());
    }

    @NonNull
    public FutureTarget<TranscodeType> submit(int i, int i2) {
        RequestFutureTarget requestFutureTarget = new RequestFutureTarget(i, i2);
        return (FutureTarget) s(requestFutureTarget, requestFutureTarget, Executors.directExecutor());
    }

    @Override // com.bumptech.glide.request.BaseRequestOptions
    @CheckResult
    /* renamed from: clone */
    public RequestBuilder<TranscodeType> mo14clone() {
        RequestBuilder<TranscodeType> requestBuilder = (RequestBuilder) super.mo14clone();
        requestBuilder.M = (TransitionOptions<?, ? super TranscodeType>) requestBuilder.M.m33clone();
        if (requestBuilder.O != null) {
            requestBuilder.O = new ArrayList(requestBuilder.O);
        }
        RequestBuilder<TranscodeType> requestBuilder2 = requestBuilder.P;
        if (requestBuilder2 != null) {
            requestBuilder.P = requestBuilder2.mo14clone();
        }
        RequestBuilder<TranscodeType> requestBuilder3 = requestBuilder.Q;
        if (requestBuilder3 != null) {
            requestBuilder.Q = requestBuilder3.mo14clone();
        }
        return requestBuilder;
    }

    @NonNull
    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> error(Object obj) {
        if (obj == null) {
            return error((RequestBuilder) null);
        }
        return error((RequestBuilder) o().m20load(obj));
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(@Nullable RequestBuilder<TranscodeType>... requestBuilderArr) {
        if (requestBuilderArr != null && requestBuilderArr.length != 0) {
            return thumbnail(Arrays.asList(requestBuilderArr));
        }
        return thumbnail((RequestBuilder) null);
    }

    @NonNull
    @CheckResult
    public RequestBuilder<TranscodeType> thumbnail(@Nullable List<RequestBuilder<TranscodeType>> list) {
        RequestBuilder<TranscodeType> requestBuilder = null;
        if (list != null && !list.isEmpty()) {
            for (int size = list.size() - 1; size >= 0; size--) {
                RequestBuilder<TranscodeType> requestBuilder2 = list.get(size);
                if (requestBuilder2 != null) {
                    requestBuilder = requestBuilder == null ? requestBuilder2 : requestBuilder2.thumbnail(requestBuilder);
                }
            }
            return thumbnail(requestBuilder);
        }
        return thumbnail((RequestBuilder) null);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m20load(@Nullable Object obj) {
        return u(obj);
    }

    @SuppressLint({"CheckResult"})
    public RequestBuilder(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        this(requestBuilder.K, requestBuilder.I, cls, requestBuilder.H);
        this.N = requestBuilder.N;
        this.T = requestBuilder.T;
        apply((BaseRequestOptions<?>) requestBuilder);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m15load(@Nullable Bitmap bitmap) {
        return u(bitmap).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m16load(@Nullable Drawable drawable) {
        return u(drawable).apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m21load(@Nullable String str) {
        return u(str);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m17load(@Nullable Uri uri) {
        return u(uri);
    }

    @NonNull
    @CheckResult
    @Deprecated
    public RequestBuilder<TranscodeType> thumbnail(float f) {
        if (isAutoCloneEnabled()) {
            return mo14clone().thumbnail(f);
        }
        if (f >= 0.0f && f <= 1.0f) {
            this.R = Float.valueOf(f);
            return selfOrThrowIfLocked();
        }
        throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m18load(@Nullable File file) {
        return u(file);
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m19load(@Nullable @DrawableRes @RawRes Integer num) {
        return u(num).apply((BaseRequestOptions<?>) RequestOptions.signatureOf(AndroidResourceSignature.obtain(this.H)));
    }

    @CheckResult
    @Deprecated
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m22load(@Nullable URL url) {
        return u(url);
    }

    @NonNull
    @CheckResult
    /* renamed from: load */
    public RequestBuilder<TranscodeType> m23load(@Nullable byte[] bArr) {
        RequestBuilder<TranscodeType> u = u(bArr);
        if (!u.isDiskCacheStrategySet()) {
            u = u.apply((BaseRequestOptions<?>) RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE));
        }
        return !u.isSkipMemoryCacheSet() ? u.apply((BaseRequestOptions<?>) RequestOptions.skipMemoryCacheOf(true)) : u;
    }
}
