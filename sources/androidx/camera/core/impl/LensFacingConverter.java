package androidx.camera.core.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public class LensFacingConverter {
    @NonNull
    public static String nameOf(int i) {
        if (i != 0) {
            if (i == 1) {
                return "BACK";
            }
            throw new IllegalArgumentException("Unknown lens facing " + i);
        }
        return "FRONT";
    }

    public static int valueOf(@Nullable String str) {
        Objects.requireNonNull(str, "name cannot be null");
        if (str.equals("BACK")) {
            return 1;
        }
        if (str.equals("FRONT")) {
            return 0;
        }
        throw new IllegalArgumentException("Unknown len facing name " + str);
    }

    @NonNull
    public static Integer[] values() {
        return new Integer[]{0, 1};
    }
}
