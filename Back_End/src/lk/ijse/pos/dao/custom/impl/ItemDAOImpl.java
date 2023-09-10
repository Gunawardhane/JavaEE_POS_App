package lk.ijse.jsp.dao.custom.impl;

import lk.ijse.jsp.dao.custom.ItemDAO;
import lk.ijse.jsp.entity.Item;
import lk.ijse.jsp.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item to) throws SQLException {
        return CrudUtil.setQuery("INSERT INTO item VALUES (?,?,?,?)", to.getCode(), to.getDescription(), to.getQtyOnHand(), to.getUnitPrice());
    }

    @Override
    public boolean update(Item to) throws SQLException {
        return CrudUtil.setQuery("UPDATE item SET itemName=?,qty=?,unitPrice=? WHERE code=?", to.getDescription(), to.getQtyOnHand(), to.getUnitPrice(), to.getCode());
    }

    @Override
    public boolean delete(Item to) throws SQLException {
        return CrudUtil.setQuery("DELETE FROM item WHERE code=? ", to.getCode());
    }



    @Override
    public ArrayList<Item> getAll() {
        return null;
    }
}
