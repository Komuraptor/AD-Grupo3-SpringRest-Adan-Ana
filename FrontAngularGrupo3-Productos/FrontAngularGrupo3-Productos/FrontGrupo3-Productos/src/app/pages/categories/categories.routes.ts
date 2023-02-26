import { Route } from '@angular/router';

export const ROUTES: Route[] = [
  {
    path: '',
    title: 'Categories',
    loadComponent: async () => (await import('./categories.page')).CategoriesPage,
  },
];
