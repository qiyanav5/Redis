package cn.web.servlet;

import cn.domain.Province;
import cn.service.Impl.ProvinceServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProvinceServlet")
public class ProvinceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*//调用service查询
        List<Province> list = new ProvinceServiceImpl().findAll();
        //序列化list为json
        ObjectMapper mapper = new ObjectMapper();
        String json=mapper.writeValueAsString(list);*/

        //调用service查询
        String json = new ProvinceServiceImpl().findAllJson();

        //响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
