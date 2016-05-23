<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<body>
    ${customer}
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Type</th>
            <th>Already ordered</th>
            <th>Order more!</th>
        </tr> 
        <c:forEach var="pizza" items="${pizzas}"> 
        <tr>        
            <td>${pizza.name}</td>
            <td>${pizza.price}</td>
            <td>${pizza.type}</td>
            <td>${cart.get(pizza)}</td>
            <td><form method="Post" action="addToOrder">
                    <input type ="hidden" name="PizzaId" value="${pizza.id}"/>
                    <input name="count" type="number" value="1"/>
                    <input type="submit" value="Add to cart"/>
                </form> 
                    
        </tr>
        </c:forEach>
    </table>
  
    	
</body>
</html>
