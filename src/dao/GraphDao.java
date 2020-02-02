package dao;


import java.util.ArrayList;

import dto.Graph;

public interface GraphDao 
{
	ArrayList<Graph> searchGraph(String label);
}
