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
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
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
import com.coveiot.android.watchfacecore.model.WatchFaceLayoutInfo;
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
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.realsil.sdk.dfu.DfuException;
import com.yalantis.ucrop.UCrop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class FragmentWatchFaceBackgroundCZ2 extends BaseFragment implements OnClickListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public ActivityWatchFaceViewModel m;
    public WatchFaceBackgroundViewModel n;
    public int o;
    @Nullable
    public String[] p;
    @Nullable
    public Uri q;
    @Nullable
    public Uri r;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FragmentWatchFaceBackgroundCZ2 newInstance() {
            return new FragmentWatchFaceBackgroundCZ2();
        }
    }

    /* loaded from: classes8.dex */
    public static final class a extends Lambda implements Function1<Boolean, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke2(bool);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Boolean item) {
            Intrinsics.checkNotNullExpressionValue(item, "item");
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = null;
            if (item.booleanValue()) {
                WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = FragmentWatchFaceBackgroundCZ2.this.n;
                if (watchFaceBackgroundViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
                } else {
                    watchFaceBackgroundViewModel = watchFaceBackgroundViewModel2;
                }
                watchFaceBackgroundViewModel.showSaveBtn(true);
                return;
            }
            WatchFaceBackgroundViewModel watchFaceBackgroundViewModel3 = FragmentWatchFaceBackgroundCZ2.this.n;
            if (watchFaceBackgroundViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            } else {
                watchFaceBackgroundViewModel = watchFaceBackgroundViewModel3;
            }
            watchFaceBackgroundViewModel.showSaveBtn(false);
        }
    }

    @JvmStatic
    @NotNull
    public static final FragmentWatchFaceBackgroundCZ2 newInstance() {
        return Companion.newInstance();
    }

    public static final void q(FragmentWatchFaceBackgroundCZ2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.o = 350;
        this$0.o();
    }

    public static final void r(FragmentWatchFaceBackgroundCZ2 this$0, View view) {
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
            this$0.u(string);
            return;
        }
        this$0.q = Utils.INSTANCE.takeCameraPictureFromFragment(this$0);
    }

    public static final void s(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void v(FragmentWatchFaceBackgroundCZ2 this$0, BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage, View view) {
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

    public final void o() {
        startActivityForResult(Utils.INSTANCE.getGalleryIntent(), 1000);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        if (i == 69) {
            if (intent != null) {
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (i != 1000) {
            if (i != 1001) {
                super.onActivityResult(i, i2, intent);
                return;
            }
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            if (Utils.isCYDevice(context)) {
                Utils utils = Utils.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                utils.startUCrop(requireActivity, this, this.q, 454, 454, 1, 1.0f, 1.0f, false);
                return;
            }
            Utils utils2 = Utils.INSTANCE;
            FragmentActivity requireActivity2 = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
            utils2.startUCrop(requireActivity2, this, this.q, DfuException.ERROR_ENTER_OTA_MODE_FAILED, 240, 1, 6.0f, 7.0f, true);
        } else if (intent != null) {
            try {
                Uri data = intent.getData();
                Context context2 = getContext();
                Intrinsics.checkNotNull(context2);
                if (Utils.isCYDevice(context2)) {
                    Utils utils3 = Utils.INSTANCE;
                    FragmentActivity requireActivity3 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity()");
                    utils3.startUCrop(requireActivity3, this, data, 454, 454, 1, 1.0f, 1.0f, false);
                } else {
                    Utils utils4 = Utils.INSTANCE;
                    FragmentActivity requireActivity4 = requireActivity();
                    Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
                    utils4.startUCrop(requireActivity4, this, data, DfuException.ERROR_ENTER_OTA_MODE_FAILED, 240, 1, 6.0f, 7.0f, true);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
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
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (Utils.isCYDevice(requireContext)) {
            return inflater.inflate(R.layout.fragment_watch_face_background_cy1, viewGroup, false);
        }
        return inflater.inflate(R.layout.fragment_watch_face_background_cz2, viewGroup, false);
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
                o();
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
        if (activityWatchFaceViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
            activityWatchFaceViewModel = null;
        }
        if (activityWatchFaceViewModel.getWatchFacePushType() == 2) {
            if (this.n == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            }
            if (BleApiManager.getInstance(requireActivity()).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
                BleApiManager.getInstance(requireActivity()).getBleApi().getData(new BatteryLevelRequest(), new DataResultListener() { // from class: com.coveiot.android.watchfaceui.fragments.FragmentWatchFaceBackgroundCZ2$onSaveClicked$1
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
                            Context requireContext = FragmentWatchFaceBackgroundCZ2.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                            if (utils.isRuggedDevice(requireContext)) {
                                intValue = utils.getBatteryPercentageForMatrix(intValue);
                            }
                            LogHelper.i("FragmentWatchFaceCloud", "batteryLevel -- " + intValue);
                            Context requireContext2 = FragmentWatchFaceBackgroundCZ2.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                            if (intValue >= utils.getMinBatteryPerForWatchFaceUpload(requireContext2)) {
                                FragmentWatchFaceBackgroundCZ2.this.t();
                                return;
                            }
                            FragmentActivity requireActivity = FragmentWatchFaceBackgroundCZ2.this.requireActivity();
                            FragmentWatchFaceBackgroundCZ2 fragmentWatchFaceBackgroundCZ2 = FragmentWatchFaceBackgroundCZ2.this;
                            int i = R.string.make_sure_battery;
                            StringBuilder sb = new StringBuilder();
                            Context requireContext3 = FragmentWatchFaceBackgroundCZ2.this.requireContext();
                            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                            sb.append(utils.getMinBatteryPerForWatchFaceUpload(requireContext3));
                            sb.append(" %");
                            Toast.makeText(requireActivity, fragmentWatchFaceBackgroundCZ2.getString(i, sb.toString()), 1).show();
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
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.cove_wave_prime), false)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.cove_wave_elite), false)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.cove_cz3), false)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(getContext()).getDeviceType(), requireContext().getResources().getString(R.string.cove_cz2), false)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(requireContext()).getDeviceType(), getString(R.string.cy1_primia_voice), true)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(requireContext()).getDeviceType(), getString(R.string.cy1_loop_call_pro), true)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
        } else if (kotlin.text.m.equals(SessionManager.getInstance(requireContext()).getDeviceType(), getString(R.string.cy1_loop_connect_pro), true)) {
            ((ImageView) _$_findCachedViewById(R.id.watchface_placeholder)).setImageResource(R.drawable.ic_background_rounded_watchface_placeholder);
        }
        FragmentActivity requireActivity = requireActivity();
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
        ViewModel viewModel = ViewModelProviders.of(requireActivity, new ViewModelFactory(requireActivity2)).get(WatchFaceBackgroundViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "of(\n            requireA…undViewModel::class.java)");
        this.n = (WatchFaceBackgroundViewModel) viewModel;
        FragmentActivity requireActivity3 = requireActivity();
        FragmentActivity requireActivity4 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity4, "requireActivity()");
        ViewModel viewModel2 = ViewModelProviders.of(requireActivity3, new ViewModelFactory(requireActivity4)).get(ActivityWatchFaceViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "of(\n            requireA…aceViewModel::class.java)");
        this.m = (ActivityWatchFaceViewModel) viewModel2;
        p();
        ActivityWatchFaceViewModel activityWatchFaceViewModel = null;
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
        ActivityWatchFaceViewModel activityWatchFaceViewModel2 = this.m;
        if (activityWatchFaceViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mActivityWatchFaceViewModel");
        } else {
            activityWatchFaceViewModel = activityWatchFaceViewModel2;
        }
        LiveData<Boolean> isShow = activityWatchFaceViewModel.getIsShow();
        if (isShow != null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            final a aVar = new a();
            isShow.observe(activity, new Observer() { // from class: com.coveiot.android.watchfaceui.fragments.x
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    FragmentWatchFaceBackgroundCZ2.s(Function1.this, obj);
                }
            });
        }
    }

    public final void p() {
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvGallery)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCZ2.q(FragmentWatchFaceBackgroundCZ2.this, view);
            }
        });
        ((ConstraintLayout) _$_findCachedViewById(R.id.cvCamera)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCZ2.r(FragmentWatchFaceBackgroundCZ2.this, view);
            }
        });
    }

    public final void t() {
        String modelNumber;
        int i;
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
        int i2 = DfuException.ERROR_ENTER_OTA_MODE_FAILED;
        int i3 = 240;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        if (Utils.isCYDevice(context)) {
            i2 = 454;
            i3 = 454;
        }
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (Utils.isCYDevice(requireContext)) {
            i = 5;
        } else {
            i = ((BleApiManager.getInstance(requireContext()).getDeviceType() == null || BleApiManager.getInstance(requireContext()).getDeviceType() != DeviceType.wavePrime) && (BleApiManager.getInstance(requireContext()).getDeviceType() == null || BleApiManager.getInstance(requireContext()).getDeviceType() != DeviceType.WAVE_ELITE)) ? 3 : 2;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel = this.n;
        if (watchFaceBackgroundViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel = null;
        }
        WatchFaceBackgroundViewModel watchFaceBackgroundViewModel2 = this.n;
        if (watchFaceBackgroundViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWatchFaceBackgroundViewModel");
            watchFaceBackgroundViewModel2 = null;
        }
        Uri selectedBackgroundImageUri = watchFaceBackgroundViewModel2.getSelectedBackgroundImageUri();
        watchFaceBackgroundViewModel.sendWatchFaceBackgroundToWatch(new WatchFaceLayoutInfo(0, null, null, null, null, null, new File(selectedBackgroundImageUri != null ? selectedBackgroundImageUri.getPath() : null), Integer.valueOf(i), 996, Integer.valueOf(i2), Integer.valueOf(i3), null, null, null, null, 30783, null));
    }

    public final void u(String str) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        String string = getString(R.string.permission_required);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.permission_required)");
        final BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(requireActivity, string, str);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonTitleMessage.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.watchfaceui.fragments.w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWatchFaceBackgroundCZ2.v(FragmentWatchFaceBackgroundCZ2.this, bottomSheetDialogOneButtonTitleMessage, view);
            }
        });
        bottomSheetDialogOneButtonTitleMessage.show();
    }
}
