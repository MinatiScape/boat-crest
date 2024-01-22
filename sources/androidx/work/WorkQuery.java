package androidx.work;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.work.WorkInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/* loaded from: classes.dex */
public final class WorkQuery {

    /* renamed from: a  reason: collision with root package name */
    public final List<UUID> f1785a;
    public final List<String> b;
    public final List<String> c;
    public final List<WorkInfo.State> d;

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public List<UUID> f1786a = new ArrayList();
        public List<String> b = new ArrayList();
        public List<String> c = new ArrayList();
        public List<WorkInfo.State> d = new ArrayList();

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromIds(@NonNull List<UUID> list) {
            Builder builder = new Builder();
            builder.addIds(list);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromStates(@NonNull List<WorkInfo.State> list) {
            Builder builder = new Builder();
            builder.addStates(list);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromTags(@NonNull List<String> list) {
            Builder builder = new Builder();
            builder.addTags(list);
            return builder;
        }

        @NonNull
        @SuppressLint({"BuilderSetStyle"})
        public static Builder fromUniqueWorkNames(@NonNull List<String> list) {
            Builder builder = new Builder();
            builder.addUniqueWorkNames(list);
            return builder;
        }

        @NonNull
        public Builder addIds(@NonNull List<UUID> list) {
            this.f1786a.addAll(list);
            return this;
        }

        @NonNull
        public Builder addStates(@NonNull List<WorkInfo.State> list) {
            this.d.addAll(list);
            return this;
        }

        @NonNull
        public Builder addTags(@NonNull List<String> list) {
            this.c.addAll(list);
            return this;
        }

        @NonNull
        public Builder addUniqueWorkNames(@NonNull List<String> list) {
            this.b.addAll(list);
            return this;
        }

        @NonNull
        public WorkQuery build() {
            if (this.f1786a.isEmpty() && this.b.isEmpty() && this.c.isEmpty() && this.d.isEmpty()) {
                throw new IllegalArgumentException("Must specify ids, uniqueNames, tags or states when building a WorkQuery");
            }
            return new WorkQuery(this);
        }
    }

    public WorkQuery(@NonNull Builder builder) {
        this.f1785a = builder.f1786a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
    }

    @NonNull
    public List<UUID> getIds() {
        return this.f1785a;
    }

    @NonNull
    public List<WorkInfo.State> getStates() {
        return this.d;
    }

    @NonNull
    public List<String> getTags() {
        return this.c;
    }

    @NonNull
    public List<String> getUniqueWorkNames() {
        return this.b;
    }
}
