//package priv.jesse.mall.web.user;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//    @Controller
//    @RequestMapping("/product")
//public class CommentController {
//    @RequestMapping(value = "/info.html",method = RequestMethod.GET)
//    public String info(Model model) {
//        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String datetime = tempDate.format(new Date());
//        model.addAttribute("curDate", datetime);
//        int n=(int)Math.random()*10000+1;
//        model.addAttribute("num", String.valueOf(n));
//        return "info";
//    }
//}