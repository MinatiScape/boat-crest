package com.coveiot.android.sportsnotificationsdk;

import androidx.core.app.NotificationCompat;
import com.coveiot.android.sportsnotificationsdk.models.livematch.GetLiveMatchRes;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.GetMatchListRes;
import com.coveiot.android.sportsnotificationsdk.models.scorecard.GetScorecardRes;
import com.coveiot.android.sportsnotificationsdk.network.SportErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportUtil;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiClient;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiErrorModel;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiListener;
import com.coveiot.android.sportsnotificationsdk.network.SportsApiService;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo.SMatchInfoResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.SMatchListResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.SMatchStatsResponse;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/SportsNotificationApiManager;", "", "<init>", "()V", "Companion", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class SportsNotificationApiManager {
    @NotNull
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001c\u0010\u001dJR\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0004\u0012\u00020\r0\nJB\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0004\u0012\u00020\r0\nJ2\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0004\u0012\u00020\r0\nJ:\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0004\u0012\u00020\r0\nJ2\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b\u0012\u0004\u0012\u00020\r0\nJ2\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u000b\u0012\u0004\u0012\u00020\r0\nJB\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000b\u0012\u0004\u0012\u00020\r0\nJJ\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u000b\u0012\u0004\u0012\u00020\r0\nJ2\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u000b\u0012\u0004\u0012\u00020\r0\nJ2\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00052\u001a\u0010\u000e\u001a\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u000b\u0012\u0004\u0012\u00020\r0\n¨\u0006\u001e"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/SportsNotificationApiManager$Companion;", "", "", NotificationCompat.CATEGORY_STATUS, "format", "", "date", MapplsLMSDbAdapter.KEY_TOKEN, "per_page", "paged", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiListener;", "Lretrofit2/Response;", "Lcom/coveiot/android/sportsnotificationsdk/models/matchlist/GetMatchListRes;", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiErrorModel;", "l", "", "getMatchList", "matchID", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/GetScorecardRes;", "getMatchScoreCard", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/GetLiveMatchRes;", "getLiveMatchResponse", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchlist/SMatchListResponse;", "getSoccerMatchList", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchinfo/SMatchInfoResponse;", "getSoccerMatchInfo", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchstats/SMatchStatsResponse;", "getSoccerMatchStats", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void getLiveMatchResponse(int i, @NotNull String token, @NotNull final SportsApiListener<Response<GetLiveMatchRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetLiveMatchRes> liveMatch = service.getSportsClientService().getLiveMatch(i, token);
            if (liveMatch == null) {
                return;
            }
            liveMatch.enqueue(new Callback<GetLiveMatchRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getLiveMatchResponse$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetLiveMatchRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetLiveMatchRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetLiveMatchRes> call, @NotNull Response<GetLiveMatchRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getMatchList(int i, int i2, @NotNull String date, @NotNull String token, int i3, int i4, @NotNull final SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetMatchListRes> matchList = service.getSportsClientService().getMatchList(i, i2, date, token, i3, i4);
            if (matchList == null) {
                return;
            }
            matchList.enqueue(new Callback<GetMatchListRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getMatchList$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetMatchListRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetMatchListRes> call, @NotNull Response<GetMatchListRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getMatchScoreCard(int i, @NotNull String token, @NotNull final SportsApiListener<Response<GetScorecardRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetScorecardRes> matchScoreCard = service.getSportsClientService().getMatchScoreCard(i, token);
            if (matchScoreCard == null) {
                return;
            }
            matchScoreCard.enqueue(new Callback<GetScorecardRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getMatchScoreCard$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetScorecardRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetScorecardRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetScorecardRes> call, @NotNull Response<GetScorecardRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getSoccerMatchInfo(int i, @NotNull String token, @NotNull final SportsApiListener<Response<SMatchInfoResponse>, SportsApiErrorModel> l) {
            SportsApiService sportsClientService;
            Call<SMatchInfoResponse> soccerMatchInfo;
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            if (service == null || (sportsClientService = service.getSportsClientService()) == null || (soccerMatchInfo = sportsClientService.getSoccerMatchInfo(i, token)) == null) {
                return;
            }
            soccerMatchInfo.enqueue(new Callback<SMatchInfoResponse>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getSoccerMatchInfo$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<SMatchInfoResponse> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<SMatchInfoResponse>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<SMatchInfoResponse> call, @NotNull Response<SMatchInfoResponse> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getSoccerMatchList(@NotNull String date, @NotNull String token, int i, int i2, @NotNull final SportsApiListener<Response<SMatchListResponse>, SportsApiErrorModel> l) {
            SportsApiService sportsClientService;
            Call<SMatchListResponse> soccerMatchList;
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            if (service == null || (sportsClientService = service.getSportsClientService()) == null || (soccerMatchList = sportsClientService.getSoccerMatchList(date, token, i, i2)) == null) {
                return;
            }
            soccerMatchList.enqueue(new Callback<SMatchListResponse>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getSoccerMatchList$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<SMatchListResponse> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<SMatchListResponse>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<SMatchListResponse> call, @NotNull Response<SMatchListResponse> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getSoccerMatchStats(int i, @NotNull String token, @NotNull final SportsApiListener<Response<SMatchStatsResponse>, SportsApiErrorModel> l) {
            SportsApiService sportsClientService;
            Call<SMatchStatsResponse> soccerMatchStats;
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            if (service == null || (sportsClientService = service.getSportsClientService()) == null || (soccerMatchStats = sportsClientService.getSoccerMatchStats(i, token)) == null) {
                return;
            }
            soccerMatchStats.enqueue(new Callback<SMatchStatsResponse>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getSoccerMatchStats$1
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<SMatchStatsResponse> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<SMatchStatsResponse>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<SMatchStatsResponse> call, @NotNull Response<SMatchStatsResponse> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getMatchList(@NotNull String date, @NotNull String token, int i, int i2, @NotNull final SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetMatchListRes> matchList = service.getSportsClientService().getMatchList(date, token, i, i2);
            if (matchList == null) {
                return;
            }
            matchList.enqueue(new Callback<GetMatchListRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getMatchList$2
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetMatchListRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetMatchListRes> call, @NotNull Response<GetMatchListRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getSoccerMatchList(@NotNull String date, @NotNull String token, int i, int i2, int i3, @NotNull final SportsApiListener<Response<SMatchListResponse>, SportsApiErrorModel> l) {
            SportsApiService sportsClientService;
            Call<SMatchListResponse> soccerMatchList;
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            if (service == null || (sportsClientService = service.getSportsClientService()) == null || (soccerMatchList = sportsClientService.getSoccerMatchList(date, token, i, i2, i3)) == null) {
                return;
            }
            soccerMatchList.enqueue(new Callback<SMatchListResponse>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getSoccerMatchList$2
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<SMatchListResponse> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<SMatchListResponse>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<SMatchListResponse> call, @NotNull Response<SMatchListResponse> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getMatchList(int i, @NotNull String token, @NotNull final SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetMatchListRes> matchList = service.getSportsClientService().getMatchList(i, token);
            if (matchList == null) {
                return;
            }
            matchList.enqueue(new Callback<GetMatchListRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getMatchList$3
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetMatchListRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetMatchListRes> call, @NotNull Response<GetMatchListRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }

        public final void getMatchList(int i, @NotNull String date, @NotNull String token, @NotNull final SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> l) {
            Intrinsics.checkNotNullParameter(date, "date");
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(l, "l");
            SportsApiClient service = SportsApiClient.Companion.getService();
            Intrinsics.checkNotNull(service);
            Call<GetMatchListRes> matchList = service.getSportsClientService().getMatchList(i, date, token);
            if (matchList == null) {
                return;
            }
            matchList.enqueue(new Callback<GetMatchListRes>() { // from class: com.coveiot.android.sportsnotificationsdk.SportsNotificationApiManager$Companion$getMatchList$4
                @Override // retrofit2.Callback
                public void onFailure(@NotNull Call<GetMatchListRes> call, @NotNull Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    SportsApiListener<Response<GetMatchListRes>, SportsApiErrorModel> sportsApiListener = l;
                    SportErrorModel buildErrorObject = SportUtil.buildErrorObject(t);
                    Intrinsics.checkNotNullExpressionValue(buildErrorObject, "buildErrorObject(t)");
                    sportsApiListener.onError(buildErrorObject);
                }

                @Override // retrofit2.Callback
                public void onResponse(@NotNull Call<GetMatchListRes> call, @NotNull Response<GetMatchListRes> response) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        l.onSuccess(response);
                        return;
                    }
                    SportsApiErrorModel sportsApiErrorModel = new SportsApiErrorModel(response.code());
                    ResponseBody errorBody = response.errorBody();
                    sportsApiErrorModel.setResponse(errorBody == null ? null : errorBody.toString());
                    l.onError(sportsApiErrorModel);
                }
            });
        }
    }
}
