<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<body bgcolor="black">
<center>
	<table width="1000" height="5">
			<tr height="50">
				<!-- �ΰ� �̹��� -->
				<td align="left" width="500">
				<a href="ShoppingMain.jsp">
					<img alt="" src="img/logo.JPG" border="0" align="left">	
				</a>
				</td>
			<!-- ȸ�� �α��� ����  -->
			<!-- ���ǰ��� ������!!! [�α���]  [ȸ������] ��ư ���̰� -->
			<c:if test="${mbean==null }">
				<td align="right" width="300">                  
					<input type="button" onclick="location.href='login.do'" value="�α���">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="location.href='joinform.do'" value="ȸ������">
				</td>
			</c:if>
			<!-- ���ǰ��� �ִٸ�( �α����� �Ǿ��ٸ�) ???�� ȯ���մϴ�.  [�α׾ƿ�] ��ư ���̰�  -->
			<c:if test="${mbean!=null }">
				<td align="right" width="300">
					<font size="3" color="white">${mbean.memid }�� ȯ�� �մϴ�.</font>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="location.href='logout.do'" value="�α׾ƿ�">
				</td>
			</c:if>
			<!-- �˻� -->
			<form action="mainsearch.do" method="post">
				<td align="right" width="200">
					<input type="text" name="msearch" size="10">
					<input type="submit" value="�˻�">
				</td>
			</form>	
			</tr>
	</table>	
	<!--  �޴����� ��Ÿ���� ���� -->
	<table width="1000" height="5">
		<tr>
			<td align="center" bgcolor="black">
				<a href="sujak.do">
					<img src="img/sujak.JPG" >  <!-- ���۾����� �޴� -->
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="jundong.do">
					<img src="img/jundong.JPG" > <!-- �������� �޴� -->
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="stanlyinfo.do">
					<img src="img/stanlyinfo.JPG" > <!-- ���ٸ� �Ұ� �޴� -->
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="tooluse.do">
					<img src="img/tooluse.JPG" > <!-- �������� �޴� -->
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="download.do">
					<img src="img/download.JPG" >  <!-- �ٿ�ε� �޴� -->
				</a>
			</td>
			<td align="center" bgcolor="black">
				<a href="board.do">
					<img src="img/gogak.JPG" > <!-- ������ �޴� -->
				</a>
			</td>
		</tr>
	</table>
</center>
</body>
</html>
