package com.coveiot.android.weathersdk;

import android.content.Context;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentWeather;
import com.coveiot.android.weathersdk.response.forecastmodel.Weather;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherDetails;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.response.hourlyforecast.HourlyWeatherDetails;
import com.coveiot.android.weathersdk.response.hourlyforecast.HourlyWeatherForecastModel;
import com.coveiot.android.weathersdk.server.OpenWeatherApiService;
import com.coveiot.android.weathersdk.server.SingletonHolder;
import com.coveiot.android.weathersdk.server.WeatherApiClient;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.android.weathersdk.server.network.forecastmodel.SCity;
import com.coveiot.android.weathersdk.server.network.forecastmodel.SFeelsLike;
import com.coveiot.android.weathersdk.server.network.forecastmodel.STemp;
import com.coveiot.android.weathersdk.server.network.forecastmodel.SWeatherDetailList;
import com.coveiot.android.weathersdk.server.network.forecastmodel.SWeatherForecastModel;
import com.coveiot.android.weathersdk.server.network.hourlyforecastmodel.SHourlyForecastModel;
import com.coveiot.android.weathersdk.server.network.hourlyforecastmodel.SHourlyWeatherDetailList;
import com.coveiot.android.weathersdk.server.network.model.SClouds;
import com.coveiot.android.weathersdk.server.network.model.SCoord;
import com.coveiot.android.weathersdk.server.network.model.SCurrentForecastModel;
import com.coveiot.android.weathersdk.server.network.model.SMain;
import com.coveiot.android.weathersdk.server.network.model.SWeather;
import com.coveiot.android.weathersdk.server.network.model.SWind;
import com.coveiot.android.weathersdk.server.network.model.Sys;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.material.color.c;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 =2\u00020\u0001:\u0001=B\u0011\b\u0002\u0012\u0006\u0010:\u001a\u000205¢\u0006\u0004\b;\u0010<J\u001d\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J=\u0010\u0010\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u0010\u0010\u0011J3\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u0013\u0010\u0014JC\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b\u0013\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010 \u001a\u00020\u001f¢\u0006\u0004\b \u0010!J\r\u0010\"\u001a\u00020\u0005¢\u0006\u0004\b\"\u0010#J=\u0010$\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b$\u0010\u0011JC\u0010%\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b%\u0010\u0017JC\u0010(\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\n2\u0014\u0010\u000f\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010'\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\u0004\b(\u0010\u0017J\u001b\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0)H\u0002¢\u0006\u0004\b*\u0010+R\u001e\u0010.\u001a\n ,*\u0004\u0018\u00010\n0\n8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010-R\u0018\u00102\u001a\u0004\u0018\u00010/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00104\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u0010-R\u0019\u0010:\u001a\u0002058\u0006@\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109¨\u0006>"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherApiCallsManager;", "", "", "lat", "lng", "", "setLatLong", "(DD)V", "latitude", "longitude", "", "unit", "Lcom/coveiot/android/weathersdk/server/listener/WeatherApiResponseListener;", "Lcom/coveiot/android/weathersdk/response/currentweathermodel/CurrentForecastModel;", "Lcom/coveiot/android/weathersdk/WeatherApiErrorModel;", "l", "getCurrentWeatherInfo", "(DDLjava/lang/String;Lcom/coveiot/android/weathersdk/server/listener/WeatherApiResponseListener;)V", "Lcom/coveiot/android/weathersdk/response/forecastmodel/WeatherForecastModel;", "getWeatherForecastInfo", "(DDLcom/coveiot/android/weathersdk/server/listener/WeatherApiResponseListener;)V", "", "daysCount", "(DDILjava/lang/String;Lcom/coveiot/android/weathersdk/server/listener/WeatherApiResponseListener;)V", "apiKey", "Lcom/coveiot/android/weathersdk/WeatherAPIType;", "weatherAPIType", "initWeatherApiClient", "(Ljava/lang/String;Lcom/coveiot/android/weathersdk/WeatherAPIType;)V", "getWeatherApiCallsManager", "()Lcom/coveiot/android/weathersdk/WeatherApiCallsManager;", "", "isInitialized", "()Z", "invalidate", "()V", "getOpenWeatherMapCurrentWeatherInfo", "getOpenWeatherMapDailyForecastInfo", "hoursCount", "Lcom/coveiot/android/weathersdk/response/hourlyforecast/HourlyWeatherForecastModel;", "getOpenWeatherMapHourlyForecastInfo", "Ljava/util/HashMap;", "a", "()Ljava/util/HashMap;", "kotlin.jvm.PlatformType", "Ljava/lang/String;", "TAG", "Lcom/coveiot/android/weathersdk/server/OpenWeatherApiService;", "b", "Lcom/coveiot/android/weathersdk/server/OpenWeatherApiService;", "openWeatherApiService", c.f10260a, "weatherApiKey", "Landroid/content/Context;", "d", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Companion", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherApiCallsManager {
    public static final Companion Companion = new Companion(null);

    /* renamed from: a  reason: collision with root package name */
    public final String f6195a;
    public OpenWeatherApiService b;
    public String c;
    @NotNull
    public final Context d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherApiCallsManager$Companion;", "Lcom/coveiot/android/weathersdk/server/SingletonHolder;", "Lcom/coveiot/android/weathersdk/WeatherApiCallsManager;", "Landroid/content/Context;", "<init>", "()V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public static final class Companion extends SingletonHolder<WeatherApiCallsManager, Context> {

        /* loaded from: classes8.dex */
        public static final /* synthetic */ class a extends FunctionReferenceImpl implements Function1<Context, WeatherApiCallsManager> {

            /* renamed from: a  reason: collision with root package name */
            public static final a f6196a = new a();

            public a() {
                super(1, WeatherApiCallsManager.class, "<init>", "<init>(Landroid/content/Context;)V", 0);
            }

            @Override // kotlin.jvm.functions.Function1
            public WeatherApiCallsManager invoke(Context context) {
                Context p1 = context;
                Intrinsics.checkNotNullParameter(p1, "p1");
                return new WeatherApiCallsManager(p1, null);
            }
        }

        public Companion() {
            super(a.f6196a);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public WeatherApiCallsManager(Context context) {
        this.d = context;
        this.f6195a = WeatherApiCallsManager.class.getSimpleName();
    }

    public /* synthetic */ WeatherApiCallsManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public static final void access$parseCurrentWeatherResponse(WeatherApiCallsManager weatherApiCallsManager, Response response, WeatherApiResponseListener weatherApiResponseListener) {
        weatherApiCallsManager.getClass();
        if (!response.isSuccessful() || response.body() == null) {
            WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
            weatherApiErrorModel.setCode(Integer.valueOf(response.code()));
            ResponseBody errorBody = response.errorBody();
            weatherApiErrorModel.setResponse(errorBody != null ? errorBody.toString() : null);
            weatherApiResponseListener.onError(weatherApiErrorModel);
            return;
        }
        Object body = response.body();
        Intrinsics.checkNotNull(body);
        Intrinsics.checkNotNullExpressionValue(body, "response.body()!!");
        SCurrentForecastModel sCurrentForecastModel = (SCurrentForecastModel) body;
        CurrentForecastModel currentForecastModel = new CurrentForecastModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 134217727, null);
        SClouds clouds = sCurrentForecastModel.getClouds();
        Intrinsics.checkNotNullExpressionValue(clouds, "serverRes.clouds");
        currentForecastModel.setClouds(clouds.getAll());
        currentForecastModel.setCod(sCurrentForecastModel.getCod());
        if (sCurrentForecastModel.getCoord() != null) {
            SCoord coord = sCurrentForecastModel.getCoord();
            Intrinsics.checkNotNullExpressionValue(coord, "serverRes.coord");
            currentForecastModel.setLat(coord.getLat());
            SCoord coord2 = sCurrentForecastModel.getCoord();
            Intrinsics.checkNotNullExpressionValue(coord2, "serverRes.coord");
            currentForecastModel.setLon(coord2.getLon());
        }
        currentForecastModel.setBase(sCurrentForecastModel.getBase());
        currentForecastModel.setDt(sCurrentForecastModel.getDt());
        long j = 1000;
        currentForecastModel.setDate(new Date(sCurrentForecastModel.getDt().longValue() * j));
        currentForecastModel.setId(sCurrentForecastModel.getId());
        currentForecastModel.setName(sCurrentForecastModel.getName());
        currentForecastModel.setTimezone(sCurrentForecastModel.getTimezone());
        currentForecastModel.setVisibility(sCurrentForecastModel.getVisibility());
        if (sCurrentForecastModel.getWind() != null) {
            SWind wind = sCurrentForecastModel.getWind();
            Intrinsics.checkNotNullExpressionValue(wind, "serverRes.wind");
            currentForecastModel.setWindDeg(wind.getDeg());
            SWind wind2 = sCurrentForecastModel.getWind();
            Intrinsics.checkNotNullExpressionValue(wind2, "serverRes.wind");
            currentForecastModel.setWindSpeed(wind2.getSpeed());
        }
        ArrayList arrayList = new ArrayList();
        if (!AppUtils.isEmpty(sCurrentForecastModel.getWeather())) {
            for (Iterator<SWeather> it = sCurrentForecastModel.getWeather().iterator(); it.hasNext(); it = it) {
                SWeather sWeather = it.next();
                CurrentWeather currentWeather = new CurrentWeather(null, null, null, null, null, 31, null);
                Intrinsics.checkNotNullExpressionValue(sWeather, "sWeather");
                currentWeather.setId(sWeather.getId());
                currentWeather.setDescription(sWeather.getDescription());
                currentWeather.setIcon(sWeather.getIcon());
                currentWeather.setMain(sWeather.getMain());
                WeatherConditionType.Companion companion = WeatherConditionType.Companion;
                String main = sWeather.getMain();
                Intrinsics.checkNotNullExpressionValue(main, "sWeather.main");
                currentWeather.setIconType(companion.getWeatherType(main));
                arrayList.add(currentWeather);
            }
        }
        currentForecastModel.setCurrentWeather(arrayList);
        if (sCurrentForecastModel.getMain() != null) {
            SMain main2 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main2, "serverRes.main");
            currentForecastModel.setFeelsLike(main2.getFeelsLike());
            SMain main3 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main3, "serverRes.main");
            currentForecastModel.setHumidity(main3.getHumidity());
            SMain main4 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main4, "serverRes.main");
            currentForecastModel.setPressure(main4.getPressure());
            SMain main5 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main5, "serverRes.main");
            currentForecastModel.setTemp(main5.getTemp());
            SMain main6 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main6, "serverRes.main");
            currentForecastModel.setTempMax(main6.getTempMax());
            SMain main7 = sCurrentForecastModel.getMain();
            Intrinsics.checkNotNullExpressionValue(main7, "serverRes.main");
            currentForecastModel.setTempMin(main7.getTempMin());
        }
        if (sCurrentForecastModel.getSys() != null) {
            Sys sys = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys, "serverRes.sys");
            currentForecastModel.setSysCountry(sys.getCountry());
            Sys sys2 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys2, "serverRes.sys");
            currentForecastModel.setSysId(sys2.getId());
            Sys sys3 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys3, "serverRes.sys");
            currentForecastModel.setSunrise(sys3.getSunrise());
            Sys sys4 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys4, "serverRes.sys");
            currentForecastModel.setSunriseDate(new Date(sys4.getSunrise().longValue() * j));
            Sys sys5 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys5, "serverRes.sys");
            currentForecastModel.setSunset(sys5.getSunset());
            Sys sys6 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys6, "serverRes.sys");
            currentForecastModel.setSunsetDate(new Date(sys6.getSunset().longValue() * j));
            Sys sys7 = sCurrentForecastModel.getSys();
            Intrinsics.checkNotNullExpressionValue(sys7, "serverRes.sys");
            currentForecastModel.setSysType(sys7.getType());
        }
        WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
        Context context = weatherApiCallsManager.d;
        Intrinsics.checkNotNull(context);
        WeatherPreferenceManager companion3 = companion2.getInstance(context);
        Intrinsics.checkNotNull(companion3);
        companion3.saveCurrentWeatherModel(currentForecastModel);
        weatherApiResponseListener.onSuccess(currentForecastModel);
    }

    public static final void access$parseWeatherForecastResponse(WeatherApiCallsManager weatherApiCallsManager, Response response, WeatherApiResponseListener weatherApiResponseListener) {
        weatherApiCallsManager.getClass();
        if (!response.isSuccessful() || response.body() == null) {
            WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
            weatherApiErrorModel.setCode(Integer.valueOf(response.code()));
            ResponseBody errorBody = response.errorBody();
            weatherApiErrorModel.setResponse(errorBody != null ? errorBody.toString() : null);
            weatherApiResponseListener.onError(weatherApiErrorModel);
            return;
        }
        Object body = response.body();
        Intrinsics.checkNotNull(body);
        Intrinsics.checkNotNullExpressionValue(body, "response.body()!!");
        SWeatherForecastModel sWeatherForecastModel = (SWeatherForecastModel) body;
        WeatherForecastModel weatherForecastModel = new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null);
        SCity city = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city, "serverRes.city");
        weatherForecastModel.setCountry(city.getCountry());
        SCity city2 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city2, "serverRes.city");
        weatherForecastModel.setCityId(city2.getId());
        SCity city3 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city3, "serverRes.city");
        weatherForecastModel.setCityName(city3.getName());
        SCity city4 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city4, "serverRes.city");
        weatherForecastModel.setPopulation(city4.getPopulation());
        SCity city5 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city5, "serverRes.city");
        weatherForecastModel.setTimezone(city5.getTimezone());
        SCity city6 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city6, "serverRes.city");
        SCoord coord = city6.getCoord();
        Intrinsics.checkNotNullExpressionValue(coord, "serverRes.city.coord");
        weatherForecastModel.setCityLat(coord.getLat());
        SCity city7 = sWeatherForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city7, "serverRes.city");
        SCoord coord2 = city7.getCoord();
        Intrinsics.checkNotNullExpressionValue(coord2, "serverRes.city.coord");
        weatherForecastModel.setCitylon(coord2.getLon());
        weatherForecastModel.setCnt(sWeatherForecastModel.getCnt());
        weatherForecastModel.setCod(sWeatherForecastModel.getCod());
        weatherForecastModel.setMessage(sWeatherForecastModel.getMessage());
        ArrayList arrayList = new ArrayList();
        for (SWeatherDetailList sWeatherDetail : sWeatherForecastModel.getWeatherDetilList()) {
            WeatherDetails weatherDetails = new WeatherDetails(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 67108863, null);
            Intrinsics.checkNotNullExpressionValue(sWeatherDetail, "sWeatherDetail");
            weatherDetails.setDateTimeSatmp(sWeatherDetail.getDt());
            long j = 1000;
            weatherDetails.setDate(new Date(sWeatherDetail.getDt().longValue() * j));
            weatherDetails.setSunrise(sWeatherDetail.getSunrise());
            weatherDetails.setSunriseDate(new Date(sWeatherDetail.getSunrise().longValue() * j));
            weatherDetails.setSunset(sWeatherDetail.getSunset());
            weatherDetails.setSunsetDate(new Date(sWeatherDetail.getSunset().longValue() * j));
            STemp temp = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp, "sWeatherDetail.temp");
            weatherDetails.setDayTemp(temp.getDay());
            STemp temp2 = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp2, "sWeatherDetail.temp");
            weatherDetails.setMinTemp(temp2.getMin());
            STemp temp3 = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp3, "sWeatherDetail.temp");
            weatherDetails.setMaxTemp(temp3.getMax());
            STemp temp4 = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp4, "sWeatherDetail.temp");
            weatherDetails.setNightTemp(temp4.getNight());
            STemp temp5 = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp5, "sWeatherDetail.temp");
            weatherDetails.setEveningTemp(temp5.getNight());
            STemp temp6 = sWeatherDetail.getTemp();
            Intrinsics.checkNotNullExpressionValue(temp6, "sWeatherDetail.temp");
            weatherDetails.setMornTemp(temp6.getMorn());
            SFeelsLike feelsLike = sWeatherDetail.getFeelsLike();
            Intrinsics.checkNotNullExpressionValue(feelsLike, "sWeatherDetail.feelsLike");
            weatherDetails.setDayFeelsLike(feelsLike.getDay());
            SFeelsLike feelsLike2 = sWeatherDetail.getFeelsLike();
            Intrinsics.checkNotNullExpressionValue(feelsLike2, "sWeatherDetail.feelsLike");
            weatherDetails.setNightFeelsLike(feelsLike2.getNight());
            SFeelsLike feelsLike3 = sWeatherDetail.getFeelsLike();
            Intrinsics.checkNotNullExpressionValue(feelsLike3, "sWeatherDetail.feelsLike");
            weatherDetails.setEveFeelsLike(feelsLike3.getEve());
            SFeelsLike feelsLike4 = sWeatherDetail.getFeelsLike();
            Intrinsics.checkNotNullExpressionValue(feelsLike4, "sWeatherDetail.feelsLike");
            weatherDetails.setMornFeelsLike(feelsLike4.getMorn());
            weatherDetails.setPressure(sWeatherDetail.getPressure());
            weatherDetails.setHumidity(sWeatherDetail.getHumidity());
            weatherDetails.setSpeed(sWeatherDetail.getSpeed());
            weatherDetails.setDeg(sWeatherDetail.getDeg());
            weatherDetails.setGust(sWeatherDetail.getGust());
            weatherDetails.setClouds(sWeatherDetail.getClouds());
            weatherDetails.setPop(sWeatherDetail.getPop());
            weatherDetails.setRain(sWeatherDetail.getRain());
            weatherDetails.setSnow(sWeatherDetail.getSnow());
            ArrayList arrayList2 = new ArrayList();
            for (SWeather sWeather : sWeatherDetail.getWeather()) {
                Weather weather = new Weather(null, null, null, null, null, 31, null);
                Intrinsics.checkNotNullExpressionValue(sWeather, "sWeather");
                weather.setDescription(sWeather.getDescription());
                weather.setIcon(sWeather.getDescription());
                weather.setId(sWeather.getId());
                weather.setMain(sWeather.getMain());
                WeatherConditionType.Companion companion = WeatherConditionType.Companion;
                String main = weather.getMain();
                Intrinsics.checkNotNull(main);
                weather.setIconType(companion.getWeatherType(main));
                arrayList2.add(weather);
            }
            weatherDetails.setWeather(arrayList2);
            arrayList.add(weatherDetails);
        }
        weatherForecastModel.setWeatherDetailList(arrayList);
        WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
        Context context = weatherApiCallsManager.d;
        Intrinsics.checkNotNull(context);
        WeatherPreferenceManager companion3 = companion2.getInstance(context);
        Intrinsics.checkNotNull(companion3);
        companion3.saveWeatherForecastModel(weatherForecastModel);
        weatherApiResponseListener.onSuccess(weatherForecastModel);
    }

    public static final void access$parseWeatherHourlyForecastResponse(WeatherApiCallsManager weatherApiCallsManager, Response response, WeatherApiResponseListener weatherApiResponseListener) {
        weatherApiCallsManager.getClass();
        if (!response.isSuccessful() || response.body() == null) {
            WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
            weatherApiErrorModel.setCode(Integer.valueOf(response.code()));
            ResponseBody errorBody = response.errorBody();
            weatherApiErrorModel.setResponse(errorBody != null ? errorBody.toString() : null);
            weatherApiResponseListener.onError(weatherApiErrorModel);
            return;
        }
        Object body = response.body();
        Intrinsics.checkNotNull(body);
        Intrinsics.checkNotNullExpressionValue(body, "response.body()!!");
        SHourlyForecastModel sHourlyForecastModel = (SHourlyForecastModel) body;
        HourlyWeatherForecastModel hourlyWeatherForecastModel = new HourlyWeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
        SCity city = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city, "serverRes.city");
        hourlyWeatherForecastModel.setCountry(city.getCountry());
        SCity city2 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city2, "serverRes.city");
        hourlyWeatherForecastModel.setCityId(city2.getId());
        SCity city3 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city3, "serverRes.city");
        hourlyWeatherForecastModel.setCityName(city3.getName());
        SCity city4 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city4, "serverRes.city");
        hourlyWeatherForecastModel.setPopulation(city4.getPopulation());
        SCity city5 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city5, "serverRes.city");
        hourlyWeatherForecastModel.setTimezone(city5.getTimezone());
        SCity city6 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city6, "serverRes.city");
        SCoord coord = city6.getCoord();
        Intrinsics.checkNotNullExpressionValue(coord, "serverRes.city.coord");
        hourlyWeatherForecastModel.setCityLat(coord.getLat());
        SCity city7 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city7, "serverRes.city");
        SCoord coord2 = city7.getCoord();
        Intrinsics.checkNotNullExpressionValue(coord2, "serverRes.city.coord");
        hourlyWeatherForecastModel.setCitylon(coord2.getLon());
        hourlyWeatherForecastModel.setCnt(sHourlyForecastModel.getCnt());
        hourlyWeatherForecastModel.setCod(sHourlyForecastModel.getCod());
        SCity city8 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city8, "serverRes.city");
        hourlyWeatherForecastModel.setTimezone(city8.getTimezone());
        SCity city9 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city9, "serverRes.city");
        hourlyWeatherForecastModel.setSunrise(city9.getSunrise());
        SCity city10 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city10, "serverRes.city");
        long j = 1000;
        hourlyWeatherForecastModel.setSunriseDate(new Date(city10.getSunrise().longValue() * j));
        SCity city11 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city11, "serverRes.city");
        hourlyWeatherForecastModel.setSunset(city11.getSunset());
        SCity city12 = sHourlyForecastModel.getCity();
        Intrinsics.checkNotNullExpressionValue(city12, "serverRes.city");
        hourlyWeatherForecastModel.setSunsetDate(new Date(city12.getSunset().longValue() * j));
        ArrayList arrayList = new ArrayList();
        for (SHourlyWeatherDetailList sWeatherDetail : sHourlyForecastModel.getHourlyWeatherDetailList()) {
            HourlyWeatherDetails hourlyWeatherDetails = new HourlyWeatherDetails(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
            Intrinsics.checkNotNullExpressionValue(sWeatherDetail, "sWeatherDetail");
            hourlyWeatherDetails.setDateTimeSatmp(sWeatherDetail.getDt());
            hourlyWeatherDetails.setDate(new Date(sWeatherDetail.getDt().longValue() * j));
            hourlyWeatherDetails.setDateTimeTxt(sWeatherDetail.getDateAndTimeText());
            SMain main = sWeatherDetail.getMain();
            Intrinsics.checkNotNullExpressionValue(main, "sWeatherDetail.main");
            hourlyWeatherDetails.setTemp(main.getTemp());
            SMain main2 = sWeatherDetail.getMain();
            Intrinsics.checkNotNullExpressionValue(main2, "sWeatherDetail.main");
            hourlyWeatherDetails.setMinTemp(main2.getTempMin());
            SMain main3 = sWeatherDetail.getMain();
            Intrinsics.checkNotNullExpressionValue(main3, "sWeatherDetail.main");
            hourlyWeatherDetails.setMaxTemp(main3.getTempMax());
            SMain main4 = sWeatherDetail.getMain();
            Intrinsics.checkNotNullExpressionValue(main4, "sWeatherDetail.main");
            hourlyWeatherDetails.setPressure(main4.getPressure());
            SMain main5 = sWeatherDetail.getMain();
            Intrinsics.checkNotNullExpressionValue(main5, "sWeatherDetail.main");
            hourlyWeatherDetails.setHumidity(main5.getHumidity());
            SWind wind = sWeatherDetail.getWind();
            Intrinsics.checkNotNullExpressionValue(wind, "sWeatherDetail.wind");
            hourlyWeatherDetails.setSpeed(wind.getSpeed());
            SWind wind2 = sWeatherDetail.getWind();
            Intrinsics.checkNotNullExpressionValue(wind2, "sWeatherDetail.wind");
            hourlyWeatherDetails.setDeg(wind2.getDeg());
            SWind wind3 = sWeatherDetail.getWind();
            Intrinsics.checkNotNullExpressionValue(wind3, "sWeatherDetail.wind");
            hourlyWeatherDetails.setGust(wind3.getGust());
            hourlyWeatherDetails.setPop(sWeatherDetail.getPop());
            ArrayList arrayList2 = new ArrayList();
            for (SWeather sWeather : sWeatherDetail.getWeather()) {
                Weather weather = new Weather(null, null, null, null, null, 31, null);
                Intrinsics.checkNotNullExpressionValue(sWeather, "sWeather");
                weather.setDescription(sWeather.getDescription());
                weather.setIcon(sWeather.getDescription());
                weather.setId(sWeather.getId());
                weather.setMain(sWeather.getMain());
                WeatherConditionType.Companion companion = WeatherConditionType.Companion;
                String main6 = weather.getMain();
                Intrinsics.checkNotNull(main6);
                weather.setIconType(companion.getWeatherType(main6));
                arrayList2.add(weather);
            }
            hourlyWeatherDetails.setWeather(arrayList2);
            arrayList.add(hourlyWeatherDetails);
        }
        hourlyWeatherForecastModel.setWeatherDetailList(arrayList);
        WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
        Context context = weatherApiCallsManager.d;
        Intrinsics.checkNotNull(context);
        WeatherPreferenceManager companion3 = companion2.getInstance(context);
        Intrinsics.checkNotNull(companion3);
        companion3.saveHourlyWeatherForecastModel(hourlyWeatherForecastModel);
        weatherApiResponseListener.onSuccess(hourlyWeatherForecastModel);
    }

    public final HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap<>();
        String x_RapidAPI_Key = WeatherConstants.INSTANCE.getX_RapidAPI_Key();
        String str = this.c;
        Intrinsics.checkNotNull(str);
        hashMap.put(x_RapidAPI_Key, str);
        return hashMap;
    }

    @NotNull
    public final Context getContext() {
        return this.d;
    }

    public final void getCurrentWeatherInfo(double d, double d2, @Nullable String str, @NotNull final WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel> l) {
        Call<SCurrentForecastModel> currentWeatherUpdates;
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (currentWeatherUpdates = openWeatherApiService.getCurrentWeatherUpdates(a(), d, d2, str)) == null) {
            return;
        }
        currentWeatherUpdates.enqueue(new Callback<SCurrentForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getCurrentWeatherInfo$1
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SCurrentForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SCurrentForecastModel> call, @NotNull Response<SCurrentForecastModel> response) {
                String str2;
                Intrinsics.checkNotNullParameter(response, "response");
                str2 = WeatherApiCallsManager.this.f6195a;
                LogHelper.d(str2, "getCurrentWeatherInfo success");
                WeatherApiCallsManager.access$parseCurrentWeatherResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    public final void getOpenWeatherMapCurrentWeatherInfo(double d, double d2, @Nullable String str, @NotNull final WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel> l) {
        Call<SCurrentForecastModel> openWeatherMapCurrentWeatherUpdates;
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (openWeatherMapCurrentWeatherUpdates = openWeatherApiService.getOpenWeatherMapCurrentWeatherUpdates(WeatherConstants.INSTANCE.getOPEN_WEATHER_MAP_CURRENT_BASE_URL(), d, d2, str, this.c)) == null) {
            return;
        }
        openWeatherMapCurrentWeatherUpdates.enqueue(new Callback<SCurrentForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getOpenWeatherMapCurrentWeatherInfo$1
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SCurrentForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SCurrentForecastModel> call, @NotNull Response<SCurrentForecastModel> response) {
                String str2;
                Intrinsics.checkNotNullParameter(response, "response");
                str2 = WeatherApiCallsManager.this.f6195a;
                LogHelper.d(str2, "getCurrentWeatherInfo success");
                WeatherApiCallsManager.access$parseCurrentWeatherResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    public final void getOpenWeatherMapDailyForecastInfo(double d, double d2, int i, @NotNull String unit, @NotNull final WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel> l) {
        Call<SWeatherForecastModel> openWeatherMapDailyForecastUpdates;
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (openWeatherMapDailyForecastUpdates = openWeatherApiService.getOpenWeatherMapDailyForecastUpdates(WeatherConstants.INSTANCE.getOPEN_WEATHER_MAP_DAILY_BASE_URL(), d, d2, Integer.valueOf(i), unit, this.c)) == null) {
            return;
        }
        openWeatherMapDailyForecastUpdates.enqueue(new Callback<SWeatherForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getOpenWeatherMapDailyForecastInfo$1
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SWeatherForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SWeatherForecastModel> call, @NotNull Response<SWeatherForecastModel> response) {
                Intrinsics.checkNotNullParameter(response, "response");
                WeatherApiCallsManager.access$parseWeatherForecastResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    public final void getOpenWeatherMapHourlyForecastInfo(double d, double d2, int i, @NotNull String unit, @NotNull final WeatherApiResponseListener<HourlyWeatherForecastModel, WeatherApiErrorModel> l) {
        Call<SHourlyForecastModel> openWeatherMapHourlyForecastUpdates;
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (openWeatherMapHourlyForecastUpdates = openWeatherApiService.getOpenWeatherMapHourlyForecastUpdates(WeatherConstants.INSTANCE.getOPEN_WEATHER_MAP_HOURLY_BASE_URL(), d, d2, Integer.valueOf(i), unit, this.c)) == null) {
            return;
        }
        openWeatherMapHourlyForecastUpdates.enqueue(new Callback<SHourlyForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getOpenWeatherMapHourlyForecastInfo$1
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SHourlyForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SHourlyForecastModel> call, @NotNull Response<SHourlyForecastModel> response) {
                Intrinsics.checkNotNullParameter(response, "response");
                WeatherApiCallsManager.access$parseWeatherHourlyForecastResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    @Nullable
    public final WeatherApiCallsManager getWeatherApiCallsManager() {
        return Companion.getInstance(this.d);
    }

    public final void getWeatherForecastInfo(double d, double d2, int i, @NotNull String unit, @NotNull final WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel> l) {
        Call<SWeatherForecastModel> weatherForecastUpdates;
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (weatherForecastUpdates = openWeatherApiService.getWeatherForecastUpdates(a(), d, d2, Integer.valueOf(i), unit)) == null) {
            return;
        }
        weatherForecastUpdates.enqueue(new Callback<SWeatherForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getWeatherForecastInfo$2
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SWeatherForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SWeatherForecastModel> call, @NotNull Response<SWeatherForecastModel> response) {
                Intrinsics.checkNotNullParameter(response, "response");
                WeatherApiCallsManager.access$parseWeatherForecastResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    public final void getWeatherForecastInfo(double d, double d2, @NotNull final WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel> l) {
        Call<SWeatherForecastModel> weatherForecastUpdates;
        Intrinsics.checkNotNullParameter(l, "l");
        OpenWeatherApiService openWeatherApiService = this.b;
        if (openWeatherApiService == null || (weatherForecastUpdates = openWeatherApiService.getWeatherForecastUpdates(a(), d, d2)) == null) {
            return;
        }
        weatherForecastUpdates.enqueue(new Callback<SWeatherForecastModel>() { // from class: com.coveiot.android.weathersdk.WeatherApiCallsManager$getWeatherForecastInfo$1
            @Override // retrofit2.Callback
            public void onFailure(@Nullable Call<SWeatherForecastModel> call, @Nullable Throwable th) {
                WeatherApiErrorModel weatherApiErrorModel = new WeatherApiErrorModel();
                weatherApiErrorModel.setResponse(String.valueOf(th));
                l.onError(weatherApiErrorModel);
            }

            @Override // retrofit2.Callback
            public void onResponse(@Nullable Call<SWeatherForecastModel> call, @NotNull Response<SWeatherForecastModel> response) {
                Intrinsics.checkNotNullParameter(response, "response");
                WeatherApiCallsManager.access$parseWeatherForecastResponse(WeatherApiCallsManager.this, response, l);
            }
        });
    }

    public final void initWeatherApiClient(@NotNull String apiKey, @NotNull WeatherAPIType weatherAPIType) {
        Intrinsics.checkNotNullParameter(apiKey, "apiKey");
        Intrinsics.checkNotNullParameter(weatherAPIType, "weatherAPIType");
        this.c = apiKey;
        WeatherApiClient.Companion companion = WeatherApiClient.Companion;
        Context context = this.d;
        Intrinsics.checkNotNull(context);
        this.b = (OpenWeatherApiService) companion.getInstance(context).getWeatherClientService(weatherAPIType).create(OpenWeatherApiService.class);
    }

    public final void invalidate() {
        this.c = null;
    }

    public final boolean isInitialized() {
        String str = this.c;
        if (str != null) {
            Integer valueOf = Integer.valueOf(str.length());
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                return true;
            }
        }
        return false;
    }

    public final void setLatLong(double d, double d2) {
    }
}
