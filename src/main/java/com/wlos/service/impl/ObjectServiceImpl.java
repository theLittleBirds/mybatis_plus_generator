package com.wlos.service.impl;

import com.wlos.service.ObjectService;
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
 * Created by wlos on 2021/06/29.
 */

@Service
@Transactional
public class ObjectServiceImpl implements ObjectService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
    * 复杂查询：select的字段必须加别名
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