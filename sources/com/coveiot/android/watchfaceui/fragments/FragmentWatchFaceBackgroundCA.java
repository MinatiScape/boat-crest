package com.coveiot.android.watchfaceui.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
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
import com.coveiot.android.watchfaceui.R;
import com.coveiot.android.watchfaceui.listener.OnClickListener;
import com.coveiot.android.watchfaceui.preference.WatchFacePreferenceManager;
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
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.realsil.sdk.dfu.DfuException;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundCA extends BaseFragment implements OnClickListener {
    public static int A;
    public static int y;
    public ActivityWatchFaceViewModel m;
    public WatchFaceBackgroundViewModel n;
    public int o;
    @Nullable
    public String[] p;
    @Nullable
    public Uri q;
    @Nullable
    public Uri r;
    public int v;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static int z = 1;
    public static int B = 1;
    public static int C = 2;
    public static int D = 3;
    public static int E = 4;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public int s = 368;
    public int t = 448;
    public final int u = 996;
    public float w = 23.0f;
    public float x = 28.0f;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getWATCH_FACE_CONTENT_CLOSE() {
            return FragmentWatchFaceBackgroundCA.A;
        }

        public final int getWATCH_FACE_CONTENT_DATE() {
            return FragmentWatchFaceBackgroundCA.B;
        }

        public final int getWATCH_FACE_CONTENT_HEART_RATE() {
            return FragmentWatchFaceBackgroundCA.D;
        }

        public final int getWATCH_FACE_CONTENT_SLEEP() {
            return FragmentWatchFaceBackgroundCA.C;
        }

        public final int getWATCH_FACE_CONTENT_STEP() {
            return FragmentWatchFaceBackgroundCA.E;
        }

        public final int getWATCH_FACE_TIME_BOTTOM() {
            return FragmentWatchFaceBackgroundCA.z;
        }

        public final int getWATCH_FACE_TIME_TOP() {
            return FragmentWatchFaceBackgroundCA.y;
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundCA newInstance() {
            return new FragmentWatchFaceBackgroundCA();
        }

        public final void setWATCH_FACE_CONTENT_CLOSE(int i) {
            FragmentWatchFaceBackgroundCA.A = i;
        }

        public final void setWATCH_FACE_CONTENT_DATE(int i) {
            FragmentWatchFaceBackgroundCA.B = i;
        }

        public final void setWATCH_FACE_CONTENT_HEART_RATE(int i) {
            FragmentWatchFaceBackgroundCA.D = i;
        }

        public final void setWATCH_FACE_CONTENT_SLEEP(int i) {
            FragmentWatchFaceBackgroundCA.C = i;
        }

        public final void setWATCH_FACE_CONTENT_STEP(int i) {
            FragmentWatchFaceBackgroundCA.E = i;
        }

        public final void setWATCH_FACE_TIME_BOTTOM(int i) {
            FragmentWatchFaceBackgroundCA.z = i;
        }

        public final void setWATCH_FACE_TIME_TOP(int i) {
            FragmentWatchFaceBackgroundCA.y = i;
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundCA newInstance() {
        return Companion.newInstance();
    }

    public static final void r(FragmentWatchFaceBackgroundCA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = 350;
        this$0.p();
    }

    public static final void s(FragmentWatchFaceBackgroundCA this$0, View view) {
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
            this$0.w(string);
            return;
        }
        this$0.q = Utils.INSTANCE.takeCameraPictureFromFragment(this$0);
    }

    public static final void t(FragmentWatchFaceBackgroundCA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.selected_watch_face_bg;
        RoundedImageView selected_watch_face_bg = (RoundedImageView) this$0._$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(selected_watch_face_bg, "selected_watch_face_bg");
        this$0.visible(selected_watch_face_bg);
        ((RoundedImageView) this$0._$_findCachedViewById(i)).setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_whitef);
        this$0.v = 0;
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setWatchFacePushType(2);
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.n;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel2 = null;
        }
        if (watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri() != null) {
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.n;
            if (watchFaceBackgroundViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
            }
            watchFaceBackgroundViewModel.showSaveBtn(true);
        }
    }

    public static final void u(FragmentWatchFaceBackgroundCA this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.selected_watch_face_bg;
        RoundedImageView selected_watch_face_bg = (RoundedImageView) this$0._$_findCachedViewById(i);
        Intrinsics.checkNotNullExpressionValue(selected_watch_face_bg, "selected_watch_face_bg");
        this$0.visible(selected_watch_face_bg);
        ((RoundedImageView) this$0._$_findCachedViewById(i)).setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_blackf);
        this$0.v = 1;
        ActivityWatchFaceViewModel activityWatchFaceViewModel = this$0.m;
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        activityWatchFaceViewModel.setWatchFacePushType(2);
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this$0.n;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel2 = null;
        }
        if (watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri() != null) {
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this$0.n;
            if (watchFaceBackgroundViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
            }
            watchFaceBackgroundViewModel.showSaveBtn(true);
        }
    }

    public static final void x(FragmentWatchFaceBackgroundCA this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonTitleMessage, "$bottomSheetDialogOneButtonTitleMessage");
        AppUtils.openAppSettings(this$0.requireActivity(), -1);
        bottomSheetDialogOneButtonTitleMessage.dismiss();
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i != 69) {
            if (i != 1000) {
                if (i != 1001) {
                    super.onActivityResult(i, i2, intent);
                    return;
                }
                Utils utils = Utils.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                utils.startUCrop(requireActivity, this, this.q, this.t, this.s, 1, this.w, this.x, false);
            } else if (intent != null) {
                try {
                    Uri data = intent.getData();
                    Utils utils2 = Utils.INSTANCE;
                    FragmentActivity requireActivity2 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                    utils2.startUCrop(requireActivity2, this, data, this.t, this.s, 1, this.w, this.x, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (intent != null) {
            Uri output = UCrop.getOutput(intent);
            this.r = output;
            if (output != null) {
                try {
                    int i3 = R.id.selected_watch_face;
                    if (((RoundedImageView) _$_findCachedViewById(i3)) != null) {
                        ContentResolver contentResolver = requireActivity().getContentResolver();
                        Uri uri = this.r;
                        Intrinsics.checkNotNull(uri);
                        ((RoundedImageView) _$_findCachedViewById(i3)).setImageBitmap(BitmapFactory.decodeStream(contentResolver.openInputStream(uri)));
                        ActivityWatchFaceViewModel activityWatchFaceViewModel = this.m;
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
                        if (activityWatchFaceViewModel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
                            activityWatchFaceViewModel = null;
                        }
                        activityWatchFaceViewModel.setWatchFacePushType(2);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
                        if (watchFaceBackgroundViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                            watchFaceBackgroundViewModel2 = null;
                        }
                        watchFaceBackgroundViewModel2.setSelectedBackgroundImageUri(this.r);
                        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.n;
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
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_watch_face_background_ca0, viewGroup, false);
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
                this.q = Utils.INSTANCE.takeCameraPictureFromFragment(this);
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
            if (this.n == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            }
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
            if (watchFaceBackgroundViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
            }
            if (watchFaceBackgroundViewModel.getSelectedBackgroundImageUri() != null) {
                if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                    BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundCA$onSaveClicked$1
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
                                Context requireContext = FragmentWatchFaceBackgroundCA.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                                if (utils.isRuggedDevice(requireContext)) {
                                    intValue = utils.getBatteryPercentageForMatrix(intValue);
                                }
                                LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                                Context requireContext2 = FragmentWatchFaceBackgroundCA.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                                if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                    FragmentWatchFaceBackgroundCA.this.v();
                                    return;
                                }
                                FragmentActivity requireActivity = FragmentWatchFaceBackgroundCA.this.requireActivity();
                                FragmentWatchFaceBackgroundCA fragmentWatchFaceBackgroundCA = FragmentWatchFaceBackgroundCA.this;
                                int i = R.string.make_sure_battery;
                                StringBuilder sb = new StringBuilder();
                                Context requireContext3 = FragmentWatchFaceBackgroundCA.this.requireContext();
                                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                                sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                                sb.append(" %");
                                Toast.makeText(requireActivity, fragmentWatchFaceBackgroundCA.getString(i, sb.toString()), 1).show();
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
            return;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = this.n;
        if (watchFaceBackgroundViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
        }
        watchFaceBackgroundViewModel.onFail();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…aceViewModel::class.java)");
        this.m = (ActivityWatchFaceViewModel) viewModel;
        Utils utils = Utils.INSTANCE;
        FragmentActivity requireActivity3 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
        if (utils.isCAULCDevice(requireActivity3)) {
            this.s = 240;
            this.t = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
            this.w = 6.0f;
            this.x = 7.0f;
        }
        int i = R.id.watchface_placeholder;
        int i2 = R.drawable.ic_background_watchface_placeholder;
        ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca5_wave_beat), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca5_wave_play), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca5_wave_style), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ulc3_wave_smart), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ulc2_wave_beat_plus), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ulc2_wave_style_plus), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ulc2_wave_smart_plus), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ulc2_wave_lync), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca3_bt_stormpro_call), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca3_wave_cosmos), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), getResources().getString(R.string.cove_ca3_bt_wave_cosmsos_pro), false)) {
            ((ImageView) _$_findCachedViewById(i)).setImageResource(i2);
        }
        WatchFacePreferenceManager.Companion companion = WatchFacePreferenceManager.Companion;
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        if (companion.getInstance(requireActivity4).getSelectedWatchfaceBackgroundType() == 1) {
            this.v = 1;
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face_bg)).setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_blackf);
        } else {
            ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face_bg)).setImageResource(R.drawable.ca3_diy_watch_face_place_holder_transparent_whitef);
            this.v = 0;
        }
        FragmentActivity requireActivity5 = requireActivity();
        FragmentActivity requireActivity6 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity6, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity5, new ViewModelFactory(requireActivity6)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…undViewModel::class.java)");
        this.n = (WatchFaceBackgroundViewModel) viewModel2;
        q();
        try {
            String lastWatchFaceBackgroundUrl = UserDataManager.getInstance(getContext()).getLastWatchFaceBackgroundUrl();
            if (lastWatchFaceBackgroundUrl != null) {
                Uri fromFile = Uri.fromFile(new File(lastWatchFaceBackgroundUrl));
                ((RoundedImageView) _$_findCachedViewById(R.id.selected_watch_face)).setImageBitmap(BitmapFactory.decodeStream(requireActivity().getContentResolver().openInputStream(Uri.parse(fromFile.toString()))));
                this.r = fromFile;
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.n;
                if (watchFaceBackgroundViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                    watchFaceBackgroundViewModel = null;
                }
                watchFaceBackgroundViewModel.setSelectedBackgroundImageUri(this.r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void p() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    public final void q() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.r
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCA.r(FragmentWatchFaceBackgroundCA.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCA.s(FragmentWatchFaceBackgroundCA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color1)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCA.t(FragmentWatchFaceBackgroundCA.this, view);
            }
        });
        ((RadioButton) _$_findCachedViewById(R.id.rb_cp_color2)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCA.u(FragmentWatchFaceBackgroundCA.this, view);
            }
        });
    }

    public final void v() {
        String modelNumber;
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.WATCHFASE_SAVE.getValue());
        DeviceModelBean deviceModelBean = SessionManager.getInstance(getContext()).getDeviceModelBean();
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
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
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
        } else {
            watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
        }
        watchFaceBackgroundViewModel.sendCAWatchFaceBackgroundToWatch(this.v, this.u);
    }

    public final void w(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.t
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCA.x(FragmentWatchFaceBackgroundCA.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }
}
