package androidx.camera.core.impl;

import android.util.ArrayMap;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class TagBundle {

    /* renamed from: a  reason: collision with root package name */
    public static final TagBundle f716a = new TagBundle(new ArrayMap());
    public final Map<String, Integer> mTagMap;

    public TagBundle(@NonNull Map<String, Integer> map) {
        this.mTagMap = map;
    }

    @NonNull
    public static TagBundle create(@NonNull Pair<String, Integer> pair) {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put((String) pair.first, (Integer) pair.second);
        return new TagBundle(arrayMap);
    }

    @NonNull
    public static TagBundle emptyBundle() {
        return f716a;
    }

    @NonNull
    public static TagBundle from(@NonNull TagBundle tagBundle) {
        ArrayMap arrayMap = new ArrayMap();
        for (String str : tagBundle.listKeys()) {
            arrayMap.put(str, tagBundle.getTag(str));
        }
        return new TagBundle(arrayMap);
    }

    @Nullable
    public Integer getTag(@NonNull String str) {
        return this.mTagMap.get(str);
    }

    @NonNull
    public Set<String> listKeys() {
        return this.mTagMap.keySet();
    }
}
