package com.mappls.sdk.maps.annotations;

import android.os.Parcelable;
import com.mappls.sdk.maps.annotations.BaseMarkerOptions;
import com.mappls.sdk.maps.annotations.Marker;
import com.mappls.sdk.maps.geometry.LatLng;
@Deprecated
/* loaded from: classes11.dex */
public abstract class BaseMarkerOptions<U extends Marker, T extends BaseMarkerOptions<U, T>> implements Parcelable {
    public Icon icon;
    public String mapplsPin;
    public LatLng position;
    public String snippet;
    public String title;

    public abstract U getMarker();

    public abstract T getThis();

    public T icon(Icon icon) {
        this.icon = icon;
        return getThis();
    }

    public T mapplsPin(String str) {
        this.position = null;
        this.mapplsPin = str.toUpperCase();
        return getThis();
    }

    public T position(LatLng latLng) {
        this.mapplsPin = null;
        this.position = latLng;
        return getThis();
    }

    public T setIcon(Icon icon) {
        return icon(icon);
    }

    public T setMapplsPin(String str) {
        return mapplsPin(str);
    }

    public T setPosition(LatLng latLng) {
        return position(latLng);
    }

    public T setSnippet(String str) {
        return snippet(str);
    }

    public T setTitle(String str) {
        return title(str);
    }

    public T snippet(String str) {
        this.snippet = str;
        return getThis();
    }

    public T title(String str) {
        this.title = str;
        return getThis();
    }
}
