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
public class c extends CTInboxBaseMessageViewHolder {
    public final RelativeLayout o;
    public final Button p;
    public final Button q;
    public final Button r;
    public final LinearLayout s;
    public final ImageView t;
    public final TextView u;
    public final TextView v;
    public final TextView w;

    public c(@NonNull View view) {
        super(view);
        view.setTag(this);
        this.u = (TextView) view.findViewById(R.id.messageTitle);
        this.v = (TextView) view.findViewById(R.id.messageText);
        this.e = (ImageView) view.findViewById(R.id.media_image);
        this.t = (ImageView) view.findViewById(R.id.image_icon);
        this.w = (TextView) view.findViewById(R.id.timestamp);
        this.p = (Button) view.findViewById(R.id.cta_button_1);
        this.q = (Button) view.findViewById(R.id.cta_button_2);
        this.r = (Button) view.findViewById(R.id.cta_button_3);
        this.d = (FrameLayout) view.findViewById(R.id.icon_message_frame_layout);
        this.f = (ImageView) view.findViewById(R.id.square_media_image);
        this.o = (RelativeLayout) view.findViewById(R.id.click_relative_layout);
        this.s = (LinearLayout) view.findViewById(R.id.cta_linear_layout);
        this.h = (FrameLayout) view.findViewById(R.id.icon_progress_frame_layout);
        this.g = (RelativeLayout) view.findViewById(R.id.media_layout);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:18|(14:127|22|23|(11:25|(2:53|(3:55|56|57)(2:60|(3:62|63|64)(10:67|(2:69|(5:71|72|(1:74)(1:79)|75|76)(4:80|(1:82)(1:86)|83|(1:85)))(2:87|(2:89|(1:91)))|28|(1:30)(2:49|(1:51)(1:52))|31|32|33|(3:35|36|37)(1:47)|39|(2:41|42)(1:44))))|27|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(2:92|(11:94|95|96|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(2:99|(11:101|102|103|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(10:106|(2:108|(3:110|111|112)(2:115|(1:117)))(2:118|(2:120|(1:122)))|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))))|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))|21|22|23|(0)(0)|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(18:1|(1:3)(1:158)|4|(5:6|7|(1:(4:10|(3:132|133|(6:135|136|137|138|139|140))|12|13)(2:150|(1:152)))(2:153|(1:155))|141|13)(1:157)|14|15|16|(16:18|(14:127|22|23|(11:25|(2:53|(3:55|56|57)(2:60|(3:62|63|64)(10:67|(2:69|(5:71|72|(1:74)(1:79)|75|76)(4:80|(1:82)(1:86)|83|(1:85)))(2:87|(2:89|(1:91)))|28|(1:30)(2:49|(1:51)(1:52))|31|32|33|(3:35|36|37)(1:47)|39|(2:41|42)(1:44))))|27|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(2:92|(11:94|95|96|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(2:99|(11:101|102|103|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(10:106|(2:108|(3:110|111|112)(2:115|(1:117)))(2:118|(2:120|(1:122)))|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))))|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))|21|22|23|(0)(0)|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))(16:128|(14:130|22|23|(0)(0)|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))|21|22|23|(0)(0)|123|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0))|131|124|28|(0)(0)|31|32|33|(0)(0)|39|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0795, code lost:
        com.clevertap.android.sdk.Logger.d(r16);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:123:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0700  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0735 A[Catch: NoClassDefFoundError -> 0x0795, TRY_LEAVE, TryCatch #10 {NoClassDefFoundError -> 0x0795, blocks: (B:129:0x072b, B:131:0x0735, B:132:0x073b, B:134:0x0772, B:135:0x078d), top: B:162:0x072b, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x078d A[Catch: NoClassDefFoundError -> 0x0795, TRY_LEAVE, TryCatch #10 {NoClassDefFoundError -> 0x0795, blocks: (B:129:0x072b, B:131:0x0735, B:132:0x073b, B:134:0x0772, B:135:0x078d), top: B:162:0x072b, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:168:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0317 A[Catch: NoClassDefFoundError -> 0x06e3, TRY_LEAVE, TryCatch #2 {NoClassDefFoundError -> 0x06e3, blocks: (B:37:0x02fb, B:54:0x032c, B:56:0x0332, B:57:0x0344, B:59:0x037b, B:60:0x0396, B:62:0x039c, B:63:0x03ae, B:65:0x03ea, B:66:0x040a, B:68:0x0410, B:70:0x0420, B:42:0x030d, B:45:0x0317), top: B:145:0x02fb, inners: #8, #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0519 A[Catch: NoClassDefFoundError -> 0x06e4, TryCatch #0 {NoClassDefFoundError -> 0x06e4, blocks: (B:73:0x042a, B:75:0x0439, B:77:0x0474, B:74:0x0432, B:78:0x048d, B:80:0x049d, B:82:0x04ac, B:84:0x04bd, B:81:0x04a5, B:85:0x04d6, B:87:0x04dd, B:89:0x0500, B:90:0x0519, B:92:0x0520, B:93:0x0532, B:95:0x056a, B:96:0x0586, B:98:0x058c, B:99:0x059e, B:101:0x05da, B:102:0x05fa, B:104:0x0600, B:106:0x0610, B:107:0x061c, B:109:0x0654, B:110:0x0670, B:112:0x068a, B:113:0x06a2, B:115:0x06a8, B:117:0x06cb), top: B:142:0x0324, inners: #3, #4, #7, #11 }] */
    /* JADX WARN: Type inference failed for: r7v46, types: [android.widget.Button] */
    /* JADX WARN: Type inference failed for: r7v70 */
    @Override // com.clevertap.android.sdk.inbox.CTInboxBaseMessageViewHolder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(com.clevertap.android.sdk.inbox.CTInboxMessage r22, com.clevertap.android.sdk.inbox.CTInboxListViewFragment r23, int r24) {
        /*
            Method dump skipped, instructions count: 1967
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.inbox.c.c(com.clevertap.android.sdk.inbox.CTInboxMessage, com.clevertap.android.sdk.inbox.CTInboxListViewFragment, int):void");
    }
}
