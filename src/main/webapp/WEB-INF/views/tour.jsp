<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrii
  Date: 3/1/19
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../../resources/WEB-INF/css/main.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
          integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">

</head>
<body>

<div class="pure-u-3-5">
    <table class="pure-table pure-table-bordered pure-u-3-5">
        <thead>
        <tr>
            <td>ID</td>
            <td>Tour Name</td>
            <td>Tour description</td>
            <td>Tour agency id</td>
            <td>Tour cost</td>
        </tr>
        </thead>
        <tbody class="pure-table-odd">
        <%--@elvariable id="tours" type="java.util.List"--%>
        <c:forEach var="tour" items="${tours}">
            <tr style="border: black">
                <td>${tour.getId()}</td>
                <td>${tour.getName()}</td>
                <td>${tour.getDescription()}</td>
                <td>${tour.getAgency()}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="pure-u-2-5">
    <form class="pure-form pure-form-stacked pure-u-2-5" action="<c:url value="/tour"/>" method="POST">
        Tout name <label>
        <input type="text" name="tour_name">
    </label>
        Tour description <label>
        <input type="text" name="tour_description">
    </label>
        Tour agency id <label>
        <input type="text" name="tour_agency">
    </label>
        <input type="submit" value="Submit"/>
    </form>
</div>

<div>
    <label name="${errorMessage}">
        ${errorMessage}
    </label>
</div>

<div>
    <a href="/index">Home</a>
</div>

</body>
</html>
