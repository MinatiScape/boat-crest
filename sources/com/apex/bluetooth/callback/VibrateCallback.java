package com.apex.bluetooth.callback;

import com.apex.bluetooth.enumeration.VibrationIntensity;
/* loaded from: classes.dex */
public interface VibrateCallback extends EABleCallback {
    void vibrateMode(VibrationIntensity vibrationIntensity);
}
