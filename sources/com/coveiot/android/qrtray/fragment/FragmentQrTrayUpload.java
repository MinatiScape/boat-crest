package com.coveiot.android.qrtray.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.qrtray.ActivityQRTray;
import com.coveiot.android.qrtray.R;
import com.coveiot.android.qrtray.databinding.FragmentQrTrayUploadBinding;
import com.coveiot.android.qrtray.fragment.FragmentQrTraySavingNEditing;
import com.coveiot.android.qrtray.utils.GetGalleryIntentKt;
import com.coveiot.android.qrtray.utils.QRCodeUtils;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessageTwoVerticalBtns;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.zxing.WriterException;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class FragmentQrTrayUpload extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public FragmentQrTrayUploadBinding m;
    @Nullable
    public String n;
    @Nullable
    public BottomSheetDialogImageTitleMessageTwoVerticalBtns o;
    @Nullable
    public BottomSheetDialogImageTitleMessage p;
    public boolean q;
    @Nullable
    public Uri r;
    public boolean t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int s = 11;

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentQrTrayUpload newInstance(boolean z) {
            FragmentQrTrayUpload fragmentQrTrayUpload = new FragmentQrTrayUpload();
            Bundle bundle = new Bundle();
            bundle.putBoolean("addQRCode", z);
            fragmentQrTrayUpload.setArguments(bundle);
            return fragmentQrTrayUpload;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<List<Barcode>, Unit> {

        /* renamed from: com.coveiot.android.qrtray.fragment.FragmentQrTrayUpload$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0300a extends Lambda implements Function1<Integer, Unit> {
            public final /* synthetic */ FragmentQrTrayUpload this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0300a(FragmentQrTrayUpload fragmentQrTrayUpload) {
                super(1);
                this.this$0 = fragmentQrTrayUpload;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                FragmentTransaction beginTransaction = this.this$0.requireActivity().getSupportFragmentManager().beginTransaction();
                int i2 = R.id.qrContainer;
                FragmentQrTraySavingNEditing.Companion companion = FragmentQrTraySavingNEditing.Companion;
                String str = this.this$0.n;
                Intrinsics.checkNotNull(str);
                beginTransaction.replace(i2, companion.newInstance(i, str, false, null, false)).addToBackStack(new FragmentQrTraySavingNEditing().toString()).commit();
            }
        }

        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<Barcode> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<Barcode> barcodes) {
            if (!(barcodes == null || barcodes.isEmpty())) {
                Intrinsics.checkNotNullExpressionValue(barcodes, "barcodes");
                FragmentQrTrayUpload fragmentQrTrayUpload = FragmentQrTrayUpload.this;
                for (Barcode barcode : barcodes) {
                    fragmentQrTrayUpload.n = barcode.getRawValue();
                    HashMap<String, Object> hashMap = new HashMap<>();
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    Context requireContext = fragmentQrTrayUpload.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    hashMap.putAll(companion.getDeviceId(requireContext));
                    Context requireContext2 = fragmentQrTrayUpload.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    hashMap.putAll(companion.getWatchDetails(requireContext2));
                    companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SCAN_SUCCESS.getValue(), hashMap);
                    QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
                    Context requireContext3 = fragmentQrTrayUpload.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                    FragmentActivity requireActivity = fragmentQrTrayUpload.requireActivity();
                    Intrinsics.checkNotNull(requireActivity, "null cannot be cast to non-null type com.coveiot.android.qrtray.ActivityQRTray");
                    qRCodeUtils.getAvailableQRCodeImageId(requireContext3, ((ActivityQRTray) requireActivity).getWatchAndServerQRData().getWatchQrCodes(), new C0300a(fragmentQrTrayUpload));
                }
                return;
            }
            LogHelper.d("qrTrayCheck", "barcodesElse: selected image does not has qr code " + FragmentQrTrayUpload.this.n);
            FragmentQrTrayUpload.this.I();
        }
    }

    public static final void A(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_WATCHDEMOVIDEO.getValue(), hashMap);
    }

    public static final void B(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.requireActivity().getSupportFragmentManager().getBackStackEntryCount() > 1) {
            this$0.requireActivity().getSupportFragmentManager().popBackStack();
        } else {
            this$0.requireActivity().finish();
        }
    }

    public static /* synthetic */ void E(FragmentQrTrayUpload fragmentQrTrayUpload, String str, String str2, Drawable drawable, String str3, String str4, boolean z, int i, Object obj) {
        if ((i & 32) != 0) {
            z = false;
        }
        fragmentQrTrayUpload.D(str, str2, drawable, str3, str4, z);
    }

    public static final void F(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = this$0.o;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns != null) {
            bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
        }
    }

    public static final void G(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            this$0.q = false;
            this$0.startActivityForResult(CropImage.activity(this$0.r).setCropShape(CropImageView.CropShape.RECTANGLE).setAspectRatio(1, 1).getIntent(this$0.requireContext()), 203);
        }
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = this$0.o;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns != null) {
            bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
        }
    }

    public static final void H(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.q) {
            this$0.q = false;
            this$0.C();
        }
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns = this$0.o;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns != null) {
            bottomSheetDialogImageTitleMessageTwoVerticalBtns.dismiss();
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentQrTrayUpload newInstance(boolean z) {
        return Companion.newInstance(z);
    }

    public static final void w(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void x(FragmentQrTrayUpload this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append("addOnFailureListener: selecting qr code addOnFailureListener ");
        String message = it.getMessage();
        if (message == null) {
            message = it.toString();
        }
        sb.append(message);
        LogHelper.e("qrTrayCheck", sb.toString());
        this$0.I();
    }

    public static final void y(final FragmentQrTrayUpload this$0, View view) {
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!AppUtils.isNetConnected(this$0.requireContext())) {
            String string = this$0.getString(R.string.no_internet_available);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet_available)");
            String string2 = this$0.getString(R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
            Drawable drawable = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_no_internet);
            Intrinsics.checkNotNull(drawable);
            String string3 = this$0.getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
            E(this$0, string, string2, drawable, string3, "", false, 32, null);
            return;
        }
        boolean z = true;
        if (BleApiManager.getInstance(this$0.requireContext()).getBleApi() != null && BleApiManager.getInstance(this$0.requireContext()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.C();
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceUtils.Companion companion = DeviceUtils.Companion;
            Context requireContext = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            hashMap.putAll(companion.getDeviceId(requireContext));
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            hashMap.putAll(companion.getWatchDetails(requireContext2));
            if (this$0.t || SessionManager.getInstance(this$0.requireContext()).isQrTrayFtu()) {
                return;
            }
            hashMap.put(CleverTapConstants.CustomEventProperties.UPLOAD_FROM.getValue(), CleverTapConstants.CustomEventValues.ONBOARDING.getValue());
            companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_UPLOAD_REQUEST.getValue(), hashMap);
            SessionManager.getInstance(this$0.requireContext()).setIsQrTrayFtu(true);
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this$0.p;
        if (bottomSheetDialogImageTitleMessage2 != null) {
            if (bottomSheetDialogImageTitleMessage2 == null || !bottomSheetDialogImageTitleMessage2.isShowing()) {
                z = false;
            }
            if (z && (bottomSheetDialogImageTitleMessage = this$0.p) != null) {
                bottomSheetDialogImageTitleMessage.dismiss();
            }
            this$0.p = null;
        }
        if (this$0.p == null) {
            Context requireContext3 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            Drawable drawable2 = AppCompatResources.getDrawable(this$0.requireContext(), R.drawable.ic_red_circular_not_connected);
            Intrinsics.checkNotNull(drawable2);
            String string4 = this$0.getString(R.string.your_watch_disconnected);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.your_watch_disconnected)");
            String string5 = this$0.getString(R.string.please_connect_your_watch_in_order_to_apply_the_qr_code);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.pleas…der_to_apply_the_qr_code)");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = new BottomSheetDialogImageTitleMessage(requireContext3, drawable2, string4, string5, false);
            this$0.p = bottomSheetDialogImageTitleMessage3;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
            bottomSheetDialogImageTitleMessage3.showBigIcon();
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this$0.p;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
            String string6 = this$0.getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.okay)");
            bottomSheetDialogImageTitleMessage4.setPositiveButton(string6, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.d0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    FragmentQrTrayUpload.z(FragmentQrTrayUpload.this, view2);
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
        if (bottomSheetDialogImageTitleMessage5.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
        bottomSheetDialogImageTitleMessage6.show();
    }

    public static final void z(FragmentQrTrayUpload this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.p;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
    }

    public final void C() {
        startActivityForResult(GetGalleryIntentKt.getGalleryIntent(), this.s);
    }

    public final void D(String str, String str2, Drawable drawable, String str3, String str4, boolean z) {
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns;
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns2;
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns3;
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns4;
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns5 = this.o;
        boolean z2 = true;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns5 != null) {
            if ((bottomSheetDialogImageTitleMessageTwoVerticalBtns5 != null && bottomSheetDialogImageTitleMessageTwoVerticalBtns5.isShowing()) && (bottomSheetDialogImageTitleMessageTwoVerticalBtns4 = this.o) != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns4.dismiss();
            }
            this.o = null;
        }
        if (this.o == null) {
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            this.o = new BottomSheetDialogImageTitleMessageTwoVerticalBtns(requireActivity, drawable, str, str2);
            if ((str4.length() == 0) && (bottomSheetDialogImageTitleMessageTwoVerticalBtns3 = this.o) != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns3.hideNegativeButton();
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns6 = this.o;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns6 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns6.showBigIcon();
            }
            if (z && (bottomSheetDialogImageTitleMessageTwoVerticalBtns2 = this.o) != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns2.showCrossIcon();
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns7 = this.o;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns7 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns7.setPositiveButton(str3, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.h0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTrayUpload.G(FragmentQrTrayUpload.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns8 = this.o;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns8 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns8.setNegativeButton(str4, new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.j0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTrayUpload.H(FragmentQrTrayUpload.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns9 = this.o;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns9 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns9.setCrossButton(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.e0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentQrTrayUpload.F(FragmentQrTrayUpload.this, view);
                    }
                });
            }
            BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns10 = this.o;
            if (bottomSheetDialogImageTitleMessageTwoVerticalBtns10 != null) {
                bottomSheetDialogImageTitleMessageTwoVerticalBtns10.setCancelable(false);
            }
        }
        BottomSheetDialogImageTitleMessageTwoVerticalBtns bottomSheetDialogImageTitleMessageTwoVerticalBtns11 = this.o;
        if (bottomSheetDialogImageTitleMessageTwoVerticalBtns11 == null || bottomSheetDialogImageTitleMessageTwoVerticalBtns11.isShowing()) {
            z2 = false;
        }
        if (!z2 || (bottomSheetDialogImageTitleMessageTwoVerticalBtns = this.o) == null) {
            return;
        }
        bottomSheetDialogImageTitleMessageTwoVerticalBtns.show();
    }

    public final void I() {
        this.q = true;
        HashMap<String, Object> hashMap = new HashMap<>();
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        hashMap.putAll(companion.getDeviceId(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        hashMap.putAll(companion.getWatchDetails(requireContext2));
        hashMap.put(CleverTapConstants.CustomEventProperties.SCAN_FAILED_REASON.getValue(), CleverTapConstants.CustomEventValues.SYSTEM_UNABLE_TO_DETECT.getValue());
        companion.logAnalyticsEvent(CleverTapConstants.EventName.BC_QR_SCAN_FAILED.getValue(), hashMap);
        String string = getString(R.string.error);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.error)");
        String string2 = getString(R.string.unable_to_scan_qr_code);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.unable_to_scan_qr_code)");
        Drawable drawable = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_red_circular_cross);
        Intrinsics.checkNotNull(drawable);
        String string3 = getString(R.string.scan_manually);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.scan_manually)");
        String string4 = getString(R.string.retry);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.retry)");
        D(string, string2, drawable, string3, string4, true);
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

    public final boolean isFromAddQR() {
        return this.t;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != this.s) {
            if (i != 203) {
                LogHelper.d("FragmentQrTrayUpload", "else");
            } else if (i2 == -1) {
                this.r = CropImage.getActivityResult(intent).getUri();
                t();
            }
        } else if (intent != null) {
            try {
                this.r = intent.getData();
                t();
            } catch (Exception e) {
                LogHelper.d("FragmentQrTrayUpload", "onActivityResultException: " + e.getMessage());
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.t = arguments.getBoolean("addQRCode");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentQrTrayUploadBinding inflate = FragmentQrTrayUploadBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, container, false)");
        this.m = inflate;
        View root = u().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentQrTrayUploadBinding u = u();
        u.btnUpload.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQrTrayUpload.y(FragmentQrTrayUpload.this, view2);
            }
        });
        u.tvWatchVideo.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.f0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQrTrayUpload.A(FragmentQrTrayUpload.this, view2);
            }
        });
        ((TextView) u().toolbar.findViewById(R.id.toolbar_title)).setText(getString(R.string.qr_tray));
        ((TextView) u().toolbar.findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.qrtray.fragment.g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentQrTrayUpload.B(FragmentQrTrayUpload.this, view2);
            }
        });
    }

    public final void setFromAddQR(boolean z) {
        this.t = z;
    }

    public final void t() {
        QRCodeUtils qRCodeUtils = QRCodeUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Uri uri = this.r;
        Intrinsics.checkNotNull(uri);
        if (qRCodeUtils.getImageFormat(requireContext, uri) != null) {
            Bitmap qrCodeBitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), this.r);
            Intrinsics.checkNotNullExpressionValue(qrCodeBitmap, "qrCodeBitmap");
            v(qrCodeBitmap);
            return;
        }
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        String string = getString(R.string.format_not_supported);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.format_not_supported)");
        toast(requireContext2, string);
    }

    public final FragmentQrTrayUploadBinding u() {
        FragmentQrTrayUploadBinding fragmentQrTrayUploadBinding = this.m;
        if (fragmentQrTrayUploadBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentQrTrayUploadBinding;
    }

    public final void v(Bitmap bitmap) {
        try {
            BarcodeScannerOptions build = new BarcodeScannerOptions.Builder().setBarcodeFormats(0, new int[0]).build();
            Intrinsics.checkNotNullExpressionValue(build, "Builder().setBarcodeForm…                 .build()");
            InputImage fromBitmap = InputImage.fromBitmap(bitmap, 0);
            Intrinsics.checkNotNullExpressionValue(fromBitmap, "fromBitmap(qrCodeBitmap, 0)");
            BarcodeScanner client = BarcodeScanning.getClient(build);
            Intrinsics.checkNotNullExpressionValue(client, "getClient(options)");
            Task<List<Barcode>> process = client.process(fromBitmap);
            final a aVar = new a();
            process.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.qrtray.fragment.l0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    FragmentQrTrayUpload.w(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.qrtray.fragment.k0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    FragmentQrTrayUpload.x(FragmentQrTrayUpload.this, exc);
                }
            });
        } catch (WriterException unused) {
            LogHelper.d("qrTrayCheck", "WriterException: selected image does not has qr code " + this.n);
        } catch (Exception e) {
            LogHelper.d("qrTrayCheck", "Exception: selected image does not has qr code " + this.n);
            I();
            e.printStackTrace();
        }
    }
}
