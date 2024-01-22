package com.bestmafen.baseble.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public interface IBleParser {
    @Nullable
    byte[] onReceive(@NotNull byte[] bArr);

    void reset();
}
