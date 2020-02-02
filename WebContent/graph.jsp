
<%@ page import="java.util.ArrayList" %> 
<%@ page import="dto.EntityObject" %>
<html>
<head>

<script src="http://s.codepen.io/assets/libs/modernizr.js"
	type="text/javascript"></script>

<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style-result.css">
	<script src="js/index.js"></script>
	  <script src="http://visjs.org/dist/vis.js"></script>
	
    <script type="text/javascript" src="js/vis.js"></script>    
    <link href="css/vis.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
    <style type="text/css">
        #mynetwork {
            width: 960px;
            height: 600px;
            border: 1px solid lightgray;
        }
    </style>
</head>
<body>
<div id="demo">
<div class="table-responsive-vertical shadow-z-1">
<div >
<!-- Search: -->
 <form method="post" action="GraphServlet">
 <input type="hidden" name="method" value="search">
 <table id="table" class="table table-hover table-mc-light-blue">
 	<tr>
 	<td width=12%>
 	<select name=searchType class="form-control">
 		<option value = "title">Title</option>
 		<option value = "author">Author</option>
 		<option value = "venue">Venue</option>
 	</select>
 	</td>
 	<td  width=20%>
 	<input name="key" type="text" class="form-control"/>
 	</td>
 	<td  width=10%>
 	<input type="submit" value="submit" class="form-control"/>
 	</td>
 	<td width=40%></td>
 	<td width=40%></td>
 	</tr>
 </table>
</form>
</div>
<div id="mynetwork"></div>
</div>
</div>
<script type="text/javascript">

    // create an array with node
     
	<% 
	   int edgeDataLength = 0;
	   int nodeDataLength = 0;
	   ArrayList<EntityObject> edgeData = (ArrayList<EntityObject>) session.getAttribute("edges"); 
       if (edgeData != null) 
    	   
    	   edgeDataLength = edgeData.size();
       
	   ArrayList<EntityObject> nodeData = (ArrayList<EntityObject>) session.getAttribute("nodes");
       if (nodeData != null) 
    	   nodeDataLength = nodeData.size();
		   System.out.println(nodeDataLength);
	%>
	
	var nodeDataSet = new Array();
	var edgeDataSet = new Array();

	<% for (int i = 0; i < edgeDataLength; i ++) {%>
	
		<%
		EntityObject eo = edgeData.get(i);
		String from = eo.getFrom();
		String to = eo.getTo();
		String label = eo.getLabel();
		%>
		
		var from = "<%= from%>";
		var to = "<%= to%>";
		var label = "<%= label%>";
		edgeDataSet[<%= i%>] = {"from" : from, "to" : to, "arrows" : 'to', "font": {"align" : 'middle'}, "label" : label};
		
	<% } %>

	
	<% for (int i = 0; i < nodeDataLength; i ++) {%>
	<%
	EntityObject eo = nodeData.get(i);
	String id = eo.getId();
	String label = eo.getLabel();
	String type = eo.getType();
	%>
	
	var label = "<%= label%>";
	var id = "<%= id%>";
	var type = "<%= type%>";
	var group = ";"
	if (type == "Author")
	{
		group = "authors";
	}
	else if (type == "Venue")
	{
		group = "venues";
	}
	else
	{
		group = "books";
	}
	
	nodeDataSet[<%= i%>] = {"id" : id , "label" : label, "group" : group};

<% } %>
var container = document.getElementById('mynetwork');
var nodes = new vis.DataSet(nodeDataSet);
var edges = new vis.DataSet(edgeDataSet);
var data = {
        nodes: nodes,
        edges: edges
    };
var options = {
		groups : { 
			books : {
				shape: 'icon',
				icon: {
		              face: 'FontAwesome',
		              code: '\uf02d',
		              size: 50,
		              color: '#f0a30a'
		            }
			},
			
			authors : {
				shape: 'icon',
				icon: {
		              face: 'FontAwesome',
		              code: '\uf007',
		              size: 50,
		              color: '#57169a'
		            }
			},
			
			venues : {
				shape: 'icon',
				icon: {
		              face: 'FontAwesome',
		              code: '\uf19c',
		              size: 50,
		              color: '#aa00ff'
		            }
			}
		},
		"physics": {
    "barnesHut": {
        "gravitationalConstant": -26850
      }}};    
var network = new vis.Network(container, data, options);   
</script>
</body>
</html>