package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.repository.RecentSearchHistoryRepository;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.mappls.sdk.geojson.Point;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest;
import com.mappls.sdk.services.api.autosuggest.MapplsAutosuggestManager;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import com.mappls.sdk.services.api.directions.DirectionsCriteria;
import com.mappls.sdk.services.api.directions.MapplsDirectionManager;
import com.mappls.sdk.services.api.directions.MapplsDirections;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class NavigationMainViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5550a;
    public final String b;
    @NotNull
    public final MutableLiveData<List<FavouritePlace>> c;
    @NotNull
    public LiveData<List<RecentSearchHistoryData>> d;

    public NavigationMainViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5550a = context;
        this.b = NavigationMainViewModel.class.getSimpleName();
        new MutableLiveData();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData();
    }

    public final void autoSuggestPlace(@NotNull Location currentLocation, @NotNull String searchString, @NotNull final Function2<? super Boolean, ? super ArrayList<AutoSuggestionData>, Unit> result) {
        Intrinsics.checkNotNullParameter(currentLocation, "currentLocation");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        Intrinsics.checkNotNullParameter(result, "result");
        MapplsAutosuggestManager.newInstance(MapplsAutoSuggest.builder().query(searchString).tokenizeAddress(Boolean.TRUE).setLocation(Double.valueOf(currentLocation.getLatitude()), Double.valueOf(currentLocation.getLongitude())).build()).call(new OnResponseCallback<AutoSuggestAtlasResponse>() { // from class: com.coveiot.android.navigation.viewModels.NavigationMainViewModel$autoSuggestPlace$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i, @Nullable String str) {
                result.invoke(Boolean.FALSE, null);
                String tag = this.getTAG();
                LogHelper.d(tag, "autoSuggestPlace onError p0 " + i + " p1 " + str);
            }

            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onSuccess(@Nullable AutoSuggestAtlasResponse autoSuggestAtlasResponse) {
                if (autoSuggestAtlasResponse != null) {
                    ArrayList<ELocation> suggestions = autoSuggestAtlasResponse.getSuggestedLocations();
                    ArrayList<AutoSuggestionData> arrayList = new ArrayList<>();
                    if (suggestions.size() > 0) {
                        Intrinsics.checkNotNullExpressionValue(suggestions, "suggestions");
                        int i = 0;
                        for (Object obj : suggestions) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            ELocation eLocation = (ELocation) obj;
                            double doubleValue = new BigDecimal(String.valueOf(eLocation.distance.doubleValue() / 1000.0d)).setScale(2, RoundingMode.HALF_UP).doubleValue();
                            String str = eLocation.placeName;
                            Intrinsics.checkNotNullExpressionValue(str, "eLocation.placeName");
                            String str2 = eLocation.placeAddress;
                            Intrinsics.checkNotNullExpressionValue(str2, "eLocation.placeAddress");
                            long j = eLocation.orderIndex;
                            String str3 = eLocation.type;
                            Intrinsics.checkNotNullExpressionValue(str3, "eLocation.type");
                            String str4 = eLocation.mapplsPin;
                            Intrinsics.checkNotNullExpressionValue(str4, "eLocation.mapplsPin");
                            arrayList.add(new AutoSuggestionData(str, str2, doubleValue, j, str3, str4));
                            i = i2;
                        }
                        result.invoke(Boolean.TRUE, arrayList);
                        return;
                    }
                    result.invoke(Boolean.FALSE, null);
                }
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5550a;
    }

    public final void getEta(@NotNull AutoSuggestionData autoSuggestionData, @Nullable Location location, @NotNull final Function1<? super DirectionsResponse, Unit> result) {
        Intrinsics.checkNotNullParameter(autoSuggestionData, "autoSuggestionData");
        Intrinsics.checkNotNullParameter(result, "result");
        MapplsDirections.Builder builder = MapplsDirections.builder();
        Intrinsics.checkNotNull(location);
        MapplsDirections.Builder origin = builder.origin(Point.fromLngLat(location.getLongitude(), location.getLatitude()));
        Boolean bool = Boolean.TRUE;
        MapplsDirections build = origin.steps(bool).resource(DirectionsCriteria.RESOURCE_ROUTE).profile("walking").alternatives(bool).overview("full").destination(autoSuggestionData.getMapplsPin()).deviceId(Settings.Secure.getString(this.f5550a.getContentResolver(), "android_id")).routeRefresh(bool).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder()\n              …outeRefresh(true).build()");
        MapplsDirectionManager.newInstance(build).call(new OnResponseCallback<DirectionsResponse>() { // from class: com.coveiot.android.navigation.viewModels.NavigationMainViewModel$getEta$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i, @Nullable String str) {
                result.invoke(null);
                String tag = this.getTAG();
                LogHelper.d(tag, "Direction Api failed p0 " + i + " p1 " + str);
            }

            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onSuccess(@Nullable DirectionsResponse directionsResponse) {
                result.invoke(directionsResponse);
            }
        });
    }

    @NotNull
    public final LiveData<List<FavouritePlace>> getFavPlacesData() {
        return this.c;
    }

    public final void getFavouritePlaces(@NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.NavigationMainViewModel$getFavouritePlaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = NavigationMainViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
                result.invoke(Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                MutableLiveData mutableLiveData;
                mutableLiveData = NavigationMainViewModel.this.c;
                mutableLiveData.setValue(getFavouritePlacesRes != null ? getFavouritePlacesRes.getFavouritePlaceList() : null);
                String tag = NavigationMainViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                result.invoke(Boolean.TRUE);
            }
        });
    }

    public final void getRecentSearchHistory() {
        this.d = RecentSearchHistoryRepository.Companion.getInstance(this.f5550a).getRecentHistoryData();
    }

    @NotNull
    public final LiveData<List<RecentSearchHistoryData>> getRecentSearchHistoryData() {
        return this.d;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void setRecentSearchHistoryData(@NotNull LiveData<List<RecentSearchHistoryData>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.d = liveData;
    }
}
