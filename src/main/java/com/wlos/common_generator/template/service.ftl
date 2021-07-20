package ${basePackage}.service;

import java.util.List;

/**
 * Created by ${author} on ${date}.
 */
public interface ObjectService{

     List<Object> executeQuerySql(String sql);

     Object executeOperateSql(String sql);
}
