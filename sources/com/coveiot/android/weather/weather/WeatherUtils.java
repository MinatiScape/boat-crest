package com.coveiot.android.weather.weather;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.weather.R;
import com.coveiot.android.weather.response.forecastmodel.WeatherDesc;
import com.coveiot.android.weather.response.forecastmodel.WeatherDetail;
import com.coveiot.android.weather.response.forecastmodel.WeatherModel;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherApiErrorModel;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.response.currentweathermodel.CurrentForecastModel;
import com.coveiot.android.weathersdk.response.forecastmodel.Weather;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherDetails;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.response.hourlyforecast.HourlyWeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.m;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.e;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class WeatherUtils {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6178a;

    /* loaded from: classes8.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WeatherConditionType.WeatherConditionEnum.values().length];
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Thunderstorm.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Clouds.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Clear_Sunny.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Drizzle.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Mist.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Rain.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[WeatherConditionType.WeatherConditionEnum.Snow.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.weather.weather.WeatherUtils$getLocationAndCallWeatherAPI$1", f = "WeatherUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes8.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Context $context;
        public int label;
        public final /* synthetic */ WeatherUtils this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, WeatherUtils weatherUtils, Continuation<? super a> continuation) {
            super(2, continuation);
            this.$context = context;
            this.this$0 = weatherUtils;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(this.$context, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (WeatherApiCallsManager.Companion.getInstance(this.$context).isInitialized()) {
                    WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
                    WeatherPreferenceManager companion2 = companion.getInstance(this.$context);
                    Intrinsics.checkNotNull(companion2);
                    int weatherInDays = companion2.getWeatherInDays();
                    WeatherPreferenceManager companion3 = companion.getInstance(this.$context);
                    Intrinsics.checkNotNull(companion3);
                    String weatherUnit = companion3.getWeatherUnit();
                    String[] c = this.this$0.c(this.$context);
                    if (c != null && c[0] != null) {
                        LogHelper.i("Weather", "lastLocation: " + c);
                        String str = c[0];
                        Intrinsics.checkNotNull(str);
                        double parseDouble = Double.parseDouble(((String[]) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).toArray(new String[0]))[0]);
                        String str2 = c[0];
                        Intrinsics.checkNotNull(str2);
                        this.this$0.a(parseDouble, Double.parseDouble(((String[]) StringsKt__StringsKt.split$default((CharSequence) str2, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).toArray(new String[0]))[1]), weatherInDays, weatherUnit, this.$context);
                    } else {
                        LogHelper.i("Weather", "lastLocation: null");
                    }
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public WeatherUtils(@NotNull Context mContext) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.f6178a = mContext;
    }

    public final void a(final double d, final double d2, int i, final String str, final Context context) {
        WeatherApiCallsManager weatherApiCallsManager = WeatherApiCallsManager.Companion.getInstance(context).getWeatherApiCallsManager();
        Intrinsics.checkNotNull(weatherApiCallsManager);
        weatherApiCallsManager.getOpenWeatherMapDailyForecastInfo(d, d2, i, str, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.WeatherUtils$getCurrentWeatherInfo$1
            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onError(@NotNull WeatherApiErrorModel obj) {
                Intrinsics.checkNotNullParameter(obj, "obj");
                LogHelper.d("Weather", "getWeatherForecastInfo onError " + obj);
            }

            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                LogHelper.d("Weather", "getWeatherForecastInfo success " + weatherForecastModel);
                if (weatherForecastModel != null) {
                    WeatherPreferenceManager companion = WeatherPreferenceManager.Companion.getInstance(context);
                    Intrinsics.checkNotNull(companion);
                    companion.saveWeatherForecastModel(weatherForecastModel);
                    WeatherApiCallsManager singletonHolder = WeatherApiCallsManager.Companion.getInstance(context);
                    final double d3 = d;
                    final double d4 = d2;
                    final String str2 = str;
                    final Context context2 = context;
                    final WeatherUtils weatherUtils = this;
                    singletonHolder.getOpenWeatherMapCurrentWeatherInfo(d3, d4, str2, new WeatherApiResponseListener<CurrentForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.WeatherUtils$getCurrentWeatherInfo$1$onSuccess$1
                        @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                        public void onError(@NotNull WeatherApiErrorModel obj) {
                            Intrinsics.checkNotNullParameter(obj, "obj");
                        }

                        @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                        public void onSuccess(@Nullable CurrentForecastModel currentForecastModel) {
                            WeatherPreferenceManager companion2 = WeatherPreferenceManager.Companion.getInstance(context2);
                            Intrinsics.checkNotNull(companion2);
                            companion2.saveCurrentWeatherModel(currentForecastModel);
                            if (weatherUtils.isIDODevice(context2)) {
                                WeatherApiCallsManager singletonHolder2 = WeatherApiCallsManager.Companion.getInstance(context2);
                                double d5 = d3;
                                double d6 = d4;
                                String str3 = str2;
                                final Context context3 = context2;
                                final WeatherUtils weatherUtils2 = weatherUtils;
                                singletonHolder2.getOpenWeatherMapHourlyForecastInfo(d5, d6, 48, str3, new WeatherApiResponseListener<HourlyWeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weather.weather.WeatherUtils$getCurrentWeatherInfo$1$onSuccess$1$onSuccess$1
                                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                                    public void onError(@NotNull WeatherApiErrorModel obj) {
                                        Intrinsics.checkNotNullParameter(obj, "obj");
                                    }

                                    @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
                                    public void onSuccess(@Nullable HourlyWeatherForecastModel hourlyWeatherForecastModel) {
                                        WeatherPreferenceManager companion3 = WeatherPreferenceManager.Companion.getInstance(context3);
                                        Intrinsics.checkNotNull(companion3);
                                        companion3.saveHourlyWeatherForecastModel(hourlyWeatherForecastModel);
                                        weatherUtils2.sendWeatherToBle(context3);
                                    }
                                });
                                return;
                            }
                            weatherUtils.sendWeatherToBle(context2);
                        }
                    });
                }
            }
        });
    }

    public final int b(WeatherConditionType.WeatherConditionEnum weatherConditionEnum) {
        int i = WhenMappings.$EnumSwitchMapping$0[weatherConditionEnum.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 5) {
                        if (i != 6) {
                            return i != 7 ? 32001 : 32013;
                        }
                        return 32010;
                    }
                    return 32050;
                }
                return 32009;
            }
            return 32003;
        }
        return 32011;
    }

    public final String[] c(final Context context) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
        WeatherPreferenceManager companion2 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion2);
        final int weatherInDays = companion2.getWeatherInDays();
        WeatherPreferenceManager companion3 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion3);
        final String weatherUnit = companion3.getWeatherUnit();
        WeatherPreferenceManager companion4 = companion.getInstance(context);
        Intrinsics.checkNotNull(companion4);
        if (companion4.isWeatherEnabled()) {
            final String[] strArr = new String[1];
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
            Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(context)");
            if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                LogHelper.i("Weather", "getLastLocation(), Location permission not available ==== ");
                return null;
            }
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.weather.weather.WeatherUtils$lastLocation$1
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public void onSuccess(@Nullable Location location) {
                    if (location != null) {
                        WeatherPreferenceManager companion5 = WeatherPreferenceManager.Companion.getInstance(context);
                        Intrinsics.checkNotNull(companion5);
                        companion5.saveLastLocationLatLng(location);
                        String[] strArr2 = strArr;
                        StringBuilder sb = new StringBuilder();
                        sb.append(location.getLatitude());
                        sb.append(',');
                        sb.append(location.getLongitude());
                        strArr2[0] = sb.toString();
                        LogHelper.i("Weather", "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                        this.a(location.getLatitude(), location.getLongitude(), weatherInDays, weatherUnit, context);
                        return;
                    }
                    LogHelper.i("Weather", "getLastLocationLatLng onSuccess() location is NULL====");
                    WeatherPreferenceManager.Companion companion6 = WeatherPreferenceManager.Companion;
                    WeatherPreferenceManager companion7 = companion6.getInstance(context);
                    Intrinsics.checkNotNull(companion7);
                    String lastLocationLatLng = companion7.getLastLocationLatLng();
                    Intrinsics.checkNotNull(lastLocationLatLng);
                    if (lastLocationLatLng.length() == 0) {
                        return;
                    }
                    String[] strArr3 = strArr;
                    WeatherPreferenceManager companion8 = companion6.getInstance(context);
                    Intrinsics.checkNotNull(companion8);
                    strArr3[0] = companion8.getLastLocationLatLng();
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.weather.weather.WeatherUtils$lastLocation$2
                @Override // com.google.android.gms.tasks.OnFailureListener
                public void onFailure(@NotNull Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    LogHelper.i("Weather", "onFailure() in getLastLocation() ====");
                }
            });
            synchronized (reentrantLock) {
                while (TextUtils.isEmpty(strArr[0])) {
                    reentrantLock.lock();
                    try {
                        newCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reentrantLock.unlock();
                }
                Unit unit = Unit.INSTANCE;
            }
            return strArr;
        }
        return null;
    }

    public final int getDashboardWeatherDrawable(@NotNull WeatherConditionType.WeatherConditionEnum weatherConditionType) {
        Intrinsics.checkNotNullParameter(weatherConditionType, "weatherConditionType");
        switch (WhenMappings.$EnumSwitchMapping$0[weatherConditionType.ordinal()]) {
            case 1:
                return R.drawable.thunderstorm_small;
            case 2:
                return R.drawable.cloud_small;
            case 3:
                return R.drawable.clear_small;
            case 4:
                return R.drawable.drizzle_small;
            case 5:
                return R.drawable.mist_small;
            case 6:
                return R.drawable.rain_small;
            case 7:
                return R.drawable.snow_small;
            default:
                return R.drawable.mist_small;
        }
    }

    public final void getLocationAndCallWeatherAPI(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        e.e(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new a(context, this, null), 3, null);
    }

    @NotNull
    public final Context getMContext() {
        return this.f6178a;
    }

    @NotNull
    public final WeatherModel getWeatherForecastModel(@Nullable WeatherForecastModel weatherForecastModel) {
        WeatherModel weatherModel = new WeatherModel(null, null, null, null, null, null, 63, null);
        Intrinsics.checkNotNull(weatherForecastModel);
        weatherModel.setCnt(weatherForecastModel.getCnt());
        weatherModel.setCod(weatherForecastModel.getCod());
        weatherModel.setMessage(weatherForecastModel.getMessage());
        weatherModel.setCityId(weatherForecastModel.getCityId());
        weatherModel.setCityName(weatherForecastModel.getCityName());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            WeatherDetail weatherDetail = new WeatherDetail(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
            List<WeatherDetails> weatherDetailList = weatherForecastModel.getWeatherDetailList();
            Intrinsics.checkNotNull(weatherDetailList);
            WeatherDetails weatherDetails = weatherDetailList.get(i);
            weatherDetail.setDate(weatherDetails.getDate());
            weatherDetail.setSunriseDate(weatherDetails.getSunriseDate());
            weatherDetail.setSunsetDate(weatherDetails.getSunsetDate());
            weatherDetail.setPressure(weatherDetails.getPressure());
            weatherDetail.setHumidity(weatherDetails.getHumidity());
            weatherDetail.setSpeed(weatherDetails.getSpeed());
            weatherDetail.setDayTemp(weatherDetails.getDayTemp());
            weatherDetail.setNightTemp(weatherDetails.getNightTemp());
            weatherDetail.setEveTemp(weatherDetails.getEveningTemp());
            weatherDetail.setMorningTemp(weatherDetails.getMornTemp());
            weatherDetail.setMinTemp(weatherDetails.getMinTemp());
            weatherDetail.setMaxTemp(weatherDetails.getMaxTemp());
            ArrayList arrayList2 = new ArrayList();
            List<Weather> weather = weatherDetails.getWeather();
            Intrinsics.checkNotNull(weather);
            for (Weather weather2 : weather) {
                WeatherDesc weatherDesc = new WeatherDesc(null, null, null, null, null, 31, null);
                weatherDesc.setDescription(weather2.getDescription());
                weatherDesc.setIcon(weather2.getDescription());
                weatherDesc.setId(weather2.getId());
                weatherDesc.setMain(weather2.getMain());
                weatherDesc.setIconType(weather2.getIconType());
                arrayList2.add(weatherDesc);
            }
            weatherDetail.setWeather(arrayList2);
            arrayList.add(weatherDetail);
        }
        weatherModel.setWeatherDetailList(arrayList);
        return weatherModel;
    }

    @NotNull
    public final TimeSlot getWeatherTimeSlot() {
        int i = Calendar.getInstance().get(11);
        if (i <= 6) {
            return TimeSlot.MORNING;
        }
        if (i <= 6 || i > 12) {
            if (i > 12 && i <= 18) {
                return TimeSlot.EVENING;
            }
            return TimeSlot.NIGHT;
        }
        return TimeSlot.DAYTIME;
    }

    public final boolean isIDODevice(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_select), false) || m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.ido_connect), false);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x056f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0575  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sendWeatherToBle(@org.jetbrains.annotations.NotNull final android.content.Context r40) {
        /*
            Method dump skipped, instructions count: 1419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.weather.weather.WeatherUtils.sendWeatherToBle(android.content.Context):void");
    }
}
