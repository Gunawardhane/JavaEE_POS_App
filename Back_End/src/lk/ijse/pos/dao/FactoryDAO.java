package lk.ijse.jsp.dao;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO() {

    }
    public static FactoryDAO getFactoryDAO() {
        if (factoryDAO == null) {
            return factoryDAO = new FactoryDAO();
        } else {
            return factoryDAO;
        }
    }
    public SuperDAO getInstance(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return null;
            default:
                return null;
        }
    }
}
