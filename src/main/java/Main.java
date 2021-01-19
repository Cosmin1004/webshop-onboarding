import com.youngculture.webshop_onboarding.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory session = HibernateUtil.getSessionFactory();
    }
}
