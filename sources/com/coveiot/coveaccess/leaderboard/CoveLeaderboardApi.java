package com.coveiot.coveaccess.leaderboard;

import com.coveiot.coveaccess.ApiException;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.coveaccess.leaderboard.model.AddressModel;
import com.coveiot.coveaccess.leaderboard.model.AddressReq;
import com.coveiot.coveaccess.leaderboard.model.AllBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.BadgesCategoryModel;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.MyBadgesModel;
import com.coveiot.coveaccess.leaderboard.model.MyRankModel;
import com.coveiot.coveaccess.leaderboard.model.RankHistoryModel;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.coveaccess.leaderboard.model.TopRankModel;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.RankDetail;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public final class CoveLeaderboardApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<TopRankModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6623a;

        public a(CoveApiListener coveApiListener) {
            this.f6623a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<TopRankModel> call, Throwable th) {
            this.f6623a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<TopRankModel> call, Response<TopRankModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6623a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6623a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<TopRankModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6624a;

        public b(CoveApiListener coveApiListener) {
            this.f6624a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<TopRankModel> call, Throwable th) {
            this.f6624a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<TopRankModel> call, Response<TopRankModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6624a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6624a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<BadgesCategoryModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6625a;

        public c(CoveApiListener coveApiListener) {
            this.f6625a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<BadgesCategoryModel> call, Throwable th) {
            this.f6625a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<BadgesCategoryModel> call, Response<BadgesCategoryModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6625a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6625a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<AllBadgesModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6626a;

        public d(CoveApiListener coveApiListener) {
            this.f6626a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AllBadgesModel> call, Throwable th) {
            this.f6626a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AllBadgesModel> call, Response<AllBadgesModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6626a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6626a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<AllBadgesModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6627a;

        public e(CoveApiListener coveApiListener) {
            this.f6627a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AllBadgesModel> call, Throwable th) {
            this.f6627a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AllBadgesModel> call, Response<AllBadgesModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6627a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6627a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<MyBadgesModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6628a;

        public f(CoveApiListener coveApiListener) {
            this.f6628a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<MyBadgesModel> call, Throwable th) {
            this.f6628a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<MyBadgesModel> call, Response<MyBadgesModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6628a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6628a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<MyRankModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6629a;

        public g(CoveApiListener coveApiListener) {
            this.f6629a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<MyRankModel> call, Throwable th) {
            this.f6629a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<MyRankModel> call, Response<MyRankModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6629a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6629a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<RankDetail> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6630a;

        public h(CoveApiListener coveApiListener) {
            this.f6630a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<RankDetail> call, Throwable th) {
            this.f6630a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<RankDetail> call, Response<RankDetail> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6630a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6630a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<MyRankModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6631a;

        public i(CoveApiListener coveApiListener) {
            this.f6631a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<MyRankModel> call, Throwable th) {
            this.f6631a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<MyRankModel> call, Response<MyRankModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6631a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6631a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<AddressModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6632a;

        public j(CoveApiListener coveApiListener) {
            this.f6632a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<AddressModel> call, Throwable th) {
            this.f6632a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<AddressModel> call, Response<AddressModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6632a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6632a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class k implements Callback<RankHistoryModel> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6633a;

        public k(CoveApiListener coveApiListener) {
            this.f6633a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<RankHistoryModel> call, Throwable th) {
            this.f6633a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<RankHistoryModel> call, Response<RankHistoryModel> response) {
            if (CoveUtil.isSuccessResponse(response)) {
                this.f6633a.onSuccess(response.body());
                return;
            }
            int code = response.code();
            this.f6633a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getAllBadges(map).enqueue(new e(coveApiListener));
    }

    public static void b(CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener, Map<String, String> map, String str) {
        CoveApi.getService().getAllBadgesByCategories(map, str).enqueue(new d(coveApiListener));
    }

    public static void c(AddressReq addressReq, CoveApiListener<AddressModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (addressReq != null) {
            CoveApi.getService().saveLocation(map, addressReq).enqueue(new j(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void d(RankType rankType, FilterType filterType, CoveApiListener<RankHistoryModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getRankHistory(map, rankType.getRankType(), filterType.getFilterType()).enqueue(new k(coveApiListener));
    }

    public static void e(String str, CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getMyRank(map, str).enqueue(new i(coveApiListener));
    }

    public static void f(String str, String str2, String str3, String str4, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBuddiesTopRank(map, str, str2, str3, str4).enqueue(new b(coveApiListener));
    }

    public static void g(CoveApiListener<BadgesCategoryModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getBadgesCategory(map).enqueue(new c(coveApiListener));
    }

    public static void getAllBadges(CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAllBadgesByCategory(CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener, String str) {
        b(coveApiListener, CoveApi.getCustomHeaders(), str);
    }

    public static void getBadgesCategories(CoveApiListener<BadgesCategoryModel, CoveApiErrorModel> coveApiListener) {
        g(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getBuddiesTopRank(String str, String str2, String str3, String str4, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener) {
        f(str, str2, str3, str4, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getMyBadges(CoveApiListener<MyBadgesModel, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getMyRank(CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener) {
        k(coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getMyRankLocality(String str, CoveApiListener<RankDetail, CoveApiErrorModel> coveApiListener) {
        h(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getRankHistory(RankType rankType, FilterType filterType, CoveApiListener<RankHistoryModel, CoveApiErrorModel> coveApiListener) {
        d(rankType, filterType, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getTopRank(String str, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener) {
        j(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void h(String str, CoveApiListener<RankDetail, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getRankDetails(map, str).enqueue(new h(coveApiListener));
    }

    public static void i(CoveApiListener<MyBadgesModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getMyBadges(map).enqueue(new f(coveApiListener));
    }

    public static void j(String str, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (!CoveUtil.isEmpty(str)) {
            CoveApi.getService().getTopRank(map, str).enqueue(new a(coveApiListener));
            return;
        }
        throw new ApiException(ErrorConstants.API_PARAM_ERR_GENERIC);
    }

    public static void k(CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getMyRank(map).enqueue(new g(coveApiListener));
    }

    public static void saveLocation(AddressReq addressReq, CoveApiListener<AddressModel, CoveApiErrorModel> coveApiListener) {
        c(addressReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getAllBadges(HashMap<String, String> hashMap, CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener) {
        a(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getAllBadgesByCategory(HashMap<String, String> hashMap, CoveApiListener<AllBadgesModel, CoveApiErrorModel> coveApiListener, String str) {
        b(coveApiListener, CoveUtil.getRevisedHeaders(hashMap), str);
    }

    public static void getBadgesCategories(HashMap<String, String> hashMap, CoveApiListener<BadgesCategoryModel, CoveApiErrorModel> coveApiListener) {
        g(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getBuddiesTopRank(HashMap<String, String> hashMap, String str, String str2, String str3, String str4, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener) {
        f(str, str2, str3, str4, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMyBadges(HashMap<String, String> hashMap, CoveApiListener<MyBadgesModel, CoveApiErrorModel> coveApiListener) {
        i(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMyRank(HashMap<String, String> hashMap, CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener) {
        k(coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMyRankLocality(HashMap<String, String> hashMap, String str, CoveApiListener<RankDetail, CoveApiErrorModel> coveApiListener) {
        h(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getRankHistory(HashMap<String, String> hashMap, RankType rankType, FilterType filterType, CoveApiListener<RankHistoryModel, CoveApiErrorModel> coveApiListener) {
        d(rankType, filterType, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getTopRank(HashMap<String, String> hashMap, String str, CoveApiListener<TopRankModel, CoveApiErrorModel> coveApiListener) {
        j(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void saveLocation(HashMap<String, String> hashMap, AddressReq addressReq, CoveApiListener<AddressModel, CoveApiErrorModel> coveApiListener) {
        c(addressReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMyRank(String str, CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener) {
        e(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getMyRank(HashMap<String, String> hashMap, String str, CoveApiListener<MyRankModel, CoveApiErrorModel> coveApiListener) {
        e(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
