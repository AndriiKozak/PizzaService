<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<body>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Type</th>
        </tr> 
        <c:forEach var="pizza" items="${pizzas}"> 
        <tr>        
            <td>${pizza.name}</td>
            <td>${pizza.price}</td>
            <td>${pizza.type}</td>
            <td><form method="Post">
                    <input type ="hidden" name="PizzaId" value="${pizza.id}"/>
                    <input type="hidden" name="action" value="edit"/>
                    <input type="submit" value="edit"/>
                </form></td>
                <td><form method="Post"/>
                    <input type ="hidden" name="PizzaId" value="${pizza.id}"/>
                    <input type="hidden" name="action" value="delete"/>
                    <input type="submit" value="delete"/>
                </form></td>    
        </tr>
        </c:forEach>
    </table>
    <a href="/PizzaService/springserv/addNew">Create new pizza</a>
    	
</body>
</html>
