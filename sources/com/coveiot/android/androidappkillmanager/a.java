package com.coveiot.android.androidappkillmanager;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2907a = "a";
    public static ApiInterface b;

    /* renamed from: com.coveiot.android.androidappkillmanager.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0253a implements Callback<ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2908a;

        public C0253a(Context context) {
            this.f2908a = context;
        }

        @Override // retrofit2.Callback
        public void onFailure(Call<ResponseBody> call, Throwable th) {
            Log.d(a.f2907a, th.toString());
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            try {
                this.f2908a.getSharedPreferences("kill-mgr-pref", 0).edit().putString("kill-mgr-pref-key", response.body().toString()).apply();
            } catch (Exception e) {
                Log.d(a.f2907a, e.toString());
            }
        }
    }

    public static String b(Context context) {
        String string = context.getSharedPreferences("kill-mgr-pref", 0).getString("kill-mgr-pref-key", null);
        return string == null ? c(context) : string;
    }

    public static String c(Context context) {
        return d(context);
    }

    public static String d(Context context) {
        try {
            InputStream open = context.getAssets().open("ActionIntents.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void e(Context context) {
        ApiInterface apiInterface = (ApiInterface) ApiClient.getClient().create(ApiInterface.class);
        b = apiInterface;
        apiInterface.serverRefresh().enqueue(new C0253a(context));
    }
}
