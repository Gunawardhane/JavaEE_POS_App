package lk.ijse.jsp.servlet;

import lk.ijse.jsp.bo.BOTypes;
import lk.ijse.jsp.bo.FactoryBO;
import lk.ijse.jsp.bo.custom.impl.CustomerBOImpl;
import lk.ijse.jsp.dto.CustomerDTO;
import lk.ijse.jsp.util.ResponseUtil;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.json.*;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = {"/pages/customer"})
public class CustomerServletAPI extends HttpServlet {
    private final CustomerBOImpl customerBO = (CustomerBOImpl) FactoryBO.getFactoryBO().getInstance(BOTypes.CUSTOMER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {



            ServletContext servletContext = getServletContext();
            BasicDataSource dbcp = (BasicDataSource) servletContext.getAttribute("dbcp");

            try (Connection connection = dbcp.getConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
                ResultSet resultSet = preparedStatement.executeQuery();
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                while (resultSet.next()) {
                    arrayBuilder.add(
                            Json.createObjectBuilder()
                                    .add("cusID", resultSet.getString(1))
                                    .add("cusName", resultSet.getString(2))
                                    .add("cusAddress", resultSet.getString(3))
                                    .add("cusSalary", resultSet.getString(4))
                    );
                }
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Successfully loaded...!",arrayBuilder.build()));
            } catch (SQLException e) {
                resp.setStatus(400);
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
            }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            if (customerBO.addCustomer(new CustomerDTO(req.getParameter("cusID"),req.getParameter("CusName"),req.getParameter("cusAddress"),req.getParameter("cusSalary")))) {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Ok","Successfully Added...!"));
            }else {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not Added...!"));
            }
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject jsonObject = Json.createReader(req.getReader()).readObject();

        try {
            if (customerBO.updateCustomer(new CustomerDTO(jsonObject.getString("cusID"),jsonObject.getString("cusName"),jsonObject.getString("cusAddress"),jsonObject.getString("cusSalary")))) {
                resp.getWriter().print(ResponseUtil.getResJsonObject("Ok","Successfully Updated...!"));
            }else{
                resp.getWriter().print(ResponseUtil.getResJsonObject("Error","Not Updated...!"));
            }
        } catch (SQLException e) {
            resp.setStatus(400);
            resp.getWriter().print(ResponseUtil.getResJsonObject("Error",e.getMessage()));
        }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            if (customerBO.deleteCustomer(new CustomerDTO(req.getParameter("cusID")))) {
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



