import { Route } from '@angular/router';

export const ROUTES: Route[] = [
  {
    path: '',
    title: 'Products',
    loadComponent: async () => (await import('./products.page')).ProductsPage,
  },
];
