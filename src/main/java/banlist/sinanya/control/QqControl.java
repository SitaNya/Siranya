package banlist.sinanya.control;

import banlist.sinanya.control.imal.MakeList;

import java.util.ArrayList;

import static banlist.sinanya.system.SystemInfo.MASTER_GROUP;
import static banlist.sinanya.system.SystemInfo.MASTER_QQ;
import static banlist.sinanya.system.SystemTag.BAN_QQ_RM;
import static banlist.sinanya.system.SystemTag.BAN_QQ_SET;
import static banlist.sinanya.tools.banlist.GetBanList.getBanQqList;
import static banlist.sinanya.tools.banlist.SetBanList.rmBanQq;
import static banlist.sinanya.tools.banlist.SetBanList.setBanQq;
import static banlist.sinanya.tools.makedata.GetGroupName.getGroupName;
import static banlist.sinanya.tools.makedata.GetQqName.getQqName;
import static banlist.sinanya.tools.makedata.RemoveTag.removeTag;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_INTERCEPT;
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
public class QqControl implements MakeList {
    private long fromQq;
    private long fromGroup;

    public QqControl(long fromGroup, long fromQq) {
        this.fromQq = fromQq;
        this.fromGroup = fromGroup;
    }

    public int set(String msg) {
        if ( fromQq != MASTER_QQ) {
            if (fromGroup == 0) {
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在私聊使用命令试图添加黑名单用户列表，已驳回");
                CQ.sendPrivateMsg(fromQq, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表添加");
            } else {
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在群: " + getGroupName(fromGroup) + "(" + fromGroup + ")使用命令试图添加黑名单用户列表，已驳回");
                CQ.sendGroupMsg(fromGroup, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表添加");
            }
            return MSG_INTERCEPT;
        }
        msg = removeTag(msg, BAN_QQ_SET);
        ArrayList<String> idList = makeList(msg);
        ArrayList<String> resultList = new ArrayList<>();
        for (String id : idList) {
            resultList.add("\n" + getQqName(id) + "(" + id + ")");
            setBanQq(Long.parseLong(id), "由: " + fromQq + "使用命令加入");
        }

        CQ.sendGroupMsg(MASTER_GROUP, "由: " + fromQq + "使用命令加入以下群黑名单用户列表:" + resultList.toString());
        return MSG_INTERCEPT;
    }

    public int get() {
        if ( fromQq != MASTER_QQ) {
            if (fromGroup == 0) {
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在私聊使用命令试图获取黑名单用户列表，已驳回");
                CQ.sendPrivateMsg(fromQq, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表获取");
            } else {
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在群: " + getGroupName(fromGroup) + "(" + fromGroup + ")使用命令试图获取黑名单用户列表，已驳回");
                CQ.sendGroupMsg(fromGroup, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表获取");
            }
            return MSG_INTERCEPT;
        }
        CQ.sendGroupMsg(MASTER_GROUP, "以下是云黑内黑名单用户列表:" + getBanQqList());
        return MSG_INTERCEPT;
    }

    public int rm(String msg) {
        if (fromQq != MASTER_QQ) {
            if (fromGroup==0){
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在私聊使用命令试图移除黑名单用户列表，已驳回");
                CQ.sendPrivateMsg(fromQq, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表移除");
            }else {
                CQ.sendGroupMsg(MASTER_GROUP, "由: " + getQqName(fromQq) + "(" + fromQq + ")在群: " + getGroupName(fromGroup) + "(" + fromGroup + ")使用命令试图移除黑名单用户列表，已驳回");
                CQ.sendGroupMsg(fromGroup, "只可以由主人: " + getQqName(MASTER_QQ) + "(" + MASTER_QQ + ")在主控群: " + getGroupName(MASTER_GROUP) + "(" + MASTER_GROUP + ")或私聊时进行黑名单用户列表移除");
            }
            return MSG_INTERCEPT;
        }
        msg = removeTag(msg, BAN_QQ_RM);

        ArrayList<String> idList = makeList(msg);
        ArrayList<String> resultList = new ArrayList<>();
        for (String id : idList) {
            resultList.add("\n" + getQqName(id) + "(" + id + ")");
            rmBanQq(Long.parseLong(id));
        }
        CQ.sendGroupMsg(MASTER_GROUP, "由: " + fromQq + "使用命令移除以下群黑名单用户列表:" + resultList.toString());
        return MSG_INTERCEPT;
    }
}
