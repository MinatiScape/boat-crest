package com.h6ah4i.android.widget.advrecyclerview.composedadapter;

import android.util.SparseIntArray;
import com.h6ah4i.android.widget.advrecyclerview.adapter.ItemViewTypeComposer;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes11.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public SparseIntArray f11896a = new SparseIntArray();
    public SparseIntArray b = new SparseIntArray();

    public static int a(long j) {
        return ItemViewTypeComposer.composeSegment(((int) (j >>> 32)) & 65535, (int) (j & 4294967295L));
    }

    public static int b(long j) {
        return (int) ((j >>> 48) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    public long c(int i) {
        int indexOfKey = this.b.indexOfKey(ItemViewTypeComposer.extractSegmentPart(i));
        if (indexOfKey >= 0) {
            return (this.b.valueAt(indexOfKey) << 32) | (i & 4294967295L);
        }
        throw new IllegalStateException("Corresponding wrapped view type is not found!");
    }

    public int d(int i, int i2) {
        int i3;
        int extractSegmentPart = (i << 16) | ItemViewTypeComposer.extractSegmentPart(i2);
        int indexOfKey = this.f11896a.indexOfKey(extractSegmentPart);
        if (indexOfKey >= 0) {
            i3 = this.f11896a.valueAt(indexOfKey);
        } else {
            int size = this.f11896a.size() + 1;
            if (size <= 127) {
                this.f11896a.put(extractSegmentPart, size);
                this.b.put(size, extractSegmentPart);
                i3 = size;
            } else {
                throw new IllegalStateException("Failed to allocate a new wrapped view type.");
            }
        }
        return ItemViewTypeComposer.composeSegment(i3, i2);
    }
}
