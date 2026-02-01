import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Categoria } from '../../models/categoria.model';
import { CategoriaService } from '../../services/categoria.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-categoria-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule
  ],
  templateUrl: './categoria-list.component.html',
  styleUrls: ['./categoria-list.component.scss']
})
export class CategoriaListComponent implements OnInit {
  categorias: Categoria[] = [];
  displayedColumns = ['nombre', 'descripcion', 'acciones'];
  isLoading = true;

  constructor(
    private route: ActivatedRoute,
    private categoriaService: CategoriaService
  ) {}

  ngOnInit(): void {
    const data = this.route.snapshot.data['categorias'];
    if (data) {
      this.categorias = data;
    }
    this.isLoading = false;
  }

  eliminar(id: number): void {
    if (!confirm('¿Eliminar categoría?')) return;
    this.categoriaService.eliminar(id).subscribe(() => {
      this.categorias = this.categorias.filter(c => c.id !== id);
    });
  }
}
