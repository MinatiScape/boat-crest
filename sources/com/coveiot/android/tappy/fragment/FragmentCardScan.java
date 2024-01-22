package com.coveiot.android.tappy.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.ocr.utils.OcrUtilsKt;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.activity.AddPaymentCardActivity;
import com.coveiot.android.tappy.databinding.FragmentScanCardBinding;
import com.coveiot.android.tappy.utils.CardReaderHelper;
import com.coveiot.android.tappy.utils.Constants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.textrecognition.OcrConstants;
import com.coveiot.textrecognition.base.BaseOcrHelper;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.OcrError;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.utils.utility.LogHelper;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class FragmentCardScan extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public FragmentScanCardBinding n;
    public long o;
    @Nullable
    public ImageCapture r;
    @Nullable
    public BaseOcrHelper s;
    @Nullable
    public String t;
    @Nullable
    public String u;
    @Nullable
    public String v;
    @Nullable
    public String w;
    @Nullable
    public String x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentCardScan";
    public final int p = 10;
    @NotNull
    public final String[] q = {"android.permission.CAMERA"};

    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentCardScan newInstance(@NotNull String param1, @NotNull String param2) {
            Intrinsics.checkNotNullParameter(param1, "param1");
            Intrinsics.checkNotNullParameter(param2, "param2");
            FragmentCardScan fragmentCardScan = new FragmentCardScan();
            Bundle bundle = new Bundle();
            bundle.putString("param1", param1);
            bundle.putString("param2", param2);
            fragmentCardScan.setArguments(bundle);
            return fragmentCardScan;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentCardScan newInstance(@NotNull String str, @NotNull String str2) {
        return Companion.newInstance(str, str2);
    }

    public static final void q(FragmentCardScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (SystemClock.elapsedRealtime() - this$0.o > 1000) {
            this$0.o = SystemClock.elapsedRealtime();
            if (this$0.p()) {
                this$0.x();
            }
        }
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_CARD_SCAN_REQUEST.getValue(), null);
    }

    public static final void r(FragmentCardScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_CARDMANUALENTRY.getValue(), null);
        if (this$0.getActivity() instanceof AddPaymentCardActivity) {
            FragmentActivity activity = this$0.getActivity();
            Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.coveiot.android.tappy.activity.AddPaymentCardActivity");
            AddPaymentCardActivity.loadCardDetailsFragment$default((AddPaymentCardActivity) activity, null, null, null, null, null, 31, null);
        }
    }

    public static final void s(FragmentCardScan this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DeviceUtils.Companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_PAY_SUPPORTISSUER_TAPPED.getValue(), null);
        Intent intent = new Intent(this$0.requireContext(), AddPaymentCardActivity.class);
        intent.putExtra(Constants.VIEW_SUPPORTED_ISSUERS, true);
        this$0.startActivity(intent);
    }

    public static final void w(ListenableFuture cameraProviderFuture, FragmentCardScan this$0) {
        Intrinsics.checkNotNullParameter(cameraProviderFuture, "$cameraProviderFuture");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        V v = cameraProviderFuture.get();
        Intrinsics.checkNotNullExpressionValue(v, "cameraProviderFuture.get()");
        ProcessCameraProvider processCameraProvider = (ProcessCameraProvider) v;
        Preview build = new Preview.Builder().build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n                .build()");
        this$0.r = new ImageCapture.Builder().setCaptureMode(0).build();
        CameraSelector build2 = new CameraSelector.Builder().requireLensFacing(1).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder().requireLensFac…LENS_FACING_BACK).build()");
        try {
            processCameraProvider.unbindAll();
            build.setSurfaceProvider(this$0.o().previewCamera1.getSurfaceProvider());
            Intrinsics.checkNotNullExpressionValue(processCameraProvider.bindToLifecycle(this$0, build2, build, this$0.r), "cameraProvider.bindToLif…Capture\n                )");
        } catch (Exception e) {
            if (this$0.isAdded()) {
                Toast.makeText(this$0.requireActivity(), this$0.getString(R.string.scan_failed), 0).show();
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

    @NotNull
    public final String getTAG() {
        return this.m;
    }

    public final FragmentScanCardBinding o() {
        FragmentScanCardBinding fragmentScanCardBinding = this.n;
        Intrinsics.checkNotNull(fragmentScanCardBinding);
        return fragmentScanCardBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogHelper.d(this.m, "onActivityResult");
        if (i == this.p) {
            startCameraByCheckingPermission();
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
        CardReaderHelper.Companion companion = CardReaderHelper.Companion;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.s = companion.getInstance(requireActivity);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.n = FragmentScanCardBinding.inflate(inflater, viewGroup, false);
        return o().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        dismissProgress();
        super.onDestroy();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        LogHelper.d(this.m, "onRequestPermissionsResult");
        if (i == this.p) {
            startCameraByCheckingPermission();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        o().btnCapture.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCardScan.q(FragmentCardScan.this, view2);
            }
        });
        o().btnAddManuVally.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCardScan.r(FragmentCardScan.this, view2);
            }
        });
        startCameraByCheckingPermission();
        o().tvSupportedIssuers.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCardScan.s(FragmentCardScan.this, view2);
            }
        });
    }

    public final boolean p() {
        if (OcrUtilsKt.checkIfCameraPermissionExists(getActivity())) {
            return true;
        }
        if (shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
            t();
            return false;
        }
        requestPermissions(this.q, this.p);
        return false;
    }

    public final void startCameraByCheckingPermission() {
        if (p()) {
            v();
        }
    }

    public final void t() {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        String string = getString(com.coveiot.android.ocr.R.string.camera_permission_content);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…amera_permission_content)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(requireContext, string);
        String string2 = getString(com.coveiot.android.ocr.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(com.coveiot.android.ocr.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.tappy.fragment.FragmentCardScan$showCameraPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                int i;
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                FragmentCardScan fragmentCardScan = this;
                i = fragmentCardScan.p;
                OcrUtilsKt.openAppSettings(fragmentCardScan, i);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void u(boolean z) {
        if (z) {
            o().scnPgBar.setVisibility(0);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(o().scnPgBar, Key.ROTATION, 0.0f, 360.0f);
            ofFloat.setDuration(com.clevertap.android.sdk.Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
            ofFloat.setRepeatCount(-1);
            ofFloat.setInterpolator(new LinearInterpolator());
            ofFloat.start();
            return;
        }
        o().scnPgBar.setVisibility(8);
    }

    public final void v() {
        final ListenableFuture<ProcessCameraProvider> processCameraProvider = ProcessCameraProvider.getInstance(requireActivity());
        Intrinsics.checkNotNullExpressionValue(processCameraProvider, "getInstance(requireActivity())");
        processCameraProvider.addListener(new Runnable() { // from class: com.coveiot.android.tappy.fragment.u
            @Override // java.lang.Runnable
            public final void run() {
                FragmentCardScan.w(ListenableFuture.this, this);
            }
        }, ContextCompat.getMainExecutor(requireActivity()));
    }

    public final void x() {
        ImageCapture imageCapture;
        if (isAdded() && (imageCapture = this.r) != null) {
            FragmentActivity activity = getActivity();
            final File file = new File(activity != null ? activity.getFilesDir() : null, OcrConstants.INSTANCE.getFILE_NAME());
            ImageCapture.OutputFileOptions build = new ImageCapture.OutputFileOptions.Builder(file).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder(photoFile).build()");
            u(true);
            imageCapture.a0(build, ContextCompat.getMainExecutor(requireActivity()), new ImageCapture.OnImageSavedCallback() { // from class: com.coveiot.android.tappy.fragment.FragmentCardScan$takePhoto$1
                @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
                public void onError(@NotNull ImageCaptureException exc) {
                    Intrinsics.checkNotNullParameter(exc, "exc");
                    if (FragmentCardScan.this.isAdded()) {
                        FragmentCardScan.this.u(false);
                        Toast.makeText(FragmentCardScan.this.requireActivity(), FragmentCardScan.this.getString(R.string.scan_failed), 0).show();
                    }
                    String tag = FragmentCardScan.this.getTAG();
                    LogHelper.e(tag, "Photo capture failed: " + exc.getMessage(), exc);
                }

                @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
                public void onImageSaved(@NotNull ImageCapture.OutputFileResults output) {
                    BaseOcrHelper baseOcrHelper;
                    BaseOcrHelper baseOcrHelper2;
                    Intrinsics.checkNotNullParameter(output, "output");
                    if (FragmentCardScan.this.isAdded()) {
                        FragmentCardScan.this.u(false);
                    }
                    baseOcrHelper = FragmentCardScan.this.s;
                    if (baseOcrHelper != null) {
                        final FragmentCardScan fragmentCardScan = FragmentCardScan.this;
                        final File file2 = file;
                        baseOcrHelper.setResponseCallback(new ResponseCallback<BaseOcrResponse>() { // from class: com.coveiot.android.tappy.fragment.FragmentCardScan$takePhoto$1$onImageSaved$1
                            @Override // com.coveiot.textrecognition.base.ResponseCallback
                            public void onFailure(@NotNull OcrError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                FragmentCardScan.this.dismissProgress();
                                if (FragmentCardScan.this.isAdded()) {
                                    Toast.makeText(FragmentCardScan.this.requireActivity(), FragmentCardScan.this.getString(R.string.scan_failed), 0).show();
                                }
                            }

                            /* JADX WARN: Code restructure failed: missing block: B:102:0x015f, code lost:
                                if ((r8 == null || r8.length() == 0) != false) goto L91;
                             */
                            /* JADX WARN: Code restructure failed: missing block: B:54:0x00e7, code lost:
                                if ((r8 == null || r8.length() == 0) != false) goto L52;
                             */
                            /* JADX WARN: Code restructure failed: missing block: B:70:0x010f, code lost:
                                if ((r8 == null || r8.length() == 0) != false) goto L65;
                             */
                            /* JADX WARN: Code restructure failed: missing block: B:86:0x0137, code lost:
                                if ((r8 == null || r8.length() == 0) != false) goto L78;
                             */
                            @Override // com.coveiot.textrecognition.base.ResponseCallback
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public void onSuccess(@org.jetbrains.annotations.NotNull com.coveiot.textrecognition.base.BaseOcrResponse r8) {
                                /*
                                    Method dump skipped, instructions count: 532
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.tappy.fragment.FragmentCardScan$takePhoto$1$onImageSaved$1.onSuccess(com.coveiot.textrecognition.base.BaseOcrResponse):void");
                            }
                        });
                    }
                    baseOcrHelper2 = FragmentCardScan.this.s;
                    if (baseOcrHelper2 != null) {
                        String absolutePath = file.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath, "photoFile.absolutePath");
                        baseOcrHelper2.detectText(absolutePath);
                    }
                }
            });
        }
    }
}
