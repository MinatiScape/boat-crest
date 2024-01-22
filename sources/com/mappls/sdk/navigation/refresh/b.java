package com.mappls.sdk.navigation.refresh;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.mappls.sdk.navigation.MapplsNavigationHelper;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.apis.NavigationLogger;
import com.mappls.sdk.navigation.apis.junction.MapplsGetJunction;
import com.mappls.sdk.navigation.apis.junction.MapplsGetJunctionName;
import com.mappls.sdk.navigation.model.Junction;
import com.mappls.sdk.navigation.model.JunctionApiResponse;
import com.mappls.sdk.services.api.directions.models.RouteOptions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public NavigationApplication f12931a;
    public MapplsGetJunction b;
    public boolean c;
    public int d = 5;
    public boolean e = false;
    public int f = 0;

    /* loaded from: classes11.dex */
    public class a implements Callback<JunctionApiResponse> {
        public a() {
        }

        @Override // retrofit2.Callback
        public final void onFailure(@NonNull Call<JunctionApiResponse> call, @NonNull Throwable th) {
            NavigationLogger.e(th);
        }

        @Override // retrofit2.Callback
        public final void onResponse(@NonNull Call<JunctionApiResponse> call, @NonNull Response<JunctionApiResponse> response) {
            if (!response.isSuccessful() || response.body() == null) {
                return;
            }
            JunctionApiResponse body = response.body();
            b.this.f12931a.h().k().setJunctionViews(body);
            b.this.f12931a.o().a(5);
            try {
                b.d(b.this, body.data);
                b bVar = b.this;
                b.h(bVar, body.data.subList(bVar.f, b.this.d));
            } catch (Exception e) {
                NavigationLogger.e(e);
            }
        }
    }

    /* renamed from: com.mappls.sdk.navigation.refresh.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class AsyncTaskC0642b extends AsyncTask<List<Junction>, Void, List<Junction>> {
        public AsyncTaskC0642b() {
        }

        public /* synthetic */ AsyncTaskC0642b(b bVar, int i) {
            this();
        }

        @Override // android.os.AsyncTask
        public final List<Junction> doInBackground(List<Junction>[] listArr) {
            List<Junction>[] listArr2 = listArr;
            if (listArr2.length > 0) {
                b.this.e = true;
                List<Junction> list = listArr2[0];
                HashMap hashMap = new HashMap();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(list.get(i).image);
                    hashMap.put(list.get(i).image, list.get(i));
                }
                try {
                    MapplsGetJunctionName.Builder builder = MapplsGetJunctionName.builder();
                    if (MapplsNavigationHelper.getInstance().getBaseRes() != null) {
                        builder.baseUrl(MapplsNavigationHelper.getInstance().getBaseRes() + "apis/O2O/");
                    }
                    Response<Map<String, String>> executeCall = builder.imageName(arrayList).junctionViewMode(MapplsNavigationHelper.getInstance().getJunctionViewMode()).size(MapplsNavigationHelper.getInstance().getJunctionViewImageSize()).build().executeCall();
                    if (!executeCall.isSuccessful() || executeCall.body() == null) {
                        return null;
                    }
                    for (Map.Entry<String, String> entry : executeCall.body().entrySet()) {
                        Response<ResponseBody> execute = com.mappls.sdk.navigation.apis.web.b.a().b().a(entry.getValue()).execute();
                        if (execute.isSuccessful()) {
                            InputStream byteStream = execute.body().byteStream();
                            if (hashMap.get(entry.getKey()) != null) {
                                ((Junction) hashMap.get(entry.getKey())).setBitmap(BitmapFactory.decodeStream(byteStream));
                            }
                        } else if (execute.code() == 403) {
                            new ArrayList().add(entry.getKey());
                            for (Map.Entry<String, String> entry2 : MapplsGetJunctionName.builder().imageName(arrayList).junctionViewMode(MapplsNavigationHelper.getInstance().getJunctionViewMode()).size(MapplsNavigationHelper.getInstance().getJunctionViewImageSize()).build().executeCall().body().entrySet()) {
                                if (com.mappls.sdk.navigation.apis.web.b.a().b().a(entry2.getValue()).execute().isSuccessful()) {
                                    InputStream byteStream2 = execute.body().byteStream();
                                    if (hashMap.get(entry2.getKey()) != null) {
                                        ((Junction) hashMap.get(entry2.getKey())).setBitmap(BitmapFactory.decodeStream(byteStream2));
                                    }
                                }
                            }
                        }
                    }
                    return null;
                } catch (Exception e) {
                    NavigationLogger.e(e);
                    return null;
                }
            }
            return null;
        }

        @Override // android.os.AsyncTask
        public final void onPostExecute(List<Junction> list) {
            b.this.e = false;
            b.this.f += b.this.d;
        }
    }

    public b(NavigationApplication navigationApplication) {
        this.f12931a = navigationApplication;
        this.c = ((Boolean) navigationApplication.k().G.get()).booleanValue();
    }

    public static void d(b bVar, List list) {
        bVar.getClass();
        if (list.size() - bVar.f >= 5) {
            bVar.d = 5;
        } else if (list.size() - bVar.f <= 0 || list.size() - bVar.f >= 5) {
        } else {
            bVar.d = list.size() - bVar.f;
        }
    }

    public static void h(b bVar, List list) {
        bVar.getClass();
        new AsyncTaskC0642b(bVar, 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, list);
    }

    public final void a(boolean z) {
        if (this.c == z) {
            return;
        }
        this.c = z;
        b();
    }

    public final void b() {
        RouteOptions fromJson;
        MapplsGetJunction mapplsGetJunction = this.b;
        if (mapplsGetJunction != null && mapplsGetJunction.isExecuted()) {
            this.b.cancelCall();
        }
        this.b = null;
        try {
            if (this.f12931a.h().k() != null) {
                this.f12931a.h().k().setJunctionViews(null);
            }
            this.f12931a.o().a(5);
        } catch (Exception e) {
            NavigationLogger.e(e);
        }
        this.e = false;
        this.d = 5;
        this.f = 0;
        if (!this.c || !MapplsNavigationHelper.getInstance().isNavigating() || (fromJson = RouteOptions.fromJson((String) this.f12931a.k().f12956a.get())) == null || fromJson.requestUuid() == null) {
            return;
        }
        int routeIndex = MapplsNavigationHelper.getInstance().getRouteIndex();
        if (MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex() != null) {
            routeIndex = MapplsNavigationHelper.getInstance().getCurrentRoute().routeIndex().intValue();
        }
        MapplsGetJunction.Builder size = MapplsGetJunction.builder().routeId(fromJson.requestUuid()).junctionViewMode(MapplsNavigationHelper.getInstance().getJunctionViewMode()).routeIdx(Integer.valueOf(routeIndex)).size(MapplsNavigationHelper.getInstance().getJunctionViewImageSize());
        if (MapplsNavigationHelper.getInstance().getBaseRes() != null) {
            size.baseUrl(MapplsNavigationHelper.getInstance().getBaseRes() + "apis/O2O/");
        }
        MapplsGetJunction build = size.build();
        this.b = build;
        build.enqueueCall(new a());
    }

    public final void c() {
        b();
    }

    public final void d() {
        b();
    }

    public final void g() {
        int i;
        if (this.e || this.f12931a.h().s()) {
            return;
        }
        List<Junction> junctionViews = this.f12931a.h().k().getJunctionViews();
        if (junctionViews.size() > 5 && (i = (this.f - 2) - 1) >= 0 && i <= junctionViews.size() - 1 && MapplsNavigationHelper.getInstance().getNodeIndex() > junctionViews.get(i).nodeIdx) {
            if (junctionViews.size() - this.f >= 5) {
                this.d = 5;
            } else if (junctionViews.size() - this.f > 0 && junctionViews.size() - this.f < 5) {
                this.d = junctionViews.size() - this.f;
            }
            if (this.f >= junctionViews.size()) {
                return;
            }
            int i2 = this.f;
            new AsyncTaskC0642b(this, 0).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, junctionViews.subList(i2, this.d + i2));
        }
    }
}
