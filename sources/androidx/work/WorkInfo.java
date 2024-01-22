package androidx.work;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
/* loaded from: classes.dex */
public final class WorkInfo {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public UUID f1783a;
    @NonNull
    public State b;
    @NonNull
    public Data c;
    @NonNull
    public Set<String> d;
    @NonNull
    public Data e;
    public int f;

    /* loaded from: classes.dex */
    public enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        public boolean isFinished() {
            return this == SUCCEEDED || this == FAILED || this == CANCELLED;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public WorkInfo(@NonNull UUID uuid, @NonNull State state, @NonNull Data data, @NonNull List<String> list, @NonNull Data data2, int i) {
        this.f1783a = uuid;
        this.b = state;
        this.c = data;
        this.d = new HashSet(list);
        this.e = data2;
        this.f = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WorkInfo.class != obj.getClass()) {
            return false;
        }
        WorkInfo workInfo = (WorkInfo) obj;
        if (this.f == workInfo.f && this.f1783a.equals(workInfo.f1783a) && this.b == workInfo.b && this.c.equals(workInfo.c) && this.d.equals(workInfo.d)) {
            return this.e.equals(workInfo.e);
        }
        return false;
    }

    @NonNull
    public UUID getId() {
        return this.f1783a;
    }

    @NonNull
    public Data getOutputData() {
        return this.c;
    }

    @NonNull
    public Data getProgress() {
        return this.e;
    }

    @IntRange(from = 0)
    public int getRunAttemptCount() {
        return this.f;
    }

    @NonNull
    public State getState() {
        return this.b;
    }

    @NonNull
    public Set<String> getTags() {
        return this.d;
    }

    public int hashCode() {
        return (((((((((this.f1783a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f;
    }

    public String toString() {
        return "WorkInfo{mId='" + this.f1783a + "', mState=" + this.b + ", mOutputData=" + this.c + ", mTags=" + this.d + ", mProgress=" + this.e + '}';
    }
}
