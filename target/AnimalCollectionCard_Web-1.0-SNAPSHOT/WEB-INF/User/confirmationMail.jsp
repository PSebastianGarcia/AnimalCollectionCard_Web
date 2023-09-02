<jsp:include page="../repeatedCode/htmlBeginning1.jsp"/>
<jsp:include page="../repeatedCode/htmlBeginning2.jsp">
    <jsp:param name="title" value="Animal Collection Confirmation"/>
</jsp:include>
<main class="container text-center my-5">
    <h1>Gracias por elegir Animal Colecction</h1><br>
    <p>Haz click en el siguiente link para finalizar la confirmación de tu usuario: 
        <a href="${confirmationLink}" target="_blank">Link</a></p>
    <p>O copia y pega el siguiente token: ${token}</p>
</main>

<jsp:include page="../repeatedCode/htmlEnding.jsp"/>