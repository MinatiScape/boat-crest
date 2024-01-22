package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class JavaBeanSerializer implements ObjectSerializer {
    public static final char[] c = {Constants.INAPP_POSITION_TOP, Constants.INAPP_POSITION_RIGHT, 'u', 'e'};
    public static final char[] d = {'f', 'a', Constants.INAPP_POSITION_LEFT, 's', 'e'};

    /* renamed from: a  reason: collision with root package name */
    public final FieldSerializer[] f2132a;
    public final FieldSerializer[] b;
    public int features;
    public final String typeKey;
    public final String typeName;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (PropertyNamingStrategy) null);
    }

    public static Map<String, String> a(String... strArr) {
        HashMap hashMap = new HashMap();
        for (String str : strArr) {
            hashMap.put(str, str);
        }
        return hashMap;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        FieldSerializer[] fieldSerializerArr;
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.b.length);
        for (FieldSerializer fieldSerializer : this.b) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0133 A[Catch: all -> 0x0097, Exception -> 0x009c, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x009c, all -> 0x0097, blocks: (B:41:0x008a, B:43:0x008e, B:44:0x0092, B:52:0x00ac, B:54:0x00b5, B:58:0x00c4, B:61:0x00cb, B:63:0x00d2, B:65:0x00d6, B:71:0x00e0, B:73:0x00e6, B:77:0x00ef, B:79:0x00f7, B:80:0x00ff, B:89:0x0110, B:90:0x0114, B:92:0x011a, B:100:0x0133, B:76:0x00eb), top: B:472:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0241 A[Catch: all -> 0x05a5, Exception -> 0x05ab, TryCatch #8 {Exception -> 0x05ab, all -> 0x05a5, blocks: (B:118:0x0165, B:120:0x0180, B:122:0x0184, B:125:0x0189, B:127:0x018d, B:131:0x0196, B:132:0x019a, B:134:0x01a0, B:142:0x01bd, B:144:0x01c4, B:146:0x01c8, B:159:0x0214, B:161:0x021a, B:171:0x0237, B:172:0x023b, B:174:0x0241, B:185:0x0269, B:187:0x026d, B:189:0x0274, B:191:0x0278, B:192:0x027d, B:194:0x0281, B:195:0x0286, B:196:0x028d, B:198:0x0293, B:203:0x02ad, B:205:0x02b1, B:207:0x02b8, B:209:0x02bc, B:210:0x02c1, B:212:0x02c5, B:213:0x02ca, B:214:0x02d1, B:216:0x02d7, B:224:0x02f9, B:226:0x0307, B:228:0x0314, B:230:0x0318, B:233:0x031d, B:235:0x0320, B:294:0x03d1, B:296:0x03d5, B:298:0x03d9, B:300:0x03dd, B:302:0x03e1, B:304:0x03e5, B:311:0x03f7, B:313:0x03fb, B:315:0x03ff, B:306:0x03e9, B:308:0x03ed, B:319:0x040f, B:321:0x0418, B:323:0x041c, B:324:0x0420, B:325:0x0424, B:327:0x0437, B:331:0x0441, B:332:0x0445, B:336:0x044f, B:337:0x0452, B:340:0x045a, B:342:0x0465, B:344:0x0469, B:346:0x046e, B:350:0x048a, B:351:0x0494, B:354:0x049b, B:358:0x04a5, B:363:0x04b1, B:365:0x04b7, B:367:0x04bb, B:368:0x04bd, B:370:0x04c5, B:372:0x04c9, B:373:0x04cd, B:376:0x04dc, B:377:0x04e6, B:378:0x04e9, B:380:0x04ed, B:381:0x04f6, B:384:0x04fc, B:385:0x0507, B:388:0x0516, B:390:0x051d, B:392:0x0526, B:395:0x052a, B:396:0x052f, B:397:0x0536, B:399:0x053a, B:400:0x053f, B:401:0x0546, B:404:0x054e, B:406:0x0557, B:411:0x056b, B:412:0x0570, B:413:0x0575, B:414:0x0580, B:415:0x0585, B:416:0x058a, B:239:0x0327, B:243:0x0335, B:245:0x0342, B:247:0x0346, B:250:0x034b, B:252:0x034e, B:255:0x0356, B:257:0x0360, B:259:0x036b, B:261:0x036f, B:264:0x0376, B:266:0x0379, B:268:0x037e, B:269:0x0385, B:271:0x038d, B:273:0x0398, B:275:0x039c, B:278:0x03a3, B:280:0x03a6, B:282:0x03ab, B:284:0x03b2, B:286:0x03b6, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:168:0x022e, B:147:0x01d8, B:149:0x01dc, B:150:0x01e8, B:152:0x01ec, B:153:0x01fc, B:154:0x0203, B:429:0x05c0, B:430:0x05c4, B:432:0x05ca, B:437:0x05dc, B:439:0x05e5, B:442:0x05f4, B:444:0x05f8, B:445:0x05fc), top: B:474:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x040f A[Catch: all -> 0x05a5, Exception -> 0x05ab, TryCatch #8 {Exception -> 0x05ab, all -> 0x05a5, blocks: (B:118:0x0165, B:120:0x0180, B:122:0x0184, B:125:0x0189, B:127:0x018d, B:131:0x0196, B:132:0x019a, B:134:0x01a0, B:142:0x01bd, B:144:0x01c4, B:146:0x01c8, B:159:0x0214, B:161:0x021a, B:171:0x0237, B:172:0x023b, B:174:0x0241, B:185:0x0269, B:187:0x026d, B:189:0x0274, B:191:0x0278, B:192:0x027d, B:194:0x0281, B:195:0x0286, B:196:0x028d, B:198:0x0293, B:203:0x02ad, B:205:0x02b1, B:207:0x02b8, B:209:0x02bc, B:210:0x02c1, B:212:0x02c5, B:213:0x02ca, B:214:0x02d1, B:216:0x02d7, B:224:0x02f9, B:226:0x0307, B:228:0x0314, B:230:0x0318, B:233:0x031d, B:235:0x0320, B:294:0x03d1, B:296:0x03d5, B:298:0x03d9, B:300:0x03dd, B:302:0x03e1, B:304:0x03e5, B:311:0x03f7, B:313:0x03fb, B:315:0x03ff, B:306:0x03e9, B:308:0x03ed, B:319:0x040f, B:321:0x0418, B:323:0x041c, B:324:0x0420, B:325:0x0424, B:327:0x0437, B:331:0x0441, B:332:0x0445, B:336:0x044f, B:337:0x0452, B:340:0x045a, B:342:0x0465, B:344:0x0469, B:346:0x046e, B:350:0x048a, B:351:0x0494, B:354:0x049b, B:358:0x04a5, B:363:0x04b1, B:365:0x04b7, B:367:0x04bb, B:368:0x04bd, B:370:0x04c5, B:372:0x04c9, B:373:0x04cd, B:376:0x04dc, B:377:0x04e6, B:378:0x04e9, B:380:0x04ed, B:381:0x04f6, B:384:0x04fc, B:385:0x0507, B:388:0x0516, B:390:0x051d, B:392:0x0526, B:395:0x052a, B:396:0x052f, B:397:0x0536, B:399:0x053a, B:400:0x053f, B:401:0x0546, B:404:0x054e, B:406:0x0557, B:411:0x056b, B:412:0x0570, B:413:0x0575, B:414:0x0580, B:415:0x0585, B:416:0x058a, B:239:0x0327, B:243:0x0335, B:245:0x0342, B:247:0x0346, B:250:0x034b, B:252:0x034e, B:255:0x0356, B:257:0x0360, B:259:0x036b, B:261:0x036f, B:264:0x0376, B:266:0x0379, B:268:0x037e, B:269:0x0385, B:271:0x038d, B:273:0x0398, B:275:0x039c, B:278:0x03a3, B:280:0x03a6, B:282:0x03ab, B:284:0x03b2, B:286:0x03b6, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:168:0x022e, B:147:0x01d8, B:149:0x01dc, B:150:0x01e8, B:152:0x01ec, B:153:0x01fc, B:154:0x0203, B:429:0x05c0, B:430:0x05c4, B:432:0x05ca, B:437:0x05dc, B:439:0x05e5, B:442:0x05f4, B:444:0x05f8, B:445:0x05fc), top: B:474:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:328:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x04dc A[Catch: all -> 0x05a5, Exception -> 0x05ab, TryCatch #8 {Exception -> 0x05ab, all -> 0x05a5, blocks: (B:118:0x0165, B:120:0x0180, B:122:0x0184, B:125:0x0189, B:127:0x018d, B:131:0x0196, B:132:0x019a, B:134:0x01a0, B:142:0x01bd, B:144:0x01c4, B:146:0x01c8, B:159:0x0214, B:161:0x021a, B:171:0x0237, B:172:0x023b, B:174:0x0241, B:185:0x0269, B:187:0x026d, B:189:0x0274, B:191:0x0278, B:192:0x027d, B:194:0x0281, B:195:0x0286, B:196:0x028d, B:198:0x0293, B:203:0x02ad, B:205:0x02b1, B:207:0x02b8, B:209:0x02bc, B:210:0x02c1, B:212:0x02c5, B:213:0x02ca, B:214:0x02d1, B:216:0x02d7, B:224:0x02f9, B:226:0x0307, B:228:0x0314, B:230:0x0318, B:233:0x031d, B:235:0x0320, B:294:0x03d1, B:296:0x03d5, B:298:0x03d9, B:300:0x03dd, B:302:0x03e1, B:304:0x03e5, B:311:0x03f7, B:313:0x03fb, B:315:0x03ff, B:306:0x03e9, B:308:0x03ed, B:319:0x040f, B:321:0x0418, B:323:0x041c, B:324:0x0420, B:325:0x0424, B:327:0x0437, B:331:0x0441, B:332:0x0445, B:336:0x044f, B:337:0x0452, B:340:0x045a, B:342:0x0465, B:344:0x0469, B:346:0x046e, B:350:0x048a, B:351:0x0494, B:354:0x049b, B:358:0x04a5, B:363:0x04b1, B:365:0x04b7, B:367:0x04bb, B:368:0x04bd, B:370:0x04c5, B:372:0x04c9, B:373:0x04cd, B:376:0x04dc, B:377:0x04e6, B:378:0x04e9, B:380:0x04ed, B:381:0x04f6, B:384:0x04fc, B:385:0x0507, B:388:0x0516, B:390:0x051d, B:392:0x0526, B:395:0x052a, B:396:0x052f, B:397:0x0536, B:399:0x053a, B:400:0x053f, B:401:0x0546, B:404:0x054e, B:406:0x0557, B:411:0x056b, B:412:0x0570, B:413:0x0575, B:414:0x0580, B:415:0x0585, B:416:0x058a, B:239:0x0327, B:243:0x0335, B:245:0x0342, B:247:0x0346, B:250:0x034b, B:252:0x034e, B:255:0x0356, B:257:0x0360, B:259:0x036b, B:261:0x036f, B:264:0x0376, B:266:0x0379, B:268:0x037e, B:269:0x0385, B:271:0x038d, B:273:0x0398, B:275:0x039c, B:278:0x03a3, B:280:0x03a6, B:282:0x03ab, B:284:0x03b2, B:286:0x03b6, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:168:0x022e, B:147:0x01d8, B:149:0x01dc, B:150:0x01e8, B:152:0x01ec, B:153:0x01fc, B:154:0x0203, B:429:0x05c0, B:430:0x05c4, B:432:0x05ca, B:437:0x05dc, B:439:0x05e5, B:442:0x05f4, B:444:0x05f8, B:445:0x05fc), top: B:474:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:426:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x05dc A[Catch: all -> 0x05a5, Exception -> 0x05ab, TRY_ENTER, TryCatch #8 {Exception -> 0x05ab, all -> 0x05a5, blocks: (B:118:0x0165, B:120:0x0180, B:122:0x0184, B:125:0x0189, B:127:0x018d, B:131:0x0196, B:132:0x019a, B:134:0x01a0, B:142:0x01bd, B:144:0x01c4, B:146:0x01c8, B:159:0x0214, B:161:0x021a, B:171:0x0237, B:172:0x023b, B:174:0x0241, B:185:0x0269, B:187:0x026d, B:189:0x0274, B:191:0x0278, B:192:0x027d, B:194:0x0281, B:195:0x0286, B:196:0x028d, B:198:0x0293, B:203:0x02ad, B:205:0x02b1, B:207:0x02b8, B:209:0x02bc, B:210:0x02c1, B:212:0x02c5, B:213:0x02ca, B:214:0x02d1, B:216:0x02d7, B:224:0x02f9, B:226:0x0307, B:228:0x0314, B:230:0x0318, B:233:0x031d, B:235:0x0320, B:294:0x03d1, B:296:0x03d5, B:298:0x03d9, B:300:0x03dd, B:302:0x03e1, B:304:0x03e5, B:311:0x03f7, B:313:0x03fb, B:315:0x03ff, B:306:0x03e9, B:308:0x03ed, B:319:0x040f, B:321:0x0418, B:323:0x041c, B:324:0x0420, B:325:0x0424, B:327:0x0437, B:331:0x0441, B:332:0x0445, B:336:0x044f, B:337:0x0452, B:340:0x045a, B:342:0x0465, B:344:0x0469, B:346:0x046e, B:350:0x048a, B:351:0x0494, B:354:0x049b, B:358:0x04a5, B:363:0x04b1, B:365:0x04b7, B:367:0x04bb, B:368:0x04bd, B:370:0x04c5, B:372:0x04c9, B:373:0x04cd, B:376:0x04dc, B:377:0x04e6, B:378:0x04e9, B:380:0x04ed, B:381:0x04f6, B:384:0x04fc, B:385:0x0507, B:388:0x0516, B:390:0x051d, B:392:0x0526, B:395:0x052a, B:396:0x052f, B:397:0x0536, B:399:0x053a, B:400:0x053f, B:401:0x0546, B:404:0x054e, B:406:0x0557, B:411:0x056b, B:412:0x0570, B:413:0x0575, B:414:0x0580, B:415:0x0585, B:416:0x058a, B:239:0x0327, B:243:0x0335, B:245:0x0342, B:247:0x0346, B:250:0x034b, B:252:0x034e, B:255:0x0356, B:257:0x0360, B:259:0x036b, B:261:0x036f, B:264:0x0376, B:266:0x0379, B:268:0x037e, B:269:0x0385, B:271:0x038d, B:273:0x0398, B:275:0x039c, B:278:0x03a3, B:280:0x03a6, B:282:0x03ab, B:284:0x03b2, B:286:0x03b6, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:168:0x022e, B:147:0x01d8, B:149:0x01dc, B:150:0x01e8, B:152:0x01ec, B:153:0x01fc, B:154:0x0203, B:429:0x05c0, B:430:0x05c4, B:432:0x05ca, B:437:0x05dc, B:439:0x05e5, B:442:0x05f4, B:444:0x05f8, B:445:0x05fc), top: B:474:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:442:0x05f4 A[Catch: all -> 0x05a5, Exception -> 0x05ab, TRY_ENTER, TryCatch #8 {Exception -> 0x05ab, all -> 0x05a5, blocks: (B:118:0x0165, B:120:0x0180, B:122:0x0184, B:125:0x0189, B:127:0x018d, B:131:0x0196, B:132:0x019a, B:134:0x01a0, B:142:0x01bd, B:144:0x01c4, B:146:0x01c8, B:159:0x0214, B:161:0x021a, B:171:0x0237, B:172:0x023b, B:174:0x0241, B:185:0x0269, B:187:0x026d, B:189:0x0274, B:191:0x0278, B:192:0x027d, B:194:0x0281, B:195:0x0286, B:196:0x028d, B:198:0x0293, B:203:0x02ad, B:205:0x02b1, B:207:0x02b8, B:209:0x02bc, B:210:0x02c1, B:212:0x02c5, B:213:0x02ca, B:214:0x02d1, B:216:0x02d7, B:224:0x02f9, B:226:0x0307, B:228:0x0314, B:230:0x0318, B:233:0x031d, B:235:0x0320, B:294:0x03d1, B:296:0x03d5, B:298:0x03d9, B:300:0x03dd, B:302:0x03e1, B:304:0x03e5, B:311:0x03f7, B:313:0x03fb, B:315:0x03ff, B:306:0x03e9, B:308:0x03ed, B:319:0x040f, B:321:0x0418, B:323:0x041c, B:324:0x0420, B:325:0x0424, B:327:0x0437, B:331:0x0441, B:332:0x0445, B:336:0x044f, B:337:0x0452, B:340:0x045a, B:342:0x0465, B:344:0x0469, B:346:0x046e, B:350:0x048a, B:351:0x0494, B:354:0x049b, B:358:0x04a5, B:363:0x04b1, B:365:0x04b7, B:367:0x04bb, B:368:0x04bd, B:370:0x04c5, B:372:0x04c9, B:373:0x04cd, B:376:0x04dc, B:377:0x04e6, B:378:0x04e9, B:380:0x04ed, B:381:0x04f6, B:384:0x04fc, B:385:0x0507, B:388:0x0516, B:390:0x051d, B:392:0x0526, B:395:0x052a, B:396:0x052f, B:397:0x0536, B:399:0x053a, B:400:0x053f, B:401:0x0546, B:404:0x054e, B:406:0x0557, B:411:0x056b, B:412:0x0570, B:413:0x0575, B:414:0x0580, B:415:0x0585, B:416:0x058a, B:239:0x0327, B:243:0x0335, B:245:0x0342, B:247:0x0346, B:250:0x034b, B:252:0x034e, B:255:0x0356, B:257:0x0360, B:259:0x036b, B:261:0x036f, B:264:0x0376, B:266:0x0379, B:268:0x037e, B:269:0x0385, B:271:0x038d, B:273:0x0398, B:275:0x039c, B:278:0x03a3, B:280:0x03a6, B:282:0x03ab, B:284:0x03b2, B:286:0x03b6, B:163:0x0221, B:165:0x0225, B:166:0x022a, B:168:0x022e, B:147:0x01d8, B:149:0x01dc, B:150:0x01e8, B:152:0x01ec, B:153:0x01fc, B:154:0x0203, B:429:0x05c0, B:430:0x05c4, B:432:0x05ca, B:437:0x05dc, B:439:0x05e5, B:442:0x05f4, B:444:0x05f8, B:445:0x05fc), top: B:474:0x0165 }] */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0624 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0110 A[Catch: all -> 0x0097, Exception -> 0x009c, TRY_ENTER, TryCatch #9 {Exception -> 0x009c, all -> 0x0097, blocks: (B:41:0x008a, B:43:0x008e, B:44:0x0092, B:52:0x00ac, B:54:0x00b5, B:58:0x00c4, B:61:0x00cb, B:63:0x00d2, B:65:0x00d6, B:71:0x00e0, B:73:0x00e6, B:77:0x00ef, B:79:0x00f7, B:80:0x00ff, B:89:0x0110, B:90:0x0114, B:92:0x011a, B:100:0x0133, B:76:0x00eb), top: B:472:0x008a }] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0129  */
    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void write(com.alibaba.fastjson.serializer.JSONSerializer r38, java.lang.Object r39, java.lang.Object r40, java.lang.reflect.Type r41) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type):void");
    }

    public JavaBeanSerializer(Class<?> cls, PropertyNamingStrategy propertyNamingStrategy) {
        this(cls, cls.getModifiers(), null, false, true, true, true, propertyNamingStrategy);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, cls.getModifiers(), a(strArr), false, true, true, true, null);
    }

    public JavaBeanSerializer(Class<?> cls, int i, Map<String, String> map, boolean z, boolean z2, boolean z3, boolean z4, PropertyNamingStrategy propertyNamingStrategy) {
        PropertyNamingStrategy propertyNamingStrategy2;
        String str;
        String str2;
        PropertyNamingStrategy naming;
        this.features = 0;
        JSONType jSONType = z2 ? (JSONType) cls.getAnnotation(JSONType.class) : null;
        if (jSONType != null) {
            this.features = SerializerFeature.of(jSONType.serialzeFeatures());
            str = jSONType.typeName();
            if (str.length() == 0) {
                str = null;
                str2 = null;
            } else {
                str2 = null;
                for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    JSONType jSONType2 = (JSONType) superclass.getAnnotation(JSONType.class);
                    if (jSONType2 == null) {
                        break;
                    }
                    str2 = jSONType2.typeKey();
                    if (str2.length() != 0) {
                        break;
                    }
                }
                for (Class<?> cls2 : cls.getInterfaces()) {
                    JSONType jSONType3 = (JSONType) cls2.getAnnotation(JSONType.class);
                    if (jSONType3 != null) {
                        str2 = jSONType3.typeKey();
                        if (str2.length() != 0) {
                            break;
                        }
                    }
                }
                if (str2 != null && str2.length() == 0) {
                    str2 = null;
                }
            }
            propertyNamingStrategy2 = (propertyNamingStrategy != null || (naming = jSONType.naming()) == PropertyNamingStrategy.CamelCase) ? propertyNamingStrategy : naming;
        } else {
            propertyNamingStrategy2 = propertyNamingStrategy;
            str = null;
            str2 = null;
        }
        this.typeName = str;
        this.typeKey = str2;
        List<FieldInfo> computeGetters = TypeUtils.computeGetters(cls, i, z, jSONType, map, false, z3, z4, propertyNamingStrategy2);
        ArrayList arrayList = new ArrayList();
        for (FieldInfo fieldInfo : computeGetters) {
            arrayList.add(new FieldSerializer(fieldInfo));
        }
        FieldSerializer[] fieldSerializerArr = (FieldSerializer[]) arrayList.toArray(new FieldSerializer[arrayList.size()]);
        this.f2132a = fieldSerializerArr;
        String[] orders = jSONType != null ? jSONType.orders() : null;
        if (orders != null && orders.length != 0) {
            List<FieldInfo> computeGetters2 = TypeUtils.computeGetters(cls, i, z, jSONType, map, true, z3, z4, propertyNamingStrategy2);
            ArrayList arrayList2 = new ArrayList();
            for (FieldInfo fieldInfo2 : computeGetters2) {
                arrayList2.add(new FieldSerializer(fieldInfo2));
            }
            this.b = (FieldSerializer[]) arrayList2.toArray(new FieldSerializer[arrayList2.size()]);
            return;
        }
        FieldSerializer[] fieldSerializerArr2 = new FieldSerializer[fieldSerializerArr.length];
        System.arraycopy(fieldSerializerArr, 0, fieldSerializerArr2, 0, fieldSerializerArr.length);
        Arrays.sort(fieldSerializerArr2);
        if (Arrays.equals(fieldSerializerArr2, fieldSerializerArr)) {
            this.b = fieldSerializerArr;
        } else {
            this.b = fieldSerializerArr2;
        }
    }
}
