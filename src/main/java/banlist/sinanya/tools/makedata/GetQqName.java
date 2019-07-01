package banlist.sinanya.tools.makedata;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * @date 2019-07-01
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class GetQqName {

    public static String getQqName(String qqId) {
        return CQ.getStrangerInfo(Long.parseLong(qqId)).getNick();
    }

    public static String getQqName(long qqId) {
        try {
            return CQ.getStrangerInfo(qqId).getNick();
        } catch (Exception e) {
            CQ.logError(e.getMessage(), String.valueOf(qqId));
            return "发生错误，未找到";
        }
    }
}
