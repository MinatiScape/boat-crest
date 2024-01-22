package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.DeviceInfoResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.utils.ViewModelFactory;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.gson.Gson;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundEastApex extends BaseFragment implements OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public ActivityWatchFaceViewModel m;
    public WatchFaceLayoutViewModel n;
    public WatchFaceBackgroundViewModel o;
    public int p;
    @Nullable
    public String[] q;
    @Nullable
    public Uri r;
    @Nullable
    public Uri s;
    @Nullable
    public WatchFaceLayoutInfo t;
    @Nullable
    public DeviceInfoResponse u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String v = "FragmentWatchFAceBackgroundEastApex";

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundEastApex newInstance() {
            return new FragmentWatchFaceBackgroundEastApex();
        }
    }

    public static final void A(FragmentWatchFaceBackgroundEastApex this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(0);
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_3)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(8);
    }

    public static final void C(FragmentWatchFaceBackgroundEastApex this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    if (this$0.t == null) {
                        this$0.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    this$0.u(radioButton.getCurrentTextColor());
                    UserDataManager.getInstance(this$0.requireActivity()).saveWatchFaceLayoutSelectedColor(radioButton.getCurrentTextColor());
                    WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo);
                    watchFaceLayoutInfo.setTextColor(Integer.valueOf(radioButton.getCurrentTextColor()));
                    LogHelper.d(this$0.v, "current text color " + radioButton.getCurrentTextColor());
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = null;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this$0.n;
                    if (watchFaceLayoutViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel2 = null;
                    }
                    watchFaceLayoutViewModel2.setWatchFaceLayoutInfo(this$0.t);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                    if (watchFaceLayoutViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                    } else {
                        watchFaceLayoutViewModel = watchFaceLayoutViewModel3;
                    }
                    watchFaceLayoutViewModel.showSaveBtn(true);
                }
            }
        }
    }

    public static final void D(FragmentWatchFaceBackgroundEastApex this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    if (this$0.t == null) {
                        this$0.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    this$0.u(radioButton.getCurrentTextColor());
                    LogHelper.d(this$0.v, "current text color " + radioButton.getCurrentTextColor());
                    UserDataManager.getInstance(this$0.requireActivity()).saveWatchFaceLayoutSelectedColor(radioButton.getCurrentTextColor());
                    WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo);
                    watchFaceLayoutInfo.setTextColor(Integer.valueOf(radioButton.getCurrentTextColor()));
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = null;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this$0.n;
                    if (watchFaceLayoutViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel2 = null;
                    }
                    watchFaceLayoutViewModel2.setWatchFaceLayoutInfo(this$0.t);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                    if (watchFaceLayoutViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                    } else {
                        watchFaceLayoutViewModel = watchFaceLayoutViewModel3;
                    }
                    watchFaceLayoutViewModel.showSaveBtn(true);
                }
            }
        }
    }

    public static final void E(FragmentWatchFaceBackgroundEastApex this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    if (this$0.t == null) {
                        this$0.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    this$0.u(radioButton.getCurrentTextColor());
                    LogHelper.d(this$0.v, "current text color " + radioButton.getCurrentTextColor());
                    UserDataManager.getInstance(this$0.requireActivity()).saveWatchFaceLayoutSelectedColor(radioButton.getCurrentTextColor());
                    WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo);
                    watchFaceLayoutInfo.setTextColor(Integer.valueOf(radioButton.getCurrentTextColor()));
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = null;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this$0.n;
                    if (watchFaceLayoutViewModel2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel2 = null;
                    }
                    watchFaceLayoutViewModel2.setWatchFaceLayoutInfo(this$0.t);
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                    if (watchFaceLayoutViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                    } else {
                        watchFaceLayoutViewModel = watchFaceLayoutViewModel3;
                    }
                    watchFaceLayoutViewModel.showSaveBtn(true);
                }
            }
        }
    }

    public static final void G(FragmentWatchFaceBackgroundEastApex this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_1))) {
                        UserDataManager userDataManager = UserDataManager.getInstance(this$0.requireActivity());
                        int i3 = R.drawable.eastapex_wf_thum_txt_black_240;
                        userDataManager.saveWatchFaceLayoutSelectedStyle(i3);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.o;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setMStylePosition(0);
                        int i4 = R.id.datetimeTextImgView;
                        ((ImageView) this$0._$_findCachedViewById(i4)).setImageResource(i3);
                        UserDataManager.getInstance(this$0.requireActivity()).saveWatchFaceLayoutSelectedColor(ViewCompat.MEASURED_STATE_MASK);
                        ((ImageView) this$0._$_findCachedViewById(i4)).setColorFilter((ColorFilter) null);
                        ((LinearLayout) this$0._$_findCachedViewById(R.id.cv_color_picker)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_2))) {
                        UserDataManager userDataManager2 = UserDataManager.getInstance(this$0.requireActivity());
                        int i5 = R.drawable.eastapex_wf_thum_black_point_240;
                        userDataManager2.saveWatchFaceLayoutSelectedStyle(i5);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.o;
                        if (watchFaceBackgroundViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel3 = null;
                        }
                        watchFaceBackgroundViewModel3.setMStylePosition(2);
                        int i6 = R.id.datetimeTextImgView;
                        ((ImageView) this$0._$_findCachedViewById(i6)).setImageResource(i5);
                        ((LinearLayout) this$0._$_findCachedViewById(R.id.cv_color_picker)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(i6)).setColorFilter((ColorFilter) null);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_3))) {
                        UserDataManager userDataManager3 = UserDataManager.getInstance(this$0.requireActivity());
                        int i7 = R.drawable.eastapex_wf_thum_white_point_240;
                        userDataManager3.saveWatchFaceLayoutSelectedStyle(i7);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this$0.o;
                        if (watchFaceBackgroundViewModel4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel4 = null;
                        }
                        watchFaceBackgroundViewModel4.setMStylePosition(3);
                        int i8 = R.id.datetimeTextImgView;
                        ((ImageView) this$0._$_findCachedViewById(i8)).setImageResource(i7);
                        ((LinearLayout) this$0._$_findCachedViewById(R.id.cv_color_picker)).setVisibility(8);
                        ((ImageView) this$0._$_findCachedViewById(i8)).setColorFilter((ColorFilter) null);
                    }
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this$0.o;
                    if (watchFaceBackgroundViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    } else {
                        watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
                    }
                    watchFaceBackgroundViewModel.showSaveBtn(true);
                }
                this$0.I(radioButton);
            }
        }
    }

    public static final void K(FragmentWatchFaceBackgroundEastApex this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundEastApex newInstance() {
        return Companion.newInstance();
    }

    public static final void x(FragmentWatchFaceBackgroundEastApex this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 350;
        this$0.v();
    }

    public static final void y(FragmentWatchFaceBackgroundEastApex this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 351;
        String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(this$0.requireActivity(), new String[]{"android.permission.CAMERA"});
        this$0.q = checkPermissionsHasGranted;
        Intrinsics.checkNotNull(checkPermissionsHasGranted);
        if (!(checkPermissionsHasGranted.length == 0)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this$0.requireActivity(), "android.permission.CAMERA")) {
                FragmentActivity requireActivity = this$0.requireActivity();
                String[] strArr = this$0.q;
                Intrinsics.checkNotNull(strArr);
                ActivityCompat.requestPermissions(requireActivity, strArr, this$0.p);
                return;
            }
            String string = this$0.getString(R.string.storage_camera_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.stora…mera_permission_required)");
            this$0.J(string);
            return;
        }
        this$0.r = Utils.INSTANCE.takeCameraPictureFromFragment(this$0);
    }

    public static final void z(FragmentWatchFaceBackgroundEastApex this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(8);
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_3)).setVisibility(8);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(8);
    }

    public final void B() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.g0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundEastApex.C(FragmentWatchFaceBackgroundEastApex.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_2)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.f0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundEastApex.D(FragmentWatchFaceBackgroundEastApex.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_3)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.e0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundEastApex.E(FragmentWatchFaceBackgroundEastApex.this, radioGroup, i);
            }
        });
    }

    public final void F() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_wf_style)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.d0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundEastApex.G(FragmentWatchFaceBackgroundEastApex.this, radioGroup, i);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:86:0x01a2, code lost:
        if (r7.getMStylePosition() == 3) goto L91;
     */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void H() {
        /*
            Method dump skipped, instructions count: 1262
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundEastApex.H():void");
    }

    public final void I(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.ic_radio_selected), (Drawable) null, (Drawable) null);
        } else {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.ic_radio_not_selected), (Drawable) null, (Drawable) null);
        }
    }

    public final void J(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundEastApex.K(FragmentWatchFaceBackgroundEastApex.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
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

    public final void initData() {
        if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.leap_call), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView != null) {
                roundedImageView.setVisibility(0);
            }
            CircularImageView circularImageView = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView == null) {
                return;
            }
            circularImageView.setVisibility(8);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.flex_connect), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView2 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView2 != null) {
                roundedImageView2.setVisibility(0);
            }
            CircularImageView circularImageView2 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView2 == null) {
                return;
            }
            circularImageView2.setVisibility(8);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.stride_voice), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView3 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView3 != null) {
                roundedImageView3.setVisibility(0);
            }
            CircularImageView circularImageView3 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView3 == null) {
                return;
            }
            circularImageView3.setVisibility(8);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_call_ace), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            RoundedImageView roundedImageView4 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView4 != null) {
                roundedImageView4.setVisibility(8);
            }
            CircularImageView circularImageView4 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView4 == null) {
                return;
            }
            circularImageView4.setVisibility(0);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_connect_ace), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            RoundedImageView roundedImageView5 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView5 != null) {
                roundedImageView5.setVisibility(8);
            }
            CircularImageView circularImageView5 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView5 == null) {
                return;
            }
            circularImageView5.setVisibility(0);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.primia_ace), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
            RoundedImageView roundedImageView6 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView6 != null) {
                roundedImageView6.setVisibility(8);
            }
            CircularImageView circularImageView6 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView6 == null) {
                return;
            }
            circularImageView6.setVisibility(0);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.xtend_plus), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView7 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView7 != null) {
                roundedImageView7.setVisibility(0);
            }
            CircularImageView circularImageView7 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView7 == null) {
                return;
            }
            circularImageView7.setVisibility(8);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.storm_plus), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView8 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView8 != null) {
                roundedImageView8.setVisibility(0);
            }
            CircularImageView circularImageView8 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView8 == null) {
                return;
            }
            circularImageView8.setVisibility(8);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.cosmos_plus), false)) {
            ((ImageView) _$_findCachedViewById(R.id.eastapex_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView9 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView9 != null) {
                roundedImageView9.setVisibility(0);
            }
            CircularImageView circularImageView9 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView9 == null) {
                return;
            }
            circularImageView9.setVisibility(8);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0115 A[Catch: IOException -> 0x021a, TryCatch #0 {IOException -> 0x021a, blocks: (B:50:0x00e0, B:52:0x00e6, B:55:0x00f7, B:57:0x0109, B:63:0x0115, B:65:0x0119, B:66:0x0140, B:67:0x0148, B:69:0x0167, B:70:0x016b, B:72:0x0172, B:73:0x0176, B:75:0x017d, B:76:0x0181, B:77:0x0184, B:79:0x018e, B:81:0x01a0, B:85:0x01a9, B:87:0x01ad, B:88:0x01d2, B:89:0x01da, B:91:0x01f9, B:92:0x01fd, B:94:0x0204, B:95:0x0208, B:97:0x020f, B:99:0x0214), top: B:104:0x00e0 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0167 A[Catch: IOException -> 0x021a, TryCatch #0 {IOException -> 0x021a, blocks: (B:50:0x00e0, B:52:0x00e6, B:55:0x00f7, B:57:0x0109, B:63:0x0115, B:65:0x0119, B:66:0x0140, B:67:0x0148, B:69:0x0167, B:70:0x016b, B:72:0x0172, B:73:0x0176, B:75:0x017d, B:76:0x0181, B:77:0x0184, B:79:0x018e, B:81:0x01a0, B:85:0x01a9, B:87:0x01ad, B:88:0x01d2, B:89:0x01da, B:91:0x01f9, B:92:0x01fd, B:94:0x0204, B:95:0x0208, B:97:0x020f, B:99:0x0214), top: B:104:0x00e0 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0172 A[Catch: IOException -> 0x021a, TryCatch #0 {IOException -> 0x021a, blocks: (B:50:0x00e0, B:52:0x00e6, B:55:0x00f7, B:57:0x0109, B:63:0x0115, B:65:0x0119, B:66:0x0140, B:67:0x0148, B:69:0x0167, B:70:0x016b, B:72:0x0172, B:73:0x0176, B:75:0x017d, B:76:0x0181, B:77:0x0184, B:79:0x018e, B:81:0x01a0, B:85:0x01a9, B:87:0x01ad, B:88:0x01d2, B:89:0x01da, B:91:0x01f9, B:92:0x01fd, B:94:0x0204, B:95:0x0208, B:97:0x020f, B:99:0x0214), top: B:104:0x00e0 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x017d A[Catch: IOException -> 0x021a, TryCatch #0 {IOException -> 0x021a, blocks: (B:50:0x00e0, B:52:0x00e6, B:55:0x00f7, B:57:0x0109, B:63:0x0115, B:65:0x0119, B:66:0x0140, B:67:0x0148, B:69:0x0167, B:70:0x016b, B:72:0x0172, B:73:0x0176, B:75:0x017d, B:76:0x0181, B:77:0x0184, B:79:0x018e, B:81:0x01a0, B:85:0x01a9, B:87:0x01ad, B:88:0x01d2, B:89:0x01da, B:91:0x01f9, B:92:0x01fd, B:94:0x0204, B:95:0x0208, B:97:0x020f, B:99:0x0214), top: B:104:0x00e0 }] */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r31, int r32, @org.jetbrains.annotations.Nullable android.content.Intent r33) {
        /*
            Method dump skipped, instructions count: 545
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundEastApex.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_east_apex, viewGroup, false);
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
        int i2 = this.p;
        if (i2 == 350) {
            if (!(grantResults.length == 0)) {
                v();
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
                this.r = Utils.INSTANCE.takeCameraPictureFromFragment(this);
            }
        }
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onSaveClicked() {
        if (this.m == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        }
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.m;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.o;
            if (watchFaceBackgroundViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                watchFaceBackgroundViewModel2 = null;
            }
            if (watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri() != null) {
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.o;
                if (watchFaceBackgroundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel3 = null;
                }
                if (watchFaceBackgroundViewModel3.getMStylePosition() != -1) {
                    if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundEastApex$onSaveClicked$1
                            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                            public void onDataError(@NotNull BleBaseError error) {
                                Intrinsics.checkNotNullParameter(error, "error");
                                LogHelper.i("FragmentWatchFaceCloud", "BatteryLevelRequest -- onDataError ");
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
                                    Utils utils = Utils.INSTANCE;
                                    Context requireContext = FragmentWatchFaceBackgroundEastApex.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    if (utils.isRuggedDevice(requireContext)) {
                                        intValue = utils.getBatteryPercentageForMatrix(intValue);
                                    }
                                    LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                    Context requireContext2 = FragmentWatchFaceBackgroundEastApex.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                        FragmentWatchFaceBackgroundEastApex.this.H();
                                        return;
                                    }
                                    FragmentActivity requireActivity = FragmentWatchFaceBackgroundEastApex.this.requireActivity();
                                    FragmentWatchFaceBackgroundEastApex fragmentWatchFaceBackgroundEastApex = FragmentWatchFaceBackgroundEastApex.this;
                                    int i = R.string.make_sure_battery;
                                    StringBuilder sb = new StringBuilder();
                                    Context requireContext3 = FragmentWatchFaceBackgroundEastApex.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                    sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                    sb.append(" %");
                                    Toast.makeText(requireActivity, fragmentWatchFaceBackgroundEastApex.getString(i, sb.toString()), 1).show();
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
                Toast.makeText(requireActivity(), getString(R.string.please_choose_textstyle), 1).show();
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.o;
                if (watchFaceBackgroundViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel4;
                }
                watchFaceBackgroundViewModel.onFail();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.please_chooose_background), 1).show();
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.o;
            if (watchFaceBackgroundViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
            }
            watchFaceBackgroundViewModel.onFail();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this.o;
        if (watchFaceBackgroundViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel6;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceLayoutViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…outViewModel::class.java)");
        this.n = (WatchFaceLayoutViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…undViewModel::class.java)");
        this.o = (WatchFaceBackgroundViewModel) viewModel2;
        FragmentActivity requireActivity5 = requireActivity();
        FragmentActivity requireActivity6 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
        ViewModel viewModel3 = ViewModelProviders.of(requireActivity5, new ViewModelFactory(requireActivity6)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel3, "of(\n            requireA…aceViewModel::class.java)");
        this.m = (ActivityWatchFaceViewModel) viewModel3;
        w();
        F();
        B();
        initData();
        WatchFaceLayoutInfo watchFaceLayoutInfo = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
        this.t = watchFaceLayoutInfo;
        watchFaceLayoutInfo.setTextColor(Integer.valueOf((int) ViewCompat.MEASURED_STATE_MASK));
        String bleDeviceUIInfo = SessionManager.getInstance(requireContext()).getBleDeviceUIInfo();
        boolean z = false;
        if (!(bleDeviceUIInfo == null || bleDeviceUIInfo.length() == 0)) {
            this.u = (DeviceInfoResponse) new Gson().fromJson(SessionManager.getInstance(requireContext()).getBleDeviceUIInfo(), (Class<Object>) DeviceInfoResponse.class);
        }
        if (UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedStyle() > 0) {
            if (UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedStyle() != R.drawable.eastapex_wf_thum_white_point_240 && UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedStyle() != R.drawable.eastapex_wf_thum_black_point_240) {
                t(UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedStyle());
                ((ImageView) _$_findCachedViewById(R.id.datetimeTextImgView)).setColorFilter(UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedColor());
            } else {
                t(UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedStyle());
            }
        }
        try {
            String lastWatchFaceBackgroundUrl = UserDataManager.getInstance(getContext()).getLastWatchFaceBackgroundUrl();
            if (lastWatchFaceBackgroundUrl != null) {
                File file = new File(lastWatchFaceBackgroundUrl);
                String calculateMD5 = Utils.INSTANCE.calculateMD5(file);
                if (calculateMD5 == null || calculateMD5.length() == 0) {
                    z = true;
                }
                if (!z) {
                    if (this.t == null) {
                        this.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    WatchFaceLayoutInfo watchFaceLayoutInfo2 = this.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo2);
                    watchFaceLayoutInfo2.setBackgroundPictureMd5(calculateMD5);
                }
                Uri fromFile = Uri.fromFile(file);
                RoundedImageView roundedImageView = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                if (roundedImageView != null) {
                    roundedImageView.setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                }
                CircularImageView circularImageView = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                if (circularImageView != null) {
                    circularImageView.setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                }
                this.s = fromFile;
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.o;
                if (watchFaceBackgroundViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel = null;
                }
                watchFaceBackgroundViewModel.setSelectedBackgroundImageUri(this.s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void t(int i) {
        int i2 = R.id.datetimeTextImgView;
        ((ImageView) _$_findCachedViewById(i2)).setVisibility(0);
        ((ImageView) _$_findCachedViewById(i2)).setImageResource(i);
    }

    public final void u(int i) {
        int i2 = R.id.datetimeTextImgView;
        ((ImageView) _$_findCachedViewById(i2)).setVisibility(0);
        ((ImageView) _$_findCachedViewById(i2)).setColorFilter(i);
    }

    public final void v() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public final void w() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundEastApex.x(FragmentWatchFaceBackgroundEastApex.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundEastApex.y(FragmentWatchFaceBackgroundEastApex.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownUpLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundEastApex.z(FragmentWatchFaceBackgroundEastApex.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownDownLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundEastApex.A(FragmentWatchFaceBackgroundEastApex.this, view);
            }
        });
    }
}
