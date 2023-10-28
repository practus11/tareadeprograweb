/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Clases.Producto;
import Clases.ProductoController;

/**
 *
 * @author Lenovo
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    Producto producto;
    ProductoController registroProducto;
    
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
        
        try ( PrintWriter respuesta = response.getWriter()) {    
            respuesta.write("");
            registroProducto = new ProductoController();
            String control = request.getParameter("control");
            StringBuffer objetoRespuesta = new StringBuffer();
            if(control.toUpperCase().equals("GUARDAR")){
            //se crea el objeto producto con los datos recibidos del navegador a traves de la petición HTTP
                producto = new Producto(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("nombre"),
                Integer.parseInt(request.getParameter("precio")),
                Integer.parseInt(request.getParameter("existencia")),
                Integer.parseInt(request.getParameter("marca")), 
                Integer.parseInt(request.getParameter("categoria"))); 
                registroProducto.guardarProducto(producto);//almacenarlo en BD             
            }else if(control.toUpperCase().equals("ELIMINAR")){
                int codigoEliminar= Integer.parseInt(request.getParameter("codigo_producto"));
                registroProducto.eliminarProducto(codigoEliminar);
            }
                  
            registroProducto.getProductos(objetoRespuesta);//consultar alumnos en la BD
            respuesta.write(objetoRespuesta.toString());             
                      
            
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
