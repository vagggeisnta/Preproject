package DAO;

import Models.User;
import Util.DBHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private DBHelper dbHelper = DBHelper.getDBHelper();

    public UserHibernateDAO (){}

    @Override
    public boolean isUserExist(String name) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User WHERE name = ?", User.class);
        query.setParameter(0, name);
        User user = query.uniqueResult();
        transaction.commit();
        session.close();
        return user != null;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public void addUser(User user) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(String name) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE User WHERE name = ?");
        query.setParameter(0, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserName(String name, String newName) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET name = ? WHERE name = ?");
        query.setParameter(0, newName);
        query.setParameter(1, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void changeUserPassword(String name, String password) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE User SET password = ? WHERE name = ?");
        query.setParameter(0, password);
        query.setParameter(1, name);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public boolean validateUser(String name, String password) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where name =? and password = ?", User.class);
        query.setParameter(0, name);
        query.setParameter(1, password);
        List<User> users = query.list();
        boolean result = users.size() > 0;
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public String getRole(String name, String password) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query<String> query = session.createQuery("SELECT role FROM User WHERE name = ? and password =?", String.class);
        query.setParameter(0, name);
        query.setParameter(1, password);
        String role = query.uniqueResult();
        transaction.commit();
        session.close();
        return role;
    }

    @Override
    public User getUserByName(String name) {
        Session session = dbHelper.getSession();
        Transaction transaction = session.beginTransaction();
        Query<User> query = session.createQuery("FROM User where name = ?", User.class);
        query.setParameter(0, name);
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users.get(0);
    }
}
