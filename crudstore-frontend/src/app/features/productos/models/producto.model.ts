export interface Producto {
  id: number;
  name: string;           
  descripcion: string;
  precio: number;         
  activo: boolean;        
  categoriaId: number;
  categoriaNombre: string;
  fechaCreacion: string;
}

// DTO para crear/actualizar (sin id, fechaCreacion ni activo)
export interface ProductoCreateDTO {
  name: string;          
  descripcion: string;
  precio: number;        
  categoriaId: number;   
}