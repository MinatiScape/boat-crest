package com.coveiot.coveaccess.model.server;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
/* loaded from: classes8.dex */
public class SleepDataBean {
    @SerializedName("baseUnit")
    private String baseUnit;
    @SerializedName("breathQuality")
    private int breathQuality;
    @SerializedName("codec")
    private Codec codec;
    @SerializedName("date")
    private String date;
    @SerializedName("maxAmbientSound")
    private int maxAmbientSound;
    @SerializedName("maxHr")
    private int maxHr;
    @SerializedName("maxStress")
    private int maxStress;
    @SerializedName("minAmbientSound")
    private int minAmbientSound;
    @SerializedName("minHr")
    private int minHr;
    @SerializedName("minStress")
    private int minStress;
    @SerializedName("quality")
    private int sleepScore;
    @SerializedName(ErrorBundle.SUMMARY_ENTRY)
    private SummaryBean summary;
    @SerializedName("timeLog")
    private TimeLogBean timeLog;
    @SerializedName("type")
    private String type;
    @SerializedName("tzOffset")
    private String tzOffset;
    @SerializedName("value")
    private int value;

    /* loaded from: classes8.dex */
    public static class Codec {
        @SerializedName("awake")
        private Expression awake;
        @SerializedName("deepSleep")
        private Expression deepSleep;
        @SerializedName("lightSleep")
        private Expression lightSleep;
        @SerializedName("remSleep")
        private Expression remSleep;

        /* loaded from: classes8.dex */
        public static class Expression {
            @SerializedName("eq")
            private Integer eq;
            @SerializedName("gte")
            private Integer gte;
            @SerializedName("lte")
            private Integer lte;

            public Integer getEq() {
                return this.eq;
            }

            public Integer getGte() {
                return this.gte;
            }

            public Integer getLte() {
                return this.lte;
            }

            public void setEq(Integer num) {
                this.eq = num;
            }

            public void setGte(Integer num) {
                this.gte = num;
            }

            public void setLte(Integer num) {
                this.lte = num;
            }
        }

        public Expression getAwake() {
            return this.awake;
        }

        public Expression getDeepSleep() {
            return this.deepSleep;
        }

        public Expression getLightSleep() {
            return this.lightSleep;
        }

        public Expression getRemSleep() {
            return this.remSleep;
        }

        public void setAwake(Expression expression) {
            this.awake = expression;
        }

        public void setDeepSleep(Expression expression) {
            this.deepSleep = expression;
        }

        public void setLightSleep(Expression expression) {
            this.lightSleep = expression;
        }

        public void setRemSleep(Expression expression) {
            this.remSleep = expression;
        }
    }

    /* loaded from: classes8.dex */
    public static class SummaryBean {
        @SerializedName("awakeDurations")
        private List<Integer> awakeDurations;
        @SerializedName("deepSleepDurations")
        private List<Integer> deepSleepDurations;
        @SerializedName("lightSleepDurations")
        private List<Integer> lightSleepDurations;
        @SerializedName("remSleepDurations")
        private List<Integer> remSleepDurations;
        @SerializedName("sleepEndTime")
        private String sleepEndTime;
        @SerializedName("sleepStartTime")
        private String sleepStartTime;

        public List<Integer> getAwakeDurations() {
            return this.awakeDurations;
        }

        public List<Integer> getDeepSleepDurations() {
            return this.deepSleepDurations;
        }

        public List<Integer> getLightSleepDurations() {
            return this.lightSleepDurations;
        }

        public List<Integer> getRemSleepDurations() {
            return this.remSleepDurations;
        }

        public String getSleepEndTime() {
            return this.sleepEndTime;
        }

        public String getSleepStartTime() {
            return this.sleepStartTime;
        }

        public void setAwakeDurations(List<Integer> list) {
            this.awakeDurations = list;
        }

        public void setDeepSleepDurations(List<Integer> list) {
            this.deepSleepDurations = list;
        }

        public void setLightSleepDurations(List<Integer> list) {
            this.lightSleepDurations = list;
        }

        public void setRemSleepDurations(List<Integer> list) {
            this.remSleepDurations = list;
        }

        public void setSleepEndTime(String str) {
            this.sleepEndTime = str;
        }

        public void setSleepStartTime(String str) {
            this.sleepStartTime = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class TimeLogBean {
        @SerializedName("logs")
        private List<LogsBean> logs;

        /* loaded from: classes8.dex */
        public static class LogsBean {
            @SerializedName("awake")
            private int awake;
            @SerializedName("codedValues")
            private List<Integer> codedValues;
            @SerializedName("deepSleep")
            private int deepSleep;
            @SerializedName("endTime")
            private String endTime;
            @SerializedName("lightSleep")
            private int lightSleep;
            @SerializedName("refinedValues")
            private List<Integer> refinedValues;
            @SerializedName("remSleep")
            private int remSleep;
            @SerializedName("startTime")
            private String startTime;

            public int getAwake() {
                return this.awake;
            }

            public List<Integer> getCodedValues() {
                return this.codedValues;
            }

            public int getDeepSleep() {
                return this.deepSleep;
            }

            public String getEndTime() {
                return this.endTime;
            }

            public int getLightSleep() {
                return this.lightSleep;
            }

            public List<Integer> getRefinedValues() {
                return this.refinedValues;
            }

            public int getRemSleep() {
                return this.remSleep;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public void setAwake(int i) {
                this.awake = i;
            }

            public void setCodedValues(List<Integer> list) {
                this.codedValues = list;
            }

            public void setDeepSleep(int i) {
                this.deepSleep = i;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setLightSleep(int i) {
                this.lightSleep = i;
            }

            public void setRefinedValues(List<Integer> list) {
                this.refinedValues = list;
            }

            public void setRemSleep(int i) {
                this.remSleep = i;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public List<LogsBean> getLogs() {
            return this.logs;
        }

        public void setLogs(List<LogsBean> list) {
            this.logs = list;
        }
    }

    public String getBaseUnit() {
        return this.baseUnit;
    }

    public int getBreathQuality() {
        return this.breathQuality;
    }

    public Codec getCodec() {
        return this.codec;
    }

    public String getDate() {
        return this.date;
    }

    public int getMaxAmbientSound() {
        return this.maxAmbientSound;
    }

    public int getMaxHr() {
        return this.maxHr;
    }

    public int getMaxStress() {
        return this.maxStress;
    }

    public int getMinAmbientSound() {
        return this.minAmbientSound;
    }

    public int getMinHr() {
        return this.minHr;
    }

    public int getMinStress() {
        return this.minStress;
    }

    public int getSleepScore() {
        return this.sleepScore;
    }

    public SummaryBean getSummary() {
        return this.summary;
    }

    public TimeLogBean getTimeLog() {
        return this.timeLog;
    }

    public String getType() {
        return this.type;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public int getValue() {
        return this.value;
    }

    public void setBaseUnit(String str) {
        this.baseUnit = str;
    }

    public void setBreathQuality(int i) {
        this.breathQuality = i;
    }

    public void setCodec(Codec codec) {
        this.codec = codec;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setMaxAmbientSound(int i) {
        this.maxAmbientSound = i;
    }

    public void setMaxHr(int i) {
        this.maxHr = i;
    }

    public void setMaxStress(int i) {
        this.maxStress = i;
    }

    public void setMinAmbientSound(int i) {
        this.minAmbientSound = i;
    }

    public void setMinHr(int i) {
        this.minHr = i;
    }

    public void setMinStress(int i) {
        this.minStress = i;
    }

    public void setSleepScore(int i) {
        this.sleepScore = i;
    }

    public void setSummary(SummaryBean summaryBean) {
        this.summary = summaryBean;
    }

    public void setTimeLog(TimeLogBean timeLogBean) {
        this.timeLog = timeLogBean;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }

    public void setValue(int i) {
        this.value = i;
    }
}
