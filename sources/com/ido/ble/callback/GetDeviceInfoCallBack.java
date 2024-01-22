package com.ido.ble.callback;

import com.ido.ble.f.a.c;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.SNInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
/* loaded from: classes11.dex */
public class GetDeviceInfoCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onGetActivityCount(ActivityDataCount activityDataCount);

        void onGetBasicInfo(BasicInfo basicInfo);

        void onGetBatteryInfo(BatteryInfo batteryInfo);

        void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo);

        void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3);

        void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo);

        void onGetFlashBinInfo(FlashBinInfo flashBinInfo);

        void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo);

        void onGetHIDInfo(HIDInfo hIDInfo);

        void onGetLiveData(LiveData liveData);

        void onGetMacAddress(MacAddressInfo macAddressInfo);

        @Deprecated
        void onGetNoticeCenterSwitchStatus(NoticeSwitchInfo noticeSwitchInfo);

        void onGetNoticeReminderSwitchStatus(NoticeReminderSwitchStatus noticeReminderSwitchStatus);

        @Deprecated
        void onGetSNInfo(SNInfo sNInfo);

        @Deprecated
        void onGetTime(SystemTime systemTime);
    }

    public static void a(final ActivityDataCount activityDataCount) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetActivityCount(ActivityDataCount.this);
                            return;
                        }
                    } else {
                        iCallBack.onGetActivityCount(ActivityDataCount.this);
                    }
                }
            }
        });
    }

    public static void a(final BasicInfo basicInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetBasicInfo(BasicInfo.this);
                            return;
                        }
                    } else if (iCallBack != null) {
                        iCallBack.onGetBasicInfo(BasicInfo.this);
                    }
                }
            }
        });
    }

    @Deprecated
    public static void a(final BatteryInfo batteryInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetBatteryInfo(BatteryInfo.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final CanDownLangInfo canDownLangInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetCanDownloadLangInfo(CanDownLangInfo.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final CanDownLangInfoV3 canDownLangInfoV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetCanDownloadLangInfoV3(CanDownLangInfoV3.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final FlashBinInfo flashBinInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (iCallBack != null) {
                        iCallBack.onGetFlashBinInfo(FlashBinInfo.this);
                    }
                }
            }
        });
    }

    public static void a(final HIDInfo hIDInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                        }
                    }
                    iCallBack.onGetHIDInfo(HIDInfo.this);
                }
            }
        });
    }

    public static void a(final LiveData liveData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetLiveData(LiveData.this);
                            return;
                        }
                    } else {
                        iCallBack.onGetLiveData(LiveData.this);
                    }
                }
            }
        });
    }

    public static void a(final MacAddressInfo macAddressInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0() && b.N().r().size() > 1) {
                        LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                    }
                    iCallBack.onGetMacAddress(MacAddressInfo.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetNoticeReminderSwitchStatus(NoticeReminderSwitchStatus.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final NoticeSwitchInfo noticeSwitchInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetNoticeCenterSwitchStatus(NoticeSwitchInfo.this);
                }
            }
        });
    }

    @Deprecated
    public static void a(final SNInfo sNInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    iCallBack.onGetSNInfo(SNInfo.this);
                }
            }
        });
    }

    public static void a(final SupportFunctionInfo supportFunctionInfo) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetFunctionTable(SupportFunctionInfo.this);
                            return;
                        }
                    } else if (iCallBack != null) {
                        iCallBack.onGetFunctionTable(SupportFunctionInfo.this);
                    }
                }
            }
        });
    }

    public static void a(final SystemTime systemTime) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().r()) {
                    if (com.ido.ble.i.a.a.a0()) {
                        if (b.N().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetTime(SystemTime.this);
                            return;
                        }
                    } else if (iCallBack != null) {
                        iCallBack.onGetTime(SystemTime.this);
                    }
                }
            }
        });
    }
}
