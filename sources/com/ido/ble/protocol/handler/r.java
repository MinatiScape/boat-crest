package com.ido.ble.protocol.handler;

import androidx.core.app.NotificationCompat;
import com.google.gson.Gson;
import com.ido.ble.callback.QueryStatusCallBack;
import com.ido.ble.callback.SetPressCalibrationCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.AlarmV3CmdParaWrapper;
import com.ido.ble.protocol.model.BloodPressureAdjustDeviceReplyInfo;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.PressCalibrationReplyInfo;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
final class r {
    public static void a(int i, int i2, int i3) {
        SettingCallBack.SettingType settingType;
        if (i == 150) {
            settingType = SettingCallBack.SettingType.WEATHER_SWITCH;
        } else if (i == 151) {
            settingType = SettingCallBack.SettingType.QUICK_SPORT_MODE;
        } else if (i == 3) {
            settingType = SettingCallBack.SettingType.ALARM;
        } else if (i == 154) {
            settingType = SettingCallBack.SettingType.SCREEN_BRIGHTNESS;
        } else if (i == 171) {
            settingType = SettingCallBack.SettingType.MENU_LIST_SET;
        } else if (i == 188) {
            return;
        } else {
            if (i == 513) {
                settingType = SettingCallBack.SettingType.CAMERA_PERMISSION;
            } else if (i == 5011) {
                settingType = SettingCallBack.SettingType.MUSIC_CONTROL_INFO;
            } else if (i != 5013) {
                switch (i) {
                    case 101:
                        settingType = SettingCallBack.SettingType.LONG_SIT;
                        break;
                    case 102:
                        settingType = SettingCallBack.SettingType.ANTI_LOST_MODE;
                        break;
                    case 103:
                        settingType = SettingCallBack.SettingType.FIND_PHONE_SWITCH;
                        break;
                    case 104:
                        settingType = SettingCallBack.SettingType.TIME;
                        break;
                    case 105:
                        settingType = SettingCallBack.SettingType.GOAL;
                        break;
                    default:
                        switch (i) {
                            case 107:
                                settingType = SettingCallBack.SettingType.USER_INFO;
                                break;
                            case 108:
                                settingType = SettingCallBack.SettingType.UNIT;
                                break;
                            case 109:
                                settingType = SettingCallBack.SettingType.HAND_MODE;
                                break;
                            default:
                                switch (i) {
                                    case 111:
                                        settingType = SettingCallBack.SettingType.NOTICE_REMINDER_SWITCH_STATUS;
                                        break;
                                    case 112:
                                        settingType = SettingCallBack.SettingType.HEART_RATE_INTERVAL;
                                        break;
                                    case 113:
                                        settingType = SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE;
                                        break;
                                    case 114:
                                        settingType = SettingCallBack.SettingType.UP_HAND_GESTURE;
                                        break;
                                    case 115:
                                        settingType = SettingCallBack.SettingType.ONE_KEY_RESET;
                                        break;
                                    case 116:
                                        settingType = SettingCallBack.SettingType.NOT_DISTURB;
                                        break;
                                    case 117:
                                        settingType = SettingCallBack.SettingType.MUSIC_SWITCH;
                                        break;
                                    case 118:
                                        settingType = SettingCallBack.SettingType.DISPLAY_MODE;
                                        break;
                                    case 119:
                                        settingType = SettingCallBack.SettingType.ONE_KEY_SOS;
                                        break;
                                    default:
                                        switch (i) {
                                            case 124:
                                                settingType = SettingCallBack.SettingType.DIAL_PLATE;
                                                break;
                                            case 125:
                                                settingType = SettingCallBack.SettingType.SHORTCUT;
                                                break;
                                            case 126:
                                                settingType = SettingCallBack.SettingType.BLOOD_ADJUST;
                                                break;
                                            default:
                                                switch (i) {
                                                    case 165:
                                                        settingType = SettingCallBack.SettingType.WALK_REMINDER;
                                                        break;
                                                    case 166:
                                                        settingType = SettingCallBack.SettingType.BREATHE_TRAIN;
                                                        break;
                                                    case 167:
                                                        settingType = SettingCallBack.SettingType.ACTIVITY_SWITCH;
                                                        break;
                                                    case 168:
                                                        settingType = SettingCallBack.SettingType.DRINK_WATER_REMINDER;
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 184:
                                                                settingType = SettingCallBack.SettingType.SLEEP_MONITORING;
                                                                break;
                                                            case 185:
                                                                settingType = SettingCallBack.SettingType.NIGHT_TEMPERATURE_MONITORING;
                                                                break;
                                                            case 186:
                                                                return;
                                                            default:
                                                                if (i == 191) {
                                                                    settingType = SettingCallBack.SettingType.RESPIRETORY_RATE;
                                                                    break;
                                                                } else if (i == 192) {
                                                                    settingType = SettingCallBack.SettingType.BODYPOWERSWITCH;
                                                                    break;
                                                                } else {
                                                                    return;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
            } else {
                settingType = SettingCallBack.SettingType.SPORT_SORT_V3;
            }
        }
        a(i2, settingType);
    }

    private static void a(int i, SettingCallBack.SettingType settingType) {
        SettingCallBack.a(i == 0 ? Boolean.TRUE : Boolean.FALSE, settingType, null);
    }

    public static void a(int i, byte[] bArr, int i2) {
        String d;
        SettingCallBack.SettingType settingType;
        String d2;
        SettingCallBack.SettingType settingType2;
        Boolean valueOf;
        SettingCallBack.SettingType settingType3;
        if (i == 126) {
            BloodPressureAdjustDeviceReplyInfo bloodPressureAdjustDeviceReplyInfo = (BloodPressureAdjustDeviceReplyInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) BloodPressureAdjustDeviceReplyInfo.class);
            if (bloodPressureAdjustDeviceReplyInfo != null) {
                QueryStatusCallBack.onQueryBloodAdjustResult(bloodPressureAdjustDeviceReplyInfo.toResult());
                return;
            }
            return;
        }
        if (i == 5052) {
            d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set Menstruation HistoricalData JsonString] " + d);
            settingType = SettingCallBack.SettingType.MENSTRUATION_HISTORICAL_DATA;
        } else if (i != 5053) {
            if (i == 128) {
                PressCalibrationReplyInfo pressCalibrationReplyInfo = (PressCalibrationReplyInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) PressCalibrationReplyInfo.class);
                if (pressCalibrationReplyInfo != null) {
                    SetPressCalibrationCallBack.onSetPressCalibrationResult(pressCalibrationReplyInfo.toResult());
                    return;
                }
                return;
            }
            if (i == 5010) {
                String d3 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return V3HeartRateMode JsonString] " + d3);
                HeartRateMeasureModeV3 heartRateMeasureModeV3 = (HeartRateMeasureModeV3) new Gson().fromJson(d3, (Class<Object>) HeartRateMeasureModeV3.class);
                if (heartRateMeasureModeV3 != null) {
                    if (i2 == 0) {
                        SettingCallBack.a(Boolean.TRUE, SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3, heartRateMeasureModeV3);
                        return;
                    }
                    return;
                }
                valueOf = Boolean.FALSE;
                settingType3 = SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3;
            } else if (i == 5011) {
                String d4 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return MusicControlInfo JsonString] " + d4);
                d(d4, SettingCallBack.SettingType.MUSIC_CONTROL_INFO);
                return;
            } else if (i != 5017) {
                if (i == 5013) {
                    a(i2, SettingCallBack.SettingType.SPORT_SORT_V3);
                    return;
                }
                if (i == 408) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return restore factory JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.RESTORE_FACTORY;
                } else if (i == 174) {
                    String d5 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return set phone voice JsonString] " + d5);
                    c(d5, SettingCallBack.SettingType.PHONE_VOICE);
                    return;
                } else if (i == 5020) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return set quick reply info JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.QUICK_REPLY_INFO;
                } else if (i == 177) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set medicine reminder JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.MEDICINE_REMINDER;
                } else if (i == 175) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set wash hand reminder JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.WASH_HAND_REMINDER;
                } else if (i == 6500) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set weather city name JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.WEATHER_CITY_NAME;
                } else if (i == 184) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set sleep monitoring para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.SLEEP_MONITORING;
                } else if (i == 185) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set night temperature monitoring para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.NIGHT_TEMPERATURE_MONITORING;
                } else if (i == 186) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set fitness guidance para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.FITNESS_GUIDANCE;
                } else if (i == 188) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set Device Unread Reminder para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER;
                } else if (i == 5047) {
                    d = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set Walk realTime Reminder para JsonString] " + d);
                    settingType = SettingCallBack.SettingType.WALK_REAL_TIME_REMINDER;
                } else if (i == 190) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set schedule reminder switch para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.SCHEDULE_REMINDER_SWITCH;
                } else if (i == 183) {
                    d2 = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set noise para JsonString] " + d2);
                    settingType2 = SettingCallBack.SettingType.NOISE;
                } else if (i == 5046) {
                    d = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set world time JsonString] " + d);
                    settingType = SettingCallBack.SettingType.WORLD_TIME;
                } else if (i == 182) {
                    d = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set heart rate smart JsonString] " + d);
                    settingType = SettingCallBack.SettingType.HEART_RATE_SMART;
                } else if (i == 511) {
                    d = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set Icon Information JsonString] " + d);
                    settingType = SettingCallBack.SettingType.ICON_INFORMATION_NOTICE;
                } else if (i != 129) {
                    return;
                } else {
                    d = com.ido.ble.common.c.d(bArr);
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set stop find phone JsonString] " + d);
                    settingType = SettingCallBack.SettingType.STOP_FIND_PHONE;
                }
                b(d2, settingType2);
                return;
            } else {
                String d6 = com.ido.ble.common.c.d(bArr);
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle Device Return setAlarmV3 JsonString] " + d6);
                AlarmV3CmdParaWrapper.AlarmSetResponse alarmSetResponse = (AlarmV3CmdParaWrapper.AlarmSetResponse) new Gson().fromJson(d6, (Class<Object>) AlarmV3CmdParaWrapper.AlarmSetResponse.class);
                if (alarmSetResponse == null) {
                    valueOf = Boolean.FALSE;
                } else {
                    valueOf = Boolean.valueOf(alarmSetResponse.status == 0);
                }
                settingType3 = SettingCallBack.SettingType.ALARM_V3;
            }
            SettingCallBack.a(valueOf, settingType3, null);
            return;
        } else {
            d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SET_PARA] [handle set WatchDial Order JsonString] " + d);
            settingType = SettingCallBack.SettingType.WATCH_DIAL_ORDER;
        }
        a(d, settingType);
    }

    private static void a(String str, SettingCallBack.SettingType settingType) {
        int i;
        try {
            i = new JSONObject(str).optInt("err_code");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.e + e.getMessage());
            i = -1;
        }
        SettingCallBack.a(Boolean.valueOf(i == 0), settingType, null);
    }

    public static boolean a(int i) {
        if (i == 128 || i == 129 || i == 150 || i == 151 || i == 174 || i == 175 || i == 5010 || i == 5011 || i == 5046 || i == 5047) {
            return true;
        }
        switch (i) {
            case 3:
            case 154:
            case 171:
            case 177:
            case 188:
            case com.veryfit.multi.nativeprotocol.b.B1 /* 408 */:
            case 511:
            case 513:
            case 5013:
            case com.veryfit.multi.nativeprotocol.b.x3 /* 5017 */:
            case 5020:
            case com.veryfit.multi.nativeprotocol.b.Y3 /* 5053 */:
            case com.veryfit.multi.nativeprotocol.b.s4 /* 6500 */:
                return true;
            default:
                switch (i) {
                    case 111:
                    case 112:
                    case 113:
                    case 114:
                    case 115:
                    case 116:
                    case 117:
                    case 118:
                    case 119:
                    case 120:
                    case 121:
                    case 122:
                    case 123:
                    case 124:
                    case 125:
                    case 126:
                        return true;
                    default:
                        switch (i) {
                            case 100:
                            case 101:
                            case 102:
                            case 103:
                            case 104:
                            case 105:
                            case 106:
                            case 107:
                            case 108:
                            case 109:
                                return true;
                            default:
                                switch (i) {
                                    case 165:
                                    case 166:
                                    case 167:
                                    case 168:
                                        return true;
                                    default:
                                        switch (i) {
                                            case 182:
                                            case 183:
                                            case 184:
                                            case 185:
                                            case 186:
                                                return true;
                                            default:
                                                switch (i) {
                                                    case 190:
                                                    case 191:
                                                    case 192:
                                                        return true;
                                                    default:
                                                        return false;
                                                }
                                        }
                                }
                        }
                }
        }
    }

    private static void b(String str, SettingCallBack.SettingType settingType) {
        int i;
        try {
            i = new JSONObject(str).optInt("is_success");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.e + e.getMessage());
            i = 0;
        }
        SettingCallBack.a(Boolean.valueOf(i == 1), settingType, null);
    }

    private static void c(String str, SettingCallBack.SettingType settingType) {
        int i;
        try {
            i = new JSONObject(str).optInt("state");
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.e + e.getMessage());
            i = 0;
        }
        SettingCallBack.a(Boolean.valueOf(i == 1), settingType, null);
    }

    private static void d(String str, SettingCallBack.SettingType settingType) {
        int i;
        try {
            i = new JSONObject(str).optInt(NotificationCompat.CATEGORY_STATUS);
        } catch (JSONException e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, com.ido.ble.logs.a.e + e.getMessage());
            i = 0;
        }
        SettingCallBack.a(Boolean.valueOf(i == 1), settingType, null);
    }
}
