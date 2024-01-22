package com.mappls.sdk.maps;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes11.dex */
public class o {
    public static final o b = new o();

    /* renamed from: a  reason: collision with root package name */
    public HashMap<String, CoordinateResult> f12795a = new HashMap<>();

    /* loaded from: classes11.dex */
    public class a implements Callback<CoordinateResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CoordinateCallback f12796a;

        public a(CoordinateCallback coordinateCallback) {
            this.f12796a = coordinateCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CoordinateResponse> call, Throwable th) {
            CoordinateCallback coordinateCallback = this.f12796a;
            if (coordinateCallback != null) {
                coordinateCallback.onFailure();
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CoordinateResponse> call, Response<CoordinateResponse> response) {
            if (response.code() == 200 && response.body() != null) {
                List<CoordinateResult> results = response.body().getResults();
                if (results != null && results.size() > 0 && results.get(0) != null) {
                    CoordinateResult coordinateResult = results.get(0);
                    if (coordinateResult.getMapplsPin() != null && coordinateResult.getLongitude() != null && coordinateResult.getLatitude() != null) {
                        o.this.f12795a.put(coordinateResult.getMapplsPin().toUpperCase(), coordinateResult);
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(coordinateResult);
                        CoordinateCallback coordinateCallback = this.f12796a;
                        if (coordinateCallback != null) {
                            coordinateCallback.coordinateResultSuccess(arrayList);
                            return;
                        }
                        return;
                    }
                    CoordinateCallback coordinateCallback2 = this.f12796a;
                    if (coordinateCallback2 != null) {
                        coordinateCallback2.onFailure();
                        return;
                    }
                    return;
                }
                CoordinateCallback coordinateCallback3 = this.f12796a;
                if (coordinateCallback3 != null) {
                    coordinateCallback3.onFailure();
                    return;
                }
                return;
            }
            CoordinateCallback coordinateCallback4 = this.f12796a;
            if (coordinateCallback4 != null) {
                coordinateCallback4.onFailure();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class b implements Callback<CoordinateResponse> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ List f12797a;
        public final /* synthetic */ CoordinateCallback b;

        public b(List list, CoordinateCallback coordinateCallback) {
            this.f12797a = list;
            this.b = coordinateCallback;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<CoordinateResponse> call, Throwable th) {
            CoordinateCallback coordinateCallback = this.b;
            if (coordinateCallback != null) {
                coordinateCallback.coordinateResultSuccess(this.f12797a);
            }
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<CoordinateResponse> call, Response<CoordinateResponse> response) {
            if (response.code() == 200 && response.body() != null) {
                List<CoordinateResult> results = response.body().getResults();
                if (results != null && results.size() > 0) {
                    for (CoordinateResult coordinateResult : results) {
                        if (coordinateResult.getMapplsPin() != null && coordinateResult.getLongitude() != null && coordinateResult.getLatitude() != null) {
                            o.this.f12795a.put(coordinateResult.getMapplsPin().toUpperCase(), coordinateResult);
                            this.f12797a.add(coordinateResult);
                        }
                    }
                    CoordinateCallback coordinateCallback = this.b;
                    if (coordinateCallback != null) {
                        coordinateCallback.coordinateResultSuccess(this.f12797a);
                        return;
                    }
                    return;
                }
                CoordinateCallback coordinateCallback2 = this.b;
                if (coordinateCallback2 != null) {
                    coordinateCallback2.coordinateResultSuccess(this.f12797a);
                    return;
                }
                return;
            }
            CoordinateCallback coordinateCallback3 = this.b;
            if (coordinateCallback3 != null) {
                coordinateCallback3.coordinateResultSuccess(this.f12797a);
            }
        }
    }

    public static o d() {
        return b;
    }

    public void b(@NonNull String str, CoordinateCallback coordinateCallback) {
        if (this.f12795a.containsKey(str.toUpperCase())) {
            List<CoordinateResult> arrayList = new ArrayList<>();
            arrayList.add(this.f12795a.get(str.toUpperCase()));
            if (coordinateCallback != null) {
                coordinateCallback.coordinateResultSuccess(arrayList);
                return;
            }
            return;
        }
        MapplsGetCoordinates.a().mapplsPin(str).build().enqueueCall(new a(coordinateCallback));
    }

    public void c(@NonNull List<String> list, CoordinateCallback coordinateCallback) {
        List<CoordinateResult> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        for (String str : list) {
            if (this.f12795a.containsKey(str.toUpperCase())) {
                arrayList.add(this.f12795a.get(str.toUpperCase()));
            } else {
                arrayList2.add(str);
            }
        }
        if (arrayList2.size() > 0) {
            MapplsGetCoordinates.a().mapplsPin(arrayList2).build().enqueueCall(new b(arrayList, coordinateCallback));
        } else if (coordinateCallback != null) {
            coordinateCallback.coordinateResultSuccess(arrayList);
        }
    }
}
