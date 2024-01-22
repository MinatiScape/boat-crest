package com.touchgui.sdk.bean;

import java.util.List;
/* loaded from: classes12.dex */
public class TGWeather {
    @Deprecated
    public static final int CLOUDY = 2;
    @Deprecated
    public static final int CLOUDY_NIGHT = 12;
    @Deprecated
    public static final int CLOUDY_SUNNY = 19;
    @Deprecated
    public static final int COLD = 14;
    @Deprecated
    public static final int COOL_BREEZE = 15;
    @Deprecated
    public static final int DENSE_FOG = 22;
    @Deprecated
    public static final int GALE = 16;
    @Deprecated
    public static final int HAZE = 21;
    @Deprecated
    public static final int HOT = 13;
    @Deprecated
    public static final int LESS_CLOUDY_NIGHT = 25;
    @Deprecated
    public static final int MIST = 17;
    @Deprecated
    public static final int OVERCAST = 3;
    @Deprecated
    public static final int RAINSTORM = 5;
    @Deprecated
    public static final int RAINY = 4;
    @Deprecated
    public static final int SANDSTORM = 10;
    @Deprecated
    public static final int SHOWER = 18;
    @Deprecated
    public static final int SHOWER_NIGHT = 24;
    @Deprecated
    public static final int SLEET = 8;
    @Deprecated
    public static final int SNOWY = 7;
    @Deprecated
    public static final int SNOWY_NIGHT = 23;
    @Deprecated
    public static final int SUNNY = 1;
    @Deprecated
    public static final int SUNNY_NIGHT = 11;
    @Deprecated
    public static final int THUNDERSHOWER_HAIL = 20;
    @Deprecated
    public static final int THUNDER_SHOWER = 6;
    @Deprecated
    public static final int TYPHOON = 9;
    private int aqi;
    private int currentTemp;
    private List<FutureWeather> futureWeatherList;
    private int humidity;
    private int maxTemp;
    private int minTemp;
    private int pm;
    private int sunriseHour;
    private int sunriseMinute;
    private int sunsetHour;
    private int sunsetMinute;
    private int type;
    private int uv;

    /* loaded from: classes12.dex */
    public static class FutureWeather {
        private int maxTemp;
        private int minTemp;
        private int type;

        public int getMaxTemp() {
            return this.maxTemp;
        }

        public int getMinTemp() {
            return this.minTemp;
        }

        public int getType() {
            return this.type;
        }

        public void setMaxTemp(int i) {
            this.maxTemp = i;
        }

        public void setMinTemp(int i) {
            this.minTemp = i;
        }

        public void setType(int i) {
            this.type = i;
        }
    }

    public int getAqi() {
        return this.aqi;
    }

    public int getCurrentTemp() {
        return this.currentTemp;
    }

    public List<FutureWeather> getFutureWeatherList() {
        return this.futureWeatherList;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public int getMaxTemp() {
        return this.maxTemp;
    }

    public int getMinTemp() {
        return this.minTemp;
    }

    public int getPm() {
        return this.pm;
    }

    public int getSunriseHour() {
        return this.sunriseHour;
    }

    public int getSunriseMinute() {
        return this.sunriseMinute;
    }

    public int getSunsetHour() {
        return this.sunsetHour;
    }

    public int getSunsetMinute() {
        return this.sunsetMinute;
    }

    public int getType() {
        return this.type;
    }

    public int getUv() {
        return this.uv;
    }

    public void setAqi(int i) {
        this.aqi = i;
    }

    public void setCurrentTemp(int i) {
        this.currentTemp = i;
    }

    public void setFutureWeatherList(List<FutureWeather> list) {
        this.futureWeatherList = list;
    }

    public void setHumidity(int i) {
        this.humidity = i;
    }

    public void setMaxTemp(int i) {
        this.maxTemp = i;
    }

    public void setMinTemp(int i) {
        this.minTemp = i;
    }

    public void setPm(int i) {
        this.pm = i;
    }

    public void setSunriseHour(int i) {
        this.sunriseHour = i;
    }

    public void setSunriseMinute(int i) {
        this.sunriseMinute = i;
    }

    public void setSunsetHour(int i) {
        this.sunsetHour = i;
    }

    public void setSunsetMinute(int i) {
        this.sunsetMinute = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setUv(int i) {
        this.uv = i;
    }
}
