package com.realsil.sdk.dfu.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.google.android.gms.common.ConnectionResult;
import com.jstyle.blesdk1860.constant.BleConst;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.image.wrapper.BinImageWrapper;
import java.io.IOException;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes12.dex */
public class DfuUtils {
    public static int IMAGE_VERSION_FORMAT;
    public static final int IMAGE_VERSION_FORMAT_AUTO = 0;

    public static int binarySearch(int[] iArr, int i) {
        if (iArr == null || iArr.length <= 0) {
            return -1;
        }
        int i2 = 0;
        int length = iArr.length - 1;
        while (i2 <= length) {
            int i3 = (i2 + length) >>> 1;
            int i4 = iArr[i3];
            if (i4 < i) {
                i2 = i3 + 1;
            } else if (i4 <= i) {
                return i3;
            } else {
                length = i3 - 1;
            }
        }
        return ~i2;
    }

    public static String convertVersion2Str(int i, int i2) {
        return convertVersion2Str(0, i, i2);
    }

    public static String formatBatteryLevel(int i) {
        return String.format(Locale.US, "%d%%", Integer.valueOf(i));
    }

    public static String formatImageVersionWithBinId(int i, int i2, int i3, int i4) {
        return new BinImageWrapper.Builder().setOtaVersion(i3).icType(i).binId(i2).imageVersion(i4, 2).build().getFormatedVersion();
    }

    public static String formatImageVersionWithBitNumber(int i, int i2, int i3, int i4) {
        return new BinImageWrapper.Builder().setOtaVersion(i3).imageVersion(i4).icType(i).bitNumber(i2).imageVersion(i4, 1).build().getFormatedVersion();
    }

    public static String formatLinkKey(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if ((bArr[i] & 255) <= 15) {
                sb.append(BleConst.GetDeviceTime + Integer.toHexString(bArr[i] & 255).toUpperCase());
            } else {
                sb.append(Integer.toHexString(bArr[i] & 255).toUpperCase());
            }
            if (i < length - 1) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static String formatManufacturerAddr(String str) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[17];
        int i = 0;
        int i2 = 0;
        while (i < 17) {
            int i3 = i + 1;
            if (i3 % 3 == 0) {
                cArr[i] = ':';
            } else {
                cArr[i] = charArray[i2];
                i2++;
            }
            i = i3;
        }
        return String.valueOf(cArr);
    }

    public static String getAssetsFileName(String str) {
        int lastIndexOf = str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        if (lastIndexOf != -1) {
            if (lastIndexOf != 0) {
                str.substring(0, lastIndexOf + 1);
            }
            return str.substring(lastIndexOf + 1);
        }
        return str;
    }

    public static int getBatteryLevel(int i) {
        if (i >= 80) {
            return 5;
        }
        if (i >= 60) {
            return 4;
        }
        if (i >= 40) {
            return 3;
        }
        if (i >= 20) {
            return 2;
        }
        return i >= 1 ? 1 : 0;
    }

    public static int getControlSpeed(int i) {
        switch (i) {
            case 1:
                return 3000;
            case 2:
                return DefaultRetryPolicy.DEFAULT_TIMEOUT_MS;
            case 3:
                return 2000;
            case 4:
                return ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            case 5:
                return 1000;
            case 6:
                return 500;
            default:
                return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0019 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001c A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0021 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getImageVersionFormatWithBitNumber(int r4, int r5) {
        /*
            r0 = 2
            r1 = 5
            r2 = 3
            if (r4 > r2) goto L7
            r0 = 7
            goto L31
        L7:
            if (r4 == r1) goto L23
            r3 = 9
            if (r4 == r3) goto L23
            r3 = 12
            if (r4 != r3) goto L12
            goto L23
        L12:
            switch(r5) {
                case 0: goto L31;
                case 1: goto L31;
                case 2: goto L21;
                case 3: goto L30;
                case 4: goto L30;
                case 5: goto L1f;
                case 6: goto L1c;
                case 7: goto L1c;
                case 8: goto L19;
                case 9: goto L31;
                default: goto L15;
            }
        L15:
            switch(r5) {
                case 19: goto L30;
                case 20: goto L30;
                case 21: goto L1f;
                case 22: goto L1c;
                case 23: goto L1c;
                case 24: goto L19;
                case 25: goto L31;
                default: goto L18;
            }
        L18:
            goto L21
        L19:
            r0 = 514(0x202, float:7.2E-43)
            goto L31
        L1c:
            r0 = 515(0x203, float:7.22E-43)
            goto L31
        L1f:
            r0 = r1
            goto L31
        L21:
            r0 = 1
            goto L31
        L23:
            if (r5 == r0) goto L31
            if (r5 == r2) goto L30
            r4 = 4
            if (r5 == r4) goto L30
            if (r5 == r1) goto L30
            r4 = 18
            if (r5 == r4) goto L31
        L30:
            r0 = r2
        L31:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.utils.DfuUtils.getImageVersionFormatWithBitNumber(int, int):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0019 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001c A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0021 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getSocImageVersionFormatWithBitNumber(int r2, boolean r3, int r4, int r5) {
        /*
            r2 = 2
            r3 = 5
            r0 = 3
            if (r4 > r0) goto L7
            r2 = 7
            goto L31
        L7:
            if (r4 == r3) goto L23
            r1 = 9
            if (r4 == r1) goto L23
            r1 = 12
            if (r4 != r1) goto L12
            goto L23
        L12:
            switch(r5) {
                case 0: goto L31;
                case 1: goto L31;
                case 2: goto L21;
                case 3: goto L30;
                case 4: goto L30;
                case 5: goto L1f;
                case 6: goto L1c;
                case 7: goto L1c;
                case 8: goto L19;
                case 9: goto L31;
                default: goto L15;
            }
        L15:
            switch(r5) {
                case 19: goto L30;
                case 20: goto L30;
                case 21: goto L1f;
                case 22: goto L1c;
                case 23: goto L1c;
                case 24: goto L19;
                case 25: goto L31;
                default: goto L18;
            }
        L18:
            goto L21
        L19:
            r2 = 514(0x202, float:7.2E-43)
            goto L31
        L1c:
            r2 = 515(0x203, float:7.22E-43)
            goto L31
        L1f:
            r2 = r3
            goto L31
        L21:
            r2 = 1
            goto L31
        L23:
            if (r5 == r2) goto L31
            if (r5 == r0) goto L30
            r4 = 4
            if (r5 == r4) goto L30
            if (r5 == r3) goto L30
            r3 = 18
            if (r5 == r3) goto L31
        L30:
            r2 = r0
        L31:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.dfu.utils.DfuUtils.getSocImageVersionFormatWithBitNumber(int, boolean, int, int):int");
    }

    public static boolean isAssetsFileExist(Context context, String str) {
        String str2;
        String[] list;
        AssetManager assets = context.getResources().getAssets();
        if (assets == null || TextUtils.isEmpty(str)) {
            return false;
        }
        int lastIndexOf = str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        str2 = "";
        if (lastIndexOf != -1) {
            str2 = lastIndexOf != 0 ? str.substring(0, lastIndexOf) : "";
            str = str.substring(lastIndexOf + 1);
        }
        try {
            list = assets.list(str2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list != null && list.length > 0) {
            for (String str3 : list) {
                if (str.equals(str3)) {
                    ZLogger.v("\t =" + str3);
                    return true;
                }
                ZLogger.v("\t =" + str3);
            }
            ZLogger.v(String.format("not find asset file: <%s>/<%s>", str2, str));
            return false;
        }
        ZLogger.d(String.format("no asset file found: <%s>/<%s>", str2, str));
        return false;
    }

    public static String convertVersion2Str(int i, int i2, int i3) {
        if (i3 == 1) {
            if (i <= 0) {
                return String.valueOf(i2);
            }
            return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i2 & 255), Integer.valueOf((i2 >> 8) & 255), Integer.valueOf((i2 >> 16) & 255), Integer.valueOf((i2 >> 24) & 255));
        } else if (i3 == 2) {
            if (i <= 0) {
                return String.valueOf(i2);
            }
            return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf((i2 >> 24) & 255), Integer.valueOf((i2 >> 16) & 255), Integer.valueOf((i2 >> 8) & 255), Integer.valueOf(i2 & 255));
        } else if (i3 == 3) {
            if (i <= 0) {
                return String.valueOf(i2);
            }
            return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i2 & 15), Integer.valueOf((i2 >> 4) & 255), Integer.valueOf((i2 >> 12) & 32767), Integer.valueOf((i2 >> 27) & 31));
        } else if (i3 == 5) {
            if (i <= 0) {
                return String.valueOf(i2);
            }
            return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf(i2 & 15), Integer.valueOf((i2 >> 4) & 255), Integer.valueOf((i2 >> 12) & 511), Integer.valueOf((i2 >> 21) & 2047));
        } else if (i3 == 515) {
            if (i <= 0) {
                return String.valueOf(i2);
            }
            return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf((i2 >> 24) & 255), Integer.valueOf((i2 >> 16) & 255), Integer.valueOf((i2 >> 8) & 255), Integer.valueOf(i2 & 255));
        } else if (i3 == 4) {
            return String.valueOf(i2);
        } else {
            if (i3 == 7) {
                return String.valueOf(i2);
            }
            if (i3 == 514) {
                if (i <= 0) {
                    return String.valueOf(i2);
                }
                return String.format(Locale.US, "%d.%d.%d.%d", Integer.valueOf((i2 >> 8) & 255), Integer.valueOf(i2 & 255), Integer.valueOf((i2 >> 24) & 255), Integer.valueOf((i2 >> 16) & 255));
            }
            return String.valueOf(i2);
        }
    }
}
