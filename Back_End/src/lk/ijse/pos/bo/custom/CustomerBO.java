package lk.ijse.jsp.bo.custom;

import lk.ijse.jsp.bo.SuperBO;
import lk.ijse.jsp.dto.CustomerDTO;

import java.sql.SQLException;


    public interface CustomerBO extends SuperBO {
        boolean addCustomer(CustomerDTO dto) throws SQLException;

        boolean updateCustomer(CustomerDTO dto) throws SQLException;

        boolean deleteCustomer(CustomerDTO dto) throws SQLException;
    }

