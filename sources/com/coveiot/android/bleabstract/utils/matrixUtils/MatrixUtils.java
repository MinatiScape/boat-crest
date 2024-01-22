package com.coveiot.android.bleabstract.utils.matrixUtils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.ido.ble.protocol.model.PressureParam;
import com.jieli.jl_rcsp.constant.Command;
import com.realsil.sdk.dfu.DfuException;
import com.touchgui.sdk.TGEventListener;
import com.veryfit.multi.nativeprotocol.b;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class MatrixUtils {
    @NotNull
    public static final MatrixUtils INSTANCE = new MatrixUtils();

    @NotNull
    public final Calendar convertSDKTimeToCalender(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Intrinsics.checkNotNullExpressionValue(calendar, "calendar");
        return calendar;
    }

    @NotNull
    public final String convertSDKTimeToDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        String format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    @Nullable
    public final String getActivityMode(int i) {
        switch (i) {
            case 1:
            case 97:
                return CoveApiConstants.CYCLE;
            case 5:
            case 9:
                return CoveApiConstants.RUN;
            case 17:
                return "MOUNTAINEERING";
            case 21:
                return "BASKETBALL";
            case 29:
                return "BADMINTON";
            case 33:
                return "FOOTBALL";
            case 37:
                return "ELLIPTICAL";
            case 41:
                return "YOGA";
            case 45:
                return "TABLE_TENNIS";
            case 49:
                return "SKIPPING";
            case 53:
                return "ROWING";
            case 65:
                return "FREE_EXERCISE";
            case 69:
                return "TENNIS";
            case 73:
                return "BASEBALL";
            case 77:
                return "RUGBY";
            case 81:
                return "CRICKET";
            case 89:
                return "STRENGTH_TRAINING";
            case 105:
                return "DANCE";
            case 109:
                return "HULA_HOOP";
            case 113:
                return "GOLF";
            case 117:
                return "LONG_JUMP";
            case 121:
                return "SIT_UPS";
            case 125:
                return "VOLLEYBALL";
            case 129:
                return "PARKOUR";
            case 133:
                return CoveApiConstants.HIKING;
            case 137:
                return "HOCKEY";
            case 141:
                return "BOATING";
            case 149:
                return "SOFTBALL";
            case 153:
                return "TRAIL_RUN";
            case 157:
                return "SKI";
            case 161:
                return "AIR_WALKER";
            case 165:
                return "COOLDOWN";
            case 169:
                return "CROSS_TRAINING";
            case 173:
                return "PILATES";
            case 177:
                return "CROSS_FIT";
            case 181:
                return "FUNCTIONAL_STRENGTH_TRAINING";
            case 185:
                return "WORKOUT";
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /* 189 */:
                return "MIXED_CARDIO";
            case 193:
                return "LATIN_DANCE";
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /* 197 */:
                return "STREET_DANCE";
            case 201:
                return "KICKBOXING";
            case 205:
                return "BARRE";
            case 209:
                return "AUSTRALIAN_FOOTBALL";
            case Command.CMD_GET_LOW_LATENCY_SETTINGS /* 213 */:
                return "BOWLING";
            case Command.CMD_GET_DEVICE_CONFIG_INFO /* 217 */:
                return "RACQUETBALL";
            case PressureParam.STATE_ALL_DAY /* 221 */:
                return "CURLING";
            case 225:
                return "SNOWBOARDING";
            case 229:
                return "FISHING";
            case 233:
                return "DISC_SPORTS";
            case 237:
                return "DOWNHILL_SKIING";
            case Command.CMD_PHONE_NUMBER_PLAY_MODE /* 241 */:
                return "CORE_TRAINING";
            case 245:
                return "SKATING";
            case 249:
                return "FITNESS_GAMING";
            case 253:
                return "AEROBICS";
            case 257:
                return "GROUP_TRAINING";
            case 261:
                return "KENDO";
            case DfuException.ERROR_CANNOT_FIND_DEVICE /* 265 */:
                return "FENCING";
            case 269:
                return "CLIMB_STAIRS";
            case 273:
                return "AMERICAN_FOOTBALL";
            case DfuException.ERROR_READ_REMOTE_MAC_ADDR /* 277 */:
                return "ROLLING";
            case DfuException.ERROR_DFU_SPP_OTA_NOT_SUPPORTED /* 281 */:
                return "PICKLEBALL";
            case 285:
                return "BOXING";
            case TGEventListener.WORKOUT_STOP /* 289 */:
                return "TAEKWONDO";
            case TGEventListener.WATCH_FACE_INSTALLED /* 293 */:
                return "KARATE";
            case 297:
                return "FLEXIBILITY";
            case 301:
                return "HANDBALL";
            case 305:
                return "HAND_CYCLING";
            case 309:
                return "MEDITATION";
            case 313:
                return "WRESTLING";
            case 317:
                return "STEPPER";
            case b.f1 /* 321 */:
                return "TAI_CHI";
            case b.i1 /* 325 */:
                return "GYMNASTICS";
            case 329:
                return "ATHLETICS";
            case b.n1 /* 333 */:
                return "MARTIAL_ARTS";
            case b.r1 /* 337 */:
                return "WARM_UP";
            case 341:
                return "SNOW_SPORTS";
            case 345:
                return "LACROSSE";
            case 349:
                return "HORIZONTAL_BAR";
            case b.t1 /* 353 */:
                return "PARALLEL_BARS";
            case 357:
                return "ROLLER_SKATING";
            case 361:
                return "DARTS";
            case 365:
                return "ARCHERY";
            case 369:
                return "HORSE_RIDING";
            case 373:
                return "SHUTTLECOCK";
            case 377:
                return "ICE_HOCKEY";
            case 381:
                return "WAIST_TRAINING";
            case 385:
                return "VO2MAX";
            case 389:
                return "JUDO";
            case 393:
                return "TRAMPOLINE";
            case 397:
                return "SKATEBOARDING";
            case 401:
                return "HOVERBOARDING";
            case b.z1 /* 405 */:
                return "BLADING";
            case 409:
                return "TREADMILL";
            case b.F1 /* 413 */:
                return "DIVING";
            case 417:
                return "SURFING";
            case 421:
                return "SNORKEL";
            case TypedValues.CycleType.TYPE_WAVE_PHASE /* 425 */:
                return "PULL_UPS";
            case 429:
                return "PUSH_UPS";
            case 433:
                return "PLANK";
            case 437:
                return "ROCK_CLIMBING";
            case 441:
                return "HIGH_JUMP";
            case 445:
                return "BUNGEE_JUMPING";
            case 449:
                return "FOLK_DANCE";
            case 457:
                return "SHOOTING";
            case 461:
                return "MARATHON";
            default:
                return "WALK";
        }
    }

    @Nullable
    public final String getActivitySite(int i) {
        return (i == 1 || i == 5 || i == 13) ? "OUTDOOR" : "INDOOR";
    }

    @NotNull
    public final Pair<Integer, Integer> getHourAndMinutes(int i) {
        return new Pair<>(Integer.valueOf(i / 60), Integer.valueOf(i % 60));
    }

    public final int getMatrixWeatherType(@NotNull String weatherType) {
        Intrinsics.checkNotNullParameter(weatherType, "weatherType");
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clear", true)) {
            return 1;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clouds", true)) {
            return 2;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Rain", true)) {
            return 7;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Thunderstorm", true)) {
            return 5;
        }
        return StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Snow", true) ? 12 : 1;
    }

    public final int getMinutes(int i, int i2) {
        return (i * 60) + i2;
    }
}
