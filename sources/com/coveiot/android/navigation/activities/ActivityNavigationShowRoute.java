package com.coveiot.android.navigation.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.databinding.ActivityNavigationShowRouteBinding;
import com.coveiot.android.navigation.interfaces.BandDisclaimerListener;
import com.coveiot.android.navigation.mapplsUtils.DirectionPolylinePlugin;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.network.NetworkMonitor;
import com.coveiot.android.navigation.network.NetworkMonitorObserver;
import com.coveiot.android.navigation.utils.MapplsConstants;
import com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.utils.ThemesUtils;
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
import com.mappls.sdk.geojson.LineString;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.geojson.utils.PolylineUtils;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapConfiguration;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.UiSettings;
import com.mappls.sdk.maps.annotations.IconFactory;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.annotations.MarkerOptions;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.maps.style.model.MapplsStyle;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationShowRoute extends BaseActivity implements MapplsMap.OnMapLongClickListener, MapplsMap.OnMapClickListener, OnMapReadyCallback, BandDisclaimerListener {
    public ActivityNavigationShowRoutesViewModel A;
    public int C;
    public boolean D;
    @Nullable
    public BottomSheetDialogImageTitleMessage E;
    public ActivityResultLauncher<Intent> F;
    @Nullable
    public BottomSheetDialogImageTitleMessage H;
    public ActivityNavigationShowRouteBinding binding;
    public AutoSuggestionData destinationSuggestionData;
    public MapplsMap mMapplsMap;
    public boolean q;
    @Nullable
    public DirectionPolylinePlugin r;
    public Marker s;
    public ImageView settingsIcon;
    public Marker t;
    public TextView titletextView;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle v;
    public boolean w;
    public FusedLocationProviderClient x;
    @Nullable
    public Location y;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityNavigationShowRoute.class.getSimpleName();
    public final int u = 1003;
    public boolean z = true;
    @NotNull
    public String B = "walking";
    @NotNull
    public final Lazy G = LazyKt__LazyJVMKt.lazy(new c());

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$getEta$1", f = "ActivityNavigationShowRoute.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ AutoSuggestionData $destinationSuggestionData;
        public final /* synthetic */ boolean $isSourceIsCurrentLocation;
        public final /* synthetic */ AutoSuggestionData $sourceAutoSuggestionData;
        public int label;
        public final /* synthetic */ ActivityNavigationShowRoute this$0;

        /* renamed from: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0292a extends Lambda implements Function1<DirectionsResponse, Unit> {
            public final /* synthetic */ AutoSuggestionData $destinationSuggestionData;
            public final /* synthetic */ ActivityNavigationShowRoute this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0292a(ActivityNavigationShowRoute activityNavigationShowRoute, AutoSuggestionData autoSuggestionData) {
                super(1);
                this.this$0 = activityNavigationShowRoute;
                this.$destinationSuggestionData = autoSuggestionData;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                invoke2(directionsResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                if (directionsResponse != null) {
                    SessionManager.getInstance(this.this$0).saveDirectionApiResponse(directionsResponse.toJson());
                    this.this$0.C = 0;
                    this.this$0.J(this.$destinationSuggestionData, null, true);
                    ActivityNavigationShowRoute activityNavigationShowRoute = this.this$0;
                    activityNavigationShowRoute.d0(activityNavigationShowRoute.C);
                    return;
                }
                this.this$0.dismissProgress();
                Toast.makeText(this.this$0, "Something went wrong", 1).show();
                LogHelper.d(this.this$0.p, "Direction Api failed");
            }
        }

        /* loaded from: classes5.dex */
        public static final class b extends Lambda implements Function1<DirectionsResponse, Unit> {
            public final /* synthetic */ AutoSuggestionData $destinationSuggestionData;
            public final /* synthetic */ AutoSuggestionData $sourceAutoSuggestionData;
            public final /* synthetic */ ActivityNavigationShowRoute this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(ActivityNavigationShowRoute activityNavigationShowRoute, AutoSuggestionData autoSuggestionData, AutoSuggestionData autoSuggestionData2) {
                super(1);
                this.this$0 = activityNavigationShowRoute;
                this.$destinationSuggestionData = autoSuggestionData;
                this.$sourceAutoSuggestionData = autoSuggestionData2;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                invoke2(directionsResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                if (directionsResponse != null) {
                    this.this$0.C = 0;
                    SessionManager.getInstance(this.this$0).saveDirectionApiResponse(directionsResponse.toJson());
                    ActivityNavigationShowRoute activityNavigationShowRoute = this.this$0;
                    activityNavigationShowRoute.d0(activityNavigationShowRoute.C);
                    this.this$0.J(this.$destinationSuggestionData, this.$sourceAutoSuggestionData, false);
                    return;
                }
                this.this$0.dismissProgress();
                Toast.makeText(this.this$0, "Something went wrong", 1).show();
                LogHelper.d(this.this$0.p, "Direction Api failed");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(boolean z, ActivityNavigationShowRoute activityNavigationShowRoute, AutoSuggestionData autoSuggestionData, AutoSuggestionData autoSuggestionData2, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$isSourceIsCurrentLocation = z;
            this.this$0 = activityNavigationShowRoute;
            this.$destinationSuggestionData = autoSuggestionData;
            this.$sourceAutoSuggestionData = autoSuggestionData2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$isSourceIsCurrentLocation, this.this$0, this.$destinationSuggestionData, this.$sourceAutoSuggestionData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel;
            ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel2;
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (this.$isSourceIsCurrentLocation) {
                    ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel3 = this.this$0.A;
                    if (activityNavigationShowRoutesViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityNavigationShowRoutesViewModel2 = null;
                    } else {
                        activityNavigationShowRoutesViewModel2 = activityNavigationShowRoutesViewModel3;
                    }
                    activityNavigationShowRoutesViewModel2.getEta(this.$destinationSuggestionData, null, this.this$0.y, true, this.this$0.B, false, new C0292a(this.this$0, this.$destinationSuggestionData));
                } else if (this.$sourceAutoSuggestionData != null) {
                    ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel4 = this.this$0.A;
                    if (activityNavigationShowRoutesViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityNavigationShowRoutesViewModel = null;
                    } else {
                        activityNavigationShowRoutesViewModel = activityNavigationShowRoutesViewModel4;
                    }
                    activityNavigationShowRoutesViewModel.getEta(this.$destinationSuggestionData, this.$sourceAutoSuggestionData, null, false, this.this$0.B, false, new b(this.this$0, this.$destinationSuggestionData, this.$sourceAutoSuggestionData));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* loaded from: classes5.dex */
    public static final class b extends Lambda implements Function1<Boolean, Unit> {
        public b() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                ActivityNavigationShowRoute.this.c0();
                return;
            }
            ActivityNavigationShowRoute.this.dismissProgress();
            Toast.makeText(ActivityNavigationShowRoute.this, "Something went wrong", 0).show();
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function0<NetworkMonitorObserver> {
        public c() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final NetworkMonitorObserver invoke() {
            return new NetworkMonitorObserver(ActivityNavigationShowRoute.this);
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function1<Boolean, Unit> {
        public d() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            if (z) {
                ActivityNavigationShowRoute.this.dismissProgress();
                DirectionsResponse directionsApiResponse = DirectionsResponse.fromJson(SessionManager.getInstance(ActivityNavigationShowRoute.this).getDirectionApiResponse());
                ActivityNavigationShowRoute activityNavigationShowRoute = ActivityNavigationShowRoute.this;
                Intrinsics.checkNotNullExpressionValue(directionsApiResponse, "directionsApiResponse");
                activityNavigationShowRoute.a0(true, directionsApiResponse, ActivityNavigationShowRoute.this.C);
                return;
            }
            ActivityNavigationShowRoute.this.dismissProgress();
            Toast.makeText(ActivityNavigationShowRoute.this, "Something went wrong", 0).show();
        }
    }

    /* loaded from: classes5.dex */
    public static final class e extends Lambda implements Function0<Unit> {
        public static final e INSTANCE = new e();

        public e() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$updateEta$1", f = "ActivityNavigationShowRoute.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ AutoSuggestionData $destinationSuggestionData;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<DirectionsResponse, Unit> {
            public final /* synthetic */ AutoSuggestionData $destinationSuggestionData;
            public final /* synthetic */ ActivityNavigationShowRoute this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityNavigationShowRoute activityNavigationShowRoute, AutoSuggestionData autoSuggestionData) {
                super(1);
                this.this$0 = activityNavigationShowRoute;
                this.$destinationSuggestionData = autoSuggestionData;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                invoke2(directionsResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                if (directionsResponse != null) {
                    this.this$0.C = 0;
                    SessionManager.getInstance(this.this$0).saveDirectionApiResponse(directionsResponse.toJson());
                    this.this$0.J(this.$destinationSuggestionData, null, true);
                    ActivityNavigationShowRoute activityNavigationShowRoute = this.this$0;
                    activityNavigationShowRoute.d0(activityNavigationShowRoute.C);
                    return;
                }
                this.this$0.dismissProgress();
                Toast.makeText(this.this$0, "Something went wrong", 1).show();
                LogHelper.d(this.this$0.p, "Direction Api failed");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public f(AutoSuggestionData autoSuggestionData, Continuation<? super f> continuation) {
            super(2, continuation);
            this.$destinationSuggestionData = autoSuggestionData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new f(this.$destinationSuggestionData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((f) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel = ActivityNavigationShowRoute.this.A;
                if (activityNavigationShowRoutesViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationShowRoutesViewModel = null;
                }
                activityNavigationShowRoutesViewModel.getEta(this.$destinationSuggestionData, null, ActivityNavigationShowRoute.this.y, true, ActivityNavigationShowRoute.this.B, true, new a(ActivityNavigationShowRoute.this, this.$destinationSuggestionData));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static /* synthetic */ void L(ActivityNavigationShowRoute activityNavigationShowRoute, AutoSuggestionData autoSuggestionData, boolean z, AutoSuggestionData autoSuggestionData2, int i, Object obj) {
        if ((i & 4) != 0) {
            autoSuggestionData2 = null;
        }
        activityNavigationShowRoute.K(autoSuggestionData, z, autoSuggestionData2);
    }

    public static final void N(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void O(ActivityNavigationShowRoute this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.dismissProgress();
        String string = this$0.getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        this$0.showErrorDialog(string);
        LogHelper.d(this$0.p, "currentLocation addOnFailureListener ");
    }

    public static final void R(ActivityNavigationShowRoute this$0, ActivityResult activityResult) {
        Intent data;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (activityResult.getResultCode() != -1 || (data = activityResult.getData()) == null) {
            return;
        }
        this$0.q = data.getBooleanExtra("isFromSource", false);
        boolean booleanExtra = data.getBooleanExtra("isUserChoosenCurrentLocationAsLocation", false);
        this$0.z = booleanExtra;
        if (booleanExtra) {
            this$0.getBinding().tvShowRouteSourceLabel.setText(this$0.getResources().getText(R.string.your_current_location));
            this$0.getBinding().tvShowRouteSourceLabel.setSelected(true);
            TextView textView = this$0.getBinding().tvShowRouteSourceLabel;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvShowRouteSourceLabel");
            this$0.drawableStart(textView, R.drawable.ic_source_indicator_for_navigation);
            TextView textView2 = this$0.getBinding().tvShowRouteDestinationLabel;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvShowRouteDestinationLabel");
            this$0.drawableStart(textView2, R.drawable.ic_location_marker_in_white);
            Object fromJson = new Gson().fromJson(SessionManager.getInstance(this$0).getTempDestinationAutosuggestionData(), new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$handleSearchResults$1$listType$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(destJsonString, listType)");
            AutoSuggestionData autoSuggestionData = (AutoSuggestionData) fromJson;
            L(this$0, autoSuggestionData, true, null, 4, null);
            String str = this$0.p;
            LogHelper.d(str, "Tofro onActivityResult isUserChoosenCurrentLocationAsLocation currentLocation " + this$0.y + " destAutoSuggestionData " + new Gson().toJson(autoSuggestionData));
        } else if (data.getStringExtra("searchString") != null) {
            Type type = new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$handleSearchResults$1$listType$2
            }.getType();
            String tempSourceAutosuggestionData = SessionManager.getInstance(this$0).getTempSourceAutosuggestionData();
            AutoSuggestionData autoSuggestionData2 = tempSourceAutosuggestionData != null ? (AutoSuggestionData) new Gson().fromJson(tempSourceAutosuggestionData, type) : null;
            Object fromJson2 = new Gson().fromJson(SessionManager.getInstance(this$0).getTempDestinationAutosuggestionData(), type);
            Intrinsics.checkNotNullExpressionValue(fromJson2, "Gson().fromJson(destJsonString, listType)");
            AutoSuggestionData autoSuggestionData3 = (AutoSuggestionData) fromJson2;
            if (this$0.q) {
                this$0.getBinding().tvShowRouteSourceLabel.setText(data.getStringExtra("searchString"));
                this$0.getBinding().tvShowRouteSourceLabel.setSelected(true);
                TextView textView3 = this$0.getBinding().tvShowRouteSourceLabel;
                Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvShowRouteSourceLabel");
                int i = R.drawable.ic_location_marker_in_white;
                this$0.drawableStart(textView3, i);
                TextView textView4 = this$0.getBinding().tvShowRouteDestinationLabel;
                Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvShowRouteDestinationLabel");
                this$0.drawableStart(textView4, i);
                this$0.K(autoSuggestionData3, false, autoSuggestionData2);
                String str2 = this$0.p;
                LogHelper.d(str2, "Tofro onActivityResult isFromSource " + this$0.q + " sourceAutoSuggestionData " + new Gson().toJson(autoSuggestionData2) + " destAutoSuggestionData " + new Gson().toJson(autoSuggestionData3));
                return;
            }
            this$0.getBinding().tvShowRouteDestinationLabel.setText(data.getStringExtra("searchString"));
            this$0.getBinding().tvShowRouteDestinationLabel.setSelected(true);
            this$0.D = false;
            if (this$0.getBinding().tvShowRouteSourceLabel.getText().equals(this$0.getResources().getString(R.string.your_current_location))) {
                TextView textView5 = this$0.getBinding().tvShowRouteSourceLabel;
                Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvShowRouteSourceLabel");
                this$0.drawableStart(textView5, R.drawable.ic_source_indicator_for_navigation);
                TextView textView6 = this$0.getBinding().tvShowRouteDestinationLabel;
                Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvShowRouteDestinationLabel");
                this$0.drawableStart(textView6, R.drawable.ic_location_marker_in_white);
                L(this$0, autoSuggestionData3, true, null, 4, null);
                String str3 = this$0.p;
                LogHelper.d(str3, "Tofro onActivityResult isFromSource else " + this$0.q + " currentLocation " + this$0.y + " destAutoSuggestionData " + new Gson().toJson(autoSuggestionData3));
                return;
            }
            TextView textView7 = this$0.getBinding().tvShowRouteSourceLabel;
            Intrinsics.checkNotNullExpressionValue(textView7, "binding.tvShowRouteSourceLabel");
            int i2 = R.drawable.ic_location_marker_in_white;
            this$0.drawableStart(textView7, i2);
            TextView textView8 = this$0.getBinding().tvShowRouteDestinationLabel;
            Intrinsics.checkNotNullExpressionValue(textView8, "binding.tvShowRouteDestinationLabel");
            this$0.drawableStart(textView8, i2);
            this$0.K(autoSuggestionData3, false, autoSuggestionData2);
            String str4 = this$0.p;
            LogHelper.d(str4, "Tofro onActivityResult isFromSource else " + this$0.q + " currentLocation else sourceAutoSuggestionData " + new Gson().toJson(autoSuggestionData2) + " destAutoSuggestionData " + new Gson().toJson(autoSuggestionData3));
        }
    }

    public static final void T(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityNavigationFTU.class);
        intent.putExtra("isToView", true);
        this$0.startActivity(intent);
    }

    public static final void U(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B = "driving";
        TextView textView = this$0.getBinding().tvDrivingMode;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDrivingMode");
        TextView textView2 = this$0.getBinding().tvWalkingMode;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvWalkingMode");
        ConstraintLayout constraintLayout = this$0.getBinding().clDriving;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clDriving");
        ConstraintLayout constraintLayout2 = this$0.getBinding().clWalking;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clWalking");
        this$0.m0(textView, textView2, constraintLayout, constraintLayout2, false);
        this$0.p0();
    }

    public static final void V(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.B = "walking";
        TextView textView = this$0.getBinding().tvWalkingMode;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvWalkingMode");
        TextView textView2 = this$0.getBinding().tvDrivingMode;
        Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvDrivingMode");
        ConstraintLayout constraintLayout = this$0.getBinding().clWalking;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.clWalking");
        ConstraintLayout constraintLayout2 = this$0.getBinding().clDriving;
        Intrinsics.checkNotNullExpressionValue(constraintLayout2, "binding.clDriving");
        this$0.m0(textView, textView2, constraintLayout, constraintLayout2, true);
        this$0.p0();
    }

    public static final void W(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityNavigationSearchPlaces.class);
        intent.putExtra("from", ActivityNavigationShowRoute.class.getSimpleName());
        intent.putExtra("isFromSource", true);
        ActivityResultLauncher<Intent> activityResultLauncher = this$0.F;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityResultLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch(intent);
    }

    public static final void X(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, ActivityNavigationSearchPlaces.class);
        intent.putExtra("from", ActivityNavigationShowRoute.class.getSimpleName());
        intent.putExtra("isFromSource", false);
        ActivityResultLauncher<Intent> activityResultLauncher = this$0.F;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityResultLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch(intent);
    }

    public static final void Y(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CharSequence text = this$0.getBinding().tvShowRouteSourceLabel.getText();
        this$0.getBinding().tvShowRouteSourceLabel.setText(this$0.getBinding().tvShowRouteDestinationLabel.getText());
        this$0.getBinding().tvShowRouteSourceLabel.setSelected(true);
        this$0.getBinding().tvShowRouteDestinationLabel.setText(text);
        this$0.getBinding().tvShowRouteDestinationLabel.setSelected(true);
        CharSequence text2 = this$0.getBinding().tvShowRouteDestinationLabel.getText();
        Resources resources = this$0.getResources();
        int i = R.string.your_current_location;
        if (text2.equals(resources.getString(i))) {
            this$0.D = true;
            this$0.p0();
            TextView textView = this$0.getBinding().tvShowRouteSourceLabel;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvShowRouteSourceLabel");
            this$0.drawableStart(textView, R.drawable.ic_location_marker_in_white);
            TextView textView2 = this$0.getBinding().tvShowRouteDestinationLabel;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvShowRouteDestinationLabel");
            this$0.drawableStart(textView2, R.drawable.ic_source_indicator_for_navigation);
            return;
        }
        this$0.D = false;
        this$0.p0();
        if (this$0.getBinding().tvShowRouteSourceLabel.getText().equals(this$0.getResources().getString(i))) {
            TextView textView3 = this$0.getBinding().tvShowRouteSourceLabel;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvShowRouteSourceLabel");
            this$0.drawableStart(textView3, R.drawable.ic_source_indicator_for_navigation);
            TextView textView4 = this$0.getBinding().tvShowRouteDestinationLabel;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvShowRouteDestinationLabel");
            this$0.drawableStart(textView4, R.drawable.ic_location_marker_in_white);
            return;
        }
        TextView textView5 = this$0.getBinding().tvShowRouteSourceLabel;
        Intrinsics.checkNotNullExpressionValue(textView5, "binding.tvShowRouteSourceLabel");
        int i2 = R.drawable.ic_location_marker_in_white;
        this$0.drawableStart(textView5, i2);
        TextView textView6 = this$0.getBinding().tvShowRouteDestinationLabel;
        Intrinsics.checkNotNullExpressionValue(textView6, "binding.tvShowRouteDestinationLabel");
        this$0.drawableStart(textView6, i2);
    }

    public static final void Z(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.getBinding().tvTapToEnter;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvTapToEnter");
        this$0.gone(textView);
    }

    public static final void e0(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.checkIfLocationPermissionExists(this$0) && AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.showProgress();
            Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$setEtaInfo$1$navigationDisclaimerDataType$1
            }.getType();
            Object fromJson = new Gson().fromJson(SessionManager.getInstance(this$0).getNavigationDiscliamerData(), type);
            Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
            NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
            ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel = this$0.A;
            if (activityNavigationShowRoutesViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityNavigationShowRoutesViewModel = null;
            }
            activityNavigationShowRoutesViewModel.getDisclaimerStatusFromBand(navigationDisclaimerData.getVersion(), this$0);
        } else if (!AppUtils.checkIfLocationPermissionExists(this$0)) {
            String string = this$0.getString(R.string.location_permission_required);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
            this$0.o0(string);
        } else if (!AppUtils.isNetConnected(this$0)) {
            String string2 = this$0.getString(R.string.no_internet);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.no_internet)");
            Drawable drawable = this$0.getResources().getDrawable(R.drawable.no_internet);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
            String string3 = this$0.getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.pleas…able_internet_connection)");
            String string4 = this$0.getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.okay)");
            this$0.n0(string2, drawable, string3, string4);
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            String string5 = this$0.getString(R.string.bluetooth_disconnected);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.bluetooth_disconnected)");
            Drawable drawable2 = this$0.getResources().getDrawable(R.drawable.ic_bluetooth_disconnected);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.…c_bluetooth_disconnected)");
            String string6 = this$0.getString(R.string.connect_smartwatch_to_the_boat_crest_app_to_get_turn);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.conne…at_crest_app_to_get_turn)");
            String string7 = this$0.getString(R.string.okay);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.okay)");
            this$0.n0(string5, drawable2, string6, string7);
        }
    }

    public static final void f0(ActivityNavigationShowRoute this$0, DirectionsResponse directionsApiResponse, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(directionsApiResponse, "directionsApiResponse");
        this$0.a0(false, directionsApiResponse, i);
    }

    public static final void g0(ActivityNavigationShowRoute this$0, DirectionsResponse directionsApiResponse, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(directionsApiResponse, "directionsApiResponse");
        this$0.a0(false, directionsApiResponse, i);
    }

    public static final void j0(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            this$0.startActivity(new Intent(this$0, ActivityNavigationSettings.class));
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.watch_disconnected_msg), 1).show();
        }
    }

    public static final void k0(ActivityNavigationShowRoute this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    public final void F(AutoSuggestionData autoSuggestionData, AutoSuggestionData autoSuggestionData2, boolean z) {
        if (this.s != null) {
            MapplsMap mMapplsMap = getMMapplsMap();
            Marker marker = this.s;
            if (marker == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sourceMarker");
                marker = null;
            }
            mMapplsMap.removeMarker(marker);
        }
        if (z) {
            if (this.y != null) {
                MarkerOptions markerOptions = new MarkerOptions();
                Location location = this.y;
                Intrinsics.checkNotNull(location);
                double latitude = location.getLatitude();
                Location location2 = this.y;
                Intrinsics.checkNotNull(location2);
                Marker addMarker = getMMapplsMap().addMarker(markerOptions.position(new LatLng(latitude, location2.getLongitude())).icon(IconFactory.getInstance(this).fromResource(R.drawable.ic_source_marker)).setTitle(getString(R.string.your_current_location)));
                Intrinsics.checkNotNullExpressionValue(addMarker, "mMapplsMap.addMarker(sourceMarkerOptions)");
                this.s = addMarker;
            }
        } else {
            Marker addMarker2 = getMMapplsMap().addMarker(new MarkerOptions().mapplsPin(autoSuggestionData2 != null ? autoSuggestionData2.getMapplsPin() : null).icon(IconFactory.getInstance(this).fromResource(R.drawable.ic_source_marker)).setTitle(autoSuggestionData2 != null ? autoSuggestionData2.getPlaceName() : null));
            Intrinsics.checkNotNullExpressionValue(addMarker2, "mMapplsMap.addMarker(sourceMarkerOptions)");
            this.s = addMarker2;
        }
        if (this.t != null) {
            MapplsMap mMapplsMap2 = getMMapplsMap();
            Marker marker2 = this.t;
            if (marker2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("destinationMarker");
                marker2 = null;
            }
            mMapplsMap2.removeMarker(marker2);
        }
        MarkerOptions title = new MarkerOptions().mapplsPin(autoSuggestionData != null ? autoSuggestionData.getMapplsPin() : null).icon(IconFactory.getInstance(this).fromResource(R.drawable.destination_marker_icon)).setTitle(autoSuggestionData != null ? autoSuggestionData.getPlaceName() : null);
        Intrinsics.checkNotNullExpressionValue(title, "MarkerOptions().mapplsPi…uggestionData?.placeName)");
        Marker addMarker3 = getMMapplsMap().addMarker(title);
        Intrinsics.checkNotNullExpressionValue(addMarker3, "mMapplsMap.addMarker(destinationMarkerOptions)");
        this.t = addMarker3;
        dismissProgress();
    }

    public final void G() {
        DirectionsResponse fromJson = DirectionsResponse.fromJson(SessionManager.getInstance(this).getDirectionApiResponse());
        ArrayList arrayList = new ArrayList();
        DirectionsRoute directionsRoute = fromJson.routes().get(this.C);
        Intrinsics.checkNotNull(directionsRoute);
        String geometry = directionsRoute.geometry();
        Intrinsics.checkNotNull(geometry);
        List<Point> decode = PolylineUtils.decode(geometry, 6);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(response.routes()…!, Constants.PRECISION_6)");
        for (Point point : decode) {
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        getMMapplsMap().animateCamera(CameraUpdateFactory.newLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), 50, 80, 50, 80));
    }

    public final void H() {
        if (AppUtils.isNetConnected(this)) {
            return;
        }
        String string = getString(R.string.please_enable_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enable_internet)");
        o0(string);
    }

    public final void I() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$checkLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityNavigationShowRoute activityNavigationShowRoute = ActivityNavigationShowRoute.this;
                ActivityCompat.requestPermissions(activityNavigationShowRoute, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, activityNavigationShowRoute.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityNavigationShowRoute activityNavigationShowRoute = ActivityNavigationShowRoute.this;
                String string = activityNavigationShowRoute.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationShowRoute.o0(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                ActivityNavigationShowRoute.this.M();
                LogHelper.d(ActivityNavigationShowRoute.this.p, "checkLocationPermissions onPermissionGranted");
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityNavigationShowRoute activityNavigationShowRoute = ActivityNavigationShowRoute.this;
                String string = activityNavigationShowRoute.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityNavigationShowRoute.o0(string);
            }
        });
    }

    public final void J(AutoSuggestionData autoSuggestionData, AutoSuggestionData autoSuggestionData2, boolean z) {
        DirectionPolylinePlugin directionPolylinePlugin = this.r;
        if (directionPolylinePlugin != null) {
            Intrinsics.checkNotNull(directionPolylinePlugin);
            directionPolylinePlugin.removeAllData();
            DirectionPolylinePlugin directionPolylinePlugin2 = this.r;
            Intrinsics.checkNotNull(directionPolylinePlugin2);
            directionPolylinePlugin2.setEnableCongestion(true);
            DirectionPolylinePlugin directionPolylinePlugin3 = this.r;
            Intrinsics.checkNotNull(directionPolylinePlugin3);
            directionPolylinePlugin3.setEnabled(true);
            DirectionPolylinePlugin directionPolylinePlugin4 = this.r;
            Intrinsics.checkNotNull(directionPolylinePlugin4);
            directionPolylinePlugin4.setProfile(this.B);
            DirectionsResponse fromJson = DirectionsResponse.fromJson(SessionManager.getInstance(this).getDirectionApiResponse());
            ArrayList arrayList = new ArrayList();
            for (DirectionsRoute directionsRoute : fromJson.routes()) {
                String geometry = directionsRoute.geometry();
                Intrinsics.checkNotNull(geometry);
                LineString lineString = LineString.fromPolyline(geometry, 6);
                Intrinsics.checkNotNullExpressionValue(lineString, "lineString");
                arrayList.add(lineString);
            }
            ArrayList arrayList2 = new ArrayList();
            List<DirectionsWaypoint> waypoints = fromJson.waypoints();
            Intrinsics.checkNotNull(waypoints);
            int size = waypoints.size() - 1;
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    List<DirectionsWaypoint> waypoints2 = fromJson.waypoints();
                    Intrinsics.checkNotNull(waypoints2);
                    Point location = waypoints2.get(i).location();
                    Intrinsics.checkNotNull(location);
                    arrayList2.add(new LatLng(location.latitude(), location.longitude()));
                }
            }
            DirectionPolylinePlugin directionPolylinePlugin5 = this.r;
            Intrinsics.checkNotNull(directionPolylinePlugin5);
            directionPolylinePlugin5.setTrips(arrayList, null, null, arrayList2, fromJson.routes());
            G();
            F(autoSuggestionData, autoSuggestionData2, z);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = true;
            DirectionPolylinePlugin directionPolylinePlugin6 = this.r;
            Intrinsics.checkNotNull(directionPolylinePlugin6);
            directionPolylinePlugin6.setOnNewRouteSelectedListener(new ActivityNavigationShowRoute$drawPolyLine$1(this, booleanRef));
            return;
        }
        dismissProgress();
    }

    public final void K(AutoSuggestionData autoSuggestionData, boolean z, AutoSuggestionData autoSuggestionData2) {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new a(z, this, autoSuggestionData, autoSuggestionData2, null), 3, null);
    }

    public final void M() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProvider…ivityNavigationShowRoute)");
        this.x = fusedLocationProviderClient;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient2 = this.x;
            if (fusedLocationProviderClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                fusedLocationProviderClient2 = null;
            }
            Task<Location> currentLocation = fusedLocationProviderClient2.getCurrentLocation(100, (CancellationToken) null);
            final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$getLocation$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                    invoke2(location);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Location location) {
                    ActivityNavigationShowRoute.this.dismissProgress();
                    ActivityNavigationShowRoute.this.y = location;
                    if (ActivityNavigationShowRoute.this.y != null) {
                        Type type = new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$getLocation$1$listType$1
                        }.getType();
                        String tempDestinationAutosuggestionData = SessionManager.getInstance(ActivityNavigationShowRoute.this).getTempDestinationAutosuggestionData();
                        ActivityNavigationShowRoute activityNavigationShowRoute = ActivityNavigationShowRoute.this;
                        Object fromJson = new Gson().fromJson(tempDestinationAutosuggestionData, type);
                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(destJsonString, listType)");
                        activityNavigationShowRoute.setDestinationSuggestionData((AutoSuggestionData) fromJson);
                        ActivityNavigationShowRoute.this.h0();
                        ActivityNavigationShowRoute activityNavigationShowRoute2 = ActivityNavigationShowRoute.this;
                        ActivityNavigationShowRoute.L(activityNavigationShowRoute2, activityNavigationShowRoute2.getDestinationSuggestionData(), true, null, 4, null);
                        return;
                    }
                    ActivityNavigationShowRoute activityNavigationShowRoute3 = ActivityNavigationShowRoute.this;
                    String string = activityNavigationShowRoute3.getString(R.string.unable_to_fetch_location);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
                    activityNavigationShowRoute3.showErrorDialog(string);
                }
            };
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.navigation.activities.q1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ActivityNavigationShowRoute.N(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.navigation.activities.p1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityNavigationShowRoute.O(ActivityNavigationShowRoute.this, exc);
                }
            });
        }
    }

    public final NetworkMonitor P() {
        return (NetworkMonitor) this.G.getValue();
    }

    public final void Q() {
        ActivityResultLauncher<Intent> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.coveiot.android.navigation.activities.o1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ActivityNavigationShowRoute.R(ActivityNavigationShowRoute.this, (ActivityResult) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul…          }\n            }");
        this.F = registerForActivityResult;
    }

    public final void S() {
        getBinding().clDriving.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.s1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.U(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().clWalking.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.l1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.V(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().llSearchSourceLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.z1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.W(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().llSearchDestinationLayout.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.w1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.X(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().ivViceVersa.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.x1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.Y(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().tvTapToEnter.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.t1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.Z(ActivityNavigationShowRoute.this, view);
            }
        });
        getBinding().tvNavigateToWatchDisclaimer.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.y1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.T(ActivityNavigationShowRoute.this, view);
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

    public final void a0(boolean z, DirectionsResponse directionsResponse, int i) {
        SessionManager.getInstance(this).saveDirectionApiResponse(directionsResponse.toJson());
        Intent intent = new Intent(this, ActivityDirections.class);
        if (z) {
            intent.setFlags(603979776);
        }
        intent.putExtra("forNavigation", z);
        intent.putExtra("routeIndex", i);
        intent.putExtra("mode", this.B);
        startActivity(intent);
    }

    public final void b0() {
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new ActivityNavigationShowRoute$observeNetworkStatus$1(this, null), 3, null);
    }

    public final void c0() {
        ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel = this.A;
        if (activityNavigationShowRoutesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityNavigationShowRoutesViewModel = null;
        }
        activityNavigationShowRoutesViewModel.setNavigationAliveTimerOnBand(120, new d());
    }

    public final void d0(final int i) {
        BigDecimal scale;
        Button button = getBinding().btnPreview;
        Intrinsics.checkNotNullExpressionValue(button, "binding.btnPreview");
        visible(button);
        Button button2 = getBinding().btnNavigateOnWatch;
        Intrinsics.checkNotNullExpressionValue(button2, "binding.btnNavigateOnWatch");
        visible(button2);
        Button button3 = getBinding().btnNavigateOnWatch;
        Resources resources = getResources();
        int i2 = R.string.navigate_on_watch;
        button3.setText(resources.getText(i2));
        final DirectionsResponse fromJson = DirectionsResponse.fromJson(SessionManager.getInstance(this).getDirectionApiResponse());
        Double distance = fromJson.routes().get(i).distance();
        Double valueOf = (distance == null || (scale = new BigDecimal(String.valueOf(distance.doubleValue() / 1000.0d)).setScale(2, RoundingMode.HALF_UP)) == null) ? null : Double.valueOf(scale.doubleValue());
        Intrinsics.checkNotNull(distance);
        if (distance.doubleValue() < 10.0d) {
            Button button4 = getBinding().btnPreview;
            Intrinsics.checkNotNullExpressionValue(button4, "binding.btnPreview");
            gone(button4);
            Button button5 = getBinding().btnNavigateOnWatch;
            Intrinsics.checkNotNullExpressionValue(button5, "binding.btnNavigateOnWatch");
            gone(button5);
            Toast.makeText(this, "Source and Destination cannot be same.", 1).show();
        } else if (getBinding().tvShowRouteSourceLabel.getText().equals(getResources().getString(R.string.your_current_location))) {
            Button button6 = getBinding().btnPreview;
            Intrinsics.checkNotNullExpressionValue(button6, "binding.btnPreview");
            visible(button6);
            Button button7 = getBinding().btnNavigateOnWatch;
            Intrinsics.checkNotNullExpressionValue(button7, "binding.btnNavigateOnWatch");
            visible(button7);
            getBinding().btnNavigateOnWatch.setText(getResources().getText(i2));
            getBinding().btnNavigateOnWatch.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.r1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNavigationShowRoute.e0(ActivityNavigationShowRoute.this, view);
                }
            });
            getBinding().btnPreview.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.m1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNavigationShowRoute.f0(ActivityNavigationShowRoute.this, fromJson, i, view);
                }
            });
        } else {
            Button button8 = getBinding().btnPreview;
            Intrinsics.checkNotNullExpressionValue(button8, "binding.btnPreview");
            gone(button8);
            getBinding().btnNavigateOnWatch.setText(getResources().getText(R.string.preview));
            getBinding().btnNavigateOnWatch.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.n1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityNavigationShowRoute.g0(ActivityNavigationShowRoute.this, fromJson, i, view);
                }
            });
        }
        TextView textView = getBinding().tvBsDistanceToReachDestination;
        textView.setText(valueOf + " Km");
        TextView textView2 = getBinding().tvBsReachDurationToDestination;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        Double duration = fromJson.routes().get(i).duration();
        Intrinsics.checkNotNull(duration);
        textView2.setText(themesUtils.formatSeconds((long) duration.doubleValue()));
        if (i == 0) {
            TextView textView3 = getBinding().tvMostOptimalRouteSelected;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvMostOptimalRouteSelected");
            visible(textView3);
        } else {
            TextView textView4 = getBinding().tvMostOptimalRouteSelected;
            Intrinsics.checkNotNullExpressionValue(textView4, "binding.tvMostOptimalRouteSelected");
            gone(textView4);
        }
        TextView textView5 = getBinding().tvBsArrival;
        StringBuilder sb = new StringBuilder();
        sb.append("Arrival: ");
        Double duration2 = fromJson.routes().get(i).duration();
        Intrinsics.checkNotNull(duration2);
        sb.append(themesUtils.addSecondsToCurrentTimeAndGetTimeIn12HoursFormat((long) duration2.doubleValue()));
        textView5.setText(sb.toString());
    }

    @Override // com.coveiot.android.navigation.interfaces.BandDisclaimerListener
    public void disclaimerAlreadySet() {
        c0();
    }

    @NotNull
    public final ActivityNavigationShowRouteBinding getBinding() {
        ActivityNavigationShowRouteBinding activityNavigationShowRouteBinding = this.binding;
        if (activityNavigationShowRouteBinding != null) {
            return activityNavigationShowRouteBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.E;
    }

    @NotNull
    public final AutoSuggestionData getDestinationSuggestionData() {
        AutoSuggestionData autoSuggestionData = this.destinationSuggestionData;
        if (autoSuggestionData != null) {
            return autoSuggestionData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("destinationSuggestionData");
        return null;
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getInternetAlertDialog() {
        return this.H;
    }

    public final int getLOCATION_PERMISSION_REQUEST_CODE() {
        return this.u;
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
    public final TextView getTitletextView() {
        TextView textView = this.titletextView;
        if (textView != null) {
            return textView;
        }
        Intrinsics.throwUninitializedPropertyAccessException("titletextView");
        return null;
    }

    public final void h0() {
        TextView textView = getBinding().tvNavigateToWatchInstructions;
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        String string = getString(R.string.check_your_watch_for);
        Intrinsics.checkNotNullExpressionValue(string, "this@ActivityNavigationS…ing.check_your_watch_for)");
        textView.setText(themesUtils.styled(string, "DND", getResources().getColor(R.color.green_text_color), false, false, e.INSTANCE, this));
        getBinding().tvShowRouteDestinationLabel.setText(getDestinationSuggestionData().getPlaceName());
        getBinding().tvShowRouteDestinationLabel.setSelected(true);
        d0(this.C);
    }

    public final void i0() {
        View findViewById = findViewById(R.id.toolbar_title);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.toolbar_title)");
        setTitletextView((TextView) findViewById);
        getTitletextView().setText(getResources().getString(R.string.navigation));
        View findViewById2 = findViewById(R.id.ivButton);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<ImageView>(R.id.ivButton)");
        setSettingsIcon((ImageView) findViewById2);
        getSettingsIcon().setImageResource(R.drawable.ic_settings);
        getSettingsIcon().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.v1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.j0(ActivityNavigationShowRoute.this, view);
            }
        });
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.u1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityNavigationShowRoute.k0(ActivityNavigationShowRoute.this, view);
            }
        });
        l0();
        S();
    }

    @Override // com.coveiot.android.navigation.interfaces.BandDisclaimerListener
    public void isAccepted() {
        c0();
    }

    public final boolean isFromAppSettings() {
        return this.w;
    }

    public final boolean isFromSource() {
        return this.q;
    }

    public final void l0() {
        getBinding().mapviewShowRoute.getMapAsync(this);
    }

    public final void m0(TextView textView, TextView textView2, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, boolean z) {
        constraintLayout.setBackgroundResource(R.drawable.red_gradient_bottom_border_background);
        constraintLayout2.setBackgroundResource(R.color.transparent);
        if (z) {
            textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.ic_walking_selected), (Drawable) null, (Drawable) null, (Drawable) null);
            textView.setTextColor(getColor(R.color.white));
            textView2.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.ic_driving_unselected), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setTextColor(getColor(R.color.info_text_color));
            return;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.ic_driving_selected), (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setTextColor(getColor(R.color.white));
        textView2.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(this, R.drawable.ic_walking_unselected), (Drawable) null, (Drawable) null, (Drawable) null);
        textView2.setTextColor(getColor(R.color.info_text_color));
    }

    public final void n0(String str, Drawable drawable, String str2, String str3) {
        if (this.E == null) {
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, str, str2, false);
            this.E = bottomSheetDialogImageTitleMessage;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.setPositiveButton(str3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$showMsgOnDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = ActivityNavigationShowRoute.this.getBottomSheetDialogImageTitleMessage();
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                    bottomSheetDialogImageTitleMessage2.dismiss();
                    ActivityNavigationShowRoute.this.setBottomSheetDialogImageTitleMessage(null);
                }
            });
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.E;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
        if (bottomSheetDialogImageTitleMessage2.isShowing()) {
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.E;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
        bottomSheetDialogImageTitleMessage3.showBigIcon();
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.E;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
        bottomSheetDialogImageTitleMessage4.show();
    }

    @Override // com.coveiot.android.navigation.interfaces.BandDisclaimerListener
    public void needToSetDisclaimer() {
        Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$needToSetDisclaimer$navigationDisclaimerDataType$1
        }.getType();
        Object fromJson = new Gson().fromJson(SessionManager.getInstance(this).getNavigationDiscliamerData(), type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
        NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
        ActivityNavigationShowRoutesViewModel activityNavigationShowRoutesViewModel = this.A;
        if (activityNavigationShowRoutesViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            activityNavigationShowRoutesViewModel = null;
        }
        activityNavigationShowRoutesViewModel.setDisclaimerContentToBand(navigationDisclaimerData.getVersion(), navigationDisclaimerData.getDvcText(), new b());
    }

    public final void o0(final String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        dismissProgress();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.v;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.v == null || z) {
            this.v = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.v;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$showPermissionDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationShowRoute.this.v;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    if (str.equals(ActivityNavigationShowRoute.this.getString(R.string.please_enable_internet))) {
                        ActivityNavigationShowRoute.this.finish();
                        return;
                    }
                    ActivityNavigationShowRoute.this.setFromAppSettings(true);
                    AppUtils.openAppSettings(ActivityNavigationShowRoute.this, 121);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.v;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.v) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityNavigationShowRouteBinding inflate = ActivityNavigationShowRouteBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        this.A = (ActivityNavigationShowRoutesViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationShowRoutesViewModel.class);
        getBinding().mapviewShowRoute.onCreate(bundle);
        showProgress();
        H();
        i0();
        MapplsMapConfiguration.getInstance().setDeveloperShowingSplash(true);
        Q();
        b0();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        getBinding().mapviewShowRoute.onDestroy();
    }

    @Override // com.coveiot.android.navigation.interfaces.BandDisclaimerListener
    public void onFailure() {
        LogHelper.d(this.p, "get disclaimer command failed");
        Toast.makeText(this, "Something went wrong", 0).show();
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapClickListener
    public boolean onMapClick(@NotNull LatLng p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return false;
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i, @Nullable String str) {
        dismissProgress();
        MapplsMapConfiguration.getInstance().setDeveloperShowingSplash(false);
        String string = getString(R.string.some_thing_went_wrong);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
        showErrorDialog(string);
        String str2 = this.p;
        LogHelper.d(str2, "onMapError p0 " + i + ' ' + str);
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMapLongClickListener
    public boolean onMapLongClick(@NotNull LatLng p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return false;
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(@NotNull MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "mapplsMap");
        setMMapplsMap(mapplsMap);
        String str = this.p;
        LogHelper.d(str, "onMapReady p0 " + mapplsMap);
        boolean z = false;
        if (!AppUtils.isGpsEnabled(this)) {
            Toast.makeText(this, getString(R.string.please_check_gps), 0).show();
        } else {
            I();
        }
        if (this.mMapplsMap != null) {
            UiSettings uiSettings = getMMapplsMap().getUiSettings();
            if (uiSettings != null) {
                uiSettings.setLogoEnabled(false);
            }
            this.r = new DirectionPolylinePlugin(getBinding().mapviewShowRoute, mapplsMap);
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
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        getBinding().mapviewShowRoute.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.u) {
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
                o0(string);
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
                o0(string);
            } else {
                l0();
                M();
            }
        }
        getBinding().mapviewShowRoute.onResume();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        getBinding().mapviewShowRoute.onSaveInstanceState(outState);
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        getBinding().mapviewShowRoute.onStart();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        getBinding().mapviewShowRoute.onStop();
    }

    public final void p0() {
        showProgress();
        Type type = new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$updateEta$listType$1
        }.getType();
        Object fromJson = new Gson().fromJson(SessionManager.getInstance(this).getTempDestinationAutosuggestionData(), type);
        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(destJsonString, listType)");
        AutoSuggestionData autoSuggestionData = (AutoSuggestionData) fromJson;
        String tempSourceAutosuggestionData = SessionManager.getInstance(this).getTempSourceAutosuggestionData();
        AutoSuggestionData autoSuggestionData2 = tempSourceAutosuggestionData != null ? (AutoSuggestionData) new Gson().fromJson(tempSourceAutosuggestionData, type) : null;
        if (this.D) {
            kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new f(autoSuggestionData, null), 3, null);
        } else if (this.z) {
            if (this.y != null) {
                dismissProgress();
                L(this, autoSuggestionData, true, null, 4, null);
                return;
            }
            dismissProgress();
            Toast.makeText(this, getResources().getText(R.string.some_thing_went_wrong), 1).show();
        } else {
            K(autoSuggestionData, false, autoSuggestionData2);
        }
    }

    public final void setBinding(@NotNull ActivityNavigationShowRouteBinding activityNavigationShowRouteBinding) {
        Intrinsics.checkNotNullParameter(activityNavigationShowRouteBinding, "<set-?>");
        this.binding = activityNavigationShowRouteBinding;
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.E = bottomSheetDialogImageTitleMessage;
    }

    public final void setDestinationSuggestionData(@NotNull AutoSuggestionData autoSuggestionData) {
        Intrinsics.checkNotNullParameter(autoSuggestionData, "<set-?>");
        this.destinationSuggestionData = autoSuggestionData;
    }

    public final void setFromAppSettings(boolean z) {
        this.w = z;
    }

    public final void setFromSource(boolean z) {
        this.q = z;
    }

    public final void setInternetAlertDialog(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.H = bottomSheetDialogImageTitleMessage;
    }

    public final void setMMapplsMap(@NotNull MapplsMap mapplsMap) {
        Intrinsics.checkNotNullParameter(mapplsMap, "<set-?>");
        this.mMapplsMap = mapplsMap;
    }

    public final void setSettingsIcon(@NotNull ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.settingsIcon = imageView;
    }

    public final void setTitletextView(@NotNull TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.titletextView = textView;
    }

    public final void showErrorDialog(String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        dismissProgress();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.v;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.v == null || z) {
            this.v = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.v;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityNavigationShowRoute$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityNavigationShowRoute.this.v;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    ActivityNavigationShowRoute.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.v;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.v) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }
}
