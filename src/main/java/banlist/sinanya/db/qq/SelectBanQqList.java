package banlist.sinanya.db.qq;

import banlist.sinanya.db.tools.DbUtil;
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
}
