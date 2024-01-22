package com.apex.bluetooth.model;

import org.bouncycastle.crypto.tls.CipherSuite;
/* loaded from: classes.dex */
public class EABleSocialContact {
    public String content;
    public String date;
    public SocialContactType eType;
    public SocialContactOps e_ops;
    public int id;
    public String title;

    /* loaded from: classes.dex */
    public enum SocialContactOps {
        add(0),
        edit(1),
        del(2),
        del_type(3),
        del_all(4);
        
        public int value;

        SocialContactOps(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: classes.dex */
    public enum SocialContactType {
        retain(0),
        incomingcall(1),
        missedcall(2),
        social(4),
        schedule(5),
        email(6),
        sms(13),
        unknow(100),
        wechat(101),
        qq(102),
        facebook(103),
        twitter(104),
        messenger(105),
        hangouts(106),
        gmail(107),
        viber(108),
        snapchat(109),
        whatsApp(110),
        instagram(111),
        linkedin(112),
        line(113),
        skype(114),
        booking(115),
        airbnb(116),
        flipboard(117),
        spotify(118),
        pandora(119),
        telegram(120),
        dropbox(121),
        waze(122),
        lift(123),
        slack(124),
        shazam(125),
        deliveroo(126),
        kakaotalk(127),
        pinterest(128),
        tumblr(129),
        vk(130),
        youtube(131),
        outlook(132),
        amazon(133),
        discord(134),
        github(135),
        google_maps(136),
        news_break(137),
        reddit(138),
        teams(139),
        tiktok(140),
        twitch(141),
        uber_eats(142),
        doordash(143),
        grubhub(144),
        instacart(145),
        postmates(CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA),
        zoom(147),
        uber(148),
        apple_email(149),
        ding_talk(150),
        alipay(151);
        
        public int value;

        SocialContactType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public String getContent() {
        return this.content;
    }

    public String getDate() {
        return this.date;
    }

    public SocialContactOps getE_ops() {
        return this.e_ops;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public SocialContactType geteType() {
        return this.eType;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public void setE_ops(SocialContactOps socialContactOps) {
        this.e_ops = socialContactOps;
    }

    public void setId(int i) {
        this.id = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void seteType(SocialContactType socialContactType) {
        this.eType = socialContactType;
    }
}
