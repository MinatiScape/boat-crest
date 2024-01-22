package com.coveiot.android.leonardo.firebaseservices.model;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.android.leonardo.firebaseservices.enums.InboxMessageTypes;
import com.coveiot.utils.CoveEventBusManager;
import com.coveiot.utils.utility.LogHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class InboxPersistenceManager {
    private static final String INBOX_PREFERENCES_FILE_NAME = "clove-inbox-preferences";
    private static final String INBOX_PREF_KEY = "clove-inbox";
    private static final String TAG = "com.coveiot.android.leonardo.firebaseservices.model.InboxPersistenceManager";
    private static InboxPersistenceManager cloveInboxPersistenceManager = new InboxPersistenceManager();
    private ArrayList<InboxMessageModel> inboxMessages = null;
    public Gson gson = new Gson();

    /* loaded from: classes2.dex */
    public class a extends TypeToken<List<InboxMessageModel>> {
        public a(InboxPersistenceManager inboxPersistenceManager) {
        }
    }

    private InboxPersistenceManager() {
    }

    public static synchronized InboxPersistenceManager getInstance() {
        InboxPersistenceManager inboxPersistenceManager;
        synchronized (InboxPersistenceManager.class) {
            inboxPersistenceManager = cloveInboxPersistenceManager;
        }
        return inboxPersistenceManager;
    }

    private void loadMessagesFromPersistence(Context context) {
        String string = context.getApplicationContext().getSharedPreferences(INBOX_PREFERENCES_FILE_NAME, 0).getString(INBOX_PREF_KEY, null);
        if (string == null) {
            this.inboxMessages = new ArrayList<>();
        } else {
            this.inboxMessages = (ArrayList) this.gson.fromJson(string, new a(this).getType());
        }
    }

    private void saveInboxMessagesToPersistence(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(INBOX_PREFERENCES_FILE_NAME, 0);
        try {
            if (this.inboxMessages != null) {
                sharedPreferences.edit().putString(INBOX_PREF_KEY, this.gson.toJson(this.inboxMessages)).commit();
            }
        } catch (Exception unused) {
            LogHelper.d(TAG, "Exception saving inbox to persistence");
        }
    }

    public void addMessageToInbox(Context context, InboxMessageModel inboxMessageModel) {
        if (this.inboxMessages == null) {
            loadMessagesFromPersistence(context);
        }
        this.inboxMessages.add(0, inboxMessageModel);
        saveInboxMessagesToPersistence(context);
        CoveEventBusManager.getInstance().getEventBus().post(new InboxMessageUpdated());
    }

    public int countOfMessagesNeedingAction(Context context) {
        if (this.inboxMessages == null) {
            loadMessagesFromPersistence(context);
        }
        Iterator<InboxMessageModel> it = this.inboxMessages.iterator();
        int i = 0;
        while (it.hasNext()) {
            InboxMessageModel next = it.next();
            if (next.getType() == InboxMessageTypes.YOU_WERE_NOMINATED_MESSAGE || next.getType() == InboxMessageTypes.YOU_WERE_INVITED_MESSAGE) {
                i++;
            }
        }
        return i;
    }

    public ArrayList<InboxMessageModel> getInboxMessages(Context context) {
        if (this.inboxMessages == null) {
            loadMessagesFromPersistence(context);
        }
        return this.inboxMessages;
    }

    public void saveMessagesToPersistence(Context context, ArrayList<InboxMessageModel> arrayList) {
        if (arrayList != null) {
            this.inboxMessages = arrayList;
            saveInboxMessagesToPersistence(context);
            CoveEventBusManager.getInstance().getEventBus().post(new InboxMessageUpdated());
        }
    }
}
