<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<body>
    <c:forEach var="pizza" items="${pizzas}">                                                                  
${pizza.name}
${pizza.price}
${pizza.type}
<br/>
    </c:forEach>	
</body>
</html>
