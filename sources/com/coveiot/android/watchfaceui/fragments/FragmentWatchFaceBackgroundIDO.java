package com.coveiot.android.watchfaceui.fragments;

import android.content.Context;
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
public final class FragmentWatchFaceBackgroundIDO extends BaseFragment implements OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
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

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundIDO newInstance() {
            return new FragmentWatchFaceBackgroundIDO();
        }
    }

    public static final void A(FragmentWatchFaceBackgroundIDO this$0, RadioGroup radioGroup, int i) {
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
                    this$0.r(radioButton.getCurrentTextColor());
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

    public static final void D(FragmentWatchFaceBackgroundIDO this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundIDO newInstance() {
        return Companion.newInstance();
    }

    public static final void u(FragmentWatchFaceBackgroundIDO this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.p = 350;
        this$0.s();
    }

    public static final void v(FragmentWatchFaceBackgroundIDO this$0, View view) {
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
            this$0.C(string);
            return;
        }
        this$0.r = Utils.INSTANCE.takeCameraPictureFromFragment(this$0);
    }

    public static final void w(FragmentWatchFaceBackgroundIDO this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(8);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(8);
    }

    public static final void x(FragmentWatchFaceBackgroundIDO this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((RadioGroup) this$0._$_findCachedViewById(R.id.rg_color_picker_2)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownUpLl)).setVisibility(0);
        ((LinearLayout) this$0._$_findCachedViewById(R.id.color_dropdownDownLl)).setVisibility(8);
    }

    public static final void z(FragmentWatchFaceBackgroundIDO this$0, RadioGroup radioGroup, int i) {
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
                    this$0.r(radioButton.getCurrentTextColor());
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

    public final void B() {
        String modelNumber;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
        DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
        WatchFaceLayoutViewModel watchFaceLayoutViewModel = null;
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
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.o;
        if (watchFaceBackgroundViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel = null;
        }
        WatchFaceLayoutViewModel watchFaceLayoutViewModel2 = this.n;
        if (watchFaceLayoutViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
        } else {
            watchFaceLayoutViewModel = watchFaceLayoutViewModel2;
        }
        WatchFaceLayoutInfo watchFaceLayoutInfo = watchFaceLayoutViewModel.getWatchFaceLayoutInfo();
        Intrinsics.checkNotNull(watchFaceLayoutInfo);
        watchFaceBackgroundViewModel.sendIDOWatchFaceBackgroundToWatch(watchFaceLayoutInfo);
    }

    public final void C(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundIDO.D(FragmentWatchFaceBackgroundIDO.this, bottomSheetDialogOneButtonTitleMessage, view);
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

    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[Catch: IOException -> 0x010d, TryCatch #1 {IOException -> 0x010d, blocks: (B:18:0x0065, B:20:0x006b, B:22:0x0075, B:24:0x0088, B:30:0x0094, B:32:0x0098, B:33:0x00bf, B:34:0x00c7, B:36:0x00e7, B:37:0x00ed, B:40:0x00f7, B:41:0x00fb, B:43:0x0102, B:45:0x0107), top: B:52:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e7 A[Catch: IOException -> 0x010d, TryCatch #1 {IOException -> 0x010d, blocks: (B:18:0x0065, B:20:0x006b, B:22:0x0075, B:24:0x0088, B:30:0x0094, B:32:0x0098, B:33:0x00bf, B:34:0x00c7, B:36:0x00e7, B:37:0x00ed, B:40:0x00f7, B:41:0x00fb, B:43:0x0102, B:45:0x0107), top: B:52:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f7 A[Catch: IOException -> 0x010d, TRY_ENTER, TryCatch #1 {IOException -> 0x010d, blocks: (B:18:0x0065, B:20:0x006b, B:22:0x0075, B:24:0x0088, B:30:0x0094, B:32:0x0098, B:33:0x00bf, B:34:0x00c7, B:36:0x00e7, B:37:0x00ed, B:40:0x00f7, B:41:0x00fb, B:43:0x0102, B:45:0x0107), top: B:52:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0102 A[Catch: IOException -> 0x010d, TryCatch #1 {IOException -> 0x010d, blocks: (B:18:0x0065, B:20:0x006b, B:22:0x0075, B:24:0x0088, B:30:0x0094, B:32:0x0098, B:33:0x00bf, B:34:0x00c7, B:36:0x00e7, B:37:0x00ed, B:40:0x00f7, B:41:0x00fb, B:43:0x0102, B:45:0x0107), top: B:52:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0106  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r31, int r32, @org.jetbrains.annotations.Nullable android.content.Intent r33) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundIDO.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // com.coveiot.android.watchfaceui.listener.OnClickListener
    public void onAppliedClicked() {
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_ido, viewGroup, false);
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
                s();
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
                WatchFaceLayoutViewModel watchFaceLayoutViewModel = this.n;
                if (watchFaceLayoutViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceLayoutViewModel");
                    watchFaceLayoutViewModel = null;
                }
                if (watchFaceLayoutViewModel.getWatchFaceLayoutInfo() != null) {
                    if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                        BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundIDO$onSaveClicked$1
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
                                    Context requireContext = FragmentWatchFaceBackgroundIDO.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                    if (utils.isRuggedDevice(requireContext)) {
                                        intValue = utils.getBatteryPercentageForMatrix(intValue);
                                    }
                                    LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                    Context requireContext2 = FragmentWatchFaceBackgroundIDO.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                    if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                        FragmentWatchFaceBackgroundIDO.this.B();
                                        return;
                                    }
                                    FragmentActivity requireActivity = FragmentWatchFaceBackgroundIDO.this.requireActivity();
                                    FragmentWatchFaceBackgroundIDO fragmentWatchFaceBackgroundIDO = FragmentWatchFaceBackgroundIDO.this;
                                    int i = R.string.make_sure_battery;
                                    StringBuilder sb = new StringBuilder();
                                    Context requireContext3 = FragmentWatchFaceBackgroundIDO.this.requireContext();
                                    Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                    sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                    sb.append(" %");
                                    Toast.makeText(requireActivity, fragmentWatchFaceBackgroundIDO.getString(i, sb.toString()), 1).show();
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
                Toast.makeText(requireActivity(), getString(R.string.please_chooose_text_color), 1).show();
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.o;
                if (watchFaceBackgroundViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
                }
                watchFaceBackgroundViewModel.onFail();
                return;
            }
            Toast.makeText(requireActivity(), getString(R.string.please_chooose_background), 1).show();
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel4 = this.o;
            if (watchFaceBackgroundViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel4;
            }
            watchFaceBackgroundViewModel.onFail();
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel5 = this.o;
        if (watchFaceBackgroundViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel5;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x00be A[Catch: Exception -> 0x0122, TryCatch #0 {Exception -> 0x0122, blocks: (B:5:0x0097, B:7:0x00a5, B:9:0x00b2, B:15:0x00be, B:17:0x00c2, B:18:0x00e1, B:19:0x00e9, B:21:0x0116, B:22:0x011c), top: B:27:0x0097 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0116 A[Catch: Exception -> 0x0122, TryCatch #0 {Exception -> 0x0122, blocks: (B:5:0x0097, B:7:0x00a5, B:9:0x00b2, B:15:0x00be, B:17:0x00c2, B:18:0x00e1, B:19:0x00e9, B:21:0x0116, B:22:0x011c), top: B:27:0x0097 }] */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onViewCreated(@org.jetbrains.annotations.NotNull android.view.View r23, @org.jetbrains.annotations.Nullable android.os.Bundle r24) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundIDO.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    public final void r(int i) {
        int i2 = R.id.datetimeTextImgV;
        ((ImageView) _$_findCachedViewById(i2)).setVisibility(0);
        ((ImageView) _$_findCachedViewById(i2)).setColorFilter(i);
    }

    public final void s() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public final void t() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.i0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundIDO.u(FragmentWatchFaceBackgroundIDO.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.k0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundIDO.v(FragmentWatchFaceBackgroundIDO.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownUpLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.h0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundIDO.w(FragmentWatchFaceBackgroundIDO.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.color_dropdownDownLl)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.j0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundIDO.x(FragmentWatchFaceBackgroundIDO.this, view);
            }
        });
    }

    public final void y() {
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_1)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.m0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundIDO.z(FragmentWatchFaceBackgroundIDO.this, radioGroup, i);
            }
        });
        ((RadioGroup) _$_findCachedViewById(R.id.rg_color_picker_2)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.coveiot.android.watchfaceui.fragments.n0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentWatchFaceBackgroundIDO.A(FragmentWatchFaceBackgroundIDO.this, radioGroup, i);
            }
        });
    }
}
