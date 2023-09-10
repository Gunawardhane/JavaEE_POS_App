package lk.ijse.jsp.dao.custom.impl;

import lk.ijse.jsp.dao.custom.CustomerDAO;
import lk.ijse.jsp.entity.Customer;
import lk.ijse.jsp.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean add(Customer to) throws SQLException {
        return CrudUtil.setQuery("INSERT INTO customer VALUES (?,?,?,?)", to.getId(), to.getName(), to.getAddress(), to.getSalary());
    }

    @Override
    public boolean update(Customer to) throws SQLException {
        return CrudUtil.setQuery("UPDATE customer SET cusName=?,cusAddress=?,cusSalary=? WHERE cusID=?", to.getName(), to.getAddress(), to.getSalary(), to.getId());
    }

    @Override
    public boolean delete(Customer to) throws SQLException {
        return CrudUtil.setQuery("DELETE FROM customer WHERE cusID=?", to.getId());
    }



    @Override
    public ArrayList<Customer> getAll() {
        return null;
    }
}
