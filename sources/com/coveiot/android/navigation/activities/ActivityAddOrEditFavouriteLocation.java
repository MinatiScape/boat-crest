package com.coveiot.android.navigation.activities;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.adapter.SearchPlacesAdapter;
import com.coveiot.android.navigation.databinding.ActivityAddOrEditFavouriteLocationBinding;
import com.coveiot.android.navigation.interfaces.AutoSuggestPlaceListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityAddOrEditFavouriteLocation extends BaseActivity implements AutoSuggestPlaceListener {
    public ActivityAddOrEditFavouriteLocationBinding p;
    public ActivityNavigationSearchPlacesViewModel q;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle s;
    public FavouritePlaceData selectedPlace;
    public boolean t;
    public TextView titletextView;
    public FusedLocationProviderClient u;
    @Nullable
    public Location v;
    public TextWatcher w;
    public boolean x;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final int r = 1003;

    /* loaded from: classes5.dex */
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
        public final void invoke2(Location location) {
            ActivityAddOrEditFavouriteLocation.this.dismissProgress();
            if (location != null) {
                ActivityAddOrEditFavouriteLocation.this.v = location;
                ActivityAddOrEditFavouriteLocation.this.N();
                return;
            }
            ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
            String string = activityAddOrEditFavouriteLocation.getString(R.string.unable_to_fetch_location);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
            activityAddOrEditFavouriteLocation.showErrorDialog(string);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$initListeners$3$1", f = "ActivityAddOrEditFavouriteLocation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ FavouritePlace $favouritePlace;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function2<String, Boolean, Unit> {
            public final /* synthetic */ ActivityAddOrEditFavouriteLocation this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation) {
                super(2);
                this.this$0 = activityAddOrEditFavouriteLocation;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull String msg, boolean z) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                if (z) {
                    this.this$0.dismissProgress();
                    this.this$0.showSuccessDialog();
                    return;
                }
                this.this$0.dismissProgress();
                this.this$0.showErrorDialog(msg);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(FavouritePlace favouritePlace, Continuation<? super b> continuation) {
            super(2, continuation);
            this.$favouritePlace = favouritePlace;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new b(this.$favouritePlace, continuation);
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
                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityAddOrEditFavouriteLocation.this.q;
                if (activityNavigationSearchPlacesViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationSearchPlacesViewModel = null;
                }
                activityNavigationSearchPlacesViewModel.saveFavouritePlaceToServer(this.$favouritePlace, new a(ActivityAddOrEditFavouriteLocation.this));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$initListeners$3$2", f = "ActivityAddOrEditFavouriteLocation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ FavouritePlace $favouritePlace;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function2<String, Boolean, Unit> {
            public final /* synthetic */ ActivityAddOrEditFavouriteLocation this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation) {
                super(2);
                this.this$0 = activityAddOrEditFavouriteLocation;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(@NotNull String msg, boolean z) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                if (z) {
                    this.this$0.dismissProgress();
                    this.this$0.showSuccessDialog();
                    return;
                }
                this.this$0.dismissProgress();
                this.this$0.showErrorDialog(msg);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(FavouritePlace favouritePlace, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$favouritePlace = favouritePlace;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$favouritePlace, continuation);
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
                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityAddOrEditFavouriteLocation.this.q;
                if (activityNavigationSearchPlacesViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationSearchPlacesViewModel = null;
                }
                activityNavigationSearchPlacesViewModel.updateFavouritePlaceToServer(this.$favouritePlace, new a(ActivityAddOrEditFavouriteLocation.this));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void C(BottomSheetDialogTwoButtons dialog, ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this$0.p;
        if (activityAddOrEditFavouriteLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding = null;
        }
        activityAddOrEditFavouriteLocationBinding.btnSave.performClick();
    }

    public static final void D(BottomSheetDialogTwoButtons dialog, ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void F(ActivityAddOrEditFavouriteLocation this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.dismissProgress();
        String string = this$0.getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        this$0.showErrorDialog(string);
    }

    public static final void G(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void I(ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this$0.p;
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
        if (activityAddOrEditFavouriteLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding = null;
        }
        EditText editText = activityAddOrEditFavouriteLocationBinding.tvFavouritePlaceTitle;
        editText.setEnabled(true);
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.setClickable(true);
        editText.requestFocus();
        editText.setSelection(editText.getText().length());
        Object systemService = this$0.getSystemService("input_method");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this$0.p;
        if (activityAddOrEditFavouriteLocationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding3;
        }
        inputMethodManager.showSoftInput(activityAddOrEditFavouriteLocationBinding2.tvFavouritePlaceTitle, 1);
    }

    public static final void J(ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this$0.p;
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
        if (activityAddOrEditFavouriteLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding = null;
        }
        activityAddOrEditFavouriteLocationBinding.ivSearchIcon.setVisibility(0);
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this$0.p;
        if (activityAddOrEditFavouriteLocationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding3 = null;
        }
        activityAddOrEditFavouriteLocationBinding3.etFavouritePlaceAddress.getText().clear();
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding4 = this$0.p;
        if (activityAddOrEditFavouriteLocationBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding4;
        }
        activityAddOrEditFavouriteLocationBinding2.btnSave.setEnabled(false);
    }

    public static final void K(ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showProgress();
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this$0.p;
            if (activityAddOrEditFavouriteLocationBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAddOrEditFavouriteLocationBinding = null;
            }
            if (activityAddOrEditFavouriteLocationBinding.tvFavouritePlaceTitle.getText().length() > 3) {
                if (this$0.getSelectedPlace().getId() == null) {
                    FavouritePlace favouritePlace = new FavouritePlace();
                    favouritePlace.setMapApi(NavigationConstants.MAP_API_TYPE);
                    favouritePlace.setMapplsPin(this$0.getSelectedPlace().getMapplsPin());
                    favouritePlace.setSortIndex(this$0.getSelectedPlace().getSortIndex());
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = this$0.p;
                    if (activityAddOrEditFavouriteLocationBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding2 = null;
                    }
                    favouritePlace.setLabel(activityAddOrEditFavouriteLocationBinding2.tvFavouritePlaceTitle.getText().toString());
                    favouritePlace.setName(this$0.getSelectedPlace().getName());
                    favouritePlace.setFullAddress(this$0.getSelectedPlace().getFullAddress());
                    kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new b(favouritePlace, null), 3, null);
                    return;
                }
                FavouritePlace favouritePlace2 = new FavouritePlace();
                favouritePlace2.setId(this$0.getSelectedPlace().getId());
                favouritePlace2.setMapApi(NavigationConstants.MAP_API_TYPE);
                favouritePlace2.setMapplsPin(this$0.getSelectedPlace().getMapplsPin());
                favouritePlace2.setSortIndex(this$0.getSelectedPlace().getSortIndex());
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this$0.p;
                if (activityAddOrEditFavouriteLocationBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding3 = null;
                }
                favouritePlace2.setLabel(activityAddOrEditFavouriteLocationBinding3.tvFavouritePlaceTitle.getText().toString());
                favouritePlace2.setName(this$0.getSelectedPlace().getName());
                favouritePlace2.setFullAddress(this$0.getSelectedPlace().getFullAddress());
                kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new c(favouritePlace2, null), 3, null);
                return;
            }
            this$0.dismissProgress();
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.min_max_characters), 1).show();
        } else if (!AppUtils.isNetConnected(this$0)) {
            this$0.dismissProgress();
            String string = this$0.getString(R.string.no_internet);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.no_internet)");
            this$0.Q(string);
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            this$0.dismissProgress();
            String string2 = this$0.getString(R.string.device_disconnected);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.device_disconnected)");
            this$0.showErrorDialog(string2);
        }
    }

    public static final void P(ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public static final void R(Ref.ObjectRef bottomSheetDialogImageTitleMessage, ActivityAddOrEditFavouriteLocation this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogImageTitleMessage, "$bottomSheetDialogImageTitleMessage");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        T t = bottomSheetDialogImageTitleMessage.element;
        Intrinsics.checkNotNull(t);
        ((BottomSheetDialogImageTitleMessage) t).dismiss();
        this$0.finish();
    }

    public final void A() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$checkLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
                ActivityCompat.requestPermissions(activityAddOrEditFavouriteLocation, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, activityAddOrEditFavouriteLocation.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
                String string = activityAddOrEditFavouriteLocation.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityAddOrEditFavouriteLocation.Q(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityAddOrEditFavouriteLocation.this.E();
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
                String string = activityAddOrEditFavouriteLocation.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityAddOrEditFavouriteLocation.Q(string);
            }
        });
    }

    public final void B() {
        if (this.selectedPlace != null) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.f
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAddOrEditFavouriteLocation.C(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityAddOrEditFavouriteLocation.D(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            if (bottomSheetDialogTwoButtons.isShowing()) {
                return;
            }
            bottomSheetDialogTwoButtons.show();
        }
    }

    public final void E() {
        showProgress();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProvider…dOrEditFavouriteLocation)");
        this.u = fusedLocationProviderClient;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient2 = this.u;
            if (fusedLocationProviderClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                fusedLocationProviderClient2 = null;
            }
            Task<Location> currentLocation = fusedLocationProviderClient2.getCurrentLocation(100, (CancellationToken) null);
            final a aVar = new a();
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.navigation.activities.i
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ActivityAddOrEditFavouriteLocation.G(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.navigation.activities.h
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityAddOrEditFavouriteLocation.F(ActivityAddOrEditFavouriteLocation.this, exc);
                }
            });
        }
    }

    public final void H() {
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this.p;
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
        if (activityAddOrEditFavouriteLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding = null;
        }
        activityAddOrEditFavouriteLocationBinding.ivEditFavouritePlaceTitle.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddOrEditFavouriteLocation.I(ActivityAddOrEditFavouriteLocation.this, view);
            }
        });
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this.p;
        if (activityAddOrEditFavouriteLocationBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAddOrEditFavouriteLocationBinding3 = null;
        }
        activityAddOrEditFavouriteLocationBinding3.ivClearIcon.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddOrEditFavouriteLocation.J(ActivityAddOrEditFavouriteLocation.this, view);
            }
        });
        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding4 = this.p;
        if (activityAddOrEditFavouriteLocationBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding4;
        }
        activityAddOrEditFavouriteLocationBinding2.btnSave.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityAddOrEditFavouriteLocation.K(ActivityAddOrEditFavouriteLocation.this, view);
            }
        });
        M();
    }

    public final void L() {
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$onBackPressedListener$1
            {
                super(true);
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                boolean z;
                boolean z2;
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding;
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2;
                ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
                if (activityAddOrEditFavouriteLocation.selectedPlace != null) {
                    z = activityAddOrEditFavouriteLocation.x;
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = null;
                    if (z) {
                        activityAddOrEditFavouriteLocationBinding2 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding2 = null;
                        }
                        if (!Intrinsics.areEqual(activityAddOrEditFavouriteLocationBinding2.tvFavouritePlaceTitle.getText().toString(), ActivityAddOrEditFavouriteLocation.this.getSelectedPlace().getLabel())) {
                            ActivityAddOrEditFavouriteLocation.this.B();
                            return;
                        }
                    }
                    z2 = ActivityAddOrEditFavouriteLocation.this.x;
                    if (z2) {
                        ActivityAddOrEditFavouriteLocation.this.B();
                        return;
                    }
                    activityAddOrEditFavouriteLocationBinding = ActivityAddOrEditFavouriteLocation.this.p;
                    if (activityAddOrEditFavouriteLocationBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddOrEditFavouriteLocationBinding3 = activityAddOrEditFavouriteLocationBinding;
                    }
                    if (!Intrinsics.areEqual(activityAddOrEditFavouriteLocationBinding3.tvFavouritePlaceTitle.getText().toString(), ActivityAddOrEditFavouriteLocation.this.getSelectedPlace().getLabel()) && ActivityAddOrEditFavouriteLocation.this.getSelectedPlace().getFullAddress() != null) {
                        ActivityAddOrEditFavouriteLocation.this.B();
                    } else {
                        ActivityAddOrEditFavouriteLocation.this.finish();
                    }
                }
            }
        });
    }

    public final void M() {
        this.w = new TextWatcher() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$searchListeners$1

            @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$searchListeners$1$onTextChanged$1", f = "ActivityAddOrEditFavouriteLocation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* loaded from: classes5.dex */
            public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public int label;
                public final /* synthetic */ ActivityAddOrEditFavouriteLocation this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public a(ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation, Continuation<? super a> continuation) {
                    super(2, continuation);
                    this.this$0 = activityAddOrEditFavouriteLocation;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invokeSuspend$lambda$0(ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation, ArrayList arrayList) {
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding;
                    if (arrayList != null) {
                        SearchPlacesAdapter searchPlacesAdapter = new SearchPlacesAdapter(arrayList, activityAddOrEditFavouriteLocation);
                        activityAddOrEditFavouriteLocationBinding = activityAddOrEditFavouriteLocation.p;
                        if (activityAddOrEditFavouriteLocationBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding = null;
                        }
                        activityAddOrEditFavouriteLocationBinding.rvFavouritePlaces.setAdapter(searchPlacesAdapter);
                    }
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                @NotNull
                public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
                    return new a(this.this$0, continuation);
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
                            ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = this.this$0.q;
                            if (activityNavigationSearchPlacesViewModel == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                activityNavigationSearchPlacesViewModel = null;
                            }
                            MutableLiveData<ArrayList<AutoSuggestionData>> autoSearchData = activityNavigationSearchPlacesViewModel.getAutoSearchData();
                            final ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = this.this$0;
                            autoSearchData.observe(activityAddOrEditFavouriteLocation, 
                            /*  JADX ERROR: Method code generation error
                                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002b: INVOKE  
                                  (r3v8 'autoSearchData' androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>>)
                                  (r0v2 'activityAddOrEditFavouriteLocation' com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation)
                                  (wrap: androidx.lifecycle.Observer<? super java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>> : 0x0028: CONSTRUCTOR  (r1v0 androidx.lifecycle.Observer<? super java.util.ArrayList<com.coveiot.android.navigation.models.AutoSuggestionData>> A[REMOVE]) = 
                                  (r0v2 'activityAddOrEditFavouriteLocation' com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation A[DONT_INLINE])
                                 call: com.coveiot.android.navigation.activities.j.<init>(com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation):void type: CONSTRUCTOR)
                                 type: VIRTUAL call: androidx.lifecycle.LiveData.observe(androidx.lifecycle.LifecycleOwner, androidx.lifecycle.Observer):void in method: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$searchListeners$1.a.invokeSuspend(java.lang.Object):java.lang.Object, file: classes5.dex
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
                                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.coveiot.android.navigation.activities.j, state: NOT_LOADED
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
                                int r0 = r2.label
                                if (r0 != 0) goto L31
                                kotlin.ResultKt.throwOnFailure(r3)
                                com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation r3 = r2.this$0
                                boolean r3 = r3.isFinishing()
                                if (r3 != 0) goto L2e
                                com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation r3 = r2.this$0
                                com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel r3 = com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation.access$getViewModel$p(r3)
                                if (r3 != 0) goto L20
                                java.lang.String r3 = "viewModel"
                                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
                                r3 = 0
                            L20:
                                androidx.lifecycle.MutableLiveData r3 = r3.getAutoSearchData()
                                com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation r0 = r2.this$0
                                com.coveiot.android.navigation.activities.j r1 = new com.coveiot.android.navigation.activities.j
                                r1.<init>(r0)
                                r3.observe(r0, r1)
                            L2e:
                                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                                return r3
                            L31:
                                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                                r3.<init>(r0)
                                throw r3
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$searchListeners$1.a.invokeSuspend(java.lang.Object):java.lang.Object");
                        }
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(@Nullable Editable editable) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2;
                        activityAddOrEditFavouriteLocationBinding = ActivityAddOrEditFavouriteLocation.this.p;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = null;
                        if (activityAddOrEditFavouriteLocationBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding = null;
                        }
                        activityAddOrEditFavouriteLocationBinding.ivSearchIcon.setVisibility(8);
                        activityAddOrEditFavouriteLocationBinding2 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityAddOrEditFavouriteLocationBinding3 = activityAddOrEditFavouriteLocationBinding2;
                        }
                        activityAddOrEditFavouriteLocationBinding3.etFavouritePlaceAddress.setTextColor(ContextCompat.getColor(ActivityAddOrEditFavouriteLocation.this, R.color.color_b3b3b3));
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding4;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding5;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding6;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding7;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding8;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding9;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding10;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding11;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding12;
                        Location location;
                        Location location2;
                        CharSequence trim;
                        activityAddOrEditFavouriteLocationBinding = ActivityAddOrEditFavouriteLocation.this.p;
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding13 = null;
                        if (activityAddOrEditFavouriteLocationBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding = null;
                        }
                        activityAddOrEditFavouriteLocationBinding.clSearchLayout.setBackgroundResource(R.drawable.rounded_light_grey_background_new_70dp);
                        Integer valueOf = (charSequence == null || (trim = StringsKt__StringsKt.trim(charSequence)) == null) ? null : Integer.valueOf(trim.length());
                        Intrinsics.checkNotNull(valueOf);
                        if (valueOf.intValue() > 3) {
                            activityAddOrEditFavouriteLocationBinding11 = ActivityAddOrEditFavouriteLocation.this.p;
                            if (activityAddOrEditFavouriteLocationBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityAddOrEditFavouriteLocationBinding11 = null;
                            }
                            activityAddOrEditFavouriteLocationBinding11.ivClearIcon.setVisibility(0);
                            activityAddOrEditFavouriteLocationBinding12 = ActivityAddOrEditFavouriteLocation.this.p;
                            if (activityAddOrEditFavouriteLocationBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityAddOrEditFavouriteLocationBinding12 = null;
                            }
                            activityAddOrEditFavouriteLocationBinding12.rvFavouritePlaces.setVisibility(0);
                            location = ActivityAddOrEditFavouriteLocation.this.v;
                            if (location != null) {
                                ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityAddOrEditFavouriteLocation.this.q;
                                if (activityNavigationSearchPlacesViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    activityNavigationSearchPlacesViewModel = null;
                                }
                                location2 = ActivityAddOrEditFavouriteLocation.this.v;
                                Intrinsics.checkNotNull(location2);
                                activityNavigationSearchPlacesViewModel.autoSuggestPlace(location2, charSequence.toString());
                            } else {
                                ActivityAddOrEditFavouriteLocation activityAddOrEditFavouriteLocation = ActivityAddOrEditFavouriteLocation.this;
                                String string = activityAddOrEditFavouriteLocation.getString(com.coveiot.android.theme.R.string.unable_to_fetch_location);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…unable_to_fetch_location)");
                                activityAddOrEditFavouriteLocation.showErrorDialog(string);
                            }
                            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(ActivityAddOrEditFavouriteLocation.this), Dispatchers.getMain(), null, new a(ActivityAddOrEditFavouriteLocation.this, null), 2, null);
                        } else {
                            if (charSequence == null || charSequence.length() == 0) {
                                activityAddOrEditFavouriteLocationBinding4 = ActivityAddOrEditFavouriteLocation.this.p;
                                if (activityAddOrEditFavouriteLocationBinding4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddOrEditFavouriteLocationBinding4 = null;
                                }
                                activityAddOrEditFavouriteLocationBinding4.ivClearIcon.setVisibility(4);
                                activityAddOrEditFavouriteLocationBinding5 = ActivityAddOrEditFavouriteLocation.this.p;
                                if (activityAddOrEditFavouriteLocationBinding5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddOrEditFavouriteLocationBinding5 = null;
                                }
                                activityAddOrEditFavouriteLocationBinding5.rvFavouritePlaces.setVisibility(8);
                            } else {
                                activityAddOrEditFavouriteLocationBinding2 = ActivityAddOrEditFavouriteLocation.this.p;
                                if (activityAddOrEditFavouriteLocationBinding2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddOrEditFavouriteLocationBinding2 = null;
                                }
                                activityAddOrEditFavouriteLocationBinding2.ivClearIcon.setVisibility(0);
                                activityAddOrEditFavouriteLocationBinding3 = ActivityAddOrEditFavouriteLocation.this.p;
                                if (activityAddOrEditFavouriteLocationBinding3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityAddOrEditFavouriteLocationBinding3 = null;
                                }
                                activityAddOrEditFavouriteLocationBinding3.rvFavouritePlaces.setVisibility(0);
                            }
                        }
                        activityAddOrEditFavouriteLocationBinding6 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding6 = null;
                        }
                        ViewGroup.LayoutParams layoutParams = activityAddOrEditFavouriteLocationBinding6.etFavouritePlaceAddress.getLayoutParams();
                        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.setMarginStart(ActivityAddOrEditFavouriteLocation.this.getResources().getDimensionPixelSize(R.dimen.margin_8dp));
                        activityAddOrEditFavouriteLocationBinding7 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding7 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding7.etFavouritePlaceAddress.setLayoutParams(marginLayoutParams);
                        activityAddOrEditFavouriteLocationBinding8 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding8 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding8.etFavouritePlaceAddress.setTextColor(ActivityAddOrEditFavouriteLocation.this.getColor(R.color.white));
                        activityAddOrEditFavouriteLocationBinding9 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding9 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding9.ivSearchIcon.setVisibility(0);
                        activityAddOrEditFavouriteLocationBinding10 = ActivityAddOrEditFavouriteLocation.this.p;
                        if (activityAddOrEditFavouriteLocationBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityAddOrEditFavouriteLocationBinding13 = activityAddOrEditFavouriteLocationBinding10;
                        }
                        activityAddOrEditFavouriteLocationBinding13.btnSave.setEnabled(false);
                    }
                };
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this.p;
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
                if (activityAddOrEditFavouriteLocationBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding = null;
                }
                EditText editText = activityAddOrEditFavouriteLocationBinding.etFavouritePlaceAddress;
                TextWatcher textWatcher = this.w;
                if (textWatcher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textWatcher");
                    textWatcher = null;
                }
                editText.addTextChangedListener(textWatcher);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this.p;
                if (activityAddOrEditFavouriteLocationBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding3;
                }
                activityAddOrEditFavouriteLocationBinding2.tvFavouritePlaceTitle.addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$searchListeners$2
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(@Nullable Editable editable) {
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                        ((Button) ActivityAddOrEditFavouriteLocation.this._$_findCachedViewById(R.id.btnSave)).setEnabled((String.valueOf(charSequence).length() < 4 || Intrinsics.areEqual(String.valueOf(charSequence), ActivityAddOrEditFavouriteLocation.this.getSelectedPlace().getLabel()) || ActivityAddOrEditFavouriteLocation.this.getSelectedPlace().getFullAddress() == null) ? false : true);
                    }
                });
            }

            public final void N() {
                if (this.selectedPlace != null) {
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this.p;
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
                    if (activityAddOrEditFavouriteLocationBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding = null;
                    }
                    EditText editText = activityAddOrEditFavouriteLocationBinding.tvFavouritePlaceTitle;
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(false);
                    editText.setClickable(false);
                    Integer sortIndex = getSelectedPlace().getSortIndex();
                    boolean z = true;
                    if ((sortIndex != null && sortIndex.intValue() == 1) || (sortIndex != null && sortIndex.intValue() == 2)) {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding3 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding3.ivEditFavouritePlaceTitle.setVisibility(8);
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding4 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding4 = null;
                        }
                        EditText editText2 = activityAddOrEditFavouriteLocationBinding4.tvFavouritePlaceTitle;
                        editText2.setEnabled(false);
                        editText2.setFocusable(false);
                        editText2.setFocusableInTouchMode(false);
                        editText2.setClickable(false);
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding5 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding5 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding5.btnSave.setEnabled(true);
                    } else {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding6 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding6 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding6.btnSave.setEnabled(false);
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding7 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding7 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding7.ivEditFavouritePlaceTitle.setVisibility(0);
                    }
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding8 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding8 = null;
                    }
                    activityAddOrEditFavouriteLocationBinding8.tvFavouritePlaceTitle.setText(getSelectedPlace().getLabel());
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding9 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding9 = null;
                    }
                    EditText editText3 = activityAddOrEditFavouriteLocationBinding9.etFavouritePlaceAddress;
                    TextWatcher textWatcher = this.w;
                    if (textWatcher == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("textWatcher");
                        textWatcher = null;
                    }
                    editText3.removeTextChangedListener(textWatcher);
                    String name = getSelectedPlace().getName();
                    if (!(name == null || name.length() == 0)) {
                        String fullAddress = getSelectedPlace().getFullAddress();
                        if (!(fullAddress == null || fullAddress.length() == 0)) {
                            ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding10 = this.p;
                            if (activityAddOrEditFavouriteLocationBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityAddOrEditFavouriteLocationBinding10 = null;
                            }
                            activityAddOrEditFavouriteLocationBinding10.etFavouritePlaceAddress.setText(getSelectedPlace().getName() + ',' + getSelectedPlace().getFullAddress());
                        }
                    }
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding11 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding11 = null;
                    }
                    Editable text = activityAddOrEditFavouriteLocationBinding11.etFavouritePlaceAddress.getText();
                    if (text != null && text.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding12 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding12 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding12.clSearchLayout.setBackgroundResource(R.drawable.list_item_background_new);
                    } else {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding13 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding13 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding13.ivSearchIcon.setVisibility(0);
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding14 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAddOrEditFavouriteLocationBinding14 = null;
                        }
                        activityAddOrEditFavouriteLocationBinding14.clSearchLayout.setBackgroundResource(R.drawable.rounded_light_grey_background_new_70dp);
                    }
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding15 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding15 = null;
                    }
                    EditText editText4 = activityAddOrEditFavouriteLocationBinding15.etFavouritePlaceAddress;
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding16 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding16 = null;
                    }
                    editText4.setSelection(activityAddOrEditFavouriteLocationBinding16.etFavouritePlaceAddress.length());
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding17 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding17 = null;
                    }
                    EditText editText5 = activityAddOrEditFavouriteLocationBinding17.etFavouritePlaceAddress;
                    TextWatcher textWatcher2 = this.w;
                    if (textWatcher2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("textWatcher");
                        textWatcher2 = null;
                    }
                    editText5.addTextChangedListener(textWatcher2);
                    if (getSelectedPlace().getFullAddress() != null) {
                        ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding18 = this.p;
                        if (activityAddOrEditFavouriteLocationBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding18;
                        }
                        activityAddOrEditFavouriteLocationBinding2.ivClearIcon.setVisibility(0);
                        return;
                    }
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding19 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding19;
                    }
                    activityAddOrEditFavouriteLocationBinding2.ivClearIcon.setVisibility(4);
                    return;
                }
                String string = getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                showErrorDialog(string);
            }

            public final void O() {
                View findViewById = findViewById(R.id.toolbar_title);
                Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
                setTitletextView((TextView) findViewById);
                getTitletextView().setText(getResources().getString(R.string.edit_place));
                ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.b
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAddOrEditFavouriteLocation.P(ActivityAddOrEditFavouriteLocation.this, view);
                    }
                });
                H();
            }

            public final void Q(final String str) {
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
                    bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$showPermissionDialog$1
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                            bottomSheetDialogOneButtonOneTitle4 = ActivityAddOrEditFavouriteLocation.this.s;
                            if (bottomSheetDialogOneButtonOneTitle4 != null) {
                                bottomSheetDialogOneButtonOneTitle4.dismiss();
                            }
                            if (str.equals(ActivityAddOrEditFavouriteLocation.this.getString(R.string.please_enable_internet))) {
                                ActivityAddOrEditFavouriteLocation.this.finish();
                                return;
                            }
                            ActivityAddOrEditFavouriteLocation.this.setFromAppSettings(true);
                            AppUtils.openAppSettings(ActivityAddOrEditFavouriteLocation.this, 121);
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

            public final int getLOCATION_PERMISSION_REQUEST_CODE() {
                return this.r;
            }

            @NotNull
            public final FavouritePlaceData getSelectedPlace() {
                FavouritePlaceData favouritePlaceData = this.selectedPlace;
                if (favouritePlaceData != null) {
                    return favouritePlaceData;
                }
                Intrinsics.throwUninitializedPropertyAccessException("selectedPlace");
                return null;
            }

            @NotNull
            public final TextView getTitletextView() {
                TextView textView = this.titletextView;
                if (textView != null) {
                    return textView;
                }
                Intrinsics.throwUninitializedPropertyAccessException("titletextView");
                return null;
            }

            public final boolean isFromAppSettings() {
                return this.t;
            }

            @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
            public void onCreate(@Nullable Bundle bundle) {
                super.onCreate(bundle);
                ActivityAddOrEditFavouriteLocationBinding inflate = ActivityAddOrEditFavouriteLocationBinding.inflate(getLayoutInflater());
                Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
                this.p = inflate;
                if (inflate == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    inflate = null;
                }
                setContentView(inflate.getRoot());
                this.q = (ActivityNavigationSearchPlacesViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationSearchPlacesViewModel.class);
                Object fromJson = new Gson().fromJson(getIntent().getStringExtra("selectedPlace"), new TypeToken<FavouritePlaceData>() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$onCreate$listType$1
                }.getType());
                Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(intent.g…selectedPlace\"),listType)");
                setSelectedPlace((FavouritePlaceData) fromJson);
                O();
                showProgress();
                z();
                L();
            }

            @Override // com.coveiot.android.navigation.interfaces.AutoSuggestPlaceListener
            public void onPlaceSelected(@NotNull AutoSuggestionData autoSuggestionData) {
                Intrinsics.checkNotNullParameter(autoSuggestionData, "autoSuggestionData");
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding = this.p;
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding2 = null;
                if (activityAddOrEditFavouriteLocationBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding = null;
                }
                activityAddOrEditFavouriteLocationBinding.clSearchLayout.setBackgroundResource(R.drawable.list_item_background_new);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding3 = this.p;
                if (activityAddOrEditFavouriteLocationBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding3 = null;
                }
                EditText editText = activityAddOrEditFavouriteLocationBinding3.etFavouritePlaceAddress;
                TextWatcher textWatcher = this.w;
                if (textWatcher == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textWatcher");
                    textWatcher = null;
                }
                editText.removeTextChangedListener(textWatcher);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding4 = this.p;
                if (activityAddOrEditFavouriteLocationBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding4 = null;
                }
                activityAddOrEditFavouriteLocationBinding4.etFavouritePlaceAddress.setText(autoSuggestionData.getPlaceName() + ',' + autoSuggestionData.getPlaceAddress());
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding5 = this.p;
                if (activityAddOrEditFavouriteLocationBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding5 = null;
                }
                EditText editText2 = activityAddOrEditFavouriteLocationBinding5.etFavouritePlaceAddress;
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding6 = this.p;
                if (activityAddOrEditFavouriteLocationBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding6 = null;
                }
                editText2.setSelection(activityAddOrEditFavouriteLocationBinding6.etFavouritePlaceAddress.length());
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding7 = this.p;
                if (activityAddOrEditFavouriteLocationBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding7 = null;
                }
                EditText editText3 = activityAddOrEditFavouriteLocationBinding7.etFavouritePlaceAddress;
                TextWatcher textWatcher2 = this.w;
                if (textWatcher2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("textWatcher");
                    textWatcher2 = null;
                }
                editText3.addTextChangedListener(textWatcher2);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding8 = this.p;
                if (activityAddOrEditFavouriteLocationBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding8 = null;
                }
                activityAddOrEditFavouriteLocationBinding8.rvFavouritePlaces.setVisibility(8);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding9 = this.p;
                if (activityAddOrEditFavouriteLocationBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding9 = null;
                }
                EditText editText4 = activityAddOrEditFavouriteLocationBinding9.etFavouritePlaceAddress;
                Intrinsics.checkNotNullExpressionValue(editText4, "binding.etFavouritePlaceAddress");
                hideKeyboard(editText4);
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding10 = this.p;
                if (activityAddOrEditFavouriteLocationBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAddOrEditFavouriteLocationBinding10 = null;
                }
                if (activityAddOrEditFavouriteLocationBinding10.tvFavouritePlaceTitle.getText().length() > 3) {
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding11 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding11 = null;
                    }
                    activityAddOrEditFavouriteLocationBinding11.btnSave.setEnabled(true);
                    this.x = true;
                }
                Integer sortIndex = getSelectedPlace().getSortIndex();
                Intrinsics.checkNotNull(sortIndex);
                if (sortIndex.intValue() < 3) {
                    ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding12 = this.p;
                    if (activityAddOrEditFavouriteLocationBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAddOrEditFavouriteLocationBinding12 = null;
                    }
                    activityAddOrEditFavouriteLocationBinding12.btnSave.setEnabled(true);
                    this.x = true;
                }
                getSelectedPlace().setMapplsPin(autoSuggestionData.getMapplsPin());
                FavouritePlaceData selectedPlace = getSelectedPlace();
                ActivityAddOrEditFavouriteLocationBinding activityAddOrEditFavouriteLocationBinding13 = this.p;
                if (activityAddOrEditFavouriteLocationBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityAddOrEditFavouriteLocationBinding2 = activityAddOrEditFavouriteLocationBinding13;
                }
                selectedPlace.setLabel(activityAddOrEditFavouriteLocationBinding2.tvFavouritePlaceTitle.getText().toString());
                getSelectedPlace().setFullAddress(autoSuggestionData.getPlaceAddress());
                getSelectedPlace().setName(autoSuggestionData.getPlaceName());
            }

            @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
            public void onResume() {
                super.onResume();
                if (this.t) {
                    if (!AppUtils.checkIfLocationPermissionExists(this)) {
                        String string = getString(R.string.location_permission_required);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                        Q(string);
                        return;
                    }
                    E();
                }
            }

            public final void setFromAppSettings(boolean z) {
                this.t = z;
            }

            public final void setSelectedPlace(@NotNull FavouritePlaceData favouritePlaceData) {
                Intrinsics.checkNotNullParameter(favouritePlaceData, "<set-?>");
                this.selectedPlace = favouritePlaceData;
            }

            public final void setTitletextView(@NotNull TextView textView) {
                Intrinsics.checkNotNullParameter(textView, "<set-?>");
                this.titletextView = textView;
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
                    bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityAddOrEditFavouriteLocation$showErrorDialog$1
                        @Override // android.view.View.OnClickListener
                        public void onClick(@Nullable View view) {
                            BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                            bottomSheetDialogOneButtonOneTitle4 = ActivityAddOrEditFavouriteLocation.this.s;
                            if (bottomSheetDialogOneButtonOneTitle4 != null) {
                                bottomSheetDialogOneButtonOneTitle4.dismiss();
                            }
                            ActivityAddOrEditFavouriteLocation.this.finish();
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

            /* JADX WARN: Type inference failed for: r7v0, types: [T, com.coveiot.android.theme.BottomSheetDialogImageTitleMessage] */
            public final void showSuccessDialog() {
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                Drawable drawable = getResources().getDrawable(R.drawable.success_image_new_img);
                Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
                String string = getString(R.string.saved);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.saved)");
                String string2 = getString(R.string.location_saved_successfully);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_saved_successfully)");
                ?? bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
                objectRef.element = bottomSheetDialogImageTitleMessage;
                ((BottomSheetDialogImageTitleMessage) bottomSheetDialogImageTitleMessage).showBigIcon();
                T t = objectRef.element;
                Intrinsics.checkNotNull(t);
                ((BottomSheetDialogImageTitleMessage) t).setCancelable(false);
                T t2 = objectRef.element;
                Intrinsics.checkNotNull(t2);
                String string3 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.okay)");
                ((BottomSheetDialogImageTitleMessage) t2).setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.g
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityAddOrEditFavouriteLocation.R(Ref.ObjectRef.this, this, view);
                    }
                });
                T t3 = objectRef.element;
                Intrinsics.checkNotNull(t3);
                if (((BottomSheetDialogImageTitleMessage) t3).isShowing()) {
                    return;
                }
                T t4 = objectRef.element;
                Intrinsics.checkNotNull(t4);
                ((BottomSheetDialogImageTitleMessage) t4).show();
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
                dismissProgress();
                String string = getString(R.string.please_enable_internet);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enable_internet)");
                Q(string);
            }
        }
