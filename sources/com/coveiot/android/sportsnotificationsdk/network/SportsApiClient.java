package com.coveiot.android.sportsnotificationsdk.network;

import android.content.Context;
import com.coveiot.android.sportsnotificationsdk.BuildConfig;
import com.coveiot.android.sportsnotificationsdk.Constants;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\u0007"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiClient;", "", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiService;", "getSportsClientService", "<init>", "()V", "Companion", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes7.dex */
public final class SportsApiClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static SportsApiClient f5978a;
    @NotNull
    public static SportType b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007J\u001a\u0010\b\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004J\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000bR\u001e\u0010\u000f\u001a\n \u000e*\u0004\u0018\u00010\f0\f8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiClient$Companion;", "", "Landroid/content/Context;", "context", "Lcom/coveiot/android/sportsnotificationsdk/network/SportType;", DeviceKey.sportType, "", "initSportsApi", "resetSportsApi", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiClient;", "getService", "Ljava/util/HashMap;", "", "getSportsHeaders", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "mContext", "Landroid/content/Context;", "mSportType", "Lcom/coveiot/android/sportsnotificationsdk/network/SportType;", "sportsApiClient", "Lcom/coveiot/android/sportsnotificationsdk/network/SportsApiClient;", "<init>", "()V", "sportsnotificationsdk_prodRelease"}, k = 1, mv = {1, 5, 1})
    /* loaded from: classes7.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void initSportsApi$default(Companion companion, Context context, SportType sportType, int i, Object obj) {
            if ((i & 2) != 0) {
                sportType = SportType.CRICKET;
            }
            companion.initSportsApi(context, sportType);
        }

        public static /* synthetic */ void resetSportsApi$default(Companion companion, Context context, SportType sportType, int i, Object obj) {
            if ((i & 2) != 0) {
                sportType = SportType.CRICKET;
            }
            companion.resetSportsApi(context, sportType);
        }

        @Nullable
        public final SportsApiClient getService() {
            if (SportsApiClient.f5978a != null) {
                return SportsApiClient.f5978a;
            }
            throw new SetupException(Constants.INSTANCE.getSETUP_ERR_NOT_INIT());
        }

        @NotNull
        public final HashMap<String, String> getSportsHeaders() {
            return new HashMap<>();
        }

        @JvmStatic
        public final synchronized void initSportsApi(@Nullable Context context, @NotNull SportType sportType) {
            Intrinsics.checkNotNullParameter(sportType, "sportType");
            if (SportsApiClient.f5978a == null) {
                SportsApiClient.access$setMContext$cp(context);
                SportsApiClient.b = sportType;
                SportsApiClient.f5978a = new SportsApiClient();
            }
        }

        public final void resetSportsApi(@Nullable Context context, @NotNull SportType sportType) {
            Intrinsics.checkNotNullParameter(sportType, "sportType");
            SportsApiClient.f5978a = null;
            initSportsApi(context, sportType);
        }
    }

    static {
        new SportsApiClient();
        b = SportType.CRICKET;
    }

    public static final /* synthetic */ void access$setMContext$cp(Context context) {
    }

    @JvmStatic
    public static final synchronized void initSportsApi(@Nullable Context context, @NotNull SportType sportType) {
        synchronized (SportsApiClient.class) {
            Companion.initSportsApi(context, sportType);
        }
    }

    public final Retrofit a() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder addInterceptor = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
        TimeUnit timeUnit = TimeUnit.SECONDS;
        return new Retrofit.Builder().baseUrl(b == SportType.CRICKET ? BuildConfig.SPORTS_BASE_URL : BuildConfig.SOCCER_SPORTS_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(addInterceptor.connectTimeout(60L, timeUnit).readTimeout(60L, timeUnit).callTimeout(60L, timeUnit).retryOnConnectionFailure(true).build()).build();
    }

    @NotNull
    public final SportsApiService getSportsClientService() {
        Retrofit a2 = a();
        Intrinsics.checkNotNull(a2);
        Object create = a2.create(SportsApiService.class);
        Intrinsics.checkNotNullExpressionValue(create, "getSportsClient()!!.create(SportsApiService::class.java)");
        return (SportsApiService) create;
    }
}
