package com.coveiot.android.activitymodes.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activities.ActivityWorkoutSession;
import com.coveiot.android.activitymodes.eventmodels.OnWorkoutDeviceDisconnected;
import com.coveiot.android.activitymodes.preference.PreferenceManager;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import com.coveiot.android.activitymodes.utils.IndoorOutdoor;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.eventmodels.CurrentStepsValue;
import com.coveiot.utils.eventmodels.GetCurrentSteps;
import com.coveiot.utils.eventmodels.OnBleCommandError;
import com.coveiot.utils.eventmodels.OnDeviceDisconnected;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.otto.Subscribe;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentWorkoutDashboard extends BaseFragment implements OnMapReadyCallback {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public SupportMapFragment googleMapFragment;
    @Nullable
    public GoogleMap n;
    public FusedLocationProviderClient o;
    public boolean q;
    @Nullable
    public BottomSheetDialogTwoButtons s;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public final String m = "FragmentWorkoutDashboa";
    public final int p = 101;
    @NotNull
    public ActivityMode r = ActivityMode.RUN;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentWorkoutDashboard newInstance() {
            return new FragmentWorkoutDashboard();
        }
    }

    /* loaded from: classes2.dex */
    public static final class a extends Lambda implements Function1<Location, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@Nullable Location location) {
            if (location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                BitmapDescriptor fromResource = BitmapDescriptorFactory.fromResource(R.drawable.marker_icon);
                Intrinsics.checkNotNullExpressionValue(fromResource, "fromResource(R.drawable.marker_icon)");
                GoogleMap googleMap = FragmentWorkoutDashboard.this.getGoogleMap();
                Intrinsics.checkNotNull(googleMap);
                googleMap.addMarker(new MarkerOptions().position(latLng).icon(fromResource));
                CameraUpdate newLatLngZoom = CameraUpdateFactory.newLatLngZoom(latLng, 15.0f);
                Intrinsics.checkNotNullExpressionValue(newLatLngZoom, "newLatLngZoom(latLng, 15f)");
                GoogleMap googleMap2 = FragmentWorkoutDashboard.this.getGoogleMap();
                Intrinsics.checkNotNull(googleMap2);
                googleMap2.moveCamera(newLatLngZoom);
            }
        }
    }

    public static final void A(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
        analyticsLog.setScreenName(FirebaseEventParams.ScreenName.MAIN_ACTIVITY_DASHBOARD.getValue());
        analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_ACTIVITY_START_SCREEN.getValue());
        analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.START_BUTTON.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        this$0.q = true;
        if (AppUtils.checkIfLocationPermissionExists(this$0.getContext())) {
            Context context = this$0.getContext();
            Intrinsics.checkNotNull(context);
            if (AppUtils.isGpsEnabled(context)) {
                if (AppUtils.isBluetoothEnabled(this$0.getActivity())) {
                    String string = this$0.getString(com.coveiot.android.theme.R.string.please_wait);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…eme.R.string.please_wait)");
                    this$0.showProgressWithTitle(string, true);
                    CoveEventBusManager.getInstance().getEventBus().post(new GetCurrentSteps());
                    return;
                }
                this$0.showOpenBluetoothSettingsDialog();
                return;
            }
            this$0.D();
            return;
        }
        FragmentActivity activity = this$0.getActivity();
        Intrinsics.checkNotNull(activity);
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.ACCESS_FINE_LOCATION")) {
            this$0.C();
            return;
        }
        FragmentActivity activity2 = this$0.getActivity();
        Intrinsics.checkNotNull(activity2);
        ActivityCompat.requestPermissions(activity2, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 202);
    }

    public static final void E(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AppUtils.openBluetoothSettings(this$0.getActivity());
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.s;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void F(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = this$0.s;
        Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
        bottomSheetDialogTwoButtons.dismiss();
    }

    public static final void u(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.up_arrow)).setVisibility(0);
        ((ImageView) this$0._$_findCachedViewById(R.id.imageView3)).setVisibility(0);
        ((ImageView) this$0._$_findCachedViewById(R.id.down_arrow)).setVisibility(8);
    }

    public static final void v(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.up_arrow)).setVisibility(8);
        int i = R.id.imageView3;
        ((ImageView) this$0._$_findCachedViewById(i)).setVisibility(8);
        ((ImageView) this$0._$_findCachedViewById(R.id.down_arrow)).setVisibility(0);
        this$0.s(((ImageView) this$0._$_findCachedViewById(i)).getDrawable().getConstantState());
    }

    public static final void w(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.up_arrow)).setVisibility(8);
        ((ImageView) this$0._$_findCachedViewById(R.id.imageView3)).setVisibility(8);
        ((ImageView) this$0._$_findCachedViewById(R.id.down_arrow)).setVisibility(0);
    }

    public static final void x(FragmentWorkoutDashboard this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ImageView) this$0._$_findCachedViewById(R.id.imageView1)).callOnClick();
    }

    public static final void z(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void B() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        PermissionUtils.checkPermission(activity, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.activitymodes.fragments.FragmentWorkoutDashboard$requestLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                int i;
                FragmentActivity activity2 = FragmentWorkoutDashboard.this.getActivity();
                Intrinsics.checkNotNull(activity2);
                i = FragmentWorkoutDashboard.this.p;
                ActivityCompat.requestPermissions(activity2, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, i);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                FragmentWorkoutDashboard.this.C();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                FragmentWorkoutDashboard.this.y();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                FragmentWorkoutDashboard.this.C();
            }
        });
    }

    public final void C() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.location_permission_modes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_modes)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.FragmentWorkoutDashboard$showLocationPermissionDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                FragmentActivity activity = this.getActivity();
                Intrinsics.checkNotNull(activity);
                AppUtils.openAppSettings(activity, 121);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final void D() {
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String string = getString(R.string.loccation_disabled);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.loccation_disabled)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(context, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.FragmentWorkoutDashboard$showLocationSettingsDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                FragmentActivity activity = this.getActivity();
                Intrinsics.checkNotNull(activity);
                AppUtils.openLocationSettings(activity, 12);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Subscribe
    public final void OnBleCommandError(@NotNull OnBleCommandError onBleCommandError) {
        Intrinsics.checkNotNullParameter(onBleCommandError, "onBleCommandError");
        Log.d(this.m, "OnBleCommandError");
        dismissProgress();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            String errorMsg = onBleCommandError.getErrorMsg();
            Intrinsics.checkNotNullExpressionValue(errorMsg, "onBleCommandError.errorMsg");
            ExtensionFuncsKt.toastShort(activity, errorMsg);
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

    @Subscribe
    public final void getCurrentData(@NotNull CurrentStepsValue currentStepsValue) {
        Intrinsics.checkNotNullParameter(currentStepsValue, "currentStepsValue");
        Log.d("Current steps", "Steps: " + currentStepsValue.getDistance());
        ProfileData userDetails = SessionManager.getInstance(getContext()).getUserDetails();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        PreferenceManager preferenceManager = new PreferenceManager(context);
        preferenceManager.setInitialSteps(currentStepsValue.getSteps());
        String height = userDetails.getHeight();
        Intrinsics.checkNotNullExpressionValue(height, "profileData.height");
        preferenceManager.setHeight(Integer.parseInt(height));
        String weight = userDetails.getWeight();
        Intrinsics.checkNotNullExpressionValue(weight, "profileData.weight");
        preferenceManager.setWeight((int) Double.parseDouble(weight));
        if (userDetails.getDob() != null) {
            preferenceManager.setAge(AppUtils.getAge(userDetails.getDob()));
        }
        onStepsReceived(currentStepsValue.getSteps());
    }

    @Nullable
    public final BottomSheetDialogTwoButtons getEnableBluetoothDialog() {
        return this.s;
    }

    @Nullable
    public final GoogleMap getGoogleMap() {
        return this.n;
    }

    @NotNull
    public final SupportMapFragment getGoogleMapFragment() {
        SupportMapFragment supportMapFragment = this.googleMapFragment;
        if (supportMapFragment != null) {
            return supportMapFragment;
        }
        Intrinsics.throwUninitializedPropertyAccessException("googleMapFragment");
        return null;
    }

    public final boolean isStartClicked() {
        return this.q;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) activity);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(activity!!)");
        this.o = fusedLocationProviderClient;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_workout_dashboard, viewGroup, false);
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Subscribe
    public final void onDeviceDisconnected(@NotNull OnDeviceDisconnected onDeviceDisconnected) {
        Intrinsics.checkNotNullParameter(onDeviceDisconnected, "onDeviceDisconnected");
        LogHelper.d(this.m, "onDeviceDisconnected");
        dismissProgress();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    @SuppressLint({"MissingPermission"})
    public void onMapReady(@NotNull GoogleMap map) {
        Intrinsics.checkNotNullParameter(map, "map");
        Log.d(this.m, "onMapReady: called");
        this.n = map;
        if (AppUtils.checkIfLocationPermissionExists(getActivity())) {
            y();
        } else {
            B();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        CoveEventBusManager.getInstance().getEventBus().register(this);
    }

    public final void onStepsReceived(int i) {
        dismissProgress();
        if (this.q) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            new PreferenceManager(context).setRunSessionActive(true);
            Intent intent = new Intent(getContext(), ActivityWorkoutSession.class);
            intent.putExtra(WorkoutConstants.ACTIVITY_MODE, this.r);
            intent.putExtra(WorkoutConstants.INDOOR_OUTDOOR, IndoorOutdoor.OUTDOOR);
            Context context2 = getContext();
            if (context2 != null) {
                context2.startActivity(intent);
            }
        }
        this.q = false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        this.q = false;
        CoveEventBusManager.getInstance().getEventBus().unregister(this);
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Log.d("preplan", "FragmentWorkoutDashboa onViewCreated: called");
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.map_fragment);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        setGoogleMapFragment((SupportMapFragment) findFragmentById);
        ((Button) _$_findCachedViewById(R.id.startButton)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.u0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentWorkoutDashboard.A(FragmentWorkoutDashboard.this, view2);
            }
        });
        t();
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        String activityMode = new PreferenceManager(context).getActivityMode();
        Intrinsics.checkNotNull(activityMode);
        ActivityMode valueOf = ActivityMode.valueOf(activityMode);
        this.r = valueOf;
        if (valueOf == ActivityMode.WALK) {
            ((ImageView) _$_findCachedViewById(R.id.imageView3)).callOnClick();
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.MAIN_ACTIVITY_DASHBOARD;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
    }

    public final void s(Drawable.ConstantState constantState) {
        Resources resources = getResources();
        int i = R.drawable.run_modes_image;
        if (Intrinsics.areEqual(constantState, resources.getDrawable(i).getConstantState())) {
            ((ImageView) _$_findCachedViewById(R.id.imageView1)).setImageDrawable(getResources().getDrawable(R.drawable.run_selected_image));
            ((ImageView) _$_findCachedViewById(R.id.imageView3)).setImageDrawable(getResources().getDrawable(R.drawable.walk_modes_image));
            this.r = ActivityMode.RUN;
        } else if (Intrinsics.areEqual(constantState, getResources().getDrawable(R.drawable.walk_modes_image).getConstantState())) {
            ((ImageView) _$_findCachedViewById(R.id.imageView1)).setImageDrawable(getResources().getDrawable(R.drawable.walk_selected_image));
            ((ImageView) _$_findCachedViewById(R.id.imageView3)).setImageDrawable(getResources().getDrawable(i));
            this.r = ActivityMode.WALK;
        }
    }

    public final void setEnableBluetoothDialog(@Nullable BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons) {
        this.s = bottomSheetDialogTwoButtons;
    }

    public final void setGoogleMap(@Nullable GoogleMap googleMap) {
        this.n = googleMap;
    }

    public final void setGoogleMapFragment(@NotNull SupportMapFragment supportMapFragment) {
        Intrinsics.checkNotNullParameter(supportMapFragment, "<set-?>");
        this.googleMapFragment = supportMapFragment;
    }

    public final void setStartClicked(boolean z) {
        this.q = z;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        SupportMapFragment googleMapFragment;
        super.setUserVisibleHint(true);
        if (isVisible() && z && (googleMapFragment = getGoogleMapFragment()) != null) {
            googleMapFragment.getMapAsync(this);
        }
    }

    public final void showOpenBluetoothSettingsDialog() {
        if (getActivity() != null) {
            if (this.s == null) {
                FragmentActivity activity = getActivity();
                Intrinsics.checkNotNull(activity);
                String string = getString(R.string.bluetooth_off);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.bluetooth_off)");
                String string2 = getString(R.string.bluetooth_off_message);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.bluetooth_off_message)");
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(activity, string, string2, false, 8, null);
                this.s = bottomSheetDialogTwoButtons;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons);
                String string3 = getString(R.string.proceed);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.proceed)");
                bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.a1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentWorkoutDashboard.E(FragmentWorkoutDashboard.this, view);
                    }
                });
                BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons2 = this.s;
                Intrinsics.checkNotNull(bottomSheetDialogTwoButtons2);
                String string4 = getString(R.string.cancel);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.cancel)");
                bottomSheetDialogTwoButtons2.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.w0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        FragmentWorkoutDashboard.F(FragmentWorkoutDashboard.this, view);
                    }
                });
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons3 = this.s;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons3);
            if (bottomSheetDialogTwoButtons3.isShowing()) {
                return;
            }
            BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons4 = this.s;
            Intrinsics.checkNotNull(bottomSheetDialogTwoButtons4);
            bottomSheetDialogTwoButtons4.show();
        }
    }

    public final void t() {
        ((ImageView) _$_findCachedViewById(R.id.imageView1)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorkoutDashboard.u(FragmentWorkoutDashboard.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.imageView3)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.x0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorkoutDashboard.v(FragmentWorkoutDashboard.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.up_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorkoutDashboard.w(FragmentWorkoutDashboard.this, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.down_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.activitymodes.fragments.v0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FragmentWorkoutDashboard.x(FragmentWorkoutDashboard.this, view);
            }
        });
    }

    public final void y() {
        GoogleMap googleMap = this.n;
        Intrinsics.checkNotNull(googleMap);
        googleMap.setMyLocationEnabled(false);
        GoogleMap googleMap2 = this.n;
        Intrinsics.checkNotNull(googleMap2);
        googleMap2.getUiSettings().setMyLocationButtonEnabled(false);
        GoogleMap googleMap3 = this.n;
        Intrinsics.checkNotNull(googleMap3);
        googleMap3.getUiSettings().setScrollGesturesEnabled(false);
        GoogleMap googleMap4 = this.n;
        Intrinsics.checkNotNull(googleMap4);
        googleMap4.getUiSettings().setZoomControlsEnabled(false);
        GoogleMap googleMap5 = this.n;
        Intrinsics.checkNotNull(googleMap5);
        googleMap5.getUiSettings().setZoomGesturesEnabled(false);
        FusedLocationProviderClient fusedLocationProviderClient = this.o;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
        final a aVar = new a();
        lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.activitymodes.fragments.b1
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FragmentWorkoutDashboard.z(Function1.this, obj);
            }
        });
    }

    @Subscribe
    public final void onDeviceDisconnected(@NotNull OnWorkoutDeviceDisconnected onWorkoutDeviceDisconnected) {
        Intrinsics.checkNotNullParameter(onWorkoutDeviceDisconnected, "onWorkoutDeviceDisconnected");
        LogHelper.d(this.m, "onWorkoutDeviceDisconnected");
        dismissProgress();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            String string = getString(R.string.device_disconnected);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.device_disconnected)");
            ExtensionFuncsKt.toastShort(activity, string);
        }
    }
}
