import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, timeout, retry } from 'rxjs/operators';
import { Categoria } from '../models/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private readonly apiUrl = 'http://localhost:9525/api/categorias';

  constructor(private http: HttpClient) {}

  // Obtener todas las categorías
  listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.apiUrl).pipe(
      timeout(5000), // ← NUEVO: Timeout de 5 segundos
      retry(1), // ← NUEVO: Reintentar una vez si falla
      catchError(error => {
        console.error('Error al listar categorías:', error);
        return throwError(() => new Error('Error cargando categorías'));
      })
    );
  }

  // Obtener una categoría por ID
  obtenerPorId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.apiUrl}/${id}`);
  }

  // Crear nueva categoría
  crear(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.apiUrl, categoria);
  }

  // Actualizar categoría existente
  actualizar(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.apiUrl}/${id}`, categoria);
  }

  // Eliminar categoría
  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}