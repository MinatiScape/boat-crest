package com.coveiot.android.dashboard2.listener;

import com.coveiot.android.bleabstract.models.ConnectionStatus;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public interface ConnectionStatusUpdateListener {
    void onConnectionUpdated(@Nullable ConnectionStatus connectionStatus);
}
