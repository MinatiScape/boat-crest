package com.polidea.rxandroidble2.internal.connection;

import android.bluetooth.BluetoothGattCharacteristic;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.logger.LoggerUtil;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
import java.util.Locale;
/* loaded from: classes12.dex */
public class IllegalOperationMessageCreator {

    /* renamed from: a  reason: collision with root package name */
    public final CharacteristicPropertiesParser f13414a;

    @Inject
    public IllegalOperationMessageCreator(CharacteristicPropertiesParser characteristicPropertiesParser) {
        this.f13414a = characteristicPropertiesParser;
    }

    public String createMismatchMessage(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        return String.format(Locale.getDefault(), "Characteristic %s supports properties: %s (%d) does not have any property matching %s (%d)", LoggerUtil.getUuidToLog(bluetoothGattCharacteristic.getUuid()), this.f13414a.propertiesIntToString(bluetoothGattCharacteristic.getProperties()), Integer.valueOf(bluetoothGattCharacteristic.getProperties()), this.f13414a.propertiesIntToString(i), Integer.valueOf(i));
    }
}
