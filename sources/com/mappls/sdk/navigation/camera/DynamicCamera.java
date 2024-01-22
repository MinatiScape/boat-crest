package com.mappls.sdk.navigation.camera;

import android.location.Location;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraPosition;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.navigation.model.AdviseInfo;
import com.mappls.sdk.services.api.directions.models.LegStep;
import java.util.ArrayList;
@Keep
/* loaded from: classes11.dex */
public class DynamicCamera extends SimpleCamera {
    public static final double MAX_CAMERA_ZOOM = 16.35d;
    public static final double MIN_CAMERA_ZOOM = 14.0d;
    @Nullable
    private LegStep currentStep;
    private boolean forceUpdateZoom;
    private boolean hasPassedHighAlertLevel;
    private boolean hasPassedLowAlertLevel;
    private boolean hasPassedMediumAlertLevel;
    @Nullable
    private MapplsMap mapplsMap;
    private boolean isShutdown = false;
    private double maxCameraZoom = 16.35d;
    private double minCameraZoom = 14.0d;

    public DynamicCamera(@NonNull MapplsMap mapplsMap) {
        this.mapplsMap = mapplsMap;
    }

    @Nullable
    private CameraPosition createCameraPosition(Location location, @NonNull AdviseInfo adviseInfo) {
        LegStep legStep = (LegStep) adviseInfo.getInfo();
        if (legStep != null) {
            Point location2 = legStep.maneuver().location();
            ArrayList arrayList = new ArrayList();
            LatLng latLng = new LatLng(location);
            LatLng latLng2 = new LatLng(location2.latitude(), location2.longitude());
            arrayList.add(latLng);
            arrayList.add(latLng2);
            if (arrayList.size() >= 1 && !latLng.equals(latLng2)) {
                return this.mapplsMap.getCameraForLatLngBounds(new LatLngBounds.Builder().includes(arrayList).build(), new int[]{0, 0, 0, 0});
            }
            return this.mapplsMap.getCameraPosition();
        }
        return this.mapplsMap.getCameraPosition();
    }

    private double createZoom(@NonNull RouteInformation routeInformation) {
        CameraPosition createCameraPosition = createCameraPosition(routeInformation.getLocation(), routeInformation.getAdviseInfo());
        if (createCameraPosition == null) {
            return getDefaultZoom();
        }
        routeInformation.getAdviseInfo();
        double d = createCameraPosition.zoom;
        double d2 = this.maxCameraZoom;
        if (d > d2) {
            return d2;
        }
        double d3 = this.minCameraZoom;
        return d < d3 ? d3 : d;
    }

    private boolean isForceUpdate() {
        if (this.forceUpdateZoom) {
            this.forceUpdateZoom = false;
            return true;
        }
        return false;
    }

    private boolean isHighAlert(@NonNull AdviseInfo adviseInfo) {
        if (!this.hasPassedHighAlertLevel) {
            double duration = ((LegStep) adviseInfo.getInfo()).duration();
            boolean z = ((double) adviseInfo.getLeftTime()) < 15.0d;
            if ((duration > 15.0d) && z) {
                this.hasPassedHighAlertLevel = true;
                return true;
            }
        }
        return false;
    }

    private boolean isLowAlert(@NonNull AdviseInfo adviseInfo) {
        if (!this.hasPassedLowAlertLevel) {
            double duration = ((LegStep) adviseInfo.getInfo()).duration();
            boolean z = ((double) adviseInfo.getLeftTime()) < 125.0d;
            if ((duration > 125.0d) && z) {
                this.hasPassedLowAlertLevel = true;
                return true;
            }
        }
        return false;
    }

    private boolean isMediumAlert(@NonNull AdviseInfo adviseInfo) {
        if (!this.hasPassedMediumAlertLevel) {
            double duration = ((LegStep) adviseInfo.getInfo()).duration();
            boolean z = ((double) adviseInfo.getLeftTime()) < 70.0d;
            if ((duration > 70.0d) && z) {
                this.hasPassedMediumAlertLevel = true;
                return true;
            }
        }
        return false;
    }

    private boolean isNewStep(@NonNull AdviseInfo adviseInfo) {
        LegStep legStep = this.currentStep;
        boolean z = legStep == null || !legStep.equals(adviseInfo.getInfo());
        this.currentStep = (LegStep) adviseInfo.getInfo();
        resetAlertLevels(z);
        return z;
    }

    private void resetAlertLevels(boolean z) {
        if (z) {
            this.hasPassedLowAlertLevel = false;
            this.hasPassedMediumAlertLevel = false;
            this.hasPassedHighAlertLevel = false;
        }
    }

    private boolean shouldUpdateZoom(@NonNull RouteInformation routeInformation) {
        AdviseInfo adviseInfo = routeInformation.getAdviseInfo();
        return isForceUpdate() || isNewStep(adviseInfo) || isLowAlert(adviseInfo) || isMediumAlert(adviseInfo) || isHighAlert(adviseInfo);
    }

    private boolean validLocationAndProgress(@NonNull RouteInformation routeInformation) {
        return (routeInformation.getLocation() == null || routeInformation.getAdviseInfo() == null) ? false : true;
    }

    public void clearMap() {
        this.isShutdown = true;
        this.mapplsMap = null;
    }

    public void forceResetZoomLevel() {
        this.forceUpdateZoom = true;
    }

    public double getMaxCameraZoom() {
        return this.maxCameraZoom;
    }

    public double getMinCameraZoom() {
        return this.minCameraZoom;
    }

    public void setMaxCameraZoom(double d) {
        this.maxCameraZoom = d;
    }

    public void setMinCameraZoom(double d) {
        this.minCameraZoom = d;
    }

    @Override // com.mappls.sdk.navigation.camera.SimpleCamera, com.mappls.sdk.navigation.camera.Camera
    public double zoom(@NonNull RouteInformation routeInformation) {
        if (this.isShutdown) {
            return getDefaultZoom();
        }
        if (validLocationAndProgress(routeInformation) && shouldUpdateZoom(routeInformation)) {
            return createZoom(routeInformation);
        }
        if (routeInformation.getRoute() != null) {
            return super.zoom(routeInformation);
        }
        return getDefaultZoom();
    }
}
