package com.coveiot.android.bleabstract.utils.eastapexUtils;

import com.apex.bluetooth.enumeration.TimeZone;
import com.apex.bluetooth.model.EABleMultiData;
import com.apex.bluetooth.model.EABlePersonInfo;
import com.apex.bluetooth.model.EABleWeather;
import com.coveiot.coveaccess.constants.CoveApiConstants;
import com.example.custom_dial.DialStyle;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EastApexUtils {
    @NotNull
    public static final EastApexUtils INSTANCE = new EastApexUtils();

    @Nullable
    public final String getActivityMode(int i) {
        if (i != EABleMultiData.MotionType.ourdoor_walking.value) {
            if (i == EABleMultiData.MotionType.ourdoor_running.value) {
                return CoveApiConstants.RUN;
            }
            if (i != EABleMultiData.MotionType.ourdoor_cycling.value) {
                if (i == EABleMultiData.MotionType.ourdoor_on_foot.value) {
                    return CoveApiConstants.HIKING;
                }
                if (i == EABleMultiData.MotionType.ourdoor_trail_running.value) {
                    return "TRAIL_RUN";
                }
                if (i == EABleMultiData.MotionType.indoor_exercise.value) {
                    return "WORKOUT";
                }
                if (i == EABleMultiData.MotionType.rowing.value) {
                    return "ROWING";
                }
                if (i == EABleMultiData.MotionType.elliptical.value) {
                    return "ELLIPTICAL";
                }
                if (i == EABleMultiData.MotionType.yoga.value) {
                    return "YOGA";
                }
                if (i == EABleMultiData.MotionType.ourdoor_on_mountaineering.value) {
                    return "MOUNTAINEERING";
                }
                if (i != EABleMultiData.MotionType.outdoor_swimming.value) {
                    if (i != EABleMultiData.MotionType.indoor_walking.value) {
                        if (i == EABleMultiData.MotionType.indoor_running.value) {
                            return "TREADMILL";
                        }
                        if (i != EABleMultiData.MotionType.indoor_cycling.value) {
                            if (i != EABleMultiData.MotionType.indoor_swimming.value) {
                                if (i == EABleMultiData.MotionType.od_rock.value) {
                                    return "ROCK_CLIMBING";
                                }
                                if (i == EABleMultiData.MotionType.od_skate.value) {
                                    return "SKATEBOARDING";
                                }
                                if (i != EABleMultiData.MotionType.od_parkour.value) {
                                    if (i == EABleMultiData.MotionType.od_parachute.value) {
                                        return "PARACHUTE";
                                    }
                                    if (i == EABleMultiData.MotionType.od_roller.value) {
                                        return "ROLLER_SKATING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_HIT.value) {
                                        return "HIIT";
                                    }
                                    if (i == EABleMultiData.MotionType.train_weight.value) {
                                        return "WEIGHTLIFTING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_plank.value) {
                                        return "PLANK";
                                    }
                                    if (i == EABleMultiData.MotionType.train_jumping.value) {
                                        return "JUMPING_JACKS";
                                    }
                                    if (i == EABleMultiData.MotionType.train_stair.value) {
                                        return "CLIMBING_MACHINE";
                                    }
                                    if (i == EABleMultiData.MotionType.train_core.value) {
                                        return "CORE_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_flex.value) {
                                        return "FLEXIBILITY";
                                    }
                                    if (i == EABleMultiData.MotionType.train_strength.value) {
                                        return "STRENGTH_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_pilates.value) {
                                        return "PILATES";
                                    }
                                    if (i == EABleMultiData.MotionType.train_stretch.value) {
                                        return "STRETCHING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_cross.value) {
                                        return "CROSS_FIT";
                                    }
                                    if (i == EABleMultiData.MotionType.train_dumbbell.value) {
                                        return "DUMBBELLS";
                                    }
                                    if (i == EABleMultiData.MotionType.train_deadlift.value) {
                                        return "DEADLIFT";
                                    }
                                    if (i == EABleMultiData.MotionType.train_sit.value) {
                                        return "SIT_UPS";
                                    }
                                    if (i == EABleMultiData.MotionType.train_funcition.value) {
                                        return "FUNCTIONAL_STRENGTH_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_upper.value) {
                                        return "UPPER_BODY_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_lower.value) {
                                        return "LOWER_BODY_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_abs.value) {
                                        return "ABS_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.train_back.value) {
                                        return "BACK_TRAINING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_sailboat.value) {
                                        return "SAILBOATING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_sup.value) {
                                        return "SUP";
                                    }
                                    if (i == EABleMultiData.MotionType.water_polo.value) {
                                        return "WATER_POLO";
                                    }
                                    if (i == EABleMultiData.MotionType.water_thrash.value) {
                                        return "THRASH";
                                    }
                                    if (i == EABleMultiData.MotionType.water_kayak.value) {
                                        return "KAYAKING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_drifting.value) {
                                        return "DRIFTING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_boating.value) {
                                        return "BOATING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_fin.value) {
                                        return "FIN_SWIM";
                                    }
                                    if (i == EABleMultiData.MotionType.water_diving.value) {
                                        return "DIVING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_artistic.value) {
                                        return "ARTISTIC_SWIM";
                                    }
                                    if (i == EABleMultiData.MotionType.water_snorkel.value) {
                                        return "SNORKEL";
                                    }
                                    if (i == EABleMultiData.MotionType.water_kitesurfing.value) {
                                        return "KITESURFING";
                                    }
                                    if (i == EABleMultiData.MotionType.water_atv.value) {
                                        return "ATV";
                                    }
                                    if (i == EABleMultiData.MotionType.water_beach.value) {
                                        return "BEACH_SOCCER";
                                    }
                                    if (i == EABleMultiData.MotionType.dance_dance.value) {
                                        return "DANCE";
                                    }
                                    if (i == EABleMultiData.MotionType.dance_delly.value) {
                                        return "BELLY_DANCE";
                                    }
                                    if (i == EABleMultiData.MotionType.dance_gymnastics.value) {
                                        return "GYMNASTICS";
                                    }
                                    if (i == EABleMultiData.MotionType.dance_aerobics.value) {
                                        return "AEROBICS";
                                    }
                                    if (i == EABleMultiData.MotionType.dance_hip_hop.value) {
                                        return "STREET_DANCE";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_boxing.value) {
                                        return "BOXING";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_wushu.value) {
                                        return "WUSHU";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_wrestling.value) {
                                        return "WRESTLING";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_taichi.value) {
                                        return "TAI_CHI";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_muay_thai.value) {
                                        return "MUAY_THAI";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_judo.value) {
                                        return "JUDO";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_taekwondo.value) {
                                        return "TAEKWONDO";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_karate.value) {
                                        return "KARATE";
                                    }
                                    if (i == EABleMultiData.MotionType.fight_free_sparring.value) {
                                        return "FREE_SPARRING";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_soccer.value) {
                                        return "FOOTBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_basketball.value) {
                                        return "BASKETBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_volleyball.value) {
                                        return "VOLLEYBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_badminton.value) {
                                        return "BADMINTON";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_pingpong.value) {
                                        return "PING_PONG";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_cricket.value) {
                                        return "CRICKET";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_football.value) {
                                        return "AMERICAN_FOOTBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_racqurball.value) {
                                        return "RACQUETBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_handball.value) {
                                        return "HANDBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_squash.value) {
                                        return "SQUASH";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_shuttlecock.value) {
                                        return "SHUTTLECOCK";
                                    }
                                    if (i == EABleMultiData.MotionType.ball_raga.value) {
                                        return "RAGA";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_mobile.value) {
                                        return "SNOWMOBILE";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_skis.value) {
                                        return "SKI";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_puck.value) {
                                        return "PUCK";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_skate.value) {
                                        return "SKATING";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_curling.value) {
                                        return "CURLING";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_board.value) {
                                        return "SNOWBOARDING";
                                    }
                                    if (i == EABleMultiData.MotionType.snow_sled.value) {
                                        return "SLEDDING";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_meditation.value) {
                                        return "MEDITATION";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_kendo.value) {
                                        return "KENDO";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_fence.value) {
                                        return "FENCING";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_bowling.value) {
                                        return "BOWLING";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_billiards.value) {
                                        return "BILLIARDS";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_archery.value) {
                                        return "ARCHERY";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_darts.value) {
                                        return "DARTS";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_horse.value) {
                                        return "HORSE_RIDING";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_hula.value) {
                                        return "HULA_HOOP";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_kite.value) {
                                        return "KITE";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_fishing.value) {
                                        return "FISHING";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_fribee.value) {
                                        return "FRISBEE";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_equestrian.value) {
                                        return "EQUESTRIAN";
                                    }
                                    if (i == EABleMultiData.MotionType.leisure_racing.value) {
                                        return "RACING";
                                    }
                                    if (i == EABleMultiData.MotionType.other_free.value) {
                                        return "FREE_EXERCISE";
                                    }
                                    if (i == EABleMultiData.MotionType.other_rope.value) {
                                        return "SKIPPING";
                                    }
                                    if (i == EABleMultiData.MotionType.other_climb.value) {
                                        return "CLIMB_STAIRS";
                                    }
                                    if (i == EABleMultiData.MotionType.other_push.value) {
                                        return "PUSH_PULL";
                                    }
                                    if (i == EABleMultiData.MotionType.other_horizontal.value) {
                                        return "HORIZONTAL_BAR";
                                    }
                                    if (i == EABleMultiData.MotionType.other_parallel.value) {
                                        return "PARALLEL_BARS";
                                    }
                                    if (i == EABleMultiData.MotionType.tennis.value) {
                                        return "TENNIS";
                                    }
                                    if (i == EABleMultiData.MotionType.baseball.value) {
                                        return "BASEBALL";
                                    }
                                    if (i == EABleMultiData.MotionType.hockey.value) {
                                        return "HOCKEY";
                                    }
                                    if (i != EABleMultiData.MotionType.mark_time.value) {
                                        if (i == EABleMultiData.MotionType.latin_dance.value) {
                                            return "LATIN_DANCE";
                                        }
                                        if (i == EABleMultiData.MotionType.ballet.value) {
                                            return "BALLET";
                                        }
                                        if (i == EABleMultiData.MotionType.golf.value) {
                                            return "GOLF";
                                        }
                                        if (i == EABleMultiData.MotionType.folk_dance.value) {
                                            return "FOLK_DANCE";
                                        }
                                        if (i == EABleMultiData.MotionType.lacrosse.value) {
                                            return "LACROSSE";
                                        }
                                        if (i == EABleMultiData.MotionType.softball.value) {
                                            return "SOFTBALL";
                                        }
                                        if (i != EABleMultiData.MotionType.parkour.value) {
                                            if (i == EABleMultiData.MotionType.push_up.value) {
                                                return "PUSH_UPS";
                                            }
                                            if (i == EABleMultiData.MotionType.high_jump.value) {
                                                return "HIGH_JUMP";
                                            }
                                            if (i == EABleMultiData.MotionType.long_jump.value) {
                                                return "LONG_JUMP";
                                            }
                                            if (i == EABleMultiData.MotionType.athletics.value) {
                                                return "TRACK_FIELD";
                                            }
                                            if (i == EABleMultiData.MotionType.peak_ball.value) {
                                                return "PICKLEBALL";
                                            }
                                            if (i == EABleMultiData.MotionType.lumbar_abdomen_training.value) {
                                                return "WAIST_TRAINING";
                                            }
                                            if (i == EABleMultiData.MotionType.custom_sport.value) {
                                                return "CUSTOM_SPORT";
                                            }
                                            if (i == EABleMultiData.MotionType.walking_machine.value) {
                                                return "WALKING_MACHINE";
                                            }
                                            if (i == EABleMultiData.MotionType.trampoline.value) {
                                                return "TRAMPOLINE";
                                            }
                                        }
                                    }
                                }
                                return "PARKOUR";
                            }
                        }
                    }
                    return "STEPPER";
                }
                return CoveApiConstants.SWIM;
            }
            return CoveApiConstants.CYCLE;
        }
        return "WALK";
    }

    @Nullable
    public final String getActivitySite(int i) {
        return (i == EABleMultiData.MotionType.ourdoor_walking.value || i == EABleMultiData.MotionType.ourdoor_running.value || i == EABleMultiData.MotionType.ourdoor_cycling.value || i == EABleMultiData.MotionType.outdoor_swimming.value) ? "OUTDOOR" : "INDOOR";
    }

    @NotNull
    public final DialStyle getDialStyle(int i) {
        switch (i) {
            case 0:
                return DialStyle.blackTxt;
            case 1:
                return DialStyle.whiteTxt;
            case 2:
                return DialStyle.blackPointer;
            case 3:
                return DialStyle.whitePointer;
            case 4:
                return DialStyle.blackPointerWithBlackTxt;
            case 5:
                return DialStyle.whitePointerWithWhiteTxt;
            case 6:
                return DialStyle.blackPointerWithBlackScale;
            case 7:
                return DialStyle.whitePointerWithWhiteScale;
            default:
                return DialStyle.whiteTxt;
        }
    }

    @NotNull
    public final EABlePersonInfo.SkinColor getSkinColor(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return EABlePersonInfo.SkinColor.skin_balck;
                    }
                    return EABlePersonInfo.SkinColor.skin_yellow_black;
                }
                return EABlePersonInfo.SkinColor.skin_yellow;
            }
            return EABlePersonInfo.SkinColor.skin_white_yellow;
        }
        return EABlePersonInfo.SkinColor.skin_white;
    }

    @NotNull
    public final TimeZone getTimeZone(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return TimeZone.unknown;
                }
                return TimeZone.west;
            }
            return TimeZone.east;
        }
        return TimeZone.zero;
    }

    public final float getWeatherInFahrenheit(int i) {
        return ((i * 9) / 5) + 32;
    }

    @NotNull
    public final EABleWeather.WeatherType getWeatherType(@NotNull String weatherType) {
        Intrinsics.checkNotNullParameter(weatherType, "weatherType");
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clear", true)) {
            return EABleWeather.WeatherType.clear;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Clouds", true)) {
            return EABleWeather.WeatherType.cloudy;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Rain", true)) {
            return EABleWeather.WeatherType.heavy_rain;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Thunderstorm", true)) {
            return EABleWeather.WeatherType.thunderstorm;
        }
        if (StringsKt__StringsKt.contains((CharSequence) weatherType, (CharSequence) "Snow", true)) {
            return EABleWeather.WeatherType.heavy_snow;
        }
        return EABleWeather.WeatherType.clear;
    }
}
