package com.example.java_lab_7;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    static boolean b;
    static int cycle;

    static int min_h = 5;

    public HelloServlet(){
        HelloServlet.b = false;
        HelloServlet.cycle = 0;
    }

    public int CalculateSumChet(String[] args) {
        int sum1 = 0;
        for (String x : args)
        {
            int number = Integer.parseInt(x);
            if (number < 0)
            {
                if (number % 2 == -1){
                }
                else {
                    sum1 += number;
                }
            }
        }

        return  sum1;
    }

    public int CalculateSumNeChet(String[] args) {
        int sum1 = 0;
        for (String x : args)
        {
            int number = Integer.parseInt(x);
            if (number < 0)
            {
                if (number % 2 == -1){
                    sum1 += number;
                }
            }
        }

        return  sum1;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Здесь должны быть ФИО и № группы</title>");
            out.println("</head>");
            out.println("<body>");

            String fio = request.getParameter("fio");
            String group = request.getParameter("group");

            out.println("<h1> ФИО: " + fio + "</h1>");
            out.println("<h1> Группа: " + group + "</h1>");


            if (HelloServlet.b) HelloServlet.b = false;
            else HelloServlet.b = true;

            HelloServlet.cycle++;
            if (HelloServlet.cycle > HelloServlet.min_h) {
                out.println("<p>Дальнейшее уменьшение текста невозможно</p>");
                HelloServlet.cycle = 5;
            }
            else {
                HelloServlet.cycle++;
            }

            String size_default = request.getParameter("size_default");

            if (size_default == "ok"){
                HelloServlet.cycle = 4;
            }

            String num1 = request.getParameter("num1");
            String num2 = request.getParameter("num2");
            String num3 = request.getParameter("num3");
            String num4 = request.getParameter("num4");

            int sumchet = 0;
            int sumnechet = 0;

            if (num1 != null && num2 != null && num3 != null && num4 != null){
                String[] mass = new String[] {num1, num2, num3, num4};
                sumchet = CalculateSumChet(mass);
                sumnechet = CalculateSumNeChet(mass);
            }


            out.println("<h" + HelloServlet.cycle + ">" + "<table>");
            out.println("<tr>" + HelloServlet.b + "</tr><br>");
            out.println("<tr>Сумма четных отрицательных: " + sumchet + "</tr><br>");
            out.println("<tr>Сумма нечетных отрицательных: " + sumnechet + "</tr>");
            out.println("</table>" + "</h" + HelloServlet.cycle + ">");


            //=============II
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

//?group=4311&fio=Горохов Андрей Сергеевич&num1=-3&num2=-4&num3=-4&num4=-4