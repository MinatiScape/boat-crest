package com.szabh.smable3;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum BleKeyFlag {
    UPDATE(0),
    READ(16),
    READ_CONTINUE(17),
    CREATE(32),
    DELETE(48),
    RESET(64),
    NONE(255);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final int mBleKeyFlag;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final BleKeyFlag of(int i) {
            BleKeyFlag bleKeyFlag;
            BleKeyFlag[] values = BleKeyFlag.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    bleKeyFlag = null;
                    break;
                }
                bleKeyFlag = values[i2];
                if (bleKeyFlag.getMBleKeyFlag() == i) {
                    break;
                }
                i2++;
            }
            return bleKeyFlag == null ? BleKeyFlag.NONE : bleKeyFlag;
        }
    }

    BleKeyFlag(int i) {
        this.mBleKeyFlag = i;
    }

    public final int getMBleKeyFlag() {
        return this.mBleKeyFlag;
    }

    @Override // java.lang.Enum
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("0x%02X_", Arrays.copyOf(new Object[]{Integer.valueOf(this.mBleKeyFlag)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(name());
        return sb.toString();
    }
}
