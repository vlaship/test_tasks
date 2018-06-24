package company.DAO;

import company.Hibernate.SessionUtil;
import company.entity.Department;
import company.entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO extends SessionUtil implements IDAO<Employee> {

    private Session session;

    @Override
    public void add(Employee employee) {
        openTransactionSession();

        session = getSession();
        session.save(employee);

        closeTransactionSession();
    }

    @Override
    public void remove(Employee employee) {
        openTransactionSession();

        session = getSession();
        session.remove(employee);

        closeTransactionSession();
    }

    @Override
    public void update(Employee employee) {
        openTransactionSession();

        session = getSession();
        session.update(employee);

        closeTransactionSession();
    }

    @Override
    public Employee getById(int id) {

        openTransactionSession();

        session = getSession();
        Employee employee = session.get(Employee.class, id);

        closeTransactionSession();

        return employee;
    }

    @Override
    public List<Employee> getList() {
        openTransactionSession();

        session = getSession();

        List<Employee> list = session.createNativeQuery("SELECT * from Employee", Employee.class).list();

        closeTransactionSession();

        return list;
    }

    public List<Employee> getListByDepartment(Department department) {
        openTransactionSession();

        String sql = "SELECT * FROM employee WHERE department_id = ?1";
        session = getSession();

        Query query = session.createNativeQuery(sql,Employee.class);
        query.setParameter(1, department.getId());

        List<Employee> list = query.getResultList();

        closeTransactionSession();

        return list;
    }
}
