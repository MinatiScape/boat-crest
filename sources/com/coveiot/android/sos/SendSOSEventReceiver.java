package com.coveiot.android.sos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.Constants;
import com.coveiot.android.sos.SendSOSEventReceiver;
import com.coveiot.android.sos.utils.SOSConstants;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.coveaccess.model.server.CommonResponseClass;
import com.coveiot.coveaccess.sos.CoveSOSApi;
import com.coveiot.coveaccess.sos.model.SosEventReq;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.sos.SOSEvents;
import com.coveiot.sdk.ble.api.response.SendSOSRes;
import com.coveiot.sdk.ble.helper.LogsHelper;
import com.coveiot.utils.permissions.PermissionUtils;
import com.coveiot.utils.utility.UtilConstants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class SendSOSEventReceiver extends BroadcastReceiver {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public SendSOSRes f5777a;
    public boolean b;

    /* loaded from: classes7.dex */
    public static final class a extends Lambda implements Function1<Location, Unit> {
        public final /* synthetic */ Context $context;
        public final /* synthetic */ FusedLocationProviderClient $locationClient;

        /* renamed from: com.coveiot.android.sos.SendSOSEventReceiver$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        public static final class C0304a extends Lambda implements Function1<Location, Unit> {
            public final /* synthetic */ Context $context;
            public final /* synthetic */ SendSOSEventReceiver this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0304a(SendSOSEventReceiver sendSOSEventReceiver, Context context) {
                super(1);
                this.this$0 = sendSOSEventReceiver;
                this.$context = context;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                invoke2(location);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Location location) {
                if (location != null) {
                    this.this$0.g(location, this.$context);
                } else {
                    this.this$0.g(null, this.$context);
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Context context, FusedLocationProviderClient fusedLocationProviderClient) {
            super(1);
            this.$context = context;
            this.$locationClient = fusedLocationProviderClient;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(Function1 tmp0, Object obj) {
            Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
            tmp0.invoke(obj);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$1(Context context, Exception it) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Intrinsics.checkNotNullParameter(it, "it");
            LogsHelper.d("Current Location", context.getString(R.string.location_not_found));
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
            invoke2(location);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Location location) {
            if (location != null) {
                SendSOSEventReceiver.this.g(location, this.$context);
                return;
            }
            Task<Location> lastLocation = this.$locationClient.getLastLocation();
            final C0304a c0304a = new C0304a(SendSOSEventReceiver.this, this.$context);
            Task<Location> addOnSuccessListener = lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.sos.q0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    SendSOSEventReceiver.a.invoke$lambda$0(Function1.this, obj);
                }
            });
            final Context context = this.$context;
            addOnSuccessListener.addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.sos.p0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SendSOSEventReceiver.a.invoke$lambda$1(context, exc);
                }
            });
        }
    }

    public static final void d(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void e(Context context, Exception it) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(it, "it");
        LogsHelper.d("Current Location", context.getString(R.string.location_not_found));
    }

    public final void c(final Context context) {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(context)");
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            Task<Location> currentLocation = fusedLocationProviderClient.getCurrentLocation(100, (CancellationToken) null);
            final a aVar = new a(context, fusedLocationProviderClient);
            currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: com.coveiot.android.sos.o0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) {
                    SendSOSEventReceiver.d(Function1.this, obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.coveiot.android.sos.n0
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    SendSOSEventReceiver.e(context, exc);
                }
            });
        }
    }

    public final void f(final Context context, final SendSOSRes sendSOSRes, Location location, String str, boolean z) {
        SosEventReq.EventItem eventItem = new SosEventReq.EventItem();
        if (z) {
            eventItem.setType("PHONE_CALL_AND_SMS");
            eventItem.setMessage(str);
        } else {
            eventItem.setType("PHONE_CALL");
        }
        SosEventReq.EventItem.ContactItem contactItem = new SosEventReq.EventItem.ContactItem();
        contactItem.setName(sendSOSRes.getContactName());
        contactItem.setMobileNumber(sendSOSRes.getContactNumber());
        eventItem.setContactItems(contactItem);
        int event = sendSOSRes.getEvent();
        if (event == 0) {
            eventItem.setStatus(SOSConstants.STARTED.getValue());
        } else if (event == 1) {
            eventItem.setStatus(SOSConstants.ABORTED.getValue());
        } else if (event == 2) {
            eventItem.setStatus(SOSConstants.CONNECTED.getValue());
        } else if (event == 3) {
            eventItem.setStatus(SOSConstants.FAILED.getValue());
        }
        Long unixTimeStamp = sendSOSRes.getUnixTimeStamp();
        eventItem.setEventDate(unixTimeStamp != null ? ThemesUtils.getDateFromTimeStamp(unixTimeStamp.longValue(), context, UtilConstants.SERVER_TIME_FORMAT) : null);
        if (location != null) {
            SosEventReq.EventItem.Location location2 = new SosEventReq.EventItem.Location();
            location2.setType("Point");
            ArrayList arrayList = new ArrayList();
            arrayList.add(Double.valueOf(location.getLatitude()));
            arrayList.add(Double.valueOf(location.getLongitude()));
            location2.setCoordinates(arrayList);
            eventItem.setLocation(location2);
        }
        SosEventReq sosEventReq = new SosEventReq();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(eventItem);
        sosEventReq.setEventItems(arrayList2);
        CoveSOSApi.postSosEvent(sosEventReq, new CoveApiListener<CommonResponseClass, CoveApiErrorModel>() { // from class: com.coveiot.android.sos.SendSOSEventReceiver$postSOSEvent$3
            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onError(@Nullable CoveApiErrorModel coveApiErrorModel) {
                LogsHelper.d("SOS event receiver", coveApiErrorModel != null ? coveApiErrorModel.getMsg() : null);
            }

            @Override // com.coveiot.coveaccess.CoveApiListener
            public void onSuccess(@Nullable CommonResponseClass commonResponseClass) {
                SendSOSEventReceiver.this.saveSOSEvents(context, sendSOSRes);
            }
        });
    }

    public final void g(Location location, Context context) {
        String str;
        URLSpan[] uRLSpanArr;
        if (location != null) {
            String str2 = "maps.google.com/?q=" + location.getLatitude() + ',' + location.getLongitude();
            Pattern compile = Pattern.compile("maps.google.com");
            Intrinsics.checkNotNullExpressionValue(compile, "compile(\"maps.google.com\")");
            Spanned fromHtml = Html.fromHtml(str2);
            Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(text)");
            Object[] spans = fromHtml.getSpans(0, str2.length(), URLSpan.class);
            Intrinsics.checkNotNullExpressionValue(spans, "text1.getSpans(0, text.l…gth, URLSpan::class.java)");
            SpannableString spannableString = new SpannableString(str2);
            Linkify.addLinks(spannableString, compile, "http://");
            for (URLSpan uRLSpan : (URLSpan[]) spans) {
                spannableString.setSpan(uRLSpan, fromHtml.getSpanStart(uRLSpan), fromHtml.getSpanEnd(uRLSpan), 0);
            }
            str = "Emergency SOS Alert\n" + SessionManager.getInstance(context).getUserDetails().getName() + " is in an emergency. You are added as an Emergency Contact.\nHere are the coordinates of " + SessionManager.getInstance(context).getUserDetails().getName() + "'s approximate location.\n" + ((Object) spannableString);
        } else {
            str = "Emergency SOS Alert\n" + SessionManager.getInstance(context).getUserDetails().getName() + " is in an emergency. You are receiving this message, as you are added as an Emergency Contact.";
        }
        String str3 = str;
        SendSOSRes sendSOSRes = this.f5777a;
        if ((sendSOSRes != null ? Integer.valueOf(sendSOSRes.getEvent()) : null) != null) {
            SendSOSRes sendSOSRes2 = this.f5777a;
            if ((sendSOSRes2 != null ? sendSOSRes2.getContactNumber() : null) != null) {
                String[] checkPermissionsHasGranted = PermissionUtils.checkPermissionsHasGranted(context, new String[]{"android.permission.SEND_SMS"});
                Intrinsics.checkNotNullExpressionValue(checkPermissionsHasGranted, "checkPermissionsHasGrant…MS)\n                    )");
                if (!(checkPermissionsHasGranted.length == 0)) {
                    if (location != null) {
                        SendSOSRes sendSOSRes3 = this.f5777a;
                        Intrinsics.checkNotNull(sendSOSRes3);
                        f(context, sendSOSRes3, location, str3, false);
                        return;
                    }
                    SendSOSRes sendSOSRes4 = this.f5777a;
                    Intrinsics.checkNotNull(sendSOSRes4);
                    f(context, sendSOSRes4, null, str3, false);
                    return;
                }
                SendSOSRes sendSOSRes5 = this.f5777a;
                if (sendSOSRes5 != null && sendSOSRes5.getEvent() == 0) {
                    if (!this.b) {
                        SmsManager smsManager = SmsManager.getDefault();
                        Intrinsics.checkNotNullExpressionValue(smsManager, "getDefault()");
                        ArrayList<String> divideMessage = smsManager.divideMessage(str3);
                        SendSOSRes sendSOSRes6 = this.f5777a;
                        smsManager.sendMultipartTextMessage(sendSOSRes6 != null ? sendSOSRes6.getContactNumber() : null, null, divideMessage, null, null);
                        this.b = true;
                    }
                } else {
                    this.b = false;
                }
                if (location != null) {
                    SendSOSRes sendSOSRes7 = this.f5777a;
                    Intrinsics.checkNotNull(sendSOSRes7);
                    f(context, sendSOSRes7, location, str3, true);
                    return;
                }
                SendSOSRes sendSOSRes8 = this.f5777a;
                Intrinsics.checkNotNull(sendSOSRes8);
                f(context, sendSOSRes8, null, str3, true);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (kotlin.text.m.equals$default(intent.getAction(), Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA, false, 2, null) && intent.hasExtra(Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA)) {
            Bundle extras = intent.getExtras();
            Intrinsics.checkNotNull(extras);
            Serializable serializable = extras.getSerializable(Constants.NOTIFY_SOS_BROADCAST_INTENT_EXTRA);
            Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type com.coveiot.sdk.ble.api.response.SendSOSRes");
            SendSOSRes sendSOSRes = (SendSOSRes) serializable;
            this.f5777a = sendSOSRes;
            LogsHelper.d("sendSOSEventRes", String.valueOf(sendSOSRes));
            if (this.f5777a != null) {
                c(context);
            }
        }
    }

    public final void saveSOSEvents(@NotNull Context context, @NotNull SendSOSRes sendSOSEventRes) {
        String value;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sendSOSEventRes, "sendSOSEventRes");
        int event = sendSOSEventRes.getEvent();
        boolean z = true;
        if (event == 0) {
            value = SOSConstants.STARTED.getValue();
        } else if (event == 1) {
            value = SOSConstants.ABORTED.getValue();
        } else if (event == 2) {
            value = SOSConstants.CONNECTED.getValue();
        } else if (event != 3) {
            value = SOSConstants.STARTED.getValue();
        } else {
            value = SOSConstants.FAILED.getValue();
        }
        SOSEvents sOSEvents = new SOSEvents(sendSOSEventRes.getUnixTimeStamp(), value, sendSOSEventRes.getContactName(), sendSOSEventRes.getContactNumber(), Boolean.TRUE);
        ArrayList<SOSEvents> arrayList = new ArrayList<>();
        ArrayList<SOSEvents> sOSEventsList = SessionManager.getInstance(context).getSOSEventsList();
        if (sOSEventsList != null && !sOSEventsList.isEmpty()) {
            z = false;
        }
        if (!z) {
            arrayList.addAll(SessionManager.getInstance(context).getSOSEventsList());
        }
        arrayList.add(sOSEvents);
        SessionManager.getInstance(context).saveSOSEventsList(arrayList);
    }
}
