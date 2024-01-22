package com.coveiot.android.camera.fragments;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.hardware.display.DisplayManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.coveiot.android.camera.ActivitySquareCamera;
import com.coveiot.android.camera.ActivitySquareCameraKt;
import com.coveiot.android.camera.R;
import com.coveiot.android.camera.fragments.CameraFragment;
import com.coveiot.android.camera.fragments.PermissionsFragment;
import com.coveiot.android.camera.utils.ViewExtensionsKt;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.messaging.Constants;
import java.io.File;
import java.io.FileFilter;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CameraFragment extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public ConstraintLayout h;
    public PreviewView i;
    public File j;
    public LocalBroadcastManager k;
    public DisplayManager l;
    public Executor m;
    @Nullable
    public Preview p;
    @Nullable
    public ImageCapture q;
    @Nullable
    public ImageAnalysis r;
    @Nullable
    public Camera s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int n = -1;
    public int o = 1;
    @NotNull
    public final CameraFragment$volumeDownReceiver$1 t = new BroadcastReceiver() { // from class: com.coveiot.android.camera.fragments.CameraFragment$volumeDownReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            ConstraintLayout constraintLayout;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            if (intent.getIntExtra(ActivitySquareCameraKt.KEY_EVENT_EXTRA, 0) == 25) {
                constraintLayout = CameraFragment.this.h;
                if (constraintLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("container");
                    constraintLayout = null;
                }
                ImageButton shutter = (ImageButton) constraintLayout.findViewById(R.id.camera_capture_button);
                Intrinsics.checkNotNullExpressionValue(shutter, "shutter");
                ViewExtensionsKt.simulateClick$default(shutter, 0L, 1, null);
            }
        }
    };
    @NotNull
    public final CameraFragment$displayListener$1 u = new DisplayManager.DisplayListener() { // from class: com.coveiot.android.camera.fragments.CameraFragment$displayListener$1
        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            int i2;
            ImageCapture imageCapture;
            ImageAnalysis imageAnalysis;
            View view = CameraFragment.this.getView();
            if (view != null) {
                CameraFragment cameraFragment = CameraFragment.this;
                i2 = cameraFragment.n;
                if (i == i2) {
                    Log.d("CameraXBasic", "Rotation changed: " + view.getDisplay().getRotation());
                    imageCapture = cameraFragment.q;
                    if (imageCapture != null) {
                        imageCapture.setTargetRotation(view.getDisplay().getRotation());
                    }
                    imageAnalysis = cameraFragment.r;
                    if (imageAnalysis != null) {
                        imageAnalysis.setTargetRotation(view.getDisplay().getRotation());
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }
    };
    @NotNull
    public final CameraFragment$imageSavedListener$1 v = new ImageCapture.OnImageSavedCallback() { // from class: com.coveiot.android.camera.fragments.CameraFragment$imageSavedListener$1
        @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
        public void onError(@NotNull ImageCaptureException exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            Log.e("CameraXBasic", "Photo capture failed: " + exception.getMessage(), exception.getCause());
        }

        @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
        public void onImageSaved(@NotNull ImageCapture.OutputFileResults outputFileResults) {
            Intrinsics.checkNotNullParameter(outputFileResults, "outputFileResults");
            Log.d("CameraXBasic", "Photo capture succeeded: " + outputFileResults.getSavedUri());
            if (outputFileResults.getSavedUri() != null) {
                Uri savedUri = outputFileResults.getSavedUri();
                Intrinsics.checkNotNull(savedUri);
                File file = new File(savedUri.getPath());
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    CameraFragment.this.o(file);
                }
                if (i < 24) {
                    CameraFragment.this.requireActivity().sendBroadcast(new Intent("android.hardware.action.NEW_PICTURE", Uri.fromFile(file)));
                }
                MediaScannerConnection.scanFile(CameraFragment.this.getContext(), new String[]{file.getAbsolutePath()}, new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension(kotlin.io.e.getExtension(file))}, null);
                CameraFragment.this.n();
            }
        }
    };

    /* loaded from: classes3.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final File a(File file, String str, String str2) {
            return new File(file, new SimpleDateFormat(str, Locale.ENGLISH).format(Long.valueOf(System.currentTimeMillis())) + str2);
        }
    }

    /* loaded from: classes3.dex */
    public static final class a implements ImageAnalysis.Analyzer {

        /* renamed from: a  reason: collision with root package name */
        public final int f4089a = 8;
        @NotNull
        public final ArrayDeque<Long> b = new ArrayDeque<>(5);
        @NotNull
        public final ArrayList<Function1<Double, Unit>> c;
        public long d;

        public a(@Nullable Function1<? super Double, Unit> function1) {
            ArrayList<Function1<Double, Unit>> arrayList = new ArrayList<>();
            if (function1 != null) {
                arrayList.add(function1);
            }
            this.c = arrayList;
        }

        public final byte[] a(ByteBuffer byteBuffer) {
            byteBuffer.rewind();
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        }

        @Override // androidx.camera.core.ImageAnalysis.Analyzer
        public void analyze(@NotNull ImageProxy image) {
            Intrinsics.checkNotNullParameter(image, "image");
            if (this.c.isEmpty()) {
                return;
            }
            this.b.push(Long.valueOf(System.currentTimeMillis()));
            while (this.b.size() >= this.f4089a) {
                this.b.removeLast();
            }
            Long peekFirst = this.b.peekFirst();
            if (peekFirst != null) {
                peekFirst.longValue();
            }
            Long peekLast = this.b.peekLast();
            if (peekLast != null) {
                peekLast.longValue();
            }
            kotlin.ranges.h.coerceAtLeast(this.b.size(), 1);
            if (this.b.getFirst().longValue() - this.d >= TimeUnit.SECONDS.toMillis(1L)) {
                Long first = this.b.getFirst();
                Intrinsics.checkNotNullExpressionValue(first, "frameTimestamps.first");
                this.d = first.longValue();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                Intrinsics.checkNotNullExpressionValue(buffer, "image.planes[0].buffer");
                byte[] a2 = a(buffer);
                ArrayList arrayList = new ArrayList(a2.length);
                for (byte b : a2) {
                    arrayList.add(Integer.valueOf(b & 255));
                }
                double averageOfInt = CollectionsKt___CollectionsKt.averageOfInt(arrayList);
                Iterator<T> it = this.c.iterator();
                while (it.hasNext()) {
                    ((Function1) it.next()).invoke(Double.valueOf(averageOfInt));
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class b extends Lambda implements Function1<Double, Unit> {
        public static final b INSTANCE = new b();

        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Double d) {
            invoke(d.doubleValue());
            return Unit.INSTANCE;
        }

        public final void invoke(double d) {
            Log.d("CameraXBasic", "Average luminosity: " + d);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.camera.fragments.CameraFragment$onViewCreated$1$1", f = "CameraFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes3.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$0(File file) {
            String[] extension_whitelist = GalleryFragmentKt.getEXTENSION_WHITELIST();
            Intrinsics.checkNotNullExpressionValue(file, "file");
            String extension = kotlin.io.e.getExtension(file);
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String upperCase = extension.toUpperCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
            return ArraysKt___ArraysKt.contains(extension_whitelist, upperCase);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                File file = CameraFragment.this.j;
                if (file == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("outputDirectory");
                    file = null;
                }
                File[] listFiles = file.listFiles(new FileFilter() { // from class: com.coveiot.android.camera.fragments.i
                    @Override // java.io.FileFilter
                    public final boolean accept(File file2) {
                        boolean invokeSuspend$lambda$0;
                        invokeSuspend$lambda$0 = CameraFragment.c.invokeSuspend$lambda$0(file2);
                        return invokeSuspend$lambda$0;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(listFiles, "outputDirectory.listFile….ROOT))\n                }");
                File file2 = (File) ArraysKt___ArraysKt.maxOrNull(listFiles);
                if (file2 != null) {
                    CameraFragment.this.o(file2);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void k(ListenableFuture cameraProviderFuture, CameraFragment this$0, int i, CameraSelector cameraSelector) {
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(cameraSelector, "$cameraSelector");
        V v = cameraProviderFuture.get();
        Intrinsics.checkNotNullExpressionValue(v, "cameraProviderFuture.get()");
        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) v;
        Preview.Builder targetRotation = new Preview.Builder().setTargetRotation(i);
        PreviewView previewView = this$0.i;
        Executor executor = null;
        if (previewView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView = null;
        }
        Preview build = targetRotation.setTargetResolution(this$0.l(previewView)).build();
        this$0.p = build;
        if (build != null) {
            PreviewView previewView2 = this$0.i;
            if (previewView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
                previewView2 = null;
            }
            build.setSurfaceProvider(previewView2.getSurfaceProvider());
        }
        ImageCapture.Builder captureMode = new ImageCapture.Builder().setCaptureMode(1);
        PreviewView previewView3 = this$0.i;
        if (previewView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView3 = null;
        }
        this$0.q = captureMode.setTargetResolution(this$0.l(previewView3)).setTargetRotation(i).build();
        ImageAnalysis.Builder builder = new ImageAnalysis.Builder();
        PreviewView previewView4 = this$0.i;
        if (previewView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView4 = null;
        }
        ImageAnalysis build2 = builder.setTargetResolution(this$0.l(previewView4)).setTargetRotation(i).build();
        Executor executor2 = this$0.m;
        if (executor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainExecutor");
        } else {
            executor = executor2;
        }
        build2.setAnalyzer(executor, new a(b.INSTANCE));
        this$0.r = build2;
        processCameraProvider.unbindAll();
        try {
            this$0.s = processCameraProvider.bindToLifecycle(this$0, cameraSelector, this$0.p, this$0.q, this$0.r);
        } catch (Exception e) {
            Log.e("CameraXBasic", "Use case binding failed", e);
        }
    }

    public static final void m(CameraFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PreviewView previewView = this$0.i;
        if (previewView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView = null;
        }
        this$0.n = previewView.getDisplay().getDisplayId();
        this$0.q();
        this$0.j();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    public static final void p(ImageButton thumbnail, CameraFragment this$0, File file) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullExpressionValue(thumbnail, "thumbnail");
        int dimension = (int) this$0.getResources().getDimension(R.dimen.stroke_small);
        thumbnail.setPadding(dimension, dimension, dimension, dimension);
        Glide.with(thumbnail).m27load(file).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(thumbnail);
    }

    public static final void r(final CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ImageCapture imageCapture = this$0.q;
        if (imageCapture != null) {
            Companion companion = Companion;
            File file = this$0.j;
            ConstraintLayout constraintLayout = null;
            if (file == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outputDirectory");
                file = null;
            }
            File a2 = companion.a(file, "yyyy-MM-dd-HH-mm-ss-SSS", ".jpg");
            ImageCapture.Metadata metadata = new ImageCapture.Metadata();
            metadata.setReversedHorizontal(this$0.o == 0);
            ImageCapture.OutputFileOptions build = new ImageCapture.OutputFileOptions.Builder(a2).setMetadata(metadata).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(photoFile)\n     …                 .build()");
            Executor executor = this$0.m;
            if (executor == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mainExecutor");
                executor = null;
            }
            imageCapture.a0(build, executor, this$0.v);
            if (Build.VERSION.SDK_INT >= 23) {
                ConstraintLayout constraintLayout2 = this$0.h;
                if (constraintLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("container");
                } else {
                    constraintLayout = constraintLayout2;
                }
                constraintLayout.postDelayed(new Runnable() { // from class: com.coveiot.android.camera.fragments.g
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraFragment.s(CameraFragment.this);
                    }
                }, 100L);
            }
        }
    }

    public static final void s(final CameraFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout constraintLayout = this$0.h;
        ConstraintLayout constraintLayout2 = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            constraintLayout = null;
        }
        constraintLayout.setForeground(new ColorDrawable(-1));
        ConstraintLayout constraintLayout3 = this$0.h;
        if (constraintLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
        } else {
            constraintLayout2 = constraintLayout3;
        }
        constraintLayout2.postDelayed(new Runnable() { // from class: com.coveiot.android.camera.fragments.f
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.t(CameraFragment.this);
            }
        }, 50L);
    }

    public static final void t(CameraFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConstraintLayout constraintLayout = this$0.h;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            constraintLayout = null;
        }
        constraintLayout.setForeground(null);
    }

    public static final void u(CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = this$0.o == 0 ? 1 : 0;
        this$0.j();
    }

    public static final void v(CameraFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.n();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final int i(int i, int i2) {
        double max = Math.max(i, i2) / Math.min(i, i2);
        return Math.abs(max - 1.3333333333333333d) <= Math.abs(max - 1.7777777777777777d) ? 0 : 1;
    }

    public final void j() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        PreviewView previewView = this.i;
        Executor executor = null;
        if (previewView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView = null;
        }
        previewView.getDisplay().getRealMetrics(displayMetrics);
        Log.d("CameraXBasic", "Screen metrics: " + displayMetrics.widthPixels + " x " + displayMetrics.heightPixels);
        int i = i(displayMetrics.widthPixels, displayMetrics.heightPixels);
        StringBuilder sb = new StringBuilder();
        sb.append("Preview aspect ratio: ");
        sb.append(i);
        Log.d("CameraXBasic", sb.toString());
        PreviewView previewView2 = this.i;
        if (previewView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView2 = null;
        }
        final int rotation = previewView2.getDisplay().getRotation();
        final CameraSelector build = new CameraSelector.Builder().requireLensFacing(this.o).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder().requireLensFacing(lensFacing).build()");
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(requireContext());
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(requireContext())");
        Runnable runnable = new Runnable() { // from class: com.coveiot.android.camera.fragments.h
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.k(ListenableFuture.this, this, rotation, build);
            }
        };
        Executor executor2 = this.m;
        if (executor2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mainExecutor");
        } else {
            executor = executor2;
        }
        processCameraProvider.addListener(runnable, executor);
    }

    public final Size l(PreviewView previewView) {
        return new Size(previewView.getMeasuredWidth(), previewView.getMeasuredHeight());
    }

    public final void n() {
        File file = this.j;
        File file2 = null;
        if (file == null) {
            Intrinsics.throwUninitializedPropertyAccessException("outputDirectory");
            file = null;
        }
        File[] listFiles = file.listFiles();
        boolean z = false;
        if (listFiles != null) {
            if (true == (!(listFiles.length == 0))) {
                z = true;
            }
        }
        if (z) {
            NavController findNavController = Navigation.findNavController(requireActivity(), R.id.fragment_container);
            File file3 = this.j;
            if (file3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("outputDirectory");
            } else {
                file2 = file3;
            }
            findNavController.navigate(CameraFragmentDirections.actionCameraToGallery(file2.getAbsolutePath()));
        }
    }

    public final void o(final File file) {
        ConstraintLayout constraintLayout = this.h;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            constraintLayout = null;
        }
        final ImageButton imageButton = (ImageButton) constraintLayout.findViewById(R.id.photo_view_button);
        imageButton.post(new Runnable() { // from class: com.coveiot.android.camera.fragments.d
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.p(imageButton, this, file);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        q();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Executor mainExecutor = ContextCompat.getMainExecutor(requireContext());
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(requireContext())");
        this.m = mainExecutor;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_camera, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LocalBroadcastManager localBroadcastManager = this.k;
        DisplayManager displayManager = null;
        if (localBroadcastManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("broadcastManager");
            localBroadcastManager = null;
        }
        localBroadcastManager.unregisterReceiver(this.t);
        DisplayManager displayManager2 = this.l;
        if (displayManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayManager");
        } else {
            displayManager = displayManager2;
        }
        displayManager.unregisterDisplayListener(this.u);
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        PermissionsFragment.Companion companion = PermissionsFragment.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (companion.hasPermissions(requireContext)) {
            return;
        }
        Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(CameraFragmentDirections.actionCameraToPermissions());
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"MissingPermission"})
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        ConstraintLayout constraintLayout;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        ConstraintLayout constraintLayout2 = (ConstraintLayout) view;
        this.h = constraintLayout2;
        PreviewView previewView = null;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            constraintLayout = null;
        } else {
            constraintLayout = constraintLayout2;
        }
        View findViewById = constraintLayout.findViewById(R.id.view_finder);
        Intrinsics.checkNotNullExpressionValue(findViewById, "container.findViewById(R.id.view_finder)");
        this.i = (PreviewView) findViewById;
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(constraintLayout2.getContext());
        Intrinsics.checkNotNullExpressionValue(localBroadcastManager, "getInstance(view.context)");
        this.k = localBroadcastManager;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActivitySquareCameraKt.KEY_EVENT_ACTION);
        LocalBroadcastManager localBroadcastManager2 = this.k;
        if (localBroadcastManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("broadcastManager");
            localBroadcastManager2 = null;
        }
        localBroadcastManager2.registerReceiver(this.t, intentFilter);
        PreviewView previewView2 = this.i;
        if (previewView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
            previewView2 = null;
        }
        Object systemService = previewView2.getContext().getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        DisplayManager displayManager = (DisplayManager) systemService;
        this.l = displayManager;
        if (displayManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("displayManager");
            displayManager = null;
        }
        displayManager.registerDisplayListener(this.u, null);
        ActivitySquareCamera.Companion companion = ActivitySquareCamera.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.j = companion.getOutputDirectory(requireContext);
        PreviewView previewView3 = this.i;
        if (previewView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewFinder");
        } else {
            previewView = previewView3;
        }
        previewView.post(new Runnable() { // from class: com.coveiot.android.camera.fragments.e
            @Override // java.lang.Runnable
            public final void run() {
                CameraFragment.m(CameraFragment.this);
            }
        });
    }

    public final void q() {
        ConstraintLayout constraintLayout = this.h;
        ConstraintLayout constraintLayout2 = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            constraintLayout = null;
        }
        ConstraintLayout constraintLayout3 = (ConstraintLayout) constraintLayout.findViewById(R.id.camera_ui_container);
        if (constraintLayout3 != null) {
            ConstraintLayout constraintLayout4 = this.h;
            if (constraintLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("container");
                constraintLayout4 = null;
            }
            constraintLayout4.removeView(constraintLayout3);
        }
        Context requireContext = requireContext();
        int i = R.layout.camera_ui_container;
        ConstraintLayout constraintLayout5 = this.h;
        if (constraintLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
        } else {
            constraintLayout2 = constraintLayout5;
        }
        View inflate = View.inflate(requireContext, i, constraintLayout2);
        ((ImageButton) inflate.findViewById(R.id.camera_capture_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraFragment.r(CameraFragment.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.camera_switch_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraFragment.u(CameraFragment.this, view);
            }
        });
        ((ImageButton) inflate.findViewById(R.id.photo_view_button)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.camera.fragments.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraFragment.v(CameraFragment.this, view);
            }
        });
    }
}
