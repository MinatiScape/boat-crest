package com.coveiot.coveaccess.navigation;

import androidx.annotation.NonNull;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseGeneric;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class NavigationApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<CommonResponseGeneric<SaveFavouritePlaceRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6654a;

        public a(CoveApiListener coveApiListener) {
            this.f6654a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<SaveFavouritePlaceRes>> call, Throwable th) {
            this.f6654a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<SaveFavouritePlaceRes>> call, Response<CommonResponseGeneric<SaveFavouritePlaceRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6654a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6654a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6654a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<CommonResponseGeneric<SaveFavouritePlaceRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6655a;

        public b(CoveApiListener coveApiListener) {
            this.f6655a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<SaveFavouritePlaceRes>> call, Throwable th) {
            this.f6655a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<SaveFavouritePlaceRes>> call, Response<CommonResponseGeneric<SaveFavouritePlaceRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6655a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6655a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6655a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<CommonResponseGeneric<GetFavouritePlacesRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6656a;

        public c(CoveApiListener coveApiListener) {
            this.f6656a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<GetFavouritePlacesRes>> call, Throwable th) {
            this.f6656a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<GetFavouritePlacesRes>> call, Response<CommonResponseGeneric<GetFavouritePlacesRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6656a.onSuccess(response.body().getData());
                        return;
                    } else {
                        this.f6656a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6656a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<CommonResponseGeneric<SavePlaceTrackHistoryRes>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6657a;

        public d(CoveApiListener coveApiListener) {
            this.f6657a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<SavePlaceTrackHistoryRes>> call, Throwable th) {
            this.f6657a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<SavePlaceTrackHistoryRes>> call, Response<CommonResponseGeneric<SavePlaceTrackHistoryRes>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6657a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6657a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6657a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<CommonResponseGeneric<FavouritePlace>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6658a;

        public e(CoveApiListener coveApiListener) {
            this.f6658a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CommonResponseGeneric<FavouritePlace>> call, Throwable th) {
            this.f6658a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CommonResponseGeneric<FavouritePlace>> call, Response<CommonResponseGeneric<FavouritePlace>> response) {
            int code = response.code();
            if (response.isSuccessful()) {
                if (response.body() != null) {
                    if (response.body().getStatus().equalsIgnoreCase(CoveApiConstants.RESPONSE_STATUS_VALUE_OK)) {
                        this.f6658a.onSuccess(response.body());
                        return;
                    } else {
                        this.f6658a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
                        return;
                    }
                }
                return;
            }
            this.f6658a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(Map<String, String> map, @NonNull CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getFavouritePlaces(map).enqueue(new c(coveApiListener));
    }

    public static void b(Map<String, String> map, FavouritePlace favouritePlace, @NonNull CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().saveFavouritePlace(map, favouritePlace).enqueue(new a(coveApiListener));
    }

    public static void c(Map<String, String> map, Object obj, FavouritePlace favouritePlace, @NonNull CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().editFavouritePlace(map, favouritePlace, obj).enqueue(new b(coveApiListener));
    }

    public static void d(Map<String, String> map, @NonNull CoveApiListener<CommonResponseGeneric<FavouritePlace>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getNavigationLastTrip(map).enqueue(new e(coveApiListener));
    }

    public static void e(Map<String, String> map, FavouritePlace favouritePlace, @NonNull CoveApiListener<CommonResponseGeneric<SavePlaceTrackHistoryRes>, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().saveUserNavigationTrip(map, favouritePlace).enqueue(new d(coveApiListener));
    }

    public static void editFavouritePlace(Object obj, FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        c(CoveApi.getCustomHeaders(), obj, favouritePlace, coveApiListener);
    }

    public static void getFavouritePlaces(CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel> coveApiListener) {
        a(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void getNavigationLastTrip(CoveApiListener<CommonResponseGeneric<FavouritePlace>, CoveApiErrorModel> coveApiListener) {
        d(CoveApi.getCustomHeaders(), coveApiListener);
    }

    public static void saveFavouritePlace(FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        b(CoveApi.getCustomHeaders(), favouritePlace, coveApiListener);
    }

    public static void saveUserNavigationTrip(FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SavePlaceTrackHistoryRes>, CoveApiErrorModel> coveApiListener) {
        e(CoveApi.getCustomHeaders(), favouritePlace, coveApiListener);
    }

    public static void editFavouritePlace(HashMap<String, String> hashMap, Object obj, FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        c(CoveUtil.getRevisedHeaders(hashMap), obj, favouritePlace, coveApiListener);
    }

    public static void getFavouritePlaces(HashMap<String, String> hashMap, CoveApiListener<GetFavouritePlacesRes, CoveApiErrorModel> coveApiListener) {
        a(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void getNavigationLastTrip(HashMap<String, String> hashMap, CoveApiListener<CommonResponseGeneric<FavouritePlace>, CoveApiErrorModel> coveApiListener) {
        d(CoveUtil.getRevisedHeaders(hashMap), coveApiListener);
    }

    public static void saveFavouritePlace(HashMap<String, String> hashMap, FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SaveFavouritePlaceRes>, CoveApiErrorModel> coveApiListener) {
        b(CoveUtil.getRevisedHeaders(hashMap), favouritePlace, coveApiListener);
    }

    public static void saveUserNavigationTrip(HashMap<String, String> hashMap, FavouritePlace favouritePlace, CoveApiListener<CommonResponseGeneric<SavePlaceTrackHistoryRes>, CoveApiErrorModel> coveApiListener) {
        e(CoveUtil.getRevisedHeaders(hashMap), favouritePlace, coveApiListener);
    }
}
