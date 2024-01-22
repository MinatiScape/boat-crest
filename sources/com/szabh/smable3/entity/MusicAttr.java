package com.szabh.smable3.entity;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public enum MusicAttr {
    PLAYER_NAME((byte) 0),
    PLAYER_PLAYBACK_INFO((byte) 1),
    PLAYER_VOLUME((byte) 2),
    QUEUE_INDEX((byte) 0),
    QUEUE_COUNT((byte) 1),
    QUEUE_SHUFFLE_MODE((byte) 2),
    QUEUE_REPEAT_MODE((byte) 3),
    TRACK_ARTIST((byte) 0),
    TRACK_ALBUM((byte) 1),
    TRACK_TITLE((byte) 2),
    TRACK_DURATION((byte) 3),
    UNKNOWN((byte) -1);
    
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final byte mAttr;

    MusicAttr(byte b) {
        this.mAttr = b;
    }

    public final byte getMAttr() {
        return this.mAttr;
    }

    /* loaded from: classes12.dex */
    public static final class Companion {

        /* loaded from: classes12.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[MusicEntity.values().length];
                try {
                    iArr[MusicEntity.PLAYER.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[MusicEntity.QUEUE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[MusicEntity.TRACK.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final MusicAttr of(@NotNull MusicEntity entity, byte b) {
            Intrinsics.checkNotNullParameter(entity, "entity");
            int i = WhenMappings.$EnumSwitchMapping$0[entity.ordinal()];
            if (i == 1) {
                if (b == 0) {
                    return MusicAttr.PLAYER_NAME;
                }
                if (b == 1) {
                    return MusicAttr.PLAYER_PLAYBACK_INFO;
                }
                if (b == 2) {
                    return MusicAttr.PLAYER_VOLUME;
                }
                return MusicAttr.UNKNOWN;
            } else if (i == 2) {
                if (b == 0) {
                    return MusicAttr.QUEUE_INDEX;
                }
                if (b == 1) {
                    return MusicAttr.QUEUE_COUNT;
                }
                if (b == 2) {
                    return MusicAttr.QUEUE_SHUFFLE_MODE;
                }
                if (b == 3) {
                    return MusicAttr.QUEUE_REPEAT_MODE;
                }
                return MusicAttr.UNKNOWN;
            } else if (i != 3) {
                return MusicAttr.UNKNOWN;
            } else {
                if (b == 0) {
                    return MusicAttr.TRACK_ARTIST;
                }
                if (b == 1) {
                    return MusicAttr.TRACK_ALBUM;
                }
                if (b == 2) {
                    return MusicAttr.TRACK_TITLE;
                }
                if (b == 3) {
                    return MusicAttr.TRACK_DURATION;
                }
                return MusicAttr.UNKNOWN;
            }
        }

        @NotNull
        public final MusicAttr of(byte b, byte b2) {
            return of(MusicEntity.Companion.of(b), b2);
        }
    }
}
