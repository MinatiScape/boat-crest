package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetNavigationDisclaimerRequest;
import com.coveiot.android.bleabstract.request.SetNavigationAliveTimerRequest;
import com.coveiot.android.bleabstract.request.SetNavigationDisclaimerRequest;
import com.coveiot.android.bleabstract.request.SetNavigationStatusRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetNavigationDisclaimerResponse;
import com.coveiot.android.navigation.interfaces.BandDisclaimerListener;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.theme.R;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.MapplsDirectionManager;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationShowRoutesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5545a;
    public final String b;

    public ActivityNavigationShowRoutesViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5545a = context;
        this.b = ActivityNavigationShowRoutesViewModel.class.getSimpleName();
    }

    @NotNull
    public final Context getContext() {
        return this.f5545a;
    }

    public final void getDisclaimerStatusFromBand(@NotNull final String latestDisclaimerVersion, @NotNull final BandDisclaimerListener disclaimerListener) {
        Intrinsics.checkNotNullParameter(latestDisclaimerVersion, "latestDisclaimerVersion");
        Intrinsics.checkNotNullParameter(disclaimerListener, "disclaimerListener");
        BleApiManager.getInstance(this.f5545a).getBleApi().getData(new GetNavigationDisclaimerRequest(), new DataResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$getDisclaimerStatusFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                BandDisclaimerListener.this.onFailure();
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetNavigationDisclaimerResponse");
                GetNavigationDisclaimerResponse getNavigationDisclaimerResponse = (GetNavigationDisclaimerResponse) responseData;
                if (getNavigationDisclaimerResponse.isAccepted()) {
                    if (Intrinsics.areEqual(getNavigationDisclaimerResponse.getVersionText(), latestDisclaimerVersion)) {
                        BandDisclaimerListener.this.isAccepted();
                    } else if (Intrinsics.areEqual(getNavigationDisclaimerResponse.getVersionText(), latestDisclaimerVersion)) {
                    } else {
                        BandDisclaimerListener.this.needToSetDisclaimer();
                    }
                } else if (!Intrinsics.areEqual(getNavigationDisclaimerResponse.getVersionText(), latestDisclaimerVersion)) {
                    BandDisclaimerListener.this.needToSetDisclaimer();
                } else if (Intrinsics.areEqual(getNavigationDisclaimerResponse.getVersionText(), latestDisclaimerVersion)) {
                    BandDisclaimerListener.this.disclaimerAlreadySet();
                }
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void getEta(@NotNull AutoSuggestionData destinationAutoSuggestionData, @Nullable AutoSuggestionData autoSuggestionData, @Nullable Location location, boolean z, @NotNull String mode, boolean z2, @NotNull final Function1<? super DirectionsResponse, Unit> result) {
        MapplsDirections build;
        Intrinsics.checkNotNullParameter(destinationAutoSuggestionData, "destinationAutoSuggestionData");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(result, "result");
        boolean areEqual = Intrinsics.areEqual(mode, "walking");
        String str = DirectionsCriteria.RESOURCE_ROUTE;
        if (!areEqual && Intrinsics.areEqual(mode, "driving")) {
            str = DirectionsCriteria.RESOURCE_ROUTE_ETA;
        }
        if (z2) {
            MapplsDirections.Builder origin = MapplsDirections.builder().origin(destinationAutoSuggestionData.getMapplsPin());
            Boolean bool = Boolean.TRUE;
            MapplsDirections.Builder overview = origin.steps(bool).resource(str).profile(mode).annotations(DirectionsCriteria.ANNOTATION_CONGESTION).alternatives(bool).overview("full");
            Intrinsics.checkNotNull(location);
            build = overview.destination(Point.fromLngLat(location.getLongitude(), location.getLatitude())).deviceId(Settings.Secure.getString(this.f5545a.getContentResolver(), "android_id")).routeRefresh(bool).build();
        } else if (z) {
            MapplsDirections.Builder builder = MapplsDirections.builder();
            Intrinsics.checkNotNull(location);
            MapplsDirections.Builder origin2 = builder.origin(Point.fromLngLat(location.getLongitude(), location.getLatitude()));
            Boolean bool2 = Boolean.TRUE;
            build = origin2.steps(bool2).resource(str).profile(mode).annotations(DirectionsCriteria.ANNOTATION_CONGESTION).alternatives(bool2).overview("full").destination(destinationAutoSuggestionData.getMapplsPin()).deviceId(Settings.Secure.getString(this.f5545a.getContentResolver(), "android_id")).routeRefresh(bool2).build();
        } else {
            MapplsDirections.Builder builder2 = MapplsDirections.builder();
            Intrinsics.checkNotNull(autoSuggestionData);
            MapplsDirections.Builder origin3 = builder2.origin(autoSuggestionData.getMapplsPin());
            Boolean bool3 = Boolean.TRUE;
            build = origin3.steps(bool3).resource(str).profile(mode).annotations(DirectionsCriteria.ANNOTATION_CONGESTION).alternatives(bool3).overview("full").destination(destinationAutoSuggestionData.getMapplsPin()).deviceId(Settings.Secure.getString(this.f5545a.getContentResolver(), "android_id")).routeRefresh(bool3).build();
        }
        MapplsDirectionManager.newInstance(build).call(new OnResponseCallback<DirectionsResponse>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$getEta$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i, @Nullable String str2) {
                result.invoke(null);
                String tag = this.getTAG();
                LogHelper.d(tag, "Direction Api failed p0 " + i + " p1 " + str2);
            }

            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onSuccess(@Nullable DirectionsResponse directionsResponse) {
                result.invoke(directionsResponse);
            }
        });
    }

    public final void getFavouritePlaces(@NotNull final Function3<? super GetFavouritePlacesRes, ? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$getFavouritePlaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityNavigationShowRoutesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<GetFavouritePlacesRes, String, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel.msg");
                        function3.invoke(null, msg, Boolean.FALSE);
                        return;
                    }
                    Function3<GetFavouritePlacesRes, String, Boolean, Unit> function32 = result;
                    String string = ActivityNavigationShowRoutesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function32.invoke(null, string, Boolean.FALSE);
                    return;
                }
                Function3<GetFavouritePlacesRes, String, Boolean, Unit> function33 = result;
                String string2 = ActivityNavigationShowRoutesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function33.invoke(null, string2, Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String tag = ActivityNavigationShowRoutesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                result.invoke(getFavouritePlacesRes, "", Boolean.TRUE);
            }
        });
    }

    public final String getTAG() {
        return this.b;
    }

    public final void setDisclaimerContentToBand(@NotNull String version, @NotNull String versionContent, @NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(versionContent, "versionContent");
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this.f5545a).getBleApi().setUserSettings(new SetNavigationDisclaimerRequest(version, versionContent), new SettingsResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$setDisclaimerContentToBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(Boolean.FALSE);
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                result.invoke(Boolean.TRUE);
            }
        });
    }

    public final void setNavigationAliveTimerOnBand(int i, @NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this.f5545a).getBleApi().setUserSettings(new SetNavigationAliveTimerRequest(i), new SettingsResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$setNavigationAliveTimerOnBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(Boolean.FALSE);
                String tag = this.getTAG();
                LogHelper.d(tag, "setNavigationAliveTimerOnBand onSettingsError " + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                result.invoke(Boolean.TRUE);
                LogHelper.d(this.getTAG(), "setNavigationAliveTimerOnBand onSettingsResponse");
            }
        });
    }

    public final void setNavigationStatusOnBand(int i) {
        BleApiManager.getInstance(this.f5545a).getBleApi().setUserSettings(new SetNavigationStatusRequest(i), new SettingsResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationShowRoutesViewModel$setNavigationStatusOnBand$1
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                LogHelper.d(ActivityNavigationShowRoutesViewModel.this.getTAG(), "setNavigationStatusOnBand onSettingsError");
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                LogHelper.d(ActivityNavigationShowRoutesViewModel.this.getTAG(), "setNavigationStatusOnBand onSettingsResponse");
            }
        });
    }
}
