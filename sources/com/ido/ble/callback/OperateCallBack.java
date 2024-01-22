package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class OperateCallBack {
    public static final int ADD_FOLDER = 4;
    public static final int ADD_MUSIC = 2;
    public static final int DELETE_FOLDER = 3;
    public static final int DELETE_FOLDER_MUSIC = 7;
    public static final int DELETE_MUSIC = 1;
    public static final int IMPORT_FOLDER = 6;
    public static final int INVALID = 0;
    public static final int MODIFY_FOLDER = 5;

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onAddResult(OperateType operateType, boolean z);

        void onDeleteResult(OperateType operateType, boolean z);

        void onModifyResult(OperateType operateType, boolean z);

        void onQueryResult(OperateType operateType, Object obj);

        void onSetResult(OperateType operateType, boolean z);
    }

    /* loaded from: classes11.dex */
    public interface IMusicCallBack {
        void onAddFolder(OperateType operateType, boolean z);

        void onAddMusic(OperateType operateType, boolean z, int i);

        void onDeleteFolder(OperateType operateType, boolean z);

        void onDeleteFolderMusic(OperateType operateType, boolean z);

        void onDeleteMusic(OperateType operateType, boolean z);

        void onImportFolder(OperateType operateType, boolean z);

        void onInvalid(OperateType operateType, boolean z);

        void onModifyFolder(OperateType operateType, boolean z);
    }

    /* loaded from: classes11.dex */
    public enum OperateType {
        FREQUENT_CONTACTS,
        SCHEDULE_REMINDER,
        SMALL_QUICK_MODULE_SORT,
        SPORT_100_TYPE_SORT,
        SPORT_SUB_ITEM_PARA_SORT,
        MESSAGE_NOTIFY_STATE,
        MUSIC_AND_FOLDER
    }

    public static final void onAddFolder(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onAddFolder(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onAddMusic(final OperateType operateType, final Boolean bool, final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onAddMusic(OperateType.this, bool.booleanValue(), i);
                }
            }
        });
    }

    public static final void onAddResult(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().u()) {
                    iCallBack.onAddResult(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onDeleteFolder(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onDeleteFolder(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onDeleteFolderMusic(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onDeleteFolderMusic(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onDeleteMusic(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onDeleteMusic(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onDeleteResult(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().u()) {
                    iCallBack.onDeleteResult(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onImportFolder(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onImportFolder(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onInvalid(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onInvalid(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onModifyFolder(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (IMusicCallBack iMusicCallBack : b.N().v()) {
                    iMusicCallBack.onModifyFolder(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onModifyResult(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().u()) {
                    iCallBack.onModifyResult(OperateType.this, bool.booleanValue());
                }
            }
        });
    }

    public static final void onQueryResult(final OperateType operateType, final Object obj) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().u()) {
                    iCallBack.onQueryResult(OperateType.this, obj);
                }
            }
        });
    }

    public static final void onSetResult(final OperateType operateType, final Boolean bool) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OperateCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().u()) {
                    iCallBack.onSetResult(OperateType.this, bool.booleanValue());
                }
            }
        });
    }
}
