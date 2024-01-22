package com.coveiot.android.tappy.model;

import java.io.Serializable;
import java.util.List;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class GetPostPersoCommandsResponseData implements Serializable {
    @Nullable
    private List<ApduCommand> apduCommands;

    @Nullable
    public final List<ApduCommand> getApduCommands() {
        return this.apduCommands;
    }

    public final void setApduCommands(@Nullable List<ApduCommand> list) {
        this.apduCommands = list;
    }
}
