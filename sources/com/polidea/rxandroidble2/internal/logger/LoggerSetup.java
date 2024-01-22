package com.polidea.rxandroidble2.internal.logger;

import com.polidea.rxandroidble2.LogOptions;
/* loaded from: classes12.dex */
public class LoggerSetup {
    public final int logLevel;
    public final LogOptions.Logger logger;
    public final int macAddressLogSetting;
    public final boolean shouldLogAttributeValues;
    public final boolean shouldLogScannedPeripherals;
    public final int uuidLogSetting;

    public LoggerSetup(int i, int i2, int i3, boolean z, boolean z2, LogOptions.Logger logger) {
        this.logLevel = i;
        this.macAddressLogSetting = i2;
        this.uuidLogSetting = i3;
        this.shouldLogAttributeValues = z;
        this.shouldLogScannedPeripherals = z2;
        this.logger = logger;
    }

    public LoggerSetup merge(LogOptions logOptions) {
        boolean z;
        int intValue = logOptions.getLogLevel() != null ? logOptions.getLogLevel().intValue() : this.logLevel;
        int intValue2 = logOptions.getMacAddressLogSetting() != null ? logOptions.getMacAddressLogSetting().intValue() : this.macAddressLogSetting;
        int intValue3 = logOptions.getUuidLogSetting() != null ? logOptions.getUuidLogSetting().intValue() : this.uuidLogSetting;
        boolean booleanValue = logOptions.getShouldLogAttributeValues() != null ? logOptions.getShouldLogAttributeValues().booleanValue() : this.shouldLogAttributeValues;
        if (logOptions.getShouldLogScannedPeripherals() != null) {
            z = logOptions.getShouldLogScannedPeripherals().booleanValue();
        } else {
            z = this.shouldLogScannedPeripherals;
        }
        return new LoggerSetup(intValue, intValue2, intValue3, booleanValue, z, logOptions.getLogger() != null ? logOptions.getLogger() : this.logger);
    }

    public String toString() {
        return "LoggerSetup{logLevel=" + this.logLevel + ", macAddressLogSetting=" + this.macAddressLogSetting + ", uuidLogSetting=" + this.uuidLogSetting + ", shouldLogAttributeValues=" + this.shouldLogAttributeValues + ", shouldLogScannedPeripherals=" + this.shouldLogScannedPeripherals + ", logger=" + this.logger + '}';
    }
}
