package com.coveiot.android.navigation.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetNavigationConfigurationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.adapter.DirectionsAdaptor;
import com.coveiot.android.navigation.databinding.ActivityDirectionsBinding;
import com.coveiot.android.navigation.interfaces.CoveNavigationListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.DirectionsData;
import com.coveiot.android.navigation.services.CoveNavigationService;
import com.coveiot.android.navigation.utils.CoveNavigationCallBacks;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.navigation.utils.NavigationUtilsKt;
import com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.coveaccess.prefs.PreferenceManager;
import com.coveiot.coveaccess.utils.TimeUtils;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.stats.CodePackage;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.plugin.directions.DirectionsUtils;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityDirections extends BaseActivity implements CoveNavigationListener, Observer<ConnectionStatus> {
    public boolean A;
    public boolean C;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle E;
    public boolean F;
    public boolean G;
    public int H;
    public int I;
    public ActivityDirectionsBinding p;
    public boolean q;
    public int r;
    @Nullable
    public DirectionsResponse s;
    @Nullable
    public AutoSuggestionData t;
    public ActivityNavigationDirectionsViewmodel v;
    @Nullable
    public BottomSheetDialogImageTitleMessage w;
    public FusedLocationProviderClient x;
    @Nullable
    public Location y;
    @Nullable
    public CoveNavigationService z;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public final String u = ActivityDirections.class.getSimpleName();
    @NotNull
    public String B = "walking";
    public final int D = 1003;
    @NotNull
    public final ActivityDirections$serviceConnection$1 J = new ServiceConnection() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$serviceConnection$1

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Boolean, Unit> {
            public final /* synthetic */ ActivityDirections this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityDirections activityDirections) {
                super(1);
                this.this$0 = activityDirections;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                DirectionsResponse directionsResponse;
                AutoSuggestionData autoSuggestionData;
                int i;
                AutoSuggestionData autoSuggestionData2;
                String str;
                MapplsNavigationHelper.getInstance().setMute(!z);
                CoveNavigationService coveNavigationService = this.this$0.getCoveNavigationService();
                Intrinsics.checkNotNull(coveNavigationService);
                Location location = this.this$0.y;
                Intrinsics.checkNotNull(location);
                directionsResponse = this.this$0.s;
                Intrinsics.checkNotNull(directionsResponse);
                autoSuggestionData = this.this$0.t;
                Intrinsics.checkNotNull(autoSuggestionData);
                String mapplsPin = autoSuggestionData.getMapplsPin();
                i = this.this$0.r;
                autoSuggestionData2 = this.this$0.t;
                Intrinsics.checkNotNull(autoSuggestionData2);
                str = this.this$0.B;
                coveNavigationService.startNavigation(location, directionsResponse, mapplsPin, i, autoSuggestionData2, str);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(@Nullable ComponentName componentName, @Nullable IBinder iBinder) {
            DirectionsResponse directionsResponse;
            AutoSuggestionData autoSuggestionData;
            Intrinsics.checkNotNull(iBinder, "null cannot be cast to non-null type com.coveiot.android.navigation.services.CoveNavigationService.NavigationServiceBinder");
            ActivityDirections.this.setCoveNavigationService(((CoveNavigationService.NavigationServiceBinder) iBinder).getService());
            directionsResponse = ActivityDirections.this.s;
            if (directionsResponse != null) {
                autoSuggestionData = ActivityDirections.this.t;
                if (autoSuggestionData != null && ActivityDirections.this.getCoveNavigationService() != null) {
                    ActivityDirections activityDirections = ActivityDirections.this;
                    activityDirections.D(new a(activityDirections));
                }
            }
            ActivityDirections.this.A = true;
            CoveNavigationService coveNavigationService = ActivityDirections.this.getCoveNavigationService();
            Intrinsics.checkNotNull(coveNavigationService);
            coveNavigationService.registerCoveNavigationListener(ActivityDirections.this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(@Nullable ComponentName componentName) {
            CoveNavigationService coveNavigationService = ActivityDirections.this.getCoveNavigationService();
            Intrinsics.checkNotNull(coveNavigationService);
            coveNavigationService.unRegisterCoveNavigationListener();
            ActivityDirections.this.A = false;
        }
    };

    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CoveNavigationCallBacks.values().length];
            try {
                iArr[CoveNavigationCallBacks.NavigationStarted.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CoveNavigationCallBacks.ReRoutingRequested.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CoveNavigationCallBacks.NavigationCancelled.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CoveNavigationCallBacks.NavigationFinished.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CoveNavigationCallBacks.NavigationError.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CoveNavigationCallBacks.OnInternetLost.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CoveNavigationCallBacks.OnInternetAvailable.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConnectionStatus.values().length];
            try {
                iArr2[ConnectionStatus.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[ConnectionStatus.DISCONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

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
            ActivityDirections.this.y = location;
            if (ActivityDirections.this.y != null) {
                if (ActivityDirections.this.q) {
                    ActivityDirections.this.d0();
                }
                if (ActivityDirections.this.C) {
                    ActivityDirections.this.U();
                    return;
                }
                return;
            }
            ActivityDirections.this.dismissProgress();
            ActivityDirections activityDirections = ActivityDirections.this;
            String string = activityDirections.getString(R.string.unable_to_fetch_location);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
            activityDirections.showErrorDialog(string);
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
                ActivityDirections.this.dismissProgress();
                return;
            }
            ActivityDirections.this.dismissProgress();
            ActivityDirections activityDirections = ActivityDirections.this;
            Toast.makeText(activityDirections, activityDirections.getString(R.string.something_went_wrong_while_saving_trip), 0).show();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityDirections$updateBandDisclaimer$1", f = "ActivityDirections.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Function1<Boolean, Unit> $result;
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function3<Boolean, Boolean, String, Unit> {
            public final /* synthetic */ Function1<Boolean, Unit> $result;
            public final /* synthetic */ ActivityDirections this$0;

            /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$c$a$a  reason: collision with other inner class name */
            /* loaded from: classes5.dex */
            public static final class C0284a extends Lambda implements Function3<String, Boolean, Boolean, Unit> {
                public final /* synthetic */ Function1<Boolean, Unit> $result;
                public final /* synthetic */ String $version;
                public final /* synthetic */ ActivityDirections this$0;

                /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$c$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes5.dex */
                public static final class C0285a extends Lambda implements Function2<Boolean, String, Unit> {
                    public final /* synthetic */ Function1<Boolean, Unit> $result;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C0285a(Function1<? super Boolean, Unit> function1) {
                        super(2);
                        this.$result = function1;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                        invoke(bool.booleanValue(), str);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z, @NotNull String msg) {
                        Intrinsics.checkNotNullParameter(msg, "msg");
                        if (z) {
                            this.$result.invoke(Boolean.TRUE);
                        } else {
                            this.$result.invoke(Boolean.FALSE);
                        }
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C0284a(Function1<? super Boolean, Unit> function1, String str, ActivityDirections activityDirections) {
                    super(3);
                    this.$result = function1;
                    this.$version = str;
                    this.this$0 = activityDirections;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Boolean bool2) {
                    invoke(str, bool.booleanValue(), bool2.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(@Nullable String str, boolean z, boolean z2) {
                    if (!z2) {
                        this.$result.invoke(Boolean.FALSE);
                    } else if (z) {
                        this.$result.invoke(Boolean.TRUE);
                    } else {
                        AcceptLegalTermsReq acceptLegalTermsReq = new AcceptLegalTermsReq();
                        acceptLegalTermsReq.type = NavigationConstants.MAP_NAV_DISCLAIMER;
                        acceptLegalTermsReq.version = this.$version;
                        acceptLegalTermsReq.medium = "DEVICE";
                        acceptLegalTermsReq.userDeviceId = Integer.valueOf(Integer.parseInt(PreferenceManager.getInstance().getUserDeviceID()));
                        acceptLegalTermsReq.acceptedDate = TimeUtils.covertTimeStamptoServerFormat(String.valueOf(System.currentTimeMillis()));
                        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = this.this$0.v;
                        if (activityNavigationDirectionsViewmodel == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            activityNavigationDirectionsViewmodel = null;
                        }
                        activityNavigationDirectionsViewmodel.updateBandAcceptedDisclaimerOnServer(acceptLegalTermsReq, new C0285a(this.$result));
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public a(ActivityDirections activityDirections, Function1<? super Boolean, Unit> function1) {
                super(3);
                this.this$0 = activityDirections;
                this.$result = function1;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, Boolean bool2, String str) {
                invoke(bool.booleanValue(), bool2.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, boolean z2, @NotNull String version) {
                Intrinsics.checkNotNullParameter(version, "version");
                if (!z2) {
                    this.$result.invoke(Boolean.FALSE);
                } else if (z) {
                    ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = this.this$0.v;
                    if (activityNavigationDirectionsViewmodel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityNavigationDirectionsViewmodel = null;
                    }
                    activityNavigationDirectionsViewmodel.checkBandDisclaimerAcceptanceOnServer(version, new C0284a(this.$result, version, this.this$0));
                } else {
                    this.$result.invoke(Boolean.FALSE);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public c(Function1<? super Boolean, Unit> function1, Continuation<? super c> continuation) {
            super(2, continuation);
            this.$result = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new c(this.$result, continuation);
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
                ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = ActivityDirections.this.v;
                if (activityNavigationDirectionsViewmodel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    activityNavigationDirectionsViewmodel = null;
                }
                activityNavigationDirectionsViewmodel.getDisclaimerStatusFromBand(new a(ActivityDirections.this, this.$result));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityDirections$updateBandDisclaimerAndTripDetailsToServer$1", f = "ActivityDirections.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class d extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* loaded from: classes5.dex */
        public static final class a extends Lambda implements Function1<Boolean, Unit> {
            public final /* synthetic */ ActivityDirections this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(ActivityDirections activityDirections) {
                super(1);
                this.this$0 = activityDirections;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                this.this$0.W();
            }
        }

        public d(Continuation<? super d> continuation) {
            super(2, continuation);
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
                ActivityDirections activityDirections = ActivityDirections.this;
                activityDirections.e0(new a(activityDirections));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void F(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.N();
    }

    public static final void I(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void J(ActivityDirections this$0, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.dismissProgress();
        String string = this$0.getString(R.string.unable_to_fetch_location);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.unable_to_fetch_location)");
        this$0.showErrorDialog(string);
    }

    public static final void L(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(this$0) && BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            Intent intent = new Intent(this$0, ActivityNavigationSettings.class);
            intent.putExtra("isFromNavigation", true);
            this$0.startActivity(intent);
        } else if (!AppUtils.isNetConnected(this$0)) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.no_internet_connection), 1).show();
        } else if (BleApiManager.getInstance(this$0).getBleApi().getConnectionStatus() != ConnectionStatus.CONNECTED) {
            Toast.makeText(this$0, this$0.getString(com.coveiot.android.theme.R.string.watch_disconnected_msg), 1).show();
        }
    }

    public static final void O(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void P(final ActivityDirections this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Drawable drawable = this$0.getResources().getDrawable(R.drawable.no_internet);
        Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
        String string = this$0.getResources().getString(R.string.no_internet);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.no_internet)");
        String string2 = this$0.getResources().getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…able_internet_connection)");
        this$0.M(drawable, string, string2);
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        if (bottomSheetDialogImageTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            bottomSheetDialogImageTitleMessage.showBigIcon();
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this$0.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
            bottomSheetDialogImageTitleMessage2.setCancelable(false);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this$0.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
            String string3 = this$0.getString(R.string.exit_navigation);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.exit_navigation)");
            bottomSheetDialogImageTitleMessage3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDirections.Q(ActivityDirections.this, view);
                }
            });
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this$0.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
            if (bottomSheetDialogImageTitleMessage4.isShowing()) {
                return;
            }
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this$0.w;
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
            bottomSheetDialogImageTitleMessage5.show();
        }
    }

    public static final void Q(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.finish();
    }

    public static final void R(ActivityDirections this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        if (bottomSheetDialogImageTitleMessage != null) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
            if (bottomSheetDialogImageTitleMessage.isShowing()) {
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this$0.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                bottomSheetDialogImageTitleMessage2.dismiss();
            }
        }
    }

    public static final void S(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.N();
    }

    public static final void T(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this$0.w;
        Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
        bottomSheetDialogImageTitleMessage.dismiss();
        this$0.N();
    }

    public static final void Y(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.q && !this$0.C) {
            this$0.finish();
            return;
        }
        CoveNavigationService coveNavigationService = this$0.z;
        if (coveNavigationService != null) {
            coveNavigationService.setNavigationStartOrStopOnBand(false);
        }
        CoveNavigationService coveNavigationService2 = this$0.z;
        if (coveNavigationService2 != null) {
            coveNavigationService2.stopNavigation();
        }
        if (this$0.C) {
            this$0.finishAndRemoveTask();
        } else {
            this$0.N();
        }
    }

    public static final void a0(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.G = true;
        AppUtils.openLocationSettings(this$0, 12);
    }

    public static final void c0(ActivityDirections this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = this$0.E;
        if (bottomSheetDialogOneButtonOneTitle != null) {
            bottomSheetDialogOneButtonOneTitle.dismiss();
        }
        this$0.F = true;
        AppUtils.openAppSettings(this$0, 121);
    }

    public final void D(final Function1<? super Boolean, Unit> function1) {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            BleApiManager.getInstance(this).getBleApi().getData(new GetNavigationConfigurationRequest(), new DataResultListener() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$checkAudioOnWatchAndEnableMapplsNavigationAudio$1
                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataError(@NotNull BleBaseError error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    function1.invoke(Boolean.FALSE);
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onDataResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    Object responseData = response.getResponseData();
                    Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetNavigationConfigurationResponse");
                    function1.invoke(Boolean.valueOf(((GetNavigationConfigurationResponse) responseData).isAudioEnabled()));
                }

                @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
                public void onProgressUpdate(@NotNull ProgressData progress) {
                    Intrinsics.checkNotNullParameter(progress, "progress");
                }
            });
        } else {
            function1.invoke(Boolean.FALSE);
        }
    }

    public final void E() {
        if (AppUtils.isNetConnected(this) && AppUtils.checkIfLocationPermissionExists(this) && AppUtils.isGpsEnabled(this)) {
            if (this.q || this.C) {
                H();
            }
        } else if (this.q || this.C) {
            if (!AppUtils.isNetConnected(this)) {
                Drawable drawable = getResources().getDrawable(R.drawable.no_internet);
                Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.drawable.no_internet)");
                String string = getResources().getString(R.string.no_internet);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.no_internet)");
                String string2 = getResources().getString(R.string.please_ensure_that_you_have_a_stable_internet_connection);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…able_internet_connection)");
                M(drawable, string, string2);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.w;
                if (bottomSheetDialogImageTitleMessage != null) {
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                    bottomSheetDialogImageTitleMessage.setCancelable(false);
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.w;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                    String string3 = getString(R.string.exit_navigation);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.exit_navigation)");
                    bottomSheetDialogImageTitleMessage2.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.p
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ActivityDirections.F(ActivityDirections.this, view);
                        }
                    });
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.w;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                    if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                        return;
                    }
                    BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.w;
                    Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                    bottomSheetDialogImageTitleMessage4.show();
                }
            } else if (!AppUtils.checkIfLocationPermissionExists(this)) {
                G();
            } else if (AppUtils.isGpsEnabled(this)) {
            } else {
                Z();
            }
        }
    }

    public final void G() {
        PermissionUtils.checkPermission(this, "android.permission.ACCESS_FINE_LOCATION", new PermissionUtils.PermissionAskListener() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$checkLocationPermission$1
            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionAsk() {
                ActivityDirections activityDirections = ActivityDirections.this;
                ActivityCompat.requestPermissions(activityDirections, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, activityDirections.getLOCATION_PERMISSION_REQUEST_CODE());
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionDisabled() {
                ActivityDirections activityDirections = ActivityDirections.this;
                String string = activityDirections.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityDirections.b0(string);
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionGranted() {
                if (AppUtils.isGpsEnabled(ActivityDirections.this)) {
                    ActivityDirections.this.H();
                } else {
                    ActivityDirections.this.Z();
                }
            }

            @Override // com.coveiot.utils.permissions.PermissionUtils.PermissionAskListener
            public void onPermissionPreviouslyDenied() {
                ActivityDirections activityDirections = ActivityDirections.this;
                String string = activityDirections.getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                activityDirections.b0(string);
            }
        });
    }

    public final void H() {
        showProgress();
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProvider…(this@ActivityDirections)");
        this.x = fusedLocationProviderClient;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient2 = this.x;
            if (fusedLocationProviderClient2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fusedLocationProviderClient");
                fusedLocationProviderClient2 = null;
            }
            Task<Location> currentLocation = fusedLocationProviderClient2.getCurrentLocation(100, (CancellationToken) null);
            final a aVar = new a();
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.navigation.activities.l
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    ActivityDirections.I(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.navigation.activities.w
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ActivityDirections.J(ActivityDirections.this, exc);
                }
            });
        }
    }

    public final void K() {
        ActivityDirectionsBinding activityDirectionsBinding = this.p;
        if (activityDirectionsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDirectionsBinding = null;
        }
        activityDirectionsBinding.ivSetting.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.u
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDirections.L(ActivityDirections.this, view);
            }
        });
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$initListeners$2
            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
            }
        });
    }

    public final void M(Drawable drawable, String str, String str2) {
        this.w = new BottomSheetDialogImageTitleMessage(this, drawable, str, str2, false);
    }

    public final void N() {
        Intent intent = new Intent(this, ActivityNavigationMain.class);
        intent.setFlags(603979776);
        startActivity(intent);
    }

    public final void U() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new ActivityDirections$prepareForBandInitiatedNavigation$1(this, null), 3, null);
    }

    public final void V(AutoSuggestionData autoSuggestionData, DirectionsResponse directionsResponse, int i) {
        ActivityDirectionsBinding activityDirectionsBinding = null;
        List<DirectionsRoute> routes = directionsResponse != null ? directionsResponse.routes() : null;
        Integer valueOf = routes != null ? Integer.valueOf(routes.size()) : null;
        Intrinsics.checkNotNull(valueOf);
        if (valueOf.intValue() > 0) {
            List<RouteLeg> legs = routes.get(i).legs();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new DirectionsData("Current Location", "Starting from here", getResources().getDrawable(R.drawable.ic_start)));
            if (legs != null) {
                for (RouteLeg routeLeg : legs) {
                    List<LegStep> steps = routeLeg.steps();
                    if (steps != null) {
                        arrayList.addAll(steps);
                    }
                }
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    int i2 = 0;
                    while (it.hasNext()) {
                        int i3 = i2 + 1;
                        LegStep legStep = (LegStep) it.next();
                        String instruction = DirectionsUtils.getTextInstructions(legStep);
                        if (i2 == arrayList.size() - 1) {
                            Intrinsics.checkNotNull(autoSuggestionData);
                            String placeName = autoSuggestionData.getPlaceName();
                            Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                            Integer maneuverId = DirectionsUtils.getManeuverId(legStep);
                            Intrinsics.checkNotNullExpressionValue(maneuverId, "getManeuverId(legStep)");
                            arrayList2.add(new DirectionsData(placeName, instruction, NavigationUtilsKt.getManevurImages(this, maneuverId.intValue())));
                        } else {
                            Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                            String formatDistance = ThemesUtils.INSTANCE.formatDistance(legStep.distance());
                            Integer maneuverId2 = DirectionsUtils.getManeuverId(legStep);
                            Intrinsics.checkNotNullExpressionValue(maneuverId2, "getManeuverId(legStep)");
                            arrayList2.add(new DirectionsData(instruction, formatDistance, NavigationUtilsKt.getManevurImages(this, maneuverId2.intValue())));
                        }
                        String str = this.u;
                        LogHelper.d(str, "Manevur Id's" + DirectionsUtils.getManeuverId(legStep));
                        i2 = i3;
                    }
                    DirectionsAdaptor directionsAdaptor = new DirectionsAdaptor(arrayList2);
                    ActivityDirectionsBinding activityDirectionsBinding2 = this.p;
                    if (activityDirectionsBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityDirectionsBinding = activityDirectionsBinding2;
                    }
                    activityDirectionsBinding.rvDirection.setAdapter(directionsAdaptor);
                }
            }
        }
        X(directionsResponse, i);
    }

    public final void W() {
        FavouritePlace favouritePlace = new FavouritePlace();
        AutoSuggestionData autoSuggestionData = this.t;
        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = null;
        favouritePlace.setMapplsPin(autoSuggestionData != null ? autoSuggestionData.getMapplsPin() : null);
        AutoSuggestionData autoSuggestionData2 = this.t;
        favouritePlace.setName(autoSuggestionData2 != null ? autoSuggestionData2.getPlaceName() : null);
        AutoSuggestionData autoSuggestionData3 = this.t;
        favouritePlace.setFullAddress(autoSuggestionData3 != null ? autoSuggestionData3.getPlaceAddress() : null);
        if (Intrinsics.areEqual(this.B, "walking")) {
            favouritePlace.setMode("WALK");
        } else {
            favouritePlace.setMode(CodePackage.DRIVE);
        }
        favouritePlace.setEndDate(TimeUtils.covertTimeStamptoServerFormat(String.valueOf(System.currentTimeMillis())));
        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel2 = this.v;
        if (activityNavigationDirectionsViewmodel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            activityNavigationDirectionsViewmodel = activityNavigationDirectionsViewmodel2;
        }
        activityNavigationDirectionsViewmodel.saveTrip(favouritePlace, new b());
    }

    public final void X(DirectionsResponse directionsResponse, int i) {
        BigDecimal scale;
        if (directionsResponse != null) {
            ActivityDirectionsBinding activityDirectionsBinding = this.p;
            ActivityDirectionsBinding activityDirectionsBinding2 = null;
            if (activityDirectionsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding = null;
            }
            TextView textView = activityDirectionsBinding.tvEta;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvEta");
            visible(textView);
            ActivityDirectionsBinding activityDirectionsBinding3 = this.p;
            if (activityDirectionsBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding3 = null;
            }
            TextView textView2 = activityDirectionsBinding3.tvTotalDistance;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvTotalDistance");
            visible(textView2);
            ActivityDirectionsBinding activityDirectionsBinding4 = this.p;
            if (activityDirectionsBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding4 = null;
            }
            TextView textView3 = activityDirectionsBinding4.tvTotalDuration;
            Intrinsics.checkNotNullExpressionValue(textView3, "binding.tvTotalDuration");
            visible(textView3);
            ActivityDirectionsBinding activityDirectionsBinding5 = this.p;
            if (activityDirectionsBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding5 = null;
            }
            Button button = activityDirectionsBinding5.btnExit;
            Intrinsics.checkNotNullExpressionValue(button, "binding.btnExit");
            visible(button);
            ActivityDirectionsBinding activityDirectionsBinding6 = this.p;
            if (activityDirectionsBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding6 = null;
            }
            Button button2 = activityDirectionsBinding6.btnNavigationOngoing;
            Intrinsics.checkNotNullExpressionValue(button2, "binding.btnNavigationOngoing");
            visible(button2);
            ActivityDirectionsBinding activityDirectionsBinding7 = this.p;
            if (activityDirectionsBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding7 = null;
            }
            TextView textView4 = activityDirectionsBinding7.tvEta;
            StringBuilder sb = new StringBuilder();
            sb.append("Arrival: ");
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            Double duration = directionsResponse.routes().get(i).duration();
            Intrinsics.checkNotNull(duration);
            sb.append(themesUtils.addSecondsToCurrentTimeAndGetTimeIn12HoursFormat((long) duration.doubleValue()));
            textView4.setText(sb.toString());
            ActivityDirectionsBinding activityDirectionsBinding8 = this.p;
            if (activityDirectionsBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding8 = null;
            }
            TextView textView5 = activityDirectionsBinding8.tvTotalDuration;
            Double duration2 = directionsResponse.routes().get(i).duration();
            Intrinsics.checkNotNull(duration2);
            textView5.setText(themesUtils.formatSeconds((long) duration2.doubleValue()));
            Double distance = directionsResponse.routes().get(i).distance();
            Double valueOf = (distance == null || (scale = new BigDecimal(String.valueOf(distance.doubleValue() / 1000.0d)).setScale(2, RoundingMode.HALF_UP)) == null) ? null : Double.valueOf(scale.doubleValue());
            ActivityDirectionsBinding activityDirectionsBinding9 = this.p;
            if (activityDirectionsBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding9 = null;
            }
            activityDirectionsBinding9.tvTotalDistance.setText(valueOf + " Km");
            if (!this.q && !this.C) {
                ActivityDirectionsBinding activityDirectionsBinding10 = this.p;
                if (activityDirectionsBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDirectionsBinding10 = null;
                }
                Button button3 = activityDirectionsBinding10.btnNavigationOngoing;
                Intrinsics.checkNotNullExpressionValue(button3, "binding.btnNavigationOngoing");
                gone(button3);
                ActivityDirectionsBinding activityDirectionsBinding11 = this.p;
                if (activityDirectionsBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDirectionsBinding11 = null;
                }
                Button button4 = activityDirectionsBinding11.btnExit;
                Intrinsics.checkNotNullExpressionValue(button4, "binding.btnExit");
                visible(button4);
            } else {
                ActivityDirectionsBinding activityDirectionsBinding12 = this.p;
                if (activityDirectionsBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDirectionsBinding12 = null;
                }
                Button button5 = activityDirectionsBinding12.btnExit;
                Intrinsics.checkNotNullExpressionValue(button5, "binding.btnExit");
                visible(button5);
                ActivityDirectionsBinding activityDirectionsBinding13 = this.p;
                if (activityDirectionsBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDirectionsBinding13 = null;
                }
                Button button6 = activityDirectionsBinding13.btnNavigationOngoing;
                Intrinsics.checkNotNullExpressionValue(button6, "binding.btnNavigationOngoing");
                visible(button6);
            }
            ActivityDirectionsBinding activityDirectionsBinding14 = this.p;
            if (activityDirectionsBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDirectionsBinding2 = activityDirectionsBinding14;
            }
            activityDirectionsBinding2.btnExit.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.t
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDirections.Y(ActivityDirections.this, view);
                }
            });
        }
    }

    public final void Z() {
        String string = getString(com.coveiot.android.theme.R.string.location_disabled);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.an…string.location_disabled)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.v
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityDirections.a0(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
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

    public final void b0(String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.E;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.E == null || z) {
            this.E = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.E;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityDirections.c0(ActivityDirections.this, view);
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.E;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.E) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void d0() {
        bindService(new Intent(this, CoveNavigationService.class), this.J, 1);
    }

    public final void e0(Function1<? super Boolean, Unit> function1) {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new c(function1, null), 3, null);
    }

    public final void f0() {
        showProgress();
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), null, null, new d(null), 3, null);
    }

    @Nullable
    public final BottomSheetDialogImageTitleMessage getBottomSheetDialogImageTitleMessage() {
        return this.w;
    }

    @Nullable
    public final CoveNavigationService getCoveNavigationService() {
        return this.z;
    }

    public final int getLOCATION_PERMISSION_REQUEST_CODE() {
        return this.D;
    }

    public final int getModeOnBand() {
        return this.I;
    }

    public final int getPlaceIdOnBand() {
        return this.H;
    }

    public final boolean isFromGPSSettings() {
        return this.G;
    }

    public final boolean isFromLocationSettings() {
        return this.F;
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onCommonEvents(@NotNull CoveNavigationCallBacks navigationCallBacks) {
        Intrinsics.checkNotNullParameter(navigationCallBacks, "navigationCallBacks");
        int i = WhenMappings.$EnumSwitchMapping$0[navigationCallBacks.ordinal()];
        if (i == 1) {
            f0();
        } else if (i == 3) {
            CoveNavigationService coveNavigationService = this.z;
            if (coveNavigationService != null) {
                coveNavigationService.stopNavigation();
            }
            if (SessionManager.getInstance(this).isNavigationFinishActivity()) {
                SessionManager.getInstance(this).setNavigationFinishActivity(false);
                finishAndRemoveTask();
            }
            N();
        } else if (i == 4) {
            Drawable drawable = getResources().getDrawable(R.drawable.success_image_new_img);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…le.success_image_new_img)");
            String string = getResources().getString(R.string.destination_reached);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.destination_reached)");
            M(drawable, string, "");
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = this.w;
            if (bottomSheetDialogImageTitleMessage != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                String string2 = getString(R.string.exit_navigation);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.exit_navigation)");
                bottomSheetDialogImageTitleMessage2.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDirections.S(ActivityDirections.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                if (bottomSheetDialogImageTitleMessage3.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                bottomSheetDialogImageTitleMessage4.show();
            }
        } else if (i != 5) {
            if (i == 6) {
                runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityDirections.P(ActivityDirections.this);
                    }
                });
            } else if (i != 7) {
            } else {
                runOnUiThread(new Runnable() { // from class: com.coveiot.android.navigation.activities.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        ActivityDirections.R(ActivityDirections.this);
                    }
                });
            }
        } else {
            dismissProgress();
            Drawable drawable2 = getResources().getDrawable(R.drawable.ic_navigation_error);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.…able.ic_navigation_error)");
            String string3 = getResources().getString(R.string.navigation_error);
            Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.navigation_error)");
            String string4 = getResources().getString(R.string.unable_to_proceed);
            Intrinsics.checkNotNullExpressionValue(string4, "resources.getString(R.string.unable_to_proceed)");
            M(drawable2, string3, string4);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.w;
            if (bottomSheetDialogImageTitleMessage5 != null) {
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                bottomSheetDialogImageTitleMessage5.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
                String string5 = getString(R.string.okay);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.okay)");
                bottomSheetDialogImageTitleMessage6.setPositiveButton(string5, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.r
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDirections.T(ActivityDirections.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                if (bottomSheetDialogImageTitleMessage7.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage8 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage8);
                bottomSheetDialogImageTitleMessage8.show();
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityDirectionsBinding inflate = ActivityDirectionsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.p = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView(inflate.getRoot());
        this.v = (ActivityNavigationDirectionsViewmodel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityNavigationDirectionsViewmodel.class);
        if (getIntent() != null) {
            this.q = getIntent().getBooleanExtra("forNavigation", false);
            this.r = getIntent().getIntExtra("routeIndex", 0);
            this.B = String.valueOf(getIntent().getStringExtra("mode"));
            this.C = getIntent().getBooleanExtra("isFromBand", false);
            this.H = getIntent().getIntExtra("placeIdOnBand", 0);
            this.I = getIntent().getIntExtra("modeOnBand", 0);
        }
        BleApiManager.getInstance(this).getBleApi().registerConnectionStatus().observe(this, this);
        K();
        if (!this.C) {
            this.s = DirectionsResponse.fromJson(SessionManager.getInstance(this).getDirectionApiResponse());
            Type type = new TypeToken<AutoSuggestionData>() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$onCreate$listType$1
            }.getType();
            AutoSuggestionData autoSuggestionData = (AutoSuggestionData) new Gson().fromJson(SessionManager.getInstance(this).getTempDestinationAutosuggestionData(), type);
            this.t = autoSuggestionData;
            DirectionsResponse directionsResponse = this.s;
            if (directionsResponse != null && autoSuggestionData != null) {
                V(autoSuggestionData, directionsResponse, this.r);
            }
            E();
            return;
        }
        E();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.A) {
            unbindService(this.J);
            this.A = false;
        }
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onETARefreshed(@Nullable String str) {
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onEvent(@Nullable NavEvent navEvent) {
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onNewRoute(@NotNull List<NavigationStep> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        if (list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DirectionsData("Current Location", "Starting from here", getResources().getDrawable(R.drawable.ic_start)));
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object extraInfo = list.get(i).getExtraInfo();
                Intrinsics.checkNotNull(extraInfo, "null cannot be cast to non-null type com.mappls.sdk.services.api.directions.models.LegStep");
                LegStep legStep = (LegStep) extraInfo;
                String instruction = DirectionsUtils.getTextInstructions(legStep);
                if (i == list.size() - 1) {
                    AutoSuggestionData autoSuggestionData = this.t;
                    Intrinsics.checkNotNull(autoSuggestionData);
                    String placeName = autoSuggestionData.getPlaceName();
                    Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                    Integer maneuverId = DirectionsUtils.getManeuverId(legStep);
                    Intrinsics.checkNotNullExpressionValue(maneuverId, "getManeuverId(legStep)");
                    arrayList.add(new DirectionsData(placeName, instruction, NavigationUtilsKt.getManevurImages(this, maneuverId.intValue())));
                } else {
                    Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                    String formatDistance = ThemesUtils.INSTANCE.formatDistance(legStep.distance());
                    Integer maneuverId2 = DirectionsUtils.getManeuverId(legStep);
                    Intrinsics.checkNotNullExpressionValue(maneuverId2, "getManeuverId(legStep)");
                    arrayList.add(new DirectionsData(instruction, formatDistance, NavigationUtilsKt.getManevurImages(this, maneuverId2.intValue())));
                }
            }
            DirectionsAdaptor directionsAdaptor = new DirectionsAdaptor(arrayList);
            ActivityDirectionsBinding activityDirectionsBinding = this.p;
            if (activityDirectionsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDirectionsBinding = null;
            }
            activityDirectionsBinding.rvDirection.setAdapter(directionsAdaptor);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i == this.D) {
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
                b0(string);
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.F) {
            if (!AppUtils.checkIfLocationPermissionExists(this)) {
                String string = getString(R.string.location_permission_required);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.location_permission_required)");
                b0(string);
                return;
            }
            this.F = false;
            if (AppUtils.isGpsEnabled(this)) {
                H();
            } else {
                Z();
            }
        } else if (this.G) {
            if (!AppUtils.isGpsEnabled(this)) {
                Z();
                return;
            }
            this.G = false;
            H();
        }
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onRouteProgress(@NotNull AdviseInfo adviseInfo) {
        Intrinsics.checkNotNullParameter(adviseInfo, "adviseInfo");
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void onWayPointReached(@Nullable WayPoint wayPoint) {
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void removeProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener) {
    }

    public final void setBottomSheetDialogImageTitleMessage(@Nullable BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage) {
        this.w = bottomSheetDialogImageTitleMessage;
    }

    public final void setCoveNavigationService(@Nullable CoveNavigationService coveNavigationService) {
        this.z = coveNavigationService;
    }

    public final void setFromGPSSettings(boolean z) {
        this.G = z;
    }

    public final void setFromLocationSettings(boolean z) {
        this.F = z;
    }

    public final void setModeOnBand(int i) {
        this.I = i;
    }

    public final void setPlaceIdOnBand(int i) {
        this.H = i;
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void setProgressChangeListener(@Nullable ProgressChangeListener progressChangeListener) {
    }

    public final void showErrorDialog(String str) {
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, str);
        String string = getString(com.coveiot.android.theme.R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$showErrorDialog$1
            @Override // android.view.View.OnClickListener
            public void onClick(@Nullable View view) {
                BottomSheetDialogOneButtonOneTitle.this.dismiss();
                this.finish();
            }
        });
        if (bottomSheetDialogOneButtonOneTitle.isShowing()) {
            return;
        }
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.navigation.interfaces.CoveNavigationListener
    public void startingNavigationOnBandFailed() {
        dismissProgress();
        Toast.makeText(this, "Something went wrong", 1).show();
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(@Nullable ConnectionStatus connectionStatus) {
        int i = connectionStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$1[connectionStatus.ordinal()];
        if (i != 1) {
            if (i == 2 && this.w == null) {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_bluetooth_disconnected);
                Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…c_bluetooth_disconnected)");
                String string = getResources().getString(R.string.bluetooth_disconnected);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…g.bluetooth_disconnected)");
                String string2 = getResources().getString(R.string.connect_smartwatch_to_the_boat_crest_app_to_get_turn);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…at_crest_app_to_get_turn)");
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(this, drawable, string, string2, false);
                this.w = bottomSheetDialogImageTitleMessage;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage);
                bottomSheetDialogImageTitleMessage.showBigIcon();
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage2);
                bottomSheetDialogImageTitleMessage2.setCancelable(false);
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage3 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage3);
                String string3 = getString(R.string.exit_navigation);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.exit_navigation)");
                bottomSheetDialogImageTitleMessage3.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.o
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ActivityDirections.O(ActivityDirections.this, view);
                    }
                });
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage4 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage4);
                if (bottomSheetDialogImageTitleMessage4.isShowing()) {
                    return;
                }
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage5 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage5);
                bottomSheetDialogImageTitleMessage5.show();
                return;
            }
            return;
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage6 = this.w;
        if (bottomSheetDialogImageTitleMessage6 != null) {
            Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage6);
            if (bottomSheetDialogImageTitleMessage6.isShowing()) {
                BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage7 = this.w;
                Intrinsics.checkNotNull(bottomSheetDialogImageTitleMessage7);
                bottomSheetDialogImageTitleMessage7.dismiss();
                this.w = null;
            }
        }
    }
}
