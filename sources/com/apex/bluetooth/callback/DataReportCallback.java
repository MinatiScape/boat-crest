package com.apex.bluetooth.callback;

import com.apex.bluetooth.enumeration.EABleSportStatus;
import com.apex.bluetooth.model.EABleMtu;
import com.apex.bluetooth.model.EABleMusicControl;
import com.apex.bluetooth.model.EABleQueryMusic;
import com.apex.bluetooth.model.EABleReportMonitorData;
import com.apex.bluetooth.model.EABleReportSportData;
import com.apex.bluetooth.model.EABleSocialResponse;
import com.apex.bluetooth.model.EABleSwitch;
import com.apex.bluetooth.model.EABleTimelyData;
/* loaded from: classes.dex */
public interface DataReportCallback extends EABleCallback {
    void answerIncoming();

    void appSportData(EABleReportSportData eABleReportSportData);

    void appSportStatus(EABleSportStatus eABleSportStatus);

    void brightScreenStatus(EABleSwitch eABleSwitch);

    void circadian();

    void connectCamera();

    void disturbStatus(EABleSwitch eABleSwitch);

    void endTakePhoto();

    void hangUpIncoming();

    void mtu(EABleMtu eABleMtu);

    void musicControl(EABleMusicControl eABleMusicControl);

    void queryMusic(EABleQueryMusic eABleQueryMusic);

    void reportMonitorData(EABleReportMonitorData eABleReportMonitorData);

    void searchPhone();

    void socialResponse(EABleSocialResponse eABleSocialResponse);

    void stopSearchPhone();

    void stopSearchWatch();

    void takePhoto();

    void timelyData(EABleTimelyData eABleTimelyData);

    void transmissionComplete();

    void updateAgps();

    void updateWeather();
}
