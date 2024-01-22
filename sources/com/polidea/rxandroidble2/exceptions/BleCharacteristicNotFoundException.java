package com.polidea.rxandroidble2.exceptions;

import java.util.UUID;
/* loaded from: classes9.dex */
public class BleCharacteristicNotFoundException extends BleException {
    private final UUID characteristicUUID;

    public BleCharacteristicNotFoundException(UUID uuid) {
        super("Characteristic not found with UUID " + uuid);
        this.characteristicUUID = uuid;
    }

    public UUID getCharacteristicUUID() {
        return this.characteristicUUID;
    }

    @Deprecated
    public UUID getCharactersisticUUID() {
        return this.characteristicUUID;
    }
}
