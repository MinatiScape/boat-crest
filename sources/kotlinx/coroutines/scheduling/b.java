package kotlinx.coroutines.scheduling;
/* loaded from: classes12.dex */
public final class b implements TaskContext {
    public final int h;

    public b(int i) {
        this.h = i;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.h;
    }
}
