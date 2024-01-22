package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;
@Beta
@GwtCompatible
/* loaded from: classes10.dex */
public final class ArrayBasedEscaperMap {
    public static final char[][] b = (char[][]) Array.newInstance(char.class, 0, 0);

    /* renamed from: a  reason: collision with root package name */
    public final char[][] f10600a;

    public ArrayBasedEscaperMap(char[][] cArr) {
        this.f10600a = cArr;
    }

    @VisibleForTesting
    public static char[][] a(Map<Character, String> map) {
        Preconditions.checkNotNull(map);
        if (map.isEmpty()) {
            return b;
        }
        char[][] cArr = new char[((Character) Collections.max(map.keySet())).charValue() + 1];
        for (Character ch : map.keySet()) {
            char charValue = ch.charValue();
            cArr[charValue] = map.get(Character.valueOf(charValue)).toCharArray();
        }
        return cArr;
    }

    public static ArrayBasedEscaperMap create(Map<Character, String> map) {
        return new ArrayBasedEscaperMap(a(map));
    }

    public char[][] b() {
        return this.f10600a;
    }
}
