package com.h6ah4i.android.widget.advrecyclerview.adapter;

import androidx.annotation.IntRange;
/* loaded from: classes11.dex */
public class ItemIdComposer {
    public static final long BIT_MASK_CHILD_ID = 268435455;
    public static final long BIT_MASK_GROUP_ID = 72057593769492480L;
    public static final long BIT_MASK_RESERVED_SIGN_FLAG = Long.MIN_VALUE;
    public static final long BIT_MASK_SEGMENT = 9151314442816847872L;
    public static final int BIT_OFFSET_CHILD_ID = 0;
    public static final int BIT_OFFSET_GROUP_ID = 28;
    public static final int BIT_OFFSET_RESERVED_SIGN_FLAG = 63;
    public static final int BIT_OFFSET_SEGMENT = 56;
    public static final int BIT_WIDTH_CHILD_ID = 28;
    public static final int BIT_WIDTH_GROUP_ID = 28;
    public static final int BIT_WIDTH_RESERVED_SIGN_FLAG = 1;
    public static final int BIT_WIDTH_SEGMENT = 7;
    public static final long MAX_CHILD_ID = 134217727;
    public static final long MAX_GROUP_ID = 134217727;
    public static final int MAX_SEGMENT = 127;
    public static final long MAX_WRAPPED_ID = 36028797018963967L;
    public static final long MIN_CHILD_ID = -134217728;
    public static final long MIN_GROUP_ID = -134217728;
    public static final int MIN_SEGMENT = 0;
    public static final long MIN_WRAPPED_ID = -36028797018963968L;

    public static long composeExpandableChildId(@IntRange(from = -134217728, to = 134217727) long j, @IntRange(from = -134217728, to = 134217727) long j2) {
        if (j < -134217728 || j > 134217727) {
            throw new IllegalArgumentException("Group ID value is out of range. (groupId = " + j + ")");
        } else if (j2 < -134217728 || j2 > 134217727) {
            throw new IllegalArgumentException("Child ID value is out of range. (childId = " + j2 + ")");
        } else {
            return ((j << 28) & BIT_MASK_GROUP_ID) | ((j2 << 0) & BIT_MASK_CHILD_ID);
        }
    }

    public static long composeExpandableGroupId(@IntRange(from = -134217728, to = 134217727) long j) {
        if (j < -134217728 || j > 134217727) {
            throw new IllegalArgumentException("Group ID value is out of range. (groupId = " + j + ")");
        }
        return ((j << 28) & BIT_MASK_GROUP_ID) | BIT_MASK_CHILD_ID;
    }

    public static long composeSegment(@IntRange(from = 0, to = 127) int i, long j) {
        if (i >= 0 && i <= 127) {
            return (j & (-9151314442816847873L)) | (i << 56);
        }
        throw new IllegalArgumentException("Segment value is out of range. (segment = " + i + ")");
    }

    @IntRange(from = -134217728, to = 134217727)
    public static long extractExpandableChildIdPart(long j) {
        if (j == -1 || isExpandableGroup(j)) {
            return -1L;
        }
        return (j << 36) >> 36;
    }

    @IntRange(from = -134217728, to = 134217727)
    public static long extractExpandableGroupIdPart(long j) {
        if (j == -1 || !isExpandableGroup(j)) {
            return -1L;
        }
        return (j << 8) >> 36;
    }

    @IntRange(from = 0, to = 127)
    public static int extractSegmentPart(long j) {
        return (int) ((j & BIT_MASK_SEGMENT) >>> 56);
    }

    public static long extractWrappedIdPart(long j) {
        if (j == -1) {
            return -1L;
        }
        return (j << 8) >> 8;
    }

    public static boolean isExpandableGroup(long j) {
        return j != -1 && (j & BIT_MASK_CHILD_ID) == BIT_MASK_CHILD_ID;
    }
}
