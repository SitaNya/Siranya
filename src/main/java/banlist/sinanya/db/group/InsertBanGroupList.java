package banlist.sinanya.db.group;

import banlist.sinanya.db.tools.DbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private static final Logger Log = LogManager.getLogger(InsertBanGroupList.class);

    /**
     * 将kp主群设定插入或更新到数据库中
     *
     * @param groupId 群号
     */
    public void insertBanGroup(Long groupId) {
            try (Connection conn = DbUtil.getConnection()) {
                String sql = "INSERT INTO banGroupList(botId,groupId) VALUES(?,?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setLong(1, CQ.getLoginQQ());
                    ps.setLong(2, groupId);

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                Log.error(e.getMessage(), e);
            }
    }
}
