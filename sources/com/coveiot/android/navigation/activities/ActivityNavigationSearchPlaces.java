package com.coveiot.android.navigation.activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces;
import com.coveiot.android.navigation.adapter.FavouritePlacesAdapter;
import com.coveiot.android.navigation.adapter.RecentHistoryAdapter;
import com.coveiot.android.navigation.adapter.SearchPlacesAdapter;
import com.coveiot.android.navigation.databinding.ActivityNavigationSearchPlacesBinding;
import com.coveiot.android.navigation.db.entity.EntityRecentSearchHistory;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.android.navigation.interfaces.AutoSuggestPlaceListener;
import com.coveiot.android.navigation.interfaces.FavouritePlaceListener;
import com.coveiot.android.navigation.interfaces.RecentHistorySearchListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.navigation.network.NetworkMonitor;
import com.coveiot.android.navigation.network.NetworkMonitorObserver;
import com.coveiot.android.navigation.repository.RecentSearchHistoryRepository;
import com.coveiot.android.navigation.utils.NavigationUtilsKt;
import com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationSearchPlaces extends BaseActivity implements FavouritePlaceListener, RecentHistorySearchListener, AutoSuggestPlaceListener {
    public ActivityNavigationSearchPlacesBinding binding;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;
    public ImageView settingsIcon;
    public boolean t;
    public TextView titleTextView;
    public FusedLocationProviderClient u;
    @Nullable
    public Location v;
    public ActivityNavigationSearchPlacesViewModel w;
    public boolean x;
    @Nullable
    public BottomSheetDialogImageTitleMessage z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public String p = "";
    public String q = ActivityNavigationSearchPlaces.class.getSimpleName();
    public final int r = 1003;
    @NotNull
    public final Lazy y = LazyKt__LazyJVMKt.lazy(new e());

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function3<GetFavouritePlacesRes, String, Boolean, Unit> {
        public a() {
            super(3);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(GetFavouritePlacesRes getFavouritePlacesRes, String str, Boolean bool) {
            invoke(getFavouritePlacesRes, str, bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(@Nullable GetFavouritePlacesRes getFavouritePlacesRes, @NotNull String msg, boolean z) {
            List<FavouritePlace> favouritePlaceList;
            Intrinsics.checkNotNullParameter(msg, "msg");
            if (z) {
                ActivityNavigationSearchPlaces.this.dismissProgress();
                List<FavouritePlace> favouritePlaceList2 = getFavouritePlacesRes != null ? getFavouritePlacesRes.getFavouritePlaceList() : null;
                int i = 0;
                if (favouritePlaceList2 == null || favouritePlaceList2.isEmpty()) {
                    ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                    activityNavigationSearchPlaces.N(NavigationUtilsKt.getFTUFavouritePlaces(activityNavigationSearchPlaces));
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (getFavouritePlacesRes != null && (favouritePlaceList = getFavouritePlacesRes.getFavouritePlaceList()) != null) {
                    for (Object obj : favouritePlaceList) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                        }
                        FavouritePlace favouritePlace = (FavouritePlace) obj;
                        arrayList2.add(new FavouritePlaceData(favouritePlace.getId(), favouritePlace.getMapApi(), favouritePlace.getMapplsPin(), favouritePlace.getSortIndex(), favouritePlace.getLabel(), favouritePlace.getName(), favouritePlace.getFullAddress(), favouritePlace.getLocation()));
                        arrayList.add(Integer.valueOf(favouritePlace.getSortIndex().intValue() - 1));
                        i = i2;
                    }
                }
                ActivityNavigationSearchPlaces.this.N(NavigationUtilsKt.replaceItemsInList(NavigationUtilsKt.getFTUFavouritePlaces(ActivityNavigationSearchPlaces.this), arrayList, arrayList2));
                return;
            }
            ActivityNavigationSearchPlaces.this.dismissProgress();
            ActivityNavigationSearchPlaces activityNavigationSearchPlaces2 = ActivityNavigationSearchPlaces.this;
            String string = activityNavigationSearchPlaces2.getString(R.string.some_thing_went_wrong);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
            activityNavigationSearchPlaces2.showErrorDialog(string);
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<Location, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Location location) {
            if (location != null) {
                ActivityNavigationSearchPlaces.this.v = location;
                ActivityNavigationSearchPlaces.this.D();
                return;
            }
            ActivityNavigationSearchPlaces.this.dismissProgress();
            ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
            String string = activityNavigationSearchPlaces.getString(R.string.unable_to_fetch_location);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
            activityNavigationSearchPlaces.showErrorDialog(string);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$initListeners$2$1", f = "ActivityNavigationSearchPlaces.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public c(Continuation<? super c> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                RecentSearchHistoryRepository.Companion.getInstance(ActivityNavigationSearchPlaces.this).clearRecentSearchHistory();
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$loadRecentSearchHistory$1", f = "ActivityNavigationSearchPlaces.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(ActivityNavigationSearchPlaces activityNavigationSearchPlaces, List it) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            if (!(!it.isEmpty())) {
                activityNavigationSearchPlaces.P();
                return;
            }
            RecentHistoryAdapter recentHistoryAdapter = new RecentHistoryAdapter(it, false, activityNavigationSearchPlaces);
            if (it.size() >= 6) {
                TextView textView = activityNavigationSearchPlaces.getBinding().tvRecentHistoryViewMoreSearchPlacesActivity;
                Intrinsics.checkNotNullExpressionValue(textView, "binding.tvRecentHistoryV…wMoreSearchPlacesActivity");
                activityNavigationSearchPlaces.visible(textView);
            } else {
                TextView textView2 = activityNavigationSearchPlaces.getBinding().tvRecentHistoryViewMoreSearchPlacesActivity;
                Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvRecentHistoryV…wMoreSearchPlacesActivity");
                activityNavigationSearchPlaces.gone(textView2);
            }
            activityNavigationSearchPlaces.getBinding().rvRecentHistorySearchPlacesActivity.setAdapter(recentHistoryAdapter);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new d(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((d) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!ActivityNavigationSearchPlaces.this.isFinishing()) {
                    ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityNavigationSearchPlaces.this.w;
                    ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel2 = null;
                    if (activityNavigationSearchPlacesViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityNavigationSearchPlacesViewModel = null;
                    }
                    activityNavigationSearchPlacesViewModel.getRecentSearchHistory();
                    ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel3 = ActivityNavigationSearchPlaces.this.w;
                    if (activityNavigationSearchPlacesViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        activityNavigationSearchPlacesViewModel2 = activityNavigationSearchPlacesViewModel3;
                    }
                    LiveData<List<RecentSearchHistoryData>> recentSearchHistoryData = activityNavigationSearchPlacesViewModel2.getRecentSearchHistoryData();
                    final ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                    recentSearchHistoryData.observe(activityNavigationSearchPlaces, new Observer() { // from class: com.coveiot.android.navigation.activities.v0
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj2) {
                            ActivityNavigationSearchPlaces.d.invokeSuspend$lambda$0(ActivityNavigationSearchPlaces.this, (List) obj2);
                        }
                    });
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class e extends Lambda implements Function0<NetworkMonitorObserver> {
        public e() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final NetworkMonitorObserver invoke() {
            return new NetworkMonitorObserver(ActivityNavigationSearchPlaces.this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$onPlaceSelected$1", f = "ActivityNavigationSearchPlaces.kt", i = {}, l = {436}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ AutoSuggestionData $autoSuggestionData;
        public int label;
        public final /* synthetic */ ActivityNavigationSearchPlaces this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(AutoSuggestionData autoSuggestionData, ActivityNavigationSearchPlaces activityNavigationSearchPlaces, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$autoSuggestionData = autoSuggestionData;
            this.this$0 = activityNavigationSearchPlaces;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$autoSuggestionData, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            Object coroutine_suspended = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                EntityRecentSearchHistory entityRecentSearchHistory = new EntityRecentSearchHistory(this.$autoSuggestionData.getPlaceName(), this.$autoSuggestionData.getPlaceAddress(), this.$autoSuggestionData.getDistance(), this.$autoSuggestionData.getOrderIndex(), this.$autoSuggestionData.getType(), this.$autoSuggestionData.getMapplsPin(), System.currentTimeMillis());
                this.label = 1;
                if (RecentSearchHistoryRepository.Companion.getInstance(this.this$0).insertSelectedPlace(entityRecentSearchHistory, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            } else {
                ResultKt.throwOnFailure(obj);
            }
            ActivityNavigationSearchPlaces activityNavigationSearchPlaces = this.this$0;
            AutoSuggestionData autoSuggestionData = this.$autoSuggestionData;
            activityNavigationSearchPlaces.C(autoSuggestionData, autoSuggestionData.getPlaceName(), false);
            return Unit.INSTANCE;
        }
    }

    public static final void F(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void G(ActivityNavigationSearchPlaces this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.dismissProgress();
        String string = this$0.getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        this$0.showErrorDialog(string);
        LogHelper.d(this$0.q, "currentLocation addOnFailureListener ");
    }

    public static final void J(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ActivityNavigationRecentHistory.class));
    }

    public static final void K(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), Dispatchers.getIO(), null, new c(null), 2, null);
    }

    public static final void L(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.C(null, null, true);
    }

    public static final void M(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.C(null, null, true);
    }

    public static final void R(ActivityNavigationSearchPlaces this$0, ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (arrayList != null) {
            this$0.dismissProgress();
            this$0.C((AutoSuggestionData) arrayList.get(0), ((AutoSuggestionData) arrayList.get(0)).getPlaceName(), false);
        }
    }

    public static final void U(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBinding().ivSearchPlacesSearchIcon.setVisibility(0);
        this$0.getBinding().etSearchPlaceAddress.getText().clear();
    }

    public static final void X(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.startActivity(new Intent(this$0, ActivityNavigationSettings.class));
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.watch_disconnected_msg), 1).show();
        }
    }

    public static final void Y(ActivityNavigationSearchPlaces this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void A() {
        if (AppUtils.isNetConnected(this)) {
            if (!AppUtils.isGpsEnabled(this)) {
                Toast.makeText(this, getString(R.string.please_check_gps), 0).show();
                return;
            } else {
                B();
                return;
            }
        }
        String string = getString(R.string.please_enable_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enable_internet)");
        Z(string);
    }

    public final void B() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$checkLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                ActivityCompat.requestPermissions(activityNavigationSearchPlaces, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, activityNavigationSearchPlaces.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                String string = activityNavigationSearchPlaces.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationSearchPlaces.Z(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNavigationSearchPlaces.this.E();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                String string = activityNavigationSearchPlaces.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationSearchPlaces.Z(string);
            }
        });
    }

    public final void C(AutoSuggestionData autoSuggestionData, String str, boolean z) {
        Intent intent = new Intent();
        intent.putExtra("searchString", str);
        intent.putExtra("isFromSource", this.x);
        intent.putExtra("isUserChoosenCurrentLocationAsLocation", z);
        if (!z) {
            if (Intrinsics.areEqual(this.p, "ActivityNavigationMain")) {
                SessionManager.getInstance(this).saveTempDestinationAutosuggestionData(new Gson().toJson(autoSuggestionData));
            } else if (this.x) {
                SessionManager.getInstance(this).saveTempSourceAutosuggestionData(new Gson().toJson(autoSuggestionData));
            } else {
                SessionManager.getInstance(this).saveTempDestinationAutosuggestionData(new Gson().toJson(autoSuggestionData));
            }
        }
        setResult(-1, intent);
        finish();
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            hideKeyBoard(currentFocus);
        }
    }

    public final void D() {
        ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = this.w;
        if (activityNavigationSearchPlacesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityNavigationSearchPlacesViewModel = null;
        }
        activityNavigationSearchPlacesViewModel.getFavouritePlaces(new a());
    }

    public final void E() {
        showProgress();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProvider…tyNavigationSearchPlaces)");
        this.u = fusedLocationProviderClient;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient2 = this.u;
            if (fusedLocationProviderClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                fusedLocationProviderClient2 = null;
            }
            Task<Location> currentLocation = fusedLocationProviderClient2.getCurrentLocation(100, (CancellationToken) null);
            final b bVar = new b();
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.navigation.activities.u0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ActivityNavigationSearchPlaces.F(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.navigation.activities.t0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityNavigationSearchPlaces.G(ActivityNavigationSearchPlaces.this, exc);
                }
            });
        }
    }

    public final NetworkMonitor H() {
        return (NetworkMonitor) this.y.getValue();
    }

    public final void I() {
        getBinding().tvRecentHistoryViewMoreSearchPlacesActivity.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.r0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSearchPlaces.J(ActivityNavigationSearchPlaces.this, view);
            }
        });
        getBinding().tvRecentHistoryClearAllSearchPlacesActivity.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.p0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSearchPlaces.K(ActivityNavigationSearchPlaces.this, view);
            }
        });
        getBinding().tvYourCurrentLocationForSearchResults.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.o0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSearchPlaces.L(ActivityNavigationSearchPlaces.this, view);
            }
        });
        getBinding().tvYourCurrentLocationForRecentHistory.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.n0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSearchPlaces.M(ActivityNavigationSearchPlaces.this, view);
            }
        });
        T();
    }

    public final void N(ArrayList<FavouritePlaceData> arrayList) {
        getBinding().rvTagsSearchPlacesActivity.setAdapter(new FavouritePlacesAdapter(this, arrayList));
    }

    public final void O() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new d(null), 2, null);
    }

    public final void P() {
        RecyclerView recyclerView = getBinding().rvAutoSuggestResults;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvAutoSuggestResults");
        gone(recyclerView);
        TextView textView = getBinding().tvYourCurrentLocationForRecentHistory;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvYourCurrentLocationForRecentHistory");
        gone(textView);
        TextView textView2 = getBinding().tvYourCurrentLocationForSearchResults;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvYourCurrentLocationForSearchResults");
        gone(textView2);
        RecyclerView recyclerView2 = getBinding().rvTagsSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvTagsSearchPlacesActivity");
        visible(recyclerView2);
        ConstraintLayout constraintLayout = getBinding().clRecentHistoryViewSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRecentHistoryViewSearchPlacesActivity");
        gone(constraintLayout);
        ConstraintLayout constraintLayout2 = getBinding().clNoRecentHistoryViewSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clNoRecentHistoryViewSearchPlacesActivity");
        visible(constraintLayout2);
    }

    public final void Q() {
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ActivityNavigationSearchPlaces$observeNetworkStatus$1(this, null), 3, null);
    }

    public final void S() {
        RecyclerView recyclerView = getBinding().rvAutoSuggestResults;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvAutoSuggestResults");
        gone(recyclerView);
        if (Intrinsics.areEqual(this.p, "ActivityNavigationMain")) {
            TextView textView = getBinding().tvYourCurrentLocationForRecentHistory;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvYourCurrentLocationForRecentHistory");
            gone(textView);
            TextView textView2 = getBinding().tvYourCurrentLocationForSearchResults;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvYourCurrentLocationForSearchResults");
            gone(textView2);
        } else if (this.x) {
            TextView textView3 = getBinding().tvYourCurrentLocationForRecentHistory;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvYourCurrentLocationForRecentHistory");
            visible(textView3);
            TextView textView4 = getBinding().tvYourCurrentLocationForSearchResults;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvYourCurrentLocationForSearchResults");
            gone(textView4);
        } else {
            TextView textView5 = getBinding().tvYourCurrentLocationForRecentHistory;
            Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvYourCurrentLocationForRecentHistory");
            gone(textView5);
            TextView textView6 = getBinding().tvYourCurrentLocationForSearchResults;
            Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvYourCurrentLocationForSearchResults");
            gone(textView6);
        }
        RecyclerView recyclerView2 = getBinding().rvTagsSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvTagsSearchPlacesActivity");
        visible(recyclerView2);
        ConstraintLayout constraintLayout = getBinding().clRecentHistoryViewSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRecentHistoryViewSearchPlacesActivity");
        visible(constraintLayout);
        ConstraintLayout constraintLayout2 = getBinding().clNoRecentHistoryViewSearchPlacesActivity;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clNoRecentHistoryViewSearchPlacesActivity");
        gone(constraintLayout2);
        O();
    }

    public final void T() {
        S();
        getBinding().ivSearchPlacesClearIcon.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.l0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationSearchPlaces.U(ActivityNavigationSearchPlaces.this, view);
            }
        });
        getBinding().etSearchPlaceAddress.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$searchListeners$2

            @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$searchListeners$2$onTextChanged$1", f = "ActivityNavigationSearchPlaces.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ CharSequence $charSequence;
                public int label;
                public final /* synthetic */ ActivityNavigationSearchPlaces this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityNavigationSearchPlaces activityNavigationSearchPlaces, CharSequence charSequence, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityNavigationSearchPlaces;
                    this.$charSequence = charSequence;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invokeSuspend$lambda$0(ActivityNavigationSearchPlaces activityNavigationSearchPlaces, CharSequence charSequence, ArrayList arrayList) {
                    if (arrayList != null) {
                        activityNavigationSearchPlaces.getBinding().rvAutoSuggestResults.setAdapter(new SearchPlacesAdapter(arrayList, activityNavigationSearchPlaces));
                        return;
                    }
                    Toast.makeText(activityNavigationSearchPlaces, "No results found for " + ((Object) charSequence), 0).show();
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, this.$charSequence, continuation);
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
                        if (!this.this$0.isFinishing()) {
                            ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = this.this$0.w;
                            if (activityNavigationSearchPlacesViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                activityNavigationSearchPlacesViewModel = null;
                            }
                            MutableLiveData<ArrayList<AutoSuggestionData>> autoSearchData = activityNavigationSearchPlacesViewModel.getAutoSearchData();
                            final ActivityNavigationSearchPlaces activityNavigationSearchPlaces = this.this$0;
                            final CharSequence charSequence = this.$charSequence;
                            autoSearchData.observe(activityNavigationSearchPlaces, 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002d: INVOKE  
                                  (r4v8 'autoSearchData' androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>>)
                                  (r0v2 'activityNavigationSearchPlaces' com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces)
                                  (wrap: androidx.lifecycle.Observer<? super java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>> : 0x002a: CONSTRUCTOR  (r2v0 androidx.lifecycle.Observer<? super java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>> A[REMOVE]) = 
                                  (r0v2 'activityNavigationSearchPlaces' com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces A[DONT_INLINE])
                                  (r1v0 'charSequence' java.lang.CharSequence A[DONT_INLINE])
                                 call: com.coveiot.android.navigation.activities.z0.<init>(com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces, java.lang.CharSequence):void type: CONSTRUCTOR)
                                 type: VIRTUAL call: androidx.lifecycle.LiveData.observe(androidx.lifecycle.LifecycleOwner, androidx.lifecycle.Observer):void in method: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$searchListeners$2.a.invokeSuspend(java.lang.Object):java.lang.Object, file: classes5.dex
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
                                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
                                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
                                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
                                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
                                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.navigation.activities.z0, state: NOT_LOADED
                                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
                                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
                                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
                                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
                                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
                                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                                	... 29 more
                                */
                            /*
                                this = this;
                                kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
                                int r0 = r3.label
                                if (r0 != 0) goto L33
                                kotlin.ResultKt.throwOnFailure(r4)
                                com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces r4 = r3.this$0
                                boolean r4 = r4.isFinishing()
                                if (r4 != 0) goto L30
                                com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces r4 = r3.this$0
                                com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel r4 = com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces.access$getViewModel$p(r4)
                                if (r4 != 0) goto L20
                                java.lang.String r4 = "viewModel"
                                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
                                r4 = 0
                            L20:
                                androidx.lifecycle.MutableLiveData r4 = r4.getAutoSearchData()
                                com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces r0 = r3.this$0
                                java.lang.CharSequence r1 = r3.$charSequence
                                com.coveiot.android.navigation.activities.z0 r2 = new com.coveiot.android.navigation.activities.z0
                                r2.<init>(r0, r1)
                                r4.observe(r0, r2)
                            L30:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            L33:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r0)
                                throw r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$searchListeners$2.a.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(@Nullable Editable editable) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                        ActivityNavigationSearchPlaces.this.getBinding().ivSearchPlacesSearchIcon.setVisibility(8);
                        ActivityNavigationSearchPlaces.this.getBinding().etSearchPlaceAddress.setTextColor(ContextCompat.getColor(ActivityNavigationSearchPlaces.this, R.color.color_b3b3b3));
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                        Location location;
                        Location location2;
                        CharSequence trim;
                        Integer valueOf = (charSequence == null || (trim = StringsKt__StringsKt.trim(charSequence)) == null) ? null : Integer.valueOf(trim.length());
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 3) {
                            ActivityNavigationSearchPlaces.this.V();
                            location = ActivityNavigationSearchPlaces.this.v;
                            if (location != null) {
                                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityNavigationSearchPlaces.this.w;
                                if (activityNavigationSearchPlacesViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    activityNavigationSearchPlacesViewModel = null;
                                }
                                location2 = ActivityNavigationSearchPlaces.this.v;
                                Intrinsics.checkNotNull(location2);
                                activityNavigationSearchPlacesViewModel.autoSuggestPlace(location2, charSequence.toString());
                            } else {
                                ActivityNavigationSearchPlaces activityNavigationSearchPlaces = ActivityNavigationSearchPlaces.this;
                                String string = activityNavigationSearchPlaces.getString(com.coveiot.android.theme.R.string.unable_to_fetch_location);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…unable_to_fetch_location)");
                                activityNavigationSearchPlaces.showErrorDialog(string);
                            }
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityNavigationSearchPlaces.this), Dispatchers.getMain(), null, new a(ActivityNavigationSearchPlaces.this, charSequence, null), 2, null);
                        } else {
                            if (charSequence == null || charSequence.length() == 0) {
                                ActivityNavigationSearchPlaces.this.getBinding().ivSearchPlacesClearIcon.setVisibility(4);
                                ActivityNavigationSearchPlaces.this.S();
                            } else {
                                ActivityNavigationSearchPlaces.this.getBinding().ivSearchPlacesClearIcon.setVisibility(0);
                            }
                        }
                        ViewGroup.LayoutParams layoutParams = ActivityNavigationSearchPlaces.this.getBinding().etSearchPlaceAddress.getLayoutParams();
                        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.setMarginStart(ActivityNavigationSearchPlaces.this.getResources().getDimensionPixelSize(R.dimen.margin_8dp));
                        ActivityNavigationSearchPlaces.this.getBinding().etSearchPlaceAddress.setLayoutParams(marginLayoutParams);
                        ActivityNavigationSearchPlaces.this.getBinding().etSearchPlaceAddress.setTextColor(ActivityNavigationSearchPlaces.this.getColor(R.color.white));
                        ActivityNavigationSearchPlaces.this.getBinding().ivSearchPlacesSearchIcon.setVisibility(0);
                    }
                });
            }

            public final void V() {
                RecyclerView recyclerView = getBinding().rvAutoSuggestResults;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvAutoSuggestResults");
                visible(recyclerView);
                if (Intrinsics.areEqual(this.p, "ActivityNavigationMain")) {
                    TextView textView = getBinding().tvYourCurrentLocationForRecentHistory;
                    Intrinsics.checkNotNullExpressionValue(textView, "binding.tvYourCurrentLocationForRecentHistory");
                    gone(textView);
                    TextView textView2 = getBinding().tvYourCurrentLocationForSearchResults;
                    Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvYourCurrentLocationForSearchResults");
                    gone(textView2);
                } else if (this.x) {
                    TextView textView3 = getBinding().tvYourCurrentLocationForRecentHistory;
                    Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvYourCurrentLocationForRecentHistory");
                    gone(textView3);
                    TextView textView4 = getBinding().tvYourCurrentLocationForSearchResults;
                    Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvYourCurrentLocationForSearchResults");
                    visible(textView4);
                } else {
                    TextView textView5 = getBinding().tvYourCurrentLocationForRecentHistory;
                    Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvYourCurrentLocationForRecentHistory");
                    gone(textView5);
                    TextView textView6 = getBinding().tvYourCurrentLocationForSearchResults;
                    Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvYourCurrentLocationForSearchResults");
                    gone(textView6);
                }
                RecyclerView recyclerView2 = getBinding().rvTagsSearchPlacesActivity;
                Intrinsics.checkNotNullExpressionValue(recyclerView2, "binding.rvTagsSearchPlacesActivity");
                gone(recyclerView2);
                ConstraintLayout constraintLayout = getBinding().clRecentHistoryViewSearchPlacesActivity;
                Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clRecentHistoryViewSearchPlacesActivity");
                gone(constraintLayout);
                ConstraintLayout constraintLayout2 = getBinding().clNoRecentHistoryViewSearchPlacesActivity;
                Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clNoRecentHistoryViewSearchPlacesActivity");
                gone(constraintLayout2);
            }

            public final void W() {
                View findViewById = findViewById(R.id.toolbar_title);
                Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
                setTitleTextView((TextView) findViewById);
                getTitleTextView().setText(getResources().getString(R.string.navigation));
                View findViewById2 = findViewById(R.id.ivButton);
                Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<ImageView>(R.id.ivButton)");
                setSettingsIcon((ImageView) findViewById2);
                getSettingsIcon().setImageResource(R.drawable.ic_settings);
                getSettingsIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.q0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSearchPlaces.X(ActivityNavigationSearchPlaces.this, view);
                    }
                });
                ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.m0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityNavigationSearchPlaces.Y(ActivityNavigationSearchPlaces.this, view);
                    }
                });
                I();
            }

            public final void Z(final String str) {
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
                String title;
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.s;
                boolean z = true;
                if (bottomSheetDialogOneButtonOneTitle2 != null) {
                    Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
                    Intrinsics.checkNotNull(valueOf);
                    z = true ^ valueOf.booleanValue();
                }
                if (this.s == null || z) {
                    this.s = new BottomSheetDialogOneButtonOneTitle(this, str);
                }
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.s;
                if (bottomSheetDialogOneButtonOneTitle3 != null) {
                    String string = getString(com.coveiot.android.theme.R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
                    bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$showPermissionDialog$1
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                            bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationSearchPlaces.this.s;
                            if (bottomSheetDialogOneButtonOneTitle4 != null) {
                                bottomSheetDialogOneButtonOneTitle4.dismiss();
                            }
                            if (str.equals(ActivityNavigationSearchPlaces.this.getString(R.string.please_enable_internet))) {
                                ActivityNavigationSearchPlaces.this.finish();
                                return;
                            }
                            ActivityNavigationSearchPlaces.this.setFromAppSettings(true);
                            AppUtils.openAppSettings(ActivityNavigationSearchPlaces.this, 121);
                        }
                    });
                }
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.s;
                Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf2);
                if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.s) != null) {
                    bottomSheetDialogOneButtonOneTitle.show();
                }
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
            public final ActivityNavigationSearchPlacesBinding getBinding() {
                ActivityNavigationSearchPlacesBinding activityNavigationSearchPlacesBinding = this.binding;
                if (activityNavigationSearchPlacesBinding != null) {
                    return activityNavigationSearchPlacesBinding;
                }
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                return null;
            }

            @NotNull
            public final String getFrom() {
                return this.p;
            }

            @Nullable
            public final BottomSheetDialogImageTitleMessage getInternetAlertDialog() {
                return this.z;
            }

            public final int getLOCATION_PERMISSION_REQUEST_CODE() {
                return this.r;
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
                return this.t;
            }

            public final boolean isFromSource() {
                return this.x;
            }

            @Override // androidx.activity.ComponentActivity, android.app.Activity
            public void onBackPressed() {
                super.onBackPressed();
            }

            @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
            public void onCreate(@Nullable Bundle bundle) {
                super.onCreate(bundle);
                ActivityNavigationSearchPlacesBinding inflate = ActivityNavigationSearchPlacesBinding.inflate(getLayoutInflater());
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
                setBinding(inflate);
                setContentView(getBinding().getRoot());
                this.w = (ActivityNavigationSearchPlacesViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationSearchPlacesViewModel.class);
                if (getIntent() != null && getIntent().getStringExtra("from") != null) {
                    String valueOf = String.valueOf(getIntent().getStringExtra("from"));
                    this.p = valueOf;
                    if (Intrinsics.areEqual(valueOf, "ActivityNavigationShowRoute")) {
                        this.x = getIntent().getBooleanExtra("isFromSource", false);
                    }
                }
                W();
                Q();
                A();
            }

            @Override // com.coveiot.android.navigation.interfaces.FavouritePlaceListener
            public void onFavouritePlaceSelected(boolean z, @Nullable FavouritePlaceData favouritePlaceData, @NotNull ArrayList<FavouritePlaceData> allPlaces) {
                Intrinsics.checkNotNullParameter(allPlaces, "allPlaces");
                if (z) {
                    Intent intent = new Intent(this, ActivityYourPlaces.class);
                    intent.putExtra("favouritePlaceData", new Gson().toJson(allPlaces));
                    startActivity(intent);
                    return;
                }
                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = null;
                if ((favouritePlaceData != null ? favouritePlaceData.getId() : null) == null) {
                    Intent intent2 = new Intent(this, ActivityYourPlaces.class);
                    intent2.putExtra("favouritePlaceData", new Gson().toJson(allPlaces));
                    startActivity(intent2);
                    return;
                }
                showProgress();
                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel2 = this.w;
                if (activityNavigationSearchPlacesViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationSearchPlacesViewModel2 = null;
                }
                Location location = this.v;
                Intrinsics.checkNotNull(location);
                String mapplsPin = favouritePlaceData.getMapplsPin();
                Intrinsics.checkNotNull(mapplsPin);
                activityNavigationSearchPlacesViewModel2.autoSuggestPlace(location, mapplsPin);
                if (isFinishing()) {
                    return;
                }
                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel3 = this.w;
                if (activityNavigationSearchPlacesViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                } else {
                    activityNavigationSearchPlacesViewModel = activityNavigationSearchPlacesViewModel3;
                }
                activityNavigationSearchPlacesViewModel.getAutoSearchData().observe(this, new Observer() { // from class: com.coveiot.android.navigation.activities.s0
                    @Override // androidx.lifecycle.Observer
                    public final void onChanged(Object obj) {
                        ActivityNavigationSearchPlaces.R(ActivityNavigationSearchPlaces.this, (ArrayList) obj);
                    }
                });
            }

            @Override // com.coveiot.android.navigation.interfaces.AutoSuggestPlaceListener
            public void onPlaceSelected(@NotNull AutoSuggestionData autoSuggestionData) {
                Intrinsics.checkNotNullParameter(autoSuggestionData, "autoSuggestionData");
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(autoSuggestionData, this, null), 3, null);
            }

            @Override // com.coveiot.android.navigation.interfaces.RecentHistorySearchListener
            public void onRecentHistorySelected(@NotNull RecentSearchHistoryData recentSearchHistoryData) {
                Intrinsics.checkNotNullParameter(recentSearchHistoryData, "recentSearchHistoryData");
                C(new AutoSuggestionData(recentSearchHistoryData.getPlaceName(), recentSearchHistoryData.getPlaceAddress(), recentSearchHistoryData.getDistance(), recentSearchHistoryData.getOrderIndex(), recentSearchHistoryData.getType(), recentSearchHistoryData.getMapplsPin()), recentSearchHistoryData.getPlaceName(), false);
            }

            @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
            public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                Intrinsics.checkNotNullParameter(grantResults, "grantResults");
                super.onRequestPermissionsResult(i, permissions, grantResults);
                if (i == this.r) {
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
                        Z(string);
                    }
                }
            }

            @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
            public void onResume() {
                super.onResume();
                if (this.t) {
                    if (!AppUtils.checkIfLocationPermissionExists(this)) {
                        String string = getString(R.string.location_permission_required);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                        Z(string);
                        return;
                    }
                    E();
                }
            }

            public final void setBinding(@NotNull ActivityNavigationSearchPlacesBinding activityNavigationSearchPlacesBinding) {
                Intrinsics.checkNotNullParameter(activityNavigationSearchPlacesBinding, "<set-?>");
                this.binding = activityNavigationSearchPlacesBinding;
            }

            public final void setFrom(@NotNull String str) {
                Intrinsics.checkNotNullParameter(str, "<set-?>");
                this.p = str;
            }

            public final void setFromAppSettings(boolean z) {
                this.t = z;
            }

            public final void setFromSource(boolean z) {
                this.x = z;
            }

            public final void setInternetAlertDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
                this.z = bottomSheetDialogImageTitleMessage;
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
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.s;
                boolean z = true;
                if (bottomSheetDialogOneButtonOneTitle2 != null) {
                    Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
                    Intrinsics.checkNotNull(valueOf);
                    z = true ^ valueOf.booleanValue();
                }
                if (this.s == null || z) {
                    this.s = new BottomSheetDialogOneButtonOneTitle(this, str);
                }
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.s;
                if (bottomSheetDialogOneButtonOneTitle3 != null) {
                    String string = getString(com.coveiot.android.theme.R.string.ok);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
                    bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationSearchPlaces$showErrorDialog$1
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                            bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationSearchPlaces.this.s;
                            if (bottomSheetDialogOneButtonOneTitle4 != null) {
                                bottomSheetDialogOneButtonOneTitle4.dismiss();
                            }
                            ActivityNavigationSearchPlaces.this.finish();
                        }
                    });
                }
                BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.s;
                Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
                Intrinsics.checkNotNull(valueOf2);
                if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.s) != null) {
                    bottomSheetDialogOneButtonOneTitle.show();
                }
            }
        }
