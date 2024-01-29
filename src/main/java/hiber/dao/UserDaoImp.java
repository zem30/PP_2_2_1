package hiber.dao;

import hiber.model.User;
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
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCarModelAndSeries(String model, int series) {
        String HQL = "FROM User as us where us.userCar.model=:model and us.userCar.series=:series";
//        User car = sessionFactory.getCurrentSession().createQuery(HQL, User.class)
//                .setParameter("model", model)
//                .setParameter("series", series).getSingleResult();
//        return car;
        return sessionFactory.getCurrentSession().createQuery(HQL, User.class)
                .setParameter("model", model)
                .setParameter("series", series).getSingleResult();

    }
}
