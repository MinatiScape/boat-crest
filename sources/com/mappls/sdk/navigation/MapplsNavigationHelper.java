package com.mappls.sdk.navigation;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.location.Location;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.Session;
import androidx.car.app.notification.CarAppExtender;
import androidx.car.app.notification.CarPendingIntent;
import androidx.core.app.NotificationCompat;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.iface.INavigationListener;
import com.mappls.sdk.navigation.iface.INavigationLoggingListener;
import com.mappls.sdk.navigation.iface.ISaveTrackListener;
import com.mappls.sdk.navigation.iface.IStopSession;
import com.mappls.sdk.navigation.iface.ITrackRecordingListener;
import com.mappls.sdk.navigation.iface.JunctionInfoChangedListener;
import com.mappls.sdk.navigation.iface.JunctionViewsLoadedListener;
import com.mappls.sdk.navigation.iface.LocationChangedListener;
import com.mappls.sdk.navigation.iface.NavigationEventListener;
import com.mappls.sdk.navigation.iface.NavigationEventLoadedListener;
import com.mappls.sdk.navigation.iface.OnAuthentication;
import com.mappls.sdk.navigation.iface.OnSpeedLimitListener;
import com.mappls.sdk.navigation.iface.POIAlongTheRouteChangedListener;
import com.mappls.sdk.navigation.iface.TollEntryExitListener;
import com.mappls.sdk.navigation.iface.VoiceCommandListener;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.navigation.model.NavigationResponse;
import com.mappls.sdk.navigation.model.NavigationSummary;
import com.mappls.sdk.navigation.routing.IRecalculatedDirection;
import com.mappls.sdk.navigation.util.ManeuverInfo;
import com.mappls.sdk.navigation.util.MapplsNavigationMode;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.event.route.model.ReportDetails;
import com.mappls.sdk.services.api.event.route.model.RouteReportSummaryResponse;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class MapplsNavigationHelper extends f {
    public static MapplsNavigationHelper G = new MapplsNavigationHelper();
    public Session F;

    public static MapplsNavigationHelper getInstance() {
        return G;
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        super.addJunctionInfoChangedListener(junctionInfoChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addLocationChangeListener(LocationChangedListener locationChangedListener) {
        super.addLocationChangeListener(locationChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addNavigationEventListener(NavigationEventListener navigationEventListener) {
        super.addNavigationEventListener(navigationEventListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public void addNavigationListener(INavigationListener iNavigationListener) {
        super.addNavigationListener(iNavigationListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public void addWayPoint(LatLng latLng, int i, String str) {
        super.addWayPoint(latLng, i, str);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addWayPoint(LatLng latLng, int i, String str, String str2) {
        super.addWayPoint(latLng, i, str, str2);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addWayPoint(String str, int i, String str2) {
        super.addWayPoint(str, i, str2);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void addWayPoint(String str, int i, String str2, String str3) {
        super.addWayPoint(str, i, str2, str3);
    }

    @Override // com.mappls.sdk.navigation.f
    public void addWayPoint(List<WayPoint> list, int i) {
        super.addWayPoint(list, i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void announceCurrentDirection() {
        super.announceCurrentDirection();
    }

    public void deleteSession(@NonNull String str, @Nullable IStopSession iStopSession) {
        this.b.r.a(str, iStopSession);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void enableFasterRouteAvailable(boolean z) {
        super.enableFasterRouteAvailable(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean enableFasterRouteAvailable() {
        return super.enableFasterRouteAvailable();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void enableNearbyPoiAlongRoute(boolean z) {
        super.enableNearbyPoiAlongRoute(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public void extendNotification(NotificationCompat.Builder builder) {
        if (this.F != null) {
            Intent data = new Intent("android.intent.action.VIEW").setComponent(new ComponentName(this.b, getInstance().getNavigationCarAppServiceClass())).setData(NavigationConstants.createDeepLinkUri("com.mappls.app.navigation.car.OpenRootScreen"));
            builder.extend(new CarAppExtender.Builder().setContentIntent(CarPendingIntent.getCarApp(this.b, data.hashCode(), data, 0)).build());
        }
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ AdviseInfo getAdviseInfo() {
        return super.getAdviseInfo();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getAllRouteLocations() {
        return super.getAllRouteLocations();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getAlternateRoutes() {
        return super.getAlternateRoutes();
    }

    @Override // com.mappls.sdk.navigation.f
    public List<String> getAvoidanceSettings() {
        return super.getAvoidanceSettings();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getBaseRes() {
        return super.getBaseRes();
    }

    public Session getCarSession() {
        return this.F;
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ Long getCurrentNodeId() {
        return super.getCurrentNodeId();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ DirectionsRoute getCurrentRoute() {
        return super.getCurrentRoute();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ com.mappls.sdk.navigation.routing.b getDefaultParams() {
        return super.getDefaultParams();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getDeviceId() {
        return super.getDeviceId();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ DirectionsResponse getDirectionsResponse() {
        return super.getDirectionsResponse();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getEvents() {
        return super.getEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ NavLocation getFirstLocation() {
        return super.getFirstLocation();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getJunctionViewImageSize() {
        return super.getJunctionViewImageSize();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getJunctionViewMode() {
        return super.getJunctionViewMode();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getJunctionViewURL(String str, boolean z) {
        return super.getJunctionViewURL(str, z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getJunctionViews() {
        return super.getJunctionViews();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ JunctionViewsLoadedListener getJunctionViewsLoadedListener() {
        return super.getJunctionViewsLoadedListener();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getLeftDistance() {
        return super.getLeftDistance();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getLeftTime() {
        return super.getLeftTime();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ ManeuverInfo getManeuverInfo(NavigationApplication navigationApplication, int i) {
        return super.getManeuverInfo(navigationApplication, i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ Class getNavigationActivityClass() {
        return super.getNavigationActivityClass();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ Class getNavigationCarAppServiceClass() {
        return super.getNavigationCarAppServiceClass();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ NavigationEventLoadedListener getNavigationEventLoadedListener() {
        return super.getNavigationEventLoadedListener();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ MapplsNavigationMode getNavigationMode() {
        return super.getNavigationMode();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getNavigationSteps() {
        return super.getNavigationSteps();
    }

    @Override // com.mappls.sdk.navigation.f
    public NavigationSummary getNavigationSummary() {
        return super.getNavigationSummary();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getNodeIndex() {
        return super.getNodeIndex();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getOffRouteThreshold() {
        return super.getOffRouteThreshold();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ OnSpeedLimitListener getOnSpeedLimitListener() {
        return super.getOnSpeedLimitListener();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getRouteIndex() {
        return super.getRouteIndex();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getRouteLocations() {
        return super.getRouteLocations();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ RouteReportSummaryResponse getRouteReportSummaryResponse() {
        return super.getRouteReportSummaryResponse();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ IRecalculatedDirection getRouteService() {
        return super.getRouteService();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ ArrayList getSavedTracks() {
        return super.getSavedTracks();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ NavLocation getSecondLocation() {
        return super.getSecondLocation();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ DirectionsRoute getSelectedTrip(DirectionsResponse directionsResponse, int i) {
        return super.getSelectedTrip(directionsResponse, i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getSessionId() {
        return super.getSessionId();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ TollEntryExitListener getTollEntryExitListener() {
        return super.getTollEntryExitListener();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ String getUuid() {
        return super.getUuid();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ VoiceCommandListener getVoiceCommandListener() {
        return super.getVoiceCommandListener();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ List getWayPoints() {
        return super.getWayPoints();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ int getWholeDistance() {
        return super.getWholeDistance();
    }

    @Override // com.mappls.sdk.navigation.f
    public void init(NavigationApplication navigationApplication) {
        super.init(navigationApplication);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isCallAlternativeDuringNavigation() {
        return super.isCallAlternativeDuringNavigation();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isCloseServiceOnRemovingTask() {
        return super.isCloseServiceOnRemovingTask();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isCurrentMetricSystemKm() {
        return super.isCurrentMetricSystemKm();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isEnableInstructionsFromAPI() {
        return super.isEnableInstructionsFromAPI();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isEnableNearbyPoiAlongRoute() {
        return super.isEnableNearbyPoiAlongRoute();
    }

    @Override // com.mappls.sdk.navigation.f
    public boolean isFollowingMode() {
        return super.isFollowingMode();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isGPSCheckEnableForLocationChange() {
        return super.isGPSCheckEnableForLocationChange();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isInterruptMusicForNavigationInstructions() {
        return super.isInterruptMusicForNavigationInstructions();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isJunctionViewEnabled() {
        return super.isJunctionViewEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isLoggingEnabled() {
        return super.isLoggingEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isMute() {
        return super.isMute();
    }

    @Override // com.mappls.sdk.navigation.f
    public boolean isNavigating() {
        return super.isNavigating();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isNavigationEventAudioPromptEnabled() {
        return super.isNavigationEventAudioPromptEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isNavigationEventEnabled() {
        return super.isNavigationEventEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isNightMode() {
        return super.isNightMode();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isPlayAsVoiceCall() {
        return super.isPlayAsVoiceCall();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isPlayDuringPhoneCallEnabled() {
        return super.isPlayDuringPhoneCallEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isRecording() {
        return super.isRecording();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isSetFasterRouteImmidiately() {
        return super.isSetFasterRouteImmidiately();
    }

    @Override // com.mappls.sdk.navigation.f
    public boolean isShortestRoute() {
        return super.isShortestRoute();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isShouldPlayNavigationInstructions() {
        return super.isShouldPlayNavigationInstructions();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean isTrafficProbeEnabled() {
        return super.isTrafficProbeEnabled();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void navigateTo(LatLng latLng, String str) {
        super.navigateTo(latLng, str);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void navigateTo(LatLng latLng, String str, String str2) {
        super.navigateTo(latLng, str, str2);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void navigateTo(String str, String str2) {
        super.navigateTo(str, str2);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void navigateTo(String str, String str2, String str3) {
        super.navigateTo(str, str2, str3);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void recalculateRoute() {
        super.recalculateRoute();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void removeJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        super.removeJunctionInfoChangedListener(junctionInfoChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void removeLocationChangeListener(LocationChangedListener locationChangedListener) {
        super.removeLocationChangeListener(locationChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void removeNavigationEventListener(NavigationEventListener navigationEventListener) {
        super.removeNavigationEventListener(navigationEventListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public void removeNavigationListener(INavigationListener iNavigationListener) {
        super.removeNavigationListener(iNavigationListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void removeWayPoint(int i) {
        super.removeWayPoint(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public void replaceRoute(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list) {
        super.replaceRoute(directionsResponse, i, latLng, wayPoint, list);
    }

    @Override // com.mappls.sdk.navigation.f
    public void replaceWayPoints(List<WayPoint> list) {
        super.replaceWayPoints(list);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void saveCurrentTrack() {
        super.saveCurrentTrack();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void saveCurrentTrack(String str, ISaveTrackListener iSaveTrackListener) {
        super.saveCurrentTrack(str, iSaveTrackListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setAlternateRoutes(List list) {
        super.setAlternateRoutes(list);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setAvailableBetterRoute(List list) {
        super.setAvailableBetterRoute(list);
    }

    @Override // com.mappls.sdk.navigation.f
    public void setAvoidanceSetting(List<String> list) {
        super.setAvoidanceSetting(list);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setCallAlternativeDuringNavigation(boolean z) {
        super.setCallAlternativeDuringNavigation(z);
    }

    public void setCarSession(Session session) {
        this.F = session;
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setCloseServiceOnRemovingTask(boolean z) {
        super.setCloseServiceOnRemovingTask(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setCurrentLocation(Location location) {
        super.setCurrentLocation(location);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setCurrentLocation(NavLocation navLocation) {
        super.setCurrentLocation(navLocation);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setDirectionsResponse(DirectionsResponse directionsResponse) {
        super.setDirectionsResponse(directionsResponse);
    }

    @Override // com.mappls.sdk.navigation.f
    public void setEnableInstructionsFromAPI(boolean z) {
        super.setEnableInstructionsFromAPI(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setEventAudioPromptBefore(int i) {
        super.setEventAudioPromptBefore(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setEventVisualPromptBefore(int i) {
        super.setEventVisualPromptBefore(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setEvents(List list) {
        super.setEvents(list);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setFasterRouteImmidiately(boolean z) {
        super.setFasterRouteImmidiately(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setGPSCheckEnableForLocationChange(boolean z) {
        super.setGPSCheckEnableForLocationChange(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setInterruptMusicForNavigationInstructions(boolean z) {
        super.setInterruptMusicForNavigationInstructions(z);
    }

    @Override // com.mappls.sdk.navigation.f
    @Deprecated
    public /* bridge */ /* synthetic */ void setJunctionInfoChangedListener(JunctionInfoChangedListener junctionInfoChangedListener) {
        super.setJunctionInfoChangedListener(junctionInfoChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setJunctionViewEnabled(boolean z) {
        super.setJunctionViewEnabled(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setJunctionViewImageSize(int i, int i2) {
        super.setJunctionViewImageSize(i, i2);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setJunctionViewMode(String str) {
        super.setJunctionViewMode(str);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setJunctionViewsLoadedListener(JunctionViewsLoadedListener junctionViewsLoadedListener) {
        super.setJunctionViewsLoadedListener(junctionViewsLoadedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setJunctionVisualPromptBefore(int i) {
        super.setJunctionVisualPromptBefore(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setLoggingEnable(boolean z) {
        super.setLoggingEnable(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setMetricSystemToKM(boolean z) {
        super.setMetricSystemToKM(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setMute(boolean z) {
        super.setMute(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationActivityClass(Class cls) {
        super.setNavigationActivityClass(cls);
    }

    @Override // com.mappls.sdk.navigation.f
    public void setNavigationCarAppServiceClass(Class<Service> cls) {
        super.setNavigationCarAppServiceClass(cls);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationEventAudioPromptEnabled(boolean z) {
        super.setNavigationEventAudioPromptEnabled(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationEventEnabled(boolean z) {
        super.setNavigationEventEnabled(z);
    }

    @Override // com.mappls.sdk.navigation.f
    @Deprecated
    public /* bridge */ /* synthetic */ void setNavigationEventListener(NavigationEventListener navigationEventListener) {
        super.setNavigationEventListener(navigationEventListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationEventLoadedListener(NavigationEventLoadedListener navigationEventLoadedListener) {
        super.setNavigationEventLoadedListener(navigationEventLoadedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationLoggingListener(INavigationLoggingListener iNavigationLoggingListener) {
        super.setNavigationLoggingListener(iNavigationLoggingListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setNavigationMode(MapplsNavigationMode mapplsNavigationMode) {
        super.setNavigationMode(mapplsNavigationMode);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setOffRouteThreshold(int i) {
        super.setOffRouteThreshold(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setOnSpeedLimitListener(OnSpeedLimitListener onSpeedLimitListener) {
        super.setOnSpeedLimitListener(onSpeedLimitListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setPlaceOnRouteListener(POIAlongTheRouteChangedListener pOIAlongTheRouteChangedListener) {
        super.setPlaceOnRouteListener(pOIAlongTheRouteChangedListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setPlayAsVoiceCall(boolean z) {
        super.setPlayAsVoiceCall(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setPlayDuringPhoneCallEnabled(boolean z) {
        super.setPlayDuringPhoneCallEnabled(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setRouteIndex(int i) {
        super.setRouteIndex(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setRouteReportSummaryResponse(RouteReportSummaryResponse routeReportSummaryResponse) {
        super.setRouteReportSummaryResponse(routeReportSummaryResponse);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setRouteService(IRecalculatedDirection iRecalculatedDirection) {
        super.setRouteService(iRecalculatedDirection);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setRouteSettingOptions(v vVar) {
        super.setRouteSettingOptions(vVar);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setRoutingHelper(com.mappls.sdk.navigation.routing.d dVar) {
        super.setRoutingHelper(dVar);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setSelectedIndex(int i) {
        super.setSelectedIndex(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setSessionId(String str) {
        super.setSessionId(str);
    }

    @Override // com.mappls.sdk.navigation.f
    public void setShortestRoute(boolean z) {
        super.setShortestRoute(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setShouldPlayNavigationInstructions(boolean z) {
        super.setShouldPlayNavigationInstructions(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setTollEntryExitListener(TollEntryExitListener tollEntryExitListener) {
        super.setTollEntryExitListener(tollEntryExitListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setTrackRecordingListener(ITrackRecordingListener iTrackRecordingListener) {
        super.setTrackRecordingListener(iTrackRecordingListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setTrafficProbeEnabled(boolean z) {
        super.setTrafficProbeEnabled(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setUuid(String str) {
        super.setUuid(str);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void setVoiceCommandListener(VoiceCommandListener voiceCommandListener) {
        super.setVoiceCommandListener(voiceCommandListener);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void showRoadConditionsEvents(boolean z) {
        super.showRoadConditionsEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean showRoadConditionsEvents() {
        return super.showRoadConditionsEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void showSafetyEvents(boolean z) {
        super.showSafetyEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean showSafetyEvents() {
        return super.showSafetyEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void showTrafficEvents(boolean z) {
        super.showTrafficEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean showTrafficEvents() {
        return super.showTrafficEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void speakNavigationPrompt(boolean z) {
        super.speakNavigationPrompt(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean speakNavigationPrompt() {
        return super.speakNavigationPrompt();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void speakRoadConditionsEvents(boolean z) {
        super.speakRoadConditionsEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean speakRoadConditionsEvents() {
        return super.speakRoadConditionsEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void speakSafetyEvents(boolean z) {
        super.speakSafetyEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean speakSafetyEvents() {
        return super.speakSafetyEvents();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void speakTrafficEvents(boolean z) {
        super.speakTrafficEvents(z);
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ boolean speakTrafficEvents() {
        return super.speakTrafficEvents();
    }

    public NavigationResponse startNavigation(LatLng latLng, WayPoint wayPoint, List<WayPoint> list) {
        return super.startNavigation(latLng, wayPoint, list, MapplsAccountManager.getInstance().getClusterId(), (List<ReportDetails>) null);
    }

    public NavigationResponse startNavigation(LatLng latLng, WayPoint wayPoint, List<WayPoint> list, String str) {
        return super.startNavigation(latLng, wayPoint, list, str, (List<ReportDetails>) null);
    }

    public NavigationResponse startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list) {
        return super.startNavigation(directionsResponse, i, latLng, wayPoint, list, MapplsAccountManager.getInstance().getClusterId(), (List<ReportDetails>) null);
    }

    public NavigationResponse startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, String str) {
        return super.startNavigation(directionsResponse, i, latLng, wayPoint, list, str, (List<ReportDetails>) null);
    }

    public NavigationResponse startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, boolean z) {
        return super.startNavigation(directionsResponse, i, latLng, wayPoint, list, MapplsAccountManager.getInstance().getClusterId(), z, (List<ReportDetails>) null);
    }

    public void startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, OnAuthentication onAuthentication) {
        super.startNavigation(directionsResponse, i, latLng, wayPoint, list, MapplsAccountManager.getInstance().getClusterId(), (List<ReportDetails>) null, onAuthentication);
    }

    public void startNavigation(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, List<WayPoint> list, String str, OnAuthentication onAuthentication) {
        super.startNavigation(directionsResponse, i, latLng, wayPoint, list, str, (List<ReportDetails>) null, onAuthentication);
    }

    @Override // com.mappls.sdk.navigation.f
    public void stopNavigation() {
        super.stopNavigation();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void stopRecording() {
        super.stopRecording();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void stopTrackRecordingHandler() {
        super.stopTrackRecordingHandler();
    }

    @Override // com.mappls.sdk.navigation.f
    public /* bridge */ /* synthetic */ void updateArrivalDistance(int i) {
        super.updateArrivalDistance(i);
    }

    @Override // com.mappls.sdk.navigation.f
    public void updateWayPoint(WayPoint wayPoint, int i) {
        super.updateWayPoint(wayPoint, i);
    }
}
