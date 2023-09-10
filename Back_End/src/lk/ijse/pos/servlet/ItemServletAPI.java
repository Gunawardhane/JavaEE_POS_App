package lk.ijse.jsp.servlet;

import lk.ijse.jsp.bo.BOTypes;
import lk.ijse.jsp.bo.FactoryBO;
import lk.ijse.jsp.bo.custom.impl.ItemBOImpl;
import lk.ijse.jsp.dto.ItemDTO;
import lk.ijse.jsp.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/pages/item")
public class ItemServletAPI extends HttpServlet {

    private final ItemBOImpl itemBO = (ItemBOImpl) FactoryBO.getFactoryBO().getInstance(BOTypes.ITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        BasicDataSource dbcp = (BasicDataSource) servletContext.getAttribute("dbcp");


        try (Connection connection = dbcp.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = preparedStatement.executeQuery();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            while (resultSet.next()) {
                arrayBuilder.add(Json.createObjectBuilder()
                        .add("code", resultSet.getString(1))
                        .add("name", resultSet.getString(2))
                        .add("price", resultSet.getString(3))
                        .add("qty", resultSet.getString(4))
                        .build()
                );
            }

            resp.getWriter().print(ResponseUtil.getResJsonObject("Ok","Successfully loaded...!",arrayBuilder.build()));
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not loaded...!"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (itemBO.addItem(new ItemDTO(req.getParameter("code"), req.getParameter("name"), Double.parseDouble(req.getParameter("price")), Integer.parseInt(req.getParameter("qty"))))) {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Successfully Added...!"));
            }else {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not Added...!"));
            }
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();
        try  {
            if (itemBO.updateItem(new ItemDTO(jsonObject.getString("code"),jsonObject.getString("name"),Double.parseDouble(jsonObject.getString("price")),Integer.parseInt(jsonObject.getString("qty"))))) {
                resp.getWriter().print(ResponseUtil.getResJsonObject("OK","Successfully Updated...!"));
            }else {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not Updated...!"));
            }
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try  {
            if (itemBO.deleteItem(new ItemDTO(req.getParameter("code")))) {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Ok","Successfully Deleted...!"));
            }else {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not Deleted...!"));
            }
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
        }
    }
}
