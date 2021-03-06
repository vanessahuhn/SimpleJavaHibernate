package com.p3p20.dao;
 
import java.util.ArrayList;
import java.util.List;
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
import com.p3p20.model.User;
import com.p3p20.util.HibernateUtil;

//DAO = Data Access Object

public class UserDao {
 
/* Methode pour ajouter un utilisateur */   
 public void addUser(User user) {
        //unit of work : une ou plusieurs queries, c'est mieux de faire une requ�te = une transaction
	 	Transaction trns = null;
        //une session est une connexion � la bdd
	 	//on ouvre une connexion avec la bdd
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	//d�but de la transaction pour cette session
            trns = session.beginTransaction();
            //pr�pare dans la m�moire une query de type insert
            session.save(user);
            //met � jour les donn�es dans la bdd et ex�cute les query souvegard�es via le .save
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
            	//si on exception est lev�e, on annule les changements faits � la bdd
                trns.rollback();
            }
            e.printStackTrace();
        }
    }
/* M�thode pour supprimer un utilisateur */
    public void deleteUser(int userid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            //permet de r�cup�rer un objet depuis la base de donn�es, il faut le caster en user
            User user = (User) session.load(User.class, new Integer(userid));
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } 
    }
/* Methode pour faire un update sur l� utilisateur */
    public void updateUser(User user) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        }
    }
 
/* Recuperer tous les utilisateurs */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            users = session.createQuery("from User").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return users;
    }
 
/*Recuperer des utilisateurs par Id */
    public User getUserById(int userid) {
        User user = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from User where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", userid);
            user = (User) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return user;
    }
}
