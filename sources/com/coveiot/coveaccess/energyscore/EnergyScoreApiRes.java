package com.coveiot.coveaccess.energyscore;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes8.dex */
public class EnergyScoreApiRes {
    @SerializedName("data")
    @Expose

    /* renamed from: a  reason: collision with root package name */
    private List<Data> f6511a;
    @SerializedName(Constants.KEY_MESSAGE)
    @Expose
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String c;

    /* loaded from: classes8.dex */
    public class Data {
        @SerializedName("date")
        @Expose

        /* renamed from: a  reason: collision with root package name */
        private String f6512a;
        @SerializedName("computedDate")
        @Expose
        private String b;
        @SerializedName("sleepScore")
        @Expose
        private Integer c;
        @SerializedName("algoIdentifier")
        @Expose
        private String d;
        @SerializedName(NotificationCompat.CATEGORY_STATUS)
        @Expose
        private String e;
        @SerializedName("dockPoints")
        @Expose
        private LinkedHashMap<String, Double> f;
        @SerializedName("replenishPoints")
        @Expose
        private LinkedHashMap<String, Double> g;
        @SerializedName("hourlyEnergyLevel")
        @Expose
        private LinkedHashMap<String, Double> h;
        @SerializedName("initialEnergyLevel")
        @Expose
        private Integer i;
        @SerializedName("currentEnergyLevel")
        @Expose
        private Integer j;
        @SerializedName("contributingFactors")
        @Expose
        private ContributingFactors k;

        /* loaded from: classes8.dex */
        public class ContributingFactors {
            @SerializedName("dock")
            @Expose

            /* renamed from: a  reason: collision with root package name */
            private Dock f6513a;
            @SerializedName("replenish")
            @Expose
            private Replenish b;

            /* loaded from: classes8.dex */
            public class Dock {
                @SerializedName("sessions")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private List<Session> f6514a = null;
                @SerializedName("nonSessionCalorieContribution")
                @Expose
                private Double b;
                @SerializedName("nonSessionCalorie")
                @Expose
                private Integer c;

                /* loaded from: classes8.dex */
                public class Session {
                    @SerializedName("sessionType")
                    @Expose

                    /* renamed from: a  reason: collision with root package name */
                    private String f6515a;
                    @SerializedName("logs")
                    @Expose
                    private List<Log> b = null;
                    @SerializedName("totalDuration")
                    @Expose
                    private Integer c;
                    @SerializedName("contribution")
                    @Expose
                    private Double d;

                    /* loaded from: classes8.dex */
                    public class Log {
                        @SerializedName("sessionStartDate")
                        @Expose

                        /* renamed from: a  reason: collision with root package name */
                        private String f6516a;
                        @SerializedName("sessionEndDate")
                        @Expose
                        private String b;
                        @SerializedName("activityDuration")
                        @Expose
                        private Integer c;

                        public Log(Session session) {
                        }

                        public Integer getActivityDuration() {
                            return this.c;
                        }

                        public String getSessionEndDate() {
                            return this.b;
                        }

                        public String getSessionStartDate() {
                            return this.f6516a;
                        }

                        public void setActivityDuration(Integer num) {
                            this.c = num;
                        }

                        public void setSessionEndDate(String str) {
                            this.b = str;
                        }

                        public void setSessionStartDate(String str) {
                            this.f6516a = str;
                        }
                    }

                    public Session(Dock dock) {
                    }

                    public Double getContribution() {
                        return this.d;
                    }

                    public List<Log> getLogs() {
                        return this.b;
                    }

                    public String getSessionType() {
                        return this.f6515a;
                    }

                    public Integer getTotalDuration() {
                        return this.c;
                    }

                    public void setContribution(Double d) {
                        this.d = d;
                    }

                    public void setLogs(List<Log> list) {
                        this.b = list;
                    }

                    public void setSessionType(String str) {
                        this.f6515a = str;
                    }

                    public void setTotalDuration(Integer num) {
                        this.c = num;
                    }
                }

                public Dock(ContributingFactors contributingFactors) {
                }

                public Integer getNonSessionCalorie() {
                    return this.c;
                }

                public Double getNonSessionCalorieContribution() {
                    return this.b;
                }

                public List<Session> getSessions() {
                    return this.f6514a;
                }

                public void setNonSessionCalorie(Integer num) {
                    this.c = num;
                }

                public void setNonSessionCalorieContribution(Double d) {
                    this.b = d;
                }

                public void setSessions(List<Session> list) {
                    this.f6514a = list;
                }
            }

            /* loaded from: classes8.dex */
            public class Replenish {
                @SerializedName("sleepScoreContribution")
                @Expose

                /* renamed from: a  reason: collision with root package name */
                private Double f6517a;

                public Replenish(ContributingFactors contributingFactors) {
                }

                public Double getSleepScoreContribution() {
                    return this.f6517a;
                }

                public void setSleepScoreContribution(Double d) {
                    this.f6517a = d;
                }
            }

            public ContributingFactors(Data data) {
            }

            public Dock getDock() {
                return this.f6513a;
            }

            public Replenish getReplenish() {
                return this.b;
            }

            public void setDock(Dock dock) {
                this.f6513a = dock;
            }

            public void setReplenish(Replenish replenish) {
                this.b = replenish;
            }
        }

        public Data(EnergyScoreApiRes energyScoreApiRes) {
        }

        public String getAlgoIdentifier() {
            return this.d;
        }

        public String getComputedDate() {
            return this.b;
        }

        public ContributingFactors getContributingFactors() {
            return this.k;
        }

        public Integer getCurrentEnergyLevel() {
            return this.j;
        }

        public String getDate() {
            return this.f6512a;
        }

        public LinkedHashMap<String, Double> getDockPoint() {
            return this.f;
        }

        public LinkedHashMap<String, Double> getHourlyEnergyLevel() {
            return this.h;
        }

        public Integer getInitialEnergyLevel() {
            return this.i;
        }

        public LinkedHashMap<String, Double> getReplenishPoints() {
            return this.g;
        }

        public Integer getSleepScore() {
            return this.c;
        }

        public String getStatus() {
            return this.e;
        }

        public void setAlgoIdentifier(String str) {
            this.d = str;
        }

        public void setComputedDate(String str) {
            this.b = str;
        }

        public void setContributingFactors(ContributingFactors contributingFactors) {
            this.k = contributingFactors;
        }

        public void setCurrentEnergyLevel(Integer num) {
            this.j = num;
        }

        public void setDate(String str) {
            this.f6512a = str;
        }

        public void setDockPoint(LinkedHashMap<String, Double> linkedHashMap) {
            this.f = linkedHashMap;
        }

        public void setHourlyEnergyLevel(LinkedHashMap<String, Double> linkedHashMap) {
            this.h = linkedHashMap;
        }

        public void setInitialEnergyLevel(Integer num) {
            this.i = num;
        }

        public void setReplenishPoints(LinkedHashMap<String, Double> linkedHashMap) {
            this.g = linkedHashMap;
        }

        public void setSleepScore(Integer num) {
            this.c = num;
        }

        public void setStatus(String str) {
            this.e = str;
        }
    }

    public List<Data> getData() {
        return this.f6511a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setData(List<Data> list) {
        this.f6511a = list;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }

    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }
}
