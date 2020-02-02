package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.EntityDaoImpl;
import dao.GraphDaoImpl;
import dto.Entity;
import dto.EntityObject;
import dto.Graph;

/**
 * Servlet implementation class GraphServelet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EntityDaoImpl entityDao = new EntityDaoImpl();
	private GraphDaoImpl graphDao = new GraphDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String method = request.getParameter("method");
		if (method.equals("search"))
		{
			ArrayList<EntityObject> nodes = new ArrayList<EntityObject>();
			ArrayList<EntityObject> edges = new ArrayList<EntityObject>();
			
			String searchType = request.getParameter("searchType");
			
			String key = request.getParameter("key");
			
			ArrayList<Entity> eList = (ArrayList<Entity>) entityDao.findAllFullLabels(key);
			
            for (Entity e : eList)
            {
            	String fullKey = e.getObject();
    			ArrayList<ArrayList<EntityObject>> result = searchByKey(fullKey, searchType);
    			if (result != null)
    			{
    				ArrayList<EntityObject> newNodes = result.get(0);
    				ArrayList<EntityObject> newEdges = result.get(1);
    				
    				for (EntityObject node : newNodes)
    				{
    					if (! nodeFoundFromArrayByLabel(nodes, node.getLabel()))
    					{
    						nodes.add(node);
    					}
    				}
    				
    				for (EntityObject edge : newEdges)
    				{
    					edges.add(edge);
    				}
    			}
            }
			
			HttpSession session = request.getSession();
			session.setAttribute("nodes", nodes);
			session.setAttribute("edges", edges);
			response.sendRedirect(request.getContextPath() + "/graph.jsp");
		
		}
	}
	
	private ArrayList<ArrayList<EntityObject>> searchByKey (String key, String type)
	{
		Entity e = (Entity) entityDao.searchEntityIdByLabel(key);
		
		if (e == null)
		{
			return null;
		}
		
		String eId = e.getSubject();
		EntityObject eo = (EntityObject) entityDao.searchEntityObjectById(eId);
		if (type.equals("title"))
		{
			if (eo.getType().equals("Venue") || eo.getType().equals("Author"))
			{
				return null;
			}
		}
		
		if (type.equals("author"))
		{
			if (! eo.getType().equals("Author"))
			{
				return null;
			}
		}
		
		if (type.equals("venue"))
		{
			if (! eo.getType().equals("Venue"))
			{
				return null;
			}
		}
		
		ArrayList<Graph> graphs = graphDao.searchGraph(eId);
		ArrayList<EntityObject> nodes = new ArrayList<EntityObject>();
		ArrayList<EntityObject> edges = new ArrayList<EntityObject>();
		ArrayList<ArrayList<EntityObject>> result = new ArrayList<ArrayList<EntityObject>>();
		for (Graph g : graphs)
		{
			EntityObject fromObject = (EntityObject) entityDao.searchEntityObjectById(g.getSubject());
			if (! nodeFoundFromArrayByLabel(nodes, fromObject.getLabel()))
			{
				nodes.add(fromObject);
			}
			
			EntityObject toObject = (EntityObject) entityDao.searchEntityObjectById(g.getObject());
			if (! nodeFoundFromArrayByLabel(nodes, toObject.getLabel()))
			{
				nodes.add(toObject);
			}
			
			EntityObject edge = (EntityObject) entityDao.searchEntityObjectById(g.getPredicate());
			edge.setFrom(g.getSubject());
			edge.setTo(g.getObject());
			edges.add(edge);	
		}
		result.add(nodes);
		result.add(edges);
		return result;
	}
	
	private boolean nodeFoundFromArrayByLabel(ArrayList<EntityObject> list, String label)
	{
		for (EntityObject eo : list)
		{
			if (eo.getLabel().equals(label))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
