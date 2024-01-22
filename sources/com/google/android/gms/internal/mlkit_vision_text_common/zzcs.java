package com.google.android.gms.internal.mlkit_vision_text_common;

import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes6.dex */
public final class zzcs extends y0 {
    public static int zza(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), (int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }
}
