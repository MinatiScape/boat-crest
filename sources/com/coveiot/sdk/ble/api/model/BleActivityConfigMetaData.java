package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.model.BleActivityDataType;
import com.coveiot.sdk.ble.model.BleMETData;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
/* loaded from: classes9.dex */
public class BleActivityConfigMetaData {
    public int MET_NUM;
    public int MET_TYPE;
    public int activityId;
    public int activityImageId;
    public int activityNum;
    public String activityUniCode;
    public List<BleMETData> bleMetDataList;
    public int categoryId;
    public int categoryImageId;
    public String categoryUnicode;
    private byte[] data;
    public BleActivityDataType dataType;
    public int orderId;

    public BleActivityConfigMetaData(int i, int i2, int i3, String str, int i4, int i5, String str2, int i6, BleActivityDataType bleActivityDataType, int i7, int i8, List<BleMETData> list) {
        this.activityNum = i;
        this.categoryId = i2;
        this.categoryImageId = i3;
        this.categoryUnicode = str;
        this.activityId = i4;
        this.activityImageId = i5;
        this.activityUniCode = str2;
        this.orderId = i6;
        this.dataType = bleActivityDataType;
        this.MET_TYPE = i7;
        this.MET_NUM = i8;
        this.bleMetDataList = list;
        processAndCreateDataBytes();
    }

    private byte[] processAndCreateDataBytes() {
        int size = !AppUtils.isEmpty(this.bleMetDataList) ? this.bleMetDataList.size() * this.bleMetDataList.get(0).getDataBytes().length : 0;
        byte[] stringToUnicode = BleUtils.stringToUnicode(this.categoryUnicode);
        byte[] stringToUnicode2 = BleUtils.stringToUnicode(this.activityUniCode);
        LogHelper.d(BleActivityConfigMetaData.class.getSimpleName(), this.categoryUnicode + ":" + stringToUnicode.length);
        LogHelper.d(BleActivityConfigMetaData.class.getSimpleName(), this.activityUniCode + ":" + stringToUnicode2.length);
        byte[] bArr = new byte[stringToUnicode.length + 15 + stringToUnicode2.length + size];
        this.data = bArr;
        bArr[0] = (byte) this.activityNum;
        bArr[1] = (byte) this.categoryId;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.categoryImageId).array();
        byte[] bArr2 = this.data;
        bArr2[2] = array[0];
        bArr2[3] = array[1];
        bArr2[4] = (byte) stringToUnicode.length;
        System.arraycopy(stringToUnicode, 0, bArr2, 5, stringToUnicode.length);
        int length = stringToUnicode.length + 4 + 1;
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.activityId).array();
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.activityImageId).array();
        byte[] bArr3 = this.data;
        int i = length + 1;
        bArr3[length] = array2[0];
        int i2 = i + 1;
        bArr3[i] = array2[1];
        int i3 = i2 + 1;
        bArr3[i2] = array3[0];
        int i4 = i3 + 1;
        bArr3[i3] = array3[1];
        int i5 = i4 + 1;
        bArr3[i4] = (byte) stringToUnicode2.length;
        System.arraycopy(stringToUnicode2, 0, bArr3, i5, stringToUnicode2.length);
        int length2 = i5 + stringToUnicode2.length;
        int i6 = length2 + 1;
        this.data[length2] = (byte) this.orderId;
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.dataType.getDataTypeValue()).array();
        byte[] bArr4 = this.data;
        int i7 = i6 + 1;
        bArr4[i6] = array4[0];
        int i8 = i7 + 1;
        bArr4[i7] = array4[1];
        int i9 = i8 + 1;
        bArr4[i8] = (byte) this.MET_TYPE;
        int i10 = i9 + 1;
        bArr4[i9] = (byte) this.MET_NUM;
        for (int i11 = 0; i11 < this.bleMetDataList.size(); i11 += 2) {
            this.data[i10] = this.bleMetDataList.get(i11).getDataBytes()[0];
            this.data[i10 + 1] = this.bleMetDataList.get(i11).getDataBytes()[1];
        }
        return this.data;
    }

    public byte[] getDataBytes() {
        return this.data;
    }
}
