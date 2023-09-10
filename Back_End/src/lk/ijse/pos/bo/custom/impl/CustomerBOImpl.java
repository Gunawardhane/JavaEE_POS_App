package lk.ijse.jsp.bo.custom.impl;

import lk.ijse.jsp.bo.custom.CustomerBO;
import lk.ijse.jsp.dao.DAOTypes;
import lk.ijse.jsp.dao.FactoryDAO;
import lk.ijse.jsp.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.jsp.dto.CustomerDTO;
import lk.ijse.jsp.entity.Customer;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAOImpl customerDAO = (CustomerDAOImpl) FactoryDAO.getFactoryDAO().getInstance(DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.add(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary()));
    }
    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.delete(new Customer(dto.getId()));
    }
}
