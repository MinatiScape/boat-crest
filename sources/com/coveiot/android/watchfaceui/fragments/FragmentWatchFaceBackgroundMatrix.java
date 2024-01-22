package com.coveiot.android.watchfaceui.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.realsil.sdk.dfu.DfuException;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundMatrix extends BaseFragment implements OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public WatchFaceBackgroundViewModel m;
    public String mCurrentPhotoPath;
    public ActivityWatchFaceViewModel n;
    public int o;
    @Nullable
    public String[] p;
    @Nullable
    public Uri s;
    @Nullable
    public Uri t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int q = 1;
    public final int r = 2;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundMatrix newInstance() {
            return new FragmentWatchFaceBackgroundMatrix();
        }
    }

    /* loaded from: classes8.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DeviceType.values().length];
            try {
                iArr[DeviceType.matrix.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DeviceType.WAVEFORCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DeviceType.WAVEFORCE2.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DeviceType.WAVEARMOUR2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DeviceType.LUNARFIT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void A(FragmentWatchFaceBackgroundMatrix this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundMatrix newInstance() {
        return Companion.newInstance();
    }

    public static final void s(FragmentWatchFaceBackgroundMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = 350;
        this$0.p();
    }

    public static final void t(FragmentWatchFaceBackgroundMatrix this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = 351;
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this$0.requireActivity(), new String[]{"android.permission.CAMERA"});
        this$0.p = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (!(checkPermissionsHasGranted.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this$0.requireActivity(), "android.permission.CAMERA")) {
                FragmentActivity requireActivity = this$0.requireActivity();
                String[] strArr = this$0.p;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(requireActivity, strArr, this$0.o);
                return;
            }
            String string = this$0.getString(R.string.storage_camera_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.stora…mera_permission_required)");
            this$0.z(string);
            return;
        }
        this$0.B();
    }

    public static final void v(FragmentWatchFaceBackgroundMatrix this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    int i3 = R.id.parent_text_cl;
                    constraintSet.clone((ConstraintLayout) this$0._$_findCachedViewById(i3));
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_top))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.m;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        } else {
                            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
                        }
                        watchFaceBackgroundViewModel.setMTimePosition(0);
                        int i4 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i4)).getId(), 3, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 3);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i4)).getId(), 6, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 6);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i4)).getId(), 7, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 7);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i4)).getId(), 4);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_bottom))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.m;
                        if (watchFaceBackgroundViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        } else {
                            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
                        }
                        watchFaceBackgroundViewModel.setMTimePosition(1);
                        int i5 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 4, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 4);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 6, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 6);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 7, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 7);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 3);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_left))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this$0.m;
                        if (watchFaceBackgroundViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        } else {
                            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel4;
                        }
                        watchFaceBackgroundViewModel.setMTimePosition(2);
                        int i6 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 6, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 6);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 3, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 3);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 4, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 4);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 7);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.position_right))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this$0.m;
                        if (watchFaceBackgroundViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        } else {
                            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
                        }
                        watchFaceBackgroundViewModel.setMTimePosition(3);
                        int i7 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i7)).getId(), 7, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 7);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i7)).getId(), 3, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 3);
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i7)).getId(), 4, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 4);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i7)).getId(), 6);
                    }
                    constraintSet.applyTo((ConstraintLayout) this$0._$_findCachedViewById(i3));
                }
                this$0.y(radioButton);
            }
        }
    }

    public static final void w(FragmentWatchFaceBackgroundMatrix this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_1))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.m;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setMTextStyle(1);
                        Utils utils = Utils.INSTANCE;
                        Context requireContext = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                        if (utils.isRuggedDevice(requireContext)) {
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.m;
                            if (watchFaceBackgroundViewModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel3 = null;
                            }
                            watchFaceBackgroundViewModel3.setMStylePosition(0);
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.rugged_dial_style1);
                        } else {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.matrix_dial_style1);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_2))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this$0.m;
                        if (watchFaceBackgroundViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel4 = null;
                        }
                        watchFaceBackgroundViewModel4.setMTextStyle(2);
                        Utils utils2 = Utils.INSTANCE;
                        Context requireContext2 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                        if (utils2.isRuggedDevice(requireContext2)) {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.rugged_dial_style2);
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this$0.m;
                            if (watchFaceBackgroundViewModel5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel5 = null;
                            }
                            watchFaceBackgroundViewModel5.setMStylePosition(1);
                        } else {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.matrix_dial_style2);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_3))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this$0.m;
                        if (watchFaceBackgroundViewModel6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel6 = null;
                        }
                        watchFaceBackgroundViewModel6.setMTextStyle(3);
                        Utils utils3 = Utils.INSTANCE;
                        Context requireContext3 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                        if (utils3.isRuggedDevice(requireContext3)) {
                            int i3 = R.id.timeTextImgV;
                            ((ImageView) this$0._$_findCachedViewById(i3)).setImageResource(R.drawable.rugged_dial_style3);
                            if (BleApiManager.getInstance(this$0.getContext()).getDeviceType() == DeviceType.LUNARFIT) {
                                ((ImageView) this$0._$_findCachedViewById(i3)).setImageResource(R.drawable.rugged_dial_style5);
                            }
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel7 = this$0.m;
                            if (watchFaceBackgroundViewModel7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel7 = null;
                            }
                            watchFaceBackgroundViewModel7.setMStylePosition(2);
                        } else {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.matrix_dial_style3);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_4))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel8 = this$0.m;
                        if (watchFaceBackgroundViewModel8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel8 = null;
                        }
                        watchFaceBackgroundViewModel8.setMTextStyle(4);
                        Utils utils4 = Utils.INSTANCE;
                        Context requireContext4 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext4, "requireContext()");
                        if (utils4.isRuggedDevice(requireContext4)) {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.rugged_dial_style4);
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel9 = this$0.m;
                            if (watchFaceBackgroundViewModel9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel9 = null;
                            }
                            watchFaceBackgroundViewModel9.setMStylePosition(3);
                        } else {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.matrix_dial_style4);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_5))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel10 = this$0.m;
                        if (watchFaceBackgroundViewModel10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel10 = null;
                        }
                        watchFaceBackgroundViewModel10.setMTextStyle(5);
                        Utils utils5 = Utils.INSTANCE;
                        Context requireContext5 = this$0.requireContext();
                        Intrinsics.checkNotNullExpressionValue(requireContext5, "requireContext()");
                        if (utils5.isRuggedDevice(requireContext5)) {
                            int i4 = R.id.timeTextImgV;
                            ((ImageView) this$0._$_findCachedViewById(i4)).setImageResource(R.drawable.rugged_dial_style5);
                            if (BleApiManager.getInstance(this$0.getContext()).getDeviceType() == DeviceType.LUNARFIT) {
                                ((ImageView) this$0._$_findCachedViewById(i4)).setImageResource(R.drawable.rugged_dial_style3);
                            }
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel11 = this$0.m;
                            if (watchFaceBackgroundViewModel11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel11 = null;
                            }
                            watchFaceBackgroundViewModel11.setMStylePosition(4);
                        } else {
                            ((ImageView) this$0._$_findCachedViewById(R.id.timeTextImgV)).setImageResource(R.drawable.matrix_dial_style5);
                        }
                    }
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.n;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel12 = this$0.m;
                    if (watchFaceBackgroundViewModel12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    } else {
                        watchFaceBackgroundViewModel = watchFaceBackgroundViewModel12;
                    }
                    watchFaceBackgroundViewModel.showSaveBtn(true);
                }
                this$0.y(radioButton);
            }
        }
    }

    public final void B() {
        File file = null;
        this.s = null;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            try {
                file = q();
            } catch (IOException unused) {
            }
            if (file != null) {
                FragmentActivity requireActivity = requireActivity();
                Uri uriForFile = FileProvider.getUriForFile(requireActivity, requireActivity().getPackageName() + ".provider", file);
                this.s = uriForFile;
                intent.putExtra("output", uriForFile);
                startActivityForResult(intent, this.r);
            }
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
    public final String getMCurrentPhotoPath() {
        String str = this.mCurrentPhotoPath;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mCurrentPhotoPath");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == this.q) {
            if (intent != null) {
                try {
                    Uri data = intent.getData();
                    Utils utils = Utils.INSTANCE;
                    Context requireContext = requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                    if (utils.isRuggedDevice(requireContext)) {
                        DeviceType deviceType = BleApiManager.getInstance(requireContext()).getDeviceType();
                        if ((deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) == 6) {
                            FragmentActivity requireActivity = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                            utils.startUCrop(requireActivity, this, data, 466, 466, 0, 6.0f, 7.0f, true);
                            return;
                        }
                        FragmentActivity requireActivity2 = requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                        utils.startUCrop(requireActivity2, this, data, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 240, 1, 6.0f, 7.0f, true);
                        return;
                    }
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                    utils.startUCrop(requireActivity3, this, data, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (i == this.r) {
            Utils utils2 = Utils.INSTANCE;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            if (utils2.isRuggedDevice(requireContext2)) {
                FragmentActivity requireActivity4 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
                utils2.startUCrop(requireActivity4, this, this.s, DfuException.ERROR_DFU_ENABLE_BUFFER_CHECK_NO_RESPONSE, 240, 1, 6.0f, 7.0f, true);
                return;
            }
            FragmentActivity requireActivity5 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity5, "requireActivity()");
            utils2.startUCrop(requireActivity5, this, this.s, 1);
        } else if (i != 69) {
            super.onActivityResult(i, i2, intent);
        } else if (intent != null) {
            Uri output = UCrop.getOutput(intent);
            this.t = output;
            if (output != null) {
                try {
                    int i3 = R.id.selected_watch_face;
                    if (((RoundedImageView) _$_findCachedViewById(i3)) != null) {
                        ContentResolver contentResolver = requireActivity().getContentResolver();
                        Uri uri = this.t;
                        Intrinsics.checkNotNull(uri);
                        ((RoundedImageView) _$_findCachedViewById(i3)).setImageBitmap(BitmapFactory.decodeStream(contentResolver.openInputStream(uri)));
                        ContentResolver contentResolver2 = requireActivity().getContentResolver();
                        Uri uri2 = this.t;
                        Intrinsics.checkNotNull(uri2);
                        ((CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2)).setImageBitmap(BitmapFactory.decodeStream(contentResolver2.openInputStream(uri2)));
                        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.n;
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                        if (activityWatchFaceViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                            activityWatchFaceViewModel = null;
                        }
                        activityWatchFaceViewModel.setWatchFacePushType(2);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.m;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setSelectedBackgroundImageUri(this.t);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.m;
                        if (watchFaceBackgroundViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        } else {
                            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
                        }
                        watchFaceBackgroundViewModel.showSaveBtn(true);
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getString("param1");
            arguments.getString("param2");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_matrix, viewGroup, false);
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
        int i2 = this.o;
        if (i2 == 350) {
            if (!(grantResults.length == 0)) {
                p();
            }
        } else if (i2 != 351) {
            super.onRequestPermissionsResult(i, permissions, grantResults);
        } else {
            if (!(grantResults.length == 0)) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(requireActivity(), new String[]{"android.permission.CAMERA"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…                        )");
                if (!(checkPermissionsHasGranted.length == 0)) {
                    return;
                }
                B();
            }
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        if (this.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.n;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            Utils utils = Utils.INSTANCE;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            if (utils.isRuggedDevice(requireContext)) {
                if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMatrix$onSaveClicked$1
                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataError(@NotNull BleBaseError error) {
                            Intrinsics.checkNotNullParameter(error, "error");
                            LogHelper.i("FragmentWatchFaceBackgroundMatrix", "BatteryLevelRequest -- onDataError ");
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onDataResponse(@NotNull BleBaseResponse response) {
                            Intrinsics.checkNotNullParameter(response, "response");
                            if (response.getResponseData() instanceof BatteryLevelResponse) {
                                Object responseData = response.getResponseData();
                                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.BatteryLevelResponse");
                                Integer batteryLevel = ((BatteryLevelResponse) responseData).getBatteryLevel();
                                Intrinsics.checkNotNull(batteryLevel);
                                int intValue = batteryLevel.intValue();
                                Utils utils2 = Utils.INSTANCE;
                                int batteryPercentageForMatrix = utils2.getBatteryPercentageForMatrix(intValue);
                                LogHelper.i("FragmentWatchFaceBackgroundMatrix", "batteryLevel -- " + batteryPercentageForMatrix);
                                Context requireContext2 = FragmentWatchFaceBackgroundMatrix.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                if (batteryPercentageForMatrix >= utils2.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                    FragmentWatchFaceBackgroundMatrix.this.x();
                                    return;
                                }
                                FragmentActivity requireActivity = FragmentWatchFaceBackgroundMatrix.this.requireActivity();
                                FragmentWatchFaceBackgroundMatrix fragmentWatchFaceBackgroundMatrix = FragmentWatchFaceBackgroundMatrix.this;
                                int i = R.string.make_sure_battery;
                                StringBuilder sb = new StringBuilder();
                                Context requireContext3 = FragmentWatchFaceBackgroundMatrix.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                sb.append(utils2.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                sb.append(" %");
                                Toast.makeText(requireActivity, fragmentWatchFaceBackgroundMatrix.getString(i, sb.toString()), 1).show();
                            }
                        }

                        @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                        public void onProgressUpdate(@NotNull ProgressData progress) {
                            Intrinsics.checkNotNullParameter(progress, "progress");
                        }
                    });
                    return;
                } else {
                    Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 1).show();
                    return;
                }
            }
            x();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.m;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        DeviceType deviceType = BleApiManager.getInstance(requireContext()).getDeviceType();
        switch (deviceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[deviceType.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                RoundedImageView roundedImageView = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                if (roundedImageView != null) {
                    roundedImageView.setVisibility(0);
                }
                CircularImageView circularImageView = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                if (circularImageView != null) {
                    circularImageView.setVisibility(8);
                    break;
                }
                break;
            case 6:
                ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
                RoundedImageView roundedImageView2 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                if (roundedImageView2 != null) {
                    roundedImageView2.setVisibility(8);
                }
                CircularImageView circularImageView2 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                if (circularImageView2 != null) {
                    circularImageView2.setVisibility(0);
                    break;
                }
                break;
            default:
                ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                RoundedImageView roundedImageView3 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                if (roundedImageView3 != null) {
                    roundedImageView3.setVisibility(0);
                }
                CircularImageView circularImageView3 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                if (circularImageView3 != null) {
                    circularImageView3.setVisibility(8);
                    break;
                }
                break;
        }
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…undViewModel::class.java)");
        this.m = (WatchFaceBackgroundViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.n = (ActivityWatchFaceViewModel) viewModel2;
        r();
        u();
        try {
            String lastWatchFaceBackgroundUrl = UserDataManager.getInstance(getContext()).getLastWatchFaceBackgroundUrl();
            if (lastWatchFaceBackgroundUrl != null) {
                Uri fromFile = Uri.fromFile(new File(lastWatchFaceBackgroundUrl));
                ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                this.t = fromFile;
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.m;
                if (watchFaceBackgroundViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel = null;
                }
                watchFaceBackgroundViewModel.setSelectedBackgroundImageUri(this.t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void p() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), this.q);
    }

    public final File q() throws IOException {
        String formatDate = AppUtils.formatDate(new Date(), "yyyyMMdd_HHmmss");
        File image = File.createTempFile("JPEG_" + formatDate + '_', ".jpg", requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        String absolutePath = image.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "image.absolutePath");
        setMCurrentPhotoPath(absolutePath);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        return image;
    }

    public final void r() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMatrix.s(FragmentWatchFaceBackgroundMatrix.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMatrix.t(FragmentWatchFaceBackgroundMatrix.this, view);
            }
        });
    }

    public final void setMCurrentPhotoPath(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mCurrentPhotoPath = str;
    }

    public final void u() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_time_position)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.r0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMatrix.v(FragmentWatchFaceBackgroundMatrix.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_text_style)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.s0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMatrix.w(FragmentWatchFaceBackgroundMatrix.this, radioGroup, i);
            }
        });
    }

    public final void x() {
        String modelNumber;
        if (this.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.n;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            if (this.m == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            }
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.m;
            if (watchFaceBackgroundViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                watchFaceBackgroundViewModel2 = null;
            }
            if (watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri() != null) {
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.m;
                if (watchFaceBackgroundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel3 = null;
                }
                if (watchFaceBackgroundViewModel3.getMTextStyle() != -1) {
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.m;
                    if (watchFaceBackgroundViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                        watchFaceBackgroundViewModel4 = null;
                    }
                    if (watchFaceBackgroundViewModel4.getMTimePosition() != -1) {
                        AnalyticsLog analyticsLog = new AnalyticsLog();
                        analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
                        DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
                        if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                            DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                            modelNumber = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
                        } else {
                            Utils utils = Utils.INSTANCE;
                            FragmentActivity requireActivity = requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                            modelNumber = utils.getModelNumber(requireActivity);
                        }
                        analyticsLog.setCVPrevScreenName(modelNumber + "_features");
                        analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put(FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue(), "background");
                        analyticsLog.setMapData(hashMap);
                        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.m;
                        if (watchFaceBackgroundViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel5 = null;
                        }
                        watchFaceBackgroundViewModel5.sendMatrixWatchFaceBackgroundToWatch();
                    } else {
                        Toast.makeText(requireActivity(), getString(R.string.please_choose_position), 1).show();
                    }
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this.m;
                    if (watchFaceBackgroundViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    } else {
                        watchFaceBackgroundViewModel = watchFaceBackgroundViewModel6;
                    }
                    watchFaceBackgroundViewModel.onFail();
                    return;
                }
                Toast.makeText(requireActivity(), getString(R.string.please_choose_textstyle), 1).show();
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel7 = this.m;
                if (watchFaceBackgroundViewModel7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel7;
                }
                watchFaceBackgroundViewModel.onFail();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.please_chooose_background), 1).show();
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel8 = this.m;
            if (watchFaceBackgroundViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel8;
            }
            watchFaceBackgroundViewModel.onFail();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel9 = this.m;
        if (watchFaceBackgroundViewModel9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel9;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    public final void y(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_selected), (Drawable) null, (Drawable) null);
        } else {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_unselected), (Drawable) null, (Drawable) null);
        }
    }

    public final void z(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.q0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMatrix.A(FragmentWatchFaceBackgroundMatrix.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }
}
