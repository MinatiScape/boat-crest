package com.jieli.jl_filebrowse.tool;

import android.os.Handler;
import android.os.Looper;
import com.jieli.jl_filebrowse.bean.FileStruct;
import com.jieli.jl_filebrowse.bean.SDCardBean;
import com.jieli.jl_filebrowse.interfaces.FileObserver;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class FileObserverHelper implements FileObserver {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList<FileObserver> f12417a = new ArrayList<>();
    private final Handler b = new Handler(Looper.getMainLooper());

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void OnFlayCallback(final boolean z) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.d
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).OnFlayCallback(z);
            }
        });
    }

    public void addFileObserver(FileObserver fileObserver) {
        if (fileObserver == null || this.f12417a.contains(fileObserver)) {
            return;
        }
        this.f12417a.add(fileObserver);
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void onFileReadFailed(final int i) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.a
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).onFileReadFailed(i);
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void onFileReadStart() {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.f
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).onFileReadStart();
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void onFileReadStop(final boolean z) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.e
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).onFileReadStop(z);
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void onFileReceiver(final List<FileStruct> list) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.b
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).onFileReceiver(list);
            }
        });
    }

    @Override // com.jieli.jl_filebrowse.interfaces.FileObserver
    public void onSdCardStatusChange(final List<SDCardBean> list) {
        a(new CallbackImpl() { // from class: com.jieli.jl_filebrowse.tool.c
            @Override // com.jieli.jl_filebrowse.tool.CallbackImpl
            public final void onCallback(Object obj) {
                ((FileObserver) obj).onSdCardStatusChange(list);
            }
        });
    }

    public void release() {
        this.f12417a.clear();
        this.b.removeCallbacksAndMessages(null);
    }

    public void removeFileObserver(FileObserver fileObserver) {
        if (fileObserver == null || this.f12417a.isEmpty()) {
            return;
        }
        this.f12417a.remove(fileObserver);
    }

    private void a(CallbackImpl<FileObserver> callbackImpl) {
        if (callbackImpl == null) {
            return;
        }
        CallbackRunnable callbackRunnable = new CallbackRunnable(this.f12417a, callbackImpl);
        if (Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()) {
            callbackRunnable.run();
        } else {
            this.b.post(callbackRunnable);
        }
    }
}
