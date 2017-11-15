<%@ Page Language="C#" AutoEventWireup="true"%>
<script runat="server">
  public int Sampler()
  {
    Random rand = new Random();
    int nextRandom = rand.Next(1,100);
    int samplepercent = Convert.ToInt32(ConfigurationManager.AppSettings["rate"]);
    if(nextRandom <= samplepercent){
        return 1;
    }
    else{
        return 0; 
    }
  }

</script>
<%
    if (ConfigurationManager.AppSettings["killswitchtype"].Equals("percentagesample")) {
%>
    <%= Sampler() %>
<% } else{ } %>

