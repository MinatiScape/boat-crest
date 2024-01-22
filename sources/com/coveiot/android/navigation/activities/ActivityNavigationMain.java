package com.coveiot.android.navigation.activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.activities.ActivityNavigationMain;
import com.coveiot.android.navigation.adapter.FavouritePlacesAdapter;
import com.coveiot.android.navigation.databinding.ActivityNavigationMainBinding;
import com.coveiot.android.navigation.interfaces.FavouritePlaceListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.navigation.utils.MapplsConstants;
import com.coveiot.android.navigation.utils.NavigationUtilsKt;
import com.coveiot.android.navigation.viewModels.NavigationMainViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapConfiguration;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.UiSettings;
import com.mappls.sdk.maps.annotations.IconFactory;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.MarkerOptions;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationMain extends BaseActivity implements OnMapReadyCallback, FavouritePlaceListener {
    public ActivityNavigationMainBinding binding;
    public MapplsMap mMapplsMap;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle q;
    public NavigationMainViewModel r;
    public Marker s;
    public ImageView settingsIcon;
    public TextView titleTextView;
    public FusedLocationProviderClient u;
    @Nullable
    public Location v;
    public boolean w;
    public ActivityResultLauncher<Intent> x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityNavigationMain.class.getSimpleName();
    public final int t = 1003;

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationMain$fetchFavouritePlacesFromServer$1", f = "ActivityNavigationMain.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* renamed from: com.coveiot.android.navigation.activities.ActivityNavigationMain$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0291a extends Lambda implements Function1<Boolean, Unit> {
            public final /* synthetic */ ActivityNavigationMain this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0291a(ActivityNavigationMain activityNavigationMain) {
                super(1);
                this.this$0 = activityNavigationMain;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final void invoke$lambda$1(ActivityNavigationMain this$0, List it) {
                Intrinsics.checkNotNullParameter(this$0, "this$0");
                int i = 0;
                if (it == null || it.isEmpty()) {
                    this$0.N(NavigationUtilsKt.getFTUFavouritePlaces(this$0));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                for (Object obj : it) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    FavouritePlace favouritePlace = (FavouritePlace) obj;
                    arrayList2.add(new FavouritePlaceData(favouritePlace.getId(), favouritePlace.getMapApi(), favouritePlace.getMapplsPin(), favouritePlace.getSortIndex(), favouritePlace.getLabel(), favouritePlace.getName(), favouritePlace.getFullAddress(), favouritePlace.getLocation()));
                    arrayList.add(Integer.valueOf(favouritePlace.getSortIndex().intValue() - 1));
                    i = i2;
                }
                this$0.N(NavigationUtilsKt.replaceItemsInList(NavigationUtilsKt.getFTUFavouritePlaces(this$0), arrayList, arrayList2));
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    NavigationMainViewModel navigationMainViewModel = this.this$0.r;
                    if (navigationMainViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        navigationMainViewModel = null;
                    }
                    LiveData<List<FavouritePlace>> favPlacesData = navigationMainViewModel.getFavPlacesData();
                    final ActivityNavigationMain activityNavigationMain = this.this$0;
                    favPlacesData.observe(activityNavigationMain, new Observer() { // from class: com.coveiot.android.navigation.activities.h0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            ActivityNavigationMain.a.C0291a.invoke$lambda$1(ActivityNavigationMain.this, (List) obj);
                        }
                    });
                    return;
                }
                ActivityNavigationMain activityNavigationMain2 = this.this$0;
                String string = activityNavigationMain2.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                activityNavigationMain2.showErrorDialog(string);
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!ActivityNavigationMain.this.isFinishing()) {
                    NavigationMainViewModel navigationMainViewModel = ActivityNavigationMain.this.r;
                    if (navigationMainViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        navigationMainViewModel = null;
                    }
                    navigationMainViewModel.getFavouritePlaces(new C0291a(ActivityNavigationMain.this));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationMain$getEta$1", f = "ActivityNavigationMain.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ AutoSuggestionData $autoSuggestionData;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<DirectionsResponse, Unit> {
            public final /* synthetic */ AutoSuggestionData $autoSuggestionData;
            public final /* synthetic */ ActivityNavigationMain this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityNavigationMain activityNavigationMain, AutoSuggestionData autoSuggestionData) {
                super(1);
                this.this$0 = activityNavigationMain;
                this.$autoSuggestionData = autoSuggestionData;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                invoke2(directionsResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                if (directionsResponse != null) {
                    this.this$0.U();
                    this.this$0.dismissProgress();
                    this.this$0.X(this.$autoSuggestionData, directionsResponse);
                    return;
                }
                this.this$0.dismissProgress();
                Toast.makeText(this.this$0, "Something went wrong", 1).show();
                LogHelper.d(this.this$0.p, "Direction Api failed");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(AutoSuggestionData autoSuggestionData, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$autoSuggestionData = autoSuggestionData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$autoSuggestionData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (ActivityNavigationMain.this.v != null) {
                    NavigationMainViewModel navigationMainViewModel = ActivityNavigationMain.this.r;
                    if (navigationMainViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        navigationMainViewModel = null;
                    }
                    navigationMainViewModel.getEta(this.$autoSuggestionData, ActivityNavigationMain.this.v, new a(ActivityNavigationMain.this, this.$autoSuggestionData));
                } else {
                    Toast.makeText(ActivityNavigationMain.this, "Something went wrong", 1).show();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<Location, Unit> {
        public c() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Location location) {
            ActivityNavigationMain activityNavigationMain = ActivityNavigationMain.this;
            if (activityNavigationMain.mMapplsMap != null) {
                activityNavigationMain.dismissProgress();
            }
            if (location != null) {
                ActivityNavigationMain.this.v = location;
                ActivityNavigationMain.this.T();
                ActivityNavigationMain.this.B();
                return;
            }
            ActivityNavigationMain activityNavigationMain2 = ActivityNavigationMain.this;
            String string = activityNavigationMain2.getString(R.string.unable_to_fetch_location);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
            activityNavigationMain2.showErrorDialog(string);
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function2<Boolean, ArrayList<AutoSuggestionData>, Unit> {
        public d() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, ArrayList<AutoSuggestionData> arrayList) {
            invoke(bool.booleanValue(), arrayList);
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z, @Nullable ArrayList<AutoSuggestionData> arrayList) {
            if (z) {
                Integer valueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
                Intrinsics.checkNotNull(valueOf);
                if (valueOf.intValue() > 0) {
                    SessionManager.getInstance(ActivityNavigationMain.this).saveTempDestinationAutosuggestionData(new Gson().toJson(arrayList.get(0)));
                    ActivityNavigationMain activityNavigationMain = ActivityNavigationMain.this;
                    AutoSuggestionData autoSuggestionData = arrayList.get(0);
                    Intrinsics.checkNotNullExpressionValue(autoSuggestionData, "autoSuggestionData[0]");
                    activityNavigationMain.C(autoSuggestionData);
                    ActivityNavigationMain activityNavigationMain2 = ActivityNavigationMain.this;
                    AutoSuggestionData autoSuggestionData2 = arrayList.get(0);
                    Intrinsics.checkNotNullExpressionValue(autoSuggestionData2, "autoSuggestionData[0]");
                    activityNavigationMain2.W(autoSuggestionData2);
                    return;
                }
                ActivityNavigationMain.this.dismissProgress();
                ActivityNavigationMain activityNavigationMain3 = ActivityNavigationMain.this;
                String string = activityNavigationMain3.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                activityNavigationMain3.showErrorDialog(string);
                return;
            }
            ActivityNavigationMain.this.dismissProgress();
            ActivityNavigationMain activityNavigationMain4 = ActivityNavigationMain.this;
            String string2 = activityNavigationMain4.getString(R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.some_thing_went_wrong)");
            activityNavigationMain4.showErrorDialog(string2);
        }
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(ActivityNavigationMain this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.dismissProgress();
        String string = this$0.getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        this$0.showErrorDialog(string);
    }

    public static final void H(ActivityNavigationMain this$0, ActivityResult activityResult) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() == -1) {
            Intent data = activityResult.getData();
            if ((data != null ? data.getStringExtra("searchString") : null) != null) {
                Object fromJson = new Gson().fromJson(SessionManager.getInstance(this$0).getTempDestinationAutosuggestionData(), new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$handleSearchResults$1$listType$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(destJsonString, listType)");
                AutoSuggestionData autoSuggestionData = (AutoSuggestionData) fromJson;
                String str = this$0.p;
                LogHelper.d(str, "searchString  " + data.getStringExtra("searchString") + " destinationAutoSuggestionData " + new Gson().toJson(autoSuggestionData));
                this$0.C(autoSuggestionData);
                this$0.W(autoSuggestionData);
            }
        }
    }

    public static final void K(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Marker marker = null;
        this$0.getBinding().tvPlaceLabel.setText((CharSequence) null);
        ImageView imageView = this$0.getBinding().ivPlaceLabelCloseIcon;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPlaceLabelCloseIcon");
        this$0.gone(imageView);
        this$0.I();
        if (this$0.mMapplsMap != null && this$0.s != null) {
            MapplsMap mMapplsMap = this$0.getMMapplsMap();
            Marker marker2 = this$0.s;
            if (marker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("marker");
            } else {
                marker = marker2;
            }
            mMapplsMap.removeMarker(marker);
        }
        this$0.T();
    }

    public static final void L(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            Intent intent = new Intent(this$0, ActivityNavigationSearchPlaces.class);
            intent.putExtra("from", ActivityNavigationMain.class.getSimpleName());
            ActivityResultLauncher<Intent> activityResultLauncher = this$0.x;
            if (activityResultLauncher == null) {
                Intrinsics.throwUninitializedPropertyAccessException("activityResultLauncher");
                activityResultLauncher = null;
            }
            activityResultLauncher.launch(intent);
            this$0.I();
            return;
        }
        Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
    }

    public static final void M(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            Intent intent = new Intent(this$0, ActivityNavigationFTU.class);
            intent.putExtra("isToView", true);
            this$0.startActivity(intent);
            return;
        }
        Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
    }

    public static final void R(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.startActivity(new Intent(this$0, ActivityNavigationSettings.class));
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.watch_disconnected_msg), 1).show();
        }
    }

    public static final void S(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void Y(ActivityNavigationMain this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0)) {
            Intent intent = new Intent(this$0, ActivityNavigationShowRoute.class);
            intent.putExtra("isFromBand", false);
            this$0.startActivity(intent);
            return;
        }
        Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
    }

    public final void A() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$checkLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNavigationMain activityNavigationMain = ActivityNavigationMain.this;
                ActivityCompat.requestPermissions(activityNavigationMain, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, activityNavigationMain.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNavigationMain activityNavigationMain = ActivityNavigationMain.this;
                String string = activityNavigationMain.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationMain.V(string, true);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNavigationMain.this.D();
                LogHelper.d(ActivityNavigationMain.this.p, "checkLocationPermissions onPermissionGranted");
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNavigationMain activityNavigationMain = ActivityNavigationMain.this;
                String string = activityNavigationMain.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationMain.V(string, true);
            }
        });
    }

    public final void B() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
    }

    public final void C(AutoSuggestionData autoSuggestionData) {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new b(autoSuggestionData, null), 3, null);
    }

    public final void D() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProvider…s@ActivityNavigationMain)");
        this.u = fusedLocationProviderClient;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient2 = this.u;
            if (fusedLocationProviderClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                fusedLocationProviderClient2 = null;
            }
            Task<Location> currentLocation = fusedLocationProviderClient2.getCurrentLocation(100, (CancellationToken) null);
            final c cVar = new c();
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.navigation.activities.g0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ActivityNavigationMain.E(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.navigation.activities.f0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityNavigationMain.F(ActivityNavigationMain.this, exc);
                }
            });
        }
    }

    public final void G() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.navigation.activities.e0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityNavigationMain.H(ActivityNavigationMain.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.x = registerForActivityResult;
    }

    public final void I() {
        RecyclerView recyclerView = getBinding().rvTags;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvTags");
        visible(recyclerView);
        LinearLayout linearLayout = getBinding().llSearchLocationInfo;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llSearchLocationInfo");
        visible(linearLayout);
        ConstraintLayout constraintLayout = getBinding().clSelectedPlaceLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSelectedPlaceLayout");
        gone(constraintLayout);
    }

    public final void J() {
        getBinding().ivPlaceLabelCloseIcon.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.K(ActivityNavigationMain.this, view);
            }
        });
        getBinding().llSearchSourceLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.d0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.L(ActivityNavigationMain.this, view);
            }
        });
        getBinding().tvSelectedPlaceDisclaimer.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.c0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.M(ActivityNavigationMain.this, view);
            }
        });
    }

    public final void N(ArrayList<FavouritePlaceData> arrayList) {
        getBinding().rvTags.setAdapter(new FavouritePlacesAdapter(this, arrayList));
    }

    public final void O(String str) {
        if (this.v != null) {
            NavigationMainViewModel navigationMainViewModel = this.r;
            if (navigationMainViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                navigationMainViewModel = null;
            }
            Location location = this.v;
            Intrinsics.checkNotNull(location);
            navigationMainViewModel.autoSuggestPlace(location, str, new d());
            return;
        }
        dismissProgress();
        String string = getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        showErrorDialog(string);
    }

    public final void P() {
        getBinding().mapplsMapviewNavigationMain.getMapAsync(this);
    }

    public final void Q() {
        P();
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        setTitleTextView((TextView) findViewById);
        getTitleTextView().setText(getResources().getString(R.string.navigation));
        View findViewById2 = findViewById(R.id.ivButton);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<ImageView>(R.id.ivButton)");
        setSettingsIcon((ImageView) findViewById2);
        getSettingsIcon().setImageResource(R.drawable.ic_settings);
        getSettingsIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.b0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.R(ActivityNavigationMain.this, view);
            }
        });
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.S(ActivityNavigationMain.this, view);
            }
        });
        J();
    }

    public final void T() {
        if (this.mMapplsMap == null || this.v == null) {
            return;
        }
        MapplsMap mMapplsMap = getMMapplsMap();
        Location location = this.v;
        Intrinsics.checkNotNull(location);
        double latitude = location.getLatitude();
        Location location2 = this.v;
        Intrinsics.checkNotNull(location2);
        mMapplsMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, location2.getLongitude()), 17.0d));
    }

    public final void U() {
        RecyclerView recyclerView = getBinding().rvTags;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvTags");
        gone(recyclerView);
        LinearLayout linearLayout = getBinding().llSearchLocationInfo;
        Intrinsics.checkNotNullExpressionValue(linearLayout, "binding.llSearchLocationInfo");
        gone(linearLayout);
        ConstraintLayout constraintLayout = getBinding().clSelectedPlaceLayout;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clSelectedPlaceLayout");
        visible(constraintLayout);
    }

    public final void V(String str, final boolean z) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        dismissProgress();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.q;
        boolean z2 = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z2 = true ^ valueOf.booleanValue();
        }
        if (this.q == null || z2) {
            this.q = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.q;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$showPermissionDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationMain.this.q;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    if (!z) {
                        ActivityNavigationMain.this.finish();
                        return;
                    }
                    ActivityNavigationMain.this.setFromAppSettings(true);
                    AppUtils.openAppSettings(ActivityNavigationMain.this, 121);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.q;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z2) && (bottomSheetDialogOneButtonOneTitle = this.q) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void W(final AutoSuggestionData autoSuggestionData) {
        if (this.mMapplsMap != null) {
            if (this.s != null) {
                MapplsMap mMapplsMap = getMMapplsMap();
                Marker marker = this.s;
                if (marker == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("marker");
                    marker = null;
                }
                mMapplsMap.removeMarker(marker);
            }
            MarkerOptions title = new MarkerOptions().mapplsPin(autoSuggestionData.getMapplsPin()).icon(IconFactory.getInstance(this).fromResource(R.drawable.destination_marker_icon)).setTitle(autoSuggestionData.getPlaceName());
            Intrinsics.checkNotNullExpressionValue(title, "MarkerOptions().mapplsPi…SuggestionData.placeName)");
            Marker addMarker = getMMapplsMap().addMarker(title, new MapplsMap.OnMarkerAddedListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$showSelectedLocationOnMap$3
                @Override // com.mappls.sdk.maps.MapplsMap.OnMarkerAddedListener
                public void onFailure() {
                    LogHelper.d(ActivityNavigationMain.this.p, "Adding marker failed");
                }

                @Override // com.mappls.sdk.maps.MapplsMap.OnMarkerAddedListener
                public void onSuccess() {
                    ActivityNavigationMain.this.getMMapplsMap().animateCamera(CameraMapplsPinUpdateFactory.newMapplsPinZoom(autoSuggestionData.getMapplsPin(), 17.0d));
                }
            });
            Intrinsics.checkNotNullExpressionValue(addMarker, "private fun showSelected…       })\n        }\n    }");
            this.s = addMarker;
        }
    }

    public final void X(AutoSuggestionData autoSuggestionData, DirectionsResponse directionsResponse) {
        BigDecimal scale;
        getBinding().tvPlaceLabel.setText(autoSuggestionData.getPlaceName());
        getBinding().tvPlaceLabel.setSelected(true);
        ImageView imageView = getBinding().ivPlaceLabelCloseIcon;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPlaceLabelCloseIcon");
        visible(imageView);
        getBinding().tvSelectedPlaceName.setText(autoSuggestionData.getPlaceName());
        getBinding().tvSelectedPlaceAddress.setText(autoSuggestionData.getPlaceAddress());
        Double distance = directionsResponse.routes().get(0).distance();
        Double valueOf = (distance == null || (scale = new BigDecimal(String.valueOf(distance.doubleValue() / 1000.0d)).setScale(2, RoundingMode.HALF_UP)) == null) ? null : Double.valueOf(scale.doubleValue());
        TextView textView = getBinding().tvDistanceToReachSelectedPlace;
        textView.setText(valueOf + " Km");
        TextView textView2 = getBinding().tvReachDurationToSelectedPlace;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Double duration = directionsResponse.routes().get(0).duration();
        Intrinsics.checkNotNull(duration);
        textView2.setText(themesUtils.formatSeconds((long) duration.doubleValue()));
        SessionManager.getInstance(this).saveDirectionApiResponse(directionsResponse.toJson());
        getBinding().btnGetDirections.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.z
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationMain.Y(ActivityNavigationMain.this, view);
            }
        });
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    @NotNull
    public final ActivityNavigationMainBinding getBinding() {
        ActivityNavigationMainBinding activityNavigationMainBinding = this.binding;
        if (activityNavigationMainBinding != null) {
            return activityNavigationMainBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final int getLOCATION_PERMISSION_REQUEST_CODE() {
        return this.t;
    }

    @NotNull
    public final MapplsMap getMMapplsMap() {
        MapplsMap mapplsMap = this.mMapplsMap;
        if (mapplsMap != null) {
            return mapplsMap;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mMapplsMap");
        return null;
    }

    @NotNull
    public final ImageView getSettingsIcon() {
        ImageView imageView = this.settingsIcon;
        if (imageView != null) {
            return imageView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("settingsIcon");
        return null;
    }

    @NotNull
    public final TextView getTitleTextView() {
        TextView textView = this.titleTextView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titleTextView");
        return null;
    }

    public final boolean isFromAppSettings() {
        return this.w;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityNavigationMainBinding inflate = ActivityNavigationMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        this.r = (NavigationMainViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(NavigationMainViewModel.class);
        showProgress();
        z();
        Q();
        MapplsMapConfiguration.getInstance().setDeveloperShowingSplash(true);
        getBinding().mapplsMapviewNavigationMain.onCreate(bundle);
        G();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getBinding().mapplsMapviewNavigationMain.onDestroy();
    }

    @Override // com.coveiot.android.navigation.interfaces.FavouritePlaceListener
    public void onFavouritePlaceSelected(boolean z, @Nullable FavouritePlaceData favouritePlaceData, @NotNull ArrayList<FavouritePlaceData> favouritePlaceData2) {
        Intrinsics.checkNotNullParameter(favouritePlaceData2, "favouritePlaceData");
        if (z) {
            if (AppUtils.isNetConnected(this)) {
                Intent intent = new Intent(this, ActivityYourPlaces.class);
                intent.putExtra("favouritePlaceData", new Gson().toJson(favouritePlaceData2));
                startActivity(intent);
                return;
            }
            Toast.makeText(this, getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
            return;
        }
        if ((favouritePlaceData != null ? favouritePlaceData.getId() : null) == null) {
            if (AppUtils.isNetConnected(this)) {
                Intent intent2 = new Intent(this, ActivityYourPlaces.class);
                intent2.putExtra("favouritePlaceData", new Gson().toJson(favouritePlaceData2));
                startActivity(intent2);
                return;
            }
            Toast.makeText(this, getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
            return;
        }
        showProgress();
        I();
        if (AppUtils.isNetConnected(this)) {
            O(String.valueOf(favouritePlaceData.getMapplsPin()));
            return;
        }
        dismissProgress();
        Toast.makeText(this, getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, @Nullable String str) {
        dismissProgress();
        String string = getString(R.string.some_thing_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
        showErrorDialog(string);
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NotNull MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        setMMapplsMap(mapplsMap);
        if (this.v != null) {
            dismissProgress();
        }
        if (this.mMapplsMap != null) {
            UiSettings uiSettings = getMMapplsMap().getUiSettings();
            boolean z = false;
            if (uiSettings != null) {
                uiSettings.setLogoEnabled(false);
            }
            List<MapplsStyle> styleList = getMMapplsMap().getMapplsAvailableStyles();
            Intrinsics.checkNotNullExpressionValue(styleList, "styleList");
            if (!(styleList instanceof Collection) || !styleList.isEmpty()) {
                Iterator<T> it = styleList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (Intrinsics.areEqual(((MapplsStyle) it.next()).getName(), MapplsConstants.INSTANCE.getDARK_THEME())) {
                        z = true;
                        break;
                    }
                }
            }
            if (z) {
                getMMapplsMap().setMapplsStyle(MapplsConstants.INSTANCE.getDARK_THEME());
            }
        }
        if (this.v != null) {
            T();
        }
        mapplsMap.getStyle(new Style.OnStyleLoaded() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$onMapReady$2
            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public void onStyleLoaded(@NotNull Style p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(@Nullable Intent intent) {
        super.onNewIntent(intent);
        D();
        Marker marker = null;
        getBinding().tvPlaceLabel.setText((CharSequence) null);
        ImageView imageView = getBinding().ivPlaceLabelCloseIcon;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivPlaceLabelCloseIcon");
        gone(imageView);
        I();
        if (this.mMapplsMap != null && this.s != null) {
            MapplsMap mMapplsMap = getMMapplsMap();
            Marker marker2 = this.s;
            if (marker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("marker");
            } else {
                marker = marker2;
            }
            mMapplsMap.removeMarker(marker);
        }
        T();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        getBinding().mapplsMapviewNavigationMain.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.t) {
            boolean z = false;
            if (!(grantResults.length == 0)) {
                int length = grantResults.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        break;
                    } else if (grantResults[i2] != 0) {
                        break;
                    } else {
                        i2++;
                    }
                }
                if (z) {
                    return;
                }
                String string = getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                V(string, true);
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.w) {
            if (!AppUtils.checkIfLocationPermissionExists(this)) {
                String string = getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                V(string, true);
            } else {
                P();
                D();
            }
        }
        if (this.mMapplsMap != null && this.v != null) {
            B();
        }
        getBinding().mapplsMapviewNavigationMain.onResume();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        getBinding().mapplsMapviewNavigationMain.onSaveInstanceState(outState);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        getBinding().mapplsMapviewNavigationMain.onStart();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getBinding().mapplsMapviewNavigationMain.onStop();
    }

    public final void setBinding(@NotNull ActivityNavigationMainBinding activityNavigationMainBinding) {
        Intrinsics.checkNotNullParameter(activityNavigationMainBinding, "<set-?>");
        this.binding = activityNavigationMainBinding;
    }

    public final void setFromAppSettings(boolean z) {
        this.w = z;
    }

    public final void setMMapplsMap(@NotNull MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "<set-?>");
        this.mMapplsMap = mapplsMap;
    }

    public final void setSettingsIcon(@NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.settingsIcon = imageView;
    }

    public final void setTitleTextView(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titleTextView = textView;
    }

    public final void showErrorDialog(String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        dismissProgress();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.q;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.q == null || z) {
            this.q = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.q;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationMain$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationMain.this.q;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    ActivityNavigationMain.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.q;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.q) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void z() {
        if (AppUtils.isNetConnected(this)) {
            if (!AppUtils.isGpsEnabled(this)) {
                Toast.makeText(this, getString(R.string.please_check_gps), 0).show();
                return;
            } else {
                A();
                return;
            }
        }
        String string = getString(R.string.please_enable_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enable_internet)");
        V(string, false);
    }
}
