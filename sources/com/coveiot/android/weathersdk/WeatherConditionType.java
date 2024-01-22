package com.coveiot.android.weathersdk;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherConditionType;", "", "<init>", "()V", "Companion", "WeatherConditionEnum", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes8.dex */
public final class WeatherConditionType {
    public static final Companion Companion = new Companion(null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherConditionType$Companion;", "", "", "weatherMain", "Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "getWeatherType", "(Ljava/lang/String;)Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "<init>", "()V", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final WeatherConditionEnum getWeatherType(@NotNull String weatherMain) {
            Intrinsics.checkNotNullParameter(weatherMain, "weatherMain");
            switch (weatherMain.hashCode()) {
                case -1810807670:
                    if (weatherMain.equals("Squall")) {
                        return WeatherConditionEnum.Squall;
                    }
                    break;
                case -1710645595:
                    if (weatherMain.equals("Thunderstorm")) {
                        return WeatherConditionEnum.Thunderstorm;
                    }
                    break;
                case -709811020:
                    if (weatherMain.equals("Drizzle")) {
                        return WeatherConditionEnum.Drizzle;
                    }
                    break;
                case 66134:
                    if (weatherMain.equals("Ash")) {
                        return WeatherConditionEnum.Ash;
                    }
                    break;
                case 70814:
                    if (weatherMain.equals("Fog")) {
                        return WeatherConditionEnum.Fog;
                    }
                    break;
                case 2141906:
                    if (weatherMain.equals("Dust")) {
                        return WeatherConditionEnum.Dust;
                    }
                    break;
                case 2242052:
                    if (weatherMain.equals("Haze")) {
                        return WeatherConditionEnum.Haze;
                    }
                    break;
                case 2398493:
                    if (weatherMain.equals("Mist")) {
                        return WeatherConditionEnum.Mist;
                    }
                    break;
                case 2539444:
                    if (weatherMain.equals("Rain")) {
                        return WeatherConditionEnum.Rain;
                    }
                    break;
                case 2569380:
                    if (weatherMain.equals("Sand")) {
                        return WeatherConditionEnum.Sand;
                    }
                    break;
                case 2581923:
                    if (weatherMain.equals("Snow")) {
                        return WeatherConditionEnum.Snow;
                    }
                    break;
                case 80009551:
                    if (weatherMain.equals("Smoke")) {
                        return WeatherConditionEnum.Smoke;
                    }
                    break;
                case 395545701:
                    if (weatherMain.equals("Clear_Sunny")) {
                        return WeatherConditionEnum.Clear_Sunny;
                    }
                    break;
                case 527388469:
                    if (weatherMain.equals("Tornado")) {
                        return WeatherConditionEnum.Tornado;
                    }
                    break;
                case 2021315838:
                    if (weatherMain.equals("Clouds")) {
                        return WeatherConditionEnum.Clouds;
                    }
                    break;
            }
            return WeatherConditionEnum.Clear_Sunny;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0012\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/coveiot/android/weathersdk/WeatherConditionType$WeatherConditionEnum;", "", "<init>", "(Ljava/lang/String;I)V", "Thunderstorm", "Drizzle", "Rain", "Snow", "Mist", "Smoke", "Haze", "Dust", "Fog", "Sand", "Ash", "Squall", "Tornado", "Clear_Sunny", "Clouds", "weather_prodRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes8.dex */
    public enum WeatherConditionEnum {
        Thunderstorm,
        Drizzle,
        Rain,
        Snow,
        Mist,
        Smoke,
        Haze,
        Dust,
        Fog,
        Sand,
        Ash,
        Squall,
        Tornado,
        Clear_Sunny,
        Clouds
    }
}
