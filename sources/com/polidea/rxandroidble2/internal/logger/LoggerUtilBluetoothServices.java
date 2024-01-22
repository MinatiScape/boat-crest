package com.polidea.rxandroidble2.internal.logger;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.RxBleDeviceServices;
import com.polidea.rxandroidble2.internal.RxBleLog;
import com.polidea.rxandroidble2.internal.util.CharacteristicPropertiesParser;
import com.polidea.rxandroidble2.utils.StandardUUIDsParser;
/* loaded from: classes12.dex */
public class LoggerUtilBluetoothServices {

    /* renamed from: a  reason: collision with root package name */
    public final CharacteristicPropertiesParser f13443a;

    @Inject
    public LoggerUtilBluetoothServices(CharacteristicPropertiesParser characteristicPropertiesParser) {
        this.f13443a = characteristicPropertiesParser;
    }

    public static void a(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        sb.append('\n');
        sb.append('\t');
        sb.append("* ");
        sb.append(i(bluetoothGattCharacteristic));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattCharacteristic.getUuid()));
        sb.append(")");
    }

    public static void c(StringBuilder sb, BluetoothGattDescriptor bluetoothGattDescriptor) {
        sb.append('\n');
        sb.append('\t');
        sb.append('\t');
        sb.append("* ");
        sb.append(j(bluetoothGattDescriptor));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattDescriptor.getUuid()));
        sb.append(")");
    }

    public static void d(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic.getDescriptors().isEmpty()) {
            return;
        }
        e(sb);
        for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
            c(sb, bluetoothGattDescriptor);
        }
    }

    public static void e(StringBuilder sb) {
        sb.append('\n');
        sb.append('\t');
        sb.append("  ");
        sb.append("-> Descriptors: ");
    }

    public static void f(BluetoothDevice bluetoothDevice, StringBuilder sb) {
        sb.append("--------------- ====== Printing peripheral content ====== ---------------\n");
        sb.append(LoggerUtil.commonMacMessage(bluetoothDevice.getAddress()));
        sb.append('\n');
        sb.append("PERIPHERAL NAME: ");
        sb.append(bluetoothDevice.getName());
        sb.append('\n');
        sb.append("-------------------------------------------------------------------------");
    }

    public static void h(StringBuilder sb, BluetoothGattService bluetoothGattService) {
        sb.append("\n");
        sb.append(l(bluetoothGattService));
        sb.append(" - ");
        sb.append(k(bluetoothGattService));
        sb.append(" (");
        sb.append(LoggerUtil.getUuidToLog(bluetoothGattService.getUuid()));
        sb.append(")\n");
        sb.append("Instance ID: ");
        sb.append(bluetoothGattService.getInstanceId());
        sb.append('\n');
    }

    public static String i(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        String characteristicName = StandardUUIDsParser.getCharacteristicName(bluetoothGattCharacteristic.getUuid());
        return characteristicName == null ? "Unknown characteristic" : characteristicName;
    }

    public static String j(BluetoothGattDescriptor bluetoothGattDescriptor) {
        String descriptorName = StandardUUIDsParser.getDescriptorName(bluetoothGattDescriptor.getUuid());
        return descriptorName == null ? "Unknown descriptor" : descriptorName;
    }

    public static String k(BluetoothGattService bluetoothGattService) {
        String serviceName = StandardUUIDsParser.getServiceName(bluetoothGattService.getUuid());
        return serviceName == null ? "Unknown service" : serviceName;
    }

    public static String l(BluetoothGattService bluetoothGattService) {
        return bluetoothGattService.getType() == 0 ? "Primary Service" : "Secondary Service";
    }

    public final void b(StringBuilder sb, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        sb.append('\n');
        sb.append('\t');
        sb.append("  ");
        sb.append("Properties: ");
        sb.append(this.f13443a.propertiesIntToString(bluetoothGattCharacteristic.getProperties()));
    }

    public final void g(StringBuilder sb, BluetoothGattService bluetoothGattService) {
        h(sb, bluetoothGattService);
        sb.append("-> Characteristics:");
        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
            a(sb, bluetoothGattCharacteristic);
            b(sb, bluetoothGattCharacteristic);
            d(sb, bluetoothGattCharacteristic);
        }
    }

    public void log(RxBleDeviceServices rxBleDeviceServices, BluetoothDevice bluetoothDevice) {
        if (RxBleLog.isAtLeast(2)) {
            RxBleLog.v("Preparing services description", new Object[0]);
            RxBleLog.v(m(rxBleDeviceServices, bluetoothDevice), new Object[0]);
        }
    }

    public final String m(RxBleDeviceServices rxBleDeviceServices, BluetoothDevice bluetoothDevice) {
        StringBuilder sb = new StringBuilder();
        f(bluetoothDevice, sb);
        for (BluetoothGattService bluetoothGattService : rxBleDeviceServices.getBluetoothGattServices()) {
            sb.append('\n');
            g(sb, bluetoothGattService);
        }
        sb.append("\n--------------- ====== Finished peripheral content ====== ---------------");
        return sb.toString();
    }
}
