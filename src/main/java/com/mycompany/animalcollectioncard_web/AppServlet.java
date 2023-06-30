/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.animalcollectioncard_web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 *
 * @author seba3
 */
@WebServlet(name = "app", urlPatterns = {"/AnimalCollection"})
public class AppServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Model mod = new Model();
            request.setAttribute("animalsList", mod.getAnimals());
            request.getRequestDispatcher("WEB-INF/animals/AnimalCollection.jsp").forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(500, "Fallo de lectura de Base de Datos");
        }

    }
}
