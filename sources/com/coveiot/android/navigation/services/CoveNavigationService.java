package com.coveiot.android.navigation.services;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DynamicSportFieldTextV2;
import com.coveiot.android.bleabstract.models.NavigationItem;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetNavigationWatchStateRequest;
import com.coveiot.android.bleabstract.request.SetNavigationApplicationContentRequest;
import com.coveiot.android.bleabstract.request.SetNavigationEventRequest;
import com.coveiot.android.bleabstract.request.SetNavigationStatusRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetNavigationWatchStateResponse;
import com.coveiot.android.navigation.activities.ActivityDirections;
import com.coveiot.android.navigation.interfaces.CoveNavigationListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.DirectionsDataForBand;
import com.coveiot.android.navigation.network.NetworkMonitor;
import com.coveiot.android.navigation.network.NetworkMonitorObserver;
import com.coveiot.android.navigation.utils.CoveNavigationCallBacks;
import com.coveiot.android.navigation.utils.NavigationUtilsKt;
import com.coveiot.android.theme.R;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.task.ITask;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.camera.INavigation;
import com.mappls.sdk.navigation.camera.ProgressChangeListener;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.plugin.directions.DirectionsUtils;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class CoveNavigationService extends Service implements INavigationListener, INavigation {
    public AutoSuggestionData destinationSuggestionData;
    @Nullable
    public CoveNavigationListener i;
    public Handler k;
    @Nullable
    public DirectionsResponse p;
    public int r;
    public long s;
    public int u;
    public int v;
    @NotNull
    public IBinder h = new NavigationServiceBinder();
    @NotNull
    public final Lazy j = LazyKt__LazyJVMKt.lazy(d.INSTANCE);
    public final String l = CoveNavigationService.class.getSimpleName();
    @NotNull
    public final String m = "navigation_is_ongoing";
    public final int n = 5;
    @NotNull
    public String o = "walking";
    @NotNull
    public final Lazy q = LazyKt__LazyJVMKt.lazy(new a());
    public final int t = 90000;

    /* loaded from: classes5.dex */
    public class NavigationServiceBinder extends Binder {
        public NavigationServiceBinder() {
        }

        @NotNull
        public final CoveNavigationService getService() {
            return CoveNavigationService.this;
        }
    }

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function0<NetworkMonitorObserver> {
        public a() {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final NetworkMonitorObserver invoke() {
            return new NetworkMonitorObserver(CoveNavigationService.this);
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
            CoveNavigationService.this.D(0, false, false);
        }
    }

    /* loaded from: classes5.dex */
    public static final class c extends Lambda implements Function1<Boolean, Unit> {
        public final /* synthetic */ AdviseInfo $p0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(AdviseInfo adviseInfo) {
            super(1);
            this.$p0 = adviseInfo;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            CoveNavigationService.this.D(this.$p0.getPosition(), false, true);
        }
    }

    /* loaded from: classes5.dex */
    public static final class d extends Lambda implements Function0<ExecutorService> {
        public static final d INSTANCE = new d();

        public d() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final ExecutorService invoke() {
            return Executors.newSingleThreadExecutor();
        }
    }

    /* loaded from: classes5.dex */
    public static final class e extends Lambda implements Function1<Boolean, Unit> {
        public static final e INSTANCE = new e();

        public e() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
        }
    }

    public static final void A(CoveNavigationService this$0, AdviseInfo p0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(p0, "$p0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onRouteProgress(p0);
        }
    }

    public static final void B(CoveNavigationService this$0, WayPoint wayPoint) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onWayPointReached(wayPoint);
        }
    }

    public static final void C(CoveNavigationService this$0, ProgressChangeListener progressChangeListener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.removeProgressChangeListener(progressChangeListener);
        }
    }

    public static final void E(CoveNavigationService this$0, ProgressChangeListener progressChangeListener) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.setProgressChangeListener(progressChangeListener);
        }
    }

    public static final void G(DirectionsResponse response, int i, Location currentLocation, String desMapplsPin, final CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(response, "$response");
        Intrinsics.checkNotNullParameter(currentLocation, "$currentLocation");
        Intrinsics.checkNotNullParameter(desMapplsPin, "$desMapplsPin");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (MapplsNavigationHelper.getInstance().startNavigation(response, i, new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), new WayPoint(desMapplsPin, "", ""), (List<WayPoint>) null).getError() != null) {
            Handler handler = this$0.k;
            if (handler == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
                handler = null;
            }
            handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.g
                @Override // java.lang.Runnable
                public final void run() {
                    CoveNavigationService.H(CoveNavigationService.this);
                }
            });
        }
    }

    public static final void H(CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LogHelper.d(this$0.l, "startNavigation navigationResponse.error");
        this$0.setNavigationStatusOnBand(0, e.INSTANCE);
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.NavigationError);
        }
    }

    public static final void t(CoveNavigationService this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onETARefreshed(str);
        }
    }

    public static final void u(CoveNavigationService this$0, NavEvent navEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onEvent(navEvent);
        }
    }

    public static final void v(CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.NavigationCancelled);
        }
    }

    public static final void w(CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.NavigationFinished);
        }
    }

    public static final void x(CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.NavigationStarted);
        }
    }

    public static final void y(CoveNavigationService this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            Intrinsics.checkNotNullExpressionValue(list, "list");
            coveNavigationListener.onNewRoute(list);
        }
    }

    public static final void z(CoveNavigationService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CoveNavigationListener coveNavigationListener = this$0.i;
        if (coveNavigationListener != null) {
            coveNavigationListener.onCommonEvents(CoveNavigationCallBacks.ReRoutingRequested);
        }
    }

    public final void D(int i, final boolean z, boolean z2) {
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            DirectionsResponse directionsResponse = this.p;
            Intrinsics.checkNotNull(directionsResponse);
            int i2 = 0;
            Double duration = directionsResponse.routes().get(0).duration();
            Intrinsics.checkNotNull(duration);
            Triple<Long, Long, Long> etaForBand = NavigationUtilsKt.getEtaForBand((long) duration.doubleValue());
            int longValue = etaForBand.getFirst().longValue() > 6 ? 0 : (int) etaForBand.getFirst().longValue();
            ThemesUtils themesUtils = ThemesUtils.INSTANCE;
            DirectionsResponse directionsResponse2 = this.p;
            Intrinsics.checkNotNull(directionsResponse2);
            Double duration2 = directionsResponse2.routes().get(0).duration();
            Intrinsics.checkNotNull(duration2);
            String addSecondsToCurrentTimeAndGetTimeIn24HoursFormat = themesUtils.addSecondsToCurrentTimeAndGetTimeIn24HoursFormat((long) duration2.doubleValue());
            int parseInt = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) addSecondsToCurrentTimeAndGetTimeIn24HoursFormat, new String[]{":"}, false, 0, 6, (Object) null).get(0));
            int parseInt2 = Integer.parseInt((String) StringsKt__StringsKt.split$default((CharSequence) addSecondsToCurrentTimeAndGetTimeIn24HoursFormat, new String[]{":"}, false, 0, 6, (Object) null).get(1));
            DirectionsResponse directionsResponse3 = this.p;
            Intrinsics.checkNotNull(directionsResponse3);
            Double distance = directionsResponse3.routes().get(0).distance();
            List<DirectionsDataForBand> list = getlatestDirections(i, p(i, z2));
            ArrayList arrayList = new ArrayList();
            if (!z) {
                for (Object obj : list) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    DirectionsDataForBand directionsDataForBand = (DirectionsDataForBand) obj;
                    arrayList.add(new NavigationItem(directionsDataForBand.getManevurId(), new DynamicSportFieldTextV2(1, NavigationUtilsKt.parseColor("0xf800"), 55, 150, 130, ITask.EVT_START, 22, 0, directionsDataForBand.getInstruction(), 2), directionsDataForBand.getDistance()));
                    i2 = i3;
                }
            }
            Intrinsics.checkNotNull(distance);
            BleApiManager.getInstance(this).getBleApi().setUserSettings(new SetNavigationApplicationContentRequest(0, 0, longValue, parseInt, parseInt2, (long) distance.doubleValue(), arrayList), new SettingsResultListener() { // from class: com.coveiot.android.navigation.services.CoveNavigationService$sendInstructionsToBand$2
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    String str;
                    Intrinsics.checkNotNullParameter(error, "error");
                    CoveNavigationService.this.s = System.currentTimeMillis();
                    str = CoveNavigationService.this.l;
                    LogHelper.d(str, "onSettingsError sendInstructionsToBand error");
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    String str;
                    Intrinsics.checkNotNullParameter(response, "response");
                    CoveNavigationService.this.s = System.currentTimeMillis();
                    if (z) {
                        CoveNavigationService.this.setNavigationStartOrStopOnBand(false);
                    }
                    str = CoveNavigationService.this.l;
                    LogHelper.d(str, "onSettingsResponse sendInstructionsToBand Success");
                }
            });
        }
    }

    public final void F() {
        NotificationCompat.Builder builder;
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, ActivityDirections.class), 67108864);
        Intrinsics.checkNotNullExpressionValue(activity, "getActivity(this, 0, int…ingIntent.FLAG_IMMUTABLE)");
        String string = getString(R.string.app_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.app_name)");
        String string2 = getString(R.string.navigation_ongoing);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.navigation_ongoing)");
        if (Build.VERSION.SDK_INT >= 26) {
            o();
            builder = new NotificationCompat.Builder(this, this.m).setChannelId(this.m);
            Intrinsics.checkNotNullExpressionValue(builder, "Builder(this, CHANNEL_ID….setChannelId(CHANNEL_ID)");
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setSmallIcon(R.drawable.ic_stat_notification_icon_cove).setContentTitle(string).setContentText(string2).setColor(getResources().getColor(R.color.colorPrimary)).setContentIntent(activity).setPriority(-2);
        startForeground(this.n, builder.build());
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

    public final void getNavigationStateFromBand(@NotNull final Function1<? super GetNavigationWatchStateResponse, Unit> state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BleApiManager.getInstance(this).getBleApi().getData(new GetNavigationWatchStateRequest(), new DataResultListener() { // from class: com.coveiot.android.navigation.services.CoveNavigationService$getNavigationStateFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                state.invoke(null);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetNavigationWatchStateResponse");
                state.invoke((GetNavigationWatchStateResponse) responseData);
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final int getPreviouPosition() {
        return this.u;
    }

    public final int getPreviousDistanceToNextAdvise() {
        return this.v;
    }

    @NotNull
    public final List<DirectionsDataForBand> getlatestDirections(int i, @NotNull ArrayList<DirectionsDataForBand> itemList) {
        Intrinsics.checkNotNullParameter(itemList, "itemList");
        int i2 = i + 5;
        if (i2 > itemList.size()) {
            i2 = itemList.size();
        }
        if (i <= i2) {
            List<DirectionsDataForBand> subList = itemList.subList(i, i2);
            Intrinsics.checkNotNullExpressionValue(subList, "{\n            itemList.s…ndex, endIndex)\n        }");
            return subList;
        }
        return CollectionsKt__CollectionsKt.emptyList();
    }

    public final boolean isThereChangeAtleast(int i, int i2, int i3) {
        return Math.abs(i3 - i2) >= i;
    }

    public final double n(double d2, double d3) {
        if (d3 == 0.0d) {
            return 1.0d;
        }
        return ((d2 - d3) / d3) * 100;
    }

    public final void o() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = getString(R.string.navigation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.navigation)");
            String string2 = getString(R.string.navigation_ongoing);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.navigation_ongoing)");
            NotificationChannel notificationChannel = new NotificationChannel(this.m, string, 1);
            notificationChannel.setDescription(string2);
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@Nullable Intent intent) {
        return this.h;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        F();
        this.k = new Handler(Looper.getMainLooper());
        MapplsNavigationHelper.getInstance().addNavigationListener(this);
        s();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        r().isShutdown();
        this.i = null;
        stopNavigation();
        stopSelf();
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onETARefreshed(@Nullable final String str) {
        String str2 = this.l;
        LogHelper.d(str2, "mmiLogs onETARefreshed p0 " + str);
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.b
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.t(CoveNavigationService.this, str);
            }
        });
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onEvent(@Nullable final NavEvent navEvent) {
        String str = this.l;
        LogHelper.d(str, "mmiLogs onEvent p0 " + navEvent);
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.l
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.u(CoveNavigationService.this, navEvent);
            }
        });
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationCancelled() {
        LogHelper.d(this.l, "mmiLogs onNavigationCancelled");
        stopNavigation();
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.e
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.v(CoveNavigationService.this);
            }
        });
        setNavigationStartOrStopOnBand(false);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationFinished() {
        LogHelper.d(this.l, "mmiLogs onNavigationFinished");
        MapplsNavigationHelper.getInstance().removeNavigationListener(this);
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.a
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.w(CoveNavigationService.this);
            }
        });
        D(0, true, false);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationStarted() {
        LogHelper.d(this.l, "mmiLogs onNavigationStarted");
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.h
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.x(CoveNavigationService.this);
            }
        });
        setNavigationStartOrStopOnBand(true);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNewRoute(@Nullable String str) {
        String str2 = this.l;
        LogHelper.d(str2, "mmiLogs onNewRoute p0 " + str);
        final List navigationSteps = MapplsNavigationHelper.getInstance().getNavigationSteps();
        if (System.currentTimeMillis() - this.s >= this.t) {
            String str3 = this.l;
            LogHelper.d(str3, "88CheckTimer onNewRoute setNavigationStatusOnBand System.currentTimeMillis() " + System.currentTimeMillis() + " lastSentTimestamp " + this.s + ' ' + (System.currentTimeMillis() - this.s) + " intervalMillis " + this.t);
            setNavigationStatusOnBand(2, new b());
        } else {
            String str4 = this.l;
            LogHelper.d(str4, "88CheckTimer onNewRoute setNavigationStatusOnBand else sendInstructionsToBand System.currentTimeMillis() " + System.currentTimeMillis() + " lastSentTimestamp " + this.s + ' ' + (System.currentTimeMillis() - this.s) + " intervalMillis " + this.t);
            D(0, false, false);
        }
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.c
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.y(CoveNavigationService.this, navigationSteps);
            }
        });
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onReRoutingRequested() {
        LogHelper.d(this.l, "mmiLogs onReRoutingRequested");
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.f
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.z(CoveNavigationService.this);
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0121, code lost:
        if (r0 <= (-1.0d)) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0129, code lost:
        if (r13.getPosition() != r12.u) goto L27;
     */
    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onRouteProgress(@org.jetbrains.annotations.NotNull final com.mappls.sdk.navigation.model.AdviseInfo r13) {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.services.CoveNavigationService.onRouteProgress(com.mappls.sdk.navigation.model.AdviseInfo):void");
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onWayPointReached(@Nullable final WayPoint wayPoint) {
        String str = this.l;
        LogHelper.d(str, "mmiLogs onWayPointReached p0 " + wayPoint);
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.k
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.B(CoveNavigationService.this, wayPoint);
            }
        });
    }

    public final ArrayList<DirectionsDataForBand> p(int i, boolean z) {
        List navigationSteps = MapplsNavigationHelper.getInstance().getNavigationSteps();
        ArrayList<DirectionsDataForBand> arrayList = new ArrayList<>();
        int size = navigationSteps.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object extraInfo = ((NavigationStep) navigationSteps.get(i2)).getExtraInfo();
            Intrinsics.checkNotNull(extraInfo, "null cannot be cast to non-null type com.mappls.sdk.services.api.directions.models.LegStep");
            LegStep legStep = (LegStep) extraInfo;
            String instruction = DirectionsUtils.getTextInstructions(legStep);
            if (!z) {
                Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                Integer maneuverId = DirectionsUtils.getManeuverId(legStep);
                Intrinsics.checkNotNullExpressionValue(maneuverId, "getManeuverId(legStep)");
                arrayList.add(new DirectionsDataForBand(instruction, (long) legStep.distance(), NavigationUtilsKt.getBandManevurId(maneuverId.intValue())));
            } else if (i == i2) {
                Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                Integer maneuverId2 = DirectionsUtils.getManeuverId(legStep);
                Intrinsics.checkNotNullExpressionValue(maneuverId2, "getManeuverId(legStep)");
                arrayList.add(new DirectionsDataForBand(instruction, this.r, NavigationUtilsKt.getBandManevurId(maneuverId2.intValue())));
            } else {
                Intrinsics.checkNotNullExpressionValue(instruction, "instruction");
                Integer maneuverId3 = DirectionsUtils.getManeuverId(legStep);
                Intrinsics.checkNotNullExpressionValue(maneuverId3, "getManeuverId(legStep)");
                arrayList.add(new DirectionsDataForBand(instruction, (long) legStep.distance(), NavigationUtilsKt.getBandManevurId(maneuverId3.intValue())));
            }
        }
        return arrayList;
    }

    public final NetworkMonitor q() {
        return (NetworkMonitor) this.q.getValue();
    }

    public final ExecutorService r() {
        return (ExecutorService) this.j.getValue();
    }

    public final void registerCoveNavigationListener(@NotNull Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.i = (CoveNavigationListener) activity;
    }

    @Override // com.mappls.sdk.navigation.camera.INavigation
    public void removeProgressChangeListener(@Nullable final ProgressChangeListener progressChangeListener) {
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.i
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.C(CoveNavigationService.this, progressChangeListener);
            }
        });
    }

    public final void s() {
        kotlinx.coroutines.e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CoveNavigationService$observeNetworkStatus$1(this, null), 3, null);
    }

    public final void setDestinationSuggestionData(@NotNull AutoSuggestionData autoSuggestionData) {
        Intrinsics.checkNotNullParameter(autoSuggestionData, "<set-?>");
        this.destinationSuggestionData = autoSuggestionData;
    }

    public final void setNavigationStartOrStopOnBand(final boolean z) {
        SetNavigationEventRequest build;
        if (BleApiManager.getInstance(this).getBleApi().getConnectionStatus() == ConnectionStatus.CONNECTED) {
            int i = 0;
            if (!this.o.equals("walking") && this.o.equals("driving")) {
                i = 1;
            }
            if (z) {
                build = new SetNavigationEventRequest.Builder().setEvent(z).setSource("Current Location").setDestination(getDestinationSuggestionData().getPlaceName()).setMode(i).build();
            } else {
                build = new SetNavigationEventRequest.Builder().setEvent(z).build();
            }
            BleApiManager.getInstance(this).getBleApi().setUserSettings(build, new SettingsResultListener() { // from class: com.coveiot.android.navigation.services.CoveNavigationService$setNavigationStartOrStopOnBand$1
                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsError(@NotNull BleBaseError error) {
                    CoveNavigationListener coveNavigationListener;
                    Intrinsics.checkNotNullParameter(error, "error");
                    coveNavigationListener = this.i;
                    if (coveNavigationListener != null) {
                        coveNavigationListener.startingNavigationOnBandFailed();
                    }
                }

                @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
                public void onSettingsResponse(@NotNull BleBaseResponse response) {
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (z) {
                        this.D(0, false, false);
                    }
                }
            });
        }
    }

    public final void setNavigationStatusOnBand(final int i, @NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this).getBleApi().setUserSettings(new SetNavigationStatusRequest(i), new SettingsResultListener() { // from class: com.coveiot.android.navigation.services.CoveNavigationService$setNavigationStatusOnBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(Boolean.FALSE);
                str = this.l;
                LogHelper.d(str, "setNavigationStatusOnBand onSettingsError status " + i);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                result.invoke(Boolean.TRUE);
                str = this.l;
                LogHelper.d(str, "setNavigationStatusOnBand onSettingsResponse status " + i);
            }
        });
    }

    public final void setPreviouPosition(int i) {
        this.u = i;
    }

    public final void setPreviousDistanceToNextAdvise(int i) {
        this.v = i;
    }

    @Override // com.mappls.sdk.navigation.camera.INavigation
    public void setProgressChangeListener(@Nullable final ProgressChangeListener progressChangeListener) {
        Handler handler = this.k;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uiUpdateHandler");
            handler = null;
        }
        handler.post(new Runnable() { // from class: com.coveiot.android.navigation.services.j
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.E(CoveNavigationService.this, progressChangeListener);
            }
        });
    }

    public final void startNavigation(@NotNull final Location currentLocation, @NotNull final DirectionsResponse response, @NotNull final String desMapplsPin, final int i, @NotNull AutoSuggestionData destinationSuggestionData, @NotNull String mode) {
        Intrinsics.checkNotNullParameter(currentLocation, "currentLocation");
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(desMapplsPin, "desMapplsPin");
        Intrinsics.checkNotNullParameter(destinationSuggestionData, "destinationSuggestionData");
        Intrinsics.checkNotNullParameter(mode, "mode");
        setDestinationSuggestionData(destinationSuggestionData);
        this.o = mode;
        this.p = response;
        MapplsNavigationHelper.getInstance().setCloseServiceOnRemovingTask(false);
        r().execute(new Runnable() { // from class: com.coveiot.android.navigation.services.d
            @Override // java.lang.Runnable
            public final void run() {
                CoveNavigationService.G(DirectionsResponse.this, i, currentLocation, desMapplsPin, this);
            }
        });
    }

    public final void stopNavigation() {
        r().isShutdown();
        MapplsNavigationHelper.getInstance().stopNavigation();
        MapplsNavigationHelper.getInstance().removeNavigationListener(this);
    }

    public final void unRegisterCoveNavigationListener() {
        this.i = null;
    }
}
