package com.coveiot.android.activitymodes.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.activitymodes.database.entities.ActivityDataSample;
import com.coveiot.android.activitymodes.database.entities.BadmintonSample;
import com.coveiot.android.activitymodes.database.entities.BasketBallSample;
import com.coveiot.android.activitymodes.database.entities.ClimbingSample;
import com.coveiot.android.activitymodes.database.entities.CyclingSample;
import com.coveiot.android.activitymodes.database.entities.DanceSample;
import com.coveiot.android.activitymodes.database.entities.EllipticalSample;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.entities.FootballSample;
import com.coveiot.android.activitymodes.database.entities.FreeExerciseSample;
import com.coveiot.android.activitymodes.database.entities.HikingSample;
import com.coveiot.android.activitymodes.database.entities.MeditationSample;
import com.coveiot.android.activitymodes.database.entities.PhysicalActivitySample;
import com.coveiot.android.activitymodes.database.entities.RowingMachineSample;
import com.coveiot.android.activitymodes.database.entities.RunSample;
import com.coveiot.android.activitymodes.database.entities.SkippingSample;
import com.coveiot.android.activitymodes.database.entities.TennisSample;
import com.coveiot.android.activitymodes.database.entities.TreadmillSample;
import com.coveiot.android.activitymodes.database.entities.WalkSample;
import com.coveiot.android.activitymodes.database.entities.WorkoutSample;
import com.coveiot.android.activitymodes.database.entities.YogaSample;
import com.coveiot.android.activitymodes.repository.WorkoutSessionRepository;
import com.coveiot.android.activitymodes.utils.ActivityMode;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class ViewModelWorkoutDetails extends AndroidViewModel {
    @NotNull
    public Context d;
    @NotNull
    public LiveData<EntityWorkoutSession> e;
    @NotNull
    public LiveData<List<RunSample>> f;
    @NotNull
    public LiveData<List<TreadmillSample>> g;
    @NotNull
    public LiveData<List<FreeExerciseSample>> h;
    @NotNull
    public LiveData<List<ClimbingSample>> i;
    @NotNull
    public LiveData<List<WalkSample>> j;
    @NotNull
    public LiveData<List<PhysicalActivitySample>> k;
    @NotNull
    public LiveData<List<BadmintonSample>> l;
    @NotNull
    public LiveData<List<BasketBallSample>> m;
    @NotNull
    public LiveData<List<CyclingSample>> n;
    @NotNull
    public LiveData<List<MeditationSample>> o;
    @NotNull
    public LiveData<List<DanceSample>> p;
    @NotNull
    public LiveData<List<FootballSample>> q;
    @NotNull
    public LiveData<List<HikingSample>> r;
    @NotNull
    public LiveData<List<TennisSample>> s;
    @NotNull
    public LiveData<List<WorkoutSample>> t;
    @NotNull
    public LiveData<List<YogaSample>> u;
    @NotNull
    public LiveData<List<SkippingSample>> v;
    @NotNull
    public LiveData<List<EllipticalSample>> w;
    @NotNull
    public LiveData<List<RowingMachineSample>> x;
    @NotNull
    public LiveData<List<ActivityDataSample>> y;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.RUN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.PHYSICAL_ACTIVITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ActivityMode.TREADMILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ActivityMode.CLIMBING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ActivityMode.WALK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ActivityMode.BADMINTON.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ActivityMode.FREE_EXERCISE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ActivityMode.BASKETBALL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ActivityMode.CYCLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ActivityMode.MEDITATION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ActivityMode.DANCE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ActivityMode.FOOTBALL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ActivityMode.HIKING.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ActivityMode.TENNIS.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ActivityMode.WORKOUT.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ActivityMode.YOGA.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ActivityMode.SKIPPING.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ActivityMode.ELLIPTICAL.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ActivityMode.ROWING.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewModelWorkoutDetails(@NotNull Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.d = application;
        this.e = new MutableLiveData();
        this.f = new MutableLiveData();
        this.k = new MutableLiveData();
        this.g = new MutableLiveData();
        this.i = new MutableLiveData();
        this.j = new MutableLiveData();
        this.l = new MutableLiveData();
        this.m = new MutableLiveData();
        this.n = new MutableLiveData();
        this.o = new MutableLiveData();
        this.p = new MutableLiveData();
        this.q = new MutableLiveData();
        this.r = new MutableLiveData();
        this.s = new MutableLiveData();
        this.t = new MutableLiveData();
        this.u = new MutableLiveData();
        this.v = new MutableLiveData();
        this.h = new MutableLiveData();
        this.w = new MutableLiveData();
        this.x = new MutableLiveData();
        this.y = new MutableLiveData();
    }

    @NotNull
    public final LiveData<List<ActivityDataSample>> getLiveDataActivityDataSamples() {
        return this.y;
    }

    @NotNull
    public final LiveData<List<BadmintonSample>> getLiveDataBadmintonSamples() {
        return this.l;
    }

    @NotNull
    public final LiveData<List<BasketBallSample>> getLiveDataBasketBallSamples() {
        return this.m;
    }

    @NotNull
    public final LiveData<List<ClimbingSample>> getLiveDataClimingSamples() {
        return this.i;
    }

    @NotNull
    public final LiveData<List<CyclingSample>> getLiveDataCyclingSamples() {
        return this.n;
    }

    @NotNull
    public final LiveData<List<DanceSample>> getLiveDataDanceSamples() {
        return this.p;
    }

    @NotNull
    public final LiveData<List<EllipticalSample>> getLiveDataEllipticalSamples() {
        return this.w;
    }

    @NotNull
    public final LiveData<List<FootballSample>> getLiveDataFootBallSamples() {
        return this.q;
    }

    @NotNull
    public final LiveData<List<FreeExerciseSample>> getLiveDataFreeExerciseSamples() {
        return this.h;
    }

    @NotNull
    public final LiveData<List<HikingSample>> getLiveDataHikingSamples() {
        return this.r;
    }

    @NotNull
    public final LiveData<List<MeditationSample>> getLiveDataMeditationSamples() {
        return this.o;
    }

    @NotNull
    public final LiveData<List<PhysicalActivitySample>> getLiveDataPhysicalSamples() {
        return this.k;
    }

    @NotNull
    public final LiveData<List<RowingMachineSample>> getLiveDataRowingMachineSamples() {
        return this.x;
    }

    @NotNull
    public final LiveData<List<RunSample>> getLiveDataRunSamples() {
        return this.f;
    }

    @NotNull
    public final LiveData<List<SkippingSample>> getLiveDataSkippingSamples() {
        return this.v;
    }

    @NotNull
    public final LiveData<List<TennisSample>> getLiveDataTennisSamples() {
        return this.s;
    }

    @NotNull
    public final LiveData<List<TreadmillSample>> getLiveDataTreadmillSamples() {
        return this.g;
    }

    @NotNull
    public final LiveData<List<WalkSample>> getLiveDataWalkSamples() {
        return this.j;
    }

    @NotNull
    public final LiveData<List<WorkoutSample>> getLiveDataWorkoutSamples() {
        return this.t;
    }

    @NotNull
    public final LiveData<EntityWorkoutSession> getLiveDataWorkoutSession() {
        return this.e;
    }

    @NotNull
    public final LiveData<List<YogaSample>> getLiveDataYogaSamples() {
        return this.u;
    }

    public final void getWorkoutSessionAndSamplesFromDB(@NotNull String sessionId, @NotNull ActivityMode activityMode) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
        this.e = companion.getInstance(this.d).getSessionLiveData(sessionId);
        switch (WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()]) {
            case 1:
                this.f = companion.getInstance(this.d).getRunSamplesListLiveDataBy(sessionId);
                return;
            case 2:
                this.k = companion.getInstance(this.d).getPhysicalActivitySamplesLiveDataBy(sessionId);
                return;
            case 3:
                this.g = companion.getInstance(this.d).getTreadmillSamplesListLiveDataBy(sessionId);
                return;
            case 4:
                this.i = companion.getInstance(this.d).getClimbingSamplesListLiveDataBy(sessionId);
                return;
            case 5:
                this.j = companion.getInstance(this.d).getWalkSamplesListLiveDataBy(sessionId);
                return;
            case 6:
                this.l = companion.getInstance(this.d).getBadmintonSamplesListLiveDataBy(sessionId);
                return;
            case 7:
                this.h = companion.getInstance(this.d).getFreeExerciseSamplesListLiveDataBy(sessionId);
                return;
            case 8:
                this.m = companion.getInstance(this.d).getBasketBallSamplesListLiveDataBy(sessionId);
                return;
            case 9:
                this.n = companion.getInstance(this.d).getCyclingSamplesListLiveDataBy(sessionId);
                return;
            case 10:
                this.o = companion.getInstance(this.d).getMeditationSamplesListLiveDataBy(sessionId);
                return;
            case 11:
                this.p = companion.getInstance(this.d).getDanceSamplesListLiveDataBy(sessionId);
                return;
            case 12:
                this.q = companion.getInstance(this.d).getFootBallSamplesListLiveDataBy(sessionId);
                return;
            case 13:
                this.r = companion.getInstance(this.d).getHikingSamplesListLiveDataBy(sessionId);
                return;
            case 14:
                this.s = companion.getInstance(this.d).getTennisSamplesListLiveDataBy(sessionId);
                return;
            case 15:
                this.t = companion.getInstance(this.d).getWorkoutSamplesListLiveDataBy(sessionId);
                return;
            case 16:
                this.u = companion.getInstance(this.d).getYogaSamplesListLiveDataBy(sessionId);
                return;
            case 17:
                this.v = companion.getInstance(this.d).getSkippingSamplesListLiveDataBy(sessionId);
                return;
            case 18:
                this.w = companion.getInstance(this.d).getEllipticalSamplesListLiveDataBy(sessionId);
                return;
            case 19:
                this.x = companion.getInstance(this.d).getRowingMachineSamplesListLiveDataBy(sessionId);
                return;
            default:
                return;
        }
    }

    public final void getWorkoutSessionAndSamplesFromGenericActivityData(@NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        WorkoutSessionRepository.Companion companion = WorkoutSessionRepository.Companion;
        this.e = companion.getInstance(this.d).getSessionLiveData(sessionId);
        this.y = companion.getInstance(this.d).getActivityDataSamplesListLiveDataBy(sessionId);
    }

    public final void setLiveDataActivityDataSamples(@NotNull LiveData<List<ActivityDataSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.y = liveData;
    }

    public final void setLiveDataBadmintonSamples(@NotNull LiveData<List<BadmintonSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.l = liveData;
    }

    public final void setLiveDataBasketBallSamples(@NotNull LiveData<List<BasketBallSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.m = liveData;
    }

    public final void setLiveDataClimingSamples(@NotNull LiveData<List<ClimbingSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.i = liveData;
    }

    public final void setLiveDataCyclingSamples(@NotNull LiveData<List<CyclingSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.n = liveData;
    }

    public final void setLiveDataDanceSamples(@NotNull LiveData<List<DanceSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.p = liveData;
    }

    public final void setLiveDataEllipticalSamples(@NotNull LiveData<List<EllipticalSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.w = liveData;
    }

    public final void setLiveDataFootBallSamples(@NotNull LiveData<List<FootballSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.q = liveData;
    }

    public final void setLiveDataFreeExerciseSamples(@NotNull LiveData<List<FreeExerciseSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.h = liveData;
    }

    public final void setLiveDataHikingSamples(@NotNull LiveData<List<HikingSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.r = liveData;
    }

    public final void setLiveDataMeditationSamples(@NotNull LiveData<List<MeditationSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.o = liveData;
    }

    public final void setLiveDataPhysicalSamples(@NotNull LiveData<List<PhysicalActivitySample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.k = liveData;
    }

    public final void setLiveDataRowingMachineSamples(@NotNull LiveData<List<RowingMachineSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.x = liveData;
    }

    public final void setLiveDataRunSamples(@NotNull LiveData<List<RunSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.f = liveData;
    }

    public final void setLiveDataSkippingSamples(@NotNull LiveData<List<SkippingSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.v = liveData;
    }

    public final void setLiveDataTennisSamples(@NotNull LiveData<List<TennisSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.s = liveData;
    }

    public final void setLiveDataTreadmillSamples(@NotNull LiveData<List<TreadmillSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.g = liveData;
    }

    public final void setLiveDataWalkSamples(@NotNull LiveData<List<WalkSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.j = liveData;
    }

    public final void setLiveDataWorkoutSamples(@NotNull LiveData<List<WorkoutSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.t = liveData;
    }

    public final void setLiveDataWorkoutSession(@NotNull LiveData<EntityWorkoutSession> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.e = liveData;
    }

    public final void setLiveDataYogaSamples(@NotNull LiveData<List<YogaSample>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.u = liveData;
    }
}
