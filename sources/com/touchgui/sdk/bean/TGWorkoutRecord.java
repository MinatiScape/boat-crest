package com.touchgui.sdk.bean;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public class TGWorkoutRecord {
    @Nullable
    private List<Event> events;
    @Nullable
    private TGFootballAvgPace footballAvgPace;
    @Nullable
    private List<? extends Gps> footballFieldGpsData;
    @Nullable
    private List<? extends Gps> gpsData;
    @Nullable
    private List<HeartRate> hearts;
    @Nullable
    private TGKeepTrack keepTrack;
    @Nullable
    private List<Pace> paceData;
    @Nullable
    private TGSportRealTimeData realTimeData;
    @Nullable
    private List<Rowing> rowingData;
    private TGSportRecord summary;
    @Nullable
    private TGSyncSwim swimData;

    /* loaded from: classes12.dex */
    public static class Event {
        public static final int WORKOUT_PAUSE = 3;
        public static final int WORKOUT_RESUME = 4;
        private int eventType;
        private int offset;

        public Event(int i, int i2) {
            this.offset = i;
            this.eventType = i2;
        }

        public int getEventType() {
            return this.eventType;
        }

        public int getOffset() {
            return this.offset;
        }

        public void setEventType(int i) {
            this.eventType = i;
        }

        public void setOffset(int i) {
            this.offset = i;
        }
    }

    /* loaded from: classes12.dex */
    public static class Gps {
        private int altitude;
        private double latitude;
        private double longitude;
        private int offset;
        private float speed;

        public int getAltitude() {
            return this.altitude;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public int getOffset() {
            return this.offset;
        }

        public float getSpeed() {
            return this.speed;
        }

        public void setAltitude(int i) {
            this.altitude = i;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public void setSpeed(float f) {
            this.speed = f;
        }
    }

    /* loaded from: classes12.dex */
    public static class HeartRate {
        private final int offset;
        private final int value;

        public HeartRate(int i, int i2) {
            this.offset = i;
            this.value = i2;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes12.dex */
    public static class Pace {
        public static final int TYPE_PER_KM = 5;
        public static final int TYPE_PER_MILE = 6;
        public static final int TYPE_REAL_TIME = 7;
        private int offset;
        private int type;
        private int value;

        public Pace(int i, int i2, int i3) {
            this.type = i;
            this.offset = i2;
            this.value = i3;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getType() {
            return this.type;
        }

        public int getValue() {
            return this.value;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public void setType(int i) {
            this.type = i;
        }

        public void setValue(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes12.dex */
    public static class Rowing {
        private int offset;
        private int strokeFrq;

        public Rowing(int i, int i2) {
            this.offset = i;
            this.strokeFrq = i2;
        }

        public int getOffset() {
            return this.offset;
        }

        public int getStrokeFrq() {
            return this.strokeFrq;
        }

        public void setOffset(int i) {
            this.offset = i;
        }

        public void setStrokeFrq(int i) {
            this.strokeFrq = i;
        }
    }

    @Nullable
    public List<Event> getEvents() {
        return this.events;
    }

    @Nullable
    public TGFootballAvgPace getFootballAvgPace() {
        return this.footballAvgPace;
    }

    @Nullable
    public List<? extends Gps> getFootballFieldGpsData() {
        return this.footballFieldGpsData;
    }

    @Nullable
    public List<? extends Gps> getGpsData() {
        return this.gpsData;
    }

    @Nullable
    public List<HeartRate> getHearts() {
        return this.hearts;
    }

    @Nullable
    public TGKeepTrack getKeepTrack() {
        return this.keepTrack;
    }

    @Nullable
    public List<Pace> getPaceData() {
        return this.paceData;
    }

    @Nullable
    public TGSportRealTimeData getRealTimeData() {
        return this.realTimeData;
    }

    @Nullable
    public List<Rowing> getRowingData() {
        return this.rowingData;
    }

    public TGSportRecord getSummary() {
        return this.summary;
    }

    @Nullable
    public TGSyncSwim getSwimData() {
        return this.swimData;
    }

    public void setEvents(@Nullable List<Event> list) {
        this.events = list;
    }

    public void setFootballAvgPace(@Nullable TGFootballAvgPace tGFootballAvgPace) {
        this.footballAvgPace = tGFootballAvgPace;
    }

    public void setFootballFieldGpsData(@Nullable List<? extends Gps> list) {
        this.footballFieldGpsData = list;
    }

    public void setGpsData(@Nullable List<? extends Gps> list) {
        this.gpsData = list;
    }

    public void setHearts(@Nullable List<HeartRate> list) {
        this.hearts = list;
    }

    public void setKeepTrack(@Nullable TGKeepTrack tGKeepTrack) {
        this.keepTrack = tGKeepTrack;
    }

    public void setPaceData(@Nullable List<Pace> list) {
        this.paceData = list;
    }

    public void setRealTimeData(@Nullable TGSportRealTimeData tGSportRealTimeData) {
        this.realTimeData = tGSportRealTimeData;
    }

    public void setRowingData(@Nullable List<Rowing> list) {
        this.rowingData = list;
    }

    public void setSummary(TGSportRecord tGSportRecord) {
        this.summary = tGSportRecord;
    }

    public void setSwimData(@Nullable TGSyncSwim tGSyncSwim) {
        this.swimData = tGSyncSwim;
    }

    public String toString() {
        return "TGWorkoutRecord{summary=" + this.summary + '}';
    }
}
