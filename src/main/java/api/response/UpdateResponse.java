/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;

/**
 *
 * @author bayan
 */
public class UpdateResponse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateResponse</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateResponse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
         request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id <= 0) {
            javax.json.JsonObjectBuilder objectBuilder = javax.json.Json.createObjectBuilder().
                    add("Ошибка", "Инвалидный айди");
            out.print(objectBuilder.build().toString());
            return;
        }
        else {
            try {
                JsonReader jsonReader = Json.createReader(request.getReader());

                JsonObject jsonObject = jsonReader.readObject();

                String text = jsonObject.getString("text");
                int price = jsonObject.getInt("price");
                DbHelper db = new DbHelper();

                //TODO: изменять дату создания не в дефолтном конструкторе, а при инициализации
                Response response2 = new Response();
                response2.setId(id);
                response2.setText(text);
                response2.setPrice(price);

                db.updateResponse(response2);
                out.print(response2.getId());
            } catch (Exception e) {
                out.print("Error: " + e.getMessage());
            }
        }
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
