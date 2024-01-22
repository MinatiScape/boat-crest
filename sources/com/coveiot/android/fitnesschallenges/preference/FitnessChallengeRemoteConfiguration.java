package com.coveiot.android.fitnesschallenges.preference;

import com.clevertap.android.sdk.Constants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
/* loaded from: classes2.dex */
public class FitnessChallengeRemoteConfiguration {
    @SerializedName("fitness_challenges")

    /* renamed from: a  reason: collision with root package name */
    private FitnessChallenge f4526a;

    /* loaded from: classes2.dex */
    public class FitnessChallenge {
        @SerializedName("visibility")

        /* renamed from: a  reason: collision with root package name */
        private Boolean f4527a = Boolean.FALSE;
        @SerializedName("distance")
        private Distance b;
        @SerializedName("calories")
        private Calories c;
        @SerializedName("challenge_duration_days")
        private ChallengeDurationDays d;

        /* loaded from: classes2.dex */
        public class Calories {
            @SerializedName("min")

            /* renamed from: a  reason: collision with root package name */
            private Integer f4528a;
            @SerializedName(Constants.PRIORITY_MAX)
            private Integer b;
            @SerializedName(DeviceKey.Step)
            private Integer c;

            public Calories(FitnessChallenge fitnessChallenge) {
            }

            public Integer getMax() {
                return this.b;
            }

            public Integer getMin() {
                return this.f4528a;
            }

            public Integer getStep() {
                return this.c;
            }

            public void setMax(Integer num) {
                this.b = num;
            }

            public void setMin(Integer num) {
                this.f4528a = num;
            }

            public void setStep(Integer num) {
                this.c = num;
            }
        }

        /* loaded from: classes2.dex */
        public class ChallengeDurationDays {
            @SerializedName("min")

            /* renamed from: a  reason: collision with root package name */
            private Integer f4529a;
            @SerializedName(Constants.PRIORITY_MAX)
            private Integer b;

            public ChallengeDurationDays(FitnessChallenge fitnessChallenge) {
            }

            public Integer getMax() {
                return this.b;
            }

            public Integer getMin() {
                return this.f4529a;
            }

            public void setMax(Integer num) {
                this.b = num;
            }

            public void setMin(Integer num) {
                this.f4529a = num;
            }
        }

        /* loaded from: classes2.dex */
        public class Distance {
            @SerializedName("min")

            /* renamed from: a  reason: collision with root package name */
            private Integer f4530a;
            @SerializedName(Constants.PRIORITY_MAX)
            private Integer b;
            @SerializedName(DeviceKey.Step)
            private Integer c;

            public Distance(FitnessChallenge fitnessChallenge) {
            }

            public Integer getMax() {
                return this.b;
            }

            public Integer getMin() {
                return this.f4530a;
            }

            public Integer getStep() {
                return this.c;
            }

            public void setMax(Integer num) {
                this.b = num;
            }

            public void setMin(Integer num) {
                this.f4530a = num;
            }

            public void setStep(Integer num) {
                this.c = num;
            }
        }

        public FitnessChallenge(FitnessChallengeRemoteConfiguration fitnessChallengeRemoteConfiguration) {
        }

        public Calories getCalories() {
            return this.c;
        }

        public ChallengeDurationDays getChallenge_duration_days() {
            return this.d;
        }

        public Distance getDistance() {
            return this.b;
        }

        public Boolean getVisibility() {
            return this.f4527a;
        }

        public void setCalories(Calories calories) {
            this.c = calories;
        }

        public void setChallenge_duration_days(ChallengeDurationDays challengeDurationDays) {
            this.d = challengeDurationDays;
        }

        public void setDistance(Distance distance) {
            this.b = distance;
        }

        public void setVisibility(Boolean bool) {
            this.f4527a = bool;
        }
    }

    public FitnessChallenge getFitness_challenges() {
        return this.f4526a;
    }

    public void setFitness_challenges(FitnessChallenge fitnessChallenge) {
        this.f4526a = fitnessChallenge;
    }
}
