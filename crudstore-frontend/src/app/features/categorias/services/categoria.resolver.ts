import { Injectable } from '@angular/core';
import { Resolve } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CategoriaService } from './categoria.service';
import { Categoria } from '../models/categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaResolver implements Resolve<Categoria[]> {
  constructor(private categoriaService: CategoriaService) {}

  resolve(): Observable<Categoria[]> {
    return this.categoriaService.listar().pipe(
      catchError(err => {
        console.error('Error cargando categorías en resolver:', err);
        return of([]); // Devolver array vacío si falla
      })
    );
  }
}
