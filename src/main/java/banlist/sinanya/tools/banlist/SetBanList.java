package banlist.sinanya.tools.banlist;

import banlist.sinanya.db.group.InsertBanGroupList;
import banlist.sinanya.db.qq.InsertBanQqList;

import java.util.ArrayList;

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
public class SetBanList {
    private static InsertBanGroupList insertBanGroupList = new InsertBanGroupList();
    private static InsertBanQqList insertBanQqList = new InsertBanQqList();

    public static void setBanGroup(Long groupId,String reason) {
        insertBanGroupList.insertBanGroup(groupId,reason);
        ArrayList<Long> banGroup = new ArrayList<>();
        if (BAN_GROUP_LIST.containsKey(CQ.getLoginQQ())) {
            banGroup = BAN_GROUP_LIST.get(CQ.getLoginQQ());
            banGroup.add(groupId);
        }
        BAN_GROUP_LIST.put(CQ.getLoginQQ(), banGroup);
    }

    public static void setBanQq(Long qqId,String reason) {
        insertBanQqList.insertBanQq(qqId,reason);
        ArrayList<Long> banQq = new ArrayList<>();
        if (BAN_QQ_LIST.containsKey(CQ.getLoginQQ())) {
            banQq = BAN_QQ_LIST.get(CQ.getLoginQQ());
            banQq.add(qqId);
        }
        BAN_QQ_LIST.put(CQ.getLoginQQ(), banQq);
    }
}
