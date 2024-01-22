package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.NoticeSportActionToggleCallBack;
import com.ido.ble.callback.OperateCallBack;
import com.ido.ble.callback.SportPlanCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.FrequentContactsV3CmdParaWrapper;
import com.ido.ble.protocol.model.MessageNotifyStateCmdParaWrapper;
import com.ido.ble.protocol.model.MusicOperate;
import com.ido.ble.protocol.model.NoticeSportActionToggle;
import com.ido.ble.protocol.model.ScheduleReminderV3CmdParaWrapper;
import com.ido.ble.protocol.model.SmallQuickModule;
import com.ido.ble.protocol.model.SmallQuickModuleCmdParaWrapper;
import com.ido.ble.protocol.model.Sport100TypeSort;
import com.ido.ble.protocol.model.SportPlan;
import com.ido.ble.protocol.model.SportSubItemParaSort;
/* loaded from: classes11.dex */
final class n {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 5044) {
            c(bArr);
        } else if (i == 5039) {
            h(bArr);
        } else if (i == 5038) {
            g(bArr);
        } else if (i == 5037) {
            i(bArr);
        } else if (i == 5040) {
            k(bArr);
        } else if (i == 5042) {
            d(bArr);
        } else if (i == 5048) {
            b(bArr);
        } else if (i == 5049) {
            e(bArr);
        } else if (i == 5054) {
            j(bArr);
        } else if (i == 5056) {
            f(bArr);
        } else if (i == 5055) {
            a(bArr);
        }
    }

    private static void a(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleBLEToAppNoticeSportActionToggle JsonString] " + d);
        NoticeSportActionToggle noticeSportActionToggle = (NoticeSportActionToggle) new Gson().fromJson(d, (Class<Object>) NoticeSportActionToggle.class);
        if (noticeSportActionToggle == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleBLEToAppNoticeSportActionToggle JsonString] == null");
        } else {
            NoticeSportActionToggleCallBack.onDeviceNotify(noticeSportActionToggle.operate, noticeSportActionToggle.err_code == 0, noticeSportActionToggle);
        }
    }

    public static boolean a(int i) {
        return i == 5044 || i == 5039 || i == 5038 || i == 5037 || i == 5040 || i == 5042 || i == 5048 || i == 5049 || i == 5054 || i == 5056 || i == 5055;
    }

    private static void b(byte[] bArr) {
        MusicOperate.MusicAndFolderInfo musicAndFolderInfo;
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo JsonString] " + d);
        try {
            musicAndFolderInfo = (MusicOperate.MusicAndFolderInfo) new Gson().fromJson(d, (Class<Object>) MusicOperate.MusicAndFolderInfo.class);
        } catch (Exception e) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo ]" + e.getMessage());
            musicAndFolderInfo = null;
        }
        if (musicAndFolderInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleDeviceMusicAndFolderInfo ] response == null");
        } else {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.MUSIC_AND_FOLDER, musicAndFolderInfo);
        }
    }

    private static void c(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return V3FrequentContacts JsonString] " + d);
        FrequentContactsV3CmdParaWrapper.Response response = (FrequentContactsV3CmdParaWrapper.Response) new Gson().fromJson(d, (Class<Object>) FrequentContactsV3CmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return V3FrequentContacts JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operat;
        if (i == 1) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 100) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 200) {
            OperateCallBack.onDeleteResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 400) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.FREQUENT_CONTACTS, response.items);
        }
    }

    private static void d(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleMessageNotifyState JsonString] " + d);
        MessageNotifyStateCmdParaWrapper.Response response = (MessageNotifyStateCmdParaWrapper.Response) new Gson().fromJson(d, (Class<Object>) MessageNotifyStateCmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleMessageNotifyState JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operat;
        if (i == 1) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, Boolean.valueOf(z));
        } else if (i == 3) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.MESSAGE_NOTIFY_STATE, response);
        }
    }

    private static void e(byte[] bArr) {
        MusicOperate.OperateResponse operateResponse;
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult JsonString] " + d);
        try {
            operateResponse = (MusicOperate.OperateResponse) new Gson().fromJson(d, (Class<Object>) MusicOperate.OperateResponse.class);
        } catch (Exception e) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult JsonString] " + e.getMessage());
            operateResponse = null;
        }
        if (operateResponse == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleMusicOperateResult ] response == null");
            return;
        }
        boolean z = operateResponse.err_code == 0;
        int i = operateResponse.operate_type;
        if (i == 0) {
            OperateCallBack.onInvalid(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 1) {
            OperateCallBack.onDeleteMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onAddMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z), operateResponse.music_id);
        } else if (i == 3) {
            OperateCallBack.onDeleteFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 4) {
            OperateCallBack.onAddFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 5) {
            OperateCallBack.onModifyFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 6) {
            OperateCallBack.onImportFolder(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        } else if (i == 7) {
            OperateCallBack.onDeleteFolderMusic(OperateCallBack.OperateType.MUSIC_AND_FOLDER, Boolean.valueOf(z));
        }
    }

    private static void f(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleNoticeSportActionToggle JsonString] " + d);
        NoticeSportActionToggle noticeSportActionToggle = (NoticeSportActionToggle) new Gson().fromJson(d, (Class<Object>) NoticeSportActionToggle.class);
        if (noticeSportActionToggle == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleNoticeSportActionToggle JsonString] == null");
        } else {
            NoticeSportActionToggleCallBack.onSettingResult(noticeSportActionToggle.operate, noticeSportActionToggle.err_code == 0, noticeSportActionToggle);
        }
    }

    private static void g(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleScheduleReminder JsonString] " + d);
        ScheduleReminderV3CmdParaWrapper.Response response = (ScheduleReminderV3CmdParaWrapper.Response) new Gson().fromJson(d, (Class<Object>) ScheduleReminderV3CmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleScheduleReminder JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operate;
        if (i == 1) {
            OperateCallBack.onAddResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
        } else if (i == 2) {
            OperateCallBack.onDeleteResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
        } else if (i == 4) {
            OperateCallBack.onModifyResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, Boolean.valueOf(z));
        } else if (i == 3) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SCHEDULE_REMINDER, response.items);
        }
    }

    private static void h(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSmallQuickModuleSort JsonString] " + d);
        SmallQuickModuleCmdParaWrapper.Response response = (SmallQuickModuleCmdParaWrapper.Response) new Gson().fromJson(d, (Class<Object>) SmallQuickModuleCmdParaWrapper.Response.class);
        if (response == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSmallQuickModuleSort JsonString] response == null");
            return;
        }
        boolean z = response.err_code == 0;
        int i = response.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            SmallQuickModule.QueryResponse queryResponse = new SmallQuickModule.QueryResponse();
            queryResponse.items.addAll(response.items);
            queryResponse.support_items.addAll(response.support_items);
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SMALL_QUICK_MODULE_SORT, queryResponse);
        }
    }

    private static void i(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSport100TypeSort JsonString] " + d);
        Sport100TypeSort sport100TypeSort = (Sport100TypeSort) new Gson().fromJson(d, (Class<Object>) Sport100TypeSort.class);
        if (sport100TypeSort == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSport100TypeSort JsonString] response == null");
            return;
        }
        boolean z = sport100TypeSort.err_code == 0;
        int i = sport100TypeSort.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SPORT_100_TYPE_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SPORT_100_TYPE_SORT, sport100TypeSort);
        }
    }

    private static void j(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSportPlan JsonString] " + d);
        SportPlan sportPlan = (SportPlan) new Gson().fromJson(d, (Class<Object>) SportPlan.class);
        if (sportPlan == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return sportPlan JsonString] == null");
            return;
        }
        boolean z = sportPlan.err_code == 0;
        int i = sportPlan.operate;
        if (i == 1) {
            SportPlanCallBack.onStartPlan(z);
        } else if (i == 2) {
            SportPlanCallBack.onSportDataSend(z);
        } else if (i == 3) {
            SportPlanCallBack.onPlanEnd(z);
        } else if (i == 4) {
            SportPlanCallBack.onQueryResult(z, sportPlan);
        }
    }

    private static void k(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSportSubItemParaSort JsonString] " + d);
        SportSubItemParaSort sportSubItemParaSort = (SportSubItemParaSort) new Gson().fromJson(d, (Class<Object>) SportSubItemParaSort.class);
        if (sportSubItemParaSort == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[OPERATE_PARA] [handle Device Return handleSportSubItemParaSort JsonString] response == null");
            return;
        }
        boolean z = sportSubItemParaSort.err_code == 0;
        int i = sportSubItemParaSort.operate;
        if (i == 2) {
            OperateCallBack.onSetResult(OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT, Boolean.valueOf(z));
        } else if (i == 1) {
            OperateCallBack.onQueryResult(OperateCallBack.OperateType.SPORT_SUB_ITEM_PARA_SORT, sportSubItemParaSort);
        }
    }
}
