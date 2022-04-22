package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User userByCar(String model, int series) {
      String HQL="FROM User user LEFT OUTER JOIN FETCH user.car WHERE user.car.model=:model and user.car.series=:series";
      try {
         User usr = sessionFactory.getCurrentSession()
                 .createQuery(HQL, User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .uniqueResult();
         return usr;
      } catch (NonUniqueResultException e) {
         List<User> users = sessionFactory.getCurrentSession()
                 .createQuery(HQL, User.class)
                 .setParameter("model", model)
                 .setParameter("series", series)
                 .list();
         return users.get(0);
      }
   }
}
