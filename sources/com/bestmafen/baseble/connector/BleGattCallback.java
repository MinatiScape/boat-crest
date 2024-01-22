package com.bestmafen.baseble.connector;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface BleGattCallback {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static void onCharacteristicChanged(@NotNull BleGattCallback bleGattCallback, @NotNull byte[] value) {
            Intrinsics.checkNotNullParameter(value, "value");
        }

        public static void onCharacteristicRead(@NotNull BleGattCallback bleGattCallback, @NotNull String characteristicUuid, @NotNull byte[] value, @NotNull String text) {
            Intrinsics.checkNotNullParameter(characteristicUuid, "characteristicUuid");
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(text, "text");
        }

        public static void onCharacteristicWrite(@NotNull BleGattCallback bleGattCallback, @NotNull String characteristicUuid, @NotNull byte[] value) {
            Intrinsics.checkNotNullParameter(characteristicUuid, "characteristicUuid");
            Intrinsics.checkNotNullParameter(value, "value");
        }

        public static void onConnectionStateChange(@NotNull BleGattCallback bleGattCallback, boolean z) {
        }

        public static void onMtuChanged(@NotNull BleGattCallback bleGattCallback) {
        }
    }

    void onCharacteristicChanged(@NotNull byte[] bArr);

    void onCharacteristicRead(@NotNull String str, @NotNull byte[] bArr, @NotNull String str2);

    void onCharacteristicWrite(@NotNull String str, @NotNull byte[] bArr);

    void onConnectionStateChange(boolean z);

    void onMtuChanged();
}
