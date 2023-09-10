package lk.ijse.jsp.listener;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    static ServletContext servletContext;

    public static ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();

        BasicDataSource pool=new BasicDataSource();
        pool.setDriverClassName("com.mysql.jdbc.Driver");
        pool.setUrl("jdbc:mysql://localhost:3306/javaee_pos_app");
        pool.setUsername("root");
        pool.setPassword("1234");
        pool.setInitialSize(3);
        pool.setMaxTotal(3);

        servletContext.setAttribute("dbcp",pool);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
