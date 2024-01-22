package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public enum WatchDiagnosticFeatureType {
    RESERVED(0),
    CHARGING_TEST(1),
    DISPLAY_TEST(2),
    VIBRATION_TEST(3),
    BUTTON_TEST(4),
    TOUCHSCREEN_TEST(5),
    SENSOR_TEST(6);
    

    /* renamed from: a  reason: collision with root package name */
    public int f3455a;

    WatchDiagnosticFeatureType(int i) {
        this.f3455a = i;
    }

    public static WatchDiagnosticFeatureType getById(int i) {
        WatchDiagnosticFeatureType watchDiagnosticFeatureType = RESERVED;
        if (i == watchDiagnosticFeatureType.f3455a) {
            return watchDiagnosticFeatureType;
        }
        WatchDiagnosticFeatureType watchDiagnosticFeatureType2 = CHARGING_TEST;
        if (i != watchDiagnosticFeatureType2.f3455a) {
            watchDiagnosticFeatureType2 = DISPLAY_TEST;
            if (i != watchDiagnosticFeatureType2.f3455a) {
                watchDiagnosticFeatureType2 = VIBRATION_TEST;
                if (i != watchDiagnosticFeatureType2.f3455a) {
                    watchDiagnosticFeatureType2 = BUTTON_TEST;
                    if (i != watchDiagnosticFeatureType2.f3455a) {
                        watchDiagnosticFeatureType2 = TOUCHSCREEN_TEST;
                        if (i != watchDiagnosticFeatureType2.f3455a) {
                            watchDiagnosticFeatureType2 = SENSOR_TEST;
                            if (i != watchDiagnosticFeatureType2.f3455a) {
                                return watchDiagnosticFeatureType;
                            }
                        }
                    }
                }
            }
        }
        return watchDiagnosticFeatureType2;
    }

    public int getId() {
        return this.f3455a;
    }
}
