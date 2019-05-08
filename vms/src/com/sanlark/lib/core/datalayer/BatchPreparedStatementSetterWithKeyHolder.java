/**
 * Sanlark Inc. Pvt. Ltd.
 * @Author     : Rajiv Kumar
 * @Version    : 1.0.1
 * @CreateDate : 09-Jul-2015 
 */
package com.sanlark.lib.core.datalayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.support.KeyHolder;

public abstract class BatchPreparedStatementSetterWithKeyHolder<T> implements BatchPreparedStatementSetter {
    private final List<T> beans;

    public BatchPreparedStatementSetterWithKeyHolder(List<T> beans) {
        this.beans = beans;
    }

    @Override
    public void setValues(PreparedStatement ps, int index) throws SQLException {
        setValues(ps, beans.get(index));
    }

    @Override
    public final int getBatchSize() {
        return beans.size();
    }

    public void setPrimaryKey(KeyHolder keyHolder) {
        List<Map<String, Object>> keys = keyHolder.getKeyList();
        for (int index = 0, len = keys.size(); index < len; index++) {
            setPrimaryKey(keys.get(index), beans.get(index));
        }
    }

    protected abstract void setValues(PreparedStatement ps, T bean) throws SQLException;
    protected abstract void setPrimaryKey(Map<String, Object> primaryKey, T bean);
}
