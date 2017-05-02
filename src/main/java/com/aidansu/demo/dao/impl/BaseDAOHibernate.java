package com.aidansu.demo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aidan on 17-5-2.
 */
public abstract class BaseDAOHibernate<T, ID extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * 获取模型类的抽象方法
     *
     * @return 返回具体实现的模型类
     */
    protected abstract Class<?> getModelClass() ;

    protected Session getSession(){
        return sessionFactory.openSession();
    }

    /**
     * 创建或更新对象
     *
     * @param obj 需要创建或更新的对象
     */
    protected void doCreateOrUpdateObject(Object obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx.commit();
        session.close();
    }

    /**
     * 创建对象
     *
     * @param obj 需要创建的对象
     * @return	返回创建对象ID
     */
    protected Serializable doCreateObject(Object obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Serializable serializable = session.save(obj);
        tx.commit();
        session.close();
        return serializable;
    }

    /**
     * 更新对象
     *
     * @param obj 需要更新的对象
     */
    protected void doUpdateObject(Object obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(obj);
        tx.commit();
        session.close();
    }

    /**
     * 删除对象
     *
     * @param obj 需要删除的对象
     */
    protected void doDeleteObject(Object obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.delete(obj);
        tx.commit();
        session.close();
    }

    /**
     * 根据id查找对象
     *
     * @param id 需要查找的对象ID
     * @return	需要查找的对象
     */
    protected Object doFindObjectById(Serializable id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Object object = session.get(this.getModelClass(), id);
        tx.commit();
        session.close();
        return object;
    }

    /**
     * 根据id查找指定对象
     *
     * @param clazz 指定需查找的对象类
     * @param id 需要查找的对象ID
     * @return	需要查找的对象
     */
    protected Object doFindObjectById(Class<?> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Object object = session.get(clazz, id);
        tx.commit();
        session.close();
        return object;
    }

    /**
     * 查找所有对象
     *
     * @return 需要查找的对象列表
     */
    protected List<T> doFindObjects() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from "+getModelClass().getName());
        List<T> list = query.list();
        tx.commit();
        session.close();
        return list;
    }

    /**
     * 统计某个对象集合的总数
     *
     * @return	记录总数
     */
    protected int doCountObject() {
        int result ;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select count(*) from " + getModelClass().getName();
        Query query = session.createQuery(hql);
        Long resultLonger = (Long) query.list().get(0);
        result = resultLonger.intValue();
        tx.commit();
        session.close();
        return result;
    }

    /**
     * 通过HQL，根据模糊查询条件，统计记录数
     *
     * @param properties 对象属性
     * @param values 需要查询的对象属性对应的值条件
     * @return	统计数
     */
    protected int doHQLCountObject(String[] properties, Object[] values) {
        int result = 0;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = "select count(*) from " + this.getModelClass().getName()+ " where 1=1 ";
        //组装hql条件字符串
        for (int i = 0; i < properties.length; i++) {
            if (i == 0)
                hql += " and " + properties[i] + " like :" + properties[i] ;
            else
                hql += " or " + properties[i] + " like :" + properties[i] ;
        }
        Query query = session.createQuery(hql);
        //设置查询条件值
        for (int i = 0; i < properties.length; i++) {
            query.setParameter(properties[i] , "%" +  values[i] + "%");
        }
        Long resultLonger = (Long) query.list().get(0);
        result = resultLonger.intValue();
        tx.commit();
        session.close();
        return result;
    }

}


