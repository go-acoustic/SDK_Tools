<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties"%>
<%@page import="java.util.Date" %>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*" errorPage=""%>
<%
	InputStream stream = application
			.getResourceAsStream("/config.properties");
	Properties props = new Properties();
	props.load(stream);
	Boolean DEBUG = false;

	DEBUG = ("true").equals(props.getProperty("debug"));
	String id = request.getParameter("id");
	String randomsample = request.getParameter("randomsample");
	String killSwitchResponse = "";
	String debugstr = "";

	// white list
	if (id != null && !id.isEmpty()) {
		InputStream whitestream = application.getResourceAsStream(props
				.getProperty("WhiteListFile"));
		BufferedReader input = new BufferedReader(
				new InputStreamReader(whitestream));
		String line = "";
		Boolean match = false;
		while ((line = input.readLine()) != null) {
			line = line.trim();
			if (line.equals(id)) {
				killSwitchResponse = "1";
				match = true;
				break;
			}
		}
		input.close();
		if (!match) {			
			killSwitchResponse = "0";
		}
	}

	// If kill switch is by sample rate
	else if (randomsample != null) {
		int rand = (int) (Math.random() * 100);
		int sampleRate = Integer.parseInt(props
				.getProperty("samplerate"));
		if (rand <= sampleRate) {			
			killSwitchResponse = "1";
		} else {			
			killSwitchResponse = "0";
		}
	} else {		
		killSwitchResponse = "0";
	}
	
	out.print(killSwitchResponse);

	//always give the path from root. This way it almost always works.
	String nameOfTextFile = props.getProperty("logfile");
	PrintWriter pw;

	if (DEBUG) {
		try {
			pw = new PrintWriter(new FileOutputStream(nameOfTextFile,
					true));
			Date date = new java.util.Date();
			debugstr = date.toString() + "\t"; 
			if (request.getQueryString() != null) {
				debugstr += request.getQueryString();
			}
			if("0".equals(killSwitchResponse))
				pw.println(debugstr + "\tDisable");
			else
				pw.println(debugstr + "\tEnable");
			//clean up
			pw.close();
		} catch (IOException e) {
			out.println(e.getMessage());
		}
	}
%>