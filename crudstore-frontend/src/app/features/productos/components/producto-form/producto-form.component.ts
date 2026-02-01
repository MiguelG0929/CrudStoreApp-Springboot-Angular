import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';

import { ProductoService } from '../../services/producto.service';
import { CategoriaService } from '../../../categorias/services/categoria.service';
import { ProductoCreateDTO } from '../../models/producto.model';
import { Categoria } from '../../../categorias/models/categoria.model';

@Component({
  selector: 'app-producto-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule
  ],
  templateUrl: './producto-form.component.html',
  styleUrls: ['./producto-form.component.scss']
})
export class ProductoFormComponent implements OnInit {
  form: FormGroup;
  isEdit = false;
  productoId?: number;
  categorias: Categoria[] = [];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private productoService: ProductoService,
    private categoriaService: CategoriaService
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]], // ← 'name', no 'nombre'
      descripcion: [''],
      precio: [0, [Validators.required, Validators.min(0.01)]],
      categoriaId: ['', Validators.required]
      // Nota: No incluimos 'activo' porque no va en el DTO de creación
    });
  }

  ngOnInit(): void {
    this.cargarCategorias();
    
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.isEdit = true;
        this.productoId = +params['id'];
        this.cargarProducto(this.productoId);
      }
    });
  }

  cargarCategorias(): void {
    this.categoriaService.listar().subscribe({
      next: data => this.categorias = data,
      error: err => console.error('Error cargando categorías:', err)
    });
  }

  cargarProducto(id: number): void {
    this.productoService.obtenerPorId(id).subscribe({
      next: (producto) => {
        // Mapeamos 'name' a 'name' (igual que en el formulario)
        this.form.patchValue({
          name: producto.name,
          descripcion: producto.descripcion,
          precio: producto.precio,
          categoriaId: producto.categoriaId
        });
      },
      error: (err) => {
        console.error('Error al cargar producto:', err);
        alert('Error al cargar el producto');
        this.router.navigate(['/productos']);
      }
    });
  }

  guardar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const producto: ProductoCreateDTO = this.form.value;
    console.log('Enviando producto:', producto); // Para debug

    if (this.isEdit && this.productoId) {
      this.productoService.actualizar(this.productoId, producto).subscribe({
        next: () => {
          alert('Producto actualizado correctamente');
          this.router.navigate(['/productos']);
        },
        error: (err) => {
          console.error('Error al actualizar:', err);
          alert('Error al actualizar el producto: ' + (err.error?.message || err.message));
        }
      });
    } else {
      this.productoService.crear(producto).subscribe({
        next: () => {
          alert('Producto creado correctamente');
          this.router.navigate(['/productos']);
        },
        error: (err) => {
          console.error('Error al crear:', err);
          alert('Error al crear el producto: ' + (err.error?.message || err.message));
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/productos']);
  }
}