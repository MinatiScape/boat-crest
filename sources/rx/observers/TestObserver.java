package rx.observers;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Notification;
import rx.Observer;
import rx.exceptions.CompositeException;
@Deprecated
/* loaded from: classes13.dex */
public class TestObserver<T> implements Observer<T> {
    public static final Observer<Object> l = new a();
    public final Observer<T> h;
    public final List<T> i;
    public final List<Throwable> j;
    public final List<Notification<T>> k;

    /* loaded from: classes13.dex */
    public static class a implements Observer<Object> {
        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
        }
    }

    public TestObserver(Observer<T> observer) {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.h = observer;
    }

    public final void a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int size = this.k.size();
        sb.append(size);
        sb.append(" completion");
        if (size != 1) {
            sb.append('s');
        }
        sb.append(HexStringBuilder.COMMENT_END_CHAR);
        if (!this.j.isEmpty()) {
            int size2 = this.j.size();
            sb.append(" (+");
            sb.append(size2);
            sb.append(" error");
            if (size2 != 1) {
                sb.append('s');
            }
            sb.append(HexStringBuilder.COMMENT_END_CHAR);
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.j.isEmpty()) {
            if (this.j.size() == 1) {
                assertionError.initCause(this.j.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.j));
            }
        }
        throw assertionError;
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.i.size() != list.size()) {
            a("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.i.size() + ".\nProvided values: " + list + "\nActual values: " + this.i + "\n");
        }
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            T t2 = this.i.get(i);
            if (t == null) {
                if (t2 != null) {
                    a("Value at index: " + i + " expected to be [null] but was: [" + t2 + "]\n");
                }
            } else if (!t.equals(t2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Value at index: ");
                sb.append(i);
                sb.append(" expected to be [");
                sb.append(t);
                sb.append("] (");
                sb.append(t.getClass().getSimpleName());
                sb.append(") but was: [");
                sb.append(t2);
                sb.append("] (");
                sb.append(t2 != null ? t2.getClass().getSimpleName() : "null");
                sb.append(")\n");
                a(sb.toString());
            }
        }
    }

    public void assertTerminalEvent() {
        if (this.j.size() > 1) {
            a("Too many onError events: " + this.j.size());
        }
        if (this.k.size() > 1) {
            a("Too many onCompleted events: " + this.k.size());
        }
        if (this.k.size() == 1 && this.j.size() == 1) {
            a("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.k.isEmpty() && this.j.isEmpty()) {
            a("No terminal events received.");
        }
    }

    public List<Object> getEvents() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.i);
        arrayList.add(this.j);
        arrayList.add(this.k);
        return Collections.unmodifiableList(arrayList);
    }

    public List<Notification<T>> getOnCompletedEvents() {
        return Collections.unmodifiableList(this.k);
    }

    public List<Throwable> getOnErrorEvents() {
        return Collections.unmodifiableList(this.j);
    }

    public List<T> getOnNextEvents() {
        return Collections.unmodifiableList(this.i);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.k.add(Notification.createOnCompleted());
        this.h.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.j.add(th);
        this.h.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.i.add(t);
        this.h.onNext(t);
    }

    public TestObserver() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.h = (Observer<T>) l;
    }
}
