package com.clevertap.android.sdk.inbox;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.clevertap.android.sdk.R;
/* loaded from: classes2.dex */
public class k extends CTInboxBaseMessageViewHolder {
    public final Button o;
    public final Button p;
    public final Button q;
    public final TextView r;
    public final TextView s;
    public final TextView t;

    public k(@NonNull View view) {
        super(view);
        view.setTag(this);
        this.t = (TextView) view.findViewById(R.id.messageTitle);
        this.r = (TextView) view.findViewById(R.id.messageText);
        this.s = (TextView) view.findViewById(R.id.timestamp);
        this.o = (Button) view.findViewById(R.id.cta_button_1);
        this.p = (Button) view.findViewById(R.id.cta_button_2);
        this.q = (Button) view.findViewById(R.id.cta_button_3);
        this.e = (ImageView) view.findViewById(R.id.media_image);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.simple_message_relative_layout);
        this.d = (FrameLayout) view.findViewById(R.id.simple_message_frame_layout);
        this.f = (ImageView) view.findViewById(R.id.square_media_image);
        this.i = (RelativeLayout) view.findViewById(R.id.click_relative_layout);
        this.b = (LinearLayout) view.findViewById(R.id.cta_linear_layout);
        this.c = (LinearLayout) view.findViewById(R.id.body_linear_layout);
        this.h = (FrameLayout) view.findViewById(R.id.simple_progress_frame_layout);
        this.g = (RelativeLayout) view.findViewById(R.id.media_layout);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:22|(10:119|26|27|(7:29|(2:45|(3:47|48|49)(2:52|(3:54|55|56)(6:59|(2:61|(5:63|64|(1:66)(1:71)|67|68)(4:72|(1:74)(1:78)|75|(1:77)))(2:79|(2:81|(1:83)))|32|(1:34)(2:41|(1:43)(1:44))|35|(2:37|38)(1:40))))|31|32|(0)(0)|35|(0)(0))(2:84|(7:86|87|88|32|(0)(0)|35|(0)(0))(2:91|(7:93|94|95|32|(0)(0)|35|(0)(0))(6:98|(2:100|(3:102|103|104)(2:107|(1:109)))(2:110|(2:112|(1:114)))|32|(0)(0)|35|(0)(0))))|115|116|32|(0)(0)|35|(0)(0))|25|26|27|(0)(0)|115|116|32|(0)(0)|35|(0)(0)) */
    /* JADX WARN: Removed duplicated region for block: B:115:0x06a7  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x06b6  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x06e3  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04d2 A[Catch: NoClassDefFoundError -> 0x0698, TryCatch #6 {NoClassDefFoundError -> 0x0698, blocks: (B:65:0x03ee, B:67:0x03fd, B:69:0x0435, B:66:0x03f6, B:70:0x0451, B:72:0x0460, B:74:0x046f, B:76:0x0477, B:73:0x0468, B:77:0x0490, B:79:0x0497, B:81:0x04b9, B:82:0x04d2, B:84:0x04d9, B:85:0x04ea, B:87:0x0522, B:88:0x053e, B:90:0x0544, B:91:0x0555, B:93:0x0591, B:94:0x05b1, B:96:0x05b7, B:98:0x05c1, B:99:0x05d2, B:101:0x060a, B:102:0x0626, B:104:0x063f, B:105:0x0657, B:107:0x065d, B:109:0x067f), top: B:138:0x02eb, inners: #0, #2, #3, #4 }] */
    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(com.clevertap.android.sdk.inbox.CTInboxMessage r21, com.clevertap.android.sdk.inbox.CTInboxListViewFragment r22, int r23) {
        /*
            Method dump skipped, instructions count: 1784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inbox.k.c(com.clevertap.android.sdk.inbox.CTInboxMessage, com.clevertap.android.sdk.inbox.CTInboxListViewFragment, int):void");
    }
}
