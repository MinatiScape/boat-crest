package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public class WeatherInfoV3 implements Serializable {
    public static final int WEATHER_TYPE_BREEZE = 15;
    public static final int WEATHER_TYPE_CLEAR = 1;
    public static final int WEATHER_TYPE_CLEAR_NIGHT = 11;
    public static final int WEATHER_TYPE_CLOUDY = 2;
    public static final int WEATHER_TYPE_CLOUDY_NIGHT = 12;
    public static final int WEATHER_TYPE_COLD = 14;
    public static int WEATHER_TYPE_FIRST_QUARTER_MOON = 22;
    public static int WEATHER_TYPE_FULL_MOON = 24;
    public static final int WEATHER_TYPE_GALE = 16;
    public static final int WEATHER_TYPE_HAZE = 17;
    public static final int WEATHER_TYPE_HOT = 13;
    public static int WEATHER_TYPE_LAST_QUARTER_MOON = 32;
    public static int WEATHER_TYPE_MOTH_EYEBROW_MOON = 21;
    public static int WEATHER_TYPE_NEW_MOON = 20;
    public static final int WEATHER_TYPE_OTHER = 0;
    public static final int WEATHER_TYPE_OVERCASTSKY = 3;
    public static final int WEATHER_TYPE_RAIN = 4;
    public static final int WEATHER_TYPE_RAINSTORM = 5;
    public static final int WEATHER_TYPE_SANDSTORMS = 10;
    public static final int WEATHER_TYPE_SHOWER = 6;
    public static final int WEATHER_TYPE_SLEET = 8;
    public static final int WEATHER_TYPE_SNOW = 7;
    public static final int WEATHER_TYPE_TYPHOON = 9;
    public static int WEATHER_TYPE_WANING_GIBBOUS_MOON = 25;
    public static int WEATHER_TYPE_WANING_MOON = 33;
    public static int WEATHER_TYPE_WAXING_GIBBOUS_MOON = 23;
    public static final int WEATHER_TYPE_YUN = 19;
    public static final int WEATHER_TYPE_ZHENYU = 18;
    private static final long serialVersionUID = 1;
    public String air_grade_item;
    public int air_quality;
    public String city_name;
    public int day;
    public ArrayList<Future> future_items;
    public int hour;
    public ArrayList<Hour24> hours_weather_items;
    public int humidity;
    public int min;
    public int month;
    public int precipitation_probability;
    public int sec;
    public int sunrise_hour;
    public ArrayList<SunRiseSet> sunrise_item;
    public int sunrise_item_num;
    public int sunrise_min;
    public int sunset_hour;
    public int sunset_min;
    public int today_max_temp;
    public int today_min_temp;
    public int today_tmp;
    public int today_uv_intensity;
    public int version;
    public int weather_type;
    public int week;
    public int wind_speed;

    /* loaded from: classes11.dex */
    public static class Future {
        public int max_temp;
        public int min_temp;
        public int weather_type;

        public String toString() {
            return "Future{weather_type=" + this.weather_type + ", max_temp=" + this.max_temp + ", min_temp=" + this.min_temp + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Hour24 {
        public int probability;
        public int temperature;
        public int weather_type;

        public String toString() {
            return "Hour24{weather_type=" + this.weather_type + ", temperature=" + this.temperature + ", probability=" + this.probability + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class Response {
        public static final int CODE_SUCCESS = 0;
        public int err_code;

        public String toString() {
            return "Response{err_code=" + this.err_code + '}';
        }
    }

    /* loaded from: classes11.dex */
    public static class SunRiseSet {
        public int sunrise_hour;
        public int sunrise_min;
        public int sunset_hour;
        public int sunset_min;

        public String toString() {
            return "SunRiseSet{sunrise_hour=" + this.sunrise_hour + ", sunrise_min=" + this.sunrise_min + ", sunset_hour=" + this.sunset_hour + ", sunset_min=" + this.sunset_min + '}';
        }
    }

    public String toString() {
        return "WeatherInfoV3{version=" + this.version + ", month=" + this.month + ", day=" + this.day + ", hour=" + this.hour + ", min=" + this.min + ", sec=" + this.sec + ", week=" + this.week + ", weather_type=" + this.weather_type + ", today_tmp=" + this.today_tmp + ", today_max_temp=" + this.today_max_temp + ", today_min_temp=" + this.today_min_temp + ", city_name='" + this.city_name + "', hours_weather_items=" + this.hours_weather_items + ", air_quality=" + this.air_quality + ", precipitation_probability=" + this.precipitation_probability + ", humidity=" + this.humidity + ", today_uv_intensity=" + this.today_uv_intensity + ", wind_speed=" + this.wind_speed + ", sunrise_hour=" + this.sunrise_hour + ", sunrise_min=" + this.sunrise_min + ", sunset_hour=" + this.sunset_hour + ", sunset_min=" + this.sunset_min + ", future_items=" + this.future_items + ", sunrise_item_num=" + this.sunrise_item_num + ", sunrise_item=" + this.sunrise_item + ", air_grade_item='" + this.air_grade_item + "'}";
    }
}
