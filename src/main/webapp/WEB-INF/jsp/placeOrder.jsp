<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

    <title>Plese, confirm your order</title>
<body>
      ${customer}<br/>
      ${order}<br/>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Type</th>
            <th>Quantity</th>
            <th>Order more!</th>
        </tr> 
        <c:forEach var="pizza" items="${cart.keySet()}"> 
        <tr>        
            <td>${pizza.name}</td>
            <td>${pizza.price}</td>
            <td>${pizza.type}</td>
            <td>${cart.get(pizza)}</td>
        </tr>
        </c:forEach>
    </table>
    <form method="Post" action="confirm">
        <input type="submit" value="Order this pizzas!"/>
    </form>
</body>
</html>
