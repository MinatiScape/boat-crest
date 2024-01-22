package androidx.work.impl;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.LiveData;
import androidx.work.ArrayCreatingInputMerger;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.workers.CombineContinuationsWorker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkContinuationImpl extends WorkContinuation {
    public static final String j = Logger.tagWithPrefix("WorkContinuationImpl");

    /* renamed from: a  reason: collision with root package name */
    public final WorkManagerImpl f1794a;
    public final String b;
    public final ExistingWorkPolicy c;
    public final List<? extends WorkRequest> d;
    public final List<String> e;
    public final List<String> f;
    public final List<WorkContinuationImpl> g;
    public boolean h;
    public Operation i;

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @NonNull List<? extends WorkRequest> list) {
        this(workManagerImpl, null, ExistingWorkPolicy.KEEP, list, null);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static boolean a(@NonNull WorkContinuationImpl workContinuationImpl, @NonNull Set<String> set) {
        set.addAll(workContinuationImpl.getIds());
        Set<String> prerequisitesFor = prerequisitesFor(workContinuationImpl);
        for (String str : set) {
            if (prerequisitesFor.contains(str)) {
                return true;
            }
        }
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                if (a(workContinuationImpl2, set)) {
                    return true;
                }
            }
        }
        set.removeAll(workContinuationImpl.getIds());
        return false;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static Set<String> prerequisitesFor(WorkContinuationImpl workContinuationImpl) {
        HashSet hashSet = new HashSet();
        List<WorkContinuationImpl> parents = workContinuationImpl.getParents();
        if (parents != null && !parents.isEmpty()) {
            for (WorkContinuationImpl workContinuationImpl2 : parents) {
                hashSet.addAll(workContinuationImpl2.getIds());
            }
        }
        return hashSet;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public WorkContinuation combineInternal(@NonNull List<WorkContinuation> list) {
        OneTimeWorkRequest build = new OneTimeWorkRequest.Builder(CombineContinuationsWorker.class).setInputMerger(ArrayCreatingInputMerger.class).build();
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<WorkContinuation> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add((WorkContinuationImpl) it.next());
        }
        return new WorkContinuationImpl(this.f1794a, null, ExistingWorkPolicy.KEEP, Collections.singletonList(build), arrayList);
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public Operation enqueue() {
        if (!this.h) {
            EnqueueRunnable enqueueRunnable = new EnqueueRunnable(this);
            this.f1794a.getWorkTaskExecutor().executeOnBackgroundThread(enqueueRunnable);
            this.i = enqueueRunnable.getOperation();
        } else {
            Logger.get().warning(j, String.format("Already enqueued work ids (%s)", TextUtils.join(", ", this.e)), new Throwable[0]);
        }
        return this.i;
    }

    public List<String> getAllIds() {
        return this.f;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.c;
    }

    @NonNull
    public List<String> getIds() {
        return this.e;
    }

    @Nullable
    public String getName() {
        return this.b;
    }

    public List<WorkContinuationImpl> getParents() {
        return this.g;
    }

    @NonNull
    public List<? extends WorkRequest> getWork() {
        return this.d;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public ListenableFuture<List<WorkInfo>> getWorkInfos() {
        StatusRunnable<List<WorkInfo>> forStringIds = StatusRunnable.forStringIds(this.f1794a, this.f);
        this.f1794a.getWorkTaskExecutor().executeOnBackgroundThread(forStringIds);
        return forStringIds.getFuture();
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public LiveData<List<WorkInfo>> getWorkInfosLiveData() {
        return this.f1794a.a(this.f);
    }

    @NonNull
    public WorkManagerImpl getWorkManagerImpl() {
        return this.f1794a;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean hasCycles() {
        return a(this, new HashSet());
    }

    public boolean isEnqueued() {
        return this.h;
    }

    public void markEnqueued() {
        this.h = true;
    }

    @Override // androidx.work.WorkContinuation
    @NonNull
    public WorkContinuation then(@NonNull List<OneTimeWorkRequest> list) {
        return list.isEmpty() ? this : new WorkContinuationImpl(this.f1794a, this.b, ExistingWorkPolicy.KEEP, list, Collections.singletonList(this));
    }

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @Nullable String str, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends WorkRequest> list) {
        this(workManagerImpl, str, existingWorkPolicy, list, null);
    }

    public WorkContinuationImpl(@NonNull WorkManagerImpl workManagerImpl, @Nullable String str, @NonNull ExistingWorkPolicy existingWorkPolicy, @NonNull List<? extends WorkRequest> list, @Nullable List<WorkContinuationImpl> list2) {
        this.f1794a = workManagerImpl;
        this.b = str;
        this.c = existingWorkPolicy;
        this.d = list;
        this.g = list2;
        this.e = new ArrayList(list.size());
        this.f = new ArrayList();
        if (list2 != null) {
            for (WorkContinuationImpl workContinuationImpl : list2) {
                this.f.addAll(workContinuationImpl.f);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String stringId = list.get(i).getStringId();
            this.e.add(stringId);
            this.f.add(stringId);
        }
    }
}
