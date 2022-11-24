<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@include file="menu.html"%>
	<div style="width: 100%;">
		<div style="width: 50%; height: 100px; float: left;">
			<form action="/webmvn/registrationcontroller" method='post'>
				<table border="0">
					<tr>
						<td width="100">Login</td>
						<td><input type="email" name="login" value="${rLogin}" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td>Re-Password</td>
						<td><input type="password" name="rePassword" /></td>
					</tr>
					<tr>
						<td>Full name</td>
						<td><input type="text" name="fullName" value="${rFullName}" /></td>
					</tr>
					<tr>
						<td>Region</td>
						<td><select name="region">
								<option value="Lviv" ${rRegionLviv}>Lviv region</option>
								<option value="Kyiv" ${rRegionKyiv}>Kyiv region</option>
								<option value="Kharkiv" ${rRegionKharkiv}>Kharkiv
									region</option>
						</select></td>
					</tr>
					<tr>
						<td>Gender</td>
						<td>F <input type="radio" value="F" name="gender" ${rGenderF} />
							M <input type="radio" value="M" name="gender" ${rGenderM} />
						</td>
					</tr>
					<tr>
						<td>Comment</td>
						<td><textarea cols="10" rows="5" name="comment">${rcomment}</textarea>
						</td>
					</tr>
					<tr>
						<td>I gree for personal</td>
						<td><input type="checkbox" name="agreement" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2"><input type="submit"
							value="Send" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="margin-left: 50%; height: 100px;">${errMessage}</div>
	</div>


</body>
</html>