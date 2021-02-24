import com.example.erp.bean.Employee_Salary;
import com.example.erp.bean.Employees;
import com.example.erp.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ParseException {
        Employees e1=new Employees();
        e1.setFirstname("Bishwa");
        e1.setLastname("Sharma");
        e1.setEmail("bishwa@gmail.com");
        e1.setTitle("SDE");

        Employees e2=new Employees();
        e2.setFirstname("Pratap");
        e2.setLastname("Chandra");
        e2.setEmail("pratap@gmail.com");
        e2.setTitle("SDE");

        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e1);
        session.save(e2);
        Employee_Salary empsal=new Employee_Salary();
        Employee_Salary empsal2=new Employee_Salary();
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date d=formatter.parse("2019-12-10");
        SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd");
        Date d2=formatter2.parse("2019-11-10");
        System.out.println(d);
        empsal.setPayment_date(d);
        empsal.setAmount(10000.00);
        empsal.setDescription("BASIC");
        empsal.setEmployee(e1);
        session.save(empsal);
        empsal2.setPayment_date(d2);
        empsal2.setAmount(15000.00);
        empsal2.setDescription("DA");
        empsal2.setEmployee(e1);
        session.save(empsal2);
        tx.commit();

    }
}