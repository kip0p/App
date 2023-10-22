

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CHK17
 */
@WebServlet("/CHK17")
public class CHK17 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CHK17() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=Shift_Jis");
		PrintWriter out = response.getWriter();
		out.println(" <html>                                               ");
		out.println("   <head>                                             ");
		out.println("     <title>Servletの動作確認</title>                 ");
		out.println("   </head>                                            ");
		out.println("   <body>                                             ");
		out.println("     <p>Servletが動作していることを確認しました。</p> ");
		out.println("   </body>                                            ");
		out.println(" </html>                                              ");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
