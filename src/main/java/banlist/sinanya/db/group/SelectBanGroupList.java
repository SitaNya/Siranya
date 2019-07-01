package banlist.sinanya.db.group;

import banlist.sinanya.db.tools.DbUtil;
import banlist.sinanya.entity.EntityBanDetail;
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
            String sql = "select groupId from banGroupList where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                try (ResultSet set = ps.executeQuery()) {
                    ArrayList<Long> banGroupListTmp = new ArrayList<>();
                    while (set.next()) {
                        banGroupListTmp.add(Long.parseLong(set.getString("groupId")));
                    }
                    BAN_GROUP_LIST.put(CQ.getLoginQQ(), banGroupListTmp);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public EntityBanDetail selectBanGroupInfoFromDatabase(long groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select createTime,reason,botId from banGroupList where groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, groupId);
                try (ResultSet set = ps.executeQuery()) {
                    if (set.next()) {
                        return new EntityBanDetail(set.getTimestamp("createTime"), groupId, set.getLong("botId"), set.getString("reason"));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public ArrayList<EntityBanDetail> selectBanGroupListFromDatabase() {
        ArrayList<EntityBanDetail> banGroupList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select createTime,reason,qqId,botId,groupId from banGroupList";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        banGroupList.add(new EntityBanDetail(set.getTimestamp("createTime"), set.getLong("groupId"), set.getLong("botId"), set.getString("reason")));
                    }
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
        return banGroupList;
    }
}
