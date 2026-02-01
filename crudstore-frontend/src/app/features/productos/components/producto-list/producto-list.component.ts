import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Producto } from '../../models/producto.model';
import { ProductoService } from '../../services/producto.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatChipsModule } from '@angular/material/chips';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-producto-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatChipsModule
  ],
  templateUrl: './producto-list.component.html',
  styleUrls: ['./producto-list.component.scss']
})
export class ProductoListComponent implements OnInit {
  productos: Producto[] = [];
  displayedColumns = ['name', 'descripcion', 'precio', 'categoria', 'estado', 'acciones'];
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private productoService: ProductoService
  ) {}

  ngOnInit(): void {
    const data = this.route.snapshot.data['productos'];
    if (data) {
      this.productos = data;
    }
    this.isLoading = false;
  }

  eliminar(id: number): void {
    if (!confirm('¿Eliminar producto?')) return;
    this.productoService.eliminar(id).subscribe(() => {
      this.productos = this.productos.filter(p => p.id !== id);
    });
  }
}
