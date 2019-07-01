package banlist.sinanya.group;

import banlist.sinanya.entity.EntityBanDetail;
import com.sobte.cqp.jcq.entity.Group;

import static banlist.sinanya.system.SystemInfo.MASTER_GROUP;
import static banlist.sinanya.tools.banlist.GetBanList.*;
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
    private long fromQq;

    public GroupCheck(long groupId, long fromQq, String msg) {
        inGroupBan = checkBanGroup(groupId);
        isQqBan = checkBanQq(fromQq);
        this.msg = msg;
        this.groupId = groupId;
        this.fromQq = fromQq;
    }

    public GroupCheck(long groupId, long fromQq) {
        inGroupBan = checkBanGroup(groupId);
        isQqBan = checkBanQq(fromQq);
        this.groupId = groupId;
        this.fromQq = fromQq;
    }

    public int inBanGroup() {
        if (inGroupBan) {
            String groupName = "";
            for (Group group : CQ.getGroupList()) {
                if (group.getId() == groupId) {
                    groupName = group.getName();
                }
            }
            EntityBanDetail entityBanDetail = getBanGroupInfo(groupId);
            CQ.sendGroupMsg(groupId, "检测到在黑名单群: " + groupName + "(" + groupId + ")中，已自动退出\n" +
                    "在" + entityBanDetail.getCreateTime() + " 由: " + entityBanDetail.getBotId() + "记录，原因为: " + entityBanDetail.getReason());
            CQ.sendGroupMsg(MASTER_GROUP, "检测到在黑名单群: " + groupName + "(" + groupId + ")中，已自动退出\n" +
                    "在" + entityBanDetail.getCreateTime() + " 由: " + entityBanDetail.getBotId() + "记录，原因为: " + entityBanDetail.getReason());
            CQ.setGroupLeave(groupId, false);
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }

    public int isBanQq() {
        if (isQqBan) {
            EntityBanDetail entityBanDetail = getBanQqInfo(fromQq);
            CQ.sendGroupMsg(groupId, "检测到黑名单成员: " + fromQq + "(" + CQ.getAnonymous(String.valueOf(fromQq)).getName() + ")的命令，不予以回复\n" +
                    "在" + entityBanDetail.getCreateTime() + " 由: " + entityBanDetail.getBotId() + "记录，原因为: " + entityBanDetail.getReason());
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }

    public int isForbidden() {
        boolean containMe = msg.contains(CQ.getLoginNick()) || msg.contains(String.valueOf(CQ.getLoginQQ()));
        if (msg.contains("禁言") && containMe) {
            setBanGroup(groupId, "被禁言");
            return MSG_INTERCEPT;
        }
        return MSG_IGNORE;
    }
}
