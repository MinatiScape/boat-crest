package com.coveiot.coveaccess.fitnesscomputeddataapi;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.fitnesscomputeddataapi.FitnessComputedData;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.ido.ble.event.stat.one.d;
import java.util.List;
/* loaded from: classes8.dex */
public class GetFitnessComputedDataResponse {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private Data f6572a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("fitnessDataComputed")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private List<FitnessDataComputed> f6573a = null;

        /* loaded from: classes8.dex */
        public class FitnessDataComputed {
            @SerializedName("userDeviceId")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private String f6574a;
            @SerializedName("date")
            @Expose
            private String b;
            @SerializedName("tzOffset")
            @Expose
            private String c;
            @SerializedName(FitnessActivities.SLEEP)
            @Expose
            private Sleep d;
            @SerializedName("energy")
            @Expose
            private Energy e;
            @SerializedName("respiratoryRate")
            @Expose
            private FitnessComputedData.RespiratoryRate f;

            /* loaded from: classes8.dex */
            public class Energy {
                @SerializedName(d.O)
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private FeedBackData f6575a;
                @SerializedName(FirebaseAnalytics.Param.LEVEL)
                @Expose
                private Level b;
                @SerializedName("baseUnits")
                @Expose
                private BaseUnits c;

                /* loaded from: classes8.dex */
                public class BaseUnits {
                    @SerializedName("activityDuration")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6576a;

                    public BaseUnits(Energy energy) {
                    }

                    public String getActivityDuration() {
                        return this.f6576a;
                    }

                    public void setActivityDuration(String str) {
                        this.f6576a = str;
                    }
                }

                /* loaded from: classes8.dex */
                public class Level {
                    @SerializedName("computedDate")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6577a;
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
                    public class Rationale {
                        @SerializedName("drains")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private List<Drain> f6578a = null;
                        @SerializedName("gains")
                        @Expose
                        private List<Gain> b = null;

                        /* loaded from: classes8.dex */
                        public class Drain {
                            @SerializedName("type")
                            @Expose

                            /* renamed from: a  reason: collision with root package name */
                            private String f6579a;
                            @SerializedName("value")
                            @Expose
                            private Integer b;
                            @SerializedName("activityDuration")
                            @Expose
                            private Object c;
                            @SerializedName("calories")
                            @Expose
                            private Integer d;
                            @SerializedName("activityId")
                            @Expose
                            private Integer e;
                            @SerializedName("categoryId")
                            @Expose
                            private Integer f;

                            public Drain(Rationale rationale) {
                            }

                            public Object getActivityDuration() {
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
                                return this.f6579a;
                            }

                            public Integer getValue() {
                                return this.b;
                            }

                            public void setActivityDuration(Object obj) {
                                this.c = obj;
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
                                this.f6579a = str;
                            }

                            public void setValue(Integer num) {
                                this.b = num;
                            }
                        }

                        /* loaded from: classes8.dex */
                        public class Gain {
                            @SerializedName("type")
                            @Expose

                            /* renamed from: a  reason: collision with root package name */
                            private String f6580a;
                            @SerializedName("value")
                            @Expose
                            private Integer b;
                            @SerializedName("activityDuration")
                            @Expose
                            private Object c;
                            @SerializedName("calories")
                            @Expose
                            private Integer d;

                            public Gain(Rationale rationale) {
                            }

                            public Object getActivityDuration() {
                                return this.c;
                            }

                            public Integer getCalories() {
                                return this.d;
                            }

                            public String getType() {
                                return this.f6580a;
                            }

                            public Integer getValue() {
                                return this.b;
                            }

                            public void setActivityDuration(Object obj) {
                                this.c = obj;
                            }

                            public void setCalories(Integer num) {
                                this.d = num;
                            }

                            public void setType(String str) {
                                this.f6580a = str;
                            }

                            public void setValue(Integer num) {
                                this.b = num;
                            }
                        }

                        public Rationale(Level level) {
                        }

                        public List<Drain> getDrains() {
                            return this.f6578a;
                        }

                        public List<Gain> getGains() {
                            return this.b;
                        }

                        public void setDrains(List<Drain> list) {
                            this.f6578a = list;
                        }

                        public void setGains(List<Gain> list) {
                            this.b = list;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public class Source {
                        @SerializedName("type")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private String f6581a;
                        @SerializedName("identifier")
                        @Expose
                        private String b;

                        public Source(Level level) {
                        }

                        public String getIdentifier() {
                            return this.b;
                        }

                        public String getType() {
                            return this.f6581a;
                        }

                        public void setIdentifier(String str) {
                            this.b = str;
                        }

                        public void setType(String str) {
                            this.f6581a = str;
                        }
                    }

                    public Level(Energy energy) {
                    }

                    public String getBaseUnit() {
                        return this.c;
                    }

                    public String getComputedDate() {
                        return this.f6577a;
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
                        this.f6577a = str;
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

                public Energy(FitnessDataComputed fitnessDataComputed) {
                }

                public BaseUnits getBaseUnits() {
                    return this.c;
                }

                public FeedBackData getFeedback() {
                    return this.f6575a;
                }

                public Level getLevel() {
                    return this.b;
                }

                public void setBaseUnits(BaseUnits baseUnits) {
                    this.c = baseUnits;
                }

                public void setFeedback(FeedBackData feedBackData) {
                    this.f6575a = feedBackData;
                }

                public void setLevel(Level level) {
                    this.b = level;
                }
            }

            /* loaded from: classes8.dex */
            public class Sleep {
                @SerializedName(d.O)
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private FeedBackData f6582a;
                @SerializedName("quality")
                @Expose
                private Quality b;
                @SerializedName("baseUnits")
                @Expose
                private BaseUnits c;

                /* loaded from: classes8.dex */
                public class BaseUnits {
                    @SerializedName("sleepDuration")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6583a;
                    @SerializedName("timeToDeepSleep")
                    @Expose
                    private String b;

                    public BaseUnits(Sleep sleep) {
                    }

                    public String getSleepDuration() {
                        return this.f6583a;
                    }

                    public String getTimeToDeepSleep() {
                        return this.b;
                    }

                    public void setSleepDuration(String str) {
                        this.f6583a = str;
                    }

                    public void setTimeToDeepSleep(String str) {
                        this.b = str;
                    }
                }

                /* loaded from: classes8.dex */
                public class Quality {
                    @SerializedName("computedDate")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6584a;
                    @SerializedName("value")
                    @Expose
                    private Integer b;
                    @SerializedName("baseUnit")
                    @Expose
                    private String c;
                    @SerializedName("rationale")
                    @Expose
                    private Rationale d;
                    @SerializedName("source")
                    @Expose
                    private Source e;

                    /* loaded from: classes8.dex */
                    public class Rationale {
                        @SerializedName("sleepDuration")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private Integer f6585a;
                        @SerializedName("timeToDeepSleep")
                        @Expose
                        private Integer b;
                        @SerializedName("wasoCount")
                        @Expose
                        private Integer c;
                        @SerializedName("consistency")
                        @Expose
                        private String d;

                        public Rationale(Quality quality) {
                        }

                        public String getConsistency() {
                            return this.d;
                        }

                        public Integer getSleepDuration() {
                            return this.f6585a;
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
                            this.f6585a = num;
                        }

                        public void setTimeToDeepSleep(Integer num) {
                            this.b = num;
                        }

                        public void setWasoCount(Integer num) {
                            this.c = num;
                        }
                    }

                    /* loaded from: classes8.dex */
                    public class Source {
                        @SerializedName("type")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private String f6586a;
                        @SerializedName("identifier")
                        @Expose
                        private String b;

                        public Source(Quality quality) {
                        }

                        public String getIdentifier() {
                            return this.b;
                        }

                        public String getType() {
                            return this.f6586a;
                        }

                        public void setIdentifier(String str) {
                            this.b = str;
                        }

                        public void setType(String str) {
                            this.f6586a = str;
                        }
                    }

                    public Quality(Sleep sleep) {
                    }

                    public String getBaseUnit() {
                        return this.c;
                    }

                    public String getComputedDate() {
                        return this.f6584a;
                    }

                    public Rationale getRationale() {
                        return this.d;
                    }

                    public Source getSource() {
                        return this.e;
                    }

                    public Integer getValue() {
                        return this.b;
                    }

                    public void setBaseUnit(String str) {
                        this.c = str;
                    }

                    public void setComputedDate(String str) {
                        this.f6584a = str;
                    }

                    public void setRationale(Rationale rationale) {
                        this.d = rationale;
                    }

                    public void setSource(Source source) {
                        this.e = source;
                    }

                    public void setValue(Integer num) {
                        this.b = num;
                    }
                }

                public Sleep(FitnessDataComputed fitnessDataComputed) {
                }

                public BaseUnits getBaseUnits() {
                    return this.c;
                }

                public FeedBackData getFeedback() {
                    return this.f6582a;
                }

                public Quality getQuality() {
                    return this.b;
                }

                public void setBaseUnits(BaseUnits baseUnits) {
                    this.c = baseUnits;
                }

                public void setFeedback(FeedBackData feedBackData) {
                    this.f6582a = feedBackData;
                }

                public void setQuality(Quality quality) {
                    this.b = quality;
                }
            }

            public FitnessDataComputed(Data data) {
            }

            public String getDate() {
                return this.b;
            }

            public Energy getEnergy() {
                return this.e;
            }

            public FitnessComputedData.RespiratoryRate getRespiratoryRate() {
                return this.f;
            }

            public Sleep getSleep() {
                return this.d;
            }

            public String getTzOffset() {
                return this.c;
            }

            public String getUserDeviceId() {
                return this.f6574a;
            }

            public void setDate(String str) {
                this.b = str;
            }

            public void setEnergy(Energy energy) {
                this.e = energy;
            }

            public void setRespiratoryRate(FitnessComputedData.RespiratoryRate respiratoryRate) {
                this.f = respiratoryRate;
            }

            public void setSleep(Sleep sleep) {
                this.d = sleep;
            }

            public void setTzOffset(String str) {
                this.c = str;
            }

            public void setUserDeviceId(String str) {
                this.f6574a = str;
            }
        }

        public Data(GetFitnessComputedDataResponse getFitnessComputedDataResponse) {
        }

        public List<FitnessDataComputed> getFitnessDataComputed() {
            return this.f6573a;
        }

        public void setFitnessDataComputed(List<FitnessDataComputed> list) {
            this.f6573a = list;
        }
    }

    public Data getData() {
        return this.f6572a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(Data data) {
        this.f6572a = data;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }
}
