package banlist.sinanya.db.qq;

import banlist.sinanya.db.tools.DbUtil;
import banlist.sinanya.entity.EntityBanDetail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static banlist.sinanya.system.SystemBanList.BAN_QQ_LIST;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 查询KP主群类，刷写到静态变量中，只在静态变量中找不到时才需要使用
 */
public class SelectBanQqList {
    private static final Logger Log = LogManager.getLogger(SelectBanQqList.class);

    public SelectBanQqList() {
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public void flushQqListFromDatabase() {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select * from banQqList where botId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                try (ResultSet set = ps.executeQuery()) {
                    ArrayList<Long> banQqListTmp = new ArrayList<>();
                    while (set.next()) {
                        banQqListTmp.add(Long.parseLong(set.getString("qqList")));
                    }
                    BAN_QQ_LIST.put(CQ.getLoginQQ(), banQqListTmp);
                }
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public EntityBanDetail selectBanQqInfoFromDatabase(long qqId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select createTime,reason,botId from banQqList where qqId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, qqId);
                try (ResultSet set = ps.executeQuery()) {
                    ArrayList<Long> banGroupListTmp = new ArrayList<>();
                    if (set.next()) {
                        return new EntityBanDetail(set.getTimestamp("createTime"), qqId, set.getLong("botId"), set.getString("reason"));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), e.toString());
        }
        return null;
    }

    /**
     * 刷新kp主群设定到静态变量中，只有静态变量中找不到某人的kp主群记录时才会使用
     */
    public ArrayList<EntityBanDetail> selectBanQqListFromDatabase() {
        ArrayList<EntityBanDetail> banQqList = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "select createTime,reason,qqId,botId from banQqList";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet set = ps.executeQuery()) {
                    while (set.next()) {
                        banQqList.add(new EntityBanDetail(set.getTimestamp("createTime"), set.getLong("qqId"), set.getLong("botId"), set.getString("reason")));
                    }
                }
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), e.toString());
        }
        return banQqList;
    }
}
