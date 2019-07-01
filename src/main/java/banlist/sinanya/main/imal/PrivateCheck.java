package banlist.sinanya.main.imal;

import banlist.sinanya.entity.EntityBanDetail;

import static banlist.sinanya.tools.banlist.GetBanList.checkBanQq;
import static banlist.sinanya.tools.banlist.GetBanList.getBanQqInfo;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_IGNORE;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_INTERCEPT;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;

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
    private long fromQq;
    private boolean isQqBan;

    public PrivateCheck(long fromQq) {
        this.fromQq=fromQq;
        isQqBan = checkBanQq(fromQq);
    }

    public int isBanQq(){
        if (isQqBan) {
            EntityBanDetail entityBanDetail = getBanQqInfo(fromQq);
            CQ.sendPrivateMsg(fromQq, "检测到黑名单成员: " + fromQq + "(" + CQ.getAnonymous(String.valueOf(fromQq)).getName() + ")的命令，不予以回复\n" +
                    "在" + entityBanDetail.getCreateTime() + " 由: " + entityBanDetail.getBotId() + "记录，原因为: " + entityBanDetail.getReason());
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }
}
