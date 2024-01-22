package com.coveiot.android.weathersdk;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.weathersdk.WeatherApiCallsManager;
import com.coveiot.android.weathersdk.WeatherConditionType;
import com.coveiot.android.weathersdk.WeatherPreferenceManager;
import com.coveiot.android.weathersdk.response.forecastmodel.WeatherForecastModel;
import com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener;
import com.coveiot.utils.utility.LogHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.color.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jose4j.jwk.RsaJsonWebKey;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010(\u001a\u0004\u0018\u00010\u001e\u0012\b\u0010*\u001a\u0004\u0018\u00010)¢\u0006\u0004\b+\u0010,J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ/\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0015\u001a\u00020\u00138\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0014R\u0016\u0010\u0018\u001a\u00020\u000e8\u0002@\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001e\u0010\u001d\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00198\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010!\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010%\u001a\u0004\u0018\u00010\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010\u0017R\u0018\u0010'\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010#¨\u0006-"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherWorker;", "Landroidx/work/Worker;", "Landroidx/work/ListenableWorker$Result;", "doWork", "()Landroidx/work/ListenableWorker$Result;", "Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "weatherConditionType", "", "getWeatherIconID", "(Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;)I", "", "latitude", "longitude", "days", "", "unit", "", "a", "(DDILjava/lang/String;)V", "Ljava/util/concurrent/locks/ReentrantLock;", "Ljava/util/concurrent/locks/ReentrantLock;", "lock", "b", "Ljava/lang/String;", "TAG", "Ljava/util/concurrent/locks/Condition;", "kotlin.jvm.PlatformType", c.f10260a, "Ljava/util/concurrent/locks/Condition;", "condition", "Landroid/content/Context;", "d", "Landroid/content/Context;", "context", RsaJsonWebKey.EXPONENT_MEMBER_NAME, "Ljava/lang/Integer;", "f", "weatherUnit", "g", "intervalMinutes", "mContext", "Landroidx/work/WorkerParameters;", "params", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherWorker extends Worker {

    /* renamed from: a  reason: collision with root package name */
    public final ReentrantLock f6207a;
    public final String b;
    public final Condition c;
    public final Context d;
    public Integer e;
    public String f;
    public Integer g;

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            WeatherConditionType.WeatherConditionEnum.values();
            $EnumSwitchMapping$0 = r0;
            int[] iArr = {1, 4, 6, 7, 5, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2};
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeatherWorker(@Nullable Context context, @Nullable WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNull(workerParameters);
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f6207a = reentrantLock;
        this.b = "WeatherWorker";
        this.c = reentrantLock.newCondition();
        this.d = context;
        WeatherApiCallsManager.Companion companion = WeatherApiCallsManager.Companion;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        companion.getInstance(applicationContext).initWeatherApiClient(BuildConfig.WEATHER_API_KEY, WeatherAPIType.OPEN_WEATHER_RAPID);
    }

    public static final void access$weatherWorkRequest(WeatherWorker weatherWorker) {
        weatherWorker.getClass();
        Constraints build = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Intrinsics.checkNotNullExpressionValue(build, "Constraints.Builder()\n  …rkType.CONNECTED).build()");
        Data.Builder builder = new Data.Builder();
        WeatherConstants weatherConstants = WeatherConstants.INSTANCE;
        String days_count = weatherConstants.getDAYS_COUNT();
        Integer num = weatherWorker.e;
        Intrinsics.checkNotNull(num);
        builder.putInt(days_count, num.intValue());
        String weather_unit = weatherConstants.getWEATHER_UNIT();
        String str = weatherWorker.f;
        Intrinsics.checkNotNull(str);
        builder.putString(weather_unit, str);
        String weather_interval = weatherConstants.getWEATHER_INTERVAL();
        Integer num2 = weatherWorker.g;
        Intrinsics.checkNotNull(num2);
        builder.putInt(weather_interval, num2.intValue());
        String str2 = weatherWorker.b;
        LogHelper.i(str2, "weatherWorkRequest: weatherUnit === " + weatherWorker.f + "   days === " + weatherWorker.e + " intervalMinutes === " + weatherWorker.g);
        OneTimeWorkRequest.Builder builder2 = new OneTimeWorkRequest.Builder(WeatherWorker.class);
        Integer num3 = weatherWorker.g;
        Intrinsics.checkNotNull(num3);
        OneTimeWorkRequest build2 = builder2.setInitialDelay((long) num3.intValue(), TimeUnit.MINUTES).setInputData(builder.build()).setConstraints(build).build();
        Intrinsics.checkNotNullExpressionValue(build2, "OneTimeWorkRequestBuilde…ints(constraints).build()");
        WorkManager.getInstance().enqueue(build2);
        LogHelper.d("weatherWorkRequest", "Called");
    }

    public final void a(double d, double d2, int i, String str) {
        WeatherApiCallsManager.Companion companion = WeatherApiCallsManager.Companion;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WeatherApiCallsManager weatherApiCallsManager = companion.getInstance(applicationContext).getWeatherApiCallsManager();
        Intrinsics.checkNotNull(weatherApiCallsManager);
        weatherApiCallsManager.getWeatherForecastInfo(d, d2, i, str, new WeatherApiResponseListener<WeatherForecastModel, WeatherApiErrorModel>() { // from class: com.coveiot.android.weathersdk.WeatherWorker$getCurrentWeatherInfo$1
            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onError(@NotNull WeatherApiErrorModel obj) {
                String str2;
                Context context;
                Intrinsics.checkNotNullParameter(obj, "obj");
                str2 = WeatherWorker.this.b;
                LogHelper.d(str2, "getWeatherForecastInfo onError " + obj);
                WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
                context = WeatherWorker.this.d;
                Intrinsics.checkNotNull(context);
                WeatherPreferenceManager companion3 = companion2.getInstance(context);
                Intrinsics.checkNotNull(companion3);
                companion3.saveWeatherForecastModel(new WeatherForecastModel(null, null, null, null, null, null, null, null, null, null, null, 2047, null));
            }

            @Override // com.coveiot.android.weathersdk.server.listener.WeatherApiResponseListener
            public void onSuccess(@Nullable WeatherForecastModel weatherForecastModel) {
                String str2;
                Context context;
                str2 = WeatherWorker.this.b;
                LogHelper.d(str2, "getWeatherForecastInfo success " + weatherForecastModel);
                if (weatherForecastModel != null) {
                    WeatherPreferenceManager.Companion companion2 = WeatherPreferenceManager.Companion;
                    context = WeatherWorker.this.d;
                    Intrinsics.checkNotNull(context);
                    WeatherPreferenceManager companion3 = companion2.getInstance(context);
                    Intrinsics.checkNotNull(companion3);
                    companion3.saveWeatherForecastModel(weatherForecastModel);
                    WeatherWorker.access$weatherWorkRequest(WeatherWorker.this);
                }
            }
        });
    }

    @Override // androidx.work.Worker
    @NotNull
    public ListenableWorker.Result doWork() {
        ListenableWorker.Result retry;
        String str;
        WeatherPreferenceManager.Companion companion = WeatherPreferenceManager.Companion;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        WeatherPreferenceManager companion2 = companion.getInstance(applicationContext);
        Intrinsics.checkNotNull(companion2);
        this.e = Integer.valueOf(companion2.getWeatherInDays());
        Context applicationContext2 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
        WeatherPreferenceManager companion3 = companion.getInstance(applicationContext2);
        Intrinsics.checkNotNull(companion3);
        this.f = companion3.getWeatherUnit();
        Context applicationContext3 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext3, "applicationContext");
        WeatherPreferenceManager companion4 = companion.getInstance(applicationContext3);
        Intrinsics.checkNotNull(companion4);
        this.g = Integer.valueOf(companion4.getWeatherInterval());
        String str2 = this.b;
        LogHelper.i(str2, "doWork: weatherUnit === " + this.f + "   days === " + this.e + " intervalMinutes === " + this.g);
        Context applicationContext4 = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext4, "applicationContext");
        WeatherPreferenceManager companion5 = companion.getInstance(applicationContext4);
        Intrinsics.checkNotNull(companion5);
        String[] strArr = null;
        if (companion5.isWeatherEnabled()) {
            final String[] strArr2 = new String[1];
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
            Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "LocationServices.getFuse…lient(applicationContext)");
            if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                LogHelper.i(this.b, "getLastLocation(), Location permission not available ==== ");
            } else {
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() { // from class: com.coveiot.android.weathersdk.WeatherWorker$lastLocation$1
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(@Nullable Location location) {
                        String str3;
                        String str4;
                        Integer num;
                        String str5;
                        if (location == null) {
                            str3 = WeatherWorker.this.b;
                            LogHelper.i(str3, "getLastLocationLatLng onSuccess() location is NULL====");
                            WeatherPreferenceManager.Companion companion6 = WeatherPreferenceManager.Companion;
                            Context applicationContext5 = WeatherWorker.this.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext5, "applicationContext");
                            WeatherPreferenceManager companion7 = companion6.getInstance(applicationContext5);
                            Intrinsics.checkNotNull(companion7);
                            String lastLocationLatLng = companion7.getLastLocationLatLng();
                            Intrinsics.checkNotNull(lastLocationLatLng);
                            if (lastLocationLatLng.length() == 0) {
                                return;
                            }
                            String[] strArr3 = strArr2;
                            Context applicationContext6 = WeatherWorker.this.getApplicationContext();
                            Intrinsics.checkNotNullExpressionValue(applicationContext6, "applicationContext");
                            WeatherPreferenceManager companion8 = companion6.getInstance(applicationContext6);
                            Intrinsics.checkNotNull(companion8);
                            strArr3[0] = companion8.getLastLocationLatLng();
                            return;
                        }
                        WeatherPreferenceManager.Companion companion9 = WeatherPreferenceManager.Companion;
                        Context applicationContext7 = WeatherWorker.this.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext7, "applicationContext");
                        WeatherPreferenceManager companion10 = companion9.getInstance(applicationContext7);
                        Intrinsics.checkNotNull(companion10);
                        companion10.saveLastLocationLatLng(location);
                        String[] strArr4 = strArr2;
                        strArr4[0] = String.valueOf(location.getLatitude()) + Constants.SEPARATOR_COMMA + location.getLongitude();
                        str4 = WeatherWorker.this.b;
                        LogHelper.i(str4, "Last Location ==== " + location.getLatitude() + ", " + location.getLongitude());
                        WeatherWorker weatherWorker = WeatherWorker.this;
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        num = WeatherWorker.this.e;
                        Intrinsics.checkNotNull(num);
                        int intValue = num.intValue();
                        str5 = WeatherWorker.this.f;
                        Intrinsics.checkNotNull(str5);
                        weatherWorker.a(latitude, longitude, intValue, str5);
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.weathersdk.WeatherWorker$lastLocation$2
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public void onFailure(@NotNull Exception e) {
                        String str3;
                        Intrinsics.checkNotNullParameter(e, "e");
                        str3 = WeatherWorker.this.b;
                        LogHelper.i(str3, "onFailure() in getLastLocation() ====");
                    }
                });
                synchronized (this.f6207a) {
                    while (TextUtils.isEmpty(strArr2[0])) {
                        this.f6207a.lock();
                        try {
                            this.c.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.f6207a.unlock();
                    }
                }
                strArr = strArr2;
            }
        }
        if (strArr == null || strArr[0] == null) {
            LogHelper.i(this.b, "lastLocation: null");
            retry = ListenableWorker.Result.retry();
            str = "Result.retry()";
        } else {
            String str3 = this.b;
            LogHelper.i(str3, "lastLocation: " + strArr);
            String str4 = strArr[0];
            Intrinsics.checkNotNull(str4);
            Object[] array = StringsKt__StringsKt.split$default((CharSequence) str4, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).toArray(new String[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T>");
            double parseDouble = Double.parseDouble(((String[]) array)[0]);
            String str5 = strArr[0];
            Intrinsics.checkNotNull(str5);
            Object[] array2 = StringsKt__StringsKt.split$default((CharSequence) str5, new String[]{Constants.SEPARATOR_COMMA}, false, 0, 6, (Object) null).toArray(new String[0]);
            Objects.requireNonNull(array2, "null cannot be cast to non-null type kotlin.Array<T>");
            double parseDouble2 = Double.parseDouble(((String[]) array2)[1]);
            Integer num = this.e;
            Intrinsics.checkNotNull(num);
            int intValue = num.intValue();
            String str6 = this.f;
            Intrinsics.checkNotNull(str6);
            a(parseDouble, parseDouble2, intValue, str6);
            retry = ListenableWorker.Result.success();
            str = "Result.success()";
        }
        Intrinsics.checkNotNullExpressionValue(retry, str);
        return retry;
    }

    public final int getWeatherIconID(@NotNull WeatherConditionType.WeatherConditionEnum weatherConditionType) {
        Intrinsics.checkNotNullParameter(weatherConditionType, "weatherConditionType");
        int ordinal = weatherConditionType.ordinal();
        if (ordinal != 14) {
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        if (ordinal != 3) {
                            return ordinal != 4 ? 31001 : 31050;
                        }
                        return 31013;
                    }
                    return 31010;
                }
                return 31009;
            }
            return 31011;
        }
        return 31003;
    }
}
