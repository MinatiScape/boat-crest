package com.coveiot.android.sportsnotificationsdk.network;

import androidx.core.app.NotificationCompat;
import com.coveiot.android.sportsnotificationsdk.models.accesstoken.AccessTokenResponse;
import com.coveiot.android.sportsnotificationsdk.models.livematch.GetLiveMatchRes;
import com.coveiot.android.sportsnotificationsdk.models.matchinfo.GetMatchInfoRes;
import com.coveiot.android.sportsnotificationsdk.models.matchlist.GetMatchListRes;
import com.coveiot.android.sportsnotificationsdk.models.scorecard.GetScorecardRes;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchinfo.SMatchInfoResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchlist.SMatchListResponse;
import com.coveiot.android.sportsnotificationsdk.soccer.models.matchstats.SMatchStatsResponse;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J&\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0005\u001a\u00020\u0004H'JN\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00062\b\b\u0001\u0010\t\u001a\u00020\u00022\b\b\u0001\u0010\n\u001a\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\u0002H'J&\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00062\b\b\u0001\u0010\t\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'J:\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00062\b\b\u0001\u0010\t\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\u0002H'J0\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u00062\b\b\u0001\u0010\t\u001a\u00020\u00022\b\b\u0001\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u0004H'J&\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'J&\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'J&\u0010\u0017\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'J:\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\u0002H'JD\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0018\u00010\u00062\b\b\u0001\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u00042\b\b\u0001\u0010\r\u001a\u00020\u00022\b\b\u0001\u0010\u000e\u001a\u00020\u00022\b\b\u0001\u0010\t\u001a\u00020\u0002H'J&\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0018\u00010\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'J&\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u00062\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\f\u001a\u00020\u0004H'¨\u0006\u001e"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiService;", "", "", "access_key", "", "secret_key", "Lretrofit2/Call;", "Lcom/coveiot/android/sportsnotificationsdk/models/accesstoken/AccessTokenResponse;", "getAccessToken", NotificationCompat.CATEGORY_STATUS, "format", "date", MapplsLMSDbAdapter.KEY_TOKEN, "per_page", "paged", "Lcom/coveiot/android/sportsnotificationsdk/models/matchlist/GetMatchListRes;", "getMatchList", "MATCH_ID", "Lcom/coveiot/android/sportsnotificationsdk/models/scorecard/GetScorecardRes;", "getMatchScoreCard", "Lcom/coveiot/android/sportsnotificationsdk/models/livematch/GetLiveMatchRes;", "getLiveMatch", "Lcom/coveiot/android/sportsnotificationsdk/models/matchinfo/GetMatchInfoRes;", "getLiveMatchInfo", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchlist/SMatchListResponse;", "getSoccerMatchList", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchinfo/SMatchInfoResponse;", "getSoccerMatchInfo", "Lcom/coveiot/android/sportsnotificationsdk/soccer/models/matchstats/SMatchStatsResponse;", "getSoccerMatchStats", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public interface SportsApiService {
    @GET(" /v2/auth/")
    @Nullable
    Call<AccessTokenResponse> getAccessToken(@Path("access_key") int i, @NotNull @Query("secret_key") String str);

    @GET("v2/matches/{MATCH_ID}/live")
    @Nullable
    Call<GetLiveMatchRes> getLiveMatch(@Path("MATCH_ID") int i, @NotNull @Query("token") String str);

    @GET("v2/matches/{MATCH_ID}/info")
    @Nullable
    Call<GetMatchInfoRes> getLiveMatchInfo(@Path("MATCH_ID") int i, @NotNull @Query("token") String str);

    @GET("v2/matches/")
    @Nullable
    Call<GetMatchListRes> getMatchList(@Query("status") int i, @Query("format") int i2, @NotNull @Query("date") String str, @NotNull @Query("token") String str2, @Query("per_page") int i3, @Query("paged") int i4);

    @GET("v2/matches/")
    @Nullable
    Call<GetMatchListRes> getMatchList(@Query("status") int i, @NotNull @Query("token") String str);

    @GET("v2/matches/")
    @Nullable
    Call<GetMatchListRes> getMatchList(@Query("status") int i, @NotNull @Query("date") String str, @NotNull @Query("token") String str2);

    @GET("v2/matches/")
    @Nullable
    Call<GetMatchListRes> getMatchList(@NotNull @Query("date") String str, @NotNull @Query("token") String str2, @Query("per_page") int i, @Query("paged") int i2);

    @GET("v2/matches/{MATCH_ID}/scorecard")
    @Nullable
    Call<GetScorecardRes> getMatchScoreCard(@Path("MATCH_ID") int i, @NotNull @Query("token") String str);

    @GET("matches/{MATCH_ID}/info")
    @Nullable
    Call<SMatchInfoResponse> getSoccerMatchInfo(@Path("MATCH_ID") int i, @NotNull @Query("token") String str);

    @GET("matches/")
    @Nullable
    Call<SMatchListResponse> getSoccerMatchList(@NotNull @Query("date") String str, @NotNull @Query("token") String str2, @Query("per_page") int i, @Query("paged") int i2);

    @GET("matches/")
    @Nullable
    Call<SMatchListResponse> getSoccerMatchList(@NotNull @Query("date") String str, @NotNull @Query("token") String str2, @Query("per_page") int i, @Query("paged") int i2, @Query("status") int i3);

    @GET("matches/{MATCH_ID}/statsv2")
    @Nullable
    Call<SMatchStatsResponse> getSoccerMatchStats(@Path("MATCH_ID") int i, @NotNull @Query("token") String str);
}
