package com.coveiot.android.weathersdk.server;

import android.content.Context;
import com.coveiot.android.weathersdk.BuildConfig;
import com.coveiot.android.weathersdk.WeatherAPIType;
import com.coveiot.android.weathersdk.WeatherConstants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0011\b\u0002\u0012\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0019\u0010\u000f\u001a\u00020\n8\u0006@\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/weathersdk/server/WeatherApiClient;", "", "Lcom/coveiot/android/weathersdk/WeatherAPIType;", "weatherAPIType", "Lretrofit2/Retrofit;", "getWeatherClientService", "(Lcom/coveiot/android/weathersdk/WeatherAPIType;)Lretrofit2/Retrofit;", "a", "Lretrofit2/Retrofit;", "retrofitApiClient", "Landroid/content/Context;", "b", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherApiClient {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public Retrofit f6220a;
    @NotNull
    public final Context b;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/weathersdk/server/WeatherApiClient$Companion;", "Lcom/coveiot/android/weathersdk/server/SingletonHolder;", "Lcom/coveiot/android/weathersdk/server/WeatherApiClient;", "Landroid/content/Context;", "<init>", "()V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<WeatherApiClient, Context> {

        /* loaded from: classes8.dex */
        public static final /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, WeatherApiClient> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f6221a = new a();

            public a() {
                super(1, WeatherApiClient.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public WeatherApiClient invoke(Context context) {
                Context p1 = context;
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new WeatherApiClient(p1, null);
            }
        }

        public Companion() {
            super(a.f6221a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            WeatherAPIType.values();
            $EnumSwitchMapping$0 = r0;
            int[] iArr = {0, 1, 2};
        }
    }

    public WeatherApiClient(Context context) {
        this.b = context;
    }

    public /* synthetic */ WeatherApiClient(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }

    @NotNull
    public final Retrofit getWeatherClientService(@NotNull WeatherAPIType weatherAPIType) {
        Retrofit build;
        Intrinsics.checkNotNullParameter(weatherAPIType, "weatherAPIType");
        int ordinal = weatherAPIType.ordinal();
        if (ordinal == 1) {
            CertificatePinner build2 = new CertificatePinner.Builder().add("www.community-open-weather-map.p.rapidapi.com", "sha256/AISCb6Xq3404CiFABiMge3ATp6Ue46HP/EGQGblfdp0=").build();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(null, 1, null);
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            build = new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).certificatePinner(build2).build()).build();
            this.f6220a = build;
        } else if (ordinal != 2) {
            HttpLoggingInterceptor httpLoggingInterceptor2 = new HttpLoggingInterceptor(null, 1, null);
            httpLoggingInterceptor2.setLevel(HttpLoggingInterceptor.Level.NONE);
            build = new Retrofit.Builder().baseUrl("AccuWeather URL").addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor2).build()).build();
            this.f6220a = build;
        } else {
            CertificatePinner build3 = new CertificatePinner.Builder().add("www.api.openweathermap.org", "sha256/AISCb6Xq3404CiFABiMge3ATp6Ue46HP/EGQGblfdp0=").build();
            HttpLoggingInterceptor httpLoggingInterceptor3 = new HttpLoggingInterceptor(null, 1, null);
            httpLoggingInterceptor3.setLevel(HttpLoggingInterceptor.Level.NONE);
            build = new Retrofit.Builder().baseUrl(WeatherConstants.INSTANCE.getOPEN_WEATHER_MAP_BASE_URL()).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor3).certificatePinner(build3).build()).build();
            this.f6220a = build;
        }
        Intrinsics.checkNotNull(build);
        return build;
    }
}
