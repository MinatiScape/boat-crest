package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.request.SetNavigationFavouriteLocationRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.navigation.db.model.RecentSearchHistoryData;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.repository.RecentSearchHistoryRepository;
import com.coveiot.android.theme.R;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.coveaccess.navigation.SaveFavouritePlaceRes;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.sdk.ble.api.model.FavouriteLocationData;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.mappls.sdk.services.api.OnResponseCallback;
import com.mappls.sdk.services.api.autosuggest.MapplsAutoSuggest;
import com.mappls.sdk.services.api.autosuggest.MapplsAutosuggestManager;
import com.mappls.sdk.services.api.autosuggest.model.AutoSuggestAtlasResponse;
import com.mappls.sdk.services.api.autosuggest.model.ELocation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationSearchPlacesViewModel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5538a;
    public final String b;
    @NotNull
    public final MutableLiveData<ArrayList<AutoSuggestionData>> c;
    @NotNull
    public LiveData<List<RecentSearchHistoryData>> d;

    public ActivityNavigationSearchPlacesViewModel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5538a = context;
        this.b = ActivityNavigationSearchPlacesViewModel.class.getSimpleName();
        this.c = new MutableLiveData<>();
        this.d = new MutableLiveData();
    }

    public final void autoSuggestPlace(@NotNull Location currentLocation, @NotNull String searchString) {
        Intrinsics.checkNotNullParameter(currentLocation, "currentLocation");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        MapplsAutosuggestManager.newInstance(MapplsAutoSuggest.builder().query(searchString).tokenizeAddress(Boolean.TRUE).setLocation(Double.valueOf(currentLocation.getLatitude()), Double.valueOf(currentLocation.getLongitude())).build()).call(new OnResponseCallback<AutoSuggestAtlasResponse>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$autoSuggestPlace$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i, @Nullable String str) {
                MutableLiveData mutableLiveData;
                mutableLiveData = ActivityNavigationSearchPlacesViewModel.this.c;
                mutableLiveData.setValue(null);
                String tag = ActivityNavigationSearchPlacesViewModel.this.getTAG();
                LogHelper.d(tag, "autoSuggestPlace onError p0 " + i + " p1 " + str);
            }

            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onSuccess(@Nullable AutoSuggestAtlasResponse autoSuggestAtlasResponse) {
                MutableLiveData mutableLiveData;
                MutableLiveData mutableLiveData2;
                if (autoSuggestAtlasResponse != null) {
                    ArrayList<ELocation> suggestions = autoSuggestAtlasResponse.getSuggestedLocations();
                    ArrayList arrayList = new ArrayList();
                    if (suggestions.size() <= 0) {
                        mutableLiveData = ActivityNavigationSearchPlacesViewModel.this.c;
                        mutableLiveData.setValue(null);
                        return;
                    }
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
                    mutableLiveData2 = ActivityNavigationSearchPlacesViewModel.this.c;
                    mutableLiveData2.setValue(arrayList);
                }
            }
        });
    }

    @NotNull
    public final MutableLiveData<ArrayList<AutoSuggestionData>> getAutoSearchData() {
        return this.c;
    }

    @NotNull
    public final Context getContext() {
        return this.f5538a;
    }

    public final void getFavouritePlaces(@NotNull final Function3<? super GetFavouritePlacesRes, ? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$getFavouritePlaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityNavigationSearchPlacesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<GetFavouritePlacesRes, String, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel!!.msg");
                        function3.invoke(null, msg, Boolean.FALSE);
                        return;
                    }
                    Function3<GetFavouritePlacesRes, String, Boolean, Unit> function32 = result;
                    String string = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function32.invoke(null, string, Boolean.FALSE);
                    return;
                }
                Function3<GetFavouritePlacesRes, String, Boolean, Unit> function33 = result;
                String string2 = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function33.invoke(null, string2, Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String tag = ActivityNavigationSearchPlacesViewModel.this.getTAG();
                LogHelper.d(tag, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                result.invoke(getFavouritePlacesRes, "", Boolean.TRUE);
            }
        });
    }

    public final void getRecentSearchHistory() {
        this.d = RecentSearchHistoryRepository.Companion.getInstance(this.f5538a).getRecentHistoryData();
    }

    @NotNull
    public final LiveData<List<RecentSearchHistoryData>> getRecentSearchHistoryData() {
        return this.d;
    }

    public final String getTAG() {
        return this.b;
    }

    public final void saveFavouritePlaceOnBand(@NotNull GetFavouritePlacesRes getFavouritePlacesRes, @NotNull final Function2<? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(getFavouritePlacesRes, "getFavouritePlacesRes");
        Intrinsics.checkNotNullParameter(result, "result");
        ArrayList arrayList = new ArrayList();
        List<FavouritePlace> favouritePlaceList = getFavouritePlacesRes.getFavouritePlaceList();
        Intrinsics.checkNotNullExpressionValue(favouritePlaceList, "getFavouritePlacesRes.favouritePlaceList");
        int i = 0;
        for (Object obj : favouritePlaceList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            FavouritePlace favouritePlace = (FavouritePlace) obj;
            Integer sortIndex = favouritePlace.getSortIndex();
            Intrinsics.checkNotNull(sortIndex);
            int intValue = sortIndex.intValue();
            Integer sortIndex2 = favouritePlace.getSortIndex();
            Intrinsics.checkNotNull(sortIndex2);
            arrayList.add(new FavouriteLocationData(intValue, sortIndex2.intValue(), favouritePlace.getLabel(), favouritePlace.getName()));
            i = i2;
        }
        BleApiManager.getInstance(this.f5538a).getBleApi().setUserSettings(new SetNavigationFavouriteLocationRequest(arrayList), new SettingsResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$saveFavouritePlaceOnBand$2
            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsError(@NotNull BleBaseError error) {
                Intrinsics.checkNotNullParameter(error, "error");
                result.invoke(error.getErrorMsg(), Boolean.FALSE);
                String tag = this.getTAG();
                LogHelper.d(tag, "saveFavouritePlaceOnBand onSettingsError " + error.getErrorMsg());
            }

            @Override // com.coveiot.android.bleabstract.listeners.SettingsResultListener
            public void onSettingsResponse(@NotNull BleBaseResponse response) {
                Intrinsics.checkNotNullParameter(response, "response");
                result.invoke("", Boolean.TRUE);
                LogHelper.d(this.getTAG(), "saveFavouritePlaceOnBand onSettingsResponse");
            }
        });
    }

    public final void saveFavouritePlaceToServer(@NotNull FavouritePlace favouritePlace, @NotNull final Function2<? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(favouritePlace, "favouritePlace");
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.saveFavouritePlace(favouritePlace, new CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$saveFavouritePlaceToServer$1

            /* loaded from: classes5.dex */
            public static final class a extends Lambda implements Function3<GetFavouritePlacesRes, String, Boolean, Unit> {
                public final /* synthetic */ Function2<String, Boolean, Unit> $result;
                public final /* synthetic */ ActivityNavigationSearchPlacesViewModel this$0;

                /* renamed from: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$saveFavouritePlaceToServer$1$a$a  reason: collision with other inner class name */
                /* loaded from: classes5.dex */
                public static final class C0297a extends Lambda implements Function2<String, Boolean, Unit> {
                    public final /* synthetic */ Function2<String, Boolean, Unit> $result;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C0297a(Function2<? super String, ? super Boolean, Unit> function2) {
                        super(2);
                        this.$result = function2;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                        invoke(str, bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull String _msg, boolean z) {
                        Intrinsics.checkNotNullParameter(_msg, "_msg");
                        if (z) {
                            this.$result.invoke(_msg, Boolean.TRUE);
                        } else {
                            this.$result.invoke(_msg, Boolean.FALSE);
                        }
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel, Function2<? super String, ? super Boolean, Unit> function2) {
                    super(3);
                    this.this$0 = activityNavigationSearchPlacesViewModel;
                    this.$result = function2;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(GetFavouritePlacesRes getFavouritePlacesRes, String str, Boolean bool) {
                    invoke(getFavouritePlacesRes, str, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(@Nullable GetFavouritePlacesRes getFavouritePlacesRes, @NotNull String msg, boolean z) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (!z) {
                        this.$result.invoke(msg, Boolean.FALSE);
                    } else if (getFavouritePlacesRes != null) {
                        this.this$0.saveFavouritePlaceOnBand(getFavouritePlacesRes, new C0297a(this.$result));
                    } else {
                        this.$result.invoke(msg, Boolean.FALSE);
                    }
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityNavigationSearchPlacesViewModel.this.getTAG();
                Log.d(tag, "saveFavouritePlace: onError " + new Gson().toJson(coveApiErrorModel));
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function2<String, Boolean, Unit> function2 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "`object`.msg");
                        function2.invoke(msg, Boolean.FALSE);
                        return;
                    }
                    Function2<String, Boolean, Unit> function22 = result;
                    String string = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function22.invoke(string, Boolean.FALSE);
                    return;
                }
                Function2<String, Boolean, Unit> function23 = result;
                String string2 = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function23.invoke(string2, Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<SaveFavouritePlaceRes> commonResponseGeneric) {
                if (m.equals$default(commonResponseGeneric != null ? commonResponseGeneric.getStatus() : null, CoveApiConstants.RESPONSE_STATUS_VALUE_OK, false, 2, null)) {
                    ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityNavigationSearchPlacesViewModel.this;
                    activityNavigationSearchPlacesViewModel.getFavouritePlaces(new a(activityNavigationSearchPlacesViewModel, result));
                    return;
                }
                Function2<String, Boolean, Unit> function2 = result;
                Intrinsics.checkNotNull(commonResponseGeneric);
                String status = commonResponseGeneric.getStatus();
                Intrinsics.checkNotNullExpressionValue(status, "`object`!!.status");
                function2.invoke(status, Boolean.FALSE);
            }
        });
    }

    public final void setRecentSearchHistoryData(@NotNull LiveData<List<RecentSearchHistoryData>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.d = liveData;
    }

    public final void updateFavouritePlaceToServer(@NotNull FavouritePlace favouritePlace, @NotNull final Function2<? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(favouritePlace, "favouritePlace");
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.editFavouritePlace(favouritePlace.getId(), favouritePlace, new CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$updateFavouritePlaceToServer$1

            /* loaded from: classes5.dex */
            public static final class a extends Lambda implements Function3<GetFavouritePlacesRes, String, Boolean, Unit> {
                public final /* synthetic */ Function2<String, Boolean, Unit> $result;
                public final /* synthetic */ ActivityNavigationSearchPlacesViewModel this$0;

                /* renamed from: com.coveiot.android.navigation.viewModels.ActivityNavigationSearchPlacesViewModel$updateFavouritePlaceToServer$1$a$a  reason: collision with other inner class name */
                /* loaded from: classes5.dex */
                public static final class C0298a extends Lambda implements Function2<String, Boolean, Unit> {
                    public final /* synthetic */ Function2<String, Boolean, Unit> $result;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public C0298a(Function2<? super String, ? super Boolean, Unit> function2) {
                        super(2);
                        this.$result = function2;
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                        invoke(str, bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@NotNull String _msg, boolean z) {
                        Intrinsics.checkNotNullParameter(_msg, "_msg");
                        if (z) {
                            this.$result.invoke(_msg, Boolean.TRUE);
                        } else {
                            this.$result.invoke(_msg, Boolean.FALSE);
                        }
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public a(ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel, Function2<? super String, ? super Boolean, Unit> function2) {
                    super(3);
                    this.this$0 = activityNavigationSearchPlacesViewModel;
                    this.$result = function2;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(GetFavouritePlacesRes getFavouritePlacesRes, String str, Boolean bool) {
                    invoke(getFavouritePlacesRes, str, bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(@Nullable GetFavouritePlacesRes getFavouritePlacesRes, @NotNull String msg, boolean z) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    if (!z) {
                        this.$result.invoke(msg, Boolean.FALSE);
                    } else if (getFavouritePlacesRes != null) {
                        this.this$0.saveFavouritePlaceOnBand(getFavouritePlacesRes, new C0298a(this.$result));
                    } else {
                        this.$result.invoke(msg, Boolean.FALSE);
                    }
                }
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String tag = ActivityNavigationSearchPlacesViewModel.this.getTAG();
                Log.d(tag, "updateFavouritePlaceToServer: onError " + new Gson().toJson(coveApiErrorModel));
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function2<String, Boolean, Unit> function2 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "`object`.msg");
                        function2.invoke(msg, Boolean.FALSE);
                        return;
                    }
                    Function2<String, Boolean, Unit> function22 = result;
                    String string = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function22.invoke(string, Boolean.FALSE);
                    return;
                }
                Function2<String, Boolean, Unit> function23 = result;
                String string2 = ActivityNavigationSearchPlacesViewModel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function23.invoke(string2, Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<SaveFavouritePlaceRes> commonResponseGeneric) {
                if (m.equals$default(commonResponseGeneric != null ? commonResponseGeneric.getStatus() : null, CoveApiConstants.RESPONSE_STATUS_VALUE_OK, false, 2, null)) {
                    ActivityNavigationSearchPlacesViewModel activityNavigationSearchPlacesViewModel = ActivityNavigationSearchPlacesViewModel.this;
                    activityNavigationSearchPlacesViewModel.getFavouritePlaces(new a(activityNavigationSearchPlacesViewModel, result));
                    return;
                }
                Function2<String, Boolean, Unit> function2 = result;
                Intrinsics.checkNotNull(commonResponseGeneric);
                String status = commonResponseGeneric.getStatus();
                Intrinsics.checkNotNullExpressionValue(status, "`object`!!.status");
                function2.invoke(status, Boolean.FALSE);
            }
        });
    }
}
