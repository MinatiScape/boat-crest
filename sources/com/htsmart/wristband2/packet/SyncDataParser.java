package com.htsmart.wristband2.packet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.work.PeriodicWorkRequest;
import com.coveiot.utils.utility.UtilConstants;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.bean.SyncDataRaw;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.data.AbstractData;
import com.htsmart.wristband2.bean.data.BloodPressureData;
import com.htsmart.wristband2.bean.data.BloodPressureMeasureData;
import com.htsmart.wristband2.bean.data.EcgData;
import com.htsmart.wristband2.bean.data.GameData;
import com.htsmart.wristband2.bean.data.GpsData;
import com.htsmart.wristband2.bean.data.GpsItem;
import com.htsmart.wristband2.bean.data.HeartRateData;
import com.htsmart.wristband2.bean.data.OxygenData;
import com.htsmart.wristband2.bean.data.PressureData;
import com.htsmart.wristband2.bean.data.RespiratoryRateData;
import com.htsmart.wristband2.bean.data.SleepData;
import com.htsmart.wristband2.bean.data.SleepItemData;
import com.htsmart.wristband2.bean.data.SportData;
import com.htsmart.wristband2.bean.data.SportItem;
import com.htsmart.wristband2.bean.data.StepData;
import com.htsmart.wristband2.bean.data.TemperatureData;
import com.htsmart.wristband2.bean.data.TodayTotalData;
import com.htsmart.wristband2.packet.a.c;
import com.htsmart.wristband2.packet.a.d;
import com.htsmart.wristband2.packet.a.e;
import com.htsmart.wristband2.packet.a.f;
import com.htsmart.wristband2.packet.a.g;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
/* loaded from: classes11.dex */
public class SyncDataParser {
    public static final byte TYPE_BLOOD_PRESSURE = 5;
    public static final byte TYPE_BLOOD_PRESSURE_MEASURE = -123;
    public static final byte TYPE_ECG = 7;
    public static final byte TYPE_GAME = 19;
    public static final byte TYPE_GPS = 10;
    public static final byte TYPE_HEART_RATE = 3;
    public static final byte TYPE_HEART_RATE_MEASURE = -125;
    public static final byte TYPE_OXYGEN = 4;
    public static final byte TYPE_OXYGEN_MEASURE = -124;
    public static final byte TYPE_PRESSURE = 18;
    public static final byte TYPE_PRESSURE_MEASURE = -110;
    public static final byte TYPE_RESPIRATORY_RATE = 6;
    public static final byte TYPE_RESPIRATORY_RATE_MEASURE = -122;
    public static final byte TYPE_SLEEP = 2;
    public static final byte TYPE_SPORT = 16;
    public static final byte TYPE_STEP = 1;
    public static final byte TYPE_TEMPERATURE = 17;
    public static final byte TYPE_TEMPERATURE_MEASURE = -111;
    public static final byte TYPE_TOTAL_DATA = -1;

    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static class DataHeader {
        public int itemCount;
        public int itemIndex;
        public long timeInterval;
        public long timeStamp;

        public long getIndexTime() {
            return this.timeStamp + (this.itemIndex * this.timeInterval);
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static class DataItem {
        public long timeStamp;
        public int value;
        public int value2;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface DataType {
    }

    /* loaded from: classes11.dex */
    public static class a implements Comparator<DataItem> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(DataItem dataItem, DataItem dataItem2) {
            return (int) (dataItem.timeStamp - dataItem2.timeStamp);
        }
    }

    /* loaded from: classes11.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final long f12026a;
        public final String b;

        public b(long j, String str) {
            this.f12026a = j;
            this.b = str;
        }

        @Nullable
        public static b b(@NonNull byte[] bArr, int i) {
            if (bArr.length < 6 || i < 6) {
                return null;
            }
            return new b(SyncDataParser.parserTime4Bytes(bArr, 0, new GregorianCalendar()), BytesUtil.internalBytes2HexStr(new byte[]{bArr[4], bArr[5]}));
        }

        @NonNull
        public String toString() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UtilConstants.SERVER_TIME_FORMAT2, Locale.US);
            return simpleDateFormat.format(new Date(this.f12026a)) + this.b;
        }
    }

    @Nullable
    @VisibleForTesting
    public static SleepData a(List<DataItem> list) {
        if (list == null || list.size() <= 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        SleepItemData sleepItemData = null;
        int i = 1;
        while (i < list.size()) {
            DataItem dataItem = list.get(i);
            DataItem dataItem2 = list.get(i - 1);
            long j = dataItem.timeStamp;
            int i2 = (j > dataItem2.timeStamp ? 1 : (j == dataItem2.timeStamp ? 0 : -1));
            if (i2 < 0) {
                WristbandLog.w("SyncDataParser", "parserNewOneSleepData wrong sleep timestamp:%d and remove it", Long.valueOf(j));
                list.remove(i);
                i--;
            } else if (i2 == 0) {
                WristbandLog.w("SyncDataParser", "parserNewOneSleepData wrong sleep timestamp:%d and skip it", Long.valueOf(j));
            } else {
                if (sleepItemData != null) {
                    if (sleepItemData.getStatus() == dataItem.value) {
                        sleepItemData.setEndTime(dataItem.timeStamp);
                    } else {
                        arrayList.add(sleepItemData);
                        sleepItemData = null;
                    }
                }
                if (sleepItemData == null) {
                    sleepItemData = new SleepItemData();
                    sleepItemData.setStartTime(dataItem2.timeStamp);
                    sleepItemData.setStatus(dataItem.value);
                    sleepItemData.setEndTime(dataItem.timeStamp);
                }
                if (i == list.size() - 1) {
                    arrayList.add(sleepItemData);
                }
            }
            i++;
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        SleepData sleepData = new SleepData();
        sleepData.setItems(arrayList);
        return sleepData;
    }

    public static DataHeader b(byte[] bArr, byte b2, @Nullable GregorianCalendar gregorianCalendar) {
        DataHeader dataHeader = new DataHeader();
        dataHeader.itemCount = ((bArr[0] & 255) << 8) | (bArr[1] & 255);
        if (gregorianCalendar == null) {
            gregorianCalendar = new GregorianCalendar();
        }
        dataHeader.timeStamp = parser4BytesTimeStamp(bArr, 2, gregorianCalendar);
        int i = (bArr[7] & 255) | ((bArr[6] & 255) << 8);
        if (b2 != 7) {
            i = i * 60 * 1000;
        }
        dataHeader.timeInterval = i;
        dataHeader.itemIndex = 0;
        WristbandLog.i("parserHeader: type=%d , header.timeStamp=%d , header.timeInterval=%d", Byte.valueOf(b2), Long.valueOf(dataHeader.timeStamp), Long.valueOf(dataHeader.timeInterval));
        return dataHeader;
    }

    public static String c(GregorianCalendar gregorianCalendar, SimpleDateFormat simpleDateFormat, DataHeader dataHeader) {
        gregorianCalendar.setTimeInMillis(dataHeader.timeStamp);
        if (gregorianCalendar.get(11) > 12) {
            gregorianCalendar.set(5, gregorianCalendar.get(5) + 1);
        }
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    /* JADX WARN: Removed duplicated region for block: B:92:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.List<com.htsmart.wristband2.packet.SyncDataParser.DataItem> d(byte r30, java.util.List<byte[]> r31, java.util.List<com.htsmart.wristband2.packet.SyncDataParser.DataHeader> r32) {
        /*
            Method dump skipped, instructions count: 644
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.packet.SyncDataParser.d(byte, java.util.List, java.util.List):java.util.List");
    }

    public static <T extends AbstractData> List<T> e(@NonNull SyncDataRaw syncDataRaw, com.htsmart.wristband2.packet.a.b<T> bVar) {
        List<byte[]> datas = syncDataRaw.getDatas();
        GregorianCalendar gregorianCalendar = null;
        if (datas == null || datas.size() <= 0) {
            return null;
        }
        byte[] bArr = new byte[8];
        ArrayList arrayList = new ArrayList();
        long currentTimeMillis = System.currentTimeMillis();
        DataHeader dataHeader = null;
        int i = 8;
        int i2 = 0;
        boolean z = true;
        for (byte[] bArr2 : datas) {
            if (bArr2 == null || bArr2.length == 0) {
                gregorianCalendar = null;
            } else {
                int length = bArr2.length;
                int i3 = 0;
                while (i3 < length) {
                    bArr[i2] = bArr2[i3];
                    i2++;
                    if (i2 == i) {
                        if (z) {
                            DataHeader b2 = b(bArr, syncDataRaw.getDataType(), gregorianCalendar);
                            if (b2.itemCount != 0) {
                                i = bVar.a();
                                z = false;
                            } else {
                                WristbandLog.w("数据无效(空数据包)：type=%d", Byte.valueOf(syncDataRaw.getDataType()));
                            }
                            dataHeader = b2;
                        } else {
                            T a2 = bVar.a(bArr);
                            if (a2 != null) {
                                if (a2.getTimeStamp() < currentTimeMillis) {
                                    arrayList.add(a2);
                                } else {
                                    WristbandLog.w("数据无效(时间错误)：type=%d , timeStamp=%d , limitTime=%d", Byte.valueOf(syncDataRaw.getDataType()), Long.valueOf(a2.getTimeStamp()), Long.valueOf(currentTimeMillis));
                                }
                            }
                            int i4 = dataHeader.itemIndex + 1;
                            dataHeader.itemIndex = i4;
                            if (i4 == dataHeader.itemCount) {
                                z = true;
                                i = 8;
                            }
                        }
                        i2 = 0;
                    }
                    i3++;
                    gregorianCalendar = null;
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public static List<SleepData> f(List<byte[]> list, Locale locale, boolean z, boolean z2) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
        Map<String, List<DataItem>> h = z2 ? h(gregorianCalendar, simpleDateFormat, simpleDateFormat2, list, z) : k(gregorianCalendar, simpleDateFormat, simpleDateFormat2, list, z);
        if (h == null || h.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(7);
        for (String str : h.keySet()) {
            List<DataItem> list2 = h.get(str);
            SleepData a2 = z2 ? a(list2) : i(list2);
            if (a2 != null) {
                try {
                    a2.setTimeStamp(simpleDateFormat.parse(str).getTime());
                    arrayList.add(a2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static Locale g() {
        Context context = WristbandApplication.getContext();
        if (context == null) {
            return Locale.getDefault();
        }
        int i = Build.VERSION.SDK_INT;
        Configuration configuration = context.getResources().getConfiguration();
        return i >= 24 ? configuration.getLocales().get(0) : configuration.locale;
    }

    public static Map<String, List<DataItem>> h(GregorianCalendar gregorianCalendar, SimpleDateFormat simpleDateFormat, SimpleDateFormat simpleDateFormat2, List<byte[]> list, boolean z) {
        byte[] bArr;
        int i;
        List list2;
        int i2;
        int i3;
        DataHeader dataHeader;
        GregorianCalendar gregorianCalendar2 = gregorianCalendar;
        DataHeader dataHeader2 = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        byte[] bArr2 = new byte[8];
        HashMap hashMap = new HashMap();
        long currentTimeMillis = z ? Long.MAX_VALUE : System.currentTimeMillis();
        List list3 = null;
        int i4 = 8;
        int i5 = 1;
        int i6 = 0;
        for (byte[] bArr3 : list) {
            if (bArr3 != null && bArr3.length != 0) {
                int i7 = 0;
                for (int length = bArr3.length; i7 < length; length = i) {
                    bArr2[i6] = bArr3[i7];
                    i6++;
                    if (i6 == i4) {
                        if (i5 != 0) {
                            DataHeader b2 = b(bArr2, (byte) 2, gregorianCalendar2);
                            if (b2.itemCount != 0) {
                                String c = c(gregorianCalendar2, simpleDateFormat, b2);
                                list3 = (List) hashMap.get(c);
                                if (list3 == null) {
                                    list3 = new ArrayList(300);
                                    hashMap.put(c, list3);
                                    DataItem dataItem = new DataItem();
                                    dataItem.timeStamp = b2.timeStamp;
                                    list3.add(dataItem);
                                    if (WristbandLog.isAtLeast(3)) {
                                        StringBuilder sb = new StringBuilder();
                                        dataHeader = b2;
                                        bArr = bArr3;
                                        sb.append(simpleDateFormat2.format(new Date(dataItem.timeStamp)));
                                        sb.append(" = NONE");
                                        i2 = 0;
                                        WristbandLog.d(sb.toString(), new Object[0]);
                                        i5 = i2;
                                        i = length;
                                        dataHeader2 = dataHeader;
                                        i4 = 5;
                                    }
                                }
                                dataHeader = b2;
                                bArr = bArr3;
                                i2 = 0;
                                i5 = i2;
                                i = length;
                                dataHeader2 = dataHeader;
                                i4 = 5;
                            } else {
                                bArr = bArr3;
                                i = length;
                                i2 = 0;
                                WristbandLog.w("数据无效(空数据包)：type=%d", (byte) 2);
                                dataHeader2 = b2;
                            }
                        } else {
                            bArr = bArr3;
                            i = length;
                            list2 = list3;
                            if (dataHeader2 != null) {
                                long parser4BytesTimeStamp = parser4BytesTimeStamp(bArr2, 0, gregorianCalendar2);
                                int i8 = bArr2[4] & 255;
                                if (parser4BytesTimeStamp >= currentTimeMillis) {
                                    i2 = 0;
                                    i3 = 1;
                                    WristbandLog.w("数据无效(时间错误)：type=%d , timeStamp=%d , limitTime=%d", (byte) 2, Long.valueOf(parser4BytesTimeStamp * 1000), Long.valueOf(1000 * currentTimeMillis));
                                } else if (i8 < 0 || i8 >= 4) {
                                    i2 = 0;
                                    i3 = 1;
                                    WristbandLog.w("数据无效(数值错误)：type=%d , value=%d ", (byte) 2, Integer.valueOf(i8));
                                } else {
                                    DataItem dataItem2 = new DataItem();
                                    dataItem2.timeStamp = parser4BytesTimeStamp;
                                    dataItem2.value = i8 == 0 ? 3 : i8;
                                    list2.add(dataItem2);
                                    if (WristbandLog.isAtLeast(3)) {
                                        i2 = 0;
                                        WristbandLog.d(simpleDateFormat2.format(new Date(parser4BytesTimeStamp)) + " = " + i8, new Object[0]);
                                    } else {
                                        i2 = 0;
                                    }
                                    i3 = 1;
                                }
                                int i9 = dataHeader2.itemIndex + 1;
                                dataHeader2.itemIndex = i9;
                                list3 = list2;
                                if (i9 == dataHeader2.itemCount) {
                                    i5 = i3;
                                    i4 = 8;
                                }
                            }
                        }
                        i6 = i2;
                        i7++;
                        gregorianCalendar2 = gregorianCalendar;
                        bArr3 = bArr;
                    } else {
                        bArr = bArr3;
                        i = length;
                        list2 = list3;
                    }
                    list3 = list2;
                    i7++;
                    gregorianCalendar2 = gregorianCalendar;
                    bArr3 = bArr;
                }
            }
            gregorianCalendar2 = gregorianCalendar;
        }
        return hashMap;
    }

    @Nullable
    @VisibleForTesting
    public static SleepData i(List<DataItem> list) {
        int i;
        if (list == null || list.size() <= 0) {
            return null;
        }
        Collections.sort(list, new a());
        while (true) {
            if (list.size() <= 0 || list.get(0).value != 3) {
                break;
            }
            list.remove(0);
        }
        for (int size = list.size() - 1; size >= 0 && list.get(size).value == 3; size--) {
            list.remove(size);
        }
        if (list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        SleepItemData sleepItemData = null;
        for (i = 0; i < list.size(); i++) {
            DataItem dataItem = list.get(i);
            if (sleepItemData != null) {
                if (sleepItemData.getStatus() == dataItem.value) {
                    sleepItemData.setEndTime(dataItem.timeStamp);
                } else {
                    arrayList.add(sleepItemData);
                    sleepItemData = null;
                }
            }
            if (sleepItemData == null) {
                long j = dataItem.timeStamp - PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;
                if (arrayList.size() > 0) {
                    SleepItemData sleepItemData2 = (SleepItemData) arrayList.get(arrayList.size() - 1);
                    if (j >= sleepItemData2.getEndTime()) {
                        if (j - sleepItemData2.getEndTime() <= PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS || sleepItemData2.getStatus() == 3) {
                            sleepItemData2.setEndTime(j);
                        } else if (dataItem.value != 3) {
                            SleepItemData sleepItemData3 = new SleepItemData();
                            sleepItemData3.setStartTime(sleepItemData2.getEndTime());
                            sleepItemData3.setEndTime(j);
                            sleepItemData3.setStatus(3);
                            arrayList.add(sleepItemData3);
                        }
                    }
                    j = sleepItemData2.getEndTime();
                }
                sleepItemData = new SleepItemData();
                sleepItemData.setStartTime(j);
                sleepItemData.setStatus(dataItem.value);
                sleepItemData.setEndTime(dataItem.timeStamp);
            }
            if (i == list.size() - 1) {
                arrayList.add(sleepItemData);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        SleepData sleepData = new SleepData();
        sleepData.setItems(arrayList);
        return sleepData;
    }

    /* JADX WARN: Type inference failed for: r11v26 */
    /* JADX WARN: Type inference failed for: r11v9 */
    @VisibleForTesting
    public static List<StepData> j(List<byte[]> list, boolean z) {
        int i;
        int i2;
        int i3;
        byte[] bArr;
        int i4;
        int i5;
        int i6;
        int i7;
        GregorianCalendar gregorianCalendar = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        int i8 = z ? 6 : 2;
        char c = '\b';
        byte[] bArr2 = new byte[Math.max(8, i8)];
        ArrayList arrayList = new ArrayList(1000);
        long currentTimeMillis = System.currentTimeMillis();
        int i9 = 0;
        int i10 = 1;
        DataHeader dataHeader = null;
        int i11 = 8;
        int i12 = 0;
        int i13 = 1;
        for (byte[] bArr3 : list) {
            if (bArr3 == null || bArr3.length == 0) {
                int i14 = i10;
                i = i9;
                i2 = i14;
                c = c;
                gregorianCalendar = null;
            } else {
                int length = bArr3.length;
                int i15 = i9;
                int i16 = i10;
                while (i15 < length) {
                    bArr2[i12] = bArr3[i15];
                    i12++;
                    if (i12 == i11) {
                        if (i13 != 0) {
                            DataHeader b2 = b(bArr2, i16, gregorianCalendar);
                            if (b2.itemCount != 0) {
                                i3 = length;
                                bArr = bArr3;
                                i11 = i8;
                                i13 = i9;
                                dataHeader = b2;
                                i5 = i16;
                                i4 = i13;
                            } else {
                                Object[] objArr = new Object[i16];
                                objArr[i9] = Byte.valueOf((byte) i16);
                                WristbandLog.w("数据无效(空数据包)：type=%d", objArr);
                                i3 = length;
                                bArr = bArr3;
                                dataHeader = b2;
                                int i17 = i16;
                                i4 = i9;
                                i5 = i17;
                            }
                        } else if (dataHeader != null) {
                            int i18 = ((bArr2[i9] & 255) << 8) | (bArr2[i16] & 255);
                            if (i8 == 6) {
                                i6 = ((bArr2[2] & 255) << 8) | (bArr2[3] & 255);
                                i7 = ((bArr2[4] & 255) << 8) | (bArr2[5] & 255);
                                i3 = length;
                                bArr = bArr3;
                            } else {
                                i3 = length;
                                bArr = bArr3;
                                i6 = 0;
                                i7 = 0;
                            }
                            long indexTime = dataHeader.getIndexTime();
                            if (indexTime >= currentTimeMillis) {
                                i5 = 1;
                                i4 = 0;
                                WristbandLog.w("数据无效(时间错误)：type=%d , timeStamp=%d , limitTime=%d", (byte) 1, Long.valueOf(indexTime * 1000), Long.valueOf(1000 * currentTimeMillis));
                            } else if (i18 > 0 && i18 < 3001) {
                                StepData stepData = new StepData();
                                stepData.setTimeStamp(indexTime);
                                stepData.setStep(i18);
                                stepData.setDistance(i6 / 100000.0f);
                                stepData.setCalories(i7 / 1000.0f);
                                arrayList.add(stepData);
                                i5 = 1;
                                i4 = 0;
                            } else {
                                i5 = 1;
                                i4 = 0;
                                WristbandLog.w("数据无效(数值错误)：type=%d , step=%d", (byte) 1, Integer.valueOf(i18));
                            }
                            int i19 = dataHeader.itemIndex + i5;
                            dataHeader.itemIndex = i19;
                            if (i19 == dataHeader.itemCount) {
                                i13 = i5;
                                i11 = 8;
                            }
                        }
                        i12 = i4;
                        i15++;
                        bArr3 = bArr;
                        length = i3;
                        gregorianCalendar = null;
                        int i20 = i4;
                        i16 = i5;
                        i9 = i20;
                    }
                    i3 = length;
                    bArr = bArr3;
                    int i21 = i16;
                    i4 = i9;
                    i5 = i21;
                    i15++;
                    bArr3 = bArr;
                    length = i3;
                    gregorianCalendar = null;
                    int i202 = i4;
                    i16 = i5;
                    i9 = i202;
                }
                int i22 = i16;
                i = i9;
                i2 = i22;
                c = '\b';
            }
            int i23 = i;
            i10 = i2;
            i9 = i23;
        }
        return arrayList;
    }

    public static Map<String, List<DataItem>> k(GregorianCalendar gregorianCalendar, SimpleDateFormat simpleDateFormat, SimpleDateFormat simpleDateFormat2, List<byte[]> list, boolean z) {
        int i;
        int i2;
        GregorianCalendar gregorianCalendar2 = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        byte[] bArr = new byte[8];
        HashMap hashMap = new HashMap();
        long currentTimeMillis = z ? Long.MAX_VALUE : System.currentTimeMillis();
        int i3 = 1;
        DataHeader dataHeader = null;
        List list2 = null;
        int i4 = 8;
        int i5 = 1;
        int i6 = 0;
        for (byte[] bArr2 : list) {
            if (bArr2 == null || bArr2.length == 0) {
                i3 = i3;
                gregorianCalendar2 = null;
            } else {
                int length = bArr2.length;
                int i7 = 0;
                while (i7 < length) {
                    bArr[i6] = bArr2[i7];
                    i6++;
                    if (i6 == i4) {
                        if (i5 != 0) {
                            DataHeader b2 = b(bArr, (byte) 2, gregorianCalendar2);
                            if (b2.itemCount != 0) {
                                String c = c(gregorianCalendar, simpleDateFormat, b2);
                                List list3 = (List) hashMap.get(c);
                                if (list3 == null) {
                                    list3 = new ArrayList(300);
                                    hashMap.put(c, list3);
                                }
                                i4 = i3;
                                i = i4;
                                list2 = list3;
                                dataHeader = b2;
                                i5 = 0;
                                i6 = 0;
                            } else {
                                Object[] objArr = new Object[i3];
                                objArr[0] = (byte) 2;
                                WristbandLog.w("数据无效(空数据包)：type=%d", objArr);
                                i = i3;
                                dataHeader = b2;
                                i6 = 0;
                            }
                        } else if (dataHeader == null) {
                            i = i3;
                            i2 = i5;
                        } else {
                            int i8 = bArr[0] & 255;
                            int i9 = i5;
                            long indexTime = dataHeader.getIndexTime();
                            if (indexTime >= currentTimeMillis) {
                                i6 = 0;
                                i = 1;
                                WristbandLog.w("数据无效(时间错误)：type=%d , timeStamp=%d , limitTime=%d", (byte) 2, Long.valueOf(indexTime * 1000), Long.valueOf(1000 * currentTimeMillis));
                            } else if (i8 <= 0 || i8 >= 4) {
                                i6 = 0;
                                i = 1;
                                WristbandLog.w("数据无效(数值错误)：type=%d , value=%d ", (byte) 2, Integer.valueOf(i8));
                            } else {
                                DataItem dataItem = new DataItem();
                                dataItem.timeStamp = indexTime;
                                dataItem.value = i8;
                                list2.add(dataItem);
                                if (WristbandLog.isAtLeast(3)) {
                                    i6 = 0;
                                    WristbandLog.d(simpleDateFormat2.format(new Date(indexTime)) + " = " + i8, new Object[0]);
                                } else {
                                    i6 = 0;
                                }
                                i = 1;
                            }
                            int i10 = dataHeader.itemIndex + 1;
                            dataHeader.itemIndex = i10;
                            if (i10 == dataHeader.itemCount) {
                                i5 = i;
                                i4 = 8;
                            } else {
                                i5 = i9;
                            }
                        }
                        i7++;
                        i3 = i;
                        gregorianCalendar2 = null;
                    } else {
                        i = i3;
                        i2 = i5;
                    }
                    i5 = i2;
                    i7++;
                    i3 = i;
                    gregorianCalendar2 = null;
                }
            }
        }
        return hashMap;
    }

    public static long parser4BytesTimeStamp(byte[] bArr, int i, @NonNull GregorianCalendar gregorianCalendar) {
        int i2 = i + 1;
        int i3 = ((bArr[i] & 1) << 3) | ((bArr[i2] >> 5) & 7);
        int i4 = (bArr[i + 3] & 255) | ((bArr[i + 2] & 255) << 8);
        gregorianCalendar.set(1, ((bArr[i] & 126) >> 1) + 2000);
        gregorianCalendar.set(2, i3 - 1);
        gregorianCalendar.set(5, bArr[i2] & 31);
        gregorianCalendar.set(11, i4 / 60);
        gregorianCalendar.set(12, i4 % 60);
        gregorianCalendar.set(13, 0);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    @Nullable
    public static List<BloodPressureData> parserBloodPressureData(List<byte[]> list) {
        List<DataItem> d = d((byte) 5, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            BloodPressureData bloodPressureData = new BloodPressureData();
            bloodPressureData.setTimeStamp(dataItem.timeStamp);
            bloodPressureData.setSbp(dataItem.value);
            bloodPressureData.setDbp(dataItem.value2);
            arrayList.add(bloodPressureData);
        }
        return arrayList;
    }

    @Nullable
    public static List<BloodPressureMeasureData> parserBloodPressureMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -123) {
            return e(syncDataRaw, new com.htsmart.wristband2.packet.a.a(syncDataRaw.getConfig().getWristbandVersion().isAirPumpBloodPressure()));
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static EcgData parserEcgData(List<byte[]> list) {
        ArrayList arrayList = new ArrayList(1);
        List<DataItem> d = d((byte) 7, list, arrayList);
        if (d == null || d.size() <= 0) {
            return null;
        }
        EcgData ecgData = new EcgData();
        ecgData.setTimeStamp(d.get(0).timeStamp);
        ArrayList arrayList2 = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            arrayList2.add(Integer.valueOf(dataItem.value));
        }
        ecgData.setItems(arrayList2);
        if (arrayList.size() <= 0 || ((DataHeader) arrayList.get(0)).timeInterval <= 0) {
            WristbandLog.w("Parser Ecg interval error", new Object[0]);
            ecgData.setSample(100);
        } else {
            ecgData.setSample(1000 / ((int) ((DataHeader) arrayList.get(0)).timeInterval));
        }
        return ecgData;
    }

    @Nullable
    public static List<GameData> parserGameData(List<byte[]> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[12];
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DataHeader dataHeader = null;
        int i = 8;
        int i2 = 0;
        boolean z = true;
        for (byte[] bArr2 : list) {
            if (bArr2 != null && bArr2.length != 0) {
                for (byte b2 : bArr2) {
                    bArr[i2] = b2;
                    i2++;
                    if (i2 == i) {
                        if (z) {
                            dataHeader = b(bArr, (byte) 19, null);
                            if (dataHeader.itemCount != 0) {
                                z = false;
                                i = 12;
                            } else {
                                WristbandLog.w("数据无效(空数据包)：type=%d", (byte) 19);
                            }
                        } else {
                            arrayList.add(com.htsmart.wristband2.a.e.b.a(bArr, 0, gregorianCalendar));
                            int i3 = dataHeader.itemIndex + 1;
                            dataHeader.itemIndex = i3;
                            if (i3 == dataHeader.itemCount) {
                                i = 8;
                                z = true;
                            }
                        }
                        i2 = 0;
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<GpsData> parserGpsData(List<byte[]> list) {
        byte[] bArr;
        Iterator<byte[]> it;
        GregorianCalendar gregorianCalendar = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        byte[] bArr2 = new byte[64];
        ArrayList arrayList2 = new ArrayList();
        Iterator<byte[]> it2 = list.iterator();
        int i = 0;
        DataHeader dataHeader = null;
        b bVar = null;
        int i2 = 0;
        boolean z = false;
        int i3 = 8;
        while (it2.hasNext()) {
            byte[] next = it2.next();
            if (next == null || next.length == 0) {
                bArr2 = bArr2;
                it2 = it2;
                gregorianCalendar = null;
                i = 0;
            } else {
                int length = next.length;
                int i4 = i;
                while (i4 < length) {
                    bArr2[i2] = next[i4];
                    i2++;
                    if (i2 == i3) {
                        if (!z) {
                            dataHeader = b(bArr2, (byte) 10, gregorianCalendar);
                            if (dataHeader.itemCount != 0) {
                                bArr = bArr2;
                                it = it2;
                                i3 = 1;
                                z = true;
                            } else {
                                Object[] objArr = new Object[1];
                                objArr[i] = (byte) 10;
                                WristbandLog.w("数据无效(空数据包)：type=%d", objArr);
                                bArr = bArr2;
                                it = it2;
                            }
                        } else if (z) {
                            bArr = bArr2;
                            it = it2;
                            i3 = bArr2[i] & 255;
                            z = true;
                        } else if (z) {
                            bArr = bArr2;
                            it = it2;
                            bVar = b.b(bArr2, i3);
                            z = true;
                            i3 = 14;
                        } else {
                            int bytes2Int = BytesUtil.bytes2Int(bArr2, i, 2, true);
                            int bytes2Int2 = BytesUtil.bytes2Int(bArr2, 2, 4, true);
                            it = it2;
                            int bytes2Int3 = BytesUtil.bytes2Int(bArr2, 6, 4, true);
                            int bytes2Int4 = BytesUtil.bytes2Int(bArr2, 10, 2, true);
                            int i5 = bArr2[12] & 255;
                            bArr = bArr2;
                            boolean z2 = (bArr2[13] & 255) > 0;
                            GpsItem gpsItem = new GpsItem();
                            gpsItem.setDuration(bytes2Int);
                            boolean z3 = z;
                            gpsItem.setLng(bytes2Int2 / 100000.0f);
                            gpsItem.setLat(bytes2Int3 / 100000.0f);
                            gpsItem.setAltitude(bytes2Int4);
                            gpsItem.setSatellites(i5);
                            gpsItem.setRestart(z2);
                            arrayList2.add(gpsItem);
                            int i6 = dataHeader.itemIndex + 1;
                            dataHeader.itemIndex = i6;
                            if (i6 == dataHeader.itemCount) {
                                if (bVar != null) {
                                    GpsData gpsData = new GpsData();
                                    gpsData.setTimeStamp(bVar.f12026a);
                                    gpsData.setRecordId(bVar.toString());
                                    gpsData.setItems(arrayList2);
                                    arrayList.add(gpsData);
                                }
                                arrayList2 = new ArrayList();
                                i3 = 8;
                                z = false;
                                bVar = null;
                            } else {
                                z = z3;
                            }
                        }
                        i2 = 0;
                    } else {
                        bArr = bArr2;
                        it = it2;
                    }
                    i4++;
                    bArr2 = bArr;
                    it2 = it;
                    gregorianCalendar = null;
                    i = 0;
                }
            }
        }
        return arrayList;
    }

    @Nullable
    public static List<HeartRateData> parserHeartRateData(List<byte[]> list) {
        List<DataItem> d = d((byte) 3, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            HeartRateData heartRateData = new HeartRateData();
            heartRateData.setTimeStamp(dataItem.timeStamp);
            heartRateData.setHeartRate(dataItem.value);
            arrayList.add(heartRateData);
        }
        return arrayList;
    }

    @Nullable
    public static List<HeartRateData> parserHeartRateMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -125) {
            return e(syncDataRaw, new c());
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static List<OxygenData> parserOxygenData(List<byte[]> list) {
        List<DataItem> d = d((byte) 4, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            OxygenData oxygenData = new OxygenData();
            oxygenData.setTimeStamp(dataItem.timeStamp);
            oxygenData.setOxygen(dataItem.value);
            arrayList.add(oxygenData);
        }
        return arrayList;
    }

    @Nullable
    public static List<OxygenData> parserOxygenMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -124) {
            return e(syncDataRaw, new d());
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static List<PressureData> parserPressureData(List<byte[]> list) {
        List<DataItem> d = d((byte) 18, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            PressureData pressureData = new PressureData();
            pressureData.setTimeStamp(dataItem.timeStamp);
            pressureData.setPressure(dataItem.value);
            arrayList.add(pressureData);
        }
        return arrayList;
    }

    @Nullable
    public static List<PressureData> parserPressureMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -110) {
            return e(syncDataRaw, new e());
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static List<RespiratoryRateData> parserRespiratoryRateData(List<byte[]> list) {
        List<DataItem> d = d((byte) 6, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            RespiratoryRateData respiratoryRateData = new RespiratoryRateData();
            respiratoryRateData.setTimeStamp(dataItem.timeStamp);
            respiratoryRateData.setRate(dataItem.value);
            arrayList.add(respiratoryRateData);
        }
        return arrayList;
    }

    @Nullable
    public static List<RespiratoryRateData> parserRespiratoryRateMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -122) {
            return e(syncDataRaw, new f());
        }
        throw new IllegalArgumentException();
    }

    @Nullable
    public static List<SleepData> parserSleepData(List<byte[]> list, WristbandConfig wristbandConfig) {
        if (wristbandConfig == null || wristbandConfig.getWristbandVersion() == null) {
            Log.w("SyncDataParser", "parserSleepData but WristbandConfig or WristbandVersion is null");
            return null;
        }
        return f(list, g(), false, wristbandConfig.getWristbandVersion().isExtNewSleepFormat());
    }

    @Nullable
    public static List<SportData> parserSportData(List<byte[]> list, WristbandConfig wristbandConfig) {
        if (wristbandConfig == null || wristbandConfig.getWristbandVersion() == null) {
            Log.w("SyncDataParser", "parserSportData but WristbandConfig or WristbandVersion is null");
            return null;
        }
        return parserSportData(list, wristbandConfig.getWristbandVersion().isDynamicHeartRateEnabled(), wristbandConfig.getWristbandVersion().isGpsEnabled());
    }

    @Nullable
    public static List<SportData> parserSportData(List<byte[]> list, boolean z, boolean z2) {
        int i;
        int i2;
        Iterator<byte[]> it;
        byte[] bArr;
        int i3;
        int i4;
        GregorianCalendar gregorianCalendar = null;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(5);
        int i5 = z ? 13 : 12;
        byte[] bArr2 = new byte[64];
        ArrayList arrayList2 = new ArrayList();
        Iterator<byte[]> it2 = list.iterator();
        DataHeader dataHeader = null;
        b bVar = null;
        char c = 0;
        int i6 = 8;
        int i7 = 0;
        int i8 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        int i9 = 0;
        int i10 = 0;
        while (it2.hasNext()) {
            byte[] next = it2.next();
            if (next == null || next.length == 0) {
                i5 = i5;
                it2 = it2;
            } else {
                int length = next.length;
                DataHeader dataHeader2 = dataHeader;
                int i11 = 0;
                while (i11 < length) {
                    bArr2[i7] = next[i11];
                    i7++;
                    if (i7 != i6) {
                        i = length;
                        i2 = i5;
                        it = it2;
                        bArr = next;
                    } else if (c == 0) {
                        DataHeader b2 = b(bArr2, (byte) 16, gregorianCalendar);
                        if (b2.itemCount != 0) {
                            i = length;
                            if (z2) {
                                i2 = i5;
                                it = it2;
                                bArr = next;
                                dataHeader2 = b2;
                                c = 1;
                                i6 = 1;
                            } else {
                                i6 = i5;
                                i2 = i6;
                                it = it2;
                                bArr = next;
                                dataHeader2 = b2;
                                c = 3;
                            }
                        } else {
                            i = length;
                            WristbandLog.w("数据无效(空数据包)：type=%d", (byte) 16);
                            i2 = i5;
                            it = it2;
                            bArr = next;
                            dataHeader2 = b2;
                        }
                        i7 = 0;
                    } else {
                        i = length;
                        if (c == 1) {
                            i6 = bArr2[0] & 255;
                            i2 = i5;
                            it = it2;
                            bArr = next;
                            c = 2;
                        } else if (c == 2) {
                            bVar = b.b(bArr2, i6);
                            i6 = i5;
                            i2 = i6;
                            it = it2;
                            bArr = next;
                            c = 3;
                        } else {
                            int bytes2Int = BytesUtil.bytes2Int(bArr2, 0, 2, true);
                            int i12 = i9 == 0 ? bytes2Int : i9;
                            if (i12 == bytes2Int) {
                                i4 = BytesUtil.bytes2Int(bArr2, 2, 2, true);
                                i2 = i5;
                                int bytes2Int2 = BytesUtil.bytes2Int(bArr2, 4, 2, true);
                                it = it2;
                                float bytes2Int3 = BytesUtil.bytes2Int(bArr2, 6, 2, true) / 1000.0f;
                                bArr = next;
                                i3 = 8;
                                float bytes2Int4 = BytesUtil.bytes2Int(bArr2, 8, 2, true) / 1000.0f;
                                i8 += bytes2Int2;
                                f += bytes2Int3;
                                f2 += bytes2Int4;
                                SportItem sportItem = new SportItem();
                                sportItem.setDuration(i4);
                                sportItem.setSteps(bytes2Int2);
                                sportItem.setDistance(bytes2Int3);
                                sportItem.setCalories(bytes2Int4);
                                if (z) {
                                    sportItem.setHeartRate(bArr2[12] & 255);
                                }
                                arrayList2.add(sportItem);
                            } else {
                                i2 = i5;
                                it = it2;
                                bArr = next;
                                i3 = 8;
                                i4 = i10;
                            }
                            DataHeader dataHeader3 = dataHeader2;
                            int i13 = dataHeader3.itemIndex + 1;
                            dataHeader3.itemIndex = i13;
                            if (i13 == dataHeader3.itemCount) {
                                if (i12 != 0) {
                                    SportData sportData = new SportData();
                                    if (bVar == null) {
                                        sportData.setTimeStamp(dataHeader3.timeStamp);
                                    } else {
                                        sportData.setTimeStamp(bVar.f12026a);
                                        sportData.setRecordId(bVar.toString());
                                    }
                                    sportData.setDuration(i4);
                                    sportData.setDistance(f);
                                    sportData.setCalories(f2);
                                    sportData.setSteps(i8);
                                    sportData.setSportType(i12);
                                    sportData.setItems(arrayList2);
                                    arrayList.add(sportData);
                                }
                                arrayList2 = new ArrayList();
                                dataHeader2 = dataHeader3;
                                i6 = i3;
                                c = 0;
                                i8 = 0;
                                f = 0.0f;
                                f2 = 0.0f;
                                i9 = 0;
                                bVar = null;
                                i10 = 0;
                            } else {
                                i9 = i12;
                                dataHeader2 = dataHeader3;
                                i10 = i4;
                            }
                        }
                        i7 = 0;
                    }
                    i11++;
                    i5 = i2;
                    length = i;
                    it2 = it;
                    next = bArr;
                    gregorianCalendar = null;
                }
                dataHeader = dataHeader2;
                i5 = i5;
            }
            gregorianCalendar = null;
        }
        return arrayList;
    }

    @Nullable
    public static List<StepData> parserStepData(List<byte[]> list, WristbandConfig wristbandConfig) {
        if (wristbandConfig == null || wristbandConfig.getWristbandVersion() == null) {
            Log.w("SyncDataParser", "parserStepData but WristbandConfig or WristbandVersion is null");
            return null;
        }
        return j(list, wristbandConfig.getWristbandVersion().isExtStepExtra());
    }

    @Nullable
    public static List<TemperatureData> parserTemperatureData(List<byte[]> list) {
        List<DataItem> d = d((byte) 17, list, null);
        if (d == null || d.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(d.size());
        for (DataItem dataItem : d) {
            TemperatureData temperatureData = new TemperatureData();
            temperatureData.setTimeStamp(dataItem.timeStamp);
            temperatureData.setWrist(dataItem.value / 100.0f);
            temperatureData.setBody(dataItem.value2 / 100.0f);
            arrayList.add(temperatureData);
        }
        return arrayList;
    }

    @Nullable
    public static List<TemperatureData> parserTemperatureMeasure(@NonNull SyncDataRaw syncDataRaw) {
        if (syncDataRaw.getDataType() == -111) {
            return e(syncDataRaw, new g());
        }
        throw new IllegalArgumentException();
    }

    public static long parserTime4Bytes(byte[] bArr, int i, @NonNull GregorianCalendar gregorianCalendar) {
        int i2 = i + 1;
        int i3 = ((bArr[i] & 3) << 2) | ((bArr[i2] & 192) >> 6);
        int i4 = i + 2;
        int i5 = ((bArr[i2] & 1) << 4) | ((bArr[i4] & 240) >> 4);
        int i6 = i + 3;
        gregorianCalendar.set(1, ((bArr[i] & 252) >> 2) + 2000);
        gregorianCalendar.set(2, i3 - 1);
        gregorianCalendar.set(5, (bArr[i2] & 62) >> 1);
        gregorianCalendar.set(11, i5);
        gregorianCalendar.set(12, ((bArr[i4] & 15) << 2) | ((bArr[i6] & 192) >> 6));
        gregorianCalendar.set(13, bArr[i6] & 63);
        gregorianCalendar.set(14, 0);
        return gregorianCalendar.getTimeInMillis();
    }

    @NonNull
    public static TodayTotalData parserTotalData(List<byte[]> list) {
        TodayTotalData c = com.htsmart.wristband2.a.e.b.c(list.get(0));
        c.setTimeStamp(BytesUtil.bytes2Long(list.get(1)));
        return c;
    }
}
