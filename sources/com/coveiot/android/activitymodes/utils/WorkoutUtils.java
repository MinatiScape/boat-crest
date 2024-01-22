package com.coveiot.android.activitymodes.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import com.coveiot.android.activitymodes.BASEUNIT;
import com.coveiot.android.activitymodes.R;
import com.coveiot.android.activitymodes.activity1k.db.entity.EntityPhysicalActivities;
import com.coveiot.android.activitymodes.activity1k.model.CategoryAndActivityModel;
import com.coveiot.android.activitymodes.activity1k.repository.PhysicalActivityRepository;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationActivity;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationDay;
import com.coveiot.android.activitymodes.database.entities.EntityPreparationWeek;
import com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession;
import com.coveiot.android.activitymodes.database.models.HeartRateZoneRanges;
import com.coveiot.android.activitymodes.models.ActivitiesItem;
import com.coveiot.android.activitymodes.models.ActivityShareData;
import com.coveiot.android.activitymodes.models.DayInfo;
import com.coveiot.android.activitymodes.models.WorkoutImageBean;
import com.coveiot.android.activitymodes.models.WorkoutUiBean;
import com.coveiot.android.activitymodes.utils.WorkoutUiBeanProvider;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.khperformancecalculator.model.KHPActivityData;
import com.coveiot.android.khperformancecalculator.preference.KHPerformancePreferenceManager;
import com.coveiot.android.theme.ExtensionFuncsKt;
import com.coveiot.coveaccess.SetupException;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.coveiot.coveaccess.constants.ErrorConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.m;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class WorkoutUtils {
    @NotNull
    public static final WorkoutUtils INSTANCE = new WorkoutUtils();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static String f2881a;

    /* loaded from: classes2.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ActivityMode.values().length];
            try {
                iArr[ActivityMode.WALK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ActivityMode.RUN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ActivityMode.CYCLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ActivityMode.BADMINTON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ActivityMode.FOOTBALL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ActivityMode.TENNIS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[ActivityMode.YOGA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[ActivityMode.MEDITATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[ActivityMode.DANCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[ActivityMode.BASKETBALL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[ActivityMode.HIKING.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[ActivityMode.WORKOUT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[ActivityMode.TREADMILL.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[ActivityMode.CLIMBING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[ActivityMode.SKIPPING.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[ActivityMode.FREE_EXERCISE.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[ActivityMode.ELLIPTICAL.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr[ActivityMode.ROWING.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr[ActivityMode.PHYSICAL_ACTIVITY.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr[ActivityMode.SWIM.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr[ActivityMode.HIIT.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr[ActivityMode.CRICKET.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr[ActivityMode.PILATES.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr[ActivityMode.ZUMBA.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr[ActivityMode.CORE_TRAINING.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr[ActivityMode.COOLDOWN.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr[ActivityMode.TRADITIONAL_STRENGTH_TRAINING.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr[ActivityMode.FUNCTIONAL_STRENGTH_TRAINING.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr[ActivityMode.OTHER.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr[ActivityMode.PULL_UPS.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr[ActivityMode.PUSH_UPS.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr[ActivityMode.SQUAT.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr[ActivityMode.HIGH_KNEE_LIFT.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr[ActivityMode.DUMBBELLS.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr[ActivityMode.BARBELL.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr[ActivityMode.BOXING.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr[ActivityMode.FREE_SPARRING.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr[ActivityMode.HORIZONTAL_BAR.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr[ActivityMode.PARALLEL_BARS.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr[ActivityMode.CARDIO_CRUISER.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr[ActivityMode.CLIMBING_MACHINE.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr[ActivityMode.BOWLING.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr[ActivityMode.PING_PONG.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr[ActivityMode.GOLF.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr[ActivityMode.HOCKEY.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                iArr[ActivityMode.RUGBY.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                iArr[ActivityMode.HANDBALL.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                iArr[ActivityMode.STREET_DANCE.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                iArr[ActivityMode.POPPING.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                iArr[ActivityMode.CLIMB_STAIRS.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                iArr[ActivityMode.SLEDDING.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                iArr[ActivityMode.SKI.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                iArr[ActivityMode.SURFING.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                iArr[ActivityMode.SAILBOATING.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                iArr[ActivityMode.SKATEBOARDING.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                iArr[ActivityMode.ROCK_CLIMBING.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                iArr[ActivityMode.STRENGTH_TRAINING.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                iArr[ActivityMode.FOLK_DANCE.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                iArr[ActivityMode.HAND_CYCLING.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                iArr[ActivityMode.KENDO.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                iArr[ActivityMode.WRESTLING.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                iArr[ActivityMode.TAI_CHI.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                iArr[ActivityMode.GYMNASTICS.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                iArr[ActivityMode.TRACK_FIELD.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                iArr[ActivityMode.MARTIAL_ARTS.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                iArr[ActivityMode.WARM_UP.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                iArr[ActivityMode.SNOW_SPORTS.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                iArr[ActivityMode.LACROSSE.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                iArr[ActivityMode.HULA_HOOP.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                iArr[ActivityMode.DARTS.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                iArr[ActivityMode.ARCHERY.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                iArr[ActivityMode.HORSE_RIDING.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                iArr[ActivityMode.SHUTTLECOCK.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                iArr[ActivityMode.ICE_HOCKEY.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                iArr[ActivityMode.WAIST_TRAINING.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                iArr[ActivityMode.BATTLE_ROPE.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                iArr[ActivityMode.SMITH_MACHINE.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                iArr[ActivityMode.KABADDI.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                iArr[ActivityMode.SHOT_PUT.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                iArr[ActivityMode.SOLID_BALL.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                iArr[ActivityMode.JAVELIN.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                iArr[ActivityMode.LONG_JUMP.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                iArr[ActivityMode.HIGH_JUMP.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                iArr[ActivityMode.SQUARE_DANCE.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                iArr[ActivityMode.KARTING.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                iArr[ActivityMode.DODGEBALL.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                iArr[ActivityMode.YOYO.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                iArr[ActivityMode.LOCKING.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                iArr[ActivityMode.BURPEES.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                iArr[ActivityMode.BELLY_DANCE.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                iArr[ActivityMode.PARKOUR.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                iArr[ActivityMode.JAZZ_DANCE.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                iArr[ActivityMode.MODERN_DANCE.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                iArr[ActivityMode.CROSS_FIT.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                iArr[ActivityMode.LATIN_DANCE.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                iArr[ActivityMode.BALLET.ordinal()] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                iArr[ActivityMode.BASEBALL.ordinal()] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                iArr[ActivityMode.RACQUETBALL.ordinal()] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                iArr[ActivityMode.CURLING.ordinal()] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                iArr[ActivityMode.HUNTING.ordinal()] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                iArr[ActivityMode.SNOWBOARDING.ordinal()] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                iArr[ActivityMode.FISHING.ordinal()] = 102;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                iArr[ActivityMode.DISC_SPORTS.ordinal()] = 103;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                iArr[ActivityMode.FITNESS_GAMING.ordinal()] = 104;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                iArr[ActivityMode.AEROBICS_GYMS.ordinal()] = 105;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                iArr[ActivityMode.GROUP_TRAINING.ordinal()] = 106;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                iArr[ActivityMode.CARDIO_BOXING.ordinal()] = 107;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                iArr[ActivityMode.MOUNTAINEERING.ordinal()] = 108;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                iArr[ActivityMode.FENCING.ordinal()] = 109;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                iArr[ActivityMode.SOFTBALL.ordinal()] = 110;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                iArr[ActivityMode.AMERICAN_FOOTBALL.ordinal()] = 111;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                iArr[ActivityMode.VOLLEYBALL.ordinal()] = 112;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                iArr[ActivityMode.ROLLING.ordinal()] = 113;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                iArr[ActivityMode.PICKLEBALL.ordinal()] = 114;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                iArr[ActivityMode.TAEKWONDO.ordinal()] = 115;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                iArr[ActivityMode.KARATE.ordinal()] = 116;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                iArr[ActivityMode.FLEXIBILITY.ordinal()] = 117;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                iArr[ActivityMode.STEPPER.ordinal()] = 118;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                iArr[ActivityMode.SIT_UPS.ordinal()] = 119;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                iArr[ActivityMode.AEROBICS.ordinal()] = 120;
            } catch (NoSuchFieldError unused120) {
            }
            try {
                iArr[ActivityMode.PLANK.ordinal()] = 121;
            } catch (NoSuchFieldError unused121) {
            }
            try {
                iArr[ActivityMode.JUMPING_JACKS.ordinal()] = 122;
            } catch (NoSuchFieldError unused122) {
            }
            try {
                iArr[ActivityMode.PARACHUTE.ordinal()] = 123;
            } catch (NoSuchFieldError unused123) {
            }
            try {
                iArr[ActivityMode.TRAIL_RUN.ordinal()] = 124;
            } catch (NoSuchFieldError unused124) {
            }
            try {
                iArr[ActivityMode.ROLLER_SKATING.ordinal()] = 125;
            } catch (NoSuchFieldError unused125) {
            }
            try {
                iArr[ActivityMode.WEIGHTLIFTING.ordinal()] = 126;
            } catch (NoSuchFieldError unused126) {
            }
            try {
                iArr[ActivityMode.STRETCHING.ordinal()] = 127;
            } catch (NoSuchFieldError unused127) {
            }
            try {
                iArr[ActivityMode.DEADLIFT.ordinal()] = 128;
            } catch (NoSuchFieldError unused128) {
            }
            try {
                iArr[ActivityMode.UPPER_BODY_TRAINING.ordinal()] = 129;
            } catch (NoSuchFieldError unused129) {
            }
            try {
                iArr[ActivityMode.LOWER_BODY_TRAINING.ordinal()] = 130;
            } catch (NoSuchFieldError unused130) {
            }
            try {
                iArr[ActivityMode.ABS_TRAINING.ordinal()] = 131;
            } catch (NoSuchFieldError unused131) {
            }
            try {
                iArr[ActivityMode.BACK_TRAINING.ordinal()] = 132;
            } catch (NoSuchFieldError unused132) {
            }
            try {
                iArr[ActivityMode.SUP.ordinal()] = 133;
            } catch (NoSuchFieldError unused133) {
            }
            try {
                iArr[ActivityMode.WATER_POLO.ordinal()] = 134;
            } catch (NoSuchFieldError unused134) {
            }
            try {
                iArr[ActivityMode.THRASH.ordinal()] = 135;
            } catch (NoSuchFieldError unused135) {
            }
            try {
                iArr[ActivityMode.KAYAKING.ordinal()] = 136;
            } catch (NoSuchFieldError unused136) {
            }
            try {
                iArr[ActivityMode.DRIFTING.ordinal()] = 137;
            } catch (NoSuchFieldError unused137) {
            }
            try {
                iArr[ActivityMode.BOATING.ordinal()] = 138;
            } catch (NoSuchFieldError unused138) {
            }
            try {
                iArr[ActivityMode.FIN_SWIM.ordinal()] = 139;
            } catch (NoSuchFieldError unused139) {
            }
            try {
                iArr[ActivityMode.DIVING.ordinal()] = 140;
            } catch (NoSuchFieldError unused140) {
            }
            try {
                iArr[ActivityMode.ARTISTIC_SWIM.ordinal()] = 141;
            } catch (NoSuchFieldError unused141) {
            }
            try {
                iArr[ActivityMode.SNORKEL.ordinal()] = 142;
            } catch (NoSuchFieldError unused142) {
            }
            try {
                iArr[ActivityMode.KITESURFING.ordinal()] = 143;
            } catch (NoSuchFieldError unused143) {
            }
            try {
                iArr[ActivityMode.ATV.ordinal()] = 144;
            } catch (NoSuchFieldError unused144) {
            }
            try {
                iArr[ActivityMode.BEACH_SOCCER.ordinal()] = 145;
            } catch (NoSuchFieldError unused145) {
            }
            try {
                iArr[ActivityMode.WUSHU.ordinal()] = 146;
            } catch (NoSuchFieldError unused146) {
            }
            try {
                iArr[ActivityMode.MUAY_THAI.ordinal()] = 147;
            } catch (NoSuchFieldError unused147) {
            }
            try {
                iArr[ActivityMode.JUDO.ordinal()] = 148;
            } catch (NoSuchFieldError unused148) {
            }
            try {
                iArr[ActivityMode.SNOWMOBILE.ordinal()] = 149;
            } catch (NoSuchFieldError unused149) {
            }
            try {
                iArr[ActivityMode.PUCK.ordinal()] = 150;
            } catch (NoSuchFieldError unused150) {
            }
            try {
                iArr[ActivityMode.SKATING.ordinal()] = 151;
            } catch (NoSuchFieldError unused151) {
            }
            try {
                iArr[ActivityMode.SQUASH.ordinal()] = 152;
            } catch (NoSuchFieldError unused152) {
            }
            try {
                iArr[ActivityMode.RAGA.ordinal()] = 153;
            } catch (NoSuchFieldError unused153) {
            }
            try {
                iArr[ActivityMode.BILLIARDS.ordinal()] = 154;
            } catch (NoSuchFieldError unused154) {
            }
            try {
                iArr[ActivityMode.KITE.ordinal()] = 155;
            } catch (NoSuchFieldError unused155) {
            }
            try {
                iArr[ActivityMode.FRISBEE.ordinal()] = 156;
            } catch (NoSuchFieldError unused156) {
            }
            try {
                iArr[ActivityMode.EQUESTRIAN.ordinal()] = 157;
            } catch (NoSuchFieldError unused157) {
            }
            try {
                iArr[ActivityMode.RACING.ordinal()] = 158;
            } catch (NoSuchFieldError unused158) {
            }
            try {
                iArr[ActivityMode.PUSH_PULL.ordinal()] = 159;
            } catch (NoSuchFieldError unused159) {
            }
            try {
                iArr[ActivityMode.AIR_WALKER.ordinal()] = 160;
            } catch (NoSuchFieldError unused160) {
            }
            try {
                iArr[ActivityMode.CROSS_TRAINING.ordinal()] = 161;
            } catch (NoSuchFieldError unused161) {
            }
            try {
                iArr[ActivityMode.MIXED_CARDIO.ordinal()] = 162;
            } catch (NoSuchFieldError unused162) {
            }
            try {
                iArr[ActivityMode.KICKBOXING.ordinal()] = 163;
            } catch (NoSuchFieldError unused163) {
            }
            try {
                iArr[ActivityMode.BARRE.ordinal()] = 164;
            } catch (NoSuchFieldError unused164) {
            }
            try {
                iArr[ActivityMode.AUSTRALIAN_FOOTBALL.ordinal()] = 165;
            } catch (NoSuchFieldError unused165) {
            }
            try {
                iArr[ActivityMode.DOWNHILL_SKIING.ordinal()] = 166;
            } catch (NoSuchFieldError unused166) {
            }
            try {
                iArr[ActivityMode.ATHLETICS.ordinal()] = 167;
            } catch (NoSuchFieldError unused167) {
            }
            try {
                iArr[ActivityMode.VO2MAX.ordinal()] = 168;
            } catch (NoSuchFieldError unused168) {
            }
            try {
                iArr[ActivityMode.TRAMPOLINE.ordinal()] = 169;
            } catch (NoSuchFieldError unused169) {
            }
            try {
                iArr[ActivityMode.HOVERBOARDING.ordinal()] = 170;
            } catch (NoSuchFieldError unused170) {
            }
            try {
                iArr[ActivityMode.BLADING.ordinal()] = 171;
            } catch (NoSuchFieldError unused171) {
            }
            try {
                iArr[ActivityMode.BUNGEE_JUMPING.ordinal()] = 172;
            } catch (NoSuchFieldError unused172) {
            }
            try {
                iArr[ActivityMode.SHOOTING.ordinal()] = 173;
            } catch (NoSuchFieldError unused173) {
            }
            try {
                iArr[ActivityMode.MARATHON.ordinal()] = 174;
            } catch (NoSuchFieldError unused174) {
            }
            try {
                iArr[ActivityMode.TREKKING.ordinal()] = 175;
            } catch (NoSuchFieldError unused175) {
            }
            try {
                iArr[ActivityMode.POLE_DANCE.ordinal()] = 176;
            } catch (NoSuchFieldError unused176) {
            }
            try {
                iArr[ActivityMode.DISCO.ordinal()] = 177;
            } catch (NoSuchFieldError unused177) {
            }
            try {
                iArr[ActivityMode.TAP_DANCE.ordinal()] = 178;
            } catch (NoSuchFieldError unused178) {
            }
            try {
                iArr[ActivityMode.SCOOTER.ordinal()] = 179;
            } catch (NoSuchFieldError unused179) {
            }
            try {
                iArr[ActivityMode.HAMMER.ordinal()] = 180;
            } catch (NoSuchFieldError unused180) {
            }
            try {
                iArr[ActivityMode.LEG_PRESS.ordinal()] = 181;
            } catch (NoSuchFieldError unused181) {
            }
            try {
                iArr[ActivityMode.OFF_ROAD_BIKE.ordinal()] = 182;
            } catch (NoSuchFieldError unused182) {
            }
            try {
                iArr[ActivityMode.MOTOCROSS.ordinal()] = 183;
            } catch (NoSuchFieldError unused183) {
            }
            try {
                iArr[ActivityMode.CROQUET.ordinal()] = 184;
            } catch (NoSuchFieldError unused184) {
            }
            try {
                iArr[ActivityMode.FLOORBALL.ordinal()] = 185;
            } catch (NoSuchFieldError unused185) {
            }
            try {
                iArr[ActivityMode.JAI_ALAI.ordinal()] = 186;
            } catch (NoSuchFieldError unused186) {
            }
            try {
                iArr[ActivityMode.TENNIS_DOUBLES.ordinal()] = 187;
            } catch (NoSuchFieldError unused187) {
            }
            try {
                iArr[ActivityMode.BODY_COMBAT.ordinal()] = 188;
            } catch (NoSuchFieldError unused188) {
            }
            try {
                iArr[ActivityMode.BODY_BALANCING.ordinal()] = 189;
            } catch (NoSuchFieldError unused189) {
            }
            try {
                iArr[ActivityMode.TRX.ordinal()] = 190;
            } catch (NoSuchFieldError unused190) {
            }
            try {
                iArr[ActivityMode.TAE_BO.ordinal()] = 191;
            } catch (NoSuchFieldError unused191) {
            }
            try {
                iArr[ActivityMode.DISC_THROW.ordinal()] = 192;
            } catch (NoSuchFieldError unused192) {
            }
            try {
                iArr[ActivityMode.HORSE_RACING.ordinal()] = 193;
            } catch (NoSuchFieldError unused193) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[WorkoutUiBeanProvider.ScreenType.values().length];
            try {
                iArr2[WorkoutUiBeanProvider.ScreenType.ACTIVITY_HOME.ordinal()] = 1;
            } catch (NoSuchFieldError unused194) {
            }
            try {
                iArr2[WorkoutUiBeanProvider.ScreenType.ACTIVITY_HISTORY.ordinal()] = 2;
            } catch (NoSuchFieldError unused195) {
            }
            try {
                iArr2[WorkoutUiBeanProvider.ScreenType.ACTIVITY_FITNESS.ordinal()] = 3;
            } catch (NoSuchFieldError unused196) {
            }
            try {
                iArr2[WorkoutUiBeanProvider.ScreenType.ACTIVITY_DETAIL.ordinal()] = 4;
            } catch (NoSuchFieldError unused197) {
            }
            try {
                iArr2[WorkoutUiBeanProvider.ScreenType.ACTIVITY_SHARE.ordinal()] = 5;
            } catch (NoSuchFieldError unused198) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.utils.WorkoutUtils", f = "WorkoutUtils.kt", i = {0, 0}, l = {2446}, m = "getDistanceOfSessionInMeters", n = {"distanceInMeters", "sessionTotalDistance"}, s = {"L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WorkoutUtils.this.getDistanceOfSessionInMeters((EntityPreparationDay) null, (Context) null, this);
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.utils.WorkoutUtils", f = "WorkoutUtils.kt", i = {0, 0}, l = {2461}, m = "getDistanceOfSessionInMeters", n = {"distanceInMeters", "sessionTotalDistance"}, s = {"L$0", "L$1"})
    /* loaded from: classes2.dex */
    public static final class b extends ContinuationImpl {
        public Object L$0;
        public Object L$1;
        public int label;
        public /* synthetic */ Object result;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WorkoutUtils.this.getDistanceOfSessionInMeters((DayInfo) null, (Context) null, this);
        }
    }

    public final String a(String str) {
        return Intrinsics.areEqual(str, "com.google.android.geo.API_KEY") ? ErrorConstants.SETUP_ERR_MALFORMED_API_KEY : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public final String b(String str) {
        return Intrinsics.areEqual(str, "com.google.android.geo.API_KEY") ? ErrorConstants.SETUP_ERR_MISSING_API_KEY : ErrorConstants.SETUP_ERR_MISSING_DATA;
    }

    public final boolean c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return calendar.get(6) == Calendar.getInstance().get(6);
    }

    public final float convertCmToKm(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String format = decimalFormat.format(Float.valueOf(i / 100000.0f));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(centimeters / 100000f)");
        return Float.parseFloat(format);
    }

    public final float convertCmToKmRoundingModeHalfUp(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        String format = decimalFormat.format(Float.valueOf(i / 100000.0f));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(centimeters / 100000f)");
        return Float.parseFloat(format);
    }

    public final float convertCmToMeters(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(i / 100.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return Float.parseFloat(format);
    }

    public final double convertKMToMiles(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String format = decimalFormat.format(d * 0.62137f);
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(milesValue)");
        return Double.parseDouble(format);
    }

    public final double convertKMToMilesRoundingModeUp(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.UP);
        String format = decimalFormat.format(Float.valueOf((i / 100000.0f) * 0.62f));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(milesValue)");
        return Double.parseDouble(format);
    }

    public final int convertKmToMeters(int i) {
        return i * 1000;
    }

    public final int convertMetersToKm(int i) {
        return i / 1000;
    }

    public final float convertMetersToKmFloat(int i) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ENGLISH, "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(i / 1000.0f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return Float.parseFloat(format);
    }

    @NotNull
    public final String convertStringToTitleCase(@Nullable String str) {
        if (str != null) {
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String lowerCase = str.toLowerCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            char upperCase = Character.toUpperCase(lowerCase.charAt(0));
            String substring = lowerCase.substring(1, lowerCase.length());
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return upperCase + substring;
        }
        return "";
    }

    @NotNull
    public final HeartRateZoneRanges geHeartRateZoneRanges(int i) {
        int maxHeartRate = getMaxHeartRate(i);
        Log.d("Session: ", String.valueOf(maxHeartRate));
        return new HeartRateZoneRanges(0, ExtensionFuncsKt.percentage(maxHeartRate, 60.0f), ExtensionFuncsKt.percentage(maxHeartRate, 70.0f), ExtensionFuncsKt.percentage(maxHeartRate, 80.0f), ExtensionFuncsKt.percentage(maxHeartRate, 90.0f), maxHeartRate);
    }

    @NotNull
    public final String getAMPMValue(long j) {
        String format = AppUtils.getSimpleDateFormat("hh:mm a").format(new Date(j));
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat1.format(Date(timestamp))");
        return format;
    }

    @Nullable
    public final String getAPI_KEY() {
        return f2881a;
    }

    @Nullable
    public final ActivitiesItem getActivitiesItem(@NotNull ActivityMode activityMode, @Nullable String str, @NotNull List<ActivitiesItem> activityList) {
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        Intrinsics.checkNotNullParameter(activityList, "activityList");
        for (ActivitiesItem activitiesItem : activityList) {
            if (activitiesItem.getSessionType() != null && activityMode != null && m.equals(activitiesItem.getSessionType(), activityMode.name(), true) && (activitiesItem.getActivitySite() == null || str == null || m.equals(activitiesItem.getActivitySite(), str, true))) {
                return activitiesItem;
            }
        }
        return null;
    }

    public final int getActivitiesTotalTarget(@NotNull List<EntityPreparationActivity> activities) {
        Intrinsics.checkNotNullParameter(activities, "activities");
        int i = 0;
        for (EntityPreparationActivity entityPreparationActivity : activities) {
            if (m.equals(entityPreparationActivity.getActivityBaseUnit(), BASEUNIT.METERS.toString(), true) && entityPreparationActivity.getTarget() != null) {
                String target = entityPreparationActivity.getTarget();
                Intrinsics.checkNotNull(target);
                i += Integer.parseInt(target);
            }
            if (m.equals(entityPreparationActivity.getActivityBaseUnit(), BASEUNIT.KILOMETERS.toString(), true) && entityPreparationActivity.getTarget() != null) {
                String target2 = entityPreparationActivity.getTarget();
                Intrinsics.checkNotNull(target2);
                i += Integer.parseInt(target2) * 1000;
            }
        }
        return i;
    }

    @NotNull
    public final ActivityMode getActivityMode(@Nullable String str) {
        ActivityMode activityMode = ActivityMode.RUN;
        if (Intrinsics.areEqual(str, activityMode.toString())) {
            return activityMode;
        }
        ActivityMode activityMode2 = ActivityMode.WALK;
        if (Intrinsics.areEqual(str, activityMode2.toString())) {
            return activityMode2;
        }
        ActivityMode activityMode3 = ActivityMode.CYCLE;
        if (Intrinsics.areEqual(str, activityMode3.toString())) {
            return activityMode3;
        }
        ActivityMode activityMode4 = ActivityMode.BADMINTON;
        if (Intrinsics.areEqual(str, activityMode4.toString())) {
            return activityMode4;
        }
        ActivityMode activityMode5 = ActivityMode.FREE_EXERCISE;
        if (Intrinsics.areEqual(str, activityMode5.toString())) {
            return activityMode5;
        }
        ActivityMode activityMode6 = ActivityMode.FOOTBALL;
        if (Intrinsics.areEqual(str, activityMode6.toString())) {
            return activityMode6;
        }
        ActivityMode activityMode7 = ActivityMode.TENNIS;
        if (Intrinsics.areEqual(str, activityMode7.toString())) {
            return activityMode7;
        }
        ActivityMode activityMode8 = ActivityMode.YOGA;
        if (Intrinsics.areEqual(str, activityMode8.toString())) {
            return activityMode8;
        }
        ActivityMode activityMode9 = ActivityMode.MEDITATION;
        if (Intrinsics.areEqual(str, activityMode9.toString())) {
            return activityMode9;
        }
        ActivityMode activityMode10 = ActivityMode.DANCE;
        if (Intrinsics.areEqual(str, activityMode10.toString())) {
            return activityMode10;
        }
        ActivityMode activityMode11 = ActivityMode.BASKETBALL;
        if (Intrinsics.areEqual(str, activityMode11.toString())) {
            return activityMode11;
        }
        ActivityMode activityMode12 = ActivityMode.HIKING;
        if (Intrinsics.areEqual(str, activityMode12.toString())) {
            return activityMode12;
        }
        ActivityMode activityMode13 = ActivityMode.WORKOUT;
        if (Intrinsics.areEqual(str, activityMode13.toString())) {
            return activityMode13;
        }
        ActivityMode activityMode14 = ActivityMode.TREADMILL;
        if (Intrinsics.areEqual(str, activityMode14.toString())) {
            return activityMode14;
        }
        ActivityMode activityMode15 = ActivityMode.CLIMBING;
        if (Intrinsics.areEqual(str, activityMode15.toString())) {
            return activityMode15;
        }
        ActivityMode activityMode16 = ActivityMode.SKIPPING;
        if (Intrinsics.areEqual(str, activityMode16.toString())) {
            return activityMode16;
        }
        ActivityMode activityMode17 = ActivityMode.ROWING;
        if (Intrinsics.areEqual(str, activityMode17.toString())) {
            return activityMode17;
        }
        ActivityMode activityMode18 = ActivityMode.ELLIPTICAL;
        if (Intrinsics.areEqual(str, activityMode18.toString())) {
            return activityMode18;
        }
        ActivityMode activityMode19 = ActivityMode.PHYSICAL_ACTIVITY;
        if (Intrinsics.areEqual(str, activityMode19.toString())) {
            return activityMode19;
        }
        ActivityMode activityMode20 = ActivityMode.HIIT;
        if (Intrinsics.areEqual(str, activityMode20.toString())) {
            return activityMode20;
        }
        ActivityMode activityMode21 = ActivityMode.CRICKET;
        if (Intrinsics.areEqual(str, activityMode21.toString())) {
            return activityMode21;
        }
        ActivityMode activityMode22 = ActivityMode.SWIM;
        if (Intrinsics.areEqual(str, activityMode22.toString())) {
            return activityMode22;
        }
        ActivityMode activityMode23 = ActivityMode.PILATES;
        if (Intrinsics.areEqual(str, activityMode23.toString())) {
            return activityMode23;
        }
        ActivityMode activityMode24 = ActivityMode.ZUMBA;
        if (Intrinsics.areEqual(str, activityMode24.toString())) {
            return activityMode24;
        }
        ActivityMode activityMode25 = ActivityMode.CORE_TRAINING;
        if (Intrinsics.areEqual(str, activityMode25.toString())) {
            return activityMode25;
        }
        ActivityMode activityMode26 = ActivityMode.COOLDOWN;
        if (Intrinsics.areEqual(str, activityMode26.toString())) {
            return activityMode26;
        }
        ActivityMode activityMode27 = ActivityMode.TRADITIONAL_STRENGTH_TRAINING;
        if (Intrinsics.areEqual(str, activityMode27.toString())) {
            return activityMode27;
        }
        ActivityMode activityMode28 = ActivityMode.FUNCTIONAL_STRENGTH_TRAINING;
        if (Intrinsics.areEqual(str, activityMode28.toString())) {
            return activityMode28;
        }
        ActivityMode activityMode29 = ActivityMode.OTHER;
        if (Intrinsics.areEqual(str, activityMode29.toString())) {
            return activityMode29;
        }
        ActivityMode activityMode30 = ActivityMode.STEPPER;
        if (Intrinsics.areEqual(str, activityMode30.toString())) {
            return activityMode30;
        }
        ActivityMode activityMode31 = ActivityMode.AEROBICS;
        if (Intrinsics.areEqual(str, activityMode31.toString())) {
            return activityMode31;
        }
        ActivityMode activityMode32 = ActivityMode.SIT_UPS;
        if (Intrinsics.areEqual(str, activityMode32.toString())) {
            return activityMode32;
        }
        ActivityMode activityMode33 = ActivityMode.PLANK;
        if (Intrinsics.areEqual(str, activityMode33.toString())) {
            return activityMode33;
        }
        ActivityMode activityMode34 = ActivityMode.JUMPING_JACKS;
        if (Intrinsics.areEqual(str, activityMode34.toString())) {
            return activityMode34;
        }
        ActivityMode activityMode35 = ActivityMode.PULL_UPS;
        if (Intrinsics.areEqual(str, activityMode35.toString())) {
            return activityMode35;
        }
        ActivityMode activityMode36 = ActivityMode.PUSH_UPS;
        if (Intrinsics.areEqual(str, activityMode36.toString())) {
            return activityMode36;
        }
        ActivityMode activityMode37 = ActivityMode.SQUAT;
        if (Intrinsics.areEqual(str, activityMode37.toString())) {
            return activityMode37;
        }
        ActivityMode activityMode38 = ActivityMode.HIGH_KNEE_LIFT;
        if (Intrinsics.areEqual(str, activityMode38.toString())) {
            return activityMode38;
        }
        ActivityMode activityMode39 = ActivityMode.DUMBBELLS;
        if (Intrinsics.areEqual(str, activityMode39.toString())) {
            return activityMode39;
        }
        ActivityMode activityMode40 = ActivityMode.BARBELL;
        if (Intrinsics.areEqual(str, activityMode40.toString())) {
            return activityMode40;
        }
        ActivityMode activityMode41 = ActivityMode.BOXING;
        if (Intrinsics.areEqual(str, activityMode41.toString())) {
            return activityMode41;
        }
        ActivityMode activityMode42 = ActivityMode.FREE_SPARRING;
        if (Intrinsics.areEqual(str, activityMode42.toString())) {
            return activityMode42;
        }
        ActivityMode activityMode43 = ActivityMode.HORIZONTAL_BAR;
        if (Intrinsics.areEqual(str, activityMode43.toString())) {
            return activityMode43;
        }
        ActivityMode activityMode44 = ActivityMode.PARALLEL_BARS;
        if (Intrinsics.areEqual(str, activityMode44.toString())) {
            return activityMode44;
        }
        ActivityMode activityMode45 = ActivityMode.CARDIO_CRUISER;
        if (Intrinsics.areEqual(str, activityMode45.toString())) {
            return activityMode45;
        }
        ActivityMode activityMode46 = ActivityMode.CLIMBING_MACHINE;
        if (Intrinsics.areEqual(str, activityMode46.toString())) {
            return activityMode46;
        }
        ActivityMode activityMode47 = ActivityMode.BOWLING;
        if (Intrinsics.areEqual(str, activityMode47.toString())) {
            return activityMode47;
        }
        ActivityMode activityMode48 = ActivityMode.PING_PONG;
        if (Intrinsics.areEqual(str, activityMode48.toString())) {
            return activityMode48;
        }
        ActivityMode activityMode49 = ActivityMode.GOLF;
        if (Intrinsics.areEqual(str, activityMode49.toString())) {
            return activityMode49;
        }
        ActivityMode activityMode50 = ActivityMode.HOCKEY;
        if (Intrinsics.areEqual(str, activityMode50.toString())) {
            return activityMode50;
        }
        ActivityMode activityMode51 = ActivityMode.RUGBY;
        if (Intrinsics.areEqual(str, activityMode51.toString())) {
            return activityMode51;
        }
        ActivityMode activityMode52 = ActivityMode.HANDBALL;
        if (Intrinsics.areEqual(str, activityMode52.toString())) {
            return activityMode52;
        }
        ActivityMode activityMode53 = ActivityMode.STREET_DANCE;
        if (Intrinsics.areEqual(str, activityMode53.toString())) {
            return activityMode53;
        }
        ActivityMode activityMode54 = ActivityMode.CLIMB_STAIRS;
        if (Intrinsics.areEqual(str, activityMode54.toString())) {
            return activityMode54;
        }
        ActivityMode activityMode55 = ActivityMode.SLEDDING;
        if (Intrinsics.areEqual(str, activityMode55.toString())) {
            return activityMode55;
        }
        ActivityMode activityMode56 = ActivityMode.SKI;
        if (Intrinsics.areEqual(str, activityMode56.toString())) {
            return activityMode56;
        }
        ActivityMode activityMode57 = ActivityMode.SURFING;
        if (Intrinsics.areEqual(str, activityMode57.toString())) {
            return activityMode57;
        }
        ActivityMode activityMode58 = ActivityMode.SAILBOATING;
        if (Intrinsics.areEqual(str, activityMode58.toString())) {
            return activityMode58;
        }
        ActivityMode activityMode59 = ActivityMode.SKATEBOARDING;
        if (Intrinsics.areEqual(str, activityMode59.toString())) {
            return activityMode59;
        }
        ActivityMode activityMode60 = ActivityMode.ROCK_CLIMBING;
        if (Intrinsics.areEqual(str, activityMode60.toString())) {
            return activityMode60;
        }
        ActivityMode activityMode61 = ActivityMode.STRENGTH_TRAINING;
        if (Intrinsics.areEqual(str, activityMode61.toString())) {
            return activityMode61;
        }
        ActivityMode activityMode62 = ActivityMode.FOLK_DANCE;
        if (Intrinsics.areEqual(str, activityMode62.toString())) {
            return activityMode62;
        }
        ActivityMode activityMode63 = ActivityMode.HAND_CYCLING;
        if (Intrinsics.areEqual(str, activityMode63.toString())) {
            return activityMode63;
        }
        ActivityMode activityMode64 = ActivityMode.KENDO;
        if (Intrinsics.areEqual(str, activityMode64.toString())) {
            return activityMode64;
        }
        ActivityMode activityMode65 = ActivityMode.WRESTLING;
        if (Intrinsics.areEqual(str, activityMode65.toString())) {
            return activityMode65;
        }
        ActivityMode activityMode66 = ActivityMode.TAI_CHI;
        if (Intrinsics.areEqual(str, activityMode66.toString())) {
            return activityMode66;
        }
        ActivityMode activityMode67 = ActivityMode.GYMNASTICS;
        if (Intrinsics.areEqual(str, activityMode67.toString())) {
            return activityMode67;
        }
        ActivityMode activityMode68 = ActivityMode.TRACK_FIELD;
        if (Intrinsics.areEqual(str, activityMode68.toString())) {
            return activityMode68;
        }
        ActivityMode activityMode69 = ActivityMode.MARTIAL_ARTS;
        if (Intrinsics.areEqual(str, activityMode69.toString())) {
            return activityMode69;
        }
        ActivityMode activityMode70 = ActivityMode.WARM_UP;
        if (Intrinsics.areEqual(str, activityMode70.toString())) {
            return activityMode70;
        }
        ActivityMode activityMode71 = ActivityMode.SNOW_SPORTS;
        if (Intrinsics.areEqual(str, activityMode71.toString())) {
            return activityMode71;
        }
        ActivityMode activityMode72 = ActivityMode.LACROSSE;
        if (Intrinsics.areEqual(str, activityMode72.toString())) {
            return activityMode72;
        }
        ActivityMode activityMode73 = ActivityMode.HULA_HOOP;
        if (Intrinsics.areEqual(str, activityMode73.toString())) {
            return activityMode73;
        }
        ActivityMode activityMode74 = ActivityMode.DARTS;
        if (Intrinsics.areEqual(str, activityMode74.toString())) {
            return activityMode74;
        }
        ActivityMode activityMode75 = ActivityMode.ARCHERY;
        if (Intrinsics.areEqual(str, activityMode75.toString())) {
            return activityMode75;
        }
        ActivityMode activityMode76 = ActivityMode.HORSE_RIDING;
        if (Intrinsics.areEqual(str, activityMode76.toString())) {
            return activityMode76;
        }
        ActivityMode activityMode77 = ActivityMode.SHUTTLECOCK;
        if (Intrinsics.areEqual(str, activityMode77.toString())) {
            return activityMode77;
        }
        ActivityMode activityMode78 = ActivityMode.ICE_HOCKEY;
        if (Intrinsics.areEqual(str, activityMode78.toString())) {
            return activityMode78;
        }
        ActivityMode activityMode79 = ActivityMode.WAIST_TRAINING;
        if (Intrinsics.areEqual(str, activityMode79.toString())) {
            return activityMode79;
        }
        ActivityMode activityMode80 = ActivityMode.BATTLE_ROPE;
        if (Intrinsics.areEqual(str, activityMode80.toString())) {
            return activityMode80;
        }
        ActivityMode activityMode81 = ActivityMode.SMITH_MACHINE;
        if (Intrinsics.areEqual(str, activityMode81.toString())) {
            return activityMode81;
        }
        ActivityMode activityMode82 = ActivityMode.KABADDI;
        if (Intrinsics.areEqual(str, activityMode82.toString())) {
            return activityMode82;
        }
        ActivityMode activityMode83 = ActivityMode.SHOT_PUT;
        if (Intrinsics.areEqual(str, activityMode83.toString())) {
            return activityMode83;
        }
        ActivityMode activityMode84 = ActivityMode.SOLID_BALL;
        if (Intrinsics.areEqual(str, activityMode84.toString())) {
            return activityMode84;
        }
        ActivityMode activityMode85 = ActivityMode.JAVELIN;
        if (Intrinsics.areEqual(str, activityMode85.toString())) {
            return activityMode85;
        }
        ActivityMode activityMode86 = ActivityMode.LONG_JUMP;
        if (Intrinsics.areEqual(str, activityMode86.toString())) {
            return activityMode86;
        }
        ActivityMode activityMode87 = ActivityMode.HIGH_JUMP;
        if (Intrinsics.areEqual(str, activityMode87.toString())) {
            return activityMode87;
        }
        ActivityMode activityMode88 = ActivityMode.SQUARE_DANCE;
        if (Intrinsics.areEqual(str, activityMode88.toString())) {
            return activityMode88;
        }
        ActivityMode activityMode89 = ActivityMode.KARTING;
        if (Intrinsics.areEqual(str, activityMode89.toString())) {
            return activityMode89;
        }
        ActivityMode activityMode90 = ActivityMode.DODGEBALL;
        if (Intrinsics.areEqual(str, activityMode90.toString())) {
            return activityMode90;
        }
        ActivityMode activityMode91 = ActivityMode.YOYO;
        if (Intrinsics.areEqual(str, activityMode91.toString())) {
            return activityMode91;
        }
        ActivityMode activityMode92 = ActivityMode.LOCKING;
        if (Intrinsics.areEqual(str, activityMode92.toString())) {
            return activityMode92;
        }
        ActivityMode activityMode93 = ActivityMode.BURPEES;
        if (Intrinsics.areEqual(str, activityMode93.toString())) {
            return activityMode93;
        }
        ActivityMode activityMode94 = ActivityMode.BELLY_DANCE;
        if (Intrinsics.areEqual(str, activityMode94.toString())) {
            return activityMode94;
        }
        ActivityMode activityMode95 = ActivityMode.PARKOUR;
        if (Intrinsics.areEqual(str, activityMode95.toString())) {
            return activityMode95;
        }
        ActivityMode activityMode96 = ActivityMode.JAZZ_DANCE;
        if (Intrinsics.areEqual(str, activityMode96.toString())) {
            return activityMode96;
        }
        ActivityMode activityMode97 = ActivityMode.MODERN_DANCE;
        if (Intrinsics.areEqual(str, activityMode97.toString())) {
            return activityMode97;
        }
        ActivityMode activityMode98 = ActivityMode.CROSS_FIT;
        if (Intrinsics.areEqual(str, activityMode98.toString())) {
            return activityMode98;
        }
        ActivityMode activityMode99 = ActivityMode.LATIN_DANCE;
        if (Intrinsics.areEqual(str, activityMode99.toString())) {
            return activityMode99;
        }
        ActivityMode activityMode100 = ActivityMode.POPPING;
        if (Intrinsics.areEqual(str, activityMode100.toString())) {
            return activityMode100;
        }
        ActivityMode activityMode101 = ActivityMode.BALLET;
        if (Intrinsics.areEqual(str, activityMode101.toString())) {
            return activityMode101;
        }
        ActivityMode activityMode102 = ActivityMode.BASEBALL;
        if (Intrinsics.areEqual(str, activityMode102.toString())) {
            return activityMode102;
        }
        ActivityMode activityMode103 = ActivityMode.RACQUETBALL;
        if (Intrinsics.areEqual(str, activityMode103.toString())) {
            return activityMode103;
        }
        ActivityMode activityMode104 = ActivityMode.CURLING;
        if (Intrinsics.areEqual(str, activityMode104.toString())) {
            return activityMode104;
        }
        ActivityMode activityMode105 = ActivityMode.HUNTING;
        if (Intrinsics.areEqual(str, activityMode105.toString())) {
            return activityMode105;
        }
        ActivityMode activityMode106 = ActivityMode.SNOWBOARDING;
        if (Intrinsics.areEqual(str, activityMode106.toString())) {
            return activityMode106;
        }
        ActivityMode activityMode107 = ActivityMode.FISHING;
        if (Intrinsics.areEqual(str, activityMode107.toString())) {
            return activityMode107;
        }
        ActivityMode activityMode108 = ActivityMode.DISC_SPORTS;
        if (Intrinsics.areEqual(str, activityMode108.toString())) {
            return activityMode108;
        }
        ActivityMode activityMode109 = ActivityMode.FITNESS_GAMING;
        if (Intrinsics.areEqual(str, activityMode109.toString())) {
            return activityMode109;
        }
        ActivityMode activityMode110 = ActivityMode.AEROBICS_GYMS;
        if (Intrinsics.areEqual(str, activityMode110.toString())) {
            return activityMode110;
        }
        ActivityMode activityMode111 = ActivityMode.GROUP_TRAINING;
        if (Intrinsics.areEqual(str, activityMode111.toString())) {
            return activityMode111;
        }
        ActivityMode activityMode112 = ActivityMode.CARDIO_BOXING;
        if (Intrinsics.areEqual(str, activityMode112.toString())) {
            return activityMode112;
        }
        ActivityMode activityMode113 = ActivityMode.MOUNTAINEERING;
        if (Intrinsics.areEqual(str, activityMode113.toString())) {
            return activityMode113;
        }
        ActivityMode activityMode114 = ActivityMode.FENCING;
        if (Intrinsics.areEqual(str, activityMode114.toString())) {
            return activityMode114;
        }
        ActivityMode activityMode115 = ActivityMode.SOFTBALL;
        if (Intrinsics.areEqual(str, activityMode115.toString())) {
            return activityMode115;
        }
        ActivityMode activityMode116 = ActivityMode.AMERICAN_FOOTBALL;
        if (Intrinsics.areEqual(str, activityMode116.toString())) {
            return activityMode116;
        }
        ActivityMode activityMode117 = ActivityMode.VOLLEYBALL;
        if (Intrinsics.areEqual(str, activityMode117.toString())) {
            return activityMode117;
        }
        ActivityMode activityMode118 = ActivityMode.ROLLING;
        if (Intrinsics.areEqual(str, activityMode118.toString())) {
            return activityMode118;
        }
        ActivityMode activityMode119 = ActivityMode.PICKLEBALL;
        if (Intrinsics.areEqual(str, activityMode119.toString())) {
            return activityMode119;
        }
        ActivityMode activityMode120 = ActivityMode.TAEKWONDO;
        if (Intrinsics.areEqual(str, activityMode120.toString())) {
            return activityMode120;
        }
        ActivityMode activityMode121 = ActivityMode.KARATE;
        if (Intrinsics.areEqual(str, activityMode121.toString())) {
            return activityMode121;
        }
        ActivityMode activityMode122 = ActivityMode.FLEXIBILITY;
        if (Intrinsics.areEqual(str, activityMode122.toString())) {
            return activityMode122;
        }
        if (Intrinsics.areEqual(str, activityMode100.toString())) {
            return activityMode100;
        }
        ActivityMode activityMode123 = ActivityMode.PARACHUTE;
        if (Intrinsics.areEqual(str, activityMode123.toString())) {
            return activityMode123;
        }
        ActivityMode activityMode124 = ActivityMode.TRAIL_RUN;
        if (Intrinsics.areEqual(str, activityMode124.toString())) {
            return activityMode124;
        }
        ActivityMode activityMode125 = ActivityMode.ROLLER_SKATING;
        if (Intrinsics.areEqual(str, activityMode125.toString())) {
            return activityMode125;
        }
        ActivityMode activityMode126 = ActivityMode.WEIGHTLIFTING;
        if (Intrinsics.areEqual(str, activityMode126.toString())) {
            return activityMode126;
        }
        ActivityMode activityMode127 = ActivityMode.STRETCHING;
        if (Intrinsics.areEqual(str, activityMode127.toString())) {
            return activityMode127;
        }
        ActivityMode activityMode128 = ActivityMode.DEADLIFT;
        if (Intrinsics.areEqual(str, activityMode128.toString())) {
            return activityMode128;
        }
        ActivityMode activityMode129 = ActivityMode.UPPER_BODY_TRAINING;
        if (Intrinsics.areEqual(str, activityMode129.toString())) {
            return activityMode129;
        }
        ActivityMode activityMode130 = ActivityMode.LOWER_BODY_TRAINING;
        if (Intrinsics.areEqual(str, activityMode130.toString())) {
            return activityMode130;
        }
        ActivityMode activityMode131 = ActivityMode.ABS_TRAINING;
        if (Intrinsics.areEqual(str, activityMode131.toString())) {
            return activityMode131;
        }
        ActivityMode activityMode132 = ActivityMode.BACK_TRAINING;
        if (Intrinsics.areEqual(str, activityMode132.toString())) {
            return activityMode132;
        }
        ActivityMode activityMode133 = ActivityMode.SUP;
        if (Intrinsics.areEqual(str, activityMode133.toString())) {
            return activityMode133;
        }
        ActivityMode activityMode134 = ActivityMode.WATER_POLO;
        if (Intrinsics.areEqual(str, activityMode134.toString())) {
            return activityMode134;
        }
        ActivityMode activityMode135 = ActivityMode.THRASH;
        if (Intrinsics.areEqual(str, activityMode135.toString())) {
            return activityMode135;
        }
        ActivityMode activityMode136 = ActivityMode.KAYAKING;
        if (Intrinsics.areEqual(str, activityMode136.toString())) {
            return activityMode136;
        }
        ActivityMode activityMode137 = ActivityMode.DRIFTING;
        if (Intrinsics.areEqual(str, activityMode137.toString())) {
            return activityMode137;
        }
        ActivityMode activityMode138 = ActivityMode.BOATING;
        if (Intrinsics.areEqual(str, activityMode138.toString())) {
            return activityMode138;
        }
        ActivityMode activityMode139 = ActivityMode.FIN_SWIM;
        if (Intrinsics.areEqual(str, activityMode139.toString())) {
            return activityMode139;
        }
        ActivityMode activityMode140 = ActivityMode.DIVING;
        if (Intrinsics.areEqual(str, activityMode140.toString())) {
            return activityMode140;
        }
        ActivityMode activityMode141 = ActivityMode.ARTISTIC_SWIM;
        if (Intrinsics.areEqual(str, activityMode141.toString())) {
            return activityMode141;
        }
        ActivityMode activityMode142 = ActivityMode.SNORKEL;
        if (Intrinsics.areEqual(str, activityMode142.toString())) {
            return activityMode142;
        }
        ActivityMode activityMode143 = ActivityMode.KITESURFING;
        if (Intrinsics.areEqual(str, activityMode143.toString())) {
            return activityMode143;
        }
        ActivityMode activityMode144 = ActivityMode.ATV;
        if (Intrinsics.areEqual(str, activityMode144.toString())) {
            return activityMode144;
        }
        ActivityMode activityMode145 = ActivityMode.BEACH_SOCCER;
        if (Intrinsics.areEqual(str, activityMode145.toString())) {
            return activityMode145;
        }
        ActivityMode activityMode146 = ActivityMode.WUSHU;
        if (Intrinsics.areEqual(str, activityMode146.toString())) {
            return activityMode146;
        }
        ActivityMode activityMode147 = ActivityMode.MUAY_THAI;
        if (Intrinsics.areEqual(str, activityMode147.toString())) {
            return activityMode147;
        }
        ActivityMode activityMode148 = ActivityMode.JUDO;
        if (Intrinsics.areEqual(str, activityMode148.toString())) {
            return activityMode148;
        }
        ActivityMode activityMode149 = ActivityMode.SNOWMOBILE;
        if (Intrinsics.areEqual(str, activityMode149.toString())) {
            return activityMode149;
        }
        ActivityMode activityMode150 = ActivityMode.PUCK;
        if (Intrinsics.areEqual(str, activityMode150.toString())) {
            return activityMode150;
        }
        ActivityMode activityMode151 = ActivityMode.SKATING;
        if (Intrinsics.areEqual(str, activityMode151.toString())) {
            return activityMode151;
        }
        ActivityMode activityMode152 = ActivityMode.SQUASH;
        if (Intrinsics.areEqual(str, activityMode152.toString())) {
            return activityMode152;
        }
        ActivityMode activityMode153 = ActivityMode.RAGA;
        if (Intrinsics.areEqual(str, activityMode153.toString())) {
            return activityMode153;
        }
        ActivityMode activityMode154 = ActivityMode.BILLIARDS;
        if (Intrinsics.areEqual(str, activityMode154.toString())) {
            return activityMode154;
        }
        ActivityMode activityMode155 = ActivityMode.KITE;
        if (Intrinsics.areEqual(str, activityMode155.toString())) {
            return activityMode155;
        }
        ActivityMode activityMode156 = ActivityMode.FRISBEE;
        if (Intrinsics.areEqual(str, activityMode156.toString())) {
            return activityMode156;
        }
        ActivityMode activityMode157 = ActivityMode.EQUESTRIAN;
        if (Intrinsics.areEqual(str, activityMode157.toString())) {
            return activityMode157;
        }
        ActivityMode activityMode158 = ActivityMode.RACING;
        if (Intrinsics.areEqual(str, activityMode158.toString())) {
            return activityMode158;
        }
        ActivityMode activityMode159 = ActivityMode.PUSH_PULL;
        if (Intrinsics.areEqual(str, activityMode159.toString())) {
            return activityMode159;
        }
        ActivityMode activityMode160 = ActivityMode.AIR_WALKER;
        if (Intrinsics.areEqual(str, activityMode160.toString())) {
            return activityMode160;
        }
        ActivityMode activityMode161 = ActivityMode.CROSS_TRAINING;
        if (Intrinsics.areEqual(str, activityMode161.toString())) {
            return activityMode161;
        }
        ActivityMode activityMode162 = ActivityMode.MIXED_CARDIO;
        if (Intrinsics.areEqual(str, activityMode162.toString())) {
            return activityMode162;
        }
        ActivityMode activityMode163 = ActivityMode.KICKBOXING;
        if (Intrinsics.areEqual(str, activityMode163.toString())) {
            return activityMode163;
        }
        ActivityMode activityMode164 = ActivityMode.BARRE;
        if (Intrinsics.areEqual(str, activityMode164.toString())) {
            return activityMode164;
        }
        ActivityMode activityMode165 = ActivityMode.AUSTRALIAN_FOOTBALL;
        if (Intrinsics.areEqual(str, activityMode165.toString())) {
            return activityMode165;
        }
        ActivityMode activityMode166 = ActivityMode.DOWNHILL_SKIING;
        if (Intrinsics.areEqual(str, activityMode166.toString())) {
            return activityMode166;
        }
        ActivityMode activityMode167 = ActivityMode.ATHLETICS;
        if (Intrinsics.areEqual(str, activityMode167.toString())) {
            return activityMode167;
        }
        ActivityMode activityMode168 = ActivityMode.VO2MAX;
        if (Intrinsics.areEqual(str, activityMode168.toString())) {
            return activityMode168;
        }
        ActivityMode activityMode169 = ActivityMode.TRAMPOLINE;
        if (Intrinsics.areEqual(str, activityMode169.toString())) {
            return activityMode169;
        }
        ActivityMode activityMode170 = ActivityMode.HOVERBOARDING;
        if (Intrinsics.areEqual(str, activityMode170.toString())) {
            return activityMode170;
        }
        ActivityMode activityMode171 = ActivityMode.BLADING;
        if (Intrinsics.areEqual(str, activityMode171.toString())) {
            return activityMode171;
        }
        ActivityMode activityMode172 = ActivityMode.BUNGEE_JUMPING;
        if (Intrinsics.areEqual(str, activityMode172.toString())) {
            return activityMode172;
        }
        ActivityMode activityMode173 = ActivityMode.SHOOTING;
        if (Intrinsics.areEqual(str, activityMode173.toString())) {
            return activityMode173;
        }
        ActivityMode activityMode174 = ActivityMode.MARATHON;
        if (Intrinsics.areEqual(str, activityMode174.toString())) {
            return activityMode174;
        }
        ActivityMode activityMode175 = ActivityMode.TREKKING;
        if (Intrinsics.areEqual(str, activityMode175.toString())) {
            return activityMode175;
        }
        ActivityMode activityMode176 = ActivityMode.POLE_DANCE;
        if (Intrinsics.areEqual(str, activityMode176.toString())) {
            return activityMode176;
        }
        ActivityMode activityMode177 = ActivityMode.DISCO;
        if (Intrinsics.areEqual(str, activityMode177.toString())) {
            return activityMode177;
        }
        ActivityMode activityMode178 = ActivityMode.TAP_DANCE;
        if (Intrinsics.areEqual(str, activityMode178.toString())) {
            return activityMode178;
        }
        ActivityMode activityMode179 = ActivityMode.SCOOTER;
        if (Intrinsics.areEqual(str, activityMode179.toString())) {
            return activityMode179;
        }
        ActivityMode activityMode180 = ActivityMode.HAMMER;
        if (Intrinsics.areEqual(str, activityMode180.toString())) {
            return activityMode180;
        }
        ActivityMode activityMode181 = ActivityMode.LEG_PRESS;
        if (Intrinsics.areEqual(str, activityMode181.toString())) {
            return activityMode181;
        }
        ActivityMode activityMode182 = ActivityMode.OFF_ROAD_BIKE;
        if (Intrinsics.areEqual(str, activityMode182.toString())) {
            return activityMode182;
        }
        ActivityMode activityMode183 = ActivityMode.MOTOCROSS;
        if (Intrinsics.areEqual(str, activityMode183.toString())) {
            return activityMode183;
        }
        ActivityMode activityMode184 = ActivityMode.CROQUET;
        if (Intrinsics.areEqual(str, activityMode184.toString())) {
            return activityMode184;
        }
        ActivityMode activityMode185 = ActivityMode.FLOORBALL;
        if (Intrinsics.areEqual(str, activityMode185.toString())) {
            return activityMode185;
        }
        ActivityMode activityMode186 = ActivityMode.JAI_ALAI;
        if (Intrinsics.areEqual(str, activityMode186.toString())) {
            return activityMode186;
        }
        ActivityMode activityMode187 = ActivityMode.TENNIS_DOUBLES;
        if (Intrinsics.areEqual(str, activityMode187.toString())) {
            return activityMode187;
        }
        ActivityMode activityMode188 = ActivityMode.BODY_COMBAT;
        if (Intrinsics.areEqual(str, activityMode188.toString())) {
            return activityMode188;
        }
        ActivityMode activityMode189 = ActivityMode.BODY_BALANCING;
        if (Intrinsics.areEqual(str, activityMode189.toString())) {
            return activityMode189;
        }
        ActivityMode activityMode190 = ActivityMode.TRX;
        if (Intrinsics.areEqual(str, activityMode190.toString())) {
            return activityMode190;
        }
        ActivityMode activityMode191 = ActivityMode.TAE_BO;
        if (Intrinsics.areEqual(str, activityMode191.toString())) {
            return activityMode191;
        }
        ActivityMode activityMode192 = ActivityMode.HORSE_RACING;
        if (Intrinsics.areEqual(str, activityMode192.toString())) {
            return activityMode192;
        }
        ActivityMode activityMode193 = ActivityMode.DISC_THROW;
        return Intrinsics.areEqual(str, activityMode193.toString()) ? activityMode193 : ActivityMode.RUN;
    }

    @NotNull
    public final String getActivityModeNames(@Nullable ActivityMode activityMode, @NotNull Context context, @NotNull EntityWorkoutSession entityWorkoutSession) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        switch (activityMode == null ? -1 : WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()]) {
            case 1:
                DeviceUtils.Companion companion = DeviceUtils.Companion;
                if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context) && !companion.isRuggedDevice(context)) {
                    String string9 = context.getString(R.string.walking_title_case);
                    Intrinsics.checkNotNullExpressionValue(string9, "{\n                    co…e_case)\n                }");
                    return string9;
                }
                if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                    if (!companion.isEastApexDevice(context) && !companion.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO && !companion.isSmaDevice(context)) {
                        if (companion.isSmaDevice(context)) {
                            if (companion.isSmaRoundJieieDevice(context)) {
                                string = context.getString(R.string.walk);
                            } else {
                                string = context.getString(R.string.walking_title_case);
                            }
                        } else {
                            string = context.getString(R.string.outdoor_walking_title_case);
                        }
                    } else {
                        string = context.getString(R.string.outdoor_walk_title_case);
                    }
                } else if (!companion.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO && !companion.isSmaDevice(context)) {
                    string = context.getString(R.string.indoor_walking_title_case);
                } else {
                    string = context.getString(R.string.indoor_walk_title_case);
                }
                Intrinsics.checkNotNullExpressionValue(string, "{\n                    if…      }\n                }");
                return string;
            case 2:
                DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                if (!companion2.isIDODevice(context) && !companion2.isTouchELXDevice(context) && !companion2.isEastApexDevice(context) && !companion2.isRuggedDevice(context) && !companion2.isSmaDevice(context)) {
                    String string10 = context.getString(R.string.running_title_case);
                    Intrinsics.checkNotNullExpressionValue(string10, "{\n                    co…e_case)\n                }");
                    return string10;
                }
                if (entityWorkoutSession.getIndoor_outdoor() != null) {
                    if (m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                        if (!companion2.isEastApexDevice(context) && !companion2.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                            string2 = context.getString(R.string.outdoor_running_title_case);
                        } else {
                            string2 = context.getString(R.string.outdoor_run_title_case);
                        }
                    } else if (!companion2.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                        string2 = context.getString(R.string.indoor_running_title_case);
                    } else {
                        string2 = context.getString(R.string.indoor_run_title_case);
                    }
                } else if (!companion2.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                    if (companion2.isSmaDevice(context)) {
                        if (companion2.isSmaRoundJieieDevice(context)) {
                            string2 = context.getString(R.string.run);
                        } else {
                            string2 = context.getString(R.string.running_title_case);
                        }
                    } else {
                        string2 = context.getString(R.string.indoor_running_title_case);
                    }
                } else {
                    string2 = context.getString(R.string.indoor_run_title_case);
                }
                Intrinsics.checkNotNullExpressionValue(string2, "{\n                    if…      }\n                }");
                return string2;
            case 3:
                DeviceUtils.Companion companion3 = DeviceUtils.Companion;
                if (!companion3.isIDODevice(context) && !companion3.isTouchELXDevice(context)) {
                    if (!companion3.isSmaDevice(context) && !companion3.isMatrixDevice(context)) {
                        if (companion3.isEastApexDevice(context)) {
                            if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                                string3 = context.getString(R.string.cycling_title_case);
                            } else {
                                string3 = context.getString(R.string.indoor_cycle_title_case);
                            }
                            Intrinsics.checkNotNullExpressionValue(string3, "{\n                    if…      }\n                }");
                        } else {
                            String string11 = context.getString(R.string.cycling_title_case);
                            Intrinsics.checkNotNullExpressionValue(string11, "context.getString(R.string.cycling_title_case)");
                            return string11;
                        }
                    } else {
                        if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                            string3 = context.getString(R.string.cycling_title_case);
                        } else {
                            string3 = context.getString(R.string.spinning_title_case);
                        }
                        Intrinsics.checkNotNullExpressionValue(string3, "{\n                    if…      }\n                }");
                    }
                } else {
                    if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                        if (Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.wave_neo))) {
                            string3 = context.getString(R.string.outdoor_cycle_title_case);
                        } else {
                            string3 = context.getString(R.string.outdoor_cycling_title_case);
                        }
                    } else if (Intrinsics.areEqual(SessionManager.getInstance(context).getDeviceType(), context.getString(R.string.wave_neo))) {
                        string3 = context.getString(R.string.indoor_cycle_title_case);
                    } else {
                        string3 = context.getString(R.string.indoor_cycling_title_case);
                    }
                    Intrinsics.checkNotNullExpressionValue(string3, "{\n                    if…      }\n                }");
                }
                return string3;
            case 4:
                String string12 = context.getString(R.string.badminton_title_case);
                Intrinsics.checkNotNullExpressionValue(string12, "context.getString(R.string.badminton_title_case)");
                return string12;
            case 5:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string13 = context.getString(R.string.soccer_title_case);
                    Intrinsics.checkNotNullExpressionValue(string13, "{\n                contex…title_case)\n            }");
                    return string13;
                }
                String string14 = context.getString(R.string.football_title_case);
                Intrinsics.checkNotNullExpressionValue(string14, "{\n                contex…title_case)\n            }");
                return string14;
            case 6:
                String string15 = context.getString(R.string.tennis_title_case);
                Intrinsics.checkNotNullExpressionValue(string15, "context.getString(R.string.tennis_title_case)");
                return string15;
            case 7:
                String string16 = context.getString(R.string.yoga_title_case);
                Intrinsics.checkNotNullExpressionValue(string16, "context.getString(R.string.yoga_title_case)");
                return string16;
            case 8:
                DeviceUtils.Companion companion4 = DeviceUtils.Companion;
                if (!companion4.isTouchELXDevice(context) && !companion4.isRuggedDevice(context)) {
                    String string17 = context.getString(R.string.meditation_title_case);
                    Intrinsics.checkNotNullExpressionValue(string17, "{\n                contex…title_case)\n            }");
                    return string17;
                }
                String string18 = context.getString(R.string.mind_body_title_case);
                Intrinsics.checkNotNullExpressionValue(string18, "{\n                contex…title_case)\n            }");
                return string18;
            case 9:
                String string19 = context.getString(R.string.dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string19, "context.getString(R.string.dance_title_case)");
                return string19;
            case 10:
                String string20 = context.getString(R.string.basketball_title_case);
                Intrinsics.checkNotNullExpressionValue(string20, "context.getString(R.string.basketball_title_case)");
                return string20;
            case 11:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string21 = context.getString(R.string.hike_title_case);
                    Intrinsics.checkNotNullExpressionValue(string21, "{\n                contex…title_case)\n            }");
                    return string21;
                }
                String string22 = context.getString(R.string.hiking_title_case);
                Intrinsics.checkNotNullExpressionValue(string22, "{\n                contex…title_case)\n            }");
                return string22;
            case 12:
                DeviceUtils.Companion companion5 = DeviceUtils.Companion;
                if (!companion5.isEastApexDevice(context) && !companion5.isRuggedDevice(context)) {
                    if (companion5.isSmaDevice(context)) {
                        String string23 = context.getString(R.string.body_building);
                        Intrinsics.checkNotNullExpressionValue(string23, "{\n                contex…y_building)\n            }");
                        return string23;
                    }
                    String string24 = context.getString(R.string.workout_title_case);
                    Intrinsics.checkNotNullExpressionValue(string24, "{\n                contex…title_case)\n            }");
                    return string24;
                }
                String string25 = context.getString(R.string.physical_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string25, "{\n                contex…title_case)\n            }");
                return string25;
            case 13:
                String string26 = context.getString(R.string.treadmill_title_case);
                Intrinsics.checkNotNullExpressionValue(string26, "context.getString(R.string.treadmill_title_case)");
                return string26;
            case 14:
                String string27 = context.getString(R.string.climbing_title_case);
                Intrinsics.checkNotNullExpressionValue(string27, "context.getString(R.string.climbing_title_case)");
                return string27;
            case 15:
                DeviceUtils.Companion companion6 = DeviceUtils.Companion;
                if (!companion6.isTouchELXDevice(context) && !companion6.isSmaDevice(context)) {
                    if (companion6.isEastApexDevice(context)) {
                        String string28 = context.getString(R.string.skip_title_case);
                        Intrinsics.checkNotNullExpressionValue(string28, "{\n                contex…title_case)\n            }");
                        return string28;
                    }
                    String string29 = context.getString(R.string.skipping_title_case);
                    Intrinsics.checkNotNullExpressionValue(string29, "{\n                contex…title_case)\n            }");
                    return string29;
                }
                String string30 = context.getString(R.string.jump_rope_title_case);
                Intrinsics.checkNotNullExpressionValue(string30, "{\n                contex…title_case)\n            }");
                return string30;
            case 16:
                DeviceUtils.Companion companion7 = DeviceUtils.Companion;
                if (companion7.isEastApexDevice(context)) {
                    String string31 = context.getString(R.string.free_excercise_title_case);
                    Intrinsics.checkNotNullExpressionValue(string31, "{\n                contex…title_case)\n            }");
                    return string31;
                } else if (companion7.isSmaDevice(context)) {
                    String string32 = context.getString(R.string.free_movement_title_case);
                    Intrinsics.checkNotNullExpressionValue(string32, "{\n            context.ge…title_case)\n            }");
                    return string32;
                } else {
                    String string33 = context.getString(R.string.free_trining_case);
                    Intrinsics.checkNotNullExpressionValue(string33, "{\n                contex…ining_case)\n            }");
                    return string33;
                }
            case 17:
                DeviceUtils.Companion companion8 = DeviceUtils.Companion;
                if (!companion8.isEastApexDevice(context) && !companion8.isRuggedDevice(context)) {
                    if (companion8.isSmaDevice(context)) {
                        String string34 = context.getString(R.string.elliptical_machine_title_case);
                        Intrinsics.checkNotNullExpressionValue(string34, "{\n                contex…title_case)\n            }");
                        return string34;
                    }
                    String string35 = context.getString(R.string.elliptical_title_case);
                    Intrinsics.checkNotNullExpressionValue(string35, "{\n                contex…title_case)\n            }");
                    return string35;
                }
                String string36 = context.getString(R.string.elliptical_trainer_title_case);
                Intrinsics.checkNotNullExpressionValue(string36, "{\n                contex…title_case)\n            }");
                return string36;
            case 18:
                DeviceUtils.Companion companion9 = DeviceUtils.Companion;
                if (companion9.isTouchELXDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                    String string37 = context.getString(R.string.rower_title_case);
                    Intrinsics.checkNotNullExpressionValue(string37, "{\n                contex…title_case)\n            }");
                    return string37;
                } else if (!companion9.isSmaDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                    String string38 = context.getString(R.string.rowing_machine_title_case);
                    Intrinsics.checkNotNullExpressionValue(string38, "{\n                contex…title_case)\n            }");
                    return string38;
                } else {
                    String string39 = context.getString(R.string.rowing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string39, "{\n                contex…title_case)\n            }");
                    return string39;
                }
            case 19:
                Integer categoryId = entityWorkoutSession.getCategoryId();
                CategoryAndActivityModel categoryAndActivityModel = null;
                if (categoryId != null) {
                    int intValue = categoryId.intValue();
                    Integer activityId = entityWorkoutSession.getActivityId();
                    if (activityId != null) {
                        categoryAndActivityModel = PhysicalActivityRepository.Companion.getInstance(context).getActivityAndCategoryList(activityId.intValue(), intValue);
                    }
                }
                if (categoryAndActivityModel != null) {
                    if (AppUtils.isEmpty(categoryAndActivityModel.getDefaultActivityName())) {
                        return String.valueOf(categoryAndActivityModel.getTitleInMetric());
                    }
                    return String.valueOf(categoryAndActivityModel.getDefaultActivityName());
                }
                String string40 = context.getString(R.string.physical_activity);
                Intrinsics.checkNotNullExpressionValue(string40, "context.getString(R.string.physical_activity)");
                return string40;
            case 20:
                DeviceUtils.Companion companion10 = DeviceUtils.Companion;
                if (companion10.isIDODevice(context)) {
                    if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                        string4 = context.getString(R.string.open_water_swim_title_case);
                    } else {
                        string4 = context.getString(R.string.pool_swim_title_case);
                    }
                    Intrinsics.checkNotNullExpressionValue(string4, "{\n                    if…      }\n                }");
                } else if (companion10.isEastApexDevice(context)) {
                    if (entityWorkoutSession.getIndoor_outdoor() != null && m.equals(entityWorkoutSession.getIndoor_outdoor(), "OUTDOOR", true)) {
                        string4 = context.getString(R.string.outdoor_swim_title_case);
                    } else {
                        string4 = context.getString(R.string.indoor_swim_title_case);
                    }
                    Intrinsics.checkNotNullExpressionValue(string4, "{\n                    if…      }\n                }");
                } else if (companion10.isSmaDevice(context)) {
                    String string41 = context.getString(R.string.swim1_title_case);
                    Intrinsics.checkNotNullExpressionValue(string41, "context.getString(R.string.swim1_title_case)");
                    return string41;
                } else {
                    String string42 = context.getString(R.string.swim_title_case);
                    Intrinsics.checkNotNullExpressionValue(string42, "context.getString(R.string.swim_title_case)");
                    return string42;
                }
                return string4;
            case 21:
                String string43 = context.getString(R.string.hiit_title_case);
                Intrinsics.checkNotNullExpressionValue(string43, "context.getString(R.string.hiit_title_case)");
                return string43;
            case 22:
                String string44 = context.getString(R.string.cricket_title_case);
                Intrinsics.checkNotNullExpressionValue(string44, "context.getString(R.string.cricket_title_case)");
                return string44;
            case 23:
                String string45 = context.getString(R.string.pilates_title_case);
                Intrinsics.checkNotNullExpressionValue(string45, "context.getString(R.string.pilates_title_case)");
                return string45;
            case 24:
                String string46 = context.getString(R.string.zumba_title_case);
                Intrinsics.checkNotNullExpressionValue(string46, "context.getString(R.string.zumba_title_case)");
                return string46;
            case 25:
                String string47 = context.getString(R.string.core_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string47, "context.getString(R.stri…core_training_title_case)");
                return string47;
            case 26:
                String string48 = context.getString(R.string.cooldown_title_case);
                Intrinsics.checkNotNullExpressionValue(string48, "context.getString(R.string.cooldown_title_case)");
                return string48;
            case 27:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string49 = context.getString(R.string.strength_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string49, "{\n                    co…e_case)\n                }");
                    return string49;
                }
                String string50 = context.getString(R.string.traditional_strength_title_case);
                Intrinsics.checkNotNullExpressionValue(string50, "{\n                    co…e_case)\n                }");
                return string50;
            case 28:
                DeviceUtils.Companion companion11 = DeviceUtils.Companion;
                if (!companion11.isEastApexDevice(context) && !companion11.isRuggedDevice(context)) {
                    String string51 = context.getString(R.string.function_strength_title_case);
                    Intrinsics.checkNotNullExpressionValue(string51, "{\n                contex…title_case)\n            }");
                    return string51;
                }
                String string52 = context.getString(R.string.functioonal_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string52, "{\n                contex…title_case)\n            }");
                return string52;
            case 29:
                String string53 = context.getString(R.string.others_title_case);
                Intrinsics.checkNotNullExpressionValue(string53, "context.getString(R.string.others_title_case)");
                return string53;
            case 30:
                String string54 = context.getString(R.string.pull_up_title_case);
                Intrinsics.checkNotNullExpressionValue(string54, "context.getString(R.string.pull_up_title_case)");
                return string54;
            case 31:
                String string55 = context.getString(R.string.push_ups_title_case);
                Intrinsics.checkNotNullExpressionValue(string55, "context.getString(R.string.push_ups_title_case)");
                return string55;
            case 32:
                String string56 = context.getString(R.string.squat_title_case);
                Intrinsics.checkNotNullExpressionValue(string56, "context.getString(R.string.squat_title_case)");
                return string56;
            case 33:
                String string57 = context.getString(R.string.high_knee_lift_title_case);
                Intrinsics.checkNotNullExpressionValue(string57, "context.getString(R.stri…igh_knee_lift_title_case)");
                return string57;
            case 34:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string58 = context.getString(R.string.dumbbells_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string58, "{\n                contex…title_case)\n            }");
                    return string58;
                }
                String string59 = context.getString(R.string.dumbbells_title_case);
                Intrinsics.checkNotNullExpressionValue(string59, "{\n                contex…title_case)\n            }");
                return string59;
            case 35:
                String string60 = context.getString(R.string.barbell_title_case);
                Intrinsics.checkNotNullExpressionValue(string60, "context.getString(R.string.barbell_title_case)");
                return string60;
            case 36:
                String string61 = context.getString(R.string.boxing_title_case);
                Intrinsics.checkNotNullExpressionValue(string61, "context.getString(R.string.boxing_title_case)");
                return string61;
            case 37:
                String string62 = context.getString(R.string.free_sparring_title_case);
                Intrinsics.checkNotNullExpressionValue(string62, "context.getString(R.stri…free_sparring_title_case)");
                return string62;
            case 38:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string63 = context.getString(R.string.horizontal_bars_title_case);
                    Intrinsics.checkNotNullExpressionValue(string63, "{\n                contex…title_case)\n            }");
                    return string63;
                }
                String string64 = context.getString(R.string.horizontal_bar_title_case);
                Intrinsics.checkNotNullExpressionValue(string64, "{\n                contex…title_case)\n            }");
                return string64;
            case 39:
                String string65 = context.getString(R.string.parallel_bars_title_case);
                Intrinsics.checkNotNullExpressionValue(string65, "context.getString(R.stri…parallel_bars_title_case)");
                return string65;
            case 40:
                String string66 = context.getString(R.string.cardio_cruiser_title_case);
                Intrinsics.checkNotNullExpressionValue(string66, "context.getString(R.stri…ardio_cruiser_title_case)");
                return string66;
            case 41:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string67 = context.getString(R.string.stair_machine_title_case);
                    Intrinsics.checkNotNullExpressionValue(string67, "{\n                contex…title_case)\n            }");
                    return string67;
                }
                String string68 = context.getString(R.string.climbing_machine_title_case);
                Intrinsics.checkNotNullExpressionValue(string68, "{\n                contex…title_case)\n            }");
                return string68;
            case 42:
                String string69 = context.getString(R.string.bowling_title_case);
                Intrinsics.checkNotNullExpressionValue(string69, "context.getString(R.string.bowling_title_case)");
                return string69;
            case 43:
                DeviceUtils.Companion companion12 = DeviceUtils.Companion;
                if (!companion12.isEastApexDevice(context) && !companion12.isSmaDevice(context)) {
                    String string70 = context.getString(R.string.table_tennis_title_case);
                    Intrinsics.checkNotNullExpressionValue(string70, "{\n                contex…title_case)\n            }");
                    return string70;
                }
                String string71 = context.getString(R.string.pingpong);
                Intrinsics.checkNotNullExpressionValue(string71, "{\n                contex…g.pingpong)\n            }");
                return string71;
            case 44:
                String string72 = context.getString(R.string.golf_title_case);
                Intrinsics.checkNotNullExpressionValue(string72, "context.getString(R.string.golf_title_case)");
                return string72;
            case 45:
                String string73 = context.getString(R.string.hockey_title_case);
                Intrinsics.checkNotNullExpressionValue(string73, "context.getString(R.string.hockey_title_case)");
                return string73;
            case 46:
                String string74 = context.getString(R.string.rugby_title_case);
                Intrinsics.checkNotNullExpressionValue(string74, "context.getString(R.string.rugby_title_case)");
                return string74;
            case 47:
                String string75 = context.getString(R.string.handball_title_case);
                Intrinsics.checkNotNullExpressionValue(string75, "context.getString(R.string.handball_title_case)");
                return string75;
            case 48:
                DeviceUtils.Companion companion13 = DeviceUtils.Companion;
                if (!companion13.isEastApexDevice(context) && !companion13.isSmaDevice(context)) {
                    String string76 = context.getString(R.string.street_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string76, "{\n                contex…title_case)\n            }");
                    return string76;
                }
                String string77 = context.getString(R.string.hip_hop_title_case);
                Intrinsics.checkNotNullExpressionValue(string77, "{\n                contex…title_case)\n            }");
                return string77;
            case 49:
                String string78 = context.getString(R.string.street_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string78, "context.getString(R.stri….street_dance_title_case)");
                return string78;
            case 50:
                DeviceUtils.Companion companion14 = DeviceUtils.Companion;
                if (!companion14.isTouchELXDevice(context) && !companion14.isRuggedDevice(context)) {
                    if (!companion14.isEastApexDevice(context) && !companion14.isSmaDevice(context)) {
                        String string79 = context.getString(R.string.stair_climbing_title_case);
                        Intrinsics.checkNotNullExpressionValue(string79, "{\n                contex…title_case)\n            }");
                        return string79;
                    }
                    String string80 = context.getString(R.string.climb_stairs);
                    Intrinsics.checkNotNullExpressionValue(string80, "{\n                contex…imb_stairs)\n            }");
                    return string80;
                }
                String string81 = context.getString(R.string.stairs_title_case);
                Intrinsics.checkNotNullExpressionValue(string81, "{\n                contex…title_case)\n            }");
                return string81;
            case 51:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string82 = context.getString(R.string.sled_title_case);
                    Intrinsics.checkNotNullExpressionValue(string82, "{\n                contex…title_case)\n            }");
                    return string82;
                }
                String string83 = context.getString(R.string.sledding_title_case);
                Intrinsics.checkNotNullExpressionValue(string83, "{\n                contex…title_case)\n            }");
                return string83;
            case 52:
                DeviceUtils.Companion companion15 = DeviceUtils.Companion;
                if (companion15.isEastApexDevice(context)) {
                    String string84 = context.getString(R.string.skis_title_case);
                    Intrinsics.checkNotNullExpressionValue(string84, "{\n                contex…title_case)\n            }");
                    return string84;
                } else if (companion15.isRuggedDevice(context)) {
                    String string85 = context.getString(R.string.skiing);
                    Intrinsics.checkNotNullExpressionValue(string85, "{\n                contex…ing.skiing)\n            }");
                    return string85;
                } else if (companion15.isSmaDevice(context)) {
                    String string86 = context.getString(R.string.ski_title_case);
                    Intrinsics.checkNotNullExpressionValue(string86, "{\n                contex…title_case)\n            }");
                    return string86;
                } else {
                    String string87 = context.getString(R.string.skating_title_case);
                    Intrinsics.checkNotNullExpressionValue(string87, "{\n                contex…title_case)\n            }");
                    return string87;
                }
            case 53:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string88 = context.getString(R.string.surf_title_case);
                    Intrinsics.checkNotNullExpressionValue(string88, "{\n                contex…title_case)\n            }");
                    return string88;
                }
                String string89 = context.getString(R.string.surfing_title_case);
                Intrinsics.checkNotNullExpressionValue(string89, "{\n                contex…title_case)\n            }");
                return string89;
            case 54:
                DeviceUtils.Companion companion16 = DeviceUtils.Companion;
                if (!companion16.isEastApexDevice(context) && !companion16.isSmaDevice(context)) {
                    String string90 = context.getString(R.string.sailboating_title_case);
                    Intrinsics.checkNotNullExpressionValue(string90, "{\n                contex…title_case)\n            }");
                    return string90;
                }
                String string91 = context.getString(R.string.sailboat);
                Intrinsics.checkNotNullExpressionValue(string91, "{\n                contex…g.sailboat)\n            }");
                return string91;
            case 55:
                DeviceUtils.Companion companion17 = DeviceUtils.Companion;
                if (!companion17.isEastApexDevice(context) && !companion17.isSmaDevice(context)) {
                    String string92 = context.getString(R.string.skateboarding_title_case);
                    Intrinsics.checkNotNullExpressionValue(string92, "{\n                contex…title_case)\n            }");
                    return string92;
                }
                String string93 = context.getString(R.string.skateboard_title_case);
                Intrinsics.checkNotNullExpressionValue(string93, "{\n                contex…title_case)\n            }");
                return string93;
            case 56:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string94 = context.getString(R.string.rock_climb_title_case);
                    Intrinsics.checkNotNullExpressionValue(string94, "{\n                contex…title_case)\n            }");
                    return string94;
                }
                String string95 = context.getString(R.string.rock_climbing_title_case);
                Intrinsics.checkNotNullExpressionValue(string95, "{\n                contex…title_case)\n            }");
                return string95;
            case 57:
                String string96 = context.getString(R.string.strength_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string96, "context.getString(R.stri…ngth_training_title_case)");
                return string96;
            case 58:
                String string97 = context.getString(R.string.folk_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string97, "context.getString(R.string.folk_dance_title_case)");
                return string97;
            case 59:
                String string98 = context.getString(R.string.hand_cycling_title_case);
                Intrinsics.checkNotNullExpressionValue(string98, "context.getString(R.stri….hand_cycling_title_case)");
                return string98;
            case 60:
                String string99 = context.getString(R.string.kendo_title_case);
                Intrinsics.checkNotNullExpressionValue(string99, "context.getString(R.string.kendo_title_case)");
                return string99;
            case 61:
                String string100 = context.getString(R.string.wrestling_title_case);
                Intrinsics.checkNotNullExpressionValue(string100, "context.getString(R.string.wrestling_title_case)");
                return string100;
            case 62:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string101 = context.getString(R.string.taichi_title_case);
                    Intrinsics.checkNotNullExpressionValue(string101, "{\n                contex…title_case)\n            }");
                    return string101;
                }
                String string102 = context.getString(R.string.tai_chi_title_case);
                Intrinsics.checkNotNullExpressionValue(string102, "{\n                contex…title_case)\n            }");
                return string102;
            case 63:
                String string103 = context.getString(R.string.gymnastics_title_case);
                Intrinsics.checkNotNullExpressionValue(string103, "context.getString(R.string.gymnastics_title_case)");
                return string103;
            case 64:
                String string104 = context.getString(R.string.track_field_title_case);
                Intrinsics.checkNotNullExpressionValue(string104, "context.getString(R.string.track_field_title_case)");
                return string104;
            case 65:
                String string105 = context.getString(R.string.martial_arts_title_case);
                Intrinsics.checkNotNullExpressionValue(string105, "context.getString(R.stri….martial_arts_title_case)");
                return string105;
            case 66:
                String string106 = context.getString(R.string.warm_up_title_case);
                Intrinsics.checkNotNullExpressionValue(string106, "context.getString(R.string.warm_up_title_case)");
                return string106;
            case 67:
                String string107 = context.getString(R.string.snow_sports_title_case);
                Intrinsics.checkNotNullExpressionValue(string107, "context.getString(R.string.snow_sports_title_case)");
                return string107;
            case 68:
                String string108 = context.getString(R.string.lacrosse_title_case);
                Intrinsics.checkNotNullExpressionValue(string108, "context.getString(R.string.lacrosse_title_case)");
                return string108;
            case 69:
                String string109 = context.getString(R.string.hula_hoop_title_case);
                Intrinsics.checkNotNullExpressionValue(string109, "context.getString(R.string.hula_hoop_title_case)");
                return string109;
            case 70:
                String string110 = context.getString(R.string.darts_title_case);
                Intrinsics.checkNotNullExpressionValue(string110, "context.getString(R.string.darts_title_case)");
                return string110;
            case 71:
                String string111 = context.getString(R.string.archery_title_case);
                Intrinsics.checkNotNullExpressionValue(string111, "context.getString(R.string.archery_title_case)");
                return string111;
            case 72:
                String string112 = context.getString(R.string.horse_riding_title_case);
                Intrinsics.checkNotNullExpressionValue(string112, "context.getString(R.stri….horse_riding_title_case)");
                return string112;
            case 73:
                String string113 = context.getString(R.string.shuttlecock_title_case);
                Intrinsics.checkNotNullExpressionValue(string113, "context.getString(R.string.shuttlecock_title_case)");
                return string113;
            case 74:
                String string114 = context.getString(R.string.ice_hockey_title_case);
                Intrinsics.checkNotNullExpressionValue(string114, "context.getString(R.string.ice_hockey_title_case)");
                return string114;
            case 75:
                String string115 = context.getString(R.string.waist_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string115, "context.getString(R.stri…aist_training_title_case)");
                return string115;
            case 76:
                String string116 = context.getString(R.string.battle_rope_title_case);
                Intrinsics.checkNotNullExpressionValue(string116, "context.getString(R.string.battle_rope_title_case)");
                return string116;
            case 77:
                String string117 = context.getString(R.string.smith_machine_title_case);
                Intrinsics.checkNotNullExpressionValue(string117, "context.getString(R.stri…smith_machine_title_case)");
                return string117;
            case 78:
                String string118 = context.getString(R.string.kabaddi_title_case);
                Intrinsics.checkNotNullExpressionValue(string118, "context.getString(R.string.kabaddi_title_case)");
                return string118;
            case 79:
                String string119 = context.getString(R.string.shot_put_title_case);
                Intrinsics.checkNotNullExpressionValue(string119, "context.getString(R.string.shot_put_title_case)");
                return string119;
            case 80:
                String string120 = context.getString(R.string.solid_ball_title_case);
                Intrinsics.checkNotNullExpressionValue(string120, "context.getString(R.string.solid_ball_title_case)");
                return string120;
            case 81:
                DeviceUtils.Companion companion18 = DeviceUtils.Companion;
                if (companion18.isSmaDevice(context)) {
                    if (companion18.isSmaRoundJieieDevice(context)) {
                        string5 = context.getString(R.string.javelin_title_case);
                    } else {
                        string5 = context.getString(R.string.javelin_throw_title_case);
                    }
                    Intrinsics.checkNotNullExpressionValue(string5, "{\n                if (De…          }\n            }");
                    return string5;
                }
                String string121 = context.getString(R.string.javelin_title_case);
                Intrinsics.checkNotNullExpressionValue(string121, "{\n                contex…title_case)\n            }");
                return string121;
            case 82:
                String string122 = context.getString(R.string.long_jump_title_case);
                Intrinsics.checkNotNullExpressionValue(string122, "context.getString(R.string.long_jump_title_case)");
                return string122;
            case 83:
                String string123 = context.getString(R.string.high_jump_title_case);
                Intrinsics.checkNotNullExpressionValue(string123, "context.getString(R.string.high_jump_title_case)");
                return string123;
            case 84:
                String string124 = context.getString(R.string.square_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string124, "context.getString(R.stri….square_dance_title_case)");
                return string124;
            case 85:
                String string125 = context.getString(R.string.karting_title_case);
                Intrinsics.checkNotNullExpressionValue(string125, "context.getString(R.string.karting_title_case)");
                return string125;
            case 86:
                String string126 = context.getString(R.string.dodgeball_title_case);
                Intrinsics.checkNotNullExpressionValue(string126, "context.getString(R.string.dodgeball_title_case)");
                return string126;
            case 87:
                String string127 = context.getString(R.string.yoyo_title_case);
                Intrinsics.checkNotNullExpressionValue(string127, "context.getString(R.string.yoyo_title_case)");
                return string127;
            case 88:
                String string128 = context.getString(R.string.locking_title_case);
                Intrinsics.checkNotNullExpressionValue(string128, "context.getString(R.string.locking_title_case)");
                return string128;
            case 89:
                String string129 = context.getString(R.string.burpees_title_case);
                Intrinsics.checkNotNullExpressionValue(string129, "context.getString(R.string.burpees_title_case)");
                return string129;
            case 90:
                String string130 = context.getString(R.string.belly_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string130, "context.getString(R.string.belly_dance_title_case)");
                return string130;
            case 91:
                String string131 = context.getString(R.string.parkour_title_case);
                Intrinsics.checkNotNullExpressionValue(string131, "context.getString(R.string.parkour_title_case)");
                return string131;
            case 92:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string132 = context.getString(R.string.jazz_title_case);
                    Intrinsics.checkNotNullExpressionValue(string132, "{\n                contex…title_case)\n            }");
                    return string132;
                }
                String string133 = context.getString(R.string.jazz_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string133, "{\n                contex…title_case)\n            }");
                return string133;
            case 93:
                String string134 = context.getString(R.string.modern_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string134, "context.getString(R.stri….modern_dance_title_case)");
                return string134;
            case 94:
                DeviceUtils.Companion companion19 = DeviceUtils.Companion;
                if (!companion19.isTouchLunarPlusDevice(context) && !companion19.isEastApexDevice(context)) {
                    if (companion19.isSmaDevice(context)) {
                        String string135 = context.getString(R.string.crossfit_cap_case);
                        Intrinsics.checkNotNullExpressionValue(string135, "{\n                contex…t_cap_case)\n            }");
                        return string135;
                    }
                    String string136 = context.getString(R.string.cross_fit_title_case);
                    Intrinsics.checkNotNullExpressionValue(string136, "{\n                contex…title_case)\n            }");
                    return string136;
                }
                String string137 = context.getString(R.string.cross_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string137, "{\n                contex…title_case)\n            }");
                return string137;
            case 95:
                String string138 = context.getString(R.string.latin_dance_title_case);
                Intrinsics.checkNotNullExpressionValue(string138, "context.getString(R.string.latin_dance_title_case)");
                return string138;
            case 96:
                String string139 = context.getString(R.string.ballet_title_case);
                Intrinsics.checkNotNullExpressionValue(string139, "context.getString(R.string.ballet_title_case)");
                return string139;
            case 97:
                String string140 = context.getString(R.string.baseball_title_case);
                Intrinsics.checkNotNullExpressionValue(string140, "context.getString(R.string.baseball_title_case)");
                return string140;
            case 98:
                String string141 = context.getString(R.string.racquetball_title_case);
                Intrinsics.checkNotNullExpressionValue(string141, "context.getString(R.string.racquetball_title_case)");
                return string141;
            case 99:
                String string142 = context.getString(R.string.curling_title_case);
                Intrinsics.checkNotNullExpressionValue(string142, "context.getString(R.string.curling_title_case)");
                return string142;
            case 100:
                String string143 = context.getString(R.string.hunting_title_case);
                Intrinsics.checkNotNullExpressionValue(string143, "context.getString(R.string.hunting_title_case)");
                return string143;
            case 101:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string144 = context.getString(R.string.snowboard_title_case);
                    Intrinsics.checkNotNullExpressionValue(string144, "{\n                contex…title_case)\n            }");
                    return string144;
                }
                String string145 = context.getString(R.string.snowboarding_title_case);
                Intrinsics.checkNotNullExpressionValue(string145, "{\n                contex…title_case)\n            }");
                return string145;
            case 102:
                String string146 = context.getString(R.string.fishing_title_case);
                Intrinsics.checkNotNullExpressionValue(string146, "context.getString(R.string.fishing_title_case)");
                return string146;
            case 103:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string147 = context.getString(R.string.disc_throw_title_case);
                    Intrinsics.checkNotNullExpressionValue(string147, "{\n                contex…title_case)\n            }");
                    return string147;
                }
                String string148 = context.getString(R.string.disc_sports_title_case);
                Intrinsics.checkNotNullExpressionValue(string148, "{\n                contex…title_case)\n            }");
                return string148;
            case 104:
                String string149 = context.getString(R.string.fitness_gaming_title_case);
                Intrinsics.checkNotNullExpressionValue(string149, "context.getString(R.stri…itness_gaming_title_case)");
                return string149;
            case 105:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string150 = context.getString(R.string.aerobics_exercise_title_case);
                    Intrinsics.checkNotNullExpressionValue(string150, "{\n                contex…title_case)\n            }");
                    return string150;
                }
                String string151 = context.getString(R.string.aerobics_gyms_title_case);
                Intrinsics.checkNotNullExpressionValue(string151, "{\n                contex…title_case)\n            }");
                return string151;
            case 106:
                String string152 = context.getString(R.string.group_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string152, "context.getString(R.stri…roup_training_title_case)");
                return string152;
            case 107:
                String string153 = context.getString(R.string.cardio_boxing_title_case);
                Intrinsics.checkNotNullExpressionValue(string153, "context.getString(R.stri…cardio_boxing_title_case)");
                return string153;
            case 108:
                DeviceUtils.Companion companion20 = DeviceUtils.Companion;
                if (companion20.isEastApexDevice(context)) {
                    String string154 = context.getString(R.string.mountaineer_title_case);
                    Intrinsics.checkNotNullExpressionValue(string154, "{\n                contex…title_case)\n            }");
                    return string154;
                } else if (companion20.isSmaDevice(context)) {
                    String string155 = context.getString(R.string.mountain_climber_title_case);
                    Intrinsics.checkNotNullExpressionValue(string155, "{\n                contex…title_case)\n            }");
                    return string155;
                } else {
                    String string156 = context.getString(R.string.mountaineering_title_case);
                    Intrinsics.checkNotNullExpressionValue(string156, "{\n                contex…title_case)\n            }");
                    return string156;
                }
            case 109:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string157 = context.getString(R.string.fence_title_case);
                    Intrinsics.checkNotNullExpressionValue(string157, "{\n                contex…title_case)\n            }");
                    return string157;
                }
                String string158 = context.getString(R.string.fencing_title_case);
                Intrinsics.checkNotNullExpressionValue(string158, "{\n                contex…title_case)\n            }");
                return string158;
            case 110:
                String string159 = context.getString(R.string.softball_title_case);
                Intrinsics.checkNotNullExpressionValue(string159, "context.getString(R.string.softball_title_case)");
                return string159;
            case 111:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string160 = context.getString(R.string.football_title_case);
                    Intrinsics.checkNotNullExpressionValue(string160, "{\n                contex…title_case)\n            }");
                    return string160;
                }
                String string161 = context.getString(R.string.american_football_title_case);
                Intrinsics.checkNotNullExpressionValue(string161, "{\n                contex…title_case)\n            }");
                return string161;
            case 112:
                String string162 = context.getString(R.string.volleyball_title_case);
                Intrinsics.checkNotNullExpressionValue(string162, "context.getString(R.string.volleyball_title_case)");
                return string162;
            case 113:
                String string163 = context.getString(R.string.rolling_title_case);
                Intrinsics.checkNotNullExpressionValue(string163, "context.getString(R.string.rolling_title_case)");
                return string163;
            case 114:
                String string164 = context.getString(R.string.pickleball_title_case);
                Intrinsics.checkNotNullExpressionValue(string164, "context.getString(R.string.pickleball_title_case)");
                return string164;
            case 115:
                String string165 = context.getString(R.string.taekwondo_title_case);
                Intrinsics.checkNotNullExpressionValue(string165, "context.getString(R.string.taekwondo_title_case)");
                return string165;
            case 116:
                String string166 = context.getString(R.string.karate_title_case);
                Intrinsics.checkNotNullExpressionValue(string166, "context.getString(R.string.karate_title_case)");
                return string166;
            case 117:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string167 = context.getString(R.string.flexiblity_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string167, "{\n                contex…title_case)\n            }");
                    return string167;
                }
                String string168 = context.getString(R.string.flexiblity_title_case);
                Intrinsics.checkNotNullExpressionValue(string168, "{\n                contex…title_case)\n            }");
                return string168;
            case 118:
                DeviceUtils.Companion companion21 = DeviceUtils.Companion;
                if (companion21.isEastApexDevice(context)) {
                    String string169 = context.getString(R.string.stepper_title_case_);
                    Intrinsics.checkNotNullExpressionValue(string169, "{\n                contex…itle_case_)\n            }");
                    return string169;
                } else if (companion21.isSmaDevice(context)) {
                    if (companion21.isSmaRoundJieieDevice(context)) {
                        string6 = context.getString(R.string.step);
                    } else {
                        string6 = context.getString(R.string.steps);
                    }
                    Intrinsics.checkNotNullExpressionValue(string6, "{\n                if(Dev…          }\n            }");
                    return string6;
                } else {
                    String string170 = context.getString(R.string.stepper_title_case);
                    Intrinsics.checkNotNullExpressionValue(string170, "{\n                contex…title_case)\n            }");
                    return string170;
                }
            case 119:
                String string171 = context.getString(R.string.sit_ups_title_case);
                Intrinsics.checkNotNullExpressionValue(string171, "context.getString(R.string.sit_ups_title_case)");
                return string171;
            case 120:
                String string172 = context.getString(R.string.aerobics_title_case);
                Intrinsics.checkNotNullExpressionValue(string172, "context.getString(R.string.aerobics_title_case)");
                return string172;
            case 121:
                DeviceUtils.Companion companion22 = DeviceUtils.Companion;
                if (!companion22.isEastApexDevice(context) && !companion22.isSmaDevice(context)) {
                    if (companion22.isRuggedDevice(context)) {
                        String string173 = context.getString(R.string.planking);
                        Intrinsics.checkNotNullExpressionValue(string173, "{\n                contex…g.planking)\n            }");
                        return string173;
                    }
                    String string174 = context.getString(R.string.plank_title_case);
                    Intrinsics.checkNotNullExpressionValue(string174, "{\n                contex…title_case)\n            }");
                    return string174;
                }
                String string175 = context.getString(R.string.plank_title_case_);
                Intrinsics.checkNotNullExpressionValue(string175, "{\n                contex…itle_case_)\n            }");
                return string175;
            case 122:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    String string176 = context.getString(R.string.jumping_jack_title_case);
                    Intrinsics.checkNotNullExpressionValue(string176, "{\n                contex…title_case)\n            }");
                    return string176;
                }
                String string177 = context.getString(R.string.jumping_jacks_title_case);
                Intrinsics.checkNotNullExpressionValue(string177, "{\n                contex…title_case)\n            }");
                return string177;
            case 123:
                if (DeviceUtils.Companion.isEastApexDevice(context)) {
                    if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.leap_call), false)) {
                        string7 = context.getString(R.string.skydive_title_case);
                    } else {
                        string7 = context.getString(R.string.parachute_title_case);
                    }
                    Intrinsics.checkNotNullExpressionValue(string7, "{\n                if (Se…          }\n            }");
                    return string7;
                }
                String string178 = context.getString(R.string.parachute_title_case);
                Intrinsics.checkNotNullExpressionValue(string178, "{\n                contex…title_case)\n            }");
                return string178;
            case 124:
                String string179 = context.getString(R.string.trail_run_title_case);
                Intrinsics.checkNotNullExpressionValue(string179, "context.getString(R.string.trail_run_title_case)");
                return string179;
            case 125:
                String string180 = context.getString(R.string.roller_skating_title_case);
                Intrinsics.checkNotNullExpressionValue(string180, "context.getString(R.stri…oller_skating_title_case)");
                return string180;
            case 126:
                String string181 = context.getString(R.string.weightlifting_title_case);
                Intrinsics.checkNotNullExpressionValue(string181, "context.getString(R.stri…weightlifting_title_case)");
                return string181;
            case 127:
                String string182 = context.getString(R.string.stretch_title_case);
                Intrinsics.checkNotNullExpressionValue(string182, "context.getString(R.string.stretch_title_case)");
                return string182;
            case 128:
                String string183 = context.getString(R.string.deadlift_title_case);
                Intrinsics.checkNotNullExpressionValue(string183, "context.getString(R.string.deadlift_title_case)");
                return string183;
            case 129:
                String string184 = context.getString(R.string.upper_body_training_case);
                Intrinsics.checkNotNullExpressionValue(string184, "context.getString(R.stri…upper_body_training_case)");
                return string184;
            case 130:
                String string185 = context.getString(R.string.lower_body_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string185, "context.getString(R.stri…body_training_title_case)");
                return string185;
            case 131:
                String string186 = context.getString(R.string.abs_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string186, "context.getString(R.stri….abs_training_title_case)");
                return string186;
            case 132:
                String string187 = context.getString(R.string.back_training_title_case);
                Intrinsics.checkNotNullExpressionValue(string187, "context.getString(R.stri…back_training_title_case)");
                return string187;
            case 133:
                String string188 = context.getString(R.string.sup_title_case);
                Intrinsics.checkNotNullExpressionValue(string188, "context.getString(R.string.sup_title_case)");
                return string188;
            case 134:
                String string189 = context.getString(R.string.water_polo_title_case);
                Intrinsics.checkNotNullExpressionValue(string189, "context.getString(R.string.water_polo_title_case)");
                return string189;
            case 135:
                String string190 = context.getString(R.string.thrash_title_case);
                Intrinsics.checkNotNullExpressionValue(string190, "context.getString(R.string.thrash_title_case)");
                return string190;
            case 136:
                String string191 = context.getString(R.string.kayak_title_case);
                Intrinsics.checkNotNullExpressionValue(string191, "context.getString(R.string.kayak_title_case)");
                return string191;
            case 137:
                String string192 = context.getString(R.string.drifting_title_case);
                Intrinsics.checkNotNullExpressionValue(string192, "context.getString(R.string.drifting_title_case)");
                return string192;
            case 138:
                String string193 = context.getString(R.string.boating_title_case);
                Intrinsics.checkNotNullExpressionValue(string193, "context.getString(R.string.boating_title_case)");
                return string193;
            case 139:
                String string194 = context.getString(R.string.fin_swim_title_case);
                Intrinsics.checkNotNullExpressionValue(string194, "context.getString(R.string.fin_swim_title_case)");
                return string194;
            case 140:
                String string195 = context.getString(R.string.diving_title_case);
                Intrinsics.checkNotNullExpressionValue(string195, "context.getString(R.string.diving_title_case)");
                return string195;
            case 141:
                String string196 = context.getString(R.string.artistic_swim_title_case);
                Intrinsics.checkNotNullExpressionValue(string196, "context.getString(R.stri…artistic_swim_title_case)");
                return string196;
            case 142:
                String string197 = context.getString(R.string.snorkel_title_case);
                Intrinsics.checkNotNullExpressionValue(string197, "context.getString(R.string.snorkel_title_case)");
                return string197;
            case 143:
                String string198 = context.getString(R.string.kitesurfing_title_case);
                Intrinsics.checkNotNullExpressionValue(string198, "context.getString(R.string.kitesurfing_title_case)");
                return string198;
            case 144:
                String string199 = context.getString(R.string.atv_title_case);
                Intrinsics.checkNotNullExpressionValue(string199, "context.getString(R.string.atv_title_case)");
                return string199;
            case 145:
                String string200 = context.getString(R.string.beach_soccer_title_case);
                Intrinsics.checkNotNullExpressionValue(string200, "context.getString(R.stri….beach_soccer_title_case)");
                return string200;
            case CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA /* 146 */:
                String string201 = context.getString(R.string.wushu_title_case);
                Intrinsics.checkNotNullExpressionValue(string201, "context.getString(R.string.wushu_title_case)");
                return string201;
            case 147:
                String string202 = context.getString(R.string.muay_thai_title_case);
                Intrinsics.checkNotNullExpressionValue(string202, "context.getString(R.string.muay_thai_title_case)");
                return string202;
            case 148:
                String string203 = context.getString(R.string.judo_title_case);
                Intrinsics.checkNotNullExpressionValue(string203, "context.getString(R.string.judo_title_case)");
                return string203;
            case 149:
                String string204 = context.getString(R.string.snowmobile_title_case);
                Intrinsics.checkNotNullExpressionValue(string204, "context.getString(R.string.snowmobile_title_case)");
                return string204;
            case 150:
                String string205 = context.getString(R.string.puck_title_case);
                Intrinsics.checkNotNullExpressionValue(string205, "context.getString(R.string.puck_title_case)");
                return string205;
            case 151:
                String string206 = context.getString(R.string.skating_title_case);
                Intrinsics.checkNotNullExpressionValue(string206, "context.getString(R.string.skating_title_case)");
                return string206;
            case 152:
                String string207 = context.getString(R.string.squash_title_case);
                Intrinsics.checkNotNullExpressionValue(string207, "context.getString(R.string.squash_title_case)");
                return string207;
            case 153:
                String string208 = context.getString(R.string.raga_title_case);
                Intrinsics.checkNotNullExpressionValue(string208, "context.getString(R.string.raga_title_case)");
                return string208;
            case 154:
                String string209 = context.getString(R.string.billiards_title_case);
                Intrinsics.checkNotNullExpressionValue(string209, "context.getString(R.string.billiards_title_case)");
                return string209;
            case 155:
                String string210 = context.getString(R.string.kite_title_case);
                Intrinsics.checkNotNullExpressionValue(string210, "context.getString(R.string.kite_title_case)");
                return string210;
            case 156:
                String string211 = context.getString(R.string.frisbee_title_case);
                Intrinsics.checkNotNullExpressionValue(string211, "context.getString(R.string.frisbee_title_case)");
                return string211;
            case 157:
                String string212 = context.getString(R.string.equestrian_title_case);
                Intrinsics.checkNotNullExpressionValue(string212, "context.getString(R.string.equestrian_title_case)");
                return string212;
            case 158:
                String string213 = context.getString(R.string.racing_title_case);
                Intrinsics.checkNotNullExpressionValue(string213, "context.getString(R.string.racing_title_case)");
                return string213;
            case 159:
                String string214 = context.getString(R.string.push_pull_title_case);
                Intrinsics.checkNotNullExpressionValue(string214, "context.getString(R.string.push_pull_title_case)");
                return string214;
            case 160:
                String string215 = context.getString(R.string.air_walker_title_case);
                Intrinsics.checkNotNullExpressionValue(string215, "context.getString(R.string.air_walker_title_case)");
                return string215;
            case 161:
                String string216 = context.getString(R.string.cross_training__title_case);
                Intrinsics.checkNotNullExpressionValue(string216, "context.getString(R.stri…oss_training__title_case)");
                return string216;
            case 162:
                String string217 = context.getString(R.string.mixed_cardio_title_case);
                Intrinsics.checkNotNullExpressionValue(string217, "context.getString(R.stri….mixed_cardio_title_case)");
                return string217;
            case 163:
                String string218 = context.getString(R.string.kickboxing);
                Intrinsics.checkNotNullExpressionValue(string218, "context.getString(R.string.kickboxing)");
                return string218;
            case 164:
                String string219 = context.getString(R.string.barre);
                Intrinsics.checkNotNullExpressionValue(string219, "context.getString(R.string.barre)");
                return string219;
            case 165:
                String string220 = context.getString(R.string.australian_football);
                Intrinsics.checkNotNullExpressionValue(string220, "context.getString(R.string.australian_football)");
                return string220;
            case 166:
                String string221 = context.getString(R.string.downhall_skiing_title_case);
                Intrinsics.checkNotNullExpressionValue(string221, "context.getString(R.stri…wnhall_skiing_title_case)");
                return string221;
            case 167:
                String string222 = context.getString(R.string.athletics);
                Intrinsics.checkNotNullExpressionValue(string222, "context.getString(R.string.athletics)");
                return string222;
            case 168:
                String string223 = context.getString(R.string.vo2max_test_title_case);
                Intrinsics.checkNotNullExpressionValue(string223, "context.getString(R.string.vo2max_test_title_case)");
                return string223;
            case 169:
                String string224 = context.getString(R.string.trampoline);
                Intrinsics.checkNotNullExpressionValue(string224, "context.getString(R.string.trampoline)");
                return string224;
            case 170:
                String string225 = context.getString(R.string.hoverboard);
                Intrinsics.checkNotNullExpressionValue(string225, "context.getString(R.string.hoverboard)");
                return string225;
            case 171:
                String string226 = context.getString(R.string.blading);
                Intrinsics.checkNotNullExpressionValue(string226, "context.getString(R.string.blading)");
                return string226;
            case 172:
                String string227 = context.getString(R.string.bungee_jumping_title_case);
                Intrinsics.checkNotNullExpressionValue(string227, "context.getString(R.stri…ungee_jumping_title_case)");
                return string227;
            case 173:
                String string228 = context.getString(R.string.shooting);
                Intrinsics.checkNotNullExpressionValue(string228, "context.getString(R.string.shooting)");
                return string228;
            case 174:
                String string229 = context.getString(R.string.marathon);
                Intrinsics.checkNotNullExpressionValue(string229, "context.getString(R.string.marathon)");
                return string229;
            case 175:
                if (DeviceUtils.Companion.isSmaDevice(context)) {
                    String string230 = context.getString(R.string.travel_by_walking);
                    Intrinsics.checkNotNullExpressionValue(string230, "context.getString(\n     …_by_walking\n            )");
                    return string230;
                }
                String string231 = context.getString(R.string.trekking);
                Intrinsics.checkNotNullExpressionValue(string231, "context.getString(R.string.trekking)");
                return string231;
            case 176:
                String string232 = context.getString(R.string.pole_dancing);
                Intrinsics.checkNotNullExpressionValue(string232, "context.getString(R.string.pole_dancing)");
                return string232;
            case 177:
                String string233 = context.getString(R.string.disco);
                Intrinsics.checkNotNullExpressionValue(string233, "context.getString(R.string.disco)");
                return string233;
            case 178:
                String string234 = context.getString(R.string.tap_dance);
                Intrinsics.checkNotNullExpressionValue(string234, "context.getString(R.string.tap_dance)");
                return string234;
            case 179:
                String string235 = context.getString(R.string.scooter);
                Intrinsics.checkNotNullExpressionValue(string235, "context.getString(R.string.scooter)");
                return string235;
            case 180:
                String string236 = context.getString(R.string.hammer);
                Intrinsics.checkNotNullExpressionValue(string236, "context.getString(R.string.hammer)");
                return string236;
            case 181:
                String string237 = context.getString(R.string.leg_press);
                Intrinsics.checkNotNullExpressionValue(string237, "context.getString(R.string.leg_press)");
                return string237;
            case 182:
                String string238 = context.getString(R.string.off_road_bike);
                Intrinsics.checkNotNullExpressionValue(string238, "context.getString(R.string.off_road_bike)");
                return string238;
            case 183:
                String string239 = context.getString(R.string.motocross);
                Intrinsics.checkNotNullExpressionValue(string239, "context.getString(R.string.motocross)");
                return string239;
            case 184:
                String string240 = context.getString(R.string.croquet);
                Intrinsics.checkNotNullExpressionValue(string240, "context.getString(R.string.croquet)");
                return string240;
            case 185:
                String string241 = context.getString(R.string.floor_ball);
                Intrinsics.checkNotNullExpressionValue(string241, "context.getString(R.string.floor_ball)");
                return string241;
            case 186:
                String string242 = context.getString(R.string.jai_ball);
                Intrinsics.checkNotNullExpressionValue(string242, "context.getString(R.string.jai_ball)");
                return string242;
            case 187:
                String string243 = context.getString(R.string.tennis_doubles);
                Intrinsics.checkNotNullExpressionValue(string243, "context.getString(R.string.tennis_doubles)");
                return string243;
            case 188:
                String string244 = context.getString(R.string.body_combat);
                Intrinsics.checkNotNullExpressionValue(string244, "context.getString(R.string.body_combat)");
                return string244;
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                DeviceUtils.Companion companion23 = DeviceUtils.Companion;
                if (companion23.isSmaDevice(context)) {
                    if (companion23.isSmaRoundJieieDevice(context)) {
                        string8 = context.getString(R.string.body_balance);
                    } else {
                        string8 = context.getString(R.string.body_balancing);
                    }
                    Intrinsics.checkNotNullExpressionValue(string8, "{\n                if (De…          }\n            }");
                    return string8;
                }
                String string245 = context.getString(R.string.body_balancing);
                Intrinsics.checkNotNullExpressionValue(string245, "{\n                contex…_balancing)\n            }");
                return string245;
            case 190:
                String string246 = context.getString(R.string.trx);
                Intrinsics.checkNotNullExpressionValue(string246, "context.getString(R.string.trx)");
                return string246;
            case 191:
                String string247 = context.getString(R.string.tae_bo);
                Intrinsics.checkNotNullExpressionValue(string247, "context.getString(R.string.tae_bo)");
                return string247;
            case 192:
                String string248 = context.getString(R.string.disc_throw);
                Intrinsics.checkNotNullExpressionValue(string248, "context.getString(R.string.disc_throw)");
                return string248;
            case 193:
                String string249 = context.getString(R.string.racing);
                Intrinsics.checkNotNullExpressionValue(string249, "context.getString(R.string.racing)");
                return string249;
            default:
                String string250 = context.getString(R.string.workout_title_case);
                Intrinsics.checkNotNullExpressionValue(string250, "context.getString(R.string.workout_title_case)");
                return string250;
        }
    }

    public final int getAutoActivityDetectionFWOrderForAnActivity(@NotNull String activityCode) {
        Intrinsics.checkNotNullParameter(activityCode, "activityCode");
        if (Intrinsics.areEqual(activityCode, "17-262")) {
            return 1;
        }
        Intrinsics.areEqual(activityCode, "12-20");
        return 0;
    }

    @NotNull
    public final String getCurrentDate() {
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar)");
        return format;
    }

    @NotNull
    public final String getCurrentDatePlusOne() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        calendar.getTime();
        String format = AppUtils.getSimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public final long getCurrentTimeInSecs() {
        long j = 1000;
        return (System.currentTimeMillis() / j) * j;
    }

    @Nullable
    public final String getDateFromTimeStamp(@Nullable Long l, @NotNull String strFormat) {
        Intrinsics.checkNotNullParameter(strFormat, "strFormat");
        if (l != null) {
            l.longValue();
            return AppUtils.getSimpleDateFormat(strFormat).format(new Date(l.longValue()));
        }
        return null;
    }

    @NotNull
    public final String getDayOfWeek(int i, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        switch (i) {
            case 1:
                String string = context.getString(R.string.sunday);
                Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.sunday)");
                return string;
            case 2:
                String string2 = context.getString(R.string.monday);
                Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.monday)");
                return string2;
            case 3:
                String string3 = context.getString(R.string.tuesday);
                Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.tuesday)");
                return string3;
            case 4:
                String string4 = context.getString(R.string.wednesday);
                Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.wednesday)");
                return string4;
            case 5:
                String string5 = context.getString(R.string.thursday);
                Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.thursday)");
                return string5;
            case 6:
                String string6 = context.getString(R.string.friday);
                Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.friday)");
                return string6;
            case 7:
                String string7 = context.getString(R.string.saturday);
                Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.saturday)");
                return string7;
            default:
                return "";
        }
    }

    public final double getDistance(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(context).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(this).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            return convertKMToMiles(i / 1000);
        }
        return convertMetersToKm(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0071 A[LOOP:0: B:19:0x006b->B:21:0x0071, LOOP_END] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceOfSessionInMeters(@org.jetbrains.annotations.NotNull com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r6, @org.jetbrains.annotations.NotNull android.content.Context r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.utils.WorkoutUtils.a
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.utils.WorkoutUtils$a r0 = (com.coveiot.android.activitymodes.utils.WorkoutUtils.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.utils.WorkoutUtils$a r0 = new com.coveiot.android.activitymodes.utils.WorkoutUtils$a
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L65
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$FloatRef r8 = new kotlin.jvm.internal.Ref$FloatRef
            r8.<init>()
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r4 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
            java.lang.Object r7 = r4.getInstance(r7)
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r7 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r7
            java.lang.String r6 = r6.getDate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r0.L$0 = r8
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r7.getSessionsOfParticularDay(r6, r0)
            if (r6 != r1) goto L62
            return r1
        L62:
            r7 = r8
            r8 = r6
            r6 = r2
        L65:
            java.util.List r8 = (java.util.List) r8
            java.util.Iterator r8 = r8.iterator()
        L6b:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L81
            java.lang.Object r0 = r8.next()
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r0 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r0
            int r1 = r6.element
            int r0 = r0.getTotal_distance()
            int r1 = r1 + r0
            r6.element = r1
            goto L6b
        L81:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r8 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            int r6 = r6.element
            float r6 = r8.convertCmToMeters(r6)
            r7.element = r6
            java.lang.Float r6 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.WorkoutUtils.getDistanceOfSessionInMeters(com.coveiot.android.activitymodes.database.entities.EntityPreparationDay, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final String getFormattedDate(@Nullable String str, @NotNull String srcFormat, @NotNull String destnFormat) {
        Intrinsics.checkNotNullParameter(srcFormat, "srcFormat");
        Intrinsics.checkNotNullParameter(destnFormat, "destnFormat");
        if (str != null) {
            String format = AppUtils.getSimpleDateFormat(destnFormat).format(AppUtils.getSimpleDateFormat(srcFormat).parse(str));
            Intrinsics.checkNotNullExpressionValue(format, "sdfNew.format(oldDate)");
            return format;
        }
        return "";
    }

    @NotNull
    public final String getFormattedDistance(double d) {
        String format = new DecimalFormat("0.#").format(d);
        Intrinsics.checkNotNullExpressionValue(format, "format.format(this)");
        return format;
    }

    @NotNull
    public final String getFormattedDuration(int i) {
        if (i == 0) {
            return "00:00:00";
        }
        int i2 = i % 60;
        int i3 = i / 60;
        int i4 = i3 % 60;
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String format = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i3 / 60)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        sb.append(format);
        sb.append(':');
        String format2 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i4)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(locale, format, *args)");
        sb.append(format2);
        sb.append(':');
        String format3 = String.format(locale, "%02d", Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
        Intrinsics.checkNotNullExpressionValue(format3, "format(locale, format, *args)");
        sb.append(format3);
        return sb.toString();
    }

    @NotNull
    public final String getFormattedPace(float f) {
        if (f == 0.0f) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        float f2 = 60;
        int i = (int) (f / f2);
        int i2 = (int) (f % f2);
        if (i <= 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        if (i >= 99) {
            return "99'99''";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d'%02d''", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public final String getFormattedPaceL(float f) {
        if (f == 0.0f) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        float f2 = 60;
        int i = (int) (f / f2);
        int i2 = (int) (f % f2);
        if (i <= 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d'%02d''", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public final WorkoutImageBean getImageUiBean(@NotNull Context context, @NotNull ActivityMode activityMode, @Nullable String str, @Nullable Integer num, @Nullable Integer num2) {
        int i;
        int i2;
        Object valueOf;
        int i3;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        int i4 = R.drawable.activity_walking_icon;
        Object valueOf2 = Integer.valueOf(i4);
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        if (!companion.isIDODevice(context) && !companion.isTouchELXDevice(context) && !companion.isEastApexDevice(context) && !companion.isRuggedDevice(context) && !companion.isSmaJieieDevice(context)) {
            int i5 = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
            if (i5 == 43) {
                valueOf2 = Integer.valueOf(R.drawable.activity_table_tennis_icon);
            } else if (i5 == 44) {
                valueOf2 = Integer.valueOf(R.drawable.activity_golf_icon);
            } else if (i5 == 46) {
                valueOf2 = Integer.valueOf(R.drawable.activity_rugby_icon);
            } else if (i5 == 69) {
                valueOf2 = Integer.valueOf(R.drawable.activity_hula_hoop_icon);
            } else if (i5 == 82) {
                valueOf2 = Integer.valueOf(R.drawable.activity_long_jump_icon);
            } else if (i5 == 97) {
                valueOf2 = Integer.valueOf(R.drawable.activity_baseball_icon);
            } else if (i5 == 112) {
                valueOf2 = Integer.valueOf(R.drawable.activity_volleyball_icon);
            } else if (i5 != 119) {
                switch (i5) {
                    case 1:
                        if (companion.isIDODevice(context) && (str == null || !m.equals(str, "OUTDOOR", true))) {
                            i4 = R.drawable.activity_treadmill_icon;
                        }
                        valueOf2 = Integer.valueOf(i4);
                        break;
                    case 2:
                        if (companion.isIDODevice(context)) {
                            if (str != null && m.equals(str, "OUTDOOR", true)) {
                                i = R.drawable.activity_running_icon;
                            } else {
                                i = R.drawable.activity_treadmill_icon;
                            }
                        } else {
                            i = R.drawable.activity_running_icon;
                        }
                        valueOf2 = Integer.valueOf(i);
                        break;
                    case 3:
                        if (!companion.isSmaDevice(context) && !companion.isMatrixDevice(context) && !companion.isIDODevice(context)) {
                            i2 = R.drawable.activity_cycling_icon;
                        } else if (str != null && m.equals(str, "INDOOR", true)) {
                            i2 = R.drawable.activity_spinning_icon;
                        } else {
                            i2 = R.drawable.activity_cycling_icon;
                        }
                        valueOf2 = Integer.valueOf(i2);
                        break;
                    case 4:
                        valueOf2 = Integer.valueOf(R.drawable.activity_badminton_icon);
                        break;
                    case 5:
                        valueOf2 = Integer.valueOf(R.drawable.activity_football_icon);
                        break;
                    case 6:
                        valueOf2 = Integer.valueOf(R.drawable.activity_tennis_icon);
                        break;
                    case 7:
                        valueOf2 = Integer.valueOf(R.drawable.activity_yoga_icon);
                        break;
                    case 8:
                        valueOf2 = Integer.valueOf(R.drawable.activity_meditation_icon);
                        break;
                    case 9:
                        valueOf2 = Integer.valueOf(R.drawable.activity_dance_icon);
                        break;
                    case 10:
                        valueOf2 = Integer.valueOf(R.drawable.activity_basketball_icon);
                        break;
                    case 11:
                        valueOf2 = Integer.valueOf(R.drawable.activity_hiking_icon);
                        break;
                    case 12:
                        valueOf2 = Integer.valueOf(R.drawable.activity_workout_icon);
                        break;
                    case 13:
                        valueOf2 = Integer.valueOf(R.drawable.activity_treadmill_icon);
                        break;
                    case 14:
                        valueOf2 = Integer.valueOf(R.drawable.activity_climbing_icon);
                        break;
                    case 15:
                        valueOf2 = Integer.valueOf(R.drawable.activity_skipping_icon);
                        break;
                    case 16:
                        valueOf2 = Integer.valueOf(R.drawable.activity_free_exercise_icon);
                        break;
                    case 17:
                        valueOf2 = Integer.valueOf(R.drawable.activity_elliptical);
                        break;
                    case 18:
                        valueOf2 = Integer.valueOf(R.drawable.activity_rowing_machine);
                        break;
                    case 19:
                        EntityPhysicalActivities physicalActivity = PhysicalActivityRepository.Companion.getInstance(context).getPhysicalActivity(num2, num);
                        if (physicalActivity != null) {
                            valueOf = physicalActivity.getIconUrl();
                            Intrinsics.checkNotNull(valueOf);
                        } else {
                            Intrinsics.checkNotNull(num);
                            int intValue = num.intValue();
                            Intrinsics.checkNotNull(num2);
                            valueOf = Integer.valueOf(getPhysicalActivityImage(intValue, num2.intValue()));
                        }
                        valueOf2 = valueOf;
                        break;
                    case 20:
                        if (companion.isIDODevice(context)) {
                            if (str != null && m.equals(str, "OUTDOOR", true)) {
                                i3 = R.drawable.activity_open_water_swim_icon;
                            } else {
                                i3 = R.drawable.activity_pool_swim_icon;
                            }
                        } else {
                            i3 = R.drawable.activity_pool_swim_icon;
                        }
                        valueOf2 = Integer.valueOf(i3);
                        break;
                    case 21:
                        valueOf2 = Integer.valueOf(R.drawable.activity_hiit_icon);
                        break;
                    case 22:
                        valueOf2 = Integer.valueOf(R.drawable.activity_cricket_icon);
                        break;
                    case 23:
                        valueOf2 = Integer.valueOf(R.drawable.activity_pilates_icon);
                        break;
                    case 24:
                        valueOf2 = Integer.valueOf(R.drawable.activity_zumba_icon);
                        break;
                    case 25:
                        valueOf2 = Integer.valueOf(R.drawable.activity_core_training_icon);
                        break;
                    case 26:
                        valueOf2 = Integer.valueOf(R.drawable.activity_cooldown_icon);
                        break;
                    case 27:
                        valueOf2 = Integer.valueOf(R.drawable.activity_traditional_strength_icon);
                        break;
                    case 28:
                        valueOf2 = Integer.valueOf(R.drawable.activity_functional_strength_icon);
                        break;
                    case 29:
                        valueOf2 = Integer.valueOf(R.drawable.activity_other_icon);
                        break;
                    default:
                        valueOf2 = Integer.valueOf(i4);
                        break;
                }
            } else {
                valueOf2 = Integer.valueOf(R.drawable.activity_sit_ups_icon);
            }
        } else {
            WorkoutImageBean workoutImageBean = WorkoutParameterFactory.INSTANCE.getWorkoutUtils(context).getWorkoutImageBean(activityMode, str, true);
            if (workoutImageBean != null) {
                valueOf2 = workoutImageBean.getImage();
            }
        }
        return new WorkoutImageBean(valueOf2);
    }

    @NotNull
    public final com.coveiot.android.khperformancecalculator.model.DeviceType getKhPerformanceDeviceType(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        return companion.isJstyleDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.JSTYLE : companion.isSmaDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.SMA : companion.isMoyangDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.MOYANG : companion.isMatrixDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.MATRIX : companion.isIDODevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.IDO : companion.isTouchELXDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.TOUCHELX : companion.isEastApexDevice(context) ? com.coveiot.android.khperformancecalculator.model.DeviceType.EASTAPEX : com.coveiot.android.khperformancecalculator.model.DeviceType.DEFAULT;
    }

    public final int getMaxHeartRate(int i) {
        if (i == 0) {
            i = 35;
        }
        return 220 - i;
    }

    public final int getMaximumWorkoutUiBeans(@NotNull WorkoutUiBeanProvider.ScreenType screenType) {
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        int i = WhenMappings.$EnumSwitchMapping$1[screenType.ordinal()];
        return (i == 1 || i == 2 || i == 3) ? 3 : -1;
    }

    public final void getMetadata(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Intrinsics.checkNotNullExpressionValue(applicationInfo, "context.packageManager.g…T_META_DATA\n            )");
            Bundle bundle = applicationInfo.metaData;
            if (bundle != null && !bundle.isEmpty()) {
                if (bundle.containsKey("com.google.android.geo.API_KEY")) {
                    String string = bundle.getString("com.google.android.geo.API_KEY");
                    if (string != null) {
                        if (string.length() > 0) {
                            f2881a = string;
                            return;
                        }
                    }
                    throw new SetupException(a("com.google.android.geo.API_KEY"));
                }
                throw new SetupException(b("com.google.android.geo.API_KEY"));
            }
            throw new SetupException(ErrorConstants.SETUP_ERR_MISSING_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new SetupException(ErrorConstants.SETUP_ERR_GENERIC);
        } catch (NullPointerException e2) {
            e2.printStackTrace();
            throw new SetupException(ErrorConstants.SETUP_ERR_GENERIC);
        }
    }

    public final int getMinUnitsForSession(@NotNull ActivityMode activityMode) {
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        int i = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
        return 100;
    }

    @NotNull
    public final Pair<Integer, Integer> getMinsAndSecs(@Nullable Integer num) {
        if (num != null) {
            num.intValue();
            return new Pair<>(Integer.valueOf(num.intValue() / 60), Integer.valueOf(num.intValue() % 60));
        }
        return new Pair<>(0, 0);
    }

    @NotNull
    public final String getMinsAndSecsColonSerperated(@Nullable Integer num) {
        Pair<Integer, Integer> minsAndSecs = getMinsAndSecs(num);
        DecimalFormat decimalFormat = new DecimalFormat("#00");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        String format = decimalFormat.format(minsAndSecs.getSecond());
        return minsAndSecs.getFirst().intValue() + ':' + format;
    }

    public final int getPhysicalActivityImage(int i, int i2) {
        switch (i) {
            case 4115:
                return R.drawable.activity_indoor_cycling_icon_cz2;
            case 4201:
            case 31150:
            case 31380:
                return R.drawable.activity_walking_icon_cz2;
            case 4276:
                return R.drawable.activity_yoga_icon_cz2;
            case 6175:
                return R.drawable.activity_dancing_icon_cz2;
            case 24596:
                return R.drawable.activity_running_icon_cz2;
            case 24601:
                return R.drawable.activity_treadmill_icon_cz2;
            case 30750:
                return R.drawable.activity_badminton_icon_cz2;
            case 30775:
                return R.drawable.activity_basketball_icon_cz2;
            case 30820:
                return R.drawable.activity_boxing_icon_cz2;
            case 30870:
                return R.drawable.activity_cricket_icon_cz2;
            case 30950:
                return R.drawable.activity_football_icon_cz2;
            case 31395:
                return R.drawable.activity_tennis_icon_cz2;
            case 35078:
                return R.drawable.activity_walking_icon_cz2;
            default:
                if (i2 == 17 && i == 262) {
                    return R.drawable.activity_walking_icon_cz2;
                }
                if (i2 == 12 && i == 25) {
                    return R.drawable.activity_treadmill_icon_cz2;
                }
                if (i2 == 12 && i == 20) {
                    return R.drawable.activity_running_icon_cz2;
                }
                if (i2 == 15 && i == 30) {
                    return R.drawable.activity_badminton_icon_cz2;
                }
                if (i2 == 15 && i == 230) {
                    return R.drawable.activity_football_icon_cz2;
                }
                if (i2 == 15 && i == 55) {
                    return R.drawable.activity_basketball_icon_cz2;
                }
                if (i2 == 15 && i == 675) {
                    return R.drawable.activity_tennis_icon_cz2;
                }
                if (i2 == 2 && i == 180) {
                    return R.drawable.activity_yoga_icon_cz2;
                }
                if (i2 == 3 && i == 31) {
                    return R.drawable.activity_dancing_icon_cz2;
                }
                if (i2 == 2 && i == 19) {
                    return R.drawable.activity_indoor_cycling_icon_cz2;
                }
                if (i2 == 15 && i == 150) {
                    return R.drawable.activity_cricket_icon_cz2;
                }
                if (i2 == 15 && i == 100) {
                    return R.drawable.activity_boxing_icon_cz2;
                }
                if (i2 == 15 && i == 430) {
                    return R.drawable.activity_karate_icon_cz2;
                }
                if (i2 == 15 && i == 660) {
                    return R.drawable.activity_table_tennis_icon_cz2;
                }
                if (i2 == 2 && i == 105) {
                    return R.drawable.activity_pilates_icon_cz2;
                }
                return R.drawable.activity_walking_icon_cz2;
        }
    }

    public final int getProgressForDay(int i, int i2) {
        return (int) ((i / i2) * 100.0f);
    }

    public final int getSPMValue(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return 0;
        }
        return (i * 60) / i2;
    }

    public final int getSampleRateForSessionInSecs(@NotNull ActivityMode activityMode) {
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        int i = WhenMappings.$EnumSwitchMapping$0[activityMode.ordinal()];
        return 5;
    }

    @NotNull
    public final String getTime(@NotNull Calendar calendar, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(calendar, "calendar");
        Intrinsics.checkNotNullParameter(context, "context");
        if (calendar.get(11) < 12) {
            String string = context.getString(R.string.morning);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.morning)");
            return string;
        }
        int i = calendar.get(11);
        boolean z = false;
        if (12 <= i && i < 16) {
            z = true;
        }
        if (z) {
            String string2 = context.getString(R.string.afternoon);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.afternoon)");
            return string2;
        }
        String string3 = context.getString(R.string.evening);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.evening)");
        return string3;
    }

    @NotNull
    public final String getTodayYesterdayString(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
        if (DateUtils.isToday(j)) {
            return String.valueOf(context.getResources().getString(R.string.today));
        }
        if (c(j)) {
            return context.getResources().getString(R.string.yesterday) + ' ';
        }
        return String.valueOf(simpleDateFormat.format(new Date(j)));
    }

    @NotNull
    public final String getTodayYesterdayStringWithTimeStamp(long j, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SimpleDateFormat simpleDateFormat = AppUtils.getSimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat simpleDateFormat2 = AppUtils.getSimpleDateFormat("hh:mm a");
        if (DateUtils.isToday(j)) {
            return context.getResources().getString(R.string.today) + ' ' + simpleDateFormat2.format(new Date(j));
        } else if (c(j)) {
            return context.getResources().getString(R.string.yesterday) + ' ' + simpleDateFormat2.format(new Date(j));
        } else {
            return simpleDateFormat.format(new Date(j)) + ' ' + simpleDateFormat2.format(new Date(j));
        }
    }

    @NotNull
    public final List<WorkoutUiBean> getWorkoutUiBeans(@NotNull Context context, @NotNull EntityWorkoutSession entityWorkoutSession, @NotNull WorkoutUiBeanProvider.ScreenType screenType) {
        String str;
        String str2;
        String str3;
        String str4;
        Integer num;
        Integer num2;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        Integer num3;
        String str10;
        Integer num4;
        String str11;
        Integer num5;
        String str12;
        Integer num6;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        ArrayList arrayList = new ArrayList();
        WorkoutUiBeanProvider workoutUiBeanProvider = WorkoutUiBeanProvider.INSTANCE;
        Integer valueOf = Integer.valueOf(entityWorkoutSession.getSession_duration());
        String activity_type = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type);
        WorkoutUiBean durationUiBean = workoutUiBeanProvider.getDurationUiBean(context, screenType, valueOf, activity_type);
        if (durationUiBean != null) {
            arrayList.add(durationUiBean);
        }
        Float valueOf2 = Float.valueOf(entityWorkoutSession.getTotal_calories());
        String activity_type2 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type2);
        WorkoutUiBean calorieUiBean = workoutUiBeanProvider.getCalorieUiBean(context, screenType, valueOf2, activity_type2);
        if (calorieUiBean != null) {
            arrayList.add(calorieUiBean);
        }
        Integer valueOf3 = Integer.valueOf(entityWorkoutSession.getAvg_hr());
        Integer valueOf4 = Integer.valueOf(entityWorkoutSession.getMin_hr());
        Integer valueOf5 = Integer.valueOf(entityWorkoutSession.getMax_hr());
        String activity_type3 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type3);
        WorkoutUiBean heartRateUiBean = workoutUiBeanProvider.getHeartRateUiBean(context, screenType, valueOf3, valueOf4, valueOf5, activity_type3, entityWorkoutSession.getActivityId(), entityWorkoutSession.getCategoryId());
        if (heartRateUiBean != null) {
            arrayList.add(heartRateUiBean);
        }
        Integer valueOf6 = Integer.valueOf(entityWorkoutSession.getTotal_steps());
        String activity_type4 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type4);
        WorkoutUiBean stepsUiBean = workoutUiBeanProvider.getStepsUiBean(context, screenType, valueOf6, activity_type4, entityWorkoutSession.getActivityId(), entityWorkoutSession.getCategoryId());
        if (stepsUiBean != null) {
            arrayList.add(stepsUiBean);
        }
        Integer valueOf7 = Integer.valueOf(entityWorkoutSession.getTotal_distance());
        String activity_type5 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type5);
        Integer activityId = entityWorkoutSession.getActivityId();
        Integer categoryId = entityWorkoutSession.getCategoryId();
        Integer num7 = null;
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor);
            str = indoor_outdoor;
        } else {
            str = null;
        }
        WorkoutUiBean distanceUiBean = workoutUiBeanProvider.getDistanceUiBean(context, screenType, valueOf7, activity_type5, activityId, categoryId, str);
        if (distanceUiBean != null) {
            arrayList.add(distanceUiBean);
        }
        Float valueOf8 = Float.valueOf(entityWorkoutSession.getPace());
        int total_distance = entityWorkoutSession.getTotal_distance();
        int session_duration = entityWorkoutSession.getSession_duration();
        String activity_type6 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type6);
        Integer activityId2 = entityWorkoutSession.getActivityId();
        Integer categoryId2 = entityWorkoutSession.getCategoryId();
        Float avgPace = entityWorkoutSession.getAvgPace();
        Integer valueOf9 = avgPace != null ? Integer.valueOf((int) avgPace.floatValue()) : null;
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor2 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor2);
            str2 = indoor_outdoor2;
        } else {
            str2 = null;
        }
        WorkoutUiBean paceUiBean = workoutUiBeanProvider.getPaceUiBean(context, screenType, valueOf8, total_distance, session_duration, activity_type6, activityId2, categoryId2, valueOf9, str2);
        if (paceUiBean != null) {
            arrayList.add(paceUiBean);
        }
        Float maxPace = entityWorkoutSession.getMaxPace();
        String activity_type7 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type7);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor3 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor3);
            str3 = indoor_outdoor3;
        } else {
            str3 = null;
        }
        WorkoutUiBean fastestPaceUiBean = workoutUiBeanProvider.getFastestPaceUiBean(context, screenType, maxPace, activity_type7, str3);
        if (fastestPaceUiBean != null) {
            arrayList.add(fastestPaceUiBean);
        }
        Float minPace = entityWorkoutSession.getMinPace();
        String activity_type8 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type8);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor4 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor4);
            str4 = indoor_outdoor4;
        } else {
            str4 = null;
        }
        WorkoutUiBean lowestPaceUiBean = workoutUiBeanProvider.getLowestPaceUiBean(context, screenType, minPace, activity_type8, str4);
        if (lowestPaceUiBean != null) {
            arrayList.add(lowestPaceUiBean);
        }
        Integer valueOf10 = Integer.valueOf(entityWorkoutSession.getTotal_steps());
        Integer valueOf11 = Integer.valueOf(entityWorkoutSession.getSession_duration());
        String activity_type9 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type9);
        Integer avgStepFrequency = entityWorkoutSession.getAvgStepFrequency();
        if (avgStepFrequency != null) {
            avgStepFrequency.intValue();
            num = entityWorkoutSession.getAvgStepFrequency();
        } else {
            num = null;
        }
        WorkoutUiBean sPMUiBean = workoutUiBeanProvider.getSPMUiBean(context, screenType, valueOf10, valueOf11, activity_type9, num);
        if (sPMUiBean != null) {
            arrayList.add(sPMUiBean);
        }
        Integer maxStepFrequency = entityWorkoutSession.getMaxStepFrequency();
        if (maxStepFrequency != null) {
            maxStepFrequency.intValue();
            num2 = entityWorkoutSession.getMaxStepFrequency();
        } else {
            num2 = null;
        }
        String activity_type10 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type10);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor5 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor5);
            str5 = indoor_outdoor5;
        } else {
            str5 = null;
        }
        WorkoutUiBean maxSPMUiBean = workoutUiBeanProvider.getMaxSPMUiBean(context, screenType, num2, activity_type10, str5);
        if (maxSPMUiBean != null) {
            arrayList.add(maxSPMUiBean);
        }
        Integer avgStrideLength = entityWorkoutSession.getAvgStrideLength();
        String activity_type11 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type11);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor6 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor6);
            str6 = indoor_outdoor6;
        } else {
            str6 = null;
        }
        WorkoutUiBean avgStrideLengthUiBean = workoutUiBeanProvider.getAvgStrideLengthUiBean(context, screenType, avgStrideLength, activity_type11, str6);
        if (avgStrideLengthUiBean != null) {
            arrayList.add(avgStrideLengthUiBean);
        }
        Float avgSpeed = entityWorkoutSession.getAvgSpeed();
        String activity_type12 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type12);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor7 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor7);
            str7 = indoor_outdoor7;
        } else {
            str7 = null;
        }
        WorkoutUiBean avgSPeedUiBean = workoutUiBeanProvider.getAvgSPeedUiBean(context, screenType, avgSpeed, activity_type12, str7);
        if (avgSPeedUiBean != null) {
            arrayList.add(avgSPeedUiBean);
        }
        if (entityWorkoutSession.getSwimmingStyle() != null) {
            String swimmingStyle = entityWorkoutSession.getSwimmingStyle();
            Intrinsics.checkNotNull(swimmingStyle);
            str8 = swimmingStyle;
        } else {
            str8 = null;
        }
        String activity_type13 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type13);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor8 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor8);
            str9 = indoor_outdoor8;
        } else {
            str9 = null;
        }
        WorkoutUiBean swimStrokeUiBean = workoutUiBeanProvider.getSwimStrokeUiBean(context, screenType, str8, activity_type13, str9);
        if (swimStrokeUiBean != null) {
            arrayList.add(swimStrokeUiBean);
        }
        Integer avgSwolf = entityWorkoutSession.getAvgSwolf();
        if (avgSwolf != null) {
            avgSwolf.intValue();
            num3 = entityWorkoutSession.getAvgSwolf();
        } else {
            num3 = null;
        }
        String activity_type14 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type14);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor9 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor9);
            str10 = indoor_outdoor9;
        } else {
            str10 = null;
        }
        WorkoutUiBean avgSwolfUiBean = workoutUiBeanProvider.getAvgSwolfUiBean(context, screenType, num3, activity_type14, str10);
        if (avgSwolfUiBean != null) {
            arrayList.add(avgSwolfUiBean);
        }
        Integer totalStrokes = entityWorkoutSession.getTotalStrokes();
        if (totalStrokes != null) {
            totalStrokes.intValue();
            num4 = entityWorkoutSession.getTotalStrokes();
        } else {
            num4 = null;
        }
        String activity_type15 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type15);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor10 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor10);
            str11 = indoor_outdoor10;
        } else {
            str11 = null;
        }
        WorkoutUiBean totalStrokeUiBean = workoutUiBeanProvider.getTotalStrokeUiBean(context, screenType, num4, activity_type15, str11);
        if (totalStrokeUiBean != null) {
            arrayList.add(totalStrokeUiBean);
        }
        Integer totalLaps = entityWorkoutSession.getTotalLaps();
        if (totalLaps != null) {
            totalLaps.intValue();
            num5 = entityWorkoutSession.getTotalLaps();
        } else {
            num5 = null;
        }
        String activity_type16 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type16);
        if (entityWorkoutSession.getIndoor_outdoor() != null) {
            String indoor_outdoor11 = entityWorkoutSession.getIndoor_outdoor();
            Intrinsics.checkNotNull(indoor_outdoor11);
            str12 = indoor_outdoor11;
        } else {
            str12 = null;
        }
        WorkoutUiBean lapsUiBean = workoutUiBeanProvider.getLapsUiBean(context, screenType, num5, activity_type16, str12);
        if (lapsUiBean != null) {
            arrayList.add(lapsUiBean);
        }
        Integer totalStrokes2 = entityWorkoutSession.getTotalStrokes();
        if (totalStrokes2 != null) {
            totalStrokes2.intValue();
            num6 = entityWorkoutSession.getTotalStrokes();
        } else {
            num6 = null;
        }
        String activity_type17 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type17);
        WorkoutUiBean strokesOrCounterUiBean = workoutUiBeanProvider.getStrokesOrCounterUiBean(context, screenType, num6, activity_type17);
        if (strokesOrCounterUiBean != null) {
            arrayList.add(strokesOrCounterUiBean);
        }
        Integer avgStrokeFreq = entityWorkoutSession.getAvgStrokeFreq();
        if (avgStrokeFreq != null) {
            avgStrokeFreq.intValue();
            num7 = entityWorkoutSession.getAvgStrokeFreq();
        }
        String activity_type18 = entityWorkoutSession.getActivity_type();
        Intrinsics.checkNotNull(activity_type18);
        WorkoutUiBean strokesOrCounterFrequencyUiBean = workoutUiBeanProvider.getStrokesOrCounterFrequencyUiBean(context, screenType, num7, activity_type18);
        if (strokesOrCounterFrequencyUiBean != null) {
            arrayList.add(strokesOrCounterFrequencyUiBean);
        }
        return arrayList;
    }

    public final boolean isBestActivityOfType(@NotNull Context context, @NotNull EntityWorkoutSession entityWorkoutSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(entityWorkoutSession, "entityWorkoutSession");
        KHPerformancePreferenceManager.getInstance(context).getBestActivities().values();
        ArrayList arrayList = new ArrayList(KHPerformancePreferenceManager.getInstance(context).getBestActivities().values());
        if (!arrayList.isEmpty()) {
            entityWorkoutSession.getStart_time();
            if (entityWorkoutSession.getStart_time() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    KHPActivityData kHPActivityData = (KHPActivityData) it.next();
                    if (kHPActivityData != null) {
                        kHPActivityData.getStartTime();
                        if (kHPActivityData.getStartTime() > 0 && kHPActivityData.getStartTime() == entityWorkoutSession.getStart_time()) {
                            LogHelper.d("WorkoutUtils", "best activity-> " + entityWorkoutSession.getActivity_type() + ' ' + entityWorkoutSession.getTotal_calories());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public final boolean isCurrentDay(@NotNull EntityPreparationDay day) {
        Intrinsics.checkNotNullParameter(day, "day");
        return m.equals(day.getDate(), AppUtils.formatDate(new Date(), "yyyy-MM-dd"), true);
    }

    public final boolean isCurrentWeek(@NotNull EntityPreparationWeek week) {
        Intrinsics.checkNotNullParameter(week, "week");
        return week.getDaysRange().contains(AppUtils.formatDate(new Date(), "yyyy-MM-dd"));
    }

    public final boolean isSelectedDayIsFutureDay(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Date parse = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str);
        Intrinsics.checkNotNullExpressionValue(parse, "sdf.parse(this)");
        return System.currentTimeMillis() < parse.getTime();
    }

    public final void setAPI_KEY(@Nullable String str) {
        f2881a = str;
    }

    public final boolean shouldShow1KActivity(@NotNull DeviceType deviceType, @NotNull List<? extends DeviceRemoteConfig.DeviceModelsBean> deviceModelList) {
        Intrinsics.checkNotNullParameter(deviceType, "deviceType");
        Intrinsics.checkNotNullParameter(deviceModelList, "deviceModelList");
        int size = deviceModelList.size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(deviceModelList.get(i).getType(), DeviceUtils.Companion.getDeviceModelFromDeviceType(deviceType).getType())) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final String getFormattedPace(int i) {
        if (i == 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 <= 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        if (i2 >= 99) {
            return "99'99''";
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d'%02d''", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    @NotNull
    public final String getFormattedPaceL(int i) {
        if (i == 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 <= 0) {
            return WorkoutConstants.EMPTY_PACE_VALUE;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%02d'%02d''", Arrays.copyOf(new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public final int getSampleRateForSessionInSecs(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return DeviceUtils.Companion.isJstyleDevice(context) ? 60 : 5;
    }

    public final double convertKMToMiles(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        String format = decimalFormat.format(Float.valueOf((i / 100000.0f) * 0.62f));
        Intrinsics.checkNotNullExpressionValue(format, "decimalFormat.format(milesValue)");
        return Double.parseDouble(format);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0071 A[LOOP:0: B:19:0x006b->B:21:0x0071, LOOP_END] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getDistanceOfSessionInMeters(@org.jetbrains.annotations.NotNull com.coveiot.android.activitymodes.models.DayInfo r6, @org.jetbrains.annotations.NotNull android.content.Context r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Float> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.coveiot.android.activitymodes.utils.WorkoutUtils.b
            if (r0 == 0) goto L13
            r0 = r8
            com.coveiot.android.activitymodes.utils.WorkoutUtils$b r0 = (com.coveiot.android.activitymodes.utils.WorkoutUtils.b) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.utils.WorkoutUtils$b r0 = new com.coveiot.android.activitymodes.utils.WorkoutUtils$b
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r6 = r0.L$1
            kotlin.jvm.internal.Ref$IntRef r6 = (kotlin.jvm.internal.Ref.IntRef) r6
            java.lang.Object r7 = r0.L$0
            kotlin.jvm.internal.Ref$FloatRef r7 = (kotlin.jvm.internal.Ref.FloatRef) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L65
        L31:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlin.jvm.internal.Ref$FloatRef r8 = new kotlin.jvm.internal.Ref$FloatRef
            r8.<init>()
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository$Companion r4 = com.coveiot.android.activitymodes.repository.WorkoutSessionRepository.Companion
            java.lang.Object r7 = r4.getInstance(r7)
            com.coveiot.android.activitymodes.repository.WorkoutSessionRepository r7 = (com.coveiot.android.activitymodes.repository.WorkoutSessionRepository) r7
            java.lang.String r6 = r6.getDate()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r0.L$0 = r8
            r0.L$1 = r2
            r0.label = r3
            java.lang.Object r6 = r7.getSessionsOfParticularDay(r6, r0)
            if (r6 != r1) goto L62
            return r1
        L62:
            r7 = r8
            r8 = r6
            r6 = r2
        L65:
            java.util.List r8 = (java.util.List) r8
            java.util.Iterator r8 = r8.iterator()
        L6b:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L81
            java.lang.Object r0 = r8.next()
            com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession r0 = (com.coveiot.android.activitymodes.database.entities.EntityWorkoutSession) r0
            int r1 = r6.element
            int r0 = r0.getTotal_distance()
            int r1 = r1 + r0
            r6.element = r1
            goto L6b
        L81:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r8 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            int r6 = r6.element
            float r6 = r8.convertCmToMeters(r6)
            r7.element = r6
            java.lang.Float r6 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.utils.WorkoutUtils.getDistanceOfSessionInMeters(com.coveiot.android.activitymodes.models.DayInfo, android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final List<WorkoutUiBean> getWorkoutUiBeans(@NotNull Context context, @NotNull ActivityShareData activityShareData, @NotNull WorkoutUiBeanProvider.ScreenType screenType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activityShareData, "activityShareData");
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        ArrayList arrayList = new ArrayList();
        WorkoutUiBeanProvider workoutUiBeanProvider = WorkoutUiBeanProvider.INSTANCE;
        Integer valueOf = Integer.valueOf(activityShareData.getDuration());
        String activityType = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType);
        WorkoutUiBean durationUiBean = workoutUiBeanProvider.getDurationUiBean(context, screenType, valueOf, activityType);
        if (durationUiBean != null) {
            arrayList.add(durationUiBean);
        }
        Integer valueOf2 = Integer.valueOf(activityShareData.getDistance());
        String activityType2 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType2);
        WorkoutUiBean distanceUiBean = workoutUiBeanProvider.getDistanceUiBean(context, screenType, valueOf2, activityType2, activityShareData.getActivityId(), activityShareData.getCategoryId(), activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (distanceUiBean != null) {
            arrayList.add(distanceUiBean);
        }
        Integer valueOf3 = Integer.valueOf(activityShareData.getHeartRate());
        Integer valueOf4 = Integer.valueOf(activityShareData.getMinHeartRate());
        Integer valueOf5 = Integer.valueOf(activityShareData.getMaxHeartRate());
        String activityType3 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType3);
        WorkoutUiBean heartRateUiBean = workoutUiBeanProvider.getHeartRateUiBean(context, screenType, valueOf3, valueOf4, valueOf5, activityType3, activityShareData.getActivityId(), activityShareData.getCategoryId());
        if (heartRateUiBean != null) {
            arrayList.add(heartRateUiBean);
        }
        Integer valueOf6 = Integer.valueOf(activityShareData.getSteps());
        String activityType4 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType4);
        WorkoutUiBean stepsUiBean = workoutUiBeanProvider.getStepsUiBean(context, screenType, valueOf6, activityType4, activityShareData.getActivityId(), activityShareData.getCategoryId());
        if (stepsUiBean != null) {
            arrayList.add(stepsUiBean);
        }
        Float valueOf7 = Float.valueOf(activityShareData.getCal());
        String activityType5 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType5);
        WorkoutUiBean calorieUiBean = workoutUiBeanProvider.getCalorieUiBean(context, screenType, valueOf7, activityType5);
        if (calorieUiBean != null) {
            arrayList.add(calorieUiBean);
        }
        Float valueOf8 = Float.valueOf(activityShareData.getPace());
        int distance = activityShareData.getDistance();
        int duration = activityShareData.getDuration();
        String activityType6 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType6);
        WorkoutUiBean paceUiBean = workoutUiBeanProvider.getPaceUiBean(context, screenType, valueOf8, distance, duration, activityType6, activityShareData.getActivityId(), activityShareData.getCategoryId(), Integer.valueOf(activityShareData.getAvgPace()), activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (paceUiBean != null) {
            arrayList.add(paceUiBean);
        }
        Float valueOf9 = Float.valueOf(activityShareData.getMaxPace());
        String activityType7 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType7);
        WorkoutUiBean fastestPaceUiBean = workoutUiBeanProvider.getFastestPaceUiBean(context, screenType, valueOf9, activityType7, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (fastestPaceUiBean != null) {
            arrayList.add(fastestPaceUiBean);
        }
        Float valueOf10 = Float.valueOf(activityShareData.getMinPace());
        String activityType8 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType8);
        WorkoutUiBean lowestPaceUiBean = workoutUiBeanProvider.getLowestPaceUiBean(context, screenType, valueOf10, activityType8, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (lowestPaceUiBean != null) {
            arrayList.add(lowestPaceUiBean);
        }
        Integer valueOf11 = Integer.valueOf(activityShareData.getSteps());
        Integer valueOf12 = Integer.valueOf(activityShareData.getDuration());
        String activityType9 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType9);
        activityShareData.getAvgStepFreq();
        WorkoutUiBean sPMUiBean = workoutUiBeanProvider.getSPMUiBean(context, screenType, valueOf11, valueOf12, activityType9, Integer.valueOf(activityShareData.getAvgStepFreq()));
        if (sPMUiBean != null) {
            arrayList.add(sPMUiBean);
        }
        Integer valueOf13 = Integer.valueOf(activityShareData.getMaxSPM());
        String activityType10 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType10);
        WorkoutUiBean maxSPMUiBean = workoutUiBeanProvider.getMaxSPMUiBean(context, screenType, valueOf13, activityType10, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (maxSPMUiBean != null) {
            arrayList.add(maxSPMUiBean);
        }
        Integer valueOf14 = Integer.valueOf(activityShareData.getAvgStrideLength());
        String activityType11 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType11);
        WorkoutUiBean avgStrideLengthUiBean = workoutUiBeanProvider.getAvgStrideLengthUiBean(context, screenType, valueOf14, activityType11, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (avgStrideLengthUiBean != null) {
            arrayList.add(avgStrideLengthUiBean);
        }
        Float valueOf15 = Float.valueOf(activityShareData.getAvgSpeed());
        String activityType12 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType12);
        WorkoutUiBean avgSPeedUiBean = workoutUiBeanProvider.getAvgSPeedUiBean(context, screenType, valueOf15, activityType12, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (avgSPeedUiBean != null) {
            arrayList.add(avgSPeedUiBean);
        }
        String swimStroke = activityShareData.getSwimStroke() != null ? activityShareData.getSwimStroke() : null;
        String activityType13 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType13);
        WorkoutUiBean swimStrokeUiBean = workoutUiBeanProvider.getSwimStrokeUiBean(context, screenType, swimStroke, activityType13, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (swimStrokeUiBean != null) {
            arrayList.add(swimStrokeUiBean);
        }
        activityShareData.getTotalStrokes();
        Integer valueOf16 = Integer.valueOf(activityShareData.getTotalStrokes());
        String activityType14 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType14);
        WorkoutUiBean totalStrokeUiBean = workoutUiBeanProvider.getTotalStrokeUiBean(context, screenType, valueOf16, activityType14, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (totalStrokeUiBean != null) {
            arrayList.add(totalStrokeUiBean);
        }
        activityShareData.getTotalLaps();
        Integer valueOf17 = Integer.valueOf(activityShareData.getTotalLaps());
        String activityType15 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType15);
        WorkoutUiBean lapsUiBean = workoutUiBeanProvider.getLapsUiBean(context, screenType, valueOf17, activityType15, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (lapsUiBean != null) {
            arrayList.add(lapsUiBean);
        }
        activityShareData.getAvgSwolf();
        Integer valueOf18 = Integer.valueOf(activityShareData.getAvgSwolf());
        String activityType16 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType16);
        WorkoutUiBean avgSwolfUiBean = workoutUiBeanProvider.getAvgSwolfUiBean(context, screenType, valueOf18, activityType16, activityShareData.isIndoor() ? "INDOOR" : "OUTDOOR");
        if (avgSwolfUiBean != null) {
            arrayList.add(avgSwolfUiBean);
        }
        activityShareData.getTotalStrokes();
        Integer valueOf19 = Integer.valueOf(activityShareData.getTotalStrokes());
        String activityType17 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType17);
        WorkoutUiBean strokesOrCounterUiBean = workoutUiBeanProvider.getStrokesOrCounterUiBean(context, screenType, valueOf19, activityType17);
        if (strokesOrCounterUiBean != null) {
            arrayList.add(strokesOrCounterUiBean);
        }
        activityShareData.getAvgStrokeFreq();
        Integer valueOf20 = Integer.valueOf(activityShareData.getAvgStrokeFreq());
        String activityType18 = activityShareData.getActivityType();
        Intrinsics.checkNotNull(activityType18);
        WorkoutUiBean strokesOrCounterFrequencyUiBean = workoutUiBeanProvider.getStrokesOrCounterFrequencyUiBean(context, screenType, valueOf20, activityType18);
        if (strokesOrCounterFrequencyUiBean != null) {
            arrayList.add(strokesOrCounterFrequencyUiBean);
        }
        return arrayList;
    }

    @NotNull
    public final String getActivityModeNames(@NotNull String activityMode, @NotNull Context context) {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        Intrinsics.checkNotNullParameter(activityMode, "activityMode");
        Intrinsics.checkNotNullParameter(context, "context");
        String upperCase = activityMode.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        switch (upperCase.hashCode()) {
            case -2119946949:
                if (upperCase.equals("TREKKING")) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string7 = context.getString(R.string.travel_by_walking);
                        Intrinsics.checkNotNullExpressionValue(string7, "context.getString(\n     …_by_walking\n            )");
                        return string7;
                    }
                    String string8 = context.getString(R.string.trekking);
                    Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.string.trekking)");
                    return string8;
                }
                break;
            case -2085244106:
                if (upperCase.equals("KARATE")) {
                    String string9 = context.getString(R.string.karate_title_case);
                    Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.string.karate_title_case)");
                    return string9;
                }
                break;
            case -2051813763:
                if (upperCase.equals("WORKOUT")) {
                    DeviceUtils.Companion companion = DeviceUtils.Companion;
                    if (!companion.isEastApexDevice(context) && !companion.isRuggedDevice(context)) {
                        if (companion.isSmaDevice(context)) {
                            String string10 = context.getString(R.string.body_building);
                            Intrinsics.checkNotNullExpressionValue(string10, "{\n                contex…y_building)\n            }");
                            return string10;
                        }
                        String string11 = context.getString(R.string.workout_title_case);
                        Intrinsics.checkNotNullExpressionValue(string11, "{\n                contex…title_case)\n            }");
                        return string11;
                    }
                    String string12 = context.getString(R.string.physical_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string12, "{\n                contex…title_case)\n            }");
                    return string12;
                }
                break;
            case -2029197648:
                if (upperCase.equals("OFF_ROAD_BIKE")) {
                    String string13 = context.getString(R.string.off_road_bike);
                    Intrinsics.checkNotNullExpressionValue(string13, "context.getString(R.string.off_road_bike)");
                    return string13;
                }
                break;
            case -1988544356:
                if (upperCase.equals("FOLK_DANCE")) {
                    String string14 = context.getString(R.string.folk_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string14, "context.getString(R.string.folk_dance_title_case)");
                    return string14;
                }
                break;
            case -1965056527:
                if (upperCase.equals("SQUARE_DANCE")) {
                    String string15 = context.getString(R.string.square_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string15, "context.getString(R.stri….square_dance_title_case)");
                    return string15;
                }
                break;
            case -1931774946:
                if (upperCase.equals("PULL_UPS")) {
                    String string16 = context.getString(R.string.pull_up_title_case);
                    Intrinsics.checkNotNullExpressionValue(string16, "context.getString(R.string.pull_up_title_case)");
                    return string16;
                }
                break;
            case -1924979037:
                if (upperCase.equals("MOTOCROSS")) {
                    String string17 = context.getString(R.string.motocross);
                    Intrinsics.checkNotNullExpressionValue(string17, "context.getString(R.string.motocross)");
                    return string17;
                }
                break;
            case -1885279410:
                if (upperCase.equals("RACING")) {
                    String string18 = context.getString(R.string.racing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string18, "context.getString(R.string.racing_title_case)");
                    return string18;
                }
                break;
            case -1876672755:
                if (upperCase.equals("DOWNHILL_SKIING")) {
                    String string19 = context.getString(R.string.downhall_skiing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string19, "context.getString(R.stri…wnhall_skiing_title_case)");
                    return string19;
                }
                break;
            case -1871754296:
                if (upperCase.equals("ROWING")) {
                    DeviceUtils.Companion companion2 = DeviceUtils.Companion;
                    if (companion2.isTouchELXDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO) {
                        String string20 = context.getString(R.string.rower_title_case);
                        Intrinsics.checkNotNullExpressionValue(string20, "{\n                contex…title_case)\n            }");
                        return string20;
                    } else if (companion2.isSmaDevice(context)) {
                        String string21 = context.getString(R.string.rowing_title_case);
                        Intrinsics.checkNotNullExpressionValue(string21, "{\n                contex…title_case)\n            }");
                        return string21;
                    } else {
                        String string22 = context.getString(R.string.rowing_machine_title_case);
                        Intrinsics.checkNotNullExpressionValue(string22, "{\n                contex…title_case)\n            }");
                        return string22;
                    }
                }
                break;
            case -1865330601:
                if (upperCase.equals("STREET_DANCE")) {
                    DeviceUtils.Companion companion3 = DeviceUtils.Companion;
                    if (!companion3.isEastApexDevice(context) && !companion3.isSmaDevice(context)) {
                        String string23 = context.getString(R.string.street_dance_title_case);
                        Intrinsics.checkNotNullExpressionValue(string23, "{\n                contex…title_case)\n            }");
                        return string23;
                    }
                    String string24 = context.getString(R.string.hip_hop_title_case);
                    Intrinsics.checkNotNullExpressionValue(string24, "{\n                contex…title_case)\n            }");
                    return string24;
                }
                break;
            case -1857624411:
                if (upperCase.equals("PING_PONG")) {
                    DeviceUtils.Companion companion4 = DeviceUtils.Companion;
                    if (!companion4.isEastApexDevice(context) && !companion4.isSmaDevice(context)) {
                        String string25 = context.getString(R.string.table_tennis_title_case);
                        Intrinsics.checkNotNullExpressionValue(string25, "{\n                contex…title_case)\n            }");
                        return string25;
                    }
                    String string26 = context.getString(R.string.pingpong);
                    Intrinsics.checkNotNullExpressionValue(string26, "{\n                contex…g.pingpong)\n            }");
                    return string26;
                }
                break;
            case -1841345217:
                if (upperCase.equals("SQUASH")) {
                    String string27 = context.getString(R.string.squash_title_case);
                    Intrinsics.checkNotNullExpressionValue(string27, "context.getString(R.string.squash_title_case)");
                    return string27;
                }
                break;
            case -1827940748:
                if (upperCase.equals("TAE_BO")) {
                    String string28 = context.getString(R.string.tae_bo);
                    Intrinsics.checkNotNullExpressionValue(string28, "context.getString(R.string.tae_bo)");
                    return string28;
                }
                break;
            case -1823994661:
                if (upperCase.equals("TENNIS")) {
                    String string29 = context.getString(R.string.tennis_title_case);
                    Intrinsics.checkNotNullExpressionValue(string29, "context.getString(R.string.tennis_title_case)");
                    return string29;
                }
                break;
            case -1821117128:
                if (upperCase.equals("THRASH")) {
                    String string30 = context.getString(R.string.thrash_title_case);
                    Intrinsics.checkNotNullExpressionValue(string30, "context.getString(R.string.thrash_title_case)");
                    return string30;
                }
                break;
            case -1819017718:
                if (upperCase.equals("WATER_POLO")) {
                    String string31 = context.getString(R.string.water_polo_title_case);
                    Intrinsics.checkNotNullExpressionValue(string31, "context.getString(R.string.water_polo_title_case)");
                    return string31;
                }
                break;
            case -1758336501:
                if (upperCase.equals("VO2MAX")) {
                    String string32 = context.getString(R.string.vo2max_test_title_case);
                    Intrinsics.checkNotNullExpressionValue(string32, "context.getString(R.string.vo2max_test_title_case)");
                    return string32;
                }
                break;
            case -1756288777:
                if (upperCase.equals("ICE_HOCKEY")) {
                    String string33 = context.getString(R.string.ice_hockey_title_case);
                    Intrinsics.checkNotNullExpressionValue(string33, "context.getString(R.string.ice_hockey_title_case)");
                    return string33;
                }
                break;
            case -1735064973:
                if (upperCase.equals("PUSH_UPS")) {
                    String string34 = context.getString(R.string.push_ups_title_case);
                    Intrinsics.checkNotNullExpressionValue(string34, "context.getString(R.string.push_ups_title_case)");
                    return string34;
                }
                break;
            case -1727133327:
                if (upperCase.equals("SAILBOATING")) {
                    DeviceUtils.Companion companion5 = DeviceUtils.Companion;
                    if (!companion5.isEastApexDevice(context) && !companion5.isSmaDevice(context)) {
                        String string35 = context.getString(R.string.sailboating_title_case);
                        Intrinsics.checkNotNullExpressionValue(string35, "{\n                contex…title_case)\n            }");
                        return string35;
                    }
                    String string36 = context.getString(R.string.sailboat);
                    Intrinsics.checkNotNullExpressionValue(string36, "{\n                contex…g.sailboat)\n            }");
                    return string36;
                }
                break;
            case -1724943759:
                if (upperCase.equals("MARTIAL_ARTS")) {
                    String string37 = context.getString(R.string.martial_arts_title_case);
                    Intrinsics.checkNotNullExpressionValue(string37, "context.getString(R.stri….martial_arts_title_case)");
                    return string37;
                }
                break;
            case -1653058095:
                if (upperCase.equals("SCOOTER")) {
                    String string38 = context.getString(R.string.scooter);
                    Intrinsics.checkNotNullExpressionValue(string38, "context.getString(R.string.scooter)");
                    return string38;
                }
                break;
            case -1627265520:
                if (upperCase.equals("HORSE_RACING")) {
                    DeviceUtils.Companion companion6 = DeviceUtils.Companion;
                    if (companion6.isSmaDevice(context)) {
                        if (companion6.isSmaRoundJieieDevice(context)) {
                            string = context.getString(R.string.race);
                        } else {
                            string = context.getString(R.string.racing);
                        }
                        Intrinsics.checkNotNullExpressionValue(string, "{\n                if (De…          }\n            }");
                        return string;
                    }
                    String string39 = context.getString(R.string.racing);
                    Intrinsics.checkNotNullExpressionValue(string39, "{\n                contex…ing.racing)\n            }");
                    return string39;
                }
                break;
            case -1619847561:
                if (upperCase.equals("HORSE_RIDING")) {
                    String string40 = context.getString(R.string.horse_riding_title_case);
                    Intrinsics.checkNotNullExpressionValue(string40, "context.getString(R.stri….horse_riding_title_case)");
                    return string40;
                }
                break;
            case -1586487546:
                if (upperCase.equals("TREADMILL")) {
                    String string41 = context.getString(R.string.treadmill_title_case);
                    Intrinsics.checkNotNullExpressionValue(string41, "context.getString(R.string.treadmill_title_case)");
                    return string41;
                }
                break;
            case -1476187625:
                if (upperCase.equals("SIT_UPS")) {
                    String string42 = context.getString(R.string.sit_ups_title_case);
                    Intrinsics.checkNotNullExpressionValue(string42, "context.getString(R.string.sit_ups_title_case)");
                    return string42;
                }
                break;
            case -1436815529:
                if (upperCase.equals("SKATING")) {
                    String string43 = context.getString(R.string.skating_title_case);
                    Intrinsics.checkNotNullExpressionValue(string43, "context.getString(R.string.skating_title_case)");
                    return string43;
                }
                break;
            case -1435714371:
                if (upperCase.equals("KITESURFING")) {
                    String string44 = context.getString(R.string.kitesurfing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string44, "context.getString(R.string.kitesurfing_title_case)");
                    return string44;
                }
                break;
            case -1418841751:
                if (upperCase.equals("DISC_SPORTS")) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string45 = context.getString(R.string.disc_throw_title_case);
                        Intrinsics.checkNotNullExpressionValue(string45, "{\n                contex…title_case)\n            }");
                        return string45;
                    }
                    String string46 = context.getString(R.string.disc_sports_title_case);
                    Intrinsics.checkNotNullExpressionValue(string46, "{\n                contex…title_case)\n            }");
                    return string46;
                }
                break;
            case -1366065295:
                if (upperCase.equals("SKIPPING")) {
                    DeviceUtils.Companion companion7 = DeviceUtils.Companion;
                    if (!companion7.isTouchELXDevice(context) && !companion7.isSmaDevice(context)) {
                        if (companion7.isEastApexDevice(context)) {
                            String string47 = context.getString(R.string.skip_title_case);
                            Intrinsics.checkNotNullExpressionValue(string47, "{\n                contex…title_case)\n            }");
                            return string47;
                        }
                        String string48 = context.getString(R.string.skipping_title_case);
                        Intrinsics.checkNotNullExpressionValue(string48, "{\n                contex…title_case)\n            }");
                        return string48;
                    }
                    String string49 = context.getString(R.string.jump_rope_title_case);
                    Intrinsics.checkNotNullExpressionValue(string49, "{\n                contex…title_case)\n            }");
                    return string49;
                }
                break;
            case -1350986735:
                if (upperCase.equals("LONG_JUMP")) {
                    String string50 = context.getString(R.string.long_jump_title_case);
                    Intrinsics.checkNotNullExpressionValue(string50, "context.getString(R.string.long_jump_title_case)");
                    return string50;
                }
                break;
            case -1338056716:
                if (upperCase.equals("SNORKEL")) {
                    String string51 = context.getString(R.string.snorkel_title_case);
                    Intrinsics.checkNotNullExpressionValue(string51, "context.getString(R.string.snorkel_title_case)");
                    return string51;
                }
                break;
            case -1324213922:
                if (upperCase.equals("MEDITATION")) {
                    DeviceUtils.Companion companion8 = DeviceUtils.Companion;
                    if (!companion8.isTouchELXDevice(context) && !companion8.isRuggedDevice(context)) {
                        String string52 = context.getString(R.string.meditation_title_case);
                        Intrinsics.checkNotNullExpressionValue(string52, "{\n                contex…title_case)\n            }");
                        return string52;
                    }
                    String string53 = context.getString(R.string.mind_body_title_case);
                    Intrinsics.checkNotNullExpressionValue(string53, "{\n                contex…title_case)\n            }");
                    return string53;
                }
                break;
            case -1320491334:
                if (upperCase.equals("GROUP_TRAINING")) {
                    String string54 = context.getString(R.string.group_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string54, "context.getString(R.stri…roup_training_title_case)");
                    return string54;
                }
                break;
            case -1308777704:
                if (upperCase.equals("STRENGTH_TRAINING")) {
                    String string55 = context.getString(R.string.strength_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string55, "context.getString(R.stri…ngth_training_title_case)");
                    return string55;
                }
                break;
            case -1178303321:
                if (upperCase.equals("ROCK_CLIMBING")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string56 = context.getString(R.string.rock_climb_title_case);
                        Intrinsics.checkNotNullExpressionValue(string56, "{\n                contex…title_case)\n            }");
                        return string56;
                    }
                    String string57 = context.getString(R.string.rock_climbing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string57, "{\n                contex…title_case)\n            }");
                    return string57;
                }
                break;
            case -1175571791:
                if (upperCase.equals("STEPPER")) {
                    DeviceUtils.Companion companion9 = DeviceUtils.Companion;
                    if (companion9.isEastApexDevice(context)) {
                        String string58 = context.getString(R.string.stepper_title_case_);
                        Intrinsics.checkNotNullExpressionValue(string58, "{\n                contex…itle_case_)\n            }");
                        return string58;
                    } else if (companion9.isSmaDevice(context)) {
                        if (companion9.isSmaRoundJieieDevice(context)) {
                            string2 = context.getString(R.string.step);
                        } else {
                            string2 = context.getString(R.string.steps);
                        }
                        Intrinsics.checkNotNullExpressionValue(string2, "{\n                if(Dev…          }\n            }");
                        return string2;
                    } else {
                        String string59 = context.getString(R.string.stepper_title_case);
                        Intrinsics.checkNotNullExpressionValue(string59, "{\n                contex…title_case)\n            }");
                        return string59;
                    }
                }
                break;
            case -1135885862:
                if (upperCase.equals("LACROSSE")) {
                    String string60 = context.getString(R.string.lacrosse_title_case);
                    Intrinsics.checkNotNullExpressionValue(string60, "context.getString(R.string.lacrosse_title_case)");
                    return string60;
                }
                break;
            case -1135241236:
                if (upperCase.equals("SURFING")) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string61 = context.getString(R.string.surf_title_case);
                        Intrinsics.checkNotNullExpressionValue(string61, "{\n                contex…title_case)\n            }");
                        return string61;
                    }
                    String string62 = context.getString(R.string.surfing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string62, "{\n                contex…title_case)\n            }");
                    return string62;
                }
                break;
            case -1101755765:
                if (upperCase.equals("JAVELIN")) {
                    DeviceUtils.Companion companion10 = DeviceUtils.Companion;
                    if (companion10.isSmaDevice(context)) {
                        if (companion10.isSmaRoundJieieDevice(context)) {
                            string3 = context.getString(R.string.javelin_title_case);
                        } else {
                            string3 = context.getString(R.string.javelin_throw_title_case);
                        }
                        Intrinsics.checkNotNullExpressionValue(string3, "{\n                if (De…          }\n            }");
                        return string3;
                    }
                    String string63 = context.getString(R.string.javelin_title_case);
                    Intrinsics.checkNotNullExpressionValue(string63, "{\n                contex…title_case)\n            }");
                    return string63;
                }
                break;
            case -1086191652:
                if (upperCase.equals("FUNCTIONAL_STRENGTH_TRAINING")) {
                    DeviceUtils.Companion companion11 = DeviceUtils.Companion;
                    if (!companion11.isEastApexDevice(context) && !companion11.isRuggedDevice(context)) {
                        String string64 = context.getString(R.string.function_strength_title_case);
                        Intrinsics.checkNotNullExpressionValue(string64, "{\n                contex…title_case)\n            }");
                        return string64;
                    }
                    String string65 = context.getString(R.string.functioonal_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string65, "{\n                contex…title_case)\n            }");
                    return string65;
                }
                break;
            case -1025285387:
                if (upperCase.equals("CLIMBING_MACHINE")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string66 = context.getString(R.string.stair_machine_title_case);
                        Intrinsics.checkNotNullExpressionValue(string66, "{\n                contex…title_case)\n            }");
                        return string66;
                    }
                    String string67 = context.getString(R.string.climbing_machine_title_case);
                    Intrinsics.checkNotNullExpressionValue(string67, "{\n                contex…title_case)\n            }");
                    return string67;
                }
                break;
            case -938464176:
                if (upperCase.equals("BASEBALL")) {
                    String string68 = context.getString(R.string.baseball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string68, "context.getString(R.string.baseball_title_case)");
                    return string68;
                }
                break;
            case -921034701:
                if (upperCase.equals("HAND_CYCLING")) {
                    String string69 = context.getString(R.string.hand_cycling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string69, "context.getString(R.stri….hand_cycling_title_case)");
                    return string69;
                }
                break;
            case -845589618:
                if (upperCase.equals("TAEKWONDO")) {
                    String string70 = context.getString(R.string.taekwondo_title_case);
                    Intrinsics.checkNotNullExpressionValue(string70, "context.getString(R.string.taekwondo_title_case)");
                    return string70;
                }
                break;
            case -840498965:
                if (upperCase.equals("AIR_WALKER")) {
                    String string71 = context.getString(R.string.air_walker_title_case);
                    Intrinsics.checkNotNullExpressionValue(string71, "context.getString(R.string.air_walker_title_case)");
                    return string71;
                }
                break;
            case -827893439:
                if (upperCase.equals("TAI_CHI")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string72 = context.getString(R.string.taichi_title_case);
                        Intrinsics.checkNotNullExpressionValue(string72, "{\n                contex…title_case)\n            }");
                        return string72;
                    }
                    String string73 = context.getString(R.string.tai_chi_title_case);
                    Intrinsics.checkNotNullExpressionValue(string73, "{\n                contex…title_case)\n            }");
                    return string73;
                }
                break;
            case -747323815:
                if (upperCase.equals("PICKLEBALL")) {
                    String string74 = context.getString(R.string.pickleball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string74, "context.getString(R.string.pickleball_title_case)");
                    return string74;
                }
                break;
            case -716921478:
                if (upperCase.equals("CORE_TRAINING")) {
                    String string75 = context.getString(R.string.core_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string75, "context.getString(R.stri…core_training_title_case)");
                    return string75;
                }
                break;
            case -688766900:
                if (upperCase.equals("POLE_DANCE")) {
                    String string76 = context.getString(R.string.pole_dancing);
                    Intrinsics.checkNotNullExpressionValue(string76, "context.getString(R.string.pole_dancing)");
                    return string76;
                }
                break;
            case -675235860:
                if (upperCase.equals("VOLLEYBALL")) {
                    String string77 = context.getString(R.string.volleyball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string77, "context.getString(R.string.volleyball_title_case)");
                    return string77;
                }
                break;
            case -650754568:
                if (upperCase.equals("PARALLEL_BARS")) {
                    String string78 = context.getString(R.string.parallel_bars_title_case);
                    Intrinsics.checkNotNullExpressionValue(string78, "context.getString(R.stri…parallel_bars_title_case)");
                    return string78;
                }
                break;
            case -604568430:
                if (upperCase.equals("TRAIL_RUN")) {
                    String string79 = context.getString(R.string.trail_run_title_case);
                    Intrinsics.checkNotNullExpressionValue(string79, "context.getString(R.string.trail_run_title_case)");
                    return string79;
                }
                break;
            case -604517962:
                if (upperCase.equals("SLEDDING")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string80 = context.getString(R.string.sled_title_case);
                        Intrinsics.checkNotNullExpressionValue(string80, "{\n                contex…title_case)\n            }");
                        return string80;
                    }
                    String string81 = context.getString(R.string.sledding_title_case);
                    Intrinsics.checkNotNullExpressionValue(string81, "{\n                contex…title_case)\n            }");
                    return string81;
                }
                break;
            case -588861927:
                if (upperCase.equals("CROSS_TRAINING")) {
                    String string82 = context.getString(R.string.cross_training__title_case);
                    Intrinsics.checkNotNullExpressionValue(string82, "context.getString(R.stri…oss_training__title_case)");
                    return string82;
                }
                break;
            case -570056757:
                if (upperCase.equals("SMITH_MACHINE")) {
                    String string83 = context.getString(R.string.smith_machine_title_case);
                    Intrinsics.checkNotNullExpressionValue(string83, "context.getString(R.stri…smith_machine_title_case)");
                    return string83;
                }
                break;
            case -538941143:
                if (upperCase.equals("JUMPING_JACKS")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string84 = context.getString(R.string.jumping_jack_title_case);
                        Intrinsics.checkNotNullExpressionValue(string84, "{\n                contex…title_case)\n            }");
                        return string84;
                    }
                    String string85 = context.getString(R.string.jumping_jacks_title_case);
                    Intrinsics.checkNotNullExpressionValue(string85, "{\n                contex…title_case)\n            }");
                    return string85;
                }
                break;
            case -249737074:
                if (upperCase.equals("FENCING")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string86 = context.getString(R.string.fence_title_case);
                        Intrinsics.checkNotNullExpressionValue(string86, "{\n                contex…title_case)\n            }");
                        return string86;
                    }
                    String string87 = context.getString(R.string.fencing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string87, "{\n                contex…title_case)\n            }");
                    return string87;
                }
                break;
            case -247332891:
                if (upperCase.equals("SNOWMOBILE")) {
                    String string88 = context.getString(R.string.snowmobile_title_case);
                    Intrinsics.checkNotNullExpressionValue(string88, "context.getString(R.string.snowmobile_title_case)");
                    return string88;
                }
                break;
            case -232849516:
                if (upperCase.equals("KABADDI")) {
                    String string89 = context.getString(R.string.kabaddi_title_case);
                    Intrinsics.checkNotNullExpressionValue(string89, "context.getString(R.string.kabaddi_title_case)");
                    return string89;
                }
                break;
            case -217502038:
                if (upperCase.equals("KARTING")) {
                    String string90 = context.getString(R.string.karting_title_case);
                    Intrinsics.checkNotNullExpressionValue(string90, "context.getString(R.string.karting_title_case)");
                    return string90;
                }
                break;
            case -174357827:
                if (upperCase.equals("STRETCHING")) {
                    String string91 = context.getString(R.string.stretch_title_case);
                    Intrinsics.checkNotNullExpressionValue(string91, "context.getString(R.string.stretch_title_case)");
                    return string91;
                }
                break;
            case -154556997:
                if (upperCase.equals("SNOW_SPORTS")) {
                    String string92 = context.getString(R.string.snow_sports_title_case);
                    Intrinsics.checkNotNullExpressionValue(string92, "context.getString(R.string.snow_sports_title_case)");
                    return string92;
                }
                break;
            case -143182912:
                if (upperCase.equals("JAI_ALAI")) {
                    String string93 = context.getString(R.string.jai_ball);
                    Intrinsics.checkNotNullExpressionValue(string93, "context.getString(R.string.jai_ball)");
                    return string93;
                }
                break;
            case -130453910:
                if (upperCase.equals("FISHING")) {
                    String string94 = context.getString(R.string.fishing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string94, "context.getString(R.string.fishing_title_case)");
                    return string94;
                }
                break;
            case -109719726:
                if (upperCase.equals("CROSS_FIT")) {
                    DeviceUtils.Companion companion12 = DeviceUtils.Companion;
                    if (!companion12.isTouchLunarPlusDevice(context) && !companion12.isEastApexDevice(context)) {
                        if (companion12.isSmaDevice(context)) {
                            String string95 = context.getString(R.string.crossfit_cap_case);
                            Intrinsics.checkNotNullExpressionValue(string95, "{\n                contex…t_cap_case)\n            }");
                            return string95;
                        }
                        String string96 = context.getString(R.string.cross_fit_title_case);
                        Intrinsics.checkNotNullExpressionValue(string96, "{\n                contex…title_case)\n            }");
                        return string96;
                    }
                    String string97 = context.getString(R.string.cross_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string97, "{\n                contex…title_case)\n            }");
                    return string97;
                }
                break;
            case -97787361:
                if (upperCase.equals("AEROBICS_GYMS")) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string98 = context.getString(R.string.aerobics_exercise_title_case);
                        Intrinsics.checkNotNullExpressionValue(string98, "{\n                contex…title_case)\n            }");
                        return string98;
                    }
                    String string99 = context.getString(R.string.aerobics_gyms_title_case);
                    Intrinsics.checkNotNullExpressionValue(string99, "{\n                contex…title_case)\n            }");
                    return string99;
                }
                break;
            case -85624266:
                if (upperCase.equals("DODGEBALL")) {
                    String string100 = context.getString(R.string.dodgeball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string100, "context.getString(R.string.dodgeball_title_case)");
                    return string100;
                }
                break;
            case -75213054:
                if (upperCase.equals("PARKOUR")) {
                    String string101 = context.getString(R.string.parkour_title_case);
                    Intrinsics.checkNotNullExpressionValue(string101, "context.getString(R.string.parkour_title_case)");
                    return string101;
                }
                break;
            case -61197506:
                if (upperCase.equals("TENNIS_DOUBLES")) {
                    String string102 = context.getString(R.string.tennis_doubles);
                    Intrinsics.checkNotNullExpressionValue(string102, "context.getString(R.string.tennis_doubles)");
                    return string102;
                }
                break;
            case -54631934:
                if (upperCase.equals("ROLLER_SKATING")) {
                    String string103 = context.getString(R.string.roller_skating_title_case);
                    Intrinsics.checkNotNullExpressionValue(string103, "context.getString(R.stri…oller_skating_title_case)");
                    return string103;
                }
                break;
            case -41149443:
                if (upperCase.equals("KICKBOXING")) {
                    String string104 = context.getString(R.string.kickboxing);
                    Intrinsics.checkNotNullExpressionValue(string104, "context.getString(R.string.kickboxing)");
                    return string104;
                }
                break;
            case -30122698:
                if (upperCase.equals("ARCHERY")) {
                    String string105 = context.getString(R.string.archery_title_case);
                    Intrinsics.checkNotNullExpressionValue(string105, "context.getString(R.string.archery_title_case)");
                    return string105;
                }
                break;
            case 65155:
                if (upperCase.equals("ATV")) {
                    String string106 = context.getString(R.string.atv_title_case);
                    Intrinsics.checkNotNullExpressionValue(string106, "context.getString(R.string.atv_title_case)");
                    return string106;
                }
                break;
            case 81515:
                if (upperCase.equals(CoveApiConstants.RUN)) {
                    DeviceUtils.Companion companion13 = DeviceUtils.Companion;
                    if (!companion13.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO && !companion13.isSmaRoundJieieDevice(context)) {
                        String string107 = context.getString(R.string.running_title_case);
                        Intrinsics.checkNotNullExpressionValue(string107, "{\n                contex…title_case)\n            }");
                        return string107;
                    }
                    String string108 = context.getString(R.string.run);
                    Intrinsics.checkNotNullExpressionValue(string108, "{\n                contex…string.run)\n            }");
                    return string108;
                }
                break;
            case 82161:
                if (upperCase.equals("SKI")) {
                    DeviceUtils.Companion companion14 = DeviceUtils.Companion;
                    if (companion14.isEastApexDevice(context)) {
                        String string109 = context.getString(R.string.skis_title_case);
                        Intrinsics.checkNotNullExpressionValue(string109, "{\n                contex…title_case)\n            }");
                        return string109;
                    } else if (companion14.isRuggedDevice(context)) {
                        String string110 = context.getString(R.string.skiing);
                        Intrinsics.checkNotNullExpressionValue(string110, "{\n                contex…ing.skiing)\n            }");
                        return string110;
                    } else if (companion14.isSmaDevice(context)) {
                        String string111 = context.getString(R.string.ski_title_case);
                        Intrinsics.checkNotNullExpressionValue(string111, "{\n                contex…title_case)\n            }");
                        return string111;
                    } else {
                        String string112 = context.getString(R.string.skating_title_case);
                        Intrinsics.checkNotNullExpressionValue(string112, "{\n                contex…title_case)\n            }");
                        return string112;
                    }
                }
                break;
            case 82478:
                if (upperCase.equals("SUP")) {
                    String string113 = context.getString(R.string.sup_title_case);
                    Intrinsics.checkNotNullExpressionValue(string113, "context.getString(R.string.sup_title_case)");
                    return string113;
                }
                break;
            case 83354:
                if (upperCase.equals("TRX")) {
                    String string114 = context.getString(R.string.trx);
                    Intrinsics.checkNotNullExpressionValue(string114, "context.getString(R.string.trx)");
                    return string114;
                }
                break;
            case 2193506:
                if (upperCase.equals("GOLF")) {
                    String string115 = context.getString(R.string.golf_title_case);
                    Intrinsics.checkNotNullExpressionValue(string115, "context.getString(R.string.golf_title_case)");
                    return string115;
                }
                break;
            case 2217452:
                if (upperCase.equals("HIIT")) {
                    String string116 = context.getString(R.string.hiit_title_case);
                    Intrinsics.checkNotNullExpressionValue(string116, "context.getString(R.string.hiit_title_case)");
                    return string116;
                }
                break;
            case 2288406:
                if (upperCase.equals("JUDO")) {
                    String string117 = context.getString(R.string.judo_title_case);
                    Intrinsics.checkNotNullExpressionValue(string117, "context.getString(R.string.judo_title_case)");
                    return string117;
                }
                break;
            case 2307151:
                if (upperCase.equals("KITE")) {
                    String string118 = context.getString(R.string.kite_title_case);
                    Intrinsics.checkNotNullExpressionValue(string118, "context.getString(R.string.kite_title_case)");
                    return string118;
                }
                break;
            case 2467117:
                if (upperCase.equals("PUCK")) {
                    String string119 = context.getString(R.string.puck_title_case);
                    Intrinsics.checkNotNullExpressionValue(string119, "context.getString(R.string.puck_title_case)");
                    return string119;
                }
                break;
            case 2507593:
                if (upperCase.equals("RAGA")) {
                    String string120 = context.getString(R.string.raga_title_case);
                    Intrinsics.checkNotNullExpressionValue(string120, "context.getString(R.string.raga_title_case)");
                    return string120;
                }
                break;
            case 2558600:
                if (upperCase.equals(CoveApiConstants.SWIM)) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string121 = context.getString(R.string.swim1_title_case);
                        Intrinsics.checkNotNullExpressionValue(string121, "{\n                contex…title_case)\n            }");
                        return string121;
                    }
                    String string122 = context.getString(R.string.swim_title_case);
                    Intrinsics.checkNotNullExpressionValue(string122, "{\n                contex…title_case)\n            }");
                    return string122;
                }
                break;
            case 2656713:
                if (upperCase.equals("WALK")) {
                    DeviceUtils.Companion companion15 = DeviceUtils.Companion;
                    if (!companion15.isRuggedDevice(context) && BleApiManager.getInstance(context).getDeviceType() != DeviceType.TOUCH_WAVE_NEO && !companion15.isSmaRoundJieieDevice(context)) {
                        String string123 = context.getString(R.string.walking_title_case);
                        Intrinsics.checkNotNullExpressionValue(string123, "{\n                contex…title_case)\n            }");
                        return string123;
                    }
                    String string124 = context.getString(R.string.walk);
                    Intrinsics.checkNotNullExpressionValue(string124, "{\n                contex…tring.walk)\n            }");
                    return string124;
                }
                break;
            case 2729584:
                if (upperCase.equals("YOGA")) {
                    String string125 = context.getString(R.string.yoga_title_case);
                    Intrinsics.checkNotNullExpressionValue(string125, "context.getString(R.string.yoga_title_case)");
                    return string125;
                }
                break;
            case 2730156:
                if (upperCase.equals("YOYO")) {
                    String string126 = context.getString(R.string.yoyo_title_case);
                    Intrinsics.checkNotNullExpressionValue(string126, "context.getString(R.string.yoyo_title_case)");
                    return string126;
                }
                break;
            case 62970214:
                if (upperCase.equals("BARRE")) {
                    String string127 = context.getString(R.string.barre);
                    Intrinsics.checkNotNullExpressionValue(string127, "context.getString(R.string.barre)");
                    return string127;
                }
                break;
            case 64594118:
                if (upperCase.equals(CoveApiConstants.CYCLE)) {
                    String string128 = context.getString(R.string.cycling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string128, "context.getString(R.string.cycling_title_case)");
                    return string128;
                }
                break;
            case 64812947:
                if (upperCase.equals("DANCE")) {
                    String string129 = context.getString(R.string.dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string129, "context.getString(R.string.dance_title_case)");
                    return string129;
                }
                break;
            case 64817332:
                if (upperCase.equals("DARTS")) {
                    String string130 = context.getString(R.string.darts_title_case);
                    Intrinsics.checkNotNullExpressionValue(string130, "context.getString(R.string.darts_title_case)");
                    return string130;
                }
                break;
            case 65056090:
                if (upperCase.equals("DISCO")) {
                    String string131 = context.getString(R.string.disco);
                    Intrinsics.checkNotNullExpressionValue(string131, "context.getString(R.string.disco)");
                    return string131;
                }
                break;
            case 71396799:
                if (upperCase.equals("KENDO")) {
                    String string132 = context.getString(R.string.kendo_title_case);
                    Intrinsics.checkNotNullExpressionValue(string132, "context.getString(R.string.kendo_title_case)");
                    return string132;
                }
                break;
            case 75532016:
                if (upperCase.equals("OTHER")) {
                    String string133 = context.getString(R.string.others_title_case);
                    Intrinsics.checkNotNullExpressionValue(string133, "context.getString(R.string.others_title_case)");
                    return string133;
                }
                break;
            case 76210754:
                if (upperCase.equals("PLANK")) {
                    DeviceUtils.Companion companion16 = DeviceUtils.Companion;
                    if (!companion16.isEastApexDevice(context) && !companion16.isSmaDevice(context)) {
                        if (companion16.isRuggedDevice(context)) {
                            String string134 = context.getString(R.string.planking);
                            Intrinsics.checkNotNullExpressionValue(string134, "{\n                contex…g.planking)\n            }");
                            return string134;
                        }
                        String string135 = context.getString(R.string.plank_title_case);
                        Intrinsics.checkNotNullExpressionValue(string135, "{\n                contex…title_case)\n            }");
                        return string135;
                    }
                    String string136 = context.getString(R.string.plank_title_case_);
                    Intrinsics.checkNotNullExpressionValue(string136, "{\n                contex…itle_case_)\n            }");
                    return string136;
                }
                break;
            case 78331323:
                if (upperCase.equals("RUGBY")) {
                    String string137 = context.getString(R.string.rugby_title_case);
                    Intrinsics.checkNotNullExpressionValue(string137, "context.getString(R.string.rugby_title_case)");
                    return string137;
                }
                break;
            case 79149098:
                if (upperCase.equals("SQUAT")) {
                    String string138 = context.getString(R.string.squat_title_case);
                    Intrinsics.checkNotNullExpressionValue(string138, "context.getString(R.string.squat_title_case)");
                    return string138;
                }
                break;
            case 82960642:
                if (upperCase.equals("WUSHU")) {
                    String string139 = context.getString(R.string.wushu_title_case);
                    Intrinsics.checkNotNullExpressionValue(string139, "context.getString(R.string.wushu_title_case)");
                    return string139;
                }
                break;
            case 85725233:
                if (upperCase.equals("ZUMBA")) {
                    String string140 = context.getString(R.string.zumba_title_case);
                    Intrinsics.checkNotNullExpressionValue(string140, "context.getString(R.string.zumba_title_case)");
                    return string140;
                }
                break;
            case 112121867:
                if (upperCase.equals("JAZZ_DANCE")) {
                    if (DeviceUtils.Companion.isSmaDevice(context)) {
                        String string141 = context.getString(R.string.jazz_title_case);
                        Intrinsics.checkNotNullExpressionValue(string141, "{\n                contex…title_case)\n            }");
                        return string141;
                    }
                    String string142 = context.getString(R.string.jazz_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string142, "{\n                contex…title_case)\n            }");
                    return string142;
                }
                break;
            case 118293932:
                if (upperCase.equals("FRISBEE")) {
                    String string143 = context.getString(R.string.frisbee_title_case);
                    Intrinsics.checkNotNullExpressionValue(string143, "context.getString(R.string.frisbee_title_case)");
                    return string143;
                }
                break;
            case 124295722:
                if (upperCase.equals("HIGH_KNEE_LIFT")) {
                    String string144 = context.getString(R.string.high_knee_lift_title_case);
                    Intrinsics.checkNotNullExpressionValue(string144, "context.getString(R.stri…igh_knee_lift_title_case)");
                    return string144;
                }
                break;
            case 129298012:
                if (upperCase.equals("FIN_SWIM")) {
                    String string145 = context.getString(R.string.fin_swim_title_case);
                    Intrinsics.checkNotNullExpressionValue(string145, "context.getString(R.string.fin_swim_title_case)");
                    return string145;
                }
                break;
            case 147985428:
                if (upperCase.equals("PILATES")) {
                    String string146 = context.getString(R.string.pilates_title_case);
                    Intrinsics.checkNotNullExpressionValue(string146, "context.getString(R.string.pilates_title_case)");
                    return string146;
                }
                break;
            case 170025881:
                if (upperCase.equals("DRIFTING")) {
                    String string147 = context.getString(R.string.drifting_title_case);
                    Intrinsics.checkNotNullExpressionValue(string147, "context.getString(R.string.drifting_title_case)");
                    return string147;
                }
                break;
            case 184281639:
                if (upperCase.equals("ABS_TRAINING")) {
                    String string148 = context.getString(R.string.abs_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string148, "context.getString(R.stri….abs_training_title_case)");
                    return string148;
                }
                break;
            case 219176476:
                if (upperCase.equals("BODY_BALANCING")) {
                    DeviceUtils.Companion companion17 = DeviceUtils.Companion;
                    if (companion17.isSmaDevice(context)) {
                        if (companion17.isSmaRoundJieieDevice(context)) {
                            string4 = context.getString(R.string.body_balance);
                        } else {
                            string4 = context.getString(R.string.body_balancing);
                        }
                        Intrinsics.checkNotNullExpressionValue(string4, "{\n                if (De…          }\n            }");
                        return string4;
                    }
                    String string149 = context.getString(R.string.body_balancing);
                    Intrinsics.checkNotNullExpressionValue(string149, "{\n                contex…_balancing)\n            }");
                    return string149;
                }
                break;
            case 236517227:
                if (upperCase.equals("COOLDOWN")) {
                    String string150 = context.getString(R.string.cooldown_title_case);
                    Intrinsics.checkNotNullExpressionValue(string150, "context.getString(R.string.cooldown_title_case)");
                    return string150;
                }
                break;
            case 261434132:
                if (upperCase.equals("CARDIO_CRUISER")) {
                    String string151 = context.getString(R.string.cardio_cruiser_title_case);
                    Intrinsics.checkNotNullExpressionValue(string151, "context.getString(R.stri…ardio_cruiser_title_case)");
                    return string151;
                }
                break;
            case 281764438:
                if (upperCase.equals("SKATEBOARDING")) {
                    DeviceUtils.Companion companion18 = DeviceUtils.Companion;
                    if (!companion18.isEastApexDevice(context) && !companion18.isSmaDevice(context)) {
                        String string152 = context.getString(R.string.skateboarding_title_case);
                        Intrinsics.checkNotNullExpressionValue(string152, "{\n                contex…title_case)\n            }");
                        return string152;
                    }
                    String string153 = context.getString(R.string.skateboard_title_case);
                    Intrinsics.checkNotNullExpressionValue(string153, "{\n                contex…title_case)\n            }");
                    return string153;
                }
                break;
            case 319278242:
                if (upperCase.equals("BILLIARDS")) {
                    String string154 = context.getString(R.string.billiards_title_case);
                    Intrinsics.checkNotNullExpressionValue(string154, "context.getString(R.string.billiards_title_case)");
                    return string154;
                }
                break;
            case 323890979:
                if (upperCase.equals("POPPING")) {
                    String string155 = context.getString(R.string.street_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string155, "context.getString(R.stri….street_dance_title_case)");
                    return string155;
                }
                break;
            case 330091969:
                if (upperCase.equals("EQUESTRIAN")) {
                    String string156 = context.getString(R.string.equestrian_title_case);
                    Intrinsics.checkNotNullExpressionValue(string156, "context.getString(R.string.equestrian_title_case)");
                    return string156;
                }
                break;
            case 359413958:
                if (upperCase.equals("TRACK_FIELD")) {
                    String string157 = context.getString(R.string.track_field_title_case);
                    Intrinsics.checkNotNullExpressionValue(string157, "context.getString(R.string.track_field_title_case)");
                    return string157;
                }
                break;
            case 384359286:
                if (upperCase.equals("BARBELL")) {
                    String string158 = context.getString(R.string.barbell_title_case);
                    Intrinsics.checkNotNullExpressionValue(string158, "context.getString(R.string.barbell_title_case)");
                    return string158;
                }
                break;
            case 436943649:
                if (upperCase.equals("PARACHUTE")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        if (m.equals(SessionManager.getInstance(context).getDeviceType(), context.getResources().getString(R.string.leap_call), false)) {
                            string5 = context.getString(R.string.skydive_title_case);
                        } else {
                            string5 = context.getString(R.string.parachute_title_case);
                        }
                        Intrinsics.checkNotNullExpressionValue(string5, "{\n                if (Se…          }\n            }");
                        return string5;
                    }
                    String string159 = context.getString(R.string.parachute_title_case);
                    Intrinsics.checkNotNullExpressionValue(string159, "{\n                contex…title_case)\n            }");
                    return string159;
                }
                break;
            case 437361507:
                if (upperCase.equals("SHOOTING")) {
                    String string160 = context.getString(R.string.shooting);
                    Intrinsics.checkNotNullExpressionValue(string160, "context.getString(R.string.shooting)");
                    return string160;
                }
                break;
            case 442313770:
                if (upperCase.equals("SHOT_PUT")) {
                    String string161 = context.getString(R.string.shot_put_title_case);
                    Intrinsics.checkNotNullExpressionValue(string161, "context.getString(R.string.shot_put_title_case)");
                    return string161;
                }
                break;
            case 481127594:
                if (upperCase.equals("GYMNASTICS")) {
                    String string162 = context.getString(R.string.gymnastics_title_case);
                    Intrinsics.checkNotNullExpressionValue(string162, "context.getString(R.string.gymnastics_title_case)");
                    return string162;
                }
                break;
            case 535809482:
                if (upperCase.equals("BELLY_DANCE")) {
                    String string163 = context.getString(R.string.belly_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string163, "context.getString(R.string.belly_dance_title_case)");
                    return string163;
                }
                break;
            case 564625747:
                if (upperCase.equals("ATHLETICS")) {
                    String string164 = context.getString(R.string.athletics);
                    Intrinsics.checkNotNullExpressionValue(string164, "context.getString(R.string.athletics)");
                    return string164;
                }
                break;
            case 571450011:
                if (upperCase.equals("MUAY_THAI")) {
                    String string165 = context.getString(R.string.muay_thai_title_case);
                    Intrinsics.checkNotNullExpressionValue(string165, "context.getString(R.string.muay_thai_title_case)");
                    return string165;
                }
                break;
            case 596941408:
                if (upperCase.equals("TRADITIONAL_STRENGTH_TRAINING")) {
                    String string166 = context.getString(R.string.traditional_strength_title_case);
                    Intrinsics.checkNotNullExpressionValue(string166, "context.getString(R.stri…onal_strength_title_case)");
                    return string166;
                }
                break;
            case 625669464:
                if (upperCase.equals("HORIZONTAL_BAR")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string167 = context.getString(R.string.horizontal_bars_title_case);
                        Intrinsics.checkNotNullExpressionValue(string167, "{\n                contex…title_case)\n            }");
                        return string167;
                    }
                    String string168 = context.getString(R.string.horizontal_bar_title_case);
                    Intrinsics.checkNotNullExpressionValue(string168, "{\n                contex…title_case)\n            }");
                    return string168;
                }
                break;
            case 657827443:
                if (upperCase.equals("MODERN_DANCE")) {
                    String string169 = context.getString(R.string.modern_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string169, "context.getString(R.stri….modern_dance_title_case)");
                    return string169;
                }
                break;
            case 683643573:
                if (upperCase.equals("BLADING")) {
                    String string170 = context.getString(R.string.blading);
                    Intrinsics.checkNotNullExpressionValue(string170, "context.getString(R.string.blading)");
                    return string170;
                }
                break;
            case 706860506:
                if (upperCase.equals("UPPER_BODY_TRAINING")) {
                    String string171 = context.getString(R.string.upper_body_training_case);
                    Intrinsics.checkNotNullExpressionValue(string171, "context.getString(R.stri…upper_body_training_case)");
                    return string171;
                }
                break;
            case 747705837:
                if (upperCase.equals("SHUTTLECOCK")) {
                    String string172 = context.getString(R.string.shuttlecock_title_case);
                    Intrinsics.checkNotNullExpressionValue(string172, "context.getString(R.string.shuttlecock_title_case)");
                    return string172;
                }
                break;
            case 770007682:
                if (upperCase.equals("BOATING")) {
                    String string173 = context.getString(R.string.boating_title_case);
                    Intrinsics.checkNotNullExpressionValue(string173, "context.getString(R.string.boating_title_case)");
                    return string173;
                }
                break;
            case 780621286:
                if (upperCase.equals("BADMINTON")) {
                    String string174 = context.getString(R.string.badminton_title_case);
                    Intrinsics.checkNotNullExpressionValue(string174, "context.getString(R.string.badminton_title_case)");
                    return string174;
                }
                break;
            case 784393966:
                if (upperCase.equals("HANDBALL")) {
                    String string175 = context.getString(R.string.handball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string175, "context.getString(R.string.handball_title_case)");
                    return string175;
                }
                break;
            case 790086816:
                if (upperCase.equals("BOWLING")) {
                    String string176 = context.getString(R.string.bowling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string176, "context.getString(R.string.bowling_title_case)");
                    return string176;
                }
                break;
            case 819666367:
                if (upperCase.equals("SNOWBOARDING")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string177 = context.getString(R.string.snowboard_title_case);
                        Intrinsics.checkNotNullExpressionValue(string177, "{\n                contex…title_case)\n            }");
                        return string177;
                    }
                    String string178 = context.getString(R.string.snowboarding_title_case);
                    Intrinsics.checkNotNullExpressionValue(string178, "{\n                contex…title_case)\n            }");
                    return string178;
                }
                break;
            case 849353688:
                if (upperCase.equals("AEROBICS")) {
                    String string179 = context.getString(R.string.aerobics_title_case);
                    Intrinsics.checkNotNullExpressionValue(string179, "context.getString(R.string.aerobics_title_case)");
                    return string179;
                }
                break;
            case 881635287:
                if (upperCase.equals("TAP_DANCE")) {
                    String string180 = context.getString(R.string.tap_dance);
                    Intrinsics.checkNotNullExpressionValue(string180, "context.getString(R.string.tap_dance)");
                    return string180;
                }
                break;
            case 957359170:
                if (upperCase.equals("BURPEES")) {
                    String string181 = context.getString(R.string.burpees_title_case);
                    Intrinsics.checkNotNullExpressionValue(string181, "context.getString(R.string.burpees_title_case)");
                    return string181;
                }
                break;
            case 1020800150:
                if (upperCase.equals("MARATHON")) {
                    String string182 = context.getString(R.string.marathon);
                    Intrinsics.checkNotNullExpressionValue(string182, "context.getString(R.string.marathon)");
                    return string182;
                }
                break;
            case 1033085322:
                if (upperCase.equals("DUMBBELLS")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string183 = context.getString(R.string.dumbbells_training_title_case);
                        Intrinsics.checkNotNullExpressionValue(string183, "{\n                contex…title_case)\n            }");
                        return string183;
                    }
                    String string184 = context.getString(R.string.dumbbells_title_case);
                    Intrinsics.checkNotNullExpressionValue(string184, "{\n                contex…title_case)\n            }");
                    return string184;
                }
                break;
            case 1056688823:
                if (upperCase.equals("LOCKING")) {
                    String string185 = context.getString(R.string.locking_title_case);
                    Intrinsics.checkNotNullExpressionValue(string185, "context.getString(R.string.locking_title_case)");
                    return string185;
                }
                break;
            case 1066899814:
                if (upperCase.equals("ARTISTIC_SWIM")) {
                    String string186 = context.getString(R.string.artistic_swim_title_case);
                    Intrinsics.checkNotNullExpressionValue(string186, "context.getString(R.stri…artistic_swim_title_case)");
                    return string186;
                }
                break;
            case 1074615275:
                if (upperCase.equals("FLOORBALL")) {
                    String string187 = context.getString(R.string.floor_ball);
                    Intrinsics.checkNotNullExpressionValue(string187, "context.getString(R.string.floor_ball)");
                    return string187;
                }
                break;
            case 1146382968:
                if (upperCase.equals("LATIN_DANCE")) {
                    String string188 = context.getString(R.string.latin_dance_title_case);
                    Intrinsics.checkNotNullExpressionValue(string188, "context.getString(R.string.latin_dance_title_case)");
                    return string188;
                }
                break;
            case 1177295725:
                if (upperCase.equals("FOOTBALL")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string189 = context.getString(R.string.soccer_title_case);
                        Intrinsics.checkNotNullExpressionValue(string189, "{\n                contex…title_case)\n            }");
                        return string189;
                    }
                    String string190 = context.getString(R.string.football_title_case);
                    Intrinsics.checkNotNullExpressionValue(string190, "{\n                contex…title_case)\n            }");
                    return string190;
                }
                break;
            case 1212242117:
                if (upperCase.equals("BASKETBALL")) {
                    String string191 = context.getString(R.string.basketball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string191, "context.getString(R.string.basketball_title_case)");
                    return string191;
                }
                break;
            case 1258668673:
                if (upperCase.equals("BUNGEE_JUMPING")) {
                    String string192 = context.getString(R.string.bungee_jumping_title_case);
                    Intrinsics.checkNotNullExpressionValue(string192, "context.getString(R.stri…ungee_jumping_title_case)");
                    return string192;
                }
                break;
            case 1286261103:
                if (upperCase.equals("DEADLIFT")) {
                    String string193 = context.getString(R.string.deadlift_title_case);
                    Intrinsics.checkNotNullExpressionValue(string193, "context.getString(R.string.deadlift_title_case)");
                    return string193;
                }
                break;
            case 1372333470:
                if (upperCase.equals("AUSTRALIAN_FOOTBALL")) {
                    String string194 = context.getString(R.string.australian_football);
                    Intrinsics.checkNotNullExpressionValue(string194, "context.getString(R.string.australian_football)");
                    return string194;
                }
                break;
            case 1426925407:
                if (upperCase.equals("WEIGHTLIFTING")) {
                    String string195 = context.getString(R.string.weightlifting_title_case);
                    Intrinsics.checkNotNullExpressionValue(string195, "context.getString(R.stri…weightlifting_title_case)");
                    return string195;
                }
                break;
            case 1433149465:
                if (upperCase.equals("BATTLE_ROPE")) {
                    String string196 = context.getString(R.string.battle_rope_title_case);
                    Intrinsics.checkNotNullExpressionValue(string196, "context.getString(R.string.battle_rope_title_case)");
                    return string196;
                }
                break;
            case 1440891949:
                if (upperCase.equals("ELLIPTICAL")) {
                    DeviceUtils.Companion companion19 = DeviceUtils.Companion;
                    if (!companion19.isEastApexDevice(context) && !companion19.isRuggedDevice(context)) {
                        if (companion19.isSmaDevice(context)) {
                            String string197 = context.getString(R.string.elliptical_machine_title_case);
                            Intrinsics.checkNotNullExpressionValue(string197, "{\n                contex…title_case)\n            }");
                            return string197;
                        }
                        String string198 = context.getString(R.string.elliptical_title_case);
                        Intrinsics.checkNotNullExpressionValue(string198, "{\n                contex…title_case)\n            }");
                        return string198;
                    }
                    String string199 = context.getString(R.string.elliptical_trainer_title_case);
                    Intrinsics.checkNotNullExpressionValue(string199, "{\n                contex…title_case)\n            }");
                    return string199;
                }
                break;
            case 1451657874:
                if (upperCase.equals("LEG_PRESS")) {
                    String string200 = context.getString(R.string.leg_press);
                    Intrinsics.checkNotNullExpressionValue(string200, "context.getString(R.string.leg_press)");
                    return string200;
                }
                break;
            case 1455853131:
                if (upperCase.equals("FREE_EXERCISE")) {
                    DeviceUtils.Companion companion20 = DeviceUtils.Companion;
                    if (companion20.isEastApexDevice(context)) {
                        String string201 = context.getString(R.string.free_excercise_title_case);
                        Intrinsics.checkNotNullExpressionValue(string201, "{\n                contex…title_case)\n            }");
                        return string201;
                    } else if (companion20.isSmaDevice(context)) {
                        String string202 = context.getString(R.string.free_movement_title_case);
                        Intrinsics.checkNotNullExpressionValue(string202, "{\n                contex…title_case)\n            }");
                        return string202;
                    } else {
                        String string203 = context.getString(R.string.free_trining_case);
                        Intrinsics.checkNotNullExpressionValue(string203, "{\n                contex…ining_case)\n            }");
                        return string203;
                    }
                }
                break;
            case 1461229297:
                if (upperCase.equals("FREE_SPARRING")) {
                    String string204 = context.getString(R.string.free_sparring_title_case);
                    Intrinsics.checkNotNullExpressionValue(string204, "context.getString(R.stri…free_sparring_title_case)");
                    return string204;
                }
                break;
            case 1478939548:
                if (upperCase.equals("DISC_THROW")) {
                    DeviceUtils.Companion companion21 = DeviceUtils.Companion;
                    if (companion21.isSmaDevice(context)) {
                        if (companion21.isSmaRoundJieieDevice(context)) {
                            string6 = context.getString(R.string.discus);
                        } else {
                            string6 = context.getString(R.string.disc_throw);
                        }
                        Intrinsics.checkNotNullExpressionValue(string6, "{\n                if (De…          }\n            }");
                        return string6;
                    }
                    String string205 = context.getString(R.string.disc_throw);
                    Intrinsics.checkNotNullExpressionValue(string205, "{\n                contex…disc_throw)\n            }");
                    return string205;
                }
                break;
            case 1503943685:
                if (upperCase.equals("HULA_HOOP")) {
                    String string206 = context.getString(R.string.hula_hoop_title_case);
                    Intrinsics.checkNotNullExpressionValue(string206, "context.getString(R.string.hula_hoop_title_case)");
                    return string206;
                }
                break;
            case 1523672883:
                if (upperCase.equals("SOLID_BALL")) {
                    String string207 = context.getString(R.string.solid_ball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string207, "context.getString(R.string.solid_ball_title_case)");
                    return string207;
                }
                break;
            case 1558128613:
                if (upperCase.equals("BEACH_SOCCER")) {
                    String string208 = context.getString(R.string.beach_soccer_title_case);
                    Intrinsics.checkNotNullExpressionValue(string208, "context.getString(R.stri….beach_soccer_title_case)");
                    return string208;
                }
                break;
            case 1566049329:
                if (upperCase.equals("BODY_COMBAT")) {
                    String string209 = context.getString(R.string.body_combat);
                    Intrinsics.checkNotNullExpressionValue(string209, "context.getString(R.string.body_combat)");
                    return string209;
                }
                break;
            case 1574919256:
                if (upperCase.equals("HOVERBOARDING")) {
                    String string210 = context.getString(R.string.hoverboard);
                    Intrinsics.checkNotNullExpressionValue(string210, "context.getString(R.string.hoverboard)");
                    return string210;
                }
                break;
            case 1597478393:
                if (upperCase.equals("LOWER_BODY_TRAINING")) {
                    String string211 = context.getString(R.string.lower_body_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string211, "context.getString(R.stri…body_training_title_case)");
                    return string211;
                }
                break;
            case 1615323782:
                if (upperCase.equals("FITNESS_GAMING")) {
                    String string212 = context.getString(R.string.fitness_gaming_title_case);
                    Intrinsics.checkNotNullExpressionValue(string212, "context.getString(R.stri…itness_gaming_title_case)");
                    return string212;
                }
                break;
            case 1639690848:
                if (upperCase.equals("CARDIO_BOXING")) {
                    String string213 = context.getString(R.string.cardio_boxing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string213, "context.getString(R.stri…cardio_boxing_title_case)");
                    return string213;
                }
                break;
            case 1688560922:
                if (upperCase.equals("MIXED_CARDIO")) {
                    String string214 = context.getString(R.string.mixed_cardio_title_case);
                    Intrinsics.checkNotNullExpressionValue(string214, "context.getString(R.stri….mixed_cardio_title_case)");
                    return string214;
                }
                break;
            case 1698056461:
                if (upperCase.equals("CLIMBING")) {
                    String string215 = context.getString(R.string.climbing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string215, "context.getString(R.string.climbing_title_case)");
                    return string215;
                }
                break;
            case 1750282193:
                if (upperCase.equals("CRICKET")) {
                    String string216 = context.getString(R.string.cricket_title_case);
                    Intrinsics.checkNotNullExpressionValue(string216, "context.getString(R.string.cricket_title_case)");
                    return string216;
                }
                break;
            case 1756250003:
                if (upperCase.equals("CROQUET")) {
                    String string217 = context.getString(R.string.croquet);
                    Intrinsics.checkNotNullExpressionValue(string217, "context.getString(R.string.croquet)");
                    return string217;
                }
                break;
            case 1842420373:
                if (upperCase.equals("WARM_UP")) {
                    String string218 = context.getString(R.string.warm_up_title_case);
                    Intrinsics.checkNotNullExpressionValue(string218, "context.getString(R.string.warm_up_title_case)");
                    return string218;
                }
                break;
            case 1844747798:
                if (upperCase.equals("CURLING")) {
                    String string219 = context.getString(R.string.curling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string219, "context.getString(R.string.curling_title_case)");
                    return string219;
                }
                break;
            case 1862139462:
                if (upperCase.equals("RACQUETBALL")) {
                    String string220 = context.getString(R.string.racquetball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string220, "context.getString(R.string.racquetball_title_case)");
                    return string220;
                }
                break;
            case 1906741465:
                if (upperCase.equals("WAIST_TRAINING")) {
                    String string221 = context.getString(R.string.waist_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string221, "context.getString(R.stri…aist_training_title_case)");
                    return string221;
                }
                break;
            case 1923016953:
                if (upperCase.equals("FLEXIBILITY")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string222 = context.getString(R.string.flexiblity_training_title_case);
                        Intrinsics.checkNotNullExpressionValue(string222, "{\n                contex…title_case)\n            }");
                        return string222;
                    }
                    String string223 = context.getString(R.string.flexiblity_title_case);
                    Intrinsics.checkNotNullExpressionValue(string223, "{\n                contex…title_case)\n            }");
                    return string223;
                }
                break;
            case 1947049132:
                if (upperCase.equals("AMERICAN_FOOTBALL")) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string224 = context.getString(R.string.football_title_case);
                        Intrinsics.checkNotNullExpressionValue(string224, "{\n                contex…title_case)\n            }");
                        return string224;
                    }
                    String string225 = context.getString(R.string.american_football_title_case);
                    Intrinsics.checkNotNullExpressionValue(string225, "{\n                contex…title_case)\n            }");
                    return string225;
                }
                break;
            case 1951892206:
                if (upperCase.equals("BALLET")) {
                    String string226 = context.getString(R.string.ballet_title_case);
                    Intrinsics.checkNotNullExpressionValue(string226, "context.getString(R.string.ballet_title_case)");
                    return string226;
                }
                break;
            case 1959274292:
                if (upperCase.equals("CLIMB_STAIRS")) {
                    DeviceUtils.Companion companion22 = DeviceUtils.Companion;
                    if (!companion22.isTouchELXDevice(context) && !companion22.isRuggedDevice(context)) {
                        if (!companion22.isEastApexDevice(context) && !companion22.isSmaDevice(context)) {
                            String string227 = context.getString(R.string.stair_climbing_title_case);
                            Intrinsics.checkNotNullExpressionValue(string227, "{\n                contex…title_case)\n            }");
                            return string227;
                        }
                        String string228 = context.getString(R.string.climb_stairs);
                        Intrinsics.checkNotNullExpressionValue(string228, "{\n                contex…imb_stairs)\n            }");
                        return string228;
                    }
                    String string229 = context.getString(R.string.stairs_title_case);
                    Intrinsics.checkNotNullExpressionValue(string229, "{\n                contex…title_case)\n            }");
                    return string229;
                }
                break;
            case 1965176375:
                if (upperCase.equals("BOXING")) {
                    String string230 = context.getString(R.string.boxing_title_case);
                    Intrinsics.checkNotNullExpressionValue(string230, "context.getString(R.string.boxing_title_case)");
                    return string230;
                }
                break;
            case 1983843151:
                if (upperCase.equals("HUNTING")) {
                    String string231 = context.getString(R.string.hunting_title_case);
                    Intrinsics.checkNotNullExpressionValue(string231, "context.getString(R.string.hunting_title_case)");
                    return string231;
                }
                break;
            case 2016833969:
                if (upperCase.equals("DIVING")) {
                    String string232 = context.getString(R.string.diving_title_case);
                    Intrinsics.checkNotNullExpressionValue(string232, "context.getString(R.string.diving_title_case)");
                    return string232;
                }
                break;
            case 2030283637:
                if (upperCase.equals("KAYAKING")) {
                    String string233 = context.getString(R.string.kayak_title_case);
                    Intrinsics.checkNotNullExpressionValue(string233, "context.getString(R.string.kayak_title_case)");
                    return string233;
                }
                break;
            case 2047416394:
                if (upperCase.equals("PUSH_PULL")) {
                    String string234 = context.getString(R.string.push_pull_title_case);
                    Intrinsics.checkNotNullExpressionValue(string234, "context.getString(R.string.push_pull_title_case)");
                    return string234;
                }
                break;
            case 2052409245:
                if (upperCase.equals("MOUNTAINEERING")) {
                    DeviceUtils.Companion companion23 = DeviceUtils.Companion;
                    if (companion23.isEastApexDevice(context)) {
                        String string235 = context.getString(R.string.mountaineer_title_case);
                        Intrinsics.checkNotNullExpressionValue(string235, "{\n                contex…title_case)\n            }");
                        return string235;
                    } else if (companion23.isSmaDevice(context)) {
                        String string236 = context.getString(R.string.mountain_climber_title_case);
                        Intrinsics.checkNotNullExpressionValue(string236, "{\n                contex…title_case)\n            }");
                        return string236;
                    } else {
                        String string237 = context.getString(R.string.mountaineering_title_case);
                        Intrinsics.checkNotNullExpressionValue(string237, "{context.getString(R.str…untaineering_title_case)}");
                        return string237;
                    }
                }
                break;
            case 2069676338:
                if (upperCase.equals("BACK_TRAINING")) {
                    String string238 = context.getString(R.string.back_training_title_case);
                    Intrinsics.checkNotNullExpressionValue(string238, "context.getString(R.stri…back_training_title_case)");
                    return string238;
                }
                break;
            case 2087812637:
                if (upperCase.equals("TRAMPOLINE")) {
                    String string239 = context.getString(R.string.trampoline);
                    Intrinsics.checkNotNullExpressionValue(string239, "context.getString(R.string.trampoline)");
                    return string239;
                }
                break;
            case 2095085093:
                if (upperCase.equals("ROLLING")) {
                    String string240 = context.getString(R.string.rolling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string240, "context.getString(R.string.rolling_title_case)");
                    return string240;
                }
                break;
            case 2101331241:
                if (upperCase.equals("SOFTBALL")) {
                    String string241 = context.getString(R.string.softball_title_case);
                    Intrinsics.checkNotNullExpressionValue(string241, "context.getString(R.string.softball_title_case)");
                    return string241;
                }
                break;
            case 2111154977:
                if (upperCase.equals("WRESTLING")) {
                    String string242 = context.getString(R.string.wrestling_title_case);
                    Intrinsics.checkNotNullExpressionValue(string242, "context.getString(R.string.wrestling_title_case)");
                    return string242;
                }
                break;
            case 2122974379:
                if (upperCase.equals("HIGH_JUMP")) {
                    String string243 = context.getString(R.string.high_jump_title_case);
                    Intrinsics.checkNotNullExpressionValue(string243, "context.getString(R.string.high_jump_title_case)");
                    return string243;
                }
                break;
            case 2123697862:
                if (upperCase.equals("HAMMER")) {
                    String string244 = context.getString(R.string.hammer);
                    Intrinsics.checkNotNullExpressionValue(string244, "context.getString(R.string.hammer)");
                    return string244;
                }
                break;
            case 2131022872:
                if (upperCase.equals(CoveApiConstants.HIKING)) {
                    if (DeviceUtils.Companion.isEastApexDevice(context)) {
                        String string245 = context.getString(R.string.hike_title_case);
                        Intrinsics.checkNotNullExpressionValue(string245, "{\n                contex…title_case)\n            }");
                        return string245;
                    }
                    String string246 = context.getString(R.string.hiking_title_case);
                    Intrinsics.checkNotNullExpressionValue(string246, "{\n                contex…title_case)\n            }");
                    return string246;
                }
                break;
            case 2136327331:
                if (upperCase.equals("HOCKEY")) {
                    String string247 = context.getString(R.string.hockey_title_case);
                    Intrinsics.checkNotNullExpressionValue(string247, "context.getString(R.string.hockey_title_case)");
                    return string247;
                }
                break;
        }
        String string248 = context.getString(R.string.workout_title_case);
        Intrinsics.checkNotNullExpressionValue(string248, "context.getString(R.string.workout_title_case)");
        return string248;
    }
}
