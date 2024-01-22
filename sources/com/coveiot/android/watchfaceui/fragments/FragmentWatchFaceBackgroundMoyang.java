package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
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
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
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
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.utils.Utils;
import com.coveiot.android.watchfaceui.viewmodel.ActivityWatchFaceViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceBackgroundViewModel;
import com.coveiot.android.watchfaceui.viewmodel.WatchFaceLayoutViewModel;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundMoyang extends BaseFragment implements OnClickListener {
    public static int u;
    public static int w;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
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
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static int v = 1;
    public static int x = 1;
    public static int y = 2;
    public static int z = 3;
    public static int A = 4;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getWATCH_FACE_CONTENT_CLOSE() {
            return FragmentWatchFaceBackgroundMoyang.w;
        }

        public final int getWATCH_FACE_CONTENT_DATE() {
            return FragmentWatchFaceBackgroundMoyang.x;
        }

        public final int getWATCH_FACE_CONTENT_HEART_RATE() {
            return FragmentWatchFaceBackgroundMoyang.z;
        }

        public final int getWATCH_FACE_CONTENT_SLEEP() {
            return FragmentWatchFaceBackgroundMoyang.y;
        }

        public final int getWATCH_FACE_CONTENT_STEP() {
            return FragmentWatchFaceBackgroundMoyang.A;
        }

        public final int getWATCH_FACE_TIME_BOTTOM() {
            return FragmentWatchFaceBackgroundMoyang.v;
        }

        public final int getWATCH_FACE_TIME_TOP() {
            return FragmentWatchFaceBackgroundMoyang.u;
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundMoyang newInstance() {
            return new FragmentWatchFaceBackgroundMoyang();
        }

        public final void setWATCH_FACE_CONTENT_CLOSE(int i) {
            FragmentWatchFaceBackgroundMoyang.w = i;
        }

        public final void setWATCH_FACE_CONTENT_DATE(int i) {
            FragmentWatchFaceBackgroundMoyang.x = i;
        }

        public final void setWATCH_FACE_CONTENT_HEART_RATE(int i) {
            FragmentWatchFaceBackgroundMoyang.z = i;
        }

        public final void setWATCH_FACE_CONTENT_SLEEP(int i) {
            FragmentWatchFaceBackgroundMoyang.y = i;
        }

        public final void setWATCH_FACE_CONTENT_STEP(int i) {
            FragmentWatchFaceBackgroundMoyang.A = i;
        }

        public final void setWATCH_FACE_TIME_BOTTOM(int i) {
            FragmentWatchFaceBackgroundMoyang.v = i;
        }

        public final void setWATCH_FACE_TIME_TOP(int i) {
            FragmentWatchFaceBackgroundMoyang.u = i;
        }
    }

    public static final void A(FragmentWatchFaceBackgroundMoyang this$0, View view) {
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
            this$0.O(string);
            return;
        }
        this$0.r = Utils.INSTANCE.takeCameraPictureFromFragment(this$0);
    }

    public static final void B(FragmentWatchFaceBackgroundMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(8);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(8);
    }

    public static final void D(FragmentWatchFaceBackgroundMoyang this$0, RadioGroup radioGroup, int i) {
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
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.above))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo);
                        watchFaceLayoutInfo.setPosition(Integer.valueOf(u));
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.below))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo2 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo2);
                        watchFaceLayoutInfo2.setPosition(Integer.valueOf(v));
                    }
                    ConstraintSet constraintSet = new ConstraintSet();
                    int i3 = R.id.parent_text_cl;
                    constraintSet.clone((ConstraintLayout) this$0._$_findCachedViewById(i3));
                    WatchFaceLayoutInfo watchFaceLayoutInfo3 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo3);
                    Integer position = watchFaceLayoutInfo3.getPosition();
                    int i4 = u;
                    if (position != null && position.intValue() == i4) {
                        int i5 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 3, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 3);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i5)).getId(), 4);
                    } else {
                        int i6 = R.id.text_cl;
                        constraintSet.connect(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 4, ((ConstraintLayout) this$0._$_findCachedViewById(i3)).getId(), 4);
                        constraintSet.clear(((ConstraintLayout) this$0._$_findCachedViewById(i6)).getId(), 3);
                    }
                    constraintSet.applyTo((ConstraintLayout) this$0._$_findCachedViewById(i3));
                    WatchFaceLayoutInfo watchFaceLayoutInfo4 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo4);
                    Integer position2 = watchFaceLayoutInfo4.getPosition();
                    Intrinsics.checkNotNull(position2);
                    position2.intValue();
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = this$0.n;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = null;
                    if (watchFaceLayoutViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel = null;
                    }
                    if (watchFaceLayoutViewModel.getWatchFaceLayoutInfoFromWatch() != null) {
                        WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                        if (watchFaceLayoutViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            watchFaceLayoutViewModel3 = null;
                        }
                        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel3.getWatchFaceLayoutInfoFromWatch();
                        Intrinsics.checkNotNull(watchFaceLayoutInfoFromWatch);
                        Integer position3 = watchFaceLayoutInfoFromWatch.getPosition();
                        WatchFaceLayoutInfo watchFaceLayoutInfo5 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo5);
                        if (!Intrinsics.areEqual(position3, watchFaceLayoutInfo5.getPosition())) {
                            ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                            if (activityWatchFaceViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                                activityWatchFaceViewModel = null;
                            }
                            activityWatchFaceViewModel.setWatchFacePushType(2);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel4 = this$0.n;
                            if (watchFaceLayoutViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                                watchFaceLayoutViewModel4 = null;
                            }
                            watchFaceLayoutViewModel4.setWatchFaceLayoutInfo(this$0.t);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel5 = this$0.n;
                            if (watchFaceLayoutViewModel5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            } else {
                                watchFaceLayoutViewModel2 = watchFaceLayoutViewModel5;
                            }
                            watchFaceLayoutViewModel2.showSaveBtn(true);
                        }
                    }
                }
                this$0.M(radioButton);
            }
        }
    }

    public static final void E(FragmentWatchFaceBackgroundMoyang this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.J();
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    if (this$0.t == null) {
                        this$0.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.date))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo);
                        watchFaceLayoutInfo.setTopContent(Integer.valueOf(x));
                        ((ImageView) this$0._$_findCachedViewById(R.id.dateTextImgVTop)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.steps))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo2 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo2);
                        watchFaceLayoutInfo2.setTopContent(Integer.valueOf(A));
                        ((ImageView) this$0._$_findCachedViewById(R.id.stepsTextImgVTop)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.heart_rate))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo3 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo3);
                        watchFaceLayoutInfo3.setTopContent(Integer.valueOf(z));
                        ((ImageView) this$0._$_findCachedViewById(R.id.heartRateTextImgVTop)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.sleep))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo4 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo4);
                        watchFaceLayoutInfo4.setTopContent(Integer.valueOf(y));
                        ((ImageView) this$0._$_findCachedViewById(R.id.sleepTextImgVTop)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.blank))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo5 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo5);
                        watchFaceLayoutInfo5.setTopContent(Integer.valueOf(w));
                    }
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = this$0.n;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = null;
                    if (watchFaceLayoutViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel = null;
                    }
                    if (watchFaceLayoutViewModel.getWatchFaceLayoutInfoFromWatch() != null) {
                        WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                        if (watchFaceLayoutViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            watchFaceLayoutViewModel3 = null;
                        }
                        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel3.getWatchFaceLayoutInfoFromWatch();
                        Intrinsics.checkNotNull(watchFaceLayoutInfoFromWatch);
                        Integer topContent = watchFaceLayoutInfoFromWatch.getTopContent();
                        WatchFaceLayoutInfo watchFaceLayoutInfo6 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo6);
                        if (!Intrinsics.areEqual(topContent, watchFaceLayoutInfo6.getTopContent())) {
                            ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                            if (activityWatchFaceViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                                activityWatchFaceViewModel = null;
                            }
                            activityWatchFaceViewModel.setWatchFacePushType(2);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel4 = this$0.n;
                            if (watchFaceLayoutViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                                watchFaceLayoutViewModel4 = null;
                            }
                            watchFaceLayoutViewModel4.setWatchFaceLayoutInfo(this$0.t);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel5 = this$0.n;
                            if (watchFaceLayoutViewModel5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            } else {
                                watchFaceLayoutViewModel2 = watchFaceLayoutViewModel5;
                            }
                            watchFaceLayoutViewModel2.showSaveBtn(true);
                        }
                    }
                }
                this$0.M(radioButton);
            }
        }
    }

    public static final void F(FragmentWatchFaceBackgroundMoyang this$0, RadioGroup radioGroup, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.K();
        int childCount = radioGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = radioGroup.getChildAt(i2);
            if (childAt instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) childAt;
                if (radioButton.isChecked()) {
                    if (this$0.t == null) {
                        this$0.t = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    }
                    if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.date))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo);
                        watchFaceLayoutInfo.setBottomContent(Integer.valueOf(x));
                        ((ImageView) this$0._$_findCachedViewById(R.id.dateTextImgVBottom)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.steps))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo2 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo2);
                        watchFaceLayoutInfo2.setBottomContent(Integer.valueOf(A));
                        ((ImageView) this$0._$_findCachedViewById(R.id.stepsTextImgVBottom)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.heart_rate))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo3 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo3);
                        watchFaceLayoutInfo3.setBottomContent(Integer.valueOf(z));
                        ((ImageView) this$0._$_findCachedViewById(R.id.heartRateTextImgVBottom)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.sleep))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo4 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo4);
                        watchFaceLayoutInfo4.setBottomContent(Integer.valueOf(y));
                        ((ImageView) this$0._$_findCachedViewById(R.id.sleepTextImgVBottom)).setVisibility(0);
                    } else if (Intrinsics.areEqual(radioButton.getText().toString(), this$0.getString(R.string.blank))) {
                        WatchFaceLayoutInfo watchFaceLayoutInfo5 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo5);
                        watchFaceLayoutInfo5.setBottomContent(Integer.valueOf(w));
                    }
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel = this$0.n;
                    WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = null;
                    if (watchFaceLayoutViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                        watchFaceLayoutViewModel = null;
                    }
                    if (watchFaceLayoutViewModel.getWatchFaceLayoutInfoFromWatch() != null) {
                        WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
                        if (watchFaceLayoutViewModel3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            watchFaceLayoutViewModel3 = null;
                        }
                        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel3.getWatchFaceLayoutInfoFromWatch();
                        Intrinsics.checkNotNull(watchFaceLayoutInfoFromWatch);
                        Integer bottomContent = watchFaceLayoutInfoFromWatch.getBottomContent();
                        WatchFaceLayoutInfo watchFaceLayoutInfo6 = this$0.t;
                        Intrinsics.checkNotNull(watchFaceLayoutInfo6);
                        if (!Intrinsics.areEqual(bottomContent, watchFaceLayoutInfo6.getBottomContent())) {
                            ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
                            if (activityWatchFaceViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                                activityWatchFaceViewModel = null;
                            }
                            activityWatchFaceViewModel.setWatchFacePushType(2);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel4 = this$0.n;
                            if (watchFaceLayoutViewModel4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                                watchFaceLayoutViewModel4 = null;
                            }
                            watchFaceLayoutViewModel4.setWatchFaceLayoutInfo(this$0.t);
                            WatchFaceLayoutViewModel watchFaceLayoutViewModel5 = this$0.n;
                            if (watchFaceLayoutViewModel5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                            } else {
                                watchFaceLayoutViewModel2 = watchFaceLayoutViewModel5;
                            }
                            watchFaceLayoutViewModel2.showSaveBtn(true);
                        }
                    }
                }
                this$0.M(radioButton);
            }
        }
    }

    public static final void G(FragmentWatchFaceBackgroundMoyang this$0, RadioGroup radioGroup, int i) {
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
                    this$0.v(radioButton.getCurrentTextColor());
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

    public static final void H(FragmentWatchFaceBackgroundMoyang this$0, RadioGroup radioGroup, int i) {
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
                    this$0.v(radioButton.getCurrentTextColor());
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

    public static final void I(FragmentWatchFaceBackgroundMoyang this$0, Boolean it) {
        WatchFaceLayoutViewModel watchFaceLayoutViewModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.booleanValue()) {
            if (this$0.t == null) {
                WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this$0.n;
                if (watchFaceLayoutViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                    watchFaceLayoutViewModel2 = null;
                }
                WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel2.getWatchFaceLayoutInfoFromWatch();
                if (watchFaceLayoutInfoFromWatch != null) {
                    WatchFaceLayoutInfo watchFaceLayoutInfo = new WatchFaceLayoutInfo(0, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 32767, null);
                    this$0.t = watchFaceLayoutInfo;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo);
                    watchFaceLayoutInfo.setCompressionType(watchFaceLayoutInfoFromWatch.getCompressionType());
                    WatchFaceLayoutInfo watchFaceLayoutInfo2 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo2);
                    watchFaceLayoutInfo2.setBackgroundPictureMd5(watchFaceLayoutInfoFromWatch.getBackgroundPictureMd5());
                    WatchFaceLayoutInfo watchFaceLayoutInfo3 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo3);
                    watchFaceLayoutInfo3.setTextColor(watchFaceLayoutInfoFromWatch.getTextColor());
                    WatchFaceLayoutInfo watchFaceLayoutInfo4 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo4);
                    watchFaceLayoutInfo4.setBottomContent(watchFaceLayoutInfoFromWatch.getBottomContent());
                    WatchFaceLayoutInfo watchFaceLayoutInfo5 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo5);
                    watchFaceLayoutInfo5.setTopContent(watchFaceLayoutInfoFromWatch.getTopContent());
                    WatchFaceLayoutInfo watchFaceLayoutInfo6 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo6);
                    watchFaceLayoutInfo6.setPosition(watchFaceLayoutInfoFromWatch.getPosition());
                    WatchFaceLayoutInfo watchFaceLayoutInfo7 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo7);
                    watchFaceLayoutInfo7.setHeight(watchFaceLayoutInfoFromWatch.getHeight());
                    WatchFaceLayoutInfo watchFaceLayoutInfo8 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo8);
                    watchFaceLayoutInfo8.setWidth(watchFaceLayoutInfoFromWatch.getWidth());
                    WatchFaceLayoutInfo watchFaceLayoutInfo9 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo9);
                    watchFaceLayoutInfo9.setThumbHeight(watchFaceLayoutInfoFromWatch.getThumbHeight());
                    WatchFaceLayoutInfo watchFaceLayoutInfo10 = this$0.t;
                    Intrinsics.checkNotNull(watchFaceLayoutInfo10);
                    watchFaceLayoutInfo10.setThumbWidth(watchFaceLayoutInfoFromWatch.getThumbWidth());
                }
            }
            WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this$0.n;
            if (watchFaceLayoutViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                watchFaceLayoutViewModel = null;
            } else {
                watchFaceLayoutViewModel = watchFaceLayoutViewModel3;
            }
            this$0.N(watchFaceLayoutViewModel.getWatchFaceLayoutInfoFromWatch());
        }
    }

    public static final void P(FragmentWatchFaceBackgroundMoyang this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundMoyang newInstance() {
        return Companion.newInstance();
    }

    public static final void y(FragmentWatchFaceBackgroundMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(8);
    }

    public static final void z(FragmentWatchFaceBackgroundMoyang this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 350;
        this$0.w();
    }

    public final void C() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_time_position)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.d1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMoyang.D(FragmentWatchFaceBackgroundMoyang.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_above_time)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.z0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMoyang.E(FragmentWatchFaceBackgroundMoyang.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_below_time)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.b1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMoyang.F(FragmentWatchFaceBackgroundMoyang.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.a1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMoyang.G(FragmentWatchFaceBackgroundMoyang.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_2)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.c1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundMoyang.H(FragmentWatchFaceBackgroundMoyang.this, radioGroup, i);
            }
        });
    }

    public final void J() {
        ((ImageView) _$_findCachedViewById(R.id.sleepTextImgVTop)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.heartRateTextImgVTop)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.stepsTextImgVTop)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.dateTextImgVTop)).setVisibility(8);
    }

    public final void K() {
        ((ImageView) _$_findCachedViewById(R.id.sleepTextImgVBottom)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.heartRateTextImgVBottom)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.stepsTextImgVBottom)).setVisibility(8);
        ((ImageView) _$_findCachedViewById(R.id.dateTextImgVBottom)).setVisibility(8);
    }

    public final void L() {
        String modelNumber;
        String modelNumber2;
        if (this.n == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
        }
        WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.n;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = null;
        if (watchFaceLayoutViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            watchFaceLayoutViewModel = null;
        }
        if (watchFaceLayoutViewModel.getWatchFaceLayoutInfo() != null) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
            DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
            if ((deviceModelBean != null ? deviceModelBean.getName() : null) != null) {
                DeviceModelBean deviceModelBean2 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                modelNumber2 = deviceModelBean2 != null ? deviceModelBean2.getName() : null;
            } else {
                Utils utils = Utils.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                modelNumber2 = utils.getModelNumber(requireActivity);
            }
            analyticsLog.setCVPrevScreenName(modelNumber2 + "_features");
            analyticsLog.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put(FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue(), "background");
            analyticsLog.setMapData(hashMap);
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            WatchFaceLayoutViewModel watchFaceLayoutViewModel3 = this.n;
            if (watchFaceLayoutViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            } else {
                watchFaceLayoutViewModel2 = watchFaceLayoutViewModel3;
            }
            watchFaceLayoutViewModel2.sendWatchFaceLayoutToWatch();
            return;
        }
        if (this.o == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        }
        WatchFaceLayoutViewModel watchFaceLayoutViewModel4 = this.n;
        if (watchFaceLayoutViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
            watchFaceLayoutViewModel4 = null;
        }
        WatchFaceLayoutInfo watchFaceLayoutInfoFromWatch = watchFaceLayoutViewModel4.getWatchFaceLayoutInfoFromWatch();
        if (watchFaceLayoutInfoFromWatch != null) {
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.o;
            if (watchFaceBackgroundViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                watchFaceBackgroundViewModel2 = null;
            }
            Uri selectedBackgroundImageUri = watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri();
            if (selectedBackgroundImageUri != null) {
                AnalyticsLog analyticsLog2 = new AnalyticsLog();
                analyticsLog2.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
                DeviceModelBean deviceModelBean3 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                if ((deviceModelBean3 != null ? deviceModelBean3.getName() : null) != null) {
                    DeviceModelBean deviceModelBean4 = SessionManager.getInstance(getContext()).getDeviceModelBean();
                    modelNumber = deviceModelBean4 != null ? deviceModelBean4.getName() : null;
                } else {
                    Utils utils2 = Utils.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                    modelNumber = utils2.getModelNumber(requireActivity2);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(modelNumber);
                sb.append(' ');
                Context context = getContext();
                sb.append(context != null ? context.getString(R.string.features) : null);
                analyticsLog2.setCVPrevScreenName(sb.toString());
                analyticsLog2.setCVScreenName(FirebaseEventParams.ScreenName.APPLY_WATCHFACE.getValue());
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put(FirebaseEventParams.MetaData.CV_WATCHFACE_CATEGORY.getValue(), "background");
                analyticsLog2.setMapData(hashMap2);
                CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog2);
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.o;
                if (watchFaceBackgroundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
                }
                watchFaceBackgroundViewModel.sendWatchFaceBackgroundToWatch(watchFaceLayoutInfoFromWatch, selectedBackgroundImageUri);
            }
        }
    }

    public final void M(RadioButton radioButton) {
        if (radioButton.isChecked()) {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_selected), (Drawable) null, (Drawable) null);
        } else {
            radioButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable(R.drawable.radio_button_unselected), (Drawable) null, (Drawable) null);
        }
    }

    public final void N(WatchFaceLayoutInfo watchFaceLayoutInfo) {
        if (watchFaceLayoutInfo != null) {
            Integer position = watchFaceLayoutInfo.getPosition();
            int i = u;
            if (position != null && position.intValue() == i) {
                ((RadioButton) _$_findCachedViewById(R.id.rb_tp_above)).setChecked(true);
            } else {
                Integer position2 = watchFaceLayoutInfo.getPosition();
                int i2 = v;
                if (position2 != null && position2.intValue() == i2) {
                    ((RadioButton) _$_findCachedViewById(R.id.rb_tp_below)).setChecked(true);
                }
            }
            Integer topContent = watchFaceLayoutInfo.getTopContent();
            int i3 = w;
            if (topContent != null && topContent.intValue() == i3) {
                ((RadioButton) _$_findCachedViewById(R.id.rb_abvt_blank)).setChecked(true);
            } else {
                int i4 = x;
                if (topContent != null && topContent.intValue() == i4) {
                    ((RadioButton) _$_findCachedViewById(R.id.rb_abvt_date)).setChecked(true);
                } else {
                    int i5 = y;
                    if (topContent != null && topContent.intValue() == i5) {
                        ((RadioButton) _$_findCachedViewById(R.id.rb_abvt_sleep)).setChecked(true);
                    } else {
                        int i6 = z;
                        if (topContent != null && topContent.intValue() == i6) {
                            ((RadioButton) _$_findCachedViewById(R.id.rb_abvt_hr)).setChecked(true);
                        } else {
                            int i7 = A;
                            if (topContent != null && topContent.intValue() == i7) {
                                ((RadioButton) _$_findCachedViewById(R.id.rb_abvt_steps)).setChecked(true);
                            }
                        }
                    }
                }
            }
            Integer bottomContent = watchFaceLayoutInfo.getBottomContent();
            int i8 = w;
            if (bottomContent != null && bottomContent.intValue() == i8) {
                ((RadioButton) _$_findCachedViewById(R.id.rb_blwt_blank)).setChecked(true);
            } else {
                int i9 = x;
                if (bottomContent != null && bottomContent.intValue() == i9) {
                    ((RadioButton) _$_findCachedViewById(R.id.rb_blwt_date)).setChecked(true);
                } else {
                    int i10 = y;
                    if (bottomContent != null && bottomContent.intValue() == i10) {
                        ((RadioButton) _$_findCachedViewById(R.id.rb_blwt_sleep)).setChecked(true);
                    } else {
                        int i11 = z;
                        if (bottomContent != null && bottomContent.intValue() == i11) {
                            ((RadioButton) _$_findCachedViewById(R.id.rb_blwt_hr)).setChecked(true);
                        } else {
                            int i12 = A;
                            if (bottomContent != null && bottomContent.intValue() == i12) {
                                ((RadioButton) _$_findCachedViewById(R.id.rb_blwt_steps)).setChecked(true);
                            }
                        }
                    }
                }
            }
            if (UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedColor() != 0) {
                v(UserDataManager.getInstance(requireActivity()).getWatchFaceLayoutSelectedColor());
            }
        }
    }

    public final void O(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMoyang.P(FragmentWatchFaceBackgroundMoyang.this, bottomSheetDialogOneButtonTitleMessage, view);
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

    /* JADX WARN: Removed duplicated region for block: B:30:0x007a A[Catch: IOException -> 0x00ed, TryCatch #1 {IOException -> 0x00ed, blocks: (B:18:0x004c, B:20:0x0052, B:22:0x005c, B:24:0x006e, B:30:0x007a, B:32:0x007e, B:33:0x009f, B:34:0x00a7, B:36:0x00c7, B:37:0x00cd, B:40:0x00d7, B:41:0x00db, B:43:0x00e2, B:45:0x00e7), top: B:52:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c7 A[Catch: IOException -> 0x00ed, TryCatch #1 {IOException -> 0x00ed, blocks: (B:18:0x004c, B:20:0x0052, B:22:0x005c, B:24:0x006e, B:30:0x007a, B:32:0x007e, B:33:0x009f, B:34:0x00a7, B:36:0x00c7, B:37:0x00cd, B:40:0x00d7, B:41:0x00db, B:43:0x00e2, B:45:0x00e7), top: B:52:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d7 A[Catch: IOException -> 0x00ed, TRY_ENTER, TryCatch #1 {IOException -> 0x00ed, blocks: (B:18:0x004c, B:20:0x0052, B:22:0x005c, B:24:0x006e, B:30:0x007a, B:32:0x007e, B:33:0x009f, B:34:0x00a7, B:36:0x00c7, B:37:0x00cd, B:40:0x00d7, B:41:0x00db, B:43:0x00e2, B:45:0x00e7), top: B:52:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00e2 A[Catch: IOException -> 0x00ed, TryCatch #1 {IOException -> 0x00ed, blocks: (B:18:0x004c, B:20:0x0052, B:22:0x005c, B:24:0x006e, B:30:0x007a, B:32:0x007e, B:33:0x009f, B:34:0x00a7, B:36:0x00c7, B:37:0x00cd, B:40:0x00d7, B:41:0x00db, B:43:0x00e2, B:45:0x00e7), top: B:52:0x004c }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e6  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r25, int r26, @org.jetbrains.annotations.Nullable android.content.Intent r27) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMoyang.onActivityResult(int, int, android.content.Intent):void");
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
        return inflater.inflate(R.layout.fragment_watch_face_background_moyang, viewGroup, false);
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
                w();
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
            if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMoyang$onSaveClicked$1
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
                            Context requireContext = FragmentWatchFaceBackgroundMoyang.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            if (utils.isRuggedDevice(requireContext)) {
                                intValue = utils.getBatteryPercentageForMatrix(intValue);
                            }
                            LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                            Context requireContext2 = FragmentWatchFaceBackgroundMoyang.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                FragmentWatchFaceBackgroundMoyang.this.L();
                                return;
                            }
                            FragmentActivity requireActivity = FragmentWatchFaceBackgroundMoyang.this.requireActivity();
                            FragmentWatchFaceBackgroundMoyang fragmentWatchFaceBackgroundMoyang = FragmentWatchFaceBackgroundMoyang.this;
                            int i = R.string.make_sure_battery;
                            StringBuilder sb = new StringBuilder();
                            Context requireContext3 = FragmentWatchFaceBackgroundMoyang.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                            sb.append(" %");
                            Toast.makeText(requireActivity, fragmentWatchFaceBackgroundMoyang.getString(i, sb.toString()), 1).show();
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
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.o;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00e0 A[Catch: Exception -> 0x0146, TryCatch #0 {Exception -> 0x0146, blocks: (B:19:0x00b9, B:21:0x00c7, B:23:0x00d4, B:29:0x00e0, B:31:0x00e4, B:32:0x0104, B:33:0x010c, B:35:0x0139, B:37:0x0140), top: B:42:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0139 A[Catch: Exception -> 0x0146, TryCatch #0 {Exception -> 0x0146, blocks: (B:19:0x00b9, B:21:0x00c7, B:23:0x00d4, B:29:0x00e0, B:31:0x00e4, B:32:0x0104, B:33:0x010c, B:35:0x0139, B:37:0x0140), top: B:42:0x00b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x013f  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r24, @org.jetbrains.annotations.Nullable android.os.Bundle r25) {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundMoyang.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void v(int i) {
        ((ImageView) _$_findCachedViewById(R.id.timeTextImgV)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.sleepTextImgVBottom)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.heartRateTextImgVBottom)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.stepsTextImgVBottom)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.dateTextImgVBottom)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.sleepTextImgVTop)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.heartRateTextImgVTop)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.stepsTextImgVTop)).setColorFilter(i);
        ((ImageView) _$_findCachedViewById(R.id.dateTextImgVTop)).setColorFilter(i);
    }

    public final void w() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public final void x() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMoyang.z(FragmentWatchFaceBackgroundMoyang.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMoyang.A(FragmentWatchFaceBackgroundMoyang.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownUpLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.w0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMoyang.B(FragmentWatchFaceBackgroundMoyang.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownDownLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.t0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundMoyang.y(FragmentWatchFaceBackgroundMoyang.this, view);
            }
        });
    }
}
