package org.apache.jsp.WEB_002dINF.route.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/route/jsp/header.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\"\r\n");
      out.write("\"http://www.w3.org/TR/html4/strict.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t\t<link rel=\"shortcut icon\" href=\"./images/icon.ico\" type=\"image/x-icon\" />\r\n");
      out.write("\t\t<title>FDM Underground</title>\r\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/underground1.css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write("<h1>\r\n");
      out.write("\t\t<a id=\"title\" href=\"homepage.htm\">Home</a>\r\n");
      out.write("\t\t<a id=\"title\" href=\"searchRoutes.htm\">Find Route</a>\r\n");
      out.write("\t\t<a id=\"title\" href=\"buyTickets.htm\">Buy Ticket</a>\r\n");
      out.write("\t\t<a id=\"title\" href=\"contact.htm\">Contact</a>\r\n");
      out.write("\t\t<a id=\"title\" href=\"transactionHistory.htm\">Transaction History</a>\r\n");
      out.write("\t\t<a id=\"title\" href=\"logout.htm\">Logout</a>\r\n");
      out.write("\t<div id=\"alignright\">User: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div>\r\n");
      out.write("</h1>");
      out.write("\r\n");
      out.write("\t<img id=\"banner\" src=\"./images/bannerj.JPG\" />\r\n");
      out.write("\t<br>\r\n");
      out.write("\tWelcome to FDM's London Underground Route Planner!\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<img id=\"subwayimage\" src=\"./images/london_underground.jpg\" />\r\n");
      out.write("\t<br>\r\n");
      out.write("\tFind Route for Underground Journey : <a href=\"searchRoutes.htm\">Find Route</a>\r\n");
      out.write("\t<br>\r\n");
      out.write("\tBuy Ticket for Underground Journey : <a href=\"buyTickets.htm\">Buy</a>\r\n");
      out.write("\t<br>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
