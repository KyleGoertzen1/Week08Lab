<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Notes</title>
        <link rel="stylesheet" href="<c:url value='styles/notes.css' />" />
    </head>
    <body>
        <h1>Manage Notes</h1>
        <h2>Notes</h2>
        <p>${errorMessage}</p>
        <table>
            <tr>
                <th>ID</th>
                <th>Date Created</th>
                <th>Contents</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="note" items="${notes}">
                <tr>
                    <td>${note.noteId}</td>
                    <td>${note.dateCreated}</td>
                    <td>${note.contents}</td>
                    <td>
                        <form action="notes" method="post" >
                            <input type="submit" value="Delete">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="selectedNoteId" value="${note.noteId}">
                        </form>
                    </td>
                    <td>
                        <form action="notes" method="get">
                            <input type="submit" value="Edit">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="selectedNoteId" value="${note.noteId}">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${selectedId == null}">
            <h3>Add Note</h3>
            <form action="notes" method="POST">
                ID: <input type="number" name="noteId"><br>
                DATE: <input type="String" name="dateCreated"><br>
                CONTENT: <input type="text" name="content"><br>
                <input type="hidden" name="action" value="add">
                <input type="submit" value="Save">
            </form>
        </c:if>
        <c:if test="${selectedId != null}">
            <h3>Edit Note</h3>
            <form action="notes" method="POST">
                ID: <input type="number" name="noteId" value="${selectedId.noteId}" readonly><br>
                DATE: <input type="date" name="dateCreated" value="${selectedId.dateCreated}" readonly><br>
                CONTENT: <input type="text" name="content" value="${selectedId.contents}"><br>
                <input type="hidden" name="action" value="edit">
                <input type="submit" value="Save">
            </form>
        </c:if>
    </body>
</html>
