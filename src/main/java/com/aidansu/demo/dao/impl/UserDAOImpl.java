package com.aidansu.demo.dao.impl;

import com.aidansu.demo.dao.UserDAO;
import com.aidansu.demo.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aidan on 17-4-26.
 */
@Repository("UserDAO")
public class UserDAOImpl extends BaseDAOHibernate implements UserDAO{

    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }

    @Override
    public void insert(User user) {
        doCreateObject(user);
    }

    @Override
    public void update(User user) {
        doUpdateObject(user);
    }

    @Override
    public void delete(long id) {
        doDeleteObject( findById( id ) ) ;
    }

    @Override
    public User findById(long id) {
        return (User)doFindObjectById(id);
    }

    @Override
    public User findByUsername(String username) {
        Session session = null;
        Transaction tx = null;
        User user = null;
        try{
            session = super.getSession();
            tx = session.beginTransaction();
            String hql = "from User where username=? order by id desc";
            user = (User)session.createQuery(hql).setParameter(0,username).setMaxResults(1).uniqueResult();
            tx.commit();
        }catch(HibernateException he){
            if(tx!=null){
                tx.rollback();
            }
            he.printStackTrace();
        }finally{
            if(session!=null)
                session.close();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return (List<User>)doFindObjects();
    }

}
