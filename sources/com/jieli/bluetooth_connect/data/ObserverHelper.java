package com.jieli.bluetooth_connect.data;

import android.os.Handler;
import android.os.Looper;
import com.jieli.bluetooth_connect.bean.history.HistoryRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ObserverHelper extends HistoryRecordObserver {
    private final List<HistoryRecordObserver> observers = new ArrayList();
    private final Handler mUIHandler = new Handler(Looper.myLooper());

    /* loaded from: classes11.dex */
    public static abstract class ObserverEvent {
        private ObserverEvent() {
        }

        public abstract void onPost(HistoryRecordObserver historyRecordObserver);
    }

    /* loaded from: classes11.dex */
    public class ObserverRunnable implements Runnable {
        private final ObserverEvent event;

        public ObserverRunnable(ObserverEvent observerEvent) {
            this.event = observerEvent;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ObserverHelper.this.observers.isEmpty() || this.event == null) {
                return;
            }
            Iterator it = new ArrayList(ObserverHelper.this.observers).iterator();
            while (it.hasNext()) {
                this.event.onPost((HistoryRecordObserver) it.next());
            }
        }
    }

    private void postEvent(ObserverEvent observerEvent) {
        ObserverRunnable observerRunnable = new ObserverRunnable(observerEvent);
        if (Thread.currentThread().getId() != Looper.getMainLooper().getThread().getId()) {
            this.mUIHandler.post(observerRunnable);
        } else {
            observerRunnable.run();
        }
    }

    public void addHistoryRecordObserver(HistoryRecordObserver historyRecordObserver) {
        if (historyRecordObserver == null || this.observers.contains(historyRecordObserver)) {
            return;
        }
        this.observers.add(historyRecordObserver);
    }

    @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
    public void onDelete(final HistoryRecord historyRecord) {
        postEvent(new ObserverEvent() { // from class: com.jieli.bluetooth_connect.data.ObserverHelper.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.data.ObserverHelper.ObserverEvent
            public void onPost(HistoryRecordObserver historyRecordObserver) {
                historyRecordObserver.onDelete(historyRecord);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
    public void onInsert(final HistoryRecord historyRecord) {
        postEvent(new ObserverEvent() { // from class: com.jieli.bluetooth_connect.data.ObserverHelper.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.data.ObserverHelper.ObserverEvent
            public void onPost(HistoryRecordObserver historyRecordObserver) {
                historyRecordObserver.onInsert(historyRecord);
            }
        });
    }

    @Override // com.jieli.bluetooth_connect.data.HistoryRecordObserver
    public void onModify(final HistoryRecord historyRecord) {
        postEvent(new ObserverEvent() { // from class: com.jieli.bluetooth_connect.data.ObserverHelper.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            @Override // com.jieli.bluetooth_connect.data.ObserverHelper.ObserverEvent
            public void onPost(HistoryRecordObserver historyRecordObserver) {
                historyRecordObserver.onModify(historyRecord);
            }
        });
    }

    public void release() {
        this.observers.clear();
        this.mUIHandler.removeCallbacksAndMessages(null);
    }

    public void removeHistoryRecordObserver(HistoryRecordObserver historyRecordObserver) {
        if (historyRecordObserver == null || this.observers.isEmpty()) {
            return;
        }
        this.observers.remove(historyRecordObserver);
    }
}
