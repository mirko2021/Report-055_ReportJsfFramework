<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name='viewport' content='width=device-width, initial-scale=1.0'>
		<title>INDEX</title>
		<style>
			.row_field{
				display: flex; 
				flex-grow: 1;
				flex-direction: row;
				flex-wrap: wrap;
			}
			.column_field{
				display: flex; 
				flex-grow: 1;
				flex-direction: column;
				flex-wrap: wrap;
				flex-basis: 200px;
			}
		</style>
	</head>
	<body>
		<div class='row_field'>
			<div class='column_field'>
				<h1>Линкови</h1>
				<%
				    int count = 0; 
					bean.FileListingBean bean = (bean.FileListingBean) request.getSession().getAttribute("bean"); 
					if(bean==null) bean = new bean.FileListingBean();
					request.getSession().setAttribute("bean", bean);
					if(request.getParameter("random")!=null) bean.randomFileSelect(); 
					for(java.io.File file: bean.files()){
						request.getSession().setAttribute("file", file);
						%><a href='${bean.web(pageContext.request, file)}' target='_blank'><%=bean.relative(file)%></a><br><%
					    count++;
					}
				%>
				<p>Пребројано је <%=count%> ставки. </p>
			</div>
			<div class='column_field'>
				<h1>Случајни линк</h1>
				<form method='POST' id='_random_index_refresh_form'><input type='submit' name='random' value='Освјежавање случајног линка'/><br><br></form>
				<a href='${bean.web(pageContext.request, bean.getRandomFileSelect())}' target='_blank'><%=bean.relative(bean.getRandomFileSelect())%></a><br><br>
			</div>
		</div>
	</body>
</html>