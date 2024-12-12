<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="models.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" />
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body style="width:95%">
        <div class="row justify-content-center" style="margin-top:13%">
            <div class="col-3">
                <h4>Form ${title}</h4>
                <%
                    Product prod = null;
                    if (request.getAttribute("product") != null) prod = (Product)request.getAttribute("product");
                %>
                <form class="border" style="padding:20px" method="POST" action="product${action}">
                    <div class="form-group">
                        <label>Nama</label>
                        <input type="text" name="name" class="form-control" value="<%= prod == null ? "" : prod.getName() %>" required />
                    </div>
                    <div class="form-group">
                        <label>Harga</label>
                        <input type="number" name="price" class="form-control" value="<%= prod == null ? "" : prod.getPrice() %>" required />
                    </div>
                    <div style="text-align:center">
                        <button class="btn btn-success">Simpan</button>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
