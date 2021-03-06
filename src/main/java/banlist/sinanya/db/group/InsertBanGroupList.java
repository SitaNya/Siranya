package banlist.sinanya.db.group;

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
public class InsertBanGroupList {

    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param groupId 群号
     */
    public void insertBanGroup(Long groupId, String reason) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "INSERT INTO banGroupList(createTime,botId,groupId,reason) VALUES(?,?,?,?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
                ps.setLong(2, CQ.getLoginQQ());
                ps.setLong(3, groupId);
                ps.setString(4, reason);

                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), e.toString());
        }
    }

    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param groupId 群号
     */
    public void removeBanGroup(Long groupId) {
        try (Connection conn = DbUtil.getConnection()) {
            String sql = "delete from banGroupList where botId=? and groupId=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setLong(1, CQ.getLoginQQ());
                ps.setLong(2, groupId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            CQ.logError(e.getMessage(), e.toString());
        }
    }
}
