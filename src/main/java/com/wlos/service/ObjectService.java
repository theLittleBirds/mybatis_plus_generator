package com.wlos.service;

import java.util.List;

/**
 * Created by wlos on 2021/06/29.
 */
public interface ObjectService{

     List<Object> executeQuerySql(String sql);

     Object executeOperateSql(String sql);
}
