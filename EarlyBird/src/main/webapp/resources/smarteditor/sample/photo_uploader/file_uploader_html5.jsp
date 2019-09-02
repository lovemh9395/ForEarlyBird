<%@page import="java.io.*"%>

<%@page import="java.util.UUID"%>

<%@page import="java.text.SimpleDateFormat"%>


<%
	String sFileInfo = "";
	//파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴 
	String name = request.getHeader("file-name");
	String ext = name.substring(name.lastIndexOf(".") + 1);
	//파일 기본경로
	String defaultPath = request.getServletContext().getRealPath("/");
	//파일 기본경로 _ 상세경로
	String path = defaultPath + "upload" + File.separator;
	File file = new File(path);
	if (!file.exists()) {
		file.mkdirs();
	}
	String realname = UUID.randomUUID().toString() + "." + ext;
	InputStream is = request.getInputStream();
	OutputStream os = new FileOutputStream(path + realname);
	int numRead;
	//파일쓰기
	byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
	while ((numRead = is.read(b, 0, b.length)) != -1) {
		os.write(b, 0, numRead);
	}
	if (is != null) {
		is.close();
	}
	os.flush();
	os.close();
	sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + "/upload/" + realname;
	out.println(sFileInfo);
%>
