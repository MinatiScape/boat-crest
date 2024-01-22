package com.coveiot.android.weathersdk.server;

import com.coveiot.android.weathersdk.server.network.forecastmodel.SWeatherForecastModel;
import com.coveiot.android.weathersdk.server.network.hourlyforecastmodel.SHourlyForecastModel;
import com.coveiot.android.weathersdk.server.network.model.SCurrentForecastModel;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwx.HeaderParameterNames;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import retrofit2.http.Url;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JO\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b\u000b\u0010\fJ\u0091\u0001\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\u001a\b\u0001\u0010\u0004\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\r2\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b\u0014\u0010\u0015JC\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\t2\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u0005H'¢\u0006\u0004\b\u0014\u0010\u0017JO\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\t2\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000fH'¢\u0006\u0004\b\u0014\u0010\u0018J[\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\t2\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b\u0014\u0010\u0019JO\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\b\b\u0001\u0010\u001a\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b\u001c\u0010\u001dJ[\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\t2\b\b\u0001\u0010\u001a\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b\u001e\u0010\u001fJ[\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010 \u0018\u00010\t2\b\b\u0001\u0010\u001a\u001a\u00020\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\u0007\u001a\u00020\u00052\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0003H'¢\u0006\u0004\b!\u0010\u001f¨\u0006\""}, d2 = {"Lcom/coveiot/android/weathersdk/server/OpenWeatherApiService;", "", "Ljava/util/HashMap;", "", "headers", "", "latitude", "longitude", "units", "Lretrofit2/Call;", "Lcom/coveiot/android/weathersdk/server/network/model/SCurrentForecastModel;", "getCurrentWeatherUpdates", "(Ljava/util/HashMap;DDLjava/lang/String;)Lretrofit2/Call;", "", RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME, "", "count", "id", HeaderParameterNames.ZIP, "lang", "getWeatherForecastUpdates", "(Ljava/util/Map;Ljava/lang/String;DDLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "Lcom/coveiot/android/weathersdk/server/network/forecastmodel/SWeatherForecastModel;", "(Ljava/util/HashMap;DD)Lretrofit2/Call;", "(Ljava/util/HashMap;DDLjava/lang/Integer;)Lretrofit2/Call;", "(Ljava/util/HashMap;DDLjava/lang/Integer;Ljava/lang/String;)Lretrofit2/Call;", "url", "apiKey", "getOpenWeatherMapCurrentWeatherUpdates", "(Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "getOpenWeatherMapDailyForecastUpdates", "(Ljava/lang/String;DDLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "Lcom/coveiot/android/weathersdk/server/network/hourlyforecastmodel/SHourlyForecastModel;", "getOpenWeatherMapHourlyForecastUpdates", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public interface OpenWeatherApiService {
    @GET("weather")
    @Nullable
    Call<SCurrentForecastModel> getCurrentWeatherUpdates(@HeaderMap @NotNull HashMap<String, String> hashMap, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("units") String str);

    @GET
    @Nullable
    Call<SCurrentForecastModel> getOpenWeatherMapCurrentWeatherUpdates(@Url @NotNull String str, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("units") String str2, @Nullable @Query("appid") String str3);

    @GET
    @Nullable
    Call<SWeatherForecastModel> getOpenWeatherMapDailyForecastUpdates(@Url @NotNull String str, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("cnt") Integer num, @Nullable @Query("units") String str2, @Nullable @Query("appid") String str3);

    @GET
    @Nullable
    Call<SHourlyForecastModel> getOpenWeatherMapHourlyForecastUpdates(@Url @NotNull String str, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("cnt") Integer num, @Nullable @Query("units") String str2, @Nullable @Query("appid") String str3);

    @GET("forecast/daily")
    @Nullable
    Call<SWeatherForecastModel> getWeatherForecastUpdates(@HeaderMap @NotNull HashMap<String, String> hashMap, @Query("lat") double d, @Query("lon") double d2);

    @GET("forecast/daily")
    @Nullable
    Call<SWeatherForecastModel> getWeatherForecastUpdates(@HeaderMap @NotNull HashMap<String, String> hashMap, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("cnt") Integer num);

    @GET("forecast/daily")
    @Nullable
    Call<SWeatherForecastModel> getWeatherForecastUpdates(@HeaderMap @NotNull HashMap<String, String> hashMap, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("cnt") Integer num, @Nullable @Query("units") String str);

    @GET("currentWeather")
    @Nullable
    Call<SCurrentForecastModel> getWeatherForecastUpdates(@HeaderMap @Nullable Map<String, String> map, @Nullable @Query("q") String str, @Query("lat") double d, @Query("lon") double d2, @Nullable @Query("cnt") Integer num, @Nullable @Query("units") String str2, @Nullable @Query("id") String str3, @Nullable @Query("zip") String str4, @Nullable @Query("lang") String str5);
}
