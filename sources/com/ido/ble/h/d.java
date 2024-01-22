package com.ido.ble.h;

import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.LocalDataManager;
import com.ido.ble.common.TimeUtil;
import com.ido.ble.common.k;
import com.ido.ble.gps.callback.GpsCallBack;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.model.ConnParamReply;
import com.ido.ble.gps.model.ControlGpsReply;
import com.ido.ble.gps.model.GPSInfo;
import com.ido.ble.gps.model.GpsDataReply;
import com.ido.ble.gps.model.GpsHotStartParam;
import com.ido.ble.gps.model.GpsStatus;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class d {
    private static List<HealthGpsItem> a(GpsDataReply gpsDataReply) {
        double d;
        ArrayList arrayList = new ArrayList();
        for (String str : gpsDataReply.items) {
            if (str.contains(Constants.SEPARATOR_COMMA)) {
                String[] split = str.split(Constants.SEPARATOR_COMMA);
                double d2 = 0.0d;
                try {
                    d = c.a(split[0], ExifInterface.LONGITUDE_EAST);
                } catch (Exception e) {
                    e = e;
                    d = 0.0d;
                }
                try {
                    d2 = c.a(split[1], "N");
                } catch (Exception e2) {
                    e = e2;
                    LogTool.b(com.ido.ble.logs.a.j, "[handleGpsItemData]" + e.toString());
                    HealthGpsItem healthGpsItem = new HealthGpsItem();
                    healthGpsItem.setDate(Long.valueOf(gpsDataReply.date));
                    healthGpsItem.setLongitude(Double.valueOf(d));
                    healthGpsItem.setLatitude(Double.valueOf(d2));
                    arrayList.add(healthGpsItem);
                }
                HealthGpsItem healthGpsItem2 = new HealthGpsItem();
                healthGpsItem2.setDate(Long.valueOf(gpsDataReply.date));
                healthGpsItem2.setLongitude(Double.valueOf(d));
                healthGpsItem2.setLatitude(Double.valueOf(d2));
                arrayList.add(healthGpsItem2);
            }
        }
        LogTool.d(com.ido.ble.logs.a.j, "[handleGpsItemData] size = " + arrayList.size());
        if (arrayList.size() > 0) {
            com.ido.ble.gps.database.a.a(arrayList);
        }
        return arrayList;
    }

    private static void a(int i) {
        GpsCallBack.a(i == 0);
    }

    public static void a(int i, int i2, int i3) {
        StringBuilder sb;
        String str;
        String sb2;
        if (i == 20) {
            GpsCallBack.b(i3);
        } else if (i == 21) {
            if (i2 == 0) {
                GpsCallBack.d();
            } else {
                GpsCallBack.a(i2);
            }
        } else if (i == 158) {
            if (i2 != 0) {
                GpsCallBack.a(false);
            }
        } else if (i == 313) {
            if (i2 != 0) {
                GpsCallBack.a((GpsHotStartParam) null);
            }
        } else if (i == 653) {
            LogTool.d(com.ido.ble.logs.a.j, "sync gps finished, error = " + i2);
            if (i2 == 0) {
                GpsCallBack.f();
            } else {
                GpsCallBack.e();
            }
        } else if (i == 5530) {
            if (i2 == 0) {
                if (i3 == 0 || i3 == 3) {
                    GpsCallBack.c();
                } else if (i3 == 1) {
                    GpsCallBack.b();
                } else if (i3 == 2) {
                    GpsCallBack.a();
                }
            }
        } else {
            switch (i) {
                case 15:
                    GpsCallBack.e(i3);
                    return;
                case 16:
                    if (i2 == 0) {
                        GpsCallBack.h();
                        sb2 = "onTranAGpsFileFinish success error = 0";
                    } else {
                        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
                        if (i2 == 24 || i2 == 25) {
                            GpsCallBack.a(i2, Integer.valueOf(i3));
                            sb = new StringBuilder();
                            str = "onTranAGpsFileFailed erroe :";
                        } else if (supportFunctionInfo == null || !supportFunctionInfo.v3_support_data_tran_get_new_error_code) {
                            GpsCallBack.d(i2);
                            sb = new StringBuilder();
                            str = "onTranAGpsFileFailed use error =";
                        } else {
                            GpsCallBack.d(i3);
                            sb = new StringBuilder();
                            sb.append("onTranAGpsFileFailed use value =");
                            sb.append(i3);
                            sb2 = sb.toString();
                        }
                        sb.append(str);
                        sb.append(i2);
                        sb2 = sb.toString();
                    }
                    LogTool.d(com.ido.ble.logs.a.j, sb2);
                    return;
                case 17:
                    GpsCallBack.c(i3);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 6004) {
            b(bArr);
            return;
        }
        switch (i) {
            case 155:
                a(i2);
                return;
            case 156:
                a(bArr);
                return;
            case 157:
                f(bArr);
                return;
            case 158:
                b(i2);
                return;
            default:
                switch (i) {
                    case 312:
                        c(bArr);
                        return;
                    case 313:
                        e(bArr);
                        return;
                    case 314:
                        d(bArr);
                        return;
                    default:
                        return;
                }
        }
    }

    private static void a(byte[] bArr) {
        GpsCallBack.a((ControlGpsReply) k.c(com.ido.ble.common.c.d(bArr), ControlGpsReply.class));
    }

    private static void b(int i) {
        GpsCallBack.b(i == 0);
    }

    private static void b(byte[] bArr) {
        List<String> list;
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.j, "[handleReplyGpsData] json data =" + d);
        GpsDataReply gpsDataReply = (GpsDataReply) k.c(d, GpsDataReply.class);
        if (gpsDataReply == null) {
            LogTool.b(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply is null");
        } else if (gpsDataReply.year == 0 || (list = gpsDataReply.items) == null || list.size() == 0) {
            LogTool.b(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply.year = 0");
        } else {
            int i = gpsDataReply.year;
            if (i < 2000) {
                gpsDataReply.year = i + 2000;
            }
            gpsDataReply.date = TimeUtil.dateToStamp(gpsDataReply.year, gpsDataReply.month, gpsDataReply.day, gpsDataReply.hour, gpsDataReply.minute, gpsDataReply.second);
            HealthGps healthGps = new HealthGps();
            healthGps.setYear(Integer.valueOf(gpsDataReply.year));
            healthGps.setMonth(Integer.valueOf(gpsDataReply.month));
            healthGps.setDay(Integer.valueOf(gpsDataReply.day));
            healthGps.setHour(Integer.valueOf(gpsDataReply.hour));
            healthGps.setMinute(Integer.valueOf(gpsDataReply.minute));
            healthGps.setSecond(Integer.valueOf(gpsDataReply.second));
            healthGps.setData_interval(Integer.valueOf(gpsDataReply.data_interval));
            healthGps.setDate(Long.valueOf(gpsDataReply.date));
            com.ido.ble.gps.database.a.a(healthGps);
            LogTool.d(com.ido.ble.logs.a.j, "[handleReplyGpsData] gpsDataReply is " + gpsDataReply.toString());
            GpsCallBack.a(healthGps, a(gpsDataReply), false);
        }
    }

    private static void c(byte[] bArr) {
        GpsCallBack.a((GPSInfo) k.c(com.ido.ble.common.c.d(bArr), GPSInfo.class));
    }

    public static boolean c(int i) {
        if (i == 20 || i == 21 || i == 653 || i == 5530 || i == 6004) {
            return true;
        }
        switch (i) {
            case 15:
            case 16:
            case 17:
                return true;
            default:
                switch (i) {
                    case 155:
                    case 156:
                    case 157:
                    case 158:
                        return true;
                    default:
                        switch (i) {
                            case 312:
                            case 313:
                            case 314:
                                return true;
                            default:
                                return false;
                        }
                }
        }
    }

    private static void d(byte[] bArr) {
        GpsStatus gpsStatus = (GpsStatus) k.c(com.ido.ble.common.c.d(bArr), GpsStatus.class);
        if (gpsStatus != null) {
            GpsCallBack.a(gpsStatus);
        }
    }

    private static void e(byte[] bArr) {
        GpsCallBack.a((GpsHotStartParam) k.c(com.ido.ble.common.c.d(bArr), GpsHotStartParam.class));
    }

    private static void f(byte[] bArr) {
        GpsCallBack.a((ConnParamReply) k.c(com.ido.ble.common.c.d(bArr), ConnParamReply.class));
    }
}
