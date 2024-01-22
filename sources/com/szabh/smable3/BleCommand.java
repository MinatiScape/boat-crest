package com.szabh.smable3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum BleCommand {
    UPDATE(1),
    SET(2),
    CONNECT(3),
    PUSH(4),
    DATA(5),
    CONTROL(6),
    IO(7),
    NONE(255);
    
    private final int mBleCommand;

    BleCommand(int i) {
        this.mBleCommand = i;
    }

    @NotNull
    public final List<BleKey> getBleKeys() {
        BleKey[] values = BleKey.values();
        ArrayList arrayList = new ArrayList();
        for (BleKey bleKey : values) {
            if ((bleKey.getMKey() >>> 8) == this.mBleCommand) {
                arrayList.add(bleKey);
            }
        }
        return arrayList;
    }

    public final int getMBleCommand() {
        return this.mBleCommand;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%02X_", Arrays.copyOf(new Object[]{Integer.valueOf(this.mBleCommand)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(name());
        return sb.toString();
    }
}
