package com.albert.toolkit.db.dynamicds;

import com.albert.toolkit.db.CommonConstants;

/**
 * 动态数据源操作
 */
public class DynamicDatasourceContextHolder {
    /**
     * 数据源名称
     */
    private static final ThreadLocal<String> DATASOURCE_KEY_HOLDER = new ThreadLocal<>();


    /**
     * 设置数据源
     * @param dsKey 数据源名称
     */
    public static void setDatasourceKey(String dsKey) {
        DATASOURCE_KEY_HOLDER.set(dsKey);
    }

    /**
     * 获取当前数据源名称
     * @return 数据源名称
     */
    public static String getDatasourceKey() {
        return DATASOURCE_KEY_HOLDER.get();
    }

    /**
     * 删除当前数据源
     */
    public static void removeContextKey() {
        DATASOURCE_KEY_HOLDER.remove();
    }
}
