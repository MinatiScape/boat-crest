package com.szabh.smable3.entity;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum MusicEntity {
    PLAYER((byte) 0),
    QUEUE((byte) 1),
    TRACK((byte) 2),
    UNKNOWN((byte) -1);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final byte mEntity;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MusicEntity of(byte b) {
            MusicEntity musicEntity;
            MusicEntity[] values = MusicEntity.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    musicEntity = null;
                    break;
                }
                musicEntity = values[i];
                if (musicEntity.getMEntity() == b) {
                    break;
                }
                i++;
            }
            return musicEntity == null ? MusicEntity.UNKNOWN : musicEntity;
        }
    }

    MusicEntity(byte b) {
        this.mEntity = b;
    }

    public final byte getMEntity() {
        return this.mEntity;
    }
}
