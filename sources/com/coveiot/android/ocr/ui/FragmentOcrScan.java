package com.coveiot.android.ocr.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.ocr.R;
import com.coveiot.android.ocr.model.OcrDeviceType;
import com.coveiot.android.ocr.utils.OcrUtilsKt;
import com.coveiot.android.ocr.utils.PagerContainer;
import com.coveiot.android.ocr.utils.RemotePagerAdapter;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.textrecognition.OcrConstants;
import com.coveiot.textrecognition.OxymeterHelper;
import com.coveiot.textrecognition.base.BaseOcrHelper;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.OcrError;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.utils.utility.LogHelper;
import com.google.common.util.concurrent.ListenableFuture;
import com.viewpagerindicator.CirclePageIndicator;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentOcrScan extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public long n;
    @Nullable
    public ImageCapture t;
    @Nullable
    public BaseOcrHelper u;
    @Nullable
    public ResponseCallback<BaseOcrResponse> v;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentOcrScan";
    @NotNull
    public ArrayList<Integer> o = new ArrayList<>();
    @NotNull
    public ArrayList<Integer> p = new ArrayList<>();
    @NotNull
    public OcrDeviceType q = OcrDeviceType.OXYMETER;
    public final int r = 10;
    @NotNull
    public final String[] s = {"android.permission.CAMERA"};

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentOcrScan newInstance(@NotNull ArrayList<Integer> contents, @NotNull ArrayList<Integer> images, @NotNull OcrDeviceType ocrDeviceType) {
            Intrinsics.checkNotNullParameter(contents, "contents");
            Intrinsics.checkNotNullParameter(images, "images");
            Intrinsics.checkNotNullParameter(ocrDeviceType, "ocrDeviceType");
            FragmentOcrScan fragmentOcrScan = new FragmentOcrScan();
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("instr_contents", contents);
            bundle.putIntegerArrayList("instr_images", images);
            bundle.putSerializable("ocr_type", ocrDeviceType);
            fragmentOcrScan.setArguments(bundle);
            return fragmentOcrScan;
        }
    }

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[OcrDeviceType.values().length];
            try {
                iArr[OcrDeviceType.OXYMETER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[OcrDeviceType.BP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentOcrScan newInstance(@NotNull ArrayList<Integer> arrayList, @NotNull ArrayList<Integer> arrayList2, @NotNull OcrDeviceType ocrDeviceType) {
        return Companion.newInstance(arrayList, arrayList2, ocrDeviceType);
    }

    public static final void p(FragmentOcrScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public static final void q(FragmentOcrScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public static final void t(FragmentOcrScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (SystemClock.elapsedRealtime() - this$0.n > 1000) {
            this$0.n = SystemClock.elapsedRealtime();
            if (this$0.s()) {
                this$0.x();
            }
        }
    }

    public static final void w(ListenableFuture cameraProviderFuture, FragmentOcrScan this$0) {
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        V v = cameraProviderFuture.get();
        Intrinsics.checkNotNullExpressionValue(v, "cameraProviderFuture.get()");
        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) v;
        Preview build = new Preview.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n                .build()");
        this$0.t = new ImageCapture.Builder().setCaptureMode(0).build();
        CameraSelector build2 = new CameraSelector.Builder().requireLensFacing(1).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder().requireLensFac…LENS_FACING_BACK).build()");
        try {
            processCameraProvider.unbindAll();
            build.setSurfaceProvider(((PreviewView) this$0._$_findCachedViewById(R.id.preview_camera)).getSurfaceProvider());
            Intrinsics.checkNotNullExpressionValue(processCameraProvider.bindToLifecycle(this$0, build2, build, this$0.t), "cameraProvider.bindToLif…Capture\n                )");
        } catch (Exception e) {
            ResponseCallback<BaseOcrResponse> responseCallback = this$0.v;
            if (responseCallback != null) {
                String string = this$0.getString(R.string.scan_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.scan_failed)");
                responseCallback.onFailure(new OcrError(string));
            }
            LogHelper.e(this$0.m, "Camera use case binding failed", e);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    public final void initToolbar() {
        int i = R.id.toolbar;
        View _$_findCachedViewById = _$_findCachedViewById(i);
        int i2 = R.id.toolbar_back_arrow;
        ((TextView) _$_findCachedViewById.findViewById(i2)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(i2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.ocr.ui.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentOcrScan.p(FragmentOcrScan.this, view);
            }
        });
        View _$_findCachedViewById2 = _$_findCachedViewById(i);
        int i3 = R.id.save;
        ((TextView) _$_findCachedViewById2.findViewById(i3)).setVisibility(0);
        ((TextView) _$_findCachedViewById(i).findViewById(i3)).setText(getString(R.string.close));
        ((TextView) _$_findCachedViewById(i).findViewById(R.id.toolbar_title)).setText(getString(R.string.camera_scan));
        ((TextView) _$_findCachedViewById(i).findViewById(i3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.ocr.ui.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentOcrScan.q(FragmentOcrScan.this, view);
            }
        });
    }

    public final void o() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.q.ordinal()];
        if (i == 1) {
            OxymeterHelper.Companion companion = OxymeterHelper.Companion;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            this.u = companion.getInstance(requireActivity);
        } else if (i != 2) {
        } else {
            OxymeterHelper.Companion companion2 = OxymeterHelper.Companion;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            this.u = companion2.getInstance(requireActivity2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogHelper.d(this.m, "onActivityResult");
        if (i == this.r) {
            startCameraByCheckingPermission();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            ArrayList<Integer> integerArrayList = arguments.getIntegerArrayList("instr_contents");
            if (integerArrayList == null) {
                integerArrayList = new ArrayList<>();
            } else {
                Intrinsics.checkNotNullExpressionValue(integerArrayList, "it.getIntegerArrayList(I…_CONTENTS) ?: ArrayList()");
            }
            this.o = integerArrayList;
            ArrayList<Integer> integerArrayList2 = arguments.getIntegerArrayList("instr_images");
            if (integerArrayList2 == null) {
                integerArrayList2 = new ArrayList<>();
            } else {
                Intrinsics.checkNotNullExpressionValue(integerArrayList2, "it.getIntegerArrayList(I…TR_IMAGES) ?: ArrayList()");
            }
            this.p = integerArrayList2;
            if (arguments.getSerializable("ocr_type") != null) {
                Serializable serializable = arguments.getSerializable("ocr_type");
                Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.android.ocr.model.OcrDeviceType");
                this.q = (OcrDeviceType) serializable;
            }
        }
        o();
        startCameraByCheckingPermission();
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_ocr_scan, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        dismissProgress();
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        LogHelper.d(this.m, "onRequestPermissionsResult");
        if (i == this.r) {
            startCameraByCheckingPermission();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initToolbar();
        r();
        ((Button) _$_findCachedViewById(R.id.btn_capture)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.ocr.ui.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentOcrScan.t(FragmentOcrScan.this, view2);
            }
        });
    }

    public final void r() {
        FragmentActivity activity = getActivity();
        PagerContainer pagerContainer = activity != null ? (PagerContainer) activity.findViewById(R.id.viewpagger_ocr) : null;
        Intrinsics.checkNotNull(pagerContainer, "null cannot be cast to non-null type com.coveiot.android.ocr.utils.PagerContainer");
        FragmentActivity activity2 = getActivity();
        pagerContainer.setAdapter(new RemotePagerAdapter(activity2 != null ? activity2.getSupportFragmentManager() : null, getActivity(), CollectionsKt___CollectionsKt.toIntArray(this.o), CollectionsKt___CollectionsKt.toIntArray(this.p)));
        pagerContainer.setAnimationEnabled(true);
        pagerContainer.setFadeEnabled(true);
        pagerContainer.setFadeFactor(0.6f);
        ((CirclePageIndicator) _$_findCachedViewById(R.id.circlePageIndicator_ocr)).setViewPager(pagerContainer);
    }

    public final boolean s() {
        if (OcrUtilsKt.checkIfCameraPermissionExists(getActivity())) {
            return true;
        }
        if (shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
            u();
            return false;
        }
        requestPermissions(this.s, this.r);
        return false;
    }

    public final void setResponseCallback(@NotNull ResponseCallback<BaseOcrResponse> ocrResponseCallback) {
        Intrinsics.checkNotNullParameter(ocrResponseCallback, "ocrResponseCallback");
        this.v = ocrResponseCallback;
    }

    public final void startCameraByCheckingPermission() {
        if (s()) {
            v();
        }
    }

    public final void u() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(R.string.camera_permission_content);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.camera_permission_content)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.ocr.ui.FragmentOcrScan$showCameraPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                int i;
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                FragmentOcrScan fragmentOcrScan = this;
                i = fragmentOcrScan.r;
                OcrUtilsKt.openAppSettings(fragmentOcrScan, i);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void v() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(requireActivity());
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(requireActivity())");
        processCameraProvider.addListener(new Runnable() { // from class: com.coveiot.android.ocr.ui.d
            @Override // java.lang.Runnable
            public final void run() {
                FragmentOcrScan.w(ListenableFuture.this, this);
            }
        }, ContextCompat.getMainExecutor(requireActivity()));
    }

    public final void x() {
        ImageCapture imageCapture = this.t;
        if (imageCapture == null) {
            return;
        }
        FragmentActivity activity = getActivity();
        final File file = new File(activity != null ? activity.getFilesDir() : null, OcrConstants.INSTANCE.getFILE_NAME());
        ImageCapture.OutputFileOptions build = new ImageCapture.OutputFileOptions.Builder(file).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder(photoFile).build()");
        imageCapture.a0(build, ContextCompat.getMainExecutor(requireActivity()), new ImageCapture.OnImageSavedCallback() { // from class: com.coveiot.android.ocr.ui.FragmentOcrScan$takePhoto$1
            /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
                r0 = r4.f5555a.v;
             */
            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onError(@org.jetbrains.annotations.NotNull androidx.camera.core.ImageCaptureException r5) {
                /*
                    r4 = this;
                    java.lang.String r0 = "exc"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                    r0.dismissProgress()
                    com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                    boolean r0 = r0.isAdded()
                    if (r0 == 0) goto L2f
                    com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                    com.coveiot.textrecognition.base.ResponseCallback r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.access$getOcrResponseCallback$p(r0)
                    if (r0 == 0) goto L2f
                    com.coveiot.textrecognition.base.OcrError r1 = new com.coveiot.textrecognition.base.OcrError
                    com.coveiot.android.ocr.ui.FragmentOcrScan r2 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                    int r3 = com.coveiot.android.ocr.R.string.scan_failed
                    java.lang.String r2 = r2.getString(r3)
                    java.lang.String r3 = "getString(R.string.scan_failed)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                    r1.<init>(r2)
                    r0.onFailure(r1)
                L2f:
                    com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                    java.lang.String r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.access$getTAG$p(r0)
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "Photo capture failed: "
                    r1.append(r2)
                    java.lang.String r2 = r5.getMessage()
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    com.coveiot.utils.utility.LogHelper.e(r0, r1, r5)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.ocr.ui.FragmentOcrScan$takePhoto$1.onError(androidx.camera.core.ImageCaptureException):void");
            }

            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onImageSaved(@NotNull ImageCapture.OutputFileResults output) {
                BaseOcrHelper baseOcrHelper;
                BaseOcrHelper baseOcrHelper2;
                Intrinsics.checkNotNullParameter(output, "output");
                if (FragmentOcrScan.this.isAdded()) {
                    FragmentOcrScan fragmentOcrScan = FragmentOcrScan.this;
                    String string = fragmentOcrScan.getString(com.coveiot.android.theme.R.string.please_wait);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…eme.R.string.please_wait)");
                    fragmentOcrScan.showProgressWithTitle(string, true);
                }
                baseOcrHelper = FragmentOcrScan.this.u;
                if (baseOcrHelper != null) {
                    final FragmentOcrScan fragmentOcrScan2 = FragmentOcrScan.this;
                    baseOcrHelper.setResponseCallback(new ResponseCallback<BaseOcrResponse>() { // from class: com.coveiot.android.ocr.ui.FragmentOcrScan$takePhoto$1$onImageSaved$1
                        /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
                            r4 = r1.v;
                         */
                        @Override // com.coveiot.textrecognition.base.ResponseCallback
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public void onFailure(@org.jetbrains.annotations.NotNull com.coveiot.textrecognition.base.OcrError r4) {
                            /*
                                r3 = this;
                                java.lang.String r0 = "error"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                                com.coveiot.android.ocr.ui.FragmentOcrScan r4 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                r4.dismissProgress()
                                com.coveiot.android.ocr.ui.FragmentOcrScan r4 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                boolean r4 = r4.isAdded()
                                if (r4 == 0) goto L2f
                                com.coveiot.android.ocr.ui.FragmentOcrScan r4 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                com.coveiot.textrecognition.base.ResponseCallback r4 = com.coveiot.android.ocr.ui.FragmentOcrScan.access$getOcrResponseCallback$p(r4)
                                if (r4 == 0) goto L2f
                                com.coveiot.textrecognition.base.OcrError r0 = new com.coveiot.textrecognition.base.OcrError
                                com.coveiot.android.ocr.ui.FragmentOcrScan r1 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                int r2 = com.coveiot.android.ocr.R.string.scan_failed
                                java.lang.String r1 = r1.getString(r2)
                                java.lang.String r2 = "getString(R.string.scan_failed)"
                                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                                r0.<init>(r1)
                                r4.onFailure(r0)
                            L2f:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.ocr.ui.FragmentOcrScan$takePhoto$1$onImageSaved$1.onFailure(com.coveiot.textrecognition.base.OcrError):void");
                        }

                        /* JADX WARN: Code restructure failed: missing block: B:4:0x0012, code lost:
                            r0 = r1.v;
                         */
                        @Override // com.coveiot.textrecognition.base.ResponseCallback
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public void onSuccess(@org.jetbrains.annotations.NotNull com.coveiot.textrecognition.base.BaseOcrResponse r2) {
                            /*
                                r1 = this;
                                java.lang.String r0 = "response"
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                                com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                r0.dismissProgress()
                                com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                boolean r0 = r0.isAdded()
                                if (r0 == 0) goto L1d
                                com.coveiot.android.ocr.ui.FragmentOcrScan r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.this
                                com.coveiot.textrecognition.base.ResponseCallback r0 = com.coveiot.android.ocr.ui.FragmentOcrScan.access$getOcrResponseCallback$p(r0)
                                if (r0 == 0) goto L1d
                                r0.onSuccess(r2)
                            L1d:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.ocr.ui.FragmentOcrScan$takePhoto$1$onImageSaved$1.onSuccess(com.coveiot.textrecognition.base.BaseOcrResponse):void");
                        }
                    });
                }
                baseOcrHelper2 = FragmentOcrScan.this.u;
                if (baseOcrHelper2 != null) {
                    String absolutePath = file.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "photoFile.absolutePath");
                    baseOcrHelper2.detectText(absolutePath);
                }
            }
        });
    }
}
