package banlist.sinanya.tools.banlist;

import banlist.sinanya.db.group.SelectBanGroupList;
import banlist.sinanya.db.qq.SelectBanQqList;
import banlist.sinanya.entity.EntityBanDetail;

import static banlist.sinanya.system.SystemBanList.BAN_GROUP_LIST;
import static banlist.sinanya.system.SystemBanList.BAN_QQ_LIST;
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
public class GetBanList {

    private static SelectBanGroupList selectBanGroupList = new SelectBanGroupList();
    private static SelectBanQqList selectBanQqList = new SelectBanQqList();

    public static boolean checkBanGroup(Long groupId) {
        if (BAN_GROUP_LIST.containsKey(CQ.getLoginQQ())) {
            return BAN_GROUP_LIST.get(CQ.getLoginQQ()).contains(groupId);
        } else {
            return false;
        }
    }

    public static boolean checkBanQq(Long qqId) {
        if (BAN_QQ_LIST.containsKey(CQ.getLoginQQ())) {
            return BAN_QQ_LIST.get(CQ.getLoginQQ()).contains(qqId);
        } else {
            return false;
        }
    }

    public static void flushBanGroup(){
        selectBanGroupList.flushGroupListFromDatabase();
    }

    public static void flushQqGroup(){
        selectBanQqList.flushQqListFromDatabase();
    }

    public static EntityBanDetail getBanQqInfo(long qqId) {
        return selectBanQqList.selectBanQqInfoFromDatabase(qqId);
    }

    public static EntityBanDetail getBanGroupInfo(long groupId) {
        return selectBanGroupList.selectBanGroupInfoFromDatabase(groupId);
    }
}
