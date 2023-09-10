package lk.ijse.jsp.bo.custom.impl;

import lk.ijse.jsp.bo.custom.ItemBO;
import lk.ijse.jsp.dao.DAOTypes;
import lk.ijse.jsp.dao.FactoryDAO;
import lk.ijse.jsp.dao.custom.impl.ItemDAOImpl;
import lk.ijse.jsp.dto.ItemDTO;
import lk.ijse.jsp.entity.Item;

import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {
    private final ItemDAOImpl itemDAO = (ItemDAOImpl) FactoryDAO.getFactoryDAO().getInstance(DAOTypes.ITEM);

    @Override
    public boolean addItem(ItemDTO dto) throws SQLException {
        return itemDAO.add(new Item(dto.getCode(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }
    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getCode(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice()));
    }
    @Override
    public boolean deleteItem(ItemDTO dto) throws SQLException {
        return itemDAO.delete(new Item(dto.getCode()));
    }
}