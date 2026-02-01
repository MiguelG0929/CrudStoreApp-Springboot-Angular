import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, timeout, retry } from 'rxjs/operators';
import { Producto, ProductoCreateDTO } from '../models/producto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private readonly apiUrl = 'http://localhost:9525/api/productos';

  constructor(private http: HttpClient) {}

  // Lista SOLO productos activos (según tu backend)
  listar(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.apiUrl).pipe(
      timeout(5000), // ← NUEVO: Timeout de 5 segundos
      retry(1), // ← NUEVO: Reintentar una vez si falla
      catchError(error => {
        console.error('Error al listar productos:', error);
        return throwError(() => new Error('Error cargando productos'));
      })
    );
  }

  obtenerPorId(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.apiUrl}/${id}`);
  }

  crear(producto: ProductoCreateDTO): Observable<Producto> {
    return this.http.post<Producto>(this.apiUrl, producto);
  }

  actualizar(id: number, producto: ProductoCreateDTO): Observable<Producto> {
    return this.http.put<Producto>(`${this.apiUrl}/${id}`, producto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // EXTRA: Listar por categoría (si lo necesitas)
  listarPorCategoria(categoriaId: number): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.apiUrl}/categoria/${categoriaId}`);
  }
}