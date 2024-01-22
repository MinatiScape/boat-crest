package com.coveiot.coveaccess.mediauplaod;

import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadReq;
import com.coveiot.coveaccess.mediauplaod.model.MediaUploadRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.SMediaUploadResponse;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class MediaUpload {

    /* loaded from: classes8.dex */
    public static class a implements Callback<SMediaUploadResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6653a;

        public a(CoveApiListener coveApiListener) {
            this.f6653a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<SMediaUploadResponse> call, Throwable th) {
            this.f6653a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<SMediaUploadResponse> call, Response<SMediaUploadResponse> response) {
            if (response.isSuccessful() && response.body() != null) {
                this.f6653a.onSuccess(new MediaUploadRes(response.body().getData()));
                return;
            }
            int code = response.code();
            this.f6653a.onError(new CoveApiErrorModel(CoveUtil.getErrorMessage(code), code));
        }
    }

    public static void a(MediaUploadReq mediaUploadReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        CoveApi.getService().uploadImage(map, MultipartBody.Part.createFormData("sourceFile", mediaUploadReq.getFile().getName(), RequestBody.create(MediaType.parse(com.crrepa.r.a.d), mediaUploadReq.getFile())), RequestBody.create(MediaType.parse("text/plain"), mediaUploadReq.getMediaClassType().toString())).enqueue(new a(coveApiListener));
    }

    public static void uploadMediaFile(MediaUploadReq mediaUploadReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener) {
        a(mediaUploadReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void uploadMediaFile(HashMap<String, String> hashMap, MediaUploadReq mediaUploadReq, CoveApiListener<MediaUploadRes, CoveApiErrorModel> coveApiListener) {
        CoveApi.getCustomHeaders();
        a(mediaUploadReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
