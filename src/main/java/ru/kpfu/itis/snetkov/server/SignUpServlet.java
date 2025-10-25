package ru.kpfu.itis.snetkov.server;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.snetkov.entity.User;
import ru.kpfu.itis.snetkov.service.UserService;
import ru.kpfu.itis.snetkov.service.impl.UserServiceImpl;
import ru.kpfu.itis.snetkov.util.CloudinaryUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static ru.kpfu.itis.snetkov.server.FileUploadServlet.DIRECTORIES_COUNT;
import static ru.kpfu.itis.snetkov.server.FileUploadServlet.FILE_PREFIX;

@WebServlet(name = "SignUp", urlPatterns = "/sign_up")
@MultipartConfig(maxFileSize = 5*1024*1024, maxRequestSize = 10*1024*1024)
public class SignUpServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    private static final Cloudinary cloudinary = CloudinaryUtil.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("sign_up.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // registration
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String filename = fileUpload(req, resp);
        if (!login.isEmpty() && !password.isEmpty() && userService.save(new User(name, lastname, login, filename, password))) {

            resp.sendRedirect("login");
        } else {
            resp.sendRedirect("sign_up");
        }
        // TODO: persist in memory (Map) login + password and after that use it in LoginServlet instead of "login" and "password"
    }

    private String fileUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part part = req.getPart("file");
        String appPath = req.getServletContext().getRealPath("/");
        String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File file = new File(appPath + File.separator + FILE_PREFIX + File.separator + filename.hashCode() % DIRECTORIES_COUNT + File.separator + filename);
        InputStream content = part.getInputStream();
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileOutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[content.available()];
        content.read(buffer);
        outputStream.write(buffer);
        outputStream.close();
        return cloudinary.uploader().upload(file, new HashMap()).get("url").toString();
    }
}
