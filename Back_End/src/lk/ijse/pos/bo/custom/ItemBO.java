package lk.ijse.jsp.bo.custom;

import lk.ijse.jsp.bo.SuperBO;
import lk.ijse.jsp.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemBO extends SuperBO {
    boolean addItem(ItemDTO obj) throws SQLException;

    boolean updateItem(ItemDTO dto) throws SQLException;

    boolean deleteItem(ItemDTO dto) throws SQLException;
}
