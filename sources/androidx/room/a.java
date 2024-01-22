package androidx.room;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Callable;
/* loaded from: classes.dex */
public class a {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final Set<LiveData> f1666a = Collections.newSetFromMap(new IdentityHashMap());
    public final RoomDatabase b;

    public a(RoomDatabase roomDatabase) {
        this.b = roomDatabase;
    }

    public <T> LiveData<T> a(String[] strArr, boolean z, Callable<T> callable) {
        return new c(this.b, this, z, callable, strArr);
    }

    public void b(LiveData liveData) {
        this.f1666a.add(liveData);
    }

    public void c(LiveData liveData) {
        this.f1666a.remove(liveData);
    }
}
