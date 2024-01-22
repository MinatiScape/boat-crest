package com.coveiot.coveaccess.timeline;

import android.util.Log;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CardItemsBean;
import com.coveiot.coveaccess.model.server.SCardItemUploadBean;
import com.coveiot.coveaccess.model.server.SMediaListResponse;
import com.coveiot.coveaccess.model.server.SMediaUploadResponse;
import com.coveiot.coveaccess.model.server.STimelineCardDataResponse;
import com.coveiot.coveaccess.model.server.STimelineCardSaveResponse;
import com.coveiot.coveaccess.timeline.model.DeleteTimeLineEnteryRes;
import com.coveiot.coveaccess.timeline.model.FetchTimeLineDataReq;
import com.coveiot.coveaccess.timeline.model.FetchTimeLineDataRes;
import com.coveiot.coveaccess.timeline.model.GetMediaListForPlaceReq;
import com.coveiot.coveaccess.timeline.model.GetMediaListForPlaceRes;
import com.coveiot.coveaccess.timeline.model.MediaUploadWithPlaceIdReq;
import com.coveiot.coveaccess.timeline.model.TimeLineCheckInData;
import com.coveiot.coveaccess.timeline.model.TimeLineCheckInReq;
import com.coveiot.coveaccess.timeline.model.TimeLineCheckInRes;
import com.coveiot.coveaccess.timeline.model.TimeLineSleepData;
import com.coveiot.coveaccess.timeline.model.TimeLineSleepReq;
import com.coveiot.coveaccess.timeline.model.TimeLineSleepRes;
import com.coveiot.coveaccess.timeline.model.TimeLineStepsData;
import com.coveiot.coveaccess.timeline.model.TimeLineStepsReq;
import com.coveiot.coveaccess.timeline.model.TimeLineStepsRes;
import com.coveiot.coveaccess.timeline.model.UpdateTimeLineCheckInRes;
import com.coveiot.coveaccess.timeline.model.UpdateTimeLineSleepRes;
import com.coveiot.coveaccess.timeline.model.UpdateTimeLineStepsRes;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class TimeLineManager {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SMediaListResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6799a;

        public a(CoveApiListener coveApiListener) {
            this.f6799a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SMediaListResponse> call, Throwable th) {
            this.f6799a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SMediaListResponse> call, Response<SMediaListResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            if (response.body().getData() != null) {
                GetMediaListForPlaceRes getMediaListForPlaceRes = new GetMediaListForPlaceRes(response.code());
                getMediaListForPlaceRes.setMediaListBeanList(response.body().getData().getMediaList());
                this.f6799a.onSuccess(getMediaListForPlaceRes);
                return;
            }
            int code = response.code();
            this.f6799a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<STimelineCardSaveResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6800a;

        public b(CoveApiListener coveApiListener) {
            this.f6800a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STimelineCardSaveResponse> call, Throwable th) {
            this.f6800a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STimelineCardSaveResponse> call, Response<STimelineCardSaveResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                TimeLineCheckInRes timeLineCheckInRes = new TimeLineCheckInRes();
                if (!CoveUtil.isEmpty(response.body().getData())) {
                    timeLineCheckInRes.setTimeLineCheckInData((TimeLineCheckInData) response.body().getData().get(0).covertToTimeLineData());
                    this.f6800a.onSuccess(timeLineCheckInRes);
                    return;
                }
                this.f6800a.onError(new CoveApiErrorModel("TimeLine Empty", response.code()));
                return;
            }
            int code = response.code();
            this.f6800a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class c implements Callback<STimelineCardSaveResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6801a;

        public c(CoveApiListener coveApiListener) {
            this.f6801a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STimelineCardSaveResponse> call, Throwable th) {
            this.f6801a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STimelineCardSaveResponse> call, Response<STimelineCardSaveResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                TimeLineStepsRes timeLineStepsRes = new TimeLineStepsRes();
                if (!CoveUtil.isEmpty(response.body().getData())) {
                    timeLineStepsRes.setTimeLineStepsData((TimeLineStepsData) response.body().getData().get(0).covertToTimeLineData());
                    this.f6801a.onSuccess(timeLineStepsRes);
                    return;
                }
                this.f6801a.onError(new CoveApiErrorModel("TimeLine Empty", response.code()));
                return;
            }
            int code = response.code();
            this.f6801a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class d implements Callback<STimelineCardSaveResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6802a;

        public d(CoveApiListener coveApiListener) {
            this.f6802a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STimelineCardSaveResponse> call, Throwable th) {
            this.f6802a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STimelineCardSaveResponse> call, Response<STimelineCardSaveResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                TimeLineSleepRes timeLineSleepRes = new TimeLineSleepRes();
                if (!CoveUtil.isEmpty(response.body().getData())) {
                    timeLineSleepRes.setTimeLineSleepData((TimeLineSleepData) response.body().getData().get(0).covertToTimeLineData());
                    this.f6802a.onSuccess(timeLineSleepRes);
                    return;
                }
                this.f6802a.onError(new CoveApiErrorModel("TimeLine Empty", response.code()));
                return;
            }
            int code = response.code();
            this.f6802a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class e implements Callback<SMediaUploadResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6803a;

        public e(CoveApiListener coveApiListener) {
            this.f6803a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SMediaUploadResponse> call, Throwable th) {
            this.f6803a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SMediaUploadResponse> call, Response<SMediaUploadResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6803a.onSuccess(new MediaUploadRes(response.body().getData()));
                return;
            }
            int code = response.code();
            this.f6803a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class f implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6804a;

        public f(CoveApiListener coveApiListener) {
            this.f6804a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6804a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6804a.onSuccess(new UpdateTimeLineCheckInRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6804a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class g implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6805a;

        public g(CoveApiListener coveApiListener) {
            this.f6805a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6805a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6805a.onSuccess(new UpdateTimeLineStepsRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6805a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class h implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6806a;

        public h(CoveApiListener coveApiListener) {
            this.f6806a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6806a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6806a.onSuccess(new UpdateTimeLineSleepRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6806a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class i implements Callback<JSONObject> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6807a;

        public i(CoveApiListener coveApiListener) {
            this.f6807a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<JSONObject> call, Throwable th) {
            this.f6807a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            if (response.isSuccessful()) {
                this.f6807a.onSuccess(new DeleteTimeLineEnteryRes(response.code()));
                return;
            }
            int code = response.code();
            this.f6807a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    /* loaded from: classes8.dex */
    public static class j implements Callback<STimelineCardDataResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6808a;

        public j(CoveApiListener coveApiListener) {
            this.f6808a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<STimelineCardDataResponse> call, Throwable th) {
            this.f6808a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<STimelineCardDataResponse> call, Response<STimelineCardDataResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                STimelineCardDataResponse body = response.body();
                if (body.getData() != null) {
                    if (!CoveUtil.isEmpty(body.getData().getItems())) {
                        ArrayList arrayList = new ArrayList();
                        for (CardItemsBean cardItemsBean : body.getData().getItems()) {
                            arrayList.add(cardItemsBean.covertToTimeLineData());
                        }
                        FetchTimeLineDataRes fetchTimeLineDataRes = new FetchTimeLineDataRes(response.code());
                        fetchTimeLineDataRes.setTimeLineDataList(arrayList);
                        this.f6808a.onSuccess(fetchTimeLineDataRes);
                        return;
                    }
                    this.f6808a.onError(new CoveApiErrorModel("TimeLine Empty", 200));
                    return;
                }
                this.f6808a.onError(new CoveApiErrorModel("TimeLine Empty", 200));
                return;
            }
            int code = response.code();
            this.f6808a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(GetMediaListForPlaceReq getMediaListForPlaceReq, CoveApiListener<GetMediaListForPlaceRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().getMediaListFor(map, getMediaListForPlaceReq.getPlaceId()).enqueue(new a(coveApiListener));
    }

    public static void b(MediaUploadWithPlaceIdReq mediaUploadWithPlaceIdReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().uploadImage(map, MultipartBody.Part.createFormData("sourceFile", mediaUploadWithPlaceIdReq.getFile().getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), mediaUploadWithPlaceIdReq.getFile())), RequestBody.create(MediaType.parse("text/plain"), mediaUploadWithPlaceIdReq.getMediaClassType().toString()), RequestBody.create(MediaType.parse("text/plain"), mediaUploadWithPlaceIdReq.getPlaceId())).enqueue(new e(coveApiListener));
    }

    public static void c(TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<TimeLineCheckInRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SCardItemUploadBean sCardItemUploadBean = new SCardItemUploadBean();
        ArrayList arrayList = new ArrayList();
        arrayList.add(CardItemsBean.getInstanceFrom(timeLineCheckInReq.getTimeLineCheckInData()));
        sCardItemUploadBean.setTimelineLogs(arrayList);
        CoveApi.getService().saveTimeLineData(map, sCardItemUploadBean).enqueue(new b(coveApiListener));
    }

    public static void d(TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<UpdateTimeLineCheckInRes, CoveApiErrorModel> coveApiListener, Map<String, String> map, CardItemsBean cardItemsBean) {
        CoveApi.getService().updateTimeLineDetails(map, cardItemsBean, timeLineCheckInReq.getTimeLineCheckInData().getLogId()).enqueue(new f(coveApiListener));
    }

    public static void deleteTimeLineEntry(String str, CoveApiListener<DeleteTimeLineEnteryRes, CoveApiErrorModel> coveApiListener) {
        g(str, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void e(TimeLineSleepReq timeLineSleepReq, CoveApiListener<TimeLineSleepRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SCardItemUploadBean sCardItemUploadBean = new SCardItemUploadBean();
        ArrayList arrayList = new ArrayList();
        arrayList.add(CardItemsBean.getInstanceFrom(timeLineSleepReq.getTimeLineSleepData()));
        sCardItemUploadBean.setTimelineLogs(arrayList);
        CoveApi.getService().saveTimeLineData(map, sCardItemUploadBean).enqueue(new d(coveApiListener));
    }

    public static void f(TimeLineStepsReq timeLineStepsReq, CoveApiListener<TimeLineStepsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        SCardItemUploadBean sCardItemUploadBean = new SCardItemUploadBean();
        ArrayList arrayList = new ArrayList();
        arrayList.add(CardItemsBean.getInstanceFrom(timeLineStepsReq.getTimeLineStepsData()));
        sCardItemUploadBean.setTimelineLogs(arrayList);
        CoveApi.getService().saveTimeLineData(map, sCardItemUploadBean).enqueue(new c(coveApiListener));
    }

    public static void g(String str, CoveApiListener<DeleteTimeLineEnteryRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().deleteTimeLineEntry(map, str).enqueue(new i(coveApiListener));
    }

    public static void getMediaListForPlace(GetMediaListForPlaceReq getMediaListForPlaceReq, CoveApiListener<GetMediaListForPlaceRes, CoveApiErrorModel> coveApiListener) {
        a(getMediaListForPlaceReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void getTimeLineDataFromServer(FetchTimeLineDataReq fetchTimeLineDataReq, CoveApiListener<FetchTimeLineDataRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getService().getTimeLineDataFor(CoveApi.getCustomHeaders(), fetchTimeLineDataReq.getPageIndex(), fetchTimeLineDataReq.getNumberOfItemsPerPage()).enqueue(new j(coveApiListener));
    }

    public static void h(TimeLineSleepReq timeLineSleepReq, CoveApiListener<UpdateTimeLineSleepRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CardItemsBean instanceFrom = CardItemsBean.getInstanceFrom(timeLineSleepReq.getTimeLineSleepData());
        if (CoveUtil.isEmpty(timeLineSleepReq.getTimeLineSleepData().getLogId())) {
            Log.e(TimeLineManager.class.getSimpleName(), "Log Id is empty");
        } else {
            CoveApi.getService().updateTimeLineDetails(map, instanceFrom, timeLineSleepReq.getTimeLineSleepData().getLogId()).enqueue(new h(coveApiListener));
        }
    }

    public static void i(TimeLineStepsReq timeLineStepsReq, CoveApiListener<UpdateTimeLineStepsRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CardItemsBean instanceFrom = CardItemsBean.getInstanceFrom(timeLineStepsReq.getTimeLineStepsData());
        if (CoveUtil.isEmpty(timeLineStepsReq.getTimeLineStepsData().getLogId())) {
            Log.e(TimeLineManager.class.getSimpleName(), "Log Id is empty");
        } else {
            CoveApi.getService().updateTimeLineDetails(map, instanceFrom, timeLineStepsReq.getTimeLineStepsData().getLogId()).enqueue(new g(coveApiListener));
        }
    }

    public static void logCheckInTimeLineEntry(TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<TimeLineCheckInRes, CoveApiErrorModel> coveApiListener) {
        c(timeLineCheckInReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void logSleepTimeLineEntry(TimeLineSleepReq timeLineSleepReq, CoveApiListener<TimeLineSleepRes, CoveApiErrorModel> coveApiListener) {
        e(timeLineSleepReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void logStepsTimeLineEntry(TimeLineStepsReq timeLineStepsReq, CoveApiListener<TimeLineStepsRes, CoveApiErrorModel> coveApiListener) {
        f(timeLineStepsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateCheckInTimeLineEntry(TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<UpdateTimeLineCheckInRes, CoveApiErrorModel> coveApiListener) {
        HashMap<String, String> customHeaders = CoveApi.getCustomHeaders();
        CardItemsBean instanceFrom = CardItemsBean.getInstanceFrom(timeLineCheckInReq.getTimeLineCheckInData());
        if (CoveUtil.isEmpty(timeLineCheckInReq.getTimeLineCheckInData().getLogId())) {
            Log.e(TimeLineManager.class.getSimpleName(), "Log Id is empty");
        } else {
            d(timeLineCheckInReq, coveApiListener, customHeaders, instanceFrom);
        }
    }

    public static void updateSleepTimeLineEntry(TimeLineSleepReq timeLineSleepReq, CoveApiListener<UpdateTimeLineSleepRes, CoveApiErrorModel> coveApiListener) {
        h(timeLineSleepReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void updateStepsTimeLineEntry(TimeLineStepsReq timeLineStepsReq, CoveApiListener<UpdateTimeLineStepsRes, CoveApiErrorModel> coveApiListener) {
        i(timeLineStepsReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void uploadMediaFileWithPlaceId(MediaUploadWithPlaceIdReq mediaUploadWithPlaceIdReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener) {
        b(mediaUploadWithPlaceIdReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void deleteTimeLineEntry(HashMap<String, String> hashMap, String str, CoveApiListener<DeleteTimeLineEnteryRes, CoveApiErrorModel> coveApiListener) {
        g(str, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void getMediaListForPlace(HashMap<String, String> hashMap, GetMediaListForPlaceReq getMediaListForPlaceReq, CoveApiListener<GetMediaListForPlaceRes, CoveApiErrorModel> coveApiListener) {
        a(getMediaListForPlaceReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void logCheckInTimeLineEntry(HashMap<String, String> hashMap, TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<TimeLineCheckInRes, CoveApiErrorModel> coveApiListener) {
        c(timeLineCheckInReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void logSleepTimeLineEntry(HashMap<String, String> hashMap, TimeLineSleepReq timeLineSleepReq, CoveApiListener<TimeLineSleepRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        e(timeLineSleepReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void logStepsTimeLineEntry(HashMap<String, String> hashMap, TimeLineStepsReq timeLineStepsReq, CoveApiListener<TimeLineStepsRes, CoveApiErrorModel> coveApiListener) {
        f(timeLineStepsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateSleepTimeLineEntry(HashMap<String, String> hashMap, TimeLineSleepReq timeLineSleepReq, CoveApiListener<UpdateTimeLineSleepRes, CoveApiErrorModel> coveApiListener) {
        h(timeLineSleepReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateStepsTimeLineEntry(HashMap<String, String> hashMap, TimeLineStepsReq timeLineStepsReq, CoveApiListener<UpdateTimeLineStepsRes, CoveApiErrorModel> coveApiListener) {
        i(timeLineStepsReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void uploadMediaFileWithPlaceId(HashMap<String, String> hashMap, MediaUploadWithPlaceIdReq mediaUploadWithPlaceIdReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        b(mediaUploadWithPlaceIdReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }

    public static void updateCheckInTimeLineEntry(HashMap<String, String> hashMap, TimeLineCheckInReq timeLineCheckInReq, CoveApiListener<UpdateTimeLineCheckInRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        CardItemsBean instanceFrom = CardItemsBean.getInstanceFrom(timeLineCheckInReq.getTimeLineCheckInData());
        if (CoveUtil.isEmpty(timeLineCheckInReq.getTimeLineCheckInData().getLogId())) {
            Log.e(TimeLineManager.class.getSimpleName(), "Log Id is empty");
        } else {
            d(timeLineCheckInReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap), instanceFrom);
        }
    }
}
