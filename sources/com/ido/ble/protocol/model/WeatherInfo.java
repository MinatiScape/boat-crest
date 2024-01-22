package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class WeatherInfo implements Serializable {
    public static int WEATHER_TYPE_BREEZE = 15;
    public static int WEATHER_TYPE_CLEAR = 1;
    public static int WEATHER_TYPE_CLEAR_NIGHT = 11;
    public static int WEATHER_TYPE_CLOUDY = 2;
    public static int WEATHER_TYPE_CLOUDY_NIGHT = 12;
    public static int WEATHER_TYPE_COLD = 14;
    public static int WEATHER_TYPE_FIRST_QUARTER_MOON = 22;
    public static int WEATHER_TYPE_FULL_MOON = 24;
    public static int WEATHER_TYPE_GALE = 16;
    public static int WEATHER_TYPE_HAZE = 17;
    public static int WEATHER_TYPE_HOT = 13;
    public static int WEATHER_TYPE_LAST_QUARTER_MOON = 32;
    public static int WEATHER_TYPE_MOTH_EYEBROW_MOON = 21;
    public static int WEATHER_TYPE_NEW_MOON = 20;
    public static int WEATHER_TYPE_OVERCASTSKY = 3;
    public static int WEATHER_TYPE_RAIN = 4;
    public static int WEATHER_TYPE_RAINSTORM = 5;
    public static int WEATHER_TYPE_SANDSTORMS = 10;
    public static int WEATHER_TYPE_SHOWER = 6;
    public static int WEATHER_TYPE_SLEET = 8;
    public static int WEATHER_TYPE_SNOW = 7;
    public static int WEATHER_TYPE_TYPHOON = 9;
    public static int WEATHER_TYPE_UNKNOWN = 0;
    public static int WEATHER_TYPE_WANING_GIBBOUS_MOON = 25;
    public static int WEATHER_TYPE_WANING_MOON = 33;
    public static int WEATHER_TYPE_WAXING_GIBBOUS_MOON = 23;
    public static int WEATHER_TYPE_YUN = 19;
    public static int WEATHER_TYPE_ZHENYU = 18;
    private static final long serialVersionUID = 1;
    public int aqi;
    public WeatherFutureInfo[] future = new WeatherFutureInfo[3];
    public int humidity;
    public int max_temp;
    public int min_temp;
    public int temp;
    public int type;
    public int uv_intensity;

    /* loaded from: classes11.dex */
    public static class WeatherFutureInfo {
        public int max_temp;
        public int min_temp;
        public int type;

        public String toString() {
            return "WeatherFutureInfo{type=" + this.type + ", max_temp=" + this.max_temp + ", min_temp=" + this.min_temp + '}';
        }
    }

    public String toString() {
        return "WeatherInfo{type=" + this.type + ", temp=" + this.temp + ", max_temp=" + this.max_temp + ", min_temp=" + this.min_temp + ", humidity=" + this.humidity + ", uv_intensity=" + this.uv_intensity + ", aqi=" + this.aqi + ", futures=" + Arrays.toString(this.future) + '}';
    }
}
