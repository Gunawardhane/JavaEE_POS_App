package lk.ijse.jsp.bo.custom.impl;

import lk.ijse.jsp.bo.custom.ItemBO;
import lk.ijse.jsp.dto.ItemDTO;

import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {
    private final ItemDAOImpl itemDAO = (ItemDAOImpl) FactoryDAO.getFactoryDAO().getInstance(DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO dto) throws SQLException {
        return itemDAO.add(new Item(dto.getCode(), dto.getName(), dto.getPrice(), dto.getQty()));
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getCode(), dto.getName(), dto.getPrice(), dto.getQty()));
    }
    @Override
    public boolean deleteItem(ItemDTO dto) throws SQLException {
        return itemDAO.delete(new Item(dto.getCode()));
    }
}