package lk.ijse.jsp.bo.custom.impl;

import lk.ijse.jsp.bo.custom.CustomerBO;
import lk.ijse.jsp.dto.CustomerDTO;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAOImpl customerDAO = (CustomerDAOImpl) FactoryDAO.getFactoryDAO().getInstance(DAOTypes.CUSTOMER);
    @Override
    public boolean addCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.add(new Customer(dto.getNic(), dto.getName(), dto.getTel(), dto.getAddress()));
    }
    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getNic(), dto.getName(), dto.getTel(), dto.getAddress()));
    }
    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.delete(new Customer(dto.getNic()));
    }
}
