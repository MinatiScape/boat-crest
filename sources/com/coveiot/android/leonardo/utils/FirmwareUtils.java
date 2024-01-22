package com.coveiot.android.leonardo.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.blankj.utilcode.constant.TimeConstants;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.DeviceType;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.service.DfuService;
import com.coveiot.coveaccess.CoveApi;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.CoveUserAppSettings;
import com.coveiot.coveaccess.device.model.BleDeviceInfo;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.userappsetting.SoftwarUpdateReq;
import com.coveiot.coveaccess.userappsetting.SoftwareUpdateRes;
import com.coveiot.covepreferences.SessionManager;
import com.google.gson.Gson;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import no.nordicsemi.android.dfu.DfuServiceInitiator;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class FirmwareUtils {
    @NotNull
    public static final FirmwareUtils INSTANCE = new FirmwareUtils();

    public final boolean canShowAppUpdateDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String previousAppUpdateShownTime = SessionManager.getInstance(context).getPreviousAppUpdateShownTime();
        try {
            if (!TextUtils.isEmpty(previousAppUpdateShownTime)) {
                return (new Date().getTime() - new Date(Long.parseLong(previousAppUpdateShownTime)).getTime()) / ((long) TimeConstants.DAY) >= ((long) Integer.parseInt(AppConstants.DAY_INTERVAL_FOR_APP_UPDATE.getValue()));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public final boolean canShowFirmwareUpdateDialog(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String previousFirmwareUpdateShownTime = SessionManager.getInstance(context).getPreviousFirmwareUpdateShownTime();
        try {
            if (!TextUtils.isEmpty(previousFirmwareUpdateShownTime)) {
                return (new Date().getTime() - new Date(Long.parseLong(previousFirmwareUpdateShownTime)).getTime()) / ((long) TimeConstants.DAY) >= ((long) Integer.parseInt(AppConstants.DAY_INTERVAL_FOR_APP_UPDATE.getValue()));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    public final void checkAppUpgradeApi(@NotNull Context context, @NotNull final CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel> listener) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        ArrayList arrayList = new ArrayList();
        SoftwarUpdateReq.DeviceBean deviceBean = new SoftwarUpdateReq.DeviceBean();
        SoftwarUpdateReq.DeviceBean.LocationBean locationBean = new SoftwarUpdateReq.DeviceBean.LocationBean();
        SoftwarUpdateReq.CloveDeviceBean cloveDeviceBean = new SoftwarUpdateReq.CloveDeviceBean();
        BleDeviceInfo bleDeviceInfo = (BleDeviceInfo) new Gson().fromJson(SessionManager.getInstance(context).getBleDeviceInfo(), (Class<Object>) BleDeviceInfo.class);
        if (bleDeviceInfo != null) {
            String macAddress = bleDeviceInfo.getMacAddress();
            String str2 = bleDeviceInfo.getmModelNumber();
            Intrinsics.checkNotNullExpressionValue(str2, "bleDeviceInfo.getmModelNumber()");
            Locale ENGLISH = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
            String lowerCase = str2.toLowerCase(ENGLISH);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1810G) {
                String str3 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str3, "bleDeviceInfo.getmModelNumber()");
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                lowerCase = str3.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963D) {
                String str4 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str4, "bleDeviceInfo.getmModelNumber()");
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                lowerCase = str4.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1963YH) {
                String str5 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str5, "bleDeviceInfo.getmModelNumber()");
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                lowerCase = str5.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.jstyle1860) {
                String str6 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str6, "bleDeviceInfo.getmModelNumber()");
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                lowerCase = str6.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            if (BleApiManager.getInstance(context).getDeviceType() == DeviceType.ULTIMA_RISE) {
                String str7 = bleDeviceInfo.getmModelNumber();
                Intrinsics.checkNotNullExpressionValue(str7, "bleDeviceInfo.getmModelNumber()");
                Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
                lowerCase = str7.toLowerCase(ENGLISH);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            }
            String firmwareRevision = bleDeviceInfo.getFirmwareRevision();
            if (bleDeviceInfo.getFirmwareRevision() != null && DeviceUtils.Companion.isMoyangDevice(context)) {
                String firmwareRevision2 = bleDeviceInfo.getFirmwareRevision();
                Intrinsics.checkNotNullExpressionValue(firmwareRevision2, "bleDeviceInfo.firmwareRevision");
                firmwareRevision = firmwareRevision2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(firmwareRevision, "this as java.lang.String).toLowerCase()");
            }
            String serialNumber = bleDeviceInfo.getSerialNumber();
            String hwRevision = bleDeviceInfo.getHwRevision();
            String packageName = context.getPackageName();
            String str8 = "";
            try {
                str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                Intrinsics.checkNotNullExpressionValue(str, "context.packageManager.g…ckageName, 0).versionName");
            } catch (PackageManager.NameNotFoundException e) {
                e = e;
            }
            try {
                if (StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) "-", false, 2, (Object) null)) {
                    str = (String) StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null).get(0);
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e = e2;
                str8 = str;
                e.printStackTrace();
                str = str8;
                deviceBean.setLocation(locationBean);
                cloveDeviceBean.setModelNumber(lowerCase);
                cloveDeviceBean.setBtMacAddress(macAddress);
                cloveDeviceBean.setFirmwareVersion(firmwareRevision);
                cloveDeviceBean.setCustomerId(CoveApi.getClientId());
                cloveDeviceBean.setSerialNumber(serialNumber);
                cloveDeviceBean.setHardwareVersion(hwRevision);
                arrayList.add(cloveDeviceBean);
                SoftwarUpdateReq softwarUpdateReq = new SoftwarUpdateReq();
                softwarUpdateReq.setAppVersion(str);
                softwarUpdateReq.setCoveDevices(arrayList);
                softwarUpdateReq.setAppId(packageName);
                softwarUpdateReq.setDevice(deviceBean);
                CoveUserAppSettings.postSoftwareUpdate(softwarUpdateReq, new CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.utils.FirmwareUtils$checkAppUpgradeApi$1
                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                        Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                    }

                    @Override // com.coveiot.coveaccess.CoveApiListener
                    public void onSuccess(@NotNull SoftwareUpdateRes softwareUpdateRes) {
                        Intrinsics.checkNotNullParameter(softwareUpdateRes, "softwareUpdateRes");
                        listener.onSuccess(softwareUpdateRes);
                    }
                });
            }
            deviceBean.setLocation(locationBean);
            cloveDeviceBean.setModelNumber(lowerCase);
            cloveDeviceBean.setBtMacAddress(macAddress);
            cloveDeviceBean.setFirmwareVersion(firmwareRevision);
            cloveDeviceBean.setCustomerId(CoveApi.getClientId());
            cloveDeviceBean.setSerialNumber(serialNumber);
            cloveDeviceBean.setHardwareVersion(hwRevision);
            arrayList.add(cloveDeviceBean);
            SoftwarUpdateReq softwarUpdateReq2 = new SoftwarUpdateReq();
            softwarUpdateReq2.setAppVersion(str);
            softwarUpdateReq2.setCoveDevices(arrayList);
            softwarUpdateReq2.setAppId(packageName);
            softwarUpdateReq2.setDevice(deviceBean);
            CoveUserAppSettings.postSoftwareUpdate(softwarUpdateReq2, new CoveApiListener<SoftwareUpdateRes, CoveApiErrorModel>() { // from class: com.coveiot.android.leonardo.utils.FirmwareUtils$checkAppUpgradeApi$1
                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onError(@NotNull CoveApiErrorModel coveApiErrorModel) {
                    Intrinsics.checkNotNullParameter(coveApiErrorModel, "coveApiErrorModel");
                }

                @Override // com.coveiot.coveaccess.CoveApiListener
                public void onSuccess(@NotNull SoftwareUpdateRes softwareUpdateRes) {
                    Intrinsics.checkNotNullParameter(softwareUpdateRes, "softwareUpdateRes");
                    listener.onSuccess(softwareUpdateRes);
                }
            });
        }
    }

    public final void initiateDFU(@NotNull String address, @NotNull String deviceName, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(deviceName, "deviceName");
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT >= 26) {
            DfuServiceInitiator.createDfuNotificationChannel(context);
        }
        DfuServiceInitiator unsafeExperimentalButtonlessServiceInSecureDfuEnabled = new DfuServiceInitiator(address).setDeviceName(deviceName).setKeepBond(false).setForceDfu(false).setPacketsReceiptNotificationsEnabled(true).setPacketsReceiptNotificationsValue(12).setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(true);
        FileUtils fileUtils = FileUtils.INSTANCE;
        File filesDir = context.getFilesDir();
        Intrinsics.checkNotNullExpressionValue(filesDir, "context.filesDir");
        File firmwareFile = fileUtils.getFirmwareFile(filesDir, AppConstants.LEONARDO_FIRMWARE_FILE_NAME.getValue());
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.setZip(null, firmwareFile != null ? firmwareFile.getAbsolutePath() : null);
        unsafeExperimentalButtonlessServiceInSecureDfuEnabled.start(context, DfuService.class);
    }
}
