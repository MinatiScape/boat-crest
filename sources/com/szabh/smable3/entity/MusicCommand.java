package com.szabh.smable3.entity;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum MusicCommand {
    PLAY((byte) 0),
    PAUSE((byte) 1),
    TOGGLE((byte) 2),
    NEXT((byte) 3),
    PRE((byte) 4),
    VOLUME_UP((byte) 5),
    VOLUME_DOWN((byte) 6),
    UNKNOWN((byte) -1);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final byte mCommand;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MusicCommand of(byte b) {
            MusicCommand musicCommand;
            MusicCommand[] values = MusicCommand.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    musicCommand = null;
                    break;
                }
                musicCommand = values[i];
                if (musicCommand.getMCommand() == b) {
                    break;
                }
                i++;
            }
            return musicCommand == null ? MusicCommand.UNKNOWN : musicCommand;
        }
    }

    MusicCommand(byte b) {
        this.mCommand = b;
    }

    public final byte getMCommand() {
        return this.mCommand;
    }
}
