package ${basePackage}.service.impl;

import ${basePackage}.service.ObjectService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ${author} on ${date}.
 */

@Service
@Transactional
public class ObjectServiceImpl implements ObjectService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
    * 查询
    * 简单查询：单表查询视为简单查询，可以使用 select *
    * 复杂查询：多表查询视为复杂查询，不可以select * ；select后的字段必须加别名。例如：select t.name as teacherName,s.name as schoolName from teacher t left join school s on t.school_id = s.id
    * @param sql 查询sql
    * @return
    */
    @Override
    public List<Object> executeQuerySql(String sql) {
        if(StringUtils.isEmpty(sql)){
             return null;
        }
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Object> objects = query.getResultList();
        return objects;
    }
     /**
     * 更新，删除，插入
     * @param sql 操作sql
     * @return
     */
     @Override
     public Object executeOperateSql(String sql) {
        if(StringUtils.isEmpty(sql)){
           return null;
        }
        Query query = entityManager.createNativeQuery(sql);
        Object objects = query.executeUpdate();
        return objects;
     }
}