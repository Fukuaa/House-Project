package com.example.houseproject.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Component
public class FangzhiSchema {
    private static final Logger log = LoggerFactory.getLogger(FangzhiSchema.class);
    private static final String[][] RENT_DETAILS = {
            {"全套家具家电", "租客自理，月均约 280 元", "12 个月"},
            {"部分家具", "含水电", "6 个月"},
            {"无家具，可协商配置", "租客自理，月均约 180 元", "3 个月"},
            {"品牌家具家电", "含水，电费自理", "12 个月"}
    };
    private static final String[][] SALE_DETAILS = {
            {"精装", "70 年住宅", "3.6 元/平米/月"},
            {"简装", "70 年住宅", "2.8 元/平米/月"},
            {"毛坯", "40 年商业", "5.0 元/平米/月"},
            {"精装，南北通透", "70 年住宅", "4.2 元/平米/月"}
    };
    private final JdbcTemplate jdbcTemplate;

    public FangzhiSchema(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void ensureRecycleColumns() {
        if (!columnExists("deleted")) {
            jdbcTemplate.execute("ALTER TABLE fangzhi ADD COLUMN deleted TINYINT NOT NULL DEFAULT 0");
            log.info("Added fangzhi.deleted");
        }
        if (!columnExists("deleted_at")) {
            jdbcTemplate.execute("ALTER TABLE fangzhi ADD COLUMN deleted_at DATETIME NULL");
            log.info("Added fangzhi.deleted_at");
        }
        addTextColumn("jiaju");
        addTextColumn("shuidian");
        addTextColumn("zuqi");
        addTextColumn("zhuangxiu");
        addTextColumn("chanquan");
        addTextColumn("wuye");
        fillBlankDetails();
        int moved = jdbcTemplate.update(
                "UPDATE fangzhi SET deleted = 1, deleted_at = IFNULL(deleted_at, NOW()) WHERE zhuangtai = '已售出' AND deleted = 0");
        if (moved > 0) {
            log.info("Moved sold houses into recycle: {}", moved);
        }
    }

    private void addTextColumn(String column) {
        if (!columnExists(column)) {
            jdbcTemplate.execute("ALTER TABLE fangzhi ADD COLUMN " + column + " VARCHAR(255) NULL");
            log.info("Added fangzhi.{}", column);
        }
    }

    private void fillBlankDetails() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(
                "SELECT hid, zhuangtai, jiaju, zhuangxiu FROM fangzhi");
        for (Map<String, Object> row : rows) {
            int hid = ((Number) row.get("hid")).intValue();
            String status = (String) row.get("zhuangtai");
            if ("出租中".equals(status) && isBlank(row.get("jiaju"))) {
                String[] preset = RENT_DETAILS[Math.floorMod(hid, RENT_DETAILS.length)];
                jdbcTemplate.update(
                        "UPDATE fangzhi SET jiaju = ?, shuidian = ?, zuqi = ? WHERE hid = ? AND (jiaju IS NULL OR jiaju = '')",
                        preset[0], preset[1], preset[2], hid);
            } else if (("售卖中".equals(status) || "已售出".equals(status)) && isBlank(row.get("zhuangxiu"))) {
                String[] preset = SALE_DETAILS[Math.floorMod(hid, SALE_DETAILS.length)];
                jdbcTemplate.update(
                        "UPDATE fangzhi SET zhuangxiu = ?, chanquan = ?, wuye = ? WHERE hid = ? AND (zhuangxiu IS NULL OR zhuangxiu = '')",
                        preset[0], preset[1], preset[2], hid);
            }
        }
    }

    private boolean isBlank(Object value) {
        return value == null || value.toString().trim().isEmpty();
    }

    private boolean columnExists(String column) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'fangzhi' AND COLUMN_NAME = ?",
                Integer.class,
                column);
        return count != null && count > 0;
    }
}
