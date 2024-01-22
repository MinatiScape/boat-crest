package com.google.android.gms.fitness.data;

import androidx.annotation.Nullable;
import com.alibaba.fastjson.parser.JSONLexer;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
@ShowFirstParty
/* loaded from: classes6.dex */
public final class zzl {
    static {
        Collections.unmodifiableSet(new HashSet(Arrays.asList(DataType.TYPE_MOVE_MINUTES, DataType.AGGREGATE_MOVE_MINUTES, DataType.TYPE_WORKOUT_EXERCISE, DataType.zzmj, DataType.TYPE_ACTIVITY_SEGMENT, DataType.AGGREGATE_ACTIVITY_SUMMARY, HealthDataTypes.TYPE_BLOOD_GLUCOSE, HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY, HealthDataTypes.TYPE_BLOOD_PRESSURE, HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY, DataType.TYPE_BODY_FAT_PERCENTAGE, DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY, HealthDataTypes.TYPE_BODY_TEMPERATURE, HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE, HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY, HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY, DataType.TYPE_BASAL_METABOLIC_RATE, DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY, DataType.zzmk, DataType.zzml, DataType.TYPE_CALORIES_EXPENDED, DataType.AGGREGATE_CALORIES_EXPENDED, HealthDataTypes.TYPE_CERVICAL_MUCUS, HealthDataTypes.TYPE_CERVICAL_POSITION, DataType.TYPE_CYCLING_PEDALING_CADENCE, DataType.TYPE_CYCLING_PEDALING_CUMULATIVE, DataType.TYPE_CYCLING_WHEEL_REVOLUTION, DataType.TYPE_CYCLING_WHEEL_RPM, DataType.zzmh, DataType.zzmg, DataType.TYPE_DISTANCE_DELTA, DataType.AGGREGATE_DISTANCE_DELTA, DataType.TYPE_HEART_POINTS, DataType.AGGREGATE_HEART_POINTS, DataType.TYPE_HEART_RATE_BPM, DataType.AGGREGATE_HEART_RATE_SUMMARY, DataType.TYPE_HEIGHT, DataType.AGGREGATE_HEIGHT_SUMMARY, DataType.TYPE_HYDRATION, DataType.AGGREGATE_HYDRATION, DataType.zzmd, DataType.zzmi, DataType.zzmm, DataType.zzmn, DataType.AGGREGATE_LOCATION_BOUNDING_BOX, DataType.TYPE_LOCATION_SAMPLE, DataType.TYPE_LOCATION_TRACK, HealthDataTypes.TYPE_MENSTRUATION, DataType.TYPE_NUTRITION, DataType.AGGREGATE_NUTRITION_SUMMARY, HealthDataTypes.TYPE_OVULATION_TEST, HealthDataTypes.TYPE_OXYGEN_SATURATION, HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY, DataType.TYPE_POWER_SAMPLE, DataType.AGGREGATE_POWER_SUMMARY, DataType.zzmf, DataType.zzme, DataType.TYPE_SLEEP_SEGMENT, DataType.TYPE_SPEED, DataType.AGGREGATE_SPEED_SUMMARY, DataType.TYPE_STEP_COUNT_CADENCE, DataType.TYPE_STEP_COUNT_CUMULATIVE, DataType.TYPE_STEP_COUNT_DELTA, DataType.AGGREGATE_STEP_COUNT_DELTA, HealthDataTypes.TYPE_VAGINAL_SPOTTING, DataType.TYPE_WEIGHT, DataType.AGGREGATE_WEIGHT_SUMMARY)));
    }

    @Nullable
    public static DataType zzb(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2060095039:
                if (str.equals("com.google.cycling.wheel_revolution.rpm")) {
                    c = 0;
                    break;
                }
                break;
            case -2032495331:
                if (str.equals("com.google.respiratory_rate")) {
                    c = 1;
                    break;
                }
                break;
            case -2027664088:
                if (str.equals("com.google.calories.consumed")) {
                    c = 2;
                    break;
                }
                break;
            case -2023954015:
                if (str.equals("com.google.location.bounding_box")) {
                    c = 3;
                    break;
                }
                break;
            case -1999891138:
                if (str.equals("com.google.heart_minutes")) {
                    c = 4;
                    break;
                }
                break;
            case -1939429191:
                if (str.equals("com.google.blood_glucose.summary")) {
                    c = 5;
                    break;
                }
                break;
            case -1757812901:
                if (str.equals("com.google.location.sample")) {
                    c = 6;
                    break;
                }
                break;
            case -1659958877:
                if (str.equals("com.google.menstruation")) {
                    c = 7;
                    break;
                }
                break;
            case -1487055015:
                if (str.equals("com.google.body.temperature.basal.summary")) {
                    c = '\b';
                    break;
                }
                break;
            case -1465729060:
                if (str.equals("com.google.internal.primary_device")) {
                    c = '\t';
                    break;
                }
                break;
            case -1431431801:
                if (str.equals("com.google.height.summary")) {
                    c = '\n';
                    break;
                }
                break;
            case -1416335448:
                if (str.equals("com.google.internal.sleep_schedule")) {
                    c = 11;
                    break;
                }
                break;
            case -1248818137:
                if (str.equals("com.google.distance.delta")) {
                    c = '\f';
                    break;
                }
                break;
            case -1103712522:
                if (str.equals("com.google.heart_minutes.summary")) {
                    c = '\r';
                    break;
                }
                break;
            case -1102520626:
                if (str.equals("com.google.step_count.delta")) {
                    c = 14;
                    break;
                }
                break;
            case -1091068721:
                if (str.equals("com.google.height")) {
                    c = 15;
                    break;
                }
                break;
            case -922976890:
                if (str.equals("com.google.cycling.pedaling.cumulative")) {
                    c = 16;
                    break;
                }
                break;
            case -900592674:
                if (str.equals("com.google.cycling.pedaling.cadence")) {
                    c = 17;
                    break;
                }
                break;
            case -886569606:
                if (str.equals("com.google.location.track")) {
                    c = 18;
                    break;
                }
                break;
            case -777285735:
                if (str.equals("com.google.heart_rate.summary")) {
                    c = 19;
                    break;
                }
                break;
            case -700668164:
                if (str.equals("com.google.internal.goal")) {
                    c = 20;
                    break;
                }
                break;
            case -661631456:
                if (str.equals("com.google.weight")) {
                    c = 21;
                    break;
                }
                break;
            case -424876584:
                if (str.equals("com.google.weight.summary")) {
                    c = 22;
                    break;
                }
                break;
            case -362418992:
                if (str.equals("com.google.body.temperature")) {
                    c = 23;
                    break;
                }
                break;
            case -316596620:
                if (str.equals("com.google.sleep.segment")) {
                    c = 24;
                    break;
                }
                break;
            case -217611775:
                if (str.equals("com.google.blood_glucose")) {
                    c = 25;
                    break;
                }
                break;
            case -185830635:
                if (str.equals("com.google.power.summary")) {
                    c = JSONLexer.EOI;
                    break;
                }
                break;
            case -177293656:
                if (str.equals("com.google.nutrition.summary")) {
                    c = 27;
                    break;
                }
                break;
            case -164586193:
                if (str.equals("com.google.activity.exercise")) {
                    c = 28;
                    break;
                }
                break;
            case -98150574:
                if (str.equals("com.google.heart_rate.bpm")) {
                    c = 29;
                    break;
                }
                break;
            case -56824761:
                if (str.equals("com.google.calories.bmr")) {
                    c = 30;
                    break;
                }
                break;
            case 53773386:
                if (str.equals("com.google.blood_pressure.summary")) {
                    c = 31;
                    break;
                }
                break;
            case 269180370:
                if (str.equals("com.google.activity.samples")) {
                    c = ' ';
                    break;
                }
                break;
            case 295793957:
                if (str.equals("com.google.sensor.events")) {
                    c = '!';
                    break;
                }
                break;
            case 296250623:
                if (str.equals("com.google.calories.bmr.summary")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case 324760871:
                if (str.equals("com.google.step_count.cadence")) {
                    c = '#';
                    break;
                }
                break;
            case 378060028:
                if (str.equals("com.google.activity.segment")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case 529727579:
                if (str.equals("com.google.power.sample")) {
                    c = '%';
                    break;
                }
                break;
            case 657433501:
                if (str.equals("com.google.step_count.cumulative")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case 682891187:
                if (str.equals("com.google.body.fat.percentage")) {
                    c = '\'';
                    break;
                }
                break;
            case 841663855:
                if (str.equals("com.google.activity.summary")) {
                    c = HexStringBuilder.COMMENT_BEGIN_CHAR;
                    break;
                }
                break;
            case 877955159:
                if (str.equals("com.google.speed.summary")) {
                    c = HexStringBuilder.COMMENT_END_CHAR;
                    break;
                }
                break;
            case 899666941:
                if (str.equals("com.google.calories.expended")) {
                    c = '*';
                    break;
                }
                break;
            case 936279698:
                if (str.equals("com.google.blood_pressure")) {
                    c = '+';
                    break;
                }
                break;
            case 946706510:
                if (str.equals("com.google.hydration")) {
                    c = ',';
                    break;
                }
                break;
            case 1029221057:
                if (str.equals("com.google.device_on_body")) {
                    c = Soundex.SILENT_MARKER;
                    break;
                }
                break;
            case 1111714923:
                if (str.equals("com.google.body.fat.percentage.summary")) {
                    c = '.';
                    break;
                }
                break;
            case 1214093899:
                if (str.equals("com.google.vaginal_spotting")) {
                    c = '/';
                    break;
                }
                break;
            case 1404118825:
                if (str.equals("com.google.oxygen_saturation")) {
                    c = '0';
                    break;
                }
                break;
            case 1439932546:
                if (str.equals("com.google.ovulation_test")) {
                    c = '1';
                    break;
                }
                break;
            case 1483133089:
                if (str.equals("com.google.body.temperature.basal")) {
                    c = '2';
                    break;
                }
                break;
            case 1498973736:
                if (str.equals("com.google.internal.sleep_attributes")) {
                    c = '3';
                    break;
                }
                break;
            case 1524007137:
                if (str.equals("com.google.cycling.wheel_revolution.cumulative")) {
                    c = '4';
                    break;
                }
                break;
            case 1532018766:
                if (str.equals("com.google.active_minutes")) {
                    c = '5';
                    break;
                }
                break;
            case 1633152752:
                if (str.equals("com.google.nutrition")) {
                    c = '6';
                    break;
                }
                break;
            case 1921738212:
                if (str.equals("com.google.distance.cumulative")) {
                    c = '7';
                    break;
                }
                break;
            case 1925848149:
                if (str.equals("com.google.cervical_position")) {
                    c = '8';
                    break;
                }
                break;
            case 1975902189:
                if (str.equals("com.google.cervical_mucus")) {
                    c = '9';
                    break;
                }
                break;
            case 2051843553:
                if (str.equals("com.google.oxygen_saturation.summary")) {
                    c = ':';
                    break;
                }
                break;
            case 2053496735:
                if (str.equals("com.google.speed")) {
                    c = ';';
                    break;
                }
                break;
            case 2131809416:
                if (str.equals("com.google.body.temperature.summary")) {
                    c = Typography.less;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return DataType.TYPE_CYCLING_WHEEL_RPM;
            case 1:
                return DataType.zzmf;
            case 2:
                return DataType.zzmk;
            case 3:
                return DataType.AGGREGATE_LOCATION_BOUNDING_BOX;
            case 4:
                return DataType.TYPE_HEART_POINTS;
            case 5:
                return HealthDataTypes.AGGREGATE_BLOOD_GLUCOSE_SUMMARY;
            case 6:
                return DataType.TYPE_LOCATION_SAMPLE;
            case 7:
                return HealthDataTypes.TYPE_MENSTRUATION;
            case '\b':
                return HealthDataTypes.AGGREGATE_BASAL_BODY_TEMPERATURE_SUMMARY;
            case '\t':
                return DataType.zzmi;
            case '\n':
                return DataType.AGGREGATE_HEIGHT_SUMMARY;
            case 11:
                return DataType.zzmn;
            case '\f':
                return DataType.TYPE_DISTANCE_DELTA;
            case '\r':
                return DataType.AGGREGATE_HEART_POINTS;
            case 14:
                return DataType.TYPE_STEP_COUNT_DELTA;
            case 15:
                return DataType.TYPE_HEIGHT;
            case 16:
                return DataType.TYPE_CYCLING_PEDALING_CUMULATIVE;
            case 17:
                return DataType.TYPE_CYCLING_PEDALING_CADENCE;
            case 18:
                return DataType.TYPE_LOCATION_TRACK;
            case 19:
                return DataType.AGGREGATE_HEART_RATE_SUMMARY;
            case 20:
                return DataType.zzmd;
            case 21:
                return DataType.TYPE_WEIGHT;
            case 22:
                return DataType.AGGREGATE_WEIGHT_SUMMARY;
            case 23:
                return HealthDataTypes.TYPE_BODY_TEMPERATURE;
            case 24:
                return DataType.TYPE_SLEEP_SEGMENT;
            case 25:
                return HealthDataTypes.TYPE_BLOOD_GLUCOSE;
            case 26:
                return DataType.AGGREGATE_POWER_SUMMARY;
            case 27:
                return DataType.AGGREGATE_NUTRITION_SUMMARY;
            case 28:
                return DataType.TYPE_WORKOUT_EXERCISE;
            case 29:
                return DataType.TYPE_HEART_RATE_BPM;
            case 30:
                return DataType.TYPE_BASAL_METABOLIC_RATE;
            case 31:
                return HealthDataTypes.AGGREGATE_BLOOD_PRESSURE_SUMMARY;
            case ' ':
                return DataType.zzmj;
            case '!':
                return DataType.zzme;
            case '\"':
                return DataType.AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
            case '#':
                return DataType.TYPE_STEP_COUNT_CADENCE;
            case '$':
                return DataType.TYPE_ACTIVITY_SEGMENT;
            case '%':
                return DataType.TYPE_POWER_SAMPLE;
            case '&':
                return DataType.TYPE_STEP_COUNT_CUMULATIVE;
            case '\'':
                return DataType.TYPE_BODY_FAT_PERCENTAGE;
            case '(':
                return DataType.AGGREGATE_ACTIVITY_SUMMARY;
            case ')':
                return DataType.AGGREGATE_SPEED_SUMMARY;
            case '*':
                return DataType.TYPE_CALORIES_EXPENDED;
            case '+':
                return HealthDataTypes.TYPE_BLOOD_PRESSURE;
            case ',':
                return DataType.TYPE_HYDRATION;
            case '-':
                return DataType.zzmh;
            case '.':
                return DataType.AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;
            case '/':
                return HealthDataTypes.TYPE_VAGINAL_SPOTTING;
            case '0':
                return HealthDataTypes.TYPE_OXYGEN_SATURATION;
            case '1':
                return HealthDataTypes.TYPE_OVULATION_TEST;
            case '2':
                return HealthDataTypes.TYPE_BASAL_BODY_TEMPERATURE;
            case '3':
                return DataType.zzmm;
            case '4':
                return DataType.TYPE_CYCLING_WHEEL_REVOLUTION;
            case '5':
                return DataType.TYPE_MOVE_MINUTES;
            case '6':
                return DataType.TYPE_NUTRITION;
            case '7':
                return DataType.zzmg;
            case '8':
                return HealthDataTypes.TYPE_CERVICAL_POSITION;
            case '9':
                return HealthDataTypes.TYPE_CERVICAL_MUCUS;
            case ':':
                return HealthDataTypes.AGGREGATE_OXYGEN_SATURATION_SUMMARY;
            case ';':
                return DataType.TYPE_SPEED;
            case '<':
                return HealthDataTypes.AGGREGATE_BODY_TEMPERATURE_SUMMARY;
            default:
                return null;
        }
    }
}
