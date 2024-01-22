package com.apex.bluetooth.model;

import java.util.List;
/* loaded from: classes.dex */
public class EABleWeather {
    public float current_temperature;
    public String place;
    public List<EABleWeatherItem> s_day;
    public TemperatureUnit temperatureUnit;

    /* loaded from: classes.dex */
    public enum AirQuality {
        excellent(0),
        good(1),
        bad(2);
        
        public int value;

        AirQuality(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public static class EABleWeatherItem {
        public int air_grade;
        public int air_humidity;
        public int cloudiness;
        public AirQuality e_air;
        public WeatherType e_day_type;
        public Moon e_moon;
        public WeatherType e_night_type;
        public RaysLevel e_rays;
        public int max_temperature;
        public int max_wind_power;
        public int min_temperature;
        public int min_wind_power;
        public long sunrise_timestamp;
        public long sunset_timestamp;

        public int getAir_grade() {
            return this.air_grade;
        }

        public int getAir_humidity() {
            return this.air_humidity;
        }

        public int getCloudiness() {
            return this.cloudiness;
        }

        public AirQuality getE_air() {
            return this.e_air;
        }

        public WeatherType getE_day_type() {
            return this.e_day_type;
        }

        public Moon getE_moon() {
            return this.e_moon;
        }

        public WeatherType getE_night_type() {
            return this.e_night_type;
        }

        public RaysLevel getE_rays() {
            return this.e_rays;
        }

        public int getMax_temperature() {
            return this.max_temperature;
        }

        public int getMax_wind_power() {
            return this.max_wind_power;
        }

        public int getMin_temperature() {
            return this.min_temperature;
        }

        public int getMin_wind_power() {
            return this.min_wind_power;
        }

        public long getSunrise_timestamp() {
            return this.sunrise_timestamp;
        }

        public long getSunset_timestamp() {
            return this.sunset_timestamp;
        }

        public void setAir_grade(int i) {
            this.air_grade = i;
        }

        public void setAir_humidity(int i) {
            this.air_humidity = i;
        }

        public void setCloudiness(int i) {
            this.cloudiness = i;
        }

        public void setE_air(AirQuality airQuality) {
            this.e_air = airQuality;
        }

        public void setE_day_type(WeatherType weatherType) {
            this.e_day_type = weatherType;
        }

        public void setE_moon(Moon moon) {
            this.e_moon = moon;
        }

        public void setE_night_type(WeatherType weatherType) {
            this.e_night_type = weatherType;
        }

        public void setE_rays(RaysLevel raysLevel) {
            this.e_rays = raysLevel;
        }

        public void setMax_temperature(int i) {
            this.max_temperature = i;
        }

        public void setMax_wind_power(int i) {
            this.max_wind_power = i;
        }

        public void setMin_temperature(int i) {
            this.min_temperature = i;
        }

        public void setMin_wind_power(int i) {
            this.min_wind_power = i;
        }

        public void setSunrise_timestamp(long j) {
            this.sunrise_timestamp = j;
        }

        public void setSunset_timestamp(long j) {
            this.sunset_timestamp = j;
        }

        public String toString() {
            return "EABleWeatherItem{e_day_type=" + this.e_day_type + ", e_night_type=" + this.e_night_type + ", e_air=" + this.e_air + ",air_grade=" + this.air_grade + ", max_temperature=" + this.max_temperature + ", min_temperature=" + this.min_temperature + ", sunrise_timestamp=" + this.sunrise_timestamp + ", sunset_timestamp=" + this.sunset_timestamp + ", min_wind_power=" + this.min_wind_power + ", max_wind_power=" + this.max_wind_power + ", air_humidity=" + this.air_humidity + ", cloudiness=" + this.cloudiness + ", e_rays=" + this.e_rays + ", e_moon=" + this.e_moon + '}';
        }
    }

    /* loaded from: classes.dex */
    public enum Moon {
        new_moon(0),
        waxing_crescent_moon(1),
        quarter_moon(2),
        half_moon_1(3),
        waxing_gibbous_moon(4),
        full_moon(5),
        waning_gibbous_moon(6),
        half_moon_2(7),
        last_quarter_moon(8),
        waning_crescent_moon(9);
        
        public int value;

        Moon(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum RaysLevel {
        weak(0),
        medium(1),
        strong(2),
        very_strong(3),
        super_strong(4);
        
        public int value;

        RaysLevel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum TemperatureUnit {
        centigrade(0),
        fahrenheit(1);
        
        public int value;

        TemperatureUnit(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum WeatherType {
        clear(0),
        cloudy(1),
        gloomy(2),
        drizzle(3),
        moderate_rain(4),
        thunderstorm(5),
        heavy_rain(6),
        sleet(7),
        light_snow(8),
        moderate_snow(9),
        heavy_snow(10),
        typhoon(11),
        dust(12),
        sandstorm(13),
        fog(14),
        haze(15);
        
        public int value;

        WeatherType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public float getCurrent_temperature() {
        return this.current_temperature;
    }

    public String getPlace() {
        return this.place;
    }

    public List<EABleWeatherItem> getS_day() {
        return this.s_day;
    }

    public TemperatureUnit getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public void setCurrent_temperature(float f) {
        this.current_temperature = f;
    }

    public void setPlace(String str) {
        this.place = str;
    }

    public void setS_day(List<EABleWeatherItem> list) {
        this.s_day = list;
    }

    public void setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String toString() {
        return "EABleWeather{current_temperature=" + this.current_temperature + ", place='" + this.place + "', temperatureUnit=" + this.temperatureUnit + ", s_day=" + this.s_day + '}';
    }
}
