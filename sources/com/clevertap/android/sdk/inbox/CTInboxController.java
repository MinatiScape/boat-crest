package com.clevertap.android.sdk.inbox;

import androidx.annotation.AnyThread;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.BaseCallbackManager;
import com.clevertap.android.sdk.CTLockManager;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.db.DBAdapter;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import com.clevertap.android.sdk.task.OnFailureListener;
import com.clevertap.android.sdk.task.OnSuccessListener;
import com.clevertap.android.sdk.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CTInboxController {

    /* renamed from: a  reason: collision with root package name */
    public final DBAdapter f2632a;
    public ArrayList<CTMessageDAO> b;
    public final Object c = new Object();
    public final String d;
    public final boolean e;
    public final CTLockManager f;
    public final BaseCallbackManager g;
    public final CleverTapInstanceConfig h;

    /* loaded from: classes2.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ CTInboxMessage h;

        public a(CTInboxMessage cTInboxMessage) {
            this.h = cTInboxMessage;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (CTInboxController.this.f.getInboxControllerLock()) {
                if (CTInboxController.this.e(this.h.getMessageId())) {
                    CTInboxController.this.g._notifyInboxMessagesDidUpdate();
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ ArrayList h;

        public b(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (CTInboxController.this.f.getInboxControllerLock()) {
                if (CTInboxController.this.f(this.h)) {
                    CTInboxController.this.g._notifyInboxMessagesDidUpdate();
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class c implements Callable<Void> {
        public final /* synthetic */ CTInboxMessage h;

        public c(CTInboxMessage cTInboxMessage) {
            this.h = cTInboxMessage;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (CTInboxController.this.f.getInboxControllerLock()) {
                if (CTInboxController.this.g(this.h.getMessageId())) {
                    CTInboxController.this.g._notifyInboxMessagesDidUpdate();
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class d implements Callable<Void> {
        public final /* synthetic */ ArrayList h;

        public d(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() {
            synchronized (CTInboxController.this.f.getInboxControllerLock()) {
                if (CTInboxController.this.h(this.h)) {
                    CTInboxController.this.g._notifyInboxMessagesDidUpdate();
                }
            }
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class e implements Callable<Void> {
        public final /* synthetic */ String h;

        public e(String str) {
            this.h = str;
        }

        @Override // java.util.concurrent.Callable
        @WorkerThread
        /* renamed from: a */
        public Void call() {
            CTInboxController.this.f2632a.deleteMessageForId(this.h, CTInboxController.this.d);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class f implements Callable<Void> {
        public final /* synthetic */ ArrayList h;

        public f(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.util.concurrent.Callable
        @WorkerThread
        /* renamed from: a */
        public Void call() {
            CTInboxController.this.f2632a.deleteMessagesForIDs(this.h, CTInboxController.this.d);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class g implements Callable<Void> {
        public final /* synthetic */ String h;

        public g(String str) {
            this.h = str;
        }

        @Override // java.util.concurrent.Callable
        @WorkerThread
        /* renamed from: a */
        public Void call() {
            CTInboxController.this.f2632a.markReadMessageForId(this.h, CTInboxController.this.d);
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public class h implements Callable<Void> {
        public final /* synthetic */ ArrayList h;

        public h(ArrayList arrayList) {
            this.h = arrayList;
        }

        @Override // java.util.concurrent.Callable
        @WorkerThread
        /* renamed from: a */
        public Void call() {
            CTInboxController.this.f2632a.markReadMessagesForIds(this.h, CTInboxController.this.d);
            return null;
        }
    }

    @WorkerThread
    public CTInboxController(CleverTapInstanceConfig cleverTapInstanceConfig, String str, DBAdapter dBAdapter, CTLockManager cTLockManager, BaseCallbackManager baseCallbackManager, boolean z) {
        this.d = str;
        this.f2632a = dBAdapter;
        this.b = dBAdapter.getMessages(str);
        this.e = z;
        this.f = cTLockManager;
        this.g = baseCallbackManager;
        this.h = cleverTapInstanceConfig;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(Void r1) {
        this.g._notifyInboxMessagesDidUpdate();
    }

    public static /* synthetic */ void o(String str, Exception exc) {
        Logger.d("Failed to update message read state for id:" + str, exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p(Void r1) {
        this.g._notifyInboxMessagesDidUpdate();
    }

    public static /* synthetic */ void q(ArrayList arrayList, Exception exc) {
        Logger.d("Failed to update message read state for ids:" + arrayList, exc);
    }

    public int count() {
        return getMessages().size();
    }

    @AnyThread
    public void deleteInboxMessage(CTInboxMessage cTInboxMessage) {
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("deleteInboxMessage", new a(cTInboxMessage));
    }

    @AnyThread
    public void deleteInboxMessagesForIDs(ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("deleteInboxMessagesForIDs", new b(arrayList));
    }

    @AnyThread
    public boolean e(String str) {
        CTMessageDAO m = m(str);
        if (m == null) {
            return false;
        }
        synchronized (this.c) {
            this.b.remove(m);
        }
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("RunDeleteMessage", new e(str));
        return true;
    }

    @AnyThread
    public boolean f(ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            CTMessageDAO m = m(it.next());
            if (m != null) {
                arrayList2.add(m);
            }
        }
        if (arrayList2.isEmpty()) {
            return false;
        }
        synchronized (this.c) {
            this.b.removeAll(arrayList2);
        }
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("RunDeleteMessagesForIDs", new f(arrayList));
        return true;
    }

    @AnyThread
    public boolean g(final String str) {
        CTMessageDAO m = m(str);
        if (m == null) {
            return false;
        }
        synchronized (this.c) {
            m.setRead(1);
        }
        Task postAsyncSafelyTask = CTExecutorFactory.executors(this.h).postAsyncSafelyTask();
        postAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.inbox.i
            @Override // com.clevertap.android.sdk.task.OnSuccessListener
            public final void onSuccess(Object obj) {
                CTInboxController.this.n((Void) obj);
            }
        });
        postAsyncSafelyTask.addOnFailureListener(new OnFailureListener() { // from class: com.clevertap.android.sdk.inbox.f
            @Override // com.clevertap.android.sdk.task.OnFailureListener
            public final void onFailure(Object obj) {
                CTInboxController.o(str, (Exception) obj);
            }
        });
        postAsyncSafelyTask.execute("RunMarkMessageRead", new g(str));
        return true;
    }

    @AnyThread
    public CTMessageDAO getMessageForId(String str) {
        return m(str);
    }

    @AnyThread
    public ArrayList<CTMessageDAO> getMessages() {
        ArrayList<CTMessageDAO> arrayList;
        synchronized (this.c) {
            r();
            arrayList = this.b;
        }
        return arrayList;
    }

    @AnyThread
    public ArrayList<CTMessageDAO> getUnreadMessages() {
        ArrayList<CTMessageDAO> arrayList = new ArrayList<>();
        synchronized (this.c) {
            Iterator<CTMessageDAO> it = getMessages().iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (next.isRead() == 0) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    @AnyThread
    public boolean h(final ArrayList<String> arrayList) {
        Boolean bool = Boolean.FALSE;
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            CTMessageDAO m = m(it.next());
            if (m != null) {
                bool = Boolean.TRUE;
                synchronized (this.c) {
                    m.setRead(1);
                }
            }
        }
        if (bool.booleanValue()) {
            Task postAsyncSafelyTask = CTExecutorFactory.executors(this.h).postAsyncSafelyTask();
            postAsyncSafelyTask.addOnSuccessListener(new OnSuccessListener() { // from class: com.clevertap.android.sdk.inbox.h
                @Override // com.clevertap.android.sdk.task.OnSuccessListener
                public final void onSuccess(Object obj) {
                    CTInboxController.this.p((Void) obj);
                }
            });
            postAsyncSafelyTask.addOnFailureListener(new OnFailureListener() { // from class: com.clevertap.android.sdk.inbox.g
                @Override // com.clevertap.android.sdk.task.OnFailureListener
                public final void onFailure(Object obj) {
                    CTInboxController.q(arrayList, (Exception) obj);
                }
            });
            postAsyncSafelyTask.execute("RunMarkMessagesReadForIDs", new h(arrayList));
            return true;
        }
        return false;
    }

    @AnyThread
    public final CTMessageDAO m(String str) {
        synchronized (this.c) {
            Iterator<CTMessageDAO> it = this.b.iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (next.getId().equals(str)) {
                    return next;
                }
            }
            Logger.v("Inbox Message for message id - " + str + " not found");
            return null;
        }
    }

    @AnyThread
    public void markReadInboxMessage(CTInboxMessage cTInboxMessage) {
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("markReadInboxMessage", new c(cTInboxMessage));
    }

    @AnyThread
    public void markReadInboxMessagesForIDs(ArrayList<String> arrayList) {
        CTExecutorFactory.executors(this.h).postAsyncSafelyTask().execute("markReadInboxMessagesForIDs", new d(arrayList));
    }

    @AnyThread
    public final void r() {
        Logger.v("CTInboxController:trimMessages() called");
        ArrayList arrayList = new ArrayList();
        synchronized (this.c) {
            Iterator<CTMessageDAO> it = this.b.iterator();
            while (it.hasNext()) {
                CTMessageDAO next = it.next();
                if (!this.e && next.a()) {
                    Logger.d("Removing inbox message containing video/audio as app does not support video. For more information checkout CleverTap documentation.");
                    arrayList.add(next);
                } else {
                    long expires = next.getExpires();
                    if (expires > 0 && System.currentTimeMillis() / 1000 > expires) {
                        Logger.v("Inbox Message: " + next.getId() + " is expired - removing");
                        arrayList.add(next);
                    }
                }
            }
            if (arrayList.size() <= 0) {
                return;
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                e(((CTMessageDAO) it2.next()).getId());
            }
        }
    }

    @AnyThread
    public int unreadCount() {
        return getUnreadMessages().size();
    }

    @WorkerThread
    public boolean updateMessages(JSONArray jSONArray) {
        Logger.v("CTInboxController:updateMessages() called");
        ArrayList<CTMessageDAO> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                CTMessageDAO c2 = CTMessageDAO.c(jSONArray.getJSONObject(i), this.d);
                if (c2 != null) {
                    if (!this.e && c2.a()) {
                        Logger.d("Dropping inbox message containing video/audio as app does not support video. For more information checkout CleverTap documentation.");
                    } else {
                        arrayList.add(c2);
                        Logger.v("Inbox Message for message id - " + c2.getId() + " added");
                    }
                }
            } catch (JSONException e2) {
                Logger.d("Unable to update notification inbox messages - " + e2.getLocalizedMessage());
            }
        }
        if (arrayList.size() > 0) {
            this.f2632a.upsertMessages(arrayList);
            Logger.v("New Notification Inbox messages added");
            synchronized (this.c) {
                this.b = this.f2632a.getMessages(this.d);
                r();
            }
            return true;
        }
        return false;
    }
}
