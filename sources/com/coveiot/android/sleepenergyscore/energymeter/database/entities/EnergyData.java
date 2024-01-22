package com.coveiot.android.sleepenergyscore.energymeter.database.entities;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.LinkedHashMap;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyData {
    @SerializedName("date")
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private String f5706a;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Nullable
    private String b;
    @SerializedName("algoIdentifier")
    @Nullable
    private String c;
    @SerializedName("computedDate")
    @Nullable
    private String d;
    @SerializedName("dockPoint")
    @Nullable
    private LinkedHashMap<String, Double> e;
    @SerializedName("replenishPoints")
    @Nullable
    private LinkedHashMap<String, Double> f;
    @SerializedName("hourlyEnergyLevel")
    @Nullable
    private LinkedHashMap<String, Double> g;
    @SerializedName("initialEnergyLevel")
    @Nullable
    private Integer h;
    @SerializedName("currentEnergyLevel")
    @Nullable
    private Integer i;
    @SerializedName("contributingFactors")
    @Nullable
    private ContributingFactors j;

    /* loaded from: classes6.dex */
    public final class ContributingFactors {
        @SerializedName("dock")
        @Expose
        @Nullable

        /* renamed from: a  reason: collision with root package name */
        private Dock f5707a;
        @SerializedName("replenish")
        @Expose
        @Nullable
        private Replenish b;

        /* loaded from: classes6.dex */
        public final class Dock {
            @SerializedName("sessions")
            @Expose
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            private List<Session> f5708a;
            @SerializedName("nonSessionCalorieContribution")
            @Expose
            @Nullable
            private Double b;
            @SerializedName("nonSessionCalorie")
            @Expose
            @Nullable
            private Integer c;

            /* loaded from: classes6.dex */
            public final class Session {
                @SerializedName("sessionType")
                @Expose
                @Nullable

                /* renamed from: a  reason: collision with root package name */
                private String f5709a;
                @SerializedName("logs")
                @Expose
                @Nullable
                private List<Log> b;
                @SerializedName("totalDuration")
                @Expose
                @Nullable
                private Integer c;
                @SerializedName("contribution")
                @Expose
                @Nullable
                private Double d;

                /* loaded from: classes6.dex */
                public final class Log {
                    @SerializedName("sessionStartDate")
                    @Expose
                    @Nullable

                    /* renamed from: a  reason: collision with root package name */
                    private String f5710a;
                    @SerializedName("sessionEndDate")
                    @Expose
                    @Nullable
                    private String b;
                    @SerializedName("activityDuration")
                    @Expose
                    @Nullable
                    private Integer c;

                    public Log(Session session) {
                    }

                    @Nullable
                    public final Integer getActivityDuration() {
                        return this.c;
                    }

                    @Nullable
                    public final String getSessionEndDate() {
                        return this.b;
                    }

                    @Nullable
                    public final String getSessionStartDate() {
                        return this.f5710a;
                    }

                    public final void setActivityDuration(@Nullable Integer num) {
                        this.c = num;
                    }

                    public final void setSessionEndDate(@Nullable String str) {
                        this.b = str;
                    }

                    public final void setSessionStartDate(@Nullable String str) {
                        this.f5710a = str;
                    }
                }

                public Session(Dock dock) {
                }

                @Nullable
                public final Double getContribution() {
                    return this.d;
                }

                @Nullable
                public final List<Log> getLogs() {
                    return this.b;
                }

                @Nullable
                public final String getSessionType() {
                    return this.f5709a;
                }

                @Nullable
                public final Integer getTotalDuration() {
                    return this.c;
                }

                public final void setContribution(@Nullable Double d) {
                    this.d = d;
                }

                public final void setLogs(@Nullable List<Log> list) {
                    this.b = list;
                }

                public final void setSessionType(@Nullable String str) {
                    this.f5709a = str;
                }

                public final void setTotalDuration(@Nullable Integer num) {
                    this.c = num;
                }
            }

            public Dock(ContributingFactors contributingFactors) {
            }

            @Nullable
            public final Integer getNonSessionCalorie() {
                return this.c;
            }

            @Nullable
            public final Double getNonSessionCalorieContribution() {
                return this.b;
            }

            @Nullable
            public final List<Session> getSessions() {
                return this.f5708a;
            }

            public final void setNonSessionCalorie(@Nullable Integer num) {
                this.c = num;
            }

            public final void setNonSessionCalorieContribution(@Nullable Double d) {
                this.b = d;
            }

            public final void setSessions(@Nullable List<Session> list) {
                this.f5708a = list;
            }
        }

        /* loaded from: classes6.dex */
        public final class Replenish {
            @SerializedName("sleepScoreContribution")
            @Expose
            @Nullable

            /* renamed from: a  reason: collision with root package name */
            private Double f5711a;

            public Replenish(ContributingFactors contributingFactors) {
            }

            @Nullable
            public final Double getSleepScoreContribution() {
                return this.f5711a;
            }

            public final void setSleepScoreContribution(@Nullable Double d) {
                this.f5711a = d;
            }
        }

        public ContributingFactors(EnergyData energyData) {
        }

        @Nullable
        public final Dock getDock() {
            return this.f5707a;
        }

        @Nullable
        public final Replenish getReplenish() {
            return this.b;
        }

        public final void setDock(@Nullable Dock dock) {
            this.f5707a = dock;
        }

        public final void setReplenish(@Nullable Replenish replenish) {
            this.b = replenish;
        }
    }

    @Nullable
    public final String getAlgoIdentifier() {
        return this.c;
    }

    @Nullable
    public final String getComputedDate() {
        return this.d;
    }

    @Nullable
    public final ContributingFactors getContributingFactors() {
        return this.j;
    }

    @Nullable
    public final Integer getCurrentEnergyLevel() {
        return this.i;
    }

    @Nullable
    public final String getDate() {
        return this.f5706a;
    }

    @Nullable
    public final LinkedHashMap<String, Double> getDockPoint() {
        return this.e;
    }

    @Nullable
    public final LinkedHashMap<String, Double> getHourlyEnergyLevel() {
        return this.g;
    }

    @Nullable
    public final Integer getInitialEnergyLevel() {
        return this.h;
    }

    @Nullable
    public final LinkedHashMap<String, Double> getReplenishPoints() {
        return this.f;
    }

    @Nullable
    public final String getStatus() {
        return this.b;
    }

    public final void setAlgoIdentifier(@Nullable String str) {
        this.c = str;
    }

    public final void setComputedDate(@Nullable String str) {
        this.d = str;
    }

    public final void setContributingFactors(@Nullable ContributingFactors contributingFactors) {
        this.j = contributingFactors;
    }

    public final void setCurrentEnergyLevel(@Nullable Integer num) {
        this.i = num;
    }

    public final void setDate(@Nullable String str) {
        this.f5706a = str;
    }

    public final void setDockPoint(@Nullable LinkedHashMap<String, Double> linkedHashMap) {
        this.e = linkedHashMap;
    }

    public final void setHourlyEnergyLevel(@Nullable LinkedHashMap<String, Double> linkedHashMap) {
        this.g = linkedHashMap;
    }

    public final void setInitialEnergyLevel(@Nullable Integer num) {
        this.h = num;
    }

    public final void setReplenishPoints(@Nullable LinkedHashMap<String, Double> linkedHashMap) {
        this.f = linkedHashMap;
    }

    public final void setStatus(@Nullable String str) {
        this.b = str;
    }
}
