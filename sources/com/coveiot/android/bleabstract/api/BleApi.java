package com.coveiot.android.bleabstract.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.coveiot.android.bleabstract.listeners.ConnectionResultListener;
import com.coveiot.android.bleabstract.listeners.DataResultListener;
import com.coveiot.android.bleabstract.listeners.ScanResultListener;
import com.coveiot.android.bleabstract.listeners.SettingsResultListener;
import com.coveiot.android.bleabstract.models.BusyStatus;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.models.DeviceSupportedFeatures;
import com.coveiot.android.bleabstract.models.PPGData;
import com.coveiot.android.bleabstract.request.BleBaseRequest;
import com.coveiot.android.bleabstract.request.ConnectRequest;
import com.coveiot.android.bleabstract.request.ScanDeviceRequest;
import com.coveiot.android.bleabstract.response.BloodPressureMeasurement;
import com.coveiot.android.bleabstract.response.ConnectionInfo;
import com.coveiot.android.bleabstract.response.LiveAGPSUploadPercentage;
import com.coveiot.android.bleabstract.response.LiveECGDataResponse;
import com.coveiot.android.bleabstract.response.LiveHealthData;
import com.coveiot.android.bleabstract.response.LiveSportData;
import com.coveiot.android.bleabstract.response.LiveStepsData;
import com.coveiot.android.bleabstract.response.LiveTemperatureData;
import com.coveiot.android.bleabstract.response.LiveWatchFaceUploadPercentage;
import com.coveiot.android.bleabstract.response.Spo2Response;
import com.coveiot.android.bleabstract.response.Spo2WaveResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface BleApi {
    boolean canTerminateConnectionNow();

    void cleanUpCommands();

    void clearCommandQueue();

    void connect(@NotNull ConnectRequest connectRequest, @NotNull ConnectionResultListener connectionResultListener);

    void disconnect(@NotNull ConnectionResultListener connectionResultListener);

    @NotNull
    BusyStatus getBusyStatus();

    @Nullable
    ConnectionInfo getConnectionInfo();

    @NotNull
    ConnectionStatus getConnectionStatus();

    void getData(@NotNull BleBaseRequest bleBaseRequest, @NotNull DataResultListener dataResultListener);

    @NotNull
    DeviceSupportedFeatures getDeviceSupportedFeatures();

    @NotNull
    String getMacAddress();

    boolean isBluetoothServiceRunning();

    boolean isScanResultReturnedImmediately();

    @NotNull
    LiveData<ConnectionStatus> registerConnectionStatus();

    @NotNull
    LiveData<LiveECGDataResponse> registerForLiveEcgData();

    @NotNull
    LiveData<LiveTemperatureData> registerForLiveTemperatureData();

    @Nullable
    MutableLiveData<LiveAGPSUploadPercentage> registerLiveAGPSUploadData();

    @Nullable
    MutableLiveData<BloodPressureMeasurement> registerLiveBpData();

    @NotNull
    LiveData<LiveHealthData> registerLiveHealthData();

    @Nullable
    MutableLiveData<PPGData> registerLivePPGData();

    @Nullable
    MutableLiveData<Spo2Response> registerLiveSpo2Data();

    @Nullable
    MutableLiveData<Spo2WaveResponse> registerLiveSpo2WaveData();

    @Nullable
    LiveData<LiveSportData> registerLiveSportsData();

    @NotNull
    LiveData<LiveStepsData> registerLiveStepsData();

    @Nullable
    MutableLiveData<LiveWatchFaceUploadPercentage> registerLiveWatchFaceUploadData();

    void restartService();

    void scan(@NotNull ScanDeviceRequest scanDeviceRequest, @NotNull ScanResultListener scanResultListener);

    void setBusyStatus(@NotNull BusyStatus busyStatus);

    void setConnectionChangeListener(@NotNull ConnectionResultListener connectionResultListener);

    void setUserSettings(@NotNull BleBaseRequest bleBaseRequest, @NotNull SettingsResultListener settingsResultListener);

    void stopScan();

    void stopService();

    void stopServiceAndRetainMacAddress();

    void unpairDevice();
}
