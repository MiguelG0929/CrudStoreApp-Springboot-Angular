import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';

import { CategoriaListComponent } from './features/categorias/components/categoria-list/categoria-list.component';
import { CategoriaFormComponent } from './features/categorias/components/categoria-form/categoria-form.component';
import { ProductoListComponent } from './features/productos/components/producto-list/producto-list.component';
import { ProductoFormComponent } from './features/productos/components/producto-form/producto-form.component';

import { CategoriaResolver } from './features/categorias/services/categoria.resolver';
import { ProductoResolver } from './features/productos/services/producto.resolver';

export const routes: Routes = [
  { path: '', component: HomeComponent }, // Página de inicio

  {
    path: 'categorias',
    component: CategoriaListComponent,
    resolve: { categorias: CategoriaResolver } // Resuelve antes de mostrar
  },
  { path: 'categorias/nueva', component: CategoriaFormComponent },
  { path: 'categorias/editar/:id', component: CategoriaFormComponent },

  {
    path: 'productos',
    component: ProductoListComponent,
    resolve: { productos: ProductoResolver } // Resuelve antes de mostrar
  },
  { path: 'productos/nuevo', component: ProductoFormComponent },
  { path: 'productos/editar/:id', component: ProductoFormComponent },

  { path: '**', redirectTo: '' }
];
