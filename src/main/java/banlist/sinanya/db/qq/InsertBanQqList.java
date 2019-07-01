package banlist.sinanya.db.qq;

import banlist.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import static com.sobte.cqp.jcq.event.JcqApp.CQ;

/**
 * @author SitaNya
 * 日期: 2019-06-15
 * 电子邮箱: sitanya@qq.com
 * 维护群(QQ): 162279609
 * 有任何问题欢迎咨询
 * 类说明: 录入KP主群类
 */
public class InsertBanQqList {

    private static final Logger Log = LogManager.getLogger(InsertBanQqList.class);

    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param qqId QQ号
     */
    public void insertBanQq(Long qqId, String reason) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO banQqList(createTime,botId,qqId,reason) VALUES(?,?,?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                    ps.setLong(2, CQ.getLoginQQ());
                    ps.setLong(3, qqId);
                    ps.setString(4, reason);

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
    }

    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param qqId QQ号
     */
    public void removeBanQq(Long qqId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from banQqList where botId=? and groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                ps.setLong(2, qqId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            Log.error(e.getMessage(), e);
        }
    }
}
