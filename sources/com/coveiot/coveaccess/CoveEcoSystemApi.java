package com.coveiot.coveaccess;

import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.ecosystem.GetNearByGymSpaReq;
import com.coveiot.coveaccess.ecosystem.GymSpaRes;
import com.coveiot.coveaccess.ecosystem.LatlongData;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SGymSpaResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class CoveEcoSystemApi {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SGymSpaResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6259a;

        public a(CoveApiListener coveApiListener) {
            this.f6259a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGymSpaResponse> call, Throwable th) {
            this.f6259a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGymSpaResponse> call, Response<SGymSpaResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                SGymSpaResponse body = response.body();
                if (body.getData() != null && !CoveUtil.isEmpty(body.getData().getItems())) {
                    ArrayList arrayList = new ArrayList();
                    for (SGymSpaResponse.GymSpaResponseModel.Item item : body.getData().getItems()) {
                        GymSpaRes.Item item2 = new GymSpaRes.Item();
                        item2.setId(item.getId());
                        item2.setPlaceId(item.getPlaceId());
                        item2.setIcon(item.getIcon());
                        item2.setName(item.getName());
                        item2.setRating(item.getRating());
                        item2.setFormattedPhoneNumber(item.getFormattedPhoneNumber());
                        item2.setInternationalPhoneNumber(item.getInternationalPhoneNumber());
                        item2.setWebsite(item.getWebsite());
                        item2.setVicinity(item.getVicinity());
                        item2.setTypes(item.getTypes());
                        GymSpaRes.Item.Location location = new GymSpaRes.Item.Location();
                        location.setCoordinates(item.getLocation().getCoordinates());
                        location.setType(item.getLocation().getType());
                        item2.setLocation(location);
                        ArrayList arrayList2 = new ArrayList();
                        if (item.getPhotos() != null) {
                            for (SGymSpaResponse.GymSpaResponseModel.Item.Photo photo : item.getPhotos()) {
                                GymSpaRes.Item.Photo photo2 = new GymSpaRes.Item.Photo();
                                photo2.setDownloaded(photo.getDownloaded());
                                photo2.setHeight(photo.getHeight());
                                photo2.setHtmlAttributions(photo.getHtmlAttributions());
                                photo2.setName(photo.getName());
                                photo2.setPhotoReference(photo.getPhotoReference());
                                photo2.setWidth(photo.getWidth());
                                arrayList2.add(photo2);
                            }
                        }
                        item2.setPhotos(arrayList2);
                        ArrayList arrayList3 = new ArrayList();
                        GymSpaRes.Item.OpeningHours openingHours = new GymSpaRes.Item.OpeningHours();
                        if (item.getOpeningHours() != null) {
                            openingHours.setOpenNow(item.getOpeningHours().getOpenNow());
                            if (item.getOpeningHours().getPeriods() != null) {
                                for (SGymSpaResponse.GymSpaResponseModel.Item.OpeningHours.Period period : item.getOpeningHours().getPeriods()) {
                                    GymSpaRes.Item.OpeningHours.Period.Close close = new GymSpaRes.Item.OpeningHours.Period.Close();
                                    close.setDay(period.getClose().getDay());
                                    close.setTime(period.getClose().getTime());
                                    GymSpaRes.Item.OpeningHours.Period.Open open = new GymSpaRes.Item.OpeningHours.Period.Open();
                                    open.setDay(period.getOpen().getDay());
                                    open.setTime(period.getOpen().getTime());
                                    GymSpaRes.Item.OpeningHours.Period period2 = new GymSpaRes.Item.OpeningHours.Period();
                                    period2.setClose(close);
                                    period2.setOpen(open);
                                    arrayList3.add(period2);
                                }
                            }
                        }
                        openingHours.setPeriods(arrayList3);
                        item2.setOpeningHours(openingHours);
                        arrayList.add(item2);
                    }
                    GymSpaRes gymSpaRes = new GymSpaRes();
                    gymSpaRes.setItems(arrayList);
                    gymSpaRes.setItemsPerPage(body.getData().getItemsPerPage());
                    gymSpaRes.setPageIndex(body.getData().getPageIndex());
                    this.f6259a.onSuccess(gymSpaRes);
                    return;
                }
                this.f6259a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
                return;
            }
            this.f6259a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<SGymSpaResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6260a;

        public b(CoveApiListener coveApiListener) {
            this.f6260a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SGymSpaResponse> call, Throwable th) {
            this.f6260a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SGymSpaResponse> call, Response<SGymSpaResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                SGymSpaResponse body = response.body();
                if (body.getData() != null && !CoveUtil.isEmpty(body.getData().getItems())) {
                    ArrayList arrayList = new ArrayList();
                    for (SGymSpaResponse.GymSpaResponseModel.Item item : body.getData().getItems()) {
                        GymSpaRes.Item item2 = new GymSpaRes.Item();
                        item2.setId(item.getId());
                        item2.setPlaceId(item.getPlaceId());
                        item2.setIcon(item.getIcon());
                        item2.setName(item.getName());
                        item2.setRating(item.getRating());
                        item2.setFormattedPhoneNumber(item.getFormattedPhoneNumber());
                        item2.setInternationalPhoneNumber(item.getInternationalPhoneNumber());
                        item2.setWebsite(item.getWebsite());
                        item2.setVicinity(item.getVicinity());
                        item2.setTypes(item.getTypes());
                        GymSpaRes.Item.Location location = new GymSpaRes.Item.Location();
                        location.setCoordinates(item.getLocation().getCoordinates());
                        location.setType(item.getLocation().getType());
                        item2.setLocation(location);
                        ArrayList arrayList2 = new ArrayList();
                        if (item.getPhotos() != null) {
                            for (SGymSpaResponse.GymSpaResponseModel.Item.Photo photo : item.getPhotos()) {
                                GymSpaRes.Item.Photo photo2 = new GymSpaRes.Item.Photo();
                                photo2.setDownloaded(photo.getDownloaded());
                                photo2.setHeight(photo.getHeight());
                                photo2.setHtmlAttributions(photo.getHtmlAttributions());
                                photo2.setName(photo.getName());
                                photo2.setPhotoReference(photo.getPhotoReference());
                                photo2.setWidth(photo.getWidth());
                            }
                            item2.setPhotos(arrayList2);
                            ArrayList arrayList3 = new ArrayList();
                            GymSpaRes.Item.OpeningHours openingHours = new GymSpaRes.Item.OpeningHours();
                            if (item.getOpeningHours() != null) {
                                openingHours.setOpenNow(item.getOpeningHours().getOpenNow());
                                if (item.getOpeningHours().getPeriods() != null) {
                                    for (SGymSpaResponse.GymSpaResponseModel.Item.OpeningHours.Period period : item.getOpeningHours().getPeriods()) {
                                        GymSpaRes.Item.OpeningHours.Period.Close close = new GymSpaRes.Item.OpeningHours.Period.Close();
                                        close.setDay(period.getClose().getDay());
                                        close.setTime(period.getClose().getTime());
                                        GymSpaRes.Item.OpeningHours.Period.Open open = new GymSpaRes.Item.OpeningHours.Period.Open();
                                        open.setDay(period.getOpen().getDay());
                                        open.setTime(period.getOpen().getTime());
                                        GymSpaRes.Item.OpeningHours.Period period2 = new GymSpaRes.Item.OpeningHours.Period();
                                        period2.setClose(close);
                                        period2.setOpen(open);
                                        arrayList3.add(period2);
                                    }
                                }
                            }
                            openingHours.setPeriods(arrayList3);
                            item2.setOpeningHours(openingHours);
                            arrayList.add(item2);
                        }
                    }
                    GymSpaRes gymSpaRes = new GymSpaRes();
                    gymSpaRes.setItems(arrayList);
                    gymSpaRes.setItemsPerPage(body.getData().getItemsPerPage());
                    gymSpaRes.setPageIndex(body.getData().getPageIndex());
                    this.f6260a.onSuccess(gymSpaRes);
                    return;
                }
                this.f6260a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
                return;
            }
            this.f6260a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(response.code()), response.code()));
        }
    }

    public static void a(LatlongData latlongData, int i, String str, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApiService service = CoveApi.getService();
        service.getGymSpaByLocation(map, String.valueOf(latlongData.getLatitude()) + Constants.SEPARATOR_COMMA + String.valueOf(latlongData.getLongitude()), i, str).enqueue(new a(coveApiListener));
    }

    public static void b(String str, int i, String str2, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getGymSpaByName(map, str, i, str2).enqueue(new b(coveApiListener));
    }

    public static void getGymSpaListByLocation(LatlongData latlongData, int i, String str, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener) {
        a(latlongData, i, str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getGymSpaListByName(String str, int i, String str2, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener) {
        b(str, i, str2, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getLocation(GetNearByGymSpaReq getNearByGymSpaReq, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener) {
        if (getNearByGymSpaReq.getLatlongData() != null) {
            if (getNearByGymSpaReq.getPlaceType() != null) {
                getGymSpaListByLocation(getNearByGymSpaReq.getLatlongData(), getNearByGymSpaReq.getRadius(), getNearByGymSpaReq.getPlaceType().toString().toLowerCase(), coveApiListener);
            } else {
                getGymSpaListByLocation(getNearByGymSpaReq.getLatlongData(), getNearByGymSpaReq.getRadius(), null, coveApiListener);
            }
        } else if (CoveUtil.isEmpty(getNearByGymSpaReq.getName())) {
        } else {
            if (getNearByGymSpaReq.getPlaceType() != null) {
                getGymSpaListByName(getNearByGymSpaReq.getName(), getNearByGymSpaReq.getRadius(), getNearByGymSpaReq.getPlaceType().toString().toLowerCase(), coveApiListener);
            } else {
                getGymSpaListByName(getNearByGymSpaReq.getName(), getNearByGymSpaReq.getRadius(), null, coveApiListener);
            }
        }
    }

    public static void getGymSpaListByLocation(HashMap<String, String> hashMap, LatlongData latlongData, int i, String str, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener) {
        a(latlongData, i, str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getGymSpaListByName(HashMap<String, String> hashMap, String str, int i, String str2, CoveApiListener<GymSpaRes, CoveApiErrorModel> coveApiListener) {
        b(str, i, str2, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
