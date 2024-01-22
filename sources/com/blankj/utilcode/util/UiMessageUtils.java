package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public final class UiMessageUtils implements Handler.Callback {
    public static final boolean m = com.blankj.utilcode.util.b.p0();
    public final Handler h;
    public final UiMessage i;
    public final SparseArray<List<UiMessageCallback>> j;
    public final List<UiMessageCallback> k;
    public final List<UiMessageCallback> l;

    /* loaded from: classes.dex */
    public static final class UiMessage {

        /* renamed from: a  reason: collision with root package name */
        public Message f2298a;

        public final void b(Message message) {
            this.f2298a = message;
        }

        public int getId() {
            return this.f2298a.what;
        }

        public Object getObject() {
            return this.f2298a.obj;
        }

        public String toString() {
            return "{ id=" + getId() + ", obj=" + getObject() + " }";
        }

        public UiMessage(Message message) {
            this.f2298a = message;
        }
    }

    /* loaded from: classes.dex */
    public interface UiMessageCallback {
        void handleMessage(@NonNull UiMessage uiMessage);
    }

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final UiMessageUtils f2299a = new UiMessageUtils();
    }

    public static UiMessageUtils getInstance() {
        return b.f2299a;
    }

    public final void a(@NonNull UiMessage uiMessage) {
        Objects.requireNonNull(uiMessage, "Argument 'msg' of type UiMessage (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        List<UiMessageCallback> list = this.j.get(uiMessage.getId());
        if ((list == null || list.size() == 0) && this.k.size() == 0) {
            Log.w("UiMessageUtils", "Delivering FAILED for message ID " + uiMessage.getId() + ". No listeners. " + uiMessage.toString());
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Delivering message ID ");
        sb.append(uiMessage.getId());
        sb.append(", Specific listeners: ");
        if (list != null && list.size() != 0) {
            sb.append(list.size());
            sb.append(" [");
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getClass().getSimpleName());
                if (i < list.size() - 1) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
            }
            sb.append("]");
        } else {
            sb.append(0);
        }
        sb.append(", Universal listeners: ");
        synchronized (this.k) {
            if (this.k.size() == 0) {
                sb.append(0);
            } else {
                sb.append(this.k.size());
                sb.append(" [");
                for (int i2 = 0; i2 < this.k.size(); i2++) {
                    sb.append(this.k.get(i2).getClass().getSimpleName());
                    if (i2 < this.k.size() - 1) {
                        sb.append(Constants.SEPARATOR_COMMA);
                    }
                }
                sb.append("], Message: ");
            }
        }
        sb.append(uiMessage.toString());
        Log.v("UiMessageUtils", sb.toString());
    }

    public void addListener(int i, @NonNull UiMessageCallback uiMessageCallback) {
        Objects.requireNonNull(uiMessageCallback, "Argument 'listener' of type UiMessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        synchronized (this.j) {
            List<UiMessageCallback> list = this.j.get(i);
            if (list == null) {
                list = new ArrayList<>();
                this.j.put(i, list);
            }
            if (!list.contains(uiMessageCallback)) {
                list.add(uiMessageCallback);
            }
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        this.i.b(message);
        if (m) {
            a(this.i);
        }
        synchronized (this.j) {
            List<UiMessageCallback> list = this.j.get(message.what);
            if (list != null) {
                if (list.size() == 0) {
                    this.j.remove(message.what);
                } else {
                    this.l.addAll(list);
                    for (UiMessageCallback uiMessageCallback : this.l) {
                        uiMessageCallback.handleMessage(this.i);
                    }
                    this.l.clear();
                }
            }
        }
        synchronized (this.k) {
            if (this.k.size() > 0) {
                this.l.addAll(this.k);
                for (UiMessageCallback uiMessageCallback2 : this.l) {
                    uiMessageCallback2.handleMessage(this.i);
                }
                this.l.clear();
            }
        }
        this.i.b(null);
        return true;
    }

    public void removeListener(@NonNull UiMessageCallback uiMessageCallback) {
        Objects.requireNonNull(uiMessageCallback, "Argument 'listener' of type UiMessageCallback (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        synchronized (this.k) {
            if (m && !this.k.contains(uiMessageCallback)) {
                Log.w("UiMessageUtils", "Trying to remove a listener that is not registered. " + uiMessageCallback.toString());
            }
            this.k.remove(uiMessageCallback);
        }
    }

    public void removeListeners(int i) {
        List<UiMessageCallback> list;
        if (m && ((list = this.j.get(i)) == null || list.size() == 0)) {
            Log.w("UiMessageUtils", "Trying to remove specific listeners that are not registered. ID " + i);
        }
        synchronized (this.j) {
            this.j.delete(i);
        }
    }

    public final void send(int i) {
        this.h.sendEmptyMessage(i);
    }

    public UiMessageUtils() {
        this.h = new Handler(Looper.getMainLooper(), this);
        this.i = new UiMessage(null);
        this.j = new SparseArray<>();
        this.k = new ArrayList();
        this.l = new ArrayList();
    }

    public final void send(int i, @NonNull Object obj) {
        Objects.requireNonNull(obj, "Argument 'obj' of type Object (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        Handler handler = this.h;
        handler.sendMessage(handler.obtainMessage(i, obj));
    }

    public void removeListener(int i, @NonNull UiMessageCallback uiMessageCallback) {
        Objects.requireNonNull(uiMessageCallback, "Argument 'listener' of type UiMessageCallback (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        synchronized (this.j) {
            List<UiMessageCallback> list = this.j.get(i);
            if (list != null && !list.isEmpty()) {
                if (m && !list.contains(uiMessageCallback)) {
                    Log.w("UiMessageUtils", "Trying to remove specific listener that is not registered. ID " + i + ", " + uiMessageCallback);
                    return;
                }
                list.remove(uiMessageCallback);
            } else if (m) {
                Log.w("UiMessageUtils", "Trying to remove specific listener that is not registered. ID " + i + ", " + uiMessageCallback);
            }
        }
    }

    public void addListener(@NonNull UiMessageCallback uiMessageCallback) {
        Objects.requireNonNull(uiMessageCallback, "Argument 'listener' of type UiMessageCallback (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        synchronized (this.k) {
            if (!this.k.contains(uiMessageCallback)) {
                this.k.add(uiMessageCallback);
            } else if (m) {
                Log.w("UiMessageUtils", "Listener is already added. " + uiMessageCallback.toString());
            }
        }
    }
}
