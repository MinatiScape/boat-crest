package androidx.work;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class ContentUriTriggers {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Trigger> f1773a = new HashSet();

    /* loaded from: classes.dex */
    public static final class Trigger {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Uri f1774a;
        public final boolean b;

        public Trigger(@NonNull Uri uri, boolean z) {
            this.f1774a = uri;
            this.b = z;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Trigger.class != obj.getClass()) {
                return false;
            }
            Trigger trigger = (Trigger) obj;
            return this.b == trigger.b && this.f1774a.equals(trigger.f1774a);
        }

        @NonNull
        public Uri getUri() {
            return this.f1774a;
        }

        public int hashCode() {
            return (this.f1774a.hashCode() * 31) + (this.b ? 1 : 0);
        }

        public boolean shouldTriggerForDescendants() {
            return this.b;
        }
    }

    public void add(@NonNull Uri uri, boolean z) {
        this.f1773a.add(new Trigger(uri, z));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ContentUriTriggers.class != obj.getClass()) {
            return false;
        }
        return this.f1773a.equals(((ContentUriTriggers) obj).f1773a);
    }

    @NonNull
    public Set<Trigger> getTriggers() {
        return this.f1773a;
    }

    public int hashCode() {
        return this.f1773a.hashCode();
    }

    public int size() {
        return this.f1773a.size();
    }
}
