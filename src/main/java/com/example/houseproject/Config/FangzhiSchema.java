package com.example.houseproject.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FangzhiSchema {
    private static final Logger log = LoggerFactory.getLogger(FangzhiSchema.class);
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
    }

    private boolean columnExists(String column) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = 'fangzhi' AND COLUMN_NAME = ?",
                Integer.class,
                column);
        return count != null && count > 0;
    }
}
