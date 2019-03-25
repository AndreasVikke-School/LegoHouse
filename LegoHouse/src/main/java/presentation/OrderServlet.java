package presentation;

import data.models.BrickLayer;
import data.models.Order;
import data.models.RoleEnum;
import data.models.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

                        List<BrickLayer> brickLayers = BrickCalculator.calcBricks(order.getLength(), order.getWidth(), order.getHeight(), order.isDoor(), order.isWindow(), order.isBound());

                        HashMap<String, Integer> map = new HashMap();
                        for (int i = 0; i < brickLayers.size(); i++) {
                            map.put("S1-2x4", map.getOrDefault("S1-2x4", 0) + brickLayers.get(i).getSides().get(0).getBricks2x4());
                            map.put("S2-2x4", map.getOrDefault("S2-2x4", 0) + brickLayers.get(i).getSides().get(1).getBricks2x4());
                            map.put("S3-2x4", map.getOrDefault("S3-2x4", 0) + brickLayers.get(i).getSides().get(2).getBricks2x4());
                            map.put("S4-2x4", map.getOrDefault("S4-2x4", 0) + brickLayers.get(i).getSides().get(3).getBricks2x4());

                            map.put("S1-2x2", map.getOrDefault("S1-2x2", 0) + brickLayers.get(i).getSides().get(0).getBricks2x2());
                            map.put("S2-2x2", map.getOrDefault("S2-2x2", 0) + brickLayers.get(i).getSides().get(1).getBricks2x2());
                            map.put("S3-2x2", map.getOrDefault("S3-2x2", 0) + brickLayers.get(i).getSides().get(2).getBricks2x2());
                            map.put("S4-2x2", map.getOrDefault("S4-2x2", 0) + brickLayers.get(i).getSides().get(3).getBricks2x2());

                            map.put("S1-2x1", map.getOrDefault("S1-2x1", 0) + brickLayers.get(i).getSides().get(0).getBricks2x1());
                            map.put("S2-2x1", map.getOrDefault("S2-2x1", 0) + brickLayers.get(i).getSides().get(1).getBricks2x1());
                            map.put("S3-2x1", map.getOrDefault("S3-2x1", 0) + brickLayers.get(i).getSides().get(2).getBricks2x1());
                            map.put("S4-2x1", map.getOrDefault("S4-2x1", 0) + brickLayers.get(i).getSides().get(3).getBricks2x1());
                        }
                        session.setAttribute("partList", map);
                        request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
                    } else {
                        response.addHeader("errormessage", "Insufficient permisson to access order.");
                        request.getRequestDispatcher("/error.jsp").forward(request, response);
                    }
                }
            } else {
                response.addHeader("errormessage", "No Order Id supplied.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (OrderException | SQLException ex) {
            ex.printStackTrace();
            response.addHeader("errormessage", ex.getMessage());
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
