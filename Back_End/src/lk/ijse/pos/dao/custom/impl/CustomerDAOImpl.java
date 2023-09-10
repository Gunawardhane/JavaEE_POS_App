package lk.ijse.jsp.dao.custom.impl;

import lk.ijse.jsp.dao.custom.CustomerDAO;
import lk.ijse.jsp.util.CrudUtil;

import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean add(Customer to) throws SQLException {
        return CrudUtil.setQuery("INSERT INTO customer VALUES (?,?,?,?)", to.getNic(), to.getName(), to.getTel(), to.getAddress());
    }

    @Override
    public boolean update(Customer to) throws SQLException {
        return CrudUtil.setQuery("UPDATE customer SET name=?,tel=?,address=? WHERE nic=?", to.getName(), to.getTel(), to.getAddress(), to.getNic());
    }

    @Override
    public boolean delete(Customer to) throws SQLException {
        return CrudUtil.setQuery("DELETE FROM customer WHERE nic=?", to.getNic());
    }

    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }
}
