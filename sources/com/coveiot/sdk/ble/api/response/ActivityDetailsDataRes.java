package com.coveiot.sdk.ble.api.response;

import com.clevertap.android.sdk.Constants;
import com.coveiot.sdk.ble.api.model.BleActivityDetailData;
import com.coveiot.sdk.ble.api.model.CalorieSample;
import com.coveiot.sdk.ble.api.model.DistanceSample;
import com.coveiot.sdk.ble.api.model.HeartSample;
import com.coveiot.sdk.ble.api.model.PaceSample;
import com.coveiot.sdk.ble.api.model.SpeedSample;
import com.coveiot.sdk.ble.api.model.StepSample;
import com.coveiot.sdk.ble.api.request.BaseRequest;
import com.coveiot.sdk.ble.model.CommandType;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes9.dex */
public class ActivityDetailsDataRes extends BaseResponse {
    public static final String f = "ActivityDetailsDataRes";
    public BleActivityDetailData e;

    /* loaded from: classes9.dex */
    public static class SampleData {

        /* renamed from: a  reason: collision with root package name */
        public List<Byte> f7550a;
        public long b;

        public SampleData(long j, List<Byte> list) {
            this.f7550a = list;
            this.b = j;
        }

        public Object getSampleData() {
            int sampleFrequency = getSampleFrequency();
            char c = 1;
            int i = 6;
            if (getType() == 1) {
                ArrayList arrayList = new ArrayList();
                while (i < this.f7550a.size()) {
                    arrayList.add(new HeartSample(this.f7550a.get(i).byteValue() & 255, (this.b + ((i - 6) * sampleFrequency)) * 1000));
                    i++;
                }
                return arrayList;
            }
            int i2 = 4;
            char c2 = 3;
            char c3 = 2;
            byte b = 0;
            if (getType() == 2) {
                ArrayList arrayList2 = new ArrayList();
                int i3 = 6;
                while (i < this.f7550a.size() - 1) {
                    byte[] bArr = new byte[i2];
                    bArr[0] = 0;
                    bArr[1] = 0;
                    bArr[2] = this.f7550a.get(i + 1).byteValue();
                    bArr[c2] = this.f7550a.get(i).byteValue();
                    arrayList2.add(new StepSample(ByteBuffer.wrap(bArr).getInt(), (this.b + ((i3 - 6) * sampleFrequency)) * 1000));
                    i3++;
                    i += 2;
                    i2 = 4;
                    c2 = 3;
                }
                return arrayList2;
            }
            char c4 = 7;
            char c5 = 5;
            if (getType() == 256) {
                ArrayList arrayList3 = new ArrayList();
                int i4 = 6;
                int i5 = 6;
                while (i4 < this.f7550a.size()) {
                    byte[] bArr2 = new byte[8];
                    bArr2[b] = b;
                    bArr2[1] = b;
                    bArr2[c3] = b;
                    bArr2[3] = b;
                    bArr2[4] = this.f7550a.get(i4 + 3).byteValue();
                    bArr2[c5] = this.f7550a.get(i4 + 2).byteValue();
                    bArr2[i] = this.f7550a.get(i4 + 1).byteValue();
                    bArr2[c4] = this.f7550a.get(i4).byteValue();
                    arrayList3.add(new CalorieSample(ByteBuffer.wrap(bArr2).getLong(), (this.b + (sampleFrequency * (i5 - 6))) * 1000));
                    i5++;
                    i4 += 4;
                    i = 6;
                    c4 = 7;
                    c3 = 2;
                    b = 0;
                    c5 = 5;
                }
                return arrayList3;
            } else if (getType() == 512) {
                ArrayList arrayList4 = new ArrayList();
                int i6 = 6;
                for (int i7 = 6; i7 < this.f7550a.size(); i7 += 4) {
                    arrayList4.add(new DistanceSample(ByteBuffer.wrap(new byte[]{0, 0, 0, 0, this.f7550a.get(i7 + 3).byteValue(), this.f7550a.get(i7 + 2).byteValue(), this.f7550a.get(i7 + 1).byteValue(), this.f7550a.get(i7).byteValue()}).getLong(), (this.b + ((i6 - 6) * sampleFrequency)) * 1000));
                    i6++;
                }
                return arrayList4;
            } else if (getType() == 16) {
                ArrayList arrayList5 = new ArrayList();
                int i8 = 6;
                while (i8 < this.f7550a.size()) {
                    byte[] bArr3 = new byte[8];
                    bArr3[0] = 0;
                    bArr3[c] = 0;
                    bArr3[2] = 0;
                    bArr3[3] = 0;
                    bArr3[4] = this.f7550a.get(i8 + 3).byteValue();
                    bArr3[5] = this.f7550a.get(i8 + 2).byteValue();
                    int i9 = i8 + 1;
                    bArr3[6] = this.f7550a.get(i9).byteValue();
                    bArr3[7] = this.f7550a.get(i8).byteValue();
                    arrayList5.add(new SpeedSample(ByteBuffer.wrap(bArr3).getLong(), (this.b + ((i8 - 6) * sampleFrequency)) * 1000));
                    i8 = i9;
                    c = 1;
                }
                return arrayList5;
            } else if (getType() != 32) {
                LogHelper.d("Unknown Data type", getType() + "");
                return null;
            } else {
                ArrayList arrayList6 = new ArrayList();
                for (int i10 = 6; i10 < this.f7550a.size(); i10++) {
                    arrayList6.add(new PaceSample(this.f7550a.get(i10).byteValue(), (this.b + ((i10 - 6) * sampleFrequency)) * 1000));
                }
                return arrayList6;
            }
        }

        public int getSampleFrequency() {
            return ByteBuffer.wrap(new byte[]{0, 0, this.f7550a.get(5).byteValue(), this.f7550a.get(4).byteValue()}).getInt();
        }

        public int getType() {
            byte byteValue = this.f7550a.get(0).byteValue();
            return ByteBuffer.wrap(new byte[]{this.f7550a.get(3).byteValue(), this.f7550a.get(2).byteValue(), this.f7550a.get(1).byteValue(), byteValue}).getInt();
        }
    }

    public ActivityDetailsDataRes(CommandType commandType, BaseRequest baseRequest) {
        super(commandType, baseRequest);
    }

    public final void a() {
        int i;
        int i2;
        int i3;
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (this.c.getDataList() != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList<String> dataList = this.c.getDataList();
            CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
            copyOnWriteArrayList.addAll(dataList);
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                i = 8;
                i2 = 4;
                i3 = 1;
                if (!it.hasNext()) {
                    break;
                }
                try {
                    String str = (String) it.next();
                    String[] split = str.substring(1, str.length() - 1).split(Constants.SEPARATOR_COMMA);
                    if ((((Byte.parseByte(split[3].trim()) & 255) << 8) | (Byte.parseByte(split[2].trim()) & 255)) == 0) {
                        int length = split.length;
                        for (int i4 = Byte.parseByte(split[0]) == Byte.MAX_VALUE ? 12 : 4; i4 < length; i4++) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i4].trim())));
                        }
                    } else {
                        int length2 = split.length;
                        while (i2 < length2) {
                            arrayList.add(Byte.valueOf(Byte.parseByte(split[i2].trim())));
                            i2++;
                        }
                    }
                } catch (Exception e2) {
                    LogHelper.d(f, e2.toString(), ModuleNames.BLEABSTRACT.getModuleName());
                }
                e.printStackTrace();
                return;
            }
            byte byteValue = ((Byte) arrayList.get(0)).byteValue();
            byte byteValue2 = ((Byte) arrayList.get(1)).byteValue();
            long j = ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(5)).byteValue(), ((Byte) arrayList.get(4)).byteValue(), ((Byte) arrayList.get(3)).byteValue(), ((Byte) arrayList.get(2)).byteValue(), byteValue2, byteValue}).getLong();
            byte byteValue3 = ((Byte) arrayList.get(6)).byteValue();
            int i5 = ByteBuffer.wrap(new byte[]{0, 0, ((Byte) arrayList.get(8)).byteValue(), ((Byte) arrayList.get(7)).byteValue()}).getInt();
            long j2 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, ((Byte) arrayList.get(12)).byteValue(), ((Byte) arrayList.get(11)).byteValue(), ((Byte) arrayList.get(10)).byteValue(), ((Byte) arrayList.get(9)).byteValue()}).getLong();
            int byteValue4 = ((Byte) arrayList.get(13)).byteValue() & 255;
            long j3 = ByteBuffer.wrap(new byte[]{0, 0, 0, 0, ((Byte) arrayList.get(17)).byteValue(), ((Byte) arrayList.get(16)).byteValue(), ((Byte) arrayList.get(15)).byteValue(), ((Byte) arrayList.get(14)).byteValue()}).getLong();
            long j4 = j3 * 1000;
            BleUtils.countSetBits(j2);
            int i6 = Integer.MAX_VALUE;
            List subList = arrayList.subList(18, arrayList.size());
            List<StepSample> list = null;
            int i7 = 0;
            List list2 = null;
            List list3 = null;
            List<SpeedSample> list4 = null;
            List<PaceSample> list5 = null;
            while (subList.size() >= i) {
                byte byteValue5 = ((Byte) subList.get(0)).byteValue();
                byte byteValue6 = ((Byte) subList.get(i3)).byteValue();
                byte[] bArr = new byte[i2];
                bArr[0] = 0;
                bArr[i3] = 0;
                bArr[2] = byteValue6;
                bArr[3] = byteValue5;
                int i8 = ByteBuffer.wrap(bArr).getInt() + 8;
                if (i8 >= subList.size()) {
                    break;
                }
                SampleData sampleData = new SampleData(j3, subList.subList(2, i8));
                if (sampleData.getSampleFrequency() > 0 && sampleData.getSampleFrequency() < i6) {
                    int sampleFrequency = sampleData.getSampleFrequency();
                    if (sampleData.getSampleData() != null) {
                        i7 = ((List) sampleData.getSampleData()).size() * sampleFrequency;
                    }
                    i6 = sampleFrequency;
                }
                if (sampleData.getType() == i3) {
                    list2 = (List) sampleData.getSampleData();
                } else if (sampleData.getType() == 256) {
                    list3 = (List) sampleData.getSampleData();
                } else if (sampleData.getType() == 512) {
                    List list6 = (List) sampleData.getSampleData();
                } else if (sampleData.getType() == 16) {
                    list4 = (List) sampleData.getSampleData();
                } else if (sampleData.getType() == 32) {
                    list5 = (List) sampleData.getSampleData();
                } else if (sampleData.getType() == 2) {
                    list = (List) sampleData.getSampleData();
                }
                if (i8 >= subList.size()) {
                    break;
                }
                subList = subList.subList(i8, subList.size());
                i = 8;
                i2 = 4;
                i3 = 1;
            }
            BleActivityDetailData bleActivityDetailData = new BleActivityDetailData(j, byteValue3, i5, j2, byteValue4, j4, list3, list2);
            this.e = bleActivityDetailData;
            bleActivityDetailData.setStepSampleList(list);
            this.e.setSpeedSampleList(list4);
            this.e.setPaceSampleList(list5);
            this.e.setLeastSampleFrequency(i6);
            this.e.setDuration(i7);
        }
    }

    public BleActivityDetailData getActivityDetailData() {
        a();
        return this.e;
    }
}
