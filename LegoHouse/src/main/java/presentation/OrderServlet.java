package presentation;

import data.models.Order;
import data.models.RoleEnum;
import data.models.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.BrickCalculator;
import logic.OrderController;
import logic.exceptions.OrderException;

/**
 *
 * @author Andreas Vikke
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/order"})
public class OrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            String orderId = request.getParameter("orderId");
            if (orderId != null) {
                Order order = OrderController.getOrder(Integer.parseInt(orderId));

                if (order == null) {
                    request.setAttribute("errormessage", "Order not found.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                } else {
                    if (session.getAttribute("user") == null) {
                        request.getRequestDispatcher("/login").forward(request, response);
                    } else if (((User) session.getAttribute("user")).getRole() == RoleEnum.EMPLOYEE
                            || ((User) session.getAttribute("user")).getId() == (order.getUserId())) {
                        session.setAttribute("order", order);
                        session.setAttribute("partList", BrickCalculator.calcBricks(order.getLength(), order.getWidth(), order.getHeight(), order.isDoor(), order.isWindow(), order.isBound()));
                        request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
                    } else {
                        request.setAttribute("errormessage", "Insufficient permisson to access order.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                }
            } else {
                response.addHeader("errormessage", "No Order Id supplied.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (OrderException | SQLException ex) {
                response.addHeader("errormessage", "No Order Id supplied.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
