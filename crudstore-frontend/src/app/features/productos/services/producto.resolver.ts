import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ProductoService } from './producto.service';
import { Producto } from '../models/producto.model';

@Injectable({
  providedIn: 'root'
})
export class ProductoResolver implements Resolve<Producto[]> {
  constructor(private productoService: ProductoService) {}

  resolve(): Observable<Producto[]> {
    return this.productoService.listar().pipe(
      catchError(err => {
        console.error('Error cargando productos en resolver:', err);
        return of([]); // Devolver array vacío si falla
      })
    );
  }
}
