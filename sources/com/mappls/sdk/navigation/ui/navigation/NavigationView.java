package com.mappls.sdk.navigation.ui.navigation;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.gestures.MoveGestureDetector;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.MapplsMapOptions;
import com.mappls.sdk.maps.OnMapReadyCallback;
import com.mappls.sdk.maps.Style;
import com.mappls.sdk.maps.annotations.MarkerOptions;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.style.OnStyleLoadListener;
import com.mappls.sdk.maps.style.layers.Layer;
import com.mappls.sdk.maps.style.layers.PropertyFactory;
import com.mappls.sdk.navigation.AlternateRoute;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.NavigationLocationProvider;
import com.mappls.sdk.navigation.camera.MathUtils;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.events.NavEvent;
import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.iface.LocationChangedListener;
import com.mappls.sdk.navigation.iface.NavigationEventListener;
import com.mappls.sdk.navigation.iface.NavigationEventLoadedListener;
import com.mappls.sdk.navigation.iface.OnSpeedLimitListener;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.NavigationOptions;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.databinding.LayoutNavigationViewBinding;
import com.mappls.sdk.navigation.ui.map.route.v;
import com.mappls.sdk.navigation.ui.navigation.finished.NavigationFinishedView;
import com.mappls.sdk.navigation.util.GPSInfo;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.LegAnnotation;
import com.mappls.sdk.services.api.directions.models.LegStep;
import com.mappls.sdk.services.api.directions.models.RouteLeg;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.weather.WeatherCriteria;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;
@Keep
/* loaded from: classes11.dex */
public class NavigationView extends CoordinatorLayout implements LifecycleOwner, OnMapReadyCallback, MapplsMap.OnMoveListener, LocationChangedListener, INavigationListener {
    private NavigationApplication app;
    private AudioManager audioManager;
    public LayoutNavigationViewBinding binding;
    public Handler gpsHandler;
    private GPSInfo gpsInfo;
    public Runnable gpsRunnable;
    private boolean isConfigurationChange;
    private boolean isInPIPMode;
    private boolean isMapInitialised;
    private LifecycleRegistry lifecycleRegistry;
    private MapView mapView;
    private MapplsMap mapplsMap;
    private NavigationCallback navigationCallback;
    private com.mappls.sdk.navigation.ui.map.b navigationMap;
    private NavigationViewCallback navigationViewCallback;
    private com.mappls.sdk.navigation.ui.navigation.a navigationViewModel;
    private final NavigationOptions options;

    /* loaded from: classes11.dex */
    public class a implements com.mappls.sdk.navigation.ui.navigation.searchalongroute.a {
        public a() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.searchalongroute.a
        public void a(ELocation eLocation) {
            if (eLocation != null) {
                MapplsNavigationViewHelper.getInstance().clearWayPoints();
                MapplsNavigationViewHelper.getInstance().setDestination(eLocation);
                if (eLocation.latitude == null || eLocation.longitude == null) {
                    MapplsNavigationHelper mapplsNavigationHelper = MapplsNavigationHelper.getInstance();
                    String mapplsPin = eLocation.getMapplsPin();
                    String str = eLocation.placeName;
                    mapplsNavigationHelper.navigateTo(mapplsPin, str, str);
                } else {
                    MapplsNavigationHelper mapplsNavigationHelper2 = MapplsNavigationHelper.getInstance();
                    LatLng latLng = new LatLng(eLocation.latitude.doubleValue(), eLocation.longitude.doubleValue());
                    String str2 = eLocation.placeName;
                    mapplsNavigationHelper2.navigateTo(latLng, str2, str2);
                }
                if (NavigationView.this.mapplsMap != null) {
                    NavigationView.this.mapplsMap.clear();
                }
                if (NavigationView.this.navigationViewModel != null) {
                    NavigationView.this.navigationViewModel.c.setValue(eLocation);
                }
            }
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.searchalongroute.a
        public void b(ELocation eLocation) {
            if (eLocation != null) {
                MapplsNavigationViewHelper.getInstance().addWayPoint(eLocation);
                if (eLocation.latitude == null || eLocation.longitude == null) {
                    MapplsNavigationHelper mapplsNavigationHelper = MapplsNavigationHelper.getInstance();
                    String mapplsPin = eLocation.getMapplsPin();
                    String str = eLocation.placeName;
                    mapplsNavigationHelper.addWayPoint(mapplsPin, 0, str, str);
                } else {
                    MapplsNavigationHelper mapplsNavigationHelper2 = MapplsNavigationHelper.getInstance();
                    LatLng latLng = new LatLng(eLocation.latitude.doubleValue(), eLocation.longitude.doubleValue());
                    String str2 = eLocation.placeName;
                    mapplsNavigationHelper2.addWayPoint(latLng, 0, str2, str2);
                }
                if (NavigationView.this.mapplsMap != null) {
                    NavigationView.this.mapplsMap.clear();
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements com.mappls.sdk.navigation.ui.navigation.directions.b {
        public b() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.directions.b
        public void a(NavigationStep navigationStep) {
            NavigationView.this.binding.directionList.hide();
            NavigationView.this.binding.nextAdviseView.setVisibility(8);
            if (navigationStep.getPosition() > NavigationView.this.binding.instructionContainer.getCurrentItem()) {
                NavigationView.this.binding.instructionContainer.setCurrentItem(navigationStep.getPosition() == 0 ? 1 : navigationStep.getPosition());
                if (NavigationView.this.navigationMap != null) {
                    NavigationView.this.navigationMap.c(true);
                    NavigationView.this.navigationMap.d(false);
                }
                if (NavigationView.this.binding.followButton.getVisibility() != 0) {
                    NavigationView.this.binding.followButton.setVisibility(0);
                }
                NavigationView.this.fixPreviewNavigationMarker();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class c implements NavigationEventLoadedListener {
        public c() {
        }

        @Override // com.mappls.sdk.navigation.iface.NavigationEventLoadedListener
        public void onNavigationEventsLoaded(List<ReportDetails> list) {
            NavigationView.this.updateEvents(list);
        }
    }

    /* loaded from: classes11.dex */
    public class d implements NavigationEventListener {
        public d() {
        }

        @Override // com.mappls.sdk.navigation.iface.NavigationEventListener
        public void onNavigationEvent(NavEvent navEvent) {
            if (navEvent != null || NavigationView.this.isInPIPMode) {
                NavigationView.this.binding.speedWarningButton.setVisibility(8);
            }
            if (NavigationView.this.isInPIPMode) {
                NavigationView.this.binding.alertView.updateRouteEvent(null);
            } else {
                NavigationView.this.binding.alertView.updateRouteEvent(navEvent);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class e implements OnStyleLoadListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MapplsMap f13001a;

        /* loaded from: classes11.dex */
        public class a implements Style.OnStyleLoaded {
            public a(e eVar) {
            }

            @Override // com.mappls.sdk.maps.Style.OnStyleLoaded
            public void onStyleLoaded(@NonNull Style style) {
                Layer layer = style.getLayer("mapbox-location-stroke-layer");
                if (layer != null) {
                    layer.setProperties(PropertyFactory.visibility("none"));
                }
            }
        }

        public e(MapplsMap mapplsMap) {
            this.f13001a = mapplsMap;
        }

        @Override // com.mappls.sdk.maps.style.OnStyleLoadListener
        public void onError(String str) {
            NavigationView navigationView = NavigationView.this;
            navigationView.navigationMap = new com.mappls.sdk.navigation.ui.map.b(navigationView.mapView, this.f13001a);
            NavigationView.this.locationModeNavigation(true);
            this.f13001a.removeAnnotations();
            NavigationView.this.navigationMap.a(MapplsNavigationHelper.getInstance().getEvents());
            AdviseInfo adviseInfo = MapplsNavigationHelper.getInstance().getAdviseInfo();
            if (adviseInfo != null) {
                adviseInfo.setLocation(NavigationLocationProvider.convertLocation(NavigationView.this.getLocationForNavigation(), NavigationView.this.app));
                NavigationView.this.onRouteProgress(adviseInfo);
            }
            if (NavigationView.this.navigationMap != null) {
                NavigationView.this.navigationMap.a(NavigationView.this.getLocationForNavigation());
            }
            NavigationView navigationView2 = NavigationView.this;
            navigationView2.binding.infobarView.isTrafficEnable(navigationView2.navigationMap.f());
            NavigationView.this.drawPolyLine();
            this.f13001a.getStyle(new a(this));
            NavigationView.this.isMapInitialised = true;
        }

        @Override // com.mappls.sdk.maps.style.OnStyleLoadListener
        public void onStyleLoaded(Style style) {
            NavigationView navigationView = NavigationView.this;
            navigationView.navigationMap = new com.mappls.sdk.navigation.ui.map.b(navigationView.mapView, this.f13001a);
            NavigationView.this.locationModeNavigation(true);
            this.f13001a.removeAnnotations();
            NavigationView.this.navigationMap.a(MapplsNavigationHelper.getInstance().getEvents());
            AdviseInfo adviseInfo = MapplsNavigationHelper.getInstance().getAdviseInfo();
            if (adviseInfo != null) {
                adviseInfo.setLocation(NavigationLocationProvider.convertLocation(NavigationView.this.getLocationForNavigation(), NavigationView.this.app));
                NavigationView.this.onRouteProgress(adviseInfo);
            }
            if (NavigationView.this.navigationMap != null) {
                NavigationView.this.navigationMap.a(NavigationView.this.getLocationForNavigation());
            }
            NavigationView navigationView2 = NavigationView.this;
            navigationView2.binding.infobarView.isTrafficEnable(navigationView2.navigationMap.f());
            NavigationView.this.drawPolyLine();
            NavigationView.this.isMapInitialised = true;
        }
    }

    /* loaded from: classes11.dex */
    public class f implements v {
        public f(NavigationView navigationView) {
        }

        @Override // com.mappls.sdk.navigation.ui.map.route.v
        public void a(int i) {
            MapplsNavigationHelper.getInstance().setSelectedIndex(i);
        }
    }

    /* loaded from: classes11.dex */
    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NavigationView navigationView;
            int color;
            String str;
            if (NavigationView.this.gpsInfo != null && !NavigationView.this.gpsInfo.fixed && !NavigationView.this.isInPIPMode && NavigationView.this.getResources().getConfiguration().orientation == 1) {
                navigationView = NavigationView.this;
                color = navigationView.getResources().getColor(R.color.red);
                str = "Searching for GPS";
            } else if (NavigationView.this.gpsInfo == null || NavigationView.this.gpsInfo.usedSatellites >= 3 || NavigationView.this.isInPIPMode || NavigationView.this.getResources().getConfiguration().orientation != 1) {
                NavigationView.this.hideWarning();
                return;
            } else {
                navigationView = NavigationView.this;
                color = navigationView.getResources().getColor(R.color.red);
                str = "Weak GPS connection";
            }
            navigationView.showWarning(str, color);
        }
    }

    /* loaded from: classes11.dex */
    public class h implements com.mappls.sdk.navigation.ui.navigation.sound.a {
        public h() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.sound.a
        public void a(boolean z) {
            MapplsNavigationHelper.getInstance().setMute(z);
            NavigationView.this.binding.settingsView.isMute(z);
        }
    }

    /* loaded from: classes11.dex */
    public class i implements OnSpeedLimitListener {
        public i() {
        }

        @Override // com.mappls.sdk.navigation.iface.OnSpeedLimitListener
        public void onSpeedChanged(double d, boolean z) {
            TextView textView;
            int i;
            if (NavigationView.this.binding.alertView.isShowingEvent()) {
                return;
            }
            if (NavigationView.this.isInPIPMode || !NavigationView.this.options.showSpeedWarning().booleanValue()) {
                textView = NavigationView.this.binding.speedWarningButton;
                i = 8;
            } else {
                TextView textView2 = NavigationView.this.binding.speedWarningButton;
                textView2.setText(((int) d) + "");
                textView = NavigationView.this.binding.speedWarningButton;
                i = 0;
            }
            textView.setVisibility(i);
        }
    }

    /* loaded from: classes11.dex */
    public class j implements com.mappls.sdk.navigation.ui.navigation.finished.a {
        public j() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.finished.a
        public void onNavigationEnd() {
            if (NavigationView.this.navigationViewCallback != null) {
                NavigationView.this.navigationViewCallback.onNavigationEnd();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class k implements com.mappls.sdk.navigation.ui.navigation.infobar.e {

        /* loaded from: classes11.dex */
        public class a implements OnStyleLoadListener {
            public a() {
            }

            @Override // com.mappls.sdk.maps.style.OnStyleLoadListener
            public void onError(String str) {
            }

            @Override // com.mappls.sdk.maps.style.OnStyleLoadListener
            public void onStyleLoaded(Style style) {
                if (NavigationView.this.navigationMap != null) {
                    NavigationView.this.navigationMap.g();
                }
            }
        }

        public k() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void a() {
            com.mappls.sdk.navigation.ui.map.b bVar;
            boolean z = false;
            if (NavigationView.this.navigationMap == null) {
                NavigationView.this.binding.infobarView.isTrafficEnable(false);
                return;
            }
            if (NavigationView.this.navigationMap.f()) {
                bVar = NavigationView.this.navigationMap;
            } else {
                bVar = NavigationView.this.navigationMap;
                z = true;
            }
            bVar.b(z);
            NavigationView navigationView = NavigationView.this;
            navigationView.binding.infobarView.isTrafficEnable(navigationView.navigationMap.f());
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void b() {
            NavigationView.this.binding.directionList.show();
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void c() {
            com.mappls.sdk.navigation.ui.theme.a.a(NavigationView.this.getContext());
            NavigationView.this.toggleTheme();
            NavigationView.this.getContext();
            NavigationView.this.mapplsMap.setMapplsStyle(com.mappls.sdk.navigation.ui.theme.a.a() ? NavigationView.this.options.mapplsMapDarkStyle() : NavigationView.this.options.mapplsMapLightStyle(), new a());
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void d() {
            if (MapplsNavigationHelper.getInstance().getCurrentRoute() == null || NavigationView.this.navigationMap == null) {
                return;
            }
            NavigationView.this.binding.followButton.show();
            NavigationView.this.navigationMap.d(false);
            NavigationView.this.navigationMap.f(false);
            NavigationView.this.navigationMap.q();
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void e() {
            MapplsNavigationHelper.getInstance().stopNavigation();
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.infobar.e
        public void f() {
            NavigationView.this.binding.settingsView.show();
        }
    }

    /* loaded from: classes11.dex */
    public class l implements View.OnClickListener {
        public l() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (NavigationView.this.navigationViewCallback != null) {
                NavigationView.this.navigationViewCallback.searchAlongRoute();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class m implements View.OnClickListener {
        public m() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (NavigationView.this.options.showNextInstructionBanner().booleanValue()) {
                NavigationView.this.binding.nextAdviseView.setVisibility(0);
            }
            NavigationView.this.binding.infobarView.showRouteOverview(true);
            NavigationView.this.followMe(true);
            if (NavigationView.this.navigationMap != null) {
                NavigationView.this.navigationMap.d(true);
            }
        }
    }

    /* loaded from: classes11.dex */
    public class n implements com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c {
        public n() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c
        public void a() {
            NavigationView.this.nextPreviousButtonPressed(true);
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.instructioncontainer.c
        public void b() {
            NavigationView.this.binding.nextAdviseView.setVisibility(8);
            if (NavigationView.this.binding.followButton.getVisibility() != 0) {
                NavigationView.this.binding.followButton.setVisibility(0);
            }
            NavigationView.this.nextPreviousButtonPressed(false);
        }
    }

    /* loaded from: classes11.dex */
    public class o implements com.mappls.sdk.navigation.ui.navigation.settings.a {
        public o() {
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.settings.a
        public void a(int i) {
            if (NavigationView.this.audioManager != null) {
                NavigationView.this.audioManager.setStreamVolume(3, i, 4);
            }
        }

        @Override // com.mappls.sdk.navigation.ui.navigation.settings.a
        public void a(com.mappls.sdk.navigation.ui.utils.a aVar) {
            com.mappls.sdk.navigation.ui.map.b bVar;
            boolean z;
            NavigationView.this.binding.settingsView.hide();
            if (aVar == com.mappls.sdk.navigation.ui.utils.a.f13030a) {
                if (NavigationView.this.navigationMap != null) {
                    NavigationView.this.navigationMap.j();
                    return;
                }
                return;
            }
            if (aVar == com.mappls.sdk.navigation.ui.utils.a.b) {
                if (NavigationView.this.navigationMap == null) {
                    return;
                }
                bVar = NavigationView.this.navigationMap;
                z = false;
            } else if (aVar != com.mappls.sdk.navigation.ui.utils.a.c || NavigationView.this.navigationMap == null) {
                return;
            } else {
                bVar = NavigationView.this.navigationMap;
                z = true;
            }
            bVar.e(z);
        }
    }

    public NavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NavigationView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public NavigationView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.binding = LayoutNavigationViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.gpsHandler = new Handler();
        this.gpsRunnable = new g();
        NavigationOptions createFromAttributes = NavigationOptions.createFromAttributes(context, attributeSet);
        this.options = createFromAttributes;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        com.mappls.sdk.navigation.ui.theme.a.a(getContext(), createFromAttributes);
        initializeViewModel();
    }

    public NavigationView(Context context, NavigationOptions navigationOptions) {
        super(context);
        this.binding = LayoutNavigationViewBinding.inflate(LayoutInflater.from(getContext()), this, true);
        this.gpsHandler = new Handler();
        this.gpsRunnable = new g();
        this.options = navigationOptions;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        com.mappls.sdk.navigation.ui.theme.a.a(getContext(), navigationOptions);
        initializeViewModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drawPolyLine() {
        if (this.navigationMap != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(MapplsNavigationHelper.getInstance().getCurrentRoute());
            this.navigationMap.a(arrayList, 0);
            this.navigationMap.a(new f(this));
        }
    }

    private void finishNavigation() {
        NavigationFinishedView navigationFinishedView = this.binding.navigationFinishedView;
        if (navigationFinishedView != null) {
            navigationFinishedView.show();
        }
        this.binding.constraintLayout2.setVisibility(8);
        this.binding.infobarView.setVisibility(8);
        this.binding.searchAlongRouteView.setVisibility(8);
        this.binding.settingsView.hide();
        this.binding.directionList.hide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fixPreviewNavigationMarker() {
        try {
            com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
            if (bVar != null) {
                bVar.f(false);
                List<NavigationStep> routeDirections = this.app.getRouteDirections();
                NavLocation navLocation = routeDirections.get(selectedInstructionPosition()).getNavLocation();
                if (navLocation != null) {
                    LatLng latLng = new LatLng(navLocation.getLatitude(), navLocation.getLongitude());
                    com.mappls.sdk.navigation.ui.map.b bVar2 = this.navigationMap;
                    bVar2.a(latLng, bVar2.d(), 0.0d, 0.0d);
                    NavLocation navLocation2 = routeDirections.get(selectedInstructionPosition()).getNavLocation();
                    if (navLocation2 != null) {
                        this.navigationMap.a(getLocationAngle(navLocation2), new LatLng(navLocation2.getLatitude(), navLocation2.getLongitude()));
                    }
                }
            }
        } catch (Exception e2) {
            Timber.e(e2);
            e2.printStackTrace();
        }
    }

    private void initializeViewModel() {
        com.mappls.sdk.navigation.ui.navigation.a aVar = (com.mappls.sdk.navigation.ui.navigation.a) new ViewModelProvider((FragmentActivity) getContext()).get(com.mappls.sdk.navigation.ui.navigation.a.class);
        this.navigationViewModel = aVar;
        aVar.a(this.options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nextPreviousButtonPressed(boolean z) {
        com.mappls.sdk.navigation.ui.map.b bVar;
        com.mappls.sdk.navigation.ui.map.b bVar2 = this.navigationMap;
        if (bVar2 != null) {
            bVar2.f(false);
        }
        if (!z ? (bVar = this.navigationMap) != null : (bVar = this.navigationMap) != null) {
            bVar.c(true);
            this.navigationMap.d(false);
        }
        fixPreviewNavigationMarker();
    }

    private void onRestoreInstanceState(Bundle bundle) {
        com.mappls.sdk.navigation.ui.model.a aVar = (com.mappls.sdk.navigation.ui.model.a) bundle.getParcelable("mappls_navigation_view_state");
        if (aVar != null) {
            if (aVar.a()) {
                this.binding.directionList.show();
            } else {
                this.binding.directionList.hide();
            }
            this.binding.navigationFinishedView.hide();
            this.binding.nextAdviseView.setVisibility(aVar.b() ? 0 : 8);
            this.binding.followButton.setVisibility(aVar.c() ? 0 : 8);
            this.binding.tvSpeed.setVisibility(aVar.e() ? 0 : 8);
            if (aVar.d()) {
                this.binding.settingsView.show();
            } else {
                this.binding.settingsView.hide();
            }
        }
    }

    private void subscribe() {
        this.binding.infobarView.subscribe(this, this.navigationViewModel);
        this.binding.instructionContainer.subscribe(this, this.navigationViewModel);
        this.binding.instructionContainerPip.subscribe(this, this.navigationViewModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEvents(List<ReportDetails> list) {
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.a(list);
        }
    }

    public void addRoute(ELocation eLocation) {
        if (this.mapplsMap != null) {
            followMe(false);
            if (eLocation.latitude == null || eLocation.longitude == null) {
                this.mapplsMap.addMarker(new MarkerOptions().mapplsPin(eLocation.getMapplsPin()));
                this.mapplsMap.animateCamera(CameraMapplsPinUpdateFactory.newMapplsPinZoom(eLocation.getMapplsPin(), 12.0d));
            } else {
                this.mapplsMap.addMarker(new MarkerOptions().position(new LatLng(eLocation.latitude.doubleValue(), eLocation.longitude.doubleValue())));
                this.mapplsMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(eLocation.latitude.doubleValue(), eLocation.longitude.doubleValue()), 12.0d));
            }
        }
        this.binding.searchAlongRouteView.setRouteDetail(eLocation);
        this.binding.searchAlongRouteView.show();
    }

    public synchronized void followMe(boolean z) {
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null && z != bVar.e()) {
            if (MapplsNavigationHelper.getInstance().isNavigating()) {
                this.navigationMap.f(z);
            }
            if (!this.isInPIPMode && z) {
                this.navigationMap.k();
            }
        }
        if (z) {
            if (MapplsNavigationHelper.getInstance().isNavigating()) {
                this.binding.instructionContainer.setFollowMe();
                if (this.binding.followButton.getVisibility() == 0) {
                    this.binding.followButton.hide();
                }
            }
            com.mappls.sdk.navigation.ui.map.b bVar2 = this.navigationMap;
            if (bVar2 != null) {
                bVar2.l();
            }
            com.mappls.sdk.navigation.ui.map.b bVar3 = this.navigationMap;
            if (bVar3 != null) {
                bVar3.c(false);
                if (!this.isInPIPMode) {
                    this.navigationMap.d(true);
                }
            }
        } else if (this.binding.followButton.getVisibility() != 0) {
            this.binding.followButton.show();
        }
    }

    @Override // androidx.lifecycle.LifecycleOwner
    @NonNull
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    public float getLocationAngle(NavLocation navLocation) {
        try {
            List<NavLocation> path = this.app.getCalculatedRoute().getPath();
            return (float) MathUtils.wrap(navLocation.bearingTo(path.get(path.indexOf(navLocation) + 1)), 0.0d, 360.0d);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public Location getLocationForNavigation() {
        Location location = new Location("gps");
        NavLocation startLocation = MapplsNavigationViewHelper.getInstance().getStartLocation();
        if (startLocation != null) {
            location.setLatitude(startLocation.getLatitude());
            location.setLongitude(startLocation.getLongitude());
        }
        try {
            NavLocation firstLocation = MapplsNavigationHelper.getInstance().getFirstLocation();
            if (firstLocation.distanceTo(NavigationLocationProvider.convertLocation(location, this.app)) < 10.0f) {
                firstLocation.setBearing(firstLocation.bearingTo(MapplsNavigationHelper.getInstance().getSecondLocation()));
                return NavigationLocationProvider.revertLocation(firstLocation, this.app);
            }
            com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
            Location c2 = bVar != null ? bVar.c() : null;
            return c2 != null ? c2 : NavigationLocationProvider.revertLocation(this.app.getLocationProvider().getFirstTimeRunDefaultLocation(), this.app);
        } catch (Exception e2) {
            e2.printStackTrace();
            Timber.e(e2);
            return NavigationLocationProvider.revertLocation(this.app.getLocationProvider().getFirstTimeRunDefaultLocation(), this.app);
        }
    }

    public void hideWarning() {
        if (this.options.showWarningMessage().booleanValue()) {
            this.binding.warningTextView.setVisibility(4);
        }
    }

    public void init(NavigationApplication navigationApplication) {
        MapView mapView = this.mapView;
        if (mapView == null) {
            return;
        }
        this.app = navigationApplication;
        if (this.isMapInitialised) {
            com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
            if (bVar != null) {
                this.binding.infobarView.isTrafficEnable(bVar.f());
            }
        } else {
            mapView.getMapAsync(this);
        }
        AudioManager audioManager = (AudioManager) navigationApplication.getSystemService("audio");
        this.audioManager = audioManager;
        if (audioManager != null) {
            this.binding.settingsView.maxVolume(audioManager.getStreamMaxVolume(3));
            this.binding.settingsView.setVolume(this.audioManager.getStreamVolume(3));
        }
        onRouteProgress(MapplsNavigationHelper.getInstance().getAdviseInfo());
        this.binding.soundView.setSoundControll();
        this.binding.directionList.init(MapplsNavigationViewHelper.getInstance().getApplication());
        com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
        aVar.c.setValue(MapplsNavigationViewHelper.getInstance().getDestination());
        subscribe();
    }

    public void locationModeNavigation(boolean z) {
        MapplsMap mapplsMap;
        try {
            if (this.navigationMap != null && (mapplsMap = this.mapplsMap) != null && mapplsMap.getStyle() != null && this.mapplsMap.getStyle().isFullyLoaded()) {
                this.navigationMap.a(z);
                if (!z) {
                    NavigationApplication navigationApplication = this.app;
                    if (navigationApplication != null) {
                        navigationApplication.getLocationProvider().setLocationChangedListener(null);
                    }
                    this.mapplsMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().bearing(0.0d).tilt(0.0d).build()));
                    return;
                }
                NavigationApplication navigationApplication2 = this.app;
                if (navigationApplication2 != null) {
                    navigationApplication2.getLocationProvider().setLocationChangedListener(this);
                }
                Location locationForNavigation = getLocationForNavigation();
                com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
                if (bVar != null) {
                    bVar.a(locationForNavigation);
                }
                followMe(true);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Timber.e(e2);
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onAlternateRoutesUpdate(@Nullable List<AlternateRoute> list) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClickable(false);
        setFocusableInTouchMode(false);
        this.binding.tvSpeed.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSpeedBackground));
        this.binding.tvSpeed.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewSpeedTextColor));
        this.binding.searchAlongRoute.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSearchAlongRouteBackground));
        this.binding.searchAlongRoute.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSearchAlongRouteIcon));
        if (!this.options.showBottomInfoBar().booleanValue()) {
            this.binding.infobarView.setVisibility(8);
        }
        if (!this.options.showInstructionBanner().booleanValue()) {
            this.binding.instructionContainer.setVisibility(8);
            this.binding.instructionContainerPip.setVisibility(8);
        }
        if (!this.options.showWarningMessage().booleanValue()) {
            this.binding.warningTextView.setVisibility(8);
        }
        this.binding.soundView.setSoundControllerCallback(new h());
        MapplsNavigationHelper.getInstance().setOnSpeedLimitListener(new i());
        this.binding.infobarView.updateOptions(this.options);
        if (this.options.showSearchDuringNavigationOption().booleanValue()) {
            this.binding.searchAlongRoute.setVisibility(0);
        } else {
            this.binding.searchAlongRoute.setVisibility(8);
        }
        if (!this.options.showCurrentSpeed().booleanValue()) {
            this.binding.tvSpeed.setVisibility(8);
        }
        this.binding.navigationFinishedView.setCallback(new j());
        this.binding.infobarView.setOnInfobarCallback(new k());
        this.binding.searchAlongRoute.setOnClickListener(new l());
        this.binding.followButton.setOnClickListener(new m());
        this.binding.instructionContainer.setNextPreviousIconClickListener(new n());
        this.binding.settingsView.setSettingsCallback(new o());
        this.binding.searchAlongRouteView.setSearchRouteCallback(new a());
        this.binding.directionList.setOnDirectionClick(new b());
    }

    public boolean onBackPressed(boolean z) {
        if (this.binding.directionList.onBackPress()) {
            return false;
        }
        if (this.binding.searchAlongRouteView.isVisible()) {
            this.binding.searchAlongRouteView.hide();
            MapplsMap mapplsMap = this.mapplsMap;
            if (mapplsMap != null) {
                mapplsMap.clear();
            }
            return true;
        } else if (this.binding.settingsView.onBackPress()) {
            return true;
        } else {
            if (z) {
                MapplsNavigationHelper.getInstance().stopNavigation();
            }
            return false;
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onBetterRouteAvailable(List<DirectionsRoute> list) {
    }

    public void onConfigurationChange(Configuration configuration) {
        followMe(true);
        this.isConfigurationChange = true;
    }

    public void onCreate(Bundle bundle) {
        if (this.options.isUsingInternalMap().booleanValue()) {
            MapView mapView = new MapView(getContext(), new MapplsMapOptions().textureMode(true));
            this.mapView = mapView;
            this.binding.mapLayout.addView(mapView);
            this.mapView.onCreate(bundle);
        } else {
            this.binding.mapLayout.setVisibility(8);
        }
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        MapplsNavigationHelper.getInstance().addNavigationListener(this);
        MapplsNavigationHelper.getInstance().setNavigationEventLoadedListener(new c());
        MapplsNavigationHelper.getInstance().setNavigationEventListener(new d());
        if (bundle != null) {
            onRestoreInstanceState(bundle);
        }
        if (MapplsNavigationViewHelper.getInstance().getApplication() != null) {
            init(MapplsNavigationViewHelper.getInstance().getApplication());
        } else {
            Timber.e("Please initialise Navigation View in Your Application Class using MapplsNavigationViewHelper", new Object[0]);
        }
    }

    public void onDestroy() {
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap != null) {
            mapplsMap.clear();
        }
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.a();
            this.navigationMap.b();
        }
        MapplsMap mapplsMap2 = this.mapplsMap;
        if (mapplsMap2 != null) {
            mapplsMap2.removeOnMoveListener(this);
            this.mapplsMap.removeAnnotations();
        }
        locationModeNavigation(false);
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onDestroy();
        }
        this.binding.infobarView.unsubscribe();
        this.binding.instructionContainer.unsubscribe();
        this.binding.instructionContainerPip.unsubscribe();
        MapplsNavigationHelper.getInstance().removeNavigationListener(this);
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onETARefreshed(String str) {
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onEvent(@Nullable NavEvent navEvent) {
    }

    @Override // com.mappls.sdk.navigation.iface.LocationChangedListener
    public void onGPSConnectionChanged(boolean z) {
        if (z) {
            hideWarning();
        } else {
            showWarning("GPS connection Lost", getResources().getColor(R.color.red));
        }
    }

    @Override // com.mappls.sdk.navigation.iface.LocationChangedListener
    public void onLocationChanged(Location location) {
        hideWarning();
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.a(location);
        }
    }

    public void onLowMemory() {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onLowMemory();
        }
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapError(int i2, String str) {
    }

    @Override // com.mappls.sdk.maps.OnMapReadyCallback
    public void onMapReady(MapplsMap mapplsMap) {
        this.mapplsMap = mapplsMap;
        mapplsMap.clear();
        com.mappls.sdk.navigation.ui.theme.a.a(getContext(), this.mapView);
        mapplsMap.addOnMoveListener(this);
        getContext();
        mapplsMap.setMapplsStyle(com.mappls.sdk.navigation.ui.theme.a.a() ? this.options.mapplsMapDarkStyle() : this.options.mapplsMapLightStyle(), new e(mapplsMap));
        NavigationViewCallback navigationViewCallback = this.navigationViewCallback;
        if (navigationViewCallback != null) {
            navigationViewCallback.onNavigationMapReady(mapplsMap);
        }
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
    public void onMove(@NonNull MoveGestureDetector moveGestureDetector) {
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
    public void onMoveBegin(@NonNull MoveGestureDetector moveGestureDetector) {
    }

    @Override // com.mappls.sdk.maps.MapplsMap.OnMoveListener
    public void onMoveEnd(@NonNull MoveGestureDetector moveGestureDetector) {
        this.binding.infobarView.showRouteOverview(true);
        followMe(false);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationCancelled() {
        this.gpsHandler.removeCallbacksAndMessages(null);
        hideWarning();
        locationModeNavigation(false);
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onNavigationCancelled();
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationFinished() {
        this.gpsHandler.removeCallbacksAndMessages(null);
        locationModeNavigation(false);
        hideWarning();
        finishNavigation();
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onNavigationFinished();
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNavigationStarted() {
        this.binding.navigationFinishedView.hide();
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onNavigationStarted();
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onNewRoute(String str) {
        List<RouteLeg> legs;
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onNewRoute(str);
        }
        NavigationApplication navigationApplication = this.app;
        if (navigationApplication != null) {
            this.binding.instructionContainer.setDataContainer("End Stop", navigationApplication, true);
            this.binding.instructionContainerPip.setDataContainer(this.app);
            this.binding.directionList.onNewRoute(this.app);
            MapplsMap mapplsMap = this.mapplsMap;
            if (mapplsMap != null) {
                mapplsMap.removeAnnotations();
            }
            Location locationForNavigation = getLocationForNavigation();
            NavLocation navLocation = new NavLocation("Router");
            if (locationForNavigation != null) {
                navLocation.setLatitude(locationForNavigation.getLatitude());
                navLocation.setLongitude(locationForNavigation.getLongitude());
            }
            DirectionsRoute currentRoute = MapplsNavigationHelper.getInstance().getCurrentRoute();
            com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
            AdviseInfo adviseInfo = MapplsNavigationHelper.getInstance().getAdviseInfo();
            int nodeIndex = MapplsNavigationHelper.getInstance().getNodeIndex();
            NavigationApplication application = MapplsNavigationViewHelper.getInstance().getApplication();
            aVar.getClass();
            if (application != null && currentRoute != null && (legs = currentRoute.legs()) != null && legs.size() > 0 && legs.get(0).annotation() != null) {
                aVar.f13009a.setValue(new com.mappls.sdk.navigation.ui.navigation.infobar.d(adviseInfo, legs.get(0).annotation().congestion(), currentRoute.distance() != null ? currentRoute.distance().doubleValue() : 0.0d, nodeIndex, application));
            }
            drawPolyLine();
        }
    }

    public void onPause() {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onPause();
        }
    }

    public void onPictureInPictureModeChange(boolean z) {
        this.isInPIPMode = z;
        if (z) {
            this.binding.infobarView.setVisibility(8);
            this.binding.soundView.setVisibility(8);
            this.binding.nextAdviseView.setVisibility(8);
            this.binding.searchAlongRoute.setVisibility(8);
            this.binding.instructionContainer.setVisibility(8);
            if (this.options.showInstructionBanner().booleanValue()) {
                this.binding.instructionContainerPip.setVisibility(0);
            }
            this.binding.alertView.updateRouteEvent(null);
            hideWarning();
            this.binding.tvSpeed.setVisibility(8);
            com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
            if (bVar != null) {
                bVar.p();
            }
        } else {
            if (this.options.showBottomInfoBar().booleanValue()) {
                this.binding.infobarView.setVisibility(0);
            }
            this.binding.soundView.setVisibility(0);
            this.binding.nextAdviseView.setVisibility(0);
            if (this.options.showSearchDuringNavigationOption().booleanValue()) {
                this.binding.searchAlongRoute.setVisibility(0);
            } else {
                this.binding.searchAlongRoute.setVisibility(8);
            }
            if (this.options.showInstructionBanner().booleanValue()) {
                this.binding.instructionContainer.setVisibility(0);
            }
            this.binding.instructionContainerPip.setVisibility(8);
            com.mappls.sdk.navigation.ui.map.b bVar2 = this.navigationMap;
            if (bVar2 != null) {
                bVar2.o();
                this.navigationMap.d(true);
            }
        }
        AdviseInfo adviseInfo = MapplsNavigationHelper.getInstance().getAdviseInfo();
        if (this.mapplsMap != null) {
            Location location = adviseInfo.getLocation();
            this.mapplsMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(location.getLatitude(), location.getLongitude())).build()));
        }
        followMe(true);
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onReRoutingRequested() {
    }

    public void onResume() {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onResume();
        }
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
        if (!MapplsNavigationHelper.getInstance().isNavigating()) {
            MapplsNavigationHelper.getInstance().stopNavigation();
            NavigationApplication navigationApplication = this.app;
            if (navigationApplication != null) {
                navigationApplication.getNotificationHelper().removeNotifications();
            }
        }
        NavigationApplication navigationApplication2 = this.app;
        if (navigationApplication2 != null) {
            navigationApplication2.getNotificationHelper().refreshNotifications();
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onRouteProgress(@NonNull AdviseInfo adviseInfo) {
        LegStep legStep;
        LegAnnotation annotation;
        hideWarning();
        if (adviseInfo == null) {
            Timber.e("No Route Information found", new Object[0]);
            return;
        }
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onRouteProgress(adviseInfo);
        }
        if (this.app == null) {
            return;
        }
        com.mappls.sdk.navigation.ui.navigation.a aVar = this.navigationViewModel;
        DirectionsRoute currentRoute = MapplsNavigationHelper.getInstance().getCurrentRoute();
        int nodeIndex = MapplsNavigationHelper.getInstance().getNodeIndex();
        NavigationApplication navigationApplication = this.app;
        aVar.getClass();
        if (navigationApplication != null) {
            aVar.b.setValue(new com.mappls.sdk.navigation.ui.navigation.instructioncontainer.a(adviseInfo));
            if (!adviseInfo.isRouteBeingRecalculated() || adviseInfo.isOnRoute()) {
                List<String> congestion = (currentRoute == null || currentRoute.legs() == null || currentRoute.legs().size() <= 0 || (annotation = currentRoute.legs().get(0).annotation()) == null) ? null : annotation.congestion();
                double d2 = 0.0d;
                if (currentRoute != null && currentRoute.distance() != null) {
                    d2 = currentRoute.distance().doubleValue();
                }
                aVar.f13009a.setValue(new com.mappls.sdk.navigation.ui.navigation.infobar.d(adviseInfo, congestion, d2, nodeIndex, navigationApplication));
            }
        }
        if (adviseInfo.isRouteBeingRecalculated() && !adviseInfo.isOnRoute()) {
            this.binding.alertView.updateRouteEvent(null);
            this.binding.tvSpeed.setVisibility(8);
            return;
        }
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null && bVar.e()) {
            this.binding.instructionContainer.setCurrentItem(adviseInfo.getPosition() == 0 ? 1 : adviseInfo.getPosition());
        }
        if (adviseInfo.getDistanceToNextAdvise() > 100) {
            if (this.isInPIPMode || !this.options.showCurrentSpeed().booleanValue()) {
                this.binding.tvSpeed.setVisibility(8);
            } else {
                this.binding.tvSpeed.setVisibility(0);
            }
            if (adviseInfo.getLocation() != null) {
                Location location = adviseInfo.getLocation();
                TextView textView = this.binding.tvSpeed;
                textView.setText((((int) (location.getSpeed() * 18.0f)) / 5) + "\nkm/h");
            } else {
                this.binding.tvSpeed.setText("0\nkm/h");
            }
        } else {
            this.binding.tvSpeed.setVisibility(8);
        }
        if (!this.isInPIPMode && this.options.showNextInstructionBanner().booleanValue()) {
            this.binding.nextAdviseView.setVisibility(0);
        }
        List<NavigationStep> routeDirections = this.app.getRouteDirections();
        if (adviseInfo.getPosition() != this.binding.instructionContainer.getSelectedItem() || routeDirections.size() - 1 <= adviseInfo.getPosition() || (legStep = (LegStep) routeDirections.get(adviseInfo.getPosition() + 1).getExtraInfo()) == null || !this.options.showNextInstructionBanner().booleanValue()) {
            this.binding.nextAdviseView.setVisibility(8);
        } else {
            this.binding.nextAdviseView.setDurationText(NavigationFormatter.getFormattedDistance((float) ((LegStep) adviseInfo.getInfo()).distance(), this.app));
            this.binding.nextAdviseView.setNextManeuverTypeAndModifier(legStep.maneuver().type(), legStep.maneuver().modifier());
            if (legStep.maneuver().type() != null && (legStep.maneuver().type().equalsIgnoreCase("roundabout") || legStep.maneuver().type().equalsIgnoreCase("rotary"))) {
                if (legStep.maneuver().degree() != null) {
                    this.binding.nextAdviseView.setRoundaboutAngle(legStep.maneuver().degree().floatValue());
                } else if (routeDirections.size() > adviseInfo.getPosition() + 2) {
                    this.binding.nextAdviseView.setRoundaboutAngle(com.mappls.sdk.navigation.ui.utils.d.a(legStep, (LegStep) routeDirections.get(adviseInfo.getPosition() + 2).getExtraInfo()));
                }
            }
        }
        if (adviseInfo.isOnRoute() && !this.isInPIPMode && this.options.showNextInstructionBanner().booleanValue()) {
            this.binding.nextAdviseView.setVisibility(0);
        }
        this.binding.instructionContainer.setDataContainer("End Stop", MapplsNavigationViewHelper.getInstance().getApplication(), false);
        this.binding.instructionContainerPip.setDataContainer(MapplsNavigationViewHelper.getInstance().getApplication());
        com.mappls.sdk.navigation.ui.map.b bVar2 = this.navigationMap;
        if (bVar2 != null) {
            Location location2 = adviseInfo.getLocation();
            MapplsNavigationHelper.getInstance().getNavigationSteps();
            bVar2.a(location2, adviseInfo);
        }
    }

    @Override // com.mappls.sdk.navigation.iface.LocationChangedListener
    public void onSatelliteInfoChanged(GPSInfo gPSInfo) {
        if (!MapplsNavigationHelper.getInstance().isNavigating()) {
            hideWarning();
            return;
        }
        this.gpsInfo = gPSInfo;
        this.gpsHandler.postDelayed(this.gpsRunnable, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS);
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onSaveInstanceState(bundle);
        }
        bundle.putParcelable("mappls_navigation_view_state", new com.mappls.sdk.navigation.ui.model.a(this.binding.nextAdviseView.getVisibility() == 0, this.binding.followButton.getVisibility() == 0, this.binding.tvSpeed.getVisibility() == 0, this.binding.settingsView.isVisible(), this.binding.directionList.isVisible()));
    }

    public void onStart() {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onStart();
        }
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.h();
        }
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
    }

    public void onStop() {
        if (this.options.isUsingInternalMap().booleanValue()) {
            this.mapView.onStop();
        }
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.i();
        }
        NavigationApplication navigationApplication = this.app;
        if (navigationApplication != null) {
            navigationApplication.getNotificationHelper().removeNotifications();
        }
    }

    @Override // com.mappls.sdk.navigation.iface.INavigationListener
    public void onWayPointReached(WayPoint wayPoint) {
        NavigationCallback navigationCallback = this.navigationCallback;
        if (navigationCallback != null) {
            navigationCallback.onWayPointReached(wayPoint);
        }
    }

    public int selectedInstructionPosition() {
        return this.binding.instructionContainer.getSelectedItem();
    }

    public void setMapView(MapView mapView) {
        this.mapView = mapView;
    }

    public void setNavigationViewCallback(@NonNull NavigationViewCallback navigationViewCallback) {
        this.navigationViewCallback = navigationViewCallback;
        MapplsMap mapplsMap = this.mapplsMap;
        if (mapplsMap != null) {
            navigationViewCallback.onNavigationMapReady(mapplsMap);
        }
    }

    public void setOnNavigationCallback(NavigationCallback navigationCallback) {
        this.navigationCallback = navigationCallback;
    }

    public void showWarning(String str, int i2) {
        if (this.options.showWarningMessage().booleanValue()) {
            this.binding.warningTextView.setVisibility(0);
            this.binding.warningTextView.setText(str);
            this.binding.warningTextView.setBackgroundColor(i2);
        }
    }

    public void toggleTheme() {
        Context context = getContext();
        NavigationOptions a2 = this.navigationViewModel.a();
        context.setTheme((com.mappls.sdk.navigation.ui.theme.a.a() ? a2.navigationDarkTheme() : a2.navigationLightTheme()).intValue());
        com.mappls.sdk.navigation.ui.theme.a.a(getContext(), this.mapView);
        this.binding.tvSpeed.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSpeedBackground));
        this.binding.tvSpeed.setTextColor(com.mappls.sdk.navigation.ui.theme.a.b(getContext(), R.attr.navigationViewSpeedTextColor));
        this.binding.searchAlongRoute.setBackground(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSearchAlongRouteBackground));
        this.binding.searchAlongRoute.setImageDrawable(com.mappls.sdk.navigation.ui.theme.a.d(getContext(), R.attr.navigationViewSearchAlongRouteIcon));
        this.binding.infobarView.toogleTheme();
        this.binding.directionList.toggleTheme();
        this.binding.soundView.toggleTheme();
        this.binding.nextAdviseView.toggleTheme();
        this.binding.alertView.toggleTheme();
        this.binding.settingsView.toggleTheme();
        this.binding.followButton.toggleTheme();
        MapplsNavigationHelper mapplsNavigationHelper = MapplsNavigationHelper.getInstance();
        getContext();
        mapplsNavigationHelper.setJunctionViewMode(com.mappls.sdk.navigation.ui.theme.a.a() ? "night" : WeatherCriteria.UNIT_TYPE_DAY);
        com.mappls.sdk.navigation.ui.map.b bVar = this.navigationMap;
        if (bVar != null) {
            bVar.n();
        }
    }
}
