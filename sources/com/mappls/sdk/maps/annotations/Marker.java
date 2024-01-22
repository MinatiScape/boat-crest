package com.mappls.sdk.maps.annotations;

import android.view.View;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.BaseMapplsHelper;
import com.mappls.sdk.maps.CoordinateCallback;
import com.mappls.sdk.maps.CoordinateResult;
import com.mappls.sdk.maps.MapView;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.geometry.LatLng;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
@Keep
@Deprecated
/* loaded from: classes11.dex */
public class Marker extends Annotation {
    @Nullable
    private Icon icon;
    @Nullable
    @Keep
    private String iconId;
    @Nullable
    private InfoWindow infoWindow;
    private boolean infoWindowShown;
    private String mapplsPin;
    @Keep
    private LatLng position;
    private int rightOffsetPixels;
    private String snippet;
    private String title;
    private int topOffsetPixels;

    /* loaded from: classes11.dex */
    public class a implements CoordinateCallback {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ InfoWindow f12670a;
        public final /* synthetic */ MapView b;

        public a(InfoWindow infoWindow, MapView mapView) {
            this.f12670a = infoWindow;
            this.b = mapView;
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void coordinateResultSuccess(List<CoordinateResult> list) {
            if (list.size() > 0) {
                LatLng latLng = new LatLng(list.get(0).getLatitude().doubleValue(), list.get(0).getLongitude().doubleValue());
                InfoWindow infoWindow = this.f12670a;
                MapView mapView = this.b;
                Marker marker = Marker.this;
                infoWindow.p(mapView, marker, latLng, marker.rightOffsetPixels, Marker.this.topOffsetPixels);
                Marker.this.infoWindowShown = true;
            }
        }

        @Override // com.mappls.sdk.maps.CoordinateCallback
        public void onFailure() {
        }
    }

    public Marker() {
    }

    private void refreshInfoWindowContent() {
        MapplsMap mapplsMap;
        if (!isInfoWindowShown() || this.mapView == null || (mapplsMap = this.mapplsMap) == null || mapplsMap.getInfoWindowAdapter() != null) {
            return;
        }
        InfoWindow infoWindow = getInfoWindow(this.mapView);
        if (this.mapView.getContext() != null) {
            infoWindow.j(this, this.mapplsMap, this.mapView);
        }
        MapplsMap mapplsMap2 = getMapplsMap();
        if (mapplsMap2 != null) {
            mapplsMap2.updateMarker(this);
        }
        infoWindow.o();
    }

    @Nullable
    public Icon getIcon() {
        return this.icon;
    }

    @Nullable
    public InfoWindow getInfoWindow() {
        return this.infoWindow;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public LatLng getPosition() {
        return this.position;
    }

    public String getSnippet() {
        return this.snippet;
    }

    public String getTitle() {
        return this.title;
    }

    public void hideInfoWindow() {
        InfoWindow infoWindow = this.infoWindow;
        if (infoWindow != null) {
            infoWindow.k();
        }
        this.infoWindowShown = false;
    }

    public boolean isInfoWindowShown() {
        return this.infoWindowShown;
    }

    public void setIcon(@Nullable Icon icon) {
        this.icon = icon;
        this.iconId = icon != null ? icon.getId() : null;
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updateMarker(this);
        }
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str.toUpperCase();
        this.position = null;
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updateMarker(this);
        }
    }

    public void setPosition(LatLng latLng) {
        this.position = latLng;
        if (latLng != null) {
            this.mapplsPin = null;
        }
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updateMarker(this);
        }
    }

    public void setRightOffsetPixels(int i) {
        this.rightOffsetPixels = i;
    }

    public void setSnippet(String str) {
        this.snippet = str;
        refreshInfoWindowContent();
    }

    public void setTitle(String str) {
        this.title = str;
        refreshInfoWindowContent();
    }

    public void setTopOffsetPixels(int i) {
        this.topOffsetPixels = i;
    }

    @Nullable
    public InfoWindow showInfoWindow(@NonNull MapplsMap mapplsMap, @NonNull MapView mapView) {
        View infoWindow;
        setMapplsMap(mapplsMap);
        setMapView(mapView);
        MapplsMap.InfoWindowAdapter infoWindowAdapter = getMapplsMap().getInfoWindowAdapter();
        if (infoWindowAdapter != null && (infoWindow = infoWindowAdapter.getInfoWindow(this)) != null) {
            InfoWindow infoWindow2 = new InfoWindow(infoWindow, mapplsMap);
            this.infoWindow = infoWindow2;
            showInfoWindow(infoWindow2, mapView);
            return this.infoWindow;
        }
        InfoWindow infoWindow3 = getInfoWindow(mapView);
        if (mapView.getContext() != null) {
            infoWindow3.j(this, mapplsMap, mapView);
        }
        return showInfoWindow(infoWindow3, mapView);
    }

    public String toString() {
        return "Marker [position[" + getPosition() + "]]";
    }

    public void updatePosition(LatLng latLng) {
        this.position = latLng;
    }

    public Marker(BaseMarkerOptions baseMarkerOptions) {
        this(baseMarkerOptions.position, baseMarkerOptions.icon, baseMarkerOptions.title, baseMarkerOptions.snippet, baseMarkerOptions.mapplsPin);
    }

    @Nullable
    private InfoWindow getInfoWindow(@NonNull MapView mapView) {
        if (this.infoWindow == null && mapView.getContext() != null) {
            this.infoWindow = new InfoWindow(mapView, R.layout.mappls_maps_infowindow_content, getMapplsMap());
        }
        return this.infoWindow;
    }

    public Marker(LatLng latLng, Icon icon, String str, String str2, String str3) {
        this.position = latLng;
        this.title = str;
        this.snippet = str2;
        this.mapplsPin = str3;
        setIcon(icon);
    }

    public void setMapplsPin(String str, MapplsMap.OnMarkerAddedListener onMarkerAddedListener) {
        this.mapplsPin = str.toUpperCase();
        this.position = null;
        MapplsMap mapplsMap = getMapplsMap();
        if (mapplsMap != null) {
            mapplsMap.updateMarker(this, onMarkerAddedListener);
        }
    }

    @NonNull
    private InfoWindow showInfoWindow(InfoWindow infoWindow, MapView mapView) {
        if (this.position == null) {
            try {
                System.out.println(BaseMapplsHelper.class.getName());
                Object newInstance = BaseMapplsHelper.class.newInstance();
                Method declaredMethod = BaseMapplsHelper.class.getDeclaredMethod("getAnnotation", String.class, CoordinateCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(newInstance, this.mapplsPin, new a(infoWindow, mapView));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (InstantiationException e3) {
                e3.printStackTrace();
            } catch (NoSuchMethodException e4) {
                e4.printStackTrace();
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
            }
        } else {
            infoWindow.p(mapView, this, getPosition(), this.rightOffsetPixels, this.topOffsetPixels);
            this.infoWindowShown = true;
        }
        return infoWindow;
    }
}
