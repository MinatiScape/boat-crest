package com.szabh.smable3.entity;

import android.util.SparseIntArray;
import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.f;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleSleep extends BleReadable {
    public static final int AWAKE = 3;
    public static final int AWAKE_LENGTH = 7;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int DEEP = 1;
    public static final int DEEP_LENGTH = 5;
    public static final int END = 34;
    private static final int ERROR_DATA = 14400;
    public static final int ITEM_LENGTH = 7;
    public static final int LIGHT = 2;
    public static final int LIGHT_LENGTH = 6;
    private static final int OFFSET = 60;
    private static final int PERIOD = 900;
    public static final int START = 17;
    public static final int TOTAL_LENGTH = 4;
    private int mMode;
    private int mSoft;
    private int mStrong;
    private int mTime;

    public BleSleep() {
        this(0, 0, 0, 0, 15, null);
    }

    public /* synthetic */ BleSleep(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public static /* synthetic */ BleSleep copy$default(BleSleep bleSleep, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = bleSleep.mTime;
        }
        if ((i5 & 2) != 0) {
            i2 = bleSleep.mMode;
        }
        if ((i5 & 4) != 0) {
            i3 = bleSleep.mSoft;
        }
        if ((i5 & 8) != 0) {
            i4 = bleSleep.mStrong;
        }
        return bleSleep.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.mTime;
    }

    public final int component2() {
        return this.mMode;
    }

    public final int component3() {
        return this.mSoft;
    }

    public final int component4() {
        return this.mStrong;
    }

    @NotNull
    public final BleSleep copy(int i, int i2, int i3, int i4) {
        return new BleSleep(i, i2, i3, i4);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mTime = BleReadable.readInt32$default(this, null, 1, null);
        this.mMode = readUInt8();
        this.mSoft = readUInt8();
        this.mStrong = readUInt8();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleSleep) {
            BleSleep bleSleep = (BleSleep) obj;
            return this.mTime == bleSleep.mTime && this.mMode == bleSleep.mMode && this.mSoft == bleSleep.mSoft && this.mStrong == bleSleep.mStrong;
        }
        return false;
    }

    public final int getMMode() {
        return this.mMode;
    }

    public final int getMSoft() {
        return this.mSoft;
    }

    public final int getMStrong() {
        return this.mStrong;
    }

    public final int getMTime() {
        return this.mTime;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mTime) * 31) + Integer.hashCode(this.mMode)) * 31) + Integer.hashCode(this.mSoft)) * 31) + Integer.hashCode(this.mStrong);
    }

    public final void setMMode(int i) {
        this.mMode = i;
    }

    public final void setMSoft(int i) {
        this.mSoft = i;
    }

    public final void setMStrong(int i) {
        this.mStrong = i;
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    @NotNull
    public String toString() {
        return "BleSleep(mTime=" + this.mTime + ", mMode=" + this.mMode + ", mSoft=" + this.mSoft + ", mStrong=" + this.mStrong + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleSleep(int i, int i2, int i3, int i4) {
        this.mTime = i;
        this.mMode = i2;
        this.mSoft = i3;
        this.mStrong = i4;
    }

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ List analyseSleep$default(Companion companion, List list, int i, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return companion.analyseSleep(list, i);
        }

        @NotNull
        public final List<BleSleep> analyseSleep(@NotNull List<BleSleep> origin, int i) {
            Intrinsics.checkNotNullParameter(origin, "origin");
            if (origin.size() < 2) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            ArrayList arrayList = new ArrayList(f.collectionSizeOrDefault(origin, 10));
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            for (Object obj : origin) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                if (((BleSleep) obj).getMMode() == 17) {
                    i3 = i4;
                }
                arrayList.add(Unit.INSTANCE);
                i4 = i5;
            }
            List takeLast = CollectionsKt___CollectionsKt.takeLast(origin, origin.size() - i3);
            int size = takeLast.size() - 2;
            if (size >= 0) {
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 1;
                    if (((BleSleep) takeLast.get(i7)).getMTime() - ((BleSleep) takeLast.get(i6)).getMTime() >= BleSleep.ERROR_DATA) {
                        return CollectionsKt__CollectionsKt.emptyList();
                    }
                    if (i6 == size) {
                        break;
                    }
                    i6 = i7;
                }
            }
            ArrayList arrayList2 = new ArrayList();
            if (i == 0) {
                boolean z = false;
                int i8 = 0;
                for (Object obj2 : takeLast) {
                    int i9 = i8 + 1;
                    if (i8 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    BleSleep bleSleep = (BleSleep) obj2;
                    int mMode = bleSleep.getMMode();
                    if (mMode != 1) {
                        if (mMode != 2) {
                            if (mMode != 3) {
                                if (mMode == 17) {
                                    arrayList2.clear();
                                    bleSleep.setMMode(2);
                                    arrayList2.add(bleSleep);
                                    if (takeLast.size() > i9 && ((BleSleep) takeLast.get(i9)).getMTime() - bleSleep.getMTime() > 900) {
                                        arrayList2.add(new BleSleep(bleSleep.getMTime() + 900, 1, 0, 0, 12, null));
                                    }
                                    z = true;
                                }
                            } else if (arrayList2.isEmpty() || ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode() != bleSleep.getMMode()) {
                                arrayList2.add(bleSleep);
                            }
                        } else if (bleSleep.getMStrong() <= 2 && bleSleep.getMSoft() <= 25) {
                            if (arrayList2.isEmpty() || ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode() != bleSleep.getMMode()) {
                                arrayList2.add(bleSleep);
                            }
                        } else if (!arrayList2.isEmpty() && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 900) {
                            if ((!arrayList2.isEmpty()) && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 900) {
                                ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).setMMode(3);
                            }
                        } else {
                            arrayList2.add(BleSleep.copy$default(bleSleep, bleSleep.getMTime() - 900, 3, 0, 0, 12, null));
                        }
                    } else if (bleSleep.getMStrong() > 2) {
                        if (!arrayList2.isEmpty() && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 960) {
                            if ((!arrayList2.isEmpty()) && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 840) {
                                ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).setMMode(3);
                            } else {
                                arrayList2.add(BleSleep.copy$default(bleSleep, 0, 3, 0, 0, 13, null));
                            }
                        } else {
                            arrayList2.add(BleSleep.copy$default(bleSleep, bleSleep.getMTime() - 900, 3, 0, 0, 12, null));
                        }
                    } else if (bleSleep.getMSoft() > 2) {
                        if (!arrayList2.isEmpty() && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 900) {
                            if ((!arrayList2.isEmpty()) && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() <= 900) {
                                ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).setMMode(2);
                            } else {
                                arrayList2.add(BleSleep.copy$default(bleSleep, 0, 2, 0, 0, 13, null));
                            }
                        } else {
                            arrayList2.add(BleSleep.copy$default(bleSleep, bleSleep.getMTime() - 900, 2, 0, 0, 12, null));
                        }
                    } else if ((!arrayList2.isEmpty()) && ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode() == bleSleep.getMMode() && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() > 840) {
                        arrayList2.add(BleSleep.copy$default(bleSleep, bleSleep.getMTime(), 2, 0, 0, 12, null));
                    } else if (arrayList2.size() > 2 && ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode() == 2 && ((BleSleep) arrayList2.get(arrayList2.size() - 2)).getMMode() == 1 && bleSleep.getMTime() - ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMTime() > 840) {
                        arrayList2.add(BleSleep.copy$default(bleSleep, bleSleep.getMTime(), 2, 0, 0, 12, null));
                    } else if (arrayList2.isEmpty() || ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode() != bleSleep.getMMode()) {
                        arrayList2.add(bleSleep);
                    }
                    i8 = i9;
                }
                if (!z && (!arrayList2.isEmpty())) {
                    ((BleSleep) arrayList2.get(0)).setMMode(2);
                    if (arrayList2.size() > 1 && ((BleSleep) arrayList2.get(1)).getMTime() - ((BleSleep) arrayList2.get(0)).getMTime() > 900) {
                        arrayList2.add(1, new BleSleep(((BleSleep) arrayList2.get(0)).getMTime() + 900, 1, 0, 0, 12, null));
                    }
                }
                return arrayList2;
            }
            for (Object obj3 : takeLast) {
                int i10 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                BleSleep bleSleep2 = (BleSleep) obj3;
                int mMode2 = bleSleep2.getMMode();
                if (mMode2 == 1) {
                    arrayList2.add(bleSleep2);
                } else if (mMode2 == 2) {
                    arrayList2.add(bleSleep2);
                } else if (mMode2 == 3) {
                    arrayList2.add(bleSleep2);
                } else if (mMode2 == 17) {
                    arrayList2.clear();
                } else if (mMode2 == 34) {
                    arrayList2.add(BleSleep.copy$default(bleSleep2, 0, ((BleSleep) CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList2)).getMMode(), 0, 0, 13, null));
                }
                i2 = i10;
            }
            return arrayList2;
        }

        @NotNull
        public final SparseIntArray getSleepStatusDuration(@NotNull List<BleSleep> sleeps) {
            Intrinsics.checkNotNullParameter(sleeps, "sleeps");
            SparseIntArray sparseIntArray = new SparseIntArray();
            int i = 0;
            for (Object obj : sleeps) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                BleSleep bleSleep = (BleSleep) obj;
                if (i < CollectionsKt__CollectionsKt.getLastIndex(sleeps)) {
                    int mMode = bleSleep.getMMode();
                    sparseIntArray.put(mMode, sparseIntArray.get(mMode) + (sleeps.get(i2).getMTime() - bleSleep.getMTime()));
                }
                i = i2;
            }
            sparseIntArray.put(2, sparseIntArray.get(2) / 60);
            sparseIntArray.put(1, sparseIntArray.get(1) / 60);
            sparseIntArray.put(3, sparseIntArray.get(3) / 60);
            return sparseIntArray;
        }

        @NotNull
        public final SparseIntArray getSleepStatusDuration(@NotNull List<BleSleep> sleeps, @NotNull List<BleSleep> origin) {
            Intrinsics.checkNotNullParameter(sleeps, "sleeps");
            Intrinsics.checkNotNullParameter(origin, "origin");
            SparseIntArray sparseIntArray = new SparseIntArray();
            for (BleSleep bleSleep : origin) {
                int mMode = bleSleep.getMMode();
                if (mMode == 4) {
                    sparseIntArray.put(4, (bleSleep.getMSoft() << 8) + bleSleep.getMStrong());
                } else if (mMode == 5) {
                    sparseIntArray.put(1, (bleSleep.getMSoft() << 8) + bleSleep.getMStrong());
                } else if (mMode == 6) {
                    sparseIntArray.put(2, (bleSleep.getMSoft() << 8) + bleSleep.getMStrong());
                } else if (mMode == 7) {
                    sparseIntArray.put(3, (bleSleep.getMSoft() << 8) + bleSleep.getMStrong());
                }
            }
            if (sparseIntArray.get(4) == 0) {
                SparseIntArray sleepStatusDuration = getSleepStatusDuration(sleeps);
                sleepStatusDuration.put(4, sleepStatusDuration.get(2) + sleepStatusDuration.get(1) + sleepStatusDuration.get(3));
                return sleepStatusDuration;
            }
            return sparseIntArray;
        }
    }
}
