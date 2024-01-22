package com.mappls.sdk.navigation.session;

import android.os.Build;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavLocation;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationConstants;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.data.WayPoint;
import com.mappls.sdk.navigation.iface.IStopSession;
import com.mappls.sdk.navigation.model.NavigationResponse;
import com.mappls.sdk.navigation.util.AuthenticationError;
import com.mappls.sdk.navigation.util.ErrorType;
import com.mappls.sdk.services.account.MapplsAccountManager;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import com.mappls.sdk.services.api.directions.models.DirectionsRoute;
import com.mappls.sdk.services.api.directions.models.DirectionsWaypoint;
import com.mappls.sdk.services.api.session.create.MapplsCreateSession;
import com.mappls.sdk.services.api.session.create.model.SessionRequestModel;
import com.mappls.sdk.services.api.session.create.model.SessionResponse;
import com.mappls.sdk.services.api.session.delete.MapplsDeleteSession;
import com.mappls.sdk.services.api.session.endsession.MapplsEndSession;
import com.mappls.sdk.services.api.session.endsession.MapplsEndSessionManager;
import com.mappls.sdk.services.api.session.update.MapplsUpdateSession;
import java.lang.reflect.Field;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12952a;
    public SessionResponse b;

    /* renamed from: com.mappls.sdk.navigation.session.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0645a implements Callback<SessionResponse> {
        public C0645a() {
        }

        @Override // retrofit2.Callback
        public final void onFailure(Call<SessionResponse> call, Throwable th) {
        }

        @Override // retrofit2.Callback
        public final void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
            a.this.b = response.body();
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Callback<SessionResponse> {
        public b() {
        }

        @Override // retrofit2.Callback
        public final void onFailure(Call<SessionResponse> call, Throwable th) {
            if (call.isCanceled()) {
                return;
            }
            a.this.b = null;
        }

        @Override // retrofit2.Callback
        public final void onResponse(Call<SessionResponse> call, Response<SessionResponse> response) {
            a.this.b = null;
        }
    }

    /* loaded from: classes11.dex */
    public class c implements OnResponseCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ IStopSession f12955a;

        public c(IStopSession iStopSession) {
            this.f12955a = iStopSession;
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onError(int i, String str) {
            IStopSession iStopSession;
            if (i == 0 || (iStopSession = this.f12955a) == null) {
                return;
            }
            iStopSession.onFailure();
        }

        @Override // com.mappls.sdk.services.api.OnResponseCallback
        public final void onSuccess(Void r2) {
            a.this.b = null;
            IStopSession iStopSession = this.f12955a;
            if (iStopSession != null) {
                iStopSession.onSuccess();
            }
        }
    }

    public a(NavigationApplication navigationApplication) {
        this.f12952a = navigationApplication;
    }

    public final NavigationResponse a(DirectionsResponse directionsResponse, int i, LatLng latLng, WayPoint wayPoint, String str) {
        String str2;
        Response<SessionResponse> executeCall;
        SessionRequestModel sessionRequestModel = new SessionRequestModel();
        sessionRequestModel.setDeviceFingerprint(this.f12952a.b());
        sessionRequestModel.setSdkVersion("0.13.6");
        sessionRequestModel.setNst(Long.valueOf(System.currentTimeMillis() / 1000));
        if (MapplsAccountManager.getInstance().getDeviceAlias() != null) {
            sessionRequestModel.setDeviceAlias(MapplsAccountManager.getInstance().getDeviceAlias());
        }
        sessionRequestModel.setStartPoint(latLng.getLatitude() + Constants.SEPARATOR_COMMA + latLng.getLongitude());
        AuthenticationError authenticationError = null;
        if (wayPoint.isValidCoordinates()) {
            str2 = wayPoint.getEntryLatitude() + Constants.SEPARATOR_COMMA + wayPoint.getEntryLongitude();
        } else if (directionsResponse.waypoints() == null || directionsResponse.waypoints().size() <= 1) {
            return new NavigationResponse(new AuthenticationError(6, "Something went wrong-101"), null);
        } else {
            DirectionsWaypoint directionsWaypoint = directionsResponse.waypoints().get(directionsResponse.waypoints().size() - 1);
            str2 = directionsWaypoint.location().latitude() + Constants.SEPARATOR_COMMA + directionsWaypoint.location().longitude();
        }
        sessionRequestModel.setEndPoint(str2);
        try {
            DirectionsRoute selectedTrip = MapplsNavigationHelper.getInstance().getSelectedTrip(directionsResponse, i);
            if (selectedTrip.legs() != null && selectedTrip.legs().size() > 0) {
                sessionRequestModel.setTripDistance(Long.valueOf(selectedTrip.distance().longValue()));
                sessionRequestModel.setTripDuration(Long.valueOf(selectedTrip.duration().longValue()));
            }
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
        if (MapplsAccountManager.getInstance().getAssociationId() != null) {
            sessionRequestModel.setAssociationId(MapplsAccountManager.getInstance().getAssociationId());
        }
        try {
            executeCall = MapplsCreateSession.builder().sessionType("navigation").sessionRequest(sessionRequestModel).clusterId(str).build().executeCall();
        } catch (Exception e2) {
            e = e2;
            this.b = null;
            authenticationError = (NavigationConstants.getFailureType(e) == 0 || NavigationConstants.getFailureType(e) == 1) ? ErrorType.NETWORK_ERROR : ErrorType.SERVER_ERROR;
            NavigationLogger.d(e);
        }
        if (executeCall.code() != 200 && executeCall.code() != 201) {
            AuthenticationError authenticationError2 = new AuthenticationError(executeCall.code(), executeCall.headers().get(Constants.KEY_MESSAGE));
            this.b = null;
            e = null;
            authenticationError = authenticationError2;
            return new NavigationResponse(authenticationError, e);
        }
        this.b = executeCall.body();
        e = null;
        return new NavigationResponse(authenticationError, e);
    }

    public final void a() {
        SessionResponse sessionResponse = this.b;
        if (sessionResponse == null) {
            return;
        }
        MapplsDeleteSession.builder().hyperlink(sessionResponse.passportLink).build().enqueueCall(new b());
    }

    public final void a(NavLocation navLocation) {
        SessionResponse sessionResponse = this.b;
        if (sessionResponse == null) {
            return;
        }
        String str = sessionResponse.passportLink;
        SessionRequestModel sessionRequestModel = new SessionRequestModel();
        String str2 = Build.BRAND;
        String str3 = Build.MODEL;
        String string = Settings.Secure.getString(this.f12952a.getContentResolver(), "android_id");
        sessionRequestModel.setDeviceFingerprint(str2 + ':' + str3 + ':' + string);
        sessionRequestModel.setOsName(Build.VERSION.CODENAME);
        Field[] fields = Build.VERSION_CODES.class.getFields();
        int i = Build.VERSION.SDK_INT;
        sessionRequestModel.setSdkVersion(fields[i + (-1)].getName());
        sessionRequestModel.setOsVersion(String.valueOf(i));
        sessionRequestModel.setNst(Long.valueOf(System.currentTimeMillis() / 1000));
        if (this.f12952a.getCurrentLocation() != null) {
            sessionRequestModel.setStartPoint(this.f12952a.getCurrentLocation().getLatitude() + Constants.SEPARATOR_COMMA + this.f12952a.getCurrentLocation().getLongitude());
        }
        if (navLocation != null) {
            sessionRequestModel.setEndPoint(navLocation.getLatitude() + Constants.SEPARATOR_COMMA + navLocation.getLongitude());
        }
        if (MapplsNavigationHelper.getInstance().getCurrentRoute() != null) {
            sessionRequestModel.setTripDistance(Long.valueOf(MapplsNavigationHelper.getInstance().getCurrentRoute().distance().longValue()));
            sessionRequestModel.setTripDuration(Long.valueOf(MapplsNavigationHelper.getInstance().getCurrentRoute().duration().longValue()));
        }
        if (MapplsAccountManager.getInstance().getAssociationId() != null) {
            sessionRequestModel.setAssociationId(MapplsAccountManager.getInstance().getAssociationId());
        }
        MapplsUpdateSession.builder().sessionRequest(sessionRequestModel).hyperlink(str).build().enqueueCall(new C0645a());
    }

    public final void a(@NonNull String str, @Nullable IStopSession iStopSession) {
        MapplsEndSessionManager.newInstance(MapplsEndSession.builder().clusterId(str).sessionType("navigation").build()).call(new c(iStopSession));
    }
}
