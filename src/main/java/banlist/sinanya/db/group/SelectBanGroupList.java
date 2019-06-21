package banlist.sinanya.db.group;

import banlist.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static banlist.sinanya.system.SystemBanList.BAN_GROUP_LIST;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectBanGroupList {
    private static final Logger Log = LogManager.getLogger(SelectBanGroupList.class);

    public SelectBanGroupList() {
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushGroupListFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from banGroupList where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                try (ResultSet set = ps.executeQuery()) {
                    ArrayList<Long> banGroupListTmp = new ArrayList<>();
                    while (set.next()) {
                        banGroupListTmp.add(Long.parseLong(set.getString("groupList")));
                    }
                    BAN_GROUP_LIST.put(CQ.getLoginQQ(), banGroupListTmp);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
