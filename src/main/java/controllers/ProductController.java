/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import models.Product;

/**
 *
 * @author luliou
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");

        if (request.getParameterMap().isEmpty()) { //view menu Model request.setAttribute("title", "Daftar Produk");
            ArrayList<Product> prods = new Product().get();
            request.setAttribute("list", prods);
            System.out.println(prods);
            request.getRequestDispatcher("product/view.jsp").forward(request, response);
            request.getSession().removeAttribute("msg");

        } else if ("custom".equals(menu)) { //view menu Object
            request.setAttribute("title", "Daftar Produk (custom query)");
            ArrayList<ArrayList<Object>> prods = new Product().query("SELECT * FROM product");
            request.setAttribute("list", prods);
            request.getRequestDispatcher("product/view_rdbms.jsp").forward(request, response);
        } else if ("add".equals(menu)) { //add menu //add menu
            request.setAttribute("title", "Tambah Produk");
            request.getRequestDispatcher("product/form.jsp").forward(request, response);

        } else if ("edit".equals(menu)) { //edit menu
            request.setAttribute("title", "Edit Produk");
            request.setAttribute("action", "?id=" + request.getParameter("id"));
            Product p = new Product().find(request.getParameter("id"));
            if (p == null) {
                response.sendRedirect("product");
                return;
            }
            request.setAttribute("product", p);
            request.getRequestDispatcher("product/form.jsp").forward(request, response);
        } else if ("qb".equals(menu)) { //view menu with query builder example
            request.setAttribute("title", "Daftar Produk dengan Query Builder");
            Product p = new Product();
            p.select("id, name");
            p.where("price < 10000000");
            p.addQuery("ORDER BY name DESC");
            ArrayList<Product> prods = p.get();
            request.setAttribute("list", prods);
            request.getRequestDispatcher("product/view.jsp").forward(request, response);
        } else {
            response.sendRedirect("product");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        if (id == null) { //insert data
            Product p = new Product();
            p.setName(request.getParameter("name"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.insert();
            request.getSession().setAttribute("msg", "Produk berhasil ditambah");
        } else if (action == null) { //update data

            Product p = new Product();
            p.setId(Integer.parseInt(id));
            p.setName(request.getParameter("name"));
            p.setPrice(Double.parseDouble(request.getParameter("price")));
            p.update();
            request.getSession().setAttribute("msg", "Produk berhasil diubah");
        } else if ("del".equals(action)) { //delete data

            Product p = new Product().find(request.getParameter("id"));
            if (p != null) {
                p.delete();
            }
            request.getSession().setAttribute("msg", "Produk berhasil dihapus");
        }
        response.sendRedirect("product");
    }

}
