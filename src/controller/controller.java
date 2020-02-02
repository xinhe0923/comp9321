package controller;
import dto.*;
import exception.UserBannedException;
import exception.UserUnVerifiedException;
import service.*;

import java.awt.event.AdjustmentEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//Servlet implementation class ControlServlet
@WebServlet(urlPatterns="/control",displayName="ControlServlet")
public class controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
    //    @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = new String("");
        if (request.getParameter("action") != null){
            action = request.getParameter("action");
        }
        String nextPage = "home.jsp";
        switch(action){
        case "registration":
            UserService userService = new UserService();
            User user = userService.makeUserbyRequest(request);
            try {
                userService.register(user);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            nextPage = "registerDone.jsp";
            break;
        case "login":
            UserService userService1 = new UserService();
            User user1 = userService1.makeUserbyRequest(request);
            if (userService1.getUserByUsername(user1.getUsername()) == null){
            
                nextPage = "noThisUser.jsp";
            }
            else{
            try {
                if ((user1 = userService1.login(user1.getUsername(),user1.getPassword())) != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("login","true");
                    session.setMaxInactiveInterval(60*60);
                    session.setAttribute("user", user1);
                }
                else{
                	
                	
                    nextPage = "wrongpassword.jsp";
                }
            } catch (UserBannedException e) {
                nextPage = "userIsBanned.jsp";
            } catch (UserUnVerifiedException e) {
                nextPage = "notActivated.jsp";
            }}
            break;
        case "logout":
            HttpSession session = request.getSession();
            session.invalidate();
            break;
        case "pauseItem":
            ItemService itemService = new ItemService();
            itemService.pauseItem(request.getParameter("id"));
            nextPage = "store.jsp";
            break;
        case "unPauseItem":
            ItemService itemService1 = new ItemService();
            itemService1.unPauseItem(request.getParameter("id"));
            nextPage = "store.jsp";
            break;
        case "verify":
            UserService userService2 = new UserService();
            userService2.verifyUserEmail(request.getParameter("uuid"));
            nextPage = "emailConfirm.jsp";
        	break;
        case "addItem":
            ItemService itemService3 = new ItemService();
            try {
                Item item = itemService3.makeItemByRequest(request);
                itemService3.saveOrUpdate(item);
                HttpSession session_item=request.getSession();
                String image_url=item.getTitle();
                session_item.setAttribute("image_url", image_url);
                nextPage="upload_file.jsp";
//                nextPage = "store.jsp";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        	break;
        
        case "editprofile":
            UserService userService21 = new UserService();
            HttpSession session1 = request.getSession();
            User user2 = (User) session1.getAttribute("user");
            user2 = userService21.makeUserbyRequest(request ,user2);
            try {
                userService21.editProfile(user2);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            user2 = userService21.getUserById(user2.getId());
            session1.setAttribute("user", user2);
            nextPage = "profile.jsp";
            break;
        case "showDetail":
        	System.out.println("2222222222222222222222222");
            ItemService itemService2 = new ItemService();
            Item item = itemService2.getItemById(request.getParameter("id"));
            System.out.println("yesssssssssssssssssssssssss");
            System.out.println(item.getAuthors());
            
            request.setAttribute("detail", item);
            nextPage = "detail.jsp";
        	break;
        case "addtocart":
            CartService cartService = new CartService();
            HttpSession session2 = request.getSession();
            User user3 = (User) session2.getAttribute("user");
            int userId = user3.getId();
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            cartService.addToCart(userId,itemId);
            nextPage = "shoppingcart.jsp";
        	break;
        case "addtowishlist":
            WishListService wishlistService = new WishListService();
            HttpSession session3 = request.getSession();
            User user4 = (User) session3.getAttribute("user");
            int userId1 = user4.getId();
            int itemId1 = Integer.parseInt(request.getParameter("itemId"));
            wishlistService.addToWishList(userId1,itemId1);
            nextPage = "wishlist.jsp";
        	break;
        case "basicsearch":
            ItemService itemService31 = new ItemService();
            List<Item> elements = itemService31.basicSearch(request.getParameter("keyword"));
            request.setAttribute("elements", elements);
            nextPage = "result.jsp";
        	break;
        case "advsearch":
            ItemService itemService4 = new ItemService();
            List<Item> elements1 = null;
            try {
                elements1 = itemService4.advSearch(request);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.setAttribute("elements", elements1);
            nextPage = "result.jsp";
        	break;
        case "rmshoppingcart":
            CartService cartService1 = new CartService();
            String[] id_list = request.getParameterValues("pick");
            if (id_list !=null) {
                for (String temp_int : id_list) {
                    int id = Integer.parseInt(temp_int);
                    cartService1.removeCart(id);
                }
            }
            nextPage = "shoppingcart.jsp";
        	break;
        case "wltocart":
        	HttpSession session4 = request.getSession();
            User user5 = (User) session4.getAttribute("user");
            int userId2 = user5.getId();
            CartService cartService2 = new CartService();
            WishListService wishlistService1 = new WishListService();
            String[] id_list1 = request.getParameterValues("pick");
            List<WishList> elements2 = wishlistService1.getExistedWishList(user5.getId());
            if (id_list1 !=null) {
                for (String temp_int : id_list1) {
                    int id = Integer.parseInt(temp_int);
                    for (WishList element : elements2){
                    	if(id == element.getId()){
                    		cartService2.addToCart(userId2,element.getItem_id());
                    		break;
                }}
                }    
            }
            nextPage = "shoppingcart.jsp";
        	break;
        case "rmwishlist":
            WishListService wishlistService2 = new WishListService();
            String[] id_list2 = request.getParameterValues("pick");
            if (id_list2 !=null) {
                for (String temp_int : id_list2) {
                    int id = Integer.parseInt(temp_int);
                    wishlistService2.removeWishList(id);;
                }
            }
            nextPage = "wishlist.jsp";
        	break;
        case "makeorder":
            CartService cartService3 = new CartService();
            OrderService orderService = new OrderService();
            String[] id_list3 = request.getParameterValues("pick");
            if (id_list3 != null) {
                List<Cart> cartList = new ArrayList<>();
                boolean outOrder = false;
                for (String temp_int : id_list3) {
                    int id = Integer.parseInt(temp_int);
                    Cart cart = cartService3.getCartById(id);
                    cartList.add(cart);
                    if (cart.getCount() > cart.getItem().getQuantity()) {
                        outOrder = true;
                    }
                }
                if (!outOrder || cartList.size() == 0) {
                    try {
                        orderService.createOrder(cartList);
                        nextPage = "shoppingcart.jsp";
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                } else {
                    nextPage = "outofOrder.jsp";
                }
            }
            break;
        case "adminlogin":
            AdminService adminService = new AdminService();
            UserService userService3 = new UserService();
            User user6 = userService3.makeUserbyRequest(request);
            Admin admin = adminService.login(user6.getUsername(),user6.getPassword());
            if (admin != null){
                HttpSession session5 = request.getSession();
                session5.setAttribute("adminlogin","true");
                session5.setMaxInactiveInterval(60*60);
                nextPage = "adminmain.jsp";
            }
            else {
                nextPage = "adminwrongpassword.jsp";
            }
            break;
        case "checkbought":
            int userId3 = Integer.parseInt(request.getParameter("id"));
            OrderService orderService1 = new OrderService();
            UserService userService4 = new UserService();
            String username = userService4.getUserById(request.getParameter("id")).getUsername();
            request.setAttribute("username", username);
            List<Order> orders = orderService1.getAllOrderByUserId(userId3);
            List<OrderItem> result = new ArrayList<>();
            for (Order order : orders){
                List<OrderItem> temp = orderService1.getAllOrderItemByOrderId(order.getId());
                result.addAll(temp);
            }
            request.setAttribute("elements", result);
            nextPage = "adminBought.jsp";
        	break;
        case "checkremoved":
            CartService cartService4 = new CartService();
            UserService userService5 = new UserService();
            List<Cart> elements3 = cartService4.getRemovedCart(Integer.parseInt(request.getParameter("id")));
            User userRmoved = userService5.getUserById(request.getParameter("id"));
            request.setAttribute("username", userRmoved.getUsername());
            request.setAttribute("elements", elements3);
            nextPage = "adminRemoved.jsp";
        	break;
        case "banuser":
            UserService userService6 = new UserService();
            userService6.banUser(request.getParameter("id"));
            nextPage = "adminUsers.jsp";
        	break;
        case "unbanuser":
            UserService userService7 = new UserService();
            userService7.unBanUser(request.getParameter("id"));
            nextPage = "adminUsers.jsp";
        	break;
        case "adminsearch":
            ItemService itemService5 = new ItemService();
            List<Item> elements4 = null;
            try {
                elements4 = itemService5.adminAdvSearch(request);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.setAttribute("elements", elements4);
            nextPage = "adminItemSearchResult.jsp";
        	break;
        case "adminShowDetail":
            ItemService itemService6 = new ItemService();
            Item item1 = itemService6.getItemById(request.getParameter("id"));
            request.setAttribute("detail", item1);
            nextPage = "adminDetail.jsp";
        	break;
        case "unbanitem":
            ItemService itemService7 = new ItemService();
            itemService7.unBanItem(request.getParameter("id"));
            nextPage = "adminmain.jsp";
        	break;
        case "banitem":
            ItemService itemService8 = new ItemService();
            itemService8.banItem(request.getParameter("id"));
            nextPage = "adminmain.jsp";
        	break;
        }
        RequestDispatcher rd = request.getRequestDispatcher("/"+nextPage);
        System.out.println("czyczy");
        rd.forward(request, response);

    }
}
    

