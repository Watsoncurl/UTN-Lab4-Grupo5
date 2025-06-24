<%@ tag description="Componente para mostrar cards de informes" pageEncoding="UTF-8" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="icono" required="true" type="java.lang.String" %>
<%@ attribute name="colorHeader" required="true" type="java.lang.String" %>
<%@ attribute name="columnas" required="true" type="java.lang.String" %>
<%@ attribute name="valores" required="true" type="java.lang.String" %>
<%@ attribute name="actualizacion" required="false" type="java.lang.String" %>

<div class="card h-100 shadow-sm">
  <div class="card-header ${colorHeader} text-white">
    <h5 class="card-title mb-0"><i class="${icono} me-2"></i>${titulo}</h5>
  </div>
  <div class="card-body">
    <div class="table-responsive">
      <table class="table table-bordered table-hover">
        <thead class="table-light">
          <tr>
            <%
              String[] columnasArray = columnas.split(",");
              for(String columna : columnasArray) {
            %>
              <th class="text-center"><%= columna.trim() %></th>
            <% } %>
          </tr>
        </thead>
        <tbody>
          <tr>
            <%
              String[] valoresArray = valores.split(",");
              for(String valor : valoresArray) {
            %>
              <td class="text-center align-middle fs-4"><%= valor.trim() %></td>
            <% } %>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="card-footer bg-light">
    <small class="text-muted">Actualizado: ${empty actualizacion ? 'hoy' : actualizacion}</small>
  </div>
</div>