package com.google.android.gms.internal.fitness;

import com.google.android.gms.fitness.FitnessActivities;
/* loaded from: classes8.dex */
public final class zzko {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f8867a;

    static {
        f8867a = r0;
        String[] strArr = {FitnessActivities.IN_VEHICLE, "biking", FitnessActivities.ON_FOOT, FitnessActivities.STILL, "unknown", FitnessActivities.TILTING, "exiting_vehicle", "walking", FitnessActivities.RUNNING, FitnessActivities.AEROBICS, FitnessActivities.BADMINTON, FitnessActivities.BASEBALL, FitnessActivities.BASKETBALL, FitnessActivities.BIATHLON, FitnessActivities.BIKING_HAND, FitnessActivities.BIKING_MOUNTAIN, FitnessActivities.BIKING_ROAD, FitnessActivities.BIKING_SPINNING, FitnessActivities.BIKING_STATIONARY, FitnessActivities.BIKING_UTILITY, FitnessActivities.BOXING, FitnessActivities.CALISTHENICS, FitnessActivities.CIRCUIT_TRAINING, FitnessActivities.CRICKET, FitnessActivities.DANCING, FitnessActivities.ELLIPTICAL, FitnessActivities.FENCING, FitnessActivities.FOOTBALL_AMERICAN, FitnessActivities.FOOTBALL_AUSTRALIAN, FitnessActivities.FOOTBALL_SOCCER, FitnessActivities.FRISBEE_DISC, FitnessActivities.GARDENING, FitnessActivities.GOLF, FitnessActivities.GYMNASTICS, FitnessActivities.HANDBALL, FitnessActivities.HIKING, FitnessActivities.HOCKEY, FitnessActivities.HORSEBACK_RIDING, FitnessActivities.HOUSEWORK, FitnessActivities.JUMP_ROPE, FitnessActivities.KAYAKING, FitnessActivities.KETTLEBELL_TRAINING, FitnessActivities.KICKBOXING, FitnessActivities.KITESURFING, FitnessActivities.MARTIAL_ARTS, FitnessActivities.MEDITATION, FitnessActivities.MIXED_MARTIAL_ARTS, FitnessActivities.P90X, FitnessActivities.PARAGLIDING, FitnessActivities.PILATES, FitnessActivities.POLO, FitnessActivities.RACQUETBALL, FitnessActivities.ROCK_CLIMBING, FitnessActivities.ROWING, FitnessActivities.ROWING_MACHINE, FitnessActivities.RUGBY, FitnessActivities.RUNNING_JOGGING, FitnessActivities.RUNNING_SAND, FitnessActivities.RUNNING_TREADMILL, FitnessActivities.SAILING, FitnessActivities.SCUBA_DIVING, FitnessActivities.SKATEBOARDING, FitnessActivities.SKATING, FitnessActivities.SKATING_CROSS, FitnessActivities.SKATING_INLINE, FitnessActivities.SKIING, FitnessActivities.SKIING_BACK_COUNTRY, FitnessActivities.SKIING_CROSS_COUNTRY, FitnessActivities.SKIING_DOWNHILL, FitnessActivities.SKIING_KITE, FitnessActivities.SKIING_ROLLER, FitnessActivities.SLEDDING, FitnessActivities.SLEEP, FitnessActivities.SNOWBOARDING, FitnessActivities.SNOWMOBILE, FitnessActivities.SNOWSHOEING, FitnessActivities.SQUASH, FitnessActivities.STAIR_CLIMBING, FitnessActivities.STAIR_CLIMBING_MACHINE, FitnessActivities.STANDUP_PADDLEBOARDING, FitnessActivities.STRENGTH_TRAINING, FitnessActivities.SURFING, FitnessActivities.SWIMMING, FitnessActivities.SWIMMING_POOL, FitnessActivities.SWIMMING_OPEN_WATER, FitnessActivities.TABLE_TENNIS, FitnessActivities.TEAM_SPORTS, FitnessActivities.TENNIS, FitnessActivities.TREADMILL, FitnessActivities.VOLLEYBALL, FitnessActivities.VOLLEYBALL_BEACH, FitnessActivities.VOLLEYBALL_INDOOR, FitnessActivities.WAKEBOARDING, FitnessActivities.WALKING_FITNESS, FitnessActivities.WALKING_NORDIC, FitnessActivities.WALKING_TREADMILL, FitnessActivities.WATER_POLO, FitnessActivities.WEIGHTLIFTING, FitnessActivities.WHEELCHAIR, FitnessActivities.WINDSURFING, FitnessActivities.YOGA, FitnessActivities.ZUMBA, FitnessActivities.DIVING, FitnessActivities.ERGOMETER, FitnessActivities.ICE_SKATING, FitnessActivities.SKATING_INDOOR, FitnessActivities.CURLING, FitnessActivities.KICK_SCOOTER, FitnessActivities.OTHER, FitnessActivities.SLEEP_LIGHT, FitnessActivities.SLEEP_DEEP, FitnessActivities.SLEEP_REM, FitnessActivities.SLEEP_AWAKE, FitnessActivities.CROSSFIT, FitnessActivities.HIGH_INTENSITY_INTERVAL_TRAINING, FitnessActivities.INTERVAL_TRAINING, FitnessActivities.WALKING_STROLLER, FitnessActivities.ELEVATOR, FitnessActivities.ESCALATOR, FitnessActivities.ARCHERY, FitnessActivities.SOFTBALL, "flossing", FitnessActivities.GUIDED_BREATHING};
    }

    public static String getMimeType(String str) {
        String valueOf = String.valueOf(str);
        return valueOf.length() != 0 ? FitnessActivities.MIME_TYPE_PREFIX.concat(valueOf) : new String(FitnessActivities.MIME_TYPE_PREFIX);
    }

    public static String getName(int i) {
        String str;
        if (i >= 0) {
            String[] strArr = f8867a;
            return (i < strArr.length && (str = strArr[i]) != null) ? str : "unknown";
        }
        return "unknown";
    }

    public static int zzo(String str) {
        int i = 0;
        while (true) {
            String[] strArr = f8867a;
            if (i >= strArr.length) {
                return 4;
            }
            if (strArr[i].equals(str)) {
                return i;
            }
            i++;
        }
    }
}
