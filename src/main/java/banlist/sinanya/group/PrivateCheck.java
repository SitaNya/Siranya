package banlist.sinanya.group;

import static banlist.sinanya.tools.banlist.GetBanList.checkBanQq;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_IGNORE;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_INTERCEPT;

/**
 * @author SitaNya
 * @date 2019-06-21
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class PrivateCheck {
    long fromQq;
    boolean isQqBan;

    public PrivateCheck(long fromQq) {
        this.fromQq=fromQq;
        isQqBan = checkBanQq(fromQq);
    }

    public int isBanQq(){
        if (isQqBan) {
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }
}
