package company.DAO;

import company.Hibernate.SessionUtil;
import company.model.Employee;
import company.model.Salary;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class SalaryDAO extends SessionUtil implements IDAO<Salary> {

    private Session session;

    @Override
    public void add(Salary salary) {
        openTransactionSession();

        session = getSession();
        session.save(salary);

        closeTransactionSession();
    }

    @Override
    public void remove(int id) {
        openTransactionSession();

        session = getSession();

        Query query = session.createNativeQuery("DELETE from salary WHERE id = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        closeTransactionSession();
    }

    @Override
    public void remove(Salary salary) {
        openTransactionSession();

        session = getSession();

        session.remove(salary);

        closeTransactionSession();
    }

    @Override
    public void update(Salary salary) {
        openTransactionSession();

        session = getSession();

        session.update(salary);
        /*Query query = session.createNativeQuery("UPDATE salary SET quantity = ?1 WHERE id = ?2");
        query.setParameter(1, salary.getQuantity());
        query.setParameter(2, salary.getId());
        query.executeUpdate();*/

        closeTransactionSession();
    }

    @Override
    public Salary getById(int id) {

        openTransactionSession();

        session = getSession();
        Salary salary = session.get(Salary.class, id);

        closeTransactionSession();

        return salary;
    }

    @Override
    public List<Salary> getList() {
        openTransactionSession();

        session = getSession();

        List<Salary> list = session.createNativeQuery("SELECT * from salary", Salary.class).list();

        closeTransactionSession();

        return list;
    }
}
