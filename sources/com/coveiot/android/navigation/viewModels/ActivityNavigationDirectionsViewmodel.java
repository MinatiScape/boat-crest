package com.coveiot.android.navigation.viewModels;

import android.content.Context;
import android.location.Location;
import android.provider.Settings;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.models.ProgressData;
import com.coveiot.android.bleabstract.request.GetNavigationDisclaimerRequest;
import com.coveiot.android.bleabstract.response.BleBaseError;
import com.coveiot.android.bleabstract.response.BleBaseResponse;
import com.coveiot.android.bleabstract.response.GetNavigationDisclaimerResponse;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.utils.NavigationConstants;
import com.coveiot.android.theme.R;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveOnboarding;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.NavigationApi;
import com.coveiot.coveaccess.navigation.SavePlaceTrackHistoryRes;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsReq;
import com.coveiot.coveaccess.onboarding.model.AcceptLegalTermsRes;
import com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes;
import com.coveiot.covepreferences.SessionManager;
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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityNavigationDirectionsViewmodel extends ViewModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5525a;
    public final String b;

    public ActivityNavigationDirectionsViewmodel(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5525a = context;
        this.b = ActivityNavigationDirectionsViewmodel.class.getSimpleName();
    }

    public final void autoSuggestPlace(@NotNull Location currentLocation, @NotNull String searchString, @NotNull final Function2<? super Boolean, ? super ArrayList<AutoSuggestionData>, Unit> result) {
        Intrinsics.checkNotNullParameter(currentLocation, "currentLocation");
        Intrinsics.checkNotNullParameter(searchString, "searchString");
        Intrinsics.checkNotNullParameter(result, "result");
        MapplsAutosuggestManager.newInstance(MapplsAutoSuggest.builder().query(searchString).tokenizeAddress(Boolean.TRUE).setLocation(Double.valueOf(currentLocation.getLatitude()), Double.valueOf(currentLocation.getLongitude())).build()).call(new OnResponseCallback<AutoSuggestAtlasResponse>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$autoSuggestPlace$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i, @Nullable String str) {
                String str2;
                result.invoke(Boolean.FALSE, null);
                str2 = this.b;
                LogHelper.d(str2, "autoSuggestPlace onError p0 " + i + " p1 " + str);
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

    public final void checkBandDisclaimerAcceptanceOnServer(@NotNull final String version, @NotNull final Function3<? super String, ? super Boolean, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getAcceptedLegalDoc(new CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$checkBandDisclaimerAcceptanceOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "checkBandDisclaimerAcceptanceOnServer onError ");
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<String, Boolean, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Boolean bool = Boolean.FALSE;
                        function3.invoke(msg, bool, bool);
                        return;
                    }
                    Function3<String, Boolean, Boolean, Unit> function32 = result;
                    String string = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Boolean bool2 = Boolean.FALSE;
                    function32.invoke(string, bool2, bool2);
                    return;
                }
                Function3<String, Boolean, Boolean, Unit> function33 = result;
                String string2 = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                Boolean bool3 = Boolean.FALSE;
                function33.invoke(string2, bool3, bool3);
            }

            /* JADX WARN: Removed duplicated region for block: B:42:0x0093 A[EDGE_INSN: B:42:0x0093->B:36:0x0093 ?: BREAK  , SYNTHETIC] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes r10) {
                /*
                    Method dump skipped, instructions count: 253
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$checkBandDisclaimerAcceptanceOnServer$1.onSuccess(com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes):void");
            }
        }, NavigationConstants.MAP_NAV_DISCLAIMER);
    }

    public final void checkNavigationDisclaimerAcceptance(@NotNull final String version, @NotNull final Function3<? super String, ? super Boolean, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getAcceptedLegalDoc(new CoveApiListener<LegalDocsAcceptedListRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$checkNavigationDisclaimerAcceptance$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "checkNavigationDisclaimerAcceptance onError");
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<String, Boolean, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Boolean bool = Boolean.FALSE;
                        function3.invoke(msg, bool, bool);
                        return;
                    }
                    Function3<String, Boolean, Boolean, Unit> function32 = result;
                    String string = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Boolean bool2 = Boolean.FALSE;
                    function32.invoke(string, bool2, bool2);
                    return;
                }
                Function3<String, Boolean, Boolean, Unit> function33 = result;
                String string2 = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                Boolean bool3 = Boolean.FALSE;
                function33.invoke(string2, bool3, bool3);
            }

            /* JADX WARN: Removed duplicated region for block: B:37:0x0074 A[EDGE_INSN: B:37:0x0074->B:31:0x0074 ?: BREAK  , SYNTHETIC] */
            @Override // com.coveiot.coveaccess.CoveApiListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onSuccess(@org.jetbrains.annotations.Nullable com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes r8) {
                /*
                    r7 = this;
                    com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel r0 = com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel.this
                    java.lang.String r0 = com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel.access$getTAG$p(r0)
                    java.lang.String r1 = "checkNavigationDisclaimerAcceptance onSuccess"
                    com.coveiot.utils.utility.LogHelper.d(r0, r1)
                    if (r8 == 0) goto L91
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r0 = r8.getData()
                    if (r0 == 0) goto L91
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Data r8 = r8.getData()
                    java.util.List r8 = r8.getItems()
                    java.lang.String r0 = "legalHistory"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
                    boolean r0 = r8.isEmpty()
                    r1 = 1
                    r0 = r0 ^ r1
                    java.lang.String r2 = ""
                    if (r0 == 0) goto L88
                    java.lang.String r0 = r3
                    boolean r3 = r8 instanceof java.util.Collection
                    r4 = 0
                    if (r3 == 0) goto L39
                    boolean r3 = r8.isEmpty()
                    if (r3 == 0) goto L39
                L37:
                    r1 = r4
                    goto L74
                L39:
                    java.util.Iterator r8 = r8.iterator()
                L3d:
                    boolean r3 = r8.hasNext()
                    if (r3 == 0) goto L37
                    java.lang.Object r3 = r8.next()
                    com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes$Items r3 = (com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes.Items) r3
                    java.lang.String r5 = r3.getType()
                    java.lang.String r6 = "MAP_NAV_DISCLAIMER"
                    boolean r5 = r5.equals(r6)
                    if (r5 == 0) goto L71
                    java.lang.String r5 = r3.medium
                    if (r5 == 0) goto L62
                    int r5 = r5.length()
                    if (r5 != 0) goto L60
                    goto L62
                L60:
                    r5 = r4
                    goto L63
                L62:
                    r5 = r1
                L63:
                    if (r5 == 0) goto L71
                    java.lang.String r3 = r3.getVersion()
                    boolean r3 = r3.equals(r0)
                    if (r3 == 0) goto L71
                    r3 = r1
                    goto L72
                L71:
                    r3 = r4
                L72:
                    if (r3 == 0) goto L3d
                L74:
                    if (r1 == 0) goto L7e
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r2
                    java.lang.Boolean r0 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r0)
                    goto L91
                L7e:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r2
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    java.lang.Boolean r1 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r1)
                    goto L91
                L88:
                    kotlin.jvm.functions.Function3<java.lang.String, java.lang.Boolean, java.lang.Boolean, kotlin.Unit> r8 = r2
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    java.lang.Boolean r1 = java.lang.Boolean.TRUE
                    r8.invoke(r2, r0, r1)
                L91:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$checkNavigationDisclaimerAcceptance$1.onSuccess(com.coveiot.coveaccess.onboarding.model.LegalDocsAcceptedListRes):void");
            }
        }, NavigationConstants.MAP_NAV_DISCLAIMER);
    }

    public final void fetchFavouritePlaces(@NotNull final Function3<? super GetFavouritePlacesRes, ? super String, ? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getFavouritePlaces(new CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$fetchFavouritePlaces$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getFavouritePlaces favPlacesData onError " + new Gson().toJson(coveApiErrorModel));
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function3<GetFavouritePlacesRes, String, Boolean, Unit> function3 = result;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "coveApiErrorModel!!.msg");
                        function3.invoke(null, msg, Boolean.FALSE);
                        return;
                    }
                    Function3<GetFavouritePlacesRes, String, Boolean, Unit> function32 = result;
                    String string = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function32.invoke(null, string, Boolean.FALSE);
                    return;
                }
                Function3<GetFavouritePlacesRes, String, Boolean, Unit> function33 = result;
                String string2 = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function33.invoke(null, string2, Boolean.FALSE);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getFavouritePlaces favPlacesData onSuccess " + new Gson().toJson(getFavouritePlacesRes));
                result.invoke(getFavouritePlacesRes, "", Boolean.TRUE);
            }
        });
    }

    public final void fetchLastTrip(@NotNull final Function2<? super Boolean, ? super FavouritePlace, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.getNavigationLastTrip(new CoveApiListener<CommonResponseGeneric<FavouritePlace>, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$fetchLastTrip$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "fetchLastTrip onError");
                result.invoke(Boolean.FALSE, null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<FavouritePlace> commonResponseGeneric) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "fetchLastTrip onSuccess");
                result.invoke(Boolean.TRUE, commonResponseGeneric != null ? commonResponseGeneric.getData() : null);
            }
        });
    }

    public final void getAppDisclaimer(@NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.getV2RemoteConfiguration("1", new CoveApiListener<SRemoteConfigResponse, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$getAppDisclaimer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                result.invoke(Boolean.FALSE);
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getAppDisclaimer onError");
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable SRemoteConfigResponse sRemoteConfigResponse) {
                String str;
                List<SRemoteConfigResponse.DataBean.LegalBean.Doc> doc;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getAppDisclaimer onSuccess");
                if (sRemoteConfigResponse != null) {
                    if (sRemoteConfigResponse.getData().getLegalBean() == null || (doc = sRemoteConfigResponse.getData().getLegalBean().getDoc()) == null) {
                        return;
                    }
                    int size = doc.size();
                    for (int i = 0; i < size; i++) {
                        if (doc.get(i).getType().equals(NavigationConstants.MAP_NAV_DISCLAIMER)) {
                            String version = doc.get(i).getVersion();
                            Intrinsics.checkNotNullExpressionValue(version, "docBean[i].version");
                            String htmlUrl = doc.get(i).getHtmlUrl();
                            Intrinsics.checkNotNullExpressionValue(htmlUrl, "docBean[i].htmlUrl");
                            String dvcText = doc.get(i).getDvcText();
                            Intrinsics.checkNotNullExpressionValue(dvcText, "docBean[i].dvcText");
                            SessionManager.getInstance(ActivityNavigationDirectionsViewmodel.this.getContext()).saveNavigationDiscliamerData(new Gson().toJson(new NavigationDisclaimerData(version, htmlUrl, dvcText)));
                            result.invoke(Boolean.TRUE);
                            return;
                        }
                    }
                    return;
                }
                result.invoke(Boolean.FALSE);
            }
        });
    }

    @NotNull
    public final Context getContext() {
        return this.f5525a;
    }

    public final void getDisclaimerStatusFromBand(@NotNull final Function3<? super Boolean, ? super Boolean, ? super String, Unit> result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BleApiManager.getInstance(this.f5525a).getBleApi().getData(new GetNavigationDisclaimerRequest(), new DataResultListener() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$getDisclaimerStatusFromBand$1
            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataError(@NotNull BleBaseError error) {
                String str;
                Intrinsics.checkNotNullParameter(error, "error");
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getDisclaimerStatusFromBand onDataError");
                Function3<Boolean, Boolean, String, Unit> function3 = result;
                Boolean bool = Boolean.FALSE;
                function3.invoke(bool, bool, "");
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onDataResponse(@NotNull BleBaseResponse response) {
                String str;
                Intrinsics.checkNotNullParameter(response, "response");
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getDisclaimerStatusFromBand onDataResponse");
                Object responseData = response.getResponseData();
                Intrinsics.checkNotNull(responseData, "null cannot be cast to non-null type com.coveiot.android.bleabstract.response.GetNavigationDisclaimerResponse");
                GetNavigationDisclaimerResponse getNavigationDisclaimerResponse = (GetNavigationDisclaimerResponse) responseData;
                if (getNavigationDisclaimerResponse.isAccepted()) {
                    Function3<Boolean, Boolean, String, Unit> function3 = result;
                    Boolean bool = Boolean.TRUE;
                    function3.invoke(bool, bool, getNavigationDisclaimerResponse.getVersionText());
                    return;
                }
                result.invoke(Boolean.FALSE, Boolean.TRUE, getNavigationDisclaimerResponse.getVersionText());
            }

            @Override // com.coveiot.android.bleabstract.listeners.DataResultListener
            public void onProgressUpdate(@NotNull ProgressData progress) {
                Intrinsics.checkNotNullParameter(progress, "progress");
            }
        });
    }

    public final void getEta(@NotNull AutoSuggestionData autoSuggestionData, @Nullable Location location, int i, @NotNull final Function1<? super DirectionsResponse, Unit> result) {
        Intrinsics.checkNotNullParameter(autoSuggestionData, "autoSuggestionData");
        Intrinsics.checkNotNullParameter(result, "result");
        MapplsDirections.Builder builder = MapplsDirections.builder();
        Intrinsics.checkNotNull(location);
        MapplsDirections.Builder origin = builder.origin(Point.fromLngLat(location.getLongitude(), location.getLatitude()));
        Boolean bool = Boolean.TRUE;
        MapplsDirections build = origin.steps(bool).resource(i == 0 ? DirectionsCriteria.RESOURCE_ROUTE : DirectionsCriteria.RESOURCE_ROUTE_ETA).profile(i == 0 ? "walking" : "driving").annotations(DirectionsCriteria.ANNOTATION_CONGESTION).alternatives(bool).overview("full").destination(autoSuggestionData.getMapplsPin()).deviceId(Settings.Secure.getString(this.f5525a.getContentResolver(), "android_id")).routeRefresh(bool).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder()\n              …outeRefresh(true).build()");
        MapplsDirectionManager.newInstance(build).call(new OnResponseCallback<DirectionsResponse>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$getEta$1
            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onError(int i2, @Nullable String str) {
                String str2;
                str2 = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str2, "getEta onError");
                result.invoke(null);
            }

            @Override // com.mappls.sdk.services.api.OnResponseCallback
            public void onSuccess(@Nullable DirectionsResponse directionsResponse) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "getEta onSuccess");
                result.invoke(directionsResponse);
            }
        });
    }

    public final void saveTrip(@NotNull FavouritePlace favouritePlace, @NotNull final Function1<? super Boolean, Unit> result) {
        Intrinsics.checkNotNullParameter(favouritePlace, "favouritePlace");
        Intrinsics.checkNotNullParameter(result, "result");
        NavigationApi.saveUserNavigationTrip(favouritePlace, new CoveApiListener<CommonResponseGeneric<SavePlaceTrackHistoryRes>, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$saveTrip$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                result.invoke(Boolean.FALSE);
                str = ActivityNavigationDirectionsViewmodel.this.b;
                Log.d(str, "saveTrip : onError " + new Gson().toJson(coveApiErrorModel));
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseGeneric<SavePlaceTrackHistoryRes> commonResponseGeneric) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                Log.d(str, "saveTrip : onSuccess " + new Gson().toJson(commonResponseGeneric));
                result.invoke(Boolean.TRUE);
            }
        });
    }

    public final void updateBandAcceptedDisclaimerOnServer(@NotNull AcceptLegalTermsReq acceptLegalTermsReq, @NotNull final Function2<? super Boolean, ? super String, Unit> result) {
        Intrinsics.checkNotNullParameter(acceptLegalTermsReq, "acceptLegalTermsReq");
        Intrinsics.checkNotNullParameter(result, "result");
        CoveOnboarding.acceptTermsAndConditions(acceptLegalTermsReq, new CoveApiListener<AcceptLegalTermsRes, CoveApiErrorModel>() { // from class: com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel$updateBandAcceptedDisclaimerOnServer$1
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "updateBandAcceptedDisclaimerOnServer onError ");
                if (coveApiErrorModel != null) {
                    if (coveApiErrorModel.getMsg() != null) {
                        Function2<Boolean, String, Unit> function2 = result;
                        Boolean bool = Boolean.FALSE;
                        String msg = coveApiErrorModel.getMsg();
                        Intrinsics.checkNotNullExpressionValue(msg, "`object`.msg");
                        function2.invoke(bool, msg);
                        return;
                    }
                    Function2<Boolean, String, Unit> function22 = result;
                    Boolean bool2 = Boolean.FALSE;
                    String string = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.some_thing_went_wrong)");
                    function22.invoke(bool2, string);
                    return;
                }
                Function2<Boolean, String, Unit> function23 = result;
                Boolean bool3 = Boolean.FALSE;
                String string2 = ActivityNavigationDirectionsViewmodel.this.getContext().getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.some_thing_went_wrong)");
                function23.invoke(bool3, string2);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable AcceptLegalTermsRes acceptLegalTermsRes) {
                String str;
                str = ActivityNavigationDirectionsViewmodel.this.b;
                LogHelper.d(str, "updateBandAcceptedDisclaimerOnServer onSuccess " + new Gson().toJson(acceptLegalTermsRes) + ' ');
                if (acceptLegalTermsRes != null) {
                    if (acceptLegalTermsRes.getStatus().equals(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        result.invoke(Boolean.TRUE, "");
                        return;
                    } else {
                        result.invoke(Boolean.FALSE, "");
                        return;
                    }
                }
                result.invoke(Boolean.FALSE, "");
            }
        });
    }
}
