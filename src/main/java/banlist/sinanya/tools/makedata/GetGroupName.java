package banlist.sinanya.tools.makedata;

import com.sobte.cqp.jcq.entity.Group;

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
public class GetGroupName {
    public static String getGroupName(String groupId) {
        for (Group group : CQ.getGroupList()) {
            if (group.getId() == Long.parseLong(groupId)) {
                return group.getName();
            }
        }
        return "未找到";
    }

    public static String getGroupName(long groupId) {
        for (Group group : CQ.getGroupList()) {
            if (group.getId() == groupId) {
                return group.getName();
            }
        }
        return "未找到";
    }
}
