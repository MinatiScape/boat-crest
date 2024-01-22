package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class StatusRunnable<T> implements Runnable {
    public final SettableFuture<T> h = SettableFuture.create();

    /* loaded from: classes.dex */
    public class a extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ List j;

        public a(WorkManagerImpl workManagerImpl, List list) {
            this.i = workManagerImpl;
            this.j = list;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        /* renamed from: b */
        public List<WorkInfo> a() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.i.getWorkDatabase().workSpecDao().getWorkStatusPojoForIds(this.j));
        }
    }

    /* loaded from: classes.dex */
    public class b extends StatusRunnable<WorkInfo> {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ UUID j;

        public b(WorkManagerImpl workManagerImpl, UUID uuid) {
            this.i = workManagerImpl;
            this.j = uuid;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        /* renamed from: b */
        public WorkInfo a() {
            WorkSpec.WorkInfoPojo workStatusPojoForId = this.i.getWorkDatabase().workSpecDao().getWorkStatusPojoForId(this.j.toString());
            if (workStatusPojoForId != null) {
                return workStatusPojoForId.toWorkInfo();
            }
            return null;
        }
    }

    /* loaded from: classes.dex */
    public class c extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ String j;

        public c(WorkManagerImpl workManagerImpl, String str) {
            this.i = workManagerImpl;
            this.j = str;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        /* renamed from: b */
        public List<WorkInfo> a() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.i.getWorkDatabase().workSpecDao().getWorkStatusPojoForTag(this.j));
        }
    }

    /* loaded from: classes.dex */
    public class d extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ String j;

        public d(WorkManagerImpl workManagerImpl, String str) {
            this.i = workManagerImpl;
            this.j = str;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        /* renamed from: b */
        public List<WorkInfo> a() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.i.getWorkDatabase().workSpecDao().getWorkStatusPojoForName(this.j));
        }
    }

    /* loaded from: classes.dex */
    public class e extends StatusRunnable<List<WorkInfo>> {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ WorkQuery j;

        public e(WorkManagerImpl workManagerImpl, WorkQuery workQuery) {
            this.i = workManagerImpl;
            this.j = workQuery;
        }

        @Override // androidx.work.impl.utils.StatusRunnable
        /* renamed from: b */
        public List<WorkInfo> a() {
            return WorkSpec.WORK_INFO_MAPPER.apply(this.i.getWorkDatabase().rawWorkInfoDao().getWorkInfoPojos(RawQueries.workQueryToRawQuery(this.j)));
        }
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forStringIds(@NonNull WorkManagerImpl workManagerImpl, @NonNull List<String> list) {
        return new a(workManagerImpl, list);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forTag(@NonNull WorkManagerImpl workManagerImpl, @NonNull String str) {
        return new c(workManagerImpl, str);
    }

    @NonNull
    public static StatusRunnable<WorkInfo> forUUID(@NonNull WorkManagerImpl workManagerImpl, @NonNull UUID uuid) {
        return new b(workManagerImpl, uuid);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forUniqueWork(@NonNull WorkManagerImpl workManagerImpl, @NonNull String str) {
        return new d(workManagerImpl, str);
    }

    @NonNull
    public static StatusRunnable<List<WorkInfo>> forWorkQuerySpec(@NonNull WorkManagerImpl workManagerImpl, @NonNull WorkQuery workQuery) {
        return new e(workManagerImpl, workQuery);
    }

    @WorkerThread
    public abstract T a();

    @NonNull
    public ListenableFuture<T> getFuture() {
        return this.h;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.h.set(a());
        } catch (Throwable th) {
            this.h.setException(th);
        }
    }
}
