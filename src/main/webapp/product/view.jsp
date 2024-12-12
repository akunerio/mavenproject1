<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, models.Product"%>
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
            <div class="col-4">
                <span class="float-left">${sessionScope.msg}</span>
                <a href="product?menu=add" class="btn btn-secondary float-right">Tambah</a><br /><br />
                <table class="table table-bordered table-striped">
                    <tr>
                        <th>No.</th>
                        <th>Nama</th>
                        <th>Harga</th>
                        <th>Aksi</th>
                    </tr>
                    <%
                        ArrayList<Product> prods = (ArrayList<Product>)request.getAttribute("list");
                        int i = 1;
                        for (Product p : prods) {
                    %>
                    <tr>
                        <td><%= i++ %></td>
                        <td><%= p.getName() %></td>
                        <td><%= p.getPrice() %></td>
                        <td>
                            <a href="product?menu=edit&id=<%= p.getId() %>" class="btn btn-primary">Edit</a>
                            <form method="post" action="product?action=del&id=<%= p.getId() %>"
                                style="display:inline" onsubmit="return confirm('Yakin hapus?')">
                                <button class="btn btn-danger">Hapus</button>
                            </form>
                        </td>
                    </tr>
                    <%  } %>
                </table>
            </div>
        </div
    </body>
</html>
