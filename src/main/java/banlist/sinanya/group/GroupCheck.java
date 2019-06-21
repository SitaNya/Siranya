package banlist.sinanya.group;

import static banlist.sinanya.tools.banlist.GetBanList.checkBanGroup;
import static banlist.sinanya.tools.banlist.GetBanList.checkBanQq;
import static banlist.sinanya.tools.banlist.SetBanList.setBanGroup;
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
public class GroupCheck {
    private boolean inGroupBan;
    private boolean isQqBan;
    private String msg;
    private long groupId;

    public GroupCheck(long groupId, long fromQq, String msg) {
        inGroupBan = checkBanGroup(groupId);
        isQqBan = checkBanQq(fromQq);
        this.msg = msg;
        this.groupId = groupId;
    }

    public int inBanGroup() {
        if (inGroupBan) {
            //TODO
            // 离开群
            // 语句
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }

    public int isBanQq() {
        if (isQqBan) {
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }

    public int isRemove() {
        boolean containMe = msg.contains(CQ.getLoginNick()) || msg.contains(String.valueOf(CQ.getLoginQQ()));
        if (msg.contains("禁言") && containMe) {
            setBanGroup(groupId);
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }
}
