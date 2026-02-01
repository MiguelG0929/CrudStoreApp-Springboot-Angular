import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { CategoriaService } from '../../services/categoria.service';
import { Categoria } from '../../models/categoria.model';

@Component({
  selector: 'app-categoria-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule
  ],
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.scss']
})
export class CategoriaFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  categoriaId?: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private categoriaService: CategoriaService
  ) {
    // Inicializar formulario reactivo
    this.form = this.fb.group({
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      descripcion: [''],
      activa: [true]
    });
  }

  ngOnInit(): void {
    // Verificar si estamos editando (hay ID en la URL)
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEdit = true;
        this.categoriaId = +params['id']; // Convertir a número
        this.cargarCategoria(this.categoriaId);
      }
    });
  }

  cargarCategoria(id: number): void {
    this.categoriaService.obtenerPorId(id).subscribe({
      next: (categoria) => {
        // Llenar el formulario con los datos de la categoría
        this.form.patchValue(categoria);
      },
      error: (err) => {
        console.error('Error al cargar categoría:', err);
        this.router.navigate(['/categorias']);
      }
    });
  }

  guardar(): void {
    // Si el formulario no es válido, no hacer nada
    if (this.form.invalid) {
      // Marcar todos los campos como "touched" para mostrar errores
      this.form.markAllAsTouched();
      return;
    }

    const categoria: Categoria = this.form.value;

    if (this.isEdit && this.categoriaId) {
      // Actualizar categoría existente
      this.categoriaService.actualizar(this.categoriaId, categoria).subscribe({
        next: () => {
          alert('Categoría actualizada correctamente');
          this.router.navigate(['/categorias']);
        },
        error: (err) => {
          console.error('Error al actualizar:', err);
          alert('Error al actualizar la categoría');
        }
      });
    } else {
      // Crear nueva categoría
      this.categoriaService.crear(categoria).subscribe({
        next: () => {
          alert('Categoría creada correctamente');
          this.router.navigate(['/categorias']);
        },
        error: (err) => {
          console.error('Error al crear:', err);
          alert('Error al crear la categoría');
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/categorias']);
  }
}