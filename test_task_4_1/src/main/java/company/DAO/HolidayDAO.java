package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Employee;
import company.entity.Holiday;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HolidayDAO extends SessionUtil implements IDAO<Holiday> {

    private Session session;

    @Override
    public void add(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.save(holiday);

        closeTransactionSession();
    }

    @Override
    public void remove(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.remove(holiday);

        closeTransactionSession();
    }

    @Override
    public void update(Holiday holiday) {
        openTransactionSession();

        session = getSession();
        session.update(holiday);

        closeTransactionSession();
    }

    @Override
    public Holiday getById(int id) {

        openTransactionSession();

        session = getSession();
        Holiday holiday = session.get(Holiday.class, id);

        closeTransactionSession();

        return holiday;
    }

    @Override
    public List<Holiday> getList() {
        openTransactionSession();

        session = getSession();

        List<Holiday> list = session.createNativeQuery("SELECT * from Holiday", Holiday.class).list();

        closeTransactionSession();

        return list;
    }

    public List<Holiday> getListByEmployee(Employee employee) {
        openTransactionSession();

        String sql = "SELECT * FROM holiday WHERE employee_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql,Holiday.class);
        query.setParameter(1, employee.getId());

        List<Holiday> list = query.getResultList();

        closeTransactionSession();

        return list;
    }
}