package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
import android.graphics.PorterDuff;
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
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.ZipUtils;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.BatteryLevelRequest;
import com.coveiot.android.bleabstract.response.BatteryLevelResponse;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.model.TouchDialConfig;
import com.coveiot.android.watchfaceui.model.TouchDialStyle;
import com.coveiot.android.watchfaceui.utils.Utils;
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
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundTouchElx extends BaseFragment implements OnClickListener {
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
    public int u;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int v = 368;
    public int w = 448;
    @NotNull
    public String x = "oy50";
    @NotNull
    public final List<TouchDialStyle> y = new ArrayList();
    public float z = 23.0f;
    public float A = 28.0f;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundTouchElx newInstance() {
            return new FragmentWatchFaceBackgroundTouchElx();
        }
    }

    public static final void A(FragmentWatchFaceBackgroundTouchElx this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(8);
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_3)).setVisibility(8);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(8);
    }

    public static final void B(FragmentWatchFaceBackgroundTouchElx this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(0);
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_3)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(8);
    }

    public static final void D(FragmentWatchFaceBackgroundTouchElx this$0, RadioGroup radioGroup, int i) {
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
                    this$0.t(radioButton.getCurrentTextColor());
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

    public static final void E(FragmentWatchFaceBackgroundTouchElx this$0, RadioGroup radioGroup, int i) {
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
                    this$0.t(radioButton.getCurrentTextColor());
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

    public static final void F(FragmentWatchFaceBackgroundTouchElx this$0, RadioGroup radioGroup, int i) {
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
                    this$0.t(radioButton.getCurrentTextColor());
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

    public static final void G(FragmentWatchFaceBackgroundTouchElx this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_1))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.o;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setMStylePosition(1);
                        if (kotlin.text.m.equals(SessionManager.getInstance(this$0.getContext()).getDeviceType(), this$0.requireContext().getResources().getString(R.string.wave_neo), false)) {
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.o;
                            if (watchFaceBackgroundViewModel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel3 = null;
                            }
                            watchFaceBackgroundViewModel3.setMTextStyle(10);
                            ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_10);
                        } else {
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this$0.o;
                            if (watchFaceBackgroundViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel4 = null;
                            }
                            watchFaceBackgroundViewModel4.setMTextStyle(1);
                            ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_1);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_2))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this$0.o;
                        if (watchFaceBackgroundViewModel5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel5 = null;
                        }
                        watchFaceBackgroundViewModel5.setMStylePosition(3);
                        if (kotlin.text.m.equals(SessionManager.getInstance(this$0.getContext()).getDeviceType(), this$0.requireContext().getResources().getString(R.string.wave_neo), false)) {
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this$0.o;
                            if (watchFaceBackgroundViewModel6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel6 = null;
                            }
                            watchFaceBackgroundViewModel6.setMTextStyle(11);
                            ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_11);
                        } else {
                            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel7 = this$0.o;
                            if (watchFaceBackgroundViewModel7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                                watchFaceBackgroundViewModel7 = null;
                            }
                            watchFaceBackgroundViewModel7.setMTextStyle(3);
                            ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_3);
                        }
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_3))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel8 = this$0.o;
                        if (watchFaceBackgroundViewModel8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel8 = null;
                        }
                        watchFaceBackgroundViewModel8.setMStylePosition(4);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel9 = this$0.o;
                        if (watchFaceBackgroundViewModel9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel9 = null;
                        }
                        watchFaceBackgroundViewModel9.setMTextStyle(4);
                        ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_4);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_4))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel10 = this$0.o;
                        if (watchFaceBackgroundViewModel10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel10 = null;
                        }
                        watchFaceBackgroundViewModel10.setMStylePosition(5);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel11 = this$0.o;
                        if (watchFaceBackgroundViewModel11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel11 = null;
                        }
                        watchFaceBackgroundViewModel11.setMTextStyle(5);
                        ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_5);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.textstyle_5))) {
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel12 = this$0.o;
                        if (watchFaceBackgroundViewModel12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel12 = null;
                        }
                        watchFaceBackgroundViewModel12.setMStylePosition(7);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel13 = this$0.o;
                        if (watchFaceBackgroundViewModel13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel13 = null;
                        }
                        watchFaceBackgroundViewModel13.setMTextStyle(7);
                        ((ImageView) this$0._$_findCachedViewById(R.id.datetimeTextImgView)).setImageResource(R.drawable.touch_style_7);
                    }
                    ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                    if (activityWatchFaceViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                        activityWatchFaceViewModel = null;
                    }
                    activityWatchFaceViewModel.setWatchFacePushType(2);
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel14 = this$0.o;
                    if (watchFaceBackgroundViewModel14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    } else {
                        watchFaceBackgroundViewModel = watchFaceBackgroundViewModel14;
                    }
                    watchFaceBackgroundViewModel.showSaveBtn(true);
                }
                this$0.I(radioButton);
            }
        }
    }

    public static final void K(FragmentWatchFaceBackgroundTouchElx this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundTouchElx newInstance() {
        return Companion.newInstance();
    }

    public static final void y(FragmentWatchFaceBackgroundTouchElx this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 350;
        this$0.u();
    }

    public static final void z(FragmentWatchFaceBackgroundTouchElx this$0, View view) {
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

    public final void C() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.x1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundTouchElx.D(FragmentWatchFaceBackgroundTouchElx.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_2)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.z1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundTouchElx.E(FragmentWatchFaceBackgroundTouchElx.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_3)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.w1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundTouchElx.F(FragmentWatchFaceBackgroundTouchElx.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_text_style)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.y1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundTouchElx.G(FragmentWatchFaceBackgroundTouchElx.this, radioGroup, i);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void H() {
        /*
            Method dump skipped, instructions count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx.H():void");
    }

    public final void I(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_selected), (Drawable) null, (Drawable) null);
        } else {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_unselected), (Drawable) null, (Drawable) null);
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
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundTouchElx.K(FragmentWatchFaceBackgroundTouchElx.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }

    public final boolean L() {
        File v = v();
        if (v == null) {
            return false;
        }
        return M(v);
    }

    public final boolean M(File file) {
        try {
            ZipUtils.unzipFile(file, new File(requireContext().getCacheDir(), "tmp"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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

    public final void initData() {
        if (!kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.wave_call_plus), false) && !kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.wave_connect_plus), false)) {
            if (!kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_call), false) && !kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_connect), false)) {
                if (!kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_call_plus), false) && !kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.lunar_connect_plus), false)) {
                    if (!kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.xtend_call_plus), false) && !kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.storm_connect_plus), false)) {
                        if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.wave_neo), false)) {
                            this.x = "oy22";
                            this.z = 9.0f;
                            this.A = 10.0f;
                            ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                            RoundedImageView roundedImageView = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                            if (roundedImageView != null) {
                                roundedImageView.setVisibility(0);
                            }
                            CircularImageView circularImageView = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                            if (circularImageView != null) {
                                circularImageView.setVisibility(8);
                            }
                            ((LinearLayout) _$_findCachedViewById(R.id.cv_color_picker)).setVisibility(8);
                            ((RadioButton) _$_findCachedViewById(R.id.rb_textstyle_3)).setVisibility(8);
                            ((RadioButton) _$_findCachedViewById(R.id.rb_textstyle_4)).setVisibility(8);
                            ((RadioButton) _$_findCachedViewById(R.id.rb_textstyle_5)).setVisibility(8);
                            int i = R.id.datetimeTextImgView;
                            ((ImageView) _$_findCachedViewById(i)).setImageResource(R.drawable.touch_style_10);
                            ((ImageView) _$_findCachedViewById(i)).setColorFilter(ContextCompat.getColor(requireContext(), R.color.color_ffffff), PorterDuff.Mode.MULTIPLY);
                            if (this.t == null) {
                                this.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                            }
                            UserDataManager.getInstance(requireActivity()).saveWatchFaceLayoutSelectedColor(-1);
                            WatchFaceLayoutInfo watchFaceLayoutInfo = this.t;
                            Intrinsics.checkNotNull(watchFaceLayoutInfo);
                            watchFaceLayoutInfo.setTextColor(-1);
                            ActivityWatchFaceViewModel activityWatchFaceViewModel = this.m;
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel = null;
                            if (activityWatchFaceViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                                activityWatchFaceViewModel = null;
                            }
                            activityWatchFaceViewModel.setWatchFacePushType(2);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this.n;
                            if (watchFaceLayoutViewModel2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            } else {
                                watchFaceLayoutViewModel = watchFaceLayoutViewModel2;
                            }
                            watchFaceLayoutViewModel.setWatchFaceLayoutInfo(this.t);
                        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.wave_magma), false)) {
                            this.x = "k622b";
                            this.z = 6.0f;
                            this.A = 7.0f;
                            ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                            RoundedImageView roundedImageView2 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                            if (roundedImageView2 != null) {
                                roundedImageView2.setVisibility(0);
                            }
                            CircularImageView circularImageView2 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                            if (circularImageView2 != null) {
                                circularImageView2.setVisibility(8);
                            }
                        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getString(R.string.lunar_embrace), false)) {
                            this.x = "oy82";
                            this.z = 1.0f;
                            this.A = 1.0f;
                            ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
                            RoundedImageView roundedImageView3 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                            if (roundedImageView3 != null) {
                                roundedImageView3.setVisibility(8);
                            }
                            CircularImageView circularImageView3 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                            if (circularImageView3 != null) {
                                circularImageView3.setVisibility(0);
                            }
                        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getString(R.string.wave_spectra), false)) {
                            this.x = "oy80";
                            this.z = 23.0f;
                            this.A = 28.0f;
                            ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                            RoundedImageView roundedImageView4 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                            if (roundedImageView4 != null) {
                                roundedImageView4.setVisibility(0);
                            }
                            CircularImageView circularImageView4 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                            if (circularImageView4 != null) {
                                circularImageView4.setVisibility(8);
                            }
                        }
                    } else {
                        this.x = "k602l";
                        this.z = 6.0f;
                        this.A = 7.0f;
                        ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
                        RoundedImageView roundedImageView5 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                        if (roundedImageView5 != null) {
                            roundedImageView5.setVisibility(0);
                        }
                        CircularImageView circularImageView5 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                        if (circularImageView5 != null) {
                            circularImageView5.setVisibility(8);
                        }
                    }
                } else {
                    this.x = "oy70";
                    this.z = 1.0f;
                    this.A = 1.0f;
                    ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
                    RoundedImageView roundedImageView6 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                    if (roundedImageView6 != null) {
                        roundedImageView6.setVisibility(8);
                    }
                    CircularImageView circularImageView6 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                    if (circularImageView6 != null) {
                        circularImageView6.setVisibility(0);
                    }
                }
            } else {
                this.x = "k802l";
                this.z = 1.0f;
                this.A = 1.0f;
                ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
                RoundedImageView roundedImageView7 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
                if (roundedImageView7 != null) {
                    roundedImageView7.setVisibility(8);
                }
                CircularImageView circularImageView7 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
                if (circularImageView7 != null) {
                    circularImageView7.setVisibility(0);
                }
            }
        } else {
            this.x = "oy50";
            this.z = 23.0f;
            this.A = 28.0f;
            ((ImageView) _$_findCachedViewById(R.id.touch_watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
            RoundedImageView roundedImageView8 = (RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face);
            if (roundedImageView8 != null) {
                roundedImageView8.setVisibility(0);
            }
            CircularImageView circularImageView8 = (CircularImageView) _$_findCachedViewById(R.id.selected_watch_face2);
            if (circularImageView8 != null) {
                circularImageView8.setVisibility(8);
            }
        }
        for (TouchDialConfig touchDialConfig : (List) GsonUtils.fromJson(ResourceUtils.readAssets2String("touchwatchface/config.json"), new TypeToken<ArrayList<TouchDialConfig>>() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx$initData$dialConfigList$1
        }.getType())) {
            if (kotlin.text.m.equals(touchDialConfig.getProject(), this.x, true)) {
                this.u = touchDialConfig.getShape();
                this.v = touchDialConfig.getWidth();
                this.w = touchDialConfig.getHeight();
                touchDialConfig.getCorner();
                touchDialConfig.getDefault_bg();
                List<Integer> style = touchDialConfig.getStyle();
                Intrinsics.checkNotNull(style);
                for (Integer num : style) {
                    this.y.add(new TouchDialStyle(num.intValue()));
                }
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00ae A[Catch: IOException -> 0x01b5, TryCatch #0 {IOException -> 0x01b5, blocks: (B:26:0x007a, B:28:0x0080, B:31:0x0090, B:33:0x00a2, B:39:0x00ae, B:41:0x00b2, B:42:0x00d9, B:43:0x00e1, B:45:0x0100, B:46:0x0104, B:48:0x010b, B:49:0x010f, B:51:0x0116, B:52:0x011a, B:53:0x011d, B:55:0x0127, B:57:0x0139, B:61:0x0142, B:63:0x0146, B:64:0x016d, B:65:0x0175, B:67:0x0194, B:68:0x0198, B:70:0x019f, B:71:0x01a3, B:73:0x01aa, B:75:0x01af), top: B:80:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0100 A[Catch: IOException -> 0x01b5, TryCatch #0 {IOException -> 0x01b5, blocks: (B:26:0x007a, B:28:0x0080, B:31:0x0090, B:33:0x00a2, B:39:0x00ae, B:41:0x00b2, B:42:0x00d9, B:43:0x00e1, B:45:0x0100, B:46:0x0104, B:48:0x010b, B:49:0x010f, B:51:0x0116, B:52:0x011a, B:53:0x011d, B:55:0x0127, B:57:0x0139, B:61:0x0142, B:63:0x0146, B:64:0x016d, B:65:0x0175, B:67:0x0194, B:68:0x0198, B:70:0x019f, B:71:0x01a3, B:73:0x01aa, B:75:0x01af), top: B:80:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010b A[Catch: IOException -> 0x01b5, TryCatch #0 {IOException -> 0x01b5, blocks: (B:26:0x007a, B:28:0x0080, B:31:0x0090, B:33:0x00a2, B:39:0x00ae, B:41:0x00b2, B:42:0x00d9, B:43:0x00e1, B:45:0x0100, B:46:0x0104, B:48:0x010b, B:49:0x010f, B:51:0x0116, B:52:0x011a, B:53:0x011d, B:55:0x0127, B:57:0x0139, B:61:0x0142, B:63:0x0146, B:64:0x016d, B:65:0x0175, B:67:0x0194, B:68:0x0198, B:70:0x019f, B:71:0x01a3, B:73:0x01aa, B:75:0x01af), top: B:80:0x007a }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0116 A[Catch: IOException -> 0x01b5, TryCatch #0 {IOException -> 0x01b5, blocks: (B:26:0x007a, B:28:0x0080, B:31:0x0090, B:33:0x00a2, B:39:0x00ae, B:41:0x00b2, B:42:0x00d9, B:43:0x00e1, B:45:0x0100, B:46:0x0104, B:48:0x010b, B:49:0x010f, B:51:0x0116, B:52:0x011a, B:53:0x011d, B:55:0x0127, B:57:0x0139, B:61:0x0142, B:63:0x0146, B:64:0x016d, B:65:0x0175, B:67:0x0194, B:68:0x0198, B:70:0x019f, B:71:0x01a3, B:73:0x01aa, B:75:0x01af), top: B:80:0x007a }] */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r31, int r32, @org.jetbrains.annotations.Nullable android.content.Intent r33) {
        /*
            Method dump skipped, instructions count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_touch, viewGroup, false);
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
                u();
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
                if (watchFaceBackgroundViewModel3.getMTextStyle() != -1) {
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.n;
                    if (watchFaceLayoutViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel = null;
                    }
                    if (watchFaceLayoutViewModel.getWatchFaceLayoutInfo() != null) {
                        if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                            BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx$onSaveClicked$1
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
                                        Context requireContext = FragmentWatchFaceBackgroundTouchElx.this.requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                        if (utils.isRuggedDevice(requireContext)) {
                                            intValue = utils.getBatteryPercentageForMatrix(intValue);
                                        }
                                        LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                        Context requireContext2 = FragmentWatchFaceBackgroundTouchElx.this.requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                        if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                            FragmentWatchFaceBackgroundTouchElx.this.H();
                                            return;
                                        }
                                        FragmentActivity requireActivity = FragmentWatchFaceBackgroundTouchElx.this.requireActivity();
                                        FragmentWatchFaceBackgroundTouchElx fragmentWatchFaceBackgroundTouchElx = FragmentWatchFaceBackgroundTouchElx.this;
                                        int i = R.string.make_sure_battery;
                                        StringBuilder sb = new StringBuilder();
                                        Context requireContext3 = FragmentWatchFaceBackgroundTouchElx.this.requireContext();
                                        Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                        sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                        sb.append(" %");
                                        Toast.makeText(requireActivity, fragmentWatchFaceBackgroundTouchElx.getString(i, sb.toString()), 1).show();
                                    }
                                }

                                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                                public void onProgressUpdate(@NotNull ProgressData progress) {
                                    Intrinsics.checkNotNullParameter(progress, "progress");
                                }
                            });
                        } else {
                            Toast.makeText(requireActivity(), getString(R.string.band_not_connected), 1).show();
                        }
                    } else {
                        Toast.makeText(requireActivity(), getString(R.string.please_chooose_text_color), 1).show();
                    }
                    WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.o;
                    if (watchFaceBackgroundViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    } else {
                        watchFaceBackgroundViewModel = watchFaceBackgroundViewModel4;
                    }
                    watchFaceBackgroundViewModel.onFail();
                    return;
                }
                Toast.makeText(requireActivity(), getString(R.string.please_choose_textstyle), 1).show();
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.o;
                if (watchFaceBackgroundViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
                }
                watchFaceBackgroundViewModel.onFail();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.please_chooose_background), 1).show();
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel6 = this.o;
            if (watchFaceBackgroundViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel6;
            }
            watchFaceBackgroundViewModel.onFail();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel7 = this.o;
        if (watchFaceBackgroundViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel7;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00c1 A[Catch: Exception -> 0x014c, TryCatch #0 {Exception -> 0x014c, blocks: (B:5:0x009a, B:7:0x00a8, B:9:0x00b5, B:15:0x00c1, B:17:0x00c5, B:18:0x00e4, B:19:0x00ec, B:21:0x00fa, B:22:0x0115, B:24:0x011f, B:25:0x013a, B:27:0x0140, B:28:0x0146), top: B:33:0x009a }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00fa A[Catch: Exception -> 0x014c, TryCatch #0 {Exception -> 0x014c, blocks: (B:5:0x009a, B:7:0x00a8, B:9:0x00b5, B:15:0x00c1, B:17:0x00c5, B:18:0x00e4, B:19:0x00ec, B:21:0x00fa, B:22:0x0115, B:24:0x011f, B:25:0x013a, B:27:0x0140, B:28:0x0146), top: B:33:0x009a }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x011f A[Catch: Exception -> 0x014c, TryCatch #0 {Exception -> 0x014c, blocks: (B:5:0x009a, B:7:0x00a8, B:9:0x00b5, B:15:0x00c1, B:17:0x00c5, B:18:0x00e4, B:19:0x00ec, B:21:0x00fa, B:22:0x0115, B:24:0x011f, B:25:0x013a, B:27:0x0140, B:28:0x0146), top: B:33:0x009a }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0140 A[Catch: Exception -> 0x014c, TryCatch #0 {Exception -> 0x014c, blocks: (B:5:0x009a, B:7:0x00a8, B:9:0x00b5, B:15:0x00c1, B:17:0x00c5, B:18:0x00e4, B:19:0x00ec, B:21:0x00fa, B:22:0x0115, B:24:0x011f, B:25:0x013a, B:27:0x0140, B:28:0x0146), top: B:33:0x009a }] */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r23, @org.jetbrains.annotations.Nullable android.os.Bundle r24) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundTouchElx.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void t(int i) {
        int i2 = R.id.datetimeTextImgView;
        ((ImageView) _$_findCachedViewById(i2)).setVisibility(0);
        ((ImageView) _$_findCachedViewById(i2)).setColorFilter(i);
    }

    public final void u() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public final File v() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.getDefault(), "touchwatchface/%s.zip", Arrays.copyOf(new Object[]{this.x}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        try {
            InputStream open = requireContext().getAssets().open(format);
            Intrinsics.checkNotNullExpressionValue(open, "requireContext().assets.open(styleFileName)");
            File file = new File(requireContext().getCacheDir(), format);
            FileIOUtils.writeFileFromIS(file, open);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final JSONObject w(int i) {
        try {
            JSONArray jSONArray = new JSONArray(FileIOUtils.readFile2String(new File(new File(requireContext().getCacheDir(), "tmp"), "position.json")));
            int length = jSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null && optJSONObject.optInt("TimePosition") == i) {
                    return optJSONObject;
                }
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void x() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundTouchElx.y(FragmentWatchFaceBackgroundTouchElx.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundTouchElx.z(FragmentWatchFaceBackgroundTouchElx.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownUpLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundTouchElx.A(FragmentWatchFaceBackgroundTouchElx.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownDownLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.r1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundTouchElx.B(FragmentWatchFaceBackgroundTouchElx.this, view);
            }
        });
    }
}
