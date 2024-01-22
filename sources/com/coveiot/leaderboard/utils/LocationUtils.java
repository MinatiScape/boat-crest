package com.coveiot.leaderboard.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;
import com.google.android.libraries.places.api.model.Place;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes9.dex */
public final class LocationUtils {
    @NotNull
    public static final LocationUtils INSTANCE = new LocationUtils();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static JsonObject f7217a;

    public final boolean checkLocationPermission(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    @Nullable
    public final JsonObject getAddressFromLatLong(@NotNull Context context, double d, double d2) {
        Intrinsics.checkNotNullParameter(context, "context");
        JsonObject jsonObject = new JsonObject();
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.ENGLISH).getFromLocation(d, d2, 1);
            if (fromLocation != null && fromLocation.size() > 0) {
                int i = 0;
                Address address = fromLocation.get(0);
                Intrinsics.checkNotNull(address);
                int maxAddressLineIndex = address.getMaxAddressLineIndex();
                if (maxAddressLineIndex >= 0) {
                    while (true) {
                        address.getAddressLine(i);
                        if (i == maxAddressLineIndex) {
                            break;
                        }
                        i++;
                    }
                }
                jsonObject.addProperty("locationType", "Home");
                jsonObject.addProperty("longitude", "" + d2);
                jsonObject.addProperty("latitude", "" + d);
                jsonObject.addProperty("locality", address.getSubLocality() == null ? address.getLocality() : address.getSubLocality());
                jsonObject.addProperty(GeoCodingCriteria.POD_CITY, address.getLocality());
                jsonObject.addProperty("country", address.getCountryName());
                jsonObject.addProperty("administrativeArea", address.getAdminArea());
                f7217a = jsonObject;
                LeaderBoardDataUtiils.saveAddressJson(context, String.valueOf(jsonObject));
                String.valueOf(f7217a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f7217a;
    }

    @Nullable
    public final JsonObject getAddressRequestObject() {
        return f7217a;
    }

    @Nullable
    public final JsonObject getCompleteAddressString(@NotNull Context context, @NotNull Place place, double d, double d2) {
        String subLocality;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(place, "place");
        JsonObject jsonObject = new JsonObject();
        new HashMap();
        try {
            List<Address> fromLocation = new Geocoder(context, Locale.ENGLISH).getFromLocation(d, d2, 1);
            if (fromLocation != null && fromLocation.size() > 0) {
                Address address = fromLocation.get(0);
                jsonObject.addProperty("locationType", "Home");
                jsonObject.addProperty("longitude", "" + d2);
                jsonObject.addProperty("latitude", "" + d);
                if (address.getSubLocality() == null) {
                    subLocality = place.getName() + "";
                } else {
                    subLocality = address.getSubLocality();
                }
                jsonObject.addProperty("locality", subLocality);
                jsonObject.addProperty(GeoCodingCriteria.POD_CITY, address.getLocality());
                jsonObject.addProperty("country", address.getCountryName());
                jsonObject.addProperty("administrativeArea", address.getAdminArea());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Nullable
    public final Location getLocation(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        LocationManager locationManager = (LocationManager) systemService;
        if (checkLocationPermission(context)) {
            Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
            return lastKnownLocation == null ? locationManager.getLastKnownLocation("passive") : lastKnownLocation;
        }
        return null;
    }

    public final void setAddressRequestObject(@Nullable JsonObject jsonObject) {
        f7217a = jsonObject;
    }
}
