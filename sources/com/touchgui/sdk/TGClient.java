package com.touchgui.sdk;

import androidx.annotation.NonNull;
import java.io.File;
/* loaded from: classes12.dex */
public interface TGClient {
    void abortDumpLog();

    void addConnectionListener(TGConnectionListener tGConnectionListener);

    void addEventListener(TGEventListener tGEventListener);

    boolean connect(@NonNull String str);

    boolean connect(@NonNull String str, boolean z);

    void disconnect();

    void dumpLog(File file, TGLogCallback tGLogCallback);

    TGCommandBuilder getCommandBuilder();

    int getContactCount();

    int getDeviceId();

    int getDevicePlatform();

    TGDialManager getDialManager();

    TGFileTransfer getFileTransfer();

    TGGPSManager getGPSManager();

    @Deprecated
    TGHealthDataManager getHealthDataManager();

    @Deprecated
    TGLogManager getLogManager();

    TGOTAManager getOtaManager();

    int getProtocolVersion();

    @Deprecated
    TGSportDataManager getSportDataManager();

    boolean hasFunction(int i);

    boolean isConnected();

    boolean isReady();

    boolean isReconnecting();

    void registerHealthDataCallback(TGHealthDataCallback tGHealthDataCallback);

    void registerWorkoutDataCallback(TGWorkoutDataCallback tGWorkoutDataCallback);

    void release();

    void removeConnectionListener(TGConnectionListener tGConnectionListener);

    void removeEventListener(TGEventListener tGEventListener);

    boolean syncHealthData();

    boolean syncHealthData(TGHealthData... tGHealthDataArr);

    boolean syncWorkoutData();

    void unregisterHealthDataCallback(TGHealthDataCallback tGHealthDataCallback);

    void unregisterWorkoutDataCallback(TGWorkoutDataCallback tGWorkoutDataCallback);
}
