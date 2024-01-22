package com.coveiot.sdk.ble.model;

import android.content.Context;
import android.os.Handler;
import com.coveiot.sdk.ble.FirmWare;
import com.coveiot.sdk.ble.eventbus.BleEventBusManager;
import com.coveiot.sdk.ble.utils.BlePreferenceManager;
import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommonPreference;
import com.coveiot.sdk.ble.utils.ModuleNames;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleDeviceInfo implements Serializable {
    private static BleDeviceInfo ourInstance = new BleDeviceInfo();
    private String mAppearance;
    private String mDeviceColor;
    private String mDeviceName;
    private String mFirmwareRevision;
    private String mHwRevision;
    private String mManufacturerName;
    private String mModelNumber;
    private String mSerialNumber;
    private String mSoftwareRevision;
    private String mSystemId;
    private String macAddress;

    private BleDeviceInfo() {
    }

    public static void clearInstance() {
        ourInstance = null;
    }

    public static BleDeviceInfo getInstance() {
        if (ourInstance == null) {
            ourInstance = new BleDeviceInfo();
        }
        return ourInstance;
    }

    public static BleDeviceInfo getInstanceFromSharedPreferences(Context context) {
        String str = (String) BlePreferenceManager.getPreference(context, CommonPreference.BLE_DEVICE, "");
        if (str == null) {
            return null;
        }
        return (BleDeviceInfo) new Gson().fromJson(str, new TypeToken<BleDeviceInfo>() { // from class: com.coveiot.sdk.ble.model.BleDeviceInfo.2
        }.getType());
    }

    public String getFirmwareRevision() {
        return this.mFirmwareRevision;
    }

    public String getHwRevision() {
        return this.mHwRevision;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getSerialNumber() {
        return this.mSerialNumber;
    }

    public String getSoftwareRevision() {
        return this.mSoftwareRevision;
    }

    public String getmAppearance() {
        return this.mAppearance;
    }

    public String getmDeviceColor() {
        return this.mDeviceColor;
    }

    public String getmDeviceName() {
        return this.mDeviceName;
    }

    public String getmManufacturerName() {
        return this.mManufacturerName;
    }

    public String getmModelNumber() {
        return this.mModelNumber;
    }

    public String getmSystemId() {
        return this.mSystemId;
    }

    public boolean isDataFilled() {
        return (BleUtils.isEmpty(this.mSerialNumber) || BleUtils.isEmpty(this.mModelNumber) || BleUtils.isEmpty(this.mFirmwareRevision) || BleUtils.isEmpty(this.mHwRevision)) ? false : true;
    }

    public synchronized void saveInstance(Context context) {
        if (context != null) {
            if (!BleUtils.isEmpty(this.mModelNumber) && !BleUtils.isEmpty(this.mSerialNumber)) {
                LogHelper.d("battery", "cove watch battery value", ModuleNames.BLEABSTRACT.getModuleName());
                BlePreferenceManager.savePreference(context, CommonPreference.BLE_DEVICE, new Gson().toJson(getInstance()));
                StringBuilder sb = new StringBuilder();
                sb.append("1;");
                sb.append(getmModelNumber());
                sb.append(";");
                getSerialNumber();
            }
        }
    }

    public void setFirmwareRevision(String str, Context context) {
        this.mFirmwareRevision = str;
        BlePreferenceManager.savePreference(context, CommonPreference.BLE_DEVICE_INFO, str);
        new Handler().postDelayed(new Runnable() { // from class: com.coveiot.sdk.ble.model.BleDeviceInfo.1
            @Override // java.lang.Runnable
            public void run() {
                BleEventBusManager.getInstance().getEventBus().post(new FirmWare());
            }
        }, 500L);
    }

    public void setHwRevision(String str) {
        this.mHwRevision = str;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setSerialNumber(String str) {
        this.mSerialNumber = str;
    }

    public void setSoftwareRevision(String str) {
        this.mSoftwareRevision = str;
    }

    public void setmAppearance(String str) {
        this.mAppearance = str;
    }

    public void setmDeviceColor(String str) {
        this.mDeviceColor = str;
    }

    public void setmDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setmManufacturerName(String str) {
        this.mManufacturerName = str;
    }

    public void setmModelNumber(String str) {
        this.mModelNumber = str;
    }

    public void setmSystemId(String str) {
        this.mSystemId = str;
    }

    public String toString() {
        return "BleDeviceInfo{mDeviceName='" + this.mDeviceName + "', mAppearance='" + this.mAppearance + "', mManufacturerName='" + this.mManufacturerName + "', mModelNumber='" + this.mModelNumber + "', mSerialNumber='" + this.mSerialNumber + "', mHwRevision='" + this.mHwRevision + "', mFirmwareRevision='" + this.mFirmwareRevision + "', mSoftwareRevision='" + this.mSoftwareRevision + "'}";
    }
}
