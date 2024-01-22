package com.coveiot.coveaccess.fitnesscomputeddataapi;

import com.clevertap.android.sdk.Constants;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class FitnessComputedData {
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("energy")
    @Expose
    public Energy energy;
    @SerializedName("otherParams")
    @Expose
    public OtherParams otherParams;
    @SerializedName("respiratoryRate")
    @Expose
    public RespiratoryRate respiratoryRate;
    @SerializedName(FitnessActivities.SLEEP)
    @Expose
    public Sleep sleep;
    @SerializedName("tzOffset")
    @Expose
    public String tzOffset;

    /* loaded from: classes8.dex */
    public static class Energy {
        @SerializedName("baseUnits")
        @Expose
        public BaseUnits baseUnits;
        @SerializedName(FirebaseAnalytics.Param.LEVEL)
        @Expose
        public Level level;

        /* loaded from: classes8.dex */
        public static class BaseUnits {
            @SerializedName("activityDuration")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6555a;

            public String getActivityDuration() {
                return this.f6555a;
            }

            public void setActivityDuration(String str) {
                this.f6555a = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class Level {
            @SerializedName("computedDate")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6556a;
            @SerializedName("baseUnit")
            @Expose
            private String c;
            @SerializedName("source")
            @Expose
            private Source f;
            @SerializedName("rationale")
            @Expose
            private Rationale g;
            @SerializedName("values")
            @Expose
            private List<Integer> b = null;
            @SerializedName("drainPoints")
            @Expose
            private List<Integer> d = null;
            @SerializedName("gainPoints")
            @Expose
            private List<Integer> e = null;

            /* loaded from: classes8.dex */
            public static class Rationale {
                @SerializedName("drains")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private List<Drain> f6557a = null;
                @SerializedName("gains")
                @Expose
                private List<Gain> b = null;

                /* loaded from: classes8.dex */
                public static class Drain {
                    @SerializedName("type")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6558a;
                    @SerializedName("value")
                    @Expose
                    private Integer b;
                    @SerializedName("activityDuration")
                    @Expose
                    private Integer c;
                    @SerializedName("calories")
                    @Expose
                    private Integer d;
                    @SerializedName("activityId")
                    @Expose
                    private Integer e;
                    @SerializedName("categoryId")
                    @Expose
                    private Integer f;

                    public Integer getActivityDuration() {
                        return this.c;
                    }

                    public Integer getActivityId() {
                        return this.e;
                    }

                    public Integer getCalories() {
                        return this.d;
                    }

                    public Integer getCategoryId() {
                        return this.f;
                    }

                    public String getType() {
                        return this.f6558a;
                    }

                    public Integer getValue() {
                        return this.b;
                    }

                    public void setActivityDuration(Integer num) {
                        this.c = num;
                    }

                    public void setActivityId(Integer num) {
                        this.e = num;
                    }

                    public void setCalories(Integer num) {
                        this.d = num;
                    }

                    public void setCategoryId(Integer num) {
                        this.f = num;
                    }

                    public void setType(String str) {
                        this.f6558a = str;
                    }

                    public void setValue(Integer num) {
                        this.b = num;
                    }
                }

                /* loaded from: classes8.dex */
                public static class Gain {
                    @SerializedName("type")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6559a;
                    @SerializedName("value")
                    @Expose
                    private Integer b;
                    @SerializedName("activityDuration")
                    @Expose
                    private Integer c;
                    @SerializedName("calories")
                    @Expose
                    private Integer d;

                    public Integer getActivityDuration() {
                        return this.c;
                    }

                    public Integer getCalories() {
                        return this.d;
                    }

                    public String getType() {
                        return this.f6559a;
                    }

                    public Integer getValue() {
                        return this.b;
                    }

                    public void setActivityDuration(Integer num) {
                        this.c = num;
                    }

                    public void setCalories(Integer num) {
                        this.d = num;
                    }

                    public void setType(String str) {
                        this.f6559a = str;
                    }

                    public void setValue(Integer num) {
                        this.b = num;
                    }
                }

                public List<Drain> getDrains() {
                    return this.f6557a;
                }

                public List<Gain> getGains() {
                    return this.b;
                }

                public void setDrains(List<Drain> list) {
                    this.f6557a = list;
                }

                public void setGains(List<Gain> list) {
                    this.b = list;
                }
            }

            /* loaded from: classes8.dex */
            public static class Source {
                @SerializedName("type")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private String f6560a;
                @SerializedName("identifier")
                @Expose
                private String b;

                public String getIdentifier() {
                    return this.b;
                }

                public String getType() {
                    return this.f6560a;
                }

                public void setIdentifier(String str) {
                    this.b = str;
                }

                public void setType(String str) {
                    this.f6560a = str;
                }
            }

            public String getBaseUnit() {
                return this.c;
            }

            public String getComputedDate() {
                return this.f6556a;
            }

            public List<Integer> getDrainPoints() {
                return this.d;
            }

            public List<Integer> getGainPoints() {
                return this.e;
            }

            public Rationale getRationale() {
                return this.g;
            }

            public Source getSource() {
                return this.f;
            }

            public List<Integer> getValues() {
                return this.b;
            }

            public void setBaseUnit(String str) {
                this.c = str;
            }

            public void setComputedDate(String str) {
                this.f6556a = str;
            }

            public void setDrainPoints(List<Integer> list) {
                this.d = list;
            }

            public void setGainPoints(List<Integer> list) {
                this.e = list;
            }

            public void setRationale(Rationale rationale) {
                this.g = rationale;
            }

            public void setSource(Source source) {
                this.f = source;
            }

            public void setValues(List<Integer> list) {
                this.b = list;
            }
        }

        public BaseUnits getBaseUnits() {
            return this.baseUnits;
        }

        public Level getLevel() {
            return this.level;
        }

        public void setBaseUnits(BaseUnits baseUnits) {
            this.baseUnits = baseUnits;
        }

        public void setLevel(Level level) {
            this.level = level;
        }
    }

    /* loaded from: classes8.dex */
    public static class OtherParams {
        @SerializedName("userBmi")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private Double f6561a;

        public Double getUserBmi() {
            return this.f6561a;
        }

        public void setUserBmi(Double d) {
            this.f6561a = d;
        }
    }

    /* loaded from: classes8.dex */
    public static class RespiratoryRate {
        @SerializedName("computedDate")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6562a;
        @SerializedName("min")
        @Expose
        private int b;
        @SerializedName(Constants.PRIORITY_MAX)
        @Expose
        private int c;
        @SerializedName("values")
        @Expose
        private ArrayList<Integer> d;
        @SerializedName("baseUnit")
        @Expose
        private String e;
        @SerializedName("accuracies")
        @Expose
        private ArrayList<Float> f;
        @SerializedName("source")
        @Expose
        private Source g;
        @SerializedName("baseUnits")
        @Expose
        private BaseUnits h;

        /* loaded from: classes8.dex */
        public static class BaseUnits {
            @SerializedName("accuracy")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6563a;

            public String getAccuracy() {
                return this.f6563a;
            }

            public void setAccuracy(String str) {
                this.f6563a = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class Source {
            @SerializedName("type")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6564a;
            @SerializedName("identifier")
            @Expose
            private String b;

            public String getIdentifier() {
                return this.b;
            }

            public String getType() {
                return this.f6564a;
            }

            public void setIdentifier(String str) {
                this.b = str;
            }

            public void setType(String str) {
                this.f6564a = str;
            }
        }

        public ArrayList<Float> getAccuracies() {
            return this.f;
        }

        public String getBaseUnit() {
            return this.e;
        }

        public BaseUnits getBaseUnits() {
            return this.h;
        }

        public String getComputedDate() {
            return this.f6562a;
        }

        public int getMax() {
            return this.c;
        }

        public int getMin() {
            return this.b;
        }

        public Source getSource() {
            return this.g;
        }

        public ArrayList<Integer> getValues() {
            return this.d;
        }

        public void setAccuracies(ArrayList<Float> arrayList) {
            this.f = arrayList;
        }

        public void setBaseUnit(String str) {
            this.e = str;
        }

        public void setBaseUnits(BaseUnits baseUnits) {
            this.h = baseUnits;
        }

        public void setComputedDate(String str) {
            this.f6562a = str;
        }

        public void setMax(int i) {
            this.c = i;
        }

        public void setMin(int i) {
            this.b = i;
        }

        public void setSource(Source source) {
            this.g = source;
        }

        public void setValues(ArrayList<Integer> arrayList) {
            this.d = arrayList;
        }
    }

    /* loaded from: classes8.dex */
    public static class Sleep {
        @SerializedName("quality")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private Quality f6565a;
        @SerializedName("baseUnits")
        @Expose
        private BaseUnits b;

        /* loaded from: classes8.dex */
        public static class BaseUnits {
            @SerializedName("sleepDuration")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6566a;
            @SerializedName("timeToDeepSleep")
            @Expose
            private String b;

            public String getSleepDuration() {
                return this.f6566a;
            }

            public String getTimeToDeepSleep() {
                return this.b;
            }

            public void setSleepDuration(String str) {
                this.f6566a = str;
            }

            public void setTimeToDeepSleep(String str) {
                this.b = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class Quality {
            @SerializedName("computedDate")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6567a;
            @SerializedName("value")
            @Expose
            private Integer b;
            @SerializedName("baseUnit")
            @Expose
            private String c;
            @SerializedName("source")
            @Expose
            private Source d;
            @SerializedName("rationale")
            @Expose
            private Rationale e;

            /* loaded from: classes8.dex */
            public static class Rationale {
                @SerializedName("sleepDuration")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private Integer f6568a;
                @SerializedName("timeToDeepSleep")
                @Expose
                private Integer b;
                @SerializedName("wasoCount")
                @Expose
                private Integer c;
                @SerializedName("consistency")
                @Expose
                private String d;

                public String getConsistency() {
                    return this.d;
                }

                public Integer getSleepDuration() {
                    return this.f6568a;
                }

                public Integer getTimeToDeepSleep() {
                    return this.b;
                }

                public Integer getWasoCount() {
                    return this.c;
                }

                public void setConsistency(String str) {
                    this.d = str;
                }

                public void setSleepDuration(Integer num) {
                    this.f6568a = num;
                }

                public void setTimeToDeepSleep(Integer num) {
                    this.b = num;
                }

                public void setWasoCount(Integer num) {
                    this.c = num;
                }
            }

            /* loaded from: classes8.dex */
            public static class Source {
                @SerializedName("type")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private String f6569a;
                @SerializedName("identifier")
                @Expose
                private String b;

                public String getIdentifier() {
                    return this.b;
                }

                public String getType() {
                    return this.f6569a;
                }

                public void setIdentifier(String str) {
                    this.b = str;
                }

                public void setType(String str) {
                    this.f6569a = str;
                }
            }

            public String getBaseUnit() {
                return this.c;
            }

            public String getComputedDate() {
                return this.f6567a;
            }

            public Rationale getRationale() {
                return this.e;
            }

            public Source getSource() {
                return this.d;
            }

            public Integer getValue() {
                return this.b;
            }

            public void setBaseUnit(String str) {
                this.c = str;
            }

            public void setComputedDate(String str) {
                this.f6567a = str;
            }

            public void setRationale(Rationale rationale) {
                this.e = rationale;
            }

            public void setSource(Source source) {
                this.d = source;
            }

            public void setValue(Integer num) {
                this.b = num;
            }
        }

        public BaseUnits getBaseUnits() {
            return this.b;
        }

        public Quality getQuality() {
            return this.f6565a;
        }

        public void setBaseUnits(BaseUnits baseUnits) {
            this.b = baseUnits;
        }

        public void setQuality(Quality quality) {
            this.f6565a = quality;
        }
    }

    public String getDate() {
        return this.date;
    }

    public Energy getEnergy() {
        return this.energy;
    }

    public OtherParams getOtherParams() {
        return this.otherParams;
    }

    public RespiratoryRate getRespiratoryRate() {
        return this.respiratoryRate;
    }

    public Sleep getSleep() {
        return this.sleep;
    }

    public String getTzOffset() {
        return this.tzOffset;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
    }

    public void setOtherParams(OtherParams otherParams) {
        this.otherParams = otherParams;
    }

    public void setRespiratoryRate(RespiratoryRate respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public void setSleep(Sleep sleep) {
        this.sleep = sleep;
    }

    public void setTzOffset(String str) {
        this.tzOffset = str;
    }
}
