package com.coveiot.coveaccess.contacttracing;

import android.util.Log;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.contacttracing.model.NearByDeviceReq;
import com.coveiot.coveaccess.contacttracing.model.NearByDeviceRes;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.utils.CoveUtil;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes8.dex */
public class SocialDistanceManger {

    /* loaded from: classes8.dex */
    public static class a implements Callback<NearByDeviceRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6444a;

        public a(CoveApiListener coveApiListener) {
            this.f6444a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<NearByDeviceRes> call, Throwable th) {
            Log.i("NEARBYDEVICE", "" + call);
            this.f6444a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<NearByDeviceRes> call, Response<NearByDeviceRes> response) {
            if (response.isSuccessful()) {
                Log.i("NEARBYDEVICE", "" + response.body());
                this.f6444a.onSuccess(response.body());
            }
        }
    }

    /* loaded from: classes8.dex */
    public static class b implements Callback<NearByDeviceRes> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoveApiListener f6445a;

        public b(CoveApiListener coveApiListener) {
            this.f6445a = coveApiListener;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<NearByDeviceRes> call, Throwable th) {
            Log.i("NEARBYDEVICE", "" + call);
            this.f6445a.onError(CoveUtil.buildErrorObject(th));
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<NearByDeviceRes> call, Response<NearByDeviceRes> response) {
            if (response.isSuccessful()) {
                Log.i("NEARBYDEVICE", "" + response.body());
                this.f6445a.onSuccess(response.body());
            }
        }
    }

    public static void a(NearByDeviceReq nearByDeviceReq, CoveApiListener<NearByDeviceRes, CoveApiErrorModel> coveApiListener, Map<String, String> map) {
        if (CoveApi.getInstance().isEgApp()) {
            CoveApi.getService().saveEgNearByDevice(map, nearByDeviceReq).enqueue(new a(coveApiListener));
        } else {
            CoveApi.getService().saveNearByDevice(map, nearByDeviceReq).enqueue(new b(coveApiListener));
        }
    }

    public static void saveNearbyDeviceDataToServer(NearByDeviceReq nearByDeviceReq, CoveApiListener<NearByDeviceRes, CoveApiErrorModel> coveApiListener) {
        a(nearByDeviceReq, coveApiListener, CoveApi.getCustomHeaders());
    }

    public static void saveNearbyDeviceDataToServer(HashMap<String, String> hashMap, NearByDeviceReq nearByDeviceReq, CoveApiListener<NearByDeviceRes, CoveApiErrorModel> coveApiListener) {
        a(nearByDeviceReq, coveApiListener, CoveUtil.getRevisedHeaders(hashMap));
    }
}
