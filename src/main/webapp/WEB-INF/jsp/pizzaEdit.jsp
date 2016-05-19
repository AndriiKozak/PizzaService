<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">


<body>
    ${pizza}
    <form method="post" action="save">
        <input name="id" type="hidden" value="${pizza.id}"/>
        <input name="name" type="text" value="${pizza.name}"/>
        <input name="price" type="text"  value="${pizza.price}"/>
        <input name="type" type="text" value="${pizza.type}"/>
        <input value="save" type="submit">
        <input name ="action" type="hidden" value="no action">
    </form>
</body>
</html>
