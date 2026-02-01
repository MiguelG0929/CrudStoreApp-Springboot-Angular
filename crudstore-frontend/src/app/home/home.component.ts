// home.component.ts
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink, MatButtonModule],
  template: `
    <div style="text-align:center; margin-top:50px;">
      <h1>Bienvenido a Mi Tienda</h1>
      <div style="margin-top:30px;">
        <button mat-raised-button color="primary" routerLink="/categorias">
          Ver Categorías
        </button>
        <button mat-raised-button color="accent" routerLink="/productos">
          Ver Productos
        </button>
      </div>
    </div>
  `
})
export class HomeComponent {}
